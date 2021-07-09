package io.zimara.backend.api.resource;

import io.zimara.backend.api.Catalog;
import io.zimara.backend.model.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;

class StepResourceTest {

    public static final String INFINISPAN_SOURCE = "infinispan-source";
    private StepResource stepResource = new StepResource();

    @Test
    void stepById() {
        Catalog.waitForWarmUp().join();
        Step s = stepResource.stepById(INFINISPAN_SOURCE);
        Assertions.assertNotNull(s);
        Assertions.assertEquals(INFINISPAN_SOURCE, s.getID());
    }

    @Test
    void stepsByName() {
        Catalog.waitForWarmUp().join();
        Collection<Step> steps = stepResource.stepsByName(INFINISPAN_SOURCE);
        for (Step s : steps) {
            Assertions.assertNotNull(s);
            Assertions.assertEquals(INFINISPAN_SOURCE, s.getName());
            Assertions.assertEquals(INFINISPAN_SOURCE, s.getID());
        }
    }

    @Test
    void allSteps() {
        Catalog.waitForWarmUp().join();
        Collection<Step> steps = stepResource.allSteps();
        Assertions.assertNotNull(steps);
        Assertions.assertEquals(Catalog.getReadOnlyCatalog().getAll().size(), steps.size());
    }
}