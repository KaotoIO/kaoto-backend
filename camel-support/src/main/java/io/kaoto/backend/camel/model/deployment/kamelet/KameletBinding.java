package io.kaoto.backend.camel.model.deployment.kamelet;

import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Plural;
import io.fabric8.kubernetes.model.annotation.Singular;
import io.fabric8.kubernetes.model.annotation.Version;
import io.quarkus.runtime.annotations.RegisterForReflection;
import org.apache.camel.v1alpha1.KameletBindingSpec;


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


@Version(
        value = "v1alpha1",
        storage = true,
        served = true
)
@Group("camel.apache.org")
@Singular("kameletbinding")
@Plural("kameletbindings")
@RegisterForReflection
public final class KameletBinding extends org.apache.camel.v1alpha1.KameletBinding {
    private static final long serialVersionUID = -1089453226037028488L;

    public KameletBinding() {
    }

    public KameletBinding(final KameletBindingSpec spec, final ObjectMeta metadata) {
        this();
        setSpec(spec);
        setMetadata(metadata);
    }


}
