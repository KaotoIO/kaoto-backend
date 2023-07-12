package io.kaoto.backend.camel.model.deployment.kamelet;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.fabric8.kubernetes.api.model.Namespaced;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Plural;
import io.fabric8.kubernetes.model.annotation.Singular;
import io.fabric8.kubernetes.model.annotation.Version;
import org.apache.camel.v1alpha1.KameletStatus;


/**
 * 🐱class SimplifiedKamelet
 *
 * Used to read kamelet properties, but not digging into the implementation of the kamelet.
 *
 */

@Version(
        value = "v1alpha1",
        storage = true,
        served = true
)
@Group("camel.apache.org")
@Singular("kamelet")
@Plural("kamelets")
public final class SimplifiedKamelet
        extends CustomResource<SimplifiedKameletSpec, KameletStatus> implements Namespaced {

    @JsonProperty("spec")
    protected SimplifiedKameletSpec spec;

    public SimplifiedKamelet() {
        //Needed for serialization
    }

    public SimplifiedKameletSpec getSpec() {
        return spec;
    }

    public void setSpec(final SimplifiedKameletSpec spec) {
        this.spec = spec;
    }

}
