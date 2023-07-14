package io.kaoto.backend.camel.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.camel.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.camel.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.camel.model.deployment.kamelet.expression.Expression;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import java.io.Serial;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@JsonPropertyOrder({"remove-property"})
@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RemovePropertyFlowStep implements FlowStep {
    @Serial
    private static final long serialVersionUID = 7630089193555236497L;

    @JsonCreator
    public RemovePropertyFlowStep(final @JsonProperty("remove-property") Expression e,
                                  final @JsonProperty("removeProperty") Expression e2) {
        super();
        setRemovePropertyFlowStep(e != null ? e : e2);
    }

    public RemovePropertyFlowStep(final Expression e) {
        super();
        setRemovePropertyFlowStep(e);
    }

    public Expression getRemovePropertyFlowStep() {
        return removePropertyFlowStep;
    }

    public void setRemovePropertyFlowStep(
            final Expression removePropertyFlowStep) {
        this.removePropertyFlowStep = removePropertyFlowStep;
    }

    private Expression removePropertyFlowStep;

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("remove-property", this.getRemovePropertyFlowStep());
        return properties;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final boolean start, final boolean end) {

        Optional<Step> res = catalog.getReadOnlyCatalog()
                .searchByName("remove-property").stream()
                .filter(step -> step.getKind().equalsIgnoreCase("EIP"))
                .findAny();


        if (res.isPresent()) {
            for (Parameter p : res.get().getParameters()) {
                if (p.getId().equalsIgnoreCase(KameletStepParserService.NAME)) {
                    p.setValue(this.getRemovePropertyFlowStep().getName());
                }
            }

            return res.get();
        }

        return null;
    }
}
