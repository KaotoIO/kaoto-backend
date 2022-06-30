package io.kaoto.backend.model.deployment.kamelet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

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
@JsonPropertyOrder({"from", "steps"})
@JsonDeserialize(
        using = JsonDeserializer.None.class
)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Flow implements Serializable {
    private static final long serialVersionUID = -4601560033032557024L;

    @JsonProperty("from")
    private FlowStep from;

    @JsonProperty("steps")
    private List<FlowStep> steps;

    public FlowStep getFrom() {
        return from;
    }

    public void setFrom(final FlowStep from) {
        this.from = from;
    }

    public List<FlowStep> getSteps() {
        return steps;
    }

    public void setSteps(final List<FlowStep> steps) {
        this.steps = steps;
    }
}
