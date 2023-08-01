package io.kaoto.backend.api.service.viewdefinition.parser;

import io.kaoto.backend.api.metadata.catalog.ViewDefinitionCatalog;
import io.kaoto.backend.model.step.Branch;
import io.kaoto.backend.model.step.Step;
import io.kaoto.backend.model.view.ViewDefinition;
import io.kaoto.backend.model.view.ViewDefinitionConstraint;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 🐱miniclass GenericViewDefinitionParserService (ViewDefinitionParserService)
 * 🐱relationship compositionOf ViewDefinitionCatalog, 0..1
 */
@ApplicationScoped
public class GenericViewDefinitionParserService
        implements ViewDefinitionParserService<ViewDefinition> {

    private ViewDefinitionCatalog catalog;

    @Inject
    public void setCatalog(final ViewDefinitionCatalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public List<ViewDefinition> parse(final List<Step> steps) {
        List<ViewDefinition> viewDefinitions = new ArrayList<>();

        for (var v : catalog.getReadOnlyCatalog().getAll()) {
            if (appliesTo(steps, v)) {
                if (v.getType().equalsIgnoreCase("generic")) {
                    viewDefinitions.add(new ViewDefinition(v));
                } else if (v.getType().equalsIgnoreCase("step")) {
                    viewDefinitions.addAll(getViewsPerStep(steps, v));
                }
            }
        }

        return viewDefinitions;
    }

    @Override
    public List<ViewDefinition> getViewsPerStep(final List<Step> steps,
                                                final ViewDefinition view) {
        List<ViewDefinition> views = new ArrayList<>();
        for (Step step : steps) {
            if (appliesToStep(step, view)) {
                ViewDefinition v = new ViewDefinition(view);
                v.setStep(step.getUUID());
                views.add(v);
            }
            if (step.getBranches() != null) {
                for (Branch b : step.getBranches()) {
                    views.addAll(getViewsPerStep(b.getSteps(), view));
                }
            }
        }
        return views;
    }

    @Override
    public boolean appliesToStep(final Step step, final ViewDefinition viewDefinition) {
        return doAppliesTo(Collections.singletonList(step), viewDefinition, false);
    }

    @Override
    public boolean appliesTo(final List<Step> steps,
                             final ViewDefinition viewDefinition) {
        return doAppliesTo(steps, viewDefinition, true);
    }

    private boolean doAppliesTo(final List<Step> steps,
                                final ViewDefinition viewDefinition,
                                boolean includeBranch) {
        if (viewDefinition.getConstraints() == null
                || viewDefinition.getConstraints().isEmpty()) {
            return true;
        }
        boolean hasOptional = false;
        boolean passOptional = false;
        for (ViewDefinitionConstraint c : viewDefinition.getConstraints()) {
            if (!c.isMandatory()) {
                hasOptional = true;
                //lazy checking of conditional, we just need one
                passOptional = passOptional || passConditional(steps, c, includeBranch);
            } else if (!passConditional(steps, c, includeBranch)) {
                //This is mandatory and didn't pass
                return false;
            }
        }

        //If we are here, all conditionals have passed.
        // Let's check if we have optionals pending:
        return !hasOptional || passOptional;
    }

    private boolean passConditional(final List<Step> steps,
                                    final ViewDefinitionConstraint c,
                                    final boolean includeBranch) {
        boolean res = false;
        switch (c.getOperation()) {
            case SIZE_EQUALS:
                res = steps != null && steps.size() == Integer.valueOf(c.getParameter());
                break;
            case SIZE_GREATER_THAN:
                res = steps != null && steps.size() > Integer.valueOf(c.getParameter());
                break;
            case SIZE_SMALLER_THAN:
                res = steps != null && steps.size() < Integer.valueOf(c.getParameter());
                break;
            case CONTAINS_STEP_IDENTIFIER:
                res = steps != null && containsStepIdentifier(steps, c, includeBranch);
                break;
            case CONTAINS_STEP_NAME:
                res = steps != null && containsStepName(steps, c, includeBranch);
                break;
            case CONTAINS_STEP_TYPE:
                res = steps != null && containsStepType(steps, c, includeBranch);
                break;
            default:
                //Unsupported operation or typo
                break;
        }

        return res;
    }

    private boolean containsStepIdentifier(final List<Step> steps,
                                           final ViewDefinitionConstraint c,
                                           final boolean includeBranch) {
        boolean res = false;
        for (Step s : steps) {
            if (s.getId() != null && s.getId().equalsIgnoreCase(c.getParameter())) {
                res = true;
                break;
            }
            if (includeBranch && s.getBranches() != null) {
                for (Branch b : s.getBranches()) {
                    res = containsStepIdentifier(b.getSteps(), c, true);
                    if (res == true) {
                        return true;
                    }
                }
            }
        }
        return res;
    }

    private boolean containsStepName(final List<Step> steps,
                                     final ViewDefinitionConstraint c,
                                     final boolean includeBranch) {
        boolean res = false;
        for (Step s : steps) {
            if (s.getName() != null
                    && s.getName().equalsIgnoreCase(c.getParameter())) {
                res = true;
                break;
            }
            if (includeBranch && s.getBranches() != null) {
                for (Branch b : s.getBranches()) {
                    res = containsStepName(b.getSteps(), c, true);
                    if (res == true) {
                        return true;
                    }
                }
            }
        }
        return res;
    }

    private boolean containsStepType(final List<Step> steps,
                                     final ViewDefinitionConstraint c,
                                     final boolean includeBranch) {
        boolean res = false;
        for (Step s : steps) {
            if (s.getType().equalsIgnoreCase(c.getParameter())
                    || s.getKind().equalsIgnoreCase(c.getParameter())) {
                res = true;
                break;
            }
            if (includeBranch && s.getBranches() != null) {
                for (Branch b : s.getBranches()) {
                    res = containsStepType(b.getSteps(), c, true);
                    if (res == true) {
                        return true;
                    }
                }
            }
        }
        return res;
    }

}
