package io.kaoto.backend.metadata.catalog;

import io.kaoto.backend.metadata.MetadataCatalog;
import io.kaoto.backend.model.Metadata;
import org.jboss.logging.Logger;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 🐱class InMemoryCatalog
 * 🐱inherits MetadataCatalog
 *
 * Basic catalog implementation useful for testing and small instances.
 */
public class InMemoryCatalog<T extends Metadata> implements MetadataCatalog<T> {

    private static final Logger LOG = Logger.getLogger(InMemoryCatalog.class);
    private final Map<String, T> metadataCatalog = new HashMap<>();

    @Override
    public boolean store(final List<T> steps) {
        if (steps == null) {
            return false;
        }
        final Collector<T, ?, Map<String, T>> stepMapCollector =
                Collectors.toMap(T::getId, step -> step, (a, b) -> a);
        metadataCatalog.putAll(Collections.synchronizedMap(
                steps.stream()
                        .parallel()
                        .filter(Objects::nonNull)
                        .collect(stepMapCollector)));
        LOG.trace("Catalog now has " + metadataCatalog.size() + " elements.");

        return true;
    }

    @Override
    public T searchByID(final String id) {
        T step = metadataCatalog.get(id);
        if (step != null) {
            step = (T) step.clone();
        }
        return step;
    }

    @Override
    public Collection<T> searchByName(final String name) {
        if (name == null) {
            return Collections.emptyList();
        }
        return metadataCatalog.entrySet().stream().parallel()
                .filter(entry -> name.equalsIgnoreCase(entry.getValue().getName()))
                .map(Map.Entry::getValue)
                .map(t -> (T) t.clone())
                .toList();
    }

    @Override
    public Collection<T> getAll() {
        return metadataCatalog.entrySet().stream().parallel()
                .map(Map.Entry::getValue)
                .map(t -> (T) t.clone())
                .toList();
    }

    @Override
    public void clear() {
        metadataCatalog.clear();
    }
}
