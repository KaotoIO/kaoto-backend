package io.kaoto.backend.camel.model.deployment.kamelet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kaoto.backend.camel.KamelHelper;
import io.kaoto.backend.camel.model.deployment.kamelet.serializer.KameletTemplateSerializer;
import io.kaoto.backend.camel.model.deployment.kamelet.step.From;
import io.quarkus.runtime.annotations.RegisterForReflection;
import org.apache.camel.v1alpha1.kameletspec.Template;

import java.util.List;

@JsonPropertyOrder({"beans", "from"})
@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonSerialize(using = KameletTemplateSerializer.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@RegisterForReflection
public class KameletTemplate extends Template {
    private static final long serialVersionUID = -4601560033032557024L;

    @JsonProperty("beans")
    private List<Bean> beans;
    @JsonProperty("from")
    private From from;
    @JsonProperty("route")
    private Flow route;
    @JsonProperty("id")
    private String id;

    @JsonProperty(KamelHelper.DESCRIPTION)
    private String description;

    public KameletTemplate() {

    }

    public From getFrom() {
        return from;
    }

    public void setFrom(final From from) {
        this.from = from;
    }

    public List<Bean> getBeans() {
        return beans;
    }

    public void setBeans(
            final List<Bean> beans) {
        this.beans = beans;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Flow getRoute() {
        return route;
    }

    public void setRoute(Flow route) {
        this.route = route;
    }
}
