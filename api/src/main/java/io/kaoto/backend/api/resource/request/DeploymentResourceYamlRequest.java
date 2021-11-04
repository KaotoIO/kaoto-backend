package io.kaoto.backend.api.resource.request;

import io.kaoto.backend.model.step.kamelet.KameletStep;

/**
 * 🐱miniclass DeploymentResourceYamlRequest (DeploymentResource)
 */
public class DeploymentResourceYamlRequest {

    /*
     * 🐱property name: String
     *
     * Name of the integration
     */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    /*
     * 🐱property steps: KameletStep[]
     *
     * steps of the integration
     */
    private KameletStep[] steps;

    public KameletStep[] getSteps() {
        return steps;
    }

    public void setSteps(final KameletStep[] steps) {
        this.steps = steps;
    }
}
