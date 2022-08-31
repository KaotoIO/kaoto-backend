package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;

import java.io.Serial;

@JsonPropertyOrder({"filter"})
@JsonDeserialize(
        using = JsonDeserializer.None.class
)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilterFlowStep implements FlowStep {
    @Serial
    private static final long serialVersionUID = -3320625752519808958L;

    @JsonCreator
    public FilterFlowStep(
           final @JsonProperty(value = "filter") Filter filter) {
        super();
        setFilter(filter);
    }

    @JsonProperty("filter")
    private Filter filter;

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(
            final Filter filter) {
        this.filter = filter;
    }


}
