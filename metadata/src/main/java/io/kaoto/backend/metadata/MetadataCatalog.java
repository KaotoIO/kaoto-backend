package io.kaoto.backend.metadata;

import io.kaoto.backend.model.Metadata;

import java.util.Collection;
import java.util.List;

/**
 * 🐱class MetadataCatalog
 * 🐱relationship dependsOn Metadata
 * All metadata catalogs implements this interface.
 * Helper to unify catalog handling implementation.
 */
public interface MetadataCatalog<T extends Metadata> {

    /*
     * 🐱method searchStepByID : Metadata
     * 🐱param id: String
     *
     * Returns the element identified by the parameter.
     *
     */
    T searchByID(String id);

    /*
     * 🐱method searchByName : List[Metadata]
     * 🐱param name: String
     *
     * Returns all the elements found (no order warranteed)
     * identified by the name.
     *
     */
    Collection<T> searchByName(String name);

    /*
     * 🐱method store: boolean
     * 🐱param steps: List[Step]
     *
     * Stores the elements passed as parameter on the catalog.
     * Returns true if no error was found.
     *
     */
    boolean store(List<T> steps);

    /*
     * 🐱method getAll : List[Metadata]
     *
     * Returns all the elements in the catalog.
     * Be careful using this as there's no limit
     * on the amount of data stored on the catalog.
     *
     */
    Collection<T> getAll();

    /*
     * 🐱method clear : void
     *
     * Removes all metadata from catalog.
     *
     */
    void clear();


}
