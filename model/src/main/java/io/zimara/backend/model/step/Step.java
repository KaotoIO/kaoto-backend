package io.zimara.backend.model.step;

import io.zimara.backend.model.Metadata;

/**
 * 🐱class Step
 * Represents a step inside an integration
 */
public interface Step extends Metadata {

    /*
     * 🐱property subtype: String
     *
     * If we need a subtype like kamelet connector, camel connector,...
     */
    public abstract String getSubType();

}
