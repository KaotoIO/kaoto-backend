package io.kaoto.backend.deployment;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.dsl.base.ResourceDefinitionContext;
import io.kaoto.backend.api.service.deployment.generator.DeploymentGeneratorService;
import io.kaoto.backend.model.deployment.Integration;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Multi;
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
     * 🐱method getIntegrations: Integration[]
     * 🐱param namespace: String
     *
     * Returns the list of resources in a given namespace
     */
    public List<Integration> getIntegrations(final String namespace) {
        List<Integration> res = new ArrayList<>();

        for (var parser : parsers) {
            for (Class<? extends CustomResource> c
                    : parser.supportedCustomResources()) {
                try {
                    final var resources =
                            kubernetesClient.resources(c).inNamespace(getNamespace(namespace)).list().getItems();
                    for (CustomResource integration : resources) {
                        Integration i = new Integration();
                        i.setName(integration.getMetadata().getName());
                        i.setNamespace(getNamespace(namespace));
                        i.setStatus(parser.getStatus(integration));
                        i.setDate(integration.getMetadata()
                                .getCreationTimestamp());
                        i.setType(integration.getKind());
                        res.add(i);
                    }
                } catch (Exception e) {
                    log.warn("Error extracting the list of integrations.", e);
                }
            }
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
    public void start(final String input, final String namespace) {
        for (var parser : parsers) {
            log.trace("Trying parser for " + parser.identifier());
            log.trace(input);
            for (Class<? extends CustomResource> c
                    : parser.supportedCustomResources()) {
                log.trace("Trying with kind " + c.getSimpleName());
                CustomResource binding = tryParsing(input, c);
                if (binding != null) {
                    log.trace("This is a " + binding.getKind());
                    setName(binding, namespace);
                    try {
                        start(binding, namespace);
                        return;
                    } catch (KubernetesClientException e) {
                        log.debug("Either the binding is not right or the CRD"
                                + " is not valid: " + e.getMessage());
                    }
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
            binding.getMetadata().setName(
                    "integration-" + System.currentTimeMillis());
        }
        //force lowercase
        binding.getMetadata().setName(
                binding.getMetadata().getName()
                        .toLowerCase(Locale.ROOT));

        checkNoDuplicatedNames(namespace, binding);
    }

    private void checkNoDuplicatedNames(
            final String namespace,
            final CustomResource binding)
            throws IllegalArgumentException {
        //check no other deployment has the same name already
        for (Integration i
                : getIntegrations(getNamespace(namespace))) {
            if (i.getName()
                    .equalsIgnoreCase(
                            binding.getMetadata().getName())) {
                throw new IllegalArgumentException("There is an "
                        + "existing deployment with the same name: "
                        + binding.getMetadata().getName());
            }
        }
    }

    private CustomResource tryParsing(final String input,
                                      final Class<? extends CustomResource> c) {
        CustomResource binding = null;
        try {
            ObjectMapper yamlMapper =
                    new ObjectMapper(new YAMLFactory())
                            .configure(
                                    DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                                    false);

            binding = yamlMapper.readValue(input, c);
        } catch (Exception e) {
            log.trace("Tried with " + c.getSimpleName() + " and it didn't "
                    + "work.");
        }
        return binding;
    }

    /*
     * 🐱method stop
     * 🐱param namespace: String
     * 🐱param binding: CustomResource
     *
     * Stops the given CustomResource.
     */
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
        Yaml yaml = new Yaml(constructor);
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
    public CustomResource get(final String namespace, final String name) {
        CustomResource cr = null;
        for (var parser : parsers) {
            log.trace("Now trying with parser for " + parser.identifier());
            for (Class<? extends CustomResource> c
                    : parser.supportedCustomResources()) {
                log.trace("Trying to parse with " + c.getSimpleName());
                try {
                    cr = kubernetesClient.customResources(c)
                            .inNamespace(getNamespace(namespace))
                            .withName(name).get();
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
     * 🐱method logs: String
     * 🐱param namespace: String
     * 🐱param podName: String
     * 🐱param lines: Integer
     *
     * Returns the log of the given pod.
     */
    public String logs(final String namespace, final String podName,
                       final int lines) {
        return kubernetesClient.pods()
                .inNamespace(getNamespace(namespace))
                .withName(podName)
                .tailingLines(lines)
                .getLog(Boolean.TRUE);
    }

    /*
     * 🐱method streamlogs: String
     * 🐱param namespace: String
     * 🐱param name: String
     * 🐱param lines: Integer
     *
     * Streams the log of the given pod, starting with said number of lines.
     */
    @Blocking
    public Multi<String> streamlogs(final String namespace,
                                    final String name,
                                    final Integer lines) {
        final var out = new PipedOutputStream();
        final var in = new PipedInputStream();

        var list = kubernetesClient.pods()
                .inNamespace(getNamespace(namespace))
                .list().getItems();

        var integrationName = name;
        for (var pod : list) {
            if (pod.getMetadata().getName().startsWith(name)) {
                integrationName = pod.getMetadata().getName();
            }
        }

        final var podName = integrationName;

        managedExecutor.execute(() ->
                kubernetesClient.pods()
                        .inNamespace(getNamespace(namespace))
                        .withName(podName)
                        .tailingLines(lines)
                        .watchLog(out));

        managedExecutor.execute(() -> {
            try {
                out.connect(in);
            } catch (IOException e) {
                log.error("Pipeline broken", e);
            }
        });

        var reader = new BufferedReader(new InputStreamReader(in));

        Multi<String> logs = Multi.createFrom().generator(
                () -> 0, (n, emitter) -> {
                    try {
                        reader
                                .lines()
                                .forEach(
                                        line -> emitter.emit(line + "\n"));
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
            ns = "default";
        }
        return ns;
    }
}
