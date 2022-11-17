package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.KamelPopulator;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.model.step.Step;

import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder({"split"})
@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SplitFlowStep implements FlowStep {

    public static final String LABEL = "split";

    @JsonProperty(LABEL)
    private Split split;

    @JsonCreator
    public SplitFlowStep(final @JsonProperty(LABEL) Split split) {
        super();
        setSplit(split);
    }

    public SplitFlowStep(final Step step, final KamelPopulator kameletPopulator) {
        setSplit(new Split(step, kameletPopulator));
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(LABEL, this.getSplit().getRepresenterProperties());
        return properties;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final Boolean start, final Boolean end) {
        return this.getSplit().getStep(catalog, LABEL, kameletStepParserService);
    }

    public Split getSplit() {
        return split;
    }

    public void setSplit(final Split split) {
        this.split = split;
    }
}
