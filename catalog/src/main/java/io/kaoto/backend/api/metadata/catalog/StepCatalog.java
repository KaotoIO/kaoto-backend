package io.kaoto.backend.api.metadata.catalog;

import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.kaoto.backend.metadata.ParseCatalog;
import io.kaoto.backend.model.configuration.Repository;
import io.kaoto.backend.model.step.Step;
import io.quarkus.runtime.Startup;
import io.quarkus.scheduler.Scheduled;
import io.smallrye.config.ConfigMapping;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 🐱class StepCatalog
 *
 * 🐱inherits AbstractCatalog
 *
 * This is a singleton that will contain all catalogs with steps.
 */
@Startup
@ApplicationScoped
public class StepCatalog extends AbstractCatalog<Step> {

    public static final String ALL = "all";
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
        boolean clusterAvailable = kclient != null;
        try {
            if (clusterAvailable) {
                kclient.pods().inAnyNamespace().list();
            }
        } catch (KubernetesClientException e) {
            clusterAvailable = false;
        }
        return clusterAvailable;
    }

    private void addCluster(final List<ParseCatalog<Step>> catalogs,
                            final boolean clusterAvailable) {
        if (clusterAvailable) {
            stepCatalogParsers.stream().parallel().forEach(parser -> catalogs.add(parser.getParserFromCluster()));
        }
    }

    private void addGit(final List<ParseCatalog<Step>> catalogs, final boolean clusterAvailable) {
        //For all git in the configuration
        repository.git().orElse(Collections.emptyList()).stream().parallel()
                //Filter depending on the cluster
                .filter(git -> !git.ifNoCluster() || !clusterAvailable)
                //And call only the parsers that apply
                .forEach(git -> stepCatalogParsers.stream().parallel()
                        .forEach(parser -> {
                            if (ALL.equalsIgnoreCase(git.kind()) || parser.generatesKind(git.kind())) {
                                catalogs.add(parser.getParser(git.url(), git.tag()));
                            }
                        }));

    }

    private void addLocalFolder(final List<ParseCatalog<Step>> catalogs, final boolean clusterAvailable) {
        //For all folders in the configuration
        repository.localFolder().orElse(Collections.emptyList()).stream().parallel()
                //Filter depending on the cluster
                .filter(folder -> !folder.ifNoCluster() || !clusterAvailable)
                //And call only the parsers that apply
                .forEach(folder -> stepCatalogParsers.stream().parallel()
                        .forEach(parser -> {
                            if (ALL.equalsIgnoreCase(folder.kind()) || parser.generatesKind(folder.kind())) {
                                File dir = new File(folder.url());
                                catalogs.add(parser.getLocalFolder(dir.toPath()));
                            }
                        }));
    }

    private void addZipJar(final List<ParseCatalog<Step>> catalogs, final boolean clusterAvailable) {
        //For all jars in the configuration
        repository.jar().orElse(Collections.emptyList()).stream().parallel()
                //Filter depending on the cluster
                .filter(jar -> !jar.ifNoCluster() || !clusterAvailable)
                //And call only the parsers that apply
                .forEach(jar -> stepCatalogParsers.stream().parallel()
                        .forEach(parser -> {
                            if (ALL.equalsIgnoreCase(jar.kind()) || parser.generatesKind(jar.kind())) {
                                catalogs.add(parser.getParser(jar.url()));
                            }
                        }));
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
    @Scheduled(every = "${repository.step.every:off}", identity = "refresh-step-catalog", concurrentExecution =
            Scheduled.ConcurrentExecution.SKIP, delay = 200, delayUnit = TimeUnit.SECONDS)
    public void refresh() {
        super.refresh();
    }
}
