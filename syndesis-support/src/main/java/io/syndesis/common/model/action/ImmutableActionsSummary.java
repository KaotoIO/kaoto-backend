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
package io.syndesis.common.model.action;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Var;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Immutable implementation of {@link io.syndesis.common.model.action.ActionsSummary}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new ActionsSummary.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableActionsSummary.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableActionsSummary implements ActionsSummary {
    private final Map<String, Integer> actionCountByTags;
    private final int totalActions;

    private ImmutableActionsSummary(
            Map<String, ? extends Integer> actionCountByTags,
            int totalActions) {
        this.actionCountByTags =
                createUnmodifiableMap(true, false, actionCountByTags);
        this.totalActions = totalActions;
    }

    private ImmutableActionsSummary(
            ImmutableActionsSummary original,
            Map<String, Integer> actionCountByTags,
            int totalActions) {
        this.actionCountByTags = actionCountByTags;
        this.totalActions = totalActions;
    }

    /**
     * Construct a new immutable {@code ActionsSummary} instance.
     *
     * @param actionCountByTags The value for the {@code actionCountByTags} attribute
     * @param totalActions      The value for the {@code totalActions} attribute
     * @return An immutable ActionsSummary instance
     */
    public static ActionsSummary of(
            Map<String, ? extends Integer> actionCountByTags,
            int totalActions) {
        return validate(
                new ImmutableActionsSummary(actionCountByTags, totalActions));
    }

    private static ImmutableActionsSummary validate(
            ImmutableActionsSummary instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.action.ActionsSummary} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable ActionsSummary instance
     */
    public static ActionsSummary copyOf(ActionsSummary instance) {
        if (instance instanceof ImmutableActionsSummary) {
            return (ImmutableActionsSummary) instance;
        }
        return new ActionsSummary.Builder()
                .createFrom(instance)
                .build();
    }

    private static <K, V> Map<K, V> createUnmodifiableMap(boolean checkNulls,
                                                          boolean skipNulls,
                                                          Map<? extends K, ? extends V> map) {
        switch (map.size()) {
            case 0:
                return Collections.emptyMap();
            case 1: {
                Map.Entry<? extends K, ? extends V> e =
                        map.entrySet().iterator().next();
                K k = e.getKey();
                V v = e.getValue();
                if (checkNulls) {
                    Objects.requireNonNull(k, "key");
                    Objects.requireNonNull(v, "value");
                }
                if (skipNulls && (k == null || v == null)) {
                    return Collections.emptyMap();
                }
                return Collections.singletonMap(k, v);
            }
            default: {
                Map<K, V> linkedMap = new LinkedHashMap<>(map.size());
                if (skipNulls || checkNulls) {
                    for (Map.Entry<? extends K, ? extends V> e : map.entrySet()) {
                        K k = e.getKey();
                        V v = e.getValue();
                        if (skipNulls) {
                            if (k == null || v == null) {
                                continue;
                            }
                        } else if (checkNulls) {
                            Objects.requireNonNull(k, "key");
                            Objects.requireNonNull(v, "value");
                        }
                        linkedMap.put(k, v);
                    }
                } else {
                    linkedMap.putAll(map);
                }
                return Collections.unmodifiableMap(linkedMap);
            }
        }
    }

    /**
     * @return The value of the {@code actionCountByTags} attribute
     */
    @JsonProperty("actionCountByTags")
    @Override
    public Map<String, Integer> getActionCountByTags() {
        return actionCountByTags;
    }

    /**
     * @return The value of the {@code totalActions} attribute
     */
    @JsonProperty("totalActions")
    @Override
    public int getTotalActions() {
        return totalActions;
    }

    /**
     * Copy the current immutable object by replacing the {@link io.syndesis.common.model.action.ActionsSummary#getActionCountByTags() actionCountByTags} map with the specified map.
     * Nulls are not permitted as keys or values.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param entries The entries to be added to the actionCountByTags map
     * @return A modified copy of {@code this} object
     */
    public final ImmutableActionsSummary withActionCountByTags(
            Map<String, ? extends Integer> entries) {
        if (this.actionCountByTags == entries) {
            return this;
        }
        Map<String, Integer> newValue =
                createUnmodifiableMap(true, false, entries);
        return validate(
                new ImmutableActionsSummary(this, newValue, this.totalActions));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.action.ActionsSummary#getTotalActions() totalActions} attribute.
     * A value equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for totalActions
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableActionsSummary withTotalActions(int value) {
        if (this.totalActions == value) {
            return this;
        }
        return validate(
                new ImmutableActionsSummary(this, this.actionCountByTags,
                        value));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableActionsSummary} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableActionsSummary
                && equalTo((ImmutableActionsSummary) another);
    }

    private boolean equalTo(ImmutableActionsSummary another) {
        return actionCountByTags.equals(another.actionCountByTags)
                && totalActions == another.totalActions;
    }

    /**
     * Computes a hash code from attributes: {@code actionCountByTags}, {@code totalActions}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + actionCountByTags.hashCode();
        h += (h << 5) + totalActions;
        return h;
    }

    /**
     * Prints the immutable value {@code ActionsSummary} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        return "ActionsSummary{"
                + "actionCountByTags=" + actionCountByTags
                + ", totalActions=" + totalActions
                + "}";
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.action.ActionsSummary ActionsSummary}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private Map<String, Integer> actionCountByTags =
                new LinkedHashMap<String, Integer>();
        private int totalActions;

        /**
         * Creates a builder for {@link io.syndesis.common.model.action.ActionsSummary ActionsSummary} instances.
         * <pre>
         * new ActionsSummary.Builder()
         *    .putActionCountByTag|putAllActionCountByTags(String =&gt; int) // {@link io.syndesis.common.model.action.ActionsSummary#getActionCountByTags() actionCountByTags} mappings
         *    .totalActions(int) // optional {@link io.syndesis.common.model.action.ActionsSummary#getTotalActions() totalActions}
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof ActionsSummary.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new ActionsSummary.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code ActionsSummary} instance.
         * Regular attribute values will be replaced with those from the given instance.
         * Absent optional values will not replace present values.
         * Collection elements and entries will be added, not replaced.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ActionsSummary.Builder createFrom(
                ActionsSummary instance) {
            Objects.requireNonNull(instance, "instance");
            putAllActionCountByTags(instance.getActionCountByTags());
            totalActions(instance.getTotalActions());
            return (ActionsSummary.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.action.ActionsSummary#getActionCountByTags() actionCountByTags} map.
         *
         * @param key   The key in the actionCountByTags map
         * @param value The associated value in the actionCountByTags map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ActionsSummary.Builder putActionCountByTag(String key,
                                                                int value) {
            this.actionCountByTags.put(
                    Objects.requireNonNull(key, "actionCountByTags key"),
                    Objects.requireNonNull(value, "actionCountByTags value"));
            return (ActionsSummary.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.action.ActionsSummary#getActionCountByTags() actionCountByTags} map. Nulls are not permitted
         *
         * @param entry The key and value entry
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ActionsSummary.Builder putActionCountByTag(
                Map.Entry<String, ? extends Integer> entry) {
            String k = entry.getKey();
            Integer v = entry.getValue();
            this.actionCountByTags.put(
                    Objects.requireNonNull(k, "actionCountByTags key"),
                    Objects.requireNonNull(v, "actionCountByTags value"));
            return (ActionsSummary.Builder) this;
        }

        /**
         * Sets or replaces all mappings from the specified map as entries for the {@link io.syndesis.common.model.action.ActionsSummary#getActionCountByTags() actionCountByTags} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the actionCountByTags map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("actionCountByTags")
        public final ActionsSummary.Builder actionCountByTags(
                Map<String, ? extends Integer> entries) {
            this.actionCountByTags.clear();
            return putAllActionCountByTags(entries);
        }

        /**
         * Put all mappings from the specified map as entries to {@link io.syndesis.common.model.action.ActionsSummary#getActionCountByTags() actionCountByTags} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the actionCountByTags map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ActionsSummary.Builder putAllActionCountByTags(
                Map<String, ? extends Integer> entries) {
            for (Map.Entry<String, ? extends Integer> e : entries.entrySet()) {
                String k = e.getKey();
                Integer v = e.getValue();
                this.actionCountByTags.put(
                        Objects.requireNonNull(k, "actionCountByTags key"),
                        Objects.requireNonNull(v, "actionCountByTags value"));
            }
            return (ActionsSummary.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.action.ActionsSummary#getTotalActions() totalActions} attribute.
         *
         * @param totalActions The value for totalActions
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("totalActions")
        public final ActionsSummary.Builder totalActions(int totalActions) {
            this.totalActions = totalActions;
            return (ActionsSummary.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.action.ActionsSummary ActionsSummary}.
         *
         * @return An immutable instance of ActionsSummary
         * @throws IllegalStateException if any required attributes are missing
         */
        public ActionsSummary build() {
            return ImmutableActionsSummary.validate(
                    new ImmutableActionsSummary(null,
                            createUnmodifiableMap(false, false,
                                    actionCountByTags), totalActions));
        }
    }
}
