package io.kaoto.backend.metadata.parser.view;

import io.quarkus.test.junit.QuarkusTest;
import io.kaoto.backend.metadata.catalog.InMemoryCatalog;
import io.kaoto.backend.model.view.ViewDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

@QuarkusTest
class ViewDefinitionDefinitionParseCatalogTest {

    @Test
    void getViews() {
        final var url =
                "https://github.com/KaotoIO/kaoto-viewdefinition-catalog";
        var parser =
                ViewDefinitionParseCatalog.getParser(
                        url,
                        "main");
        InMemoryCatalog<ViewDefinition> catalog = new InMemoryCatalog<>();

        List<ViewDefinition> viewDefinitions = parser.parse().join();
        Assertions.assertTrue(catalog.store(viewDefinitions));
        Assertions.assertEquals(viewDefinitions.size(),
                catalog.getAll().size());
    }
}
