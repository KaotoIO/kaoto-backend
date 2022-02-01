/*
 * Copyright (C) 2016 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.syndesis.common.model.integration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.syndesis.common.model.Dependency;
import io.syndesis.common.model.ToJson;
import io.syndesis.common.model.WithDependencies;
import io.syndesis.common.model.WithEnvironment;
import io.syndesis.common.model.WithId;
import io.syndesis.common.model.WithLabels;
import io.syndesis.common.model.WithModificationTimestamps;
import io.syndesis.common.model.WithName;
import io.syndesis.common.model.WithProperties;
import io.syndesis.common.model.WithResourceId;
import io.syndesis.common.model.WithResources;
import io.syndesis.common.model.WithTags;
import io.syndesis.common.model.WithVersion;
import io.syndesis.common.model.action.ConnectorAction;
import io.syndesis.common.model.action.ConnectorDescriptor;
import io.syndesis.common.model.connection.Connection;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface IntegrationBase
        extends WithProperties, WithResourceId, WithVersion,
        WithModificationTimestamps, WithTags, WithName, WithSteps, ToJson,
        WithResources, WithDependencies, WithLabels, WithEnvironment {

    default Optional<Connection> findConnectionById(final String connectionId) {
        if (getConnections() == null) {
            return Optional.empty();
        }

        return getConnections().stream()
                .filter(WithId.class::isInstance)
                .filter(connection -> connection.getId().isPresent())
                .filter(connection -> connectionId.equals(
                        connection.getId().get()))
                .findFirst();
    }

    default Optional<Flow> findFlowBy(final Predicate<Flow> p) {
        return getFlows().stream()
                .filter(p)
                .findFirst();
    }

    default List<Connection> getConnections() {
        return Collections.emptyList();
    }

    /**
     * Map of target environment ids and continuous delivery states. Names are
     * created/deleted on the fly in the UI (since it's just a string). Managed
     * by release tag service and used by CD export and import service.
     */
    default Map<String, ContinuousDeliveryEnvironment> getContinuousDeliveryState() {
        return Collections.emptyMap();
    }

    Optional<String> getDescription();

    Optional<String> getExposure();

    default List<Flow> getFlows() {
        return Collections.emptyList();
    }

}
