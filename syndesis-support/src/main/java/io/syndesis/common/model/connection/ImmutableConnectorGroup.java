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
package io.syndesis.common.model.connection;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Var;
import io.syndesis.common.model.WithName;
import io.syndesis.common.model.WithResourceId;

import java.io.ObjectStreamException;
import java.util.Objects;
import java.util.Optional;

/**
 * Immutable implementation of {@link io.syndesis.common.model.connection.ConnectorGroup}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new ConnectorGroup.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableConnectorGroup.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableConnectorGroup implements ConnectorGroup {
    private final String id;
    private final String name;

    private ImmutableConnectorGroup(Optional<String> id, String name) {
        this.id = id.orElse(null);
        this.name = name;
    }

    private ImmutableConnectorGroup(
            ImmutableConnectorGroup original,
            String id,
            String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Construct a new immutable {@code ConnectorGroup} instance.
     *
     * @param id   The value for the {@code id} attribute
     * @param name The value for the {@code name} attribute
     * @return An immutable ConnectorGroup instance
     */
    public static ConnectorGroup of(Optional<String> id, String name) {
        return validate(new ImmutableConnectorGroup(id, name));
    }

    private static ImmutableConnectorGroup validate(
            ImmutableConnectorGroup instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.connection.ConnectorGroup} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable ConnectorGroup instance
     */
    public static ConnectorGroup copyOf(ConnectorGroup instance) {
        if (instance instanceof ImmutableConnectorGroup) {
            return (ImmutableConnectorGroup) instance;
        }
        return new ConnectorGroup.Builder()
                .createFrom(instance)
                .build();
    }

    /**
     * @return The value of the {@code id} attribute
     */
    @JsonProperty("id")
    @Override
    public Optional<String> getId() {
        return Optional.ofNullable(id);
    }

    /**
     * @return The value of the {@code name} attribute
     */
    @JsonProperty("name")
    @Override
    public String getName() {
        return name;
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.connection.ConnectorGroup#getId() id} attribute.
     *
     * @param value The value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnectorGroup withId(String value) {
        String newValue = Objects.requireNonNull(value, "id");
        if (Objects.equals(this.id, newValue)) {
            return this;
        }
        return validate(new ImmutableConnectorGroup(this, newValue, this.name));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.connection.ConnectorGroup#getId() id} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnectorGroup withId(Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.id, value)) {
            return this;
        }
        return validate(new ImmutableConnectorGroup(this, value, this.name));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConnectorGroup#getName() name} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for name (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConnectorGroup withName(String value) {
        if (Objects.equals(this.name, value)) {
            return this;
        }
        return validate(new ImmutableConnectorGroup(this, this.id, value));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableConnectorGroup} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableConnectorGroup
                && equalTo((ImmutableConnectorGroup) another);
    }

    private boolean equalTo(ImmutableConnectorGroup another) {
        return Objects.equals(id, another.id)
                && Objects.equals(name, another.name);
    }

    /**
     * Computes a hash code from attributes: {@code id}, {@code name}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(id);
        h += (h << 5) + Objects.hashCode(name);
        return h;
    }

    /**
     * Prints the immutable value {@code ConnectorGroup} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("ConnectorGroup{");
        if (id != null) {
            builder.append("id=").append(id);
        }
        if (name != null) {
            if (builder.length() > 15) {
                builder.append(", ");
            }
            builder.append("name=").append(name);
        }
        return builder.append("}").toString();
    }

    private Object readResolve() throws ObjectStreamException {
        return validate(this);
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.connection.ConnectorGroup ConnectorGroup}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private String id;
        private String name;

        /**
         * Creates a builder for {@link io.syndesis.common.model.connection.ConnectorGroup ConnectorGroup} instances.
         * <pre>
         * new ConnectorGroup.Builder()
         *    .id(String) // optional {@link io.syndesis.common.model.connection.ConnectorGroup#getId() id}
         *    .name(String | null) // nullable {@link io.syndesis.common.model.connection.ConnectorGroup#getName() name}
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof ConnectorGroup.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new ConnectorGroup.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithName} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorGroup.Builder createFrom(WithName instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (ConnectorGroup.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithResourceId} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorGroup.Builder createFrom(
                WithResourceId instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (ConnectorGroup.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.connection.ConnectorGroup} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorGroup.Builder createFrom(
                ConnectorGroup instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (ConnectorGroup.Builder) this;
        }

        private void from(Object object) {
            if (object instanceof WithName) {
                WithName instance = (WithName) object;
                String nameValue = instance.getName();
                if (nameValue != null) {
                    name(nameValue);
                }
            }
            if (object instanceof WithResourceId) {
                WithResourceId instance = (WithResourceId) object;
                Optional<String> idOptional = instance.getId();
                if (idOptional.isPresent()) {
                    id(idOptional);
                }
            }
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.ConnectorGroup#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorGroup.Builder id(String id) {
            this.id = Objects.requireNonNull(id, "id");
            return (ConnectorGroup.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.ConnectorGroup#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("id")
        public final ConnectorGroup.Builder id(Optional<String> id) {
            this.id = id.orElse(null);
            return (ConnectorGroup.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConnectorGroup#getName() name} attribute.
         *
         * @param name The value for name (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("name")
        public final ConnectorGroup.Builder name(String name) {
            this.name = name;
            return (ConnectorGroup.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.connection.ConnectorGroup ConnectorGroup}.
         *
         * @return An immutable instance of ConnectorGroup
         * @throws IllegalStateException if any required attributes are missing
         */
        public ConnectorGroup build() {
            return ImmutableConnectorGroup.validate(
                    new ImmutableConnectorGroup(null, id, name));
        }
    }
}
