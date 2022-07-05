package io.kaoto.backend.deployment;

import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.base.ResourceDefinitionContext;
import io.kaoto.backend.api.service.deployment.generator.DeploymentGeneratorService;
import io.kaoto.backend.model.deployment.Integration;
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
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ClusterService {


    private Logger log = Logger.getLogger(ClusterService.class);
    private KubernetesClient kubernetesClient;
    private Instance<DeploymentGeneratorService> parsers;

    @Inject
    public void setKubernetesClient(final KubernetesClient kubernetesClient) {
        this.kubernetesClient = kubernetesClient;
    }

    @Inject
    public void setParsers(final Instance<DeploymentGeneratorService> parsers) {
        this.parsers = parsers;
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

    private String getNamespace(final String namespace) {
        String ns = namespace;
        if (ns == null || ns.isBlank()) {
            ns = "default";
        }
        return ns;
    }
}
