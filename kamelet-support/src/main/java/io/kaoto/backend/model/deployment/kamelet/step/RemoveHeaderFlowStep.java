package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.model.deployment.kamelet.Expression;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;

import java.io.Serial;
import java.util.HashMap;
import java.util.Map;


@JsonPropertyOrder({"remove-header"})
@JsonDeserialize(
        using = JsonDeserializer.None.class
)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RemoveHeaderFlowStep implements FlowStep {
    @Serial
    private static final long serialVersionUID = 7630089193555236497L;

    @JsonCreator
    public RemoveHeaderFlowStep(
            final @JsonProperty(value = "remove-header", required = true)
                    Expression e) {
        super();
        setSetHeaderPairFlowStep(e);
    }

    @JsonProperty("remove-header")
    private Expression setHeaderPairFlowStep;

    public Expression getSetHeaderPairFlowStep() {
        return setHeaderPairFlowStep;
    }

    public void setSetHeaderPairFlowStep(
            final Expression setHeaderPairFlowStep) {
        this.setHeaderPairFlowStep = setHeaderPairFlowStep;
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("remove-header", this.getSetHeaderPairFlowStep());
        return properties;
    }
}
