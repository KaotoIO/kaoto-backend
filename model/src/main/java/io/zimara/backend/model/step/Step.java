package io.zimara.backend.model.step;

import io.zimara.backend.model.Metadata;

/**
 * 🐱class Step
 * 🐱aka List[Step]
 * 🐱inherits Metadata
 * Represents a step inside an integration.
 */
public interface Step extends Metadata {

    /*
     * 🐱property subtype: String
     *
     * Specifies the subtype (kamelet connector, camel connector,...)
     */
    public abstract String getSubType();

}
