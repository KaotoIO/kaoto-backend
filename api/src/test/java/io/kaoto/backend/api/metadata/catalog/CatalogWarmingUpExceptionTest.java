package io.kaoto.backend.api.metadata.catalog;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@QuarkusTest
class CatalogWarmingUpExceptionTest {

    private StepCatalog injectedCatalog;

    @Test
    void warmUp() {
        StepCatalog catalog = new StepCatalog();

        Assertions.assertThrows(CatalogWarmingUpException.class, () -> {
            catalog.getReadOnlyCatalog();
        });

        injectedCatalog.waitForWarmUp().join();

        Assertions.assertNotNull(injectedCatalog.getReadOnlyCatalog());
    }

    @Inject
    public void setInjectedCatalog(final StepCatalog injectedCatalog) {
        this.injectedCatalog = injectedCatalog;
    }
}
