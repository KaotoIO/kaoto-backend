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
package io.syndesis.common.model.filter;

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
 * Immutable implementation of {@link io.syndesis.common.model.filter.FilterOptions}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new FilterOptions.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableFilterOptions.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableFilterOptions implements FilterOptions {
    private final List<String> paths;
    private final List<Op> ops;

    private ImmutableFilterOptions(Iterable<String> paths,
                                   Iterable<? extends Op> ops) {
        this.paths = createUnmodifiableList(false,
                createSafeList(paths, true, false));
        this.ops =
                createUnmodifiableList(false, createSafeList(ops, true, false));
    }

    private ImmutableFilterOptions(
            ImmutableFilterOptions original,
            List<String> paths,
            List<Op> ops) {
        this.paths = paths;
        this.ops = ops;
    }

    /**
     * Construct a new immutable {@code FilterOptions} instance.
     *
     * @param paths The value for the {@code paths} attribute
     * @param ops   The value for the {@code ops} attribute
     * @return An immutable FilterOptions instance
     */
    public static FilterOptions of(List<String> paths, List<Op> ops) {
        return of((Iterable<String>) paths, (Iterable<? extends Op>) ops);
    }

    /**
     * Construct a new immutable {@code FilterOptions} instance.
     *
     * @param paths The value for the {@code paths} attribute
     * @param ops   The value for the {@code ops} attribute
     * @return An immutable FilterOptions instance
     */
    public static FilterOptions of(Iterable<String> paths,
                                   Iterable<? extends Op> ops) {
        return validate(new ImmutableFilterOptions(paths, ops));
    }

    private static ImmutableFilterOptions validate(
            ImmutableFilterOptions instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.filter.FilterOptions} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable FilterOptions instance
     */
    public static FilterOptions copyOf(FilterOptions instance) {
        if (instance instanceof ImmutableFilterOptions) {
            return (ImmutableFilterOptions) instance;
        }
        return new FilterOptions.Builder()
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
     * @return The value of the {@code paths} attribute
     */
    @JsonProperty("paths")
    @Override
    public List<String> getPaths() {
        return paths;
    }

    /**
     * @return The value of the {@code ops} attribute
     */
    @JsonProperty("ops")
    @Override
    public List<Op> getOps() {
        return ops;
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.filter.FilterOptions#getPaths() paths}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableFilterOptions withPaths(String... elements) {
        List<String> newValue = createUnmodifiableList(false,
                createSafeList(Arrays.asList(elements), true, false));
        return validate(new ImmutableFilterOptions(this, newValue, this.ops));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.filter.FilterOptions#getPaths() paths}.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of paths elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableFilterOptions withPaths(Iterable<String> elements) {
        if (this.paths == elements) {
            return this;
        }
        List<String> newValue = createUnmodifiableList(false,
                createSafeList(elements, true, false));
        return validate(new ImmutableFilterOptions(this, newValue, this.ops));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.filter.FilterOptions#getOps() ops}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableFilterOptions withOps(Op... elements) {
        List<Op> newValue = createUnmodifiableList(false,
                createSafeList(Arrays.asList(elements), true, false));
        return validate(new ImmutableFilterOptions(this, this.paths, newValue));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.filter.FilterOptions#getOps() ops}.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of ops elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableFilterOptions withOps(
            Iterable<? extends Op> elements) {
        if (this.ops == elements) {
            return this;
        }
        List<Op> newValue = createUnmodifiableList(false,
                createSafeList(elements, true, false));
        return validate(new ImmutableFilterOptions(this, this.paths, newValue));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableFilterOptions} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableFilterOptions
                && equalTo((ImmutableFilterOptions) another);
    }

    private boolean equalTo(ImmutableFilterOptions another) {
        return paths.equals(another.paths)
                && ops.equals(another.ops);
    }

    /**
     * Computes a hash code from attributes: {@code paths}, {@code ops}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + paths.hashCode();
        h += (h << 5) + ops.hashCode();
        return h;
    }

    /**
     * Prints the immutable value {@code FilterOptions} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        return "FilterOptions{"
                + "paths=" + paths
                + ", ops=" + ops
                + "}";
    }

    private Object readResolve() throws ObjectStreamException {
        return validate(this);
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.filter.FilterOptions FilterOptions}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private List<String> paths = new ArrayList<String>();
        private List<Op> ops = new ArrayList<Op>();

        /**
         * Creates a builder for {@link io.syndesis.common.model.filter.FilterOptions FilterOptions} instances.
         * <pre>
         * new FilterOptions.Builder()
         *    .addPath|addAllPaths(String) // {@link io.syndesis.common.model.filter.FilterOptions#getPaths() paths} elements
         *    .addOp|addAllOps(io.syndesis.common.io.syndesis.common.model.filter.Op) // {@link io.syndesis.common.model.filter.FilterOptions#getOps() ops} elements
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof FilterOptions.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new FilterOptions.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code FilterOptions} instance.
         * Regular attribute values will be replaced with those from the given instance.
         * Absent optional values will not replace present values.
         * Collection elements and entries will be added, not replaced.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final FilterOptions.Builder createFrom(FilterOptions instance) {
            Objects.requireNonNull(instance, "instance");
            addAllPaths(instance.getPaths());
            addAllOps(instance.getOps());
            return (FilterOptions.Builder) this;
        }

        /**
         * Adds one element to {@link io.syndesis.common.model.filter.FilterOptions#getPaths() paths} list.
         *
         * @param element A paths element
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final FilterOptions.Builder addPath(String element) {
            this.paths.add(Objects.requireNonNull(element, "paths element"));
            return (FilterOptions.Builder) this;
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.filter.FilterOptions#getPaths() paths} list.
         *
         * @param elements An array of paths elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final FilterOptions.Builder addPaths(String... elements) {
            for (String element : elements) {
                this.paths.add(
                        Objects.requireNonNull(element, "paths element"));
            }
            return (FilterOptions.Builder) this;
        }


        /**
         * Sets or replaces all elements for {@link io.syndesis.common.model.filter.FilterOptions#getPaths() paths} list.
         *
         * @param elements An iterable of paths elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("paths")
        public final FilterOptions.Builder paths(Iterable<String> elements) {
            this.paths.clear();
            return addAllPaths(elements);
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.filter.FilterOptions#getPaths() paths} list.
         *
         * @param elements An iterable of paths elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final FilterOptions.Builder addAllPaths(
                Iterable<String> elements) {
            for (String element : elements) {
                this.paths.add(
                        Objects.requireNonNull(element, "paths element"));
            }
            return (FilterOptions.Builder) this;
        }

        /**
         * Adds one element to {@link io.syndesis.common.model.filter.FilterOptions#getOps() ops} list.
         *
         * @param element A ops element
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final FilterOptions.Builder addOp(Op element) {
            this.ops.add(Objects.requireNonNull(element, "ops element"));
            return (FilterOptions.Builder) this;
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.filter.FilterOptions#getOps() ops} list.
         *
         * @param elements An array of ops elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final FilterOptions.Builder addOps(Op... elements) {
            for (Op element : elements) {
                this.ops.add(Objects.requireNonNull(element, "ops element"));
            }
            return (FilterOptions.Builder) this;
        }


        /**
         * Sets or replaces all elements for {@link io.syndesis.common.model.filter.FilterOptions#getOps() ops} list.
         *
         * @param elements An iterable of ops elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("ops")
        public final FilterOptions.Builder ops(
                Iterable<? extends Op> elements) {
            this.ops.clear();
            return addAllOps(elements);
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.filter.FilterOptions#getOps() ops} list.
         *
         * @param elements An iterable of ops elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final FilterOptions.Builder addAllOps(
                Iterable<? extends Op> elements) {
            for (Op element : elements) {
                this.ops.add(Objects.requireNonNull(element, "ops element"));
            }
            return (FilterOptions.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.filter.FilterOptions FilterOptions}.
         *
         * @return An immutable instance of FilterOptions
         * @throws IllegalStateException if any required attributes are missing
         */
        public FilterOptions build() {
            return ImmutableFilterOptions.validate(
                    new ImmutableFilterOptions(null,
                            createUnmodifiableList(true, paths),
                            createUnmodifiableList(true, ops)));
        }
    }
}
