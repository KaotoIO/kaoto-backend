package io.kaoto.backend.api.service.step;

import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.model.step.Step;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;
/**
 *
 * 🐱miniclass StepService (StepResource)
 * 🐱relationship compositionOf StepCatalog, 0..1
 *
 * 🐱section
 * Service to interact with steps. This is the utility class the
 * resource relies on to perform the operations.
 *
 */
@ApplicationScoped
public class StepService {

    private StepCatalog catalog;

    @Inject
    public void setCatalog(final StepCatalog catalog) {
        this.catalog = catalog;
    }

    /*
     * 🐱method stepById : Step
     * 🐱param id: String
     *
     *  Returns the first step identified by the parameter.
     *
     */
    public Step stepById(final String id) {
        return catalog.getReadOnlyCatalog().searchStepByID(id);
    }

    /*
     * 🐱method stepsByName : List[Step]
     * 🐱param name: String
     *
     *  Returns all the steps identified by the name.
     *
     */
    public Collection<Step> stepsByName(final String name) {
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
