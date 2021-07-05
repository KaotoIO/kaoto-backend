package io.zimara.backend.metadata.parser.kamelet;

import io.quarkus.test.junit.QuarkusTest;
import io.zimara.backend.metadata.catalog.InMemoryCatalog;
import io.zimara.backend.model.Step;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

@QuarkusTest
class KameletParseCatalogTest {

    private Logger log = Logger.getLogger(KameletParseCatalogTest.class);

    @Test
    void getSteps() {
        KameletParseCatalog kameletParser = new KameletParseCatalog("https://github.com/apache/camel-kamelets.git", "v0.2.1");
        InMemoryCatalog catalog = new InMemoryCatalog();

        List<Step> steps = kameletParser.parse().join();
        Assertions.assertTrue(catalog.store(steps));
        Assertions.assertEquals(51, steps.size());
        Assertions.assertEquals(steps.size(), catalog.getAll().size());

        String name = "ftp-source";
        Step step = catalog.searchStepByName(name);
        Assertions.assertNotNull(step);
        Assertions.assertEquals(name, step.getID());
        Assertions.assertEquals(name, step.getName());
        Assertions.assertEquals("KAMELET", step.getSubType());
        Assertions.assertEquals("CONNECTOR", step.getType());
    }
}