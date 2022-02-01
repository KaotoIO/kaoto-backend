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
 * Immutable implementation of {@link io.syndesis.common.model.connection.ConfigurationProperty.ArrayDefinition}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new ConfigurationProperty.ArrayDefinition.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableArrayDefinition.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableArrayDefinition
        implements ConfigurationProperty.ArrayDefinition {
    private final ConfigurationProperty.ArrayDefinitionElement key;
    private final ConfigurationProperty.ArrayDefinitionElement value;

    private ImmutableArrayDefinition(
            ConfigurationProperty.ArrayDefinitionElement key,
            ConfigurationProperty.ArrayDefinitionElement value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Construct a new immutable {@code ArrayDefinition} instance.
     *
     * @param key   The value for the {@code key} attribute
     * @param value The value for the {@code value} attribute
     * @return An immutable ArrayDefinition instance
     */
    public static ConfigurationProperty.ArrayDefinition of(
            ConfigurationProperty.ArrayDefinitionElement key,
            ConfigurationProperty.ArrayDefinitionElement value) {
        return validate(new ImmutableArrayDefinition(key, value));
    }

    private static ImmutableArrayDefinition validate(
            ImmutableArrayDefinition instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.connection.ConfigurationProperty.ArrayDefinition} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable ArrayDefinition instance
     */
    public static ConfigurationProperty.ArrayDefinition copyOf(
            ConfigurationProperty.ArrayDefinition instance) {
        if (instance instanceof ImmutableArrayDefinition) {
            return (ImmutableArrayDefinition) instance;
        }
        return new ConfigurationProperty.ArrayDefinition.Builder()
                .createFrom(instance)
                .build();
    }

    /**
     * @return The value of the {@code key} attribute
     */
    @JsonProperty("key")
    @Override
    public ConfigurationProperty.ArrayDefinitionElement getKey() {
        return key;
    }

    /**
     * @return The value of the {@code value} attribute
     */
    @JsonProperty("value")
    @Override
    public ConfigurationProperty.ArrayDefinitionElement getValue() {
        return value;
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConfigurationProperty.ArrayDefinition#getKey() key} attribute.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for key (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableArrayDefinition withKey(
            ConfigurationProperty.ArrayDefinitionElement value) {
        if (this.key == value) {
            return this;
        }
        return validate(new ImmutableArrayDefinition(value, this.value));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConfigurationProperty.ArrayDefinition#getValue() value} attribute.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for value (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableArrayDefinition withValue(
            ConfigurationProperty.ArrayDefinitionElement value) {
        if (this.value == value) {
            return this;
        }
        return validate(new ImmutableArrayDefinition(this.key, value));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableArrayDefinition} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableArrayDefinition
                && equalTo((ImmutableArrayDefinition) another);
    }

    private boolean equalTo(ImmutableArrayDefinition another) {
        return Objects.equals(key, another.key)
                && Objects.equals(value, another.value);
    }

    /**
     * Computes a hash code from attributes: {@code key}, {@code value}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(key);
        h += (h << 5) + Objects.hashCode(value);
        return h;
    }

    /**
     * Prints the immutable value {@code ArrayDefinition} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        return "ArrayDefinition{"
                + "key=" + key
                + ", value=" + value
                + "}";
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.connection.ConfigurationProperty.ArrayDefinition ArrayDefinition}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private ConfigurationProperty.ArrayDefinitionElement key;
        private ConfigurationProperty.ArrayDefinitionElement value;

        /**
         * Creates a builder for {@link io.syndesis.common.model.connection.ConfigurationProperty.ArrayDefinition ArrayDefinition} instances.
         * <pre>
         * new ConfigurationProperty.ArrayDefinition.Builder()
         *    .key(io.syndesis.common.io.syndesis.common.model.connection.ConfigurationProperty.ArrayDefinitionElement | null) // nullable {@link io.syndesis.common.model.connection.ConfigurationProperty.ArrayDefinition#getKey() key}
         *    .value(io.syndesis.common.io.syndesis.common.model.connection.ConfigurationProperty.ArrayDefinitionElement | null) // nullable {@link io.syndesis.common.model.connection.ConfigurationProperty.ArrayDefinition#getValue() value}
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof ConfigurationProperty.ArrayDefinition.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new ConfigurationProperty.ArrayDefinition.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code ArrayDefinition} instance.
         * Regular attribute values will be replaced with those from the given instance.
         * Absent optional values will not replace present values.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConfigurationProperty.ArrayDefinition.Builder createFrom(
                ConfigurationProperty.ArrayDefinition instance) {
            Objects.requireNonNull(instance, "instance");
            ConfigurationProperty.ArrayDefinitionElement keyValue =
                    instance.getKey();
            if (keyValue != null) {
                key(keyValue);
            }
            ConfigurationProperty.ArrayDefinitionElement valueValue =
                    instance.getValue();
            if (valueValue != null) {
                value(valueValue);
            }
            return (ConfigurationProperty.ArrayDefinition.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConfigurationProperty.ArrayDefinition#getKey() key} attribute.
         *
         * @param key The value for key (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("key")
        public final ConfigurationProperty.ArrayDefinition.Builder key(
                ConfigurationProperty.ArrayDefinitionElement key) {
            this.key = key;
            return (ConfigurationProperty.ArrayDefinition.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConfigurationProperty.ArrayDefinition#getValue() value} attribute.
         *
         * @param value The value for value (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("value")
        public final ConfigurationProperty.ArrayDefinition.Builder value(
                ConfigurationProperty.ArrayDefinitionElement value) {
            this.value = value;
            return (ConfigurationProperty.ArrayDefinition.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.connection.ConfigurationProperty.ArrayDefinition ArrayDefinition}.
         *
         * @return An immutable instance of ArrayDefinition
         * @throws IllegalStateException if any required attributes are missing
         */
        public ConfigurationProperty.ArrayDefinition build() {
            return ImmutableArrayDefinition.validate(
                    new ImmutableArrayDefinition(key, value));
        }
    }
}
