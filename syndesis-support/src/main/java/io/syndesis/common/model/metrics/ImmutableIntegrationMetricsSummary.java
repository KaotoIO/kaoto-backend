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
import io.syndesis.common.model.WithResourceId;

import java.io.ObjectStreamException;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Immutable implementation of {@link io.syndesis.common.model.metrics.IntegrationMetricsSummary}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new IntegrationMetricsSummary.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableIntegrationMetricsSummary.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableIntegrationMetricsSummary
        implements IntegrationMetricsSummary {
    private final String id;
    private final Long errors;
    private final List<IntegrationDeploymentMetrics>
            integrationDeploymentMetrics;
    private final Instant lastProcessed;
    private final Long messages;
    private final String metricsProvider;
    private final Instant start;
    private final Map<String, Long> topIntegrations;
    private final Long uptimeDuration;

    @SuppressWarnings("unchecked") // safe covariant cast
    private ImmutableIntegrationMetricsSummary(
            Optional<String> id,
            Long errors,
            Optional<? extends List<IntegrationDeploymentMetrics>> integrationDeploymentMetrics,
            Optional<? extends Instant> lastProcessed,
            Long messages,
            String metricsProvider,
            Optional<? extends Instant> start,
            Optional<? extends Map<String, Long>> topIntegrations,
            Long uptimeDuration) {
        this.id = id.orElse(null);
        this.errors = errors;
        this.integrationDeploymentMetrics =
                integrationDeploymentMetrics.orElse(null);
        this.lastProcessed = lastProcessed.orElse(null);
        this.messages = messages;
        this.metricsProvider = metricsProvider;
        this.start = start.orElse(null);
        this.topIntegrations = topIntegrations.orElse(null);
        this.uptimeDuration = uptimeDuration;
    }

    private ImmutableIntegrationMetricsSummary(
            ImmutableIntegrationMetricsSummary original,
            String id,
            Long errors,
            List<IntegrationDeploymentMetrics> integrationDeploymentMetrics,
            Instant lastProcessed,
            Long messages,
            String metricsProvider,
            Instant start,
            Map<String, Long> topIntegrations,
            Long uptimeDuration) {
        this.id = id;
        this.errors = errors;
        this.integrationDeploymentMetrics = integrationDeploymentMetrics;
        this.lastProcessed = lastProcessed;
        this.messages = messages;
        this.metricsProvider = metricsProvider;
        this.start = start;
        this.topIntegrations = topIntegrations;
        this.uptimeDuration = uptimeDuration;
    }

    /**
     * Construct a new immutable {@code IntegrationMetricsSummary} instance.
     *
     * @param id                           The value for the {@code id} attribute
     * @param errors                       The value for the {@code errors} attribute
     * @param integrationDeploymentMetrics The value for the {@code integrationDeploymentMetrics} attribute
     * @param lastProcessed                The value for the {@code lastProcessed} attribute
     * @param messages                     The value for the {@code messages} attribute
     * @param metricsProvider              The value for the {@code metricsProvider} attribute
     * @param start                        The value for the {@code start} attribute
     * @param topIntegrations              The value for the {@code topIntegrations} attribute
     * @param uptimeDuration               The value for the {@code uptimeDuration} attribute
     * @return An immutable IntegrationMetricsSummary instance
     */
    public static IntegrationMetricsSummary of(Optional<String> id, Long errors,
                                               Optional<? extends List<IntegrationDeploymentMetrics>> integrationDeploymentMetrics,
                                               Optional<? extends Instant> lastProcessed,
                                               Long messages,
                                               String metricsProvider,
                                               Optional<? extends Instant> start,
                                               Optional<? extends Map<String, Long>> topIntegrations,
                                               Long uptimeDuration) {
        return validate(new ImmutableIntegrationMetricsSummary(id, errors,
                integrationDeploymentMetrics, lastProcessed, messages,
                metricsProvider, start, topIntegrations, uptimeDuration));
    }

    private static ImmutableIntegrationMetricsSummary validate(
            ImmutableIntegrationMetricsSummary instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.metrics.IntegrationMetricsSummary} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable IntegrationMetricsSummary instance
     */
    public static IntegrationMetricsSummary copyOf(
            IntegrationMetricsSummary instance) {
        if (instance instanceof ImmutableIntegrationMetricsSummary) {
            return (ImmutableIntegrationMetricsSummary) instance;
        }
        return new IntegrationMetricsSummary.Builder()
                .createFrom(instance)
                .build();
    }

    /**
     * @return The value of the {@code id} attribute
     */
    @JsonProperty("id")
    @Override
    public Optional<String> getId() {
        return Optional.ofNullable(id);
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
     * @return The value of the {@code integrationDeploymentMetrics} attribute
     */
    @JsonProperty("integrationDeploymentMetrics")
    @Override
    public Optional<List<IntegrationDeploymentMetrics>> getIntegrationDeploymentMetrics() {
        return Optional.ofNullable(integrationDeploymentMetrics);
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
     * @return The value of the {@code messages} attribute
     */
    @JsonProperty("messages")
    @Override
    public Long getMessages() {
        return messages;
    }

    /**
     * @return The value of the {@code metricsProvider} attribute
     */
    @JsonProperty("metricsProvider")
    @Override
    public String getMetricsProvider() {
        return metricsProvider;
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
     * @return The value of the {@code topIntegrations} attribute
     */
    @JsonProperty("topIntegrations")
    @Override
    public Optional<Map<String, Long>> getTopIntegrations() {
        return Optional.ofNullable(topIntegrations);
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
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.metrics.IntegrationMetricsSummary#getId() id} attribute.
     *
     * @param value The value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegrationMetricsSummary withId(String value) {
        String newValue = Objects.requireNonNull(value, "id");
        if (Objects.equals(this.id, newValue)) {
            return this;
        }
        return validate(new ImmutableIntegrationMetricsSummary(
                this,
                newValue,
                this.errors,
                this.integrationDeploymentMetrics,
                this.lastProcessed,
                this.messages,
                this.metricsProvider,
                this.start,
                this.topIntegrations,
                this.uptimeDuration));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.metrics.IntegrationMetricsSummary#getId() id} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegrationMetricsSummary withId(
            Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.id, value)) {
            return this;
        }
        return validate(new ImmutableIntegrationMetricsSummary(
                this,
                value,
                this.errors,
                this.integrationDeploymentMetrics,
                this.lastProcessed,
                this.messages,
                this.metricsProvider,
                this.start,
                this.topIntegrations,
                this.uptimeDuration));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.metrics.IntegrationMetricsSummary#getErrors() errors} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for errors (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableIntegrationMetricsSummary withErrors(Long value) {
        if (Objects.equals(this.errors, value)) {
            return this;
        }
        return validate(new ImmutableIntegrationMetricsSummary(
                this,
                this.id,
                value,
                this.integrationDeploymentMetrics,
                this.lastProcessed,
                this.messages,
                this.metricsProvider,
                this.start,
                this.topIntegrations,
                this.uptimeDuration));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.metrics.IntegrationMetricsSummary#getIntegrationDeploymentMetrics() integrationDeploymentMetrics} attribute.
     *
     * @param value The value for integrationDeploymentMetrics
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegrationMetricsSummary withIntegrationDeploymentMetrics(
            List<IntegrationDeploymentMetrics> value) {
        List<IntegrationDeploymentMetrics> newValue =
                Objects.requireNonNull(value, "integrationDeploymentMetrics");
        if (this.integrationDeploymentMetrics == newValue) {
            return this;
        }
        return validate(new ImmutableIntegrationMetricsSummary(
                this,
                this.id,
                this.errors,
                newValue,
                this.lastProcessed,
                this.messages,
                this.metricsProvider,
                this.start,
                this.topIntegrations,
                this.uptimeDuration));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.metrics.IntegrationMetricsSummary#getIntegrationDeploymentMetrics() integrationDeploymentMetrics} attribute.
     * A shallow reference equality check is used on unboxed optional value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for integrationDeploymentMetrics
     * @return A modified copy of {@code this} object
     */
    @SuppressWarnings("unchecked") // safe covariant cast
    public final ImmutableIntegrationMetricsSummary withIntegrationDeploymentMetrics(
            Optional<? extends List<IntegrationDeploymentMetrics>> optional) {
        List<IntegrationDeploymentMetrics> value = optional.orElse(null);
        if (this.integrationDeploymentMetrics == value) {
            return this;
        }
        return validate(new ImmutableIntegrationMetricsSummary(
                this,
                this.id,
                this.errors,
                value,
                this.lastProcessed,
                this.messages,
                this.metricsProvider,
                this.start,
                this.topIntegrations,
                this.uptimeDuration));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.metrics.IntegrationMetricsSummary#getLastProcessed() lastProcessed} attribute.
     *
     * @param value The value for lastProcessed
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegrationMetricsSummary withLastProcessed(
            Instant value) {
        Instant newValue = Objects.requireNonNull(value, "lastProcessed");
        if (this.lastProcessed == newValue) {
            return this;
        }
        return validate(new ImmutableIntegrationMetricsSummary(
                this,
                this.id,
                this.errors,
                this.integrationDeploymentMetrics,
                newValue,
                this.messages,
                this.metricsProvider,
                this.start,
                this.topIntegrations,
                this.uptimeDuration));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.metrics.IntegrationMetricsSummary#getLastProcessed() lastProcessed} attribute.
     * A shallow reference equality check is used on unboxed optional value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for lastProcessed
     * @return A modified copy of {@code this} object
     */
    @SuppressWarnings("unchecked") // safe covariant cast
    public final ImmutableIntegrationMetricsSummary withLastProcessed(
            Optional<? extends Instant> optional) {
        Instant value = optional.orElse(null);
        if (this.lastProcessed == value) {
            return this;
        }
        return validate(new ImmutableIntegrationMetricsSummary(
                this,
                this.id,
                this.errors,
                this.integrationDeploymentMetrics,
                value,
                this.messages,
                this.metricsProvider,
                this.start,
                this.topIntegrations,
                this.uptimeDuration));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.metrics.IntegrationMetricsSummary#getMessages() messages} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for messages (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableIntegrationMetricsSummary withMessages(Long value) {
        if (Objects.equals(this.messages, value)) {
            return this;
        }
        return validate(new ImmutableIntegrationMetricsSummary(
                this,
                this.id,
                this.errors,
                this.integrationDeploymentMetrics,
                this.lastProcessed,
                value,
                this.metricsProvider,
                this.start,
                this.topIntegrations,
                this.uptimeDuration));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.metrics.IntegrationMetricsSummary#getMetricsProvider() metricsProvider} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for metricsProvider (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableIntegrationMetricsSummary withMetricsProvider(
            String value) {
        if (Objects.equals(this.metricsProvider, value)) {
            return this;
        }
        return validate(new ImmutableIntegrationMetricsSummary(
                this,
                this.id,
                this.errors,
                this.integrationDeploymentMetrics,
                this.lastProcessed,
                this.messages,
                value,
                this.start,
                this.topIntegrations,
                this.uptimeDuration));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.metrics.IntegrationMetricsSummary#getStart() start} attribute.
     *
     * @param value The value for start
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegrationMetricsSummary withStart(Instant value) {
        Instant newValue = Objects.requireNonNull(value, "start");
        if (this.start == newValue) {
            return this;
        }
        return validate(new ImmutableIntegrationMetricsSummary(
                this,
                this.id,
                this.errors,
                this.integrationDeploymentMetrics,
                this.lastProcessed,
                this.messages,
                this.metricsProvider,
                newValue,
                this.topIntegrations,
                this.uptimeDuration));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.metrics.IntegrationMetricsSummary#getStart() start} attribute.
     * A shallow reference equality check is used on unboxed optional value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for start
     * @return A modified copy of {@code this} object
     */
    @SuppressWarnings("unchecked") // safe covariant cast
    public final ImmutableIntegrationMetricsSummary withStart(
            Optional<? extends Instant> optional) {
        Instant value = optional.orElse(null);
        if (this.start == value) {
            return this;
        }
        return validate(new ImmutableIntegrationMetricsSummary(
                this,
                this.id,
                this.errors,
                this.integrationDeploymentMetrics,
                this.lastProcessed,
                this.messages,
                this.metricsProvider,
                value,
                this.topIntegrations,
                this.uptimeDuration));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.metrics.IntegrationMetricsSummary#getTopIntegrations() topIntegrations} attribute.
     *
     * @param value The value for topIntegrations
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegrationMetricsSummary withTopIntegrations(
            Map<String, Long> value) {
        Map<String, Long> newValue =
                Objects.requireNonNull(value, "topIntegrations");
        if (this.topIntegrations == newValue) {
            return this;
        }
        return validate(new ImmutableIntegrationMetricsSummary(
                this,
                this.id,
                this.errors,
                this.integrationDeploymentMetrics,
                this.lastProcessed,
                this.messages,
                this.metricsProvider,
                this.start,
                newValue,
                this.uptimeDuration));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.metrics.IntegrationMetricsSummary#getTopIntegrations() topIntegrations} attribute.
     * A shallow reference equality check is used on unboxed optional value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for topIntegrations
     * @return A modified copy of {@code this} object
     */
    @SuppressWarnings("unchecked") // safe covariant cast
    public final ImmutableIntegrationMetricsSummary withTopIntegrations(
            Optional<? extends Map<String, Long>> optional) {
        Map<String, Long> value = optional.orElse(null);
        if (this.topIntegrations == value) {
            return this;
        }
        return validate(new ImmutableIntegrationMetricsSummary(
                this,
                this.id,
                this.errors,
                this.integrationDeploymentMetrics,
                this.lastProcessed,
                this.messages,
                this.metricsProvider,
                this.start,
                value,
                this.uptimeDuration));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.metrics.IntegrationMetricsSummary#getUptimeDuration() uptimeDuration} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for uptimeDuration (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableIntegrationMetricsSummary withUptimeDuration(
            Long value) {
        if (Objects.equals(this.uptimeDuration, value)) {
            return this;
        }
        return validate(new ImmutableIntegrationMetricsSummary(
                this,
                this.id,
                this.errors,
                this.integrationDeploymentMetrics,
                this.lastProcessed,
                this.messages,
                this.metricsProvider,
                this.start,
                this.topIntegrations,
                value));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableIntegrationMetricsSummary} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableIntegrationMetricsSummary
                && equalTo((ImmutableIntegrationMetricsSummary) another);
    }

    private boolean equalTo(ImmutableIntegrationMetricsSummary another) {
        return Objects.equals(id, another.id)
                && Objects.equals(errors, another.errors)
                && Objects.equals(integrationDeploymentMetrics,
                another.integrationDeploymentMetrics)
                && Objects.equals(lastProcessed, another.lastProcessed)
                && Objects.equals(messages, another.messages)
                && Objects.equals(metricsProvider, another.metricsProvider)
                && Objects.equals(start, another.start)
                && Objects.equals(topIntegrations, another.topIntegrations)
                && Objects.equals(uptimeDuration, another.uptimeDuration);
    }

    /**
     * Computes a hash code from attributes: {@code id}, {@code errors}, {@code integrationDeploymentMetrics}, {@code lastProcessed}, {@code messages}, {@code metricsProvider}, {@code start}, {@code topIntegrations}, {@code uptimeDuration}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(id);
        h += (h << 5) + Objects.hashCode(errors);
        h += (h << 5) + Objects.hashCode(integrationDeploymentMetrics);
        h += (h << 5) + Objects.hashCode(lastProcessed);
        h += (h << 5) + Objects.hashCode(messages);
        h += (h << 5) + Objects.hashCode(metricsProvider);
        h += (h << 5) + Objects.hashCode(start);
        h += (h << 5) + Objects.hashCode(topIntegrations);
        h += (h << 5) + Objects.hashCode(uptimeDuration);
        return h;
    }

    /**
     * Prints the immutable value {@code IntegrationMetricsSummary} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("IntegrationMetricsSummary{");
        if (id != null) {
            builder.append("id=").append(id);
        }
        if (errors != null) {
            if (builder.length() > 26) {
                builder.append(", ");
            }
            builder.append("errors=").append(errors);
        }
        if (integrationDeploymentMetrics != null) {
            if (builder.length() > 26) {
                builder.append(", ");
            }
            builder.append("integrationDeploymentMetrics=")
                    .append(integrationDeploymentMetrics);
        }
        if (lastProcessed != null) {
            if (builder.length() > 26) {
                builder.append(", ");
            }
            builder.append("lastProcessed=").append(lastProcessed);
        }
        if (messages != null) {
            if (builder.length() > 26) {
                builder.append(", ");
            }
            builder.append("messages=").append(messages);
        }
        if (metricsProvider != null) {
            if (builder.length() > 26) {
                builder.append(", ");
            }
            builder.append("metricsProvider=").append(metricsProvider);
        }
        if (start != null) {
            if (builder.length() > 26) {
                builder.append(", ");
            }
            builder.append("start=").append(start);
        }
        if (topIntegrations != null) {
            if (builder.length() > 26) {
                builder.append(", ");
            }
            builder.append("topIntegrations=").append(topIntegrations);
        }
        if (uptimeDuration != null) {
            if (builder.length() > 26) {
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
     * Builds instances of type {@link io.syndesis.common.model.metrics.IntegrationMetricsSummary IntegrationMetricsSummary}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private String id;
        private Long errors;
        private List<IntegrationDeploymentMetrics> integrationDeploymentMetrics;
        private Instant lastProcessed;
        private Long messages;
        private String metricsProvider;
        private Instant start;
        private Map<String, Long> topIntegrations;
        private Long uptimeDuration;

        /**
         * Creates a builder for {@link io.syndesis.common.model.metrics.IntegrationMetricsSummary IntegrationMetricsSummary} instances.
         * <pre>
         * new IntegrationMetricsSummary.Builder()
         *    .id(String) // optional {@link io.syndesis.common.model.metrics.IntegrationMetricsSummary#getId() id}
         *    .errors(Long | null) // nullable {@link io.syndesis.common.model.metrics.IntegrationMetricsSummary#getErrors() errors}
         *    .integrationDeploymentMetrics(List&amp;lt;io.syndesis.common.io.syndesis.common.model.metrics.IntegrationDeploymentMetrics&amp;gt;) // optional {@link io.syndesis.common.model.metrics.IntegrationMetricsSummary#getIntegrationDeploymentMetrics() integrationDeploymentMetrics}
         *    .lastProcessed(java.time.Instant) // optional {@link io.syndesis.common.model.metrics.IntegrationMetricsSummary#getLastProcessed() lastProcessed}
         *    .messages(Long | null) // nullable {@link io.syndesis.common.model.metrics.IntegrationMetricsSummary#getMessages() messages}
         *    .metricsProvider(String | null) // nullable {@link io.syndesis.common.model.metrics.IntegrationMetricsSummary#getMetricsProvider() metricsProvider}
         *    .start(java.time.Instant) // optional {@link io.syndesis.common.model.metrics.IntegrationMetricsSummary#getStart() start}
         *    .topIntegrations(Map&amp;lt;String, Long&amp;gt;) // optional {@link io.syndesis.common.model.metrics.IntegrationMetricsSummary#getTopIntegrations() topIntegrations}
         *    .uptimeDuration(Long | null) // nullable {@link io.syndesis.common.model.metrics.IntegrationMetricsSummary#getUptimeDuration() uptimeDuration}
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof IntegrationMetricsSummary.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new IntegrationMetricsSummary.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithResourceId} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationMetricsSummary.Builder createFrom(
                WithResourceId instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (IntegrationMetricsSummary.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.metrics.IntegrationMetricsSummary} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationMetricsSummary.Builder createFrom(
                IntegrationMetricsSummary instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (IntegrationMetricsSummary.Builder) this;
        }

        private void from(Object object) {
            if (object instanceof WithResourceId) {
                WithResourceId instance = (WithResourceId) object;
                Optional<String> idOptional = instance.getId();
                if (idOptional.isPresent()) {
                    id(idOptional);
                }
            }
            if (object instanceof IntegrationMetricsSummary) {
                IntegrationMetricsSummary instance =
                        (IntegrationMetricsSummary) object;
                Optional<List<IntegrationDeploymentMetrics>>
                        integrationDeploymentMetricsOptional =
                        instance.getIntegrationDeploymentMetrics();
                if (integrationDeploymentMetricsOptional.isPresent()) {
                    integrationDeploymentMetrics(
                            integrationDeploymentMetricsOptional);
                }
                Optional<Instant> lastProcessedOptional =
                        instance.getLastProcessed();
                if (lastProcessedOptional.isPresent()) {
                    lastProcessed(lastProcessedOptional);
                }
                String metricsProviderValue = instance.getMetricsProvider();
                if (metricsProviderValue != null) {
                    metricsProvider(metricsProviderValue);
                }
                Optional<Map<String, Long>> topIntegrationsOptional =
                        instance.getTopIntegrations();
                if (topIntegrationsOptional.isPresent()) {
                    topIntegrations(topIntegrationsOptional);
                }
                Long uptimeDurationValue = instance.getUptimeDuration();
                if (uptimeDurationValue != null) {
                    uptimeDuration(uptimeDurationValue);
                }
                Optional<Instant> startOptional = instance.getStart();
                if (startOptional.isPresent()) {
                    start(startOptional);
                }
                Long messagesValue = instance.getMessages();
                if (messagesValue != null) {
                    messages(messagesValue);
                }
                Long errorsValue = instance.getErrors();
                if (errorsValue != null) {
                    errors(errorsValue);
                }
            }
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.metrics.IntegrationMetricsSummary#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationMetricsSummary.Builder id(String id) {
            this.id = Objects.requireNonNull(id, "id");
            return (IntegrationMetricsSummary.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.metrics.IntegrationMetricsSummary#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("id")
        public final IntegrationMetricsSummary.Builder id(Optional<String> id) {
            this.id = id.orElse(null);
            return (IntegrationMetricsSummary.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.metrics.IntegrationMetricsSummary#getErrors() errors} attribute.
         *
         * @param errors The value for errors (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("errors")
        public final IntegrationMetricsSummary.Builder errors(Long errors) {
            this.errors = errors;
            return (IntegrationMetricsSummary.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.metrics.IntegrationMetricsSummary#getIntegrationDeploymentMetrics() integrationDeploymentMetrics} to integrationDeploymentMetrics.
         *
         * @param integrationDeploymentMetrics The value for integrationDeploymentMetrics
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationMetricsSummary.Builder integrationDeploymentMetrics(
                List<IntegrationDeploymentMetrics> integrationDeploymentMetrics) {
            this.integrationDeploymentMetrics =
                    Objects.requireNonNull(integrationDeploymentMetrics,
                            "integrationDeploymentMetrics");
            return (IntegrationMetricsSummary.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.metrics.IntegrationMetricsSummary#getIntegrationDeploymentMetrics() integrationDeploymentMetrics} to integrationDeploymentMetrics.
         *
         * @param integrationDeploymentMetrics The value for integrationDeploymentMetrics
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("integrationDeploymentMetrics")
        public final IntegrationMetricsSummary.Builder integrationDeploymentMetrics(
                Optional<? extends List<IntegrationDeploymentMetrics>> integrationDeploymentMetrics) {
            this.integrationDeploymentMetrics =
                    integrationDeploymentMetrics.orElse(null);
            return (IntegrationMetricsSummary.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.metrics.IntegrationMetricsSummary#getLastProcessed() lastProcessed} to lastProcessed.
         *
         * @param lastProcessed The value for lastProcessed
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationMetricsSummary.Builder lastProcessed(
                Instant lastProcessed) {
            this.lastProcessed =
                    Objects.requireNonNull(lastProcessed, "lastProcessed");
            return (IntegrationMetricsSummary.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.metrics.IntegrationMetricsSummary#getLastProcessed() lastProcessed} to lastProcessed.
         *
         * @param lastProcessed The value for lastProcessed
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("lastProcessed")
        public final IntegrationMetricsSummary.Builder lastProcessed(
                Optional<? extends Instant> lastProcessed) {
            this.lastProcessed = lastProcessed.orElse(null);
            return (IntegrationMetricsSummary.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.metrics.IntegrationMetricsSummary#getMessages() messages} attribute.
         *
         * @param messages The value for messages (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("messages")
        public final IntegrationMetricsSummary.Builder messages(Long messages) {
            this.messages = messages;
            return (IntegrationMetricsSummary.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.metrics.IntegrationMetricsSummary#getMetricsProvider() metricsProvider} attribute.
         *
         * @param metricsProvider The value for metricsProvider (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("metricsProvider")
        public final IntegrationMetricsSummary.Builder metricsProvider(
                String metricsProvider) {
            this.metricsProvider = metricsProvider;
            return (IntegrationMetricsSummary.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.metrics.IntegrationMetricsSummary#getStart() start} to start.
         *
         * @param start The value for start
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationMetricsSummary.Builder start(Instant start) {
            this.start = Objects.requireNonNull(start, "start");
            return (IntegrationMetricsSummary.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.metrics.IntegrationMetricsSummary#getStart() start} to start.
         *
         * @param start The value for start
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("start")
        public final IntegrationMetricsSummary.Builder start(
                Optional<? extends Instant> start) {
            this.start = start.orElse(null);
            return (IntegrationMetricsSummary.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.metrics.IntegrationMetricsSummary#getTopIntegrations() topIntegrations} to topIntegrations.
         *
         * @param topIntegrations The value for topIntegrations
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationMetricsSummary.Builder topIntegrations(
                Map<String, Long> topIntegrations) {
            this.topIntegrations =
                    Objects.requireNonNull(topIntegrations, "topIntegrations");
            return (IntegrationMetricsSummary.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.metrics.IntegrationMetricsSummary#getTopIntegrations() topIntegrations} to topIntegrations.
         *
         * @param topIntegrations The value for topIntegrations
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("topIntegrations")
        public final IntegrationMetricsSummary.Builder topIntegrations(
                Optional<? extends Map<String, Long>> topIntegrations) {
            this.topIntegrations = topIntegrations.orElse(null);
            return (IntegrationMetricsSummary.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.metrics.IntegrationMetricsSummary#getUptimeDuration() uptimeDuration} attribute.
         *
         * @param uptimeDuration The value for uptimeDuration (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("uptimeDuration")
        public final IntegrationMetricsSummary.Builder uptimeDuration(
                Long uptimeDuration) {
            this.uptimeDuration = uptimeDuration;
            return (IntegrationMetricsSummary.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.metrics.IntegrationMetricsSummary IntegrationMetricsSummary}.
         *
         * @return An immutable instance of IntegrationMetricsSummary
         * @throws IllegalStateException if any required attributes are missing
         */
        public IntegrationMetricsSummary build() {
            return ImmutableIntegrationMetricsSummary.validate(
                    new ImmutableIntegrationMetricsSummary(
                            null,
                            id,
                            errors,
                            integrationDeploymentMetrics,
                            lastProcessed,
                            messages,
                            metricsProvider,
                            start,
                            topIntegrations,
                            uptimeDuration));
        }
    }
}
