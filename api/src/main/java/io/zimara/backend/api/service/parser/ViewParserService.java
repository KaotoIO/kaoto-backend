package io.zimara.backend.api.service.parser;

import io.zimara.backend.model.step.Step;
import io.zimara.backend.model.view.ViewDefinition;

import java.util.List;

public interface ViewParserService<T extends ViewDefinition> {

    List<T> parse(List<Step> steps, String id);

    String getIdentifier();

    boolean appliesTo(List<Step> steps, ViewDefinition viewDefinition);

}
