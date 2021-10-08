package io.zimara.backend.metadata.parser;

import io.zimara.backend.metadata.ParseCatalog;
import io.zimara.backend.model.Metadata;
import org.apache.commons.io.IOUtils;
import org.jboss.logging.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * 🐱class JatParseCatalog
 * 🐱inherits ParseCatalog
 * Abstract implementation that downloads a jar file
 * and walks through all the files
 * parsing them and preparing elements to add to a catalog.
 */
public class JarParseCatalog<T extends Metadata>
        implements ParseCatalog<T> {

    private Logger log = Logger.getLogger(JarParseCatalog.class);

    private final CompletableFuture<List<T>> metadata =
            new CompletableFuture<>();

    private YamlProcessFile<T> yamlProcessFile;

    public JarParseCatalog(final String url) {
        log.trace("Warming up repository in " + url);
        metadata.completeAsync(() -> cloneRepoAndParse(url));
    }

    private List<T> cloneRepoAndParse(final String url) {
        List<T> metadataList =
                Collections.synchronizedList(new CopyOnWriteArrayList<>());
        final List<CompletableFuture<Void>> futureMd =
                Collections.synchronizedList(new CopyOnWriteArrayList<>());

        String location = url;

        //If it is a remote file, download it
        if (url.startsWith("http://") || url.startsWith("https://")) {
            try {
                Path tmp = Files.createTempFile("remote-", ".jar");
                try (FileOutputStream fos =
                             new FileOutputStream(tmp.toFile())) {
                    URL remote = new URL(url);
                    URLConnection connection = remote.openConnection();
                    IOUtils.copy(connection.getInputStream(), fos);
                    location = tmp.toFile().getAbsolutePath();
                }
            } catch (IOException e) {
                log.error("Error trying to create temporary file.", e);
            }
        }

        Path tmp = null;
        //Unzip and parse the files
        try (ZipInputStream zis =
                     new ZipInputStream(new FileInputStream(location))) {

            tmp = Files.createTempDirectory("remote-");
            ZipEntry zipEntry = zis.getNextEntry();

            while (zipEntry != null) {
                // Zip Slip vulnerability
                // https://snyk.io/research/zip-slip-vulnerability
                String canonicalDestinationDirPath =
                        tmp.toFile().getCanonicalPath();
                File destinationFile =
                        new File(tmp.toFile(), zipEntry.getName());
                String canonicalDestinationFile =
                        destinationFile.getCanonicalPath();
                if (!canonicalDestinationFile.startsWith(
                        canonicalDestinationDirPath + File.separator)) {
                    throw new IOException(
                            "Potential vulnerability: "
                                    + "entry outside of target dir: "
                                    + zipEntry.getName());
                }

                if (zipEntry.getName().endsWith(File.separator)) {
                    Files.createDirectories(destinationFile.toPath());
                } else {
                    //Make sure we have the path created
                    if (destinationFile.getParent() != null) {
                        if (Files.notExists(
                                destinationFile.toPath().getParent())) {
                            Files.createDirectories(
                                    destinationFile.toPath().getParent());
                        }
                    }

                    Files.copy(zis, destinationFile.toPath(),
                            StandardCopyOption.REPLACE_EXISTING);
                }

                zipEntry = zis.getNextEntry();
            }
        } catch (FileNotFoundException e) {
            log.error("No jar file found.", e);
        } catch (IOException e) {
            log.error("Error trying to parse catalog.", e);
        }

        //Walk the expanded directory
        try {
            log.trace("Parsing all files in the jar");
            this.yamlProcessFile.setFutureMetadata(futureMd);
            this.yamlProcessFile.setMetadataList(metadataList);
            Files.walkFileTree(tmp, this.yamlProcessFile);
            log.trace("Found " + futureMd.size() + " elements.");
            CompletableFuture.allOf(
                            futureMd.toArray(new CompletableFuture[0]))
                    .join();
        } catch (IOException e) {
            log.error("Error trying to parse catalog.", e);
        }

        return metadataList;
    }


    @Override
    public CompletableFuture<List<T>> parse() {
        return metadata;
    }

    public void setFileVisitor(final YamlProcessFile<T> fileVisitor) {
        this.yamlProcessFile = fileVisitor;
    }
}

