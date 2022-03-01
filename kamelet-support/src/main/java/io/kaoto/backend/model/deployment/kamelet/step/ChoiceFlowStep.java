package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.choice.SuperChoice;

import java.io.Serial;

@JsonPropertyOrder({"choice"})
@JsonDeserialize(
        using = JsonDeserializer.None.class
)
public class ChoiceFlowStep implements FlowStep {
    @Serial
    private static final long serialVersionUID = -3320625752519808958L;

    @JsonCreator
    public ChoiceFlowStep(
           final @JsonProperty(value = "choice") SuperChoice choice) {
        super();
        setChoice(choice);
    }

    @JsonProperty("choice")
    private SuperChoice choice;

    public SuperChoice getChoice() {
        return choice;
    }

    public void setChoice(
            final SuperChoice choice) {
        this.choice = choice;
    }


}
