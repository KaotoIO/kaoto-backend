package io.kaoto.backend.camel.model.deployment.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.camel.model.deployment.kamelet.FlowStep;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class HttpVerb implements Serializable {
    @Serial
    private static final long serialVersionUID = 481836716282364509L;

    @JsonProperty("consumes")
    private String consumes;

    @JsonProperty("produces")
    private String produces;

    @JsonProperty("id")
    private String id;

    @JsonProperty("uri")
    private String uri;
    @JsonProperty("description")
    private String description;

    @JsonProperty("param")
    private List<RestParameter> parameterList;

    @JsonProperty("to")
    private FlowStep to;

    @JsonCreator
    public HttpVerb(final @JsonProperty("consumes") String consumes,
                         final @JsonProperty("produces") String produces,
                         final @JsonProperty("id") String id,
                         final @JsonProperty("uri") String uri,
                         final @JsonProperty("description") String description,
                         final @JsonProperty("param") List<RestParameter> parameterList,
                         final @JsonProperty("to") FlowStep to) {
        this();
        setConsumes(consumes);
        setProduces(produces);
        setId(id);
        setUri(uri);
        setParameterList(parameterList);
        setTo(to);
        setDescription(description);
    }

    public HttpVerb() {

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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
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

    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> props = new LinkedHashMap<>();
        if (this.getConsumes() != null) {
            props.put("consumes", this.getConsumes());
        }
        if (this.getId() != null) {
            props.put("id", this.getId());
        }
        if (this.getProduces() != null) {
            props.put("produces", this.getProduces());
        }
        if (this.getUri() != null) {
            props.put("uri", this.getUri());
        }
        if (this.getDescription() != null) {
            props.put("description", this.getDescription());
        }
        if (getParameterList() != null && !getParameterList().isEmpty()) {
            var parameters = new ArrayList<>(getParameterList().size());
            for (RestParameter p : this.getParameterList()) {
                parameters.add(p.getRepresenterProperties());
            }
            props.put("param", parameters);
        }
        if (this.getTo() != null) {
            props.put("to", this.getTo().getRepresenterProperties().get("to"));
        }
        return props;
    }
}
