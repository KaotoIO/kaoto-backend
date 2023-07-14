package io.kaoto.backend.camel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;

public final class KamelHelper {
    private static final Logger LOG = LoggerFactory.getLogger(KamelHelper.class);

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

    public static Optional<String> loadResourceAsString(Class<?> type, String resource) {
        try (InputStream is = type.getResourceAsStream(resource)){
            if (is == null) {
                LOG.error("Can't load resource {}", resource);
            } else {
                return Optional.of(new String(is.readAllBytes(), StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            LOG.error("Can't load resource {}", resource);
        }

        return Optional.empty();
    }
}
