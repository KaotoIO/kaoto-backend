package io.zimara.backend.api.service.deployment;

import io.quarkus.test.junit.QuarkusTest;
import io.zimara.backend.api.metadata.catalog.StepCatalog;
import io.zimara.backend.api.metadata.catalog.ViewDefinitionCatalog;
import io.zimara.backend.api.service.step.parser.KameletBindingStepParserService;
import io.zimara.backend.api.service.viewdefinition.ViewDefinitionService;
import io.zimara.backend.model.step.kamelet.KameletStep;
import io.zimara.backend.model.view.ViewDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@QuarkusTest
class DeploymentServiceTest {

    private StepCatalog stepCatalog;

    private ViewDefinitionCatalog viewCatalog;

    private DeploymentService deploymentService;

    private ViewDefinitionService viewDefinitionService;

    private KameletBindingStepParserService stepParser;

    @Inject
    public void setStepCatalog(StepCatalog stepCatalog) {
        this.stepCatalog = stepCatalog;
    }

    @Inject
    public void setViewCatalog(ViewDefinitionCatalog viewCatalog) {
        this.viewCatalog = viewCatalog;
    }

    @Inject
    public void setDeploymentService(DeploymentService deploymentService) {
        this.deploymentService = deploymentService;
    }

    @Inject
    public void setViewDefinitionService(ViewDefinitionService viewDefinitionService) {
        this.viewDefinitionService = viewDefinitionService;
    }

    @Inject
    public void setStepParser(KameletBindingStepParserService stepParser) {
        this.stepParser = stepParser;
    }

    private static String binding = "";

    @BeforeAll
    static void setup() throws URISyntaxException, IOException {
        binding = Files.readString(
                Path.of(DeploymentServiceTest.class.getResource(
                        "../../resource/twitter-search-source-binding.yaml")
                        .toURI()));
    }

    @BeforeEach
    void ensureCatalog() {
        stepCatalog.waitForWarmUp().join();
        viewCatalog.waitForWarmUp().join();
    }

    @Test
    void yaml() {
        final var steps = stepParser.parse(binding);
        List<ViewDefinition> views = viewDefinitionService.views(binding, steps);
        String res = deploymentService.yaml("twitter-search-source-binding", steps.toArray(new KameletStep[0]));
        String expectedStr ="apiVersion: camel.apache.org/v1alpha1\n"
                + "kind: KameletBinding\n"
                + "metadata:\n"
                + "  name: twitter-search-source-binding\n"
                + "spec:\n"
                + "  sink:\n"
                + "    properties:\n"
                + "      password: The Password\n"
                + "      brokers: The Brokers\n"
                + "      topic: The Topic Names\n"
                + "      username: The Username\n"
                + "    ref:\n"
                + "      apiVersion: camel.apache.org/v1alpha1\n"
                + "      kind: Kamelet\n"
                + "      name: kafka-sink\n"
                + "  source:\n"
                + "    properties:\n"
                + "      keywords: Apache Camel\n"
                + "      apiKey: your own\n"
                + "      apiKeySecret: your own\n"
                + "      accessToken: your own\n"
                + "      accessTokenSecret: your own\n"
                + "    ref:\n"
                + "      apiVersion: camel.apache.org/v1alpha1\n"
                + "      kind: Kamelet\n"
                + "      name: twitter-search-source\n"
                + "  steps:\n"
                + "  - properties: {}\n"
                + "    ref:\n"
                + "      apiVersion: camel.apache.org/v1alpha1\n"
                + "      kind: Kamelet\n"
                + "      name: aws-translate-action\n"
                + "  - properties: {}\n"
                + "    ref:\n"
                + "      apiVersion: camel.apache.org/v1alpha1\n"
                + "      kind: Kamelet\n"
                + "      name: avro-deserialize-action\n";

        Assertions.assertEquals(expectedStr, res);
    }
}
