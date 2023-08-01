package io.kaoto.backend.camel.model.deployment.kamelet.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.kaoto.backend.camel.KamelHelper;
import org.apache.camel.v1alpha1.KameletBindingSpec;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class KameletBindingSpecSerializer extends StdSerializer<KameletBindingSpec> {

    public KameletBindingSpecSerializer() {
        super(KameletBindingSpec.class);
    }

    @Override
    public void serialize(KameletBindingSpec data, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        Map<String, Object> properties = new LinkedHashMap<>();
        //Ordering
        properties.put("source", data.getSource());
        if (data.getSteps() != null) {
            properties.put(KamelHelper.STEPS, data.getSteps());
        }
        properties.put("sink", data.getSink());

        if (data.getErrorHandler() != null) {
            properties.put("errorHandler", data.getErrorHandler());
        }
        if (data.getIntegration() != null) {
            properties.put("integration", data.getIntegration());
        }
        if (data.getReplicas() != null) {
            properties.put("replicas", data.getReplicas());
        }
        if (data.getServiceAccountName() != null) {
            properties.put("serviceAccountName", data.getServiceAccountName());
        }
        jsonGenerator.writeObject(properties);
    }
}
