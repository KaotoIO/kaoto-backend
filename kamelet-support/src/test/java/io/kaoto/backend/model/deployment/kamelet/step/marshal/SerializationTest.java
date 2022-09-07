package io.kaoto.backend.model.deployment.kamelet.step.marshal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.kaoto.backend.api.service.deployment.generator.kamelet.KameletRepresenter;
import io.kaoto.backend.model.deployment.kamelet.Kamelet;
import io.kaoto.backend.model.deployment.kamelet.step.MarshalFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.dataformat.DataFormat;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
class SerializationTest {


    @Test
    void yaml() throws JsonProcessingException {
        Yaml yaml = new Yaml(
                new Constructor(),
                new KameletRepresenter());
        ObjectMapper yamlMapper =
                new ObjectMapper(new YAMLFactory())
                        .configure(
                                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                                false);

        var step = getMarshalFlowStep();
        var yamlstring = yaml.dumpAsMap(step);
        compareMarshalSteps(step, yamlMapper.readValue(yamlstring, MarshalFlowStep.class));

        step = getMarshalFlowStep2();
        yamlstring = yaml.dumpAsMap(step);
        compareMarshalSteps(step, yamlMapper.readValue(yamlstring, MarshalFlowStep.class));
    }

    private void compareMarshalSteps(final MarshalFlowStep step,
                                     final MarshalFlowStep other) {
        DataFormat dataFormat1 = step.getDataFormat();
        DataFormat dataFormat2 = other.getDataFormat();
        assertEquals(dataFormat1.getFormat(), dataFormat2.getFormat());

        assertTrue(dataFormat1.getProperties().keySet().stream()
                .allMatch(k -> dataFormat2.getProperties().containsKey(k)));

        assertTrue(dataFormat2.getProperties().keySet().stream()
                .allMatch(k -> dataFormat1.getProperties().containsKey(k)));
    }

    private MarshalFlowStep getMarshalFlowStep() {
        MarshalFlowStep step = new MarshalFlowStep();
        step.setDataFormat(new DataFormat());
        step.getDataFormat().setFormat("json");
        step.getDataFormat().setProperties(new HashMap<>());
        step.getDataFormat().getProperties().put("library", "Gson");
        return step;
    }

    private MarshalFlowStep getMarshalFlowStep2() {
        MarshalFlowStep step = new MarshalFlowStep();
        step.setDataFormat(new DataFormat());
        step.getDataFormat().setFormat("json");
        step.getDataFormat().setProperties(new HashMap<>());
        step.getDataFormat().getProperties().put("library", "Jackson");
        step.getDataFormat().getProperties().put("unmarshalType",
                "com.fasterxml.jackson.databind.JsonNode");
        step.getDataFormat().getProperties().put("schemaResolver", "#class"
                + ":org.apache.camel.kamelets.utils.serialization"
                + ".InflightProtobufSchemaResolver");
        return step;
    }
}
