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

import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Immutable implementation of {@link io.syndesis.common.model.environment.Environment}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new Environment.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableEnvironment.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableEnvironment implements Environment {
    private final String id;
    private final String name;
    private final EnvironmentType environmentType;
    private final List<Organization> organizations;

    private ImmutableEnvironment(
            Optional<String> id,
            String name,
            EnvironmentType environmentType,
            Iterable<? extends Organization> organizations) {
        this.id = id.orElse(null);
        this.name = name;
        this.environmentType = environmentType;
        this.organizations = createUnmodifiableList(false,
                createSafeList(organizations, true, false));
    }

    private ImmutableEnvironment(
            ImmutableEnvironment original,
            String id,
            String name,
            EnvironmentType environmentType,
            List<Organization> organizations) {
        this.id = id;
        this.name = name;
        this.environmentType = environmentType;
        this.organizations = organizations;
    }

    /**
     * Construct a new immutable {@code Environment} instance.
     *
     * @param id              The value for the {@code id} attribute
     * @param name            The value for the {@code name} attribute
     * @param environmentType The value for the {@code environmentType} attribute
     * @param organizations   The value for the {@code organizations} attribute
     * @return An immutable Environment instance
     */
    public static Environment of(Optional<String> id, String name,
                                 EnvironmentType environmentType,
                                 List<Organization> organizations) {
        return of(id, name, environmentType,
                (Iterable<? extends Organization>) organizations);
    }

    /**
     * Construct a new immutable {@code Environment} instance.
     *
     * @param id              The value for the {@code id} attribute
     * @param name            The value for the {@code name} attribute
     * @param environmentType The value for the {@code environmentType} attribute
     * @param organizations   The value for the {@code organizations} attribute
     * @return An immutable Environment instance
     */
    public static Environment of(Optional<String> id, String name,
                                 EnvironmentType environmentType,
                                 Iterable<? extends Organization> organizations) {
        return validate(new ImmutableEnvironment(id, name, environmentType,
                organizations));
    }

    private static ImmutableEnvironment validate(
            ImmutableEnvironment instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.environment.Environment} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable Environment instance
     */
    public static Environment copyOf(Environment instance) {
        if (instance instanceof ImmutableEnvironment) {
            return (ImmutableEnvironment) instance;
        }
        return new Environment.Builder()
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
     * @return The value of the {@code environmentType} attribute
     */
    @JsonProperty("environmentType")
    @Override
    public EnvironmentType environmentType() {
        return environmentType;
    }

    /**
     * @return The value of the {@code organizations} attribute
     */
    @JsonProperty("organizations")
    @Override
    public List<Organization> organizations() {
        return organizations;
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.environment.Environment#getId() id} attribute.
     *
     * @param value The value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableEnvironment withId(String value) {
        String newValue = Objects.requireNonNull(value, "id");
        if (Objects.equals(this.id, newValue)) {
            return this;
        }
        return validate(new ImmutableEnvironment(this, newValue, this.name,
                this.environmentType, this.organizations));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.environment.Environment#getId() id} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableEnvironment withId(Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.id, value)) {
            return this;
        }
        return validate(new ImmutableEnvironment(this, value, this.name,
                this.environmentType, this.organizations));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.environment.Environment#getName() name} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for name (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableEnvironment withName(String value) {
        if (Objects.equals(this.name, value)) {
            return this;
        }
        return validate(new ImmutableEnvironment(this, this.id, value,
                this.environmentType, this.organizations));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.environment.Environment#environmentType() environmentType} attribute.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for environmentType (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableEnvironment withEnvironmentType(
            EnvironmentType value) {
        if (this.environmentType == value) {
            return this;
        }
        return validate(
                new ImmutableEnvironment(this, this.id, this.name, value,
                        this.organizations));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.environment.Environment#organizations() organizations}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableEnvironment withOrganizations(
            Organization... elements) {
        List<Organization> newValue = createUnmodifiableList(false,
                createSafeList(Arrays.asList(elements), true, false));
        return validate(new ImmutableEnvironment(this, this.id, this.name,
                this.environmentType, newValue));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.environment.Environment#organizations() organizations}.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of organizations elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableEnvironment withOrganizations(
            Iterable<? extends Organization> elements) {
        if (this.organizations == elements) {
            return this;
        }
        List<Organization> newValue = createUnmodifiableList(false,
                createSafeList(elements, true, false));
        return validate(new ImmutableEnvironment(this, this.id, this.name,
                this.environmentType, newValue));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableEnvironment} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableEnvironment
                && equalTo((ImmutableEnvironment) another);
    }

    private boolean equalTo(ImmutableEnvironment another) {
        return Objects.equals(id, another.id)
                && Objects.equals(name, another.name)
                && Objects.equals(environmentType, another.environmentType)
                && organizations.equals(another.organizations);
    }

    /**
     * Computes a hash code from attributes: {@code id}, {@code name}, {@code environmentType}, {@code organizations}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(id);
        h += (h << 5) + Objects.hashCode(name);
        h += (h << 5) + Objects.hashCode(environmentType);
        h += (h << 5) + organizations.hashCode();
        return h;
    }

    /**
     * Prints the immutable value {@code Environment} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Environment{");
        if (id != null) {
            builder.append("id=").append(id);
        }
        if (name != null) {
            if (builder.length() > 12) {
                builder.append(", ");
            }
            builder.append("name=").append(name);
        }
        if (environmentType != null) {
            if (builder.length() > 12) {
                builder.append(", ");
            }
            builder.append("environmentType=").append(environmentType);
        }
        if (builder.length() > 12) {
            builder.append(", ");
        }
        builder.append("organizations=").append(organizations);
        return builder.append("}").toString();
    }

    private Object readResolve() throws ObjectStreamException {
        return validate(this);
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.environment.Environment Environment}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private String id;
        private String name;
        private EnvironmentType environmentType;
        private List<Organization> organizations =
                new ArrayList<Organization>();

        /**
         * Creates a builder for {@link io.syndesis.common.model.environment.Environment Environment} instances.
         * <pre>
         * new Environment.Builder()
         *    .id(String) // optional {@link io.syndesis.common.model.environment.Environment#getId() id}
         *    .name(String | null) // nullable {@link io.syndesis.common.model.environment.Environment#getName() name}
         *    .environmentType(io.syndesis.common.io.syndesis.common.model.environment.EnvironmentType | null) // nullable {@link io.syndesis.common.model.environment.Environment#environmentType() environmentType}
         *    .addOrganization|addAllOrganizations(io.syndesis.common.io.syndesis.common.model.environment.Organization) // {@link io.syndesis.common.model.environment.Environment#organizations() organizations} elements
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof Environment.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new Environment.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithName} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Environment.Builder createFrom(WithName instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Environment.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithResourceId} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Environment.Builder createFrom(WithResourceId instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Environment.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.environment.Environment} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Environment.Builder createFrom(Environment instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Environment.Builder) this;
        }

        private void from(Object object) {
            if (object instanceof WithName) {
                WithName instance = (WithName) object;
                String nameValue = instance.getName();
                if (nameValue != null) {
                    name(nameValue);
                }
            }
            if (object instanceof WithResourceId) {
                WithResourceId instance = (WithResourceId) object;
                Optional<String> idOptional = instance.getId();
                if (idOptional.isPresent()) {
                    id(idOptional);
                }
            }
            if (object instanceof Environment) {
                Environment instance = (Environment) object;
                addAllOrganizations(instance.organizations());
                EnvironmentType environmentTypeValue =
                        instance.environmentType();
                if (environmentTypeValue != null) {
                    environmentType(environmentTypeValue);
                }
            }
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.environment.Environment#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final Environment.Builder id(String id) {
            this.id = Objects.requireNonNull(id, "id");
            return (Environment.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.environment.Environment#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("id")
        public final Environment.Builder id(Optional<String> id) {
            this.id = id.orElse(null);
            return (Environment.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.environment.Environment#getName() name} attribute.
         *
         * @param name The value for name (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("name")
        public final Environment.Builder name(String name) {
            this.name = name;
            return (Environment.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.environment.Environment#environmentType() environmentType} attribute.
         *
         * @param environmentType The value for environmentType (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("environmentType")
        public final Environment.Builder environmentType(
                EnvironmentType environmentType) {
            this.environmentType = environmentType;
            return (Environment.Builder) this;
        }

        /**
         * Adds one element to {@link io.syndesis.common.model.environment.Environment#organizations() organizations} list.
         *
         * @param element A organizations element
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Environment.Builder addOrganization(Organization element) {
            this.organizations.add(
                    Objects.requireNonNull(element, "organizations element"));
            return (Environment.Builder) this;
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.environment.Environment#organizations() organizations} list.
         *
         * @param elements An array of organizations elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Environment.Builder addOrganizations(
                Organization... elements) {
            for (Organization element : elements) {
                this.organizations.add(Objects.requireNonNull(element,
                        "organizations element"));
            }
            return (Environment.Builder) this;
        }


        /**
         * Sets or replaces all elements for {@link io.syndesis.common.model.environment.Environment#organizations() organizations} list.
         *
         * @param elements An iterable of organizations elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("organizations")
        public final Environment.Builder organizations(
                Iterable<? extends Organization> elements) {
            this.organizations.clear();
            return addAllOrganizations(elements);
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.environment.Environment#organizations() organizations} list.
         *
         * @param elements An iterable of organizations elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Environment.Builder addAllOrganizations(
                Iterable<? extends Organization> elements) {
            for (Organization element : elements) {
                this.organizations.add(Objects.requireNonNull(element,
                        "organizations element"));
            }
            return (Environment.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.environment.Environment Environment}.
         *
         * @return An immutable instance of Environment
         * @throws IllegalStateException if any required attributes are missing
         */
        public Environment build() {
            return ImmutableEnvironment.validate(
                    new ImmutableEnvironment(null, id, name, environmentType,
                            createUnmodifiableList(true, organizations)));
        }
    }
}
