package io.kaoto.backend.metadata.parser.step.camelRoute;

import io.kaoto.backend.metadata.ParseCatalog;
import io.kaoto.backend.metadata.catalog.InMemoryCatalog;
import io.kaoto.backend.model.parameter.BooleanParameter;
import io.kaoto.backend.model.parameter.ObjectParameter;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.parameter.StringParameter;
import io.kaoto.backend.model.step.Step;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class CamelRouteParseCatalogTest {

    @Inject
    public void setParseCatalog(final CamelRouteParseCatalog parseCatalog) {
        this.parseCatalog = parseCatalog;
    }
    private CamelRouteParseCatalog parseCatalog;

    @Test
    void shouldLoadFromGit() {
        String camelGit = "https://github.com/apache/camel.git";
        String tag = "camel-3.18.2";
        InMemoryCatalog<Step> catalog = new InMemoryCatalog<>();

        ParseCatalog<Step> camelParser =
                parseCatalog.getParser(camelGit, tag);
        List<Step> steps = camelParser.parse().join();

        assertTrue(catalog.store(steps));

        Step browseComponentSink = catalog.searchStepByID("browse-sink");
        Step browseComponentSource = catalog.searchStepByID("browse-source");
        Step browseComponentAction = catalog.searchStepByID("browse-action");

        assertBrowseJsonHasBeenParsedCorrectly(
                browseComponentSource, "source", false);
        assertBrowseJsonHasBeenParsedCorrectly(
                browseComponentSink, "sink", false);
        assertBrowseJsonHasBeenParsedCorrectly(
                browseComponentAction, "action", false);
    }

    @Test
    void shouldLoadFromJar() {
        String camelGit = "https://github.com/apache/camel/"
                + "archive/refs/tags/camel-3.18.2.zip";
        InMemoryCatalog<Step> catalog = new InMemoryCatalog<>();

        ParseCatalog<Step> camelParser =
                parseCatalog.getParser(camelGit);
        List<Step> steps = camelParser.parse().join();

        assertTrue(catalog.store(steps));

        Step browseComponentSink = catalog.searchStepByID("browse-sink");
        Step browseComponentSource = catalog.searchStepByID("browse-source");
        Step browseComponentAction = catalog.searchStepByID("browse-action");

        assertBrowseJsonHasBeenParsedCorrectly(
                browseComponentSource, "source", false);
        assertBrowseJsonHasBeenParsedCorrectly(
                browseComponentSink, "sink", false);
        assertBrowseJsonHasBeenParsedCorrectly(
                browseComponentAction, "action", false);
    }

    @Test
    void shouldLoadFromLocalFolder() throws URISyntaxException {
        Path camelJsonRoute = Path.of(
                CamelRouteFileProcessorTest.class.getResource(
                        ".").toURI());

        ParseCatalog<Step> camelParser =
                parseCatalog.getLocalFolder(camelJsonRoute);
        List<Step> steps = camelParser.parse().join().stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        assertEquals(3, steps.size());

        BiFunction<List<Step>, String, Step> fetchBrowse =
                (stepList, stepType) -> stepList
                        .stream().filter(
                                step -> stepType.equals(step.getType())
                        ).findFirst().get();

        Step browseComponentSink = fetchBrowse.apply(steps, "sink");
        Step browseComponentSource = fetchBrowse.apply(steps, "source");
        Step browseComponentAction = fetchBrowse.apply(steps, "action");

        assertBrowseJsonHasBeenParsedCorrectly(
                browseComponentSink, "sink", false);
        assertBrowseJsonHasBeenParsedCorrectly(
                browseComponentSource, "source", false);
        assertBrowseJsonHasBeenParsedCorrectly(
                browseComponentAction, "action", false);
    }

    private void assertBrowseJsonHasBeenParsedCorrectly(
            final Step parsedStep, final String type,
            final boolean assertDescription) {
        assertEquals("browse-" + type, parsedStep.getId());
        assertEquals("browse-" + type, parsedStep.getName());
        assertEquals("Browse", parsedStep.getTitle());
        assertEquals("Inspect the messages received"
                        + " on endpoints supporting BrowsableEndpoint.",
                parsedStep.getDescription());
        assertEquals(type, parsedStep.getType());
        assertIterableEquals(List.of("name"), parsedStep.getRequired());

        Map<String, Parameter> expectedParameterValues = Map.of(
                "name", new StringParameter(
                        "name", "Name", "d1",
                        null, null),
                "bridgeErrorHandler", new BooleanParameter(
                        "bridgeErrorHandler", "Bridge Error Handler",
                        "d2", false),
                "exceptionHandler", new ObjectParameter(
                        "exceptionHandler", "Exception Handler",
                        "d3", null),
                "exchangePattern", new ObjectParameter(
                        "exchangePattern", "Exchange Pattern",
                        "d4", null),
                "lazyStartProducer", new BooleanParameter(
                        "lazyStartProducer", "Lazy Start Producer",
                        "d5", false)
        );

        expectedParameterValues.get("name").setPath(true);

        parsedStep.getParameters().forEach(
                parameter -> {
                    assertEquals(parameter.getId(),
                            expectedParameterValues.get(parameter.getId())
                                    .getId());
                    assertEquals(parameter.isPath(),
                            expectedParameterValues.get(parameter.getId())
                                    .isPath());
                    assertEquals(parameter.getTitle(),
                            expectedParameterValues.get(parameter.getId())
                                    .getTitle());
                    assertEquals(parameter.getDefaultValue(),
                            expectedParameterValues.get(parameter.getId())
                                    .getDefaultValue());
                    if (assertDescription) {
                        assertEquals(parameter.getDescription(),
                                expectedParameterValues.get(parameter.getId())
                                        .getDescription());
                    }
                }
        );
    }
}
