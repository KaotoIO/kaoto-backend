package io.zimara.backend.model.deployment.kamelet;

import java.util.HashMap;
import java.util.Map;

/**
 * 🐱class KameletBindingStep
 *
 * 🐱example
 *       kind: Kamelet
 *       apiVersion: camel.apache.org/v1alpha1
 *       name: twitter-search-source
 */
public class KameletBindingStepRef {

    private String kind = "Kamelet";
    private String apiVersion = "camel.apache.org/v1alpha1";
    private String name = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }
}
