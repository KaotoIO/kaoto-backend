package io.kaoto.backend.camel.model.deployment.kamelet.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.kaoto.backend.camel.KamelHelper;
import io.kaoto.backend.camel.model.deployment.kamelet.KameletSpec;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class KameletSpecSerializer extends StdSerializer<KameletSpec> {

    public KameletSpecSerializer() {
        super(KameletSpec.class);
    }

    @Override
    public void serialize(KameletSpec data, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        Map<String, Object> properties = new LinkedHashMap<>();
        properties.putAll(
                KamelHelper.YAML_MAPPER.convertValue(data, new TypeReference<Map<String, Object>>() {
                }));
        properties.put("template", data.getTemplate());
        jsonGenerator.writeObject(properties);
    }
}
