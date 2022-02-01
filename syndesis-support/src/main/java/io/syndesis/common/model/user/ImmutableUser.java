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
import io.syndesis.common.model.WithResourceId;
import io.syndesis.common.model.integration.Integration;

import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Immutable implementation of {@link io.syndesis.common.model.user.User}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new User.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableUser.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableUser implements User {
    private final String id;
    private final String name;
    private final String fullName;
    private final String lastName;
    private final String firstName;
    private final String username;
    private final List<Integration> integrations;
    private final String roleId;
    private final String organizationId;

    private ImmutableUser(
            Optional<String> id,
            Optional<String> name,
            Optional<String> fullName,
            Optional<String> lastName,
            Optional<String> firstName,
            String username,
            Iterable<? extends Integration> integrations,
            Optional<String> roleId,
            Optional<String> organizationId) {
        this.id = id.orElse(null);
        this.name = name.orElse(null);
        this.fullName = fullName.orElse(null);
        this.lastName = lastName.orElse(null);
        this.firstName = firstName.orElse(null);
        this.username = username;
        this.integrations = createUnmodifiableList(false,
                createSafeList(integrations, true, false));
        this.roleId = roleId.orElse(null);
        this.organizationId = organizationId.orElse(null);
    }

    private ImmutableUser(
            ImmutableUser original,
            String id,
            String name,
            String fullName,
            String lastName,
            String firstName,
            String username,
            List<Integration> integrations,
            String roleId,
            String organizationId) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.lastName = lastName;
        this.firstName = firstName;
        this.username = username;
        this.integrations = integrations;
        this.roleId = roleId;
        this.organizationId = organizationId;
    }

    /**
     * Construct a new immutable {@code User} instance.
     *
     * @param id             The value for the {@code id} attribute
     * @param name           The value for the {@code name} attribute
     * @param fullName       The value for the {@code fullName} attribute
     * @param lastName       The value for the {@code lastName} attribute
     * @param firstName      The value for the {@code firstName} attribute
     * @param username       The value for the {@code username} attribute
     * @param integrations   The value for the {@code integrations} attribute
     * @param roleId         The value for the {@code roleId} attribute
     * @param organizationId The value for the {@code organizationId} attribute
     * @return An immutable User instance
     */
    public static User of(Optional<String> id, Optional<String> name,
                          Optional<String> fullName, Optional<String> lastName,
                          Optional<String> firstName, String username,
                          List<Integration> integrations,
                          Optional<String> roleId,
                          Optional<String> organizationId) {
        return of(id, name, fullName, lastName, firstName, username,
                (Iterable<? extends Integration>) integrations, roleId,
                organizationId);
    }

    /**
     * Construct a new immutable {@code User} instance.
     *
     * @param id             The value for the {@code id} attribute
     * @param name           The value for the {@code name} attribute
     * @param fullName       The value for the {@code fullName} attribute
     * @param lastName       The value for the {@code lastName} attribute
     * @param firstName      The value for the {@code firstName} attribute
     * @param username       The value for the {@code username} attribute
     * @param integrations   The value for the {@code integrations} attribute
     * @param roleId         The value for the {@code roleId} attribute
     * @param organizationId The value for the {@code organizationId} attribute
     * @return An immutable User instance
     */
    public static User of(Optional<String> id, Optional<String> name,
                          Optional<String> fullName, Optional<String> lastName,
                          Optional<String> firstName, String username,
                          Iterable<? extends Integration> integrations,
                          Optional<String> roleId,
                          Optional<String> organizationId) {
        return validate(
                new ImmutableUser(id, name, fullName, lastName, firstName,
                        username, integrations, roleId, organizationId));
    }

    private static ImmutableUser validate(ImmutableUser instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.user.User} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable User instance
     */
    public static User copyOf(User instance) {
        if (instance instanceof ImmutableUser) {
            return (ImmutableUser) instance;
        }
        return new User.Builder()
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
    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    /**
     * @return The value of the {@code fullName} attribute
     */
    @JsonProperty("fullName")
    @Override
    public Optional<String> getFullName() {
        return Optional.ofNullable(fullName);
    }

    /**
     * @return The value of the {@code lastName} attribute
     */
    @JsonProperty("lastName")
    @Override
    public Optional<String> getLastName() {
        return Optional.ofNullable(lastName);
    }

    /**
     * @return The value of the {@code firstName} attribute
     */
    @JsonProperty("firstName")
    @Override
    public Optional<String> getFirstName() {
        return Optional.ofNullable(firstName);
    }

    /**
     * @return The value of the {@code username} attribute
     */
    @JsonProperty("username")
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * @return The value of the {@code integrations} attribute
     */
    @JsonProperty("integrations")
    @Override
    public List<Integration> getIntegrations() {
        return integrations;
    }

    /**
     * @return The value of the {@code roleId} attribute
     */
    @JsonProperty("roleId")
    @Override
    public Optional<String> getRoleId() {
        return Optional.ofNullable(roleId);
    }

    /**
     * @return The value of the {@code organizationId} attribute
     */
    @JsonProperty("organizationId")
    @Override
    public Optional<String> getOrganizationId() {
        return Optional.ofNullable(organizationId);
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.user.User#getId() id} attribute.
     *
     * @param value The value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableUser withId(String value) {
        String newValue = Objects.requireNonNull(value, "id");
        if (Objects.equals(this.id, newValue)) {
            return this;
        }
        return validate(new ImmutableUser(
                this,
                newValue,
                this.name,
                this.fullName,
                this.lastName,
                this.firstName,
                this.username,
                this.integrations,
                this.roleId,
                this.organizationId));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.user.User#getId() id} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableUser withId(Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.id, value)) {
            return this;
        }
        return validate(new ImmutableUser(
                this,
                value,
                this.name,
                this.fullName,
                this.lastName,
                this.firstName,
                this.username,
                this.integrations,
                this.roleId,
                this.organizationId));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.user.User#getName() name} attribute.
     *
     * @param value The value for name
     * @return A modified copy of {@code this} object
     */
    public final ImmutableUser withName(String value) {
        String newValue = Objects.requireNonNull(value, "name");
        if (Objects.equals(this.name, newValue)) {
            return this;
        }
        return validate(new ImmutableUser(
                this,
                this.id,
                newValue,
                this.fullName,
                this.lastName,
                this.firstName,
                this.username,
                this.integrations,
                this.roleId,
                this.organizationId));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.user.User#getName() name} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for name
     * @return A modified copy of {@code this} object
     */
    public final ImmutableUser withName(Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.name, value)) {
            return this;
        }
        return validate(new ImmutableUser(
                this,
                this.id,
                value,
                this.fullName,
                this.lastName,
                this.firstName,
                this.username,
                this.integrations,
                this.roleId,
                this.organizationId));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.user.User#getFullName() fullName} attribute.
     *
     * @param value The value for fullName
     * @return A modified copy of {@code this} object
     */
    public final ImmutableUser withFullName(String value) {
        String newValue = Objects.requireNonNull(value, "fullName");
        if (Objects.equals(this.fullName, newValue)) {
            return this;
        }
        return validate(new ImmutableUser(
                this,
                this.id,
                this.name,
                newValue,
                this.lastName,
                this.firstName,
                this.username,
                this.integrations,
                this.roleId,
                this.organizationId));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.user.User#getFullName() fullName} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for fullName
     * @return A modified copy of {@code this} object
     */
    public final ImmutableUser withFullName(Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.fullName, value)) {
            return this;
        }
        return validate(new ImmutableUser(
                this,
                this.id,
                this.name,
                value,
                this.lastName,
                this.firstName,
                this.username,
                this.integrations,
                this.roleId,
                this.organizationId));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.user.User#getLastName() lastName} attribute.
     *
     * @param value The value for lastName
     * @return A modified copy of {@code this} object
     */
    public final ImmutableUser withLastName(String value) {
        String newValue = Objects.requireNonNull(value, "lastName");
        if (Objects.equals(this.lastName, newValue)) {
            return this;
        }
        return validate(new ImmutableUser(
                this,
                this.id,
                this.name,
                this.fullName,
                newValue,
                this.firstName,
                this.username,
                this.integrations,
                this.roleId,
                this.organizationId));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.user.User#getLastName() lastName} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for lastName
     * @return A modified copy of {@code this} object
     */
    public final ImmutableUser withLastName(Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.lastName, value)) {
            return this;
        }
        return validate(new ImmutableUser(
                this,
                this.id,
                this.name,
                this.fullName,
                value,
                this.firstName,
                this.username,
                this.integrations,
                this.roleId,
                this.organizationId));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.user.User#getFirstName() firstName} attribute.
     *
     * @param value The value for firstName
     * @return A modified copy of {@code this} object
     */
    public final ImmutableUser withFirstName(String value) {
        String newValue = Objects.requireNonNull(value, "firstName");
        if (Objects.equals(this.firstName, newValue)) {
            return this;
        }
        return validate(new ImmutableUser(
                this,
                this.id,
                this.name,
                this.fullName,
                this.lastName,
                newValue,
                this.username,
                this.integrations,
                this.roleId,
                this.organizationId));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.user.User#getFirstName() firstName} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for firstName
     * @return A modified copy of {@code this} object
     */
    public final ImmutableUser withFirstName(Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.firstName, value)) {
            return this;
        }
        return validate(new ImmutableUser(
                this,
                this.id,
                this.name,
                this.fullName,
                this.lastName,
                value,
                this.username,
                this.integrations,
                this.roleId,
                this.organizationId));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.user.User#getUsername() username} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for username (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableUser withUsername(String value) {
        if (Objects.equals(this.username, value)) {
            return this;
        }
        return validate(new ImmutableUser(
                this,
                this.id,
                this.name,
                this.fullName,
                this.lastName,
                this.firstName,
                value,
                this.integrations,
                this.roleId,
                this.organizationId));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.user.User#getIntegrations() integrations}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableUser withIntegrations(Integration... elements) {
        List<Integration> newValue = createUnmodifiableList(false,
                createSafeList(Arrays.asList(elements), true, false));
        return validate(new ImmutableUser(
                this,
                this.id,
                this.name,
                this.fullName,
                this.lastName,
                this.firstName,
                this.username,
                newValue,
                this.roleId,
                this.organizationId));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.user.User#getIntegrations() integrations}.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of integrations elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableUser withIntegrations(
            Iterable<? extends Integration> elements) {
        if (this.integrations == elements) {
            return this;
        }
        List<Integration> newValue = createUnmodifiableList(false,
                createSafeList(elements, true, false));
        return validate(new ImmutableUser(
                this,
                this.id,
                this.name,
                this.fullName,
                this.lastName,
                this.firstName,
                this.username,
                newValue,
                this.roleId,
                this.organizationId));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.user.User#getRoleId() roleId} attribute.
     *
     * @param value The value for roleId
     * @return A modified copy of {@code this} object
     */
    public final ImmutableUser withRoleId(String value) {
        String newValue = Objects.requireNonNull(value, "roleId");
        if (Objects.equals(this.roleId, newValue)) {
            return this;
        }
        return validate(new ImmutableUser(
                this,
                this.id,
                this.name,
                this.fullName,
                this.lastName,
                this.firstName,
                this.username,
                this.integrations,
                newValue,
                this.organizationId));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.user.User#getRoleId() roleId} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for roleId
     * @return A modified copy of {@code this} object
     */
    public final ImmutableUser withRoleId(Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.roleId, value)) {
            return this;
        }
        return validate(new ImmutableUser(
                this,
                this.id,
                this.name,
                this.fullName,
                this.lastName,
                this.firstName,
                this.username,
                this.integrations,
                value,
                this.organizationId));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.user.User#getOrganizationId() organizationId} attribute.
     *
     * @param value The value for organizationId
     * @return A modified copy of {@code this} object
     */
    public final ImmutableUser withOrganizationId(String value) {
        String newValue = Objects.requireNonNull(value, "organizationId");
        if (Objects.equals(this.organizationId, newValue)) {
            return this;
        }
        return validate(new ImmutableUser(
                this,
                this.id,
                this.name,
                this.fullName,
                this.lastName,
                this.firstName,
                this.username,
                this.integrations,
                this.roleId,
                newValue));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.user.User#getOrganizationId() organizationId} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for organizationId
     * @return A modified copy of {@code this} object
     */
    public final ImmutableUser withOrganizationId(Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.organizationId, value)) {
            return this;
        }
        return validate(new ImmutableUser(
                this,
                this.id,
                this.name,
                this.fullName,
                this.lastName,
                this.firstName,
                this.username,
                this.integrations,
                this.roleId,
                value));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableUser} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableUser
                && equalTo((ImmutableUser) another);
    }

    private boolean equalTo(ImmutableUser another) {
        return Objects.equals(id, another.id)
                && Objects.equals(name, another.name)
                && Objects.equals(fullName, another.fullName)
                && Objects.equals(lastName, another.lastName)
                && Objects.equals(firstName, another.firstName)
                && Objects.equals(username, another.username)
                && integrations.equals(another.integrations)
                && Objects.equals(roleId, another.roleId)
                && Objects.equals(organizationId, another.organizationId);
    }

    /**
     * Computes a hash code from attributes: {@code id}, {@code name}, {@code fullName}, {@code lastName}, {@code firstName}, {@code username}, {@code integrations}, {@code roleId}, {@code organizationId}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(id);
        h += (h << 5) + Objects.hashCode(name);
        h += (h << 5) + Objects.hashCode(fullName);
        h += (h << 5) + Objects.hashCode(lastName);
        h += (h << 5) + Objects.hashCode(firstName);
        h += (h << 5) + Objects.hashCode(username);
        h += (h << 5) + integrations.hashCode();
        h += (h << 5) + Objects.hashCode(roleId);
        h += (h << 5) + Objects.hashCode(organizationId);
        return h;
    }

    /**
     * Prints the immutable value {@code User} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("User{");
        if (id != null) {
            builder.append("id=").append(id);
        }
        if (name != null) {
            if (builder.length() > 5) {
                builder.append(", ");
            }
            builder.append("name=").append(name);
        }
        if (fullName != null) {
            if (builder.length() > 5) {
                builder.append(", ");
            }
            builder.append("fullName=").append(fullName);
        }
        if (lastName != null) {
            if (builder.length() > 5) {
                builder.append(", ");
            }
            builder.append("lastName=").append(lastName);
        }
        if (firstName != null) {
            if (builder.length() > 5) {
                builder.append(", ");
            }
            builder.append("firstName=").append(firstName);
        }
        if (username != null) {
            if (builder.length() > 5) {
                builder.append(", ");
            }
            builder.append("username=").append(username);
        }
        if (builder.length() > 5) {
            builder.append(", ");
        }
        builder.append("integrations=").append(integrations);
        if (roleId != null) {
            builder.append(", ");
            builder.append("roleId=").append(roleId);
        }
        if (organizationId != null) {
            builder.append(", ");
            builder.append("organizationId=").append(organizationId);
        }
        return builder.append("}").toString();
    }

    private Object readResolve() throws ObjectStreamException {
        return validate(this);
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.user.User User}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private String id;
        private String name;
        private String fullName;
        private String lastName;
        private String firstName;
        private String username;
        private List<Integration> integrations = new ArrayList<Integration>();
        private String roleId;
        private String organizationId;

        /**
         * Creates a builder for {@link io.syndesis.common.model.user.User User} instances.
         * <pre>
         * new User.Builder()
         *    .id(String) // optional {@link io.syndesis.common.model.user.User#getId() id}
         *    .name(String) // optional {@link io.syndesis.common.model.user.User#getName() name}
         *    .fullName(String) // optional {@link io.syndesis.common.model.user.User#getFullName() fullName}
         *    .lastName(String) // optional {@link io.syndesis.common.model.user.User#getLastName() lastName}
         *    .firstName(String) // optional {@link io.syndesis.common.model.user.User#getFirstName() firstName}
         *    .username(String | null) // nullable {@link io.syndesis.common.model.user.User#getUsername() username}
         *    .addIntegration|addAllIntegrations(io.syndesis.common.io.syndesis.common.model.integration.Integration) // {@link io.syndesis.common.model.user.User#getIntegrations() integrations} elements
         *    .roleId(String) // optional {@link io.syndesis.common.model.user.User#getRoleId() roleId}
         *    .organizationId(String) // optional {@link io.syndesis.common.model.user.User#getOrganizationId() organizationId}
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof User.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new User.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithResourceId} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final User.Builder createFrom(WithResourceId instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (User.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.user.User} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final User.Builder createFrom(User instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (User.Builder) this;
        }

        private void from(Object object) {
            if (object instanceof WithResourceId) {
                WithResourceId instance = (WithResourceId) object;
                Optional<String> idOptional = instance.getId();
                if (idOptional.isPresent()) {
                    id(idOptional);
                }
            }
            if (object instanceof User) {
                User instance = (User) object;
                Optional<String> organizationIdOptional =
                        instance.getOrganizationId();
                if (organizationIdOptional.isPresent()) {
                    organizationId(organizationIdOptional);
                }
                Optional<String> lastNameOptional = instance.getLastName();
                if (lastNameOptional.isPresent()) {
                    lastName(lastNameOptional);
                }
                Optional<String> firstNameOptional = instance.getFirstName();
                if (firstNameOptional.isPresent()) {
                    firstName(firstNameOptional);
                }
                Optional<String> roleIdOptional = instance.getRoleId();
                if (roleIdOptional.isPresent()) {
                    roleId(roleIdOptional);
                }
                Optional<String> nameOptional = instance.getName();
                if (nameOptional.isPresent()) {
                    name(nameOptional);
                }
                Optional<String> fullNameOptional = instance.getFullName();
                if (fullNameOptional.isPresent()) {
                    fullName(fullNameOptional);
                }
                addAllIntegrations(instance.getIntegrations());
                String usernameValue = instance.getUsername();
                if (usernameValue != null) {
                    username(usernameValue);
                }
            }
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.user.User#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final User.Builder id(String id) {
            this.id = Objects.requireNonNull(id, "id");
            return (User.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.user.User#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("id")
        public final User.Builder id(Optional<String> id) {
            this.id = id.orElse(null);
            return (User.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.user.User#getName() name} to name.
         *
         * @param name The value for name
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final User.Builder name(String name) {
            this.name = Objects.requireNonNull(name, "name");
            return (User.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.user.User#getName() name} to name.
         *
         * @param name The value for name
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("name")
        public final User.Builder name(Optional<String> name) {
            this.name = name.orElse(null);
            return (User.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.user.User#getFullName() fullName} to fullName.
         *
         * @param fullName The value for fullName
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final User.Builder fullName(String fullName) {
            this.fullName = Objects.requireNonNull(fullName, "fullName");
            return (User.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.user.User#getFullName() fullName} to fullName.
         *
         * @param fullName The value for fullName
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("fullName")
        public final User.Builder fullName(Optional<String> fullName) {
            this.fullName = fullName.orElse(null);
            return (User.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.user.User#getLastName() lastName} to lastName.
         *
         * @param lastName The value for lastName
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final User.Builder lastName(String lastName) {
            this.lastName = Objects.requireNonNull(lastName, "lastName");
            return (User.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.user.User#getLastName() lastName} to lastName.
         *
         * @param lastName The value for lastName
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("lastName")
        public final User.Builder lastName(Optional<String> lastName) {
            this.lastName = lastName.orElse(null);
            return (User.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.user.User#getFirstName() firstName} to firstName.
         *
         * @param firstName The value for firstName
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final User.Builder firstName(String firstName) {
            this.firstName = Objects.requireNonNull(firstName, "firstName");
            return (User.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.user.User#getFirstName() firstName} to firstName.
         *
         * @param firstName The value for firstName
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("firstName")
        public final User.Builder firstName(Optional<String> firstName) {
            this.firstName = firstName.orElse(null);
            return (User.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.user.User#getUsername() username} attribute.
         *
         * @param username The value for username (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("username")
        public final User.Builder username(String username) {
            this.username = username;
            return (User.Builder) this;
        }

        /**
         * Adds one element to {@link io.syndesis.common.model.user.User#getIntegrations() integrations} list.
         *
         * @param element A integrations element
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final User.Builder addIntegration(Integration element) {
            this.integrations.add(
                    Objects.requireNonNull(element, "integrations element"));
            return (User.Builder) this;
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.user.User#getIntegrations() integrations} list.
         *
         * @param elements An array of integrations elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final User.Builder addIntegrations(Integration... elements) {
            for (Integration element : elements) {
                this.integrations.add(Objects.requireNonNull(element,
                        "integrations element"));
            }
            return (User.Builder) this;
        }


        /**
         * Sets or replaces all elements for {@link io.syndesis.common.model.user.User#getIntegrations() integrations} list.
         *
         * @param elements An iterable of integrations elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("integrations")
        public final User.Builder integrations(
                Iterable<? extends Integration> elements) {
            this.integrations.clear();
            return addAllIntegrations(elements);
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.user.User#getIntegrations() integrations} list.
         *
         * @param elements An iterable of integrations elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final User.Builder addAllIntegrations(
                Iterable<? extends Integration> elements) {
            for (Integration element : elements) {
                this.integrations.add(Objects.requireNonNull(element,
                        "integrations element"));
            }
            return (User.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.user.User#getRoleId() roleId} to roleId.
         *
         * @param roleId The value for roleId
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final User.Builder roleId(String roleId) {
            this.roleId = Objects.requireNonNull(roleId, "roleId");
            return (User.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.user.User#getRoleId() roleId} to roleId.
         *
         * @param roleId The value for roleId
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("roleId")
        public final User.Builder roleId(Optional<String> roleId) {
            this.roleId = roleId.orElse(null);
            return (User.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.user.User#getOrganizationId() organizationId} to organizationId.
         *
         * @param organizationId The value for organizationId
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final User.Builder organizationId(String organizationId) {
            this.organizationId =
                    Objects.requireNonNull(organizationId, "organizationId");
            return (User.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.user.User#getOrganizationId() organizationId} to organizationId.
         *
         * @param organizationId The value for organizationId
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("organizationId")
        public final User.Builder organizationId(
                Optional<String> organizationId) {
            this.organizationId = organizationId.orElse(null);
            return (User.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.user.User User}.
         *
         * @return An immutable instance of User
         * @throws IllegalStateException if any required attributes are missing
         */
        public User build() {
            return ImmutableUser.validate(new ImmutableUser(
                    null,
                    id,
                    name,
                    fullName,
                    lastName,
                    firstName,
                    username,
                    createUnmodifiableList(true, integrations),
                    roleId,
                    organizationId));
        }
    }
}
