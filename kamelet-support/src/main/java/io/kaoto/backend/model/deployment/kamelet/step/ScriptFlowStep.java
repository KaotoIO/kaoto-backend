package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import java.util.HashMap;
import java.util.Map;

@JsonDeserialize(using = ScriptDeserializer.class)
public class ScriptFlowStep implements FlowStep {
    public static final String LABEL = "script";

    private HashMap<String, Object> properties;

    public ScriptFlowStep() {
        //Needed for the serialization
    }

    public ScriptFlowStep(final Step step) {
        this.setProperties(new HashMap<>());
        if (step.getParameters() != null) {
            for (Parameter p : step.getParameters()) {
                if ("properties".equalsIgnoreCase(p.getId()) && p.getValue() != null) {
                    this.getProperties().putAll((Map) p.getValue());
                }
            }
        }
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> values = new HashMap<>();
        values.put(LABEL, properties);
        return values;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final Boolean start, final Boolean end) {
        Step res = catalog.getReadOnlyCatalog()
                .searchByName(LABEL).stream()
                .filter(step -> step.getKind().equalsIgnoreCase("EIP"))
                .findAny().orElse(null);
        if (res != null) {
            assignParameters(res);
        }
        return res;
    }

    protected void assignParameters(final Step res) {
        for (var param : res.getParameters()) {
            param.setValue(this.getProperties());
        }
    }

    public HashMap<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(final HashMap<String, Object> properties) {
        this.properties = properties;
    }
}
