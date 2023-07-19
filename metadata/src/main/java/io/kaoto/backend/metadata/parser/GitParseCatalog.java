package io.kaoto.backend.metadata.parser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.TagOpt;
import org.jboss.logging.Logger;

import io.kaoto.backend.metadata.ParseCatalog;
import io.kaoto.backend.model.Metadata;

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

    private static final Logger LOG = Logger.getLogger(GitParseCatalog.class);
    private final String url;
    private final String tag;

    private ProcessFile<T> processFile;

    public GitParseCatalog(final String url, final String tag) {
        this.url = url;
        this.tag = tag;
    }

    private List<T> cloneRepoAndParse(final String url, final String tag) {
        LOG.trace("Warming up repository in " + url);
        List<T> metadataList =
                Collections.synchronizedList(new CopyOnWriteArrayList<>());
        final List<CompletableFuture<Void>> futureMd =
                Collections.synchronizedList(new CopyOnWriteArrayList<>());

        File file = null;
        try {
            LOG.trace("Creating temporary folder.");
            file = Files.createTempDirectory("kaoto-git-").toFile();
            file.setExecutable(true, true);
            file.setReadable(true, true);
            file.setWritable(true, true);
        } catch (IOException e) {
            LOG.error("Error trying to create temporary directory.", e);
        }

        if (file != null) {
            LOG.trace("Cloning git repository.");
            try (Git git = Git.cloneRepository()
                    .setCloneSubmodules(true)
                    .setURI(url)
                    .setDirectory(file)
                    .setBranch(tag)
                    .setTagOption(TagOpt.FETCH_TAGS)
                    .call()) {

                LOG.trace("Parsing all files in the repository");
                this.processFile.setFutureMetadata(futureMd);
                this.processFile.setMetadataList(metadataList);
                Files.walkFileTree(file.getAbsoluteFile().toPath(),
                        this.processFile);
                LOG.trace("Found " + futureMd.size() + " elements.");
                CompletableFuture.allOf(
                        futureMd.toArray(new CompletableFuture[0]))
                        .join();

            } catch (GitAPIException e) {
                LOG.error("Error trying to clone repository.", e);
            } catch (IOException e) {
                LOG.error("Error trying to parse catalog.", e);
            }

            try {
                FileUtils.deleteDirectory(file);
            } catch (IOException e) {
                LOG.error("Error cleaning up catalog.", e);
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

    public void setFileVisitor(final ProcessFile<T> fileVisitor) {
        this.processFile = fileVisitor;
    }
}
