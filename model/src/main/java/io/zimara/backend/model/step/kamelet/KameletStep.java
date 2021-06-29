package io.zimara.backend.model.step.kamelet;

import io.zimara.backend.model.Parameter;
import io.zimara.backend.model.Step;
import io.zimara.backend.model.parameter.BooleanParameter;
import io.zimara.backend.model.parameter.IntegerParameter;
import io.zimara.backend.model.parameter.TextParameter;

import java.util.ArrayList;
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

    public String getId() {
        return id;
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

        if (spec.containsKey("definition")) {
            Map<String, Object> definition = (Map<String, Object>) spec.get("definition");
            if (definition.containsKey("title")) {
                setTitle(definition.get("title").toString());
            }

            if (definition.containsKey("description")) {
                setDescription(definition.get("description").toString());
            }

            if (definition.containsKey("properties")) {
                Map<String, Object> properties = (Map<String, Object>) definition.get("properties");
                this.parameters = new ArrayList<>();

                for (Map.Entry<String, Object> property : properties.entrySet()) {
                    Map<String, Object> definitions = (Map<String, Object>) property.getValue();
                    Parameter p;
                    final var title = definitions.get("title").toString();
                    var description = definitions.getOrDefault("description", title).toString();
                    String value = null;
                    if(definitions.containsKey("default")) {
                        value = definitions.get("default").toString();
                    }

                    try {
                        switch (definitions.get("type").toString()) {
                            case "integer":
                                p = new IntegerParameter(title, (value != null ? Integer.valueOf(value) : null), description);
                                break;
                            case "boolean":
                                p = new BooleanParameter(title, (value != null ? Boolean.valueOf(value) : null), description);
                                break;
                            default:
                                p = new TextParameter(title, value, description);
                        }
                        this.parameters.add(p);
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


        this.spec = null;
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
        if (metadata.containsKey("name")) {
            setId(metadata.get("name").toString());
            setName(metadata.get("name").toString());
        }

        if (metadata.containsKey("labels")) {
            Map<String, Object> labels = (Map<String, Object>) metadata.get("labels");
            if (labels.containsKey("camel.apache.org/kamelet.type")) {
                setKameletType(labels.get("camel.apache.org/kamelet.type").toString());
            }
        }

        if (metadata.containsKey("annotations")) {
            Map<String, Object> labels = (Map<String, Object>) metadata.get("annotations");
            if (labels.containsKey("camel.apache.org/kamelet.group")) {
                setGroup(labels.get("camel.apache.org/kamelet.group").toString());
            }
            if (labels.containsKey("camel.apache.org/kamelet.icon")) {
                setIcon(labels.get("camel.apache.org/kamelet.icon").toString());
            }
        }

        this.metadata = null;
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
