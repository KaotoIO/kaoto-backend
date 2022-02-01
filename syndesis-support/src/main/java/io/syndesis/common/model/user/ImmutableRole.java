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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Immutable implementation of {@link io.syndesis.common.model.user.Role}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new Role.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableRole.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableRole implements Role {
    private final String id;
    private final String name;
    private final List<Permission> permissions;

    private ImmutableRole(
            Optional<String> id,
            String name,
            Iterable<? extends Permission> permissions) {
        this.id = id.orElse(null);
        this.name = name;
        this.permissions = createUnmodifiableList(false,
                createSafeList(permissions, true, false));
    }

    private ImmutableRole(
            ImmutableRole original,
            String id,
            String name,
            List<Permission> permissions) {
        this.id = id;
        this.name = name;
        this.permissions = permissions;
    }

    /**
     * Construct a new immutable {@code Role} instance.
     *
     * @param id          The value for the {@code id} attribute
     * @param name        The value for the {@code name} attribute
     * @param permissions The value for the {@code permissions} attribute
     * @return An immutable Role instance
     */
    public static Role of(Optional<String> id, String name,
                          List<Permission> permissions) {
        return of(id, name, (Iterable<? extends Permission>) permissions);
    }

    /**
     * Construct a new immutable {@code Role} instance.
     *
     * @param id          The value for the {@code id} attribute
     * @param name        The value for the {@code name} attribute
     * @param permissions The value for the {@code permissions} attribute
     * @return An immutable Role instance
     */
    public static Role of(Optional<String> id, String name,
                          Iterable<? extends Permission> permissions) {
        return validate(new ImmutableRole(id, name, permissions));
    }

    private static ImmutableRole validate(ImmutableRole instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.user.Role} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable Role instance
     */
    public static Role copyOf(Role instance) {
        if (instance instanceof ImmutableRole) {
            return (ImmutableRole) instance;
        }
        return new Role.Builder()
                .createFrom(instance)
                .build();
    }

    private static <T> List<T> createSafeList(Iterable<? extends T> iterable,
                                              boolean checkNulls,
                                              boolean skipNulls) {
        ArrayList<T> list;
        if (iterable instanceof Collection<?>) {
            int size = ((Collection<?>) iterable).size();
            if (size == 0) {
                return Collections.emptyList();
            }
            list = new ArrayList<>();
        } else {
            list = new ArrayList<>();
        }
        for (T element : iterable) {
            if (skipNulls && element == null) {
                continue;
            }
            if (checkNulls) {
                Objects.requireNonNull(element, "element");
            }
            list.add(element);
        }
        return list;
    }

    private static <T> List<T> createUnmodifiableList(boolean clone,
                                                      List<T> list) {
        switch (list.size()) {
            case 0:
                return Collections.emptyList();
            case 1:
                return Collections.singletonList(list.get(0));
            default:
                if (clone) {
                    return Collections.unmodifiableList(new ArrayList<>(list));
                } else {
                    if (list instanceof ArrayList<?>) {
                        ((ArrayList<?>) list).trimToSize();
                    }
                    return Collections.unmodifiableList(list);
                }
        }
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
     * @return The value of the {@code permissions} attribute
     */
    @JsonProperty("permissions")
    @Override
    public List<Permission> getPermissions() {
        return permissions;
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.user.Role#getId() id} attribute.
     *
     * @param value The value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableRole withId(String value) {
        String newValue = Objects.requireNonNull(value, "id");
        if (Objects.equals(this.id, newValue)) {
            return this;
        }
        return validate(
                new ImmutableRole(this, newValue, this.name, this.permissions));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.user.Role#getId() id} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableRole withId(Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.id, value)) {
            return this;
        }
        return validate(
                new ImmutableRole(this, value, this.name, this.permissions));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.user.Role#getName() name} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for name (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableRole withName(String value) {
        if (Objects.equals(this.name, value)) {
            return this;
        }
        return validate(
                new ImmutableRole(this, this.id, value, this.permissions));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.user.Role#getPermissions() permissions}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableRole withPermissions(Permission... elements) {
        List<Permission> newValue = createUnmodifiableList(false,
                createSafeList(Arrays.asList(elements), true, false));
        return validate(new ImmutableRole(this, this.id, this.name, newValue));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.user.Role#getPermissions() permissions}.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of permissions elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableRole withPermissions(
            Iterable<? extends Permission> elements) {
        if (this.permissions == elements) {
            return this;
        }
        List<Permission> newValue = createUnmodifiableList(false,
                createSafeList(elements, true, false));
        return validate(new ImmutableRole(this, this.id, this.name, newValue));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableRole} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableRole
                && equalTo((ImmutableRole) another);
    }

    private boolean equalTo(ImmutableRole another) {
        return Objects.equals(id, another.id)
                && Objects.equals(name, another.name)
                && permissions.equals(another.permissions);
    }

    /**
     * Computes a hash code from attributes: {@code id}, {@code name}, {@code permissions}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(id);
        h += (h << 5) + Objects.hashCode(name);
        h += (h << 5) + permissions.hashCode();
        return h;
    }

    /**
     * Prints the immutable value {@code Role} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Role{");
        if (id != null) {
            builder.append("id=").append(id);
        }
        if (name != null) {
            if (builder.length() > 5) {
                builder.append(", ");
            }
            builder.append("name=").append(name);
        }
        if (builder.length() > 5) {
            builder.append(", ");
        }
        builder.append("permissions=").append(permissions);
        return builder.append("}").toString();
    }

    private Object readResolve() throws ObjectStreamException {
        return validate(this);
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.user.Role Role}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private String id;
        private String name;
        private List<Permission> permissions = new ArrayList<Permission>();

        /**
         * Creates a builder for {@link io.syndesis.common.model.user.Role Role} instances.
         * <pre>
         * new Role.Builder()
         *    .id(String) // optional {@link io.syndesis.common.model.user.Role#getId() id}
         *    .name(String | null) // nullable {@link io.syndesis.common.model.user.Role#getName() name}
         *    .addPermission|addAllPermissions(io.syndesis.common.io.syndesis.common.model.user.Permission) // {@link io.syndesis.common.model.user.Role#getPermissions() permissions} elements
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof Role.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new Role.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithName} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Role.Builder createFrom(WithName instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Role.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.user.Role} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Role.Builder createFrom(Role instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Role.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithResourceId} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Role.Builder createFrom(WithResourceId instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Role.Builder) this;
        }

        private void from(Object object) {
            if (object instanceof WithName) {
                WithName instance = (WithName) object;
                String nameValue = instance.getName();
                if (nameValue != null) {
                    name(nameValue);
                }
            }
            if (object instanceof Role) {
                Role instance = (Role) object;
                addAllPermissions(instance.getPermissions());
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
         * Initializes the optional value {@link io.syndesis.common.model.user.Role#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final Role.Builder id(String id) {
            this.id = Objects.requireNonNull(id, "id");
            return (Role.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.user.Role#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("id")
        public final Role.Builder id(Optional<String> id) {
            this.id = id.orElse(null);
            return (Role.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.user.Role#getName() name} attribute.
         *
         * @param name The value for name (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("name")
        public final Role.Builder name(String name) {
            this.name = name;
            return (Role.Builder) this;
        }

        /**
         * Adds one element to {@link io.syndesis.common.model.user.Role#getPermissions() permissions} list.
         *
         * @param element A permissions element
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Role.Builder addPermission(Permission element) {
            this.permissions.add(
                    Objects.requireNonNull(element, "permissions element"));
            return (Role.Builder) this;
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.user.Role#getPermissions() permissions} list.
         *
         * @param elements An array of permissions elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Role.Builder addPermissions(Permission... elements) {
            for (Permission element : elements) {
                this.permissions.add(
                        Objects.requireNonNull(element, "permissions element"));
            }
            return (Role.Builder) this;
        }


        /**
         * Sets or replaces all elements for {@link io.syndesis.common.model.user.Role#getPermissions() permissions} list.
         *
         * @param elements An iterable of permissions elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("permissions")
        public final Role.Builder permissions(
                Iterable<? extends Permission> elements) {
            this.permissions.clear();
            return addAllPermissions(elements);
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.user.Role#getPermissions() permissions} list.
         *
         * @param elements An iterable of permissions elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Role.Builder addAllPermissions(
                Iterable<? extends Permission> elements) {
            for (Permission element : elements) {
                this.permissions.add(
                        Objects.requireNonNull(element, "permissions element"));
            }
            return (Role.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.user.Role Role}.
         *
         * @return An immutable instance of Role
         * @throws IllegalStateException if any required attributes are missing
         */
        public Role build() {
            return ImmutableRole.validate(new ImmutableRole(null, id, name,
                    createUnmodifiableList(true, permissions)));
        }
    }
}
