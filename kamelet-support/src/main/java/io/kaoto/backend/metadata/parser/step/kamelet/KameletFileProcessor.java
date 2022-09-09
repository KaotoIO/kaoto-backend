package io.kaoto.backend.metadata.parser.step.kamelet;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.kaoto.backend.api.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.metadata.parser.YamlProcessFile;
import io.kaoto.backend.model.deployment.kamelet.Kamelet;
import io.kaoto.backend.model.deployment.kamelet.KameletDefinitionProperty;
import io.kaoto.backend.metadata.parser.ProcessFile;
import io.kaoto.backend.model.parameter.ArrayParameter;
import io.kaoto.backend.model.parameter.BooleanParameter;
import io.kaoto.backend.model.parameter.IntegerParameter;
import io.kaoto.backend.model.parameter.NumberParameter;
import io.kaoto.backend.model.parameter.ObjectParameter;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.parameter.StringParameter;
import io.kaoto.backend.model.step.Step;
import org.jboss.logging.Logger;
import org.yaml.snakeyaml.error.YAMLException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KameletFileProcessor extends YamlProcessFile<Step> {

    private final Logger log = Logger.getLogger(KameletFileProcessor.class);

    public KameletFileProcessor(final KameletStepParserService service) {
        this.service = service;
    }
    private KameletStepParserService service;

    @Override
    public Step parseFile(final File f) {
        String kind = null;
        try {
            final var yaml = Files.readString(f.toPath());
            if (!service.appliesTo(yaml)) {
                return null;
            }
            kind = getKind(yaml);
        } catch (IOException e) {
            log.trace("Skipping file as I can't read it: " + f.getName());
        }

        try (FileReader fr = new FileReader(f)) {
            ObjectMapper yamlMapper =
                new ObjectMapper(new YAMLFactory())
                        .configure(
                            DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                            false);
            Kamelet kamelet = yamlMapper.readValue(fr, Kamelet.class);

            Step step = new Step();
            step.setKind(kind);

            if (kamelet.getMetadata() != null) {
                final var metadata = kamelet.getMetadata();
                step.setId(metadata.getName());
                step.setName(metadata.getName());

                if (metadata.getLabels() != null) {
                    switch (metadata.getLabels()
                            .getOrDefault("camel.apache.org/kamelet.type",
                                    "action")
                            .toLowerCase()) {
                        case "source" -> step.setType("START");
                        case "sink" -> step.setType("END");
                        default -> step.setType("MIDDLE");
                    }
                }

                if (metadata.getAnnotations() != null) {
                    final var annotations = metadata.getAnnotations();
                    step.setGroup(annotations
                            .getOrDefault("camel.apache.org/kamelet.group",
                                    "others"));

                    step.setIcon(
                            annotations.getOrDefault(
                                    "camel.apache.org/kamelet.icon", ""));

                    if (annotations.containsKey("kaoto.io/minbranches")) {
                        step.setMinBranches(
                                Integer.valueOf(
                                        annotations
                                                .get("kaoto.io/minbranches")));
                    }

                    if (annotations.containsKey("kaoto.io/maxbranches")) {
                        step.setMaxBranches(
                                Integer.valueOf(
                                        annotations
                                                .get("kaoto.io/maxbranches")));
                    }
                }
            }

            if (kamelet.getSpec() != null
                    && kamelet.getSpec().getDefinition() != null) {
                step.setTitle(kamelet.getSpec()
                        .getDefinition().getTitle());
                step.setDescription(kamelet.getSpec()
                        .getDefinition().getDescription());


                if (kamelet.getSpec().getDefinition().getProperties() != null) {
                    parseParameters(step,
                            kamelet.getSpec().getDefinition().getProperties(),
                            kamelet.getSpec().getDefinition().getRequired());
                }

            }
            if (step.getId() == null) {
                step = null;
            }
            return step;
        } catch (IOException | YAMLException e) {
            log.error("Error parsing '" + f.getAbsolutePath() + "'", e);
        }

        return null;
    }

    private String getKind(final String yaml) {
        Pattern pattern = Pattern.compile(
                "(\nkind:)(.+)\n", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(yaml);
        if (matcher.find()) {
            return matcher.group(2).trim();
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
                    (value != null ? Double.valueOf(value) : null));
            case "integer" -> new IntegerParameter(title, title, description,
                    (value != null ? Integer.valueOf(value) : null));
            case "string" -> new StringParameter(title, title, description,
                    value, property.getFormat());
            case "boolean" -> new BooleanParameter(title, title, description,
                    (value != null ? Boolean.valueOf(value) : null));
            case "array" -> new ArrayParameter(title, title, description,
                    (value != null ? value.split(",") : null));
            default -> new ObjectParameter(title, title, description,
                    value);
        };
    }
}
