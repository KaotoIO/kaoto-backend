package io.kaoto.backend.api.resource.v1;

import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.quarkus.test.junit.QuarkusTest;

import javax.inject.Inject;


@QuarkusTest
class DeploymentsResourceTest extends DeploymentsResourceTestAbstract {

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
