package io.zimara.backend.api.metadata.catalog;

import io.zimara.backend.metadata.ParseCatalog;
import io.zimara.backend.metadata.parser.view.ViewDefinitionParseCatalog;
import io.zimara.backend.model.view.ViewDefinition;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 🐱class ViewDefinitionCatalog
 * 🐱relationship dependsOn ViewDefinition
 * 🐱inherits AbstractCatalog
 *
 * This is a singleton that will contain all catalogs with viewdefinitions.
 *
 */
@ApplicationScoped
public class ViewDefinitionCatalog extends AbstractCatalog<ViewDefinition> {

    @Override
    protected List<ParseCatalog<ViewDefinition>> loadParsers() {
        List<ParseCatalog<ViewDefinition>> catalogs = new ArrayList<>();
        catalogs.add(new ViewDefinitionParseCatalog("https://github.com/ZimaraIO/zimara-viewdefinition-catalog.git", "main"));
        return catalogs;
    }
}
