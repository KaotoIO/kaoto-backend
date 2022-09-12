package io.kaoto.backend.metadata.parser.step.camelRoute;

import io.kaoto.backend.metadata.parser.JsonProcessFile;
import io.kaoto.backend.model.parameter.*;
import io.kaoto.backend.model.step.Step;
import org.jboss.logging.Logger;

import javax.json.Json;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
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
    ) {}
    private final Logger log = Logger.getLogger(CamelRouteFileProcessor.class);

    @Override
    protected Step parseFile(File f) {
        try (FileReader fr = new FileReader(f)) {
            JsonReader reader = Json.createReader(fr);
            JsonObject json = reader.readObject();
            Step step = convertToStep(json);
            return step;
        } catch (IOException | JsonException e) {
            log.error("Error parsing '" + f.getAbsolutePath() + "'", e);
        }

        return null;
    }

    private Step convertToStep(JsonObject json) {
        Step step = new Step();

        JsonCamelObject parsedCamelJson = getDataFromJson(json);

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

    private JsonCamelObject getDataFromJson(JsonObject json) {
        final String CAMEL_KIND = "Camel-Connector";
        final String DEFAULT_GROUP = "Camel-Component";
        final String DEFAULT_ICON = "data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI" +
            "/Pg0KPCEtLSBHZW5lcmF0b3I6IEFkb2JlIElsbHVzdHJhdG9yIDE2LjAuMCwgU1ZHIEV4cG9ydCBQbHVnLUluIC4gU1ZHIFZlcnNpb2" +
            "46IDYuMDAgQnVpbGQgMCkgIC0tPg0KPCFET0NUWVBFIHN2ZyBQVUJMSUMgIi0vL1czQy8vRFREIFNWRyAxLjEvL0VOIiAiaHR0cDov" +
            "L3d3dy53My5vcmcvR3JhcGhpY3MvU1ZHLzEuMS9EVEQvc3ZnMTEuZHRkIj4NCjxzdmcgdmVyc2lvbj0iMS4xIiBpZD0iQ2FwYV8xIiB" +
            "4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIi" +
            "B4PSIwcHgiIHk9IjBweCINCgkgd2lkdGg9IjUxMnB4IiBoZWlnaHQ9IjUxMnB4IiB2aWV3Qm94PSIwIDAgNTEyIDUxMiIgc3R5bGU9I" +
            "mVuYWJsZS1iYWNrZ3JvdW5kOm5ldyAwIDAgNTEyIDUxMjsiIHhtbDpzcGFjZT0icHJlc2VydmUiPg0KPGc+DQoJPHBhdGggZD0iTTQ0" +
            "OCwwSDY0QzQ2LjMyOCwwLDMyLDE0LjMxMywzMiwzMnY0NDhjMCwxNy42ODgsMTQuMzI4LDMyLDMyLDMyaDM4NGMxNy42ODgsMCwzMi0" +
            "xNC4zMTIsMzItMzJWMzINCgkJQzQ4MCwxNC4zMTMsNDY1LjY4OCwwLDQ0OCwweiBNNjQsNDgwVjEyOGg4MHY2NEg5NnYxNmg0OHY0O" +
            "Eg5NnYxNmg0OHY0OEg5NnYxNmg0OHY0OEg5NnYxNmg0OHY4MEg2NHogTTQ0OCw0ODBIMTYwdi04MGgyNTZ2LTE2DQoJCUgxNjB2LTQ4" +
            "aDI1NnYtMTZIMTYwdi00OGgyNTZ2LTE2SDE2MHYtNDhoMjU2di0xNkgxNjB2LTY0aDI4OFY0ODB6Ii8+DQo8L2c+DQo8Zz4NCjwvZz4" +
            "NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg" +
            "0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+D" +
            "Qo8L3N2Zz4NCg==";

        JsonObject component = json.getJsonObject("component");
        JsonObject properties = json.getJsonObject("properties");

        String title = component.getString("title");
        String description = component.getString("description");
        String type = getStepType(component);

        Map<String, Map<String, String>> propertiesParsed = properties.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        property -> {
                            var hashMap = new HashMap<String, String>();
                            var jsonObject = property.getValue().asJsonObject();

                            jsonObject.forEach((key, value) -> hashMap.put(key, value.toString()));

                            return hashMap;
                        }
                ));

        List<String> requiredProperties = propertiesParsed.entrySet().stream().filter(
                property -> Boolean.parseBoolean(property.getValue().getOrDefault("required", "false"))
        ).map(
                Map.Entry::getKey
        ).toList();


        List<Parameter> parameters = propertiesParsed.entrySet().stream().map(
                parameter -> {
                    Map<String, String> parameterData = parameter.getValue();

                    Map<String, Function<Map.Entry<String, Map<String,String>>, Parameter>> typeToClassConversion = Map.of(
                            "string", this::getStringParameter,
                            "object", this::getObjectParameter,
                            "integer", this::getNumberParameter,
                            "boolean", this::getBooleanParameter
                    );

                    Parameter parsedParameter = typeToClassConversion.get(parameterData.get("type")).apply(parameter);

                    return parsedParameter;
                }
        ).toList();

        return new JsonCamelObject(
                CAMEL_KIND,
                DEFAULT_ICON,
                title,
                description,
                DEFAULT_GROUP,
                type,
                parameters,
                requiredProperties
        );
    }

    private StringParameter getStringParameter(Map.Entry<String, Map<String,String>> parameter) {
        String id = parameter.getKey();
        Map<String, String> parameterData = parameter.getValue();

        StringParameter parsedParameter = new StringParameter(
                id,
                parameterData.get("displayName"), //I can put Id here too like in Yaml
                parameterData.get("description"),
                parameterData.getOrDefault("defaultValue", null),
                null
        );

        return parsedParameter;
    }

    private NumberParameter getNumberParameter(Map.Entry<String, Map<String,String>> parameter) {
        //IntegerParameter doesn't fit some long types there are present
        String id = parameter.getKey();
        Map<String, String> parameterData = parameter.getValue();

        NumberParameter parsedParameter = new NumberParameter(
                id,
                parameterData.get("displayName"),
                parameterData.get("description"),
                Long.parseLong(parameterData.getOrDefault("defaultValue", "0"))
        );

        return parsedParameter;
    }

    private ObjectParameter getObjectParameter(Map.Entry<String, Map<String,String>> parameter) {
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

    private BooleanParameter getBooleanParameter(Map.Entry<String, Map<String,String>> parameter) {
        String id = parameter.getKey();
        Map<String, String> parameterData = parameter.getValue();

        BooleanParameter parsedParameter = new BooleanParameter(
                id,
                parameterData.get("displayName"),
                parameterData.get("description"),
                Boolean.parseBoolean(parameterData.getOrDefault("defaultValue", "false"))
        );

        return parsedParameter;
    }

    private String getStepType(JsonObject component) {
        final String SOURCE = "source";
        final String SINK = "sink";
        final String ACTION = "action";

        final boolean canBeSource = component.getBoolean("producerOnly");
        final boolean canBeSink = component.getBoolean("consumerOnly");

        //If both are true or false we assume it to be an action.
        //Or else, it has to be one of source or sink depending
        //Take care that this later has to be duplicated because
        //of the differences in how Camel components and kaoto components are
        return canBeSource == canBeSink ? ACTION
                : canBeSource ? SOURCE
                : SINK;
    }
}
