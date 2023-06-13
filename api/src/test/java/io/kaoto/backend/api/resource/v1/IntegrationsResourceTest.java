package io.kaoto.backend.api.resource.v1;

import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.quarkus.test.junit.QuarkusTest;

import jakarta.inject.Inject;

@QuarkusTest
public class IntegrationsResourceTest extends IntegrationsResourceTestAbstract {
    private StepCatalog catalog;

    @Inject
    public void setCatalog(final StepCatalog catalog) {
        this.catalog = catalog;
    }

    @Override
    protected void waitForWarmUpCatalog() {
        catalog.waitForWarmUp().join();
    }
}
