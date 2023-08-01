package io.kaoto.backend.camel.model.deployment.rest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.kaoto.backend.camel.KamelHelper;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class HttpVerbSerializer extends StdSerializer<HttpVerb> {

    public HttpVerbSerializer() {
        super(HttpVerb.class);
    }

    @Override
    public void serialize(HttpVerb data, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {

        Map<String, Object> props = new LinkedHashMap<>();
        if (data.getConsumes() != null) {
            props.put("consumes", data.getConsumes());
        }
        if (data.getProduces() != null) {
            props.put("produces", data.getProduces());
        }
        if (data.getId() != null) {
            props.put("id", data.getId());
        }
        if (data.getUri() != null) {
            props.put("uri", data.getUri());
        }
        if (data.getDescription() != null) {
            props.put(KamelHelper.DESCRIPTION, data.getDescription());
        }
        if (data.getParameterList() != null && !data.getParameterList().isEmpty()) {
            props.put("param", data.getParameterList());
        }
        if (data.getTo() != null) {
            props.put("to", data.getTo().getRepresenterProperties().get("to"));
        }

        jsonGenerator.writeObject(props);
    }
}
