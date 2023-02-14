package io.kaoto.backend.api.service.step.parser.kamelet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.step.parser.StepParserService;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.model.deployment.kamelet.Kamelet;
import io.kaoto.backend.model.deployment.kamelet.KameletDefinitionProperty;
import io.kaoto.backend.model.deployment.kamelet.KameletSpec;
import io.kaoto.backend.model.deployment.kamelet.step.Filter;
import io.kaoto.backend.model.deployment.kamelet.step.choice.Choice;
import io.kaoto.backend.model.parameter.ArrayParameter;
import io.kaoto.backend.model.parameter.BooleanParameter;
import io.kaoto.backend.model.parameter.IntegerParameter;
import io.kaoto.backend.model.parameter.NumberParameter;
import io.kaoto.backend.model.parameter.ObjectParameter;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.parameter.StringParameter;
import io.kaoto.backend.model.step.Step;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 🐱miniclass KameletStepParserService (StepParserService)
 */
@ApplicationScoped
public class KameletStepParserService
        implements StepParserService<Step> {

    public static final String SIMPLE = "simple";
    public static final String JQ = "jq";
    public static final String OTHERWISE = "otherwise";
    public static final String CONSTANT = "constant";
    public static final String NAME = "name";
    public static final Pattern PATTERN = Pattern.compile("[\n|\r]kind:(.+)[\n|\r]", Pattern.CASE_INSENSITIVE);
    private final Logger log =
            Logger.getLogger(KameletStepParserService.class);

    private StepCatalog catalog;

    public String identifier() {
        return "Kamelet";
    }

    public String description() {
        return "A Kamelet is a snippet of a route. It defines meta building "
                + "blocks or steps that can be reused on integrations.";
    }

    @Inject
    public void setCatalog(final StepCatalog catalog) {
        this.catalog = catalog;
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
                    new ObjectMapper(
                            new YAMLFactory()).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,  false);
            Kamelet kamelet = yamlMapper.readValue(input, Kamelet.class);

            processMetadata(res, kamelet.getMetadata());
            processSpec(steps, res, kamelet.getSpec());
            processParameters(res, kamelet.getSpec());

        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error trying to parse.", e);
        }

        res.setSteps(steps.stream().filter(Objects::nonNull).toList());

        return res;
    }

    private void processParameters(final ParseResult<Step> res,
                                   final KameletSpec spec) {
        res.setParameters(new ArrayList<>());
        if (spec.getDefinition() != null
                && spec.getDefinition().getProperties() != null) {
            Map<String, KameletDefinitionProperty> props =
                    spec.getDefinition().getProperties();

            for (var prop : props.entrySet()) {
                String key = prop.getKey();
                var def = prop.getValue();
                Parameter p = new ObjectParameter();
                switch (def.getType()) {
                    case "string":
                        p = new StringParameter(key, def.getTitle(),
                        def.getDescription(), def.getDefault(), def.getFormat());
                        break;
                    case "number":
                        p = new NumberParameter(key, def.getTitle(),
                        def.getDescription(), Double.valueOf(def.getDefault()));
                        break;
                    case "integer":
                        p = new IntegerParameter(key, def.getTitle(),
                                def.getDescription(),
                                def.getDefault() != null? Integer.valueOf(def.getDefault()) : null);
                        break;
                    case "boolean":
                        p = new BooleanParameter(key, def.getTitle(),
                                def.getDescription(),
                                def.getDefault() != null? Boolean.valueOf(def.getDefault()) : null);
                        break;
                    case "array":
                        p = new ArrayParameter(key, def.getTitle(),
                                def.getDescription(),
                                def.getDefault() != null? def.getDefault().split(",") : null);
                        break;
                    default:
                        p = new ObjectParameter(key, def.getTitle(), def.getDescription(), def.getDefault());
                }
                res.getParameters().add(p);
            }
        }
    }

    private void processSpec(final List<Step> steps,
                             final ParseResult<Step> res,
                             final KameletSpec spec) {
        if (spec.getTemplate() != null && spec.getTemplate().getFrom() != null) {
            steps.add(processStep(spec.getTemplate().getFrom(), true, false));

            final var fromSteps = spec.getTemplate().getFrom().getSteps();
            if (fromSteps != null) {
                int i = 0;
                for (FlowStep flowStep : fromSteps) {
                    steps.add(processStep(flowStep, false, i++ == fromSteps.size() - 1));
                }
            }
        }

        res.getMetadata().put("definition", spec.getDefinition());
        res.getMetadata().put("beans", spec.getTemplate().getBeans());
        res.getMetadata().put("dependencies", spec.getDependencies());
    }

    public Step processStep(final FlowStep step, final Boolean start, final Boolean end) {
        try {
            return step.getStep(catalog, this, start, end);
        } catch (Exception e) {
            log.warn("Can't parse step -> " + step, e);
        }
        return null;
    }


    public String getChoiceIdentifier(final Choice flow) {
        return getChoiceCondition(flow);
    }

    public String getChoiceCondition(final Choice flow) {
        return flow.getJq() != null && !flow.getJq().isEmpty() ? flow.getJq() : flow.getSimple();
    }

    public String getFilterIdentifier(final Filter flow) {
        return getFilterCondition(flow);
    }

    public String getFilterCondition(final Filter flow) {
        return flow.getJq() != null && !flow.getJq().isEmpty() ? flow.getJq() : flow.getSimple();
    }


    public void setValuesOnParameters(final Step step, final String uri) {

        String path = uri.substring(uri.indexOf(':') + 1);
        if (path.contains("?")) {
            path = path.substring(0, path.indexOf('?'));
        }
        Collections.sort(step.getParameters());
        String[] pathParts = path.split(":");
        int i = 0;
        for (Parameter p : step.getParameters()) {
            if (i >= pathParts.length) {
                break;
            }
            if (p.isPath()) {
                p.setValue(p.convertToType(pathParts[i++]));
            }
        }

        Pattern pattern = Pattern.compile("[?&]([^=]+)=([^&\\n]+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(uri);

        while (matcher.find()) {
            setValueOnStepProperty(step, matcher.group(1), matcher.group(2));
        }
    }

    public void setValuesOnParameters(final Step step, final Map<String, String> properties) {

        if (properties != null) {
            for (Map.Entry<String, String> c : properties.entrySet()) {
                setValueOnStepProperty(step, c.getKey(), c.getValue());
            }
        }

    }

    public void setValueOnStepProperty(final Step step, final String key, final Object value) {
        for (Parameter p : step.getParameters()) {
            if (p.getId().equalsIgnoreCase(key)) {
                p.setValue(p.convertToType(value));
                break;
            }
        }
    }

    public void processMetadata(final ParseResult<Step> result, final ObjectMeta metadata) {
        result.setMetadata(new LinkedHashMap<>());

        var labels = new LinkedHashMap<String, String>();
        result.getMetadata().put("labels", labels);
        if (metadata.getLabels() != null) {
            labels.putAll(metadata.getLabels());
        }

        var annotations = new LinkedHashMap<String, String>();
        result.getMetadata().put("annotations", annotations);
        if (metadata.getAnnotations() != null) {
            annotations.putAll(metadata.getAnnotations());
            annotations.put("icon", annotations.get("camel.apache.org/kamelet.icon"));
        }

        var additionalProperties = new LinkedHashMap<String, Object>();
        result.getMetadata().put("additionalProperties", additionalProperties);
        if (metadata.getAdditionalProperties() != null) {
            additionalProperties.putAll((Map<String, Object>)
                    metadata.getAdditionalProperties().getOrDefault("additionalProperties", Collections.emptyMap()));
        }

        result.getMetadata().put(NAME, metadata.getName());
    }

    @Override
    public boolean appliesTo(final String yaml) {
        String[] kinds = new String[]{"Kamelet", "Knative", "Camel-Connector", "EIP", "EIP-BRANCH"};

        Matcher matcher = PATTERN.matcher(yaml);
        if (matcher.find()) {
            return Arrays.stream(kinds).anyMatch(k -> k.equalsIgnoreCase(matcher.group(1).trim()));
        }

        return false;
    }

}
