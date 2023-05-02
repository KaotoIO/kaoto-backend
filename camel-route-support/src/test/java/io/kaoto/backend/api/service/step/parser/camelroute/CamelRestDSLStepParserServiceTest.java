package io.kaoto.backend.api.service.step.parser.camelroute;

import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.deployment.generator.camelroute.CamelRouteDeploymentGeneratorService;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
class CamelRestDSLStepParserServiceTest {

    @Inject
    CamelRouteStepParserService camelRestDSLStepParserService;
    @Inject
    CamelRouteDeploymentGeneratorService camelRestDSLDeploymentGeneratorService;
    @Inject
    StepCatalog catalog;

    @Test
    void identifier() {
        final var identifier = camelRestDSLStepParserService.identifier();
        assertNotNull(identifier);
        assertTrue(!identifier.isEmpty());
    }

    @Test
    void description() {
        final var description = camelRestDSLStepParserService.description();
        assertNotNull(description);
        assertTrue(!description.isEmpty());
    }

    @BeforeEach
    void ensureCatalog() {
        catalog.waitForWarmUp().join();
    }

    @Test
    void deepParse() throws IOException {
        var route = new String(this.getClass().getResourceAsStream("rest-dsl.yaml").readAllBytes(),
                StandardCharsets.UTF_8);
        assertTrue(camelRestDSLStepParserService.appliesTo(route));
        var flows = camelRestDSLStepParserService.getParsedFlows(route);
        for (var flow : flows) {
            assertTrue(camelRestDSLDeploymentGeneratorService.appliesTo(flow.getSteps()));
            assertTrue(flow.getParameters() == null || flow.getParameters().isEmpty());
            assertTrue(flow.getMetadata() == null || flow.getMetadata().isEmpty());
            assertFalse(flow.getSteps().isEmpty());
        }
        var yaml = camelRestDSLDeploymentGeneratorService.parse(flows);
        assertThat(yaml).isEqualToNormalizingNewlines(route);
        assertTrue(camelRestDSLDeploymentGeneratorService.supportedCustomResources().isEmpty());
    }
}
