package io.zimara.backend.metadata.parser.view;

import io.zimara.backend.metadata.ParseCatalog;
import io.zimara.backend.metadata.parser.GitParseCatalog;
import io.zimara.backend.metadata.parser.YamlProcessFile;
import io.zimara.backend.model.view.ViewDefinition;
import org.jboss.logging.Logger;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.error.YAMLException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 🐱class ViewDefinitionParseCatalog
 * 🐱relationship dependsOn ParseCatalog
 * Reads and parses a view definition catalog.
 * Extracts all the view definitions it can find
 * and generate a ViewDefinition for each one.
 */
public final class ViewDefinitionParseCatalog {

    private ViewDefinitionParseCatalog() {

    }

    public static ParseCatalog<ViewDefinition> getParser(
            final String url, final String tag) {
        ParseCatalog<ViewDefinition> parseCatalog =
                new GitParseCatalog<>(url, tag);
        parseCatalog.setFileVisitor(new ViewDefinitionProcessFile());
        return parseCatalog;
    }
}

class ViewDefinitionProcessFile extends YamlProcessFile<ViewDefinition> {

    private Logger log = Logger.getLogger(ViewDefinitionProcessFile.class);

    ViewDefinitionProcessFile() {
    }

    @Override
    public ViewDefinition parseFile(final File f) {
        try (FileReader fr = new FileReader(f)) {
            Yaml yaml = new Yaml(new Constructor(ViewDefinition.class));
            return yaml.load(fr);
        } catch (IOException | YAMLException e) {
            log.error("Error parsing '" + f.getAbsolutePath() + "'", e);
        }

        return null;
    }
}
