package io.kaoto.backend.deployment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.base.Strings;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.LogWatch;
import io.fabric8.kubernetes.client.dsl.base.ResourceDefinitionContext;
import io.kaoto.backend.camel.KamelHelper;
import io.kaoto.backend.api.service.dsl.DSLSpecification;
import io.kaoto.backend.model.deployment.Deployment;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.context.ManagedExecutor;
import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 🐱miniclass ClusterService (DeploymentsResource)
 * <p>
 * 🐱relationship compositionOf DeploymentGeneratorService, 0..1
 * <p>
 * 🐱section Service to interact with the cluster. This is the utility class the resource relies on to perform the
 * operations.
 */
@ApplicationScoped
public class ClusterService {

    private static final Logger LOG = Logger.getLogger(ClusterService.class);
    private KubernetesClient kubernetesClient;
    private Instance<DSLSpecification> parsers;
    private ManagedExecutor managedExecutor;

    @ConfigProperty(name = "kaoto.openshift.namespace",
            defaultValue = "default")
    private String namespace;

    @Inject
    public void setKubernetesClient(final KubernetesClient kubernetesClient) {
        this.kubernetesClient = kubernetesClient;
    }

    @Inject
    public void setParsers(final Instance<DSLSpecification> parsers) {
        this.parsers = parsers;
    }


    @Inject
    public void setManagedExecutor(
            final ManagedExecutor managedExecutor) {
        this.managedExecutor = managedExecutor;
    }

    /*
     * 🐱method getResources: Deployment[]
     * 🐱param namespace: String
     *
     * Returns the list of resources in a given namespace
     */
    @WithSpan
    public List<Deployment> getResources(final String namespace) {
        List<Deployment> res = new ArrayList<>();

        for (var parser : parsers) {
            res.addAll(parser.getDeploymentGeneratorService().getResources(getNamespace(namespace), kubernetesClient));
        }

        return res;
    }

    /*
     * 🐱method start
     * 🐱param namespace: String
     * 🐱param input: String
     *
     * Deploys the given input resource
     *
     */
    @WithSpan
    public void start(final String input, final String namespace) {
        for (var parser : parsers) {
            CustomResource binding = parser.getDeploymentGeneratorService().parse(input);
            if (binding != null) {
                LOG.trace("This is a " + binding.getKind());
                setName(binding, namespace);
                try {
                    start(binding, namespace);

                    Span span = Span.current();
                    if (span != null && binding != null) {
                        span.setAttribute("integration", binding.toString());
                    }
                    return;
                } catch (Exception e) {
                    LOG.debug("Either the binding is not right or the CRD is not valid: " + e.getMessage());
                }
            }
        }

        throw new IllegalArgumentException("The provided CRD is invalid or "
                + "not supported.");
    }

    private void setName(final CustomResource binding, final String namespace)
            throws IllegalArgumentException {
        if (binding.getMetadata() == null) {
            binding.setMetadata(new ObjectMeta());
        }
        final var name = binding.getMetadata().getName();
        if (name == null || name.isEmpty()) {
            binding.getMetadata().setName("integration-" + System.currentTimeMillis());
        }
        //force lowercase
        binding.getMetadata().setName(binding.getMetadata().getName().toLowerCase(Locale.ROOT));

        checkNoDuplicatedNames(namespace, binding, 0);
    }


    private void checkNoDuplicatedNames(final String namespace, final CustomResource binding, final Integer iterations)
            throws IllegalArgumentException {
        //This could lead to an infinite loop, very weird, but just in case
        if (iterations > 5) {
            throw new IllegalArgumentException("Couldn't find a proper renaming for the iteration.");
        }

        //check no other deployment has the same name already
        for (Deployment i : getResources(getNamespace(namespace))) {
            if (i.getName().equalsIgnoreCase(binding.getMetadata().getName())) {
                LOG.warn("There is an existing deployment with the same name: " + binding.getMetadata().getName());
                binding.getMetadata().setName(binding.getMetadata().getName() + System.currentTimeMillis());
                LOG.warn("Renaming to: " + binding.getMetadata().getName());
                checkNoDuplicatedNames(namespace, binding, iterations + 1);
                break;
            }
        }
    }

    /*
     * 🐱method start
     * 🐱param namespace: String
     * 🐱param binding: CustomResource
     *
     * Starts the given CustomResource.
     */
    @WithSpan
    public void start(final CustomResource binding, final String namespace) throws JsonProcessingException {
        ResourceDefinitionContext context =
                new ResourceDefinitionContext.Builder()
                        .withNamespaced(true)
                        .withGroup(binding.getGroup())
                        .withKind(binding.getKind())
                        .withPlural(binding.getPlural())
                        .withVersion(binding.getVersion())
                        .build();

        kubernetesClient.genericKubernetesResources(context)
                .inNamespace(getNamespace(namespace))
                .load(new ByteArrayInputStream(KamelHelper.YAML_MAPPER.writeValueAsBytes(binding)))
                .create();
    }

    /*
     * 🐱method stop
     * 🐱param namespace: String
     * 🐱param name: String
     *
     * Stops the resource with the given name.
     */
    @WithSpan
    public boolean stop(final String name, final String namespace, final String type) {
        CustomResource cr = get(namespace, name, type);

        if (cr == null) {
            throw new NotFoundException("Resource with name " + name + " not found.");
        }

        LOG.trace("Going to delete a " + cr.getClass() + " in " + getNamespace(namespace) + " with name " + name);

        return !kubernetesClient.resources(cr.getClass()).inNamespace(getNamespace(namespace))
                .withName(name).delete().isEmpty();
    }

    /*
     * 🐱method get: CustomResource
     * 🐱param namespace: String
     * 🐱param name: String
     *
     * Returns the given resource.
     */
    @WithSpan
    public CustomResource get(final String namespace, final String name, final String type) {

        CustomResource cr = null;
        var crs = getResources(namespace);

        for (var resource : crs) {
            if (resource.getName().equalsIgnoreCase(name)
                    && (type == null || resource.getType().equalsIgnoreCase(type))) {
                cr = resource.getResource();
                break;
            }
        }

        return cr;
    }

    /*
     * 🐱method streamlogs: String
     * 🐱param namespace: String
     * 🐱param name: String
     * 🐱param lines: Integer
     *
     * Streams the log of the given pod, starting with said number of lines.
     *
     *
     */
    @WithSpan
    @Blocking
    public Multi<String> streamlogs(final String namespace,
                                    final String name,
                                    final String dsl,
                                    final Integer lines) {
        Pod pod = null;
        //We are going to assume no repeated names
        //When we find a pod, that's the one.
        for (var parser : parsers) {
            if (Strings.isNullOrEmpty(dsl) || dsl.equalsIgnoreCase(parser.identifier())) {
                pod = parser.getDeploymentGeneratorService().getPod(namespace, name, kubernetesClient);
                if (pod != null) {
                    break;
                }
            }
        }

        if (pod == null) {
            throw new IllegalArgumentException("No running resource found in " + namespace + " with name " + name);
        }

        final var podResource = kubernetesClient.pods().inNamespace(getNamespace(namespace))
                .withName(pod.getMetadata().getName());
        LogWatch watch = podResource.tailingLines(lines).watchLog();
        BufferedReader reader = new BufferedReader(new InputStreamReader(watch.getOutput()));
        AtomicBoolean closed = new AtomicBoolean(false);
        try {
            Multi<String> logs = Multi.createFrom().generator(
                    () -> 0, (n, emitter) -> {
                        try {
                            String line = reader.readLine();
                            if (line == null) {
                                // The stream has reached EOF without reading any characters.
                                // This can happen when the pod has been deleted.
                                // A better solution would be to watch the pod resource,
                                // while streaming the logs, and propagate the error up to
                                // the client.
                                emitter.complete();
                                return n;
                            }
                            emitter.emit(line + "\n");
                            return ++n;
                        } catch (Exception e) {
                            if (!closed.get()) {
                                LOG.error("Error reading log stream", e);
                                emitter.fail(e);
                            } else {
                                emitter.complete();
                            }
                        }
                        return n;
                    }
            );
            return logs.runSubscriptionOn(managedExecutor)
                    .onTermination().invoke(() -> {
                        closed.set(true);
                        watch.close();
                        try {
                            reader.close();
                        } catch (Exception e) {
                            LOG.error("Error closing log stream", e);
                        }
                    });
        } catch (Exception e) {
            LOG.error("Error watching log stream", e);
        }
        return Multi.createFrom().nothing();
    }

    private String getNamespace(final String namespace) {
        String ns = namespace;
        if (ns == null || ns.isBlank()) {
            ns = this.namespace;
        }
        return ns;
    }

    public String getDefaultNamespace() {
        return this.namespace;
    }
}
