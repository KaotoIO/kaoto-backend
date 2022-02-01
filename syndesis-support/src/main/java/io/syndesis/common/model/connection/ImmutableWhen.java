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

import java.util.Objects;

/**
 * Immutable implementation of {@link io.syndesis.common.model.connection.PropertyRelation.When}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new PropertyRelation.When.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableWhen.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableWhen implements PropertyRelation.When {
    private final String id;
    private final String value;

    private ImmutableWhen(String id, String value) {
        this.id = id;
        this.value = value;
    }

    /**
     * Construct a new immutable {@code When} instance.
     *
     * @param id    The value for the {@code id} attribute
     * @param value The value for the {@code value} attribute
     * @return An immutable When instance
     */
    public static PropertyRelation.When of(String id, String value) {
        return validate(new ImmutableWhen(id, value));
    }

    private static ImmutableWhen validate(ImmutableWhen instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.connection.PropertyRelation.When} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable When instance
     */
    public static PropertyRelation.When copyOf(PropertyRelation.When instance) {
        if (instance instanceof ImmutableWhen) {
            return (ImmutableWhen) instance;
        }
        return new PropertyRelation.When.Builder()
                .createFrom(instance)
                .build();
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
     * @return The value of the {@code value} attribute
     */
    @JsonProperty("value")
    @Override
    public String getValue() {
        return value;
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.PropertyRelation.When#getId() id} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for id (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableWhen withId(String value) {
        if (Objects.equals(this.id, value)) {
            return this;
        }
        return validate(new ImmutableWhen(value, this.value));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.PropertyRelation.When#getValue() value} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for value (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableWhen withValue(String value) {
        if (Objects.equals(this.value, value)) {
            return this;
        }
        return validate(new ImmutableWhen(this.id, value));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableWhen} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableWhen
                && equalTo((ImmutableWhen) another);
    }

    private boolean equalTo(ImmutableWhen another) {
        return Objects.equals(id, another.id)
                && Objects.equals(value, another.value);
    }

    /**
     * Computes a hash code from attributes: {@code id}, {@code value}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(id);
        h += (h << 5) + Objects.hashCode(value);
        return h;
    }

    /**
     * Prints the immutable value {@code When} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        return "When{"
                + "id=" + id
                + ", value=" + value
                + "}";
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.connection.PropertyRelation.When When}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private String id;
        private String value;

        /**
         * Creates a builder for {@link io.syndesis.common.model.connection.PropertyRelation.When When} instances.
         * <pre>
         * new PropertyRelation.When.Builder()
         *    .id(String | null) // nullable {@link io.syndesis.common.model.connection.PropertyRelation.When#getId() id}
         *    .value(String | null) // nullable {@link io.syndesis.common.model.connection.PropertyRelation.When#getValue() value}
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof PropertyRelation.When.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new PropertyRelation.When.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code When} instance.
         * Regular attribute values will be replaced with those from the given instance.
         * Absent optional values will not replace present values.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final PropertyRelation.When.Builder createFrom(
                PropertyRelation.When instance) {
            Objects.requireNonNull(instance, "instance");
            String idValue = instance.getId();
            if (idValue != null) {
                id(idValue);
            }
            String valueValue = instance.getValue();
            if (valueValue != null) {
                value(valueValue);
            }
            return (PropertyRelation.When.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.PropertyRelation.When#getId() id} attribute.
         *
         * @param id The value for id (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("id")
        public final PropertyRelation.When.Builder id(String id) {
            this.id = id;
            return (PropertyRelation.When.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.PropertyRelation.When#getValue() value} attribute.
         *
         * @param value The value for value (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("value")
        public final PropertyRelation.When.Builder value(String value) {
            this.value = value;
            return (PropertyRelation.When.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.connection.PropertyRelation.When When}.
         *
         * @return An immutable instance of When
         * @throws IllegalStateException if any required attributes are missing
         */
        public PropertyRelation.When build() {
            return ImmutableWhen.validate(new ImmutableWhen(id, value));
        }
    }
}
