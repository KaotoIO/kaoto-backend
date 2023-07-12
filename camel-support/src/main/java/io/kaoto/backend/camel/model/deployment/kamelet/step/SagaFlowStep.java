package io.kaoto.backend.camel.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.camel.KamelPopulator;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.camel.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.camel.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.step.Step;

import java.util.HashMap;
import java.util.Map;

@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SagaFlowStep implements FlowStep {

    public static final String LABEL = "saga";

    @JsonProperty(LABEL)
    private Saga saga;

    @JsonCreator
    public SagaFlowStep(final @JsonProperty(LABEL) Saga saga) {
        super();
        setSaga(saga);
    }

    public SagaFlowStep(final Step step, final KamelPopulator kameletPopulator) {
        setSaga(new Saga(step, kameletPopulator));
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(LABEL, this.getSaga().getRepresenterProperties());
        return properties;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final Boolean start, final Boolean end) {
        return this.getSaga().getStep(catalog, LABEL, kameletStepParserService);
    }

    public Saga getSaga() {
        return saga;
    }

    public void setSaga(final Saga saga) {
        this.saga = saga;
    }
}
