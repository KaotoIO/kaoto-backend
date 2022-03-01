package io.kaoto.backend.model.deployment.kamelet.step.choice;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;

import java.util.List;


@JsonPropertyOrder({"simple", "steps"})
public class Choice {

//    @JsonProperty("jsonpath")
//    private JSONPath jsonPath;

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
//
//    public JSONPath getJsonPath() {
//        return jsonPath;
//    }
//
//    public void setJsonPath(
//            final JSONPath jsonPath) {
//        this.jsonPath = jsonPath;
//    }

    public String getSimple() {
        return simple;
    }

    public void setSimple(final String simple) {
        this.simple = simple;
    }
}
