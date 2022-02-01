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
 * Immutable implementation of {@link io.syndesis.common.model.connection.ConfigurationProperty.PropertyValue}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new ConfigurationProperty.PropertyValue.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutablePropertyValue.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutablePropertyValue
        implements ConfigurationProperty.PropertyValue {
    private final String label;
    private final String value;

    private ImmutablePropertyValue(String label, String value) {
        this.label = label;
        this.value = value;
    }

    /**
     * Construct a new immutable {@code PropertyValue} instance.
     *
     * @param label The value for the {@code label} attribute
     * @param value The value for the {@code value} attribute
     * @return An immutable PropertyValue instance
     */
    public static ConfigurationProperty.PropertyValue of(String label,
                                                         String value) {
        return validate(new ImmutablePropertyValue(label, value));
    }

    private static ImmutablePropertyValue validate(
            ImmutablePropertyValue instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.connection.ConfigurationProperty.PropertyValue} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable PropertyValue instance
     */
    public static ConfigurationProperty.PropertyValue copyOf(
            ConfigurationProperty.PropertyValue instance) {
        if (instance instanceof ImmutablePropertyValue) {
            return (ImmutablePropertyValue) instance;
        }
        return new ConfigurationProperty.PropertyValue.Builder()
                .createFrom(instance)
                .build();
    }

    /**
     * @return The value of the {@code label} attribute
     */
    @JsonProperty("label")
    @Override
    public String getLabel() {
        return label;
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
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConfigurationProperty.PropertyValue#getLabel() label} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for label (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutablePropertyValue withLabel(String value) {
        if (Objects.equals(this.label, value)) {
            return this;
        }
        return validate(new ImmutablePropertyValue(value, this.value));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConfigurationProperty.PropertyValue#getValue() value} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for value (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutablePropertyValue withValue(String value) {
        if (Objects.equals(this.value, value)) {
            return this;
        }
        return validate(new ImmutablePropertyValue(this.label, value));
    }

    /**
     * This instance is equal to all instances of {@code ImmutablePropertyValue} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutablePropertyValue
                && equalTo((ImmutablePropertyValue) another);
    }

    private boolean equalTo(ImmutablePropertyValue another) {
        return Objects.equals(label, another.label)
                && Objects.equals(value, another.value);
    }

    /**
     * Computes a hash code from attributes: {@code label}, {@code value}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(label);
        h += (h << 5) + Objects.hashCode(value);
        return h;
    }

    /**
     * Prints the immutable value {@code PropertyValue} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        return "PropertyValue{"
                + "label=" + label
                + ", value=" + value
                + "}";
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.connection.ConfigurationProperty.PropertyValue PropertyValue}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private String label;
        private String value;

        /**
         * Creates a builder for {@link io.syndesis.common.model.connection.ConfigurationProperty.PropertyValue PropertyValue} instances.
         * <pre>
         * new ConfigurationProperty.PropertyValue.Builder()
         *    .label(String | null) // nullable {@link io.syndesis.common.model.connection.ConfigurationProperty.PropertyValue#getLabel() label}
         *    .value(String | null) // nullable {@link io.syndesis.common.model.connection.ConfigurationProperty.PropertyValue#getValue() value}
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof ConfigurationProperty.PropertyValue.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new ConfigurationProperty.PropertyValue.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code PropertyValue} instance.
         * Regular attribute values will be replaced with those from the given instance.
         * Absent optional values will not replace present values.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConfigurationProperty.PropertyValue.Builder createFrom(
                ConfigurationProperty.PropertyValue instance) {
            Objects.requireNonNull(instance, "instance");
            String labelValue = instance.getLabel();
            if (labelValue != null) {
                label(labelValue);
            }
            String valueValue = instance.getValue();
            if (valueValue != null) {
                value(valueValue);
            }
            return (ConfigurationProperty.PropertyValue.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConfigurationProperty.PropertyValue#getLabel() label} attribute.
         *
         * @param label The value for label (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("label")
        public final ConfigurationProperty.PropertyValue.Builder label(
                String label) {
            this.label = label;
            return (ConfigurationProperty.PropertyValue.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConfigurationProperty.PropertyValue#getValue() value} attribute.
         *
         * @param value The value for value (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("value")
        public final ConfigurationProperty.PropertyValue.Builder value(
                String value) {
            this.value = value;
            return (ConfigurationProperty.PropertyValue.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.connection.ConfigurationProperty.PropertyValue PropertyValue}.
         *
         * @return An immutable instance of PropertyValue
         * @throws IllegalStateException if any required attributes are missing
         */
        public ConfigurationProperty.PropertyValue build() {
            return ImmutablePropertyValue.validate(
                    new ImmutablePropertyValue(label, value));
        }
    }
}
