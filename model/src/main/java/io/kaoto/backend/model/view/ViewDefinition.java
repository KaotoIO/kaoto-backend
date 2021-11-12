package io.kaoto.backend.model.view;

import io.kaoto.backend.model.Metadata;

import java.util.List;
import java.util.Map;

/**
 * 🐱class ViewDefinition
 * 🐱inherits Metadata
 * 🐱relationship compositionOf ViewDefinitionConstraint, 0..n
 * 🐱aka List[ViewDefinition]
 * Represents a possible view the frontend can use to show data and metadata.
 * It may have some constraints on when this view can be used.
 */
public class ViewDefinition extends Metadata {

    public ViewDefinition() {

    }

    public ViewDefinition(final ViewDefinition v) {
        setId(v.getId());
        setName(v.getName());
        setType(v.getType());
        setUrl(v.getUrl());
        setScope(v.getScope());
        setModule(v.getModule());
    }

    /*
     * 🐱property properties: Map[String, String]
     *
     * Properties useful for the user interface to visualize
     * this view definition.
     */
    private Map<String, String> properties = null;

    /*
     * 🐱property step: Step
     *
     * If this extension relates to a specific step (StepExtension),
     * point to it via the UUID of the step in this integration.
     *
     */
    private String step = null;

    /*
     * 🐱property url: String
     *
     * If this is a custom step, url to fetch the view.
     *
     */
    private String url = null;

    /*
     * 🐱property constraints: List[ViewDefinitionConstraint]
     *
     * List of constraints on when to use this view.
     * All mandatory constraints must be fulfilled.
     * If there are optional constraints, at least
     * one of them should be fulfilled.
     */
    private List<ViewDefinitionConstraint> constraints = null;

    private String scope = null;

    private String module = null;

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(final Map<String, String> properties) {
        this.properties = properties;
    }

    public List<ViewDefinitionConstraint> getConstraints() {
        return constraints;
    }

    public void setConstraints(
            final List<ViewDefinitionConstraint> constraints) {
        this.constraints = constraints;
    }

    public String getStep() {
        return step;
    }

    public void setStep(final String step) {
        this.step = step;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(final String scope) {
        this.scope = scope;
    }

    public String getModule() {
        return module;
    }

    public void setModule(final String module) {
        this.module = module;
    }

    @Override
    public String toString() {
        return "ViewDefinition{"
                + ", name='" + getName() + '\''
                + ", type='" + getType() + '\''
                + ", id='" + getId() + '\''
                + ", url='" + getUrl() + '\''
                + ", step='" + getStep() + '\''
                + ", properties=" + properties
                + '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ViewDefinition)) {
            return false;
        }

        ViewDefinition that = (ViewDefinition) o;

        if (getName() != null
                ? !getName().equals(that.getName())
                : that.getName() != null) {
            return false;
        }
        if (getType() != null
                ? !getType().equals(that.getType())
                : that.getType() != null) {
            return false;
        }
        if (getId() != null
                ? !getId().equals(that.getId())
                : that.getId() != null) {
            return false;
        }
        return getProperties() != null
                ? getProperties().equals(that.getProperties())
                : that.getProperties() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        result = 31 * result + (getProperties() != null
                ? getProperties().hashCode() : 0);
        return result;
    }

    @Override
    public ViewDefinition clone() {
        return (ViewDefinition) super.clone();
    }
}
