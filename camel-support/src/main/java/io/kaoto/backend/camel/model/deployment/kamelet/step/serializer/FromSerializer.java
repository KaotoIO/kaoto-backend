package io.kaoto.backend.camel.model.deployment.kamelet.step.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.kaoto.backend.camel.model.deployment.kamelet.step.From;

import java.io.IOException;

public class FromSerializer extends StdSerializer<From> {

    public FromSerializer() {
        super(From.class);
    }

    @Override
    public void serialize(From data, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeObject(data.getRepresenterProperties());
    }
}
