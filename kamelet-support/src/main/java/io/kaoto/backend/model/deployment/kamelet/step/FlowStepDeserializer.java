package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jboss.logging.Logger;

import java.util.HashMap;
import java.util.Map;

public class FlowStepDeserializer extends JsonDeserializer {
    private final Logger log = Logger.getLogger(FlowStepDeserializer.class);

    @Override
    public Object deserialize(
            final JsonParser jsonParser,
            final DeserializationContext deserializationContext) {
        try {
            JsonNode n = jsonParser.getCodec().readTree(jsonParser);

            for (var step : getFlowSteps().entrySet()) {
                if (n.get(step.getKey()) != null) {
                    return new ObjectMapper().readValue(n.toPrettyString(), step.getValue());
                }
            }

        } catch (Exception e) {
            log.error("Error trying to deserialize step: " + e.getMessage());
        }

        return new UriFlowStep();
    }

    private Map<String, Class> getFlowSteps() {
        Map<String, Class> steps = new HashMap<>();

        steps.put("aggregate", AggregateFlowStep.class);
        steps.put("circuit-breaker", CircuitBreakerFlowStep.class);
        steps.put("choice", ChoiceFlowStep.class);
        steps.put("filter", FilterFlowStep.class);
        steps.put("from", From.class);
        steps.put("marshal", MarshalFlowStep.class);
        steps.put("log", LogFlowStep.class);
        steps.put("remove-header", RemoveHeaderFlowStep.class);
        steps.put("remove-property", RemovePropertyFlowStep.class);
        steps.put("set-body", SetBodyFlowStep.class);
        steps.put("set-header", SetHeaderFlowStep.class);
        steps.put("set-property", SetPropertyFlowStep.class);
        steps.put("to", ToFlowStep.class);
        steps.put("transform", TransformFlowStep.class);
        steps.put("unmarshal", UnmarshalFlowStep.class);
        steps.put("uri", UriFlowStep.class);

        return steps;
    }
}
