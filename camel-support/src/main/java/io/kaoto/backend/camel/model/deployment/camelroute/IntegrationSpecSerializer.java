package io.kaoto.backend.camel.model.deployment.camelroute;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

class IntegrationSpecSerializer extends StdSerializer<IntegrationSpec> {

    public IntegrationSpecSerializer() {
        super(IntegrationSpec.class);
    }

    @Override
    public void serialize(IntegrationSpec data, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {

        jsonGenerator.writeStartObject();

        if (data.getConfiguration() != null) {
            jsonGenerator.writeObjectField("configuration", data.getConfiguration());
        }
        if (data.getDependencies() != null) {
            jsonGenerator.writeObjectField("dependencies", data.getDependencies());
        }
        if (data.getIntegrationKit() != null) {
            jsonGenerator.writeObjectField("integrationKit", data.getIntegrationKit());
        }
        if (data.getProfile() != null) {
            jsonGenerator.writeObjectField("profile", data.getProfile());
        }
        if (data.getReplicas() != null) {
            jsonGenerator.writeObjectField("replicas", data.getReplicas());
        }
        if (data.getRepositories() != null) {
            jsonGenerator.writeObjectField("repositories", data.getRepositories());
        }
        if (data.getServiceAccountName() != null) {
            jsonGenerator.writeObjectField("serviceAccountName", data.getServiceAccountName());
        }
        if (data.getSources() != null) {
            jsonGenerator.writeObjectField("sources", data.getSources());
        }
        if (data.getTemplate() != null) {
            jsonGenerator.writeObjectField("template", data.getTemplate());
        }
        if (data.getTraits() != null) {
            jsonGenerator.writeObjectField("traits", data.getTraits());
        }
        if (data.get_flows() != null) {
            jsonGenerator.writeObjectField("flows", data.get_flows());
        }
        jsonGenerator.writeEndObject();
    }


}
