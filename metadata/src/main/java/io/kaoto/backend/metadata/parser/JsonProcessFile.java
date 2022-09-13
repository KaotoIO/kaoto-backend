package io.kaoto.backend.metadata.parser;

import io.kaoto.backend.model.Metadata;
import org.jboss.logging.Logger;

import java.io.File;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public abstract class JsonProcessFile<T extends Metadata>
        extends ProcessFile<T> {

    private Logger log = Logger.getLogger(ProcessFile.class);

    @Override
    protected boolean isDesiredType(final File file) {
        return file.getName().endsWith(".json")
                && !file.getName().startsWith(".");
    }

    @Override
    public FileVisitResult preVisitDirectory(final Path dir,
                                             final BasicFileAttributes attrs) {
        final var name = dir.toFile().getName();
        if (name.equalsIgnoreCase(".git")
                || name.equalsIgnoreCase(".github")
                || name.equalsIgnoreCase("docs")
                || name.equalsIgnoreCase("library")
                || name.equalsIgnoreCase("script")
                || name.equalsIgnoreCase("templates")
                || name.equalsIgnoreCase("test")
                || name.equalsIgnoreCase(".mvn")
                || name.equalsIgnoreCase("apache-camel")
                || name.equalsIgnoreCase("archetypes")
                || name.equalsIgnoreCase("bom")
                || name.equalsIgnoreCase("buildingtools")
                || name.equalsIgnoreCase("camel-dependencies")
                || name.equalsIgnoreCase("tooling")
                || name.equalsIgnoreCase("test-infra")
                || name.equalsIgnoreCase("parent")
                || name.equalsIgnoreCase("init")
                || name.equalsIgnoreCase("etc")
                || name.equalsIgnoreCase("dsl")
                || name.equalsIgnoreCase("core")
                ) {
            return FileVisitResult.SKIP_SUBTREE;
        }

        log.trace("Visiting '" + name + "'");
        return FileVisitResult.CONTINUE;
    }
}
