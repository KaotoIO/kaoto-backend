package io.kaoto.backend.api.service.step.parser.kamelet;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.deployment.generator.kamelet.KameletDeploymentGeneratorService;
import io.kaoto.backend.metadata.ParseCatalog;
import io.kaoto.backend.metadata.catalog.CatalogCollection;
import io.kaoto.backend.metadata.catalog.InMemoryCatalog;
import io.kaoto.backend.metadata.parser.step.camelroute.CamelRouteParseCatalog;
import io.kaoto.backend.model.deployment.kamelet.KameletDefinition;
import io.kaoto.backend.model.deployment.kamelet.KameletDefinitionProperty;
import io.kaoto.backend.model.parameter.Parameter;
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
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class KameletStepParserServiceTest {

    private static String kamelet;
    private static String incomplete;
    private static String kameletEIP;
    private static String kameletJq;
    private static String multiKamelet;

    private CamelRouteParseCatalog parseCatalog;
    @Inject
    private KameletStepParserService service;

    @Inject
    private KameletDeploymentGeneratorService deploymentService;

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
                KameletBindingStepParserServiceTest.class.getResource(
                                "dropbox-sink.kamelet.yaml")
                        .toURI()));
        incomplete = Files.readString(Path.of(
                KameletBindingStepParserServiceTest.class.getResource(
                                "dropbox-sink.kamelet-incomplete.yaml")
                        .toURI()));
        kameletEIP = Files.readString(Path.of(
                KameletBindingStepParserServiceTest.class.getResource(
                                "eip.kamelet.yaml")
                        .toURI()));
        kameletJq = Files.readString(Path.of(
                KameletBindingStepParserServiceTest.class.getResource(
                                "jq.kamelet.yaml")
                        .toURI()));
        multiKamelet = Files.readString(Path.of(
                KameletBindingStepParserServiceTest.class.getResource(
                                "multi-kamelets.yaml")
                        .toURI()));
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
        var parsed = service.deepParse(kamelet);
        assertNotNull(parsed);

        assertNotNull(parsed.getMetadata());
        assertFalse(parsed.getMetadata().isEmpty());
        Map<String, String> labels =
                (Map<String, String>) parsed.getMetadata().get("labels");
        Map<String, String> annotations =
                (Map<String, String>) parsed.getMetadata().get("annotations");
        assertEquals("Apache Software Foundation",
                annotations.get("camel.apache.org/provider"));
        assertEquals("Preview",
                annotations.get(
                        "camel.apache.org/kamelet.support.level"));
        assertEquals("sink",
                labels.get(
                        "camel.apache.org/kamelet.type"));
        assertEquals("Dropbox",
                annotations.get(
                        "camel.apache.org/kamelet.group"));
        assertEquals("dropbox-sink", parsed.getMetadata().get("name"));

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

        KameletDefinition definition =
                (KameletDefinition) parsed.getMetadata().get("definition");

        assertNotNull(definition);

        assertEquals("Dropbox Sink", definition.getTitle());
        assertNotNull(definition.getProperties());
        assertFalse(definition.getProperties().isEmpty());
        assertEquals(4, definition.getProperties().size());
        assertTrue(definition.getProperties().containsKey("accessToken"));
        KameletDefinitionProperty accessToken =
                definition.getProperties().get("accessToken");
        assertEquals("Dropbox Access Token", accessToken.getTitle());
        assertEquals("The access Token to use to access Dropbox",
                accessToken.getDescription());
        assertEquals("string", accessToken.getType());
    }

    @Test
    void parseMultipleFlows() throws JsonProcessingException {
        var parsed = service.getParsedFlows(multiKamelet);
        assertEquals(2, parsed.size());
        var yaml = deploymentService.parse(parsed);

        assertThat(yaml).isEqualToNormalizingWhitespace(multiKamelet);
    }

    @Test
    void goAndBackAgain() {
        var parsed = service.deepParse(kamelet);
        String output = deploymentService.parse(parsed.getSteps(),
                parsed.getMetadata(), parsed.getParameters());
        var parsed2 = service.deepParse(output);
        assertEquals(parsed.getSteps(), parsed2.getSteps());
        assertEquals(parsed.getMetadata().keySet(),
                parsed2.getMetadata().keySet());
        for (String key : new String[]{"labels", "annotations", "additionalProperties", "name"}) {
            assertEquals(parsed.getMetadata().get(key),
                    parsed2.getMetadata().get(key));
        }

        var parsedInc = service.deepParse(incomplete);
        String outputInc = deploymentService.parse(parsedInc.getSteps(),
                parsedInc.getMetadata(), parsed.getParameters());
        var parsed2Inc = service.deepParse(outputInc);
        assertEquals(parsedInc.getSteps(), parsed2Inc.getSteps());
    }

    @Test
    void appliesTo() {
        assertTrue(service.appliesTo(kamelet));
        assertTrue(service.appliesTo(incomplete));
        assertTrue(
                deploymentService.appliesTo(
                        service.deepParse(kamelet).getSteps()));
        assertTrue(
                deploymentService.appliesTo(
                        service.deepParse(incomplete).getSteps()));
    }

    @Test
    void checkEIP() {
        assertTrue(service.appliesTo(kameletEIP));
        final var parsed = service.deepParse(kameletEIP);
        assertNotNull(parsed);
        assertTrue(deploymentService.appliesTo(parsed.getSteps()));
        String parsedString = deploymentService.parse(parsed.getSteps(), parsed.getMetadata(), parsed.getParameters());
        assertThat(parsedString).isEqualToNormalizingNewlines(kameletEIP);
    }

    @Test
    void checkJq() {
        assertTrue(service.appliesTo(kameletJq));
        final var parsed = service.deepParse(kameletJq);
        assertNotNull(parsed);
        assertTrue(deploymentService.appliesTo(parsed.getSteps()));
        String parsedString = deploymentService.parse(parsed.getSteps(), parsed.getMetadata(), parsed.getParameters());
        assertThat(parsedString).isEqualToNormalizingNewlines(kameletJq);
    }

    @Test
    void fail() {
        //include here potential security issues
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.deepParse("Kind: Kamelet\n But not really");
        });
        assertFalse(service.appliesTo("Malformed YAML"));
    }
}
