package io.kaoto.backend.metadata.catalog;

import io.quarkus.test.junit.QuarkusTest;
import io.kaoto.backend.model.step.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@QuarkusTest
class CatalogCollectionTest {

    public static final String CONNECTOR_2 = "connector-2";
    private static CatalogCollection<Step> catalogCollection =
            new CatalogCollection<>();

    @BeforeAll
    static void before() {
        List<Step> steps = new ArrayList<>();

        InMemoryCatalog<Step> ic = new InMemoryCatalog<>();
        steps.add(new Step("id-1",
                "connector-1", "icon",
                new LinkedList<>()));
        steps.add(new Step("id-2", CONNECTOR_2,
                "icon", new LinkedList<>()));
        ic.store(steps);
        catalogCollection.addCatalog(ic);

        ic = new InMemoryCatalog<>();
        steps.clear();
        steps.add(new Step("id-2", CONNECTOR_2,
                "icon", new LinkedList<>()));
        steps.add(new Step("id-3", CONNECTOR_2,
                "icon", new LinkedList<>()));
        ic.store(steps);
        catalogCollection.addCatalog(ic);
    }

    @Test
    void searchStepByID() {
        Assertions.assertNotNull(catalogCollection.searchByID("id-1"));
        Assertions.assertNotNull(catalogCollection.searchByID("id-3"));
        Assertions.assertNull(catalogCollection.searchByID("non-existent"));
    }

    @Test
    void searchStepByName() {
        var steps = catalogCollection.searchByName(CONNECTOR_2);
        Assertions.assertNotNull(steps);
        Assertions.assertEquals(3, steps.size());
        Assertions.assertTrue(steps.stream().allMatch(
                step -> CONNECTOR_2.equalsIgnoreCase(step.getName())));
    }

    @Test
    void searchStepsByName() {
        Collection<Step> step =
                catalogCollection.searchByName(CONNECTOR_2);
        Assertions.assertNotNull(step);
        Assertions.assertEquals(3, step.size());
        for (Step s : step) {
            Assertions.assertEquals(CONNECTOR_2, s.getName());
        }
    }

    @Test
    void store() {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            catalogCollection.store(null);
        });
    }

    @Test
    void getAll() {
        Assertions.assertEquals(4, catalogCollection.getAll().size());
    }


    @Test
    void readOnlyWrapper() {
        ReadOnlyCatalog<Step> readOnlyCatalog =
                new ReadOnlyCatalog<>(catalogCollection);

        readOnlyCatalog.addCatalog(catalogCollection);

        Assertions.assertEquals(catalogCollection.getAll().size(),
                readOnlyCatalog.getAll().size());
    }
}
