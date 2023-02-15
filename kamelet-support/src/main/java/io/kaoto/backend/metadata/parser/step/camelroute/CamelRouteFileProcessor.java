package io.kaoto.backend.metadata.parser.step.camelroute;

import io.kaoto.backend.metadata.parser.JsonProcessFile;
import io.kaoto.backend.model.parameter.BooleanParameter;
import io.kaoto.backend.model.parameter.NumberParameter;
import io.kaoto.backend.model.parameter.ObjectParameter;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.parameter.StringParameter;
import io.kaoto.backend.model.step.Step;
import org.jboss.logging.Logger;

import javax.json.Json;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CamelRouteFileProcessor extends JsonProcessFile<Step> {

    private static final String INVALID_TYPE = "invalid";
    public static final String DESCRIPTION = "description";
    public static final String DEFAULT_VALUE = "defaultValue";
    public static final String DISPLAY_NAME = "displayName";
    public static final String COMPONENT = "component";
    public static final String PROPERTIES = "properties";
    
    private static String DEFAULT_ICON_STRING;

    private static final Logger log = Logger.getLogger(CamelRouteFileProcessor.class);

    static {
        try {
            DEFAULT_ICON_STRING =
                    new String(CamelRouteFileProcessor.class.getResourceAsStream("default-icon.txt").readAllBytes(),
                            StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("Couldn't find the default icon for camel routes.", e);
        }

    }

    record ParsedCamelComponentFromJson(
        String id,
        String kind,
        String icon,

        String title,
        String description,
        String group,
        String type,
        List<Parameter> parameters,
        List<String> required
    ) { }

    @Override
    protected List<Step> parseInputStream(final Reader input) {
        try {
            JsonReader reader = Json.createReader(input);
            JsonObject json = reader.readObject();
            Step step = convertToStep(json);

            if (step != null) {
                return duplicateCamelOperatorToKameletsOfCompatibleTypes(step);
            } else {
                return List.of();
            }
        } catch (JsonException e) {
            log.error("Error parsing CamelRoute", e);
        }

        return List.of();
    }


    private Step convertToStep(final JsonObject json) {
        if (!isCamelRouteJson(json)) {
            return null;
        }

        ParsedCamelComponentFromJson parsedCamelJson = getDataFromJson(json);

        Step step = new Step();

        step.setId(parsedCamelJson.id());
        step.setName(parsedCamelJson.id());
        step.setIcon(parsedCamelJson.icon());
        step.setKind(parsedCamelJson.kind());
        step.setTitle(parsedCamelJson.title());
        step.setDescription(parsedCamelJson.description());
        step.setGroup(parsedCamelJson.group());
        step.setParameters(parsedCamelJson.parameters());
        step.setRequired(parsedCamelJson.required());
        step.setType(parsedCamelJson.type());

        return step;
    }

    private boolean isCamelRouteJson(final JsonObject json) {
        //Naive filtering by file structure
        return json.keySet().containsAll(Set.of(COMPONENT, PROPERTIES));
    }

    private ParsedCamelComponentFromJson getDataFromJson(
            final JsonObject json) {
        final String camelKind = "Camel-Connector";
        final String defaultGroup = "Camel-Component";
        final String defaultIcon = DEFAULT_ICON_STRING;

        JsonObject component = json.getJsonObject(COMPONENT);
        JsonObject properties = json.getJsonObject(PROPERTIES);

        String id = component.getString("name");
        String title = component.getString("title");
        String description = component.getString(DESCRIPTION);
        String type = getStepType(component);

        Map<String, Map<String, String>> propertiesParsed = properties
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        property -> {
                            var hashMap = new HashMap<String, String>();
                            var jsonObject = property.getValue().asJsonObject();

                            jsonObject.forEach((key, value) ->
                                    hashMap.put(key, value.toString().replace("\"", "")));

                            return hashMap;
                        }
                ));

        List<String> requiredProperties = propertiesParsed.entrySet().stream()
                .filter(property -> Boolean.parseBoolean(property.getValue().getOrDefault("required", "false")))
                .map(Map.Entry::getKey).toList();


        List<Parameter> parameters = propertiesParsed.entrySet().stream().map(
                parameter -> {
                    Map<String, String> parameterData = parameter.getValue();

                    Map<String, Function<Map.Entry<
                            String, Map<String, String>>,
                            Parameter>> typeToClassConversion = Map.of(
                            "string", this::getStringParameter,
                            "object", this::getObjectParameter,
                            "integer", this::getNumberParameter,
                            "boolean", this::getBooleanParameter
                    );

                    Parameter parsedParameter = typeToClassConversion
                            .getOrDefault(parameterData.get("type"), this::getObjectParameter)
                            .apply(parameter);

                    if ("path".equals(parameterData.get("kind"))) {
                        parsedParameter.setPath(true);
                    }

                    return parsedParameter;
                }
        ).toList();

        //Assign path order to parameters
        //We will have to assume the component defined them in order in the JSON because
        //the JSON does not give this information.
        int i = 0;
        for (Parameter p : parameters) {
            if (p.isPath()) {
                p.setPathOrder(i++);
            }
        }
        //But this assumption does not always work
        //Here are the weird cases
        if ("avro".equalsIgnoreCase(id)) {
            for (Parameter p : parameters) {
                if ("messageName".equalsIgnoreCase(p.getId())) {
                    p.setPathSeparator("/");
                    p.setPathOrder(0);
                } else if ("host".equalsIgnoreCase(p.getId())) {
                    p.setPathOrder(2);
                } else if ("port".equalsIgnoreCase(p.getId())) {
                    p.setPathOrder(1);
                } else if ("transport".equalsIgnoreCase(p.getId())) {
                    p.setPathOrder(3);
                }
            }
        }

        return new ParsedCamelComponentFromJson(
                id,
                camelKind,
                defaultIcon,
                title,
                description,
                defaultGroup,
                type,
                parameters,
                requiredProperties
        );
    }

    private StringParameter getStringParameter(
            final Map.Entry<String, Map<String, String>> parameter) {
        String id = parameter.getKey();
        Map<String, String> parameterData = parameter.getValue();

        return new StringParameter(
                id,
                parameterData.get(DISPLAY_NAME),
                parameterData.get(DESCRIPTION),
                parameterData.getOrDefault(DEFAULT_VALUE, null),
                null
        );
    }

    private NumberParameter getNumberParameter(
            final Map.Entry<String, Map<String, String>> parameter) {
        //IntegerParameter doesn't fit some long types there are present
        String id = parameter.getKey();
        Map<String, String> parameterData = parameter.getValue();
        Long value;

        if (parameterData.containsKey(DEFAULT_VALUE)) {
            value = Long.parseLong(parameterData
                    .get(DEFAULT_VALUE)
                    .replace("L", ""));
        } else {
            value = null;
        }

        return new NumberParameter(
                id,
                parameterData.get(DISPLAY_NAME),
                parameterData.get(DESCRIPTION),
                value
        );
    }

    private ObjectParameter getObjectParameter(
            final Map.Entry<String, Map<String, String>> parameter) {
        String id = parameter.getKey();
        Map<String, String> parameterData = parameter.getValue();

        return new ObjectParameter(
                id,
                parameterData.get(DISPLAY_NAME),
                parameterData.get(DESCRIPTION),
                parameterData.getOrDefault(DEFAULT_VALUE, null)
        );
    }

    private BooleanParameter getBooleanParameter(
            final Map.Entry<String, Map<String, String>> parameter) {
        String id = parameter.getKey();
        Map<String, String> parameterData = parameter.getValue();

        return new BooleanParameter(
                id,
                parameterData.get(DISPLAY_NAME),
                parameterData.get(DESCRIPTION),
                Boolean.parseBoolean(parameterData
                        .getOrDefault(DEFAULT_VALUE, "false"))
        );
    }

    private String getStepType(final JsonObject component) {
        final boolean isCamelComponentSourceOnly = component.getBoolean("consumerOnly");
        final boolean isCamelComponentSinkOnly = component.getBoolean("producerOnly");
        final boolean canCamelComponentBeSourceAndSink = !isCamelComponentSourceOnly && !isCamelComponentSinkOnly;

        if (isCamelComponentSourceOnly) {
            return Step.START;
        } else if (isCamelComponentSinkOnly) {
            return Step.END;
        } else if (canCamelComponentBeSourceAndSink) {
            return Step.MIDDLE;
        } else {
            return INVALID_TYPE;
        }
    }

    private List<Step> duplicateCamelOperatorToKameletsOfCompatibleTypes(
            final Step step) {

        if (INVALID_TYPE.equals(step.getType())) {
            log.error("Parsed step has an invalid type. This can occur because "
                    + "the parsed camel component has both producerOnly and "
                    + "consumerOnly to true, which can't ever be.\n"
                    + "The parsed step will be skipped.\n"
                    + "The parsed step id is " + step.getId());
            return List.of();
        }

        Map<String, List<String>> typesToDuplicateTo =
                Map.of(
                        Step.START, List.of(Step.START),
                        Step.MIDDLE, List.of(Step.START, Step.MIDDLE, Step.END),
                        Step.END, List.of(Step.MIDDLE, Step.END)
                );

        return typesToDuplicateTo.get(step.getType()).stream().map(type -> duplicateStepToType(step, type)).toList();
    }

    private Step duplicateStepToType(final Step step, final String type) {
        Step duplicatedStep = new Step();

        Map<String, String> typeToIdConversion = Map.of(
                Step.MIDDLE, "action",
                Step.END, "producer",
                Step.START, "consumer"
        );

        String typedId = step.getId() + "-" + typeToIdConversion.get(type);

        duplicatedStep.setId(typedId);
        duplicatedStep.setName(step.getId());
        duplicatedStep.setIcon(step.getIcon());
        duplicatedStep.setKind(step.getKind());
        duplicatedStep.setTitle(step.getTitle());
        duplicatedStep.setDescription(step.getDescription());
        duplicatedStep.setGroup(step.getGroup());
        duplicatedStep.setParameters(step.getParameters());
        duplicatedStep.setRequired(step.getRequired());
        duplicatedStep.setType(type);

        return duplicatedStep;
    }
}
