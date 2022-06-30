package io.kaoto.backend.model.deployment.kamelet.step.choice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@JsonPropertyOrder({"when", "otherwise"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class SuperChoice implements Serializable {
    @Serial
    private static final long serialVersionUID = 7290333980965193880L;

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
