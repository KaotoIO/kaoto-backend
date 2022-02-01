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
 * Immutable implementation of {@link io.syndesis.common.model.ModelExport}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code ImmutableModelExport.builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableModelExport.of()}.
 */
@SuppressWarnings({"varargs", "all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableModelExport implements ModelExport {
    private final int schemaVersion;

    private ImmutableModelExport(int schemaVersion) {
        this.schemaVersion = schemaVersion;
    }

    /**
     * Construct a new immutable {@code ModelExport} instance.
     *
     * @param schemaVersion The value for the {@code schemaVersion} attribute
     * @return An immutable ModelExport instance
     */
    public static ModelExport of(int schemaVersion) {
        return validate(new ImmutableModelExport(schemaVersion));
    }

    private static ImmutableModelExport validate(
            ImmutableModelExport instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.ModelExport} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable ModelExport instance
     */
    public static ModelExport copyOf(ModelExport instance) {
        if (instance instanceof ImmutableModelExport) {
            return (ImmutableModelExport) instance;
        }
        return ImmutableModelExport.builder()
                .createFrom(instance)
                .build();
    }

    /**
     * Creates a builder for {@link io.syndesis.common.model.ModelExport ModelExport}.
     * <pre>
     * ImmutableModelExport.builder()
     *    .schemaVersion(int) // optional {@link io.syndesis.common.model.ModelExport#schemaVersion() schemaVersion}
     *    .build();
     * </pre>
     *
     * @return A new ModelExport builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * @return The value of the {@code schemaVersion} attribute
     */
    @JsonProperty("schemaVersion")
    @Override
    public int schemaVersion() {
        return schemaVersion;
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.ModelExport#schemaVersion() schemaVersion} attribute.
     * A value equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for schemaVersion
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableModelExport withSchemaVersion(int value) {
        if (this.schemaVersion == value) {
            return this;
        }
        return validate(new ImmutableModelExport(value));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableModelExport} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableModelExport
                && equalTo((ImmutableModelExport) another);
    }

    private boolean equalTo(ImmutableModelExport another) {
        return schemaVersion == another.schemaVersion;
    }

    /**
     * Computes a hash code from attributes: {@code schemaVersion}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + schemaVersion;
        return h;
    }

    /**
     * Prints the immutable value {@code ModelExport} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        return "ModelExport{"
                + "schemaVersion=" + schemaVersion
                + "}";
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.ModelExport ModelExport}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static final class Builder {
        private int schemaVersion;

        private Builder() {
        }

        /**
         * Fill a builder with attribute values from the provided {@code ModelExport} instance.
         * Regular attribute values will be replaced with those from the given instance.
         * Absent optional values will not replace present values.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Builder createFrom(ModelExport instance) {
            Objects.requireNonNull(instance, "instance");
            schemaVersion(instance.schemaVersion());
            return this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.ModelExport#schemaVersion() schemaVersion} attribute.
         *
         * @param schemaVersion The value for schemaVersion
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("schemaVersion")
        public final Builder schemaVersion(int schemaVersion) {
            this.schemaVersion = schemaVersion;
            return this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.ModelExport ModelExport}.
         *
         * @return An immutable instance of ModelExport
         * @throws IllegalStateException if any required attributes are missing
         */
        public ModelExport build() {
            return ImmutableModelExport.validate(
                    new ImmutableModelExport(schemaVersion));
        }
    }
}
