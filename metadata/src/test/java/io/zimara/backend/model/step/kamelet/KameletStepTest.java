package io.zimara.backend.model.step.kamelet;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

@QuarkusTest
class KameletStepTest {

    @Test
    void testParser() {
        Yaml yaml = new Yaml(new Constructor(KameletStep.class));
        KameletStep step = yaml.load(KameletStepTest.class.getResourceAsStream("/elasticsearch-index-sink.kamelet.yaml"));

        Assertions.assertNotNull(step.getSpec());
        Assertions.assertNotNull(step.getMetadata());
        Assertions.assertNotNull(step.getApiVersion());
    }

}