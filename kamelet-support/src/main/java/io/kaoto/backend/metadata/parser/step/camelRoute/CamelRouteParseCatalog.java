package io.kaoto.backend.metadata.parser.step.camelRoute;

import io.fabric8.kubernetes.client.KubernetesClient;
import io.kaoto.backend.api.metadata.catalog.StepCatalogParser;
import io.kaoto.backend.metadata.ParseCatalog;
import io.kaoto.backend.metadata.parser.ClusterParseCatalog;
import io.kaoto.backend.metadata.parser.GitParseCatalog;
import io.kaoto.backend.metadata.parser.JarParseCatalog;
import io.kaoto.backend.metadata.parser.LocalFolderParseCatalog;
import io.kaoto.backend.model.deployment.kamelet.Kamelet;
import io.kaoto.backend.model.step.Step;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.nio.file.Path;

@ApplicationScoped
public final class CamelRouteParseCatalog implements StepCatalogParser {

    @Inject
    public void setKubernetesClient(final KubernetesClient kubernetesClient) {
        this.kubernetesClient = kubernetesClient;
    }
    private KubernetesClient kubernetesClient;

    private CamelRouteParseCatalog() {

    }

    @Override
    public ParseCatalog<Step> getParser(final String url, final String tag) {
        ParseCatalog<Step> parseCatalog = new GitParseCatalog<>(url, tag);
        parseCatalog.setFileVisitor(new CamelRouteFileProcessor());
        return parseCatalog;
    }


    @Override
    public ParseCatalog<Step> getParser(final String url) {

        ParseCatalog<Step> parseCatalog = new JarParseCatalog<>(url);
        parseCatalog.setFileVisitor(new CamelRouteFileProcessor());
        return parseCatalog;
    }

    @Override
    public ParseCatalog<Step> getParserFromCluster() {
        //This wont work, maybe
        return new ClusterParseCatalog<>(Kamelet.class);
//        ClusterParseCatalog<Step> parseCatalog =
//                new ClusterParseCatalog<>(Kamelet.class);
//        parseCatalog.setFileVisitor(new CamelRouteFileProcessor());
//        parseCatalog.setKubernetesClient(kubernetesClient);
//        return parseCatalog;
    }

    @Override
    public ParseCatalog<Step> getLocalFolder(final Path path) {
        ParseCatalog<Step> parseCatalog =
                new LocalFolderParseCatalog<>(path);
        parseCatalog.setFileVisitor(new CamelRouteFileProcessor());
        return parseCatalog;
    }
}
