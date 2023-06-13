package io.kaoto.backend.metadata.parser.step.kamelet;

import io.fabric8.kubernetes.client.KubernetesClient;
import io.kaoto.backend.api.metadata.catalog.StepCatalogParser;
import io.kaoto.backend.metadata.ParseCatalog;
import io.kaoto.backend.metadata.parser.ClusterParseCatalog;
import io.kaoto.backend.metadata.parser.GitParseCatalog;
import io.kaoto.backend.metadata.parser.JarParseCatalog;
import io.kaoto.backend.metadata.parser.LocalFolderParseCatalog;
import io.kaoto.backend.model.deployment.kamelet.Kamelet;
import io.kaoto.backend.model.step.Step;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.nio.file.Path;
import java.util.Arrays;

/**
 * 🐱class KameletParseCatalog
 * 🐱inherits StepCatalogParser
 *
 * 🐱relationship dependsOn ParseCatalog
 *
 * Reads and parses a kamelet catalog.
 * Extracts all the kamelet definitions it can find
 * and generate a kamelet step for each one.
 */
@ApplicationScoped
public final class KameletParseCatalog implements StepCatalogParser {

    @ConfigProperty(name = "kaoto.openshift.catalog-namespace",
            defaultValue = "false")
    private String namespace;

    @Inject
    public void setKubernetesClient(final KubernetesClient kubernetesClient) {
        this.kubernetesClient = kubernetesClient;
    }
    private KubernetesClient kubernetesClient;

    private KameletFileProcessor kameletFileProcessor = new KameletFileProcessor();

    @Override
    public ParseCatalog<Step> getParser(final String url, final String tag) {
        ParseCatalog<Step> parseCatalog = new GitParseCatalog<>(url, tag);
        parseCatalog.setFileVisitor(kameletFileProcessor);
        return parseCatalog;
    }

    @Override
    public ParseCatalog<Step> getParser(final String url) {
        ParseCatalog<Step> parseCatalog = new JarParseCatalog<>(url);
        parseCatalog.setFileVisitor(kameletFileProcessor);
        return parseCatalog;
    }

    @Override
    public ParseCatalog<Step> getParserFromCluster() {
        ClusterParseCatalog<Step> parseCatalog =
                new ClusterParseCatalog<>(Kamelet.class);
        parseCatalog.setFileVisitor(new KameletFileProcessor());
        parseCatalog.setKubernetesClient(kubernetesClient);
        parseCatalog.setNamespace(namespace);
        return parseCatalog;
    }

    @Override
    public ParseCatalog<Step> getLocalFolder(final Path path) {
        ParseCatalog<Step> parseCatalog =
                new LocalFolderParseCatalog<>(path);
        parseCatalog.setFileVisitor(kameletFileProcessor);
        return parseCatalog;
    }

    @Override
    public boolean generatesKind(final String kind) {
        String[] kinds = new String[]{"Kamelet", "EIP", "EIP-BRANCH"};
        return kind.isBlank() || Arrays.stream(kinds).anyMatch(k -> k.equalsIgnoreCase(kind));
    }
}
