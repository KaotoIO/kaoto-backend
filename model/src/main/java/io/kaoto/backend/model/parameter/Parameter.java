package io.kaoto.backend.model.parameter;

/**
 * 🐱class Parameter
 * 🐱aka List[Parameter]
 *
 * Represents a parameter of a step in an integration.
 * These parameters could be used on the UI to configure the step.
 */
public final class Parameter<T> {

    private String label;
    private String description;
    private String id;
    private String type;
    private T value;
    private T defaultValue;

    public Parameter(final String id, final String label,
                     final String description,
                     final T defaultValue, final String type) {
        this.id = id;
        this.label = label;
        this.description = description;
        this.defaultValue = defaultValue;
        this.type = type;
    }

    public Parameter() {
    }

    /*
     * 🐱property description: String
     *
     * Helping text describing the parameter
     */
    public String getDescription() {
        return this.description;
    }

    /*
     * 🐱property label: String
     *
     * Human name for the view
     */
    public String getLabel() {
        return this.label;
    }

    /*
     * 🐱property id: String
     *
     * Identifier of the parameter
     */
    public String getId() {
        return this.id;
    }

    public void setValue(final T value) {
        this.value = value;
    }

    /*
     * 🐱property value: String
     *
     * Actual value of this parameter.
     * Used when describing a configured element.
     *
     */
    public T getValue() {
        return this.value;
    }

    /*
     * 🐱property default: String
     *
     * Default value, if there is any
     */
    public T getDefault() {
        return this.defaultValue;
    }

    /*
     * 🐱property type: String
     *
     * Type of parameter: text, integer, float, boolean,...
     */
    public String getType() {
        return this.type;
    }
}
