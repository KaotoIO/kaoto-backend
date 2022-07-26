package io.kaoto.backend.api.service.deployment.generator.camelroute;

import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.step.parser.camelroute.IntegrationStepParserService;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class IntegrationDeploymentGeneratorServiceTest {

    private IntegrationDeploymentGeneratorService deploymentGeneratorService;

    private IntegrationStepParserService stepParserService;

    private StepCatalog catalog;

    @BeforeEach
    void ensureCatalog() {
        catalog.waitForWarmUp().join();
    }

    @Test
    void parse() {
        String yaml = "apiVersion: camel.apache.org/v1\n"
                + "kind: Integration\n"
                + "metadata:\n"
                + "  labels: {}\n"
                + "  name: hello.yaml\n"
                + "spec:\n"
                + "  flows:\n"
                + "  - from:\n"
                + "      uri: timer:tick\n"
                + "      parameters:\n"
                + "        period: '5000'\n"
                + "      steps:\n"
                + "      - to:\n"
                + "          uri: log:tick\n";

        var parsed = stepParserService.deepParse(yaml);

        var yaml2 = deploymentGeneratorService.parse(parsed.getSteps(),
                parsed.getMetadata(), parsed.getParameters());

        assertEquals(yaml, yaml2);
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
