package io.zimara.backend.model.step.kamelet;

import io.zimara.backend.model.parameter.Parameter;
import io.zimara.backend.model.step.Step;

import java.util.List;
import java.util.Map;

/**
 * 🐱class KameletStep
 * 🐱aka KameletStep[]
 * 🐱inherits Step
 * 🐱relationship compositionOf Parameter, 0..n
 * Represents a Kamelet step inside an integration
 */
public class KameletStep extends Step {

    public KameletStep(final String id, final String name,
                       final String icon, final List<Parameter> parameters) {
        this();
        setId(id);
        setName(name);
        setType("MIDDLE");
        setIcon(icon);
        setParameters(parameters);
    }

    public KameletStep() {
        super();
        setSubType("KAMELET");
    }


    private String apiVersion;
    private String kind;
    private String kameletType;
    private String group;

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

    public void setGroup(final String group) {
        this.group = group;
    }

    /*
     * 🐱property kameletType: String
     *
     */
    public String getKameletType() {
        return kameletType;
    }

    public void setKameletType(final String kameletType) {
        this.kameletType = kameletType;
    }

    /*
     * 🐱property apiVersion: String
     *
     */
    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(final String apiVersion) {
        this.apiVersion = apiVersion;
    }

    /*
     * 🐱property kind: String
     *
     */
    public String getKind() {
        return kind;
    }

    public void setKind(final String kind) {
        this.kind = kind;
    }

    /*
     * 🐱property spec: String
     *
     */
    public void setSpec(final Map<String, Object> spec) {
        this.spec = spec;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public Map<String, Object> getSpec() {
        return spec;
    }

    public void setMetadata(final Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return "KameletStep{" + '\n'
                + "  id='" + getId() + '\'' + '\n'
                + ", name='" + getName() + '\'' + '\n'
                + ", type=" + kameletType + '\n'
                + ", metadata=" + metadata + '\n'
                + '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof KameletStep)) {
            return false;
        }

        KameletStep that = (KameletStep) o;

        if (!getId().equals(that.getId())) {
            return false;
        }
        if (!getName().equals(that.getName())) {
            return false;
        }
        if (!getParameters().equals(that.getParameters())) {
            return false;
        }
        if (!getApiVersion().equals(that.getApiVersion())) {
            return false;
        }
        if (!getKind().equals(that.getKind())) {
            return false;
        }
        if (!getKameletType().equals(that.getKameletType())) {
            return false;
        }
        if (getGroup() != null
                ? !getGroup().equals(that.getGroup())
                : that.getGroup() != null) {
            return false;
        }
        if (!getTitle().equals(that.getTitle())) {
            return false;
        }
        return getDescription().equals(that.getDescription());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result
                + (getIcon() != null ? getIcon().hashCode() : 0);
        result = 31 * result
                + (getParameters() != null ? getParameters().hashCode() : 0);
        result = 31 * result + getApiVersion().hashCode();
        result = 31 * result + getKind().hashCode();
        result = 31 * result + getKameletType().hashCode();
        result = 31 * result + (getGroup() != null ? getGroup().hashCode() : 0);
        result = 31 * result + getTitle().hashCode();
        result = 31 * result + getDescription().hashCode();
        return result;
    }
}
