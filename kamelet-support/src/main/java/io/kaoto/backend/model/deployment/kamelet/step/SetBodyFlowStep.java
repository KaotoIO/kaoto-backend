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


@JsonPropertyOrder({"set-body"})
@JsonDeserialize(
        using = JsonDeserializer.None.class
)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SetBodyFlowStep implements FlowStep {
    @Serial
    private static final long serialVersionUID = 7630089193555236497L;

    @JsonCreator
    public SetBodyFlowStep(
            final @JsonProperty(value = "set-body", required = true)
                    Expression setBody) {
        super();
        setSetBody(setBody);
    }

    @JsonProperty("set-body")
    private Expression setBody;

    @JsonProperty("set-body")
    public Expression getSetBody() {
        return setBody;
    }

    public void setSetBody(final Expression setBody) {
        this.setBody = setBody;
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("set-body", this.getSetBody());
        return properties;
    }
}
