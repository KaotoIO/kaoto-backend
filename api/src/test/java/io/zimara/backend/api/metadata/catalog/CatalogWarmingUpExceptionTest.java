package io.zimara.backend.api.metadata.catalog;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
class CatalogWarmingUpExceptionTest {

    @Test
    void warmUp() {
        StepCatalog catalog = new StepCatalog();

        Assertions.assertThrows(CatalogWarmingUpException.class, () -> {
            catalog.getReadOnlyCatalog();
        });

        catalog.waitForWarmUp().join();

        Assertions.assertNotNull(catalog.getReadOnlyCatalog());
    }

}