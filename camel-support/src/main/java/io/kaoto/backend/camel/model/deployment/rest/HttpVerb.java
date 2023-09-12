package io.kaoto.backend.camel.model.deployment.rest;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kaoto.backend.camel.KamelHelper;
import io.kaoto.backend.camel.model.deployment.kamelet.FlowStep;

@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonSerialize(using = HttpVerbSerializer.class)
public class HttpVerb implements Serializable {
    private static final long serialVersionUID = 481836716282364509L;

    @JsonProperty("consumes")
    private String consumes;

    @JsonProperty("produces")
    private String produces;

    @JsonProperty("id")
    private String id;

    @JsonProperty("path")
    private String path;
    @JsonProperty(KamelHelper.DESCRIPTION)
    private String description;

    @JsonProperty("param")
    private List<RestParameter> parameterList;

    @JsonProperty("to")
    private FlowStep to;

    public HttpVerb() {
        //Need for serialization
    }

    public String getConsumes() {
        return consumes;
    }

    public void setConsumes(String consumes) {
        this.consumes = consumes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<RestParameter> getParameterList() {
        return parameterList;
    }

    public void setParameterList(List<RestParameter> parameterList) {
        this.parameterList = parameterList;
    }

    public FlowStep getTo() {
        return to;
    }

    public void setTo(FlowStep to) {
        this.to = to;
    }

    public String getProduces() {
        return produces;
    }

    public void setProduces(String produces) {
        this.produces = produces;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
