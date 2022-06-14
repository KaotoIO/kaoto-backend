package io.kaoto.backend.api.service.viewdefinition;

import io.kaoto.backend.api.service.viewdefinition.parser.ViewDefinitionParserService;
import io.kaoto.backend.model.step.Step;
import io.kaoto.backend.model.view.ViewDefinition;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * 🐱class ViewDefinitionService
 * 🐱relationship dependsOn StepParserService
 * 🐱relationship dependsOn ViewDefinitionParserService
 * This endpoint will return a list of views based on the parameters.
 */
@ApplicationScoped
public class ViewDefinitionService {

    private Instance<ViewDefinitionParserService<ViewDefinition>> viewParsers;

    private Logger log = Logger.getLogger(ViewDefinitionService.class);

    /*
     * 🐱method viewsPerStepList: List[ViewDefinition]
     * 🐱param steps: Step[]
     *
     * Based on the provided list of steps, offer a list of compatible
     * ViewDefinitions.
     */
    public List<ViewDefinition> viewsPerStepList(final List<Step> steps,
                                                 final String name) {

        int i = 0;
        if (steps != null) {
            for (Step step : steps) {
                if (step != null && step.getUUID() == null) {
                    step.setUUID(i + step.getId());
                }
                i++;
            }
            log.trace("Found " + steps.size() + " steps.");
        }

        List<ViewDefinition> viewDefinitions = new ArrayList<>();
        for (var viewParser : getViewParsers()) {
            log.trace("Using " + viewParser.getClass());
            viewDefinitions.addAll(viewParser.parse(steps, name));
        }

        return viewDefinitions;
    }

    public Instance<ViewDefinitionParserService<ViewDefinition>>
    getViewParsers() {
        return viewParsers;
    }

    @Inject
    public void setViewParsers(
            final Instance<ViewDefinitionParserService<ViewDefinition>>
                    viewParsers) {
        this.viewParsers = viewParsers;
    }

}
