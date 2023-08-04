package io.kaoto.backend.camel.metadata.parser.step.camelroute;

import io.kaoto.backend.api.metadata.catalog.StepCatalogParser;
import io.kaoto.backend.metadata.ParseCatalog;
import io.kaoto.backend.metadata.parser.EmptyParseCatalog;
import io.kaoto.backend.metadata.parser.GitParseCatalog;
import io.kaoto.backend.metadata.parser.JarParseCatalog;
import io.kaoto.backend.metadata.parser.LocalFolderParseCatalog;
import io.kaoto.backend.model.step.Step;

import jakarta.enterprise.context.ApplicationScoped;
import java.nio.file.Path;

@ApplicationScoped
public final class CamelRouteParseCatalog implements StepCatalogParser {

    @Override
    public ParseCatalog<Step> getParser(final String url, final String tag) {
        ParseCatalog<Step> parseCatalog = new GitParseCatalog<>(url, tag);
        parseCatalog.setFileVisitor(new CamelRouteFileProcessor());
        return parseCatalog;
    }


    @Override
    public ParseCatalog<Step> getParser() {
        //We are not expecting to get Camel Operators from memory
        return new EmptyParseCatalog<>();
    }

    @Override
    public ParseCatalog<Step> getParser(final String url) {
        ParseCatalog<Step> parseCatalog = new JarParseCatalog<>(url);
        parseCatalog.setFileVisitor(new CamelRouteFileProcessor());
        return parseCatalog;
    }

    @Override
    public ParseCatalog<Step> getParserFromCluster() {
        //We are not expecting to get Camel Operators from here
        return new EmptyParseCatalog<>();
    }

    @Override
    public ParseCatalog<Step> getLocalFolder(final Path path) {
        ParseCatalog<Step> parseCatalog = new LocalFolderParseCatalog<>(path);
        parseCatalog.setFileVisitor(new CamelRouteFileProcessor());
        return parseCatalog;
    }

    @Override
    public boolean generatesKind(final String kind) {
        return kind.isBlank() || "Camel-Connector".equalsIgnoreCase(kind);
    }
}
