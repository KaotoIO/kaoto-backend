package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.kaoto.backend.KamelPopulator;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Branch;
import io.kaoto.backend.model.step.Step;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CircuitBreaker extends EIPStep {

    public static final String STEPS_LABEL = "steps";
    public static final String ON_FALLBACK_LABEL = "on-fallback";
    public static final String RESILIENCE_4_J_CONFIGURATION_LABEL = "resilience-4j-configuration";
    public static final String FAULT_TOLERANCE_CONFIGURATION_LABEL = "fault-tolerance-configuration";
    public static final String CONFIGURATION_LABEL = "configuration";
    public static final String DESCRIPTION_LABEL = "description";
    public static final String RESILIENCE_4_J_CONFIGURATION_LABEL2 = "resilience4jConfiguration";
    public static final String FAULT_TOLERANCE_CONFIGURATION_LABEL2 = "faultToleranceConfiguration";

    @JsonProperty(STEPS_LABEL)
    private List<FlowStep> steps;

    @JsonProperty(ON_FALLBACK_LABEL)
    private CircuitBreakerOnFallback onFallback;

    @JsonProperty(RESILIENCE_4_J_CONFIGURATION_LABEL)
    private Map<String, String> resilience4jConfiguration;

    @JsonProperty(FAULT_TOLERANCE_CONFIGURATION_LABEL)
    private Map<String, String> faultToleranceConfiguration;

    @JsonProperty(CONFIGURATION_LABEL)
    private String configuration;

    @JsonProperty(DESCRIPTION_LABEL)
    private Map<String, String> description;

    public CircuitBreaker() {
        super();
    }

    @JsonCreator
    public CircuitBreaker(
            final @JsonProperty(STEPS_LABEL) List<FlowStep> steps,
            final @JsonProperty(ON_FALLBACK_LABEL) CircuitBreakerOnFallback onFallback,
            final @JsonProperty(RESILIENCE_4_J_CONFIGURATION_LABEL) Map<String, String>  resilience4jConfiguration,
            final @JsonProperty(FAULT_TOLERANCE_CONFIGURATION_LABEL) Map<String, String>  faultToleranceConfiguration,
            final @JsonProperty(CONFIGURATION_LABEL) String configuration,
            final @JsonProperty(DESCRIPTION_LABEL) Map<String, String> description) {
        super();
        setSteps(steps);
        setOnFallback(onFallback);
        setResilience4jConfiguration(resilience4jConfiguration);
        setFaultToleranceConfiguration(faultToleranceConfiguration);
        setConfiguration(configuration);
        setDescription(description);
    }

    public CircuitBreaker(final Step step, final KamelPopulator kameletPopulator) {
        super(step);

        if (step.getBranches() != null) {
            if (!step.getBranches().isEmpty()) {
                setSteps(kameletPopulator.processSteps(step.getBranches().get(0)));
            }
            if (step.getBranches().size() > 1) {
                setOnFallback(new CircuitBreakerOnFallback());
                getOnFallback().setSteps(kameletPopulator.processSteps(step.getBranches().get(1)));
            }
        }
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new HashMap<>();

        if (this.resilience4jConfiguration != null) {
            properties.put(RESILIENCE_4_J_CONFIGURATION_LABEL2, this.getResilience4jConfiguration());
        }
        if (this.faultToleranceConfiguration != null) {
            properties.put(FAULT_TOLERANCE_CONFIGURATION_LABEL2, this.getFaultToleranceConfiguration());
        }
        if (this.configuration != null) {
            properties.put(CONFIGURATION_LABEL, this.getConfiguration());
        }
        if (this.description != null) {
            properties.put(DESCRIPTION_LABEL, this.getDescription());
        }

        properties.put(STEPS_LABEL, this.getSteps());
        Map<String, Object> fallbacksteps = new HashMap<>();
        properties.put(ON_FALLBACK_LABEL, fallbacksteps);
        fallbacksteps.put(STEPS_LABEL, this.getOnFallback().getSteps());

        return properties;
    }

    @Override
    public void processBranches(final Step step, final StepCatalog catalog,
                        final KameletStepParserService kameletStepParserService) {
        step.setBranches(new LinkedList<>());

        var identifier = STEPS_LABEL;
        if (this.getDescription() != null) {
            identifier = this.getDescription().toString();
        }
        Branch branch = new Branch(identifier);
        step.getBranches().add(branch);
        if (this.getSteps() != null) {
            for (var s : this.getSteps()) {
                branch.getSteps().add(kameletStepParserService.processStep(s));
            }
        }
        branch = new Branch(ON_FALLBACK_LABEL);
        step.getBranches().add(branch);
        if (this.getOnFallback() != null && this.getOnFallback().getSteps() != null) {
            for (var s : this.getOnFallback().getSteps()) {
                branch.getSteps().add(kameletStepParserService.processStep(s));
            }
        }
    }


    @Override
    void assignAttribute(final Parameter parameter) {
        switch (parameter.getId()) {
            case RESILIENCE_4_J_CONFIGURATION_LABEL2:
                this.setResilience4jConfiguration((Map<String, String>) parameter.getValue());
                break;
            case FAULT_TOLERANCE_CONFIGURATION_LABEL2:
                this.setFaultToleranceConfiguration((Map<String, String>) parameter.getValue());
                break;
            case CONFIGURATION_LABEL:
                this.setConfiguration(parameter.getValue().toString());
                break;
            case DESCRIPTION_LABEL:
                this.setDescription((Map<String, String>) parameter.getValue());
                break;
            default:
                log.error("Unknown property: " + parameter.getId());
                break;
        }
    }

    @Override
    void assignProperty(final Parameter parameter) {
        switch (parameter.getId()) {
            case RESILIENCE_4_J_CONFIGURATION_LABEL2:
                parameter.setValue(this.getResilience4jConfiguration());
                break;
            case FAULT_TOLERANCE_CONFIGURATION_LABEL2:
                parameter.setValue(this.getFaultToleranceConfiguration());
                break;
            case CONFIGURATION_LABEL:
                parameter.setValue(this.getConfiguration());
                break;
            case DESCRIPTION_LABEL:
                parameter.setValue(this.getDescription());
                break;
            default:
                log.error("Unknown property: " + parameter.getId());
                break;
        }
    }

    public List<FlowStep> getSteps() {
        return steps;
    }

    public void setSteps(final List<FlowStep> steps) {
        this.steps = steps;
    }

    public CircuitBreakerOnFallback getOnFallback() {
        return onFallback;
    }

    public void setOnFallback(final CircuitBreakerOnFallback onFallback) {
        this.onFallback = onFallback;
    }

    public Map<String, String>  getResilience4jConfiguration() {
        return resilience4jConfiguration;
    }

    public void setResilience4jConfiguration(final Map<String, String>  resilience4jConfiguration) {
        this.resilience4jConfiguration = resilience4jConfiguration;
    }

    public Map<String, String>  getFaultToleranceConfiguration() {
        return faultToleranceConfiguration;
    }

    public void setFaultToleranceConfiguration(final Map<String, String>  faultToleranceConfiguration) {
        this.faultToleranceConfiguration = faultToleranceConfiguration;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(final String configuration) {
        this.configuration = configuration;
    }

    public Map<String, String>  getDescription() {
        return description;
    }

    public void setDescription(final Map<String, String>  description) {
        this.description = description;
    }
}
