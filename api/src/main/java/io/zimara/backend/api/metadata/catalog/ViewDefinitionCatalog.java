package io.zimara.backend.api.metadata.catalog;

import io.smallrye.config.ConfigMapping;
import io.zimara.backend.metadata.ParseCatalog;
import io.zimara.backend.metadata.parser.view.ViewDefinitionParseCatalog;
import io.zimara.backend.model.configuration.Repository;
import io.zimara.backend.model.view.ViewDefinition;

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
@ApplicationScoped
public class ViewDefinitionCatalog extends AbstractCatalog<ViewDefinition> {

    private ViewDefinitionCatalog.ViewDefinitionRepository repository;

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
            final ViewDefinitionCatalog.ViewDefinitionRepository repo) {
        this.repository = repo;
    }

    @ConfigMapping(prefix = "repository.viewdefinition")
    interface ViewDefinitionRepository extends Repository {
    }
}
