package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.model.step.Step;

import java.util.HashMap;
import java.util.Map;


@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PollEnrichFlowStep implements FlowStep {

    public static final String LABEL = "poll-enrich";
    public static final String LABEL2 = "pollEnrich";

    @JsonCreator
    public PollEnrichFlowStep(final @JsonProperty(LABEL) PollEnrich enrich,
                              final @JsonProperty(LABEL2) PollEnrich enrich2) {
        setPollEnrich(enrich != null ? enrich : enrich2);
    }

    public PollEnrichFlowStep() {
        //Needed for serialization
    }

    public PollEnrichFlowStep(Step step) {
        setPollEnrich(new PollEnrich(step));
    }

    private PollEnrich pollEnrich;

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(LABEL, this.getPollEnrich().getRepresenterProperties());
        return properties;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final Boolean start, final Boolean end) {
        return getPollEnrich().getStep(catalog, LABEL, kameletStepParserService);
    }

    public PollEnrich getPollEnrich() {
        return pollEnrich;
    }

    public void setPollEnrich(final PollEnrich pollEnrich) {
        this.pollEnrich = pollEnrich;
    }
}
