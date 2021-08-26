package io.zimara.backend.metadata.catalog;

import io.quarkus.test.junit.QuarkusTest;
import io.zimara.backend.model.step.Step;
import io.zimara.backend.model.step.kamelet.KameletStep;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@QuarkusTest
class CatalogCollectionTest {

    public static final String CONNECTOR_2 = "connector-2";
    private static CatalogCollection<Step> catalogCollection = new CatalogCollection<>();

    @BeforeAll
    static void before() {
        List<Step> steps = new ArrayList<>();

        InMemoryCatalog<Step> ic = new InMemoryCatalog<>();
        steps.add(new KameletStep("id-1", "connector-1", "icon", Collections.emptyList()));
        steps.add(new KameletStep("id-2", CONNECTOR_2, "icon", Collections.emptyList()));
        ic.store(steps);
        catalogCollection.addCatalog(ic);

        ic = new InMemoryCatalog<>();
        steps.clear();
        steps.add(new KameletStep("id-2", CONNECTOR_2, "icon", Collections.emptyList()));
        steps.add(new KameletStep("id-3", CONNECTOR_2, "icon", Collections.emptyList()));
        ic.store(steps);
        catalogCollection.addCatalog(ic);
    }

    @Test
    void searchStepByID() {
        Assertions.assertNotNull(catalogCollection.searchStepByID("id-1"));
        Assertions.assertNotNull(catalogCollection.searchStepByID("id-3"));
        Assertions.assertNull(catalogCollection.searchStepByID("non-existent"));
    }

    @Test
    void searchStepByName() {
        Step step = catalogCollection.searchStepByName(CONNECTOR_2);
        Assertions.assertNotNull(step);
        Assertions.assertEquals(CONNECTOR_2, step.getName());
    }

    @Test
    void searchStepsByName() {
        Collection<Step> step = catalogCollection.searchStepsByName(CONNECTOR_2);
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
        ReadOnlyCatalog<Step> readOnlyCatalog = new ReadOnlyCatalog<>(catalogCollection);

        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            readOnlyCatalog.addCatalog(catalogCollection);
        });
    }
}