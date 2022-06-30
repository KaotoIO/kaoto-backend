package io.kaoto.backend.model.deployment.kamelet.step.choice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;


@JsonPropertyOrder({"simple", "steps"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Choice implements Serializable {
    @Serial
    private static final long serialVersionUID = -7206333633897407153L;

    @JsonProperty("simple")
    private String simple;

    @JsonProperty("steps")
    private List<FlowStep> steps;

    public List<FlowStep> getSteps() {
        return steps;
    }

    public void setSteps(
            final List<FlowStep> steps) {
        this.steps = steps;
    }

    public String getSimple() {
        return simple;
    }

    public void setSimple(final String simple) {
        this.simple = simple;
    }
}
