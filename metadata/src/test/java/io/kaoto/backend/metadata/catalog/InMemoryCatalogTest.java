package io.kaoto.backend.metadata.catalog;

import io.quarkus.test.junit.QuarkusTest;
import io.kaoto.backend.model.step.Step;
import io.kaoto.backend.model.step.kamelet.KameletStep;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@QuarkusTest
class InMemoryCatalogTest {

    private final InMemoryCatalog<Step> catalog = new InMemoryCatalog<>();

    @Test
    void store() {
        //corner cases
        Assertions.assertFalse(catalog.store(null));
        Assertions.assertTrue(catalog.store(Collections.emptyList()));
    }

    @Test
    void searchStepByName() {
        Assertions.assertNull(
                catalog.searchStepByName("non-existent-empty-catalog"));

        List<Step> steps = new ArrayList<>();
        steps.add(new KameletStep("id", "name",
                "icon", Collections.emptyList()));

        Assertions.assertTrue(catalog.store(steps));

        Assertions.assertNull(
                catalog.searchStepByName("non-existent-populated-catalog"));
    }

    @Test
    void searchStepsByName() {
        List<Step> steps = new ArrayList<>();
        final var connector = "connector";
        steps.add(new KameletStep("id-1", connector,
                "icon", Collections.emptyList()));
        steps.add(new KameletStep("id-2", connector,
                "icon", Collections.emptyList()));
        steps.add(new KameletStep("id-3", connector,
                "icon", Collections.emptyList()));
        steps.add(new KameletStep("id-4", "another-one",
                "icon", Collections.emptyList()));
        Assertions.assertTrue(catalog.store(steps));

        Assertions.assertEquals(
                3,
                catalog.searchStepsByName(connector).size());
        Assertions.assertTrue(
                catalog.searchStepsByName(connector)
                        .contains(catalog.searchStepByName(connector)));
    }
}
