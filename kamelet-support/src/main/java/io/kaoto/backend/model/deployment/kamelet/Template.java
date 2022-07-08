package io.kaoto.backend.model.deployment.kamelet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.model.deployment.kamelet.step.From;

import java.io.Serializable;
import java.util.List;


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
@JsonPropertyOrder({"beans", "from"})
@JsonDeserialize(
        using = JsonDeserializer.None.class
)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Template implements Serializable {
    private static final long serialVersionUID = -4601560033032557024L;

    @JsonProperty("beans")
    private List<Bean> beans;
    @JsonProperty("from")
    private From from;

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
}
