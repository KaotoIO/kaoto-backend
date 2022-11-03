package io.kaoto.backend.api.metadata.catalog;

import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.kaoto.backend.metadata.ParseCatalog;
import io.kaoto.backend.model.configuration.Repository;
import io.kaoto.backend.model.step.Step;
import io.quarkus.runtime.Startup;
import io.quarkus.scheduler.Scheduled;
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

        boolean clusterAvailable = isClusterAvailable();
        addCluster(catalogs, clusterAvailable);
        addZipJar(catalogs, clusterAvailable);
        addLocalFolder(catalogs, clusterAvailable);
        addGit(catalogs, clusterAvailable);

        return catalogs;
    }

    private boolean isClusterAvailable() {
        boolean clusterAvailable = true;
        try {
            kclient.pods().list();
        } catch (KubernetesClientException e) {
            clusterAvailable = false;
        }
        return clusterAvailable;
    }

    private void addCluster(final List<ParseCatalog<Step>> catalogs,
                           final boolean clusterAvailable) {
        if (clusterAvailable) {
            for (StepCatalogParser parser : stepCatalogParsers) {
                catalogs.add(parser.getParserFromCluster());
            }
        }
    }

    private void addGit(final List<ParseCatalog<Step>> catalogs,
                           final boolean clusterAvailable) {
        for (Repository.Git git : repository.git().orElse(Collections.emptyList())) {
            for (StepCatalogParser parser : stepCatalogParsers) {
                if (!git.ifNoCluster() || !clusterAvailable) {
                    catalogs.add(parser.getParser(
                            git.url(), git.tag()));
                }
            }
        }
    }

    private void addLocalFolder(final List<ParseCatalog<Step>> catalogs,
                           final boolean clusterAvailable) {
        for (var location : repository.localFolder().orElse(
                Collections.emptyList())) {
            for (StepCatalogParser parser : stepCatalogParsers) {
                if (!location.ifNoCluster() || !clusterAvailable) {
                    File dir = new File(location.url());
                    catalogs.add(parser.getLocalFolder(dir.toPath()));
                }
            }
        }
    }

    private void addZipJar(final List<ParseCatalog<Step>> catalogs,
                           final boolean clusterAvailable) {
        for (var jar : repository.jar().orElse(Collections.emptyList())) {
            for (StepCatalogParser parser : stepCatalogParsers) {
                if (!jar.ifNoCluster() || !clusterAvailable) {
                    catalogs.add(parser.getParser(jar.url()));
                }
            }
        }
    }

    @Inject
    public void setRepository(final StepRepository repo) {
        this.repository = repo;
    }

    public Instance<StepCatalogParser> getStepCatalogParsers() {
        return stepCatalogParsers;
    }

    @Inject
    public void setStepCatalogParsers(final Instance<StepCatalogParser> stepCatalogParsers) {
        this.stepCatalogParsers = stepCatalogParsers;
    }

    @Inject
    public void setKclient(final KubernetesClient kclient) {
        this.kclient = kclient;
    }

    @ConfigMapping(prefix = "repository.step", namingStrategy = ConfigMapping.NamingStrategy.KEBAB_CASE)
    interface StepRepository extends Repository {
    }

    @Override
    @Scheduled(every = "120s", identity = "refresh-step-catalog", concurrentExecution =
            Scheduled.ConcurrentExecution.SKIP)
    public void refresh() {
        super.refresh();
    }
}
