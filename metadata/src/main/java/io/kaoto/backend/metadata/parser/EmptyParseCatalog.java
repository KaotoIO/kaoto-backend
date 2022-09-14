package io.kaoto.backend.metadata.parser;

import io.kaoto.backend.metadata.ParseCatalog;
import io.kaoto.backend.model.Metadata;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 🐱class EmptyParseCatalog
 * 🐱inherits ParseCatalog
 * This returns an empty catalog.
 */
public class EmptyParseCatalog<T extends Metadata> implements ParseCatalog<T> {

    @Override
    public CompletableFuture<List<T>> parse() {
        CompletableFuture<List<T>> metadata = new CompletableFuture<>();
        metadata.complete(List.of());
        return metadata;
    }

    @Override
    public void setFileVisitor(final ProcessFile<T> fileVisitor) {
    }
}
