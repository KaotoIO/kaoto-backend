package io.kaoto.backend.model.parameter;

import jakarta.json.JsonObject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.serializer.DeserializationContext;
import jakarta.json.bind.serializer.JsonbDeserializer;
import jakarta.json.stream.JsonParser;
import java.lang.reflect.Type;

import io.quarkus.runtime.annotations.RegisterForReflection;

/**
 * This is a workaround utility class until Quarkus supports full polymorphic
 * unmarshalling.
 */
@RegisterForReflection
public class ParameterDeserializer implements JsonbDeserializer<Parameter> {

    private static final Jsonb JSONB = JsonbBuilder.create();

    @Override
    public Parameter deserialize(final JsonParser parser,
                                 final DeserializationContext context,
                                 final Type rtType) {

        JsonObject jsonObj = parser.getObject();
        String jsonString = jsonObj.toString();
        String type = jsonObj.getString("type");

        switch (type) {
            case "string":
                return JSONB.fromJson(jsonString, StringParameter.class);
            case "number":
                return JSONB.fromJson(jsonString, NumberParameter.class);
            case "integer":
                return JSONB.fromJson(jsonString, IntegerParameter.class);
            case "boolean":
                return JSONB.fromJson(jsonString, BooleanParameter.class);
            case "array":
                return JSONB.fromJson(jsonString, ArrayParameter.class);
            default:
                return JSONB.fromJson(jsonString, ObjectParameter.class);
        }
    }
}
