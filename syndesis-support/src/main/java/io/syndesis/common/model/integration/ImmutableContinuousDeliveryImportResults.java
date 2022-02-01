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
package io.syndesis.common.model.integration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Var;
import io.syndesis.common.model.WithResourceId;

import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Immutable implementation of {@link io.syndesis.common.model.integration.ContinuousDeliveryImportResults}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new ContinuousDeliveryImportResults.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableContinuousDeliveryImportResults.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableContinuousDeliveryImportResults
        implements ContinuousDeliveryImportResults {
    private final Date lastImportedAt;
    private final List<WithResourceId> results;

    private ImmutableContinuousDeliveryImportResults(Date lastImportedAt,
                                                     Iterable<? extends WithResourceId> results) {
        this.lastImportedAt = lastImportedAt;
        this.results = createUnmodifiableList(false,
                createSafeList(results, true, false));
    }

    private ImmutableContinuousDeliveryImportResults(
            ImmutableContinuousDeliveryImportResults original,
            Date lastImportedAt,
            List<WithResourceId> results) {
        this.lastImportedAt = lastImportedAt;
        this.results = results;
    }

    /**
     * Construct a new immutable {@code ContinuousDeliveryImportResults} instance.
     *
     * @param lastImportedAt The value for the {@code lastImportedAt} attribute
     * @param results        The value for the {@code results} attribute
     * @return An immutable ContinuousDeliveryImportResults instance
     */
    public static ContinuousDeliveryImportResults of(Date lastImportedAt,
                                                     List<WithResourceId> results) {
        return of(lastImportedAt, (Iterable<? extends WithResourceId>) results);
    }

    /**
     * Construct a new immutable {@code ContinuousDeliveryImportResults} instance.
     *
     * @param lastImportedAt The value for the {@code lastImportedAt} attribute
     * @param results        The value for the {@code results} attribute
     * @return An immutable ContinuousDeliveryImportResults instance
     */
    public static ContinuousDeliveryImportResults of(Date lastImportedAt,
                                                     Iterable<? extends WithResourceId> results) {
        return validate(
                new ImmutableContinuousDeliveryImportResults(lastImportedAt,
                        results));
    }

    private static ImmutableContinuousDeliveryImportResults validate(
            ImmutableContinuousDeliveryImportResults instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.integration.ContinuousDeliveryImportResults} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable ContinuousDeliveryImportResults instance
     */
    public static ContinuousDeliveryImportResults copyOf(
            ContinuousDeliveryImportResults instance) {
        if (instance instanceof ImmutableContinuousDeliveryImportResults) {
            return (ImmutableContinuousDeliveryImportResults) instance;
        }
        return new ContinuousDeliveryImportResults.Builder()
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
     * @return The value of the {@code lastImportedAt} attribute
     */
    @JsonProperty("lastImportedAt")
    @Override
    public Date getLastImportedAt() {
        return lastImportedAt;
    }

    /**
     * @return The value of the {@code results} attribute
     */
    @JsonProperty("results")
    @Override
    public List<WithResourceId> getResults() {
        return results;
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.integration.ContinuousDeliveryImportResults#getLastImportedAt() lastImportedAt} attribute.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for lastImportedAt (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableContinuousDeliveryImportResults withLastImportedAt(
            Date value) {
        if (this.lastImportedAt == value) {
            return this;
        }
        return validate(
                new ImmutableContinuousDeliveryImportResults(this, value,
                        this.results));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.integration.ContinuousDeliveryImportResults#getResults() results}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableContinuousDeliveryImportResults withResults(
            WithResourceId... elements) {
        List<WithResourceId> newValue = createUnmodifiableList(false,
                createSafeList(Arrays.asList(elements), true, false));
        return validate(new ImmutableContinuousDeliveryImportResults(this,
                this.lastImportedAt, newValue));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.integration.ContinuousDeliveryImportResults#getResults() results}.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of results elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableContinuousDeliveryImportResults withResults(
            Iterable<? extends WithResourceId> elements) {
        if (this.results == elements) {
            return this;
        }
        List<WithResourceId> newValue = createUnmodifiableList(false,
                createSafeList(elements, true, false));
        return validate(new ImmutableContinuousDeliveryImportResults(this,
                this.lastImportedAt, newValue));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableContinuousDeliveryImportResults} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableContinuousDeliveryImportResults
                && equalTo((ImmutableContinuousDeliveryImportResults) another);
    }

    private boolean equalTo(ImmutableContinuousDeliveryImportResults another) {
        return Objects.equals(lastImportedAt, another.lastImportedAt)
                && results.equals(another.results);
    }

    /**
     * Computes a hash code from attributes: {@code lastImportedAt}, {@code results}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(lastImportedAt);
        h += (h << 5) + results.hashCode();
        return h;
    }

    /**
     * Prints the immutable value {@code ContinuousDeliveryImportResults} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        return "ContinuousDeliveryImportResults{"
                + "lastImportedAt=" + lastImportedAt
                + ", results=" + results
                + "}";
    }

    private Object readResolve() throws ObjectStreamException {
        return validate(this);
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.integration.ContinuousDeliveryImportResults ContinuousDeliveryImportResults}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private Date lastImportedAt;
        private List<WithResourceId> results = new ArrayList<WithResourceId>();

        /**
         * Creates a builder for {@link io.syndesis.common.model.integration.ContinuousDeliveryImportResults ContinuousDeliveryImportResults} instances.
         * <pre>
         * new ContinuousDeliveryImportResults.Builder()
         *    .lastImportedAt(Date | null) // nullable {@link io.syndesis.common.model.integration.ContinuousDeliveryImportResults#getLastImportedAt() lastImportedAt}
         *    .addResult|addAllResults(io.syndesis.common.io.syndesis.common.model.WithResourceId) // {@link io.syndesis.common.model.integration.ContinuousDeliveryImportResults#getResults() results} elements
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof ContinuousDeliveryImportResults.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new ContinuousDeliveryImportResults.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code ContinuousDeliveryImportResults} instance.
         * Regular attribute values will be replaced with those from the given instance.
         * Absent optional values will not replace present values.
         * Collection elements and entries will be added, not replaced.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ContinuousDeliveryImportResults.Builder createFrom(
                ContinuousDeliveryImportResults instance) {
            Objects.requireNonNull(instance, "instance");
            Date lastImportedAtValue = instance.getLastImportedAt();
            if (lastImportedAtValue != null) {
                lastImportedAt(lastImportedAtValue);
            }
            addAllResults(instance.getResults());
            return (ContinuousDeliveryImportResults.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.integration.ContinuousDeliveryImportResults#getLastImportedAt() lastImportedAt} attribute.
         *
         * @param lastImportedAt The value for lastImportedAt (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("lastImportedAt")
        public final ContinuousDeliveryImportResults.Builder lastImportedAt(
                Date lastImportedAt) {
            this.lastImportedAt = lastImportedAt;
            return (ContinuousDeliveryImportResults.Builder) this;
        }

        /**
         * Adds one element to {@link io.syndesis.common.model.integration.ContinuousDeliveryImportResults#getResults() results} list.
         *
         * @param element A results element
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ContinuousDeliveryImportResults.Builder addResult(
                WithResourceId element) {
            this.results.add(
                    Objects.requireNonNull(element, "results element"));
            return (ContinuousDeliveryImportResults.Builder) this;
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.integration.ContinuousDeliveryImportResults#getResults() results} list.
         *
         * @param elements An array of results elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ContinuousDeliveryImportResults.Builder addResults(
                WithResourceId... elements) {
            for (WithResourceId element : elements) {
                this.results.add(
                        Objects.requireNonNull(element, "results element"));
            }
            return (ContinuousDeliveryImportResults.Builder) this;
        }


        /**
         * Sets or replaces all elements for {@link io.syndesis.common.model.integration.ContinuousDeliveryImportResults#getResults() results} list.
         *
         * @param elements An iterable of results elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("results")
        public final ContinuousDeliveryImportResults.Builder results(
                Iterable<? extends WithResourceId> elements) {
            this.results.clear();
            return addAllResults(elements);
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.integration.ContinuousDeliveryImportResults#getResults() results} list.
         *
         * @param elements An iterable of results elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ContinuousDeliveryImportResults.Builder addAllResults(
                Iterable<? extends WithResourceId> elements) {
            for (WithResourceId element : elements) {
                this.results.add(
                        Objects.requireNonNull(element, "results element"));
            }
            return (ContinuousDeliveryImportResults.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.integration.ContinuousDeliveryImportResults ContinuousDeliveryImportResults}.
         *
         * @return An immutable instance of ContinuousDeliveryImportResults
         * @throws IllegalStateException if any required attributes are missing
         */
        public ContinuousDeliveryImportResults build() {
            return ImmutableContinuousDeliveryImportResults.validate(
                    new ImmutableContinuousDeliveryImportResults(null,
                            lastImportedAt,
                            createUnmodifiableList(true, results)));
        }
    }
}
