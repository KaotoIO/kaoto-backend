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
public class RoutingSlipFlowStep implements FlowStep {

    public static final String LABEL = "routing-slip";

    private RoutingSlip routingSlip;

    @JsonCreator
    public RoutingSlipFlowStep(final @JsonProperty(LABEL) RoutingSlip routingSlip,
                               final @JsonProperty("routingSlip") RoutingSlip routingSlip2) {
        super();
        setRoutingSlip(routingSlip != null ? routingSlip : routingSlip2);
    }

    public RoutingSlipFlowStep(final Step step) {
        setRoutingSlip(new RoutingSlip(step));
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(LABEL, this.getRoutingSlip().getRepresenterProperties());
        return properties;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final Boolean start, final Boolean end) {
        return this.getRoutingSlip().getStep(catalog, LABEL, kameletStepParserService);
    }

    public RoutingSlip getRoutingSlip() {
        return routingSlip;
    }

    public void setRoutingSlip(final RoutingSlip routingSlip) {
        this.routingSlip = routingSlip;
    }
}
