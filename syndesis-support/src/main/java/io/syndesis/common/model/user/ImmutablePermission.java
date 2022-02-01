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
import io.syndesis.common.model.WithName;
import io.syndesis.common.model.WithResourceId;

import java.io.ObjectStreamException;
import java.util.Objects;
import java.util.Optional;

/**
 * Immutable implementation of {@link io.syndesis.common.model.user.Permission}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new Permission.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutablePermission.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutablePermission implements Permission {
    private final String id;
    private final String name;
    private final String description;

    private ImmutablePermission(Optional<String> id, String name,
                                String description) {
        this.id = id.orElse(null);
        this.name = name;
        this.description = description;
    }

    private ImmutablePermission(
            ImmutablePermission original,
            String id,
            String name,
            String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    /**
     * Construct a new immutable {@code Permission} instance.
     *
     * @param id          The value for the {@code id} attribute
     * @param name        The value for the {@code name} attribute
     * @param description The value for the {@code description} attribute
     * @return An immutable Permission instance
     */
    public static Permission of(Optional<String> id, String name,
                                String description) {
        return validate(new ImmutablePermission(id, name, description));
    }

    private static ImmutablePermission validate(ImmutablePermission instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.user.Permission} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable Permission instance
     */
    public static Permission copyOf(Permission instance) {
        if (instance instanceof ImmutablePermission) {
            return (ImmutablePermission) instance;
        }
        return new Permission.Builder()
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
     * @return The value of the {@code name} attribute
     */
    @JsonProperty("name")
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return The value of the {@code description} attribute
     */
    @JsonProperty("description")
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.user.Permission#getId() id} attribute.
     *
     * @param value The value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutablePermission withId(String value) {
        String newValue = Objects.requireNonNull(value, "id");
        if (Objects.equals(this.id, newValue)) {
            return this;
        }
        return validate(new ImmutablePermission(this, newValue, this.name,
                this.description));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.user.Permission#getId() id} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutablePermission withId(Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.id, value)) {
            return this;
        }
        return validate(new ImmutablePermission(this, value, this.name,
                this.description));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.user.Permission#getName() name} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for name (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutablePermission withName(String value) {
        if (Objects.equals(this.name, value)) {
            return this;
        }
        return validate(new ImmutablePermission(this, this.id, value,
                this.description));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.user.Permission#getDescription() description} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for description (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutablePermission withDescription(String value) {
        if (Objects.equals(this.description, value)) {
            return this;
        }
        return validate(
                new ImmutablePermission(this, this.id, this.name, value));
    }

    /**
     * This instance is equal to all instances of {@code ImmutablePermission} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutablePermission
                && equalTo((ImmutablePermission) another);
    }

    private boolean equalTo(ImmutablePermission another) {
        return Objects.equals(id, another.id)
                && Objects.equals(name, another.name)
                && Objects.equals(description, another.description);
    }

    /**
     * Computes a hash code from attributes: {@code id}, {@code name}, {@code description}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(id);
        h += (h << 5) + Objects.hashCode(name);
        h += (h << 5) + Objects.hashCode(description);
        return h;
    }

    /**
     * Prints the immutable value {@code Permission} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Permission{");
        if (id != null) {
            builder.append("id=").append(id);
        }
        if (name != null) {
            if (builder.length() > 11) {
                builder.append(", ");
            }
            builder.append("name=").append(name);
        }
        if (description != null) {
            if (builder.length() > 11) {
                builder.append(", ");
            }
            builder.append("description=").append(description);
        }
        return builder.append("}").toString();
    }

    private Object readResolve() throws ObjectStreamException {
        return validate(this);
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.user.Permission Permission}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private String id;
        private String name;
        private String description;

        /**
         * Creates a builder for {@link io.syndesis.common.model.user.Permission Permission} instances.
         * <pre>
         * new Permission.Builder()
         *    .id(String) // optional {@link io.syndesis.common.model.user.Permission#getId() id}
         *    .name(String | null) // nullable {@link io.syndesis.common.model.user.Permission#getName() name}
         *    .description(String | null) // nullable {@link io.syndesis.common.model.user.Permission#getDescription() description}
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof Permission.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new Permission.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithName} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Permission.Builder createFrom(WithName instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Permission.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.user.Permission} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Permission.Builder createFrom(Permission instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Permission.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithResourceId} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Permission.Builder createFrom(WithResourceId instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Permission.Builder) this;
        }

        private void from(Object object) {
            if (object instanceof WithName) {
                WithName instance = (WithName) object;
                String nameValue = instance.getName();
                if (nameValue != null) {
                    name(nameValue);
                }
            }
            if (object instanceof Permission) {
                Permission instance = (Permission) object;
                String descriptionValue = instance.getDescription();
                if (descriptionValue != null) {
                    description(descriptionValue);
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
         * Initializes the optional value {@link io.syndesis.common.model.user.Permission#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final Permission.Builder id(String id) {
            this.id = Objects.requireNonNull(id, "id");
            return (Permission.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.user.Permission#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("id")
        public final Permission.Builder id(Optional<String> id) {
            this.id = id.orElse(null);
            return (Permission.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.user.Permission#getName() name} attribute.
         *
         * @param name The value for name (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("name")
        public final Permission.Builder name(String name) {
            this.name = name;
            return (Permission.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.user.Permission#getDescription() description} attribute.
         *
         * @param description The value for description (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("description")
        public final Permission.Builder description(String description) {
            this.description = description;
            return (Permission.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.user.Permission Permission}.
         *
         * @return An immutable instance of Permission
         * @throws IllegalStateException if any required attributes are missing
         */
        public Permission build() {
            return ImmutablePermission.validate(
                    new ImmutablePermission(null, id, name, description));
        }
    }
}
