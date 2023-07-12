package io.kaoto.backend.camel.service.deployment.generator;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.kaoto.backend.api.service.deployment.generator.DeploymentGeneratorService;

public abstract class AbstractDeploymentGeneratorService implements DeploymentGeneratorService {


    @Override
    public Pod getPod(final String namespace, final String name, final KubernetesClient kubernetesClient) {

        for (var d : getResources(namespace, kubernetesClient)) {
            if (d.getName().equalsIgnoreCase(name)) {
                var pods = kubernetesClient.pods()
                        .inNamespace(namespace)
                        .withLabel("camel.apache.org/integration=" + d.getName())
                        .list().getItems();

                for (var pod : pods) {
                    if (pod.getStatus() != null
                            && pod.getStatus().getPhase() != null
                            && (pod.getStatus().getPhase().equalsIgnoreCase("Running")
                            || pod.getStatus().getPhase().equalsIgnoreCase("Succeeded"))) {
                        return pod;
                    }
                }

            }
        }

        return null;
    }
}
