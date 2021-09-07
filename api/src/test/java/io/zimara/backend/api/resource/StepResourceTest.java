package io.zimara.backend.api.resource;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.zimara.backend.api.metadata.catalog.StepCatalog;
import io.zimara.backend.model.step.Step;
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
    public void setCatalog(StepCatalog catalog) {
        this.catalog = catalog;
    }

    @Inject
    public void setStepResource(StepResource stepResource) {
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
    void allSteps() {
        Collection<Step> steps = stepResource.allSteps();
        Assertions.assertNotNull(steps);
        Assertions.assertEquals(catalog.getReadOnlyCatalog().getAll().size(), steps.size());
    }
}