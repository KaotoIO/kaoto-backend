package io.kaoto.backend.camel.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.camel.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.camel.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.step.Step;

import java.util.HashMap;
import java.util.Map;


@JsonPropertyOrder({"delay"})
@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DelayFlowStep implements FlowStep {

    public static final String DELAY_LABEL = "delay";

    @JsonCreator
    public DelayFlowStep(final @JsonProperty(DELAY_LABEL) Delay delay) {
        setDelay(delay);
    }

    public DelayFlowStep() {
        //Needed for serialization
    }

    public DelayFlowStep(Step step) {
        setDelay(new Delay(step));
    }

    private Delay delay;

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(DELAY_LABEL, this.getDelay().getRepresenterProperties());
        return properties;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final boolean start, final boolean end) {
        return getDelay().getStep(catalog, DELAY_LABEL, kameletStepParserService);
    }

    public Delay getDelay() {
        return delay;
    }

    public void setDelay(final Delay delay) {
        this.delay = delay;
    }
}
