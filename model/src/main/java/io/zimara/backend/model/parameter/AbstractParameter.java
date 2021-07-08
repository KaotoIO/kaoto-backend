package io.zimara.backend.model.parameter;

import io.zimara.backend.model.Parameter;

public abstract class AbstractParameter implements Parameter {

    private final String label;
    private String description;
    private String id;
    private Object value;

    AbstractParameter(String label) {
        this.label = label;
    }

    AbstractParameter(String id, String label, String description) {
        this.id = id;
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

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setValue(Object value) {
        this.value = value;
    }
    @Override
    public Object getValue() {
        return this.value;
    }

}
