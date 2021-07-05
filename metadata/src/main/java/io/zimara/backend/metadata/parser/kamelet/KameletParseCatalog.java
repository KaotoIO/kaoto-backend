package io.zimara.backend.metadata.parser.kamelet;

import io.zimara.backend.metadata.ParseCatalog;
import io.zimara.backend.model.Parameter;
import io.zimara.backend.model.Step;
import io.zimara.backend.model.parameter.BooleanParameter;
import io.zimara.backend.model.parameter.IntegerParameter;
import io.zimara.backend.model.parameter.TextParameter;
import io.zimara.backend.model.step.kamelet.KameletStep;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.TagOpt;
import org.jboss.logging.Logger;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class KameletParseCatalog implements ParseCatalog {

    private Logger log = Logger.getLogger(KameletParseCatalog.class);

    private final Yaml yaml = new Yaml(new Constructor(KameletStep.class));
    private final CompletableFuture<List<Step>> steps = new CompletableFuture<>();

    public KameletParseCatalog(String url, String tag) {
        log.trace("Warming up kamelet catalog in " + url);
        steps.completeAsync(() -> cloneRepoAndParse(url, tag));
    }

    private List<Step> cloneRepoAndParse(String url, String tag) {
        List<Step> stepList = Collections.synchronizedList(new ArrayList<>());
        File file = null;
        List<CompletableFuture<Step>> futureSteps = new ArrayList<>();
        try {
            log.trace("Creating temporary folder.");
            file = Files.createTempDirectory("kamelet-catalog").toFile();
            file.setReadable(true, true);
            file.setWritable(true, true);

            log.trace("Cloning git repository.");
            Git.cloneRepository()
                    .setCloneSubmodules(true)
                    .setURI(url)
                    .setDirectory(file)
                    .setBranch(tag)
                    .setTagOption(TagOpt.FETCH_TAGS)
                    .call();

            log.trace("Parsing all kamelet files in the folder.");
            for (File f : file.listFiles()) {
                if (isYAML(f)) {
                    CompletableFuture<Step> step =
                            CompletableFuture.supplyAsync(() -> parseFile(f))
                                    .thenApply(this::extractSpec)
                                    .thenApply(this::extractMetadata);
                    step.thenAccept(s -> stepList.add(s));
                    futureSteps.add(step);
                }
            }
        } catch (GitAPIException e) {
            log.error(e, e);
        } catch (IOException e) {
            log.error(e, e);
        } finally {
            log.trace("Cleaning up resources.");
            if (file != null) {
                file.delete();
            }
        }

        CompletableFuture.allOf(futureSteps.toArray(new CompletableFuture[0])).join();
        return stepList;

    }

    private KameletStep parseFile(File f) {
        try {
            return yaml.load(new FileReader(f));
        } catch (FileNotFoundException e) {
            log.error(e, e);
        }

        return null;
    }

    private KameletStep extractMetadata(KameletStep step) {
        if (step.getMetadata().containsKey("name")) {
            step.setId(step.getMetadata().get("name").toString());
            step.setName(step.getMetadata().get("name").toString());
        }

        if (step.getMetadata().containsKey("labels")) {
            Map<String, Object> labels = (Map<String, Object>) step.getMetadata().get("labels");
            if (labels.containsKey("camel.apache.org/kamelet.type")) {
                step.setKameletType(labels.get("camel.apache.org/kamelet.type").toString());
            }
        }

        if (step.getMetadata().containsKey("annotations")) {
            Map<String, Object> labels = (Map<String, Object>) step.getMetadata().get("annotations");
            if (labels.containsKey("camel.apache.org/kamelet.group")) {
                step.setGroup(labels.get("camel.apache.org/kamelet.group").toString());
            }
            if (labels.containsKey("camel.apache.org/kamelet.icon")) {
                step.setIcon(labels.get("camel.apache.org/kamelet.icon").toString());
            }
        }

        step.setMetadata(null);

        return step;
    }

    private KameletStep extractSpec(KameletStep step) {
        if (step.getSpec().containsKey("definition")) {
            Map<String, Object> definition = (Map<String, Object>) step.getSpec().get("definition");
            if (definition.containsKey("title")) {
                step.setTitle(definition.get("title").toString());
            }

            if (definition.containsKey("description")) {
                step.setDescription(definition.get("description").toString());
            }

            if (definition.containsKey("properties")) {
                Map<String, Object> properties = (Map<String, Object>) definition.get("properties");
                step.setParameters(new ArrayList<>());

                for (Map.Entry<String, Object> property : properties.entrySet()) {
                    Map<String, Object> definitions = (Map<String, Object>) property.getValue();
                    Parameter p;
                    final var title = definitions.get("title").toString();
                    var description = definitions.getOrDefault("description", title).toString();
                    String value = null;
                    if (definitions.containsKey("default")) {
                        value = definitions.get("default").toString();
                    }

                    switch (definitions.get("type").toString()) {
                        case "integer":
                            p = new IntegerParameter(title, (value != null ? Integer.valueOf(value) : null), description);
                            break;
                        case "boolean":
                            p = new BooleanParameter(title, (value != null ? Boolean.valueOf(value) : null), description);
                            break;
                        default:
                            p = new TextParameter(title, value, description);
                    }
                    step.getParameters().add(p);
                }
            }
        }
        step.setSpec(null);

        return step;
    }

    @Override
    public CompletableFuture<List<Step>> parse() {
        return steps;
    }

    private boolean isYAML(File file) {
        return (file.getName().endsWith(".yml") || file.getName().endsWith(".yaml")) && !file.getName().startsWith(".");
    }

}
