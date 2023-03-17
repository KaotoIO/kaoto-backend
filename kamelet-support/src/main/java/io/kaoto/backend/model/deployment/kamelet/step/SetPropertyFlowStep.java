package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.model.deployment.kamelet.expression.Expression;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import java.io.Serial;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@JsonPropertyOrder({"set-property"})
@JsonDeserialize(
        using = JsonDeserializer.None.class
)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SetPropertyFlowStep implements FlowStep {
    @Serial
    private static final long serialVersionUID = 7630089193555236497L;

    @JsonCreator
    public SetPropertyFlowStep(final @JsonProperty("set-property") Expression e,
                               final @JsonProperty("setProperty") Expression e2) {
        super();
        setSetPropertyPairFlowStep(e != null ? e : e2);
    }


    public SetPropertyFlowStep(final Expression e) {
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

    private Expression setPropertyPairFlowStep;

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("set-property", this.getSetPropertyPairFlowStep());
        return properties;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final Boolean start, final Boolean end) {

        Optional<Step> res = catalog.getReadOnlyCatalog()
                .searchByName("set-property").stream()
                .filter(step -> step.getKind().equalsIgnoreCase("EIP"))
                .findAny();

        if (res.isPresent()) {
            for (Parameter p : res.get().getParameters()) {
                if (p.getId().equalsIgnoreCase(KameletStepParserService.NAME)) {
                    p.setValue(this
                            .getSetPropertyPairFlowStep().getName());
                } else if (p.getId()
                        .equalsIgnoreCase(KameletStepParserService.SIMPLE)) {
                    p.setValue(this.getSetPropertyPairFlowStep().getSimple());
                } else if (p.getId()
                        .equalsIgnoreCase(KameletStepParserService.CONSTANT)) {
                    p.setValue(this.getSetPropertyPairFlowStep().getConstant());
                } else if (p.getId()
                        .equalsIgnoreCase(KameletStepParserService.JQ)) {
                    p.setValue(this.getSetPropertyPairFlowStep().getJq());
                } else {
                    p.setValue(this.getSetPropertyPairFlowStep().getExpression());
                }

                res.get().setStepId(this.getSetPropertyPairFlowStep().getId());
            }
            return res.get();
        }

        return null;
    }
}
