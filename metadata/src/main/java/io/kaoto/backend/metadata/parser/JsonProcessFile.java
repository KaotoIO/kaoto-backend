package io.kaoto.backend.metadata.parser;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

import org.jboss.logging.Logger;

import io.kaoto.backend.model.Metadata;

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

    private static final Logger LOG = Logger.getLogger(JsonProcessFile.class);

    @Override
    protected boolean isDesiredType(final String filename ) {
        return filename != null && !filename.isEmpty() && filename.endsWith(".json") && !filename.startsWith(".");
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

        LOG.trace("Visiting '" + name + "'");
        return FileVisitResult.CONTINUE;
    }
}
