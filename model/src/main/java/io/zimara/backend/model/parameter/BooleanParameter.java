package io.zimara.backend.model.parameter;

import io.zimara.backend.model.Parameter;

/**
 * 🐱class BooleanParameter
 * 🐱inherits Parameter
 * Parameter of type boolean
 */
public class BooleanParameter implements Parameter {

    private String label;
    private Boolean defaultValue;
    private String description;

    public BooleanParameter(String label) {
        this.label = label;
        this.defaultValue = false;
    }
    public BooleanParameter(String label, Boolean defaultValue, String description) {
        this.label = label;
        this.defaultValue = defaultValue;
        this.description = description;
    }

    @Override
    public Boolean getDefault() {
        return this.defaultValue;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public String getType() {
        return "BOOLEAN";
    }
}
