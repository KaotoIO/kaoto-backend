package io.zimara.backend.api;

import io.zimara.backend.metadata.ParseCatalog;
import io.zimara.backend.metadata.catalog.InMemoryCatalog;
import io.zimara.backend.metadata.catalog.ReadOnlyCatalog;
import io.zimara.backend.metadata.parser.kamelet.KameletParseCatalog;
import org.jboss.logging.Logger;

public class Catalog {

    private Catalog() {
    }

    private static InMemoryCatalog c = new InMemoryCatalog();
    private final static ReadOnlyCatalog readOnlyCatalog = new ReadOnlyCatalog(c);
    private static Boolean warmedUp = false;
    private static Logger log = Logger.getLogger(Catalog.class);

    static {
        addCatalog(new KameletParseCatalog("https://github.com/apache/camel-kamelets.git", "v0.3.0"));
    }

    public static ReadOnlyCatalog getReadOnlyCatalog() {
        return readOnlyCatalog;
    }

    public static Boolean isWarmedUp() {
        return warmedUp;
    }

    private static void addCatalog(ParseCatalog catalog) {
        catalog.parse()
                .thenApply(steps -> c.store(steps))
                .thenApply(complete -> warmedUp = true)
                .thenRun(() -> log.trace("Catalog WARMED UP"));
    }
}
