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
import io.syndesis.common.model.WithResourceId;

import java.util.Objects;
import java.util.Optional;

/**
 * Immutable implementation of {@link io.syndesis.common.model.integration.IntegrationEndpoint}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new IntegrationEndpoint.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableIntegrationEndpoint.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableIntegrationEndpoint
        implements IntegrationEndpoint {
    private final String id;
    private final String protocol;
    private final String host;
    private final String contextPath;

    private ImmutableIntegrationEndpoint(
            Optional<String> id,
            Optional<String> protocol,
            Optional<String> host,
            Optional<String> contextPath) {
        this.id = id.orElse(null);
        this.protocol = protocol.orElse(null);
        this.host = host.orElse(null);
        this.contextPath = contextPath.orElse(null);
    }

    private ImmutableIntegrationEndpoint(
            ImmutableIntegrationEndpoint original,
            String id,
            String protocol,
            String host,
            String contextPath) {
        this.id = id;
        this.protocol = protocol;
        this.host = host;
        this.contextPath = contextPath;
    }

    /**
     * Construct a new immutable {@code IntegrationEndpoint} instance.
     *
     * @param id          The value for the {@code id} attribute
     * @param protocol    The value for the {@code protocol} attribute
     * @param host        The value for the {@code host} attribute
     * @param contextPath The value for the {@code contextPath} attribute
     * @return An immutable IntegrationEndpoint instance
     */
    public static IntegrationEndpoint of(Optional<String> id,
                                         Optional<String> protocol,
                                         Optional<String> host,
                                         Optional<String> contextPath) {
        return validate(new ImmutableIntegrationEndpoint(id, protocol, host,
                contextPath));
    }

    private static ImmutableIntegrationEndpoint validate(
            ImmutableIntegrationEndpoint instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.integration.IntegrationEndpoint} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable IntegrationEndpoint instance
     */
    public static IntegrationEndpoint copyOf(IntegrationEndpoint instance) {
        if (instance instanceof ImmutableIntegrationEndpoint) {
            return (ImmutableIntegrationEndpoint) instance;
        }
        return new IntegrationEndpoint.Builder()
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
     * @return The value of the {@code protocol} attribute
     */
    @JsonProperty("protocol")
    @Override
    public Optional<String> getProtocol() {
        return Optional.ofNullable(protocol);
    }

    /**
     * @return The value of the {@code host} attribute
     */
    @JsonProperty("host")
    @Override
    public Optional<String> getHost() {
        return Optional.ofNullable(host);
    }

    /**
     * @return The value of the {@code contextPath} attribute
     */
    @JsonProperty("contextPath")
    @Override
    public Optional<String> getContextPath() {
        return Optional.ofNullable(contextPath);
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.integration.IntegrationEndpoint#getId() id} attribute.
     *
     * @param value The value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegrationEndpoint withId(String value) {
        String newValue = Objects.requireNonNull(value, "id");
        if (Objects.equals(this.id, newValue)) {
            return this;
        }
        return validate(
                new ImmutableIntegrationEndpoint(this, newValue, this.protocol,
                        this.host, this.contextPath));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.integration.IntegrationEndpoint#getId() id} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegrationEndpoint withId(
            Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.id, value)) {
            return this;
        }
        return validate(
                new ImmutableIntegrationEndpoint(this, value, this.protocol,
                        this.host, this.contextPath));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.integration.IntegrationEndpoint#getProtocol() protocol} attribute.
     *
     * @param value The value for protocol
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegrationEndpoint withProtocol(String value) {
        String newValue = Objects.requireNonNull(value, "protocol");
        if (Objects.equals(this.protocol, newValue)) {
            return this;
        }
        return validate(
                new ImmutableIntegrationEndpoint(this, this.id, newValue,
                        this.host, this.contextPath));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.integration.IntegrationEndpoint#getProtocol() protocol} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for protocol
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegrationEndpoint withProtocol(
            Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.protocol, value)) {
            return this;
        }
        return validate(new ImmutableIntegrationEndpoint(this, this.id, value,
                this.host, this.contextPath));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.integration.IntegrationEndpoint#getHost() host} attribute.
     *
     * @param value The value for host
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegrationEndpoint withHost(String value) {
        String newValue = Objects.requireNonNull(value, "host");
        if (Objects.equals(this.host, newValue)) {
            return this;
        }
        return validate(
                new ImmutableIntegrationEndpoint(this, this.id, this.protocol,
                        newValue, this.contextPath));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.integration.IntegrationEndpoint#getHost() host} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for host
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegrationEndpoint withHost(
            Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.host, value)) {
            return this;
        }
        return validate(
                new ImmutableIntegrationEndpoint(this, this.id, this.protocol,
                        value, this.contextPath));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.integration.IntegrationEndpoint#getContextPath() contextPath} attribute.
     *
     * @param value The value for contextPath
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegrationEndpoint withContextPath(String value) {
        String newValue = Objects.requireNonNull(value, "contextPath");
        if (Objects.equals(this.contextPath, newValue)) {
            return this;
        }
        return validate(
                new ImmutableIntegrationEndpoint(this, this.id, this.protocol,
                        this.host, newValue));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.integration.IntegrationEndpoint#getContextPath() contextPath} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for contextPath
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegrationEndpoint withContextPath(
            Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.contextPath, value)) {
            return this;
        }
        return validate(
                new ImmutableIntegrationEndpoint(this, this.id, this.protocol,
                        this.host, value));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableIntegrationEndpoint} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableIntegrationEndpoint
                && equalTo((ImmutableIntegrationEndpoint) another);
    }

    private boolean equalTo(ImmutableIntegrationEndpoint another) {
        return Objects.equals(id, another.id)
                && Objects.equals(protocol, another.protocol)
                && Objects.equals(host, another.host)
                && Objects.equals(contextPath, another.contextPath);
    }

    /**
     * Computes a hash code from attributes: {@code id}, {@code protocol}, {@code host}, {@code contextPath}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(id);
        h += (h << 5) + Objects.hashCode(protocol);
        h += (h << 5) + Objects.hashCode(host);
        h += (h << 5) + Objects.hashCode(contextPath);
        return h;
    }

    /**
     * Prints the immutable value {@code IntegrationEndpoint} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("IntegrationEndpoint{");
        if (id != null) {
            builder.append("id=").append(id);
        }
        if (protocol != null) {
            if (builder.length() > 20) {
                builder.append(", ");
            }
            builder.append("protocol=").append(protocol);
        }
        if (host != null) {
            if (builder.length() > 20) {
                builder.append(", ");
            }
            builder.append("host=").append(host);
        }
        if (contextPath != null) {
            if (builder.length() > 20) {
                builder.append(", ");
            }
            builder.append("contextPath=").append(contextPath);
        }
        return builder.append("}").toString();
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.integration.IntegrationEndpoint IntegrationEndpoint}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private String id;
        private String protocol;
        private String host;
        private String contextPath;

        /**
         * Creates a builder for {@link io.syndesis.common.model.integration.IntegrationEndpoint IntegrationEndpoint} instances.
         * <pre>
         * new IntegrationEndpoint.Builder()
         *    .id(String) // optional {@link io.syndesis.common.model.integration.IntegrationEndpoint#getId() id}
         *    .protocol(String) // optional {@link io.syndesis.common.model.integration.IntegrationEndpoint#getProtocol() protocol}
         *    .host(String) // optional {@link io.syndesis.common.model.integration.IntegrationEndpoint#getHost() host}
         *    .contextPath(String) // optional {@link io.syndesis.common.model.integration.IntegrationEndpoint#getContextPath() contextPath}
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof IntegrationEndpoint.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new IntegrationEndpoint.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.integration.IntegrationEndpoint} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationEndpoint.Builder createFrom(
                IntegrationEndpoint instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (IntegrationEndpoint.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithResourceId} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationEndpoint.Builder createFrom(
                WithResourceId instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (IntegrationEndpoint.Builder) this;
        }

        private void from(Object object) {
            if (object instanceof IntegrationEndpoint) {
                IntegrationEndpoint instance = (IntegrationEndpoint) object;
                Optional<String> hostOptional = instance.getHost();
                if (hostOptional.isPresent()) {
                    host(hostOptional);
                }
                Optional<String> contextPathOptional =
                        instance.getContextPath();
                if (contextPathOptional.isPresent()) {
                    contextPath(contextPathOptional);
                }
                Optional<String> protocolOptional = instance.getProtocol();
                if (protocolOptional.isPresent()) {
                    protocol(protocolOptional);
                }
            }
            if (object instanceof WithResourceId) {
                WithResourceId instance = (WithResourceId) object;
                Optional<String> idOptional = instance.getId();
                if (idOptional.isPresent()) {
                    id(idOptional);
                }
            }
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.integration.IntegrationEndpoint#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationEndpoint.Builder id(String id) {
            this.id = Objects.requireNonNull(id, "id");
            return (IntegrationEndpoint.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.integration.IntegrationEndpoint#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("id")
        public final IntegrationEndpoint.Builder id(Optional<String> id) {
            this.id = id.orElse(null);
            return (IntegrationEndpoint.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.integration.IntegrationEndpoint#getProtocol() protocol} to protocol.
         *
         * @param protocol The value for protocol
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationEndpoint.Builder protocol(String protocol) {
            this.protocol = Objects.requireNonNull(protocol, "protocol");
            return (IntegrationEndpoint.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.integration.IntegrationEndpoint#getProtocol() protocol} to protocol.
         *
         * @param protocol The value for protocol
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("protocol")
        public final IntegrationEndpoint.Builder protocol(
                Optional<String> protocol) {
            this.protocol = protocol.orElse(null);
            return (IntegrationEndpoint.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.integration.IntegrationEndpoint#getHost() host} to host.
         *
         * @param host The value for host
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationEndpoint.Builder host(String host) {
            this.host = Objects.requireNonNull(host, "host");
            return (IntegrationEndpoint.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.integration.IntegrationEndpoint#getHost() host} to host.
         *
         * @param host The value for host
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("host")
        public final IntegrationEndpoint.Builder host(Optional<String> host) {
            this.host = host.orElse(null);
            return (IntegrationEndpoint.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.integration.IntegrationEndpoint#getContextPath() contextPath} to contextPath.
         *
         * @param contextPath The value for contextPath
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationEndpoint.Builder contextPath(
                String contextPath) {
            this.contextPath =
                    Objects.requireNonNull(contextPath, "contextPath");
            return (IntegrationEndpoint.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.integration.IntegrationEndpoint#getContextPath() contextPath} to contextPath.
         *
         * @param contextPath The value for contextPath
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("contextPath")
        public final IntegrationEndpoint.Builder contextPath(
                Optional<String> contextPath) {
            this.contextPath = contextPath.orElse(null);
            return (IntegrationEndpoint.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.integration.IntegrationEndpoint IntegrationEndpoint}.
         *
         * @return An immutable instance of IntegrationEndpoint
         * @throws IllegalStateException if any required attributes are missing
         */
        public IntegrationEndpoint build() {
            return ImmutableIntegrationEndpoint.validate(
                    new ImmutableIntegrationEndpoint(null, id, protocol, host,
                            contextPath));
        }
    }
}
