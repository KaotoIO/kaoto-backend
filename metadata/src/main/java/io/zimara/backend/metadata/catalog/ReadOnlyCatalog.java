package io.zimara.backend.metadata.catalog;

import io.zimara.backend.metadata.MetadataCatalog;

public class ReadOnlyCatalog extends CatalogCollection {

    public ReadOnlyCatalog(MetadataCatalog catalog) {
        super.addCatalog(catalog);
    }

    @Override
    public void addCatalog(MetadataCatalog c) {
        throw new UnsupportedOperationException("Read Only Catalogs are initialized at the beginning");
    }
}
