package io.kaoto.backend.camel.model.deployment.kamelet.step;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.camel.KamelHelper;
import io.kaoto.backend.camel.KamelPopulator;
import io.kaoto.backend.camel.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.camel.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.parameter.ArrayParameter;
import io.kaoto.backend.model.parameter.ObjectParameter;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Branch;
import io.kaoto.backend.model.step.Step;

public class TryCatch extends EIPStep {

    public static final String STEPS_LABEL = KamelHelper.STEPS;

    public static final String DO_CATCH_LABEL = "do-catch";
    public static final String DO_CATCH_LABEL2 = "doCatch";

    public static final String DO_FINALLY_LABEL = "do-finally";
    public static final String DO_FINALLY_LABEL2 = "doFinally";

    @JsonProperty(STEPS_LABEL)
    private List<FlowStep> steps;

    @JsonProperty(DO_CATCH_LABEL)
    @JsonAlias(DO_CATCH_LABEL2)
    private List<DoCatch> doCatch;

    @JsonProperty(DO_FINALLY_LABEL)
    @JsonAlias(DO_FINALLY_LABEL2)
    private GenericFlowWithSteps doFinally;

    public TryCatch() {
        super();
    }

    public TryCatch(final Step step, final KamelPopulator kameletPopulator) {
        super(step);

        if (step.getBranches() != null) {
            if (!step.getBranches().isEmpty()) {
                var stepsBranch = step.getBranches().stream()
                        .filter(b -> b.getIdentifier().equalsIgnoreCase(STEPS_LABEL))
                        .findAny();
                if (stepsBranch.isPresent()) {
                    setSteps(kameletPopulator.processSteps(stepsBranch.get()));
                }
            }
            this.setDoCatch(new ArrayList<>());
            step.getBranches().stream()
                    .filter(b -> !b.getIdentifier().equalsIgnoreCase(DO_FINALLY_LABEL)
                                    && !b.getIdentifier().equalsIgnoreCase(STEPS_LABEL))
                    .forEach(branch -> this.getDoCatch().add(new DoCatch(branch, kameletPopulator)));
            var doFinally =
                    step.getBranches().stream()
                            .filter(b -> b.getIdentifier().equalsIgnoreCase(DO_FINALLY_LABEL))
                            .findAny();
            if (doFinally.isPresent()) {
                this.setDoFinally(new GenericFlowWithSteps());
                this.getDoFinally().setSteps(kameletPopulator.processSteps(doFinally.get()));
            }
        }
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new LinkedHashMap<>();
        properties.put(STEPS_LABEL, this.getSteps());
        List<Map<String, Object>> doC = new ArrayList<>();
        if (this.getDoCatch() != null) {
            for (var doCatch : this.getDoCatch()) {
                Map<String, Object> map = new LinkedHashMap<>();
                map.put("exception", doCatch.getExceptions());
                map.put("on-when", doCatch.getOnWhen());
                map.put(STEPS_LABEL, doCatch.getSteps());
                doC.add(map);
            }
        }
        properties.put(DO_CATCH_LABEL, doC);
        Map<String, Object> doF = new LinkedHashMap<>();
        if (doFinally != null) {
            doF.put(STEPS_LABEL, doFinally.getSteps());
        }
        properties.put(DO_FINALLY_LABEL, doF);
        return properties;
    }

    @Override
    public void processBranches(final Step step, final StepCatalog catalog,
                                final KameletStepParserService kameletStepParserService) {
        step.setBranches(new ArrayList<>());
        var identifier = STEPS_LABEL;
        step.getBranches().add(createBranch(identifier, this.getSteps(), kameletStepParserService));
        if (this.getDoCatch() != null) {
            int i = 1;
            for (DoCatch doC : this.getDoCatch()) {
                final var branch = createBranch(DO_CATCH_LABEL + "-" + i++, doC.getSteps(), kameletStepParserService);
                branch.setParameters(new ArrayList<>());
                setExceptions(doC, branch);
                setOnWhen(doC, branch);
                step.getBranches().add(branch);
            }
        }
        if (this.getDoFinally() != null) {
            step.getBranches()
                    .add(createBranch(DO_FINALLY_LABEL, this.getDoFinally().getSteps(), kameletStepParserService));
        }
    }

    private void setOnWhen(final DoCatch doC, final Branch branch) {
        final var onWhen = new ObjectParameter();
        onWhen.setValue(doC.getOnWhen());
        onWhen.setTitle("On When");
        onWhen.setId("on-when");
        onWhen.setNullable(true);
        branch.getParameters().add(onWhen);
    }

    private void setExceptions(final DoCatch doC, final Branch branch) {
        final var exceptions = new ArrayParameter();
        exceptions.setValue(doC.getExceptions().toArray());
        exceptions.setTitle("Exceptions");
        exceptions.setId("exceptions");
        exceptions.setNullable(false);
        branch.getParameters().add(exceptions);
    }


    @Override
    protected void assignAttribute(final Parameter parameter) {
    }

    @Override
    protected void assignProperty(final Parameter parameter) {
    }

    public List<FlowStep> getSteps() {
        return steps;
    }

    public void setSteps(final List<FlowStep> steps) {
        this.steps = steps;
    }

    public List<DoCatch> getDoCatch() {
        return doCatch;
    }

    public void setDoCatch(final List<DoCatch> doCatch) {
        this.doCatch = doCatch;
    }

    public GenericFlowWithSteps getDoFinally() {
        return doFinally;
    }

    public void setDoFinally(final GenericFlowWithSteps doFinally) {
        this.doFinally = doFinally;
    }
}
