package io.kaoto.backend.model.deployment.kamelet.step;

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
        step.setBranches(new LinkedList<>());

        Branch branch = new Branch(STEPS_LABEL);
        if (this.getSteps() != null) {
            int i = 0;
            for (var s : this.getSteps()) {
                var _step = kameletStepParserService
                        .processStep(s, i == 0,i++ == this.getSteps().size() - 1);
                branch.getSteps().add(_step);
            }
        }
        step.getBranches().add(branch);
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
        Map<String, Object> properties = new HashMap<>();
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
