package io.kaoto.backend.camel.model.deployment.kamelet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

/**
 * ```
 *       period:
 *         title: Period
 *         description: The interval between two events in milliseconds
 *         type: integer
 *         default: 1000
 *         example: hello world
 *  ```
 */
@JsonPropertyOrder({"title", "description", "type", "default", "example"})
@JsonDeserialize(
        using = JsonDeserializer.None.class
)
@JsonIgnoreProperties(ignoreUnknown = true)
public class KameletDefinitionProperty  implements Serializable {
    private static final long serialVersionUID = -2212036216788957786L;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("type")
    private String type;

    @JsonProperty("default")
    private Object defaultValue;

    @JsonProperty("example")
    private Object example;

    @JsonProperty("path")
    private Boolean path;

    @JsonProperty("format")
    private String format;

    public KameletDefinitionProperty() {

    }

    public KameletDefinitionProperty(final String defaultValue, final String example, final String description,
                                     final String format,
                                     final String title, final String type) {
        this.setDefault(defaultValue);
        this.setExample(example);
        this.setDescription(description);
        this.setFormat(format);
        this.setPath(false);
        this.setTitle(title);
        this.setType(type);
    }

    public KameletDefinitionProperty(final org.apache.camel.v1alpha1.kameletspec.definition.Properties value) {
        this(value.get_default() != null && value.get_default().getValue() != null ?
                        String.valueOf(value.get_default().getValue()) :
                        null,
                value.getExample() != null ? String.valueOf(value.getExample()) : null,
                value.getDescription(),
                value.getFormat(),
                value.getTitle(),
                value.getType());
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public Object getDefault() {
        return defaultValue;
    }

    public void setDefault(final Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Object getExample() {
        return example;
    }

    public void setExample(final Object example) {
        this.example = example;
    }

    public Boolean getPath() {
        return path;
    }

    public void setPath(final Boolean path) {
        this.path = path;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(final String format) {
        this.format = format;
    }

    @Override
    public String toString() {
        return "KameletDefinitionProperty{"
                + "title='" + title + '\''
                + ", description='" + description + '\''
                + ", type='" + type + '\''
                + ", defaultValue='" + defaultValue + '\''
                + ", example='" + example + '\''
                + '}';
    }
}
