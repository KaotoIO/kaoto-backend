package io.kaoto.backend.camel.metadata.parser.step.camelroute;

import io.kaoto.backend.camel.KamelHelper;
import io.kaoto.backend.metadata.parser.JsonProcessFile;
import io.kaoto.backend.model.parameter.BooleanParameter;
import io.kaoto.backend.model.parameter.NumberParameter;
import io.kaoto.backend.model.parameter.ObjectParameter;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.parameter.StringParameter;
import io.kaoto.backend.model.step.Step;
import jakarta.json.Json;
import jakarta.json.JsonException;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import org.jboss.logging.Logger;

import java.io.Reader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class CamelRouteFileProcessor extends JsonProcessFile<Step> {

    public static final String DESCRIPTION = "description";
    public static final String DEFAULT_VALUE = "defaultValue";
    public static final String DISPLAY_NAME = "displayName";
    public static final String COMPONENT = "component";
    public static final String PROPERTIES = "properties";
    private static final String INVALID_TYPE = "invalid";
    private static final Logger log = Logger.getLogger(CamelRouteFileProcessor.class);

    private static final String DEFAULT_ICON_STRING = KamelHelper.loadResourceAsString(
            CamelRouteFileProcessor.class, "default-icon.txt").orElse("");


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
        step.setRequired(parsedCamelJson.required());
        step.setType(parsedCamelJson.type());
        step.setParameters(parsedCamelJson.parameters());
        //Add extra step-id parameter
        final var stepId = new StringParameter();
        step.getParameters().add(stepId);
        stepId.setTitle("Step ID");
        stepId.setId("step-id-kaoto");
        stepId.setDescription("Identifier of this step inside the route.");

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

        JsonObject component = json.getJsonObject(COMPONENT);
        JsonObject properties = json.getJsonObject(PROPERTIES);

        String id = component.getString("name");
        String title = component.getString("title");
        String description = component.getString(DESCRIPTION);
        String type = getStepType(component);

        List<Parameter<?>> parameters = new ArrayList<>();
        final AtomicInteger i = new AtomicInteger(0);
        final var requiredProperties = new ArrayList<String>();

        Map<String, Function<ParameterEntry, Parameter<?>>> typeToClassConversion = Map.of(
                "string", CamelRouteFileProcessor::getStringParameter,
                "duration", CamelRouteFileProcessor::getStringParameter,
                "object", CamelRouteFileProcessor::getObjectParameter,
                "integer", CamelRouteFileProcessor::getNumberParameter,
                "long", CamelRouteFileProcessor::getNumberParameter,
                "numeric", CamelRouteFileProcessor::getNumberParameter,
                "boolean", CamelRouteFileProcessor::getBooleanParameter
        );

        properties.entrySet().stream()
                .forEachOrdered(entrySet -> {
                            final Map<String, String> parameterData = new LinkedHashMap<>();
                            final JsonObject jsonObject = entrySet.getValue().asJsonObject();

                            // Properties of the parameter
                            jsonObject.forEach((key, value) ->
                                    parameterData.put(key, value.toString().replace("\"", "")));

                            ParameterEntry entry = new ParameterEntry(entrySet.getKey(), parameterData);

                            Parameter<?> parsedParameter = typeToClassConversion
                                    .getOrDefault(
                                            parameterData.get("type"),
                                            CamelRouteFileProcessor::getObjectParameter)
                                    .apply(entry);

                            if ("path".equals(parameterData.get("kind"))) {
                                parsedParameter.setPath(true);
                                //Assign path order to parameters
                                //We will have to assume the component defined them in order in the JSON because
                                //the JSON does not give this information.
                                parsedParameter.setPathOrder(i.getAndIncrement());
                                if (parsedParameter.getPathOrder() == 0) {
                                    parsedParameter.setPathSeparator("");
                                }
                            }

                            if(Boolean.parseBoolean(parameterData.getOrDefault("required", "false"))) {
                                requiredProperties.add(entrySet.getKey());
                            }

                            parameters.add(parsedParameter);
                        }
                );

        handleWeirdCases(id, parameters);

        return new ParsedCamelComponentFromJson(
                id,
                camelKind,
                DEFAULT_ICON_STRING,
                title,
                description,
                defaultGroup,
                type,
                parameters,
                requiredProperties
        );
    }

    private static void handleWeirdCases(String id, List<Parameter<?>> parameters) {
        //Here are the weird cases
        if ("avro".equalsIgnoreCase(id)) {
            for (Parameter<?> p : parameters) {
                if ("messageName".equalsIgnoreCase(p.getId())) {
                    p.setPathSeparator("/");
                    p.setPathOrder(3);
                } else if ("host".equalsIgnoreCase(p.getId())) {
                    p.setPathOrder(1);
                } else if ("port".equalsIgnoreCase(p.getId())) {
                    p.setPathOrder(2);
                } else if ("transport".equalsIgnoreCase(p.getId())) {
                    p.setPathOrder(0);
                }
            }
        } else if ("sftp".equalsIgnoreCase(id)) {
            for (Parameter<?> p : parameters) {
                if ("host".equalsIgnoreCase(p.getId())) {
                    p.setPathSeparator("//");
                    p.setPathOrder(0);
                } else if ("port".equalsIgnoreCase(p.getId())) {
                    p.setPathOrder(1);
                } else if ("directoryName".equalsIgnoreCase(p.getId())) {
                    p.setPathSeparator("/");
                    p.setPathOrder(2);
                }
            }
        } else if ("kamelet".equalsIgnoreCase(id)) {
            for (Parameter<?> p : parameters) {
                if ("routeId".equalsIgnoreCase(p.getId())) {
                    p.setPathSeparator("/");
                    break;
                }
            }
            //Convert to array when it is supported by the frontend
            ObjectParameter parameter = new ObjectParameter();
            parameter.setId("kaoto-parameters");
            parameter.setTitle("Parameters");
            parameter.setPath(false);
            parameter.setDescription("Parameters of the kamelet.");
            parameter.setNullable(true);
            parameters.add(parameter);
        } else if ("netty-http".equalsIgnoreCase(id)) {
            for (Parameter<?> p : parameters) {
                if ("host".equalsIgnoreCase(p.getId())) {
                    p.setPathSeparator("://");
                } else if ("path".equalsIgnoreCase(p.getId())) {
                    p.setPathSeparator("/");
                }
            }
        }
    }

    private static StringParameter getStringParameter(final ParameterEntry parameter) {
        String id = parameter.key();
        Map<String, String> parameterData = parameter.value();

        return new StringParameter(
                id,
                parameterData.get(DISPLAY_NAME),
                parameterData.get(DESCRIPTION),
                null, null, null,
                parameterData.getOrDefault(DEFAULT_VALUE, null),
                null
        );
    }

    private static NumberParameter getNumberParameter(final ParameterEntry parameter) {
        String id = parameter.key();
        Map<String, String> parameterData = parameter.value();
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
                null, null, null,
                value
        );
    }

    private static ObjectParameter getObjectParameter(final ParameterEntry parameter) {
        String id = parameter.key();
        Map<String, String> parameterData = parameter.value();

        return new ObjectParameter(
                id,
                parameterData.get(DISPLAY_NAME),
                parameterData.get(DESCRIPTION),
                null, null, null,
                parameterData.getOrDefault(DEFAULT_VALUE, null)
        );
    }

    private static BooleanParameter getBooleanParameter(final ParameterEntry parameter) {
        String id = parameter.key();
        Map<String, String> parameterData = parameter.value();

        return new BooleanParameter(
                id,
                parameterData.get(DISPLAY_NAME),
                parameterData.get(DESCRIPTION),
                null, null, null,
                Boolean.parseBoolean(parameterData
                        .getOrDefault(DEFAULT_VALUE, "false"))
        );
    }

    private static String getStepType(final JsonObject component) {
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

    record ParsedCamelComponentFromJson(
            String id,
            String kind,
            String icon,

            String title,
            String description,
            String group,
            String type,
            List<Parameter<?>> parameters,
            List<String> required
    ) {
    }

    record ParameterEntry(
            String key,
            Map<String, String> value
    )
    {}
}
