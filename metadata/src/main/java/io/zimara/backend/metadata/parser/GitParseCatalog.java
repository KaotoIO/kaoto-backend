package io.zimara.backend.metadata.parser;

import io.zimara.backend.metadata.ParseCatalog;
import io.zimara.backend.model.Metadata;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.transport.TagOpt;
import org.jboss.logging.Logger;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 🐱class GitParseCatalog
 * 🐱inherits ParseCatalog
 * <p>
 * Abstract implementation that downloads a git repository and walks through all the files
 * parsing them and preparing elements to add to a catalog.
 */
public abstract class GitParseCatalog<T extends Metadata> implements ParseCatalog<T> {

    Logger log = Logger.getLogger(GitParseCatalog.class);

    private final CompletableFuture<List<T>> metadata = new CompletableFuture<>();


    protected GitParseCatalog(String url, String tag) {
        log.trace("Warming up repository in " + url);
        metadata.completeAsync(() -> cloneRepoAndParse(url, tag));
    }

    private List<T> cloneRepoAndParse(String url, String tag) {
        List<T> metadataList = Collections.synchronizedList(new CopyOnWriteArrayList<>());
        final List<CompletableFuture<T>> futureMetadatas = Collections.synchronizedList(new CopyOnWriteArrayList<>());

        try {
            log.trace("Creating temporary folder.");
            File file = Files.createTempDirectory("zimara-catalog").toFile();
            file.setExecutable(true, true);
            file.setReadable(true, true);
            file.setWritable(true, true);

            log.trace("Cloning git repository.");
            try (Git git = Git.cloneRepository()
                    .setCloneSubmodules(true)
                    .setURI(url)
                    .setDirectory(file)
                    .setBranch(tag)
                    .setTagOption(TagOpt.FETCH_TAGS)
                    .call()) {

                log.trace("Parsing all files in the repository");
                Files.walkFileTree(file.getAbsoluteFile().toPath(), getFileVisitor(metadataList, futureMetadatas));
                log.trace("Found " + futureMetadatas.size() + " elements.");
                CompletableFuture.allOf(futureMetadatas.toArray(new CompletableFuture[0])).join();

            } catch (InvalidRemoteException e) {
                log.error("Error trying to clone repository.", e);
            } catch (TransportException e) {
                log.error("Error trying to clone repository.", e);
            } catch (GitAPIException e) {
                log.error("Error trying to clone repository.", e);
            } catch (IOException | NullPointerException e) {
                log.error("Error trying to parse catalog.", e);
            } finally {
                FileUtils.deleteDirectory(file);
            }

        } catch (IOException e) {
            log.error("Error trying to create temporary directory.", e);
        }

        return metadataList;
    }

    @Override
    public CompletableFuture<List<T>> parse() {
        return metadata;
    }

    /*
     * 🐱method getFileVisitor : YamlProcessFile
     * 🐱param metadataList: List[Metadata]
     * 🐱param futureMetadata: List[CompletableFuture[]]
     *
     *
     * Returns the helper class based on YamlProcessFile to process the files found on the repository.
     * This needs to be implemented.
     *
     */
    protected abstract YamlProcessFile<T> getFileVisitor
    (List<T> metadataList, List<CompletableFuture<T>> futureMetadata);

}

