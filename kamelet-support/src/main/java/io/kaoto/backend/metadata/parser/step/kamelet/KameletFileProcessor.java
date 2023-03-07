package io.kaoto.backend.metadata.parser.step.kamelet;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.kaoto.backend.metadata.parser.YamlProcessFile;
import io.kaoto.backend.model.deployment.kamelet.KameletDefinitionProperty;
import io.kaoto.backend.model.deployment.kamelet.SimplifiedKamelet;
import io.kaoto.backend.model.parameter.ArrayParameter;
import io.kaoto.backend.model.parameter.BooleanParameter;
import io.kaoto.backend.model.parameter.IntegerParameter;
import io.kaoto.backend.model.parameter.NumberParameter;
import io.kaoto.backend.model.parameter.ObjectParameter;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.parameter.StringParameter;
import io.kaoto.backend.model.step.Step;
import org.apache.commons.io.IOUtils;
import org.jboss.logging.Logger;
import org.yaml.snakeyaml.error.YAMLException;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KameletFileProcessor extends YamlProcessFile<Step> {

    public static final ObjectMapper YAML_MAPPER =
            new ObjectMapper(new YAMLFactory()).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private final Logger log = Logger.getLogger(KameletFileProcessor.class);

    public KameletFileProcessor() {
        //Nothing needed here
    }

    @Override
    public List<Step> parseInputStream(Reader reader) {
        try {
            final var yaml = IOUtils.toString(reader);
            String[] kinds = new String[]{"Kamelet", "Knative", "Camel-Connector", "EIP", "EIP-BRANCH"};
            var kind = getKind(yaml);
            if (Arrays.stream(kinds).noneMatch(k -> k.equalsIgnoreCase(kind))) {
                return List.of();
            }
            SimplifiedKamelet kamelet = YAML_MAPPER.readValue(yaml, SimplifiedKamelet.class);
            Step step = new Step();
            step.setKind(kind);

            if (kamelet.getMetadata() != null) {
                final var metadata = kamelet.getMetadata();
                step.setName(metadata.getName());

                if (metadata.getLabels() != null) {
                    switch (metadata.getLabels()
                            .getOrDefault("camel.apache.org/kamelet.type", "action").toLowerCase()) {
                        case "source" -> step.setType(Step.START);
                        case "sink" -> step.setType(Step.END);
                        default -> step.setType(Step.MIDDLE);
                    }
                }
                step.setId(step.getName() + "-" + step.getType());

                if (metadata.getAnnotations() != null) {
                    final var annotations = metadata.getAnnotations();
                    step.setGroup(annotations.getOrDefault("camel.apache.org/kamelet.group", "others"));

                    step.setIcon(annotations.getOrDefault("camel.apache.org/kamelet.icon", ""));

                    if (annotations.containsKey("kaoto.io/minbranches")) {
                        step.setMinBranches(Integer.valueOf(annotations.get("kaoto.io/minbranches")));
                    }

                    if (annotations.containsKey("kaoto.io/maxbranches")) {
                        step.setMaxBranches(Integer.valueOf(annotations.get("kaoto.io/maxbranches")));
                    }
                }
            }

            if (kamelet.getSpec() != null && kamelet.getSpec().getDefinition() != null) {
                step.setTitle(kamelet.getSpec().getDefinition().getTitle());
                step.setDescription(kamelet.getSpec().getDefinition().getDescription());


                if (kamelet.getSpec().getDefinition().getProperties() != null) {
                    parseParameters(step,
                            kamelet.getSpec().getDefinition().getProperties(),
                            kamelet.getSpec().getDefinition().getRequired());
                }

            }
            if (step.getId() == null) {
                step = null;
            }
            return List.of(step);
        } catch (IOException | YAMLException e) {
            log.trace("Error parsing Kamelet." , e);
        }

        return List.of();
    }

    private String getKind(final String yaml) {
        Pattern pattern = Pattern.compile(
                "[\n|\r]kind:(.+)[\n|\r]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(yaml);
        if (matcher.find()) {
            return matcher.group(1).trim();
        } else {
            return null;
        }
    }

    private void parseParameters(
            final Step step,
            final Map<String, KameletDefinitionProperty> properties,
            final List<String> required) {

        step.setRequired(required);

        step.setParameters(new ArrayList<>());

        for (var property : properties.entrySet()) {
            Parameter p;
            final var title = property.getKey();

            final var prop = property.getValue();
            var description = prop.getDescription();
            String value = prop.getDefault();
            p = getParameter(prop, title, description, value);
            p.setPath(prop.getPath());

            p.setNullable(required == null || required.stream()
                    .noneMatch(r -> r.equalsIgnoreCase(title)));

            step.getParameters().add(p);

        }
    }

    private Parameter getParameter(final KameletDefinitionProperty property,
                                   final String title, final String description,
                                   final String value) {
        final var type = property.getType().toLowerCase();

        return switch (type) {
            //number, integer, string, boolean, array, object, or null
            case "number" -> new NumberParameter(title, title, description,
                    value != null ? Double.valueOf(value) : null);
            case "integer" -> new IntegerParameter(title, title, description,
                    value != null ? Integer.valueOf(value) : null);
            case "string" -> new StringParameter(title, title, description,
                    value, property.getFormat());
            case "boolean" -> new BooleanParameter(title, title, description,
                    value != null ? Boolean.valueOf(value) : null);
            case "array" -> new ArrayParameter(title, title, description,
                    value != null ? value.split(",") : null);
            default -> new ObjectParameter(title, title, description,
                    value);
        };
    }
}
