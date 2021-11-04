package io.kaoto.backend.api.metadata.catalog;

import io.smallrye.config.ConfigMapping;
import io.kaoto.backend.metadata.ParseCatalog;
import io.kaoto.backend.metadata.parser.step.KameletParseCatalog;
import io.kaoto.backend.model.configuration.Repository;
import io.kaoto.backend.model.step.Step;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 🐱class StepCatalog
 * 🐱inherits AbstractCatalog
 *
 * This is a singleton that will contain all catalogs with steps.
 */
@ApplicationScoped
public class StepCatalog extends AbstractCatalog<Step> {

    private StepRepository repository;

    @Override
    protected List<ParseCatalog<Step>> loadParsers() {
        List<ParseCatalog<Step>> catalogs = new ArrayList<>();
        for (String jar : repository.jar().orElse(
                Collections.emptyList())) {
            catalogs.add(KameletParseCatalog.getParser(
                    jar));
        }
        for (Repository.Git git : repository.git().orElse(
                Collections.emptyList())) {
            catalogs.add(KameletParseCatalog.getParser(
                    git.url(), git.tag()));
        }
        return catalogs;
    }

    @Inject
    public void setRepository(final StepRepository repo) {
        this.repository = repo;
    }

    @ConfigMapping(prefix = "repository.step")
    interface StepRepository extends Repository {
    }
}
