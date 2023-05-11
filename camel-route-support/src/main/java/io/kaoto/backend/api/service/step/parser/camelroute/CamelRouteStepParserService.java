package io.kaoto.backend.api.service.step.parser.camelroute;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.kaoto.backend.api.service.step.parser.StepParserService;
import io.kaoto.backend.api.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.deployment.camelroute.CamelRoute;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.model.deployment.rest.Rest;
import io.kaoto.backend.model.step.Step;
import io.quarkus.runtime.annotations.RegisterForReflection;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
            processFlows(route, resultList);
            processBeans(route, resultList);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error trying to parse.", e);
        }

        return resultList;
    }

    private void processFlows(CamelRoute route, List<ParseResult<Step>> resultList) {
        var flows = route.getFlows();
        if (flows == null) {
            return;
        }

        for (var flow : flows) {
            var from = flow.getFrom();
            if (from == null) {
                from = flow.getRest();
            }

            ParseResult<Step> res = new ParseResult<>();
            List<Step> steps = new ArrayList<>();

            if (from instanceof Rest rest) {
                steps.add(rest.getStep(ksps, false, true));
            } else if (from.getSteps() != null) {
                steps.add(ksps.processStep(from, true, false));
                if (from.getSteps() != null) {
                    for (FlowStep step : from.getSteps()) {
                        //end is always false in this case because we can always edit one step after it
                        steps.add(ksps.processStep(step, false, false));
                    }
                }
            }
            res.setSteps(steps.stream().filter(Objects::nonNull).toList());
            resultList.add(res);
        }
    }

    private void processBeans(CamelRoute route, List<ParseResult<Step>> resultList) {
        if (route.getBeans() == null || route.getBeans().isEmpty()) {
            return;
        }
        ParseResult<Step> res = new ParseResult<>();
        Map<String, Object> metadata = new LinkedHashMap<>();
        metadata.put("beans", route.getBeans());
        res.setMetadata(metadata);
        resultList.add(res);
    }

    @Override
    public boolean appliesTo(final String input) {
        return getCamelRoute(input) != null;
    }

    private CamelRoute getCamelRoute(final String input) {
        try {
            ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory())
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return yamlMapper.readValue(input, CamelRoute.class);
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
