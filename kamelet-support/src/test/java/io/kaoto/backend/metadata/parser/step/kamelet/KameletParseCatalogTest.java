package io.kaoto.backend.metadata.parser.step.kamelet;

import io.quarkus.test.junit.QuarkusTest;
import io.kaoto.backend.metadata.ParseCatalog;
import io.kaoto.backend.metadata.catalog.InMemoryCatalog;
import io.kaoto.backend.model.Metadata;
import io.kaoto.backend.model.step.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Comparator;
import java.util.List;

@QuarkusTest
public class KameletParseCatalogTest {

    public KameletParseCatalog getParseCatalog() {
        return parseCatalog;
    }

    public void setParseCatalog(final KameletParseCatalog parseCatalog) {
        this.parseCatalog = parseCatalog;
    }

    @Inject
    private KameletParseCatalog parseCatalog;

    @Test
    void getSteps() {
        ParseCatalog<Step> kameletParser =
                parseCatalog.getParser(
                        "https://github.com/apache/camel-kamelets.git",
                        "v0.3.0");
        InMemoryCatalog<Step> catalog = new InMemoryCatalog<>();

        List<Step> steps = kameletParser.parse().join();
        Assertions.assertTrue(catalog.store(steps));
        Assertions.assertEquals(catalog.getAll().size(), steps.size());

        String name = "ftp-source";
        Step step = catalog.searchStepByName(name);
        Assertions.assertNotNull(step);
        Assertions.assertEquals(name, step.getId());
        Assertions.assertEquals(name, step.getName());
        Assertions.assertEquals("Kamelet", step.getKind());
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
        ParseCatalog<Step> kameletParser =
                parseCatalog.getParser(
                        "https://nothing/wrong/url.git",
                        "");

        List<Step> steps = kameletParser.parse().join();
        Assertions.assertNotNull(steps);
        Assertions.assertEquals(0, steps.size());
    }

    @Test
    void compareJarAndGit() {

        ParseCatalog<Step> kameletParserGit =
                parseCatalog.getParser(
                        "https://github.com/apache/camel-kamelets.git",
                        "v0.4.0");
        List<Step> stepsGit = kameletParserGit.parse().join();


        String jarUrl = "https://repo1.maven.org/maven2/org/apache/camel/"
                + "kamelets/camel-kamelets/0.4.0/camel-kamelets-0.4.0.jar";


        ParseCatalog<Step> kameletParserJar =
                parseCatalog.getParser(
                        jarUrl);
        List<Step> stepsJar = kameletParserJar.parse().join();

        Assertions.assertEquals(stepsJar.size(), stepsGit.size());

        stepsJar.sort(Comparator.comparing(Metadata::getId));
        stepsGit.sort(Comparator.comparing(Metadata::getId));

        Assertions.assertIterableEquals(stepsJar, stepsGit);
    }

}
