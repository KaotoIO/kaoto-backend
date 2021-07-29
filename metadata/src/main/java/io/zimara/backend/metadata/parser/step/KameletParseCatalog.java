package io.zimara.backend.metadata.parser.step;

import io.zimara.backend.metadata.parser.GithubParseCatalog;
import io.zimara.backend.metadata.parser.YamlProcessFile;
import io.zimara.backend.model.Parameter;
import io.zimara.backend.model.parameter.AbstractParameter;
import io.zimara.backend.model.step.Step;
import io.zimara.backend.model.step.kamelet.KameletStep;
import org.jboss.logging.Logger;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.error.YAMLException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class KameletParseCatalog extends GithubParseCatalog<Step> {

    public KameletParseCatalog(String url, String tag) {
        super(url, tag);
    }

    @Override
    protected KameletFileProcessor getFileVisitor(List<Step> metadataList, List<CompletableFuture<Step>> futureMetadata) {
        return new KameletFileProcessor(metadataList, futureMetadata);
    }
}

class KameletFileProcessor extends YamlProcessFile<Step> {

    public static final String PROPERTIES = "properties";
    Logger log = Logger.getLogger(KameletFileProcessor.class);

    KameletFileProcessor(List<Step> stepList, List<CompletableFuture<Step>> futureSteps) {
        super(stepList, futureSteps);
    }

    @Override
    public FileVisitResult visitFile(final Path file, BasicFileAttributes attrs) {

        File f = file.toFile();

        if (isYAML(f)) {
            CompletableFuture<Step> step =
                    CompletableFuture.supplyAsync(() -> parseFile(f));

            step.thenRun(() -> log.trace(f.getName() + " parsed, now generating step."));
            step
                    .thenApply(this::extractSpec)
                    .thenApply(this::extractMetadata)
                    .thenAccept(metadataList::add);
            futureMetadata.add(step);
        }

        return FileVisitResult.CONTINUE;
    }

    @Override
    public KameletStep parseFile(File f) {
        try (FileReader fr = new FileReader(f)) {
            Yaml yaml = new Yaml(new Constructor(KameletStep.class));
            return yaml.load(fr);
        } catch (IOException | YAMLException e) {
            log.error("Error parsing '" + f.getAbsolutePath() + "'", e);
        }

        return null;
    }

    private KameletStep extractMetadata(KameletStep step) {
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
                Map<String, Object> labelsMap = (Map<String, Object>) step.getMetadata().get(labels);
                final var type = "camel.apache.org/kamelet.type";
                if (labelsMap.containsKey(type)) {
                    step.setKameletType(labelsMap.get(type).toString());
                }
            }

            final var annotations = "annotations";
            if (step.getMetadata().containsKey(annotations)) {
                Map<String, Object> annotationsMap = (Map<String, Object>) step.getMetadata().get(annotations);

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

    private KameletStep extractSpec(Step s) {
        KameletStep step = (KameletStep) s;
        if (step == null || step.getSpec() == null) {
            return step;
        }

        try {
            final var definitionLabel = "definition";
            if (step.getSpec().containsKey(definitionLabel)) {
                Map<String, Object> definition = (Map<String, Object>) step.getSpec().get(definitionLabel);
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

    private void parseParameters(KameletStep step, Map<String, Object> definition) {
        Map<String, Object> properties = (Map<String, Object>) definition.get(PROPERTIES);
        step.setParameters(new ArrayList<>());

        for (Map.Entry<String, Object> property : properties.entrySet()) {
            Map<String, Object> definitions = (Map<String, Object>) property.getValue();
            Parameter p;
            final var title = definitions.getOrDefault("title", "unknown").toString();
            var description = definitions.getOrDefault("description", title).toString();
            String value = definitions.getOrDefault("default", "").toString();
            p = getParameter(definitions, title, description, value);
            step.getParameters().add(p);
        }
    }

    private Parameter getParameter(Map<String, Object> definitions, String title, String description, String value) {
        return switch (definitions.get("type").toString()) {
            case "integer" -> new AbstractParameter<>(title, title, description, (value != null ? Integer.valueOf(value) : null), definitions.get("type").toString());
            case "boolean" -> new AbstractParameter<>(title, title, description, (value != null ? Boolean.valueOf(value) : null), definitions.get("type").toString());
            default -> new AbstractParameter<>(title, title, description, value, definitions.get("type").toString());
        };
    }
}
