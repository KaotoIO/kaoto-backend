package io.kaoto.backend.api.service.deployment.generator.camelroute;

import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.step.parser.camelroute.IntegrationStepParserService;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
class DeploymentGeneratorServiceTest {

    private IntegrationDeploymentGeneratorService deploymentGeneratorService;

    private IntegrationStepParserService stepParserService;

    private StepCatalog catalog;

    @BeforeEach
    void ensureCatalog() {
        catalog.waitForWarmUp().join();
    }

    @Test
    void parse() {
        String yaml = String.join(System.lineSeparator(), Arrays.asList(
                "apiVersion: camel.apache.org/v1",
                "kind: Integration",
                "metadata:",
                "  name: hello.yaml",
                "spec:",
                "  flows:",
                "  - from:",
                "      uri: timer:tick",
                "      parameters:",
                "        period: '5000'",
                "      steps:",
                "      - to:",
                "          uri: log:tick")) + System.lineSeparator();

        var parsed = stepParserService.deepParse(yaml);

        var yaml2 = deploymentGeneratorService.parse(parsed.getSteps(),
                parsed.getMetadata(), parsed.getParameters());

        assertThat(yaml).isEqualToNormalizingNewlines(yaml2);
    }

    @Test
    void parseFlows() {
        String yaml = String.join(System.lineSeparator(), Arrays.asList(
                "apiVersion: camel.apache.org/v1",
                "kind: Integration",
                "metadata:",
                "  name: hello.yaml",
                "spec:",
                "  flows:",
                "  - from:",
                "      uri: timer:tick",
                "      parameters:",
                "        period: '5000'",
                "      steps:",
                "      - to:",
                "          uri: log:tick",
                "  - from:",
                "      uri: timer:tock",
                "      parameters:",
                "        period: '3000'",
                "      steps:",
                "      - to:",
                "          uri: log:tock"
)) + System.lineSeparator();

        var parsed = stepParserService.getParsedFlows(yaml);

        var yaml2 = deploymentGeneratorService.parse(parsed);

        assertThat(yaml).isEqualToNormalizingNewlines(yaml2);
    }

    @Inject
    public void setters(
            final IntegrationDeploymentGeneratorService dgs,
            final IntegrationStepParserService stepParserService,
            final StepCatalog catalog) {
        this.deploymentGeneratorService = dgs;
        this.catalog = catalog;
        this.stepParserService = stepParserService;
    }

}
