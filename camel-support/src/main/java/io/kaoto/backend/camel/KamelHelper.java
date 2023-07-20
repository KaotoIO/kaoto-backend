package io.kaoto.backend.camel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public final class KamelHelper {
    public static final ObjectMapper JSON_MAPPER = JsonMapper.builder()
        .build();

    public static final ObjectMapper YAML_MAPPER = YAMLMapper.builder()
        .build();

    private KamelHelper() {
        // final class
    }
}
