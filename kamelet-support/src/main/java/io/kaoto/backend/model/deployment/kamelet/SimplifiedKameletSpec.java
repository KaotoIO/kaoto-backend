package io.kaoto.backend.model.deployment.kamelet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class SimplifiedKameletSpec {
    @JsonProperty("definition")
    private KameletDefinition definition;

    public SimplifiedKameletSpec() {
        //Needed for serialization
    }

    public KameletDefinition getDefinition() {
        return definition;
    }

    public void setDefinition(final KameletDefinition definition) {
        this.definition = definition;
    }
}
