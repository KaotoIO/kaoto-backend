package io.kaoto.backend.model.deployment.kamelet.step.choice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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

    public Otherwise getOtherwise() {
        return otherwise;
    }

    public void setOtherwise(
            final Otherwise otherwise) {
        this.otherwise = otherwise;
    }

    @JsonProperty("otherwise")
    private Otherwise otherwise;

    public List<Choice> getChoice() {
        return choice;
    }

    public void setChoice(
            final List<Choice> choice) {
        this.choice = choice;
    }


}
