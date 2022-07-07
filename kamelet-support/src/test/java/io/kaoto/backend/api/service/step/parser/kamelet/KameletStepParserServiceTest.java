package io.kaoto.backend.api.service.step.parser.kamelet;

import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.deployment.generator.kamelet.KameletDeploymentGeneratorService;
import io.kaoto.backend.model.deployment.kamelet.KameletDefinition;
import io.kaoto.backend.model.deployment.kamelet.KameletDefinitionProperty;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
class KameletStepParserServiceTest {

    private static String binding;
    private static String bindingIncomplete;

    @Inject
    private KameletStepParserService service;

    @Inject
    private KameletDeploymentGeneratorService deploymentService;

    private StepCatalog catalog;

    @Inject
    public void setStepCatalog(final StepCatalog catalog) {
        this.catalog = catalog;
    }

    @BeforeAll
    static void setup() throws URISyntaxException, IOException {
        binding = Files.readString(Path.of(
                KameletBindingStepParserServiceTest.class.getResource(
                                "dropbox-sink.kamelet.yaml")
                        .toURI()));
        bindingIncomplete = Files.readString(Path.of(
                KameletBindingStepParserServiceTest.class.getResource(
                                "dropbox-sink.kamelet-incomplete.yaml")
                        .toURI()));
    }

    @BeforeEach
    void ensureCatalog() {
        catalog.waitForWarmUp().join();
    }

    @Test
    void deepParse() {
        var parsed = service.deepParse(binding);
        assertNotNull(parsed);

        assertNotNull(parsed.getMetadata());
        assertFalse(parsed.getMetadata().isEmpty());
        Map<String, String> labels =
                (Map<String, String>) parsed.getMetadata().get("labels");
        Map<String, String> annotations =
                (Map<String, String>) parsed.getMetadata().get("annotations");
        assertEquals("Apache Software Foundation",
                annotations.get("camel.apache.org/provider"));
        assertEquals("Preview",
                annotations.get(
                        "camel.apache.org/kamelet.support.level"));
        assertEquals("sink",
                labels.get(
                        "camel.apache.org/kamelet.type"));
        assertEquals("Dropbox",
                annotations.get(
                        "camel.apache.org/kamelet.group"));
        assertEquals("dropbox-sink", parsed.getMetadata().get("name"));

        assertNotNull(parsed.getSteps());
        assertFalse(parsed.getSteps().isEmpty());
        assertTrue(parsed.getSteps().stream().allMatch(s -> s != null));
        assertEquals(3, parsed.getSteps().size());

        final var fromStep = parsed.getSteps().get(0);
        assertEquals("kamelet:source", fromStep.getId());

        final var choiceStep = parsed.getSteps().get(1);
        assertEquals("choice", choiceStep.getId());
        assertNotNull(choiceStep.getBranches());
        assertEquals(2, choiceStep.getBranches().size());

        final var dropboxStep = parsed.getSteps().get(2);
        assertEquals("dropbox", dropboxStep.getId());

        KameletDefinition definition =
                (KameletDefinition) parsed.getMetadata().get("definition");

        assertNotNull(definition);

        assertEquals("Dropbox Sink", definition.getTitle());
        assertNotNull(definition.getProperties());
        assertFalse(definition.getProperties().isEmpty());
        assertEquals(4, definition.getProperties().size());
        assertTrue(definition.getProperties().containsKey("accessToken"));
        KameletDefinitionProperty accessToken =
                definition.getProperties().get("accessToken");
        assertEquals("Dropbox Access Token", accessToken.getTitle());
        assertEquals("The access Token to use to access Dropbox",
                accessToken.getDescription());
        assertEquals("string", accessToken.getType());
    }

    @Test
    void goAndBackAgain() {
        var parsed = service.deepParse(binding);
        String output = deploymentService.parse(parsed.getSteps(),
                parsed.getMetadata(), parsed.getParameters());
        var parsed2 = service.deepParse(output);
        assertEquals(parsed.getSteps(), parsed2.getSteps());
        assertEquals(parsed.getMetadata().keySet(),
                parsed2.getMetadata().keySet());
        for (String key : new String[]{"labels", "annotations",
                "additionalProperties",
                "name"}) {
            assertEquals(parsed.getMetadata().get(key),
                    parsed2.getMetadata().get(key));
        }

        var parsedInc = service.deepParse(bindingIncomplete);
        String outputInc = deploymentService.parse(parsedInc.getSteps(),
                parsedInc.getMetadata(), parsed.getParameters());
        var parsed2Inc = service.deepParse(outputInc);
        assertEquals(parsedInc.getSteps(), parsed2Inc.getSteps());
    }

    @Test
    void appliesTo() {
        assertTrue(service.appliesTo(binding));
        assertTrue(service.appliesTo(bindingIncomplete));
        assertTrue(
                deploymentService.appliesTo(
                        service.deepParse(binding).getSteps()));
        assertTrue(
                deploymentService.appliesTo(
                        service.deepParse(bindingIncomplete).getSteps()));
    }

    @Test
    void fail() {
        //include here potential security issues
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.deepParse("Kind: Kamelet\n But not really");
        });
        assertFalse(service.appliesTo("Malformed YAML"));
    }
}
