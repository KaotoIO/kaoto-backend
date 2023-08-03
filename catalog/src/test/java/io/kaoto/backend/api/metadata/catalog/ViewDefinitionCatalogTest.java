package io.kaoto.backend.api.metadata.catalog;

import io.kaoto.backend.model.configuration.Repository;
import io.kaoto.backend.model.view.ViewDefinition;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@QuarkusTest
public class ViewDefinitionCatalogTest {

    private static final String REPO = "https://github.com/KaotoIO/kaoto-viewdefinition-catalog";
    private static final String TAG = "test-202308";
    private static final int EXPECTED_VIEW_DEFINITIONS = 13;


    @Test
    void testParseFromGit() {
        ViewDefinitionRepository stepRepository = new ViewDefinitionRepository(
                Optional.empty(),
                Optional.of(List.of(
                        new GitRepository(REPO + ".git", TAG, true, "all"))),
                Optional.empty());
        tryLoadViewDefinitions(stepRepository);
    }

    @Test
    void testParseFromFile() {
        ViewDefinitionRepository stepRepository = new ViewDefinitionRepository(
                Optional.of(List.of(new LocationRepository(REPO + "/archive/refs/tags/" + TAG + ".zip", true, "all"))),
                Optional.empty(), Optional.empty());
        tryLoadViewDefinitions(stepRepository);
    }

    private void tryLoadViewDefinitions(ViewDefinitionRepository viewDefinitionRepository) {
        ViewDefinitionCatalog viewDefinitionCatalog = new ViewDefinitionCatalog();
        viewDefinitionCatalog.setRepository(viewDefinitionRepository);
        viewDefinitionCatalog.loadParsers();
        viewDefinitionCatalog.warmUpCatalog();
        Collection<ViewDefinition> allViews = viewDefinitionCatalog.getReadOnlyCatalog().getAll();
        assertThat(allViews)
                .isNotEmpty()
                .hasSize(EXPECTED_VIEW_DEFINITIONS);
    }

    // GitRepository implementation used for testing
    private record GitRepository(String url, String tag, boolean ifNoCluster, String kind) implements Repository.Git {
    }

    // LocationRepository implementation used for testing
    private record LocationRepository(String url, boolean ifNoCluster, String kind) implements Repository.Location {
    }

    // StepRepository implementation used for testing
    private record ViewDefinitionRepository(Optional<List<Location>> jar, Optional<List<Git>> git,
                                            Optional<List<Location>> localFolder)
            implements ViewDefinitionCatalog.ViewDefinitionRepository {
    }

}
