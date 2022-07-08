package io.kaoto.backend.metadata.catalog;


import io.kaoto.backend.metadata.MetadataCatalog;
import io.kaoto.backend.model.Metadata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 🐱class CatalogCollection
 * 🐱inherits MetadataCatalog
 *
 * Joins a list of metadata catalogs.
 * When those catalogs are updated, the content of this collection changes too.
 * As each catalog have their own ID constraints, there may be more than one
 * connector with the same ID on this collection of catalogs.
 */
public class CatalogCollection<T extends Metadata>
        implements MetadataCatalog<T> {

    private final List<MetadataCatalog<T>> catalogs;

    public CatalogCollection() {
        catalogs = new ArrayList<>();
    }

    public void addCatalog(final MetadataCatalog<T> c) {
        if (!catalogs.contains(c)) {
            catalogs.add(c);
        }
    }

    @Override
    public T searchStepByID(final String id) {
        for (MetadataCatalog<T> c : catalogs) {
            T s = c.searchStepByID(id);
            if (s != null) {
                return s;
            }
        }
        return null;
    }

    @Override
    public T searchStepByName(final String connectionName) {
        for (MetadataCatalog<T> c : catalogs) {
            var s = c.searchStepByName(connectionName);
            if (s != null) {
                return s;
            }
        }
        return null;
    }

    @Override
    public Collection<T> searchStepsByName(final String connectionName) {
        Collection<T> steps = new ArrayList<>();
        for (MetadataCatalog<T> c : catalogs) {
            steps.addAll(c.searchStepsByName(connectionName));
        }
        return steps;
    }

    @Override
    public boolean store(final List<T> steps) {
        throw new UnsupportedOperationException(
                "CatalogCollection does not support direct storage.");
    }

    @Override
    public Collection<T> getAll() {
        Collection<T> steps = new ArrayList<>();
        for (MetadataCatalog<T> c : catalogs) {
            steps.addAll(c.getAll());
        }
        return steps;
    }

    @Override
    public void clear() {
        catalogs.clear();
    }
}
