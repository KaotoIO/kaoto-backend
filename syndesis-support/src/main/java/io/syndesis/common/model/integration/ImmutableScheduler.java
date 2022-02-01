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
 * Immutable implementation of {@link io.syndesis.common.model.integration.Scheduler}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new Scheduler.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableScheduler.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableScheduler implements Scheduler {
    private final Type type;
    private final String expression;

    private ImmutableScheduler(Type type, String expression) {
        this.type = Objects.requireNonNull(type, "type");
        this.expression = expression;
    }

    private ImmutableScheduler(Builder builder) {
        this.expression = builder.expression;
        this.type = builder.type != null
                ? builder.type
                : Objects.requireNonNull(Scheduler.super.getType(), "type");
    }

    private ImmutableScheduler(
            ImmutableScheduler original,
            Type type,
            String expression) {
        this.type = type;
        this.expression = expression;
    }

    /**
     * Construct a new immutable {@code Scheduler} instance.
     *
     * @param type       The value for the {@code type} attribute
     * @param expression The value for the {@code expression} attribute
     * @return An immutable Scheduler instance
     */
    public static Scheduler of(Type type, String expression) {
        return validate(new ImmutableScheduler(type, expression));
    }

    private static ImmutableScheduler validate(ImmutableScheduler instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.integration.Scheduler} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable Scheduler instance
     */
    public static Scheduler copyOf(Scheduler instance) {
        if (instance instanceof ImmutableScheduler) {
            return (ImmutableScheduler) instance;
        }
        return new Scheduler.Builder()
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
     * @return The value of the {@code expression} attribute
     */
    @JsonProperty("expression")
    @Override
    public String getExpression() {
        return expression;
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.integration.Scheduler#getType() type} attribute.
     * A value equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for type
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableScheduler withType(Type value) {
        if (this.type == value) {
            return this;
        }
        Type newValue = Objects.requireNonNull(value, "type");
        if (this.type.equals(newValue)) {
            return this;
        }
        return validate(
                new ImmutableScheduler(this, newValue, this.expression));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.integration.Scheduler#getExpression() expression} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for expression (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableScheduler withExpression(String value) {
        if (Objects.equals(this.expression, value)) {
            return this;
        }
        return validate(new ImmutableScheduler(this, this.type, value));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableScheduler} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableScheduler
                && equalTo((ImmutableScheduler) another);
    }

    private boolean equalTo(ImmutableScheduler another) {
        return type.equals(another.type)
                && Objects.equals(expression, another.expression);
    }

    /**
     * Computes a hash code from attributes: {@code type}, {@code expression}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + type.hashCode();
        h += (h << 5) + Objects.hashCode(expression);
        return h;
    }

    /**
     * Prints the immutable value {@code Scheduler} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        return "Scheduler{"
                + "type=" + type
                + ", expression=" + expression
                + "}";
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.integration.Scheduler Scheduler}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private Scheduler.Type type;
        private String expression;

        /**
         * Creates a builder for {@link io.syndesis.common.model.integration.Scheduler Scheduler} instances.
         * <pre>
         * new Scheduler.Builder()
         *    .type(io.syndesis.common.io.syndesis.common.model.integration.Scheduler.Type) // optional {@link io.syndesis.common.model.integration.Scheduler#getType() type}
         *    .expression(String | null) // nullable {@link io.syndesis.common.model.integration.Scheduler#getExpression() expression}
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof Scheduler.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new Scheduler.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code Scheduler} instance.
         * Regular attribute values will be replaced with those from the given instance.
         * Absent optional values will not replace present values.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Scheduler.Builder createFrom(Scheduler instance) {
            Objects.requireNonNull(instance, "instance");
            type(instance.getType());
            String expressionValue = instance.getExpression();
            if (expressionValue != null) {
                expression(expressionValue);
            }
            return (Scheduler.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.integration.Scheduler#getType() type} attribute.
         * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link io.syndesis.common.model.integration.Scheduler#getType() type}.</em>
         *
         * @param type The value for type
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("type")
        public final Scheduler.Builder type(Type type) {
            this.type = Objects.requireNonNull(type, "type");
            return (Scheduler.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.integration.Scheduler#getExpression() expression} attribute.
         *
         * @param expression The value for expression (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("expression")
        public final Scheduler.Builder expression(String expression) {
            this.expression = expression;
            return (Scheduler.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.integration.Scheduler Scheduler}.
         *
         * @return An immutable instance of Scheduler
         * @throws IllegalStateException if any required attributes are missing
         */
        public Scheduler build() {
            return ImmutableScheduler.validate(new ImmutableScheduler(this));
        }
    }
}
