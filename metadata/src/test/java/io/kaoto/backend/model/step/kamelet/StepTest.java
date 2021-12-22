package io.kaoto.backend.model.step.kamelet;

import io.kaoto.backend.model.step.Step;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

@QuarkusTest
class StepTest {

    @Test
    void testParser() {
        Representer representer = new Representer();
        representer.getPropertyUtils().setSkipMissingProperties(true);
        Yaml yaml = new Yaml(new Constructor(Step.class), representer);
        Step step = yaml.load(
                StepTest.class.getResourceAsStream(
                        "/elasticsearch-index-sink.kamelet.yaml"));

        Assertions.assertNotNull(step.getSpec());
        Assertions.assertNotNull(step.getMetadata());
    }

}
