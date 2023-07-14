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
import io.kaoto.backend.model.step.Step;

import java.io.Serial;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@JsonPropertyOrder({"set-body"})
@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SetBodyFlowStep implements FlowStep {
    @Serial
    private static final long serialVersionUID = 7630089193555236497L;

    @JsonCreator
    public SetBodyFlowStep(final @JsonProperty("set-body") Expression setBody,
                           final @JsonProperty("setBody") Expression setBody2) {
        super();
        setSetBody(setBody != null ? setBody : setBody2);
    }


    public SetBodyFlowStep(final Expression setBody) {
        super();
        setSetBody(setBody);
    }

    private Expression setBody;

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
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final boolean start, final boolean end) {

        Optional<Step> res = catalog.getReadOnlyCatalog()
                .searchByName("set-body").stream()
                .filter(step -> step.getKind().equalsIgnoreCase("EIP"))
                .findAny();


        if (res.isPresent()) {
            for (var p : res.get().getParameters()) {
                if (p.getId()
                        .equalsIgnoreCase(KameletStepParserService.SIMPLE)) {
                    p.setValue(this.getSetBody().getSimple());
                } else if (p.getId()
                        .equalsIgnoreCase(KameletStepParserService.CONSTANT)) {
                    p.setValue(this.getSetBody().getConstant());
                } else if (p.getId()
                        .equalsIgnoreCase(KameletStepParserService.JQ)) {
                    p.setValue(this.getSetBody().getJq());
                } else if (p.getId().equalsIgnoreCase("step-id-kaoto")) {
                    res.get().setStepId((String) p.getValue());
                } else {
                    p.setValue(this.getSetBody().getExpression());
                }
            }

            return res.get();
        }

        return null;
    }
}
