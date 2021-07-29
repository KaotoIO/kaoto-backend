package io.zimara.backend.model.view;

import io.zimara.backend.model.Metadata;
import io.zimara.backend.model.step.Step;

import java.util.List;

/**
 * 🐱class View
 * Represents a possible view the frontend can use to view data and metadata.
 */
public abstract class ViewDefinition implements Metadata {

    public ViewDefinition() {
    }
    /*
     * 🐱property steps: List<Step>
     *
     * List of steps for this viewDefinition
     */
    public abstract List<Step> getSteps();
}
