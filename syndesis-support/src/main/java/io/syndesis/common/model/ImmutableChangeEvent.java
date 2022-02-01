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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Var;

import java.io.ObjectStreamException;
import java.util.Objects;
import java.util.Optional;

/**
 * Immutable implementation of {@link io.syndesis.common.model.ChangeEvent}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new ChangeEvent.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableChangeEvent.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableChangeEvent implements ChangeEvent {
    private final String action;
    private final String kind;
    private final String id;

    private ImmutableChangeEvent(
            Optional<String> action,
            Optional<String> kind,
            Optional<String> id) {
        this.action = action.orElse(null);
        this.kind = kind.orElse(null);
        this.id = id.orElse(null);
    }

    private ImmutableChangeEvent(
            ImmutableChangeEvent original,
            String action,
            String kind,
            String id) {
        this.action = action;
        this.kind = kind;
        this.id = id;
    }

    /**
     * Construct a new immutable {@code ChangeEvent} instance.
     *
     * @param action The value for the {@code action} attribute
     * @param kind   The value for the {@code kind} attribute
     * @param id     The value for the {@code id} attribute
     * @return An immutable ChangeEvent instance
     */
    public static ChangeEvent of(Optional<String> action, Optional<String> kind,
                                 Optional<String> id) {
        return validate(new ImmutableChangeEvent(action, kind, id));
    }

    private static ImmutableChangeEvent validate(
            ImmutableChangeEvent instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.ChangeEvent} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable ChangeEvent instance
     */
    public static ChangeEvent copyOf(ChangeEvent instance) {
        if (instance instanceof ImmutableChangeEvent) {
            return (ImmutableChangeEvent) instance;
        }
        return new ChangeEvent.Builder()
                .createFrom(instance)
                .build();
    }

    /**
     * @return The value of the {@code action} attribute
     */
    @JsonProperty("action")
    @Override
    public Optional<String> getAction() {
        return Optional.ofNullable(action);
    }

    /**
     * @return The value of the {@code kind} attribute
     */
    @JsonProperty("kind")
    @Override
    public Optional<String> getKind() {
        return Optional.ofNullable(kind);
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
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.ChangeEvent#getAction() action} attribute.
     *
     * @param value The value for action
     * @return A modified copy of {@code this} object
     */
    public final ImmutableChangeEvent withAction(String value) {
        String newValue = Objects.requireNonNull(value, "action");
        if (Objects.equals(this.action, newValue)) {
            return this;
        }
        return validate(
                new ImmutableChangeEvent(this, newValue, this.kind, this.id));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.ChangeEvent#getAction() action} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for action
     * @return A modified copy of {@code this} object
     */
    public final ImmutableChangeEvent withAction(Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.action, value)) {
            return this;
        }
        return validate(
                new ImmutableChangeEvent(this, value, this.kind, this.id));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.ChangeEvent#getKind() kind} attribute.
     *
     * @param value The value for kind
     * @return A modified copy of {@code this} object
     */
    public final ImmutableChangeEvent withKind(String value) {
        String newValue = Objects.requireNonNull(value, "kind");
        if (Objects.equals(this.kind, newValue)) {
            return this;
        }
        return validate(
                new ImmutableChangeEvent(this, this.action, newValue, this.id));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.ChangeEvent#getKind() kind} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for kind
     * @return A modified copy of {@code this} object
     */
    public final ImmutableChangeEvent withKind(Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.kind, value)) {
            return this;
        }
        return validate(
                new ImmutableChangeEvent(this, this.action, value, this.id));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.ChangeEvent#getId() id} attribute.
     *
     * @param value The value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableChangeEvent withId(String value) {
        String newValue = Objects.requireNonNull(value, "id");
        if (Objects.equals(this.id, newValue)) {
            return this;
        }
        return validate(new ImmutableChangeEvent(this, this.action, this.kind,
                newValue));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.ChangeEvent#getId() id} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableChangeEvent withId(Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.id, value)) {
            return this;
        }
        return validate(
                new ImmutableChangeEvent(this, this.action, this.kind, value));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableChangeEvent} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableChangeEvent
                && equalTo((ImmutableChangeEvent) another);
    }

    private boolean equalTo(ImmutableChangeEvent another) {
        return Objects.equals(action, another.action)
                && Objects.equals(kind, another.kind)
                && Objects.equals(id, another.id);
    }

    /**
     * Computes a hash code from attributes: {@code action}, {@code kind}, {@code id}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(action);
        h += (h << 5) + Objects.hashCode(kind);
        h += (h << 5) + Objects.hashCode(id);
        return h;
    }

    /**
     * Prints the immutable value {@code ChangeEvent} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("ChangeEvent{");
        if (action != null) {
            builder.append("action=").append(action);
        }
        if (kind != null) {
            if (builder.length() > 12) {
                builder.append(", ");
            }
            builder.append("kind=").append(kind);
        }
        if (id != null) {
            if (builder.length() > 12) {
                builder.append(", ");
            }
            builder.append("id=").append(id);
        }
        return builder.append("}").toString();
    }

    private Object readResolve() throws ObjectStreamException {
        return validate(this);
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.ChangeEvent ChangeEvent}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private String action;
        private String kind;
        private String id;

        /**
         * Creates a builder for {@link io.syndesis.common.model.ChangeEvent ChangeEvent} instances.
         * <pre>
         * new ChangeEvent.Builder()
         *    .action(String) // optional {@link io.syndesis.common.model.ChangeEvent#getAction() action}
         *    .kind(String) // optional {@link io.syndesis.common.model.ChangeEvent#getKind() kind}
         *    .id(String) // optional {@link io.syndesis.common.model.ChangeEvent#getId() id}
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof ChangeEvent.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new ChangeEvent.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code ChangeEvent} instance.
         * Regular attribute values will be replaced with those from the given instance.
         * Absent optional values will not replace present values.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ChangeEvent.Builder createFrom(ChangeEvent instance) {
            Objects.requireNonNull(instance, "instance");
            Optional<String> actionOptional = instance.getAction();
            if (actionOptional.isPresent()) {
                action(actionOptional);
            }
            Optional<String> kindOptional = instance.getKind();
            if (kindOptional.isPresent()) {
                kind(kindOptional);
            }
            Optional<String> idOptional = instance.getId();
            if (idOptional.isPresent()) {
                id(idOptional);
            }
            return (ChangeEvent.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.ChangeEvent#getAction() action} to action.
         *
         * @param action The value for action
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final ChangeEvent.Builder action(String action) {
            this.action = Objects.requireNonNull(action, "action");
            return (ChangeEvent.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.ChangeEvent#getAction() action} to action.
         *
         * @param action The value for action
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("action")
        public final ChangeEvent.Builder action(Optional<String> action) {
            this.action = action.orElse(null);
            return (ChangeEvent.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.ChangeEvent#getKind() kind} to kind.
         *
         * @param kind The value for kind
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final ChangeEvent.Builder kind(String kind) {
            this.kind = Objects.requireNonNull(kind, "kind");
            return (ChangeEvent.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.ChangeEvent#getKind() kind} to kind.
         *
         * @param kind The value for kind
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("kind")
        public final ChangeEvent.Builder kind(Optional<String> kind) {
            this.kind = kind.orElse(null);
            return (ChangeEvent.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.ChangeEvent#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final ChangeEvent.Builder id(String id) {
            this.id = Objects.requireNonNull(id, "id");
            return (ChangeEvent.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.ChangeEvent#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("id")
        public final ChangeEvent.Builder id(Optional<String> id) {
            this.id = id.orElse(null);
            return (ChangeEvent.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.ChangeEvent ChangeEvent}.
         *
         * @return An immutable instance of ChangeEvent
         * @throws IllegalStateException if any required attributes are missing
         */
        public ChangeEvent build() {
            return ImmutableChangeEvent.validate(
                    new ImmutableChangeEvent(null, action, kind, id));
        }
    }
}
