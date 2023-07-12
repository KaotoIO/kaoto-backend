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
public class ResequenceFlowStep implements FlowStep {

    public static final String LABEL = "resequence";

    @JsonProperty(LABEL)
    private Resequence resequence;

    @JsonCreator
    public ResequenceFlowStep(final @JsonProperty(LABEL) Resequence resequence) {
        super();
        setResequence(resequence);
    }

    public ResequenceFlowStep(final Step step, final KamelPopulator kameletPopulator) {
        setResequence(new Resequence(step, kameletPopulator));
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(LABEL, this.getResequence().getRepresenterProperties());
        return properties;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final Boolean start, final Boolean end) {
        return this.getResequence().getStep(catalog, LABEL, kameletStepParserService);
    }

    public Resequence getResequence() {
        return resequence;
    }

    public void setResequence(final Resequence resequence) {
        this.resequence = resequence;
    }
}
