package io.zimara.backend.api;

import io.zimara.backend.metadata.catalog.InMemoryCatalog;
import io.zimara.backend.metadata.catalog.ReadOnlyCatalog;
import io.zimara.backend.metadata.parser.kamelet.KameletParseCatalog;

public class Catalog {

    private Catalog() {}

    private static final ReadOnlyCatalog readOnlyCatalog;

    static {
        KameletParseCatalog kameletParser = new KameletParseCatalog("https://github.com/apache/camel-kamelets.git", "v0.2.1");

        InMemoryCatalog c = new InMemoryCatalog();
        c.store(kameletParser.parse().result());
        readOnlyCatalog = new ReadOnlyCatalog(c);
    }

    public static ReadOnlyCatalog getReadOnlyCatalog() {
        return readOnlyCatalog;
    }
}
