package io.kaoto.backend.deployment;

import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.kaoto.backend.model.deployment.Integration;
import io.kaoto.backend.model.deployment.kamelet.KameletBinding;
import org.jboss.logging.Logger;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.constructor.ConstructorException;
import org.yaml.snakeyaml.introspector.BeanAccess;
import org.yaml.snakeyaml.representer.Representer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ClusterService {

    private KubernetesClient kubernetesClient;

    private Logger log = Logger.getLogger(ClusterService.class);

    @Inject
    public void setKubernetesClient(final KubernetesClient kubernetesClient) {
        this.kubernetesClient = kubernetesClient;
    }

    public List<Integration> getIntegrations(final String namespace) {
        List<Integration> res = new ArrayList<>();
        try {
            final var resources =
                    kubernetesClient.resources(KameletBinding.class)
                            .inNamespace(getNamespace(namespace))
                            .list().getItems();
            for (KameletBinding integration : resources) {
                Integration i = new Integration();
                i.setName(integration.getMetadata().getName());
                i.setRunning(true);
                i.setResource(integration);
                res.add(i);
            }
        } catch (Exception e) {
            log.warn("Error extracting the list of integrations.", e);
        }
        return res;
    }

    public boolean start(final String input, final String namespace) {
        try {
            var constructor = new Constructor(KameletBinding.class);
            Representer representer = new Representer();
            representer.getPropertyUtils().setSkipMissingProperties(true);
            representer.getPropertyUtils().setAllowReadOnlyProperties(true);
            representer.getPropertyUtils().setBeanAccess(BeanAccess.FIELD);
            constructor.getPropertyUtils().setSkipMissingProperties(true);
            constructor.getPropertyUtils().setAllowReadOnlyProperties(true);
            constructor.getPropertyUtils().setBeanAccess(BeanAccess.FIELD);
            Yaml yaml = new Yaml(constructor, representer);
            KameletBinding binding = yaml.load(input);
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
            log.warn("Error starting the integration.", e);
            return false;
        }
    }

    public boolean start(final KameletBinding binding, final String namespace) {
        try {
            kubernetesClient.resources(KameletBinding.class)
                    .inNamespace(getNamespace(namespace))
                    .createOrReplace(binding);
        } catch (Exception e) {
            log.warn("Error starting the integration.", e);
            return false;
        }

        return true;
    }

    public boolean stop(final Integration i, final String namespace) {
        return kubernetesClient.resources(KameletBinding.class)
                .inNamespace(getNamespace(namespace))
                .withName(i.getName())
                .delete();
    }

    private String getNamespace(final String namespace) {
        String ns = namespace;
        if (ns == null || ns.isBlank()) {
            ns = "default";
        }
        return ns;
    }
}
