package io.kaoto.backend.api.service.step.parser.camelroute;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.deployment.generator.camelroute.CamelRouteDeploymentGeneratorService;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@QuarkusTest
class CamelRouteStepParserServiceTest {

    @Inject
    CamelRouteStepParserService camelRouteStepParserService;
    @Inject
    CamelRouteDeploymentGeneratorService camelRouteDeploymentGeneratorService;
    @Inject
    StepCatalog catalog;

    @Test
    void identifier() {
        final var identifier = camelRouteStepParserService.identifier();
        assertNotNull(identifier);
        assertTrue(!identifier.isEmpty());
    }

    @Test
    void description() {
        final var description = camelRouteStepParserService.description();
        assertNotNull(description);
        assertTrue(!description.isEmpty());
    }

    @BeforeEach
    void ensureCatalog() {
        catalog.waitForWarmUp().join();
    }

    @Test
    void deepParse() throws IOException {
        var route = new String(this.getClass().getResourceAsStream("route.yaml").readAllBytes(),
                StandardCharsets.UTF_8);
        assertTrue(camelRouteStepParserService.appliesTo(route));
        var steps = camelRouteStepParserService.deepParse(route);
        assertTrue(camelRouteDeploymentGeneratorService.appliesTo(steps.getSteps()));
        assertTrue(steps.getParameters() == null || steps.getParameters().isEmpty());
        assertTrue(steps.getMetadata() == null || steps.getMetadata().isEmpty());
        assertFalse(steps.getSteps().isEmpty());

        var yaml = camelRouteDeploymentGeneratorService.parse(steps.getSteps(), steps.getMetadata(),
                steps.getParameters());
        assertThat(yaml).isEqualToNormalizingNewlines(route);
        assertTrue(camelRouteDeploymentGeneratorService.supportedCustomResources().isEmpty());
    }


    @ParameterizedTest
    @ValueSource(strings = {"route.yaml", "route2-complex-expressions.yaml", "route3-complex-expressions.yaml"})
    void deepParseParametrized(String file) throws IOException {
        var route = new String(this.getClass().getResourceAsStream(file).readAllBytes(),
                StandardCharsets.UTF_8);
        assertTrue(camelRouteStepParserService.appliesTo(route));
        var steps = camelRouteStepParserService.deepParse(route);
        assertTrue(camelRouteDeploymentGeneratorService.appliesTo(steps.getSteps()));
        assertTrue(steps.getParameters() == null || steps.getParameters().isEmpty());
        assertTrue(steps.getMetadata() == null || steps.getMetadata().isEmpty());
        assertFalse(steps.getSteps().isEmpty());

        var yaml = camelRouteDeploymentGeneratorService.parse(steps.getSteps(), steps.getMetadata(),
                steps.getParameters());
        assertThat(yaml).isEqualToNormalizingNewlines(route);
        assertTrue(camelRouteDeploymentGeneratorService.supportedCustomResources().isEmpty());
    }


    @Test
    @Disabled("Until we support full expressions in conditionals")
    void compareCamelAndKebabCases() throws IOException {
        var route = new String(this.getClass().getResourceAsStream("route2-complex-expressions.yaml").readAllBytes(),
                StandardCharsets.UTF_8);
        var routeb = new String(this.getClass().getResourceAsStream("route2b.yaml").readAllBytes(),
                StandardCharsets.UTF_8);

        var steps = camelRouteStepParserService.deepParse(route);
        var stepsb = camelRouteStepParserService.deepParse(routeb);

        assertEquals(steps, stepsb);
    }
}
