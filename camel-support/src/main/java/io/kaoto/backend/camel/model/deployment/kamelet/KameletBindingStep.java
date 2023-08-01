package io.kaoto.backend.camel.model.deployment.kamelet;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kaoto.backend.camel.KamelHelper;
import org.apache.camel.v1alpha1.kameletbindingspec.Sink;
import org.apache.camel.v1alpha1.kameletbindingspec.Source;
import org.apache.camel.v1alpha1.kameletbindingspec.Steps;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public final class KameletBindingStep {

    @JsonProperty("ref")
    private KameletBindingStepRef ref;

    @JsonProperty("uri")
    private String uri;

    @JsonProperty("properties")
    private Map<String, Object> properties = new HashMap<>();

    @JsonProperty(KamelHelper.PARAMETERS)
    private Map<String, Object> parameters = new HashMap<>();

    public KameletBindingStep() {
    }


    public KameletBindingStep(final Map<String, Object> additionalProperties,
                              final KameletBindingStepRef kameletBindingStepRef,
                              final String uri) {
        this.setProperties(additionalProperties);
        this.setRef(kameletBindingStepRef);
        this.setUri(uri);
    }

    public KameletBindingStep(final Source camelKStep) {
        this(camelKStep.getProperties() != null ? camelKStep.getProperties().getAdditionalProperties() : null,
                new KameletBindingStepRef(camelKStep.getRef()),
                camelKStep.getUri());
    }
    public KameletBindingStep(final Sink camelKStep) {
        this(camelKStep.getProperties() != null ? camelKStep.getProperties().getAdditionalProperties() : null,
                new KameletBindingStepRef(camelKStep.getRef()),
                camelKStep.getUri());
    }
    public KameletBindingStep(final Steps camelKStep) {
        this(camelKStep.getProperties() != null ? camelKStep.getProperties().getAdditionalProperties() : null,
                new KameletBindingStepRef(camelKStep.getRef()),
                camelKStep.getUri());
    }

    public KameletBindingStepRef getRef() {
        return ref;
    }

    public void setRef(final KameletBindingStepRef ref) {
        this.ref = ref;
    }

    public void setProperties(final Map<String, Object> properties) {
        this.properties = properties;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(final String uri) {
        this.uri = uri;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(final Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "KameletBindingStep{" +
                "ref=" + ref +
                ", uri='" + uri + '\'' +
                ", properties=" + properties +
                ", parameters=" + parameters +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof KameletBindingStep)) {
            return false;
        }

        final KameletBindingStep that = (KameletBindingStep) o;

        if (getRef() != null ? !getRef().equals(that.getRef()) : that.getRef() != null) {
            return false;
        }
        if (getUri() != null ? !getUri().equals(that.getUri()) : that.getUri() != null) {
            return false;
        }
        if (getProperties() != null ? !getProperties().equals(that.getProperties()) : that.getProperties() != null) {
            return false;
        }
        return getParameters() != null ? getParameters().equals(that.getParameters()) : that.getParameters() == null;
    }

    @Override
    public int hashCode() {
        int result = getRef() != null ? getRef().hashCode() : 0;
        result = 31 * result + (getUri() != null ? getUri().hashCode() : 0);
        result = 31 * result + (getProperties() != null ? getProperties().hashCode() : 0);
        result = 31 * result + (getParameters() != null ? getParameters().hashCode() : 0);
        return result;
    }

    public Source getSource() {
        Source source = new Source();
        var properties = new org.apache.camel.v1alpha1.kameletbindingspec.source.Properties();
        properties.setAdditionalProperties(this.getProperties());
        source.setProperties(properties);
        source.setUri(this.getUri());
        source.setRef(this.getRef().getSourceRef());
        var types = new LinkedHashMap<String, org.apache.camel.v1alpha1.kameletbindingspec.source.Types>();
        source.setTypes(types);
        return source;
    }

    public Sink getSink() {
        Sink sink = new Sink();
        var properties = new org.apache.camel.v1alpha1.kameletbindingspec.sink.Properties();
        properties.setAdditionalProperties(this.getProperties());
        sink.setProperties(properties);
        sink.setUri(this.getUri());
        sink.setRef(this.getRef().getSinkRef());
        var types = new LinkedHashMap<String, org.apache.camel.v1alpha1.kameletbindingspec.sink.Types>();
        sink.setTypes(types);
        return sink;
    }

    public Steps getSteps() {
        Steps steps = new Steps();
        var properties = new org.apache.camel.v1alpha1.kameletbindingspec.steps.Properties();
        properties.setAdditionalProperties(this.getProperties());
        steps.setProperties(properties);
        steps.setUri(this.getUri());
        steps.setRef(this.getRef().getStepsRef());
        var types = new LinkedHashMap<String, org.apache.camel.v1alpha1.kameletbindingspec.steps.Types>();
        steps.setTypes(types);
        return steps;
    }
}
