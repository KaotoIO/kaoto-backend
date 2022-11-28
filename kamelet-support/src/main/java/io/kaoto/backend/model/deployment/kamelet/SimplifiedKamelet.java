package io.kaoto.backend.model.deployment.kamelet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.model.ObjectMeta;


/**
 * 🐱class SimplifiedKamelet
 *
 * Used to read kamelet properties, but not digging into the implementation of the kamelet.
 *
 */


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public final class SimplifiedKamelet {

    @JsonProperty("spec")
    protected SimplifiedKameletSpec spec;

    @JsonProperty("kind")
    private String kind;

    @JsonProperty("metadata")
    private ObjectMeta metadata = new ObjectMeta();

    public SimplifiedKamelet() {
        //Needed for serialization
    }

    public SimplifiedKameletSpec getSpec() {
        return spec;
    }

    public void setSpec(final SimplifiedKameletSpec spec) {
        this.spec = spec;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(final String kind) {
        this.kind = kind;
    }

    public ObjectMeta getMetadata() {
        return metadata;
    }

    public void setMetadata(final ObjectMeta metadata) {
        this.metadata = metadata;
    }
}
