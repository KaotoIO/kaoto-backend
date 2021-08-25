package io.zimara.backend.model.deployment.kamelet;

import java.util.HashMap;
import java.util.Map;

/**
 * 🐱class KameletBindingStep
 *
 * 🐱example
 *     ref:
 *       kind: Kamelet
 *       apiVersion: camel.apache.org/v1alpha1
 *       name: twitter-search-source
 *     properties:
 *       keywords: "Apache Camel"
 *       apiKey: "your own"
 *       apiKeySecret: "your own"
 *       accessToken: "your own"
 *       accessTokenSecret: "your own"
 */
public class KameletBindingStep {
    private KameletBindingStepRef ref;
    private Map<String, String> properties = new HashMap<>();

    public KameletBindingStepRef getRef() {
        return ref;
    }

    public void setRef(KameletBindingStepRef ref) {
        this.ref = ref;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }
}
