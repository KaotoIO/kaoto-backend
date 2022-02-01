package io.kaoto.backend.api.metadata.catalog;

import io.quarkus.runtime.Startup;
import io.kaoto.backend.metadata.ParseCatalog;
import io.kaoto.backend.metadata.parser.view.ViewDefinitionParseCatalog;
import io.kaoto.backend.model.configuration.Repository;
import io.kaoto.backend.model.view.ViewDefinition;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * 🐱class ViewDefinitionCatalog
 * 🐱inherits AbstractCatalog
 *
 * This is a singleton that will contain all catalogs with viewdefinitions.
 *
 */
@Startup
@ApplicationScoped
public class ViewDefinitionCatalog extends AbstractCatalog<ViewDefinition> {

    private ViewDefinitionRepository repository;

    @Override
    protected List<ParseCatalog<ViewDefinition>> loadParsers() {
        List<ParseCatalog<ViewDefinition>> catalogs = new ArrayList<>();
        for (String jar : repository.jar().orElse(
                Collections.emptyList())) {
            catalogs.add(ViewDefinitionParseCatalog.getParser(
                    jar));
        }
        for (Repository.Git git : repository.git().orElse(
                Collections.emptyList())) {
            catalogs.add(ViewDefinitionParseCatalog.getParser(
                    git.url(), git.tag()));
        }
        return catalogs;
    }

    @Inject
    public void setRepository(
            final ViewDefinitionRepository repo) {
        this.repository = repo;
    }

}
