package io.kaoto.backend.metadata.parser;

import io.kaoto.backend.model.Metadata;
import org.jboss.logging.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 🐱class YamlProcessFile
 * 🐱relationship dependsOn GitParseCatalog
 * Helper class to walk around YAML files to parse Metadata objects.
 */
public abstract class ProcessFile<T extends Metadata>
        implements FileVisitor<Path> {

    private List<T> metadataList;
    private List<CompletableFuture<Void>> futureMetadata;
    private Logger log = Logger.getLogger(ProcessFile.class);

    protected ProcessFile() {
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
                || name.equalsIgnoreCase("test")) {
            return FileVisitResult.SKIP_SUBTREE;
        }

        log.trace("Visiting '" + name + "'");
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(final Path file,
                                     final BasicFileAttributes attrs) {

        File f = file.toFile();

        if (isDesiredType(f)) {
            CompletableFuture<Void> metadata =
                    CompletableFuture.runAsync(() ->
                            metadataList.add(parseFile(f)));
            metadata.thenRun(() -> log.trace(f.getName()
                    + " parsed, now generating metadata."));
            futureMetadata.add(metadata);
        }

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(final Path file,
                                           final IOException exc) {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(final Path dir,
                                              final IOException exc) {
        return FileVisitResult.CONTINUE;
    }

    abstract boolean isDesiredType(File file);

    protected abstract T parseFile(File f);

    public void setMetadataList(final List<T> metadataList) {
        this.metadataList = metadataList;
    }

    public void setFutureMetadata(
            final List<CompletableFuture<Void>> futureMetadata) {
        this.futureMetadata = futureMetadata;
    }
}
