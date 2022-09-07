package io.kaoto.backend.model.deployment.kamelet.step.marshal;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.kaoto.backend.model.deployment.kamelet.step.MarshalFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.dataformat.DataFormat;

import java.io.IOException;

public class MarshalSerializer extends JsonSerializer {

    @Override
    public void serialize(final Object value, final JsonGenerator gen,
                          final SerializerProvider serializers)
            throws IOException {

        MarshalFlowStep step = (MarshalFlowStep) value;

        gen.writeStartObject();
        gen.writeFieldName("marshal");
        if (step.getDataFormat() != null) {
            writeDataFormat(gen, step.getDataFormat());
        }
        gen.writeEndObject();
    }

    private void writeDataFormat(final JsonGenerator gen,
                             final DataFormat df)
            throws IOException {
        gen.writeStartObject();
        gen.writeFieldName(df.getFormat());
        if (df.getProperties() != null) {
            gen.writeStartObject();
            for (var property : df.getProperties().entrySet()) {
                gen.writeStringField(property.getKey(), property.getValue());
            }
            gen.writeEndObject();
        }
        gen.writeEndObject();
    }
}
