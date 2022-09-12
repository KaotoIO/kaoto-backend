package io.kaoto.backend.metadata.parser.view;

import io.kaoto.backend.metadata.ParseCatalog;
import io.kaoto.backend.metadata.parser.GitParseCatalog;
import io.kaoto.backend.metadata.parser.JarParseCatalog;
import io.kaoto.backend.metadata.parser.YamlProcessFile;
import io.kaoto.backend.model.view.ViewDefinition;
import org.jboss.logging.Logger;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.error.YAMLException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

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
            final String url) {
        ParseCatalog<ViewDefinition> parseCatalog = new JarParseCatalog<>(url);
        parseCatalog.setFileVisitor(new ViewDefinitionProcessFile());
        return parseCatalog;
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
    public List<ViewDefinition> parseFile(final File f) {
        try (FileReader fr = new FileReader(f)) {
            Yaml yaml = new Yaml(new Constructor(ViewDefinition.class));
            ViewDefinition viewDefinition = yaml.load(fr);
            return List.of(viewDefinition);
        } catch (IOException | YAMLException e) {
            log.error("Error parsing '" + f.getAbsolutePath() + "'", e);
        }

        return List.of();
    }
}
