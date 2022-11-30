package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.model.step.Step;

import java.io.Serial;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ToFlowStep implements FlowStep {

    @JsonCreator
    public ToFlowStep(final @JsonProperty(value = "to") Object to) {
        super();
        if (to instanceof FlowStep flowStep) {
            setTo(flowStep);
        } else if (to instanceof String sto) {
            UriFlowStep uri = new UriFlowStep();
            uri.setUri(sto);
            setTo(uri);
        } else if (to instanceof Map map) {
            UriFlowStep uri = new UriFlowStep();
            uri.setUri(map.getOrDefault("uri", "").toString());
            var parameters = (Map<String, String>) map.getOrDefault("parameters", Collections.emptyMap());
            if (parameters != null && !parameters.isEmpty()) {
                uri.setParameters(new LinkedHashMap<>());
                uri.getParameters().putAll(parameters);
            }
            setTo(uri);
        }
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

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final Boolean start, final Boolean end) {
        return kameletStepParserService.processStep(this.getTo(), start, end);
    }
}
