package io.zimara.backend.model.parameter;

/**
 * 🐱class TextParameter
 * 🐱inherits Parameter
 * Parameter of type text.
 */
public class TextParameter extends AbstractParameter {

    private final String defaultValue;

    public TextParameter(String label) {
        this(label, label, "", "");
    }

    public TextParameter(String id, String label, String defaultValue, String description) {
        super(id, label, description);
        this.defaultValue = defaultValue;
    }

    @Override
    public String getDefault() {
        return this.defaultValue;
    }

    @Override
    public String getType() {
        return "TEXT";
    }

}
