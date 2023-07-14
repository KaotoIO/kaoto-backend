package io.kaoto.backend.api.service.parser.viewdefinition;

import io.kaoto.backend.api.metadata.catalog.ViewDefinitionCatalog;
import io.kaoto.backend.api.service.viewdefinition.parser.GenericViewDefinitionParserService;
import io.kaoto.backend.model.step.Branch;
import io.kaoto.backend.model.step.Step;
import io.kaoto.backend.model.view.ConstraintOperation;
import io.kaoto.backend.model.view.ViewDefinition;
import io.kaoto.backend.model.view.ViewDefinitionConstraint;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
        List<Step> steps = new ArrayList<>();
        List<ViewDefinition> views =
                viewDefinitionParserService.parse(steps);
        Assertions.assertEquals(0, views.size());

        steps.add(new Step());
        views = viewDefinitionParserService.parse(steps);
        Assertions.assertEquals(0, views.size());

        Step s = new Step();
        s.setKind("KAMELET");
        steps.add(s);
        views = viewDefinitionParserService.parse(steps);
        Assertions.assertEquals(2, views.size());

        steps.add(new Step());
        views = viewDefinitionParserService.parse(steps);
        Assertions.assertEquals(2, views.size());
    }

    @Test
    void appliesToSize() {
        List<Step> steps = new ArrayList<>();
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

        List<Step> steps = new ArrayList<>();
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

    @Test
    void appliesToBranchStep() {
        List<Step> steps = new ArrayList<>();
        Step choice = new Step();
        choice.setName("choice");
        choice.setType("choice");
        choice.setId("choice-1");
        choice.setKind("Kamelet");
        choice.setBranches(new ArrayList());
        steps.add(choice);

        Step choiceSetBody = new Step();
        choiceSetBody.setName("set-body");
        choiceSetBody.setType("set-body");
        choiceSetBody.setId("set-body-1");
        choice.setKind("Kamelet");
        Branch choiceSetBodyBranch = new Branch();
        choiceSetBodyBranch.getSteps().add(choiceSetBody);
        choice.getBranches().add(choiceSetBodyBranch);
        Step choiceSetHeader = new Step();
        choiceSetHeader.setName("set-header");
        choiceSetHeader.setType("set-header");
        choiceSetHeader.setId("set-header-1");
        choice.setKind("Kamelet");
        Branch choiceSetHeaderBranch = new Branch();
        choiceSetHeaderBranch.getSteps().add(choiceSetHeader);
        choice.getBranches().add(choiceSetHeaderBranch);
        Step choiceTransform = new Step();
        choiceTransform.setName("transform");
        choiceTransform.setType("transform");
        choiceTransform.setId("transform-1");
        choice.setKind("Kamelet");
        Branch choiceTransformBranch = new Branch();
        choiceTransformBranch.getSteps().add(choiceTransform);
        choice.getBranches().add(choiceTransformBranch);

        ConstraintOperation op = ConstraintOperation.CONTAINS_STEP_NAME;
        ViewDefinition viewChoice = getMockView(op, "choice");
        ViewDefinition viewSetBody = getMockView(op, "set-body");
        ViewDefinition viewSetHeader = getMockView(op, "set-header");
        ViewDefinition viewTransform = getMockView(op,"transform");
        Assertions.assertTrue(
                viewDefinitionParserService.appliesTo(steps, viewChoice));
        Assertions.assertTrue(
                viewDefinitionParserService.appliesTo(steps, viewSetBody));
        Assertions.assertTrue(
                viewDefinitionParserService.appliesTo(steps, viewSetHeader));
        Assertions.assertTrue(
                viewDefinitionParserService.appliesTo(steps, viewTransform));

        Assertions.assertTrue(
                viewDefinitionParserService.appliesToStep(choice, viewChoice));
        Assertions.assertFalse(
                viewDefinitionParserService.appliesToStep(choiceSetBody, viewChoice));
        Assertions.assertFalse(
                viewDefinitionParserService.appliesToStep(choiceSetHeader, viewChoice));
        Assertions.assertFalse(
                viewDefinitionParserService.appliesToStep(choiceTransform, viewChoice));
        Assertions.assertFalse(
                viewDefinitionParserService.appliesToStep(choice, viewSetBody));
        Assertions.assertTrue(
                viewDefinitionParserService.appliesToStep(choiceSetBody, viewSetBody));
        Assertions.assertFalse(
                viewDefinitionParserService.appliesToStep(choiceSetHeader, viewSetBody));
        Assertions.assertFalse(
                viewDefinitionParserService.appliesToStep(choiceTransform, viewSetBody));
        Assertions.assertFalse(
                viewDefinitionParserService.appliesToStep(choice, viewSetHeader));
        Assertions.assertFalse(
                viewDefinitionParserService.appliesToStep(choiceSetBody, viewSetHeader));
        Assertions.assertTrue(
                viewDefinitionParserService.appliesToStep(choiceSetHeader, viewSetHeader));
        Assertions.assertFalse(
                viewDefinitionParserService.appliesToStep(choiceTransform, viewSetHeader));
        Assertions.assertFalse(
                viewDefinitionParserService.appliesToStep(choice, viewTransform));
        Assertions.assertFalse(
                viewDefinitionParserService.appliesToStep(choiceSetBody, viewTransform));
        Assertions.assertFalse(
                viewDefinitionParserService.appliesToStep(choiceSetHeader, viewTransform));
        Assertions.assertTrue(
                viewDefinitionParserService.appliesToStep(choiceTransform, viewTransform));
    }

    @Test
    void appliesToBranchStepIdentifier() {
        List<Step> steps = new ArrayList<>();
        Step choice = new Step();
        choice.setName("choice");
        choice.setType("choice");
        choice.setId("choice-1");
        choice.setKind("Kamelet");
        choice.setBranches(new ArrayList());
        steps.add(choice);

        Step choiceSetBody = new Step();
        choiceSetBody.setName("set-body");
        choiceSetBody.setType("set-body");
        choiceSetBody.setId("set-body-1");
        choice.setKind("Kamelet");
        Branch choiceSetBodyBranch = new Branch();
        choiceSetBodyBranch.getSteps().add(choiceSetBody);
        choice.getBranches().add(choiceSetBodyBranch);

        ConstraintOperation op = ConstraintOperation.CONTAINS_STEP_IDENTIFIER;
        ViewDefinition viewChoice = getMockView(op, "choice-1");
        ViewDefinition viewSetBody = getMockView(op, "set-body-1");
        Assertions.assertTrue(viewDefinitionParserService.appliesTo(steps, viewChoice));
        Assertions.assertTrue(viewDefinitionParserService.appliesTo(steps, viewSetBody));
        Assertions.assertTrue(viewDefinitionParserService.appliesToStep(choice, viewChoice));
        Assertions.assertFalse(viewDefinitionParserService.appliesToStep(choice, viewSetBody));
        Assertions.assertFalse(viewDefinitionParserService.appliesToStep(choiceSetBody, viewChoice));
        Assertions.assertTrue(viewDefinitionParserService.appliesToStep(choiceSetBody, viewSetBody));
    }

    @Test
    void appliesToBranchStepType() {
        List<Step> steps = new ArrayList<>();
        Step choice = new Step();
        choice.setName("choice");
        choice.setType("choice");
        choice.setId("choice-1");
        choice.setKind("Kamelet");
        choice.setBranches(new ArrayList());
        steps.add(choice);

        Step choiceSetBody = new Step();
        choiceSetBody.setName("set-body");
        choiceSetBody.setType("set-body");
        choiceSetBody.setId("set-body-1");
        choice.setKind("Kamelet");
        Branch choiceSetBodyBranch = new Branch();
        choiceSetBodyBranch.getSteps().add(choiceSetBody);
        choice.getBranches().add(choiceSetBodyBranch);

        ConstraintOperation op = ConstraintOperation.CONTAINS_STEP_TYPE;
        ViewDefinition viewChoice = getMockView(op, "choice");
        ViewDefinition viewSetBody = getMockView(op, "set-body");
        Assertions.assertTrue(viewDefinitionParserService.appliesTo(steps, viewChoice));
        Assertions.assertTrue(viewDefinitionParserService.appliesTo(steps, viewSetBody));
        Assertions.assertTrue(viewDefinitionParserService.appliesToStep(choice, viewChoice));
        Assertions.assertFalse(viewDefinitionParserService.appliesToStep(choice, viewSetBody));
        Assertions.assertFalse(viewDefinitionParserService.appliesToStep(choiceSetBody, viewChoice));
        Assertions.assertTrue(viewDefinitionParserService.appliesToStep(choiceSetBody, viewSetBody));
    }

    private ViewDefinition getMockView(ConstraintOperation operation, String name) {
        ViewDefinition view = new ViewDefinition();
        view.setConstraints(new ArrayList<>());
        ViewDefinitionConstraint constraint =
                new ViewDefinitionConstraint();
        constraint.setMandatory(false);
        constraint.setOperation(operation);
        constraint.setParameter(name);
        view.getConstraints().add(constraint);
        return view;
    }
}
