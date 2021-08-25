package io.zimara.backend.api.resource.request;

import io.zimara.backend.model.step.Step;

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

    public Step[] getSteps() {
        return steps;
    }

    public void setSteps(Step[] steps) {
        this.steps = steps;
    }

    private Step[] steps;


}
