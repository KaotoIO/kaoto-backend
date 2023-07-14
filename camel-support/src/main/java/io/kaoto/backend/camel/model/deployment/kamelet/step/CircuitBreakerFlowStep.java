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

import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CircuitBreakerFlowStep implements FlowStep {
    @Serial
    private static final long serialVersionUID = 2863336982210367342L;
    public static final String CIRCUIT_BREAKER_LABEL = "circuit-breaker";

    @JsonProperty(CIRCUIT_BREAKER_LABEL)
    private CircuitBreaker circuitBreaker;

    @JsonCreator
    public CircuitBreakerFlowStep(final @JsonProperty(CIRCUIT_BREAKER_LABEL) CircuitBreaker circuitBreaker) {
        super();
        setCircuitBreaker(circuitBreaker);
    }

    public CircuitBreakerFlowStep(final Step step, final KamelPopulator kameletPopulator) {
        super();
        setCircuitBreaker(new CircuitBreaker(step, kameletPopulator));
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> circuitbreaker = new HashMap<>();
        circuitbreaker.put(CIRCUIT_BREAKER_LABEL, getCircuitBreaker().getRepresenterProperties());
        return circuitbreaker;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final boolean start, final boolean end) {
        return getCircuitBreaker().getStep(catalog, CIRCUIT_BREAKER_LABEL, kameletStepParserService);
    }

    public CircuitBreaker getCircuitBreaker() {
        return circuitBreaker;
    }

    public void setCircuitBreaker(final CircuitBreaker circuitBreaker) {
        this.circuitBreaker = circuitBreaker;
    }
}
