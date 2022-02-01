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
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.syndesis.common.model.WithDependencies;
import io.syndesis.common.model.WithId;
import io.syndesis.common.model.WithMetadata;
import io.syndesis.common.model.WithName;
import io.syndesis.common.model.WithTags;
import io.syndesis.common.model.connection.Connection;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@JsonDeserialize(builder = Flow.Builder.class)
@SuppressWarnings("immutables")
public interface Flow
        extends WithName, WithTags, WithSteps, WithMetadata,
        WithDependencies, Serializable {

    default FlowType getType() {
        return FlowType.PRIMARY;
    }

    default List<Connection> getConnections() {
        return Collections.emptyList();
    }

    Scheduler getScheduler();

    String getDescription();

    default Flow.Builder builder() {
        return new Flow.Builder().createFrom(this);
    }

    enum FlowType {
        PRIMARY,
        API_PROVIDER,
        ALTERNATE
    }

    class Builder extends ImmutableFlow.Builder {
        // allow access to ImmutableIntegration.Builder

        public Builder putMetadata(FlowMetadata metadata, String value) {
            this.putMetadata(metadata.getKey(), value);
            return this;
        }
    }
}
