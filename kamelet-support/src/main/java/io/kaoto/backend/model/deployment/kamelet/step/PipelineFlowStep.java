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

@JsonPropertyOrder({"pipeline"})
@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PipelineFlowStep implements FlowStep {

    public static final String LABEL = "pipeline";

    @JsonProperty(LABEL)
    private Pipeline pipeline;

    @JsonCreator
    public PipelineFlowStep(final @JsonProperty(LABEL) Pipeline pipeline) {
        super();
        setPipeline(pipeline);
    }

    public PipelineFlowStep(final Step step, final KamelPopulator kameletPopulator) {
        setPipeline(new Pipeline(step, kameletPopulator));
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(LABEL, this.getPipeline().getRepresenterProperties());
        return properties;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final Boolean start, final Boolean end) {
        return this.getPipeline().getStep(catalog, LABEL, kameletStepParserService);
    }

    public Pipeline getPipeline() {
        return pipeline;
    }

    public void setPipeline(final Pipeline pipeline) {
        this.pipeline = pipeline;
    }
}
