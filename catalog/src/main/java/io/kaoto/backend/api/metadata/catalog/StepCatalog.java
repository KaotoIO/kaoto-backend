package io.kaoto.backend.api.metadata.catalog;

import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.kaoto.backend.metadata.ParseCatalog;
import io.kaoto.backend.model.configuration.Repository;
import io.kaoto.backend.model.step.Step;
import io.quarkus.runtime.Startup;
import io.smallrye.config.ConfigMapping;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 🐱class StepCatalog
 * 🐱inherits AbstractCatalog
 *
 * This is a singleton that will contain all catalogs with steps.
 */
@Startup
@ApplicationScoped
public class StepCatalog extends AbstractCatalog<Step> {

    private StepRepository repository;

    private Instance<StepCatalogParser> stepCatalogParsers;

    private KubernetesClient kclient;

    @Override
    protected List<ParseCatalog<Step>> loadParsers() {
        List<ParseCatalog<Step>> catalogs = new ArrayList<>();

        boolean clusterAvailable = true;
        try {
            kclient.pods().list();
        } catch (KubernetesClientException e) {
            clusterAvailable = false;
        }

        if (clusterAvailable) {
            for (StepCatalogParser parser : stepCatalogParsers) {
                catalogs.add(parser.getParserFromCluster());
            }
        }

        for (var jar : repository.jar().orElse(
                Collections.emptyList())) {
            for (StepCatalogParser parser : stepCatalogParsers) {
                if (!jar.whennocluster() || !clusterAvailable) {
                    catalogs.add(parser.getParser(jar.url()));
                }
            }
        }
        for (var location : repository.localFolder().orElse(
                Collections.emptyList())) {
            for (StepCatalogParser parser : stepCatalogParsers) {
                if (!location.whennocluster() || !clusterAvailable) {
                    File dir = new File(location.url());
                    catalogs.add(parser.getLocalFolder(dir.toPath()));
                }
            }
        }
        for (Repository.Git git : repository.git().orElse(
                Collections.emptyList())) {
            for (StepCatalogParser parser : stepCatalogParsers) {
                if (!git.ifNoCluster() || !clusterAvailable) {
                    catalogs.add(parser.getParser(
                            git.url(), git.tag()));
                }
            }
        }

        return catalogs;
    }

    @Inject
    public void setRepository(final StepRepository repo) {
        this.repository = repo;
    }

    public Instance<StepCatalogParser> getStepCatalogParsers() {
        return stepCatalogParsers;
    }

    @Inject
    public void setStepCatalogParsers(
            final Instance<StepCatalogParser> stepCatalogParsers) {
        this.stepCatalogParsers = stepCatalogParsers;
    }

    @Inject
    public void setKclient(final KubernetesClient kclient) {
        this.kclient = kclient;
    }

    @ConfigMapping(prefix = "repository.step")
    interface StepRepository extends Repository {
    }
}
