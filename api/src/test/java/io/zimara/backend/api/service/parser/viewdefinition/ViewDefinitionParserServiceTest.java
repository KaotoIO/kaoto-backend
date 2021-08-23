package io.zimara.backend.api.service.parser.viewdefinition;

import io.quarkus.test.junit.QuarkusTest;
import io.zimara.backend.api.metadata.catalog.ViewDefinitionCatalog;
import io.zimara.backend.model.step.Step;
import io.zimara.backend.model.step.kamelet.KameletStep;
import io.zimara.backend.model.view.ViewDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class ViewDefinitionParserServiceTest {

    @Inject
    ViewDefinitionParserService viewDefinitionParserService;

    @Inject
    ViewDefinitionCatalog viewCatalog;

    @BeforeEach
    void ensureCatalog() {
        viewCatalog.waitForWarmUp().join();
    }

    @Test
    void checkConstraintsBySize() {
        List<Step> steps = new LinkedList<>();
        List<ViewDefinition> views = viewDefinitionParserService.parse(steps);
        Assertions.assertEquals(0, views.size());

        steps.add(new KameletStep());
        views = viewDefinitionParserService.parse(steps);
        Assertions.assertEquals(0, views.size());

        steps.add(new KameletStep());
        views = viewDefinitionParserService.parse(steps);
        Assertions.assertEquals(2, views.size());

        steps.add(new KameletStep());
        views = viewDefinitionParserService.parse(steps);
        Assertions.assertEquals(2, views.size());
    }
}