package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kaoto.backend.KamelPopulator;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import java.util.List;
import java.util.Map;


public class Pipeline extends EIPStep {
    public static final String STEPS_LABEL = "steps";

    @JsonProperty(STEPS_LABEL)
    private List<FlowStep> steps;


    public Pipeline() {
         //Needed for serialization
    }

    public Pipeline(Step step, final KamelPopulator kameletPopulator) {
        super(step);

        if (step.getBranches() != null && !step.getBranches().isEmpty()) {
            setSteps(kameletPopulator.processSteps(step.getBranches().get(0)));
        }
    }

    @Override
    public void processBranches(final Step step, final StepCatalog catalog,
                                final KameletStepParserService kameletStepParserService) {
        step.setBranches(List.of(createBranch(STEPS_LABEL, this.getSteps(), kameletStepParserService)));
    }

    @Override
    protected void assignAttribute(final Parameter parameter) {
        //We have no attributes
    }

    @Override
    protected void assignProperty(final Parameter parameter) {
        //We have no attributes
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = super.getDefaultRepresenterProperties();
        if (this.getSteps() != null) {
            properties.put(STEPS_LABEL, this.getSteps());
        }

        return properties;
    }
    public List<FlowStep> getSteps() {
        return steps;
    }

    public void setSteps(final List<FlowStep> steps) {
        this.steps = steps;
    }
}
