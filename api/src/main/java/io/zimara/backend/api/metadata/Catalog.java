package io.zimara.backend.api.metadata;

import io.zimara.backend.metadata.MetadataCatalog;
import io.zimara.backend.metadata.ParseCatalog;
import io.zimara.backend.metadata.catalog.InMemoryCatalog;
import io.zimara.backend.metadata.catalog.ReadOnlyCatalog;
import io.zimara.backend.metadata.parser.kamelet.KameletParseCatalog;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@ApplicationScoped
public class Catalog {

    public Catalog() {
        warmUpCatalog(loadParsers());
    }

    private static InMemoryCatalog c = new InMemoryCatalog();
    private static final MetadataCatalog readOnlyCatalog = new ReadOnlyCatalog(c);
    private static Boolean warmedUp = false;
    private static Logger log = Logger.getLogger(Catalog.class);
    private static CompletableFuture<Void> waitingForWarmUp;

    public MetadataCatalog getReadOnlyCatalog() {
        return readOnlyCatalog;
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
    private List<ParseCatalog> loadParsers() {
        List<ParseCatalog> catalogs = new ArrayList<>();
        catalogs.add(new KameletParseCatalog("https://github.com/apache/camel-kamelets.git", "v0.3.0"));
        return catalogs;
    }

    /**
     * Add all steps from the parsers into the catalog
     * @param catalogs
     */
    private void warmUpCatalog(List<ParseCatalog> catalogs) {
        log.debug("Warming up catalog.");
        List<CompletableFuture<Boolean>> futureSteps = new ArrayList<>();

        for(var catalog : catalogs) {
            futureSteps.add(addCatalog(catalog));
        }

        waitingForWarmUp = CompletableFuture.allOf(futureSteps.toArray(new CompletableFuture[0]));
        waitingForWarmUp.thenAccept(complete -> warmedUp = true)
                        .thenRun(() -> log.debug("Catalog warmed up."));
    }

    private static CompletableFuture<Boolean> addCatalog(ParseCatalog catalog) {
        CompletableFuture<Boolean> res = new CompletableFuture<>();
        catalog.parse()
                .thenApply(steps -> c.store(steps))
                .thenAccept(steps -> res.complete(true));
        return res;
    }
}
