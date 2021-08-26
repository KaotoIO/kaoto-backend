package io.zimara.backend.model.deployment.kamelet;

import java.util.ArrayList;
import java.util.List;

/**
 * 🐱class KameletBindingSpec
 *
 * 🐱example
 ```
 spec:
     source:
         ref:
             kind: Kamelet
             apiVersion: camel.apache.org/v1alpha1
             name: twitter-search-source
        properties:
             keywords: "Apache Camel"
             apiKey: "your own"
             apiKeySecret: "your own"
             accessToken: "your own"
         accessTokenSecret: "your own"
     steps:
         -
             ref:
                 kind: Kamelet
                 apiVersion: camel.apache.org/v1alpha1
                 name: aws-translate-action
         -
             ref:
                 kind: Kamelet
                 apiVersion: camel.apache.org/v1alpha1
                 name: avro-deserialize-action
     sink:
         ref:
             kind: Kamelet
             apiVersion: camel.apache.org/v1alpha1
             name: kafka-sink
        properties:
             brokers: "The Brokers"
             password: "The Password"
             topic: "The Topic Names"
             username: "The Username"
 ```
 */
public class KameletBindingSpec {
    private KameletBindingStep source;
    private List<KameletBindingStep> steps = new ArrayList<>();
    private KameletBindingStep sink;

    public KameletBindingStep getSource() {
        return source;
    }

    public void setSource(KameletBindingStep source) {
        this.source = source;
    }

    public List<KameletBindingStep> getSteps() {
        return steps;
    }

    public KameletBindingStep getSink() {
        return sink;
    }

    public void setSink(KameletBindingStep sink) {
        this.sink = sink;
    }

    public void setSteps(List<KameletBindingStep> steps) {
        this.steps = steps;
    }
}
