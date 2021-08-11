package io.zimara.backend.model.parameter;

import io.zimara.backend.model.Parameter;

/**
 * 🐱class AbstractParameter
 * 🐱miniclass AbstractParameter (Parameter)
 * 🐱inherits Parameter
 * Implements common parameter actions
 */
public class AbstractParameter<T> implements Parameter<T> {

    private final String label;
    private final String description;
    private final String id;
    private final String type;
    private T value;
    private final T defaultValue;

    public AbstractParameter(String id, String label, String description, T defaultValue, String type) {
        this.id = id;
        this.label = label;
        this.description = description;
        this.defaultValue = defaultValue;
        this.type = type;
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
    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public T getValue() {
        return this.value;
    }

    @Override
    public T getDefault() {
        return this.defaultValue;
    }

    @Override
    public String getType() {
        return this.type;
    }
}
