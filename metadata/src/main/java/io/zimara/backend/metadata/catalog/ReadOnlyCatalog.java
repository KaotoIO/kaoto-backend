package io.zimara.backend.metadata.catalog;

import io.zimara.backend.metadata.MetadataCatalog;
import io.zimara.backend.model.Metadata;

/**
 * 🐱class ReadOnlyCatalog
 * 🐱inherits CatalogCollection
 * <p>
 * A Catalog Collection we make sure it can't be modified.
 */
public class ReadOnlyCatalog<T extends Metadata> extends CatalogCollection<T> {

    public ReadOnlyCatalog(MetadataCatalog<T> catalog) {
        super.addCatalog(catalog);
    }

    @Override
    public void addCatalog(MetadataCatalog<T> c) {
        throw new UnsupportedOperationException("Read Only Catalogs are initialized at the beginning");
    }
}
