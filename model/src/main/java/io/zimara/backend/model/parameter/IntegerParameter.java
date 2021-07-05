package io.zimara.backend.model.parameter;

/**
 * 🐱class IntegerParameter
 * 🐱inherits Parameter
 * Parameter of type numeric integer.
 */
public class IntegerParameter extends AbstractParameter {

    private final Integer defaultValue;

    public IntegerParameter(String label) {
        this(label, 0, "");
    }

    public IntegerParameter(String label, Integer defaultValue, String description) {
        super(label, description);
        this.defaultValue = defaultValue;
    }

    @Override
    public Integer getDefault() {
        return this.defaultValue;
    }

    @Override
    public String getType() {
        return "INTEGER";
    }
}
