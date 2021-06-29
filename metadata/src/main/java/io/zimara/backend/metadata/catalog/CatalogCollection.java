package io.zimara.backend.metadata.catalog;

import io.zimara.backend.metadata.MetadataCatalog;
import io.zimara.backend.model.Step;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CatalogCollection implements MetadataCatalog {

    private List<MetadataCatalog> catalogs;

    public CatalogCollection() {
        catalogs = new ArrayList<>();
    }

    public void addCatalog(MetadataCatalog c) {
        if(!catalogs.contains(c)) {
            catalogs.add(c);
        }
    }

    @Override
    public Step searchStepByID(String ID) {
        for(MetadataCatalog c : catalogs) {
            Step s = c.searchStepByID(ID);
            if(s != null) {
                return s;
            }
        }
        return null;
    }

    @Override
    public Step searchStepByName(String connectionName) {
        for(MetadataCatalog c : catalogs) {
            Step s = c.searchStepByName(connectionName);
            if(s != null) {
                return s;
            }
        }
        return null;
    }

    @Override
    public Collection<Step> searchStepsByName(String connectionName) {
        Collection<Step> steps = new ArrayList<>();
        for(MetadataCatalog c : catalogs) {
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
        for(MetadataCatalog c : catalogs) {
            steps.addAll(c.getAll());
        }
        return steps;
    }
}
