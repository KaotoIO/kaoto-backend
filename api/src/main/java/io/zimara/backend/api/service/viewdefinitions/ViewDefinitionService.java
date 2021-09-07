package io.zimara.backend.api.service.viewdefinitions;

import io.zimara.backend.api.service.parser.StepParserService;
import io.zimara.backend.api.service.parser.ViewParserService;
import io.zimara.backend.api.service.parser.step.KameletBindingStepParserService;
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
 * 🐱relationship dependsOn ViewParserService
 *
 * This endpoint will return a list of views based on the parameters.
 *
 */
@ApplicationScoped
public class ViewDefinitionService {

    private List<StepParserService<Step>> stepParsers = new ArrayList<>();
    private List<ViewParserService<ViewDefinition>> viewParsers = new ArrayList<>();
    private Logger log = Logger.getLogger(ViewDefinitionService.class);

    @Inject
    public void setKameletBindingParserService(KameletBindingStepParserService kameletBindingParserService) {
        stepParsers.add(kameletBindingParserService);
    }

    @Inject
    public void setViewParser(ViewParserService<ViewDefinition> viewParser) {
        viewParsers.add(viewParser);
    }

    /*
     * 🐱method views: List[ViewDefinition]
     * 🐱param yaml: String
     *
     * Based on the provided yaml, offer a list of compatible ViewDefinitions.
     */
    public List<ViewDefinition> views(@QueryParam("yaml") String yaml) {
        List<ViewDefinition> viewDefinitions = new ArrayList<>();
        for (var stepParser : stepParsers) {
            if (stepParser.appliesTo(yaml)) {
                log.trace("Applying " + stepParser.getClass());
                final var steps = stepParser.parse(yaml);
                final String name = stepParser.getIdentifier(yaml);
                for (var viewParser : viewParsers) {
                    log.trace("Using " + viewParser.getClass());
                    viewDefinitions.addAll(viewParser.parse(steps));
                }

            }
        }
        return viewDefinitions;
    }
}
