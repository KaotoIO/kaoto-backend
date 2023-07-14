package io.kaoto.backend.camel.service.deployment.generator;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public final class GeneratorHelper {

    private static final Logger LOG = LoggerFactory.getLogger(GeneratorHelper.class);

    private GeneratorHelper() {
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
