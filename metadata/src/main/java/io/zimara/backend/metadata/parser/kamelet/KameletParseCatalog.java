package io.zimara.backend.metadata.parser.kamelet;

import io.quarkus.arc.log.LoggerName;
import io.vertx.core.Future;
import io.zimara.backend.metadata.ParseCatalog;
import io.zimara.backend.model.Step;
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
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KameletParseCatalog implements ParseCatalog {

    @LoggerName("KameletParseCatalog")
    private Logger log = Logger.getLogger(KameletParseCatalog.class);

    private final Yaml yaml = new Yaml(new Constructor(KameletStep.class));
    private final Future<List<Step>> steps;

    public KameletParseCatalog(String url, String tag) {
        steps = Future.future(listPromise -> {
            listPromise.complete(cloneRepoAndParse(url, tag));
        });
    }

    private List<Step> cloneRepoAndParse(String url, String tag) {
        List<Step> stepList = Collections.synchronizedList(new ArrayList<>());
        try {
            //clone git repository
            File file = Files.createTempDirectory("kamelet-catalog").toFile();
            file.setReadable(true, true);
            file.setWritable(true, true);
            file.setExecutable(false);
            file.deleteOnExit();

            Git.cloneRepository()
                    .setCloneSubmodules(true)
                    .setURI(url)
                    .setDirectory(file)
                    .setBranch(tag)
                    .setTagOption(TagOpt.FETCH_TAGS)
                    .call();

            //parse all the files
            for (File f : file.listFiles()) {
                this.parseKamelet(f, stepList);
            }
            //cleanup resources
            file.delete();
        } catch (GitAPIException e) {
            log.error(e, e);
        } catch (IOException e) {
            log.error(e, e);
        }
        return stepList;
    }

    @Override
    public Future<List<Step>> parse() {
        return steps;
    }

    private void parseKamelet(File file, List<Step> steps) {
        if (isYAML(file)) {
            Future<KameletStep> step = Future.future(kameletStepPromise -> {
                try {
                    kameletStepPromise.complete(yaml.load(new FileReader(file)));
                } catch (FileNotFoundException e) {
                    log.error(e, e);
                }
            });
            steps.add(step.result());
        } else {
            log.debug("Skipping " + file.getAbsolutePath());
        }
    }

    private boolean isYAML(File file) {
        return (file.getName().endsWith(".yml") || file.getName().endsWith(".yaml")) && !file.getName().startsWith(".");
    }

}
