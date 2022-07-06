package io.kaoto.backend.deployment;

import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.base.ResourceDefinitionContext;
import io.kaoto.backend.api.service.deployment.generator.DeploymentGeneratorService;
import io.kaoto.backend.model.deployment.Integration;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.context.ManagedExecutor;
import org.jboss.logging.Logger;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.constructor.ConstructorException;
import org.yaml.snakeyaml.introspector.BeanAccess;
import org.yaml.snakeyaml.representer.Representer;

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

    public List<Integration> getIntegrations(final String namespace) {
        List<Integration> res = new ArrayList<>();

        for (var parser : parsers) {
            for (Class<? extends CustomResource> c
                    : parser.supportedCustomResources()) {
                try {
                    final var resources =
                            kubernetesClient.resources(c)
                                    .inNamespace(getNamespace(namespace))
                                    .list().getItems();
                    for (CustomResource integration : resources) {
                        Integration i = new Integration();
                        i.setName(integration.getMetadata().getName());
                        i.setNamespace(getNamespace(namespace));
                        i.setStatus(integration.getStatus());
                        i.setDate(integration.getMetadata()
                                .getCreationTimestamp());
                        res.add(i);
                    }
                } catch (Exception e) {
                    log.warn("Error extracting the list of integrations.", e);
                }
            }
        }
        return res;
    }

    public boolean start(final String input, final String namespace) {
        for (var parser : parsers) {
            for (Class<? extends CustomResource> c
                    : parser.supportedCustomResources()) {
                try {
                    var constructor = new Constructor(c);
                    Representer representer = new Representer();
                    representer.getPropertyUtils()
                            .setSkipMissingProperties(true);
                    representer.getPropertyUtils()
                            .setAllowReadOnlyProperties(true);
                    representer.getPropertyUtils()
                            .setBeanAccess(BeanAccess.FIELD);
                    constructor.getPropertyUtils()
                            .setSkipMissingProperties(true);
                    constructor.getPropertyUtils()
                            .setAllowReadOnlyProperties(true);
                    constructor.getPropertyUtils()
                            .setBeanAccess(BeanAccess.FIELD);
                    Yaml yaml = new Yaml(constructor, representer);
                    CustomResource binding = yaml.load(input);
                    if (binding.getMetadata() == null) {
                        binding.setMetadata(new ObjectMeta());
                    }
                    final var name = binding.getMetadata().getName();
                    if (name == null || name.isEmpty()) {
                        binding.getMetadata().setName(
                                "integration-" + System.currentTimeMillis());
                    }
                    return start(binding, namespace);
                } catch (ConstructorException e) {
                    log.trace("Tried with " + c.getName() + " and it didn't "
                            + "work.");
                }
            }
        }

        return false;
    }

    public boolean start(final CustomResource binding, final String namespace) {
        try {
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
            return true;
        } catch (Exception e) {
            log.error("Error starting the integration.", e);
        }
        return false;
    }

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

    public CustomResource get(final String namespace, final String name) {
        CustomResource cr = null;
        for (var parser : parsers) {
            for (Class<? extends CustomResource> c
                    : parser.supportedCustomResources()) {
                cr = kubernetesClient.customResources(c)
                        .inNamespace(getNamespace(namespace))
                        .withName(name).get();
                if (cr != null) {
                    break;
                }
            }
            if (cr != null) {
                break;
            }
        }
        return cr;
    }

    public String logs(final String namespace, final String podName,
                       final int lines) {
        return kubernetesClient.pods()
                .inNamespace(getNamespace(namespace))
                .withName(podName)
                .tailingLines(lines)
                .getLog(Boolean.TRUE);
    }

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
                                    line ->  emitter.emit(line + "\n"));
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
