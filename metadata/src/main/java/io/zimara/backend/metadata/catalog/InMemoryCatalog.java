package io.zimara.backend.metadata.catalog;

import io.zimara.backend.metadata.MetadataCatalog;
import io.zimara.backend.model.Step;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class InMemoryCatalog implements MetadataCatalog {

    private Map<String, Step> metadataCatalog = new HashMap<>();

    @Override
    public boolean store(List<Step> steps) {
        if (steps == null) {
            return false;
        }
        final Collector<Step, ?, Map<String, Step>> stepMapCollector =
                Collectors.toMap(Step::getID, step -> step, (a, b) -> a);
        metadataCatalog = Collections.synchronizedMap(steps.stream().parallel().collect(stepMapCollector));
        return true;
    }

    @Override
    public Step searchStepByID(String id) {
        return metadataCatalog.get(id);
    }

    @Override
    public Step searchStepByName(String name) {
        for (Map.Entry<String, Step> entry : metadataCatalog.entrySet()) {
            if (name.equalsIgnoreCase(entry.getValue().getName())) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public Collection<Step> searchStepsByName(String name) {
        Collection<Step> steps = new ArrayList<>();

        for (Map.Entry<String, Step> entry : metadataCatalog.entrySet()) {
            if (name.equalsIgnoreCase(entry.getValue().getName())) {
                steps.add(entry.getValue());
            }
        }

        return steps;
    }

    @Override
    public Collection<Step> getAll() {
        return Collections.unmodifiableCollection(this.metadataCatalog.values());
    }
}
