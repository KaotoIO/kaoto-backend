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
package io.syndesis.common.model.icon;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Var;
import io.syndesis.common.model.WithResourceId;

import java.util.Objects;
import java.util.Optional;

/**
 * Immutable implementation of {@link io.syndesis.common.model.icon.Icon}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new Icon.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableIcon.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableIcon implements Icon {
    private final String id;
    private final String mediaType;

    private ImmutableIcon(Optional<String> id, String mediaType) {
        this.id = id.orElse(null);
        this.mediaType = mediaType;
    }

    private ImmutableIcon(ImmutableIcon original, String id, String mediaType) {
        this.id = id;
        this.mediaType = mediaType;
    }

    /**
     * Construct a new immutable {@code Icon} instance.
     *
     * @param id        The value for the {@code id} attribute
     * @param mediaType The value for the {@code mediaType} attribute
     * @return An immutable Icon instance
     */
    public static Icon of(Optional<String> id, String mediaType) {
        return validate(new ImmutableIcon(id, mediaType));
    }

    private static ImmutableIcon validate(ImmutableIcon instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.icon.Icon} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable Icon instance
     */
    public static Icon copyOf(Icon instance) {
        if (instance instanceof ImmutableIcon) {
            return (ImmutableIcon) instance;
        }
        return new Icon.Builder()
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
     * @return The value of the {@code mediaType} attribute
     */
    @JsonProperty("mediaType")
    @Override
    public String getMediaType() {
        return mediaType;
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.icon.Icon#getId() id} attribute.
     *
     * @param value The value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIcon withId(String value) {
        String newValue = Objects.requireNonNull(value, "id");
        if (Objects.equals(this.id, newValue)) {
            return this;
        }
        return validate(new ImmutableIcon(this, newValue, this.mediaType));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.icon.Icon#getId() id} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIcon withId(Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.id, value)) {
            return this;
        }
        return validate(new ImmutableIcon(this, value, this.mediaType));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.icon.Icon#getMediaType() mediaType} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for mediaType (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableIcon withMediaType(String value) {
        if (Objects.equals(this.mediaType, value)) {
            return this;
        }
        return validate(new ImmutableIcon(this, this.id, value));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableIcon} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableIcon
                && equalTo((ImmutableIcon) another);
    }

    private boolean equalTo(ImmutableIcon another) {
        return Objects.equals(id, another.id)
                && Objects.equals(mediaType, another.mediaType);
    }

    /**
     * Computes a hash code from attributes: {@code id}, {@code mediaType}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(id);
        h += (h << 5) + Objects.hashCode(mediaType);
        return h;
    }

    /**
     * Prints the immutable value {@code Icon} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Icon{");
        if (id != null) {
            builder.append("id=").append(id);
        }
        if (mediaType != null) {
            if (builder.length() > 5) {
                builder.append(", ");
            }
            builder.append("mediaType=").append(mediaType);
        }
        return builder.append("}").toString();
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.icon.Icon Icon}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private String id;
        private String mediaType;

        /**
         * Creates a builder for {@link io.syndesis.common.model.icon.Icon Icon} instances.
         * <pre>
         * new Icon.Builder()
         *    .id(String) // optional {@link io.syndesis.common.model.icon.Icon#getId() id}
         *    .mediaType(String | null) // nullable {@link io.syndesis.common.model.icon.Icon#getMediaType() mediaType}
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof Icon.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new Icon.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.icon.Icon} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Icon.Builder createFrom(Icon instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Icon.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithResourceId} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Icon.Builder createFrom(WithResourceId instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Icon.Builder) this;
        }

        private void from(Object object) {
            if (object instanceof Icon) {
                Icon instance = (Icon) object;
                String mediaTypeValue = instance.getMediaType();
                if (mediaTypeValue != null) {
                    mediaType(mediaTypeValue);
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
         * Initializes the optional value {@link io.syndesis.common.model.icon.Icon#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final Icon.Builder id(String id) {
            this.id = Objects.requireNonNull(id, "id");
            return (Icon.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.icon.Icon#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("id")
        public final Icon.Builder id(Optional<String> id) {
            this.id = id.orElse(null);
            return (Icon.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.icon.Icon#getMediaType() mediaType} attribute.
         *
         * @param mediaType The value for mediaType (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("mediaType")
        public final Icon.Builder mediaType(String mediaType) {
            this.mediaType = mediaType;
            return (Icon.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.icon.Icon Icon}.
         *
         * @return An immutable instance of Icon
         * @throws IllegalStateException if any required attributes are missing
         */
        public Icon build() {
            return ImmutableIcon.validate(
                    new ImmutableIcon(null, id, mediaType));
        }
    }
}
