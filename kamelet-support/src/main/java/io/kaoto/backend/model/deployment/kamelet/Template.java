package io.kaoto.backend.model.deployment.kamelet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.model.deployment.kamelet.step.From;

import java.io.Serializable;


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
@JsonPropertyOrder({"from"})
@JsonDeserialize(
        using = JsonDeserializer.None.class
)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Template implements Serializable {
    private static final long serialVersionUID = -4601560033032557024L;

    @JsonProperty("from")
    private From from;

    public From getFrom() {
        return from;
    }

    public void setFrom(final From from) {
        this.from = from;
    }
}
