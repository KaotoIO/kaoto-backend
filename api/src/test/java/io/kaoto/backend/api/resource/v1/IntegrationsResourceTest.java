package io.kaoto.backend.api.resource.v1;

import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.language.LanguageService;
import io.quarkus.test.junit.QuarkusTest;

import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

@QuarkusTest
public class IntegrationsResourceTest extends IntegrationsResourceTestAbstract {
    private StepCatalog catalog;
    private LanguageService languageService;
    @Inject
    public void setCatalog(final StepCatalog catalog) {
        this.catalog = catalog;
    }
    @Inject
    public void setLanguageService(final LanguageService languageService) {
        this.languageService = languageService;
    }

    @Override
    protected void waitForWarmUpCatalog() {
        catalog.waitForWarmUp().join();
    }

    @Override
    protected List<String> getAllLanguages() {
        return languageService.getAll().stream().map(language ->
                String.valueOf(language.get("name"))).collect(Collectors.toList());
    }
}
