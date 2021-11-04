package io.kaoto.backend.metadata.catalog;

import io.kaoto.backend.metadata.MetadataCatalog;
import io.kaoto.backend.model.Metadata;

/**
 * 🐱class ReadOnlyCatalog
 * 🐱inherits CatalogCollection
 *
 * A Catalog Collection we make sure it can't be modified.
 */
public class ReadOnlyCatalog<T extends Metadata> extends CatalogCollection<T> {

    public ReadOnlyCatalog(final MetadataCatalog<T> catalog) {
        super.addCatalog(catalog);
    }

    @Override
    public void addCatalog(final MetadataCatalog<T> c) {
        throw new UnsupportedOperationException(
                "Read Only Catalogs are initialized at the beginning");
    }
}
