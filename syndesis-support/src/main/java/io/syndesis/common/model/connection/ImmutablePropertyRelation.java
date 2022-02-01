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
package io.syndesis.common.model.connection;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Var;

import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Immutable implementation of {@link io.syndesis.common.model.connection.PropertyRelation}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new PropertyRelation.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutablePropertyRelation.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutablePropertyRelation implements PropertyRelation {
    private final String action;
    private final List<When> when;

    private ImmutablePropertyRelation(
            String action,
            Iterable<? extends When> when) {
        this.action = action;
        this.when = createUnmodifiableList(false,
                createSafeList(when, true, false));
    }

    private ImmutablePropertyRelation(
            ImmutablePropertyRelation original,
            String action,
            List<When> when) {
        this.action = action;
        this.when = when;
    }

    /**
     * Construct a new immutable {@code PropertyRelation} instance.
     *
     * @param action The value for the {@code action} attribute
     * @param when   The value for the {@code when} attribute
     * @return An immutable PropertyRelation instance
     */
    public static PropertyRelation of(String action, List<When> when) {
        return of(action, (Iterable<? extends When>) when);
    }

    /**
     * Construct a new immutable {@code PropertyRelation} instance.
     *
     * @param action The value for the {@code action} attribute
     * @param when   The value for the {@code when} attribute
     * @return An immutable PropertyRelation instance
     */
    public static PropertyRelation of(String action,
                                      Iterable<? extends When> when) {
        return validate(new ImmutablePropertyRelation(action, when));
    }

    private static ImmutablePropertyRelation validate(
            ImmutablePropertyRelation instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.connection.PropertyRelation} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable PropertyRelation instance
     */
    public static PropertyRelation copyOf(PropertyRelation instance) {
        if (instance instanceof ImmutablePropertyRelation) {
            return (ImmutablePropertyRelation) instance;
        }
        return new PropertyRelation.Builder()
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
     * @return The value of the {@code action} attribute
     */
    @JsonProperty("action")
    @Override
    public String getAction() {
        return action;
    }

    /**
     * @return The value of the {@code when} attribute
     */
    @JsonProperty("when")
    @Override
    public List<When> getWhen() {
        return when;
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.PropertyRelation#getAction() action} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for action (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutablePropertyRelation withAction(String value) {
        if (Objects.equals(this.action, value)) {
            return this;
        }
        return validate(new ImmutablePropertyRelation(this, value, this.when));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.connection.PropertyRelation#getWhen() when}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutablePropertyRelation withWhen(When... elements) {
        List<When> newValue = createUnmodifiableList(false,
                createSafeList(Arrays.asList(elements), true, false));
        return validate(
                new ImmutablePropertyRelation(this, this.action, newValue));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.connection.PropertyRelation#getWhen() when}.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of when elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutablePropertyRelation withWhen(
            Iterable<? extends When> elements) {
        if (this.when == elements) {
            return this;
        }
        List<When> newValue = createUnmodifiableList(false,
                createSafeList(elements, true, false));
        return validate(
                new ImmutablePropertyRelation(this, this.action, newValue));
    }

    /**
     * This instance is equal to all instances of {@code ImmutablePropertyRelation} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutablePropertyRelation
                && equalTo((ImmutablePropertyRelation) another);
    }

    private boolean equalTo(ImmutablePropertyRelation another) {
        return Objects.equals(action, another.action)
                && when.equals(another.when);
    }

    /**
     * Computes a hash code from attributes: {@code action}, {@code when}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(action);
        h += (h << 5) + when.hashCode();
        return h;
    }

    /**
     * Prints the immutable value {@code PropertyRelation} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        return "PropertyRelation{"
                + "action=" + action
                + ", when=" + when
                + "}";
    }

    private Object readResolve() throws ObjectStreamException {
        return validate(this);
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.connection.PropertyRelation PropertyRelation}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private String action;
        private List<When> when = new ArrayList<When>();

        /**
         * Creates a builder for {@link io.syndesis.common.model.connection.PropertyRelation PropertyRelation} instances.
         * <pre>
         * new PropertyRelation.Builder()
         *    .action(String | null) // nullable {@link io.syndesis.common.model.connection.PropertyRelation#getAction() action}
         *    .addWhen|addAllWhen(io.syndesis.common.io.syndesis.common.model.connection.PropertyRelation.When) // {@link io.syndesis.common.model.connection.PropertyRelation#getWhen() when} elements
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof PropertyRelation.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new PropertyRelation.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code PropertyRelation} instance.
         * Regular attribute values will be replaced with those from the given instance.
         * Absent optional values will not replace present values.
         * Collection elements and entries will be added, not replaced.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final PropertyRelation.Builder createFrom(
                PropertyRelation instance) {
            Objects.requireNonNull(instance, "instance");
            String actionValue = instance.getAction();
            if (actionValue != null) {
                action(actionValue);
            }
            addAllWhen(instance.getWhen());
            return (PropertyRelation.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.PropertyRelation#getAction() action} attribute.
         *
         * @param action The value for action (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("action")
        public final PropertyRelation.Builder action(String action) {
            this.action = action;
            return (PropertyRelation.Builder) this;
        }

        /**
         * Adds one element to {@link io.syndesis.common.model.connection.PropertyRelation#getWhen() when} list.
         *
         * @param element A when element
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final PropertyRelation.Builder addWhen(When element) {
            this.when.add(Objects.requireNonNull(element, "when element"));
            return (PropertyRelation.Builder) this;
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.connection.PropertyRelation#getWhen() when} list.
         *
         * @param elements An array of when elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final PropertyRelation.Builder addWhen(When... elements) {
            for (When element : elements) {
                this.when.add(Objects.requireNonNull(element, "when element"));
            }
            return (PropertyRelation.Builder) this;
        }


        /**
         * Sets or replaces all elements for {@link io.syndesis.common.model.connection.PropertyRelation#getWhen() when} list.
         *
         * @param elements An iterable of when elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("when")
        public final PropertyRelation.Builder when(
                Iterable<? extends When> elements) {
            this.when.clear();
            return addAllWhen(elements);
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.connection.PropertyRelation#getWhen() when} list.
         *
         * @param elements An iterable of when elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final PropertyRelation.Builder addAllWhen(
                Iterable<? extends When> elements) {
            for (When element : elements) {
                this.when.add(Objects.requireNonNull(element, "when element"));
            }
            return (PropertyRelation.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.connection.PropertyRelation PropertyRelation}.
         *
         * @return An immutable instance of PropertyRelation
         * @throws IllegalStateException if any required attributes are missing
         */
        public PropertyRelation build() {
            return ImmutablePropertyRelation.validate(
                    new ImmutablePropertyRelation(null, action,
                            createUnmodifiableList(true, when)));
        }
    }
}
