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
package io.syndesis.common.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Var;

import java.io.ObjectStreamException;
import java.util.Objects;
import java.util.Optional;

/**
 * Immutable implementation of {@link io.syndesis.common.model.user.Quota}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new Quota.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableQuota.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableQuota implements Quota {
    private final Integer maxIntegrationsPerUser;
    private final Integer maxDeploymentsPerUser;
    private final Integer usedIntegrationsPerUser;
    private final Integer usedDeploymentsPerUser;

    private ImmutableQuota(
            Optional<Integer> maxIntegrationsPerUser,
            Optional<Integer> maxDeploymentsPerUser,
            Optional<Integer> usedIntegrationsPerUser,
            Optional<Integer> usedDeploymentsPerUser) {
        this.maxIntegrationsPerUser = maxIntegrationsPerUser.orElse(null);
        this.maxDeploymentsPerUser = maxDeploymentsPerUser.orElse(null);
        this.usedIntegrationsPerUser = usedIntegrationsPerUser.orElse(null);
        this.usedDeploymentsPerUser = usedDeploymentsPerUser.orElse(null);
    }

    private ImmutableQuota(
            ImmutableQuota original,
            Integer maxIntegrationsPerUser,
            Integer maxDeploymentsPerUser,
            Integer usedIntegrationsPerUser,
            Integer usedDeploymentsPerUser) {
        this.maxIntegrationsPerUser = maxIntegrationsPerUser;
        this.maxDeploymentsPerUser = maxDeploymentsPerUser;
        this.usedIntegrationsPerUser = usedIntegrationsPerUser;
        this.usedDeploymentsPerUser = usedDeploymentsPerUser;
    }

    /**
     * Construct a new immutable {@code Quota} instance.
     *
     * @param maxIntegrationsPerUser  The value for the {@code maxIntegrationsPerUser} attribute
     * @param maxDeploymentsPerUser   The value for the {@code maxDeploymentsPerUser} attribute
     * @param usedIntegrationsPerUser The value for the {@code usedIntegrationsPerUser} attribute
     * @param usedDeploymentsPerUser  The value for the {@code usedDeploymentsPerUser} attribute
     * @return An immutable Quota instance
     */
    public static Quota of(Optional<Integer> maxIntegrationsPerUser,
                           Optional<Integer> maxDeploymentsPerUser,
                           Optional<Integer> usedIntegrationsPerUser,
                           Optional<Integer> usedDeploymentsPerUser) {
        return validate(new ImmutableQuota(maxIntegrationsPerUser,
                maxDeploymentsPerUser, usedIntegrationsPerUser,
                usedDeploymentsPerUser));
    }

    private static ImmutableQuota validate(ImmutableQuota instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.user.Quota} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable Quota instance
     */
    public static Quota copyOf(Quota instance) {
        if (instance instanceof ImmutableQuota) {
            return (ImmutableQuota) instance;
        }
        return new Quota.Builder()
                .createFrom(instance)
                .build();
    }

    /**
     * @return The value of the {@code maxIntegrationsPerUser} attribute
     */
    @JsonProperty("maxIntegrationsPerUser")
    @Override
    public Optional<Integer> getMaxIntegrationsPerUser() {
        return Optional.ofNullable(maxIntegrationsPerUser);
    }

    /**
     * @return The value of the {@code maxDeploymentsPerUser} attribute
     */
    @JsonProperty("maxDeploymentsPerUser")
    @Override
    public Optional<Integer> getMaxDeploymentsPerUser() {
        return Optional.ofNullable(maxDeploymentsPerUser);
    }

    /**
     * @return The value of the {@code usedIntegrationsPerUser} attribute
     */
    @JsonProperty("usedIntegrationsPerUser")
    @Override
    public Optional<Integer> getUsedIntegrationsPerUser() {
        return Optional.ofNullable(usedIntegrationsPerUser);
    }

    /**
     * @return The value of the {@code usedDeploymentsPerUser} attribute
     */
    @JsonProperty("usedDeploymentsPerUser")
    @Override
    public Optional<Integer> getUsedDeploymentsPerUser() {
        return Optional.ofNullable(usedDeploymentsPerUser);
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.user.Quota#getMaxIntegrationsPerUser() maxIntegrationsPerUser} attribute.
     *
     * @param value The value for maxIntegrationsPerUser
     * @return A modified copy of {@code this} object
     */
    public final ImmutableQuota withMaxIntegrationsPerUser(int value) {
        Integer newValue = value;
        if (Objects.equals(this.maxIntegrationsPerUser, newValue)) {
            return this;
        }
        return validate(new ImmutableQuota(
                this,
                newValue,
                this.maxDeploymentsPerUser,
                this.usedIntegrationsPerUser,
                this.usedDeploymentsPerUser));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.user.Quota#getMaxIntegrationsPerUser() maxIntegrationsPerUser} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for maxIntegrationsPerUser
     * @return A modified copy of {@code this} object
     */
    public final ImmutableQuota withMaxIntegrationsPerUser(
            Optional<Integer> optional) {
        Integer value = optional.orElse(null);
        if (Objects.equals(this.maxIntegrationsPerUser, value)) {
            return this;
        }
        return validate(new ImmutableQuota(
                this,
                value,
                this.maxDeploymentsPerUser,
                this.usedIntegrationsPerUser,
                this.usedDeploymentsPerUser));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.user.Quota#getMaxDeploymentsPerUser() maxDeploymentsPerUser} attribute.
     *
     * @param value The value for maxDeploymentsPerUser
     * @return A modified copy of {@code this} object
     */
    public final ImmutableQuota withMaxDeploymentsPerUser(int value) {
        Integer newValue = value;
        if (Objects.equals(this.maxDeploymentsPerUser, newValue)) {
            return this;
        }
        return validate(new ImmutableQuota(
                this,
                this.maxIntegrationsPerUser,
                newValue,
                this.usedIntegrationsPerUser,
                this.usedDeploymentsPerUser));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.user.Quota#getMaxDeploymentsPerUser() maxDeploymentsPerUser} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for maxDeploymentsPerUser
     * @return A modified copy of {@code this} object
     */
    public final ImmutableQuota withMaxDeploymentsPerUser(
            Optional<Integer> optional) {
        Integer value = optional.orElse(null);
        if (Objects.equals(this.maxDeploymentsPerUser, value)) {
            return this;
        }
        return validate(new ImmutableQuota(
                this,
                this.maxIntegrationsPerUser,
                value,
                this.usedIntegrationsPerUser,
                this.usedDeploymentsPerUser));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.user.Quota#getUsedIntegrationsPerUser() usedIntegrationsPerUser} attribute.
     *
     * @param value The value for usedIntegrationsPerUser
     * @return A modified copy of {@code this} object
     */
    public final ImmutableQuota withUsedIntegrationsPerUser(int value) {
        Integer newValue = value;
        if (Objects.equals(this.usedIntegrationsPerUser, newValue)) {
            return this;
        }
        return validate(new ImmutableQuota(
                this,
                this.maxIntegrationsPerUser,
                this.maxDeploymentsPerUser,
                newValue,
                this.usedDeploymentsPerUser));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.user.Quota#getUsedIntegrationsPerUser() usedIntegrationsPerUser} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for usedIntegrationsPerUser
     * @return A modified copy of {@code this} object
     */
    public final ImmutableQuota withUsedIntegrationsPerUser(
            Optional<Integer> optional) {
        Integer value = optional.orElse(null);
        if (Objects.equals(this.usedIntegrationsPerUser, value)) {
            return this;
        }
        return validate(new ImmutableQuota(
                this,
                this.maxIntegrationsPerUser,
                this.maxDeploymentsPerUser,
                value,
                this.usedDeploymentsPerUser));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.user.Quota#getUsedDeploymentsPerUser() usedDeploymentsPerUser} attribute.
     *
     * @param value The value for usedDeploymentsPerUser
     * @return A modified copy of {@code this} object
     */
    public final ImmutableQuota withUsedDeploymentsPerUser(int value) {
        Integer newValue = value;
        if (Objects.equals(this.usedDeploymentsPerUser, newValue)) {
            return this;
        }
        return validate(new ImmutableQuota(
                this,
                this.maxIntegrationsPerUser,
                this.maxDeploymentsPerUser,
                this.usedIntegrationsPerUser,
                newValue));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.user.Quota#getUsedDeploymentsPerUser() usedDeploymentsPerUser} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for usedDeploymentsPerUser
     * @return A modified copy of {@code this} object
     */
    public final ImmutableQuota withUsedDeploymentsPerUser(
            Optional<Integer> optional) {
        Integer value = optional.orElse(null);
        if (Objects.equals(this.usedDeploymentsPerUser, value)) {
            return this;
        }
        return validate(new ImmutableQuota(
                this,
                this.maxIntegrationsPerUser,
                this.maxDeploymentsPerUser,
                this.usedIntegrationsPerUser,
                value));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableQuota} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableQuota
                && equalTo((ImmutableQuota) another);
    }

    private boolean equalTo(ImmutableQuota another) {
        return Objects.equals(maxIntegrationsPerUser,
                another.maxIntegrationsPerUser)
                && Objects.equals(maxDeploymentsPerUser,
                another.maxDeploymentsPerUser)
                && Objects.equals(usedIntegrationsPerUser,
                another.usedIntegrationsPerUser)
                && Objects.equals(usedDeploymentsPerUser,
                another.usedDeploymentsPerUser);
    }

    /**
     * Computes a hash code from attributes: {@code maxIntegrationsPerUser}, {@code maxDeploymentsPerUser}, {@code usedIntegrationsPerUser}, {@code usedDeploymentsPerUser}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(maxIntegrationsPerUser);
        h += (h << 5) + Objects.hashCode(maxDeploymentsPerUser);
        h += (h << 5) + Objects.hashCode(usedIntegrationsPerUser);
        h += (h << 5) + Objects.hashCode(usedDeploymentsPerUser);
        return h;
    }

    /**
     * Prints the immutable value {@code Quota} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Quota{");
        if (maxIntegrationsPerUser != null) {
            builder.append("maxIntegrationsPerUser=")
                    .append(maxIntegrationsPerUser);
        }
        if (maxDeploymentsPerUser != null) {
            if (builder.length() > 6) {
                builder.append(", ");
            }
            builder.append("maxDeploymentsPerUser=")
                    .append(maxDeploymentsPerUser);
        }
        if (usedIntegrationsPerUser != null) {
            if (builder.length() > 6) {
                builder.append(", ");
            }
            builder.append("usedIntegrationsPerUser=")
                    .append(usedIntegrationsPerUser);
        }
        if (usedDeploymentsPerUser != null) {
            if (builder.length() > 6) {
                builder.append(", ");
            }
            builder.append("usedDeploymentsPerUser=")
                    .append(usedDeploymentsPerUser);
        }
        return builder.append("}").toString();
    }

    private Object readResolve() throws ObjectStreamException {
        return validate(this);
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.user.Quota Quota}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private Integer maxIntegrationsPerUser;
        private Integer maxDeploymentsPerUser;
        private Integer usedIntegrationsPerUser;
        private Integer usedDeploymentsPerUser;

        /**
         * Creates a builder for {@link io.syndesis.common.model.user.Quota Quota} instances.
         * <pre>
         * new Quota.Builder()
         *    .maxIntegrationsPerUser(Integer) // optional {@link io.syndesis.common.model.user.Quota#getMaxIntegrationsPerUser() maxIntegrationsPerUser}
         *    .maxDeploymentsPerUser(Integer) // optional {@link io.syndesis.common.model.user.Quota#getMaxDeploymentsPerUser() maxDeploymentsPerUser}
         *    .usedIntegrationsPerUser(Integer) // optional {@link io.syndesis.common.model.user.Quota#getUsedIntegrationsPerUser() usedIntegrationsPerUser}
         *    .usedDeploymentsPerUser(Integer) // optional {@link io.syndesis.common.model.user.Quota#getUsedDeploymentsPerUser() usedDeploymentsPerUser}
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof Quota.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new Quota.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code Quota} instance.
         * Regular attribute values will be replaced with those from the given instance.
         * Absent optional values will not replace present values.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Quota.Builder createFrom(Quota instance) {
            Objects.requireNonNull(instance, "instance");
            Optional<Integer> maxIntegrationsPerUserOptional =
                    instance.getMaxIntegrationsPerUser();
            if (maxIntegrationsPerUserOptional.isPresent()) {
                maxIntegrationsPerUser(maxIntegrationsPerUserOptional);
            }
            Optional<Integer> maxDeploymentsPerUserOptional =
                    instance.getMaxDeploymentsPerUser();
            if (maxDeploymentsPerUserOptional.isPresent()) {
                maxDeploymentsPerUser(maxDeploymentsPerUserOptional);
            }
            Optional<Integer> usedIntegrationsPerUserOptional =
                    instance.getUsedIntegrationsPerUser();
            if (usedIntegrationsPerUserOptional.isPresent()) {
                usedIntegrationsPerUser(usedIntegrationsPerUserOptional);
            }
            Optional<Integer> usedDeploymentsPerUserOptional =
                    instance.getUsedDeploymentsPerUser();
            if (usedDeploymentsPerUserOptional.isPresent()) {
                usedDeploymentsPerUser(usedDeploymentsPerUserOptional);
            }
            return (Quota.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.user.Quota#getMaxIntegrationsPerUser() maxIntegrationsPerUser} to maxIntegrationsPerUser.
         *
         * @param maxIntegrationsPerUser The value for maxIntegrationsPerUser
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final Quota.Builder maxIntegrationsPerUser(
                int maxIntegrationsPerUser) {
            this.maxIntegrationsPerUser = maxIntegrationsPerUser;
            return (Quota.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.user.Quota#getMaxIntegrationsPerUser() maxIntegrationsPerUser} to maxIntegrationsPerUser.
         *
         * @param maxIntegrationsPerUser The value for maxIntegrationsPerUser
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("maxIntegrationsPerUser")
        public final Quota.Builder maxIntegrationsPerUser(
                Optional<Integer> maxIntegrationsPerUser) {
            this.maxIntegrationsPerUser = maxIntegrationsPerUser.orElse(null);
            return (Quota.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.user.Quota#getMaxDeploymentsPerUser() maxDeploymentsPerUser} to maxDeploymentsPerUser.
         *
         * @param maxDeploymentsPerUser The value for maxDeploymentsPerUser
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final Quota.Builder maxDeploymentsPerUser(
                int maxDeploymentsPerUser) {
            this.maxDeploymentsPerUser = maxDeploymentsPerUser;
            return (Quota.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.user.Quota#getMaxDeploymentsPerUser() maxDeploymentsPerUser} to maxDeploymentsPerUser.
         *
         * @param maxDeploymentsPerUser The value for maxDeploymentsPerUser
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("maxDeploymentsPerUser")
        public final Quota.Builder maxDeploymentsPerUser(
                Optional<Integer> maxDeploymentsPerUser) {
            this.maxDeploymentsPerUser = maxDeploymentsPerUser.orElse(null);
            return (Quota.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.user.Quota#getUsedIntegrationsPerUser() usedIntegrationsPerUser} to usedIntegrationsPerUser.
         *
         * @param usedIntegrationsPerUser The value for usedIntegrationsPerUser
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final Quota.Builder usedIntegrationsPerUser(
                int usedIntegrationsPerUser) {
            this.usedIntegrationsPerUser = usedIntegrationsPerUser;
            return (Quota.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.user.Quota#getUsedIntegrationsPerUser() usedIntegrationsPerUser} to usedIntegrationsPerUser.
         *
         * @param usedIntegrationsPerUser The value for usedIntegrationsPerUser
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("usedIntegrationsPerUser")
        public final Quota.Builder usedIntegrationsPerUser(
                Optional<Integer> usedIntegrationsPerUser) {
            this.usedIntegrationsPerUser = usedIntegrationsPerUser.orElse(null);
            return (Quota.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.user.Quota#getUsedDeploymentsPerUser() usedDeploymentsPerUser} to usedDeploymentsPerUser.
         *
         * @param usedDeploymentsPerUser The value for usedDeploymentsPerUser
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final Quota.Builder usedDeploymentsPerUser(
                int usedDeploymentsPerUser) {
            this.usedDeploymentsPerUser = usedDeploymentsPerUser;
            return (Quota.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.user.Quota#getUsedDeploymentsPerUser() usedDeploymentsPerUser} to usedDeploymentsPerUser.
         *
         * @param usedDeploymentsPerUser The value for usedDeploymentsPerUser
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("usedDeploymentsPerUser")
        public final Quota.Builder usedDeploymentsPerUser(
                Optional<Integer> usedDeploymentsPerUser) {
            this.usedDeploymentsPerUser = usedDeploymentsPerUser.orElse(null);
            return (Quota.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.user.Quota Quota}.
         *
         * @return An immutable instance of Quota
         * @throws IllegalStateException if any required attributes are missing
         */
        public Quota build() {
            return ImmutableQuota.validate(new ImmutableQuota(
                    null,
                    maxIntegrationsPerUser,
                    maxDeploymentsPerUser,
                    usedIntegrationsPerUser,
                    usedDeploymentsPerUser));
        }
    }
}
