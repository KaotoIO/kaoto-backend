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

    public TextParameter(String label) {
        this.label = label;
        this.defaultValue = "";
    }
    public TextParameter(String label, String defaultValue) {
        this.label = label;
        this.defaultValue = defaultValue;
    }

    @Override
    public String getDefault() {
        return this.defaultValue;
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
