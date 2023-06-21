package io.kaoto.backend.api.service.step.parser.camelroute;

import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.dsl.camelroute.CamelRouteDSLSpecification;
import io.kaoto.backend.api.service.step.parser.StepParserService;
import io.kaoto.backend.model.step.Step;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import jakarta.inject.Inject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
class CamelRouteStepParserServiceTest {

    @Inject
    CamelRouteDSLSpecification camelRouteDSLSpecification;
    @Inject
    StepCatalog catalog;

    @Test
    void identifier() {
        final var identifier = camelRouteDSLSpecification.identifier();
        assertThat(identifier).isNotNull().isNotEmpty();
    }

    @Test
    void description() {
        final var description = camelRouteDSLSpecification.description();
        assertThat(description).isNotNull().isNotEmpty();
    }

    @BeforeEach
    void ensureCatalog() {
        catalog.waitForWarmUp().join();
    }

    @Test
    void deepParse() throws IOException {
        var route = new String(this.getClass().getResourceAsStream("route.yaml").readAllBytes(),
                StandardCharsets.UTF_8);
        assertTrue(camelRouteDSLSpecification.getStepParserService().appliesTo(route));
        var steps = camelRouteDSLSpecification.getStepParserService().deepParse(route);
        assertTrue(camelRouteDSLSpecification.appliesTo(steps.getSteps()));
        assertTrue(steps.getParameters() == null || steps.getParameters().isEmpty());
        assertTrue(steps.getMetadata() == null || steps.getMetadata().isEmpty());
        assertFalse(steps.getSteps().isEmpty());

        Step https = (Step) steps.getSteps().get(3);
        assertTrue(https.getParameters().stream().anyMatch(
                p -> "httpUri".equalsIgnoreCase(p.getId())
                        && "https://mycustom.url:42/with/things".equals(p.getValue())));

        var yaml = camelRouteDSLSpecification.getDeploymentGeneratorService()
                .parse(steps.getSteps(), steps.getMetadata(),
                        steps.getParameters());
        assertThat(yaml).isEqualToNormalizingNewlines(route);
        assertTrue(camelRouteDSLSpecification.getDeploymentGeneratorService().supportedCustomResources().isEmpty());
    }


    @ParameterizedTest
    @ValueSource(strings = {"route.yaml", "route2-complex-expressions.yaml",
            "route3-complex-expressions.yaml", "route-ids.yaml", "route4-pathparams.yaml", "route5-placeholders.yaml",
            "route6-un-marshal.yaml", "route6-kamelet-extraparameters.yaml" })
    void deepParseParametrized(String file) throws IOException {
        var route = new String(this.getClass().getResourceAsStream(file).readAllBytes(),
                StandardCharsets.UTF_8);
        assertTrue(camelRouteDSLSpecification.getStepParserService().appliesTo(route));
        var steps = camelRouteDSLSpecification.getStepParserService().deepParse(route);
        assertTrue(camelRouteDSLSpecification.appliesTo(steps.getSteps()));
        assertTrue(steps.getParameters() == null || steps.getParameters().isEmpty());
        assertTrue(steps.getMetadata() == null || steps.getMetadata().isEmpty());
        assertFalse(steps.getSteps().isEmpty());

        var yaml = camelRouteDSLSpecification.getDeploymentGeneratorService()
                .parse(steps.getSteps(), steps.getMetadata(), steps.getParameters());
        assertThat(yaml).isEqualToNormalizingNewlines(route);
    }

    @ParameterizedTest
    @ValueSource(strings = {"invalid/dropbox-sink.kamelet.yaml", "invalid/twitter-search-source-binding.yaml",
            "integration.yaml"})
    void deepParseInvalid(String resourcePath) throws IOException {
        String input = new String(Objects.requireNonNull(this.getClass().getResourceAsStream(resourcePath))
                .readAllBytes(), StandardCharsets.UTF_8);

        assertThatThrownBy(() -> camelRouteDSLSpecification.getStepParserService().deepParse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Wrong format provided. This is not parseable by us.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"route.yaml", "route2-complex-expressions.yaml", "route-multi.yaml",
            "route3-complex-expressions.yaml", "route-ids.yaml", "route4-pathparams.yaml", "route5-placeholders.yaml",
            "route6-un-marshal.yaml", "route6-kamelet-extraparameters.yaml"})
    void parsedFlows(String file) throws IOException {
        var route = new String(this.getClass().getResourceAsStream(file).readAllBytes(),
                StandardCharsets.UTF_8);
        assertTrue(camelRouteDSLSpecification.getStepParserService().appliesTo(route));
        List<StepParserService.ParseResult<Step>> flows =
                camelRouteDSLSpecification.getStepParserService().getParsedFlows(route);

        for (var flow : flows) {
            assertTrue(camelRouteDSLSpecification.appliesTo(flow.getSteps()));
            assertTrue(flow.getParameters() == null || flow.getParameters().isEmpty());
            assertTrue(flow.getMetadata() == null || flow.getMetadata().isEmpty());
            assertFalse(flow.getSteps().isEmpty());
        }

        assertThat(route)
                .isEqualToNormalizingNewlines(camelRouteDSLSpecification.getDeploymentGeneratorService().parse(flows));
    }

    @ParameterizedTest
    @ValueSource(strings = {"route-with-id.yaml"})
    void parsedFlowsWithMetadata(String file) throws IOException {
        var route = new String(this.getClass().getResourceAsStream(file).readAllBytes(),
                StandardCharsets.UTF_8);
        assertTrue(camelRouteDSLSpecification.getStepParserService().appliesTo(route));
        List<StepParserService.ParseResult<Step>> flows =
                camelRouteDSLSpecification.getStepParserService().getParsedFlows(route);

        for (var flow : flows) {
            assertTrue(camelRouteDSLSpecification.appliesTo(flow.getSteps()));
            assertTrue(flow.getParameters() == null || flow.getParameters().isEmpty());
            assertFalse(flow.getSteps().isEmpty());
        }

        assertThat(route)
                .isEqualToNormalizingNewlines(camelRouteDSLSpecification.getDeploymentGeneratorService().parse(flows));
    }

    @Test
    @Disabled("Until we support full expressions in conditionals")
    void compareCamelAndKebabCases() throws IOException {
        var route = new String(this.getClass().getResourceAsStream("route2-complex-expressions.yaml").readAllBytes(),
                StandardCharsets.UTF_8);
        var routeb = new String(this.getClass().getResourceAsStream("route2b.yaml").readAllBytes(),
                StandardCharsets.UTF_8);

        var steps = camelRouteDSLSpecification.getStepParserService().deepParse(route);
        var stepsb = camelRouteDSLSpecification.getStepParserService().deepParse(routeb);

        assertEquals(steps, stepsb);
    }

    @Test
    void compareLogs() throws IOException {
        var route = new String(this.getClass().getResourceAsStream("route-logs.yaml").readAllBytes(),
                StandardCharsets.UTF_8);
        var routeb = new String(this.getClass().getResourceAsStream("route2-logs.yaml").readAllBytes(),
                StandardCharsets.UTF_8);
        var routec = new String(this.getClass().getResourceAsStream("route3-logs.yaml").readAllBytes(),
                StandardCharsets.UTF_8);

        var steps = camelRouteDSLSpecification.getStepParserService().deepParse(route);
        var stepsb = camelRouteDSLSpecification.getStepParserService().deepParse(routeb);

        assertEquals(steps, stepsb);

        //Now check default properties are removed
        var yaml = camelRouteDSLSpecification.getDeploymentGeneratorService()
                .parse(steps.getSteps(), steps.getMetadata(), steps.getParameters());
        assertThat(yaml).isEqualToNormalizingNewlines(routec);
    }

    @Test
    void beans() throws Exception {
        var route = new String(this.getClass().getResourceAsStream("route-with-beans.yaml").readAllBytes(),
                StandardCharsets.UTF_8);
        var steps = camelRouteDSLSpecification.getStepParserService().getParsedFlows(route);
        var yaml = camelRouteDSLSpecification.getDeploymentGeneratorService().parse(steps);
        assertThat(yaml).isEqualToNormalizingNewlines(route);
    }

    @ParameterizedTest
    @ValueSource(strings = {"invalid/dropbox-sink.kamelet.yaml", "invalid/twitter-search-source-binding.yaml",
            "integration.yaml"})
    void appliesToInvalid(String resourcePath) throws IOException {
        String input = new String(Objects.requireNonNull(this.getClass().getResourceAsStream(resourcePath))
                .readAllBytes(), StandardCharsets.UTF_8);
        assertThat(camelRouteDSLSpecification.getStepParserService().appliesTo(input)).isFalse();
    }
}
