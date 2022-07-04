package io.kaoto.backend.model.configuration;

import io.smallrye.config.WithDefault;

import java.util.List;
import java.util.Optional;

public interface Repository {
    Optional<List<Location>> jar();
    Optional<List<Git>> git();
    Optional<List<Location>> localFolder();

    interface Git {
        String url();
        @WithDefault("main")
        String tag();
        @WithDefault("false")
        Boolean ifNoCluster();
    }

    interface Location {
        String url();
        @WithDefault("false")
        Boolean whennocluster();
    }

}
