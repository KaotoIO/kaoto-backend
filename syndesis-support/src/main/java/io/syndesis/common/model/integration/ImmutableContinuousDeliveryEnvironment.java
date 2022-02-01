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

import java.io.ObjectStreamException;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

/**
 * Immutable implementation of {@link io.syndesis.common.model.integration.ContinuousDeliveryEnvironment}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new ContinuousDeliveryEnvironment.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableContinuousDeliveryEnvironment.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableContinuousDeliveryEnvironment
        implements ContinuousDeliveryEnvironment {
    private final String environmentId;
    private final String releaseTag;
    private final Date lastTaggedAt;
    private final Date lastExportedAt;
    private final Date lastImportedAt;

    @SuppressWarnings("unchecked") // safe covariant cast
    private ImmutableContinuousDeliveryEnvironment(
            String environmentId,
            String releaseTag,
            Date lastTaggedAt,
            Optional<? extends Date> lastExportedAt,
            Optional<? extends Date> lastImportedAt) {
        this.environmentId = environmentId;
        this.releaseTag = releaseTag;
        this.lastTaggedAt = lastTaggedAt;
        this.lastExportedAt = lastExportedAt.orElse(null);
        this.lastImportedAt = lastImportedAt.orElse(null);
    }

    private ImmutableContinuousDeliveryEnvironment(
            ImmutableContinuousDeliveryEnvironment original,
            String environmentId,
            String releaseTag,
            Date lastTaggedAt,
            Date lastExportedAt,
            Date lastImportedAt) {
        this.environmentId = environmentId;
        this.releaseTag = releaseTag;
        this.lastTaggedAt = lastTaggedAt;
        this.lastExportedAt = lastExportedAt;
        this.lastImportedAt = lastImportedAt;
    }

    /**
     * Construct a new immutable {@code ContinuousDeliveryEnvironment} instance.
     *
     * @param environmentId  The value for the {@code environmentId} attribute
     * @param releaseTag     The value for the {@code releaseTag} attribute
     * @param lastTaggedAt   The value for the {@code lastTaggedAt} attribute
     * @param lastExportedAt The value for the {@code lastExportedAt} attribute
     * @param lastImportedAt The value for the {@code lastImportedAt} attribute
     * @return An immutable ContinuousDeliveryEnvironment instance
     */
    public static ContinuousDeliveryEnvironment of(String environmentId,
                                                   String releaseTag,
                                                   Date lastTaggedAt,
                                                   Optional<? extends Date> lastExportedAt,
                                                   Optional<? extends Date> lastImportedAt) {
        return validate(
                new ImmutableContinuousDeliveryEnvironment(environmentId,
                        releaseTag, lastTaggedAt, lastExportedAt,
                        lastImportedAt));
    }

    private static ImmutableContinuousDeliveryEnvironment validate(
            ImmutableContinuousDeliveryEnvironment instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.integration.ContinuousDeliveryEnvironment} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable ContinuousDeliveryEnvironment instance
     */
    public static ContinuousDeliveryEnvironment copyOf(
            ContinuousDeliveryEnvironment instance) {
        if (instance instanceof ImmutableContinuousDeliveryEnvironment) {
            return (ImmutableContinuousDeliveryEnvironment) instance;
        }
        return new ContinuousDeliveryEnvironment.Builder()
                .createFrom(instance)
                .build();
    }

    /**
     * @return The value of the {@code environmentId} attribute
     */
    @JsonProperty("environmentId")
    @Override
    public String getEnvironmentId() {
        return environmentId;
    }

    /**
     * @return The value of the {@code releaseTag} attribute
     */
    @JsonProperty("releaseTag")
    @Override
    public String getReleaseTag() {
        return releaseTag;
    }

    /**
     * @return The value of the {@code lastTaggedAt} attribute
     */
    @JsonProperty("lastTaggedAt")
    @Override
    public Date getLastTaggedAt() {
        return lastTaggedAt;
    }

    /**
     * @return The value of the {@code lastExportedAt} attribute
     */
    @JsonProperty("lastExportedAt")
    @Override
    public Optional<Date> getLastExportedAt() {
        return Optional.ofNullable(lastExportedAt);
    }

    /**
     * @return The value of the {@code lastImportedAt} attribute
     */
    @JsonProperty("lastImportedAt")
    @Override
    public Optional<Date> getLastImportedAt() {
        return Optional.ofNullable(lastImportedAt);
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.integration.ContinuousDeliveryEnvironment#getEnvironmentId() environmentId} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for environmentId (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableContinuousDeliveryEnvironment withEnvironmentId(
            String value) {
        if (Objects.equals(this.environmentId, value)) {
            return this;
        }
        return validate(new ImmutableContinuousDeliveryEnvironment(this, value,
                this.releaseTag, this.lastTaggedAt, this.lastExportedAt,
                this.lastImportedAt));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.integration.ContinuousDeliveryEnvironment#getReleaseTag() releaseTag} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for releaseTag (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableContinuousDeliveryEnvironment withReleaseTag(
            String value) {
        if (Objects.equals(this.releaseTag, value)) {
            return this;
        }
        return validate(new ImmutableContinuousDeliveryEnvironment(this,
                this.environmentId, value, this.lastTaggedAt,
                this.lastExportedAt, this.lastImportedAt));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.integration.ContinuousDeliveryEnvironment#getLastTaggedAt() lastTaggedAt} attribute.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for lastTaggedAt (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableContinuousDeliveryEnvironment withLastTaggedAt(
            Date value) {
        if (this.lastTaggedAt == value) {
            return this;
        }
        return validate(new ImmutableContinuousDeliveryEnvironment(this,
                this.environmentId, this.releaseTag, value, this.lastExportedAt,
                this.lastImportedAt));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.integration.ContinuousDeliveryEnvironment#getLastExportedAt() lastExportedAt} attribute.
     *
     * @param value The value for lastExportedAt
     * @return A modified copy of {@code this} object
     */
    public final ImmutableContinuousDeliveryEnvironment withLastExportedAt(
            Date value) {
        Date newValue = Objects.requireNonNull(value, "lastExportedAt");
        if (this.lastExportedAt == newValue) {
            return this;
        }
        return validate(new ImmutableContinuousDeliveryEnvironment(this,
                this.environmentId, this.releaseTag, this.lastTaggedAt,
                newValue, this.lastImportedAt));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.integration.ContinuousDeliveryEnvironment#getLastExportedAt() lastExportedAt} attribute.
     * A shallow reference equality check is used on unboxed optional value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for lastExportedAt
     * @return A modified copy of {@code this} object
     */
    @SuppressWarnings("unchecked") // safe covariant cast
    public final ImmutableContinuousDeliveryEnvironment withLastExportedAt(
            Optional<? extends Date> optional) {
        Date value = optional.orElse(null);
        if (this.lastExportedAt == value) {
            return this;
        }
        return validate(new ImmutableContinuousDeliveryEnvironment(this,
                this.environmentId, this.releaseTag, this.lastTaggedAt, value,
                this.lastImportedAt));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.integration.ContinuousDeliveryEnvironment#getLastImportedAt() lastImportedAt} attribute.
     *
     * @param value The value for lastImportedAt
     * @return A modified copy of {@code this} object
     */
    public final ImmutableContinuousDeliveryEnvironment withLastImportedAt(
            Date value) {
        Date newValue = Objects.requireNonNull(value, "lastImportedAt");
        if (this.lastImportedAt == newValue) {
            return this;
        }
        return validate(new ImmutableContinuousDeliveryEnvironment(this,
                this.environmentId, this.releaseTag, this.lastTaggedAt,
                this.lastExportedAt, newValue));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.integration.ContinuousDeliveryEnvironment#getLastImportedAt() lastImportedAt} attribute.
     * A shallow reference equality check is used on unboxed optional value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for lastImportedAt
     * @return A modified copy of {@code this} object
     */
    @SuppressWarnings("unchecked") // safe covariant cast
    public final ImmutableContinuousDeliveryEnvironment withLastImportedAt(
            Optional<? extends Date> optional) {
        Date value = optional.orElse(null);
        if (this.lastImportedAt == value) {
            return this;
        }
        return validate(new ImmutableContinuousDeliveryEnvironment(this,
                this.environmentId, this.releaseTag, this.lastTaggedAt,
                this.lastExportedAt, value));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableContinuousDeliveryEnvironment} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableContinuousDeliveryEnvironment
                && equalTo((ImmutableContinuousDeliveryEnvironment) another);
    }

    private boolean equalTo(ImmutableContinuousDeliveryEnvironment another) {
        return Objects.equals(environmentId, another.environmentId)
                && Objects.equals(releaseTag, another.releaseTag)
                && Objects.equals(lastTaggedAt, another.lastTaggedAt)
                && Objects.equals(lastExportedAt, another.lastExportedAt)
                && Objects.equals(lastImportedAt, another.lastImportedAt);
    }

    /**
     * Computes a hash code from attributes: {@code environmentId}, {@code releaseTag}, {@code lastTaggedAt}, {@code lastExportedAt}, {@code lastImportedAt}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(environmentId);
        h += (h << 5) + Objects.hashCode(releaseTag);
        h += (h << 5) + Objects.hashCode(lastTaggedAt);
        h += (h << 5) + Objects.hashCode(lastExportedAt);
        h += (h << 5) + Objects.hashCode(lastImportedAt);
        return h;
    }

    /**
     * Prints the immutable value {@code ContinuousDeliveryEnvironment} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        StringBuilder builder =
                new StringBuilder("ContinuousDeliveryEnvironment{");
        if (environmentId != null) {
            builder.append("environmentId=").append(environmentId);
        }
        if (releaseTag != null) {
            if (builder.length() > 30) {
                builder.append(", ");
            }
            builder.append("releaseTag=").append(releaseTag);
        }
        if (lastTaggedAt != null) {
            if (builder.length() > 30) {
                builder.append(", ");
            }
            builder.append("lastTaggedAt=").append(lastTaggedAt);
        }
        if (lastExportedAt != null) {
            if (builder.length() > 30) {
                builder.append(", ");
            }
            builder.append("lastExportedAt=").append(lastExportedAt);
        }
        if (lastImportedAt != null) {
            if (builder.length() > 30) {
                builder.append(", ");
            }
            builder.append("lastImportedAt=").append(lastImportedAt);
        }
        return builder.append("}").toString();
    }

    private Object readResolve() throws ObjectStreamException {
        return validate(this);
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.integration.ContinuousDeliveryEnvironment ContinuousDeliveryEnvironment}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private String environmentId;
        private String releaseTag;
        private Date lastTaggedAt;
        private Date lastExportedAt;
        private Date lastImportedAt;

        /**
         * Creates a builder for {@link io.syndesis.common.model.integration.ContinuousDeliveryEnvironment ContinuousDeliveryEnvironment} instances.
         * <pre>
         * new ContinuousDeliveryEnvironment.Builder()
         *    .environmentId(String | null) // nullable {@link io.syndesis.common.model.integration.ContinuousDeliveryEnvironment#getEnvironmentId() environmentId}
         *    .releaseTag(String | null) // nullable {@link io.syndesis.common.model.integration.ContinuousDeliveryEnvironment#getReleaseTag() releaseTag}
         *    .lastTaggedAt(Date | null) // nullable {@link io.syndesis.common.model.integration.ContinuousDeliveryEnvironment#getLastTaggedAt() lastTaggedAt}
         *    .lastExportedAt(Date) // optional {@link io.syndesis.common.model.integration.ContinuousDeliveryEnvironment#getLastExportedAt() lastExportedAt}
         *    .lastImportedAt(Date) // optional {@link io.syndesis.common.model.integration.ContinuousDeliveryEnvironment#getLastImportedAt() lastImportedAt}
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof ContinuousDeliveryEnvironment.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new ContinuousDeliveryEnvironment.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code ContinuousDeliveryEnvironment} instance.
         * Regular attribute values will be replaced with those from the given instance.
         * Absent optional values will not replace present values.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ContinuousDeliveryEnvironment.Builder createFrom(
                ContinuousDeliveryEnvironment instance) {
            Objects.requireNonNull(instance, "instance");
            String environmentIdValue = instance.getEnvironmentId();
            if (environmentIdValue != null) {
                environmentId(environmentIdValue);
            }
            String releaseTagValue = instance.getReleaseTag();
            if (releaseTagValue != null) {
                releaseTag(releaseTagValue);
            }
            Date lastTaggedAtValue = instance.getLastTaggedAt();
            if (lastTaggedAtValue != null) {
                lastTaggedAt(lastTaggedAtValue);
            }
            Optional<Date> lastExportedAtOptional =
                    instance.getLastExportedAt();
            if (lastExportedAtOptional.isPresent()) {
                lastExportedAt(lastExportedAtOptional);
            }
            Optional<Date> lastImportedAtOptional =
                    instance.getLastImportedAt();
            if (lastImportedAtOptional.isPresent()) {
                lastImportedAt(lastImportedAtOptional);
            }
            return (ContinuousDeliveryEnvironment.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.integration.ContinuousDeliveryEnvironment#getEnvironmentId() environmentId} attribute.
         *
         * @param environmentId The value for environmentId (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("environmentId")
        public final ContinuousDeliveryEnvironment.Builder environmentId(
                String environmentId) {
            this.environmentId = environmentId;
            return (ContinuousDeliveryEnvironment.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.integration.ContinuousDeliveryEnvironment#getReleaseTag() releaseTag} attribute.
         *
         * @param releaseTag The value for releaseTag (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("releaseTag")
        public final ContinuousDeliveryEnvironment.Builder releaseTag(
                String releaseTag) {
            this.releaseTag = releaseTag;
            return (ContinuousDeliveryEnvironment.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.integration.ContinuousDeliveryEnvironment#getLastTaggedAt() lastTaggedAt} attribute.
         *
         * @param lastTaggedAt The value for lastTaggedAt (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("lastTaggedAt")
        public final ContinuousDeliveryEnvironment.Builder lastTaggedAt(
                Date lastTaggedAt) {
            this.lastTaggedAt = lastTaggedAt;
            return (ContinuousDeliveryEnvironment.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.integration.ContinuousDeliveryEnvironment#getLastExportedAt() lastExportedAt} to lastExportedAt.
         *
         * @param lastExportedAt The value for lastExportedAt
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final ContinuousDeliveryEnvironment.Builder lastExportedAt(
                Date lastExportedAt) {
            this.lastExportedAt =
                    Objects.requireNonNull(lastExportedAt, "lastExportedAt");
            return (ContinuousDeliveryEnvironment.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.integration.ContinuousDeliveryEnvironment#getLastExportedAt() lastExportedAt} to lastExportedAt.
         *
         * @param lastExportedAt The value for lastExportedAt
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("lastExportedAt")
        public final ContinuousDeliveryEnvironment.Builder lastExportedAt(
                Optional<? extends Date> lastExportedAt) {
            this.lastExportedAt = lastExportedAt.orElse(null);
            return (ContinuousDeliveryEnvironment.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.integration.ContinuousDeliveryEnvironment#getLastImportedAt() lastImportedAt} to lastImportedAt.
         *
         * @param lastImportedAt The value for lastImportedAt
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final ContinuousDeliveryEnvironment.Builder lastImportedAt(
                Date lastImportedAt) {
            this.lastImportedAt =
                    Objects.requireNonNull(lastImportedAt, "lastImportedAt");
            return (ContinuousDeliveryEnvironment.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.integration.ContinuousDeliveryEnvironment#getLastImportedAt() lastImportedAt} to lastImportedAt.
         *
         * @param lastImportedAt The value for lastImportedAt
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("lastImportedAt")
        public final ContinuousDeliveryEnvironment.Builder lastImportedAt(
                Optional<? extends Date> lastImportedAt) {
            this.lastImportedAt = lastImportedAt.orElse(null);
            return (ContinuousDeliveryEnvironment.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.integration.ContinuousDeliveryEnvironment ContinuousDeliveryEnvironment}.
         *
         * @return An immutable instance of ContinuousDeliveryEnvironment
         * @throws IllegalStateException if any required attributes are missing
         */
        public ContinuousDeliveryEnvironment build() {
            return ImmutableContinuousDeliveryEnvironment.validate(
                    new ImmutableContinuousDeliveryEnvironment(null,
                            environmentId, releaseTag, lastTaggedAt,
                            lastExportedAt, lastImportedAt));
        }
    }
}
