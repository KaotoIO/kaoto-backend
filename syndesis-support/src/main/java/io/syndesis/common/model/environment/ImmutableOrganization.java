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
package io.syndesis.common.model.environment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Var;
import io.syndesis.common.model.WithName;
import io.syndesis.common.model.WithResourceId;
import io.syndesis.common.model.user.User;

import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Immutable implementation of {@link io.syndesis.common.model.environment.Organization}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new Organization.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableOrganization.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableOrganization implements Organization {
    private final String id;
    private final String name;
    private final List<Environment> environments;
    private final List<User> users;

    private ImmutableOrganization(
            Optional<String> id,
            String name,
            Iterable<? extends Environment> environments,
            Iterable<? extends User> users) {
        this.id = id.orElse(null);
        this.name = name;
        this.environments = createUnmodifiableList(false,
                createSafeList(environments, true, false));
        this.users = createUnmodifiableList(false,
                createSafeList(users, true, false));
    }

    private ImmutableOrganization(
            ImmutableOrganization original,
            String id,
            String name,
            List<Environment> environments,
            List<User> users) {
        this.id = id;
        this.name = name;
        this.environments = environments;
        this.users = users;
    }

    /**
     * Construct a new immutable {@code Organization} instance.
     *
     * @param id           The value for the {@code id} attribute
     * @param name         The value for the {@code name} attribute
     * @param environments The value for the {@code environments} attribute
     * @param users        The value for the {@code users} attribute
     * @return An immutable Organization instance
     */
    public static Organization of(Optional<String> id, String name,
                                  List<Environment> environments,
                                  List<User> users) {
        return of(id, name, (Iterable<? extends Environment>) environments,
                (Iterable<? extends User>) users);
    }

    /**
     * Construct a new immutable {@code Organization} instance.
     *
     * @param id           The value for the {@code id} attribute
     * @param name         The value for the {@code name} attribute
     * @param environments The value for the {@code environments} attribute
     * @param users        The value for the {@code users} attribute
     * @return An immutable Organization instance
     */
    public static Organization of(Optional<String> id, String name,
                                  Iterable<? extends Environment> environments,
                                  Iterable<? extends User> users) {
        return validate(
                new ImmutableOrganization(id, name, environments, users));
    }

    private static ImmutableOrganization validate(
            ImmutableOrganization instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.environment.Organization} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable Organization instance
     */
    public static Organization copyOf(Organization instance) {
        if (instance instanceof ImmutableOrganization) {
            return (ImmutableOrganization) instance;
        }
        return new Organization.Builder()
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
     * @return The value of the {@code environments} attribute
     */
    @JsonProperty("environments")
    @Override
    public List<Environment> getEnvironments() {
        return environments;
    }

    /**
     * @return The value of the {@code users} attribute
     */
    @JsonProperty("users")
    @Override
    public List<User> getUsers() {
        return users;
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.environment.Organization#getId() id} attribute.
     *
     * @param value The value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableOrganization withId(String value) {
        String newValue = Objects.requireNonNull(value, "id");
        if (Objects.equals(this.id, newValue)) {
            return this;
        }
        return validate(new ImmutableOrganization(this, newValue, this.name,
                this.environments, this.users));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.environment.Organization#getId() id} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableOrganization withId(Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.id, value)) {
            return this;
        }
        return validate(new ImmutableOrganization(this, value, this.name,
                this.environments, this.users));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.environment.Organization#getName() name} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for name (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableOrganization withName(String value) {
        if (Objects.equals(this.name, value)) {
            return this;
        }
        return validate(new ImmutableOrganization(this, this.id, value,
                this.environments, this.users));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.environment.Organization#getEnvironments() environments}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableOrganization withEnvironments(
            Environment... elements) {
        List<Environment> newValue = createUnmodifiableList(false,
                createSafeList(Arrays.asList(elements), true, false));
        return validate(
                new ImmutableOrganization(this, this.id, this.name, newValue,
                        this.users));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.environment.Organization#getEnvironments() environments}.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of environments elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableOrganization withEnvironments(
            Iterable<? extends Environment> elements) {
        if (this.environments == elements) {
            return this;
        }
        List<Environment> newValue = createUnmodifiableList(false,
                createSafeList(elements, true, false));
        return validate(
                new ImmutableOrganization(this, this.id, this.name, newValue,
                        this.users));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.environment.Organization#getUsers() users}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableOrganization withUsers(User... elements) {
        List<User> newValue = createUnmodifiableList(false,
                createSafeList(Arrays.asList(elements), true, false));
        return validate(new ImmutableOrganization(this, this.id, this.name,
                this.environments, newValue));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.environment.Organization#getUsers() users}.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of users elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableOrganization withUsers(
            Iterable<? extends User> elements) {
        if (this.users == elements) {
            return this;
        }
        List<User> newValue = createUnmodifiableList(false,
                createSafeList(elements, true, false));
        return validate(new ImmutableOrganization(this, this.id, this.name,
                this.environments, newValue));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableOrganization} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableOrganization
                && equalTo((ImmutableOrganization) another);
    }

    private boolean equalTo(ImmutableOrganization another) {
        return Objects.equals(id, another.id)
                && Objects.equals(name, another.name)
                && environments.equals(another.environments)
                && users.equals(another.users);
    }

    /**
     * Computes a hash code from attributes: {@code id}, {@code name}, {@code environments}, {@code users}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(id);
        h += (h << 5) + Objects.hashCode(name);
        h += (h << 5) + environments.hashCode();
        h += (h << 5) + users.hashCode();
        return h;
    }

    /**
     * Prints the immutable value {@code Organization} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Organization{");
        if (id != null) {
            builder.append("id=").append(id);
        }
        if (name != null) {
            if (builder.length() > 13) {
                builder.append(", ");
            }
            builder.append("name=").append(name);
        }
        if (builder.length() > 13) {
            builder.append(", ");
        }
        builder.append("environments=").append(environments);
        builder.append(", ");
        builder.append("users=").append(users);
        return builder.append("}").toString();
    }

    private Object readResolve() throws ObjectStreamException {
        return validate(this);
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.environment.Organization Organization}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private String id;
        private String name;
        private List<Environment> environments = new ArrayList<Environment>();
        private List<User> users = new ArrayList<User>();

        /**
         * Creates a builder for {@link io.syndesis.common.model.environment.Organization Organization} instances.
         * <pre>
         * new Organization.Builder()
         *    .id(String) // optional {@link io.syndesis.common.model.environment.Organization#getId() id}
         *    .name(String | null) // nullable {@link io.syndesis.common.model.environment.Organization#getName() name}
         *    .addEnvironment|addAllEnvironments(io.syndesis.common.io.syndesis.common.model.environment.Environment) // {@link io.syndesis.common.model.environment.Organization#getEnvironments() environments} elements
         *    .addUser|addAllUsers(io.syndesis.common.io.syndesis.common.model.user.User) // {@link io.syndesis.common.model.environment.Organization#getUsers() users} elements
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof Organization.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new Organization.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithName} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Organization.Builder createFrom(WithName instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Organization.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.environment.Organization} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Organization.Builder createFrom(Organization instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Organization.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithResourceId} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Organization.Builder createFrom(WithResourceId instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Organization.Builder) this;
        }

        private void from(Object object) {
            if (object instanceof WithName) {
                WithName instance = (WithName) object;
                String nameValue = instance.getName();
                if (nameValue != null) {
                    name(nameValue);
                }
            }
            if (object instanceof Organization) {
                Organization instance = (Organization) object;
                addAllEnvironments(instance.getEnvironments());
                addAllUsers(instance.getUsers());
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
         * Initializes the optional value {@link io.syndesis.common.model.environment.Organization#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final Organization.Builder id(String id) {
            this.id = Objects.requireNonNull(id, "id");
            return (Organization.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.environment.Organization#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("id")
        public final Organization.Builder id(Optional<String> id) {
            this.id = id.orElse(null);
            return (Organization.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.environment.Organization#getName() name} attribute.
         *
         * @param name The value for name (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("name")
        public final Organization.Builder name(String name) {
            this.name = name;
            return (Organization.Builder) this;
        }

        /**
         * Adds one element to {@link io.syndesis.common.model.environment.Organization#getEnvironments() environments} list.
         *
         * @param element A environments element
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Organization.Builder addEnvironment(Environment element) {
            this.environments.add(
                    Objects.requireNonNull(element, "environments element"));
            return (Organization.Builder) this;
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.environment.Organization#getEnvironments() environments} list.
         *
         * @param elements An array of environments elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Organization.Builder addEnvironments(
                Environment... elements) {
            for (Environment element : elements) {
                this.environments.add(Objects.requireNonNull(element,
                        "environments element"));
            }
            return (Organization.Builder) this;
        }


        /**
         * Sets or replaces all elements for {@link io.syndesis.common.model.environment.Organization#getEnvironments() environments} list.
         *
         * @param elements An iterable of environments elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("environments")
        public final Organization.Builder environments(
                Iterable<? extends Environment> elements) {
            this.environments.clear();
            return addAllEnvironments(elements);
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.environment.Organization#getEnvironments() environments} list.
         *
         * @param elements An iterable of environments elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Organization.Builder addAllEnvironments(
                Iterable<? extends Environment> elements) {
            for (Environment element : elements) {
                this.environments.add(Objects.requireNonNull(element,
                        "environments element"));
            }
            return (Organization.Builder) this;
        }

        /**
         * Adds one element to {@link io.syndesis.common.model.environment.Organization#getUsers() users} list.
         *
         * @param element A users element
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Organization.Builder addUser(User element) {
            this.users.add(Objects.requireNonNull(element, "users element"));
            return (Organization.Builder) this;
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.environment.Organization#getUsers() users} list.
         *
         * @param elements An array of users elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Organization.Builder addUsers(User... elements) {
            for (User element : elements) {
                this.users.add(
                        Objects.requireNonNull(element, "users element"));
            }
            return (Organization.Builder) this;
        }


        /**
         * Sets or replaces all elements for {@link io.syndesis.common.model.environment.Organization#getUsers() users} list.
         *
         * @param elements An iterable of users elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("users")
        public final Organization.Builder users(
                Iterable<? extends User> elements) {
            this.users.clear();
            return addAllUsers(elements);
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.environment.Organization#getUsers() users} list.
         *
         * @param elements An iterable of users elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Organization.Builder addAllUsers(
                Iterable<? extends User> elements) {
            for (User element : elements) {
                this.users.add(
                        Objects.requireNonNull(element, "users element"));
            }
            return (Organization.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.environment.Organization Organization}.
         *
         * @return An immutable instance of Organization
         * @throws IllegalStateException if any required attributes are missing
         */
        public Organization build() {
            return ImmutableOrganization.validate(new ImmutableOrganization(
                    null,
                    id,
                    name,
                    createUnmodifiableList(true, environments),
                    createUnmodifiableList(true, users)));
        }
    }
}
