package io.kaoto.backend.model.deployment.kamelet;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kaoto.backend.model.deployment.kamelet.step.From;
import io.kaoto.backend.model.deployment.rest.Rest;
import org.apache.camel.v1.integrationspec.Flows;


/**
 * ```
 *   flow:
 *     from:
 *       uri: timer:tick
 *       parameters:
 *         period: "{{period}}"
 *       steps:
 *         - set-body:
 *             constant: "{{message}}"
 *         - to: kamelet:sink
 * ```
 */

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
