package io.kaoto.backend.metadata;

import io.kaoto.backend.metadata.parser.YamlProcessFile;
import io.kaoto.backend.model.Metadata;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 🐱class ParseCatalog
 * Load and warm up catalog utility.
 */
public interface ParseCatalog<T extends Metadata> {
    /*
     * 🐱method parse : CompletableFuture[List[T]]
     *
     * Load all the source data, parse it and
     * return a completable list of elements to add to a catalog.
     *
     */
    CompletableFuture<List<T>> parse();


    /*
     * 🐱method setFileVisitor
     * 🐱param fileVisitor: YamlProcessFile
     *
     */
    void setFileVisitor(YamlProcessFile<T> fileVisitor);
}
