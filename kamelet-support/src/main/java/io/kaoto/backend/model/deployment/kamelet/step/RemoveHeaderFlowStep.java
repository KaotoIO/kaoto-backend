package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.deployment.kamelet.expression.Expression;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import java.io.Serial;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@JsonPropertyOrder({"remove-header"})
@JsonDeserialize(
        using = JsonDeserializer.None.class
)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RemoveHeaderFlowStep implements FlowStep {
    @Serial
    private static final long serialVersionUID = 7630089193555236497L;

    @JsonCreator
    public RemoveHeaderFlowStep(final @JsonProperty("remove-header") Expression e,
                                final @JsonProperty("removeHeader") Expression e2) {
        super();
        setSetHeaderPairFlowStep(e != null ? e : e2);
    }

    public RemoveHeaderFlowStep(final Expression e) {
        super();
        setSetHeaderPairFlowStep(e);
    }

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

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final Boolean start, final Boolean end) {
        Optional<Step> res = catalog.getReadOnlyCatalog()
                .searchByName("remove-header").stream()
                .filter(step -> step.getKind().equalsIgnoreCase("EIP"))
                .findAny();

        if (res.isPresent()) {
            for (Parameter p : res.get().getParameters()) {
                if (p.getId().equalsIgnoreCase(KameletStepParserService.NAME)) {
                    p.setValue(this.getSetHeaderPairFlowStep().getName());
                }
            }
            return res.get();
        }

        return null;
    }
}
