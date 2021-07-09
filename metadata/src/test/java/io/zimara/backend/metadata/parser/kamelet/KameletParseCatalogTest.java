package io.zimara.backend.metadata.parser.kamelet;

import io.quarkus.test.junit.QuarkusTest;
import io.zimara.backend.metadata.catalog.InMemoryCatalog;
import io.zimara.backend.model.Step;
import io.zimara.backend.model.step.kamelet.KameletStep;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

@QuarkusTest
class KameletParseCatalogTest {

    @Test
    void getSteps() {
        KameletParseCatalog kameletParser = new KameletParseCatalog("https://github.com/apache/camel-kamelets.git", "v0.3.0");
        InMemoryCatalog catalog = new InMemoryCatalog();

        List<Step> steps = kameletParser.parse().join();
        Assertions.assertTrue(catalog.store(steps));
        Assertions.assertEquals(234, steps.size());
        Assertions.assertEquals(117, catalog.getAll().size());

        String name = "ftp-source";
        KameletStep step = (KameletStep) catalog.searchStepByName(name);
        Assertions.assertNotNull(step);
        Assertions.assertEquals(name, step.getID());
        Assertions.assertEquals(name, step.getName());
        Assertions.assertEquals("KAMELET", step.getSubType());
        Assertions.assertEquals("CONNECTOR", step.getType());
        Assertions.assertNotNull(step.getParameters());
        Assertions.assertEquals(8, step.getParameters().size());
        for(var p : step.getParameters()) {
            Assertions.assertNotNull(p.getType());
            Assertions.assertNotNull(p.getLabel());
            Assertions.assertNotNull(p.getId());
            Assertions.assertNotNull(p.getDefault());
        }
    }
}