package io.kaoto.backend.api.resource.v0.response;

import io.kaoto.backend.model.step.Step;
import io.kaoto.backend.model.view.ViewDefinition;

import java.util.List;

/**
 * 🐱miniclass ViewDefinitionResourceResponse (ViewDefinitionResource)
 */
@Deprecated
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
