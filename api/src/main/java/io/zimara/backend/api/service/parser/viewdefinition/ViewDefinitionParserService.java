package io.zimara.backend.api.service.parser.viewdefinition;

import io.zimara.backend.api.metadata.catalog.ViewDefinitionCatalog;
import io.zimara.backend.api.service.parser.ViewParserService;
import io.zimara.backend.model.step.Step;
import io.zimara.backend.model.view.ViewDefinition;
import io.zimara.backend.model.view.ViewDefinitionConstraint;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * 🐱miniclass ViewDefinitionParserService (ViewParserService)
 * 🐱relationship compositionOf ViewDefinitionCatalog, 0..1
 *
 *
 */
@ApplicationScoped
public class ViewDefinitionParserService implements ViewParserService<ViewDefinition> {

    @Inject
    ViewDefinitionCatalog catalog;

    @Override
    public List<ViewDefinition> parse(List<Step> steps) {
        List<ViewDefinition> viewDefinitions = new ArrayList<>();

        for (var v : catalog.getReadOnlyCatalog().getAll()) {
            if (appliesTo(steps, v)) {
                ViewDefinition clone = new ViewDefinition(v);
                clone.setSteps(steps);
                viewDefinitions.add(clone);
            }
        }

        return viewDefinitions;
    }

    @Override
    public String getIdentifier() {
        return "DEFAULT-VIEW-PARSER";
    }

    @Override
    public boolean appliesTo(List<Step> steps, ViewDefinition viewDefinition) {
        if (viewDefinition.getConstraints() == null || viewDefinition.getConstraints().isEmpty()) {
            return true;
        }
        boolean hasOptional = false;
        boolean passOptional = false;
        for (ViewDefinitionConstraint c : viewDefinition.getConstraints()) {
            if (!c.isMandatory()) {
                hasOptional = true;
                //lazy checking of conditional, we just need one
                passOptional = passOptional || passConditional(steps, c);
            } else if (!passConditional(steps, c)) {
                //This is mandatory and didn't pass
                return false;
            }
        }

        //If we are here, all conditionals have passed. Let's check if we have optionals pending:
        return !hasOptional || passOptional;
    }

    private boolean passConditional(List<Step> steps, ViewDefinitionConstraint c) {
        boolean res = false;
        switch (c.getOperation()) {
            case SIZE_EQUALS:
                res = steps.size() == Integer.valueOf(c.getParameter());
                break;
            case SIZE_GREATER_THAN:
                res = steps.size() > Integer.valueOf(c.getParameter());
                break;
            case SIZE_SMALLER_THAN:
                res = steps.size() < Integer.valueOf(c.getParameter());
                break;
            case CONTAINS_STEP_IDENTIFIER:
                res = containsStepIdentifier(steps, c, res);
                break;
            case CONTAINS_STEP_NAME:
                res = containsStepName(steps, c, res);
                break;
            case CONTAINS_STEP_TYPE:
                res = containsStepType(steps, c, res);
                break;
        }

        return res;
    }

    private boolean containsStepIdentifier(List<Step> steps, ViewDefinitionConstraint c, boolean res) {
        for (Step s : steps) {
            if (s.getId().equalsIgnoreCase(c.getParameter())) {
                res = true;
                break;
            }
        }
        return res;
    }

    private boolean containsStepName(List<Step> steps, ViewDefinitionConstraint c, boolean res) {
        for (Step s : steps) {
            if (s.getName().equalsIgnoreCase(c.getParameter())) {
                res = true;
                break;
            }
        }
        return res;
    }

    private boolean containsStepType(List<Step> steps, ViewDefinitionConstraint c, boolean res) {
        for (Step s : steps) {
            if (s.getType().equalsIgnoreCase(c.getParameter()) || s.getSubType().equalsIgnoreCase(c.getParameter())) {
                res = true;
                break;
            }
        }
        return res;
    }

}
