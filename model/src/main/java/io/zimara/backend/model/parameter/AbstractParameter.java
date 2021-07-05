package io.zimara.backend.model.parameter;

import io.zimara.backend.model.Parameter;

public abstract class AbstractParameter implements Parameter {

    private final String label;
    private String description;

    AbstractParameter(String label) {
        this.label = label;
    }

    AbstractParameter(String label, String description) {
        this.label = label;
        this.description = description;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

}
