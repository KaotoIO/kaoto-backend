package io.kaoto.backend.model.deployment.camelroute;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.model.deployment.kamelet.Flow;
import org.apache.camel.v1.integrationspec.Flows;

import java.util.LinkedList;
import java.util.List;

@JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"configuration","dependencies","flows","integrationKit","profile","replicas","repositories",
        "serviceAccountName","sources","template","traits"})
@JsonDeserialize(using = JsonDeserializer.None.class)
public class IntegrationSpec extends org.apache.camel.v1.IntegrationSpec {
    /**
     * a source in YAML DSL language which contain the routes to run
     */
    @JsonProperty("flows")
    @JsonPropertyDescription("a source in YAML DSL language which contain the routes to run")
    @JsonSetter(nulls = Nulls.SKIP)
    private List<Flow> flows = new LinkedList<>();

    public List<Flow> get_flows() {
        return flows;
    }

    public void set_flows(List<Flow> flows) {
        this.flows = flows;
    }

    @Override
    @Deprecated
    @JsonIgnore
    public List<Flows> getFlows() {
        return super.getFlows();
    }
    @Override
    @Deprecated
    @JsonIgnore
    public void setFlows(final List<Flows> flows) {
        //do nothing
    }
}
