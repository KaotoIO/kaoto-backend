package io.zimara.backend.metadata;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ParseCatalog<T> {
    CompletableFuture<List<T>> parse();
}
