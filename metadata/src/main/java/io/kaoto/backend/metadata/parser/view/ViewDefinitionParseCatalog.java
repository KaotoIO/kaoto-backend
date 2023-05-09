package io.kaoto.backend.metadata.parser.view;

import io.kaoto.backend.metadata.ParseCatalog;
import io.kaoto.backend.metadata.parser.GitParseCatalog;
import io.kaoto.backend.metadata.parser.JarParseCatalog;
import io.kaoto.backend.metadata.parser.LocalFolderParseCatalog;
import io.kaoto.backend.metadata.parser.YamlProcessFile;
import io.kaoto.backend.model.view.ViewDefinition;
import org.apache.commons.io.IOUtils;
import org.jboss.logging.Logger;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.error.YAMLException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 🐱class ViewDefinitionParseCatalog
 *
 * 🐱relationship dependsOn ParseCatalog
 *
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
        ParseCatalog<ViewDefinition> parseCatalog = new GitParseCatalog<>(url, tag);
        parseCatalog.setFileVisitor(new ViewDefinitionProcessFile());
        return parseCatalog;
    }

    public static ParseCatalog<ViewDefinition> getParser(final Path toPath) {
        ParseCatalog<ViewDefinition> parseCatalog = new LocalFolderParseCatalog<>(toPath);
        parseCatalog.setFileVisitor(new ViewDefinitionProcessFile());
        return parseCatalog;
    }
}

class ViewDefinitionProcessFile extends YamlProcessFile<ViewDefinition> {

    private Logger log = Logger.getLogger(ViewDefinitionProcessFile.class);

    ViewDefinitionProcessFile() {
    }

    @Override
    public List<ViewDefinition> parseInputStream(final Reader reader) {
        try {
            final var content = IOUtils.toString(reader);
            if (!appliesTo(content)) {
                return List.of();
            }
            Yaml yaml = new Yaml(new Constructor(ViewDefinition.class, new LoaderOptions()));
            ViewDefinition viewDefinition = yaml.load(content);
            return List.of(viewDefinition);
        } catch (YAMLException | IOException e) {
            log.error("Error parsing ViewDefinition.", e);
        }

        return List.of();
    }

    private boolean appliesTo(final String yaml) {
        String[] kinds = new String[]{"generic", "step"};

        Pattern pattern = Pattern.compile("[\r|\n]type:(.+)[\n|\r]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(yaml);
        if (matcher.find()) {
            return Arrays.stream(kinds).anyMatch(k -> k.equalsIgnoreCase(matcher.group(1).trim()));
        }

        return false;
    }
}
