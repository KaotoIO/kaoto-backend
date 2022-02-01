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
 * Immutable implementation of {@link io.syndesis.common.model.Violation}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new Violation.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableViolation.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableViolation implements Violation {
    private final String error;
    private final String message;
    private final String property;

    private ImmutableViolation(String error, String message, String property) {
        this.error = error;
        this.message = message;
        this.property = property;
    }

    /**
     * Construct a new immutable {@code Violation} instance.
     *
     * @param error    The value for the {@code error} attribute
     * @param message  The value for the {@code message} attribute
     * @param property The value for the {@code property} attribute
     * @return An immutable Violation instance
     */
    public static Violation of(String error, String message, String property) {
        return validate(new ImmutableViolation(error, message, property));
    }

    private static ImmutableViolation validate(ImmutableViolation instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.Violation} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable Violation instance
     */
    public static Violation copyOf(Violation instance) {
        if (instance instanceof ImmutableViolation) {
            return (ImmutableViolation) instance;
        }
        return new Violation.Builder()
                .createFrom(instance)
                .build();
    }

    /**
     * @return The value of the {@code error} attribute
     */
    @JsonProperty("error")
    @Override
    public String error() {
        return error;
    }

    /**
     * @return The value of the {@code message} attribute
     */
    @JsonProperty("message")
    @Override
    public String message() {
        return message;
    }

    /**
     * @return The value of the {@code property} attribute
     */
    @JsonProperty("property")
    @Override
    public String property() {
        return property;
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.Violation#error() error} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for error (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableViolation withError(String value) {
        if (Objects.equals(this.error, value)) {
            return this;
        }
        return validate(
                new ImmutableViolation(value, this.message, this.property));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.Violation#message() message} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for message (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableViolation withMessage(String value) {
        if (Objects.equals(this.message, value)) {
            return this;
        }
        return validate(
                new ImmutableViolation(this.error, value, this.property));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.Violation#property() property} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for property (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableViolation withProperty(String value) {
        if (Objects.equals(this.property, value)) {
            return this;
        }
        return validate(
                new ImmutableViolation(this.error, this.message, value));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableViolation} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableViolation
                && equalTo((ImmutableViolation) another);
    }

    private boolean equalTo(ImmutableViolation another) {
        return Objects.equals(error, another.error)
                && Objects.equals(message, another.message)
                && Objects.equals(property, another.property);
    }

    /**
     * Computes a hash code from attributes: {@code error}, {@code message}, {@code property}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(error);
        h += (h << 5) + Objects.hashCode(message);
        h += (h << 5) + Objects.hashCode(property);
        return h;
    }

    /**
     * Prints the immutable value {@code Violation} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        return "Violation{"
                + "error=" + error
                + ", message=" + message
                + ", property=" + property
                + "}";
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.Violation Violation}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private String error;
        private String message;
        private String property;

        /**
         * Creates a builder for {@link io.syndesis.common.model.Violation Violation} instances.
         * <pre>
         * new Violation.Builder()
         *    .error(String | null) // nullable {@link io.syndesis.common.model.Violation#error() error}
         *    .message(String | null) // nullable {@link io.syndesis.common.model.Violation#message() message}
         *    .property(String | null) // nullable {@link io.syndesis.common.model.Violation#property() property}
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof Violation.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new Violation.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code Violation} instance.
         * Regular attribute values will be replaced with those from the given instance.
         * Absent optional values will not replace present values.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Violation.Builder createFrom(Violation instance) {
            Objects.requireNonNull(instance, "instance");
            String errorValue = instance.error();
            if (errorValue != null) {
                error(errorValue);
            }
            String messageValue = instance.message();
            if (messageValue != null) {
                message(messageValue);
            }
            String propertyValue = instance.property();
            if (propertyValue != null) {
                property(propertyValue);
            }
            return (Violation.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.Violation#error() error} attribute.
         *
         * @param error The value for error (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("error")
        public final Violation.Builder error(String error) {
            this.error = error;
            return (Violation.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.Violation#message() message} attribute.
         *
         * @param message The value for message (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("message")
        public final Violation.Builder message(String message) {
            this.message = message;
            return (Violation.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.Violation#property() property} attribute.
         *
         * @param property The value for property (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("property")
        public final Violation.Builder property(String property) {
            this.property = property;
            return (Violation.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.Violation Violation}.
         *
         * @return An immutable instance of Violation
         * @throws IllegalStateException if any required attributes are missing
         */
        public Violation build() {
            return ImmutableViolation.validate(
                    new ImmutableViolation(error, message, property));
        }
    }
}
