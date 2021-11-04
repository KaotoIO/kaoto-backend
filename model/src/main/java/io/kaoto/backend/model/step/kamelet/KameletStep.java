package io.kaoto.backend.model.step.kamelet;

import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

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
}
