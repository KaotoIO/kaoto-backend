package io.zimara.backend.model.view;

import io.zimara.backend.model.Step;
import io.zimara.backend.model.View;

import java.util.List;
/**
 * 🐱class IntegrationView
 * 🐱inherits View
 * Represents an integration view, composed by steps.
 */
public class IntegrationView implements View {

    public IntegrationView(List<Step> steps, String name) {
        this.steps = steps;
        this.name = name;
    }

    /*
     * 🐱property steps: List<Step>
     *
     * List of Steps on this integration
     */
    private List<Step> steps;
    private String name;

    @Override
    public String getType() {
        return "INTEGRATION";
    }

    @Override
    public String getName() {
        return name;
    }

    public List<Step> getSteps() {
        return steps;
    }
}
