package io.kaoto.backend.api.service.step.parser.camelroute;

import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.deployment.generator.camelroute.CamelRouteDeploymentGeneratorService;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        assertEquals(route, yaml);
        assertTrue(camelRouteDeploymentGeneratorService.supportedCustomResources().isEmpty());
    }
}
