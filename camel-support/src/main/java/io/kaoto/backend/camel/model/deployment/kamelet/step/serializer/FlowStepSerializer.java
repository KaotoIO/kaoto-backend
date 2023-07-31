package io.kaoto.backend.camel.model.deployment.kamelet.step.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.kaoto.backend.camel.model.deployment.kamelet.FlowStep;

import java.io.IOException;

public class FlowStepSerializer extends StdSerializer<FlowStep> {

    public FlowStepSerializer() {
        super(FlowStep.class);
    }

    @Override
    public void serialize(FlowStep data, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeObject(data.getRepresenterProperties());
    }
}
