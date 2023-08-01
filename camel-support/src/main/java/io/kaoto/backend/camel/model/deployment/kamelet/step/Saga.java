package io.kaoto.backend.camel.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kaoto.backend.camel.KamelHelper;
import io.kaoto.backend.camel.KamelPopulator;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.camel.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.camel.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import java.util.List;
import java.util.Map;


public class Saga extends EIPStep {
    public static final String STEPS_LABEL = KamelHelper.STEPS;

    public static final String SAGA_SERVICE_LABEL = "saga-service";
    public static final String SAGA_SERVICE_LABEL2 = "sagaService";

    public static final String PROPAGATION_LABEL = "propagation";

    public static final String COMPLETION_MODE_LABEL = "completion-mode";
    public static final String COMPLETION_MODE_LABEL2 = "completionMode";

    public static final String TIMEOUT_LABEL = "timeout";

    public static final String COMPENSATION_LABEL = "compensation";

    public static final String COMPLETION_LABEL = "completion";

    public static final String OPTION_LABEL = "option";

    public static final String DESCRIPTION_LABEL = KamelHelper.DESCRIPTION;

    private List<FlowStep> steps;

    private Map<String, Object> sagaService;

    private String propagation;

    private String completionMode;

    private String timeout;

    private Object compensation;

    private Object completion;

    private Object option;

    private Map<String, String> description;


    public Saga() {
        //Needed for serialization
    }

    public Saga(final @JsonProperty(SAGA_SERVICE_LABEL) Map<String, Object> sagaService,
                final @JsonProperty(SAGA_SERVICE_LABEL2) Map<String, Object> sagaService2,
                final @JsonProperty(PROPAGATION_LABEL) String propagation,
                final @JsonProperty(COMPLETION_MODE_LABEL) String completionMode,
                final @JsonProperty(COMPLETION_MODE_LABEL2) String completionMode2,
                final @JsonProperty(TIMEOUT_LABEL) String timeout,
                final @JsonProperty(COMPENSATION_LABEL) Object compensation,
                final @JsonProperty(COMPLETION_LABEL) Object completion,
                final @JsonProperty(OPTION_LABEL) Object option,
                final @JsonProperty(DESCRIPTION_LABEL) Map<String, String> description,
                final @JsonProperty("id") String id) {
        setSagaService(sagaService != null ? sagaService : sagaService2);
        setPropagation(propagation);
        setCompletionMode(completionMode != null ? completionMode : completionMode2);
        setTimeout(timeout);
        setCompensation(compensation);
        setCompletion(completion);
        setOption(option);
        setDescription(description);
        setId(id);
    }


    public Saga(Step step, final KamelPopulator kameletPopulator) {
        super(step);

        if (step.getBranches() != null && !step.getBranches().isEmpty()) {
            setSteps(kameletPopulator.processSteps(step.getBranches().get(0)));
        }
    }


    @Override
    protected void assignAttribute(final Parameter parameter) {
        switch (parameter.getId()) {
            case SAGA_SERVICE_LABEL:
            case SAGA_SERVICE_LABEL2:
                this.setSagaService((Map<String, Object>) parameter.getValue());
                break;
            case PROPAGATION_LABEL:
                this.setPropagation(String.valueOf(parameter.getValue()));
                break;
            case COMPLETION_MODE_LABEL:
            case COMPLETION_MODE_LABEL2:
                this.setCompletionMode(String.valueOf(parameter.getValue()));
                break;
            case TIMEOUT_LABEL:
                this.setTimeout(String.valueOf(parameter.getValue()));
                break;
            case COMPENSATION_LABEL:
                this.setCompensation(parameter.getValue());
                break;
            case COMPLETION_LABEL:
                this.setCompletion(parameter.getValue());
                break;
            case OPTION_LABEL:
                this.setOption(parameter.getValue());
                break;
            case DESCRIPTION_LABEL:
                this.setDescription((Map<String, String>) parameter.getValue());
                break;
            default:
                break;
        }
    }

    @Override
    public void processBranches(final Step step, final StepCatalog catalog,
                                final KameletStepParserService kameletStepParserService) {
        step.setBranches(List.of(createBranch(STEPS_LABEL, this.getSteps(), kameletStepParserService)));
    }

    @Override
    protected void assignProperty(final Parameter parameter) {
        switch (parameter.getId()) {
            case SAGA_SERVICE_LABEL:
            case SAGA_SERVICE_LABEL2:
                parameter.setValue(this.getSagaService());
                break;
            case PROPAGATION_LABEL:
                parameter.setValue(this.getPropagation());
                break;
            case COMPLETION_MODE_LABEL:
            case COMPLETION_MODE_LABEL2:
                parameter.setValue(this.getCompletionMode());
                break;
            case TIMEOUT_LABEL:
                parameter.setValue(this.getTimeout());
                break;
            case COMPENSATION_LABEL:
                parameter.setValue(this.getCompensation());
                break;
            case COMPLETION_LABEL:
                parameter.setValue(this.getCompletion());
                break;
            case OPTION_LABEL:
                parameter.setValue(this.getOption());
                break;
            case DESCRIPTION_LABEL:
                parameter.setValue(this.description);
                break;
            default:
                break;
        }
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = super.getDefaultRepresenterProperties();
        if (this.getSagaService() != null) {
            properties.put(SAGA_SERVICE_LABEL, this.getSagaService());
        }
        if (this.getPropagation() != null) {
            properties.put(PROPAGATION_LABEL, this.getPropagation());
        }
        if (this.getCompletionMode() != null) {
            properties.put(COMPLETION_MODE_LABEL, this.getCompletionMode());
        }
        if (this.getTimeout() != null) {
            properties.put(TIMEOUT_LABEL, this.getTimeout());
        }
        if (this.getCompensation() != null) {
            properties.put(COMPENSATION_LABEL, this.getCompensation());
        }
        if (this.getCompletion() != null) {
            properties.put(COMPLETION_LABEL, this.getCompletion());
        }
        if (this.getOption() != null) {
            properties.put(OPTION_LABEL, this.getOption());
        }
        if (this.getDescription() != null) {
            properties.put(DESCRIPTION_LABEL, this.getDescription());
        }
        if (this.getSteps() != null) {
            properties.put(STEPS_LABEL, this.getSteps());
        }

        return properties;
    }

    public Map<String, Object> getSagaService() {
        return sagaService;
    }

    public void setSagaService(final Map<String, Object> sagaService) {
        this.sagaService = sagaService;
    }

    public String getPropagation() {
        return propagation;
    }

    public void setPropagation(final String propagation) {
        this.propagation = propagation;
    }

    public String getCompletionMode() {
        return completionMode;
    }

    public void setCompletionMode(final String completionMode) {
        this.completionMode = completionMode;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(final String timeout) {
        this.timeout = timeout;
    }

    public Object getCompensation() {
        return compensation;
    }

    public void setCompensation(final Object compensation) {
        this.compensation = compensation;
    }

    public Object getCompletion() {
        return completion;
    }

    public void setCompletion(final Object completion) {
        this.completion = completion;
    }

    public Object getOption() {
        return option;
    }

    public void setOption(final Object option) {
        this.option = option;
    }

    public Map<String, String> getDescription() {
        return description;
    }

    public void setDescription(final Map<String, String> description) {
        this.description = description;
    }

    public List<FlowStep> getSteps() {
        return steps;
    }

    public void setSteps(final List<FlowStep> steps) {
        this.steps = steps;
    }
}
