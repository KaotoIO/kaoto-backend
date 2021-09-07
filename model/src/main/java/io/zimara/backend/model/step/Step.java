package io.zimara.backend.model.step;

import io.zimara.backend.model.Metadata;

/**
 * 🐱class Step
 * 🐱aka List[Step]
 * 🐱inherits Metadata
 * Represents a step inside an integration.
 */
public class Step extends Metadata {

    private String subType = null;

    /*
     * 🐱property subtype: String
     *
     * Specifies the subtype (kamelet connector, camel connector,...)
     */
    public String getSubType() {
        return this.subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

}
