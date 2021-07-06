package io.zimara.backend.model.step.kamelet;

import io.zimara.backend.model.Parameter;
import io.zimara.backend.model.Step;

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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKameletType() {
        return kameletType;
    }

    public void setKameletType(String kameletType) {
        this.kameletType = kameletType;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

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
    public String getID() {
        return this.id;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

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

}
