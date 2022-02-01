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
 * Immutable implementation of {@link io.syndesis.common.model.connection.WithDynamicProperties.ActionPropertySuggestion}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new WithDynamicProperties.ActionPropertySuggestion.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableActionPropertySuggestion.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableActionPropertySuggestion
        implements WithDynamicProperties.ActionPropertySuggestion {
    private final String displayValue;
    private final String value;

    private ImmutableActionPropertySuggestion(String displayValue,
                                              String value) {
        this.displayValue = displayValue;
        this.value = value;
    }

    /**
     * Construct a new immutable {@code ActionPropertySuggestion} instance.
     *
     * @param displayValue The value for the {@code displayValue} attribute
     * @param value        The value for the {@code value} attribute
     * @return An immutable ActionPropertySuggestion instance
     */
    public static WithDynamicProperties.ActionPropertySuggestion of(
            String displayValue, String value) {
        return validate(
                new ImmutableActionPropertySuggestion(displayValue, value));
    }

    private static ImmutableActionPropertySuggestion validate(
            ImmutableActionPropertySuggestion instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.connection.WithDynamicProperties.ActionPropertySuggestion} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable ActionPropertySuggestion instance
     */
    public static WithDynamicProperties.ActionPropertySuggestion copyOf(
            WithDynamicProperties.ActionPropertySuggestion instance) {
        if (instance instanceof ImmutableActionPropertySuggestion) {
            return (ImmutableActionPropertySuggestion) instance;
        }
        return new WithDynamicProperties.ActionPropertySuggestion.Builder()
                .createFrom(instance)
                .build();
    }

    /**
     * @return The value of the {@code displayValue} attribute
     */
    @JsonProperty("displayValue")
    @Override
    public String displayValue() {
        return displayValue;
    }

    /**
     * @return The value of the {@code value} attribute
     */
    @JsonProperty("value")
    @Override
    public String value() {
        return value;
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.WithDynamicProperties.ActionPropertySuggestion#displayValue() displayValue} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for displayValue (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableActionPropertySuggestion withDisplayValue(
            String value) {
        if (Objects.equals(this.displayValue, value)) {
            return this;
        }
        return validate(
                new ImmutableActionPropertySuggestion(value, this.value));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.WithDynamicProperties.ActionPropertySuggestion#value() value} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for value (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableActionPropertySuggestion withValue(String value) {
        if (Objects.equals(this.value, value)) {
            return this;
        }
        return validate(new ImmutableActionPropertySuggestion(this.displayValue,
                value));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableActionPropertySuggestion} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableActionPropertySuggestion
                && equalTo((ImmutableActionPropertySuggestion) another);
    }

    private boolean equalTo(ImmutableActionPropertySuggestion another) {
        return Objects.equals(displayValue, another.displayValue)
                && Objects.equals(value, another.value);
    }

    /**
     * Computes a hash code from attributes: {@code displayValue}, {@code value}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(displayValue);
        h += (h << 5) + Objects.hashCode(value);
        return h;
    }

    /**
     * Prints the immutable value {@code ActionPropertySuggestion} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        return "ActionPropertySuggestion{"
                + "displayValue=" + displayValue
                + ", value=" + value
                + "}";
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.connection.WithDynamicProperties.ActionPropertySuggestion ActionPropertySuggestion}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private String displayValue;
        private String value;

        /**
         * Creates a builder for {@link io.syndesis.common.model.connection.WithDynamicProperties.ActionPropertySuggestion ActionPropertySuggestion} instances.
         * <pre>
         * new WithDynamicProperties.ActionPropertySuggestion.Builder()
         *    .displayValue(String | null) // nullable {@link io.syndesis.common.model.connection.WithDynamicProperties.ActionPropertySuggestion#displayValue() displayValue}
         *    .value(String | null) // nullable {@link io.syndesis.common.model.connection.WithDynamicProperties.ActionPropertySuggestion#value() value}
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof WithDynamicProperties.ActionPropertySuggestion.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new WithDynamicProperties.ActionPropertySuggestion.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code ActionPropertySuggestion} instance.
         * Regular attribute values will be replaced with those from the given instance.
         * Absent optional values will not replace present values.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final WithDynamicProperties.ActionPropertySuggestion.Builder createFrom(
                WithDynamicProperties.ActionPropertySuggestion instance) {
            Objects.requireNonNull(instance, "instance");
            String displayValueValue = instance.displayValue();
            if (displayValueValue != null) {
                displayValue(displayValueValue);
            }
            String valueValue = instance.value();
            if (valueValue != null) {
                value(valueValue);
            }
            return (WithDynamicProperties.ActionPropertySuggestion.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.WithDynamicProperties.ActionPropertySuggestion#displayValue() displayValue} attribute.
         *
         * @param displayValue The value for displayValue (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("displayValue")
        public final WithDynamicProperties.ActionPropertySuggestion.Builder displayValue(
                String displayValue) {
            this.displayValue = displayValue;
            return (WithDynamicProperties.ActionPropertySuggestion.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.WithDynamicProperties.ActionPropertySuggestion#value() value} attribute.
         *
         * @param value The value for value (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("value")
        public final WithDynamicProperties.ActionPropertySuggestion.Builder value(
                String value) {
            this.value = value;
            return (WithDynamicProperties.ActionPropertySuggestion.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.connection.WithDynamicProperties.ActionPropertySuggestion ActionPropertySuggestion}.
         *
         * @return An immutable instance of ActionPropertySuggestion
         * @throws IllegalStateException if any required attributes are missing
         */
        public WithDynamicProperties.ActionPropertySuggestion build() {
            return ImmutableActionPropertySuggestion.validate(
                    new ImmutableActionPropertySuggestion(displayValue, value));
        }
    }
}
