package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;

import java.util.List;


@JsonPropertyOrder({"steps"})
@JsonDeserialize(
        using = JsonDeserializer.None.class
)
public class From extends UriFlowStep {
    private static final long serialVersionUID = -4601560033032557024L;

    @JsonProperty("steps")
    private List<FlowStep> steps;

    public List<FlowStep> getSteps() {
        return steps;
    }

    public void setSteps(
            final List<FlowStep> steps) {
        this.steps = steps;
    }
}
