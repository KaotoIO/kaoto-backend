package io.kaoto.backend.metadata.parser.step.camelroute;

import io.kaoto.backend.metadata.parser.JsonProcessFile;
import io.kaoto.backend.model.parameter.BooleanParameter;
import io.kaoto.backend.model.parameter.ObjectParameter;
import io.kaoto.backend.model.parameter.NumberParameter;
import io.kaoto.backend.model.parameter.StringParameter;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;
import org.jboss.logging.Logger;

import javax.json.Json;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CamelRouteFileProcessor extends JsonProcessFile<Step> {

    private static final String SOURCE_TYPE = "source";
    private static final String SINK_TYPE = "sink";
    private static final String ACTION_TYPE = "action";
    private static final String INVALID_TYPE = "invalid";

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
    private final Logger log = Logger.getLogger(CamelRouteFileProcessor.class);

    @Override
    protected List<Step> parseFile(final File f) {

        log.info("Parsing " +  f.getAbsolutePath());

        try (FileReader fr = new FileReader(f)) {

            JsonReader reader = Json.createReader(fr);
            JsonObject json = reader.readObject();
            Step step = convertToStep(json);

            if (step != null) {
                return duplicateCamelOperatorToKameletsOfCompatibleTypes(step);
            } else {
                return List.of();
            }
        } catch (IOException | JsonException e) {
            log.error("Error parsing '"  + f.getAbsolutePath() + "'", e);
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
        return json.keySet().containsAll(Set.of("component", "properties"));
    }

    private ParsedCamelComponentFromJson getDataFromJson(
            final JsonObject json) {
        final String camelKind = "Camel-Connector";
        final String defaultGroup = "Camel-Component";
        final String defaultIcon = DEFAULT_ICON_STRING;

        JsonObject component = json.getJsonObject("component");
        JsonObject properties = json.getJsonObject("properties");

        String id = component.getString("name");
        String title = component.getString("title");
        String description = component.getString("description");
        String type = getStepType(component);

        Map<String, Map<String, String>> propertiesParsed = properties
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        property -> {
                            var hashMap = new HashMap<String, String>();
                            var jsonObject = property.getValue().asJsonObject();

                            jsonObject.forEach((key, value) ->
                                    hashMap.put(key,
                                            value.toString()
                                                    .replaceAll("\"", "")));

                            return hashMap;
                        }
                ));

        List<String> requiredProperties = propertiesParsed
                .entrySet().stream().filter(
                    property -> Boolean.parseBoolean(
                            property.getValue().getOrDefault(
                                    "required", "false"))
        ).map(
                Map.Entry::getKey
        ).toList();


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
                            .getOrDefault(parameterData.get("type"),
                                    this::getObjectParameter)
                            .apply(parameter);

                    if ("path".equals(parameterData.get("kind"))) {
                        parsedParameter.setPath(true);
                    }

                    return parsedParameter;
                }
        ).toList();

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
                parameterData.get("displayName"),
                parameterData.get("description"),
                parameterData.getOrDefault("defaultValue", null),
                null
        );
    }

    private NumberParameter getNumberParameter(
            final Map.Entry<String, Map<String, String>> parameter) {
        //IntegerParameter doesn't fit some long types there are present
        String id = parameter.getKey();
        Map<String, String> parameterData = parameter.getValue();
        Long value;

        if (parameterData.containsKey("defaultValue")) {
            value = Long.parseLong(parameterData
                    .get("defaultValue")
                    .replaceAll("L", ""));
        } else {
            value = null;
        }

        return new NumberParameter(
                id,
                parameterData.get("displayName"),
                parameterData.get("description"),
                value
        );
    }

    private ObjectParameter getObjectParameter(
            final Map.Entry<String, Map<String, String>> parameter) {
        String id = parameter.getKey();
        Map<String, String> parameterData = parameter.getValue();

        return new ObjectParameter(
                id,
                parameterData.get("displayName"),
                parameterData.get("description"),
                parameterData.getOrDefault("defaultValue", null)
        );
    }

    private BooleanParameter getBooleanParameter(
            final Map.Entry<String, Map<String, String>> parameter) {
        String id = parameter.getKey();
        Map<String, String> parameterData = parameter.getValue();

        return new BooleanParameter(
                id,
                parameterData.get("displayName"),
                parameterData.get("description"),
                Boolean.parseBoolean(parameterData
                        .getOrDefault("defaultValue", "false"))
        );
    }

    private String getStepType(final JsonObject component) {
        final boolean isCamelComponentSourceOnly
                = component.getBoolean("producerOnly");
        final boolean isCamelComponentSinkOnly
                = component.getBoolean("consumerOnly");
        final boolean canCamelComponentBeSourceAndSink
                = !isCamelComponentSourceOnly && !isCamelComponentSinkOnly;

        if (isCamelComponentSourceOnly) {
            return SOURCE_TYPE;
        } else if (isCamelComponentSinkOnly) {
            return SINK_TYPE;
        } else if (canCamelComponentBeSourceAndSink) {
            return ACTION_TYPE;
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
                    SOURCE_TYPE, List.of(SOURCE_TYPE, ACTION_TYPE),
                    ACTION_TYPE, List.of(SOURCE_TYPE, ACTION_TYPE, SINK_TYPE),
                    SINK_TYPE, List.of(ACTION_TYPE, SINK_TYPE)
                );

        return typesToDuplicateTo.get(step.getType()).stream()
                .map(type -> duplicateStepToType(step, type))
                .toList();
    }

    private Step duplicateStepToType(final Step step, final String type) {
        Step duplicatedStep = new Step();
        String typedId = step.getId() + "-" + type;

        duplicatedStep.setId(typedId);
        duplicatedStep.setName(typedId);
        duplicatedStep.setKind(step.getKind());
        duplicatedStep.setTitle(step.getTitle());
        duplicatedStep.setDescription(step.getDescription());
        duplicatedStep.setGroup(step.getGroup());
        duplicatedStep.setParameters(step.getParameters());
        duplicatedStep.setRequired(step.getRequired());
        duplicatedStep.setType(type);

        return duplicatedStep;
    }


    // Putting it at the end because is just a big string being
    // used as placeholder
    private static final String DEFAULT_ICON_STRING =
            "data:image/svg+xml;base64"
            + ",PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4N"
            + "TktMSI/Pg0KPCEtLSBHZW5lcmF0b3I6IEFkb2JlIElsbHVzdHJ"
            + "hdG9yIDE2LjAuMCwgU1ZHIEV4cG9ydCBQbHVnLUluIC4gU1ZHIF"
            + "ZlcnNpb246IDYuMDAgQnVpbGQgMCkgIC0tPg0KPCFET0NUWVBF"
            + "IHN2ZyBQVUJMSUMgIi0vL1czQy8vRFREIFNWRyAxLjEvL0VOIi"
            + "AiaHR0cDovL3d3dy53My5vcmcvR3JhcGhpY3MvU1ZHLzEuMS9E"
            + "VEQvc3ZnMTEuZHRkIj4NCjxzdmcgdmVyc2lvbj0iMS4xIiBpZD"
            + "0iQ2FwYV8xIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAw"
            + "MC9zdmciIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy"
            + "8xOTk5L3hsaW5rIiB4PSIwcHgiIHk9IjBweCINCgkgd2lkdGg9"
            + "IjUxMnB4IiBoZWlnaHQ9IjUxMnB4IiB2aWV3Qm94PSIwIDAgNT"
            + "EyIDUxMiIgc3R5bGU9ImVuYWJsZS1iYWNrZ3JvdW5kOm5ldyAw"
            + "IDAgNTEyIDUxMjsiIHhtbDpzcGFjZT0icHJlc2VydmUiPg0KPG"
            + "c+DQoJPHBhdGggZD0iTTQ0OCwwSDY0QzQ2LjMyOCwwLDMyLDE0"
            + "LjMxMywzMiwzMnY0NDhjMCwxNy42ODgsMTQuMzI4LDMyLDMyLD"
            + "MyaDM4NGMxNy42ODgsMCwzMi0xNC4zMTIsMzItMzJWMzINCgkJ"
            + "QzQ4MCwxNC4zMTMsNDY1LjY4OCwwLDQ0OCwweiBNNjQsNDgwVj"
            + "EyOGg4MHY2NEg5NnYxNmg0OHY0OEg5NnYxNmg0OHY0OEg5NnYx"
            + "Nmg0OHY0OEg5NnYxNmg0OHY4MEg2NHogTTQ0OCw0ODBIMTYwdi"
            + "04MGgyNTZ2LTE2DQoJCUgxNjB2LTQ4aDI1NnYtMTZIMTYwdi00"
            + "OGgyNTZ2LTE2SDE2MHYtNDhoMjU2di0xNkgxNjB2LTY0aDI4OF"
            + "Y0ODB6Ii8+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0K"
            + "PGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQ"
            + "o8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+"
            + "DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz"
            + "4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8L3N2Zz4NCg==";
}
