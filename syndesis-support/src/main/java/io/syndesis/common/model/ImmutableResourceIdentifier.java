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
package io.syndesis.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Var;

import java.io.ObjectStreamException;
import java.util.Objects;
import java.util.Optional;

/**
 * Immutable implementation of {@link io.syndesis.common.model.ResourceIdentifier}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new ResourceIdentifier.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableResourceIdentifier.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableResourceIdentifier implements ResourceIdentifier {
    private final String id;
    private final Kind kind;
    private final String name;
    private final Integer version;

    private ImmutableResourceIdentifier(
            Optional<String> id,
            Kind kind,
            Optional<String> name,
            Optional<Integer> version) {
        this.id = id.orElse(null);
        this.kind = kind;
        this.name = name.orElse(null);
        this.version = version.orElse(null);
    }

    private ImmutableResourceIdentifier(
            ImmutableResourceIdentifier original,
            String id,
            Kind kind,
            String name,
            Integer version) {
        this.id = id;
        this.kind = kind;
        this.name = name;
        this.version = version;
    }

    /**
     * Construct a new immutable {@code ResourceIdentifier} instance.
     *
     * @param id      The value for the {@code id} attribute
     * @param kind    The value for the {@code kind} attribute
     * @param name    The value for the {@code name} attribute
     * @param version The value for the {@code version} attribute
     * @return An immutable ResourceIdentifier instance
     */
    public static ResourceIdentifier of(Optional<String> id, Kind kind,
                                        Optional<String> name,
                                        Optional<Integer> version) {
        return validate(
                new ImmutableResourceIdentifier(id, kind, name, version));
    }

    private static ImmutableResourceIdentifier validate(
            ImmutableResourceIdentifier instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.ResourceIdentifier} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable ResourceIdentifier instance
     */
    public static ResourceIdentifier copyOf(ResourceIdentifier instance) {
        if (instance instanceof ImmutableResourceIdentifier) {
            return (ImmutableResourceIdentifier) instance;
        }
        return new ResourceIdentifier.Builder()
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
     * @return The value of the {@code kind} attribute
     */
    @JsonProperty("kind")
    @JsonIgnore(false)
    @Override
    public Kind getKind() {
        return kind;
    }

    /**
     * @return The value of the {@code name} attribute
     */
    @JsonProperty("name")
    @Override
    public Optional<String> name() {
        return Optional.ofNullable(name);
    }

    /**
     * @return The value of the {@code version} attribute
     */
    @JsonProperty("version")
    @Override
    public Optional<Integer> getVersion() {
        return Optional.ofNullable(version);
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.ResourceIdentifier#getId() id} attribute.
     *
     * @param value The value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableResourceIdentifier withId(String value) {
        String newValue = Objects.requireNonNull(value, "id");
        if (Objects.equals(this.id, newValue)) {
            return this;
        }
        return validate(
                new ImmutableResourceIdentifier(this, newValue, this.kind,
                        this.name, this.version));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.ResourceIdentifier#getId() id} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableResourceIdentifier withId(Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.id, value)) {
            return this;
        }
        return validate(new ImmutableResourceIdentifier(this, value, this.kind,
                this.name, this.version));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.ResourceIdentifier#getKind() kind} attribute.
     * A value equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for kind (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableResourceIdentifier withKind(Kind value) {
        if (this.kind == value) {
            return this;
        }
        if (Objects.equals(this.kind, value)) {
            return this;
        }
        return validate(
                new ImmutableResourceIdentifier(this, this.id, value, this.name,
                        this.version));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.ResourceIdentifier#name() name} attribute.
     *
     * @param value The value for name
     * @return A modified copy of {@code this} object
     */
    public final ImmutableResourceIdentifier withName(String value) {
        String newValue = Objects.requireNonNull(value, "name");
        if (Objects.equals(this.name, newValue)) {
            return this;
        }
        return validate(
                new ImmutableResourceIdentifier(this, this.id, this.kind,
                        newValue, this.version));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.ResourceIdentifier#name() name} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for name
     * @return A modified copy of {@code this} object
     */
    public final ImmutableResourceIdentifier withName(
            Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.name, value)) {
            return this;
        }
        return validate(
                new ImmutableResourceIdentifier(this, this.id, this.kind, value,
                        this.version));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.ResourceIdentifier#getVersion() version} attribute.
     *
     * @param value The value for version
     * @return A modified copy of {@code this} object
     */
    public final ImmutableResourceIdentifier withVersion(int value) {
        Integer newValue = value;
        if (Objects.equals(this.version, newValue)) {
            return this;
        }
        return validate(
                new ImmutableResourceIdentifier(this, this.id, this.kind,
                        this.name, newValue));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.ResourceIdentifier#getVersion() version} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for version
     * @return A modified copy of {@code this} object
     */
    public final ImmutableResourceIdentifier withVersion(
            Optional<Integer> optional) {
        Integer value = optional.orElse(null);
        if (Objects.equals(this.version, value)) {
            return this;
        }
        return validate(
                new ImmutableResourceIdentifier(this, this.id, this.kind,
                        this.name, value));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableResourceIdentifier} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableResourceIdentifier
                && equalTo((ImmutableResourceIdentifier) another);
    }

    private boolean equalTo(ImmutableResourceIdentifier another) {
        return Objects.equals(id, another.id)
                && Objects.equals(kind, another.kind)
                && Objects.equals(name, another.name)
                && Objects.equals(version, another.version);
    }

    /**
     * Computes a hash code from attributes: {@code id}, {@code kind}, {@code name}, {@code version}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(id);
        h += (h << 5) + Objects.hashCode(kind);
        h += (h << 5) + Objects.hashCode(name);
        h += (h << 5) + Objects.hashCode(version);
        return h;
    }

    /**
     * Prints the immutable value {@code ResourceIdentifier} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("ResourceIdentifier{");
        if (id != null) {
            builder.append("id=").append(id);
        }
        if (kind != null) {
            if (builder.length() > 19) {
                builder.append(", ");
            }
            builder.append("kind=").append(kind);
        }
        if (name != null) {
            if (builder.length() > 19) {
                builder.append(", ");
            }
            builder.append("name=").append(name);
        }
        if (version != null) {
            if (builder.length() > 19) {
                builder.append(", ");
            }
            builder.append("version=").append(version);
        }
        return builder.append("}").toString();
    }

    private Object readResolve() throws ObjectStreamException {
        return validate(this);
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.ResourceIdentifier ResourceIdentifier}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private String id;
        private Kind kind;
        private String name;
        private Integer version;

        /**
         * Creates a builder for {@link io.syndesis.common.model.ResourceIdentifier ResourceIdentifier} instances.
         * <pre>
         * new ResourceIdentifier.Builder()
         *    .id(String) // optional {@link io.syndesis.common.model.ResourceIdentifier#getId() id}
         *    .kind(io.syndesis.common.io.syndesis.common.model.Kind | null) // nullable {@link io.syndesis.common.model.ResourceIdentifier#getKind() kind}
         *    .name(String) // optional {@link io.syndesis.common.model.ResourceIdentifier#name() name}
         *    .version(Integer) // optional {@link io.syndesis.common.model.ResourceIdentifier#getVersion() version}
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof ResourceIdentifier.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new ResourceIdentifier.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithKind} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ResourceIdentifier.Builder createFrom(WithKind instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (ResourceIdentifier.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.ResourceIdentifier} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ResourceIdentifier.Builder createFrom(
                ResourceIdentifier instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (ResourceIdentifier.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithResourceId} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ResourceIdentifier.Builder createFrom(
                WithResourceId instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (ResourceIdentifier.Builder) this;
        }

        private void from(Object object) {
            @Var long bits = 0;
            if (object instanceof WithKind) {
                WithKind instance = (WithKind) object;
                if ((bits & 0x1L) == 0) {
                    Kind kindValue = instance.getKind();
                    if (kindValue != null) {
                        kind(kindValue);
                    }
                    bits |= 0x1L;
                }
            }
            if (object instanceof ResourceIdentifier) {
                ResourceIdentifier instance = (ResourceIdentifier) object;
                Optional<String> nameOptional = instance.name();
                if (nameOptional.isPresent()) {
                    name(nameOptional);
                }
                Optional<Integer> versionOptional = instance.getVersion();
                if (versionOptional.isPresent()) {
                    version(versionOptional);
                }
                if ((bits & 0x1L) == 0) {
                    Kind kindValue = instance.getKind();
                    if (kindValue != null) {
                        kind(kindValue);
                    }
                    bits |= 0x1L;
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
         * Initializes the optional value {@link io.syndesis.common.model.ResourceIdentifier#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final ResourceIdentifier.Builder id(String id) {
            this.id = Objects.requireNonNull(id, "id");
            return (ResourceIdentifier.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.ResourceIdentifier#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("id")
        public final ResourceIdentifier.Builder id(Optional<String> id) {
            this.id = id.orElse(null);
            return (ResourceIdentifier.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.ResourceIdentifier#getKind() kind} attribute.
         *
         * @param kind The value for kind (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("kind")
        @JsonIgnore(false)
        public final ResourceIdentifier.Builder kind(Kind kind) {
            this.kind = kind;
            return (ResourceIdentifier.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.ResourceIdentifier#name() name} to name.
         *
         * @param name The value for name
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final ResourceIdentifier.Builder name(String name) {
            this.name = Objects.requireNonNull(name, "name");
            return (ResourceIdentifier.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.ResourceIdentifier#name() name} to name.
         *
         * @param name The value for name
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("name")
        public final ResourceIdentifier.Builder name(Optional<String> name) {
            this.name = name.orElse(null);
            return (ResourceIdentifier.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.ResourceIdentifier#getVersion() version} to version.
         *
         * @param version The value for version
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final ResourceIdentifier.Builder version(int version) {
            this.version = version;
            return (ResourceIdentifier.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.ResourceIdentifier#getVersion() version} to version.
         *
         * @param version The value for version
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("version")
        public final ResourceIdentifier.Builder version(
                Optional<Integer> version) {
            this.version = version.orElse(null);
            return (ResourceIdentifier.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.ResourceIdentifier ResourceIdentifier}.
         *
         * @return An immutable instance of ResourceIdentifier
         * @throws IllegalStateException if any required attributes are missing
         */
        public ResourceIdentifier build() {
            return ImmutableResourceIdentifier.validate(
                    new ImmutableResourceIdentifier(null, id, kind, name,
                            version));
        }
    }
}
