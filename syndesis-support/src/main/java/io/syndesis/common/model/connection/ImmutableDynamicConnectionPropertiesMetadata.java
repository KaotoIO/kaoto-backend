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

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Immutable implementation of {@link io.syndesis.common.model.connection.DynamicConnectionPropertiesMetadata}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new DynamicConnectionPropertiesMetadata.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableDynamicConnectionPropertiesMetadata.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableDynamicConnectionPropertiesMetadata
        implements DynamicConnectionPropertiesMetadata {
    private final Map<String, List<ActionPropertySuggestion>> properties;

    private ImmutableDynamicConnectionPropertiesMetadata(
            Map<String, ? extends List<ActionPropertySuggestion>> properties) {
        this.properties = createUnmodifiableMap(true, false, properties);
    }

    private ImmutableDynamicConnectionPropertiesMetadata(
            ImmutableDynamicConnectionPropertiesMetadata original,
            Map<String, List<ActionPropertySuggestion>> properties) {
        this.properties = properties;
    }

    /**
     * Construct a new immutable {@code DynamicConnectionPropertiesMetadata} instance.
     *
     * @param properties The value for the {@code properties} attribute
     * @return An immutable DynamicConnectionPropertiesMetadata instance
     */
    public static DynamicConnectionPropertiesMetadata of(
            Map<String, ? extends List<ActionPropertySuggestion>> properties) {
        return validate(
                new ImmutableDynamicConnectionPropertiesMetadata(properties));
    }

    private static ImmutableDynamicConnectionPropertiesMetadata validate(
            ImmutableDynamicConnectionPropertiesMetadata instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.connection.DynamicConnectionPropertiesMetadata} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable DynamicConnectionPropertiesMetadata instance
     */
    public static DynamicConnectionPropertiesMetadata copyOf(
            DynamicConnectionPropertiesMetadata instance) {
        if (instance instanceof ImmutableDynamicConnectionPropertiesMetadata) {
            return (ImmutableDynamicConnectionPropertiesMetadata) instance;
        }
        return new DynamicConnectionPropertiesMetadata.Builder()
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
     * @return The value of the {@code properties} attribute
     */
    @JsonProperty("properties")
    @Override
    public Map<String, List<ActionPropertySuggestion>> properties() {
        return properties;
    }

    /**
     * Copy the current immutable object by replacing the {@link io.syndesis.common.model.connection.DynamicConnectionPropertiesMetadata#properties() properties} map with the specified map.
     * Nulls are not permitted as keys or values.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param entries The entries to be added to the properties map
     * @return A modified copy of {@code this} object
     */
    public final ImmutableDynamicConnectionPropertiesMetadata withProperties(
            Map<String, ? extends List<ActionPropertySuggestion>> entries) {
        if (this.properties == entries) {
            return this;
        }
        Map<String, List<ActionPropertySuggestion>> newValue =
                createUnmodifiableMap(true, false, entries);
        return validate(new ImmutableDynamicConnectionPropertiesMetadata(this,
                newValue));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableDynamicConnectionPropertiesMetadata} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableDynamicConnectionPropertiesMetadata
                && equalTo(
                (ImmutableDynamicConnectionPropertiesMetadata) another);
    }

    private boolean equalTo(
            ImmutableDynamicConnectionPropertiesMetadata another) {
        return properties.equals(another.properties);
    }

    /**
     * Computes a hash code from attributes: {@code properties}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + properties.hashCode();
        return h;
    }

    /**
     * Prints the immutable value {@code DynamicConnectionPropertiesMetadata} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        return "DynamicConnectionPropertiesMetadata{"
                + "properties=" + properties
                + "}";
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.connection.DynamicConnectionPropertiesMetadata DynamicConnectionPropertiesMetadata}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private Map<String, List<ActionPropertySuggestion>> properties =
                new LinkedHashMap<String, List<ActionPropertySuggestion>>();

        /**
         * Creates a builder for {@link io.syndesis.common.model.connection.DynamicConnectionPropertiesMetadata DynamicConnectionPropertiesMetadata} instances.
         * <pre>
         * new DynamicConnectionPropertiesMetadata.Builder()
         *    .putProperty|putAllProperties(String =&gt; List&amp;lt;io.syndesis.common.io.syndesis.common.model.connection.WithDynamicProperties.ActionPropertySuggestion&amp;gt;) // {@link io.syndesis.common.model.connection.DynamicConnectionPropertiesMetadata#properties() properties} mappings
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof DynamicConnectionPropertiesMetadata.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new DynamicConnectionPropertiesMetadata.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.connection.DynamicConnectionPropertiesMetadata} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final DynamicConnectionPropertiesMetadata.Builder createFrom(
                DynamicConnectionPropertiesMetadata instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (DynamicConnectionPropertiesMetadata.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.connection.WithDynamicProperties} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final DynamicConnectionPropertiesMetadata.Builder createFrom(
                WithDynamicProperties instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (DynamicConnectionPropertiesMetadata.Builder) this;
        }

        private void from(Object object) {
            @Var long bits = 0;
            if (object instanceof DynamicConnectionPropertiesMetadata) {
                DynamicConnectionPropertiesMetadata instance =
                        (DynamicConnectionPropertiesMetadata) object;
                if ((bits & 0x1L) == 0) {
                    putAllProperties(instance.properties());
                    bits |= 0x1L;
                }
            }
            if (object instanceof WithDynamicProperties) {
                WithDynamicProperties instance = (WithDynamicProperties) object;
                if ((bits & 0x1L) == 0) {
                    putAllProperties(instance.properties());
                    bits |= 0x1L;
                }
            }
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.connection.DynamicConnectionPropertiesMetadata#properties() properties} map.
         *
         * @param key   The key in the properties map
         * @param value The associated value in the properties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final DynamicConnectionPropertiesMetadata.Builder putProperty(
                String key, List<ActionPropertySuggestion> value) {
            this.properties.put(
                    Objects.requireNonNull(key, "properties key"),
                    Objects.requireNonNull(value, "properties value"));
            return (DynamicConnectionPropertiesMetadata.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.connection.DynamicConnectionPropertiesMetadata#properties() properties} map. Nulls are not permitted
         *
         * @param entry The key and value entry
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final DynamicConnectionPropertiesMetadata.Builder putProperty(
                Map.Entry<String, ? extends List<ActionPropertySuggestion>> entry) {
            String k = entry.getKey();
            List<ActionPropertySuggestion> v = entry.getValue();
            this.properties.put(
                    Objects.requireNonNull(k, "properties key"),
                    Objects.requireNonNull(v, "properties value"));
            return (DynamicConnectionPropertiesMetadata.Builder) this;
        }

        /**
         * Sets or replaces all mappings from the specified map as entries for the {@link io.syndesis.common.model.connection.DynamicConnectionPropertiesMetadata#properties() properties} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the properties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("properties")
        public final DynamicConnectionPropertiesMetadata.Builder properties(
                Map<String, ? extends List<ActionPropertySuggestion>> entries) {
            this.properties.clear();
            return putAllProperties(entries);
        }

        /**
         * Put all mappings from the specified map as entries to {@link io.syndesis.common.model.connection.DynamicConnectionPropertiesMetadata#properties() properties} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the properties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final DynamicConnectionPropertiesMetadata.Builder putAllProperties(
                Map<String, ? extends List<ActionPropertySuggestion>> entries) {
            for (Map.Entry<String, ? extends List<ActionPropertySuggestion>> e : entries.entrySet()) {
                String k = e.getKey();
                List<ActionPropertySuggestion> v = e.getValue();
                this.properties.put(
                        Objects.requireNonNull(k, "properties key"),
                        Objects.requireNonNull(v, "properties value"));
            }
            return (DynamicConnectionPropertiesMetadata.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.connection.DynamicConnectionPropertiesMetadata DynamicConnectionPropertiesMetadata}.
         *
         * @return An immutable instance of DynamicConnectionPropertiesMetadata
         * @throws IllegalStateException if any required attributes are missing
         */
        public DynamicConnectionPropertiesMetadata build() {
            return ImmutableDynamicConnectionPropertiesMetadata.validate(
                    new ImmutableDynamicConnectionPropertiesMetadata(null,
                            createUnmodifiableMap(false, false, properties)));
        }
    }
}
