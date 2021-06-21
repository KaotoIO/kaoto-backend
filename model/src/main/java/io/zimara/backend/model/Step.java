package io.zimara.backend.model;
/**
 * 🐱class Step
 * Represents a step inside an integration
 */
public interface Step {
    /*
     * 🐱property type: String
     *
     * Type of step: transformation, connector
     */
    public String getType();
    /*
     * 🐱property subtype: String
     *
     * If we need a subtype like kamelet connector, camel connector,...
     */
    public String getSubType();
}
