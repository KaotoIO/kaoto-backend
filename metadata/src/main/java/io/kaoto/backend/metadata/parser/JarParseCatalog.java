package io.kaoto.backend.metadata.parser;

import io.kaoto.backend.metadata.ParseCatalog;
import io.kaoto.backend.model.Metadata;
import org.apache.commons.io.FileUtils;
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
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * 🐱class JarParseCatalog
 * 🐱inherits ParseCatalog
 * Abstract implementation that downloads a jar file
 * and walks through all the files
 * parsing them and preparing elements to add to a catalog.
 */
public class JarParseCatalog<T extends Metadata>
        implements ParseCatalog<T> {

    private Logger log = Logger.getLogger(JarParseCatalog.class);

    private final String url;

    private ProcessFile<T> processFile;

    //to avoid bomb attacks
    private int thresholdSize = 1000000000; // 1 GB

    public JarParseCatalog(final String url) {
        this.url = url;
    }

    private List<T> getJarAndParse(final String url) {
        log.trace("Warming up repository in " + url);
        List<T> metadataList =
                Collections.synchronizedList(new CopyOnWriteArrayList<>());
        final List<CompletableFuture<Void>> futureMd =
                Collections.synchronizedList(new CopyOnWriteArrayList<>());

        String location = downloadIfRemote(url);

        Path tmp = null;
        long totalSize = 0;
        try (ZipInputStream zis =
                     new ZipInputStream(new FileInputStream(location))) {
            FileAttribute<Set<PosixFilePermission>> attr =
                    PosixFilePermissions.asFileAttribute(
                            PosixFilePermissions.fromString("rwx------"));
            tmp = Files.createTempDirectory("kaoto-zip-", attr);
            tmp.toFile().deleteOnExit();

            //Unzip the files
            ZipEntry zipEntry = zis.getNextEntry();

            while (zipEntry != null) {
                totalSize += processExtractedFile(tmp, zis, zipEntry);

                if (totalSize > thresholdSize) {
                    throw new IOException(
                            "This jar file unzipped is too big.");
                }

                zipEntry = zis.getNextEntry();
            }

            //Walk the expanded directory
            log.trace("Parsing all files in the jar");
            this.processFile.setFutureMetadata(futureMd);
            this.processFile.setMetadataList(metadataList);
            Files.walkFileTree(tmp, this.processFile);
            log.trace("Found " + futureMd.size() + " elements.");
            CompletableFuture.allOf(
                            futureMd.toArray(new CompletableFuture[0]))
                    .join();
        } catch (FileNotFoundException e) {
            log.error("No jar file found.", e);
        } catch (IOException e) {
            log.error("Error trying to parse catalog.", e);
        }

        try {
            if (tmp != null) {
                FileUtils.deleteDirectory(tmp.toFile());
            }
            if (!location.equalsIgnoreCase(url) && !url.startsWith("resource:/")) {
                FileUtils.deleteQuietly(new File(location));
            }
        } catch (IOException e) {
            log.error("Error cleaning up catalog.", e);
        }

        return metadataList;
    }

    private long processExtractedFile(final Path tmp,
                                      final ZipInputStream zis,
                                      final ZipEntry zipEntry)
            throws IOException {
        File destinationFile = getDestinationFile(tmp, zipEntry);

        if (zipEntry.getName().endsWith(File.separator)) {
            Files.createDirectories(destinationFile.toPath());
        } else {
            //Make sure we have the path created
            if (destinationFile.getParent() != null
                    && Files.notExists(destinationFile
                    .toPath().getParent())) {
                Files.createDirectories(
                        destinationFile.toPath().getParent());
            }

            Files.copy(zis, destinationFile.toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
        }
        destinationFile.deleteOnExit();
        return Files.size(tmp);
    }

    // Zip Slip vulnerability
    // https://snyk.io/research/zip-slip-vulnerability
    private File getDestinationFile(final Path tmp,
                                    final ZipEntry zipEntry)
            throws IOException {
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
        return destinationFile;
    }

    //If it is a remote file, download it
    private String downloadIfRemote(final String url) {
        String location = url;

        if (url.startsWith("http://") || url.startsWith("https://")) {
            try {
                FileAttribute<Set<PosixFilePermission>> attr =
                        PosixFilePermissions.asFileAttribute(PosixFilePermissions.fromString("rwx------"));
                Path tmp = Files.createTempFile("remote-", ".jar", attr);
                try (FileOutputStream fos = new FileOutputStream(tmp.toFile())) {
                    URL remote = new URL(url);
                    URLConnection connection = remote.openConnection();
                    IOUtils.copy(connection.getInputStream(), fos);
                    location = tmp.toFile().getAbsolutePath();
                    tmp.toFile().deleteOnExit();
                }
            } catch (IOException e) {
                log.error("Error trying to create temporary file.", e);
            }
        } else if (location.startsWith("resource://")) {
            try {
                location = location.substring(10);
                location = this.getClass().getResource(location).toURI().getPath();
            } catch (Exception e) {
                location = url;
                log.error("We had issues accessing " + location);
            }
        }

        return location;
    }


    @Override
    public CompletableFuture<List<T>> parse() {
        CompletableFuture<List<T>> metadata = new CompletableFuture<>();
        metadata.completeAsync(() -> getJarAndParse(url));
        return metadata;
    }

    public void setFileVisitor(final ProcessFile<T> fileVisitor) {
        this.processFile = fileVisitor;
    }
}
