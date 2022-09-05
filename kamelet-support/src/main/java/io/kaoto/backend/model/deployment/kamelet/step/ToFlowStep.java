package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;

import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder({"to"})
@JsonDeserialize(
        using = JsonDeserializer.None.class
)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ToFlowStep implements FlowStep {
    @Serial
    private static final long serialVersionUID = 6334914135393038266L;

    @JsonCreator
    public ToFlowStep(
           final @JsonProperty(value = "to", required = true) FlowStep to) {
        super();
        setTo(to);
    }

    @JsonProperty("to")
    private FlowStep to;

    public FlowStep getTo() {
        return to;
    }

    public void setTo(final FlowStep to) {
        this.to = to;
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("to", this.getTo());
        return properties;
    }
}
