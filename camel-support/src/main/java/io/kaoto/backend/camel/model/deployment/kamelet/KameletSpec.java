package io.kaoto.backend.camel.model.deployment.kamelet;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.quarkus.runtime.annotations.RegisterForReflection;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"definition", "dependencies", "sources", "template", "types"})
@JsonDeserialize(using = JsonDeserializer.None.class)
@RegisterForReflection
public class KameletSpec extends org.apache.camel.v1alpha1.KameletSpec {

    @JsonProperty("template")
    @JsonPropertyDescription("the main source in YAML DSL")
    @JsonSetter(nulls = com.fasterxml.jackson.annotation.Nulls.SKIP)
    private KameletTemplate template;

    @Override
    public KameletTemplate getTemplate() {
        return template;
    }

    public void setTemplate(KameletTemplate template) {
        this.template = template;
    }
}
