package io.kaoto.backend.model.deployment.kamelet.step.choice;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;

import java.util.List;

@JsonPropertyOrder({"when", "otherwise"})
public class SuperChoice {
    @JsonProperty("when")
    private List<Choice> choice;

    @JsonProperty("otherwise")
    private List<FlowStep> otherwise;

    public List<Choice> getChoice() {
        return choice;
    }

    public void setChoice(
            final List<Choice> choice) {
        this.choice = choice;
    }

    public List<FlowStep> getOtherwise() {
        return otherwise;
    }

    public void setOtherwise(
            final List<FlowStep> otherwise) {
        this.otherwise = otherwise;
    }

}
