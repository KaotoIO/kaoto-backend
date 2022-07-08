package io.kaoto.backend.metadata.catalog;

import io.kaoto.backend.metadata.MetadataCatalog;
import io.kaoto.backend.model.Metadata;

/**
 * 🐱class ReadOnlyCatalog
 * 🐱inherits CatalogCollection
 *
 * A Catalog Collection that acts as singleton to a catalog collection. It
 * only contains one catalog, and if you add another one, it replaces it.
 * This is useful to avoid empty catalogs when reloading data.
 */
public class ReadOnlyCatalog<T extends Metadata> extends CatalogCollection<T> {

    public ReadOnlyCatalog(final MetadataCatalog<T> catalog) {
        super.addCatalog(catalog);
    }

    @Override
    public synchronized void addCatalog(final MetadataCatalog<T> c) {
        this.clear();
        super.addCatalog(c);
    }
}
