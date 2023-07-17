package io.kaoto.backend.metadata.catalog;

import io.quarkus.test.junit.QuarkusTest;
import io.kaoto.backend.model.step.Step;
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
        Assertions.assertEquals(0,
                catalog.searchByName("non-existent-empty-catalog").size());

        List<Step> steps = new ArrayList<>();
        steps.add(new Step("id", "name",
                "icon", new ArrayList<>()));

        Assertions.assertTrue(catalog.store(steps));

        Assertions.assertEquals(0,
                catalog.searchByName(
                        "non-existent-populated-catalog").size());
    }

    @Test
    void searchStepsByName() {
        List<Step> steps = new ArrayList<>();
        final var connector = "connector";
        steps.add(new Step("id-1", connector,
                "icon", new ArrayList<>()));
        steps.add(new Step("id-2", connector,
                "icon", new ArrayList<>()));
        steps.add(new Step("id-3", connector,
                "icon", new ArrayList<>()));
        steps.add(new Step("id-4", "another-one",
                "icon", new ArrayList<>()));
        Assertions.assertTrue(catalog.store(steps));

        Assertions.assertEquals(3,
                catalog.searchByName(connector).size());
        Assertions.assertTrue(catalog.searchByName(connector).stream()
                .allMatch(step ->
                        catalog.searchByID(step.getId()).equals(step)));
    }
}
