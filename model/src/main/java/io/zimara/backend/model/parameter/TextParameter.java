package io.zimara.backend.model.parameter;

import io.zimara.backend.model.Parameter;

/**
 * 🐱class TextParameter
 * 🐱inherits Parameter
 * Parameter of type text.
 */
public class TextParameter implements Parameter {

    private String label;
    private String defaultValue;
    private String description;

    public TextParameter(String label) {
        this.label = label;
        this.defaultValue = "";
    }
    public TextParameter(String label, String defaultValue, String description) {
        this.label = label;
        this.defaultValue = defaultValue;
        this.description = description;
    }

    @Override
    public String getDefault() {
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
        return "TEXT";
    }

}
