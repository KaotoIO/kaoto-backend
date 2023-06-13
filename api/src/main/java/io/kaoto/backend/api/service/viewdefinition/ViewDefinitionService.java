package io.kaoto.backend.api.service.viewdefinition;

import io.kaoto.backend.api.service.viewdefinition.parser.ViewDefinitionParserService;
import io.kaoto.backend.model.step.Step;
import io.kaoto.backend.model.view.ViewDefinition;
import io.opentelemetry.extension.annotations.WithSpan;
import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 🐱miniclass ViewDefinitionService (ViewDefinitionResource)
 * 🐱relationship compositionOf ViewDefinitionParserService, 0..1
 *
 * 🐱section
 * Service to interact with view definitions. This is the utility class the
 * resource relies on to perform the operations.
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
    @WithSpan
    public List<ViewDefinition> viewsPerStepList(final List<Step> steps) {
        List<ViewDefinition> viewDefinitions = new ArrayList<>();
        for (var viewParser : getViewParsers()) {
            log.trace("Using " + viewParser.getClass());
            viewDefinitions.addAll(viewParser.parse(steps));
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
