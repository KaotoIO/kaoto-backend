package io.kaoto.backend.api.metadata.catalog;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
            catalogs.addAll(
                stepCatalogParsers.stream().parallel().map(StepCatalogParser::getParserFromCluster).toList()
            );
        }
    }

    private void addGit(final List<ParseCatalog<Step>> catalogs, final boolean clusterAvailable) {
        //For all git in the configuration
        List<ParseCatalog<Step>> items = repository.git().orElse(Collections.emptyList()).stream().parallel()
                //Filter depending on the cluster
                .filter(git -> !git.ifNoCluster() || !clusterAvailable)
                //And call only the parsers that apply
                .flatMap(git -> {
                    return stepCatalogParsers.stream().parallel()
                        .filter(parser -> ALL.equalsIgnoreCase(git.kind()) || parser.generatesKind(git.kind()))
                        .map(parser -> parser.getParser(git.url(), git.tag()));
                    }
                ).toList();

        catalogs.addAll(items);

    }

    private void addLocalFolder(final List<ParseCatalog<Step>> catalogs, final boolean clusterAvailable) {
        //For all folders in the configuration
        List<ParseCatalog<Step>> items = repository.localFolder().orElse(Collections.emptyList()).stream().parallel()
                //Filter depending on the cluster
                .filter(folder -> !folder.ifNoCluster() || !clusterAvailable)
                //And call only the parsers that apply
                .flatMap(folder -> stepCatalogParsers.stream().parallel()
                    .filter(parser -> ALL.equalsIgnoreCase(folder.kind()) || parser.generatesKind(folder.kind()))
                        .map(parser -> {
                            File dir = new File(folder.url());
                            return parser.getLocalFolder(dir.toPath());
                        })
                ).toList();

        catalogs.addAll(items);
    }

    private void addZipJar(final List<ParseCatalog<Step>> catalogs, final boolean clusterAvailable) {
        //For all jars in the configuration
        List<ParseCatalog<Step>> items = repository.jar().orElse(Collections.emptyList()).stream().parallel()
                //Filter depending on the cluster
                .filter(jar -> !jar.ifNoCluster() || !clusterAvailable)
                //And call only the parsers that apply
                .flatMap(jar -> stepCatalogParsers.stream().parallel()
                    .filter(parser -> ALL.equalsIgnoreCase(jar.kind()) || parser.generatesKind(jar.kind()))
                    .map(parser -> parser.getParser(jar.url()))
                ).toList();

        catalogs.addAll(items);

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
