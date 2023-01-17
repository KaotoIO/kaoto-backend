package io.kaoto.backend.metadata.parser;

import io.kaoto.backend.metadata.ParseCatalog;
import io.kaoto.backend.model.Metadata;
import org.jboss.logging.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 🐱class LocalFolderParseCatalog
 *
 * 🐱inherits ParseCatalog
 *
 * Abstract implementation that walks through all the files in a local folder
 * parsing them and preparing elements to add to a catalog.
 */
public class LocalFolderParseCatalog<T extends Metadata>
        implements ParseCatalog<T> {

    private Logger log = Logger.getLogger(LocalFolderParseCatalog.class);

    private ProcessFile<T> yamlProcessFile;

    private final Path uri;

    public LocalFolderParseCatalog(final Path uri) {
        this.uri = uri;
    }

    private List<T> getFolderAndParse(final Path location) {
        log.trace("Warming up repository in local folder" + uri);
        List<T> metadataList =
                Collections.synchronizedList(new CopyOnWriteArrayList<>());
        final List<CompletableFuture<Void>> futureMd =
                Collections.synchronizedList(new CopyOnWriteArrayList<>());

        //Walk the directory
        log.trace("Parsing all files in the folder.");
        this.yamlProcessFile.setFutureMetadata(futureMd);
        this.yamlProcessFile.setMetadataList(metadataList);
        try {
            Files.walkFileTree(location, this.yamlProcessFile);
        } catch (IOException e) {
            log.error("Error loading files from local folder.", e);
        }
        log.trace("Found " + futureMd.size() + " elements.");
        CompletableFuture.allOf(
                        futureMd.toArray(new CompletableFuture[0]))
                .join();


        return metadataList;
    }

    @Override
    public CompletableFuture<List<T>> parse() {
        CompletableFuture<List<T>> metadata = new CompletableFuture<>();
        metadata.completeAsync(() -> getFolderAndParse(uri));
        return metadata;
    }

    @Override
    public void setFileVisitor(final ProcessFile<T> fileVisitor) {
        this.yamlProcessFile = fileVisitor;
    }
}
