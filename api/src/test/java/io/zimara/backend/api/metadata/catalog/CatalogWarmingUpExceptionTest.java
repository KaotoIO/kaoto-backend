package io.zimara.backend.api.metadata.catalog;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@QuarkusTest
class CatalogWarmingUpExceptionTest {
    @Inject
    ViewDefinitionCatalog viewCatalog;

    @Test
    void warmUp() {
        Assertions.assertThrows(CatalogWarmingUpException.class, () -> {
            viewCatalog.getReadOnlyCatalog();
        });

        viewCatalog.waitForWarmUp().join();

        Assertions.assertNotNull(viewCatalog.getReadOnlyCatalog());
    }

}