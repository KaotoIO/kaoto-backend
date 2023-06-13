package io.kaoto.backend.api.service.step.parser.camelroute;

import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.dsl.camelroute.CamelRouteDSLSpecification;
import io.kaoto.backend.api.service.step.parser.StepParserService;
import io.kaoto.backend.model.step.Step;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
class CamelRestDSLStepParserServiceTest {

    @Inject
    CamelRouteDSLSpecification camelRestDSLSpecification;
    @Inject
    StepCatalog catalog;

    @Test
    void identifier() {
        final var identifier = camelRestDSLSpecification.identifier();
        assertNotNull(identifier);
        assertTrue(!identifier.isEmpty());
    }

    @Test
    void description() {
        final var description = camelRestDSLSpecification.description();
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
        assertTrue(camelRestDSLSpecification.getStepParserService().appliesTo(route));
        List<StepParserService.ParseResult<Step>> flows =
                camelRestDSLSpecification.getStepParserService().getParsedFlows(route);
        for (var flow : flows) {
            assertTrue(camelRestDSLSpecification.appliesTo(flow.getSteps()));
            assertTrue(flow.getParameters() == null || flow.getParameters().isEmpty());
            assertTrue(flow.getMetadata() == null || flow.getMetadata().isEmpty());
            assertFalse(flow.getSteps().isEmpty());
        }
        var yaml = camelRestDSLSpecification.getDeploymentGeneratorService().parse(flows);
        assertThat(yaml).isEqualToNormalizingNewlines(route);
        assertTrue(camelRestDSLSpecification.getDeploymentGeneratorService().supportedCustomResources().isEmpty());
    }
}
