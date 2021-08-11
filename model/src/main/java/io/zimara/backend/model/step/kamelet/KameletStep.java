package io.zimara.backend.model.step.kamelet;

import io.zimara.backend.model.parameter.Parameter;
import io.zimara.backend.model.step.Step;

import java.util.List;
import java.util.Map;

/**
 * 🐱class KameletStep
 * 🐱inherits Step
 * Represents a Kamelet step inside an integration
 */
public class KameletStep implements Step {

    public KameletStep(String id, String name, String icon, List<Parameter> parameters) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.parameters = parameters;
    }

    public KameletStep() {
        super();
    }

    private String id;
    private String name;
    private String icon;
    private List<Parameter> parameters;

    private String apiVersion;
    private String kind;
    private String kameletType;
    private String group;
    private String title;
    private String description;

    private Map<String, Object> metadata;
    private Map<String, Object> spec;

    /*
     * 🐱property group: String
     *
     * Kamelet group that identifies and classifies inside the kamelet world.
     */
    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    /*
     * 🐱property title: String
     *
     */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /*
     * 🐱property description: String
     *
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*
     * 🐱property kameletType: String
     *
     */
    public String getKameletType() {
        return kameletType;
    }

    public void setKameletType(String kameletType) {
        this.kameletType = kameletType;
    }

    /*
     * 🐱property apiVersion: String
     *
     */
    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    /*
     * 🐱property kind: String
     *
     */
    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    /*
     * 🐱property spec: String
     *
     */
    public void setSpec(Map<String, Object> spec) {
        this.spec = spec;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public Map<String, Object> getSpec() {
        return spec;
    }

    @Override
    public String getType() {
        return "CONNECTOR";
    }

    @Override
    public String getSubType() {
        return "KAMELET";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getId() {
        return this.id;
    }

    /*
     * 🐱property parameters: List[Parameter]
     *
     */
    public List<Parameter> getParameters() {
        return parameters;
    }

    /*
     * 🐱property icon: String
     *
     */
    public String getIcon() {
        return icon;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return "KameletStep{" + '\n' +
                "  id='" + id + '\'' + '\n' +
                ", name='" + name + '\'' + '\n' +
                ", icon='" + icon + '\'' + '\n' +
                ", parameters=" + parameters + '\n' +
                ", type=" + kameletType + '\n' +
                ", metadata=" + metadata + '\n' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KameletStep)) return false;

        KameletStep that = (KameletStep) o;

        if (!id.equals(that.id)) return false;
        if (!getName().equals(that.getName())) return false;
        if (getIcon() != null ? !getIcon().equals(that.getIcon()) : that.getIcon() != null) return false;
        if (!getParameters().equals(that.getParameters())) return false;
        if (!getApiVersion().equals(that.getApiVersion())) return false;
        if (!getKind().equals(that.getKind())) return false;
        if (!getKameletType().equals(that.getKameletType())) return false;
        if (getGroup() != null ? !getGroup().equals(that.getGroup()) : that.getGroup() != null) return false;
        if (!getTitle().equals(that.getTitle())) return false;
        return getDescription().equals(that.getDescription());
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + (getIcon() != null ? getIcon().hashCode() : 0);
        result = 31 * result + (getParameters() != null ? getParameters().hashCode() : 0);
        result = 31 * result + getApiVersion().hashCode();
        result = 31 * result + getKind().hashCode();
        result = 31 * result + getKameletType().hashCode();
        result = 31 * result + (getGroup() != null ? getGroup().hashCode() : 0);
        result = 31 * result + getTitle().hashCode();
        result = 31 * result + getDescription().hashCode();
        return result;
    }
}
