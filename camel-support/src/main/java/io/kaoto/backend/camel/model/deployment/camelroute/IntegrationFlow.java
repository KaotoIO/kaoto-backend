package io.kaoto.backend.camel.model.deployment.camelroute;

import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import java.util.List;
import java.util.Map;

public class IntegrationFlow {
    private List<Step> steps;
    private List<Parameter> parameters;
    private Map<String, Object> metadata;

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(final List<Step> steps) {
        this.steps = steps;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(final Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(
            final List<Parameter> parameters) {
        this.parameters = parameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntegrationFlow that)) return false;

        if (getSteps() != null ? !getSteps().equals(that.getSteps()) : that.getSteps() != null) return false;
        if (getParameters() != null ? !getParameters().equals(that.getParameters()) : that.getParameters() != null)
            return false;
        return getMetadata() != null ? getMetadata().equals(that.getMetadata()) : that.getMetadata() == null;
    }

    @Override
    public int hashCode() {
        int result = getSteps() != null ? getSteps().hashCode() : 0;
        result = 31 * result + (getParameters() != null ? getParameters().hashCode() : 0);
        result = 31 * result + (getMetadata() != null ? getMetadata().hashCode() : 0);
        return result;
    }
}


