package io.kaoto.backend.api.metadata.catalog;

import io.kaoto.backend.metadata.ParseCatalog;
import io.kaoto.backend.model.step.Step;
import io.opentelemetry.extension.annotations.WithSpan;

import java.nio.file.Path;

/**
 * 🐱class StepCatalogParser
 *
 * Interface used to parse steps from a remote catalog.
 *
 */
public interface StepCatalogParser {

    /*
     * 🐱method getParser : ParseCatalog
     * 🐱param url : String
     *
     * Loads all the elements on the given url.
     *
     */
    @WithSpan
    ParseCatalog<Step> getParser(String url);

    /*
     * 🐱method getParser : ParseCatalog
     * 🐱param url : String
     * 🐱param tag : String
     *
     * Loads all the elements on the git repository.
     *
     */
    @WithSpan
    ParseCatalog<Step> getParser(String url, String tag);

    /*
     * 🐱method getParserFromCluster : ParseCatalog
     *
     * Loads all the elements on the connected cluster.
     *
     */
    @WithSpan
    ParseCatalog<Step> getParserFromCluster();

    /*
     * 🐱method getLocalFolder : ParseCatalog
     * 🐱param path : Path
     *
     * Loads all the elements on the given path.
     *
     */
    @WithSpan
    ParseCatalog<Step> getLocalFolder(Path path);
}
