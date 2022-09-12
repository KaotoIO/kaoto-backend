package io.kaoto.backend.metadata.parser.step.camelRoute;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CamelRouteFileProcessor extends JsonProcessFile<Step> {

    record JsonCamelObject(
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
    protected Step parseFile(final File f) {
        try (FileReader fr = new FileReader(f)) {
            JsonReader reader = Json.createReader(fr);
            JsonObject json = reader.readObject();
            Step step = convertToStep(json);
            return step;
        } catch (IOException | JsonException e) {
            log.error("Error parsing '"  + f.getAbsolutePath() + "'", e);
        }

        return null;
    }

    private Step convertToStep(final JsonObject json) {
        JsonCamelObject parsedCamelJson = getDataFromJson(json);

        Step step = new Step();

        step.setKind(parsedCamelJson.kind());
        step.setTitle(parsedCamelJson.title());
        step.setDescription(parsedCamelJson.description());
        step.setGroup(parsedCamelJson.group());
        step.setParameters(parsedCamelJson.parameters());
        step.setRequired(parsedCamelJson.required());
        step.setType(parsedCamelJson.type());

        //Duplicate component with action if kind Sink or Source

        return step;
    }

    private JsonCamelObject getDataFromJson(final JsonObject json) {
        final String camelKind = "Camel-Connector";
        final String defaultGroup = "Camel-Component";
        final String defaultIcon = defaultIconString;

        JsonObject component = json.getJsonObject("component");
        JsonObject properties = json.getJsonObject("properties");

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
                                    hashMap.put(key, value.toString().replaceAll("\"", "")));

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
                            .get(parameterData.get("type")).apply(parameter);

                    if ("path".equals(parameterData.get("kind"))) {
                        parsedParameter.setPath(true);
                    }

                    return parsedParameter;
                }
        ).toList();

        return new JsonCamelObject(
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

        StringParameter parsedParameter = new StringParameter(
                id,
                parameterData.get("displayName"),
                parameterData.get("description"),
                parameterData.getOrDefault("defaultValue", null),
                null
        );

        return parsedParameter;
    }

    private NumberParameter getNumberParameter(
            final Map.Entry<String, Map<String, String>> parameter) {
        //IntegerParameter doesn't fit some long types there are present
        String id = parameter.getKey();
        Map<String, String> parameterData = parameter.getValue();

        NumberParameter parsedParameter = new NumberParameter(
                id,
                parameterData.get("displayName"),
                parameterData.get("description"),
                Long.parseLong(parameterData
                        .getOrDefault("defaultValue", "0"))
        );

        return parsedParameter;
    }

    private ObjectParameter getObjectParameter(
            final Map.Entry<String, Map<String, String>> parameter) {
        String id = parameter.getKey();
        Map<String, String> parameterData = parameter.getValue();

        ObjectParameter parsedParameter = new ObjectParameter(
                id,
                parameterData.get("displayName"),
                parameterData.get("description"),
                parameterData.getOrDefault("defaultValue", null)
        );

        return parsedParameter;
    }

    private BooleanParameter getBooleanParameter(
            final Map.Entry<String, Map<String, String>> parameter) {
        String id = parameter.getKey();
        Map<String, String> parameterData = parameter.getValue();

        BooleanParameter parsedParameter = new BooleanParameter(
                id,
                parameterData.get("displayName"),
                parameterData.get("description"),
                Boolean.parseBoolean(parameterData
                        .getOrDefault("defaultValue", "false"))
        );

        return parsedParameter;
    }

    private String getStepType(final JsonObject component) {
        final String sourceType = "source";
        final String sinkType = "sink";
        final String actionType = "action";

        final boolean canBeSource = component.getBoolean("producerOnly");
        final boolean canBeSink = component.getBoolean("consumerOnly");

        //If both are true or false we assume it to be an action.
        //Or else, it has to be one of source or sink depending
        //Take care that this later has to be duplicated because
        //of the differences in how Camel components and kaoto components are
        return canBeSource == canBeSink ? actionType
                : canBeSource ? sourceType
                : sinkType;
    }

    // Putting it at the end because is just a big string being
    // used as placeholder
    private final String defaultIconString = "data:image/svg+xml;base64"
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
