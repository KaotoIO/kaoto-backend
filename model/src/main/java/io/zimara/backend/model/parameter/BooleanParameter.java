package io.zimara.backend.model.parameter;

import io.zimara.backend.model.Parameter;

/**
 * 🐱class BooleanParameter
 * 🐱inherits Parameter
 * Parameter of type boolean
 */
public class BooleanParameter extends AbstractParameter {

    private final Boolean defaultValue;

    public BooleanParameter(String label) {
        this(label, false, "");
    }

    public BooleanParameter(String label, Boolean defaultValue, String description) {
        super(label, description);
        this.defaultValue = defaultValue;
    }

    @Override
    public Boolean getDefault() {
        return this.defaultValue;
    }

    @Override
    public String getType() {
        return "BOOLEAN";
    }
}
