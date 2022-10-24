package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.KamelPopulator;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.model.step.Step;

import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CircuitBreakerFlowStep implements FlowStep {
    @Serial
    private static final long serialVersionUID = 2863336982210367342L;

    @JsonProperty("circuit-breaker")
    private CircuitBreaker circuitBreaker;

    @JsonCreator
    public CircuitBreakerFlowStep(final @JsonProperty("circuit-breaker") CircuitBreaker circuitBreaker) {
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
        circuitbreaker.put("circuit-breaker", getCircuitBreaker().getRepresenterProperties());
        return circuitbreaker;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService) {
        return getCircuitBreaker().getStep(catalog, kameletStepParserService);
    }

    public CircuitBreaker getCircuitBreaker() {
        return circuitBreaker;
    }

    public void setCircuitBreaker(final CircuitBreaker circuitBreaker) {
        this.circuitBreaker = circuitBreaker;
    }
}
