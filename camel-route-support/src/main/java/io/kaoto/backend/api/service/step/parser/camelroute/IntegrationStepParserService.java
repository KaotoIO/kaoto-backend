package io.kaoto.backend.api.service.step.parser.camelroute;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.kaoto.backend.api.service.step.parser.StepParserService;
import io.kaoto.backend.api.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.deployment.camelroute.Integration;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.model.step.Step;
import io.quarkus.runtime.annotations.RegisterForReflection;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 🐱miniclass IntegrationStepParserService (StepParserService)
 */
@ApplicationScoped
@RegisterForReflection
public class IntegrationStepParserService implements StepParserService<Step> {

    private KameletStepParserService ksps;

    @Override
    public ParseResult<Step> deepParse(final String input) {
        if (!appliesTo(input)) {
            throw new IllegalArgumentException(
                    "Wrong format provided. This is not parseable by us.");
        }

        ParseResult<Step> res = new ParseResult<>();
        List<Step> steps = new ArrayList<>();
        try {
            ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());
            Integration integration = yamlMapper.readValue(input, Integration.class);

            ksps.processMetadata(res, integration.getMetadata());
            res.setParameters(new ArrayList<>());

            for (var flow : integration.getSpec().get_flows()) {
                steps.add(ksps.processStep(flow.getFrom(), true, false));
                if (flow.getFrom().getSteps() != null) {
                    for (FlowStep step : flow.getFrom().getSteps()) {
                        //end is always false in this case because we can always edit one step after it
                        steps.add(ksps.processStep(step, false, false));
                    }
                }
            }

            //Let's store the spec to make sure we don't lose anything else
            if (integration.getSpec().get_flows() != null) {
                integration.getSpec().get_flows().clear();
            }
            ((Map<String, Object>)res.getMetadata().get("additionalProperties")).put("spec", integration.getSpec());
            if (integration.getMetadata().getAnnotations().containsKey("description")) {
                res.getMetadata().put("description",
                        integration.getMetadata().getAnnotations().get("description"));
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
    public List<ParseResult<Step>> getParsedFlows(String input) {
        var res = new LinkedList<ParseResult<Step>>();
        String[] splitCRDs = input.split(System.lineSeparator() + "---" + System.lineSeparator());
        for (var crd : splitCRDs) {
            res.add(deepParse(crd));
        }
        return res;
    }

    @Override
    public boolean appliesTo(final String yaml) {
        String[] kinds = new String[]{"Integration"};

        Pattern pattern = Pattern.compile("(kind:)(.+)(\r\n|\r|\n)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(yaml);
        if (matcher.find()) {
            return Arrays.stream(kinds).anyMatch(
                    k -> k.equalsIgnoreCase(matcher.group(2).trim()));
        }

        return false;
    }

    @Inject
    public void setKsps(final KameletStepParserService ksps) {
        this.ksps = ksps;
    }
}
