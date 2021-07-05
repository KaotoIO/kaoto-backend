package io.zimara.backend.metadata;

import io.zimara.backend.model.Step;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ParseCatalog {
    CompletableFuture<List<Step>> parse();
}
