package io.kaoto.backend.metadata.parser;

import io.kaoto.backend.metadata.ParseCatalog;
import io.kaoto.backend.model.Metadata;
import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.TagOpt;
import org.jboss.logging.Logger;

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
 * 🐱relationship dependsOn YamlProcessFile
 * Abstract implementation that downloads a git repository
 * and walks through all the files
 * parsing them and preparing elements to add to a catalog.
 */
public class GitParseCatalog<T extends Metadata>
        implements ParseCatalog<T> {

    private Logger log = Logger.getLogger(GitParseCatalog.class);
    private YamlProcessFile<T> yamlProcessFile;
    private final String url;
    private final String tag;

    public GitParseCatalog(final String url, final String tag) {
        this.url = url;
        this.tag = tag;
    }

    private List<T> cloneRepoAndParse(final String url, final String tag) {
        log.trace("Warming up repository in " + url);
        List<T> metadataList =
                Collections.synchronizedList(new CopyOnWriteArrayList<>());
        final List<CompletableFuture<Void>> futureMd =
                Collections.synchronizedList(new CopyOnWriteArrayList<>());

        File file = null;
        try {
            log.trace("Creating temporary folder.");
            file = Files.createTempDirectory("kaoto-catalog").toFile();
            file.setExecutable(true, true);
            file.setReadable(true, true);
            file.setWritable(true, true);
        } catch (IOException e) {
            log.error("Error trying to create temporary directory.", e);
        }

        if (file != null) {
            log.trace("Cloning git repository.");
            try (Git git = Git.cloneRepository()
                    .setCloneSubmodules(true)
                    .setURI(url)
                    .setDirectory(file)
                    .setBranch(tag)
                    .setTagOption(TagOpt.FETCH_TAGS)
                    .call()) {

                log.trace("Parsing all files in the repository");
                this.yamlProcessFile.setFutureMetadata(futureMd);
                this.yamlProcessFile.setMetadataList(metadataList);
                Files.walkFileTree(file.getAbsoluteFile().toPath(),
                        this.yamlProcessFile);
                log.trace("Found " + futureMd.size() + " elements.");
                CompletableFuture.allOf(
                        futureMd.toArray(new CompletableFuture[0]))
                        .join();

            } catch (GitAPIException e) {
                log.error("Error trying to clone repository.", e);
            } catch (IOException e) {
                log.error("Error trying to parse catalog.", e);
            }

            try {
                FileUtils.deleteDirectory(file);
            } catch (IOException e) {
                log.error("Error cleaning up catalog.", e);
            }
        }

        return metadataList;
    }

    @Override
    public CompletableFuture<List<T>> parse() {
        CompletableFuture<List<T>> metadata = new CompletableFuture<>();
        metadata.completeAsync(() -> cloneRepoAndParse(url, tag));
        return metadata;
    }

    public void setFileVisitor(final YamlProcessFile<T> fileVisitor) {
        this.yamlProcessFile = fileVisitor;
    }
}
