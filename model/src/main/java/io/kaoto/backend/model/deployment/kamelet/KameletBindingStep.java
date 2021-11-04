package io.kaoto.backend.model.deployment.kamelet;

import java.util.HashMap;
import java.util.Map;

public final class KameletBindingStep {
    private KameletBindingStepRef ref;
    private String uri;
    private Map<String, String> properties = new HashMap<>();

    public KameletBindingStepRef getRef() {
        return ref;
    }

    public void setRef(final KameletBindingStepRef ref) {
        this.ref = ref;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(final Map<String, String> properties) {
        this.properties = properties;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(final String uri) {
        this.uri = uri;
    }
}
