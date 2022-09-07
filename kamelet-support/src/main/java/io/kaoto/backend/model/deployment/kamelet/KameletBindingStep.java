package io.kaoto.backend.model.deployment.kamelet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public final class KameletBindingStep implements Serializable {
    @Serial
    private static final long serialVersionUID = 2963462792033661194L;

    @JsonProperty("ref")
    private KameletBindingStepRef ref;

    @JsonProperty("uri")
    private String uri;

    @JsonProperty("properties")
    private Map<String, String> properties = new HashMap<>();

    @JsonProperty("parameters")
    private Map<String, String> parameters = new HashMap<>();

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

    public void setProperties(final Map<String, String> properties) {
        this.properties = properties;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(final String uri) {
        this.uri = uri;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(
            final Map<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "KameletBindingStep{" +
                "ref=" + ref +
                ", uri='" + uri + '\'' +
                ", properties=" + properties +
                ", parameters=" + parameters +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof KameletBindingStep)) {
            return false;
        }

        final KameletBindingStep that = (KameletBindingStep) o;

        if (getRef() != null ? !getRef().equals(that.getRef()) : that.getRef() != null) {
            return false;
        }
        if (getUri() != null ? !getUri().equals(that.getUri()) : that.getUri() != null) {
            return false;
        }
        if (getProperties() != null ? !getProperties().equals(that.getProperties()) : that.getProperties() != null) {
            return false;
        }
        return getParameters() != null ? getParameters().equals(that.getParameters()) : that.getParameters() == null;
    }

    @Override
    public int hashCode() {
        int result = getRef() != null ? getRef().hashCode() : 0;
        result = 31 * result + (getUri() != null ? getUri().hashCode() : 0);
        result = 31 * result + (getProperties() != null ? getProperties().hashCode() : 0);
        result = 31 * result + (getParameters() != null ? getParameters().hashCode() : 0);
        return result;
    }
}
