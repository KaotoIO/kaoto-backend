package io.kaoto.backend.api.metadata.catalog;

import io.kaoto.backend.model.configuration.Repository;
import io.smallrye.config.ConfigMapping;

/**
 * 🐱miniclass ViewDefinitionRepository (ViewDefinitionCatalog)
 */
@ConfigMapping(prefix = "repository.viewdefinition")
public interface ViewDefinitionRepository extends Repository {
}
