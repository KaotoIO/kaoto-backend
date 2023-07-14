package io.kaoto.backend.camel.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.camel.KamelPopulator;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.camel.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.camel.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.model.step.Step;

import java.util.HashMap;
import java.util.Map;

@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class WireTapFlowStep implements FlowStep {

    private static final String LABEL = "wire-tap";
    private static final String LABEL2 = "wireTap";

    private WireTap wiretap;

    @JsonCreator
    public WireTapFlowStep(final @JsonProperty(value = LABEL) WireTap wiretap,
                           final @JsonProperty(value = LABEL2) WireTap wiretap2) {
        super();
        setWiretap(wiretap != null ? wiretap : wiretap2);
    }

    public WireTapFlowStep(Step step, final KamelPopulator kamelPopulator) {
        setWiretap(new WireTap(step, kamelPopulator));
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final boolean start, final boolean end) {
        return this.getWiretap().getStep(catalog, LABEL, kameletStepParserService);
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(LABEL, this.getWiretap().getRepresenterProperties());
        return properties;
    }

    public WireTap getWiretap() {
        return wiretap;
    }

    public void setWiretap(final WireTap wiretap) {
        this.wiretap = wiretap;
    }
}
