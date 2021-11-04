package io.kaoto.backend.api.metadata.catalog;

public class CatalogWarmingUpException extends RuntimeException {

    CatalogWarmingUpException(final String message) {
        super(message);
    }
}
