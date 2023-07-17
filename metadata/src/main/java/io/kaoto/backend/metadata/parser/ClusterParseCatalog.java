package io.kaoto.backend.metadata.parser;

import java.io.StringReader;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

import org.jboss.logging.Logger;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.kaoto.backend.metadata.ParseCatalog;
import io.kaoto.backend.model.Metadata;

/**
 * 🐱class ClusterParseCatalog
 *
 * 🐱inherits ParseCatalog
 *
 * Abstract implementation that extracts CustomResources from a cluster and parses them to add to a catalog.
 */
public class ClusterParseCatalog<T extends Metadata> implements ParseCatalog<T> {

    private final Class<? extends CustomResource> cr;
    private static final Logger LOG = Logger.getLogger(ClusterParseCatalog.class);

    private String namespace;

    public void setNamespace(final String namespace) {
        this.namespace = namespace;
    }

    private KubernetesClient kubernetesClient;

    public void setKubernetesClient(final KubernetesClient kubernetesClient) {
        this.kubernetesClient = kubernetesClient;
    }

    private ProcessFile<T> yamlProcessFile;

    public ClusterParseCatalog(final Class<? extends CustomResource> cr) {
        this.cr = cr;
    }

    private List<T> getCRAndParse(final Class<? extends CustomResource> cr) {
        LOG.trace("Warming up repository from cluster.");

        List<T> metadataList = Collections.synchronizedList(new CopyOnWriteArrayList<>());
        final List<CompletableFuture<Void>> futureMd = Collections.synchronizedList(new CopyOnWriteArrayList<>());

        try {
            final List<? extends CustomResource> resources;

            var time = System.currentTimeMillis();
            //if the backend is deployed cluster-wide
            if ("false".equals(namespace) || "".equals(namespace)) {
                resources = kubernetesClient.resources(cr).inAnyNamespace().list().getItems();
            } else {
                resources = kubernetesClient.resources(cr).inNamespace(namespace).list().getItems();
            }
            LOG.info("Retrieved resources  in " + (System.currentTimeMillis() - time) + "ms.");

            //For each custom resource, let's process it
            resources.stream().parallel().forEach(resource ->
                    //Add the data to the future asynchronously
                    futureMd.add(CompletableFuture.runAsync(() ->
                    {
                        try {
                            metadataList.addAll(this.yamlProcessFile.parseInputStream(
                                    // Yaml is not thread-safe, so it needs to be initialized here
                                    new StringReader(new Yaml(new Constructor(cr,
                                            new LoaderOptions())).dumpAsMap(resource))));
                        } catch (Throwable t) {
                            LOG.trace("Couldn't parse the resource.", t);
                        }
                    }))
            );

        } catch (Exception e) {
            LOG.error("Error retrieving elements from cluster.", e);
        }

        this.yamlProcessFile.setFutureMetadata(futureMd);
        this.yamlProcessFile.setMetadataList(metadataList);
        LOG.trace("Found " + futureMd.size() + " elements.");
        CompletableFuture.allOf(futureMd.toArray(new CompletableFuture[0])).join();

        return metadataList;
    }

    @Override
    public CompletableFuture<List<T>> parse() {
        CompletableFuture<List<T>> metadata = new CompletableFuture<>();
        metadata.completeAsync(() -> getCRAndParse(cr));
        return metadata;
    }

    @Override
    public void setFileVisitor(final ProcessFile<T> fileVisitor) {
        this.yamlProcessFile = fileVisitor;
    }
}
