package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import java.util.Map;


public class PollEnrich extends Enrich {
    public static final String TIMEOUT_LABEL = "timeout";

    @JsonProperty(TIMEOUT_LABEL)
    private String timeout;


    public PollEnrich() {
         //Needed for serialization
    }

    public PollEnrich(Step step) {
        super(step);
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = super.getRepresenterProperties();
        if (this.getTimeout() != null) {
            properties.put(TIMEOUT_LABEL, this.getTimeout());
        }
        return properties;
    }

    @Override
    protected void assignProperty(final Parameter parameter) {
        super.assignProperty(parameter);
        if (parameter.getId().equalsIgnoreCase(TIMEOUT_LABEL)) {
                parameter.setValue(this.getTimeout());
        }
    }


    @Override
    protected void assignAttribute(final Parameter parameter) {
        super.assignAttribute(parameter);
        if (parameter.getId().equalsIgnoreCase(TIMEOUT_LABEL)) {
            this.setTimeout(parameter.getValue().toString());
        }
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(final String timeout) {
        this.timeout = timeout;
    }
}
