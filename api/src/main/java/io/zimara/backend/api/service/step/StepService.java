package io.zimara.backend.api.service.step;

import io.zimara.backend.api.metadata.catalog.StepCatalog;
import io.zimara.backend.model.step.Step;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;

@ApplicationScoped
public class StepService {

    @Inject
    StepCatalog catalog;

    public Step stepById(String id) {
        return catalog.getReadOnlyCatalog().searchStepByID(id);
    }

    public Collection<Step> stepsByName(String name) {
        return catalog.getReadOnlyCatalog().searchStepsByName(name);
    }

    public Collection<Step> allSteps() {
        return catalog.getReadOnlyCatalog().getAll();
    }
}