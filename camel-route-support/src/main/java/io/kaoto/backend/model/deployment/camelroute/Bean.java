package io.kaoto.backend.model.deployment.camelroute;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonPropertyOrder({ "name", "type", "properties" })
@JsonDeserialize(
        using = JsonDeserializer.None.class
)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Bean implements Serializable {
    private static final long serialVersionUID = -4601560033032557024L;

    public static final String NAME_LABEL = "name";
    public static final String TYPE_LABEL = "type";
    public static final String PROPERTIES_LABEL = "properties";

    public Bean() {
        super();
    }

    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;
    @JsonProperty("properties")
    private Map<String, Object> properties;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(final Map<String, Object> properties) {
        this.properties = properties;
    }

    @JsonIgnore
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> props = new LinkedHashMap<>();
        if (this.getName() != null) {
            props.put(NAME_LABEL, this.getName());
        }
        if (this.getType() != null) {
            props.put(TYPE_LABEL, this.getType());
        }
        if (this.getProperties() != null) {
            props.put(PROPERTIES_LABEL, this.getProperties());
        }
        return props;
    }
}
