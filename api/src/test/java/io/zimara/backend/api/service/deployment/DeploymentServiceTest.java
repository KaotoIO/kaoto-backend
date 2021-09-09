package io.zimara.backend.api.service.deployment;

import io.quarkus.test.junit.QuarkusTest;
import io.zimara.backend.api.metadata.catalog.StepCatalog;
import io.zimara.backend.api.metadata.catalog.ViewDefinitionCatalog;
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

    @Inject
    StepCatalog stepCatalog;

    @Inject
    ViewDefinitionCatalog viewCatalog;

    @Inject
    DeploymentService deploymentService;

    @Inject
    ViewDefinitionService viewDefinitionService;

    private static String binding = "";

    @BeforeAll
    static void setup() throws URISyntaxException, IOException {
        binding = Files.readString(Path.of(DeploymentServiceTest.class.getResource("../../resource/twitter-search-source-binding.yaml").toURI()));
    }

    @BeforeEach
    void ensureCatalog() {
        stepCatalog.waitForWarmUp().join();
        viewCatalog.waitForWarmUp().join();
    }

    @Test
    void yaml() {
        List<ViewDefinition> views = viewDefinitionService.views(binding);
        String res = deploymentService.yaml("twitter-search-source-binding", views.get(0).getSteps().toArray(new KameletStep[0]));
        String expectedStr ="apiVersion: camel.apache.org/v1alpha1\n" +
                "kind: KameletBinding\n" +
                "metadata:\n" +
                "  name: twitter-search-source-binding\n" +
                "spec:\n" +
                "  sink:\n" +
                "    properties:\n" +
                "      password: The Password\n" +
                "      brokers: The Brokers\n" +
                "      topic: The Topic Names\n" +
                "      username: The Username\n" +
                "    ref:\n" +
                "      apiVersion: camel.apache.org/v1alpha1\n" +
                "      kind: Kamelet\n" +
                "      name: kafka-sink\n" +
                "  source:\n" +
                "    properties:\n" +
                "      keywords: Apache Camel\n" +
                "      apiKey: your own\n" +
                "      apiKeySecret: your own\n" +
                "      accessToken: your own\n" +
                "      accessTokenSecret: your own\n" +
                "    ref:\n" +
                "      apiVersion: camel.apache.org/v1alpha1\n" +
                "      kind: Kamelet\n" +
                "      name: twitter-search-source\n" +
                "  steps:\n" +
                "  - properties: {}\n" +
                "    ref:\n" +
                "      apiVersion: camel.apache.org/v1alpha1\n" +
                "      kind: Kamelet\n" +
                "      name: aws-translate-action\n" +
                "  - properties: {}\n" +
                "    ref:\n" +
                "      apiVersion: camel.apache.org/v1alpha1\n" +
                "      kind: Kamelet\n" +
                "      name: avro-deserialize-action\n";

        Assertions.assertEquals(expectedStr, res);
    }
}