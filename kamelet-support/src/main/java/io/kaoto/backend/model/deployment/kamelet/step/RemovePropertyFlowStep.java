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
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import java.io.Serial;
import java.util.HashMap;
import java.util.Map;


@JsonPropertyOrder({"remove-property"})
@JsonDeserialize(
        using = JsonDeserializer.None.class
)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RemovePropertyFlowStep implements FlowStep {
    @Serial
    private static final long serialVersionUID = 7630089193555236497L;

    @JsonCreator
    public RemovePropertyFlowStep(
            final @JsonProperty(value = "remove-property", required = true)
                    Expression e) {
        super();
        setRemovePropertyFlowStep(e);
    }

    @JsonProperty("remove-property")
    private Expression removePropertyFlowStep;

    public Expression getRemovePropertyFlowStep() {
        return removePropertyFlowStep;
    }

    public void setRemovePropertyFlowStep(
            final Expression removePropertyFlowStep) {
        this.removePropertyFlowStep = removePropertyFlowStep;
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("remove-property", this.getRemovePropertyFlowStep());
        return properties;
    }

    @Override
    public Step getStep(final StepCatalog catalog,
                        final KameletStepParserService
                                kameletStepParserService) {
        Step res = catalog.getReadOnlyCatalog()
                .searchStepByName("remove-property");

        for (Parameter p : res.getParameters()) {
            if (p.getId().equalsIgnoreCase(KameletStepParserService.NAME)) {
                p.setValue(this.getRemovePropertyFlowStep().getName());
            }
        }

        return res;
    }
}
