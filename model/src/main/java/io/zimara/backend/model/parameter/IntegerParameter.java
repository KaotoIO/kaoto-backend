package io.zimara.backend.model.parameter;

import io.zimara.backend.model.Parameter;

/**
 * 🐱class IntegerParameter
 * 🐱inherits Parameter
 * Parameter of type numeric integer.
 */
public class IntegerParameter implements Parameter {

    private String label;
    private Integer defaultValue;

    public IntegerParameter(String label) {
        this.label = label;
        this.defaultValue = 0;
    }
    public IntegerParameter(String label, Integer defaultValue) {
        this.label = label;
        this.defaultValue = defaultValue;
    }

    @Override
    public Integer getDefault() {
        return this.defaultValue;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public String getType() {
        return "INTEGER";
    }
}
