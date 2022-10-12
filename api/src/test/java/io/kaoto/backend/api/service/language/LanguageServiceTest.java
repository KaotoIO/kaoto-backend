package io.kaoto.backend.api.service.language;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;


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
        var map = languageService.getAll();
        Assertions.assertEquals(4, map.size());

        for (var language : map) {
            if (language.get("name").equalsIgnoreCase("KameletBinding")) {
                Assertions.assertEquals("true", language.get("default"));
                Assertions.assertEquals("true", language.get("input"));
                Assertions.assertEquals("true", language.get("output"));
                Assertions.assertEquals("true", language.get("deployable"));
            } else if (language.get("name").equalsIgnoreCase("Kamelet")) {
                Assertions.assertFalse(language.containsKey("default"));
                Assertions.assertEquals("true", language.get("input"));
                Assertions.assertEquals("true", language.get("output"));
                Assertions.assertEquals("true", language.get("deployable"));
            } else if (language.get("name").equalsIgnoreCase("Camel Route")) {
                Assertions.assertFalse(language.containsKey("default"));
                Assertions.assertEquals("true", language.get("input"));
                Assertions.assertEquals("true", language.get("output"));
                Assertions.assertEquals("false", language.get("deployable"));
            }
        }
    }

}
