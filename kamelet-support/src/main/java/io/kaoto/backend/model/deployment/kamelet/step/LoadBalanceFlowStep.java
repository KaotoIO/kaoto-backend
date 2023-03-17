package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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

@JsonDeserialize(using = LoadBalanceDeserializer.class)
public class LoadBalanceFlowStep implements FlowStep {
    public static final String LOAD_BALANCE_LABEL = "load-balance";

    private HashMap<String, Object> properties;
    private List<FlowStep> steps;

    public LoadBalanceFlowStep() {
        //Needed for the serialization
    }

    public LoadBalanceFlowStep(final Step step, final KamelPopulator kameletPopulator) {
        this.setProperties(new HashMap<>());
        if (step.getParameters() != null) {
            for (Parameter p : step.getParameters()) {
                if ("properties".equalsIgnoreCase(p.getId()) && p.getValue() != null) {
                    this.getProperties().putAll((Map) p.getValue());
                }
            }
        }

        //The UI will want branches, but the DSL uses a list of steps
        this.setSteps(new LinkedList<>());
        if (step.getBranches() != null) {
            for (Branch b : step.getBranches()) {
                for (var s : kameletPopulator.processSteps(b)) {
                    this.getSteps().add(s);
                }
            }
        }

    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> step = new HashMap<>();
        Map<String, Object> values = new HashMap<>();
        values.putAll(getProperties());
        values.put("steps", this.getSteps());
        step.put(LOAD_BALANCE_LABEL, values);
        return step;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final Boolean start, final Boolean end) {
        Step res = catalog.getReadOnlyCatalog()
                .searchByName(LOAD_BALANCE_LABEL).stream()
                .filter(step -> step.getKind().equalsIgnoreCase("EIP")
                        || step.getKind().equalsIgnoreCase("EIP-BRANCH"))
                .findAny().orElse(null);
        if (res != null) {
            assignParameters(res);
            int i = 1;
            res.setBranches(new LinkedList<>());
            for (var step : this.getSteps()) {
                var b = new Branch();
                b.setIdentifier(String.valueOf(i++));
                b.setSteps(List.of(kameletStepParserService.processStep(step, false, true)));
                res.getBranches().add(b);
            }
        }
        return res;
    }

    protected void assignParameters(final Step res) {
        for (var param : res.getParameters()) {
            if (!param.getId().equalsIgnoreCase("step-id-kaoto")) {
                param.setValue(this.getProperties());
            }
        }
    }

    public HashMap<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(final HashMap<String, Object> properties) {
        this.properties = properties;
    }

    public List<FlowStep> getSteps() {
        return steps;
    }

    public void setSteps(final List<FlowStep> steps) {
        this.steps = steps;
    }
}
