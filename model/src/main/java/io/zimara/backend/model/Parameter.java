package io.zimara.backend.model;

/**
 * 🐱class Parameter
 * 🐱aka List[Parameter]
 *
 * Represents a parameter of a step in an integration. This parameters could be used on the UI to configure the step.
 */
public interface Parameter<T> {
    /*
     * 🐱property label: String
     *
     * Human name for the view
     */
    String getLabel();

    /*
     * 🐱property id: String
     *
     * Identifier of the parameter
     */
    String getId();

    /*
     * 🐱property type: String
     *
     * Type of parameter: text, integer, float, boolean,...
     */
    String getType();

    /*
     * 🐱property default: String
     *
     * Default value, if there is any
     */
    T getDefault();

    /*
     * 🐱property description: String
     *
     * Helping text describing the parameter
     */
    String getDescription();

    void setValue(T value);

    /*
     * 🐱property value: String
     *
     * Actual value of this parameter. Used when describing a configured element.
     */
    T getValue();
}
