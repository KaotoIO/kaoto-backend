package io.zimara.backend.model;

/**
 * 🐱class Metadata
 * Common class for all metadata. Used to simplify the implementation of catalogs.
 */
public interface Metadata {
    /*
     * 🐱property ID: String
     *
     * Unique identifier for this step in our whole Zimara environment
     */
    public abstract String getId();
    /*
     * 🐱property name: String
     *
     * This represents the first part of the camel connection string.
     * It may or may not be the same as the ID depending on the type of step.
     */

    public abstract String getName();

    /*
     * 🐱property type: String
     *
     * Type of step: transformation, connector
     */
    public abstract String getType();
}
