package io.kaoto.backend.deployment;

import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.dsl.base.ResourceDefinitionContext;
import io.kaoto.backend.api.service.deployment.generator.DeploymentGeneratorService;
import io.kaoto.backend.api.service.deployment.generator.kamelet.KameletRepresenter;
import io.kaoto.backend.model.deployment.Deployment;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.extension.annotations.WithSpan;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.context.ManagedExecutor;
import org.jboss.logging.Logger;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 🐱miniclass ClusterService (DeploymentsResource)
 * 🐱relationship compositionOf DeploymentGeneratorService, 0..1
 *
 * 🐱section
 * Service to interact with the cluster. This is the utility class the
 * resource relies on to perform the operations.
 *
 */
@ApplicationScoped
public class ClusterService {

    private Logger log = Logger.getLogger(ClusterService.class);
    private KubernetesClient kubernetesClient;
    private Instance<DeploymentGeneratorService> parsers;
    private ManagedExecutor managedExecutor;

    @ConfigProperty(name = "kaoto.openshift.namespace",
            defaultValue = "default")
    private String namespace;

    @Inject
    public void setKubernetesClient(final KubernetesClient kubernetesClient) {
        this.kubernetesClient = kubernetesClient;
    }

    @Inject
    public void setParsers(final Instance<DeploymentGeneratorService> parsers) {
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
            res.addAll(parser.getResources(getNamespace(namespace), kubernetesClient));
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
            log.trace("Trying parser for " + parser.identifier());
            log.trace(input);

            CustomResource binding = parser.parse(input);
            if (binding != null) {
                log.trace("This is a " + binding.getKind());
                setName(binding, namespace);
                try {
                    start(binding, namespace);

                    Span span = Span.current();
                    if (span != null && binding != null) {
                        span.setAttribute("integration", binding.toString());
                    }
                    return;
                } catch (KubernetesClientException e) {
                    log.debug("Either the binding is not right or the CRD"
                            + " is not valid: " + e.getMessage());
                }
            }
        }

        throw new IllegalArgumentException("The provided CRD is invalid or "
                + "not supported.");
    }
    @WithSpan
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

    @WithSpan
    private void checkNoDuplicatedNames(final String namespace, final CustomResource binding, final Integer iterations)
            throws IllegalArgumentException {
        //This could lead to an infinite loop, very weird, but just in case
        if (iterations > 5) {
            throw new IllegalArgumentException("Couldn't find a proper renaming for the iteration.");
        }

        //check no other deployment has the same name already
        for (Deployment i : getResources(getNamespace(namespace))) {
            if (i.getName().equalsIgnoreCase(binding.getMetadata().getName())) {
                log.warn("There is an existing deployment with the same name: " + binding.getMetadata().getName());
                binding.getMetadata().setName(binding.getMetadata().getName() + System.currentTimeMillis());
                log.warn("Renaming to: " + binding.getMetadata().getName());
                checkNoDuplicatedNames(namespace, binding, iterations + 1);
                break;
            }
        }
    }

    /*
     * 🐱method stop
     * 🐱param namespace: String
     * 🐱param binding: CustomResource
     *
     * Stops the given CustomResource.
     */
    @WithSpan
    public void start(final CustomResource binding, final String namespace) {
        ResourceDefinitionContext context =
                new ResourceDefinitionContext.Builder()
                        .withNamespaced(true)
                        .withGroup(binding.getGroup())
                        .withKind(binding.getKind())
                        .withPlural(binding.getPlural())
                        .withVersion(binding.getVersion())
                        .build();

        var constructor = new Constructor(binding.getClass());
        Yaml yaml = new Yaml(constructor, new KameletRepresenter());
        kubernetesClient.genericKubernetesResources(context)
                .inNamespace(getNamespace(namespace))
                .load(new ByteArrayInputStream(
                        yaml.dumpAsMap(binding)
                                .getBytes(StandardCharsets.UTF_8)))
                .create();
    }

    /*
     * 🐱method start
     * 🐱param namespace: String
     * 🐱param name: String
     *
     * Starts the resource with the given name.
     */
    @WithSpan
    public boolean stop(final String name, final String namespace) {
        CustomResource cr = get(namespace, name);
        if (cr == null) {
            throw new NotFoundException("Resource with name " + name + " not "
                    + "found.");
        }
        return kubernetesClient.resources(cr.getClass())
                .inNamespace(getNamespace(namespace))
                .withName(name)
                .delete();
    }

    /*
     * 🐱method get: CustomResource
     * 🐱param namespace: String
     * 🐱param name: String
     *
     * Returns the given resource.
     */
    @WithSpan
    public CustomResource get(final String namespace, final String name) {
        CustomResource cr = null;
        for (var parser : parsers) {
            log.trace("Now trying with parser for " + parser.identifier());
            for (Class<? extends CustomResource> c : parser.supportedCustomResources()) {
                log.trace("Trying to parse with " + c.getSimpleName());
                try {
                    cr = kubernetesClient.customResources(c).inNamespace(getNamespace(namespace)).withName(name).get();
                } catch (Exception e) {
                    log.trace("This is not the proper kind: " + parser.identifier());
                }
                if (cr != null) {
                    log.trace("Found a customResource of kind " + cr.getKind() + " and name " + name);
                    break;
                }
            }
            if (cr != null) {
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
        final var out = new PipedOutputStream();
        final var in = new PipedInputStream();

        Pod pod = null;
        //We are going to assume no repeated names
        //When we find a pod, that's the one.
        for (var parser : parsers) {
            if (dsl == null || dsl.isEmpty() || dsl.equalsIgnoreCase(parser.identifier())) {
                pod = parser.getPod(namespace, name, kubernetesClient);
                if (pod != null) {
                    break;
                }
            }
        }

        if (pod == null) {
            throw new IllegalArgumentException("No running resource found in " + namespace + " with name " + name);
        }

        final Pod finalPod = pod;

        //Send stream of log to PipedOutputStream
        managedExecutor.execute(() ->
                kubernetesClient.pods()
                        .inNamespace(getNamespace(namespace))
                        .withName(finalPod.getMetadata().getName())
                        .tailingLines(lines)
                        .watchLog(out));

        //Pipe both streams
        managedExecutor.execute(() -> {
            try {
                out.connect(in);
            } catch (IOException e) {
                log.error("Pipeline broken", e);
            }
        });

        //Connect InputStream to response
        var reader = new BufferedReader(new InputStreamReader(in));

        Multi<String> logs = Multi.createFrom().generator(
                () -> 0, (n, emitter) -> {
                    try {
                        reader.lines().forEach(line -> emitter.emit(line + "\n"));
                        emitter.emit(reader.readLine() + "\n");
                    } catch (Exception e) {
                        emitter.fail(e);
                    } finally {
                        try {
                            in.close();
                            out.close();
                            reader.close();
                        } catch (Exception e) {
                            log.error(e);
                        }
                    }
                    emitter.complete();
                    return ++n;
                }
        );
        logs.runSubscriptionOn(managedExecutor);
        logs.onOverflow().buffer(3);

        return logs;
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
