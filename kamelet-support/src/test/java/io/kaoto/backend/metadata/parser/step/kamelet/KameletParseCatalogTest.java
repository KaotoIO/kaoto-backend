package io.kaoto.backend.metadata.parser.step.kamelet;

import io.fabric8.kubernetes.client.KubernetesClient;
import io.kaoto.backend.metadata.ParseCatalog;
import io.kaoto.backend.metadata.catalog.InMemoryCatalog;
import io.kaoto.backend.model.Metadata;
import io.kaoto.backend.model.deployment.kamelet.Kamelet;
import io.kaoto.backend.model.step.Step;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.kubernetes.client.WithKubernetesTestServer;
import org.apache.commons.io.IOUtils;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
@WithKubernetesTestServer
class KameletParseCatalogTest {

    private static final String FILE_NAME = "camel-kamelets-3.20.2.jar";

    private Logger log = Logger.getLogger(KameletParseCatalogTest.class);

    public KameletParseCatalog getParseCatalog() {
        return parseCatalog;
    }

    @Inject
    public void setParseCatalog(final KameletParseCatalog parseCatalog) {
        this.parseCatalog = parseCatalog;
    }

    private KameletParseCatalog parseCatalog;

    @Inject
    public void setKubernetesClient(final KubernetesClient kubernetesClient) {
        this.kubernetesClient = kubernetesClient;
    }

    private KubernetesClient kubernetesClient;

    @Test
    void getSteps() {
        ParseCatalog<Step> kameletParser =
                parseCatalog.getParser("https://github.com/apache/camel-kamelets.git", "v0.5.0");
        InMemoryCatalog<Step> catalog = new InMemoryCatalog<>();

        List<Step> steps = kameletParser.parse().join();
        assertTrue(catalog.store(steps));
        assertEquals(catalog.getAll().size(), steps.size());

        String name = "ftp-source";
        String[] required = new String[]{"connectionHost", "connectionPort", "username", "password", "directoryName"};
        Step step = catalog.searchByName(name).stream().findAny().get();

        assertEquals(step.getRequired().size(), required.length);
        assertTrue(Arrays.stream(required).allMatch(property -> step.getRequired().contains(property)));

        Assertions.assertNotNull(step);
        assertEquals(name + "-" + step.getType(), step.getId());
        assertEquals(name, step.getName());
        assertEquals("Kamelet", step.getKind());
        assertEquals(Step.START, step.getType());
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
    void getCamelConnector() {
        ParseCatalog<Step> kameletParser =
                parseCatalog.getParser("https://github.com/KaotoIO/camel-component-metadata.git", "test");
        InMemoryCatalog<Step> catalog = new InMemoryCatalog<>();

        List<Step> steps = kameletParser.parse().join();
        assertTrue(catalog.store(steps));
        assertEquals(catalog.getAll().size(), steps.size());

        String name = "dropbox";
        String[] required = new String[]{"operation", "accessToken"};
        final Step step = catalog.searchByName(name).stream().findAny().get();

        assertEquals(step.getRequired().size(), required.length);
        assertTrue(Arrays.stream(required).allMatch(property -> step.getRequired().contains(property)));

        Assertions.assertNotNull(step);
        assertEquals(name + "-" + step.getType(), step.getId());
        assertEquals(name, step.getName());
        assertEquals("Camel-Connector", step.getKind());
        assertEquals(Step.MIDDLE, step.getType());
        Assertions.assertNotNull(step.getParameters());
        assertEquals(6, step.getParameters().size());
        for (var p : step.getParameters()) {
            Assertions.assertNotNull(p.getType());
            Assertions.assertNotNull(p.getTitle());
            Assertions.assertNotNull(p.getId());
            Assertions.assertNotNull(p.getType());
            Assertions.assertNotNull(p.getDescription());
        }

        name = "choice";
        Step step2 = catalog.searchByName(name).stream().findAny().get();
        Assertions.assertNotNull(step2);
        assertEquals(name + "-" + step.getType(), step2.getId());
        assertEquals(name, step2.getName());
        assertEquals("EIP-BRANCH", step2.getKind());
        assertEquals(Step.MIDDLE, step2.getType());
        assertEquals(1, step2.getMinBranches());
        assertEquals(-1, step2.getMaxBranches());
    }

    @Test
    void wrongUrlSilentlyFails() {
        ParseCatalog<Step> kameletParser = parseCatalog.getParser("https://nothing/wrong/url.git", "");

        List<Step> steps = kameletParser.parse().join();
        Assertions.assertNotNull(steps);
        assertEquals(0, steps.size());
    }

    @Test
    void localFolder() throws URISyntaxException {

        ParseCatalog<Step> kameletParser =
                parseCatalog.getLocalFolder(Path.of(
                        KameletParseCatalogTest.class.getResource("../../../../api/service/step/parser/kamelet/")
                                .toURI()));
        List<Step> steps = kameletParser.parse().join().stream().filter(Objects::nonNull).collect(Collectors.toList());

        assertEquals(5, steps.size());
    }

    @Test
    void testCluster() {
        kubernetesClient.resources(Kamelet.class)
                .inNamespace("default")
                .load(KameletParseCatalogTest.class
                        .getResource("../../../../api/service/step/parser/kamelet/dropbox-sink.kamelet.yaml"))
                .create();

        ParseCatalog<Step> kameletParser = parseCatalog.getParserFromCluster();
        List<Step> steps = kameletParser.parse().join();
        assertEquals(1, steps.size());
    }

    //reproducer for issue #656
    @Test
    void testClusterWithWholeKamelets() {
        int numberOfKamelets = loadAllKameletsFromResourcesIntoCluster();

        ParseCatalog<Step> kameletParser = parseCatalog.getParserFromCluster();
        List<Step> steps = kameletParser.parse().join();
        assertEquals(numberOfKamelets, steps.size());
    }

    @Test
    void compareJarAndGit() {

        ParseCatalog<Step> kameletParserGit =
                parseCatalog.getParser("https://github.com/apache/camel-kamelets.git", "v3.20.2");
        List<Step> stepsGit = kameletParserGit.parse().join();

        String jarUrl = "https://repo1.maven.org/maven2/org/apache/camel/"
                + "kamelets/camel-kamelets/3.20.2/" + FILE_NAME;

        ParseCatalog<Step> kameletParserJar =
                parseCatalog.getParser(
                        jarUrl);
        List<Step> stepsJar = kameletParserJar.parse().join();

        assertEquals(stepsJar.size(), stepsGit.size());

        stepsJar.sort(Comparator.comparing(Metadata::getId));
        stepsGit.sort(Comparator.comparing(Metadata::getId));

        Assertions.assertIterableEquals(stepsJar, stepsGit);
    }

    @Test
    void loadFromLocalZip() {
        String camelZip = "resource://" + FILE_NAME;
        InMemoryCatalog<Step> catalog = new InMemoryCatalog<>();

        ParseCatalog<Step> camelParser = parseCatalog.getParser(camelZip);
        List<Step> steps = camelParser.parse().join();

        assertTrue(catalog.store(steps));

        var salesforces = catalog.searchByName("salesforce-create-sink");
        assertNotNull(salesforces);
        assertTrue(!salesforces.isEmpty());

        salesforces = catalog.searchByName("salesforce-source");
        assertNotNull(salesforces);
        assertTrue(!salesforces.isEmpty());
    }

    @Test
//    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    void testSpeed() {
        String camelZip = "resource://" + FILE_NAME;
        InMemoryCatalog<Step> catalog = new InMemoryCatalog<>();
        ParseCatalog<Step> camelParser = parseCatalog.getParser(camelZip);
        List<Step> steps = camelParser.parse().join();
        assertTrue(catalog.store(steps));
    }

    public int loadAllKameletsFromResourcesIntoCluster() {
        int loadedResources = 0;
        try (ZipInputStream zis = new ZipInputStream(
                Objects.requireNonNull(this.getClass().getResourceAsStream("/" + FILE_NAME)))) {
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                if (zipEntry.getName().contains("kamelets/") && zipEntry.getName().contains(".yaml")) {
                    log.info(String.format("Loading Kamelet %s into cluster",
                            zipEntry.getName().replaceFirst("kamelets/", "")));
                    final String content = IOUtils.toString(new InputStreamReader(zis));
                    kubernetesClient.resource(content).inNamespace("default").createOrReplace();
                    loadedResources++;
                }
                zipEntry = zis.getNextEntry();
            }
        } catch (IOException e) {
            log.error("Problem with processing zip file.", e);
        }
        return loadedResources;
    }
}
