package io.zimara.backend.metadata;

import io.zimara.backend.model.Metadata;

import java.util.Collection;
import java.util.List;

/**
 * 🐱class MetadataCatalog
 * 🐱relationship dependsOn Metadata
 * <p>
 * <p>
 * All metadata catalogs implements this interface. Helper to unify catalog handling implementation.
 */
public interface MetadataCatalog<T extends Metadata> {

    /*
     * 🐱method searchStepByID : Metadata
     * 🐱param id: String
     *
     * Returns the element identified by the parameter.
     *
     */
    T searchStepByID(String id);

    /*
     * 🐱method searchStepByName : Metadata
     * 🐱param name: String
     *
     * Returns the first element found (no order warranteed) identified by the name.
     *
     */
    T searchStepByName(String name);

    /*
     * 🐱method searchStepByName : List[Metadata]
     * 🐱param name: String
     *
     * Returns all the elements found (no order warranteed) identified by the name.
     *
     */
    Collection<T> searchStepsByName(String name);

    /*
     * 🐱method store: boolean
     * 🐱param steps: List[Step]
     *
     * Stores the elements passed as parameter on the catalog. Returns true if no error was found.
     *
     */
    boolean store(List<T> steps);

    /*
     * 🐱method getAll : List[Metadata]
     *
     * Returns all the elements in the catalog. Be careful using this as there's no limit on the amount of data stored on the catalog.
     *
     */
    Collection<T> getAll();
}
