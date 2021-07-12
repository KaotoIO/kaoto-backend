package io.zimara.backend.metadata.catalog;

import io.zimara.backend.metadata.MetadataCatalog;
import io.zimara.backend.model.Step;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * 🐱class CatalogCollection
 *
 * Joins a list of catalogs.
 *
 * When those catalogs are updated, the content of this collection changes too.
 * As each catalog have their own ID constraints, there may be more than one
 * connector with the same ID on this collection of catalogs.
 */
public class CatalogCollection implements MetadataCatalog {

    private final List<MetadataCatalog> catalogs;

    public CatalogCollection() {
        catalogs = new ArrayList<>();
    }

    public void addCatalog(MetadataCatalog c) {
        if (!catalogs.contains(c)) {
            catalogs.add(c);
        }
    }

    @Override
    public Step searchStepByID(String id) {
        for (MetadataCatalog c : catalogs) {
            Step s = c.searchStepByID(id);
            if (s != null) {
                return s;
            }
        }
        return null;
    }

    @Override
    public Step searchStepByName(String connectionName) {
        for (MetadataCatalog c : catalogs) {
            var s = c.searchStepByName(connectionName);
            if (s != null) {
                return s;
            }
        }
        return null;
    }

    @Override
    public Collection<Step> searchStepsByName(String connectionName) {
        Collection<Step> steps = new ArrayList<>();
        for (MetadataCatalog c : catalogs) {
            steps.addAll(c.searchStepsByName(connectionName));
        }
        return steps;
    }

    @Override
    public boolean store(List<Step> steps) {
        throw new UnsupportedOperationException("CatalogCollection does not support direct storage.");
    }

    @Override
    public Collection<Step> getAll() {
        Collection<Step> steps = new ArrayList<>();
        for (MetadataCatalog c : catalogs) {
            steps.addAll(c.getAll());
        }
        return steps;
    }
}
