package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.model.deployment.kamelet.Expression;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;

import java.io.Serial;


@JsonPropertyOrder({"set-header"})
@JsonDeserialize(
        using = JsonDeserializer.None.class
)
public class SetHeaderFlowStep implements FlowStep {
    @Serial
    private static final long serialVersionUID = 7630089193555236497L;

    @JsonCreator
    public SetHeaderFlowStep(
            final @JsonProperty(value = "set-header", required = true)
                    Expression e) {
        super();
        setSetHeaderPairFlowStep(e);
    }

    @JsonProperty("set-header")
    private Expression setHeaderPairFlowStep;

    public Expression getSetHeaderPairFlowStep() {
        return setHeaderPairFlowStep;
    }

    public void setSetHeaderPairFlowStep(
            final Expression setHeaderPairFlowStep) {
        this.setHeaderPairFlowStep = setHeaderPairFlowStep;
    }
}
