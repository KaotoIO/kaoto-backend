package io.kaoto.backend.camel.model.deployment.kamelet;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kaoto.backend.camel.model.deployment.kamelet.serializer.FlowSerializer;
import io.kaoto.backend.camel.model.deployment.kamelet.step.From;
import io.kaoto.backend.camel.model.deployment.rest.Rest;
import io.kaoto.backend.camel.service.step.parser.camelroute.FlowDeserializer;
import org.apache.camel.v1.integrationspec.Flows;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonSerialize(using = FlowSerializer.class)
@JsonDeserialize(using = FlowDeserializer.class)
public class Flow extends Flows {
    private static final long serialVersionUID = -4601560033032557024L;

    @JsonProperty("id")
    private String id;

    @JsonProperty("route-configuration-id")
    private String routeConfigurationId;

    @JsonProperty("description")
    private String description;

    @JsonProperty("from")
    private From from;

    @JsonProperty("rest")
    private Rest rest;

    @JsonProperty("beans")
    private List<Bean> beans;

    public From getFrom() {
        return from;
    }

    public void setFrom(final From from) {
        this.from = from;
    }

    public Rest getRest() {
        return rest;
    }

    public void setRest(Rest rest) {
        this.rest = rest;
    }

    public List<Bean> getBeans() {
        return beans;
    }

    public void setBeans(List<Bean> beans) {
        this.beans = beans;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRouteConfigurationId() {
        return routeConfigurationId;
    }

    public void setRouteConfigurationId(String routeConfigurationId) {
        this.routeConfigurationId = routeConfigurationId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
