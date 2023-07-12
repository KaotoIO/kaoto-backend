package io.kaoto.backend.camel.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.camel.KamelPopulator;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.camel.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.camel.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.model.step.Step;

import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder({"multicast"})
@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MulticastFlowStep implements FlowStep {

    public static final String LABEL = "multicast";

    @JsonProperty(LABEL)
    private Multicast multicast;

    @JsonCreator
    public MulticastFlowStep(final @JsonProperty(LABEL) Multicast multicast) {
        super();
        setMulticast(multicast);
    }

    public MulticastFlowStep(final Step step, final KamelPopulator kameletPopulator) {
        setMulticast(new Multicast(step, kameletPopulator));
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(LABEL, this.getMulticast().getRepresenterProperties());
        return properties;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final Boolean start, final Boolean end) {
        return this.getMulticast().getStep(catalog, LABEL, kameletStepParserService);
    }

    public Multicast getMulticast() {
        return multicast;
    }

    public void setMulticast(final Multicast multicast) {
        this.multicast = multicast;
    }
}
