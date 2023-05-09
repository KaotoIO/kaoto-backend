package io.kaoto.backend.api.service.step.parser.kamelet;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.deployment.generator.kamelet.KameletBindingDeploymentGeneratorService;
import io.quarkus.test.junit.QuarkusTest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@QuarkusTest
class KameletBindingStepParserServiceTest {

    private static String binding;

    @Inject
    private KameletBindingStepParserService service;

    @Inject
    private KameletBindingDeploymentGeneratorService deploymentService;

    private StepCatalog catalog;

    @Inject
    public void setStepCatalog(final StepCatalog catalog) {
        this.catalog = catalog;
    }

    @BeforeAll
    static void setup() throws URISyntaxException, IOException {
        binding = Files.readString(Path.of(
                KameletBindingStepParserServiceTest.class.getResource(
                                "twitter-search-source-binding.yaml")
                        .toURI()));
    }

    @BeforeEach
    void ensureCatalog() {
        catalog.waitForWarmUp().join();
    }

    @Test
    void parse() throws JsonProcessingException {
        var parsed = service.deepParse(binding);
        assertEquals(3, parsed.getSteps().size());
        assertEquals("twitter-search-source", parsed.getSteps().get(0).getName());
        assertEquals("aws-translate-action", parsed.getSteps().get(1).getName());
        assertEquals("knative", parsed.getSteps().get(2).getName());
        assertEquals("Kamelet Binding generated by Kaoto", parsed.getMetadata().get("name"));
        assertTrue(parsed.getParameters().isEmpty());
        var yaml = deploymentService.parse(parsed.getSteps(), parsed.getMetadata(), parsed.getParameters());

        assertThat(yaml).isEqualToNormalizingNewlines(binding);
    }

    @Test
    void parseKnative() throws URISyntaxException, IOException {
        final String binding = Files.readString(
                Path.of(KameletBindingStepParserServiceTest.class.getResource("knative-binding.yaml").toURI()));
        var parsed = service.deepParse(binding);
        assertEquals(2, parsed.getSteps().size());
       var yaml = deploymentService.parse(parsed.getSteps(), parsed.getMetadata(), parsed.getParameters());

       assertThat(yaml).isEqualToNormalizingNewlines(binding);
    }

    @Test
    void appliesTo() {
        assertTrue(service.appliesTo(binding));
        assertFalse(service.appliesTo("whatever"));
    }
}
