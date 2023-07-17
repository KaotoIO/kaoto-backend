package io.kaoto.backend.api.service.viewdefinition;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;

import io.kaoto.backend.api.service.viewdefinition.parser.ViewDefinitionParserService;
import io.kaoto.backend.model.step.Step;
import io.kaoto.backend.model.view.ViewDefinition;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

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

    private static final Logger LOG = Logger.getLogger(ViewDefinitionService.class);

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
            LOG.trace("Using " + viewParser.getClass());
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
