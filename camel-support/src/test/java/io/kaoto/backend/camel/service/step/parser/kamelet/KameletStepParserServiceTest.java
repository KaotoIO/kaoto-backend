package io.kaoto.backend.camel.service.step.parser.kamelet;

import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.camel.KamelHelper;
import io.kaoto.backend.camel.service.dsl.kamelet.KameletDSLSpecification;
import io.kaoto.backend.api.service.step.parser.StepParserService;
import io.kaoto.backend.metadata.ParseCatalog;
import io.kaoto.backend.metadata.catalog.CatalogCollection;
import io.kaoto.backend.metadata.catalog.InMemoryCatalog;
import io.kaoto.backend.camel.metadata.parser.step.camelroute.CamelRouteParseCatalog;
import io.kaoto.backend.camel.model.deployment.kamelet.Bean;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;
import io.quarkus.test.junit.QuarkusTest;
import org.apache.camel.v1alpha1.kameletspec.Definition;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class KameletStepParserServiceTest {

    private static String kamelet;
    private static String incomplete;
    private static String beansKamelet;

    private CamelRouteParseCatalog parseCatalog;
    @Inject
    private KameletDSLSpecification dslSpecification;

    private StepCatalog catalog;

    @Inject
    public void setStepCatalog(final StepCatalog catalog) {
        this.catalog = catalog;
    }

    @Inject
    public void setParseCatalog(final CamelRouteParseCatalog parseCatalog) {
        this.parseCatalog = parseCatalog;
    }

    @BeforeAll
    static void setup() throws URISyntaxException, IOException {
        kamelet = Files.readString(Path.of(
                KameletBindingStepParserServiceTest.class.getResource("dropbox-sink.kamelet.yaml").toURI()));
        incomplete = Files.readString(Path.of(
                KameletBindingStepParserServiceTest.class.getResource("dropbox-sink.kamelet-incomplete.yaml")
                        .toURI()));
        beansKamelet = Files.readString(Path.of(
                KameletBindingStepParserServiceTest.class.getResource("beans.kamelet.yaml").toURI()));
    }

    @BeforeEach
    void ensureCatalog() {
        catalog.waitForWarmUp().join();

        if (catalog.getReadOnlyCatalog().searchByID("log-producer") == null) {
            String camelGit = "https://github.com/apache/camel/"
                    + "archive/refs/tags/camel-3.18.2.zip";

            ParseCatalog<Step> camelParser = parseCatalog.getParser(camelGit);
            List<Step> steps = camelParser.parse().join();
            InMemoryCatalog<Step> c2 = new InMemoryCatalog<>();
            c2.store(steps);
            c2.store((List<Step>) catalog.getReadOnlyCatalog().getAll());
            ((CatalogCollection) catalog.getReadOnlyCatalog()).addCatalog(c2);
        }
    }

    @Test
    void deepParse() {
        StepParserService.ParseResult<Step> parsed = dslSpecification.getStepParserService().deepParse(kamelet);
        assertNotNull(parsed);

        assertNotNull(parsed.getMetadata());
        assertFalse(parsed.getMetadata().isEmpty());
        Map<String, String> labels = (Map<String, String>) parsed.getMetadata().get("labels");
        Map<String, String> annotations = (Map<String, String>) parsed.getMetadata().get("annotations");
        assertEquals("Apache Software Foundation", annotations.get("camel.apache.org/provider"));
        assertEquals("Preview", annotations.get("camel.apache.org/kamelet.support.level"));
        assertEquals("sink", labels.get("camel.apache.org/kamelet.type"));
        assertEquals("Dropbox", annotations.get("camel.apache.org/kamelet.group"));
        assertEquals("dropbox-sink", parsed.getMetadata().get(KamelHelper.NAME));

        assertNotNull(parsed.getSteps());
        assertFalse(parsed.getSteps().isEmpty());
        assertTrue(parsed.getSteps().stream().allMatch(s -> s != null));
        assertEquals(5, parsed.getSteps().size());

        final var fromStep = parsed.getSteps().get(0);
        assertEquals("kamelet:source", fromStep.getName());

        final var choiceStep = parsed.getSteps().get(1);
        assertEquals("choice", choiceStep.getName());
        assertNotNull(choiceStep.getBranches());
        assertEquals(2, choiceStep.getBranches().size());

        final var amqStep = parsed.getSteps().get(2);
        for (Parameter p : amqStep.getParameters()) {
            if (p.getId().equalsIgnoreCase("destinationType")) {
                assertEquals("queue", p.getValue());
            } else if (p.getId().equalsIgnoreCase("destinationName")) {
                assertEquals("HELLO.WORLD", p.getValue());
            }
        }

        final var avroStep = parsed.getSteps().get(3);
        for (Parameter p : avroStep.getParameters()) {
            if (p.getId().equalsIgnoreCase("host")) {
                assertEquals("hostname", p.getValue());
            } else if (p.getId().equalsIgnoreCase("transport")) {
                assertEquals("https", p.getValue());
            } else if (p.getId().equalsIgnoreCase("messageName")) {
                assertEquals("message", p.getValue());
            } else if (p.getId().equalsIgnoreCase("port")) {
                assertEquals(999l, p.getValue());
            }
        }

        final var dropboxStep = parsed.getSteps().get(4);
        assertEquals("dropbox-action", dropboxStep.getId());

        Definition definition = (Definition) parsed.getMetadata().get("definition");

        assertNotNull(definition);

        assertEquals("Dropbox Sink", definition.getTitle());
        assertNotNull(definition.getProperties());
        assertFalse(definition.getProperties().isEmpty());
        assertEquals(4, definition.getProperties().size());
        assertTrue(definition.getProperties().containsKey("accessToken"));
        var accessToken = definition.getProperties().get("accessToken");
        assertEquals("Dropbox Access Token", accessToken.getTitle());
        assertEquals("The access Token to use to access Dropbox",
                accessToken.getDescription());
        assertEquals("string", accessToken.getType());
    }

    @ParameterizedTest
    @ValueSource(strings = {"twitter-search-source-binding.yaml", "invalid/route.yaml",
            "invalid/integration.yaml"})
    void deepParseInvalid(String resourcePath) throws IOException {
        String input = new String(Objects.requireNonNull(this.getClass().getResourceAsStream(resourcePath))
                .readAllBytes(), StandardCharsets.UTF_8);
        assertThatThrownBy(() -> dslSpecification.getStepParserService().deepParse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Wrong format provided. This is not parseable by us.");
    }

    @Test
    void parseWithBeans() {
        List<StepParserService.ParseResult<Step>> parsed =
                dslSpecification.getStepParserService().getParsedFlows(beansKamelet);
        assertEquals(2, parsed.size());
        var meta = parsed.get(0);
        assertThat(meta.getSteps()).isNull();
        List<Bean> beans = (List<Bean>) meta.getMetadata().get("beans");
        assertThat(beans).hasSize(1);
        assertThat(beans.get(0).getName()).isEqualTo("connectionFactoryBean");
        assertThat(beans.get(0).getType()).isEqualTo("#class:org.apache.qpid.jms.JmsConnectionFactory");
        var beanProps = (Map<String, Object>) beans.get(0).getProperties();
        assertThat(beanProps)
                .hasSize(2)
                .containsEntry("remoteURI", "{{remoteURI}}")
                .containsEntry("secondProperty", "another");
        var route = parsed.get(1);
        assertThat(route.getSteps())
                .hasSize(2)
                .extracting(Step::getName)
                .containsExactly("jms", "kamelet:sink");
        assertThat(route.getMetadata()).doesNotContainKey("beans");
        var yaml = dslSpecification.getDeploymentGeneratorService().parse(parsed);
        assertThat(yaml).isEqualToNormalizingWhitespace(beansKamelet);
    }

    @Test
    void goAndBackAgain() {
        var parsed = dslSpecification.getStepParserService().deepParse(kamelet);
        assertTrue(!parsed.getSteps().isEmpty(), "Something got broken: we didn't get any steps from the kamelet.");
        assertEquals(5, parsed.getSteps().size());
        String output = dslSpecification.getDeploymentGeneratorService().parse(parsed.getSteps(),
                parsed.getMetadata(), parsed.getParameters());
        var parsed2 = dslSpecification.getStepParserService().deepParse(output);
        assertEquals(parsed.getSteps(), parsed2.getSteps());
        assertEquals(parsed.getMetadata().keySet(), parsed2.getMetadata().keySet());
        assertTrue(parsed.getMetadata().containsKey("labels"));
        assertTrue(parsed.getMetadata().containsKey("annotations"));
        assertTrue(parsed.getMetadata().containsKey("additionalProperties"));
        assertTrue(parsed.getMetadata().containsKey(KamelHelper.NAME));
        for (String key : new String[]{"labels", "annotations", "additionalProperties", KamelHelper.NAME}) {
            assertEquals(parsed.getMetadata().get(key), parsed2.getMetadata().get(key));
        }

        var parsedInc = dslSpecification.getStepParserService().deepParse(incomplete);
        String outputInc = dslSpecification.getDeploymentGeneratorService().parse(parsedInc.getSteps(),
                parsedInc.getMetadata(), parsed.getParameters());
        var parsed2Inc = dslSpecification.getStepParserService().deepParse(outputInc);
        assertEquals(parsedInc.getSteps(), parsed2Inc.getSteps());
    }

    @Test
    void appliesTo() {
        assertTrue(dslSpecification.appliesTo(kamelet));
        assertTrue(dslSpecification.appliesTo(incomplete));
        assertTrue(dslSpecification.appliesTo(dslSpecification.getStepParserService().deepParse(kamelet).getSteps()));
        assertTrue(
                dslSpecification.appliesTo(dslSpecification.getStepParserService().deepParse(incomplete).getSteps()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"twitter-search-source-binding.yaml", "invalid/route.yaml",
            "invalid/integration.yaml"})
    void appliesToInvalid(String resourcePath) throws IOException {
        String input = new String(Objects.requireNonNull(this.getClass().getResourceAsStream(resourcePath))
                .readAllBytes(), StandardCharsets.UTF_8);
        assertThat(dslSpecification.appliesTo(input)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"eip.kamelet.yaml", "jq.kamelet.yaml", "name.kamelet.yaml",
            "kafka-manual-commit-action.kamelet.yaml"})
    void checkRoundTrip(String resource) throws Exception {
        String kamelet = new String(Objects.requireNonNull(this.getClass().getResourceAsStream(resource))
                .readAllBytes(), StandardCharsets.UTF_8);
        assertTrue(dslSpecification.appliesTo(kamelet));
        final var parsed = dslSpecification.getStepParserService().deepParse(kamelet);
        assertNotNull(parsed);
        assertTrue(dslSpecification.appliesTo(parsed.getSteps()));
        String parsedString = dslSpecification.getDeploymentGeneratorService()
                .parse(parsed.getSteps(), parsed.getMetadata(),
                parsed.getParameters());
        assertThat(parsedString).isEqualToNormalizingNewlines(kamelet);
    }

    @Test
    void checkIdNameTitleDescription() throws Exception {
        String kamelet = new String(Objects.requireNonNull(
                this.getClass().getResourceAsStream("id-name-desc.kamelet.yaml"))
                .readAllBytes(), StandardCharsets.UTF_8);
        final List<StepParserService.ParseResult<Step>> parsedList
                = dslSpecification.getStepParserService().getParsedFlows(kamelet);
        assertThat(parsedList).hasSize(2);
        var parsedMeta = parsedList.get(0);
        assertThat(parsedMeta.getSteps()).isNull();
        var metadata = parsedMeta.getMetadata();
        assertThat(metadata).containsEntry(KamelHelper.NAME, "test-kamelet")
                .containsEntry(KamelHelper.DESCRIPTION, "metadata annotations kaoto.io/description");
        Definition definition = (Definition) metadata.get("definition");
        assertThat(definition.getTitle()).isEqualTo("test-kamelet definition title");
        assertThat(definition.getDescription()).isEqualTo("test-kamelet definition description");
        var parsedFlow = parsedList.get(1);
        assertNotNull(parsedFlow);
        assertTrue(dslSpecification.appliesTo(parsedFlow.getSteps()));
        var flowMeta = parsedFlow.getMetadata();
        assertThat(flowMeta).containsEntry(KamelHelper.NAME, "test-kamelet")
                .doesNotContainKey(KamelHelper.DESCRIPTION)
                .containsEntry("template-id", "test-kamelet-template-id")
                .containsEntry("template-description", "test-kamelet template description")
                .containsEntry("route-id", "test-kamelet-template-route-id")
                .containsEntry("route-description", "test-kamelet template route description");
        String parsedString = dslSpecification.getDeploymentGeneratorService().parse(parsedList);
        assertThat(parsedString).isEqualToNormalizingNewlines(kamelet);
    }

    @Test
    void checkCopyTitleAndDescription() throws Exception {
        String kamelet = new String(Objects.requireNonNull(
                        this.getClass().getResourceAsStream("copy-title-desc.kamelet.yaml"))
                .readAllBytes(), StandardCharsets.UTF_8);
        List<StepParserService.ParseResult<Step>> parsedList
                = dslSpecification.getStepParserService().getParsedFlows(kamelet);
        String parsedString = dslSpecification.getDeploymentGeneratorService().parse(parsedList);
        assertThat(parsedString).isEqualToNormalizingNewlines(kamelet);

        parsedList.get(0).getMetadata().remove(KamelHelper.NAME);
        parsedList.get(0).getMetadata().remove(KamelHelper.DESCRIPTION);
        parsedString = dslSpecification.getDeploymentGeneratorService().parse(parsedList);
        assertThat(parsedString).isEqualToNormalizingNewlines(kamelet);

        parsedList = dslSpecification.getStepParserService().getParsedFlows(kamelet);
        Definition definition = (Definition) parsedList.get(0).getMetadata().get("definition");
        definition.setTitle(null);
        definition.setDescription(null);;
        parsedString = dslSpecification.getDeploymentGeneratorService().parse(parsedList);
        assertThat(parsedString).isEqualToNormalizingNewlines(kamelet);
    }
}
