package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.deployment.kamelet.Expression;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.model.step.Step;

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

    @Override
    public Step getStep(final StepCatalog catalog,
                        final KameletStepParserService
                                kameletStepParserService) {
        Step res = catalog.getReadOnlyCatalog().searchStepByName("set-body");

        for (var p : res.getParameters()) {
            if (p.getId()
                    .equalsIgnoreCase(KameletStepParserService.SIMPLE)) {
                p.setValue(this.getSetBody().getSimple());
            } else if (p.getId()
                    .equalsIgnoreCase(KameletStepParserService.CONSTANT)) {
                p.setValue(this.getSetBody().getConstant());
            } else if (p.getId()
                    .equalsIgnoreCase(KameletStepParserService.NAME)) {
                p.setValue(this.getSetBody().getName());
            }
        }

        return res;
    }
}
