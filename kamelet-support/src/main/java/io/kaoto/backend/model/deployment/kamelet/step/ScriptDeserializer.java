package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jboss.logging.Logger;

import java.util.HashMap;
import java.util.Map;

public class ScriptDeserializer extends JsonDeserializer {
    private final Logger log = Logger.getLogger(ScriptDeserializer.class);

    @Override
    public Object deserialize(final JsonParser jsonParser,
                              final DeserializationContext ctxt) {
        var step = new ScriptFlowStep();
        step.setProperties(new HashMap<>());

        try {
            JsonNode n = jsonParser.getCodec().readTree(jsonParser);
            var jsonNode = n.get("script");

            final var fields = jsonNode.fields();
            while (fields.hasNext()) {
                var field = fields.next();
                step.getProperties().put(field.getKey(),
                        new ObjectMapper().readValue(field.getValue().toPrettyString(), Map.class));

            }
        } catch (Exception e) {
            log.error("Error trying to deserialize step: " + e.getMessage());
        }

        return step;
    }
}
