package io.zimara.backend.api.service.parser;

import io.zimara.backend.model.Metadata;

import java.util.List;

public interface ParserService<T extends Metadata> {

    List<T> parse(String yaml);

    String getIdentifier();

    boolean appliesTo(String yaml);

}
