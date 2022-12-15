package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import java.util.HashMap;
import java.util.Map;


public class Transacted extends EIPStep {

    public static final String REF_LABEL = "ref";

    private String ref;


    public Transacted() {
        //Needed for serialization
    }

    public Transacted(Step step) {
        super(step);
    }

    public Transacted(final @JsonProperty(REF_LABEL) String ref) {
        super();
        setRef(ref);
    }

    @Override
    protected void assignAttribute(final Parameter parameter) {
        this.setRef(String.valueOf(parameter.getValue()));
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        var properties = new HashMap<String, Object>();
        if (this.getRef() != null) {
            properties.put(REF_LABEL, this.getRef());
        }
        return properties;
    }

    @Override
    protected void assignProperty(final Parameter parameter) {
        parameter.setValue(this.getRef());
    }

    public String getRef() {
        return ref;
    }

    public void setRef(final String ref) {
        this.ref = ref;
    }
}
