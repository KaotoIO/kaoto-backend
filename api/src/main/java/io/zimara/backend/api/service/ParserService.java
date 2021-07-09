package io.zimara.backend.api.service;

import io.zimara.backend.model.Step;

import java.util.List;

public interface ParserService {

    List<Step> parse(String yaml);

    String getIdentifier();

    boolean appliesTo(String yaml);

}
