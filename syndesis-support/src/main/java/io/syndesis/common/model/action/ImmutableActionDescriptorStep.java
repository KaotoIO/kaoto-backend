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
import io.syndesis.common.model.WithConfigurationProperties;
import io.syndesis.common.model.WithConfiguredProperties;
import io.syndesis.common.model.WithName;
import io.syndesis.common.model.connection.ConfigurationProperty;

import java.io.ObjectStreamException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Immutable implementation of {@link io.syndesis.common.model.action.ActionDescriptor.ActionDescriptorStep}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new ActionDescriptor.ActionDescriptorStep.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableActionDescriptorStep.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableActionDescriptorStep
        implements ActionDescriptor.ActionDescriptorStep {
    private final String name;
    private final Map<String, ConfigurationProperty> properties;
    private final Map<String, String> configuredProperties;
    private final String description;

    private ImmutableActionDescriptorStep(
            String name,
            Map<String, ? extends ConfigurationProperty> properties,
            Map<String, ? extends String> configuredProperties,
            String description) {
        this.name = name;
        this.properties = createUnmodifiableMap(true, false, properties);
        this.configuredProperties =
                createUnmodifiableMap(true, false, configuredProperties);
        this.description = description;
    }

    private ImmutableActionDescriptorStep(Builder builder) {
        this.name = builder.name;
        this.properties =
                createUnmodifiableMap(false, false, builder.properties);
        this.description = builder.description;
        this.configuredProperties = builder.configuredPropertiesIsSet()
                ? createUnmodifiableMap(false, false,
                builder.configuredProperties)
                : createUnmodifiableMap(true, false,
                ActionDescriptor.ActionDescriptorStep.super.getConfiguredProperties());
    }

    private ImmutableActionDescriptorStep(
            ImmutableActionDescriptorStep original,
            String name,
            Map<String, ConfigurationProperty> properties,
            Map<String, String> configuredProperties,
            String description) {
        this.name = name;
        this.properties = properties;
        this.configuredProperties = configuredProperties;
        this.description = description;
    }

    /**
     * Construct a new immutable {@code ActionDescriptorStep} instance.
     *
     * @param name                 The value for the {@code name} attribute
     * @param properties           The value for the {@code properties} attribute
     * @param configuredProperties The value for the {@code configuredProperties} attribute
     * @param description          The value for the {@code description} attribute
     * @return An immutable ActionDescriptorStep instance
     */
    public static ActionDescriptor.ActionDescriptorStep of(String name,
                                                           Map<String, ? extends ConfigurationProperty> properties,
                                                           Map<String, ? extends String> configuredProperties,
                                                           String description) {
        return validate(new ImmutableActionDescriptorStep(name, properties,
                configuredProperties, description));
    }

    private static ImmutableActionDescriptorStep validate(
            ImmutableActionDescriptorStep instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.action.ActionDescriptor.ActionDescriptorStep} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable ActionDescriptorStep instance
     */
    public static ActionDescriptor.ActionDescriptorStep copyOf(
            ActionDescriptor.ActionDescriptorStep instance) {
        if (instance instanceof ImmutableActionDescriptorStep) {
            return (ImmutableActionDescriptorStep) instance;
        }
        return new ActionDescriptor.ActionDescriptorStep.Builder()
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
     * @return The value of the {@code name} attribute
     */
    @JsonProperty("name")
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return The value of the {@code properties} attribute
     */
    @JsonProperty("properties")
    @Override
    public Map<String, ConfigurationProperty> getProperties() {
        return properties;
    }

    /**
     * @return The value of the {@code configuredProperties} attribute
     */
    @JsonProperty("configuredProperties")
    @Override
    public Map<String, String> getConfiguredProperties() {
        return configuredProperties;
    }

    /**
     * @return The value of the {@code description} attribute
     */
    @JsonProperty("description")
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.action.ActionDescriptor.ActionDescriptorStep#getName() name} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for name (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableActionDescriptorStep withName(String value) {
        if (Objects.equals(this.name, value)) {
            return this;
        }
        return validate(
                new ImmutableActionDescriptorStep(this, value, this.properties,
                        this.configuredProperties, this.description));
    }

    /**
     * Copy the current immutable object by replacing the {@link io.syndesis.common.model.action.ActionDescriptor.ActionDescriptorStep#getProperties() properties} map with the specified map.
     * Nulls are not permitted as keys or values.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param entries The entries to be added to the properties map
     * @return A modified copy of {@code this} object
     */
    public final ImmutableActionDescriptorStep withProperties(
            Map<String, ? extends ConfigurationProperty> entries) {
        if (this.properties == entries) {
            return this;
        }
        Map<String, ConfigurationProperty> newValue =
                createUnmodifiableMap(true, false, entries);
        return validate(
                new ImmutableActionDescriptorStep(this, this.name, newValue,
                        this.configuredProperties, this.description));
    }

    /**
     * Copy the current immutable object by replacing the {@link io.syndesis.common.model.action.ActionDescriptor.ActionDescriptorStep#getConfiguredProperties() configuredProperties} map with the specified map.
     * Nulls are not permitted as keys or values.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param entries The entries to be added to the configuredProperties map
     * @return A modified copy of {@code this} object
     */
    public final ImmutableActionDescriptorStep withConfiguredProperties(
            Map<String, ? extends String> entries) {
        if (this.configuredProperties == entries) {
            return this;
        }
        Map<String, String> newValue =
                createUnmodifiableMap(true, false, entries);
        return validate(new ImmutableActionDescriptorStep(this, this.name,
                this.properties, newValue, this.description));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.action.ActionDescriptor.ActionDescriptorStep#getDescription() description} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for description (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableActionDescriptorStep withDescription(String value) {
        if (Objects.equals(this.description, value)) {
            return this;
        }
        return validate(new ImmutableActionDescriptorStep(this, this.name,
                this.properties, this.configuredProperties, value));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableActionDescriptorStep} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableActionDescriptorStep
                && equalTo((ImmutableActionDescriptorStep) another);
    }

    private boolean equalTo(ImmutableActionDescriptorStep another) {
        return Objects.equals(name, another.name)
                && properties.equals(another.properties)
                && configuredProperties.equals(another.configuredProperties)
                && Objects.equals(description, another.description);
    }

    /**
     * Computes a hash code from attributes: {@code name}, {@code properties}, {@code configuredProperties}, {@code description}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(name);
        h += (h << 5) + properties.hashCode();
        h += (h << 5) + configuredProperties.hashCode();
        h += (h << 5) + Objects.hashCode(description);
        return h;
    }

    /**
     * Prints the immutable value {@code ActionDescriptorStep} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        return "ActionDescriptorStep{"
                + "name=" + name
                + ", properties=" + properties
                + ", configuredProperties=" + configuredProperties
                + ", description=" + description
                + "}";
    }

    private Object readResolve() throws ObjectStreamException {
        return validate(this);
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.action.ActionDescriptor.ActionDescriptorStep ActionDescriptorStep}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private static final long OPT_BIT_CONFIGURED_PROPERTIES = 0x1L;
        private long optBits;

        private String name;
        private Map<String, ConfigurationProperty> properties =
                new LinkedHashMap<String, ConfigurationProperty>();
        private Map<String, String> configuredProperties =
                new LinkedHashMap<String, String>();
        private String description;

        /**
         * Creates a builder for {@link io.syndesis.common.model.action.ActionDescriptor.ActionDescriptorStep ActionDescriptorStep} instances.
         * <pre>
         * new ActionDescriptor.ActionDescriptorStep.Builder()
         *    .name(String | null) // nullable {@link io.syndesis.common.model.action.ActionDescriptor.ActionDescriptorStep#getName() name}
         *    .putProperty|putAllProperties(String =&gt; io.syndesis.common.io.syndesis.common.model.connection.ConfigurationProperty) // {@link io.syndesis.common.model.action.ActionDescriptor.ActionDescriptorStep#getProperties() properties} mappings
         *    .putConfiguredProperty|putAllConfiguredProperties(String =&gt; String) // {@link io.syndesis.common.model.action.ActionDescriptor.ActionDescriptorStep#getConfiguredProperties() configuredProperties} mappings
         *    .description(String | null) // nullable {@link io.syndesis.common.model.action.ActionDescriptor.ActionDescriptorStep#getDescription() description}
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof ActionDescriptor.ActionDescriptorStep.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new ActionDescriptor.ActionDescriptorStep.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithName} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ActionDescriptor.ActionDescriptorStep.Builder createFrom(
                WithName instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (ActionDescriptor.ActionDescriptorStep.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithConfiguredProperties} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ActionDescriptor.ActionDescriptorStep.Builder createFrom(
                WithConfiguredProperties instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (ActionDescriptor.ActionDescriptorStep.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.action.ActionDescriptor.ActionDescriptorStep} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ActionDescriptor.ActionDescriptorStep.Builder createFrom(
                ActionDescriptor.ActionDescriptorStep instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (ActionDescriptor.ActionDescriptorStep.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithConfigurationProperties} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ActionDescriptor.ActionDescriptorStep.Builder createFrom(
                WithConfigurationProperties instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (ActionDescriptor.ActionDescriptorStep.Builder) this;
        }

        private void from(Object object) {
            if (object instanceof WithName) {
                WithName instance = (WithName) object;
                String nameValue = instance.getName();
                if (nameValue != null) {
                    name(nameValue);
                }
            }
            if (object instanceof WithConfiguredProperties) {
                WithConfiguredProperties instance =
                        (WithConfiguredProperties) object;
                putAllConfiguredProperties(instance.getConfiguredProperties());
            }
            if (object instanceof ActionDescriptor.ActionDescriptorStep) {
                ActionDescriptor.ActionDescriptorStep instance =
                        (ActionDescriptor.ActionDescriptorStep) object;
                String descriptionValue = instance.getDescription();
                if (descriptionValue != null) {
                    description(descriptionValue);
                }
            }
            if (object instanceof WithConfigurationProperties) {
                WithConfigurationProperties instance =
                        (WithConfigurationProperties) object;
                putAllProperties(instance.getProperties());
            }
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.action.ActionDescriptor.ActionDescriptorStep#getName() name} attribute.
         *
         * @param name The value for name (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("name")
        public final ActionDescriptor.ActionDescriptorStep.Builder name(
                String name) {
            this.name = name;
            return (ActionDescriptor.ActionDescriptorStep.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.action.ActionDescriptor.ActionDescriptorStep#getProperties() properties} map.
         *
         * @param key   The key in the properties map
         * @param value The associated value in the properties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ActionDescriptor.ActionDescriptorStep.Builder putProperty(
                String key, ConfigurationProperty value) {
            this.properties.put(
                    Objects.requireNonNull(key, "properties key"),
                    Objects.requireNonNull(value, "properties value"));
            return (ActionDescriptor.ActionDescriptorStep.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.action.ActionDescriptor.ActionDescriptorStep#getProperties() properties} map. Nulls are not permitted
         *
         * @param entry The key and value entry
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ActionDescriptor.ActionDescriptorStep.Builder putProperty(
                Map.Entry<String, ? extends ConfigurationProperty> entry) {
            String k = entry.getKey();
            ConfigurationProperty v = entry.getValue();
            this.properties.put(
                    Objects.requireNonNull(k, "properties key"),
                    Objects.requireNonNull(v, "properties value"));
            return (ActionDescriptor.ActionDescriptorStep.Builder) this;
        }

        /**
         * Sets or replaces all mappings from the specified map as entries for the {@link io.syndesis.common.model.action.ActionDescriptor.ActionDescriptorStep#getProperties() properties} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the properties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("properties")
        public final ActionDescriptor.ActionDescriptorStep.Builder properties(
                Map<String, ? extends ConfigurationProperty> entries) {
            this.properties.clear();
            return putAllProperties(entries);
        }

        /**
         * Put all mappings from the specified map as entries to {@link io.syndesis.common.model.action.ActionDescriptor.ActionDescriptorStep#getProperties() properties} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the properties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ActionDescriptor.ActionDescriptorStep.Builder putAllProperties(
                Map<String, ? extends ConfigurationProperty> entries) {
            for (Map.Entry<String, ? extends ConfigurationProperty> e : entries.entrySet()) {
                String k = e.getKey();
                ConfigurationProperty v = e.getValue();
                this.properties.put(
                        Objects.requireNonNull(k, "properties key"),
                        Objects.requireNonNull(v, "properties value"));
            }
            return (ActionDescriptor.ActionDescriptorStep.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.action.ActionDescriptor.ActionDescriptorStep#getConfiguredProperties() configuredProperties} map.
         *
         * @param key   The key in the configuredProperties map
         * @param value The associated value in the configuredProperties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ActionDescriptor.ActionDescriptorStep.Builder putConfiguredProperty(
                String key, String value) {
            this.configuredProperties.put(
                    Objects.requireNonNull(key, "configuredProperties key"),
                    Objects.requireNonNull(value,
                            "configuredProperties value"));
            optBits |= OPT_BIT_CONFIGURED_PROPERTIES;
            return (ActionDescriptor.ActionDescriptorStep.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.action.ActionDescriptor.ActionDescriptorStep#getConfiguredProperties() configuredProperties} map. Nulls are not permitted
         *
         * @param entry The key and value entry
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ActionDescriptor.ActionDescriptorStep.Builder putConfiguredProperty(
                Map.Entry<String, ? extends String> entry) {
            String k = entry.getKey();
            String v = entry.getValue();
            this.configuredProperties.put(
                    Objects.requireNonNull(k, "configuredProperties key"),
                    Objects.requireNonNull(v, "configuredProperties value"));
            optBits |= OPT_BIT_CONFIGURED_PROPERTIES;
            return (ActionDescriptor.ActionDescriptorStep.Builder) this;
        }

        /**
         * Sets or replaces all mappings from the specified map as entries for the {@link io.syndesis.common.model.action.ActionDescriptor.ActionDescriptorStep#getConfiguredProperties() configuredProperties} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the configuredProperties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("configuredProperties")
        public final ActionDescriptor.ActionDescriptorStep.Builder configuredProperties(
                Map<String, ? extends String> entries) {
            this.configuredProperties.clear();
            optBits |= OPT_BIT_CONFIGURED_PROPERTIES;
            return putAllConfiguredProperties(entries);
        }

        /**
         * Put all mappings from the specified map as entries to {@link io.syndesis.common.model.action.ActionDescriptor.ActionDescriptorStep#getConfiguredProperties() configuredProperties} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the configuredProperties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ActionDescriptor.ActionDescriptorStep.Builder putAllConfiguredProperties(
                Map<String, ? extends String> entries) {
            for (Map.Entry<String, ? extends String> e : entries.entrySet()) {
                String k = e.getKey();
                String v = e.getValue();
                this.configuredProperties.put(
                        Objects.requireNonNull(k, "configuredProperties key"),
                        Objects.requireNonNull(v,
                                "configuredProperties value"));
            }
            optBits |= OPT_BIT_CONFIGURED_PROPERTIES;
            return (ActionDescriptor.ActionDescriptorStep.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.action.ActionDescriptor.ActionDescriptorStep#getDescription() description} attribute.
         *
         * @param description The value for description (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("description")
        public final ActionDescriptor.ActionDescriptorStep.Builder description(
                String description) {
            this.description = description;
            return (ActionDescriptor.ActionDescriptorStep.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.action.ActionDescriptor.ActionDescriptorStep ActionDescriptorStep}.
         *
         * @return An immutable instance of ActionDescriptorStep
         * @throws IllegalStateException if any required attributes are missing
         */
        public ActionDescriptor.ActionDescriptorStep build() {
            return ImmutableActionDescriptorStep.validate(
                    new ImmutableActionDescriptorStep(this));
        }

        private boolean configuredPropertiesIsSet() {
            return (optBits & OPT_BIT_CONFIGURED_PROPERTIES) != 0;
        }
    }
}
