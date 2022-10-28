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
public class EnrichFlowStep implements FlowStep {

    public static final String LABEL = "enrich";

    @JsonCreator
    public EnrichFlowStep(final @JsonProperty(LABEL) Enrich enrich) {
        setEnrich(enrich);
    }

    public EnrichFlowStep() {
        //Needed for serialization
    }

    public EnrichFlowStep(Step step) {
        setEnrich(new Enrich(step));
    }

    private Enrich enrich;

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(LABEL, this.getEnrich().getRepresenterProperties());
        return properties;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService) {
        return getEnrich().getStep(catalog, LABEL, kameletStepParserService);
    }

    public Enrich getEnrich() {
        return enrich;
    }

    public void setEnrich(final Enrich enrich) {
        this.enrich = enrich;
    }
}
