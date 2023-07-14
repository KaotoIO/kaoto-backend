package io.kaoto.backend.model.step;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import io.kaoto.backend.model.Metadata;
import io.kaoto.backend.model.jsonviews.Views;
import io.kaoto.backend.model.parameter.Parameter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * 🐱class Step
 * 🐱aka List[Step]
 * 🐱aka Step[]
 * 🐱inherits Metadata
 * Represents a step inside an integration.
 */
public class Step extends Metadata {

    public static final String START = Type.START.name();
    public static final String MIDDLE = Type.MIDDLE.name();
    public static final String END = Type.END.name();

    @JsonView(Views.Summary.class)
    private String kind;
    @JsonView(Views.Summary.class)
    private String icon;
    @JsonView(Views.Summary.class)
    private String title;
    @JsonView(Views.Summary.class)
    private String description;
    @JsonView(Views.Summary.class)
    private String group;
    @JsonView(Views.Complete.class)
    private List<Parameter> parameters;
    @JsonView(Views.Complete.class)
    private List<String> required = new ArrayList<>();
    @JsonView(Views.Complete.class)
    private List<Branch> branches;
    @JsonView(Views.Summary.class)
    private Integer minBranches = 0;
    @JsonView(Views.Summary.class)
    private Integer maxBranches = 0;
    @JsonView(Views.Summary.class)
    @JsonProperty("UUID")
    private String uuid;

    public Step() {
        setType(MIDDLE);
        setKind("UNKNOWN");
    }

    //Used only for testing
    public Step(final String identifier, final String connector,
                final String icon, final List<Parameter> parameters) {
        this();
        setId(identifier);
        setName(connector);
        setIcon(icon);
        setParameters(parameters);
    }

    public Step(final String identifier, final String connector, final String icon,
                final List<Parameter> parameters,
                final String kind, final Step.Type type) {
        this();
        setId(identifier);
        setName(connector);
        setIcon(icon);
        setParameters(parameters);
        setKind(kind);
        setType(type.name());
    }

    /*
     * 🐱property description: String
     *
     * Human-readable description of what this step does.
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
     * Human-readable title of this step.
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
     * List of configurable parameters for this step.
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
     * Base64 icon image for this step.
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

    /*
     * 🐱property required: String[]
     *
     * List of properties that the object must have.
     */
    public List<String> getRequired() {
        return required;
    }

    public void setRequired(final List<String> required) {
        if (required != null) {
            this.required = required;
        }
    }

    /*
     * 🐱property group: String
     *
     * Group that identifies and classifies inside the steps world.
     */
    public String getGroup() {
        return group;
    }

    public void setGroup(final String group) {
        this.group = group;
    }

    /*
     * 🐱property kind: String
     *
     * Kind of step which usually translates to the kind this step will have
     * on the final CRD for deployment.
     *
     */
    public String getKind() {
        return kind;
    }

    public void setKind(final String kind) {
        this.kind = kind;
    }

    /*
     * 🐱property branches: Branch[]
     *
     * If this is a nested step that contains branches of steps, this is
     * where those branches are defined.
     *
     */
    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(final List<Branch> branches) {
        this.branches = branches;
    }


    /*
     * 🐱property minBranches: Integer
     *
     * Minimum amount of branches used on this step. Zero by default.
     *
     */
    public Integer getMinBranches() {
        return minBranches;
    }

    public void setMinBranches(final Integer minBranches) {
        this.minBranches = minBranches;
    }


    /*
     * 🐱property maxBranches: Integer
     *
     * Maximum amount of branches used on this step. Zero by default.
     *
     */
    public Integer getMaxBranches() {
        return maxBranches;
    }

    public void setMaxBranches(final Integer maxBranches) {
        this.maxBranches = maxBranches;
    }


    /*
     * 🐱property id: String
     *
     * User defined id for this step.
     *
     */
    public String getStepId() {
        var p = this.getParameters().stream()
                .filter(parameter -> parameter.getId().equalsIgnoreCase("step-id-kaoto"))
                .findAny();
        if (p.isPresent() && p.get().getValue() != null) {
            return String.valueOf(p.get().getValue());
        } else {
            return null;
        }
    }

    public void setStepId(final String stepId) {
        if (this.getParameters() != null) {
            this.getParameters().stream()
                    .filter(parameter -> parameter.getId().equalsIgnoreCase("step-id-kaoto"))
                    .findAny().ifPresent(parameter -> parameter.setValue(stepId));
        }
    }

    @Override
    public String toString() {
        return "Step{" + '\n'
                + "  id='" + getId() + '\'' + '\n'
                + ", name='" + getName() + '\'' + '\n'
                + ", uuid=" + getUUID() + '\n'
                + ", title=" + getTitle() + '\n'
                + ", type=" + getType() + '\n'
                + ", kind='" + getKind() + '\'' + '\n'
                + ", group='" + getGroup() + '\'' + '\n'
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

        if (getDescription() != null
                ? !getDescription().equals(step.getDescription())
                : step.getDescription() != null) {
            return false;
        }
        if (!Objects.equals(uuid, step.uuid)) {
            return false;
        }

        if (!Objects.equals(this.getParameters(), step.getParameters())) {
            return false;
        }
        return getTitle() != null
                ? getTitle().equals(step.getTitle())
                : step.getTitle() == null;
    }

    @Override
    public int hashCode() {
        int result = getTitle() != null ? getTitle().hashCode() : 0;
        result = 31 * result + (getDescription() != null
                ? getDescription().hashCode() : 0);
        result = 31 * result + (uuid != null ? uuid.hashCode() : 0);
        return result;
    }

    @Override
    public Step clone() {
        Step step = (Step) super.clone();

        step.setParameters(new LinkedList<>());
        if (parameters != null) {
            for (var p : parameters) {
                step.getParameters().add(p.clone());
            }
        }

        return step;
    }

    public enum Type {
        START, MIDDLE, END
    }
}
