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
 * Immutable implementation of {@link io.syndesis.common.model.EventMessage}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code ImmutableEventMessage.builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableEventMessage.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableEventMessage implements EventMessage {
    private final String event;
    private final Object data;

    @SuppressWarnings("unchecked") // safe covariant cast
    private ImmutableEventMessage(Optional<String> event,
                                  Optional<? extends Object> data) {
        this.event = event.orElse(null);
        this.data = data.orElse(null);
    }

    private ImmutableEventMessage(
            ImmutableEventMessage original,
            String event,
            Object data) {
        this.event = event;
        this.data = data;
    }

    /**
     * Construct a new immutable {@code EventMessage} instance.
     *
     * @param event The value for the {@code event} attribute
     * @param data  The value for the {@code data} attribute
     * @return An immutable EventMessage instance
     */
    public static EventMessage of(Optional<String> event,
                                  Optional<? extends Object> data) {
        return validate(new ImmutableEventMessage(event, data));
    }

    private static ImmutableEventMessage validate(
            ImmutableEventMessage instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.EventMessage} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable EventMessage instance
     */
    public static EventMessage copyOf(EventMessage instance) {
        if (instance instanceof ImmutableEventMessage) {
            return (ImmutableEventMessage) instance;
        }
        return ImmutableEventMessage.builder()
                .createFrom(instance)
                .build();
    }

    /**
     * Creates a builder for {@link io.syndesis.common.model.EventMessage EventMessage}.
     * <pre>
     * ImmutableEventMessage.builder()
     *    .event(String) // optional {@link io.syndesis.common.model.EventMessage#getEvent() event}
     *    .data(Object) // optional {@link io.syndesis.common.model.EventMessage#getData() data}
     *    .build();
     * </pre>
     *
     * @return A new EventMessage builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * @return The value of the {@code event} attribute
     */
    @JsonProperty("event")
    @Override
    public Optional<String> getEvent() {
        return Optional.ofNullable(event);
    }

    /**
     * @return The value of the {@code data} attribute
     */
    @JsonProperty("data")
    @Override
    public Optional<Object> getData() {
        return Optional.ofNullable(data);
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.EventMessage#getEvent() event} attribute.
     *
     * @param value The value for event
     * @return A modified copy of {@code this} object
     */
    public final ImmutableEventMessage withEvent(String value) {
        String newValue = Objects.requireNonNull(value, "event");
        if (Objects.equals(this.event, newValue)) {
            return this;
        }
        return validate(new ImmutableEventMessage(this, newValue, this.data));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.EventMessage#getEvent() event} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for event
     * @return A modified copy of {@code this} object
     */
    public final ImmutableEventMessage withEvent(Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.event, value)) {
            return this;
        }
        return validate(new ImmutableEventMessage(this, value, this.data));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.EventMessage#getData() data} attribute.
     *
     * @param value The value for data
     * @return A modified copy of {@code this} object
     */
    public final ImmutableEventMessage withData(Object value) {
        Object newValue = Objects.requireNonNull(value, "data");
        if (this.data == newValue) {
            return this;
        }
        return validate(new ImmutableEventMessage(this, this.event, newValue));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.EventMessage#getData() data} attribute.
     * A shallow reference equality check is used on unboxed optional value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for data
     * @return A modified copy of {@code this} object
     */
    @SuppressWarnings("unchecked") // safe covariant cast
    public final ImmutableEventMessage withData(
            Optional<? extends Object> optional) {
        Object value = optional.orElse(null);
        if (this.data == value) {
            return this;
        }
        return validate(new ImmutableEventMessage(this, this.event, value));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableEventMessage} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableEventMessage
                && equalTo((ImmutableEventMessage) another);
    }

    private boolean equalTo(ImmutableEventMessage another) {
        return Objects.equals(event, another.event)
                && Objects.equals(data, another.data);
    }

    /**
     * Computes a hash code from attributes: {@code event}, {@code data}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(event);
        h += (h << 5) + Objects.hashCode(data);
        return h;
    }

    /**
     * Prints the immutable value {@code EventMessage} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("EventMessage{");
        if (event != null) {
            builder.append("event=").append(event);
        }
        if (data != null) {
            if (builder.length() > 13) {
                builder.append(", ");
            }
            builder.append("data=").append(data);
        }
        return builder.append("}").toString();
    }

    private Object readResolve() throws ObjectStreamException {
        return validate(this);
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.EventMessage EventMessage}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static final class Builder {
        private String event;
        private Object data;

        private Builder() {
        }

        /**
         * Fill a builder with attribute values from the provided {@code EventMessage} instance.
         * Regular attribute values will be replaced with those from the given instance.
         * Absent optional values will not replace present values.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Builder createFrom(EventMessage instance) {
            Objects.requireNonNull(instance, "instance");
            Optional<String> eventOptional = instance.getEvent();
            if (eventOptional.isPresent()) {
                event(eventOptional);
            }
            Optional<Object> dataOptional = instance.getData();
            if (dataOptional.isPresent()) {
                data(dataOptional);
            }
            return this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.EventMessage#getEvent() event} to event.
         *
         * @param event The value for event
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final Builder event(String event) {
            this.event = Objects.requireNonNull(event, "event");
            return this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.EventMessage#getEvent() event} to event.
         *
         * @param event The value for event
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("event")
        public final Builder event(Optional<String> event) {
            this.event = event.orElse(null);
            return this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.EventMessage#getData() data} to data.
         *
         * @param data The value for data
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final Builder data(Object data) {
            this.data = Objects.requireNonNull(data, "data");
            return this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.EventMessage#getData() data} to data.
         *
         * @param data The value for data
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("data")
        public final Builder data(Optional<? extends Object> data) {
            this.data = data.orElse(null);
            return this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.EventMessage EventMessage}.
         *
         * @return An immutable instance of EventMessage
         * @throws IllegalStateException if any required attributes are missing
         */
        public EventMessage build() {
            return ImmutableEventMessage.validate(
                    new ImmutableEventMessage(null, event, data));
        }
    }
}
