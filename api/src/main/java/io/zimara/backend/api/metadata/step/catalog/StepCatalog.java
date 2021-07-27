package io.zimara.backend.api.metadata.step.catalog;

import io.zimara.backend.metadata.MetadataCatalog;
import io.zimara.backend.metadata.ParseCatalog;
import io.zimara.backend.metadata.catalog.InMemoryCatalog;
import io.zimara.backend.metadata.catalog.ReadOnlyCatalog;
import io.zimara.backend.metadata.parser.step.kamelet.KameletParseCatalog;
import io.zimara.backend.model.step.Step;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@ApplicationScoped
public class StepCatalog {

    public StepCatalog() {
        warmUpCatalog(loadParsers());
    }

    private static InMemoryCatalog<Step> c = new InMemoryCatalog<>();
    private static final MetadataCatalog<Step> readOnlyCatalog = new ReadOnlyCatalog<>(c);
    private static Boolean warmedUp = false;
    private static Logger log = Logger.getLogger(StepCatalog.class);
    private static CompletableFuture<Void> waitingForWarmUp;

    public MetadataCatalog<Step> getReadOnlyCatalog() {
        if(warmedUp) {
            return readOnlyCatalog;
        }
        else throw new RuntimeException("Catalog still warming up.");
    }

    public Boolean isWarmedUp() {
        return warmedUp;
    }

    public CompletableFuture<Void> waitForWarmUp() {
        return waitingForWarmUp;
    }

    /**
     * This may be autowired by jandex?
     *
     * @return
     */
    private List<ParseCatalog<Step>> loadParsers() {
        List<ParseCatalog<Step>> catalogs = new ArrayList<>();
        catalogs.add(new KameletParseCatalog("https://github.com/apache/camel-kamelets.git", "v0.3.0"));
        return catalogs;
    }

    /**
     * Add all steps from the parsers into the catalog
     * @param catalogs
     */
    private void warmUpCatalog(List<ParseCatalog<Step>> catalogs) {
        log.debug("Warming up catalog.");
        List<CompletableFuture<Boolean>> futureSteps = new ArrayList<>();

        for(var catalog : catalogs) {
            futureSteps.add(addCatalog(catalog));
        }

        waitingForWarmUp = CompletableFuture.allOf(futureSteps.toArray(new CompletableFuture[0]));
        waitingForWarmUp.thenAccept(complete -> warmedUp = true)
                        .thenRun(() -> log.debug("Catalog warmed up."));
    }

    private static CompletableFuture<Boolean> addCatalog(ParseCatalog<Step> catalog) {
        CompletableFuture<Boolean> res = new CompletableFuture<>();
        catalog.parse()
                .thenApply(steps -> c.store(steps))
                .thenAccept(steps -> res.complete(true));
        return res;
    }
}
