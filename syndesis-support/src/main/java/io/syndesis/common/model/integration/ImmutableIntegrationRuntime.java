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
import io.syndesis.common.model.environment.Environment;

import java.io.ObjectStreamException;
import java.util.Objects;
import java.util.Optional;

/**
 * Immutable implementation of {@link io.syndesis.common.model.integration.IntegrationRuntime}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new IntegrationRuntime.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableIntegrationRuntime.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableIntegrationRuntime
        implements IntegrationRuntime {
    private final String id;
    private final String state;
    private final Integration integration;
    private final Environment environment;

    private ImmutableIntegrationRuntime(
            Optional<String> id,
            String state,
            Integration integration,
            Environment environment) {
        this.id = id.orElse(null);
        this.state = state;
        this.integration = integration;
        this.environment = environment;
    }

    private ImmutableIntegrationRuntime(
            ImmutableIntegrationRuntime original,
            String id,
            String state,
            Integration integration,
            Environment environment) {
        this.id = id;
        this.state = state;
        this.integration = integration;
        this.environment = environment;
    }

    /**
     * Construct a new immutable {@code IntegrationRuntime} instance.
     *
     * @param id          The value for the {@code id} attribute
     * @param state       The value for the {@code state} attribute
     * @param integration The value for the {@code integration} attribute
     * @param environment The value for the {@code environment} attribute
     * @return An immutable IntegrationRuntime instance
     */
    public static IntegrationRuntime of(Optional<String> id, String state,
                                        Integration integration,
                                        Environment environment) {
        return validate(new ImmutableIntegrationRuntime(id, state, integration,
                environment));
    }

    private static ImmutableIntegrationRuntime validate(
            ImmutableIntegrationRuntime instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.integration.IntegrationRuntime} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable IntegrationRuntime instance
     */
    public static IntegrationRuntime copyOf(IntegrationRuntime instance) {
        if (instance instanceof ImmutableIntegrationRuntime) {
            return (ImmutableIntegrationRuntime) instance;
        }
        return new IntegrationRuntime.Builder()
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
     * @return The value of the {@code state} attribute
     */
    @JsonProperty("state")
    @Override
    public String getState() {
        return state;
    }

    /**
     * @return The value of the {@code integration} attribute
     */
    @JsonProperty("integration")
    @Override
    public Integration getIntegration() {
        return integration;
    }

    /**
     * @return The value of the {@code environment} attribute
     */
    @JsonProperty("environment")
    @Override
    public Environment getEnvironment() {
        return environment;
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.integration.IntegrationRuntime#getId() id} attribute.
     *
     * @param value The value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegrationRuntime withId(String value) {
        String newValue = Objects.requireNonNull(value, "id");
        if (Objects.equals(this.id, newValue)) {
            return this;
        }
        return validate(
                new ImmutableIntegrationRuntime(this, newValue, this.state,
                        this.integration, this.environment));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.integration.IntegrationRuntime#getId() id} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegrationRuntime withId(Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.id, value)) {
            return this;
        }
        return validate(new ImmutableIntegrationRuntime(this, value, this.state,
                this.integration, this.environment));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.integration.IntegrationRuntime#getState() state} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for state (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableIntegrationRuntime withState(String value) {
        if (Objects.equals(this.state, value)) {
            return this;
        }
        return validate(new ImmutableIntegrationRuntime(this, this.id, value,
                this.integration, this.environment));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.integration.IntegrationRuntime#getIntegration() integration} attribute.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for integration (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableIntegrationRuntime withIntegration(
            Integration value) {
        if (this.integration == value) {
            return this;
        }
        return validate(
                new ImmutableIntegrationRuntime(this, this.id, this.state,
                        value, this.environment));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.integration.IntegrationRuntime#getEnvironment() environment} attribute.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for environment (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableIntegrationRuntime withEnvironment(
            Environment value) {
        if (this.environment == value) {
            return this;
        }
        return validate(
                new ImmutableIntegrationRuntime(this, this.id, this.state,
                        this.integration, value));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableIntegrationRuntime} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableIntegrationRuntime
                && equalTo((ImmutableIntegrationRuntime) another);
    }

    private boolean equalTo(ImmutableIntegrationRuntime another) {
        return Objects.equals(id, another.id)
                && Objects.equals(state, another.state)
                && Objects.equals(integration, another.integration)
                && Objects.equals(environment, another.environment);
    }

    /**
     * Computes a hash code from attributes: {@code id}, {@code state}, {@code integration}, {@code environment}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(id);
        h += (h << 5) + Objects.hashCode(state);
        h += (h << 5) + Objects.hashCode(integration);
        h += (h << 5) + Objects.hashCode(environment);
        return h;
    }

    /**
     * Prints the immutable value {@code IntegrationRuntime} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("IntegrationRuntime{");
        if (id != null) {
            builder.append("id=").append(id);
        }
        if (state != null) {
            if (builder.length() > 19) {
                builder.append(", ");
            }
            builder.append("state=").append(state);
        }
        if (integration != null) {
            if (builder.length() > 19) {
                builder.append(", ");
            }
            builder.append("integration=").append(integration);
        }
        if (environment != null) {
            if (builder.length() > 19) {
                builder.append(", ");
            }
            builder.append("environment=").append(environment);
        }
        return builder.append("}").toString();
    }

    private Object readResolve() throws ObjectStreamException {
        return validate(this);
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.integration.IntegrationRuntime IntegrationRuntime}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private String id;
        private String state;
        private Integration integration;
        private Environment environment;

        /**
         * Creates a builder for {@link io.syndesis.common.model.integration.IntegrationRuntime IntegrationRuntime} instances.
         * <pre>
         * new IntegrationRuntime.Builder()
         *    .id(String) // optional {@link io.syndesis.common.model.integration.IntegrationRuntime#getId() id}
         *    .state(String | null) // nullable {@link io.syndesis.common.model.integration.IntegrationRuntime#getState() state}
         *    .integration(io.syndesis.common.io.syndesis.common.model.integration.Integration | null) // nullable {@link io.syndesis.common.model.integration.IntegrationRuntime#getIntegration() integration}
         *    .environment(io.syndesis.common.io.syndesis.common.model.environment.Environment | null) // nullable {@link io.syndesis.common.model.integration.IntegrationRuntime#getEnvironment() environment}
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof IntegrationRuntime.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new IntegrationRuntime.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithResourceId} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationRuntime.Builder createFrom(
                WithResourceId instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (IntegrationRuntime.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.integration.IntegrationRuntime} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationRuntime.Builder createFrom(
                IntegrationRuntime instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (IntegrationRuntime.Builder) this;
        }

        private void from(Object object) {
            if (object instanceof WithResourceId) {
                WithResourceId instance = (WithResourceId) object;
                Optional<String> idOptional = instance.getId();
                if (idOptional.isPresent()) {
                    id(idOptional);
                }
            }
            if (object instanceof IntegrationRuntime) {
                IntegrationRuntime instance = (IntegrationRuntime) object;
                Integration integrationValue = instance.getIntegration();
                if (integrationValue != null) {
                    integration(integrationValue);
                }
                Environment environmentValue = instance.getEnvironment();
                if (environmentValue != null) {
                    environment(environmentValue);
                }
                String stateValue = instance.getState();
                if (stateValue != null) {
                    state(stateValue);
                }
            }
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.integration.IntegrationRuntime#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationRuntime.Builder id(String id) {
            this.id = Objects.requireNonNull(id, "id");
            return (IntegrationRuntime.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.integration.IntegrationRuntime#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("id")
        public final IntegrationRuntime.Builder id(Optional<String> id) {
            this.id = id.orElse(null);
            return (IntegrationRuntime.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.integration.IntegrationRuntime#getState() state} attribute.
         *
         * @param state The value for state (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("state")
        public final IntegrationRuntime.Builder state(String state) {
            this.state = state;
            return (IntegrationRuntime.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.integration.IntegrationRuntime#getIntegration() integration} attribute.
         *
         * @param integration The value for integration (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("integration")
        public final IntegrationRuntime.Builder integration(
                Integration integration) {
            this.integration = integration;
            return (IntegrationRuntime.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.integration.IntegrationRuntime#getEnvironment() environment} attribute.
         *
         * @param environment The value for environment (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("environment")
        public final IntegrationRuntime.Builder environment(
                Environment environment) {
            this.environment = environment;
            return (IntegrationRuntime.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.integration.IntegrationRuntime IntegrationRuntime}.
         *
         * @return An immutable instance of IntegrationRuntime
         * @throws IllegalStateException if any required attributes are missing
         */
        public IntegrationRuntime build() {
            return ImmutableIntegrationRuntime.validate(
                    new ImmutableIntegrationRuntime(null, id, state,
                            integration, environment));
        }
    }
}
