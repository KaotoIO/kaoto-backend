package io.zimara.backend.api.service.deployment;

import io.quarkus.test.junit.QuarkusTest;
import io.zimara.backend.api.metadata.catalog.StepCatalog;
import io.zimara.backend.api.metadata.catalog.ViewDefinitionCatalog;
import io.zimara.backend.api.service.viewdefinitions.ViewDefinitionService;
import io.zimara.backend.model.deployment.kamelet.KameletBinding;
import io.zimara.backend.model.step.kamelet.KameletStep;
import io.zimara.backend.model.view.ViewDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

import javax.inject.Inject;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@QuarkusTest
class DeploymentServiceTest {

    @Inject
    StepCatalog stepCatalog;

    @Inject
    ViewDefinitionCatalog viewCatalog;

    @Inject
    DeploymentService deploymentService;

    @Inject
    ViewDefinitionService viewDefinitionService;

    private static String binding = "";

    @BeforeAll
    static void setup() throws URISyntaxException, IOException {
        binding = Files.readString(Path.of(DeploymentServiceTest.class.getResource("../../resource/twitter-search-source-binding.yaml").toURI()));
    }

    @BeforeEach
    void ensureCatalog() {
        stepCatalog.waitForWarmUp().join();
        viewCatalog.waitForWarmUp().join();
    }

    @Test
    void yaml() {
        List<ViewDefinition> views = viewDefinitionService.views(binding);

        String res = deploymentService.yaml("twitter-search-source-binding", views.get(0).getSteps().toArray(new KameletStep[0]));

        Representer representer = new Representer();
        representer.getPropertyUtils().setAllowReadOnlyProperties(true);

        Yaml yaml = new Yaml(new Constructor(KameletBinding.class), representer);
        var expected = yaml.load(binding);
        String expectedStr = yaml.dump(expected);

        Assertions.assertEquals(expectedStr, res);
    }
}