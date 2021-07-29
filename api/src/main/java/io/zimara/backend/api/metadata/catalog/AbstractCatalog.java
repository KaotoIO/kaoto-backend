package io.zimara.backend.api.metadata.catalog;

import io.zimara.backend.metadata.MetadataCatalog;
import io.zimara.backend.metadata.ParseCatalog;
import io.zimara.backend.metadata.catalog.InMemoryCatalog;
import io.zimara.backend.metadata.catalog.ReadOnlyCatalog;
import io.zimara.backend.model.Metadata;
import org.jboss.logging.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public abstract class AbstractCatalog<T extends Metadata> {

    public AbstractCatalog() {
        warmUpCatalog(loadParsers());
    }

    private InMemoryCatalog<T> c = new InMemoryCatalog<>();
    private final MetadataCatalog<T> readOnlyCatalog = new ReadOnlyCatalog<>(c);
    private Boolean warmedUp = false;
    private static Logger log = Logger.getLogger(AbstractCatalog.class);
    private CompletableFuture<Void> waitingForWarmUp;

    public MetadataCatalog<T> getReadOnlyCatalog() {
        if (warmedUp) {
            return readOnlyCatalog;
        } else throw new RuntimeException("Catalog still warming up.");
    }

    public Boolean isWarmedUp() {
        return warmedUp;
    }

    public CompletableFuture<Void> waitForWarmUp() {
        return waitingForWarmUp;
    }

    /**
     * This may be autowired by jandex?
     *
     * @return
     */
    abstract List<ParseCatalog<T>> loadParsers();

    /**
     * Add all steps from the parsers into the catalog
     *
     * @param catalogs
     */
    private void warmUpCatalog(List<ParseCatalog<T>> catalogs) {
        log.debug("Warming up catalog.");
        List<CompletableFuture<Boolean>> futureSteps = new ArrayList<>();

        for (var catalog : catalogs) {
            futureSteps.add(addCatalog(catalog));
        }

        waitingForWarmUp = CompletableFuture.allOf(futureSteps.toArray(new CompletableFuture[0]));
        waitingForWarmUp.thenAccept(complete -> warmedUp = true)
                .thenRun(() -> log.debug("Catalog warmed up."));
    }

    private CompletableFuture<Boolean> addCatalog(ParseCatalog<T> catalog) {
        CompletableFuture<Boolean> res = new CompletableFuture<>();
        catalog.parse()
                .thenApply(md -> c.store(md))
                .thenAccept(md -> res.complete(true));
        return res;
    }
}
