package io.zimara.backend.metadata.parser;

import io.zimara.backend.model.Metadata;
import org.jboss.logging.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public abstract class YamlProcessFile<T extends Metadata> implements FileVisitor<Path> {

    protected List<T> metadataList;
    protected List<CompletableFuture<T>> futureMetadata;
    private Logger log = Logger.getLogger(YamlProcessFile.class);

    protected YamlProcessFile(List<T> metadataList, List<CompletableFuture<T>> futureMetadata) {
        this.metadataList = metadataList;
        this.futureMetadata = futureMetadata;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        final var name = dir.toFile().getName();
        if (name.equalsIgnoreCase("test")
                || name.equalsIgnoreCase(".git")
                || name.equalsIgnoreCase(".github")
                || name.equalsIgnoreCase("docs")
                || name.equalsIgnoreCase("script")) {
            return FileVisitResult.SKIP_SUBTREE;
        }

        log.trace("Visiting '" + name + "'");
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(final Path file, BasicFileAttributes attrs) {

        File f = file.toFile();

        if (isYAML(f)) {
            CompletableFuture<T> metadata =
                    CompletableFuture.supplyAsync(() -> parseFile(f));

            metadata.thenRun(() -> log.trace(f.getName() + " parsed, now generating metadata."));
            metadata
                    .thenAccept(metadataList::add);
            futureMetadata.add(metadata);
        }

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    protected boolean isYAML(File file) {
        return (file.getName().endsWith(".yml") || file.getName().endsWith(".yaml")) && !file.getName().startsWith(".");
    }

    protected abstract T parseFile(File f);
}
