package io.zimara.backend.api.resource.response;

import io.zimara.backend.model.step.Step;
import io.zimara.backend.model.view.ViewDefinition;

import java.util.List;

/**
 * 🐱miniclass ViewDefinitionResourceResponse (ViewDefinitionResource)
 */
public class ViewDefinitionResourceResponse {
    private List<Step> steps;
    private List<ViewDefinition> views;

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(final List<Step> steps) {
        this.steps = steps;
    }

    public List<ViewDefinition> getViews() {
        return views;
    }

    public void setViews(final List<ViewDefinition> views) {
        this.views = views;
    }
}
