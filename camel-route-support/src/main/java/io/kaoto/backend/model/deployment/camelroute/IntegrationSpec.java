package io.kaoto.backend.model.deployment.camelroute;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import io.kaoto.backend.model.deployment.kamelet.Flow;

import java.io.Serializable;
import java.util.List;

/**
 * 🐱miniclass IntegrationSpec (Integration)
 */

@JsonPropertyOrder({"flows"})
@JsonDeserialize(
        using = JsonDeserializer.None.class
)
@JsonIgnoreProperties(ignoreUnknown = true)
public class IntegrationSpec implements KubernetesResource, Serializable {

    /*
     * 🐱property flows: Flow[]
     *
     * List of flows this route supports.
     */
    @JsonProperty("flows")
    private List<Flow> flows;

    public List<Flow> getFlows() {
        return flows;
    }

    public void setFlows(
            final List<Flow> flows) {
        this.flows = flows;
    }
}
