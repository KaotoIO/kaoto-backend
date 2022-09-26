package io.kaoto.backend.metadata.parser;

import io.kaoto.backend.model.Metadata;
import org.jboss.logging.Logger;

import java.io.File;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public abstract class JsonProcessFile<T extends Metadata>
        extends ProcessFile<T> {

    private static final List<String> SUBDIRECTORIES_TO_SKIP = List.of(
                ".git",
                ".github",
                "docs",
                "library",
                "script",
                "templates",
                "test",
                ".mvn",
                "apache-camel",
                "archetypes",
                "bom",
                "buildingtools",
                "camel-dependencies",
                "tooling",
                "test-infra",
                "parent",
                "init",
                "etc",
                "dsl",
                "core"
            );

    private Logger log = Logger.getLogger(JsonProcessFile.class);

    @Override
    protected boolean isDesiredType(final File file) {
        return file.getName().endsWith(".json")
                && !file.getName().startsWith(".");
    }

    @Override
    public FileVisitResult preVisitDirectory(final Path dir,
                                             final BasicFileAttributes attrs) {

        final var name = dir.toFile().getName();
        if (SUBDIRECTORIES_TO_SKIP.stream().anyMatch(
                directoryToSkipName ->
                        directoryToSkipName.equalsIgnoreCase(name))
        ) {
            return FileVisitResult.SKIP_SUBTREE;
        }

        log.trace("Visiting '" + name + "'");
        return FileVisitResult.CONTINUE;
    }
}
