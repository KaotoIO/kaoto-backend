package io.kaoto.backend.camel.model.deployment.kamelet.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.kaoto.backend.camel.model.deployment.kamelet.KameletTemplate;

import java.io.IOException;
import java.util.LinkedHashMap;

public class KameletTemplateSerializer extends StdSerializer<KameletTemplate> {

    public KameletTemplateSerializer() {
        super(KameletTemplate.class);
    }

    @Override
    public void serialize(KameletTemplate template, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {

        var properties = new LinkedHashMap<String, Object>();

        if (template.getId() != null) {
            properties.put("id", template.getId());
        }
        if (template.getDescription() != null) {
            properties.put("description", template.getDescription());
        }
        if (template.getBeans() != null) {
            properties.put("beans", template.getBeans());
        }
        if (template.getRoute() == null) {
            properties.put("from", template.getFrom());
        } else {
            FlowSerializer.getRouteProperties(template.getRoute(), properties);
        }
        jsonGenerator.writeObject(properties);
    }
}
