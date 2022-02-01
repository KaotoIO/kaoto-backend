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
package io.syndesis.common.model.metrics;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Var;

import java.io.ObjectStreamException;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

/**
 * Immutable implementation of {@link io.syndesis.common.model.metrics.IntegrationDeploymentMetrics}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new IntegrationDeploymentMetrics.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableIntegrationDeploymentMetrics.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableIntegrationDeploymentMetrics
        implements IntegrationDeploymentMetrics {
    private final String version;
    private final Long messages;
    private final Long errors;
    private final Instant start;
    private final Instant lastProcessed;
    private final Long uptimeDuration;

    @SuppressWarnings("unchecked") // safe covariant cast
    private ImmutableIntegrationDeploymentMetrics(
            String version,
            Long messages,
            Long errors,
            Optional<? extends Instant> start,
            Optional<? extends Instant> lastProcessed,
            Long uptimeDuration) {
        this.version = version;
        this.messages = messages;
        this.errors = errors;
        this.start = start.orElse(null);
        this.lastProcessed = lastProcessed.orElse(null);
        this.uptimeDuration = uptimeDuration;
    }

    private ImmutableIntegrationDeploymentMetrics(
            ImmutableIntegrationDeploymentMetrics original,
            String version,
            Long messages,
            Long errors,
            Instant start,
            Instant lastProcessed,
            Long uptimeDuration) {
        this.version = version;
        this.messages = messages;
        this.errors = errors;
        this.start = start;
        this.lastProcessed = lastProcessed;
        this.uptimeDuration = uptimeDuration;
    }

    /**
     * Construct a new immutable {@code IntegrationDeploymentMetrics} instance.
     *
     * @param version        The value for the {@code version} attribute
     * @param messages       The value for the {@code messages} attribute
     * @param errors         The value for the {@code errors} attribute
     * @param start          The value for the {@code start} attribute
     * @param lastProcessed  The value for the {@code lastProcessed} attribute
     * @param uptimeDuration The value for the {@code uptimeDuration} attribute
     * @return An immutable IntegrationDeploymentMetrics instance
     */
    public static IntegrationDeploymentMetrics of(String version, Long messages,
                                                  Long errors,
                                                  Optional<? extends Instant> start,
                                                  Optional<? extends Instant> lastProcessed,
                                                  Long uptimeDuration) {
        return validate(
                new ImmutableIntegrationDeploymentMetrics(version, messages,
                        errors, start, lastProcessed, uptimeDuration));
    }

    private static ImmutableIntegrationDeploymentMetrics validate(
            ImmutableIntegrationDeploymentMetrics instance) {

        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.metrics.IntegrationDeploymentMetrics} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable IntegrationDeploymentMetrics instance
     */
    public static IntegrationDeploymentMetrics copyOf(
            IntegrationDeploymentMetrics instance) {
        if (instance instanceof ImmutableIntegrationDeploymentMetrics) {
            return (ImmutableIntegrationDeploymentMetrics) instance;
        }
        return new IntegrationDeploymentMetrics.Builder()
                .createFrom(instance)
                .build();
    }

    /**
     * @return The value of the {@code version} attribute
     */
    @JsonProperty("version")
    @Override
    public String getVersion() {
        return version;
    }

    /**
     * @return The value of the {@code messages} attribute
     */
    @JsonProperty("messages")
    @Override
    public Long getMessages() {
        return messages;
    }

    /**
     * @return The value of the {@code errors} attribute
     */
    @JsonProperty("errors")
    @Override
    public Long getErrors() {
        return errors;
    }

    /**
     * @return The value of the {@code start} attribute
     */
    @JsonProperty("start")
    @Override
    public Optional<Instant> getStart() {
        return Optional.ofNullable(start);
    }

    /**
     * @return The value of the {@code lastProcessed} attribute
     */
    @JsonProperty("lastProcessed")
    @Override
    public Optional<Instant> getLastProcessed() {
        return Optional.ofNullable(lastProcessed);
    }

    /**
     * @return The value of the {@code uptimeDuration} attribute
     */
    @JsonProperty("uptimeDuration")
    @Override
    public Long getUptimeDuration() {
        return uptimeDuration;
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.metrics.IntegrationDeploymentMetrics#getVersion() version} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for version (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableIntegrationDeploymentMetrics withVersion(
            String value) {
        if (Objects.equals(this.version, value)) {
            return this;
        }
        return validate(new ImmutableIntegrationDeploymentMetrics(this, value,
                this.messages, this.errors, this.start, this.lastProcessed,
                this.uptimeDuration));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.metrics.IntegrationDeploymentMetrics#getMessages() messages} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for messages (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableIntegrationDeploymentMetrics withMessages(
            Long value) {
        if (Objects.equals(this.messages, value)) {
            return this;
        }
        return validate(
                new ImmutableIntegrationDeploymentMetrics(this, this.version,
                        value, this.errors, this.start, this.lastProcessed,
                        this.uptimeDuration));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.metrics.IntegrationDeploymentMetrics#getErrors() errors} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for errors (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableIntegrationDeploymentMetrics withErrors(Long value) {
        if (Objects.equals(this.errors, value)) {
            return this;
        }
        return validate(
                new ImmutableIntegrationDeploymentMetrics(this, this.version,
                        this.messages, value, this.start, this.lastProcessed,
                        this.uptimeDuration));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.metrics.IntegrationDeploymentMetrics#getStart() start} attribute.
     *
     * @param value The value for start
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegrationDeploymentMetrics withStart(
            Instant value) {
        Instant newValue = Objects.requireNonNull(value, "start");
        if (this.start == newValue) {
            return this;
        }
        return validate(new ImmutableIntegrationDeploymentMetrics(
                this,
                this.version,
                this.messages,
                this.errors,
                newValue,
                this.lastProcessed,
                this.uptimeDuration));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.metrics.IntegrationDeploymentMetrics#getStart() start} attribute.
     * A shallow reference equality check is used on unboxed optional value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for start
     * @return A modified copy of {@code this} object
     */
    @SuppressWarnings("unchecked") // safe covariant cast
    public final ImmutableIntegrationDeploymentMetrics withStart(
            Optional<? extends Instant> optional) {
        Instant value = optional.orElse(null);
        if (this.start == value) {
            return this;
        }
        return validate(
                new ImmutableIntegrationDeploymentMetrics(this, this.version,
                        this.messages, this.errors, value, this.lastProcessed,
                        this.uptimeDuration));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.metrics.IntegrationDeploymentMetrics#getLastProcessed() lastProcessed} attribute.
     *
     * @param value The value for lastProcessed
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegrationDeploymentMetrics withLastProcessed(
            Instant value) {
        Instant newValue = Objects.requireNonNull(value, "lastProcessed");
        if (this.lastProcessed == newValue) {
            return this;
        }
        return validate(
                new ImmutableIntegrationDeploymentMetrics(this, this.version,
                        this.messages, this.errors, this.start, newValue,
                        this.uptimeDuration));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.metrics.IntegrationDeploymentMetrics#getLastProcessed() lastProcessed} attribute.
     * A shallow reference equality check is used on unboxed optional value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for lastProcessed
     * @return A modified copy of {@code this} object
     */
    @SuppressWarnings("unchecked") // safe covariant cast
    public final ImmutableIntegrationDeploymentMetrics withLastProcessed(
            Optional<? extends Instant> optional) {
        Instant value = optional.orElse(null);
        if (this.lastProcessed == value) {
            return this;
        }
        return validate(
                new ImmutableIntegrationDeploymentMetrics(this, this.version,
                        this.messages, this.errors, this.start, value,
                        this.uptimeDuration));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.metrics.IntegrationDeploymentMetrics#getUptimeDuration() uptimeDuration} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for uptimeDuration (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableIntegrationDeploymentMetrics withUptimeDuration(
            Long value) {
        if (Objects.equals(this.uptimeDuration, value)) {
            return this;
        }
        return validate(
                new ImmutableIntegrationDeploymentMetrics(this, this.version,
                        this.messages, this.errors, this.start,
                        this.lastProcessed, value));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableIntegrationDeploymentMetrics} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableIntegrationDeploymentMetrics
                && equalTo((ImmutableIntegrationDeploymentMetrics) another);
    }

    private boolean equalTo(ImmutableIntegrationDeploymentMetrics another) {
        return Objects.equals(version, another.version)
                && Objects.equals(messages, another.messages)
                && Objects.equals(errors, another.errors)
                && Objects.equals(start, another.start)
                && Objects.equals(lastProcessed, another.lastProcessed)
                && Objects.equals(uptimeDuration, another.uptimeDuration);
    }

    /**
     * Computes a hash code from attributes: {@code version}, {@code messages}, {@code errors}, {@code start}, {@code lastProcessed}, {@code uptimeDuration}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(version);
        h += (h << 5) + Objects.hashCode(messages);
        h += (h << 5) + Objects.hashCode(errors);
        h += (h << 5) + Objects.hashCode(start);
        h += (h << 5) + Objects.hashCode(lastProcessed);
        h += (h << 5) + Objects.hashCode(uptimeDuration);
        return h;
    }

    /**
     * Prints the immutable value {@code IntegrationDeploymentMetrics} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        StringBuilder builder =
                new StringBuilder("IntegrationDeploymentMetrics{");
        if (version != null) {
            builder.append("version=").append(version);
        }
        if (messages != null) {
            if (builder.length() > 29) {
                builder.append(", ");
            }
            builder.append("messages=").append(messages);
        }
        if (errors != null) {
            if (builder.length() > 29) {
                builder.append(", ");
            }
            builder.append("errors=").append(errors);
        }
        if (start != null) {
            if (builder.length() > 29) {
                builder.append(", ");
            }
            builder.append("start=").append(start);
        }
        if (lastProcessed != null) {
            if (builder.length() > 29) {
                builder.append(", ");
            }
            builder.append("lastProcessed=").append(lastProcessed);
        }
        if (uptimeDuration != null) {
            if (builder.length() > 29) {
                builder.append(", ");
            }
            builder.append("uptimeDuration=").append(uptimeDuration);
        }
        return builder.append("}").toString();
    }

    private Object readResolve() throws ObjectStreamException {
        return validate(this);
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.metrics.IntegrationDeploymentMetrics IntegrationDeploymentMetrics}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private String version;
        private Long messages;
        private Long errors;
        private Instant start;
        private Instant lastProcessed;
        private Long uptimeDuration;

        /**
         * Creates a builder for {@link io.syndesis.common.model.metrics.IntegrationDeploymentMetrics IntegrationDeploymentMetrics} instances.
         * <pre>
         * new IntegrationDeploymentMetrics.Builder()
         *    .version(String | null) // nullable {@link io.syndesis.common.model.metrics.IntegrationDeploymentMetrics#getVersion() version}
         *    .messages(Long | null) // nullable {@link io.syndesis.common.model.metrics.IntegrationDeploymentMetrics#getMessages() messages}
         *    .errors(Long | null) // nullable {@link io.syndesis.common.model.metrics.IntegrationDeploymentMetrics#getErrors() errors}
         *    .start(java.time.Instant) // optional {@link io.syndesis.common.model.metrics.IntegrationDeploymentMetrics#getStart() start}
         *    .lastProcessed(java.time.Instant) // optional {@link io.syndesis.common.model.metrics.IntegrationDeploymentMetrics#getLastProcessed() lastProcessed}
         *    .uptimeDuration(Long | null) // nullable {@link io.syndesis.common.model.metrics.IntegrationDeploymentMetrics#getUptimeDuration() uptimeDuration}
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof IntegrationDeploymentMetrics.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new IntegrationDeploymentMetrics.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code IntegrationDeploymentMetrics} instance.
         * Regular attribute values will be replaced with those from the given instance.
         * Absent optional values will not replace present values.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationDeploymentMetrics.Builder createFrom(
                IntegrationDeploymentMetrics instance) {
            Objects.requireNonNull(instance, "instance");
            String versionValue = instance.getVersion();
            if (versionValue != null) {
                version(versionValue);
            }
            Long messagesValue = instance.getMessages();
            if (messagesValue != null) {
                messages(messagesValue);
            }
            Long errorsValue = instance.getErrors();
            if (errorsValue != null) {
                errors(errorsValue);
            }
            Optional<Instant> startOptional = instance.getStart();
            if (startOptional.isPresent()) {
                start(startOptional);
            }
            Optional<Instant> lastProcessedOptional =
                    instance.getLastProcessed();
            if (lastProcessedOptional.isPresent()) {
                lastProcessed(lastProcessedOptional);
            }
            Long uptimeDurationValue = instance.getUptimeDuration();
            if (uptimeDurationValue != null) {
                uptimeDuration(uptimeDurationValue);
            }
            return (IntegrationDeploymentMetrics.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.metrics.IntegrationDeploymentMetrics#getVersion() version} attribute.
         *
         * @param version The value for version (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("version")
        public final IntegrationDeploymentMetrics.Builder version(
                String version) {
            this.version = version;
            return (IntegrationDeploymentMetrics.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.metrics.IntegrationDeploymentMetrics#getMessages() messages} attribute.
         *
         * @param messages The value for messages (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("messages")
        public final IntegrationDeploymentMetrics.Builder messages(
                Long messages) {
            this.messages = messages;
            return (IntegrationDeploymentMetrics.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.metrics.IntegrationDeploymentMetrics#getErrors() errors} attribute.
         *
         * @param errors The value for errors (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("errors")
        public final IntegrationDeploymentMetrics.Builder errors(Long errors) {
            this.errors = errors;
            return (IntegrationDeploymentMetrics.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.metrics.IntegrationDeploymentMetrics#getStart() start} to start.
         *
         * @param start The value for start
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationDeploymentMetrics.Builder start(Instant start) {
            this.start = Objects.requireNonNull(start, "start");
            return (IntegrationDeploymentMetrics.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.metrics.IntegrationDeploymentMetrics#getStart() start} to start.
         *
         * @param start The value for start
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("start")
        public final IntegrationDeploymentMetrics.Builder start(
                Optional<? extends Instant> start) {
            this.start = start.orElse(null);
            return (IntegrationDeploymentMetrics.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.metrics.IntegrationDeploymentMetrics#getLastProcessed() lastProcessed} to lastProcessed.
         *
         * @param lastProcessed The value for lastProcessed
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationDeploymentMetrics.Builder lastProcessed(
                Instant lastProcessed) {
            this.lastProcessed =
                    Objects.requireNonNull(lastProcessed, "lastProcessed");
            return (IntegrationDeploymentMetrics.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.metrics.IntegrationDeploymentMetrics#getLastProcessed() lastProcessed} to lastProcessed.
         *
         * @param lastProcessed The value for lastProcessed
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("lastProcessed")
        public final IntegrationDeploymentMetrics.Builder lastProcessed(
                Optional<? extends Instant> lastProcessed) {
            this.lastProcessed = lastProcessed.orElse(null);
            return (IntegrationDeploymentMetrics.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.metrics.IntegrationDeploymentMetrics#getUptimeDuration() uptimeDuration} attribute.
         *
         * @param uptimeDuration The value for uptimeDuration (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("uptimeDuration")
        public final IntegrationDeploymentMetrics.Builder uptimeDuration(
                Long uptimeDuration) {
            this.uptimeDuration = uptimeDuration;
            return (IntegrationDeploymentMetrics.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.metrics.IntegrationDeploymentMetrics IntegrationDeploymentMetrics}.
         *
         * @return An immutable instance of IntegrationDeploymentMetrics
         * @throws IllegalStateException if any required attributes are missing
         */
        public IntegrationDeploymentMetrics build() {
            return ImmutableIntegrationDeploymentMetrics.validate(
                    new ImmutableIntegrationDeploymentMetrics(null, version,
                            messages, errors, start, lastProcessed,
                            uptimeDuration));
        }
    }
}
