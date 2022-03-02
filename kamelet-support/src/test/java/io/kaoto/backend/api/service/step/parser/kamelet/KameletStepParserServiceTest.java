package io.kaoto.backend.api.service.step.parser.kamelet;

import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.deployment.generator.kamelet.KameletDeploymentGeneratorService;
import io.quarkus.test.junit.QuarkusTest;
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
    }

    @Test
    void goAndBackAgain() {
        var parsed = service.deepParse(binding);
        String output = deploymentService.parse(parsed.getSteps(),
                parsed.getMetadata());
        var parsed2 = service.deepParse(output);
        assertEquals(parsed.getSteps(), parsed2.getSteps());
        assertEquals(parsed.getMetadata(), parsed2.getMetadata());
    }

    @Test
    void appliesTo() {
        assertTrue(service.appliesTo(binding));
        assertTrue(deploymentService.appliesTo(service.parse(binding)));
    }
}