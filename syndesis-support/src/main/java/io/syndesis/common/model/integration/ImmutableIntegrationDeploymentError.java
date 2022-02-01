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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Var;

import java.util.Objects;

/**
 * Immutable implementation of {@link io.syndesis.common.model.integration.IntegrationDeploymentError}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new IntegrationDeploymentError.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableIntegrationDeploymentError.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableIntegrationDeploymentError
        implements IntegrationDeploymentError {
    private final String type;
    private final String message;

    private ImmutableIntegrationDeploymentError(String type, String message) {
        this.type = type;
        this.message = message;
    }

    /**
     * Construct a new immutable {@code IntegrationDeploymentError} instance.
     *
     * @param type    The value for the {@code type} attribute
     * @param message The value for the {@code message} attribute
     * @return An immutable IntegrationDeploymentError instance
     */
    public static IntegrationDeploymentError of(String type, String message) {
        return validate(new ImmutableIntegrationDeploymentError(type, message));
    }

    private static ImmutableIntegrationDeploymentError validate(
            ImmutableIntegrationDeploymentError instance) {

        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.integration.IntegrationDeploymentError} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable IntegrationDeploymentError instance
     */
    public static IntegrationDeploymentError copyOf(
            IntegrationDeploymentError instance) {
        if (instance instanceof ImmutableIntegrationDeploymentError) {
            return (ImmutableIntegrationDeploymentError) instance;
        }
        return new IntegrationDeploymentError.Builder()
                .createFrom(instance)
                .build();
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
     * @return The value of the {@code message} attribute
     */
    @JsonProperty("message")
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.integration.IntegrationDeploymentError#getType() type} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for type (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableIntegrationDeploymentError withType(String value) {
        if (Objects.equals(this.type, value)) {
            return this;
        }
        return validate(
                new ImmutableIntegrationDeploymentError(value, this.message));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.integration.IntegrationDeploymentError#getMessage() message} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for message (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableIntegrationDeploymentError withMessage(String value) {
        if (Objects.equals(this.message, value)) {
            return this;
        }
        return validate(
                new ImmutableIntegrationDeploymentError(this.type, value));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableIntegrationDeploymentError} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableIntegrationDeploymentError
                && equalTo((ImmutableIntegrationDeploymentError) another);
    }

    private boolean equalTo(ImmutableIntegrationDeploymentError another) {
        return Objects.equals(type, another.type)
                && Objects.equals(message, another.message);
    }

    /**
     * Computes a hash code from attributes: {@code type}, {@code message}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(type);
        h += (h << 5) + Objects.hashCode(message);
        return h;
    }

    /**
     * Prints the immutable value {@code IntegrationDeploymentError} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        return "IntegrationDeploymentError{"
                + "type=" + type
                + ", message=" + message
                + "}";
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.integration.IntegrationDeploymentError IntegrationDeploymentError}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private String type;
        private String message;

        /**
         * Creates a builder for {@link io.syndesis.common.model.integration.IntegrationDeploymentError IntegrationDeploymentError} instances.
         * <pre>
         * new IntegrationDeploymentError.Builder()
         *    .type(String | null) // nullable {@link io.syndesis.common.model.integration.IntegrationDeploymentError#getType() type}
         *    .message(String | null) // nullable {@link io.syndesis.common.model.integration.IntegrationDeploymentError#getMessage() message}
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof IntegrationDeploymentError.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new IntegrationDeploymentError.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code IntegrationDeploymentError} instance.
         * Regular attribute values will be replaced with those from the given instance.
         * Absent optional values will not replace present values.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationDeploymentError.Builder createFrom(
                IntegrationDeploymentError instance) {
            Objects.requireNonNull(instance, "instance");
            String typeValue = instance.getType();
            if (typeValue != null) {
                type(typeValue);
            }
            String messageValue = instance.getMessage();
            if (messageValue != null) {
                message(messageValue);
            }
            return (IntegrationDeploymentError.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.integration.IntegrationDeploymentError#getType() type} attribute.
         *
         * @param type The value for type (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("type")
        public final IntegrationDeploymentError.Builder type(String type) {
            this.type = type;
            return (IntegrationDeploymentError.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.integration.IntegrationDeploymentError#getMessage() message} attribute.
         *
         * @param message The value for message (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("message")
        public final IntegrationDeploymentError.Builder message(
                String message) {
            this.message = message;
            return (IntegrationDeploymentError.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.integration.IntegrationDeploymentError IntegrationDeploymentError}.
         *
         * @return An immutable instance of IntegrationDeploymentError
         * @throws IllegalStateException if any required attributes are missing
         */
        public IntegrationDeploymentError build() {
            return ImmutableIntegrationDeploymentError.validate(
                    new ImmutableIntegrationDeploymentError(type, message));
        }
    }
}
