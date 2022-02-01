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
package io.syndesis.common.model.action;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Var;

import java.util.Objects;

/**
 * Immutable implementation of {@link io.syndesis.common.model.action.ConnectorDescriptor.StandardizedError}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new ConnectorDescriptor.StandardizedError.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableStandardizedError.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableStandardizedError
        implements ConnectorDescriptor.StandardizedError {
    private final String displayName;
    private final String name;

    private ImmutableStandardizedError(String displayName, String name) {
        this.displayName = displayName;
        this.name = name;
    }

    /**
     * Construct a new immutable {@code StandardizedError} instance.
     *
     * @param displayName The value for the {@code displayName} attribute
     * @param name        The value for the {@code name} attribute
     * @return An immutable StandardizedError instance
     */
    public static ConnectorDescriptor.StandardizedError of(String displayName,
                                                           String name) {
        return validate(new ImmutableStandardizedError(displayName, name));
    }

    private static ImmutableStandardizedError validate(
            ImmutableStandardizedError instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.action.ConnectorDescriptor.StandardizedError} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable StandardizedError instance
     */
    public static ConnectorDescriptor.StandardizedError copyOf(
            ConnectorDescriptor.StandardizedError instance) {
        if (instance instanceof ImmutableStandardizedError) {
            return (ImmutableStandardizedError) instance;
        }
        return new ConnectorDescriptor.StandardizedError.Builder()
                .createFrom(instance)
                .build();
    }

    /**
     * @return The value of the {@code displayName} attribute
     */
    @JsonProperty("displayName")
    @Override
    public String displayName() {
        return displayName;
    }

    /**
     * @return The value of the {@code name} attribute
     */
    @JsonProperty("name")
    @Override
    public String name() {
        return name;
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.action.ConnectorDescriptor.StandardizedError#displayName() displayName} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for displayName (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableStandardizedError withDisplayName(String value) {
        if (Objects.equals(this.displayName, value)) {
            return this;
        }
        return validate(new ImmutableStandardizedError(value, this.name));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.action.ConnectorDescriptor.StandardizedError#name() name} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for name (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableStandardizedError withName(String value) {
        if (Objects.equals(this.name, value)) {
            return this;
        }
        return validate(
                new ImmutableStandardizedError(this.displayName, value));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableStandardizedError} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableStandardizedError
                && equalTo((ImmutableStandardizedError) another);
    }

    private boolean equalTo(ImmutableStandardizedError another) {
        return Objects.equals(displayName, another.displayName)
                && Objects.equals(name, another.name);
    }

    /**
     * Computes a hash code from attributes: {@code displayName}, {@code name}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(displayName);
        h += (h << 5) + Objects.hashCode(name);
        return h;
    }

    /**
     * Prints the immutable value {@code StandardizedError} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        return "StandardizedError{"
                + "displayName=" + displayName
                + ", name=" + name
                + "}";
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.action.ConnectorDescriptor.StandardizedError StandardizedError}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private String displayName;
        private String name;

        /**
         * Creates a builder for {@link io.syndesis.common.model.action.ConnectorDescriptor.StandardizedError StandardizedError} instances.
         * <pre>
         * new ConnectorDescriptor.StandardizedError.Builder()
         *    .displayName(String | null) // nullable {@link io.syndesis.common.model.action.ConnectorDescriptor.StandardizedError#displayName() displayName}
         *    .name(String | null) // nullable {@link io.syndesis.common.model.action.ConnectorDescriptor.StandardizedError#name() name}
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof ConnectorDescriptor.StandardizedError.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new ConnectorDescriptor.StandardizedError.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code StandardizedError} instance.
         * Regular attribute values will be replaced with those from the given instance.
         * Absent optional values will not replace present values.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorDescriptor.StandardizedError.Builder createFrom(
                ConnectorDescriptor.StandardizedError instance) {
            Objects.requireNonNull(instance, "instance");
            String displayNameValue = instance.displayName();
            if (displayNameValue != null) {
                displayName(displayNameValue);
            }
            String nameValue = instance.name();
            if (nameValue != null) {
                name(nameValue);
            }
            return (ConnectorDescriptor.StandardizedError.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.action.ConnectorDescriptor.StandardizedError#displayName() displayName} attribute.
         *
         * @param displayName The value for displayName (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("displayName")
        public final ConnectorDescriptor.StandardizedError.Builder displayName(
                String displayName) {
            this.displayName = displayName;
            return (ConnectorDescriptor.StandardizedError.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.action.ConnectorDescriptor.StandardizedError#name() name} attribute.
         *
         * @param name The value for name (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("name")
        public final ConnectorDescriptor.StandardizedError.Builder name(
                String name) {
            this.name = name;
            return (ConnectorDescriptor.StandardizedError.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.action.ConnectorDescriptor.StandardizedError StandardizedError}.
         *
         * @return An immutable instance of StandardizedError
         * @throws IllegalStateException if any required attributes are missing
         */
        public ConnectorDescriptor.StandardizedError build() {
            return ImmutableStandardizedError.validate(
                    new ImmutableStandardizedError(displayName, name));
        }
    }
}
