package io.kaoto.backend.camel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;

public final class KamelHelper {
    public static final ObjectMapper JSON_MAPPER = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    public static final ObjectMapper JSON_MAPPER_LAZY = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static final ObjectMapper YAML_MAPPER = new ObjectMapper(new YAMLFactory())
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private KamelHelper() {
        // final class
    }

    public static <K, V> Map<K, V> asMap(Object data) {
        return JSON_MAPPER.convertValue(data, new TypeReference<Map<K, V>>() {});
    }
}
