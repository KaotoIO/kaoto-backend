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
import java.util.Optional;


@JsonPropertyOrder({"set-header"})
@JsonDeserialize(
        using = JsonDeserializer.None.class
)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SetHeaderFlowStep implements FlowStep {
    @Serial
    private static final long serialVersionUID = 7630089193555236497L;

    @JsonCreator
    public SetHeaderFlowStep(final @JsonProperty("set-header") Expression e,
                             final @JsonProperty("setHeader") Expression e2) {
        super();
        if (e != null) {
            setSetHeaderPairFlowStep(e);
        } else if (e2 != null) {
            setSetHeaderPairFlowStep(e2);
        }
    }


    public SetHeaderFlowStep(final Expression e) {
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
        properties.put("set-header", this.getSetHeaderPairFlowStep());
        return properties;
    }

    @Override
    public Step getStep(final StepCatalog catalog,
                        final KameletStepParserService
                                kameletStepParserService) {

        Optional<Step> res = catalog.getReadOnlyCatalog()
                .searchByName("set-header").stream()
                .filter(step -> step.getKind().equalsIgnoreCase("EIP"))
                .findAny();

        if (res.isPresent()) {
            for (Parameter p : res.get().getParameters()) {
                if (p.getId()
                        .equalsIgnoreCase(KameletStepParserService.NAME)) {
                    p.setValue(this.getSetHeaderPairFlowStep().getName());
                } else if (p.getId()
                        .equalsIgnoreCase(KameletStepParserService.SIMPLE)) {
                    p.setValue(this.getSetHeaderPairFlowStep().getSimple());
                } else if (p.getId()
                        .equalsIgnoreCase(KameletStepParserService.CONSTANT)) {
                    p.setValue(this.getSetHeaderPairFlowStep().getConstant());
                }
            }

            return res.get();
        }

        return null;
    }
}
