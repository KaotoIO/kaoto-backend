package io.kaoto.backend.metadata.parser;

import io.kaoto.backend.model.Metadata;

public abstract class YamlProcessFile<T extends Metadata>
        extends ProcessFile<T> {
    protected boolean isDesiredType(final String filename) {
        return filename != null && !filename.isEmpty()
                && (filename.endsWith(".yml") || filename.endsWith(".yaml"))
                && !filename.startsWith(".");
    }
}
