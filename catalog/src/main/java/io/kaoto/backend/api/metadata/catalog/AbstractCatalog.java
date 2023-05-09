package io.kaoto.backend.api.metadata.catalog;

import io.kaoto.backend.metadata.MetadataCatalog;
import io.kaoto.backend.metadata.ParseCatalog;
import io.kaoto.backend.metadata.catalog.InMemoryCatalog;
import io.kaoto.backend.metadata.catalog.ReadOnlyCatalog;
import io.kaoto.backend.model.Metadata;
import io.opentelemetry.extension.annotations.WithSpan;
import jakarta.annotation.PostConstruct;
import org.jboss.logging.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 🐱class AbstractCatalog
 * 🐱relationship dependsOn MetadataCatalog
 * Abstract implementation of an ApplicationScoped catalog.
 * This will be the base of the beans
 * that can be injected in the different services and resources.
 */
public abstract class AbstractCatalog<T extends Metadata> {

    private InMemoryCatalog<T> c = new InMemoryCatalog<>();
    private final MetadataCatalog<T> readOnlyCatalog = new ReadOnlyCatalog<>(c);
    private static Logger log = Logger.getLogger(AbstractCatalog.class);
    private CompletableFuture<Void> waitingForWarmUp;
    private CompletableFuture<Void> initializing = new CompletableFuture<>();

    protected AbstractCatalog() {
        this.waitingForWarmUp = initializing;
    }

    public MetadataCatalog<T> getReadOnlyCatalog() {
        if (waitingForWarmUp.isDone()) {
            return readOnlyCatalog;
        } else {
            throw new CatalogWarmingUpException("Catalog still warming up.");
        }
    }

    /*
     * 🐱method waitForWarmUp : CompletableFuture
     *
     * Completable reference to when the loadParsers method finishes.
     */
    @WithSpan
    public CompletableFuture<Void> waitForWarmUp() {
        return waitingForWarmUp;
    }

    /*
     * 🐱method loadParsers : List[ParseCatalog]
     *
     * Loads all the catalogs into the bean
     */
    abstract List<ParseCatalog<T>> loadParsers();

    /*
     * 🐱method warmUpCatalog
     *
     *  Add all elements from the parsers into the catalog
     *
     */
    @PostConstruct
    @WithSpan
    public void warmUpCatalog() {
        log.debug("Warming up catalog.");
        final var time = System.currentTimeMillis();
        final List<CompletableFuture<Boolean>> futureSteps = new ArrayList<>();
        loadParsers().stream().parallel().forEach(parser -> futureSteps.add(addCatalog(parser)));

        waitingForWarmUp = CompletableFuture.allOf(futureSteps.toArray(new CompletableFuture[0]));
        waitingForWarmUp
                .thenAccept(complete -> initializing.complete(null))
                .thenRun(() ->
                        log.info("Catalog " + this.getClass() + " warmed up in "
                                + (System.currentTimeMillis() - time) + "ms."));
    }

    private CompletableFuture<Boolean> addCatalog(final ParseCatalog<T> catalog) {
        CompletableFuture<Boolean> res = new CompletableFuture<>();
        final var time = System.currentTimeMillis();
        catalog.parse()
                .thenApply(md -> c.store(md))
                .thenRun(() -> log.info("Parser " + catalog.getClass() + " processed in "
                        + (System.currentTimeMillis() - time) + "ms."))
                .thenAccept(md -> res.complete(true));
        return res;
    }

    /*
     * 🐱method refresh
     *
     * Function to be called periodically that refreshes the elements of the
     * catalog, considering the catalog is dynamic. It prevents any
     * synchronization error that would show an empty catalog during the
     * refreshing.
     *
     */
    public void refresh() {
        List<CompletableFuture<Boolean>> futureSteps = new ArrayList<>();
        for (var catalog : loadParsers()) {
            futureSteps.add(addCatalog(catalog));
        }

        var waitingForWarmUp = CompletableFuture.allOf(
                futureSteps.toArray(new CompletableFuture[0]));
        waitingForWarmUp.thenAccept(complete ->
                ((ReadOnlyCatalog) getReadOnlyCatalog()).addCatalog(c))
                .thenRun(() -> log.info("Catalog refreshed."));
    }
}
