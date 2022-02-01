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
 * Immutable implementation of {@link io.syndesis.common.model.connection.ConfigurationProperty.ArrayDefinitionElement}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new ConfigurationProperty.ArrayDefinitionElement.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableArrayDefinitionElement.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableArrayDefinitionElement
        implements ConfigurationProperty.ArrayDefinitionElement {
    private final String displayName;
    private final String type;

    private ImmutableArrayDefinitionElement(String displayName, String type) {
        this.displayName = displayName;
        this.type = type;
    }

    /**
     * Construct a new immutable {@code ArrayDefinitionElement} instance.
     *
     * @param displayName The value for the {@code displayName} attribute
     * @param type        The value for the {@code type} attribute
     * @return An immutable ArrayDefinitionElement instance
     */
    public static ConfigurationProperty.ArrayDefinitionElement of(
            String displayName, String type) {
        return validate(new ImmutableArrayDefinitionElement(displayName, type));
    }

    private static ImmutableArrayDefinitionElement validate(
            ImmutableArrayDefinitionElement instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.connection.ConfigurationProperty.ArrayDefinitionElement} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable ArrayDefinitionElement instance
     */
    public static ConfigurationProperty.ArrayDefinitionElement copyOf(
            ConfigurationProperty.ArrayDefinitionElement instance) {
        if (instance instanceof ImmutableArrayDefinitionElement) {
            return (ImmutableArrayDefinitionElement) instance;
        }
        return new ConfigurationProperty.ArrayDefinitionElement.Builder()
                .createFrom(instance)
                .build();
    }

    /**
     * @return The value of the {@code displayName} attribute
     */
    @JsonProperty("displayName")
    @Override
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @return The value of the {@code type} attribute
     */
    @JsonProperty("type")
    @Override
    public String getType() {
        return type;
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConfigurationProperty.ArrayDefinitionElement#getDisplayName() displayName} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for displayName (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableArrayDefinitionElement withDisplayName(String value) {
        if (Objects.equals(this.displayName, value)) {
            return this;
        }
        return validate(new ImmutableArrayDefinitionElement(value, this.type));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConfigurationProperty.ArrayDefinitionElement#getType() type} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for type (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableArrayDefinitionElement withType(String value) {
        if (Objects.equals(this.type, value)) {
            return this;
        }
        return validate(
                new ImmutableArrayDefinitionElement(this.displayName, value));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableArrayDefinitionElement} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableArrayDefinitionElement
                && equalTo((ImmutableArrayDefinitionElement) another);
    }

    private boolean equalTo(ImmutableArrayDefinitionElement another) {
        return Objects.equals(displayName, another.displayName)
                && Objects.equals(type, another.type);
    }

    /**
     * Computes a hash code from attributes: {@code displayName}, {@code type}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(displayName);
        h += (h << 5) + Objects.hashCode(type);
        return h;
    }

    /**
     * Prints the immutable value {@code ArrayDefinitionElement} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        return "ArrayDefinitionElement{"
                + "displayName=" + displayName
                + ", type=" + type
                + "}";
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.connection.ConfigurationProperty.ArrayDefinitionElement ArrayDefinitionElement}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private String displayName;
        private String type;

        /**
         * Creates a builder for {@link io.syndesis.common.model.connection.ConfigurationProperty.ArrayDefinitionElement ArrayDefinitionElement} instances.
         * <pre>
         * new ConfigurationProperty.ArrayDefinitionElement.Builder()
         *    .displayName(String | null) // nullable {@link io.syndesis.common.model.connection.ConfigurationProperty.ArrayDefinitionElement#getDisplayName() displayName}
         *    .type(String | null) // nullable {@link io.syndesis.common.model.connection.ConfigurationProperty.ArrayDefinitionElement#getType() type}
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof ConfigurationProperty.ArrayDefinitionElement.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new ConfigurationProperty.ArrayDefinitionElement.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code ArrayDefinitionElement} instance.
         * Regular attribute values will be replaced with those from the given instance.
         * Absent optional values will not replace present values.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConfigurationProperty.ArrayDefinitionElement.Builder createFrom(
                ConfigurationProperty.ArrayDefinitionElement instance) {
            Objects.requireNonNull(instance, "instance");
            String displayNameValue = instance.getDisplayName();
            if (displayNameValue != null) {
                displayName(displayNameValue);
            }
            String typeValue = instance.getType();
            if (typeValue != null) {
                type(typeValue);
            }
            return (ConfigurationProperty.ArrayDefinitionElement.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConfigurationProperty.ArrayDefinitionElement#getDisplayName() displayName} attribute.
         *
         * @param displayName The value for displayName (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("displayName")
        public final ConfigurationProperty.ArrayDefinitionElement.Builder displayName(
                String displayName) {
            this.displayName = displayName;
            return (ConfigurationProperty.ArrayDefinitionElement.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConfigurationProperty.ArrayDefinitionElement#getType() type} attribute.
         *
         * @param type The value for type (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("type")
        public final ConfigurationProperty.ArrayDefinitionElement.Builder type(
                String type) {
            this.type = type;
            return (ConfigurationProperty.ArrayDefinitionElement.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.connection.ConfigurationProperty.ArrayDefinitionElement ArrayDefinitionElement}.
         *
         * @return An immutable instance of ArrayDefinitionElement
         * @throws IllegalStateException if any required attributes are missing
         */
        public ConfigurationProperty.ArrayDefinitionElement build() {
            return ImmutableArrayDefinitionElement.validate(
                    new ImmutableArrayDefinitionElement(displayName, type));
        }
    }
}
