package io.kaoto.backend.api.service.step.parser.camelroute;

import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.deployment.generator.camelroute.IntegrationDeploymentGeneratorService;
import io.kaoto.backend.model.deployment.kamelet.Bean;
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
import java.util.List;
import java.util.Map;
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

    @Test
    void parseMultipleRoutes() throws Exception {
        String input = new String(Objects.requireNonNull(
                        this.getClass().getResourceAsStream("integration-multiroute.yaml"))
                .readAllBytes(), StandardCharsets.UTF_8);
        var parsed = service.getParsedFlows(input);
        assertThat(parsed).hasSize(3);
        var metadata = parsed.get(0);
        assertThat(metadata.getSteps()).isNull();
        assertThat(metadata.getParameters()).isEmpty();
        assertThat(metadata.getMetadata()).containsEntry("name", "multiroute-integration-example");
        assertThat(metadata.getMetadata()).containsEntry("description", "The multiple route integration CRD example");
        var flow1 = parsed.get(1);
        assertThat(flow1.getMetadata()).containsEntry("name", "timer-amq-log");
        assertThat(flow1.getMetadata()).containsEntry("description", "The 1st Timer to ActiveMQ to Log Route");
        assertThat(flow1.getParameters()).isEmpty();
        assertThat(flow1.getSteps()).extracting(Step::getName).containsExactly("timer", "activemq", "log");
        var flow2 = parsed.get(2);
        assertThat(flow2.getMetadata()).containsEntry("name", "timer-amq-log2");
        assertThat(flow2.getMetadata()).containsEntry("description", "The 2nd Timer to ActiveMQ to Log Route");
        assertThat(flow2.getParameters()).isEmpty();
        assertThat(flow2.getSteps()).extracting(Step::getName).containsExactly("timer", "activemq", "log");
        var yaml = deploymentService.parse(parsed);
        assertThat(yaml).isEqualToNormalizingNewlines(input);
    }
    @Test
    void parseWithBeans() throws Exception {
        var yaml = Files.readString(Path.of(this.getClass().getResource("integration-with-beans.yaml").toURI()));
        var parsed = service.getParsedFlows(yaml);
        assertThat(parsed).hasSize(3);
        var route1 = parsed.get(1);
        assertThat(route1.getSteps())
                .hasSize(2)
                .extracting(Step::getName)
                .containsExactly("netty-http", "sql");
        var route2 = parsed.get(2);
        assertThat(route2.getSteps())
                .hasSize(3)
                .extracting(Step::getName)
                .containsExactly("timer", "activemq", "log");
        var metadata = parsed.get(0);
        assertThat(metadata.getMetadata().get("name")).isEqualTo("integration.with.beans");
        List<Bean> beans = (List) metadata.getMetadata().get("beans");
        assertThat(beans)
                .hasSize(2)
                .extracting(Bean::getName)
                .containsExactly("dataSource", "someBean");
        Bean dataSource = beans.get(0);
        assertThat(dataSource.getType()).isEqualTo("org.postgresql.ds.PGSimpleDataSource");
        assertThat(dataSource.getProperties()).hasSize(5);
        assertThat(dataSource.getProperties().get("password")).isEqualTo("password");
        Bean someBean = beans.get(1);
        assertThat(someBean.getType())
                .isEqualTo("io.kaoto.test.backend.api.service.step.parser.camelroute.SomeBean");
        assertThat(someBean.getProperties()).hasSize(2);
        assertThat(someBean.getProperties().get("someObjectProperty"))
                .isInstanceOf(Map.class)
                .extracting(m -> ((Map)m).get("someObjectPropertyProperty"))
                .isEqualTo("someObjectPropertyValue");
        var yaml2 = deploymentService.parse(parsed);
        assertThat(yaml2).isEqualToNormalizingNewlines(yaml);
    }

    @Test
    void parseNoStep() throws Exception {
        String input = new String(Objects.requireNonNull(
                this.getClass().getResourceAsStream("integration-no-step.yaml"))
                .readAllBytes(), StandardCharsets.UTF_8);
        var parsed = service.getParsedFlows(input);
        assertThat(parsed).hasSize(2);
        var metadata = parsed.get(0);
        assertThat(metadata.getSteps()).isNull();
        assertThat(metadata.getParameters()).isEmpty();
        assertThat(metadata.getMetadata().get("name")).isEqualTo("integration-no-step");
        var yaml = deploymentService.parse(parsed);
        assertThat(yaml).isEqualToNormalizingNewlines(input);
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
