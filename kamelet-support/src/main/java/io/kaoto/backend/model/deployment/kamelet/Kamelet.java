package io.kaoto.backend.model.deployment.kamelet;

import io.fabric8.kubernetes.api.model.Namespaced;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Plural;
import io.fabric8.kubernetes.model.annotation.Singular;
import io.fabric8.kubernetes.model.annotation.Version;
import io.kaoto.backend.KamelPopulator;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;
import io.quarkus.runtime.annotations.RegisterForReflection;
import org.apache.camel.v1alpha1.KameletStatus;

import java.io.Serial;
import java.util.List;
import java.util.Map;


/**
 * 🐱class Kamelet
 * Represents a Kamelet definition that can be deployed.
 *
 * This generates a flow based on how Camel-K defines YAML routes as
 * described in https://camel.apache.org/camel-k/1.6.x/languages/yaml.html
 * This implementation models the steps as "Using URI and parameters".
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
@RegisterForReflection
public final class Kamelet extends CustomResource<KameletSpec, KameletStatus> implements Namespaced {

    @Serial
    private static final long serialVersionUID = -6210736155226440906L;

    public Kamelet() {
    }

    public Kamelet(final List<Step> steps,
                   final Map<String, Object> metadata,
                   final List<Parameter> parameters,
                   final StepCatalog catalog) {
        this();
        new KamelPopulator(catalog).populateKamelet(this, metadata != null ? metadata : Map.of(),
                steps != null ? steps : List.of(),
                parameters != null ? parameters : List.of());
    }


}
