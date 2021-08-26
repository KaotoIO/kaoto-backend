package io.zimara.backend.model.deployment.kamelet;

import java.util.HashMap;
import java.util.Map;

/**
 * 🐱class KameletBinding
 * Represents a Kamelet binding that can be deployed
 * <p>
 * 🐱example
 * <p>
 * apiVersion: camel.apache.org/v1alpha1
 * kind: KameletBinding
 * metadata:
 * name: twitter-search-source-binding
 * spec:
 * source:
 * ref:
 * kind: Kamelet
 * apiVersion: camel.apache.org/v1alpha1
 * name: twitter-search-source
 * properties:
 * keywords: "Apache Camel"
 * apiKey: "your own"
 * apiKeySecret: "your own"
 * accessToken: "your own"
 * accessTokenSecret: "your own"
 * steps:
 * -
 * ref:
 * kind: Kamelet
 * apiVersion: camel.apache.org/v1alpha1
 * name: aws-translate-action
 * -
 * ref:
 * kind: Kamelet
 * apiVersion: camel.apache.org/v1alpha1
 * name: avro-deserialize-action
 * sink:
 * ref:
 * kind: Kamelet
 * apiVersion: camel.apache.org/v1alpha1
 * name: kafka-sink
 * properties:
 * brokers: "The Brokers"
 * password: "The Password"
 * topic: "The Topic Names"
 * username: "The Username"
 */
public class KameletBinding {
    private String apiVersion = "camel.apache.org/v1alpha1";
    private String kind = "KameletBinding";
    private Map<String, String> metadata = new HashMap<>();
    private KameletBindingSpec spec;

    public KameletBinding() {
    }

    public KameletBinding(String name, KameletBindingSpec spec) {
        this();
        setSpec(spec);
        getMetadata().put("name", name);
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public String getKind() {
        return kind;
    }

    public  Map<String, String> getMetadata() {
        return metadata;
    }

    public KameletBindingSpec getSpec() {
        return spec;
    }

    public void setSpec(KameletBindingSpec spec) {
        this.spec = spec;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

}
