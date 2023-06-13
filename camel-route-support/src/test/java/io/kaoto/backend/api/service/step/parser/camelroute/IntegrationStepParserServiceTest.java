package io.kaoto.backend.api.service.step.parser.camelroute;

import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.deployment.generator.camelroute.IntegrationDeploymentGeneratorService;
import io.kaoto.backend.model.step.Step;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import jakarta.inject.Inject;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@QuarkusTest
class IntegrationStepParserServiceTest {
    private static String integration;
    @Inject
    IntegrationStepParserService service;

    @Inject
    IntegrationDeploymentGeneratorService deploymentService;

    @Inject
    private StepCatalog catalog;

    @BeforeAll
    static void setup() throws IOException, URISyntaxException {
        integration = Files.readString(Path.of(
                IntegrationStepParserServiceTest.class.getResource(
                                "integration.yaml")
                        .toURI()));
    }

    @BeforeEach
    void ensureCatalog() {
        catalog.waitForWarmUp().join();
    }

    @Test
    void parse() {
        var parsed = service.deepParse(integration);
        assertThat(parsed.getSteps())
                .hasSize(3)
                .extracting(Step::getName)
                .containsExactly("timer", "activemq", "log");
        assertThat(parsed.getMetadata().get("name")).isEqualTo("Easy integration example");
        assertThat(parsed.getParameters()).isEmpty();
        var yaml = deploymentService.parse(parsed.getSteps(), parsed.getMetadata(), parsed.getParameters());
        assertThat(yaml).isEqualToNormalizingNewlines(integration);
    }

    @ParameterizedTest
    @ValueSource(strings = {"invalid/dropbox-sink.kamelet.yaml", "invalid/twitter-search-source-binding.yaml",
            "route.yaml"})
    void deepParseInvalid(String resourcePath) throws IOException {
        String input = new String(Objects.requireNonNull(this.getClass().getResourceAsStream(resourcePath))
                .readAllBytes(), StandardCharsets.UTF_8);

        assertThatThrownBy(() -> service.deepParse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Wrong format provided. This is not parseable by us.");
    }

    @Test
    void appliesTo() {
        assertThat(service.appliesTo(integration)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"invalid/dropbox-sink.kamelet.yaml", "invalid/twitter-search-source-binding.yaml",
            "route.yaml"})
    void appliesToInvalid(String resourcePath) throws IOException {
        String input = new String(Objects.requireNonNull(this.getClass().getResourceAsStream(resourcePath))
                .readAllBytes(), StandardCharsets.UTF_8);
        assertThat(service.appliesTo(input)).isFalse();
    }
}
