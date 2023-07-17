package io.kaoto.backend.metadata.parser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.IOUtils;
import org.jboss.logging.Logger;

import io.kaoto.backend.metadata.ParseCatalog;
import io.kaoto.backend.model.Metadata;

/**
 * 🐱class JarParseCatalog
 * 🐱inherits ParseCatalog
 * Abstract implementation that downloads a jar file
 * and walks through all the files
 * parsing them and preparing elements to add to a catalog.
 */
public class JarParseCatalog<T extends Metadata>
        implements ParseCatalog<T> {

    private static final Logger LOG = Logger.getLogger(JarParseCatalog.class);

    private final String url;

    private ProcessFile<T> processFile;

    //to avoid bomb attacks
    private int thresholdSize = 1000000000; // 1 GB

    public JarParseCatalog(final String url) {
        this.url = url;
    }

    private List<T> getJarAndParse(final String url) {
        LOG.trace("Warming up repository in " + url);
        List<T> metadataList = Collections.synchronizedList(new CopyOnWriteArrayList<>());
        final List<CompletableFuture<Void>> futureMd = Collections.synchronizedList(new CopyOnWriteArrayList<>());
        this.processFile.setFutureMetadata(futureMd);
        this.processFile.setMetadataList(metadataList);

        long totalSize = 0;
        try (ZipInputStream zis = new ZipInputStream(getInputStream(url))) {

            //Unzip the files
            ZipEntry zipEntry = zis.getNextEntry();

            while (zipEntry != null) {
                totalSize += processExtractedFile(zis, zipEntry, futureMd, metadataList);

                if (totalSize > thresholdSize) {
                    throw new IOException("This jar file unzipped is too big.");
                }

                zipEntry = zis.getNextEntry();
            }

            LOG.trace("Found " + futureMd.size() + " elements.");
            CompletableFuture.allOf(futureMd.toArray(new CompletableFuture[0])).join();
        } catch (FileNotFoundException e) {
            LOG.error("No jar file found.", e);
        } catch (Exception e) {
            LOG.error("Error trying to parse catalog.", e);
        }

        return metadataList;
    }

    private long processExtractedFile(final ZipInputStream zis, final ZipEntry zipEntry,
                                      final List<CompletableFuture<Void>> futureMd, final List<T> metadataList)
            throws IOException {
        long size = zipEntry.getSize();
        if (!zipEntry.isDirectory() && this.processFile.isDesiredType(zipEntry.getName())) {
            final InputStreamReader isr = new InputStreamReader(zis);
            final String content = IOUtils.toString(isr);
            size = content.length();
            final StringReader sr = new StringReader(content);
            CompletableFuture<Void> metadata =
                    CompletableFuture.runAsync(() -> metadataList.addAll(this.processFile.parseInputStream(sr)));
            metadata.thenRun(() -> LOG.trace(zipEntry.getName() + " parsed, now generating metadata."));
            futureMd.add(metadata);
        }
        return size;
    }

    //If it is a remote file, download it
    private InputStream getInputStream(final String url) {
        InputStream res = null;

        if (url.startsWith("http://") || url.startsWith("https://")) {
            try {
                URL remote = new URL(url);
                URLConnection connection = remote.openConnection();
                res = connection.getInputStream();
            } catch (IOException e) {
                LOG.error("Error trying to access remote file.", e);
            }
        } else if (url.startsWith("resource://")) {
            try {
                res = this.getClass().getResourceAsStream(url.substring(10));
            } catch (Exception e) {
                LOG.error("We had issues accessing " + url);
            }
        } else {
            try {
                res = this.getClass().getResourceAsStream(url);
            } catch (Exception e) {
                LOG.error("We had issues accessing " + url);
            }
        }

        return res;
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
