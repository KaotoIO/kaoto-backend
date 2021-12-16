package io.kaoto.backend.api.service.deployment;

import io.quarkus.test.junit.QuarkusTest;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.metadata.catalog.ViewDefinitionCatalog;
import io.kaoto.backend.api.service.step.parser.KameletBindingStepParserService;
import io.kaoto.backend.api.service.viewdefinition.ViewDefinitionService;
import io.kaoto.backend.model.step.kamelet.KameletStep;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

@QuarkusTest
class DeploymentServiceTest {

    private StepCatalog stepCatalog;

    private ViewDefinitionCatalog viewCatalog;

    private DeploymentService deploymentService;

    private ViewDefinitionService viewDefinitionService;

    private KameletBindingStepParserService stepParser;

    @Inject
    public void setStepCatalog(final StepCatalog stepCatalog) {
        this.stepCatalog = stepCatalog;
    }

    @Inject
    public void setViewCatalog(final ViewDefinitionCatalog viewCatalog) {
        this.viewCatalog = viewCatalog;
    }

    @Inject
    public void setDeploymentService(
            final DeploymentService deploymentService) {
        this.deploymentService = deploymentService;
    }

    @Inject
    public void setViewDefinitionService(
            final ViewDefinitionService viewDefinitionService) {
        this.viewDefinitionService = viewDefinitionService;
    }

    @Inject
    public void setStepParser(
            final KameletBindingStepParserService stepParser) {
        this.stepParser = stepParser;
    }

    private static String binding = "";
    private static String bindingRegularCamel = "";

    @BeforeAll
    static void setup() throws URISyntaxException, IOException {
        binding = Files.readString(
                Path.of(DeploymentServiceTest.class.getResource(
                        "../../resource/twitter-search-source-binding.yaml")
                        .toURI()));
        bindingRegularCamel
                = Files.readString(
                Path.of(DeploymentServiceTest.class.getResource(
                                "../../resource/camel-conector-example.yaml")
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
        String res = deploymentService.yaml(
                "twitter-search-source-binding",
                steps.toArray(new KameletStep[0]));
        String expectedStr =
                "CRDName: kameletbindings.camel.apache.org\n"
                + "apiVersion: camel.apache.org/v1alpha1\n"
                + "group: camel.apache.org\n"
                + "kind: KameletBinding\n"
                + "metadata:\n"
                + "  additionalProperties: {}\n"
                + "  finalizers: []\n"
                + "  managedFields: []\n"
                + "  name: twitter-search-source-binding\n"
                + "  ownerReferences: []\n"
                + "plural: kameletbindings\n"
                + "scope: Cluster\n"
                + "served: true\n"
                + "singular: kameletbinding\n"
                + "spec:\n"
                + "  sink:\n"
                + "    properties:\n"
                + "      password: The Password\n"
                + "      bootstrapServers: The Brokers\n"
                + "      topic: The Topic Names\n"
                + "      user: The Username\n"
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
                + "      name: avro-deserialize-action\n"
                + "storage: true\n"
                + "version: camel.apache.org/v1alpha1\n";

        Assertions.assertEquals(expectedStr, res);
    }


    @Test
    void regularCamelConnector() {
        final var steps = stepParser.parse(bindingRegularCamel);
        String res = deploymentService.yaml(
                "camel-conector-example",
                steps.toArray(new KameletStep[0]));
        String expectedStr =
                "CRDName: kameletbindings.camel.apache.org\n"
                        + "apiVersion: camel.apache.org/v1alpha1\n"
                        + "group: camel.apache.org\n"
                        + "kind: KameletBinding\n"
                        + "metadata:\n"
                        + "  additionalProperties: {}\n"
                        + "  finalizers: []\n"
                        + "  managedFields: []\n"
                        + "  name: camel-conector-example\n"
                        + "  ownerReferences: []\n"
                        + "plural: kameletbindings\n"
                        + "scope: Cluster\n"
                        + "served: true\n"
                        + "singular: kameletbinding\n"
                        + "spec:\n"
                        + "  sink:\n"
                        + "    uri: log:info?showBody=false&\n"
                        + "  source:\n"
                        + "    uri: log:debug?showBody=true&\n"
                        + "storage: true\n"
                        + "version: camel.apache.org/v1alpha1\n";

        Assertions.assertEquals(expectedStr, res);
    }
}
