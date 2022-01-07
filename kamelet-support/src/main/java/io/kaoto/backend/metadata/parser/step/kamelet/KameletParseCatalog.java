package io.kaoto.backend.metadata.parser.step.kamelet;

import io.kaoto.backend.api.metadata.catalog.StepCatalogParser;
import io.kaoto.backend.metadata.ParseCatalog;
import io.kaoto.backend.metadata.parser.GitParseCatalog;
import io.kaoto.backend.metadata.parser.JarParseCatalog;
import io.kaoto.backend.model.step.Step;

import javax.enterprise.context.ApplicationScoped;

/**
 * 🐱class KameletParseCatalog
 * 🐱relationship dependsOn ParseCatalog
 * Reads and parses a kamelet catalog.
 * Extracts all the kamelet definitions it can find
 * and generate a kamelet step for each one.
 */
@ApplicationScoped
public final class KameletParseCatalog implements StepCatalogParser {

    private KameletParseCatalog() {

    }

    @Override
    public ParseCatalog<Step> getParser(final String url, final String tag) {
        ParseCatalog<Step> parseCatalog = new GitParseCatalog<>(url, tag);
        parseCatalog.setFileVisitor(new KameletFileProcessor());
        return parseCatalog;
    }


    @Override
    public ParseCatalog<Step> getParser(final String url) {
        ParseCatalog<Step> parseCatalog = new JarParseCatalog<>(url);
        parseCatalog.setFileVisitor(new KameletFileProcessor());
        return parseCatalog;
    }
}

