package io.kaoto.backend.camel.model.deployment.kamelet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * ```
 *
 beans:
 - name: dsBean
 type: "#class:org.apache.commons.dbcp2.BasicDataSource"
 property:
 - key: username
 value: '{{username}}'
 - key: password
 value: '{{password}}'
 - key: url
 value: 'jdbc:postgresql://{{serverName}}:{{serverPort}}/{{databaseName}}'
 - key: driverClassName
 value: 'org.postgresql.Driver'
 * ```
 */
@JsonPropertyOrder({"name", "type", "properties", "property"  })
@JsonDeserialize(
        using = JsonDeserializer.None.class
)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Bean implements Serializable {
    private static final long serialVersionUID = -4601560033032557024L;

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

    @JsonProperty("property")
    public void setProperty(final List<Property> property) {
        if (this.properties == null) {
            this.properties = new LinkedHashMap<>();
        }
        for (Property prop : property) {
            this.properties.put(prop.getKey(), prop.getValue());
        }
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
}
