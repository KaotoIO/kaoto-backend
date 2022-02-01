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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Immutable implementation of {@link io.syndesis.common.model.ListResult}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new ListResult.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableListResult.of()}.
 */
@SuppressWarnings({"rawtypes", "varargs", "all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableListResult<T> implements ListResult<T> {
    private final int totalCount;
    private final List<T> items;

    private ImmutableListResult(int totalCount, Iterable<? extends T> items) {
        this.totalCount = totalCount;
        this.items = createUnmodifiableList(false,
                createSafeList(items, true, false));
    }

    private ImmutableListResult(Builder<T> builder) {
        this.totalCount = builder.totalCount;
        this.items = builder.itemsIsSet()
                ? createUnmodifiableList(true, builder.items)
                : createUnmodifiableList(false,
                createSafeList(ListResult.super.getItems(), true, false));
    }

    private ImmutableListResult(ImmutableListResult<T> original, int totalCount,
                                List<T> items) {
        this.totalCount = totalCount;
        this.items = items;
    }

    /**
     * Construct a new immutable {@code ListResult} instance.
     *
     * @param <T>        generic parameter T
     * @param totalCount The value for the {@code totalCount} attribute
     * @param items      The value for the {@code items} attribute
     * @return An immutable ListResult instance
     */
    public static <T> ListResult<T> of(int totalCount, List<T> items) {
        return of(totalCount, (Iterable<? extends T>) items);
    }

    /**
     * Construct a new immutable {@code ListResult} instance.
     *
     * @param <T>        generic parameter T
     * @param totalCount The value for the {@code totalCount} attribute
     * @param items      The value for the {@code items} attribute
     * @return An immutable ListResult instance
     */
    public static <T> ListResult<T> of(int totalCount,
                                       Iterable<? extends T> items) {
        return validate(new ImmutableListResult<>(totalCount, items));
    }

    private static <T> ImmutableListResult<T> validate(
            ImmutableListResult<T> instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.ListResult} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param <T>      generic parameter T
     * @param instance The instance to copy
     * @return A copied immutable ListResult instance
     */
    public static <T> ListResult<T> copyOf(ListResult<T> instance) {
        if (instance instanceof ImmutableListResult<?>) {
            return (ImmutableListResult<T>) instance;
        }
        return new ListResult.Builder<T>()
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
     * @return The value of the {@code totalCount} attribute
     */
    @JsonProperty("totalCount")
    @Override
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * @return The value of the {@code items} attribute
     */
    @JsonProperty("items")
    @Override
    public List<T> getItems() {
        return items;
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.ListResult#getTotalCount() totalCount} attribute.
     * A value equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for totalCount
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableListResult<T> withTotalCount(int value) {
        if (this.totalCount == value) {
            return this;
        }
        return validate(new ImmutableListResult<>(this, value, this.items));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.ListResult#getItems() items}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    @SafeVarargs
    @SuppressWarnings("varargs")
    public final ImmutableListResult<T> withItems(T... elements) {
        List<T> newValue = createUnmodifiableList(false,
                createSafeList(Arrays.asList(elements), true, false));
        return validate(
                new ImmutableListResult<>(this, this.totalCount, newValue));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.ListResult#getItems() items}.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of items elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableListResult<T> withItems(
            Iterable<? extends T> elements) {
        if (this.items == elements) {
            return this;
        }
        List<T> newValue = createUnmodifiableList(false,
                createSafeList(elements, true, false));
        return validate(
                new ImmutableListResult<>(this, this.totalCount, newValue));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableListResult} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableListResult<?>
                && equalTo((ImmutableListResult<?>) another);
    }

    private boolean equalTo(ImmutableListResult<?> another) {
        return totalCount == another.totalCount
                && items.equals(another.items);
    }

    /**
     * Computes a hash code from attributes: {@code totalCount}, {@code items}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + totalCount;
        h += (h << 5) + items.hashCode();
        return h;
    }

    /**
     * Prints the immutable value {@code ListResult} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        return "ListResult{"
                + "totalCount=" + totalCount
                + ", items=" + items
                + "}";
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.ListResult ListResult}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder<T> {
        private static final long OPT_BIT_ITEMS = 0x1L;
        private long optBits;

        private int totalCount;
        private List<T> items = new ArrayList<T>();

        /**
         * Creates a builder for {@link io.syndesis.common.model.ListResult ListResult} instances.
         * <pre>
         * new ListResult.Builder&amp;lt;T&amp;gt;()
         *    .totalCount(int) // optional {@link io.syndesis.common.model.ListResult#getTotalCount() totalCount}
         *    .addItem|addAllItems(T) // {@link io.syndesis.common.model.ListResult#getItems() items} elements
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof ListResult.Builder<?>)) {
                throw new UnsupportedOperationException(
                        "Use: new ListResult.Builder<T>()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code ListResult} instance.
         * Regular attribute values will be replaced with those from the given instance.
         * Absent optional values will not replace present values.
         * Collection elements and entries will be added, not replaced.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ListResult.Builder<T> createFrom(ListResult<T> instance) {
            Objects.requireNonNull(instance, "instance");
            totalCount(instance.getTotalCount());
            addAllItems(instance.getItems());
            return (ListResult.Builder<T>) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.ListResult#getTotalCount() totalCount} attribute.
         *
         * @param totalCount The value for totalCount
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("totalCount")
        public final ListResult.Builder<T> totalCount(int totalCount) {
            this.totalCount = totalCount;
            return (ListResult.Builder<T>) this;
        }

        /**
         * Adds one element to {@link io.syndesis.common.model.ListResult#getItems() items} list.
         *
         * @param element A items element
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ListResult.Builder<T> addItem(T element) {
            this.items.add(Objects.requireNonNull(element, "items element"));
            optBits |= OPT_BIT_ITEMS;
            return (ListResult.Builder<T>) this;
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.ListResult#getItems() items} list.
         *
         * @param elements An array of items elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @SafeVarargs
        @SuppressWarnings("varargs")
        public final ListResult.Builder<T> addItems(T... elements) {
            for (T element : elements) {
                this.items.add(
                        Objects.requireNonNull(element, "items element"));
            }
            optBits |= OPT_BIT_ITEMS;
            return (ListResult.Builder<T>) this;
        }


        /**
         * Sets or replaces all elements for {@link io.syndesis.common.model.ListResult#getItems() items} list.
         *
         * @param elements An iterable of items elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("items")
        public final ListResult.Builder<T> items(
                Iterable<? extends T> elements) {
            this.items.clear();
            return addAllItems(elements);
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.ListResult#getItems() items} list.
         *
         * @param elements An iterable of items elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ListResult.Builder<T> addAllItems(
                Iterable<? extends T> elements) {
            for (T element : elements) {
                this.items.add(
                        Objects.requireNonNull(element, "items element"));
            }
            optBits |= OPT_BIT_ITEMS;
            return (ListResult.Builder<T>) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.ListResult ListResult}.
         *
         * @return An immutable instance of ListResult
         * @throws IllegalStateException if any required attributes are missing
         */
        public ListResult<T> build() {
            return ImmutableListResult.validate(
                    new ImmutableListResult<T>(this));
        }

        private boolean itemsIsSet() {
            return (optBits & OPT_BIT_ITEMS) != 0;
        }
    }
}
