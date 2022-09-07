package io.kaoto.backend.model.deployment.kamelet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Kind;
import io.fabric8.kubernetes.model.annotation.Version;
import io.kaoto.backend.KamelPopulator;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import java.io.Serial;
import java.io.Serializable;
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


@JsonInclude(JsonInclude.Include.NON_NULL)
@Group("camel.apache.org")
@Version("v1alpha1")
@Kind("Kamelet")
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Kamelet
        extends CustomResource<KameletSpec, KameletBindingStatus>
        implements io.fabric8.kubernetes.api.model.Namespaced, Serializable {

    @Serial
    private static final long serialVersionUID = -6210736155226440906L;

    public Kamelet() {
    }

    public Kamelet(final List<Step> steps,
                   final Map<String, Object> metadata,
                   final List<Parameter> parameters) {
        this();
        new KamelPopulator().populateKamelet(this, metadata, steps, parameters);
    }


}
