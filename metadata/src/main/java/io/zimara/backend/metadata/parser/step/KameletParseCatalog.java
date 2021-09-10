package io.zimara.backend.metadata.parser.step;

import io.zimara.backend.metadata.parser.GitParseCatalog;
import io.zimara.backend.metadata.parser.YamlProcessFile;
import io.zimara.backend.model.parameter.Parameter;
import io.zimara.backend.model.step.Step;
import io.zimara.backend.model.step.kamelet.KameletStep;
import org.jboss.logging.Logger;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.error.YAMLException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * 🐱class KameletParseCatalog
 * 🐱inherits GitParseCatalog
 * Reads and parses a kamelet catalog.
 * Extracts all the kamelet definitions it can find
 * and generate a kamelet step for each one.
 */
public class KameletParseCatalog extends GitParseCatalog<Step> {

    public KameletParseCatalog(final String url, final String tag) {
        super(url, tag);
    }

    @Override
    protected KameletFileProcessor getFileVisitor(final List<Step> mdList,
                   final List<CompletableFuture<Void>> futureMetadata) {
        return new KameletFileProcessor(mdList, futureMetadata);
    }
}

class KameletFileProcessor extends YamlProcessFile<Step> {

    public static final String PROPERTIES = "properties";
    private Logger log = Logger.getLogger(KameletFileProcessor.class);

    KameletFileProcessor(final List<Step> stepList,
                         final List<CompletableFuture<Void>> futureSteps) {
        super(stepList, futureSteps);
    }

    @Override
    public KameletStep parseFile(final File f) {
        try (FileReader fr = new FileReader(f)) {
            Yaml yaml = new Yaml(new Constructor(KameletStep.class));
            KameletStep step = yaml.load(fr);
            extractSpec(step);
            extractMetadata(step);
            return step;
        } catch (IOException | YAMLException e) {
            log.error("Error parsing '" + f.getAbsolutePath() + "'", e);
        }

        return null;
    }

    private KameletStep extractMetadata(final KameletStep step) {
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
                    step.setKameletType(labelsMap.get(type).toString());
                    switch (step.getKameletType().toLowerCase()) {
                        case "source":
                            step.setType("START");
                            break;
                        case "sink":
                            step.setType("END");
                            break;
                        default:
                            step.setType("MIDDLE");
                            break;

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

    private KameletStep extractSpec(final Step s) {
        KameletStep step = (KameletStep) s;
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

    private void parseParameters(final KameletStep step,
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
