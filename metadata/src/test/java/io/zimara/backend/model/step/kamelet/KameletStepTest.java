package io.zimara.backend.model.step.kamelet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

class KameletStepTest {

    @Test
    public void testParser() {

        Yaml yaml = new Yaml(new Constructor(KameletStep.class));
        KameletStep step = yaml.load(KameletStepTest.class.getResourceAsStream("/elasticsearch-index-sink.kamelet.yaml"));

        Assertions.assertEquals("elasticsearch-index-sink", step.getID());
        Assertions.assertEquals("elasticsearch-index-sink", step.getName());
        Assertions.assertNotNull(step.getIcon());
        Assertions.assertNotNull(step.getDescription());
        Assertions.assertNotNull(step.getApiVersion());
        Assertions.assertEquals("ElasticSearch", step.getGroup());
        Assertions.assertNotNull(step.getParameters());


    }

}