package io.kaoto.backend.api.metadata.catalog;

import io.kaoto.backend.model.configuration.Repository;
import io.smallrye.config.ConfigMapping;

/**
 * 🐱miniclass StepRepository (StepCatalog)
 */
@ConfigMapping(prefix = "repository.step")
public interface StepRepository extends Repository {
}
