package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.model.deployment.kamelet.expression.Expression;
import io.kaoto.backend.model.step.Step;

import java.util.HashMap;
import java.util.Map;


@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ValidateFlowStep implements FlowStep {
    public static final String LABEL = "validate";


    private Expression validate;

    public ValidateFlowStep() {
        //Empty for serialization purposes
    }

    @JsonCreator
    public ValidateFlowStep(final @JsonProperty(LABEL) Expression validate) {
        super();
        setValidate(validate);
    }

    public ValidateFlowStep(Step step) {
        setValidate(new Expression(step));
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> aggregator = new HashMap<>();
        aggregator.put(LABEL, getValidate().getRepresenterProperties());
        return aggregator;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final Boolean start, final Boolean end) {
        return getValidate().getStep(catalog, LABEL, kameletStepParserService);
    }

    public Expression getValidate() {
        return validate;
    }

    public void setValidate(final Expression validate) {
        this.validate = validate;
    }
}
