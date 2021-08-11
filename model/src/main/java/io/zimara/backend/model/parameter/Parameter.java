package io.zimara.backend.model.parameter;

/**
 * 🐱class Parameter
 * 🐱aka List[Parameter]
 *
 * Represents a parameter of a step in an integration. These parameters could be used on the UI to configure the step.
 */
public class Parameter<T> {

    private final String label;
    private final String description;
    private final String id;
    private final String type;
    private T value;
    private final T defaultValue;

    public Parameter(String id, String label, String description, T defaultValue, String type) {
        this.id = id;
        this.label = label;
        this.description = description;
        this.defaultValue = defaultValue;
        this.type = type;
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

    public void setValue(T value) {
        this.value = value;
    }

    /*
     * 🐱property value: String
     *
     * Actual value of this parameter. Used when describing a configured element.
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
