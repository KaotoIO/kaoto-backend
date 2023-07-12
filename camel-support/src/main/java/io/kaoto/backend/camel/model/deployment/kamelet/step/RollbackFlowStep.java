package io.kaoto.backend.camel.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.camel.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.camel.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.step.Step;

import java.util.HashMap;
import java.util.Map;


@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RollbackFlowStep implements FlowStep {

    public static final String LABEL = "rollback";

    private Rollback rollback;

    @JsonCreator
    public RollbackFlowStep(final @JsonProperty(LABEL) Rollback rollback) {
        setRollback(rollback);
    }

    public RollbackFlowStep() {
        //Needed for serialization
    }

    public RollbackFlowStep(Step step) {
        setRollback(new Rollback(step));
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(LABEL, this.getRollback().getRepresenterProperties());
        return properties;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final Boolean start, final Boolean end) {
        return getRollback().getStep(catalog, LABEL, kameletStepParserService);
    }

    public Rollback getRollback() {
        return rollback;
    }

    public void setRollback(final Rollback rollback) {
        this.rollback = rollback;
    }
}
