package io.kaoto.backend.api.metadata.catalog;

import io.fabric8.kubernetes.client.KubernetesClient;
import io.kaoto.backend.model.configuration.Repository;
import io.kaoto.backend.model.step.Step;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class StepCatalogTest {
    @Inject
    private Instance<StepCatalogParser> stepCatalogParsers;
    @Inject
    private KubernetesClient kclient;
    @Inject
    private StepCatalog mainStepCatalog;

    @ParameterizedTest
    @CsvSource({"https://github.com/KaotoIO/camel-component-metadata.git,test-202308",
            "https://github.com/apache/camel-kamelets.git,v3.21.0"})
    void testParseFromGit(String repo, String tag) {
        StepRepository stepRepository = new StepRepository(
                Optional.empty(),
                Optional.of(List.of(
                        new GitRepository(repo, tag, true, "all"))),
                Optional.empty());
        tryLoadSteps(stepRepository, repo + ":" + tag);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "https://github.com/KaotoIO/camel-component-metadata/archive/refs/tags/test-202308.zip",
            "https://repo1.maven.org/maven2/org/apache/camel/kamelets/camel-kamelets/3.21.0/camel-kamelets-3.21.0.jar",
            "resource://camel-component-metadata.zip",
            "resource://camel-kamelets-3.21.0.jar",
            "resource://camel-connectors-3.21.0.zip"
    })
    void testParseFromFile(String resource) {
        StepRepository stepRepository = new StepRepository(
                Optional.of(List.of(
                        new LocationRepository(resource, true, "all"))),
                Optional.empty(),
                Optional.empty());
        tryLoadSteps(stepRepository, resource);
    }

    private void tryLoadSteps(StepRepository stepRepository, String resource) {
        StepCatalog anotherStepCatalog = new StepCatalog();
        anotherStepCatalog.setStepCatalogParsers(stepCatalogParsers);
        anotherStepCatalog.setKclient(kclient);
        anotherStepCatalog.setRepository(stepRepository);
        anotherStepCatalog.loadParsers();
        anotherStepCatalog.warmUpCatalog();
        Collection<Step> steps = anotherStepCatalog.getReadOnlyCatalog().getAll();
        assertThat(steps)
                .isNotEmpty()
                .as("Test that more than 50 steps was loaded from resource: " + resource)
                .hasSizeGreaterThan(50)
                .as("Test that catalog contains steps only from resource '" + resource +
                        "' and not from different resources.")
                .hasSizeLessThan(mainStepCatalog.getReadOnlyCatalog().getAll().size());
    }


    // GitRepository implementation used for testing
    private record GitRepository(String url, String tag, boolean ifNoCluster, String kind) implements Repository.Git {
    }

    // LocationRepository implementation used for testing
    private record LocationRepository(String url, boolean ifNoCluster, String kind) implements Repository.Location {
    }

    // StepRepository implementation used for testing
    private record StepRepository(Optional<List<Location>> jar, Optional<List<Git>> git,
                                  Optional<List<Location>> localFolder) implements StepCatalog.StepRepository {
    }
}
