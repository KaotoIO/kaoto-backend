package io.kaoto.backend.model.step;

import io.kaoto.backend.model.Metadata;
import io.kaoto.backend.model.parameter.Parameter;

import java.util.List;
import java.util.Objects;

/**
 * 🐱class Step
 * 🐱aka List[Step]
 * 🐱inherits Metadata
 * Represents a step inside an integration.
 */
public class Step extends Metadata {

    private String icon;
    private List<Parameter> parameters;
    private String title;
    private String description;
    private String uuid;
    private String subType = null;

    /*
     * 🐱property subtype: String
     *
     * Specifies the subtype (kamelet connector, camel connector,...)
     */
    public String getSubType() {
        return this.subType;
    }

    public void setSubType(final String subType) {
        this.subType = subType;
    }

    /*
     * 🐱property description: String
     *
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    /*
     * 🐱property title: String
     *
     */
    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    /*
     * 🐱property parameters: List[Parameter]
     *
     */
    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(final List<Parameter> parameters) {
        this.parameters = parameters;
    }

    /*
     * 🐱property icon: String
     *
     */
    public String getIcon() {
        return icon;
    }

    public void setIcon(final String icon) {
        this.icon = icon;
    }

    /*
     * 🐱property UUID: String
     *
     * Volatile UUID to mark the relationship between
     * a viewDefinition and a step.
     *
     */
    public String getUUID() {
        return uuid;
    }

    public void setUUID(final String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "Step{" + '\n'
                + "  id='" + getId() + '\'' + '\n'
                + ", name='" + getName() + '\'' + '\n'
                + ", uuid=" + getUUID() + '\n'
                + ", title=" + getTitle() + '\n'
                + ", type=" + getType() + '\n'
                + ", subType=" + getSubType() + '\n'
                + '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Step)) {
            return false;
        }
        Step step = (Step) o;

        if (getTitle() != null
                ? !getTitle().equals(step.getTitle())
                : step.getTitle() != null) {
            return false;
        }
        if (getDescription() != null
                ? !getDescription().equals(step.getDescription())
                : step.getDescription() != null) {
            return false;
        }
            if (!Objects.equals(uuid, step.uuid)) {
            return false;
        }
        return getSubType() != null
                ? getSubType().equals(step.getSubType())
                : step.getSubType() == null;
    }

    @Override
    public int hashCode() {
        int result = getTitle() != null ? getTitle().hashCode() : 0;
        result = 31 * result + (getDescription() != null
                ? getDescription().hashCode() : 0);
        result = 31 * result + (uuid != null ? uuid.hashCode() : 0);
        result = 31 * result + (getSubType() != null
                ? getSubType().hashCode() : 0);
        return result;
    }
}
