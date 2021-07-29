package io.zimara.backend.api.service.parser;

import io.zimara.backend.model.step.Step;

import java.util.List;

public interface StepParserService<T extends Step> {

    List<T> parse(String yaml);

    String getIdentifier();

    boolean appliesTo(String yaml);

}
