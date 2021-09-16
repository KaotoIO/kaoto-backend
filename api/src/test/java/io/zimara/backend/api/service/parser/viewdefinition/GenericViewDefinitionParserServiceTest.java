package io.zimara.backend.api.service.parser.viewdefinition;

import io.quarkus.test.junit.QuarkusTest;
import io.zimara.backend.api.metadata.catalog.ViewDefinitionCatalog;
import io.zimara.backend.api.service.viewdefinition.parser.GenericViewDefinitionParserService;
import io.zimara.backend.model.step.Step;
import io.zimara.backend.model.step.kamelet.KameletStep;
import io.zimara.backend.model.view.ConstraintOperation;
import io.zimara.backend.model.view.ViewDefinition;
import io.zimara.backend.model.view.ViewDefinitionConstraint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@QuarkusTest
class GenericViewDefinitionParserServiceTest {

    @Inject
    GenericViewDefinitionParserService viewDefinitionParserService;

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
        Assertions.assertEquals(1, views.size());

        steps.add(new KameletStep());
        views = viewDefinitionParserService.parse(steps);
        Assertions.assertEquals(4, views.size());

        steps.add(new KameletStep());
        views = viewDefinitionParserService.parse(steps);
        Assertions.assertEquals(5, views.size());
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
        KameletStep step = new KameletStep();
        step.setName(stepName);
        step.setId(stepId);
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
