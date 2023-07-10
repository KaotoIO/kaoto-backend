package io.kaoto.backend.api.service.step.parser.kamelet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.fabric8.kubernetes.api.model.AnyType;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.step.parser.StepParserService;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.model.deployment.kamelet.Kamelet;
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
import io.quarkus.runtime.util.StringUtil;
import org.apache.camel.v1alpha1.kameletspec.definition.Properties;
import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 🐱miniclass KameletStepParserService (StepParserService)
 */
@ApplicationScoped
public class KameletStepParserService implements StepParserService<Step> {

    public static final Pattern PATTERN = Pattern.compile("[\n|\r]kind:(.+)[\n|\r]", Pattern.CASE_INSENSITIVE);

    public static final String SIMPLE = "simple";
    public static final String JQ = "jq";
    public static final String OTHERWISE = "otherwise";
    public static final String CONSTANT = "constant";
    public static final String NAME = "name";
    public static final String GROOVY = "groovy";
    public static final String JAVASCRIPT = "javascript";
    public static final String EXPRESSION = "expression";
    private final Logger log = Logger.getLogger(KameletStepParserService.class);

    private StepCatalog catalog;

    public StepCatalog getCatalog() {
        return catalog;
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
            ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());
            Kamelet kamelet = yamlMapper.readValue(input, Kamelet.class);

            processMetadata(res, kamelet.getMetadata());
            processSpec(steps, res, kamelet.getSpec());
            processParameters(res, kamelet.getSpec());

            //Let's store the spec to make sure we don't lose anything else
            if (kamelet.getSpec().getTemplate() != null) {
                kamelet.getSpec().getTemplate().setFrom(null);
                kamelet.getSpec().getTemplate().setBeans(null);
            }
            res.getMetadata().put("spec", kamelet.getSpec());

        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error trying to parse.", e);
        }

        res.setSteps(steps.stream().filter(Objects::nonNull).toList());

        return res;
    }

    @Override
    public List<ParseResult<Step>> getParsedFlows(String input) {
        var res = new LinkedList<ParseResult<Step>>();
        var kamelet = deepParse(input);
        res.add(kamelet);
        // move beans to the upper level to align with other DSLs
        if (kamelet.getMetadata().get("beans") != null) {
            var meta = new ParseResult<Step>();
            meta.setMetadata(new LinkedHashMap<>());
            meta.getMetadata().put("beans", kamelet.getMetadata().get("beans"));
            res.add(meta);
            kamelet.getMetadata().remove("beans");
        }
        return res;
    }

    private void processParameters(final ParseResult<Step> res,
                                   final KameletSpec spec) {
        res.setParameters(new ArrayList<>());
        if (spec.getDefinition() != null
                && spec.getDefinition().getProperties() != null) {
            Map<String, Properties> props = spec.getDefinition().getProperties();

            for (var prop : props.entrySet()) {
                String key = prop.getKey();
                var def = prop.getValue();
                Parameter p;
                switch (def.getType()) {
                    case "string":
                        p = new StringParameter(key,
                                def.getTitle(),
                                def.getDescription(),
                                def.getNullable(),
                                def.get_enum() != null ? def.get_enum().toArray(new String[]{}) : null,
                                def.getExample() != null ?
                                        new String[]{String.valueOf(def.getExample().getValue())} :
                                        null,
                                def.get_default() != null ?
                                        String.valueOf(def.get_default().getValue()) : null,
                                def.getFormat());
                        break;
                    case "number":
                        p = new NumberParameter(key, def.getTitle(),
                                def.getDescription(), def.getNullable(),
                                def.get_enum() != null ? def.get_enum().toArray(new Double[]{}) : null,
                                def.getExample() != null ?
                                        new Number[]{Double.valueOf(String.valueOf(def.getExample().getValue()))} :
                                        null,
                                def.get_default() != null ?
                                        Double.valueOf(String.valueOf(def.get_default().getValue()))
                                        : null);
                        break;
                    case "integer":
                        p = new IntegerParameter(key, def.getTitle(),
                                def.getDescription(), def.getNullable(),
                                def.get_enum() != null ? def.get_enum().toArray(new Integer[]{}) : null,
                                def.getExample() != null ?
                                        new Integer[]{Integer.valueOf(String.valueOf(def.getExample().getValue()))} :
                                        null,
                                def.get_default() != null ?
                                        Integer.valueOf(String.valueOf(def.get_default().getValue())) : null);
                        break;
                    case "boolean":
                        p = new BooleanParameter(key, def.getTitle(),
                                def.getDescription(), def.getNullable(),
                                def.get_enum() != null ? def.get_enum().toArray(new Boolean[]{}) : null,
                                def.getExample() != null ?
                                        new Boolean[]{Boolean.valueOf(String.valueOf(def.getExample().getValue()))} :
                                        null,
                                def.get_default() != null ?
                                        Boolean.valueOf(String.valueOf(def.get_default().getValue())) : null);
                        break;
                    case "array":
                        p = new ArrayParameter(key, def.getTitle(),
                                def.getDescription(), def.getNullable(),
                                def.get_enum() != null ? def.get_enum().toArray(new Object[][]{}) : null,
                                def.getExample() != null ?
                                        new Object[][]{ (Object[]) def.getExample().getValue()} :
                                        null,
                                def.get_default() != null ?
                                        String.valueOf(def.get_default().getValue()).split(",") :
                                        null);
                        break;
                    default:
                        p = new ObjectParameter(key, def.getTitle(), def.getDescription(), def.getNullable(),
                                def.get_enum().toArray(new AnyType[0]),
                                def.getExample() != null ?
                                        new Object[]{ def.getExample().getValue()} :
                                        null,
                                def.get_default());
                }
                res.getParameters().add(p);
            }
        }
    }

    private void processSpec(final List<Step> steps,
                             final ParseResult<Step> res,
                             final KameletSpec spec) {
        if (spec.getTemplate() != null) {
            final var template = spec.getTemplate();
            if (template.getFrom() != null) {
                steps.add(processStep(template.getFrom(), true, false));

                final var fromSteps = template.getFrom().getSteps();
                if (fromSteps != null) {
                    for (FlowStep flowStep : fromSteps) {
                        //end is always false in this case because we can always edit one step after it
                        steps.add(processStep(flowStep, false, false));
                    }
                }
            }
        }

        res.getMetadata().put("definition", spec.getDefinition());
        res.getMetadata().put("beans", spec.getTemplate().getBeans());
        res.getMetadata().put("dependencies", spec.getDependencies());
        if (!StringUtil.isNullOrEmpty(spec.getDefinition().getDescription())) {
            res.getMetadata().put("description", spec.getDefinition().getDescription());
        }
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
        if (step.getName().equalsIgnoreCase("http")
                || step.getName().equalsIgnoreCase("https")) {
            path = step.getName() + ":" + path;
        } else if (path.contains("?")) {
            path = path.substring(0, path.indexOf('?'));
        }
        var pathParameters = new LinkedList<Parameter>();
        pathParameters.addAll(step.getParameters().stream().parallel()
                .filter(Objects::nonNull).filter(s -> s.isPath()).toList());

        //Let's iterate over the path string to extract the parameters
        if (!pathParameters.isEmpty()) {
            //Make sure the order is right
            Collections.sort(pathParameters);

            Parameter previous = null;
            List<String> pathParts = new LinkedList<>();
            for (Parameter p : pathParameters) {
                //To split, we will have to consider the path separator of the next path param,
                // not of the current one
                if (previous != null) {
                    var endIndex = path.indexOf(p.getPathSeparator());
                    if (endIndex < 0) {
                        //If there is no path separator in the string, then everything to the end is this one
                        endIndex = path.length();
                    }
                    pathParts.add(path.substring(0, endIndex));
                    path = path.substring(endIndex);
                }

                //We remove the path separator from the beginning of this path parameter
                //The if is here just in case it is malformed
                if (path.length() >= p.getPathSeparator().length()) {
                    path = path.substring(p.getPathSeparator().length());
                }
                //prepare for next iteration
                previous = p;
            }

            //Last path parameter here!
            if (path.length() > 0) {
                pathParts.add(path);
            }

            int i = 0;
            for (Parameter p : pathParameters) {
                if (i >= pathParts.size()) {
                    break;
                }
                if (p.isPath()) {
                    p.setValue(p.convertToType(pathParts.get(i++)));
                }
            }

            //Aaah, someone used the path separator as part of the parameter value. Sneaky.
            //This should be a string. Please, let it be a string. What else could it be?
            if (i < pathParts.size() && previous != null) {
                for (; i < pathParts.size(); i++) {
                    previous.setValue(previous.getValue().toString()
                            + previous.getPathSeparator() + previous.convertToType(pathParts.get(i++)));
                }
                Pattern pattern = Pattern.compile("[?&]([^=]+)=([^&\\n]+)", Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(uri);

                while (matcher.find()) {
                    setValueOnStepProperty(step, matcher.group(1), matcher.group(2));
                }
            }
        }
    }

    public void setValuesOnParameters(final Step step, final Map<String, Object> properties) {
        if (properties != null) {
            for (Map.Entry<String, Object> c : properties.entrySet()) {
                if (c.getValue() != null) {
                    if (step.getName().equalsIgnoreCase("kamelet")) {
                        setValueOnStepProperty(step, c.getKey(), c.getValue(), "kaoto-parameters");
                    } else {
                        setValueOnStepProperty(step, c.getKey(), c.getValue());
                    }
                }
            }
        }

    }

    public void setValueOnStepProperty(final Step step, final String key, final Object value,
                                       final String extraPropertiesIn) {
        if (value != null) {
            boolean found = false;
            for (Parameter p : step.getParameters()) {
                if (p.getId().equalsIgnoreCase(key)) {
                    final var typedValue = p.convertToType(value);
                    if (typedValue != null && !typedValue.equals(p.getDefaultValue())) {
                        p.setValue(typedValue);
                    } else if (typedValue == null && value != null) {
                        //It may be a string with a variable, for example
                        p.setValue(value);
                    }
                    found = true;
                    break;
                }
            }

            if (extraPropertiesIn != null && !found) {
                for (Parameter p : step.getParameters()) {
                    if (p.getId().equalsIgnoreCase(extraPropertiesIn)) {
                        if (p.getValue() == null) {
                            p.setValue(new LinkedHashMap<String, Object>());
                        }
                        ((Map<String, Object>) p.getValue()).put(key, value);
                        break;
                    }
                }
            }
        }
    }

    public void setValueOnStepProperty(final Step step, final String key, final Object value) {
        setValueOnStepProperty(step, key, value, null);
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
            if (annotations.containsKey("camel.apache.org/kamelet.icon")) {
                annotations.put("icon", annotations.get("camel.apache.org/kamelet.icon"));
            }
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
        if (!yaml.contains("kind: KameletBinding") && matcher.find()) {
            return Arrays.stream(kinds).anyMatch(k -> k.equalsIgnoreCase(matcher.group(1).trim()));
        }

        return false;
    }

}
