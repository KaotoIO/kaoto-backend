package io.kaoto.backend.camel.service.step.parser.camelroute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import io.kaoto.backend.api.service.step.parser.StepParserService;
import io.kaoto.backend.camel.KamelHelper;
import io.kaoto.backend.camel.model.deployment.camelroute.Integration;
import io.kaoto.backend.camel.model.deployment.kamelet.Bean;
import io.kaoto.backend.camel.model.deployment.kamelet.Flow;
import io.kaoto.backend.camel.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.camel.model.deployment.rest.Rest;
import io.kaoto.backend.camel.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.step.Step;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 * 🐱miniclass IntegrationStepParserService (StepParserService)
 */
@ApplicationScoped
@RegisterForReflection
public class IntegrationStepParserService implements StepParserService<Step> {

    private static final ObjectMapper YAML_MAPPER = new ObjectMapper(new YAMLFactory())
        .registerModule(new SimpleModule().addDeserializer(Flow.class, new FlowDeserializer()));

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
            Integration integration = KamelHelper.YAML_MAPPER.readValue(input, Integration.class);

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
            if (integration.getMetadata().getAnnotations().containsKey(KameletStepParserService.DESCRIPTION_ANNO)) {
                res.getMetadata().put(KameletStepParserService.DESCRIPTION,
                        integration.getMetadata().getAnnotations().get(KameletStepParserService.DESCRIPTION_ANNO));
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
        if (!appliesTo(input)) {
            throw new IllegalArgumentException(
                    "Wrong format provided. This is not parseable by us.");
        }

        List<ParseResult<Step>> answer = new ArrayList<>();
        ParseResult<Step> metadata = new ParseResult<>();
        metadata.setParameters(new ArrayList<>());
        answer.add(metadata);
        try {
            Integration integration = YAML_MAPPER.readValue(input, Integration.class);

            ksps.processMetadata(metadata, integration.getMetadata());

            if (integration.getSpec().get_flows() != null) {
                integration.getSpec().get_flows().forEach(flow -> {
                    if (flow.getBeans() != null) {
                        processBeans(metadata, flow.getBeans());
                    } else {
                        processFlows(answer, flow);
                    }
                });
                integration.getSpec().get_flows().clear();
            }

            //Let's store the spec to make sure we don't lose anything else
            ((Map<String, Object>)metadata
                    .getMetadata()
                    .get("additionalProperties"))
                    .put("spec", integration.getSpec());
            if (integration.getMetadata().getAnnotations().containsKey(KameletStepParserService.DESCRIPTION_ANNO)) {
                metadata.getMetadata().put(KameletStepParserService.DESCRIPTION,
                        integration.getMetadata().getAnnotations().get(KameletStepParserService.DESCRIPTION_ANNO));
            }

        } catch (Exception e) {
            throw new IllegalArgumentException("Error trying to parse.", e);
        }

        return answer;
    }

    private void processFlows(List<ParseResult<Step>> answer, Flow flow) {
        ParseResult<Step> res = new ParseResult<>();
        res.setParameters(new ArrayList<>());
        List<Step> steps = new ArrayList<>();
        res.setSteps(steps);

        var from = flow.getFrom();
        if (from == null) {
            from = flow.getRest();
        }
        if (from instanceof Rest rest) {
            var restStep = rest.getStep(ksps, false, true);
            if (restStep != null) {
                steps.add(restStep);
            }
        } else if (from != null) {
            var fromStep = ksps.processStep(from, true, false);
            if (fromStep != null) {
                steps.add(fromStep);
            }
            if (from.getSteps() != null) {
                for (FlowStep step : from.getSteps()) {
                    //end is always false in this case because we can always edit one step after it
                    var processed = ksps.processStep(step, false, false);
                    if (processed != null) {
                        steps.add(processed);
                    }
                }
            }
        }
        if (res.getMetadata() == null) {
            res.setMetadata(new LinkedHashMap<>());
        }
        if (flow.getId() != null) {
            res.getMetadata().put("name", flow.getId());
        }
        if (flow.getRouteConfigurationId() != null) {
            res.getMetadata().put("route-configuration-id", flow.getRouteConfigurationId());
        }
        if (flow.getDescription() != null) {
            res.getMetadata().put(KameletStepParserService.DESCRIPTION, flow.getDescription());
        }
        answer.add(res);
    }

    private void processBeans(ParseResult<Step> parsedMetadata, List<Bean> beans) {
        if (parsedMetadata.getMetadata() == null) {
            parsedMetadata.setMetadata(new LinkedHashMap<>());
        }
        parsedMetadata.getMetadata().put("beans", beans);
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
