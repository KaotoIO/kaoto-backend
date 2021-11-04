package io.kaoto.backend.model;

/**
 * 🐱class Metadata
 * 🐱aka List[Metadata]
 *
 * Common class for all metadata.
 * Used to simplify the implementation of catalogs.
 *
 */
public abstract class Metadata {

    private String name = null;
    private String type = "UNDEFINED";
    private String id = null;

    /*
     * 🐱property ID: String
     *
     * Unique identifier for this step in our whole Kaoto environment
     */
    public String getId() {
        return this.id;
    }
    /*
     * 🐱property name: String
     *
     * This represents the first part of the camel connection string.
     * It may or may not be the same as the ID depending on the type of step.
     */

    public String getName() {
        return this.name;
    }

    /*
     * 🐱property type: String
     *
     * Type of step: transformation, connector
     */
    public String getType() {
        return this.type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setId(final String id) {
        this.id = id;
    }

}
