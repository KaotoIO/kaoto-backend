package io.kaoto.backend.api.resource.v0.request;

import io.kaoto.backend.model.step.Step;

import java.util.Arrays;

@Deprecated(since = "0.3.0", forRemoval = true)
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
     * 🐱property steps: Step[]
     *
     * steps of the integration
     */
    private Step[] steps;

    public Step[] getSteps() {
        return Arrays.copyOf(steps, steps.length);
    }

    public void setSteps(final Step[] steps) {
        this.steps = steps;
    }
}
