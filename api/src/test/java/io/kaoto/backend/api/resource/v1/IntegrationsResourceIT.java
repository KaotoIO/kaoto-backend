package io.kaoto.backend.api.resource.v1;

import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.resource.support.NativeHelper;
import io.quarkus.test.junit.QuarkusIntegrationTest;

import java.util.List;

@QuarkusIntegrationTest
class IntegrationsResourceIT extends IntegrationsResourceTestAbstract {
    @Override
    protected void waitForWarmUpCatalog() {
        NativeHelper.waitForWarmUpCatalog(StepCatalog.class.getCanonicalName());
    }

    @Override
    protected List<String> getAllLanguages() {
        return List.of("KameletBinding",
                "Kamelet",
                "Camel Route",
                "Integration");
    }
}
