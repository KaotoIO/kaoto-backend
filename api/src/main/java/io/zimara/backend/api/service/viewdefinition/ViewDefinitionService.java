package io.zimara.backend.api.service.viewdefinition;

import io.zimara.backend.api.service.step.parser.KameletBindingStepParserService;
import io.zimara.backend.api.service.step.parser.StepParserService;
import io.zimara.backend.api.service.viewdefinition.parser.ViewDefinitionParserService;
import io.zimara.backend.model.step.Step;
import io.zimara.backend.model.view.ViewDefinition;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.QueryParam;
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

    private List<StepParserService<Step>> stepParsers = new ArrayList<>();
    private List<ViewDefinitionParserService<ViewDefinition>> viewParsers =
            new ArrayList<>();
    private Logger log = Logger.getLogger(ViewDefinitionService.class);

    @Inject
    public void setKameletBindingParserService(
            final KameletBindingStepParserService kameletBindingParserService) {
        stepParsers.add(kameletBindingParserService);
    }

    @Inject
    public void setViewParser(
            final ViewDefinitionParserService<ViewDefinition> viewParser) {
        viewParsers.add(viewParser);
    }

    /*
     * 🐱method views: List[ViewDefinition]
     * 🐱param yaml: String
     *
     * Based on the provided yaml, offer a list of compatible ViewDefinitions.
     */
    public List<ViewDefinition> views(final @QueryParam("yaml") String yaml) {
        List<ViewDefinition> viewDefinitions = new ArrayList<>();
        for (var stepParser : stepParsers) {
            if (stepParser.appliesTo(yaml)) {
                log.trace("Applying " + stepParser.getClass());
                final var steps = stepParser.parse(yaml);
                for (var viewParser : viewParsers) {
                    log.trace("Using " + viewParser.getClass());
                    viewDefinitions.addAll(viewParser.parse(steps));
                }

            }
        }
        return viewDefinitions;
    }
}
