package io.kaoto.backend.camel.model.deployment.kamelet;

import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Plural;
import io.fabric8.kubernetes.model.annotation.Singular;
import io.fabric8.kubernetes.model.annotation.Version;
import org.apache.camel.v1alpha1.KameletBindingSpec;
import org.apache.camel.v1alpha1.kameletbindingspec.Sink;
import org.apache.camel.v1alpha1.kameletbindingspec.Source;
import org.apache.camel.v1alpha1.kameletbindingspec.Steps;

import java.util.Objects;


/**
 * 🐱class KameletBinding
 * Represents a Kamelet binding that can be deployed
 * <p>
 * <p>
 * ```
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
 * ```
 */


@Version(value = "v1alpha1")
@Group("camel.apache.org")
@Singular("kameletbinding")
@Plural("kameletbindings")
public final class KameletBinding extends org.apache.camel.v1alpha1.KameletBinding {
    private static final long serialVersionUID = -1089453226037028488L;

    public KameletBinding() {
        this.setSpec(new KameletBindingSpec());
    }

    public KameletBinding(final KameletBindingSpec spec, final ObjectMeta metadata) {
        this();
        setSpec(spec);
        if (spec == null) {
            setSpec(new KameletBindingSpec());
        }
        setMetadata(metadata);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof KameletBinding)) {
            return false;
        }
        KameletBinding that = (KameletBinding) o;
        if (!this.getMetadata().equals(that.getMetadata())) {
            return false;
        }

        if (this.getSpec() != null && !equalsToSpec(that)
                || this.getSpec() == null && that.getSpec() != null) {
            return false;
        }

        if (!Objects.equals(this.getStatus(), that.getStatus())) {
            return false;
        }

        return Objects.equals(this.getFinalizers(), that.getFinalizers());
    }

    private boolean equalsToSpec(KameletBinding that) {
        final var thisSpec = this.getSpec();
        final var thatSpec = that.getSpec();
        if (!Objects.equals(thisSpec.getIntegration(), thatSpec.getIntegration())
                || !equalsToSink(thisSpec.getSink(), thatSpec.getSink())
                || !equalsToSource(thisSpec.getSource(), thatSpec.getSource())
                || !Objects.equals(thisSpec.getReplicas(), thatSpec.getReplicas())
                || !Objects.equals(thisSpec.getErrorHandler(), thatSpec.getErrorHandler())) {
            return false;
        }

        if (!Objects.equals(thisSpec.getSteps(), thatSpec.getSteps())) {
            if (thisSpec.getSteps() == null || thatSpec.getSteps() == null
                    || thisSpec.getSteps().size() != thatSpec.getSteps().size()) {
                return false;
            }
            for (int i = 0; i < thisSpec.getSteps().size(); i++) {
                if (!equalsToStep(thisSpec.getSteps().get(i), thatSpec.getSteps().get(i))) {
                    return false;
                }
            }
        }

        return Objects.equals(thisSpec.getServiceAccountName(), thatSpec.getServiceAccountName());
    }

    @SuppressWarnings("CPD-START")
    //Repetition here because the Sink, Source, and Steps classes era equivalent but Java doesn't know about that.
    private boolean equalsToSink(Sink thisSink, Sink thatSink) {
        if (thisSink != null) {
            if (!Objects.equals(thisSink.getProperties(), thatSink.getProperties())
                    //Objects.equals should have checked both null already
                    && (thisSink.getProperties() == null
                    || thatSink.getProperties() == null
                    || !Objects.equals(thisSink.getProperties().getAdditionalProperties(),
                    thatSink.getProperties().getAdditionalProperties()))) {
                return false;

            }

            if (!Objects.equals(thisSink.getRef(), thatSink.getRef())) {
                var thisRef = thisSink.getRef();
                var thatRef = thatSink.getRef();
                if (thisRef == null || thatRef == null
                    //Objects.equals should have checked both null already
                    || !Objects.equals(thisRef.getKind(), thatRef.getKind())
                        || !Objects.equals(thisRef.getName(), thatRef.getName())
                        || !Objects.equals(thisRef.getApiVersion(), thatRef.getApiVersion())
                        || !Objects.equals(thisRef.getUid(), thatRef.getUid())
                        || !Objects.equals(thisRef.getNamespace(), thatRef.getNamespace())
                        || !Objects.equals(thisRef.getFieldPath(), thatRef.getFieldPath())
                        || !Objects.equals(thisRef.getResourceVersion(), thatRef.getResourceVersion())) {
                    return false;
                }
            }

            return Objects.equals(thisSink.getUri(), thatSink.getUri()) &&
                    Objects.equals(thisSink.getTypes(), thatSink.getTypes());
        }

        return thatSink == null;

    }

    private boolean equalsToSource(Source thisSource, Source thatSource) {
        if (thisSource != null) {
            if (!Objects.equals(thisSource.getProperties(), thatSource.getProperties())
                    //Objects.equals should have checked both null already
                    && (thisSource.getProperties() == null || thatSource.getProperties() == null
                    || !Objects.equals(thisSource.getProperties().getAdditionalProperties(),
                    thatSource.getProperties().getAdditionalProperties()))) {
                return false;
            }

            if (!Objects.equals(thisSource.getRef(), thatSource.getRef())) {
                var thisRef = thisSource.getRef();
                var thatRef = thatSource.getRef();
                if (thisRef == null || thatRef == null
                    //Objects.equals should have checked both null already
                    || !Objects.equals(thisRef.getKind(), thatRef.getKind())
                        || !Objects.equals(thisRef.getName(), thatRef.getName())
                        || !Objects.equals(thisRef.getApiVersion(), thatRef.getApiVersion())
                        || !Objects.equals(thisRef.getUid(), thatRef.getUid())
                        || !Objects.equals(thisRef.getNamespace(), thatRef.getNamespace())
                        || !Objects.equals(thisRef.getFieldPath(), thatRef.getFieldPath())
                        || !Objects.equals(thisRef.getResourceVersion(), thatRef.getResourceVersion())) {
                    return false;
                }
            }

            return Objects.equals(thisSource.getUri(), thatSource.getUri()) && Objects.equals(thisSource.getTypes(),
                    thatSource.getTypes());

        }

        return thatSource == null;

    }

    private boolean equalsToStep(Steps thisSteps, Steps thatSteps) {
        if (thisSteps != null) {
            if (!Objects.equals(thisSteps.getProperties(), thatSteps.getProperties())
                    //Objects.equals should have checked both null already
                    && (thisSteps.getProperties() == null || thatSteps.getProperties() == null
                    || !Objects.equals(thisSteps.getProperties().getAdditionalProperties(),
                    thatSteps.getProperties().getAdditionalProperties()))) {
                return false;
            }
            if (!Objects.equals(thisSteps.getRef(), thatSteps.getRef())) {
                var thisRef = thisSteps.getRef();
                var thatRef = thatSteps.getRef();
                if (thisRef == null || thatRef == null
                    //Objects.equals should have checked both null already
                    || !Objects.equals(thisRef.getKind(), thatRef.getKind())
                        || !Objects.equals(thisRef.getName(), thatRef.getName())
                        || !Objects.equals(thisRef.getApiVersion(), thatRef.getApiVersion())
                        || !Objects.equals(thisRef.getUid(), thatRef.getUid())
                        || !Objects.equals(thisRef.getNamespace(), thatRef.getNamespace())
                        || !Objects.equals(thisRef.getFieldPath(), thatRef.getFieldPath())
                        || !Objects.equals(thisRef.getResourceVersion(), thatRef.getResourceVersion())) {
                    return false;
                }
            }

            return Objects.equals(thisSteps.getUri(), thatSteps.getUri())
                    && Objects.equals(thisSteps.getTypes(), thatSteps.getTypes());

        }
        return thatSteps == null;

    }

    @Override
    public int hashCode() {
        int result = this.getMetadata().hashCode();
        result = 31 * result + (this.spec != null ? this.spec.hashCode() : 0);
        result = 31 * result + (this.status != null ? this.status.hashCode() : 0);
        result = 31 * result + this.getCRDName().hashCode();
        result = 31 * result + this.getKind().hashCode();
        result = 31 * result + this.getApiVersion().hashCode();
        result = 31 * result + this.getScope().hashCode();
        result = 31 * result + this.getPlural().hashCode();
        return result;
    }
}
