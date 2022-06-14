package io.kaoto.backend.api.service.parser.viewdefinition;

import io.quarkus.test.junit.QuarkusTest;
import io.kaoto.backend.api.metadata.catalog.ViewDefinitionCatalog;
import io.kaoto.backend.api.service.viewdefinition.parser.GenericViewDefinitionParserService;
import io.kaoto.backend.model.step.Step;
import io.kaoto.backend.model.view.ConstraintOperation;
import io.kaoto.backend.model.view.ViewDefinition;
import io.kaoto.backend.model.view.ViewDefinitionConstraint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@QuarkusTest
class GenericViewDefinitionParserServiceTest {

    private GenericViewDefinitionParserService viewDefinitionParserService;

    private ViewDefinitionCatalog viewCatalog;

    @Inject
    public void setViewDefinitionParserService(
            final GenericViewDefinitionParserService viewParser) {
        this.viewDefinitionParserService = viewParser;
    }

    @Inject
    public void setViewCatalog(final ViewDefinitionCatalog viewCatalog) {
        this.viewCatalog = viewCatalog;
    }

    @BeforeEach
    void ensureCatalog() {
        viewCatalog.waitForWarmUp().join();
    }

    @Test
    void checkConstraintsBySize() {
        List<Step> steps = new LinkedList<>();
        List<ViewDefinition> views =
                viewDefinitionParserService.parse(steps, "integration");
        Assertions.assertEquals(0, views.size());
        for (ViewDefinition v : views) {
            Assertions.assertEquals("integration",
                    v.getProperties().get("name"));
        }

        steps.add(new Step());
        views = viewDefinitionParserService.parse(steps, "integration");
        Assertions.assertEquals(0, views.size());
        for (ViewDefinition v : views) {
            Assertions.assertEquals("integration",
                    v.getProperties().get("name"));
        }

        Step s = new Step();
        s.setKind("KAMELET");
        steps.add(s);
        views = viewDefinitionParserService.parse(steps, "integration");
        Assertions.assertEquals(2, views.size());
        for (ViewDefinition v : views) {
            Assertions.assertEquals("integration",
                    v.getProperties().get("name"));
        }

        steps.add(new Step());
        views = viewDefinitionParserService.parse(steps, "integration");
        Assertions.assertEquals(2, views.size());
        for (ViewDefinition v : views) {
            Assertions.assertEquals("integration",
                    v.getProperties().get("name"));
        }
    }

    @Test
    void appliesToSize() {
        List<Step> steps = new LinkedList<>();
        ViewDefinition view = new ViewDefinition();
        view.setConstraints(new ArrayList<>());
        ViewDefinitionConstraint viewDefinitionConstraint =
                new ViewDefinitionConstraint();
        viewDefinitionConstraint.setMandatory(false);
        viewDefinitionConstraint.setOperation(
                ConstraintOperation.SIZE_GREATER_THAN);
        viewDefinitionConstraint.setParameter("0");
        view.getConstraints().add(viewDefinitionConstraint);
        Assertions.assertFalse(
                viewDefinitionParserService.appliesTo(steps, view));

        viewDefinitionConstraint = new ViewDefinitionConstraint();
        viewDefinitionConstraint.setMandatory(false);
        viewDefinitionConstraint.setOperation(
                ConstraintOperation.SIZE_EQUALS);
        viewDefinitionConstraint.setParameter("0");
        view.getConstraints().add(viewDefinitionConstraint);
        Assertions.assertTrue(
                viewDefinitionParserService.appliesTo(steps, view));

        view = new ViewDefinition();
        view.setConstraints(new ArrayList<>());
        viewDefinitionConstraint = new ViewDefinitionConstraint();
        viewDefinitionConstraint.setMandatory(false);
        viewDefinitionConstraint.setOperation(
                ConstraintOperation.SIZE_SMALLER_THAN);
        viewDefinitionConstraint.setParameter("1");
        view.getConstraints().add(viewDefinitionConstraint);
        Assertions.assertTrue(
                viewDefinitionParserService.appliesTo(steps, view));
    }

    @Test
    void appliesToConnector() {

        final String stepName = "test-name";
        final String stepId = "test-id";

        List<Step> steps = new LinkedList<>();
        Step step = new Step();
        step.setName(stepName);
        step.setId(stepId);
        step.setKind("Kamelet");
        steps.add(step);

        ViewDefinition view = new ViewDefinition();
        view.setConstraints(new ArrayList<>());
        ViewDefinitionConstraint viewDefinitionConstraint =
                new ViewDefinitionConstraint();
        viewDefinitionConstraint.setMandatory(false);
        viewDefinitionConstraint.setOperation(
                ConstraintOperation.CONTAINS_STEP_TYPE);
        viewDefinitionConstraint.setParameter("no-type");
        view.getConstraints().add(viewDefinitionConstraint);
        Assertions.assertFalse(
                viewDefinitionParserService.appliesTo(steps, view));

        viewDefinitionConstraint = new ViewDefinitionConstraint();
        viewDefinitionConstraint.setMandatory(false);
        viewDefinitionConstraint.setOperation(
                ConstraintOperation.CONTAINS_STEP_TYPE);
        viewDefinitionConstraint.setParameter("KAMELET");
        view.getConstraints().add(viewDefinitionConstraint);
        Assertions.assertTrue(
                viewDefinitionParserService.appliesTo(steps, view));

        view = new ViewDefinition();
        view.setConstraints(new ArrayList<>());
        viewDefinitionConstraint = new ViewDefinitionConstraint();
        viewDefinitionConstraint.setMandatory(false);
        viewDefinitionConstraint.setOperation(
                ConstraintOperation.CONTAINS_STEP_NAME);
        viewDefinitionConstraint.setParameter("no-name");
        view.getConstraints().add(viewDefinitionConstraint);
        Assertions.assertFalse(
                viewDefinitionParserService.appliesTo(steps, view));

        viewDefinitionConstraint = new ViewDefinitionConstraint();
        viewDefinitionConstraint.setMandatory(false);
        viewDefinitionConstraint.setOperation(
                ConstraintOperation.CONTAINS_STEP_NAME);
        viewDefinitionConstraint.setParameter(stepName);
        view.getConstraints().add(viewDefinitionConstraint);
        Assertions.assertTrue(
                viewDefinitionParserService.appliesTo(steps, view));

        view = new ViewDefinition();
        view.setConstraints(new ArrayList<>());
        viewDefinitionConstraint = new ViewDefinitionConstraint();
        viewDefinitionConstraint.setMandatory(false);
        viewDefinitionConstraint.setOperation(
                ConstraintOperation.CONTAINS_STEP_IDENTIFIER);
        viewDefinitionConstraint.setParameter("no-id");
        view.getConstraints().add(viewDefinitionConstraint);
        Assertions.assertFalse(
                viewDefinitionParserService.appliesTo(steps, view));

        viewDefinitionConstraint = new ViewDefinitionConstraint();
        viewDefinitionConstraint.setMandatory(false);
        viewDefinitionConstraint.setOperation(
                ConstraintOperation.CONTAINS_STEP_IDENTIFIER);
        viewDefinitionConstraint.setParameter(stepId);
        view.getConstraints().add(viewDefinitionConstraint);
        Assertions.assertTrue(
                viewDefinitionParserService.appliesTo(steps, view));
    }
}
