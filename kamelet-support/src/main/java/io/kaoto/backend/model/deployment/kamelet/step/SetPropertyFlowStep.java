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


@JsonPropertyOrder({"set-property"})
@JsonDeserialize(
        using = JsonDeserializer.None.class
)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SetPropertyFlowStep implements FlowStep {
    @Serial
    private static final long serialVersionUID = 7630089193555236497L;

    @JsonCreator
    public SetPropertyFlowStep(
            final @JsonProperty(value = "set-property", required = true)
                    Expression e) {
        super();
        setSetPropertyPairFlowStep(e);
    }

    public Expression getSetPropertyPairFlowStep() {
        return setPropertyPairFlowStep;
    }

    public void setSetPropertyPairFlowStep(
            final Expression setPropertyPairFlowStep) {
        this.setPropertyPairFlowStep = setPropertyPairFlowStep;
    }

    @JsonProperty("set-property")
    private Expression setPropertyPairFlowStep;

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("set-property", this.getSetPropertyPairFlowStep());
        return properties;
    }
}
