package io.kaoto.backend.camel.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.camel.KamelPopulator;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.camel.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.camel.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.step.Step;

import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder({"loop"})
@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoopFlowStep implements FlowStep {

    public static final String LOOP_LABEL = "loop";

    @JsonProperty(LOOP_LABEL)
    private Loop loop;

    @JsonCreator
    public LoopFlowStep(final @JsonProperty(LOOP_LABEL) Loop loop) {
        super();
        setLoop(loop);
    }

    public LoopFlowStep(final Step step, final KamelPopulator kameletPopulator) {
        setLoop(new Loop(step, kameletPopulator));
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(LOOP_LABEL, this.getLoop().getRepresenterProperties());
        return properties;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final Boolean start, final Boolean end) {
        return this.getLoop().getStep(catalog, LOOP_LABEL, kameletStepParserService);
    }

    public Loop getLoop() {
        return loop;
    }

    public void setLoop(final Loop loop) {
        this.loop = loop;
    }
}
