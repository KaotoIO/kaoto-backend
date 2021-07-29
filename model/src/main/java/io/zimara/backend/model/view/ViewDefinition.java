package io.zimara.backend.model.view;

import io.zimara.backend.model.Metadata;
import io.zimara.backend.model.step.Step;

import java.util.List;
import java.util.Map;

/**
 * 🐱class View
 * Represents a possible view the frontend can use to view data and metadata.
 */
public class ViewDefinition implements Metadata {

    public ViewDefinition() {

    }


    public ViewDefinition(ViewDefinition v) {
        this.id = v.getId();
        this.name = v.getName();
        this.type = v.getType();
    }

    /*
     * 🐱property steps: List<Step>
     *
     * List of Steps on this integration.
     */
    private List<Step> steps = null;
    /*
     * 🐱property name: String
     *
     * Human understandable name of this view.
     */
    private String name = null;

    /*
     * 🐱property type: String
     *
     * Type of view. This helps the user interface to choose which visualization to use.
     */
    private String type = null;
    /*
     * 🐱property id: String
     *
     * Unique identifier for this view.
     */
    private String id = null;

    /*
     * 🐱property properties: Map<String, String>
     *
     * Properties useful for the user interface to visualize this view definition.
     */
    private Map<String, String> properties = null;

    @Override
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "ViewDefinition{" +
                "steps=" + steps +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", id='" + id + '\'' +
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
