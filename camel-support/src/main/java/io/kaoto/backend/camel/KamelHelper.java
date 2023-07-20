package io.kaoto.backend.camel;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public final class KamelHelper {
    private static final Logger LOG = LoggerFactory.getLogger(KamelHelper.class);


    public static final ObjectMapper JSON_MAPPER = JsonMapper.builder()
        .build();

    public static final ObjectMapper YAML_MAPPER = YAMLMapper.builder()
        .build();

    private KamelHelper() {
        // final class
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
