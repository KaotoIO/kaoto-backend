package io.zimara.backend.api.metadata.catalog;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@QuarkusTest
class CatalogWarmingUpExceptionTest {
    @Inject
    StepCatalog catalog;

    @Test
    void warmUp() {
        Assertions.assertThrows(CatalogWarmingUpException.class, () -> {
            catalog.getReadOnlyCatalog();
        });

        catalog.waitForWarmUp().join();

        Assertions.assertNotNull(catalog.getReadOnlyCatalog());
    }

}