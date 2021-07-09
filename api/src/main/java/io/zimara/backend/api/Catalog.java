package io.zimara.backend.api;

import io.zimara.backend.metadata.MetadataCatalog;
import io.zimara.backend.metadata.ParseCatalog;
import io.zimara.backend.metadata.catalog.InMemoryCatalog;
import io.zimara.backend.metadata.catalog.ReadOnlyCatalog;
import io.zimara.backend.metadata.parser.kamelet.KameletParseCatalog;
import org.jboss.logging.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Catalog {


    private Catalog() {
    }

    private static InMemoryCatalog c = new InMemoryCatalog();
    private static final MetadataCatalog readOnlyCatalog = new ReadOnlyCatalog(c);
    private static Boolean warmedUp = false;
    private static Logger log = Logger.getLogger(Catalog.class);
    private static CompletableFuture<Void> waitingForWarmUp;

    static {
        warmUpCatalog(loadParsers());
    }

    public static MetadataCatalog getReadOnlyCatalog() {
        return readOnlyCatalog;
    }

    public static Boolean isWarmedUp() {
        return warmedUp;
    }

    public static CompletableFuture waitForWarmUp() {
        return waitingForWarmUp;
    }

    /**
     * This may be autowired by jandex?
     *
     * @return
     */
    private static List<ParseCatalog> loadParsers() {
        List<ParseCatalog> catalogs = new ArrayList<>();
        catalogs.add(new KameletParseCatalog("https://github.com/apache/camel-kamelets.git", "v0.3.0"));
        return catalogs;
    }

    /**
     * Add all steps from the parsers into the catalog
     * @param catalogs
     */
    private static void warmUpCatalog(List<ParseCatalog> catalogs) {
        List<CompletableFuture<Boolean>> futureSteps = new ArrayList<>();

        for(var catalog : catalogs) {
            futureSteps.add(addCatalog(catalog));
        }

        waitingForWarmUp = CompletableFuture.allOf(futureSteps.toArray(new CompletableFuture[0]));
        waitingForWarmUp.thenAccept(complete -> warmedUp = true);
    }

    private static CompletableFuture<Boolean> addCatalog(ParseCatalog catalog) {
        CompletableFuture<Boolean> res = new CompletableFuture<>();
        catalog.parse()
                .thenApply(steps -> c.store(steps))
                .thenAccept(steps -> res.complete(true));
        return res;
    }
}
