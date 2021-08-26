package io.zimara.backend.metadata;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 🐱class ParseCatalog
 * <p>
 * Load and warm up catalog utility.
 */
public interface ParseCatalog<T> {
    /*
     * 🐱method parse : CompletableFuture[List[T]]
     *
     * Load all the source data, parse it and return a completable list of elements to add to a catalog.
     *
     */
    CompletableFuture<List<T>> parse();
}
