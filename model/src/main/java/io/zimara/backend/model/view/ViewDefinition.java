package io.zimara.backend.model.view;

import io.zimara.backend.model.Metadata;
import io.zimara.backend.model.step.Step;

import java.util.List;
import java.util.Map;

/**
 * 🐱class ViewDefinition
 * 🐱inherits Metadata
 * 🐱relationship compositionOf ViewDefinitionConstraint, 0..n
 * 🐱aka List[ViewDefinition]
 * Represents a possible view the frontend can use to show data and metadata.
 *
 * It may have some constraints on when this view can be used.
 */
public class ViewDefinition extends Metadata {

    public ViewDefinition() {

    }

    public ViewDefinition(ViewDefinition v) {
        setId(v.getId());
        setName(v.getName());
        setType(v.getType());
    }

    /*
     * 🐱property steps: List[Step]
     *
     * List of Steps on this view. Usually used on integrations views.
     */
    private List<Step> steps = null;


    /*
     * 🐱property properties: Map[String, String]
     *
     * Properties useful for the user interface to visualize this view definition.
     */
    private Map<String, String> properties = null;

    /*
     * 🐱property constraints: List[ViewDefinitionConstraint]
     *
     * List of constraints on when to use this view. All mandatory constraints must be fulfilled.
     * If there are optional constraints, at least one of them should be fulfilled.
     */
    private List<ViewDefinitionConstraint> constraints = null;

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public List<ViewDefinitionConstraint> getConstraints() {
        return constraints;
    }

    public void setConstraints(List<ViewDefinitionConstraint> constraints) {
        this.constraints = constraints;
    }

    @Override
    public String toString() {
        return "ViewDefinition{" +
                "steps=" + steps +
                ", name='" + getName() + '\'' +
                ", type='" + getType() + '\'' +
                ", id='" + getId() + '\'' +
                ", properties=" + properties +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ViewDefinition)) return false;

        ViewDefinition that = (ViewDefinition) o;

        if (getSteps() != null ? !getSteps().equals(that.getSteps()) : that.getSteps() != null) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getType() != null ? !getType().equals(that.getType()) : that.getType() != null) return false;
        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        return getProperties() != null ? getProperties().equals(that.getProperties()) : that.getProperties() == null;
    }

    @Override
    public int hashCode() {
        int result = getSteps() != null ? getSteps().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        result = 31 * result + (getProperties() != null ? getProperties().hashCode() : 0);
        return result;
    }
}
