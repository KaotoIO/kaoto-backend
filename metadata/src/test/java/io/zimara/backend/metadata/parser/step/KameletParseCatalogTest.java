package io.zimara.backend.metadata.parser.step;

import io.quarkus.test.junit.QuarkusTest;
import io.zimara.backend.metadata.catalog.InMemoryCatalog;
import io.zimara.backend.model.step.Step;
import io.zimara.backend.model.step.kamelet.KameletStep;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

@QuarkusTest
class KameletParseCatalogTest {

    @Test
    void getSteps() {
        KameletParseCatalog kameletParser =
                new KameletParseCatalog(
                        "https://github.com/apache/camel-kamelets.git",
                        "v0.3.0");
        InMemoryCatalog<Step> catalog = new InMemoryCatalog<>();

        List<Step> steps = kameletParser.parse().join();
        Assertions.assertTrue(catalog.store(steps));
        Assertions.assertEquals(234, steps.size());
        Assertions.assertEquals(117, catalog.getAll().size());

        String name = "ftp-source";
        KameletStep step = (KameletStep) catalog.searchStepByName(name);
        Assertions.assertNotNull(step);
        Assertions.assertEquals(name, step.getId());
        Assertions.assertEquals(name, step.getName());
        Assertions.assertEquals("KAMELET", step.getSubType());
        Assertions.assertEquals("START", step.getType());
        Assertions.assertNotNull(step.getParameters());
        Assertions.assertEquals(8, step.getParameters().size());
        for (var p : step.getParameters()) {
            Assertions.assertNotNull(p.getType());
            Assertions.assertNotNull(p.getLabel());
            Assertions.assertNotNull(p.getId());
            Assertions.assertNotNull(p.getDefault());
        }
    }

    @Test
    void wrongUrlSilentlyFails() {
        KameletParseCatalog kameletParser =
                new KameletParseCatalog(
                        "https://nothing/wrong/url.git",
                        "");

        List<Step> steps = kameletParser.parse().join();
        Assertions.assertNotNull(steps);
        Assertions.assertEquals(0, steps.size());
    }
}
