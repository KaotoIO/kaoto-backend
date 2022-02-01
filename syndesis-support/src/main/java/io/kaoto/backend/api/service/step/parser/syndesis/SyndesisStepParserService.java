package io.kaoto.backend.api.service.step.parser.syndesis;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.step.parser.StepParserService;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;
import io.syndesis.common.model.integration.ImmutableIntegration;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 🐱class SyndesisStepParserService
 */
@ApplicationScoped
public class SyndesisStepParserService
        implements StepParserService<Step> {

    private Logger log =
            Logger.getLogger(SyndesisStepParserService.class);
    private ObjectMapper mapper = new ObjectMapper()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .disable(JsonParser.Feature.AUTO_CLOSE_SOURCE);
    private StepCatalog catalog;

    @Inject
    public void setCatalog(final StepCatalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public List<Step> parse(final String input) {
        return deepParse(input).getSteps();
    }

    @Override
    public ParseResult<Step> deepParse(final String input) {
        ParseResult<Step> parseResult = new ParseResult<>();
        parseResult.setSteps(new ArrayList<>());

        try {
            Map<String, Object> map = mapper.readValue(input, Map.class);

            Map<String, Object> integrationsArray =
                    (Map<String, Object>) map.get("integrations");

            for (Object integration : integrationsArray.values()) {
                Map<String, Object> integrationMap =
                        (Map<String, Object>) integration;

                List<Object> flows = (List<Object>) integrationMap.get("flows");

                for (Object f : flows) {
                    Map<String, Object> flow = (Map<String, Object>) f;
                    List<Object> steps =
                            (List<Object>) flow.get("steps");
                    for (Object s : steps) {
                        Map<String, Object> syndesisStep =
                                (Map<String, Object>) s;

                        switch (syndesisStep.get("stepKind").toString()) {
                            case "endpoint":
                                processEndpoint(parseResult, syndesisStep);
                                break;
                            case "log":
                                processLog(parseResult, syndesisStep);
                                break;
                            case "mapper":
                            default:
                                log.warn("Unsupported step: '"
                                        + syndesisStep.get("stepKind") + "' ->"
                                        + " " + syndesisStep.keySet());
                        }
                    }
                }
            }
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            throw new IllegalArgumentException(
                    "Wrong format provided. This is not parseable by us", e);
        }

        return parseResult;
    }

    private void processLog(final ParseResult<Step> parseResult,
                           final Map<String, Object> syndesisStep) {

        Step step = catalog.getReadOnlyCatalog().searchStepByName("log");
        parseResult.getSteps().add(step);
        Map<String, Object> properties =
                (Map<String, Object>) syndesisStep
                        .getOrDefault("configuredProperties",
                                Collections.EMPTY_MAP);
// {bodyLoggingEnabled=true, contextLoggingEnabled=false, customText=prefix}
        for (Parameter p : step.getParameters()) {
            if (p.getId()
                    .equalsIgnoreCase("showBody")) {
                p.setValue(properties.getOrDefault(
                        "bodyLoggingEnabled",
                        false));
                break;
            }
        }
    }

    private void processEndpoint(final ParseResult<Step> parseResult,
                           final Map<String, Object> syndesisStep) {
        var action = (Map<String, Object>) syndesisStep.get("action");
        var descriptor = (Map<String, Object>) action.get("descriptor");
        System.out.println(descriptor);
        var component = descriptor.get("componentScheme").toString();
        var step = catalog.getReadOnlyCatalog()
                .searchStepByName(component);
        parseResult.getSteps().add(step);

        if (step == null) {
            log.error("Unsupported endpoint step: " + component);
            log.error(syndesisStep.keySet());
            return;
        }

        var properties = (Map<String, Object>) descriptor.getOrDefault(
                "configuredProperties", Collections.EMPTY_MAP);
        for (var prop : properties.entrySet()) {
            log.debug("Syndesis property: " + prop);
            var key = prop.getKey();
            var value = prop.getValue();
            for (var p : step.getParameters()) {
                if (p.getId().equalsIgnoreCase(key)) {
                    p.setValue(value);
                    log.debug("Assigned! " + p);
                    break;
                }
            }
        }

        if (descriptor.containsKey("propertyDefinitionSteps")) {
            var definitionSteps = (List<Map<String, Object>>) descriptor
                    .get("propertyDefinitionSteps");

            for (var def : definitionSteps) {
                var props = (Map<String, Object>) def.get("properties");
                for (var property : props.entrySet()) {
                    String prop = property.getKey();
                    var value = (Map<String, Object>) property.getValue();

                    String kind = value.get("kind").toString();
                    if ("path".equalsIgnoreCase(kind)) {
                        for (var p : step.getParameters()) {
                            if (p.isPath()) {
                                p.setValue(value.get("defaultValue"));
                                break;
                            }
                        }
                    } else if ("parameter".equalsIgnoreCase(kind)) {
                        for (var p : step.getParameters()) {
                            if (p.getId().equalsIgnoreCase(prop)) {
                                p.setValue(value.get("defaultValue"));
                                break;
                            }
                        }
                    }
                }
            }

        }
    }

    @Override
    public boolean appliesTo(final String input) {
        try {
            mapper.readValue(input, ImmutableIntegration.class);
        } catch (JsonProcessingException e) {
            return false;
        }
        return true;
    }
}
