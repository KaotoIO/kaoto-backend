package io.kaoto.backend.model.deployment.kamelet.step.marshal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import io.kaoto.backend.model.deployment.kamelet.step.MarshalFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.UnmarshalFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.dataformat.DataFormat;
import org.jboss.logging.Logger;

import java.util.HashMap;

public class MarshalDeserializer extends JsonDeserializer {
    private final Logger log = Logger.getLogger(MarshalDeserializer.class);
    @Override
    public Object deserialize(final JsonParser jsonParser,
                              final DeserializationContext ctxt) {
        var step = new MarshalFlowStep();

        try {
            JsonNode n = jsonParser.getCodec().readTree(jsonParser);
            var marshal = n.get("marshal");
            if (marshal == null) {
                marshal = n.get("unmarshal");
                step = new UnmarshalFlowStep();
            }

            final var fields = marshal.fields();
            if (fields.hasNext()) {
                DataFormat dataFormat = new DataFormat();
                step.setDataFormat(dataFormat);

                var field = fields.next();
                dataFormat.setFormat(field.getKey());
                dataFormat.setProperties(new HashMap<>());
                field.getValue().fields().forEachRemaining(e -> {
                    dataFormat.getProperties().put(
                            e.getKey(),
                            e.getValue().asText());
                });

            }
            if (fields.hasNext()) {
                log.error("Found a second data format on this marshal? "
                        + n.toPrettyString());
            }

        } catch (Exception e) {
            log.debug("Error trying to deserialize step: " + e.getMessage());
        }

        return step;
    }
}
