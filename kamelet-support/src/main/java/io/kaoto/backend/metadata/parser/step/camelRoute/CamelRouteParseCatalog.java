package io.kaoto.backend.metadata.parser.step.camelRoute;

import io.kaoto.backend.api.metadata.catalog.StepCatalogParser;
import io.kaoto.backend.metadata.ParseCatalog;
import io.kaoto.backend.metadata.parser.GitParseCatalog;
import io.kaoto.backend.metadata.parser.JarParseCatalog;
import io.kaoto.backend.model.step.Step;

import javax.enterprise.context.ApplicationScoped;
import java.nio.file.FileVisitor;
import java.nio.file.Path;

@ApplicationScoped
public final class CamelRouteParseCatalog implements StepCatalogParser {
    private CamelRouteParseCatalog() {

    }

    @Override
    public ParseCatalog<Step> getParser(final String url, final String tag) {
        ParseCatalog<Step> parseCatalog = new GitParseCatalog<>(url, tag);
        parseCatalog.setFileVisitor(new CamelRouteFileProcessor());
        return parseCatalog;
    }


    @Override
    public ParseCatalog<Step> getParser(final String url) {

        ParseCatalog<Step> parseCatalog = new JarParseCatalog<>(url);
        parseCatalog.setFileVisitor(new CamelRouteFileProcessor());
        return parseCatalog;
    }
}
