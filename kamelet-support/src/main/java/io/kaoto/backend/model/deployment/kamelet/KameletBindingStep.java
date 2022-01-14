package io.kaoto.backend.model.deployment.kamelet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder({"ref", "uri", "properties"})
@JsonDeserialize(
        using = JsonDeserializer.None.class
)
public final class KameletBindingStep implements Serializable {
    @Serial
    private static final long serialVersionUID = 2963462792033661194L;

    @JsonProperty("ref")
    private KameletBindingStepRef ref;

    @JsonProperty("uri")
    private String uri;

    @JsonProperty("properties")
    private Map<String, String> properties = new HashMap<>();

    public KameletBindingStep() {
    }

    public KameletBindingStep(final String uri) {
        this.uri = uri;
    }
    public KameletBindingStep(final KameletBindingStepRef ref) {
        this.ref = ref;
    }

    public KameletBindingStepRef getRef() {
        return ref;
    }

    public void setRef(final KameletBindingStepRef ref) {
        this.ref = ref;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(final Map<String, String> properties) {
        this.properties = properties;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(final String uri) {
        this.uri = uri;
    }
}
