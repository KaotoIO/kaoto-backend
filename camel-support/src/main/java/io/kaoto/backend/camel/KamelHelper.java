package io.kaoto.backend.camel;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import org.apache.camel.v1alpha1.KameletBindingSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import io.kaoto.backend.camel.model.deployment.kamelet.serializer.KameletBindingSpecSerializer;

public final class KamelHelper {
    private static final Logger LOG = LoggerFactory.getLogger(KamelHelper.class);

    //Some common constants
    public final static String STEPS = "steps";
    public final static String DESCRIPTION = "description";
    public final static String PARAMETERS = "parameters";
    public final static String NAME = "name";

    public static final ObjectMapper JSON_MAPPER = JsonMapper.builder()
        .build();

    public static final ObjectMapper YAML_MAPPER = YAMLMapper.builder()
            .enable(YAMLGenerator.Feature.MINIMIZE_QUOTES)
            .disable(YAMLGenerator.Feature.INDENT_ARRAYS)
            .disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        .build()
            .registerModule(new SimpleModule()
                    .addSerializer(KameletBindingSpec.class, new KameletBindingSpecSerializer()));

    private KamelHelper() {
        // final class
    }

    public static String getCRKind(String yaml) {
        try {
            return String.valueOf(YAML_MAPPER.readValue(yaml, java.util.Map.class).get("kind"));
        } catch (Exception e) {
            LOG.trace("Tried to get the kind of something that wasn't a Custom Resource?", e);
            return null;
        }
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
