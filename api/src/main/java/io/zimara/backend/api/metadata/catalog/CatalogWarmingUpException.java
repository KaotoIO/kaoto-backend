package io.zimara.backend.api.metadata.catalog;

public class CatalogWarmingUpException extends RuntimeException {

    CatalogWarmingUpException(String message){
        super(message);
    }
}
