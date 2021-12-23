package io.kaoto.backend.model.deployment.kamelet;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Kind;
import io.fabric8.kubernetes.model.annotation.Plural;
import io.fabric8.kubernetes.model.annotation.ShortNames;
import io.fabric8.kubernetes.model.annotation.Version;

import java.io.Serializable;


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
@Version("v1alpha1")
@Kind("KameletBinding")
@Plural("kameletbindings")
@ShortNames("klb")
public final class KameletBinding
        extends CustomResource<KameletBindingSpec, KameletBindingStatus>
        implements  io.fabric8.kubernetes.api.model.Namespaced , Serializable {
    private static final long serialVersionUID = -1089453226037028488L;

    public KameletBinding() {
    }

    public KameletBinding(final String name, final KameletBindingSpec spec) {
        this();
        setSpec(spec);
        if (getMetadata() == null) {
           setMetadata(new ObjectMeta());
        }
        getMetadata().setName(name);
    }
}
