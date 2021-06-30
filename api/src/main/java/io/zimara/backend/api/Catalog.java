package io.zimara.backend.api;

import io.zimara.backend.metadata.catalog.InMemoryCatalog;
import io.zimara.backend.metadata.catalog.ReadOnlyCatalog;
import io.zimara.backend.metadata.parser.kamelet.KameletParseCatalog;

public class Catalog {

    private static final ReadOnlyCatalog catalog;

    static {
        KameletParseCatalog kameletParser = null;
        kameletParser = new KameletParseCatalog("https://github.com/apache/camel-kamelets.git", "v0.2.1");

        InMemoryCatalog c = new InMemoryCatalog();
        c.store(kameletParser.parse().result());
        catalog = new ReadOnlyCatalog(c);
    }

    public static ReadOnlyCatalog getCatalog() {
        return catalog;
    }
}
