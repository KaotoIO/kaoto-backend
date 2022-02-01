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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Var;

import java.util.Objects;

/**
 * Immutable implementation of {@link io.syndesis.common.model.Dependency}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new Dependency.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableDependency.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableDependency implements Dependency {
    private final Type type;
    private final String id;

    private ImmutableDependency(Type type, String id) {
        this.type = type;
        this.id = id;
    }

    /**
     * Construct a new immutable {@code Dependency} instance.
     *
     * @param type The value for the {@code type} attribute
     * @param id   The value for the {@code id} attribute
     * @return An immutable Dependency instance
     */
    public static Dependency of(Type type, String id) {
        return validate(new ImmutableDependency(type, id));
    }

    private static ImmutableDependency validate(ImmutableDependency instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.Dependency} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable Dependency instance
     */
    public static Dependency copyOf(Dependency instance) {
        if (instance instanceof ImmutableDependency) {
            return (ImmutableDependency) instance;
        }
        return new Dependency.Builder()
                .createFrom(instance)
                .build();
    }

    /**
     * @return The value of the {@code type} attribute
     */
    @JsonProperty("type")
    @Override
    public Type getType() {
        return type;
    }

    /**
     * @return The value of the {@code id} attribute
     */
    @JsonProperty("id")
    @Override
    public String getId() {
        return id;
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.Dependency#getType() type} attribute.
     * A value equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for type (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableDependency withType(Type value) {
        if (this.type == value) {
            return this;
        }
        if (Objects.equals(this.type, value)) {
            return this;
        }
        return validate(new ImmutableDependency(value, this.id));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.Dependency#getId() id} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for id (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableDependency withId(String value) {
        if (Objects.equals(this.id, value)) {
            return this;
        }
        return validate(new ImmutableDependency(this.type, value));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableDependency} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableDependency
                && equalTo((ImmutableDependency) another);
    }

    private boolean equalTo(ImmutableDependency another) {
        return Objects.equals(type, another.type)
                && Objects.equals(id, another.id);
    }

    /**
     * Computes a hash code from attributes: {@code type}, {@code id}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(type);
        h += (h << 5) + Objects.hashCode(id);
        return h;
    }

    /**
     * Prints the immutable value {@code Dependency} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        return "Dependency{"
                + "type=" + type
                + ", id=" + id
                + "}";
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.Dependency Dependency}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private Dependency.Type type;
        private String id;

        /**
         * Creates a builder for {@link io.syndesis.common.model.Dependency Dependency} instances.
         * <pre>
         * new Dependency.Builder()
         *    .type(io.syndesis.common.io.syndesis.common.model.Dependency.Type | null) // nullable {@link io.syndesis.common.model.Dependency#getType() type}
         *    .id(String | null) // nullable {@link io.syndesis.common.model.Dependency#getId() id}
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof Dependency.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new Dependency.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code Dependency} instance.
         * Regular attribute values will be replaced with those from the given instance.
         * Absent optional values will not replace present values.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Dependency.Builder createFrom(Dependency instance) {
            Objects.requireNonNull(instance, "instance");
            Type typeValue = instance.getType();
            if (typeValue != null) {
                type(typeValue);
            }
            String idValue = instance.getId();
            if (idValue != null) {
                id(idValue);
            }
            return (Dependency.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.Dependency#getType() type} attribute.
         *
         * @param type The value for type (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("type")
        public final Dependency.Builder type(Type type) {
            this.type = type;
            return (Dependency.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.Dependency#getId() id} attribute.
         *
         * @param id The value for id (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("id")
        public final Dependency.Builder id(String id) {
            this.id = id;
            return (Dependency.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.Dependency Dependency}.
         *
         * @return An immutable instance of Dependency
         * @throws IllegalStateException if any required attributes are missing
         */
        public Dependency build() {
            return ImmutableDependency.validate(
                    new ImmutableDependency(type, id));
        }
    }
}
