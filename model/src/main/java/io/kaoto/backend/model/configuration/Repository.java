package io.kaoto.backend.model.configuration;

import java.util.List;
import java.util.Optional;

public interface Repository {
    Optional<List<String>> jar();
    Optional<List<Git>> git();
    Optional<List<String>> localFolder();

    interface Git {
        String url();
        String tag();
    }

}
