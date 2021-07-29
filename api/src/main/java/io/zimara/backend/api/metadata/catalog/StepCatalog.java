package io.zimara.backend.api.metadata.catalog;

import io.zimara.backend.metadata.ParseCatalog;
import io.zimara.backend.metadata.parser.step.KameletParseCatalog;
import io.zimara.backend.model.step.Step;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class StepCatalog extends AbstractCatalog<Step> {

    @Override
    protected List<ParseCatalog<Step>> loadParsers() {
        List<ParseCatalog<Step>> catalogs = new ArrayList<>();
        catalogs.add(new KameletParseCatalog("https://github.com/apache/camel-kamelets.git", "v0.3.0"));
        return catalogs;
    }

}
