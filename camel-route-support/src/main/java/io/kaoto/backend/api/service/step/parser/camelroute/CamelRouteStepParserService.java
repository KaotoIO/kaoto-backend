package io.kaoto.backend.api.service.step.parser.camelroute;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.kaoto.backend.api.service.step.parser.StepParserService;
import io.kaoto.backend.api.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.deployment.camelroute.CamelRoute;
import io.kaoto.backend.model.deployment.kamelet.Flow;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.model.step.Step;
import io.quarkus.runtime.annotations.RegisterForReflection;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 🐱miniclass CamelRouteStepParserService (StepParserService)
 */
@ApplicationScoped
@RegisterForReflection
public class CamelRouteStepParserService implements StepParserService<Step> {
    private Logger log = Logger.getLogger(CamelRouteStepParserService.class);

    private KameletStepParserService ksps;

    public String identifier() {
        return "Camel Route";
    }

    public String description() {
        return "A camel route is a non deployable in cluster workflow of actions and steps.";
    }

    @Override
    public ParseResult<Step> deepParse(final String input) {
        // Right now we discard any flow that is not the first
        return getParsedFlows(input).get(0);
    }

    @Override
    public List<ParseResult<Step>> getParsedFlows(final String input) {
        if (!appliesTo(input)) {
            throw new IllegalArgumentException(
                    "Wrong format provided. This is not parseable by us.");
        }

        List<ParseResult<Step>> resultList = new ArrayList<>();

        try {
            CamelRoute route = getCamelRoute(input);
            var flows = route.getFlows();

            for (var flow : flows) {
                var from = flow.getFrom();

                ParseResult<Step> res = new ParseResult<>();
                List<Step> steps = new ArrayList<>();

                steps.add(ksps.processStep(from, true, false));
                if (from.getSteps() != null) {
                    int i = 0;
                    for (FlowStep step : from.getSteps()) {
                        steps.add(ksps.processStep(step, false, i++ == from.getSteps().size() - 1));
                    }
                }

                res.setSteps(steps.stream().filter(Objects::nonNull).toList());
                resultList.add(res);
            }

        } catch (Exception e) {
            throw new IllegalArgumentException("Error trying to parse.", e);
        }

        return resultList;
    }

    @Override
    public boolean appliesTo(final String input) {
        return getCamelRoute(input) != null;
    }

    private CamelRoute getCamelRoute(final String input) {
        try {
            ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory())
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            TypeReference<List<Flow>> mapType = new TypeReference<>() {
            };
            List<Flow> flowList = yamlMapper.readValue(input, mapType);
            var route = new CamelRoute();
            route.setFlows(flowList);
            return route;
        } catch (JsonProcessingException e) {
            //We don't care what happened, it is wrongly formatted and that's it
            log.trace("Error trying to parse camel route.", e);
        }
        return null;
    }

    @Inject
    public void setKsps(final KameletStepParserService ksps) {
        this.ksps = ksps;
    }
}
