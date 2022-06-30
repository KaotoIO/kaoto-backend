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
            final @JsonProperty(value = "setBody", required = true)
                    Expression setBody) {
        super();
        setSetBody(setBody);
    }

    @JsonProperty("set-body")
    private Expression setBody;

    public Expression getSetBody() {
        return setBody;
    }

    public void setSetBody(final Expression setBody) {
        this.setBody = setBody;
    }
}
