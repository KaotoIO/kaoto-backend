package io.zimara.backend.metadata;

import io.zimara.backend.model.Metadata;

import java.util.Collection;
import java.util.List;

public interface MetadataCatalog<T extends Metadata > {

    T searchStepByID(String id);

    T searchStepByName(String name);

    Collection<T> searchStepsByName(String name);

    boolean store(List<T> steps);

    Collection<T> getAll();
}
