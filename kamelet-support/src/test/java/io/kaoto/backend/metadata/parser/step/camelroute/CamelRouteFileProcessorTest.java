package io.kaoto.backend.metadata.parser.step.camelroute;

import io.kaoto.backend.model.parameter.BooleanParameter;
import io.kaoto.backend.model.parameter.ObjectParameter;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.parameter.StringParameter;
import io.kaoto.backend.model.step.Step;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;


@QuarkusTest
class CamelRouteFileProcessorTest {
    @Test
    void shouldParseCamelRouteJson() throws URISyntaxException {

        File camelRouteJson = Path.of(
                CamelRouteFileProcessorTest.class.getResource(
                                "browse.json").toURI()).toFile();

        List<Step> steps = new CamelRouteFileProcessor()
                .parseFile(camelRouteJson);

        BiFunction<List<Step>, String, Step> fetchBrowse =
                (stepList, stepType) -> stepList
                        .stream().filter(
                                step -> stepType.equals(step.getType())
                        ).findFirst().get();

        Step browseComponentSink = fetchBrowse.apply(steps, "END");
        Step browseComponentSource = fetchBrowse.apply(steps, "START");
        Step browseComponentAction = fetchBrowse.apply(steps, "MIDDLE");

        assertBrowseJsonHasBeenParsedCorrectly(browseComponentSink, "END");
        assertBrowseJsonHasBeenParsedCorrectly(browseComponentSource, "START");
        assertBrowseJsonHasBeenParsedCorrectly(browseComponentAction, "MIDDLE");
    }

    @Test
    void shouldSkipNotCamelRouteJsons() throws URISyntaxException {
        File camelRouteJson = Path.of(
                CamelRouteFileProcessorTest.class.getResource(
                        "not.json").toURI()).toFile();

        List<Step> parsedStep = new CamelRouteFileProcessor()
                .parseFile(camelRouteJson);

        assertTrue(parsedStep.isEmpty());
    }

    private void assertBrowseJsonHasBeenParsedCorrectly(
            final Step parsedStep, final String type) {
        Map<String, String> typeToIdConversion = Map.of(
                "MIDDLE", "action",
                "END", "producer",
                "START", "consumer"
        );

        String expectedType = typeToIdConversion.get(type);

        assertEquals("browse-" + expectedType,
                parsedStep.getId());
        assertEquals("browse", parsedStep.getName());
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
                    assertEquals(parameter.getDescription(),
                            expectedParameterValues.get(parameter.getId())
                                    .getDescription());
                    assertEquals(parameter.getTitle(),
                            expectedParameterValues.get(parameter.getId())
                                    .getTitle());
                    assertEquals(parameter.getDefaultValue(),
                            expectedParameterValues.get(parameter.getId())
                                    .getDefaultValue());
                    assertEquals(parameter.isPath(),
                            expectedParameterValues.get(parameter.getId())
                                    .isPath());
                }
        );
    }

}
