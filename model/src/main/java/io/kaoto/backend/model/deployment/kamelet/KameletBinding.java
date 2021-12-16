package io.kaoto.backend.model.deployment.kamelet;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Version;


/**
 * 🐱class KameletBinding
 * Represents a Kamelet binding that can be deployed
 *
 *
 ```
 apiVersion: camel.apache.org/v1alpha1
 kind: KameletBinding
 metadata:
    name: twitter-search-source-binding
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


@JsonInclude(JsonInclude.Include.NON_NULL)
@Group("camel.apache.org")
@Version("camel.apache.org/v1alpha1")
public final class KameletBinding
        extends CustomResource<KameletBindingSpec, KameletBindingStatus> {
    private String apiVersion = "camel.apache.org/v1alpha1";
    private String kind = "KameletBinding";
    private KameletBindingSpec spec;

    public KameletBinding() {
    }

    public KameletBinding(final String name, final KameletBindingSpec spec) {
        this();
        setSpec(spec);
        getMetadata().setName(name);
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public String getKind() {
        return kind;
    }

    public KameletBindingSpec getSpec() {
        return spec;
    }

    public void setSpec(final KameletBindingSpec spec) {
        this.spec = spec;
    }

    public void setApiVersion(final String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public void setKind(final String kind) {
        this.kind = kind;
    }

}
