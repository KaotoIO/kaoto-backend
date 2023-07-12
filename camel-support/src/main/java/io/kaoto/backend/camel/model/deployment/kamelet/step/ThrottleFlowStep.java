package io.kaoto.backend.camel.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.camel.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.camel.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.step.Step;

import java.util.HashMap;
import java.util.Map;


@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ThrottleFlowStep implements FlowStep {
    public static final String LABEL = "throttle";

    private Throttle throttle;

    public ThrottleFlowStep() {
        //Empty for serialization purposes
    }

    @JsonCreator
    public ThrottleFlowStep(final @JsonProperty(LABEL) Throttle throttle) {
        super();
        setThrottle(throttle);
    }

    public ThrottleFlowStep(Step step) {
        setThrottle(new Throttle(step));
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> aggregator = new HashMap<>();
        aggregator.put(LABEL, getThrottle().getRepresenterProperties());
        return aggregator;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final Boolean start, final Boolean end) {
        return getThrottle().getStep(catalog, LABEL, kameletStepParserService);
    }

    public Throttle getThrottle() {
        return throttle;
    }

    public void setThrottle(final Throttle throttle) {
        this.throttle = throttle;
    }
}
