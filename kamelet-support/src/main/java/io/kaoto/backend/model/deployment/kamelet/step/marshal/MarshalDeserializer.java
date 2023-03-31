package io.kaoto.backend.model.deployment.kamelet.step.marshal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.NumericNode;
import com.fasterxml.jackson.databind.node.ValueNode;
import io.kaoto.backend.model.deployment.kamelet.step.MarshalFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.UnmarshalFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.dataformat.DataFormat;
import org.jboss.logging.Logger;

import java.util.LinkedHashMap;
import java.util.Map;

public class MarshalDeserializer extends JsonDeserializer {
    private final Logger log = Logger.getLogger(MarshalDeserializer.class);

    private static void extractProperties(final Map<String, Object> properties,
                                          final Map.Entry<String, JsonNode> field) {
        if (field.getValue() instanceof ValueNode v) {
            extractScalarValue(properties, field, v);
        } else {
            field.getValue().fields().forEachRemaining(e -> {
                if (e.getValue() instanceof ValueNode v) {
                    extractScalarValue(properties, e, v);
                } else {
                    Map<String, Object> innerProperties = new LinkedHashMap<>();
                    JsonNode node = e.getValue();
                    node.fields().forEachRemaining(
                            stringJsonNodeEntry -> extractProperties(innerProperties, stringJsonNodeEntry));
                    properties.put(e.getKey(), innerProperties);
                }
            });
        }
    }

    private static void extractScalarValue(final Map<String, Object> properties,
                                           final Map.Entry<String, JsonNode> field,
                                           final ValueNode v) {
        if (v instanceof NumericNode numericNode) {
            properties.put(field.getKey(), numericNode.numberValue());
        } else if (v instanceof BooleanNode booleanNode) {
            properties.put(field.getKey(), booleanNode.booleanValue());
        } else {
            properties.put(field.getKey(), v.asText());
        }
    }

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
                dataFormat.setProperties(new LinkedHashMap<>());
                extractProperties(dataFormat.getProperties(), field);
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
