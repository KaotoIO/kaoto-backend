package io.kaoto.backend.model.configuration;

import io.quarkus.runtime.annotations.RegisterForReflection;
import io.quarkus.runtime.annotations.StaticInitSafe;
import io.smallrye.common.constraint.Nullable;
import io.smallrye.config.WithDefault;
import io.smallrye.config.WithName;

import java.util.List;
import java.util.Optional;

@StaticInitSafe
@RegisterForReflection
public interface Repository {
    Optional<List<Location>> jar();
    Optional<List<Git>> git();
    Optional<List<Location>> localFolder();

    @RegisterForReflection
    interface Git {
        String url();
        @WithDefault("main")
        String tag();
        @WithDefault("false")
        @WithName("if-no-cluster")
        boolean ifNoCluster();
        @WithDefault("all")
        @Nullable
        String kind();
    }

    @RegisterForReflection
    interface Location {
        String url();
        @WithDefault("false")
        @WithName("if-no-cluster")
        boolean ifNoCluster();
        @WithDefault("all")
        @Nullable
        String kind();
    }

}
