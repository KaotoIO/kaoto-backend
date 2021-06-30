package io.zimara.backend.metadata;

import io.zimara.backend.model.Step;

import java.util.Collection;
import java.util.List;

public interface MetadataCatalog {

    Step searchStepByID(String id);

    Step searchStepByName(String connectionName);

    Collection<Step> searchStepsByName(String connectionName);

    boolean store(List<Step> steps);

    Collection<Step> getAll();
}
