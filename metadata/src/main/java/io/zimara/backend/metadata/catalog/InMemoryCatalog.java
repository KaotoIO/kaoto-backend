package io.zimara.backend.metadata.catalog;

import io.zimara.backend.metadata.MetadataCatalog;
import io.zimara.backend.model.Metadata;
import org.jboss.logging.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class InMemoryCatalog<T extends Metadata> implements MetadataCatalog<T> {

    private Map<String, T> metadataCatalog = new HashMap<>();
    private Logger log = Logger.getLogger(InMemoryCatalog.class);

    @Override
    public boolean store(List<T> steps) {
        if (steps == null) {
            return false;
        }
        final Collector<T, ?, Map<String, T>> stepMapCollector =
                Collectors.toMap(T::getId, step -> step, (a, b) -> a);
        metadataCatalog = Collections.synchronizedMap(steps.stream().filter(Objects::nonNull).parallel().collect(stepMapCollector));
        log.trace("Catalog now has " + metadataCatalog.size() + " elements.");

        return true;
    }

    @Override
    public T searchStepByID(String id) {
        return metadataCatalog.get(id);
    }

    @Override
    public T searchStepByName(String name) {
        for (Map.Entry<String, T> entry : metadataCatalog.entrySet()) {
            if (name.equalsIgnoreCase(entry.getValue().getName())) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public Collection<T> searchStepsByName(String name) {
        Collection<T> steps = new ArrayList<>();

        for (Map.Entry<String, T> entry : metadataCatalog.entrySet()) {
            if (name.equalsIgnoreCase(entry.getValue().getName())) {
                steps.add(entry.getValue());
            }
        }

        return steps;
    }

    @Override
    public Collection<T> getAll() {
        return Collections.unmodifiableCollection(this.metadataCatalog.values());
    }
}
