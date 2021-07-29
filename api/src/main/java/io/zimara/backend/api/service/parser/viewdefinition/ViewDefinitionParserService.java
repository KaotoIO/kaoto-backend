package io.zimara.backend.api.service.parser.viewdefinition;

import io.zimara.backend.api.metadata.catalog.ViewDefinitionCatalog;
import io.zimara.backend.api.service.parser.ViewParserService;
import io.zimara.backend.model.step.Step;
import io.zimara.backend.model.view.ViewDefinition;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ViewDefinitionParserService implements ViewParserService<ViewDefinition> {

    @Inject
    ViewDefinitionCatalog catalog;

    @Override
    public List<ViewDefinition> parse(List<Step> steps, String id) {
        if (!appliesTo(steps)) {
            throw new IllegalArgumentException("Wrong format provided. This is not parseable by " + getIdentifier());
        }

        List<ViewDefinition> viewDefinitions = new ArrayList<>();

        for(var v : catalog.getReadOnlyCatalog().getAll()) {
            ViewDefinition clone = new ViewDefinition(v);
            clone.setSteps(steps);
            viewDefinitions.add(clone);
        }

        return viewDefinitions;
    }

    @Override
    public String getIdentifier() {
        return "DEFAULT-VIEW-PARSER";
    }

    @Override
    public boolean appliesTo(List<Step> yaml) {
        return true;
    }

}
