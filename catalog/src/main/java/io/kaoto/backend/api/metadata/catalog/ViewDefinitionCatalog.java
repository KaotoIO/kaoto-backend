package io.kaoto.backend.api.metadata.catalog;

import io.quarkus.runtime.Startup;
import io.smallrye.config.ConfigMapping;
import io.kaoto.backend.metadata.ParseCatalog;
import io.kaoto.backend.metadata.parser.view.ViewDefinitionParseCatalog;
import io.kaoto.backend.model.configuration.Repository;
import io.kaoto.backend.model.view.ViewDefinition;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 🐱class ViewDefinitionCatalog
 *
 * 🐱inherits AbstractCatalog
 *
 * This is a singleton that will contain all catalogs with viewdefinitions.
 */
@Startup
@ApplicationScoped
public class ViewDefinitionCatalog extends AbstractCatalog<ViewDefinition> {

    private ViewDefinitionCatalog.ViewDefinitionRepository repository;

    @Override
    protected List<ParseCatalog<ViewDefinition>> loadParsers() {
        List<ParseCatalog<ViewDefinition>> catalogs = new ArrayList<>();

        //For all jars in the configuration
        repository.jar().orElse(Collections.emptyList()).stream().parallel()
                .forEach(jar -> catalogs.add(ViewDefinitionParseCatalog.getParser(jar.url())));

        //For all git in the configuration
        repository.git().orElse(Collections.emptyList()).stream().parallel()
                .forEach(git -> catalogs.add(ViewDefinitionParseCatalog.getParser(git.url(), git.tag())));

        //For all folders in the configuration
        repository.localFolder().orElse(Collections.emptyList()).stream().parallel()
                .forEach(folder -> catalogs.add(ViewDefinitionParseCatalog.getParser(new File(folder.url()).toPath())));

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
