package io.kaoto.backend.api.service.deployment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.metadata.catalog.ViewDefinitionCatalog;
import io.kaoto.backend.api.resource.v1.model.Integration;
import io.kaoto.backend.camel.KamelHelper;
import io.kaoto.backend.camel.service.step.parser.kamelet.KameletBindingStepParserService;
import io.kaoto.backend.api.service.viewdefinition.ViewDefinitionService;
import io.kaoto.backend.camel.model.deployment.kamelet.KameletBinding;
import io.kaoto.backend.model.step.Step;
import io.quarkus.test.junit.QuarkusTest;

import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void yaml() throws JsonProcessingException {
        final var steps = stepParser.deepParse(binding).getSteps();
        var res = deploymentService.crd(
                "twitter-search-source-binding",
                steps.toArray(new Step[0]));

        String expectedStr = "apiVersion: camel.apache.org/v1alpha1\n"
                + "group: camel.apache.org\n"
                + "kind: KameletBinding\n"
                + "metadata:\n"
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
                + "    properties:\n"
                + "      accessToken: your own\n"
                + "      accessTokenSecret: your own\n"
                + "      apiKey: your own\n"
                + "      apiKeySecret: your own\n"
                + "      keywords: Apache Camel\n"
                + "    ref:\n"
                + "      apiVersion: camel.apache.org/v1alpha1\n"
                + "      name: twitter-search-source\n"
                + "      kind: Kamelet\n"
                + "    types: {}\n"
                + "  steps:\n"
                + "  - properties: {}\n"
                + "    ref:\n"
                + "      apiVersion: camel.apache.org/v1alpha1\n"
                + "      name: aws-translate-action\n"
                + "      kind: Kamelet\n"
                + "    types: {}\n"
                + "  - properties: {}\n"
                + "    ref:\n"
                + "      apiVersion: camel.apache.org/v1alpha1\n"
                + "      name: avro-deserialize-action\n"
                + "      kind: Kamelet\n"
                + "    types: {}\n"
                + "  sink:\n"
                + "    properties:\n"
                + "      bootstrapServers: The Brokers\n"
                + "      password: The Password\n"
                + "      topic: The Topic Names\n"
                + "      user: The Username\n"
                + "    ref:\n"
                + "      apiVersion: camel.apache.org/v1alpha1\n"
                + "      name: kafka-sink\n"
                + "      kind: Kamelet\n"
                + "    types: {}\n"
                + "storage: true\n"
                + "version: v1alpha1";

        Assertions.assertFalse(res.isEmpty());
        String result = null;
        for (var crd : res) {
            if ("KameletBinding".equalsIgnoreCase(crd.get("dsl"))) {
                result = crd.get("crd").trim();
            }
        }

        KameletBinding res1 = KamelHelper.YAML_MAPPER.readValue(expectedStr, KameletBinding.class);
        KameletBinding res2 = KamelHelper.YAML_MAPPER.readValue(result, KameletBinding.class);

        //spec does not have a good equals function, so we have to check manually
        assertEquals(res1.getMetadata(), res2.getMetadata());
        assertEquals(res1.getKind(), res2.getKind());

        //the step objects also don't have good equals functions
        assertEquals(res1.getSpec().getSink().getUri(), res2.getSpec().getSink().getUri());
        assertEquals(res1.getSpec().getSink().getRef().getFieldPath(),
                res2.getSpec().getSink().getRef().getFieldPath());
        assertEquals(res1.getSpec().getSink().getTypes(), res2.getSpec().getSink().getTypes());
        assertEquals(res1.getSpec().getSource().getUri(), res2.getSpec().getSource().getUri());
        assertEquals(res1.getSpec().getSource().getRef().getFieldPath(),
                res2.getSpec().getSource().getRef().getFieldPath());
        assertEquals(res1.getSpec().getSource().getTypes(), res2.getSpec().getSource().getTypes());
        assertEquals(res1.getSpec().getSteps().size(), res2.getSpec().getSteps().size());

    }


    @Disabled("Disabled until camel-connector aren't supported again in KameletBindings")
    @Test
    void regularCamelConnector() {
        final var steps = stepParser.deepParse(bindingRegularCamel).getSteps();
        var res = deploymentService.crd(
                "camel-conector-example",
                steps.toArray(new Step[0]));

        Assertions.assertFalse(res.isEmpty());
        String result = null;
        for (var crd : res) {
            if ("KameletBinding".equalsIgnoreCase(crd.get("dsl"))) {
                result = crd.get("crd").trim();
            }
        }
        Assertions.assertEquals(bindingRegularCamel.trim(), result);
    }

    @Test
    void choiceWithNullIdentifiedBranches() throws IOException {

        var yaml = new String(this.getClass().getResourceAsStream("route-choice-null-condition.yaml").readAllBytes(),
                StandardCharsets.UTF_8);
        var json = new String(this.getClass().getResourceAsStream("route-choice-null-condition.json").readAllBytes(),
                StandardCharsets.UTF_8);

        Integration parsed = KamelHelper.YAML_MAPPER.readValue(json, new TypeReference<>() {});

        var yaml2 = deploymentService.crd(parsed, "Camel Route");
        assertThat(yaml).isEqualToNormalizingNewlines(yaml2);
    }
}
