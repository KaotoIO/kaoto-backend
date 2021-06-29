package io.zimara.backend.metadata.parser.kamelet;

import io.quarkus.arc.log.LoggerName;
import io.zimara.backend.metadata.catalog.InMemoryCatalog;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class KameletParseCatalogTest {

    @LoggerName("KameletParseCatalogTest")
    private Logger log = Logger.getLogger(KameletParseCatalogTest.class);

    @Test
    void getSteps() throws GitAPIException, IOException {
        KameletParseCatalog kameletParser = null;
        kameletParser = new KameletParseCatalog("https://github.com/apache/camel-kamelets.git", "v0.2.1");

        InMemoryCatalog catalog = new InMemoryCatalog();
        catalog.store(kameletParser.parse());
        Assertions.assertEquals(51, catalog.getAll().size());
    }
}