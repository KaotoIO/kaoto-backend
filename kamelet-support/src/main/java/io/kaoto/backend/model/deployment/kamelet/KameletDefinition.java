package io.kaoto.backend.model.deployment.kamelet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.List;

/**
 * ```
 *   definition:
 *     title: Timer Source
 *     description: Produces periodic events with a custom payload
 *     required:
 *       - message
 *     properties:
 *       period:
 *         title: Period
 *         description: The interval between two events in milliseconds
 *         type: integer
 *         default: 1000
 *       message:
 *         title: Message
 *         description: The message to generate
 *         type: string
 *         example: hello world
 *  ```
 */
@JsonPropertyOrder({"title", "description", "required", "properties"})
@JsonDeserialize(
        using = JsonDeserializer.None.class
)
public class KameletDefinition implements Serializable {
    private static final long serialVersionUID = 6250254108665065927L;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("required")
    private List<String> required;

    @JsonProperty("properties")
    private List<KameletDefinitionProperty> properties;

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

    public List<String> getRequired() {
        return required;
    }

    public void setRequired(final List<String> required) {
        this.required = required;
    }

    public List<KameletDefinitionProperty> getProperties() {
        return properties;
    }

    public void setProperties(
            final List<KameletDefinitionProperty> properties) {
        this.properties = properties;
    }
}
