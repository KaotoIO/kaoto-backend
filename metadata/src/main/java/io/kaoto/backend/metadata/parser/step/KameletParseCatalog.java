package io.kaoto.backend.metadata.parser.step;

import io.kaoto.backend.metadata.ParseCatalog;
import io.kaoto.backend.metadata.parser.GitParseCatalog;
import io.kaoto.backend.metadata.parser.JarParseCatalog;
import io.kaoto.backend.metadata.parser.YamlProcessFile;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;
import org.jboss.logging.Logger;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.representer.Representer;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * 🐱class KameletParseCatalog
 * 🐱relationship dependsOn ParseCatalog
 * Reads and parses a kamelet catalog.
 * Extracts all the kamelet definitions it can find
 * and generate a kamelet step for each one.
 */
public final class KameletParseCatalog {

    private KameletParseCatalog() {

    }

    public static ParseCatalog<Step> getParser(
            final String url, final String tag) {
        ParseCatalog<Step> parseCatalog = new GitParseCatalog<>(url, tag);
        parseCatalog.setFileVisitor(new KameletFileProcessor());
        return parseCatalog;
    }


    public static ParseCatalog<Step> getParser(
            final String url) {
        ParseCatalog<Step> parseCatalog = new JarParseCatalog<>(url);
        parseCatalog.setFileVisitor(new KameletFileProcessor());
        return parseCatalog;
    }
}

class KameletFileProcessor extends YamlProcessFile<Step> {

    public static final String PROPERTIES = "properties";
    private final Logger log = Logger.getLogger(KameletFileProcessor.class);

    @Override
    public Step parseFile(final File f) {
        try (FileReader fr = new FileReader(f)) {
            Representer representer = new Representer();
            representer.getPropertyUtils().setSkipMissingProperties(true);
            Yaml yaml = new Yaml(new Constructor(Step.class), representer);
            Step step = yaml.load(fr);
            step = extractSpec(step);
            step = extractMetadata(step);
            return step;
        } catch (IOException | YAMLException e) {
            log.error("Error parsing '" + f.getAbsolutePath() + "'", e);
        }

        return null;
    }

    private Step extractMetadata(final Step step) {
        if (step == null || step.getMetadata() == null) {
            return step;
        }
        try {
            final var name = "name";
            if (step.getMetadata().containsKey(name)) {
                step.setId(step.getMetadata().get(name).toString());
                step.setName(step.getMetadata().get(name).toString());
            }

            final var labels = "labels";
            if (step.getMetadata().containsKey(labels)) {
                Map<String, Object> labelsMap =
                        (Map<String, Object>) step.getMetadata().get(labels);
                final var type = "camel.apache.org/kamelet.type";
                if (labelsMap.containsKey(type)) {
                    step.setType(labelsMap.get(type).toString());
                    switch (step.getType().toLowerCase()) {
                        case "source" -> step.setType("START");
                        case "sink" -> step.setType("END");
                        default -> step.setType("MIDDLE");
                    }
                }
            }

            final var annotations = "annotations";
            if (step.getMetadata().containsKey(annotations)) {
                Map<String, Object> annotationsMap =
                        (Map<String, Object>) step.getMetadata()
                                .get(annotations);

                final var group = "camel.apache.org/kamelet.group";
                if (annotationsMap.containsKey(group)) {
                    step.setGroup(annotationsMap.get(group).toString());
                }

                final var icon = "camel.apache.org/kamelet.icon";
                if (annotationsMap.containsKey(icon)) {
                    step.setIcon(annotationsMap.get(icon).toString());
                }
            }
        } catch (Exception e) {
            log.error(e, e);
        } finally {
            step.setMetadata(null);
        }

        return step;
    }

    private Step extractSpec(final Step step) {
        if (step == null || step.getSpec() == null) {
            return step;
        }

        try {
            final var definitionLabel = "definition";
            if (step.getSpec().containsKey(definitionLabel)) {
                Map<String, Object> definition =
                        (Map<String, Object>) step.getSpec()
                                .get(definitionLabel);
                final var title = "title";
                if (definition.containsKey(title)) {
                    step.setTitle(definition.get(title).toString());
                }

                final var description = "description";
                if (definition.containsKey(description)) {
                    step.setDescription(definition.get(description).toString());
                }

                if (definition.containsKey(PROPERTIES)) {
                    parseParameters(step, definition);
                }
            }
        } catch (Exception e) {
            log.error(e, e);
        } finally {
            step.setSpec(null);
        }

        return step;
    }

    private void parseParameters(final Step step,
                                 final Map<String, Object> definition) {
        Map<String, Object> properties =
                (Map<String, Object>) definition.get(PROPERTIES);
        step.setParameters(new ArrayList<>());

        for (Map.Entry<String, Object> property : properties.entrySet()) {
            Map<String, Object> definitions =
                    (Map<String, Object>) property.getValue();
            Parameter p;
            final var title = property.getKey();
            var description =
                    definitions.getOrDefault("description", title).toString();
            String value =
                    definitions.getOrDefault("default", "").toString();
            p = getParameter(definitions, title, description, value);
            p.setPath((Boolean) definitions.getOrDefault("path", false));
            step.getParameters().add(p);
        }
    }

    private Parameter getParameter(final Map<String, Object> definitions,
                                   final String title, final String description,
                                   final String value) {
        final var type = definitions.get("type").toString();
        return switch (type) {
            case "integer" -> {
                final var v = value != null ? Integer.valueOf(value) : null;
                yield new Parameter<>(title, title, description, v, type);
            }
            case "boolean" -> {
                final var bool = value != null ? Boolean.valueOf(value) : null;
                yield new Parameter<>(title, title, description, bool, type);
            }
            default -> new Parameter<>(title, title, description, value, type);
        };
    }
}
