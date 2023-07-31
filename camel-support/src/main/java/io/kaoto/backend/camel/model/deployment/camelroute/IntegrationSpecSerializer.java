package io.kaoto.backend.camel.model.deployment.camelroute;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

class IntegrationSpecSerializer extends StdSerializer<IntegrationSpec> {

    public IntegrationSpecSerializer() {
        super(IntegrationSpec.class);
    }

    @Override
    public void serialize(IntegrationSpec data, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {

        Map<String, Object> properties = new LinkedHashMap<>();
        properties.put("configuration", data.getConfiguration());
        properties.put("dependencies", data.getDependencies());
        properties.put("integrationKit", data.getIntegrationKit());
        properties.put("profile", data.getProfile());
        properties.put("replicas", data.getReplicas());
        properties.put("repositories", data.getRepositories());
        properties.put("serviceAccountName", data.getServiceAccountName());
        properties.put("sources", data.getSources());
        properties.put("template", data.getTemplate());
        properties.put("traits", data.getTraits());
        properties.put("flows", data.get_flows());
        properties.values().removeAll(Collections.singleton(null));
        jsonGenerator.writeObject(properties);
    }


}
