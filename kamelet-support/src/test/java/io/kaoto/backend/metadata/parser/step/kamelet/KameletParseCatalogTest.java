package io.kaoto.backend.metadata.parser.step.kamelet;

import io.quarkus.test.junit.QuarkusTest;
import io.kaoto.backend.metadata.ParseCatalog;
import io.kaoto.backend.metadata.catalog.InMemoryCatalog;
import io.kaoto.backend.model.Metadata;
import io.kaoto.backend.model.step.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class KameletParseCatalogTest {

    public KameletParseCatalog getParseCatalog() {
        return parseCatalog;
    }

    @Inject
    public void setParseCatalog(final KameletParseCatalog parseCatalog) {
        this.parseCatalog = parseCatalog;
    }

    private KameletParseCatalog parseCatalog;

    @Test
    void getSteps() {
        ParseCatalog<Step> kameletParser =
                parseCatalog.getParser(
                        "https://github.com/apache/camel-kamelets.git",
                        "v0.5.0");
        InMemoryCatalog<Step> catalog = new InMemoryCatalog<>();

        List<Step> steps = kameletParser.parse().join();
        Assertions.assertTrue(catalog.store(steps));
        assertEquals(catalog.getAll().size(), steps.size());

        String name = "ftp-source";
        String[] required = new String[]{
                "connectionHost", "connectionPort",
                "username", "password", "directoryName"};
        Step step = catalog.searchStepByName(name);

        assertEquals(step.getRequired().size(), required.length);
        Arrays.stream(required).allMatch(
                property -> step.getRequired().contains(property));

        Assertions.assertNotNull(step);
        assertEquals(name, step.getId());
        assertEquals(name, step.getName());
        assertEquals("Kamelet", step.getKind());
        assertEquals("START", step.getType());
        Assertions.assertNotNull(step.getParameters());
        assertEquals(8, step.getParameters().size());
        for (var p : step.getParameters()) {
            Assertions.assertNotNull(p.getType());
            Assertions.assertNotNull(p.getTitle());
            Assertions.assertNotNull(p.getId());
            Assertions.assertNotNull(p.getType());
            Assertions.assertNotNull(p.getDescription());
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
        assertEquals(0, steps.size());
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

        assertEquals(stepsJar.size(), stepsGit.size());

        stepsJar.sort(Comparator.comparing(Metadata::getId));
        stepsGit.sort(Comparator.comparing(Metadata::getId));

        Assertions.assertIterableEquals(stepsJar, stepsGit);
    }

}
