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
package io.syndesis.common.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.Optional;

/**
 * A ChangeEvent is used to notify clients about changes
 * to rest API resources.
 */
@JsonDeserialize(builder = ChangeEvent.Builder.class)
public interface ChangeEvent extends ToJson, Serializable {

    static ChangeEvent of(String action, String kind, String id) {
        return new ChangeEvent.Builder().action(action).kind(kind).id(id)
                .build();
    }

    Optional<String> getAction();

    Optional<String> getKind();

    Optional<String> getId();

    class Builder extends ImmutableChangeEvent.Builder {
        // make ImmutableChangeEvent.Builder which is package private
        // accessible
    }

}
