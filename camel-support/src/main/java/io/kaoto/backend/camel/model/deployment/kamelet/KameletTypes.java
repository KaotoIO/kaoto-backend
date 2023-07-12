package io.kaoto.backend.camel.model.deployment.kamelet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serial;
import java.io.Serializable;

@JsonPropertyOrder({"in", "out"})
@JsonDeserialize(
        using = JsonDeserializer.None.class
)
@JsonIgnoreProperties(ignoreUnknown = true)
public class KameletTypes implements Serializable {
    @Serial
    private static final long serialVersionUID = -6_210_736_155_226_440_906L;

    @JsonProperty("in")
    private KameletType in;

    @JsonProperty("out")
    private KameletType out;

    public KameletType getIn() {
        return in;
    }

    public void setIn(final KameletType in) {
        this.in = in;
    }

    public KameletType getOut() {
        return out;
    }

    public void setOut(final KameletType out) {
        this.out = out;
    }
}
