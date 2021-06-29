package io.zimara.backend.model;
/**
 * 🐱class Parameter
 * Represents a parameter on a form.
 */
public interface Parameter {
    /*
     * 🐱property label: String
     *
     * Human name for the view
     */
    public String getLabel();
    /*
     * 🐱property type: String
     *
     * Type of parameter: text, integer, float, boolean,...
     */
    public String getType();
    /*
     * 🐱property default: String
     *
     * Default value, if there is any
     */
    public Object getDefault();
    /*
     * 🐱property description: String
     *
     * Helping text describing the parameter
     */
    public String getDescription();

}
