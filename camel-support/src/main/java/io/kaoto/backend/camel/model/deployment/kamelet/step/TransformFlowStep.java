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


@JsonPropertyOrder({"transform"})
@JsonDeserialize(
        using = JsonDeserializer.None.class
)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransformFlowStep implements FlowStep {
    @Serial
    private static final long serialVersionUID = 7630089193555236497L;

    @JsonCreator
    public TransformFlowStep(
            final @JsonProperty(value = "transform", required = true)
                    Expression transform) {
        super();
        setTransform(transform);
    }

    @JsonProperty("transform")
    private Expression transform;

    public Expression getTransform() {
        return transform;
    }

    public void setTransform(
            final Expression transform) {
        this.transform = transform;
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("transform", this.getTransform());
        return properties;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final boolean start, final boolean end) {

        Optional<Step> res = catalog.getReadOnlyCatalog()
                .searchByName("transform").stream()
                .filter(step -> step.getKind().equalsIgnoreCase("EIP"))
                .findAny();

        if (res.isPresent()) {
            for (Parameter p : res.get().getParameters()) {
                if (p.getId()
                        .equalsIgnoreCase(KameletStepParserService.NAME)) {
                    p.setValue(this.getTransform().getName());
                } else if (p.getId()
                        .equalsIgnoreCase(KameletStepParserService.SIMPLE)) {
                    p.setValue(this.getTransform().getSimple());
                } else if (p.getId()
                        .equalsIgnoreCase(KameletStepParserService.CONSTANT)) {
                    p.setValue(this.getTransform().getConstant());
                } else if (p.getId()
                        .equalsIgnoreCase(KameletStepParserService.JQ)) {
                    p.setValue(this.getTransform().getJq());
                } else if (p.getId()
                        .equalsIgnoreCase(KameletStepParserService.EXPRESSION)) {
                    p.setValue(this.getTransform().getExpression());
                }
            }
            return res.get();
        }

        return null;
    }
}
