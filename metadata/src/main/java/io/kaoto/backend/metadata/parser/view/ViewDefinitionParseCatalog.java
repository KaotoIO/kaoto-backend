package io.kaoto.backend.metadata.parser.view;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.jboss.logging.Logger;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.error.YAMLException;

import io.kaoto.backend.metadata.ParseCatalog;
import io.kaoto.backend.metadata.parser.GitParseCatalog;
import io.kaoto.backend.metadata.parser.JarParseCatalog;
import io.kaoto.backend.metadata.parser.LocalFolderParseCatalog;
import io.kaoto.backend.metadata.parser.YamlProcessFile;
import io.kaoto.backend.model.view.ViewDefinition;

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

    private static final Logger LOG = Logger.getLogger(ViewDefinitionProcessFile.class);
    private static final String[] KINDS = new String[] {"generic", "step"};
    private static final Pattern TYPE_PATTERN = Pattern.compile("[\r|\n]type:(.+)[\n|\r]", Pattern.CASE_INSENSITIVE);

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
            LOG.error("Error parsing ViewDefinition.", e);
        }

        return List.of();
    }

    private boolean appliesTo(final String yaml) {
        Matcher matcher = TYPE_PATTERN.matcher(yaml);
        if (matcher.find()) {
            String match = matcher.group(1).trim();
            for (String kind : KINDS) {
                if (kind.equals(match)) {
                    return true;
                }
            }
        }

        return false;
    }
}
