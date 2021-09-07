package io.zimara.backend.api.resource.request;

import io.zimara.backend.model.step.Step;
import io.zimara.backend.model.step.kamelet.KameletStep;

/**
 * 🐱class DeploymentResourceYamlRequest
 */
public class DeploymentResourceYamlRequest {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public KameletStep[] getSteps() {
        return steps;
    }

    public void setSteps(KameletStep[] steps) {
        this.steps = steps;
    }

    private KameletStep[] steps;


}
