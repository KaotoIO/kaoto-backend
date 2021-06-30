package io.zimara.backend.metadata;

import io.vertx.core.Future;
import io.zimara.backend.model.Step;

import java.util.List;

public interface ParseCatalog {
    Future<List<Step>> parse();
}
