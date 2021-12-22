package io.kaoto.backend.api.metadata.catalog;

import io.kaoto.backend.metadata.ParseCatalog;
import io.kaoto.backend.model.step.Step;

public interface StepCatalogParser {
    ParseCatalog<Step> getParser(String url);

    ParseCatalog<Step> getParser(String url, String tag);
}
