package io.kaoto.backend.metadata.parser;

import io.kaoto.backend.model.Metadata;

import java.io.File;

public abstract class JsonProcessFile<T extends Metadata>
        extends ProcessFile<T> {

    @Override
    protected boolean isDesiredType(final File file) {
        return file.getName().endsWith(".json")
                && !file.getName().startsWith(".");
    }
}
