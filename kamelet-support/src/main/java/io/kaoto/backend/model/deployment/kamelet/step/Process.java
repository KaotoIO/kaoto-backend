package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import java.util.HashMap;
import java.util.Map;


public class Process extends EIPStep {
    public static final String REF_LABEL = "ref";

    @JsonProperty(REF_LABEL)
    private String ref;


    public Process() {
         //Needed for serialization
    }

    public Process(Step step) {
        super(step);
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new HashMap<>();
        if (this.getRef() != null) {
            properties.put(REF_LABEL, this.getRef());
        }
        return properties;
    }

    @Override
    protected void assignProperty(final Parameter parameter) {
        if (parameter.getId().equalsIgnoreCase(REF_LABEL)) {
                parameter.setValue(this.getRef());
        }
    }


    @Override
    protected void assignAttribute(final Parameter parameter) {
        if (parameter.getId().equalsIgnoreCase(REF_LABEL)) {
            this.setRef(String.valueOf(parameter.getValue()));
        }
    }

    public String getRef() {
        return ref;
    }

    public void setRef(final String ref) {
        this.ref = ref;
    }
}
