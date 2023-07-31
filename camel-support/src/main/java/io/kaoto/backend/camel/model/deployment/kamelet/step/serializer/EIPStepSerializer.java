package io.kaoto.backend.camel.model.deployment.kamelet.step.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.kaoto.backend.camel.model.deployment.kamelet.step.EIPStep;

import java.io.IOException;

public class EIPStepSerializer extends StdSerializer<EIPStep> {

    public EIPStepSerializer() {
        super(EIPStep.class);
    }

    @Override
    public void serialize(EIPStep data, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeObject(data.getRepresenterProperties());
    }
}
