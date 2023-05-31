package io.kaoto.backend.api.service.language;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static io.kaoto.backend.api.service.language.LanguagesSpecificationChecker.checkAllLanguagesSpecifications;


@QuarkusTest
class LanguageServiceTest {

    private LanguageService languageService;

    @Inject
    public void setLanguageService(
            final LanguageService languageService) {
        this.languageService = languageService;
    }

    @Test
    void getAll() {
        checkAllLanguagesSpecifications(languageService.getAll());
    }
}
