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
    public void getAll() {
        var map = languageService.getAll();
        Assertions.assertEquals(2, map.size());
        Assertions.assertTrue(map.containsKey("Kamelet"));
        Assertions.assertTrue(map.containsKey("KameletBinding"));

        var kameletBinding = map.get("KameletBinding");
        Assertions.assertEquals(kameletBinding.get("default"), "true");
        Assertions.assertEquals(kameletBinding.get("input"), "true");
        Assertions.assertEquals(kameletBinding.get("output"), "true");

        var kamelet = map.get("Kamelet");
        Assertions.assertFalse(kamelet.containsKey("default"));
        Assertions.assertEquals(kamelet.get("input"), "true");
        Assertions.assertEquals(kamelet.get("output"), "true");
    }

}
