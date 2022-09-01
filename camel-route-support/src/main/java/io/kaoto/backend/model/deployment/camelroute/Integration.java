package io.kaoto.backend.model.deployment.camelroute;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Kind;
import io.fabric8.kubernetes.model.annotation.Version;
import io.kaoto.backend.KamelPopulator;
import io.kaoto.backend.model.deployment.kamelet.Flow;
import io.kaoto.backend.model.deployment.kamelet.KameletBindingStatus;
import io.kaoto.backend.model.step.Step;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * 🐱class CamelRoute
 *
 * Represents an Integration definition that can be deployed.
 *
 * This generates a flow based on how Camel-K defines YAML routes as
 * described in https://camel.apache.org/components/latest/others/yaml-dsl.html
 * This implementation models the steps as "Loading Camel K Integrations".
 *
 */


@JsonInclude(JsonInclude.Include.NON_NULL)
@Group("camel.apache.org")
@Version("v1")
@Kind("Integration")
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Integration
        extends CustomResource<IntegrationSpec, KameletBindingStatus>
        implements io.fabric8.kubernetes.api.model.Namespaced, Serializable {
    @Serial
    private static final long serialVersionUID = -6210923845780906L;

    public Integration() {

    }

    public Integration(final List<Step> steps,
                       final Map<String, Object> metadata) {

        this.setMetadata(new ObjectMeta());
        this.getMetadata().setName(
                metadata.getOrDefault("name", "").toString());

        this.getMetadata().setAdditionalProperties(
                (Map<String, Object>) metadata.getOrDefault(
                "additionalProperties", Collections.emptyMap()));

        this.getMetadata().setAdditionalProperties(
                (Map<String, Object>) metadata.getOrDefault(
                        "annotations", Collections.emptyMap()));

        this.getMetadata().setLabels(
                (Map<String, String>) metadata.getOrDefault(
                        "labels", Collections.emptyMap()));

        this.setSpec(new IntegrationSpec());
        this.getSpec().setFlows(new ArrayList<>());
        var flow = new Flow();
        flow.setFrom(new KamelPopulator().getFlow(steps));
        this.getSpec().getFlows().add(flow);
    }


}
