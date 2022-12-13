package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kaoto.backend.KamelPopulator;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Filter extends EIPStep implements ConditionBlock {

    public static final String STEPS_LABEL = "steps";
    public static final String SIMPLE_LABEL = "simple";
    public static final String JQ_LABEL = "jq";
    @JsonProperty(SIMPLE_LABEL)
    private String simple;
    @JsonProperty(JQ_LABEL)
    private String jq;

    @JsonProperty(STEPS_LABEL)
    private List<FlowStep> steps;

    public Filter(Step step, final KamelPopulator kameletPopulator) {
        super(step);

        if (step.getBranches() != null && !step.getBranches().isEmpty()) {
            setSteps(kameletPopulator.processSteps(step.getBranches().get(0)));
        }
    }

    public Filter() {
        //Needed for serialization
    }

    public List<FlowStep> getSteps() {
        return steps;
    }

    public void setSteps(
            final List<FlowStep> steps) {
        this.steps = steps;
    }

    @Override
    protected void processBranches(final Step step, final StepCatalog catalog,
                                   final KameletStepParserService kameletStepParserService) {
        var id = STEPS_LABEL;
        if (getSimple() != null && !getSimple().isBlank()) {
            id = String.valueOf(getSimple());
        } else if (getJq() != null && !getJq().isBlank()) {
            id = String.valueOf(getJq());
        }
        step.setBranches(List.of(createBranch(id, this.getSteps(), kameletStepParserService)));
    }
    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new HashMap<>();
        if (this.getSimple() != null) {
            properties.put(SIMPLE_LABEL, this.getSimple());
        }
        if (this.getJq() != null) {
            properties.put(JQ_LABEL, this.getJq());
        }
        if (this.getSteps() != null) {
            properties.put(STEPS_LABEL, this.getSteps());
        }

        return properties;
    }



    @Override
    protected void assignProperty(final Parameter parameter) {
        switch (parameter.getId()) {
            case SIMPLE_LABEL:
                parameter.setValue(this.getSimple());
                break;
            case JQ_LABEL:
                parameter.setValue(this.getJq());
                break;
            default:
                break;
        }
    }

    @Override
    protected void assignAttribute(final Parameter parameter) {
        switch (parameter.getId()) {
            case SIMPLE_LABEL:
                this.setSimple(String.valueOf(parameter.getValue()));
                break;
            case JQ_LABEL:
                this.setJq(String.valueOf(parameter.getValue()));
                break;
            default:
                break;
        }
    }

    public String getSimple() {
        return simple;
    }

    public void setSimple(final String simple) {
        this.simple = simple;
    }
    public String getJq() {
        return jq;
    }

    public void setJq(final String jq) {
        this.jq = jq;
    }
}
