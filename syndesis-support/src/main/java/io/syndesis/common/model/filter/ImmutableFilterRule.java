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
package io.syndesis.common.model.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Var;
import io.syndesis.common.model.expression.RuleBase;

import java.util.Objects;

/**
 * Immutable implementation of {@link io.syndesis.common.model.filter.FilterRule}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new FilterRule.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableFilterRule.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableFilterRule implements FilterRule {
    private final String path;
    private final String op;
    private final String value;

    private ImmutableFilterRule(String path, String op, String value) {
        this.path = path;
        this.op = op;
        this.value = value;
    }

    /**
     * Construct a new immutable {@code FilterRule} instance.
     *
     * @param path  The value for the {@code path} attribute
     * @param op    The value for the {@code op} attribute
     * @param value The value for the {@code value} attribute
     * @return An immutable FilterRule instance
     */
    public static FilterRule of(String path, String op, String value) {
        return validate(new ImmutableFilterRule(path, op, value));
    }

    private static ImmutableFilterRule validate(ImmutableFilterRule instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.filter.FilterRule} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable FilterRule instance
     */
    public static FilterRule copyOf(FilterRule instance) {
        if (instance instanceof ImmutableFilterRule) {
            return (ImmutableFilterRule) instance;
        }
        return new FilterRule.Builder()
                .createFrom(instance)
                .build();
    }

    /**
     * @return The value of the {@code path} attribute
     */
    @JsonProperty("path")
    @Override
    public String getPath() {
        return path;
    }

    /**
     * @return The value of the {@code op} attribute
     */
    @JsonProperty("op")
    @Override
    public String getOp() {
        return op;
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
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.filter.FilterRule#getPath() path} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for path (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableFilterRule withPath(String value) {
        if (Objects.equals(this.path, value)) {
            return this;
        }
        return validate(new ImmutableFilterRule(value, this.op, this.value));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.filter.FilterRule#getOp() op} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for op (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableFilterRule withOp(String value) {
        if (Objects.equals(this.op, value)) {
            return this;
        }
        return validate(new ImmutableFilterRule(this.path, value, this.value));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.filter.FilterRule#getValue() value} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for value (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableFilterRule withValue(String value) {
        if (Objects.equals(this.value, value)) {
            return this;
        }
        return validate(new ImmutableFilterRule(this.path, this.op, value));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableFilterRule} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableFilterRule
                && equalTo((ImmutableFilterRule) another);
    }

    private boolean equalTo(ImmutableFilterRule another) {
        return Objects.equals(path, another.path)
                && Objects.equals(op, another.op)
                && Objects.equals(value, another.value);
    }

    /**
     * Computes a hash code from attributes: {@code path}, {@code op}, {@code value}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(path);
        h += (h << 5) + Objects.hashCode(op);
        h += (h << 5) + Objects.hashCode(value);
        return h;
    }

    /**
     * Prints the immutable value {@code FilterRule} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        return "FilterRule{"
                + "path=" + path
                + ", op=" + op
                + ", value=" + value
                + "}";
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.filter.FilterRule FilterRule}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private String path;
        private String op;
        private String value;

        /**
         * Creates a builder for {@link io.syndesis.common.model.filter.FilterRule FilterRule} instances.
         * <pre>
         * new FilterRule.Builder()
         *    .path(String | null) // nullable {@link io.syndesis.common.model.filter.FilterRule#getPath() path}
         *    .op(String | null) // nullable {@link io.syndesis.common.model.filter.FilterRule#getOp() op}
         *    .value(String | null) // nullable {@link io.syndesis.common.model.filter.FilterRule#getValue() value}
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof FilterRule.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new FilterRule.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.expression.RuleBase} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final FilterRule.Builder createFrom(RuleBase instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (FilterRule.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.filter.FilterRule} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final FilterRule.Builder createFrom(FilterRule instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (FilterRule.Builder) this;
        }

        private void from(Object object) {
            if (object instanceof RuleBase) {
                RuleBase instance = (RuleBase) object;
                String pathValue = instance.getPath();
                if (pathValue != null) {
                    path(pathValue);
                }
                String opValue = instance.getOp();
                if (opValue != null) {
                    op(opValue);
                }
                String valueValue = instance.getValue();
                if (valueValue != null) {
                    value(valueValue);
                }
            }
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.filter.FilterRule#getPath() path} attribute.
         *
         * @param path The value for path (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("path")
        public final FilterRule.Builder path(String path) {
            this.path = path;
            return (FilterRule.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.filter.FilterRule#getOp() op} attribute.
         *
         * @param op The value for op (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("op")
        public final FilterRule.Builder op(String op) {
            this.op = op;
            return (FilterRule.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.filter.FilterRule#getValue() value} attribute.
         *
         * @param value The value for value (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("value")
        public final FilterRule.Builder value(String value) {
            this.value = value;
            return (FilterRule.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.filter.FilterRule FilterRule}.
         *
         * @return An immutable instance of FilterRule
         * @throws IllegalStateException if any required attributes are missing
         */
        public FilterRule build() {
            return ImmutableFilterRule.validate(
                    new ImmutableFilterRule(path, op, value));
        }
    }
}
