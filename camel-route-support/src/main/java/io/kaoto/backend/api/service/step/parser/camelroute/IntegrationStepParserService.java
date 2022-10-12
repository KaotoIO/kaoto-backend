package io.kaoto.backend.api.service.step.parser.camelroute;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.kaoto.backend.api.service.step.parser.StepParserService;
import io.kaoto.backend.api.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.deployment.camelroute.Integration;
import io.kaoto.backend.model.deployment.kamelet.Flow;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.model.step.Step;
import io.quarkus.runtime.annotations.RegisterForReflection;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 🐱miniclass IntegrationStepParserService (StepParserService)
 */
@ApplicationScoped
@RegisterForReflection
public class IntegrationStepParserService
        implements StepParserService<Step> {

    private KameletStepParserService ksps;

    public String identifier() {
        return "Integration";
    }

    public String description() {
        return "An Integration defines a workflow of actions and steps.";
    }

    @Override
    public ParseResult<Step> deepParse(final String input) {
        if (!appliesTo(input)) {
            throw new IllegalArgumentException(
                    "Wrong format provided. This is not parseable by us.");
        }

        ParseResult<Step> res = new ParseResult<>();

        List<Step> steps = new ArrayList<>();
        try {
            ObjectMapper yamlMapper =
                new ObjectMapper(new YAMLFactory())
                        .configure(
                            DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                            false);
            Integration integration = yamlMapper.readValue(input,
                    Integration.class);

            ksps.processMetadata(res, integration.getMetadata());
            res.setParameters(new ArrayList<>());

            for (Flow flow : integration.getSpec().getFlows()) {
                steps.add(ksps.processStep(flow.getFrom()));
                if (flow.getFrom().getSteps() != null) {
                    for (FlowStep step : flow.getFrom().getSteps()) {
                        steps.add(ksps.processStep(step));
                    }
                }
            }


        } catch (Exception e) {
            throw new IllegalArgumentException("Error trying to parse.", e);
        }

        res.setSteps(steps.stream()
                .filter(Objects::nonNull)
                .toList());
        return res;
    }


    @Override
    public boolean appliesTo(final String yaml) {
        String[] kinds = new String[]{"Integration"};

        Pattern pattern = Pattern.compile(
                "(\nkind:)(.+)\n", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(yaml);
        if (matcher.find()) {
            return Arrays.stream(kinds).anyMatch(
                    k -> k.equalsIgnoreCase(matcher.group(2).trim()));
        }

        return false;
    }

    @Inject
    public void setKsps(
            final KameletStepParserService ksps) {
        this.ksps = ksps;
    }
}
