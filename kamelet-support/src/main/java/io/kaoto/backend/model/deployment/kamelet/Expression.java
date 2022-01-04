package io.kaoto.backend.model.deployment.kamelet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serial;

@JsonDeserialize(
        using = JsonDeserializer.None.class
)
public class Expression implements FlowStep {
    @Serial
    private static final long serialVersionUID = 7630089193555236497L;

    public Expression() {

    }

    @JsonProperty("constant")
    private String constant;

    @JsonProperty("simple")
    private String simple;

    public String getConstant() {
        return constant;
    }

    public void setConstant(final String constant) {
        this.constant = constant;
    }

    public String getSimple() {
        return simple;
    }

    public void setSimple(final String simple) {
        this.simple = simple;
    }
}
