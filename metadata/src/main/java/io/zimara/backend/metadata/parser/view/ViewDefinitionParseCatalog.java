package io.zimara.backend.metadata.parser.view;

import io.zimara.backend.metadata.parser.GithubParseCatalog;
import io.zimara.backend.metadata.parser.YamlProcessFile;
import io.zimara.backend.model.view.ViewDefinition;
import org.jboss.logging.Logger;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.error.YAMLException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ViewDefinitionParseCatalog extends GithubParseCatalog<ViewDefinition> {

    public ViewDefinitionParseCatalog(String url, String tag) {
        super(url, tag);
    }

    @Override
    protected YamlProcessFile<ViewDefinition> getFileVisitor(List<ViewDefinition> metadataList, List<CompletableFuture<ViewDefinition>> futureMetadata) {
        return new ViewDefinitionProcessFile(metadataList, futureMetadata);
    }
}

class ViewDefinitionProcessFile extends YamlProcessFile<ViewDefinition> {

    Logger log = Logger.getLogger(ViewDefinitionProcessFile.class);

    public ViewDefinitionProcessFile(List<ViewDefinition> metadataList, List<CompletableFuture<ViewDefinition>> futureMetadata) {
        super(metadataList, futureMetadata);
    }

    @Override
    public ViewDefinition parseFile(File f) {
        try (FileReader fr = new FileReader(f)) {
            Yaml yaml = new Yaml(new Constructor(ViewDefinition.class));
            return yaml.load(fr);
        } catch (IOException | YAMLException e) {
            log.error("Error parsing '" + f.getAbsolutePath() + "'", e);
        }

        return null;
    }
}
