package io.kaoto.backend.api.service.deployment;

import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.metadata.catalog.ViewDefinitionCatalog;
import io.kaoto.backend.api.service.step.parser.kamelet.KameletBindingStepParserService;
import io.kaoto.backend.api.service.viewdefinition.ViewDefinitionService;
import io.kaoto.backend.model.step.Step;
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

@QuarkusTest
class DeploymentServiceTest {

    private static String binding = "";
    private static String bindingRegularCamel = "";
    private StepCatalog stepCatalog;
    private ViewDefinitionCatalog viewCatalog;
    private DeploymentService deploymentService;
    private ViewDefinitionService viewDefinitionService;
    private KameletBindingStepParserService stepParser;

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

    @BeforeEach
    void ensureCatalog() {
        stepCatalog.waitForWarmUp().join();
        viewCatalog.waitForWarmUp().join();
    }

    @Test
    void yaml() {
        final var steps = stepParser.parse(binding);
        var res = deploymentService.crd(
                "twitter-search-source-binding",
                steps.toArray(new Step[0]));
        String expectedStr = "apiVersion: camel.apache.org/v1alpha1\n"
                + "group: camel.apache.org\n"
                + "kind: KameletBinding\n"
                + "metadata:\n"
                + "  additionalProperties: {}\n"
                + "  finalizers: []\n"
                + "  managedFields: []\n"
                + "  name: twitter-search-source-binding\n"
                + "  ownerReferences: []\n"
                + "plural: kameletbindings\n"
                + "scope: Namespaced\n"
                + "served: true\n"
                + "singular: kameletbinding\n"
                + "spec:\n"
                + "  source:\n"
                + "    ref:\n"
                + "      apiVersion: camel.apache.org/v1alpha1\n"
                + "      name: twitter-search-source\n"
                + "      kind: Kamelet\n"
                + "    properties:\n"
                + "      keywords: Apache Camel\n"
                + "      apiKey: your own\n"
                + "      apiKeySecret: your own\n"
                + "      accessToken: your own\n"
                + "      accessTokenSecret: your own\n"
                + "  steps:\n"
                + "  - ref:\n"
                + "      apiVersion: camel.apache.org/v1alpha1\n"
                + "      name: aws-translate-action\n"
                + "      kind: Kamelet\n"
                + "  - ref:\n"
                + "      apiVersion: camel.apache.org/v1alpha1\n"
                + "      name: avro-deserialize-action\n"
                + "      kind: Kamelet\n"
                + "  sink:\n"
                + "    ref:\n"
                + "      apiVersion: camel.apache.org/v1alpha1\n"
                + "      name: kafka-sink\n"
                + "      kind: Kamelet\n"
                + "    properties:\n"
                + "      password: The Password\n"
                + "      bootstrapServers: The Brokers\n"
                + "      topic: The Topic Names\n"
                + "      user: The Username\n"
                + "storage: true\n"
                + "version: v1alpha1";

        Assertions.assertFalse(res.isEmpty());
        String result = null;
        for (var crd : res) {
            if ("KameletBinding".equalsIgnoreCase(crd.get("dsl"))) {
                result = crd.get("crd").trim();
            }
        }
        Assertions.assertEquals(expectedStr, result);
    }


    @Test
    void regularCamelConnector() {
        final var steps = stepParser.parse(bindingRegularCamel);
        var res = deploymentService.crd(
                "camel-conector-example",
                steps.toArray(new Step[0]));

        String expectedStr = "apiVersion: camel.apache.org/v1alpha1\n"
                + "group: camel.apache.org\n"
                + "kind: KameletBinding\n"
                + "metadata:\n"
                + "  additionalProperties: {}\n"
                + "  finalizers: []\n"
                + "  managedFields: []\n"
                + "  name: camel-conector-example\n"
                + "  ownerReferences: []\n"
                + "plural: kameletbindings\n"
                + "scope: Namespaced\n"
                + "served: true\n"
                + "singular: kameletbinding\n"
                + "spec:\n"
                + "  steps:\n"
                + "  - uri: log:debug?showBody=true&\n"
                + "  sink:\n"
                + "    uri: log:info?showBody=false&\n"
                + "storage: true\n"
                + "version: v1alpha1";

        Assertions.assertFalse(res.isEmpty());
        String result = null;
        for (var crd : res) {
            if ("KameletBinding".equalsIgnoreCase(crd.get("dsl"))) {
                result = crd.get("crd").trim();
            }
        }
        Assertions.assertEquals(expectedStr, result);
    }
}
