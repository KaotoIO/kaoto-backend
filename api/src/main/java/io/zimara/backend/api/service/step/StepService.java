package io.zimara.backend.api.service.step;

import io.zimara.backend.api.metadata.catalog.StepCatalog;
import io.zimara.backend.model.step.Step;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;
/**
 *
 * 🐱class StepService
 * 🐱relationship compositionOf StepCatalog, 0..1
 *
 */
@ApplicationScoped
public class StepService {

    @Inject
    StepCatalog catalog;

    /*
     * 🐱method stepById : Step
     * 🐱param id: String
     *
     *  Returns the first step identified by the parameter.
     *
     */
    public Step stepById(String id) {
        return catalog.getReadOnlyCatalog().searchStepByID(id);
    }

    /*
     * 🐱method stepsByName : List[Step]
     * 🐱param name: String
     *
     *  Returns all the steps identified by the name.
     *
     */
    public Collection<Step> stepsByName(String name) {
        return catalog.getReadOnlyCatalog().searchStepsByName(name);
    }

    /*
     * 🐱method allSteps : List[Step]
     *
     *  Returns all the steps.
     *
     */
    public Collection<Step> allSteps() {
        return catalog.getReadOnlyCatalog().getAll();
    }
}