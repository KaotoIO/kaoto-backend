package io.kaoto.backend.api.metadata.catalog;

import io.fabric8.kubernetes.client.KubernetesClient;
import io.kaoto.backend.model.configuration.Repository;
import io.kaoto.backend.model.step.Step;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class StepCatalogTest {
    @Inject
    private Instance<StepCatalogParser> stepCatalogParsers;
    @Inject
    private KubernetesClient kclient;
    @Inject
    private StepCatalog mainStepCatalog;

    //helping class with all information about version/repo/file for Kamelets
    private record KameletInfo() {
        static final String REPO = "https://github.com/apache/camel-kamelets";
        //don't rename, update-resources.sh script uses/updates this
        static final String KAMELET_VERSION = "3.21.0";
        static final String TAG = "v" + KAMELET_VERSION;

        static final String FILE = "camel-kamelets-" + KAMELET_VERSION + ".jar";
        static final int EXPECT_NUMBER = 215;

        static String getFileFromRepo() {
            return String.format("%s/archive/refs/tags/%s.zip", REPO, TAG);
        }

        static String getFileFromCentral() {
            return String.format(
                    "https://repo1.maven.org/maven2/org/apache/camel/kamelets/camel-kamelets/%s/camel-kamelets-%s.jar",
                    KAMELET_VERSION, KAMELET_VERSION);
        }

        static String getFileFromResources() {
            return "resource://" + FILE;
        }
    }

    //helping class with all information about version/repo/file for Kaoto component metadata
    private record ComponentMetadataInfo() {
        static final String REPO = "https://github.com/KaotoIO/camel-component-metadata";
        static final String TAG = "test-202308";
        static final String FILE = "camel-component-metadata.zip";
        static final int EXPECT_REPO_NUMBER = 54;
        static final int EXPECT_FILE_NUMBER = 54;

        static String getFileFromRepo() {
            return String.format("%s/archive/refs/tags/%s.zip", REPO, TAG);
        }

        static String getFileFromResources() {
            return "resource://" + FILE;
        }
    }

    //helping class with all information about version/repo/file for Camel connectors
    private record CamelConnectorsInfo() {
        //don't rename, update-resources.sh script uses/updates this
        static final String CAMEL_CONNECTORS_VERSION = "3.21.0";
        static final String FILE = "camel-connectors-" + CAMEL_CONNECTORS_VERSION + ".zip";

        static final int EXPECT_NUMBER = 847;

        static String getFileFromResources() {
            return "resource://" + FILE;
        }
    }

    //helping class with all information about in-memory steps
    private record InMemoryStepsInfo() {
        //can be more steps (also with different types) in the future
        static final int CAMEL_REST_DSL_IN_MEMORY_STEPS = 11;

        static int getAllInMemoryStepsNumber() {
            return CAMEL_REST_DSL_IN_MEMORY_STEPS;
        }

    }

    private static Stream<Arguments> provideTestParametersForGitRepoTest() {
        return Stream.of(
                Arguments.of(KameletInfo.REPO + ".git", KameletInfo.TAG, KameletInfo.EXPECT_NUMBER),
                Arguments.of(ComponentMetadataInfo.REPO + ".git",
                        ComponentMetadataInfo.TAG, ComponentMetadataInfo.EXPECT_REPO_NUMBER)
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestParametersForGitRepoTest")
    void testParseFromGit(String repo, String tag, int expectNumbers) {
        StepRepository stepRepository = new StepRepository(
                Optional.empty(),
                Optional.of(List.of(
                        new GitRepository(repo, tag, true, "all"))),
                Optional.empty());
        tryLoadSteps(stepRepository, repo + ":" + tag, expectNumbers);
    }

    private static Stream<Arguments> provideTestParametersForJarRepoTest() {
        return Stream.of(
                //kamelets
                Arguments.of(KameletInfo.getFileFromRepo(), KameletInfo.EXPECT_NUMBER),
                Arguments.of(KameletInfo.getFileFromCentral(), KameletInfo.EXPECT_NUMBER),
                Arguments.of(KameletInfo.getFileFromResources(), KameletInfo.EXPECT_NUMBER),
                //component-metadata
                Arguments.of(ComponentMetadataInfo.getFileFromRepo(), ComponentMetadataInfo.EXPECT_REPO_NUMBER),
                Arguments.of(ComponentMetadataInfo.getFileFromResources(), ComponentMetadataInfo.EXPECT_FILE_NUMBER),
                //connectors
                Arguments.of(CamelConnectorsInfo.getFileFromResources(), CamelConnectorsInfo.EXPECT_NUMBER)
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestParametersForJarRepoTest")
    void testParseFromJar(String resource, int expectNumbers) {
        StepRepository stepRepository = new StepRepository(
                Optional.of(List.of(
                        new LocationRepository(resource, true, "all"))),
                Optional.empty(),
                Optional.empty());
        tryLoadSteps(stepRepository, resource, expectNumbers);
    }

    @Test
    void testSameResourceFromMoreRepositories() {
        StepRepository stepRepository = new StepRepository(
                Optional.of(List.of(
                        new LocationRepository(KameletInfo.getFileFromCentral(), true, "all"),
                        new LocationRepository(KameletInfo.getFileFromRepo(), true, "all"))),
                Optional.of(List.of(
                        new GitRepository(KameletInfo.REPO + ".git", KameletInfo.TAG, true, "all"))),
                Optional.empty());
        tryLoadSteps(stepRepository, KameletInfo.getFileFromCentral() + " + " + KameletInfo.getFileFromRepo(),
                KameletInfo.EXPECT_NUMBER);
    }

    @Test
    void testMultipleResource() {
        StepRepository stepRepository = new StepRepository(
                Optional.empty(),
                Optional.of(List.of(
                        new GitRepository(KameletInfo.REPO + ".git", KameletInfo.TAG, true, "all"),
                        new GitRepository(ComponentMetadataInfo.REPO + ".git", ComponentMetadataInfo.TAG,
                                true, "all"))),
                Optional.empty());
        tryLoadSteps(stepRepository, KameletInfo.REPO + " + " + ComponentMetadataInfo.REPO,
                KameletInfo.EXPECT_NUMBER + ComponentMetadataInfo.EXPECT_REPO_NUMBER);
    }

    private void tryLoadSteps(StepRepository stepRepository, String resource, int expectNumbers) {
        tryLoadSteps(stepRepository, resource, expectNumbers, false);
    }

    private void tryLoadSteps(StepRepository stepRepository, String resource, int expectNumbers,
                              boolean includeInMemorySteps) {
        StepCatalog anotherStepCatalog = new StepCatalog();
        anotherStepCatalog.includeInMemoryCatalogs = includeInMemorySteps;
        anotherStepCatalog.setStepCatalogParsers(stepCatalogParsers);
        anotherStepCatalog.setKclient(kclient);
        anotherStepCatalog.setRepository(stepRepository);
        anotherStepCatalog.loadParsers();
        anotherStepCatalog.warmUpCatalog();
        Collection<Step> steps = anotherStepCatalog.getReadOnlyCatalog().getAll();
        assertThat(steps)
                .as("Test that all expected steps '" + expectNumbers + "' was loaded from resource: " + resource)
                .hasSize(expectNumbers)
                .as("Test that catalog contains steps only from resource '" + resource +
                        "' and not from different resources.")
                .hasSizeLessThan(mainStepCatalog.getReadOnlyCatalog().getAll().size());
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testInMemorySteps(boolean shouldContains) {
        StepRepository stepRepository = new StepRepository(
                Optional.empty(),
                Optional.empty(),
                Optional.empty());
        int numberOfSteps = shouldContains ? InMemoryStepsInfo.getAllInMemoryStepsNumber() : 0;
        tryLoadSteps(stepRepository, "In memory steps (loaded from no repo)", numberOfSteps, shouldContains);
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
