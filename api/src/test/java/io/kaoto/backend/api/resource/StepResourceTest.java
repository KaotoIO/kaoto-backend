package io.kaoto.backend.api.resource;

import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.model.step.Step;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Collection;

@QuarkusTest
@TestHTTPEndpoint(StepResource.class)
class StepResourceTest {

    public static final String INFINISPAN_SOURCE = "infinispan-source";
    private StepResource stepResource;
    private StepCatalog catalog;

    @Inject
    public void setStepCatalog(final StepCatalog catalog) {
        this.catalog = catalog;
    }

    @Inject
    public void setStepResource(final StepResource stepResource) {
        this.stepResource = stepResource;
    }

    @BeforeEach
    void ensureCatalog() {
        catalog.waitForWarmUp().join();
    }

    @Test
    void stepById() {
        Step s = stepResource.stepById(INFINISPAN_SOURCE);
        Assertions.assertNotNull(s);
        Assertions.assertEquals(INFINISPAN_SOURCE, s.getId());
    }

    @Test
    void stepsByName() {
        Collection<Step> steps = stepResource.stepsByName(INFINISPAN_SOURCE);
        for (Step s : steps) {
            Assertions.assertNotNull(s);
            Assertions.assertEquals(INFINISPAN_SOURCE, s.getName());
            Assertions.assertEquals(INFINISPAN_SOURCE, s.getId());
        }
    }

    @Test
    void stepsByKind() {
        final var kinds = new String[]{"Camel-Connector", "EIP", "Kamelet"};
        for (String expected : kinds) {
            Collection<Step> steps = stepResource.stepsByKind(expected);

            for (Step s : steps) {
                Assertions.assertNotNull(s);
                Assertions.assertEquals(expected, s.getKind());
            }
        }
    }

    @Test
    void stepsByIntegrationType() {
        final var types = new String[]{"KameletBinding", "Kamelet"};
        for (String expected : types) {
            Assertions.assertFalse(
                    stepResource.stepsByIntegrationType(expected).isEmpty());
        }
    }

    @Test
    void allSteps() {
        Collection<Step> steps = stepResource.allSteps();
        Assertions.assertNotNull(steps);
        Assertions.assertEquals(
                catalog.getReadOnlyCatalog().getAll().size(),
                steps.size());
    }
}
