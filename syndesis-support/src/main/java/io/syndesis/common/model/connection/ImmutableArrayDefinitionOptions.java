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
 * Immutable implementation of {@link io.syndesis.common.model.connection.ConfigurationProperty.ArrayDefinitionOptions}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new ConfigurationProperty.ArrayDefinitionOptions.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableArrayDefinitionOptions.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableArrayDefinitionOptions
        implements ConfigurationProperty.ArrayDefinitionOptions {
    private final String i18nAddElementText;
    private final Integer minElements;

    private ImmutableArrayDefinitionOptions(String i18nAddElementText,
                                            Integer minElements) {
        this.i18nAddElementText = i18nAddElementText;
        this.minElements = minElements;
    }

    /**
     * Construct a new immutable {@code ArrayDefinitionOptions} instance.
     *
     * @param i18nAddElementText The value for the {@code i18nAddElementText} attribute
     * @param minElements        The value for the {@code minElements} attribute
     * @return An immutable ArrayDefinitionOptions instance
     */
    public static ConfigurationProperty.ArrayDefinitionOptions of(
            String i18nAddElementText, Integer minElements) {
        return validate(new ImmutableArrayDefinitionOptions(i18nAddElementText,
                minElements));
    }

    private static ImmutableArrayDefinitionOptions validate(
            ImmutableArrayDefinitionOptions instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.connection.ConfigurationProperty.ArrayDefinitionOptions} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable ArrayDefinitionOptions instance
     */
    public static ConfigurationProperty.ArrayDefinitionOptions copyOf(
            ConfigurationProperty.ArrayDefinitionOptions instance) {
        if (instance instanceof ImmutableArrayDefinitionOptions) {
            return (ImmutableArrayDefinitionOptions) instance;
        }
        return new ConfigurationProperty.ArrayDefinitionOptions.Builder()
                .createFrom(instance)
                .build();
    }

    /**
     * @return The value of the {@code i18nAddElementText} attribute
     */
    @JsonProperty("i18nAddElementText")
    @Override
    public String getI18nAddElementText() {
        return i18nAddElementText;
    }

    /**
     * @return The value of the {@code minElements} attribute
     */
    @JsonProperty("minElements")
    @Override
    public Integer getMinElements() {
        return minElements;
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConfigurationProperty.ArrayDefinitionOptions#getI18nAddElementText() i18nAddElementText} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for i18nAddElementText (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableArrayDefinitionOptions withI18nAddElementText(
            String value) {
        if (Objects.equals(this.i18nAddElementText, value)) {
            return this;
        }
        return validate(
                new ImmutableArrayDefinitionOptions(value, this.minElements));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConfigurationProperty.ArrayDefinitionOptions#getMinElements() minElements} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for minElements (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableArrayDefinitionOptions withMinElements(
            Integer value) {
        if (Objects.equals(this.minElements, value)) {
            return this;
        }
        return validate(
                new ImmutableArrayDefinitionOptions(this.i18nAddElementText,
                        value));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableArrayDefinitionOptions} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableArrayDefinitionOptions
                && equalTo((ImmutableArrayDefinitionOptions) another);
    }

    private boolean equalTo(ImmutableArrayDefinitionOptions another) {
        return Objects.equals(i18nAddElementText, another.i18nAddElementText)
                && Objects.equals(minElements, another.minElements);
    }

    /**
     * Computes a hash code from attributes: {@code i18nAddElementText}, {@code minElements}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(i18nAddElementText);
        h += (h << 5) + Objects.hashCode(minElements);
        return h;
    }

    /**
     * Prints the immutable value {@code ArrayDefinitionOptions} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        return "ArrayDefinitionOptions{"
                + "i18nAddElementText=" + i18nAddElementText
                + ", minElements=" + minElements
                + "}";
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.connection.ConfigurationProperty.ArrayDefinitionOptions ArrayDefinitionOptions}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private String i18nAddElementText;
        private Integer minElements;

        /**
         * Creates a builder for {@link io.syndesis.common.model.connection.ConfigurationProperty.ArrayDefinitionOptions ArrayDefinitionOptions} instances.
         * <pre>
         * new ConfigurationProperty.ArrayDefinitionOptions.Builder()
         *    .i18nAddElementText(String | null) // nullable {@link io.syndesis.common.model.connection.ConfigurationProperty.ArrayDefinitionOptions#getI18nAddElementText() i18nAddElementText}
         *    .minElements(Integer | null) // nullable {@link io.syndesis.common.model.connection.ConfigurationProperty.ArrayDefinitionOptions#getMinElements() minElements}
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof ConfigurationProperty.ArrayDefinitionOptions.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new ConfigurationProperty.ArrayDefinitionOptions.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code ArrayDefinitionOptions} instance.
         * Regular attribute values will be replaced with those from the given instance.
         * Absent optional values will not replace present values.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConfigurationProperty.ArrayDefinitionOptions.Builder createFrom(
                ConfigurationProperty.ArrayDefinitionOptions instance) {
            Objects.requireNonNull(instance, "instance");
            String i18nAddElementTextValue = instance.getI18nAddElementText();
            if (i18nAddElementTextValue != null) {
                i18nAddElementText(i18nAddElementTextValue);
            }
            Integer minElementsValue = instance.getMinElements();
            if (minElementsValue != null) {
                minElements(minElementsValue);
            }
            return (ConfigurationProperty.ArrayDefinitionOptions.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConfigurationProperty.ArrayDefinitionOptions#getI18nAddElementText() i18nAddElementText} attribute.
         *
         * @param i18nAddElementText The value for i18nAddElementText (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("i18nAddElementText")
        public final ConfigurationProperty.ArrayDefinitionOptions.Builder i18nAddElementText(
                String i18nAddElementText) {
            this.i18nAddElementText = i18nAddElementText;
            return (ConfigurationProperty.ArrayDefinitionOptions.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConfigurationProperty.ArrayDefinitionOptions#getMinElements() minElements} attribute.
         *
         * @param minElements The value for minElements (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("minElements")
        public final ConfigurationProperty.ArrayDefinitionOptions.Builder minElements(
                Integer minElements) {
            this.minElements = minElements;
            return (ConfigurationProperty.ArrayDefinitionOptions.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.connection.ConfigurationProperty.ArrayDefinitionOptions ArrayDefinitionOptions}.
         *
         * @return An immutable instance of ArrayDefinitionOptions
         * @throws IllegalStateException if any required attributes are missing
         */
        public ConfigurationProperty.ArrayDefinitionOptions build() {
            return ImmutableArrayDefinitionOptions.validate(
                    new ImmutableArrayDefinitionOptions(i18nAddElementText,
                            minElements));
        }
    }
}
