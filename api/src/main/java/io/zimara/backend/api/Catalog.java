package io.zimara.backend.api;

import io.zimara.backend.metadata.catalog.InMemoryCatalog;
import io.zimara.backend.metadata.catalog.ReadOnlyCatalog;
import io.zimara.backend.metadata.parser.kamelet.KameletParseCatalog;

public class Catalog {

    private Catalog() {
    }

    private static InMemoryCatalog c = new InMemoryCatalog();
    private final static ReadOnlyCatalog readOnlyCatalog = new ReadOnlyCatalog(c);
    private static Boolean warmedUp = false;

    static {
        new KameletParseCatalog("https://github.com/apache/camel-kamelets.git", "v0.3.0")
                .parse()
                .thenApply(steps -> c.store(steps))
                .thenApply(complete -> warmedUp = complete);
    }

    public static ReadOnlyCatalog getReadOnlyCatalog() {
        return readOnlyCatalog;
    }

    public static Boolean isWarmedUp() {
        return warmedUp;
    }
}
