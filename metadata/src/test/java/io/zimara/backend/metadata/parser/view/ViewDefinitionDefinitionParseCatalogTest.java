package io.zimara.backend.metadata.parser.view;

import io.quarkus.test.junit.QuarkusTest;
import io.zimara.backend.metadata.catalog.InMemoryCatalog;
import io.zimara.backend.model.view.ViewDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

@QuarkusTest
class ViewDefinitionDefinitionParseCatalogTest {

    @Test
    void getViews() {
        ViewDefinitionParseCatalog parser = new ViewDefinitionParseCatalog("https://github.com/ZimaraIO/zimara-viewdefinition-catalog", "main");
        InMemoryCatalog<ViewDefinition> catalog = new InMemoryCatalog<>();

        List<ViewDefinition> viewDefinitions = parser.parse().join();
        Assertions.assertTrue(catalog.store(viewDefinitions));
        Assertions.assertEquals(2, viewDefinitions.size());
        Assertions.assertEquals(2, catalog.getAll().size());
    }
}