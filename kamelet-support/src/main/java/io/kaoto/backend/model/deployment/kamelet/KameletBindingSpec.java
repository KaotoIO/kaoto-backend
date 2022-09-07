package io.kaoto.backend.model.deployment.kamelet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.model.KubernetesResource;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({"source", "steps", "sink"})
@JsonDeserialize(
        using = JsonDeserializer.None.class
)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class KameletBindingSpec
        implements KubernetesResource, Serializable {
    private static final long serialVersionUID = 7040723764223374489L;

    @JsonProperty("source")
    private KameletBindingStep source;

    @JsonProperty("steps")
    private List<KameletBindingStep> steps = new ArrayList<>();

    @JsonProperty("sink")
    private KameletBindingStep sink;

    public KameletBindingStep getSource() {
        return source;
    }

    public void setSource(final KameletBindingStep source) {
        this.source = source;
    }

    public List<KameletBindingStep> getSteps() {
        return steps;
    }

    public KameletBindingStep getSink() {
        return sink;
    }

    public void setSink(final KameletBindingStep sink) {
        this.sink = sink;
    }

    public void setSteps(final List<KameletBindingStep> steps) {
        this.steps = steps;
    }

    @Override
    public String toString() {
        return "KameletBindingSpec{" +
                "source=" + source +
                ", steps=" + steps +
                ", sink=" + sink +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof KameletBindingSpec)) {
            return false;
        }

        final KameletBindingSpec that = (KameletBindingSpec) o;

        if (getSource() != null ? !getSource().equals(that.getSource()) : that.getSource() != null) {
            return false;
        }
        if (getSteps() != null ? !getSteps().equals(that.getSteps()) : that.getSteps() != null) {
            return false;
        }
        return getSink() != null ? getSink().equals(that.getSink()) : that.getSink() == null;
    }

    @Override
    public int hashCode() {
        int result = getSource() != null ? getSource().hashCode() : 0;
        result = 31 * result + (getSteps() != null ? getSteps().hashCode() : 0);
        result = 31 * result + (getSink() != null ? getSink().hashCode() : 0);
        return result;
    }
}
