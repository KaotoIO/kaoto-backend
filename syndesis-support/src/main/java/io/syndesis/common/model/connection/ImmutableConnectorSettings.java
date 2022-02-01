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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Var;
import io.syndesis.common.model.WithConfigurationProperties;
import io.syndesis.common.model.WithConfiguredProperties;
import io.syndesis.common.model.WithName;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Immutable implementation of {@link io.syndesis.common.model.connection.ConnectorSettings}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new ConnectorSettings.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableConnectorSettings.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableConnectorSettings implements ConnectorSettings {
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private static final byte STAGE_INITIALIZED = 1;
    private final String name;
    private final Map<String, ConfigurationProperty> properties;
    private final Map<String, String> configuredProperties;
    private final String connectorTemplateId;
    private final String description;
    private final String icon;
    private final Optional<InputStream> specification;
    @SuppressWarnings("Immutable")
    private transient volatile InitShim initShim = new InitShim();

    private ImmutableConnectorSettings(
            String name,
            Map<String, ? extends ConfigurationProperty> properties,
            Map<String, ? extends String> configuredProperties,
            String connectorTemplateId,
            String description,
            String icon,
            Optional<InputStream> specification) {
        this.name = name;
        this.properties = createUnmodifiableMap(true, false, properties);
        this.configuredProperties =
                createUnmodifiableMap(true, false, configuredProperties);
        this.connectorTemplateId = connectorTemplateId;
        this.description = description;
        this.icon = icon;
        this.specification =
                Objects.requireNonNull(specification, "specification");
        this.initShim = null;
    }

    private ImmutableConnectorSettings(Builder builder) {
        this.name = builder.name;
        this.properties =
                createUnmodifiableMap(false, false, builder.properties);
        this.connectorTemplateId = builder.connectorTemplateId;
        this.description = builder.description;
        this.icon = builder.icon;
        if (builder.configuredPropertiesIsSet()) {
            initShim.configuredProperties(createUnmodifiableMap(false, false,
                    builder.configuredProperties));
        }
        if (builder.specification != null) {
            initShim.specification(builder.specification);
        }
        this.configuredProperties = initShim.getConfiguredProperties();
        this.specification = initShim.getSpecification();
        this.initShim = null;
    }

    private ImmutableConnectorSettings(
            ImmutableConnectorSettings original,
            String name,
            Map<String, ConfigurationProperty> properties,
            Map<String, String> configuredProperties,
            String connectorTemplateId,
            String description,
            String icon,
            Optional<InputStream> specification) {
        this.name = name;
        this.properties = properties;
        this.configuredProperties = configuredProperties;
        this.connectorTemplateId = connectorTemplateId;
        this.description = description;
        this.icon = icon;
        this.specification = specification;
        this.initShim = null;
    }

    /**
     * Construct a new immutable {@code ConnectorSettings} instance.
     *
     * @param name                 The value for the {@code name} attribute
     * @param properties           The value for the {@code properties} attribute
     * @param configuredProperties The value for the {@code configuredProperties} attribute
     * @param connectorTemplateId  The value for the {@code connectorTemplateId} attribute
     * @param description          The value for the {@code description} attribute
     * @param icon                 The value for the {@code icon} attribute
     * @param specification        The value for the {@code specification} attribute
     * @return An immutable ConnectorSettings instance
     */
    public static ConnectorSettings of(String name,
                                       Map<String, ? extends ConfigurationProperty> properties,
                                       Map<String, ? extends String> configuredProperties,
                                       String connectorTemplateId,
                                       String description, String icon,
                                       Optional<InputStream> specification) {
        return validate(new ImmutableConnectorSettings(name, properties,
                configuredProperties, connectorTemplateId, description, icon,
                specification));
    }

    private static ImmutableConnectorSettings validate(
            ImmutableConnectorSettings instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.connection.ConnectorSettings} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable ConnectorSettings instance
     */
    public static ConnectorSettings copyOf(ConnectorSettings instance) {
        if (instance instanceof ImmutableConnectorSettings) {
            return (ImmutableConnectorSettings) instance;
        }
        return new ConnectorSettings.Builder()
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

    private Map<String, String> getConfiguredPropertiesInitialize() {
        return ConnectorSettings.super.getConfiguredProperties();
    }

    private Optional<InputStream> getSpecificationInitialize() {
        return ConnectorSettings.super.getSpecification();
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
        InitShim shim = this.initShim;
        return shim != null
                ? shim.getConfiguredProperties()
                : this.configuredProperties;
    }

    /**
     * @return The value of the {@code connectorTemplateId} attribute
     */
    @JsonProperty("connectorTemplateId")
    @Override
    public String getConnectorTemplateId() {
        return connectorTemplateId;
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
     * @return The value of the {@code icon} attribute
     */
    @JsonProperty("icon")
    @Override
    public String getIcon() {
        return icon;
    }

    /**
     * @return The value of the {@code specification} attribute
     */
    @JsonProperty("specification")
    @JsonIgnore
    @Override
    public Optional<InputStream> getSpecification() {
        InitShim shim = this.initShim;
        return shim != null
                ? shim.getSpecification()
                : this.specification;
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConnectorSettings#getName() name} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for name (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConnectorSettings withName(String value) {
        if (Objects.equals(this.name, value)) {
            return this;
        }
        return validate(new ImmutableConnectorSettings(
                this,
                value,
                this.properties,
                this.configuredProperties,
                this.connectorTemplateId,
                this.description,
                this.icon,
                this.specification));
    }

    /**
     * Copy the current immutable object by replacing the {@link io.syndesis.common.model.connection.ConnectorSettings#getProperties() properties} map with the specified map.
     * Nulls are not permitted as keys or values.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param entries The entries to be added to the properties map
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnectorSettings withProperties(
            Map<String, ? extends ConfigurationProperty> entries) {
        if (this.properties == entries) {
            return this;
        }
        Map<String, ConfigurationProperty> newValue =
                createUnmodifiableMap(true, false, entries);
        return validate(new ImmutableConnectorSettings(
                this,
                this.name,
                newValue,
                this.configuredProperties,
                this.connectorTemplateId,
                this.description,
                this.icon,
                this.specification));
    }

    /**
     * Copy the current immutable object by replacing the {@link io.syndesis.common.model.connection.ConnectorSettings#getConfiguredProperties() configuredProperties} map with the specified map.
     * Nulls are not permitted as keys or values.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param entries The entries to be added to the configuredProperties map
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnectorSettings withConfiguredProperties(
            Map<String, ? extends String> entries) {
        if (this.configuredProperties == entries) {
            return this;
        }
        Map<String, String> newValue =
                createUnmodifiableMap(true, false, entries);
        return validate(new ImmutableConnectorSettings(
                this,
                this.name,
                this.properties,
                newValue,
                this.connectorTemplateId,
                this.description,
                this.icon,
                this.specification));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConnectorSettings#getConnectorTemplateId() connectorTemplateId} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for connectorTemplateId (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConnectorSettings withConnectorTemplateId(
            String value) {
        if (Objects.equals(this.connectorTemplateId, value)) {
            return this;
        }
        return validate(new ImmutableConnectorSettings(
                this,
                this.name,
                this.properties,
                this.configuredProperties,
                value,
                this.description,
                this.icon,
                this.specification));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConnectorSettings#getDescription() description} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for description (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConnectorSettings withDescription(String value) {
        if (Objects.equals(this.description, value)) {
            return this;
        }
        return validate(new ImmutableConnectorSettings(
                this,
                this.name,
                this.properties,
                this.configuredProperties,
                this.connectorTemplateId,
                value,
                this.icon,
                this.specification));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConnectorSettings#getIcon() icon} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for icon (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConnectorSettings withIcon(String value) {
        if (Objects.equals(this.icon, value)) {
            return this;
        }
        return validate(new ImmutableConnectorSettings(
                this,
                this.name,
                this.properties,
                this.configuredProperties,
                this.connectorTemplateId,
                this.description,
                value,
                this.specification));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConnectorSettings#getSpecification() specification} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for specification
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConnectorSettings withSpecification(
            Optional<InputStream> value) {
        Optional<InputStream> newValue =
                Objects.requireNonNull(value, "specification");
        if (this.specification.equals(newValue)) {
            return this;
        }
        return validate(new ImmutableConnectorSettings(
                this,
                this.name,
                this.properties,
                this.configuredProperties,
                this.connectorTemplateId,
                this.description,
                this.icon,
                newValue));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableConnectorSettings} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableConnectorSettings
                && equalTo((ImmutableConnectorSettings) another);
    }

    private boolean equalTo(ImmutableConnectorSettings another) {
        return Objects.equals(name, another.name)
                && properties.equals(another.properties)
                && configuredProperties.equals(another.configuredProperties)
                && Objects.equals(connectorTemplateId,
                another.connectorTemplateId)
                && Objects.equals(description, another.description)
                && Objects.equals(icon, another.icon)
                && specification.equals(another.specification);
    }

    /**
     * Computes a hash code from attributes: {@code name}, {@code properties}, {@code configuredProperties}, {@code connectorTemplateId}, {@code description}, {@code icon}, {@code specification}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(name);
        h += (h << 5) + properties.hashCode();
        h += (h << 5) + configuredProperties.hashCode();
        h += (h << 5) + Objects.hashCode(connectorTemplateId);
        h += (h << 5) + Objects.hashCode(description);
        h += (h << 5) + Objects.hashCode(icon);
        h += (h << 5) + specification.hashCode();
        return h;
    }

    /**
     * Prints the immutable value {@code ConnectorSettings} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        return "ConnectorSettings{"
                + "name=" + name
                + ", properties=" + properties
                + ", configuredProperties=" + configuredProperties
                + ", connectorTemplateId=" + connectorTemplateId
                + ", description=" + description
                + ", icon=" + icon
                + ", specification=" + specification
                + "}";
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.connection.ConnectorSettings ConnectorSettings}.
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
        private String connectorTemplateId;
        private String description;
        private String icon;
        private Optional<InputStream> specification;

        /**
         * Creates a builder for {@link io.syndesis.common.model.connection.ConnectorSettings ConnectorSettings} instances.
         * <pre>
         * new ConnectorSettings.Builder()
         *    .name(String | null) // nullable {@link io.syndesis.common.model.connection.ConnectorSettings#getName() name}
         *    .putProperty|putAllProperties(String =&gt; io.syndesis.common.io.syndesis.common.model.connection.ConfigurationProperty) // {@link io.syndesis.common.model.connection.ConnectorSettings#getProperties() properties} mappings
         *    .putConfiguredProperty|putAllConfiguredProperties(String =&gt; String) // {@link io.syndesis.common.model.connection.ConnectorSettings#getConfiguredProperties() configuredProperties} mappings
         *    .connectorTemplateId(String | null) // nullable {@link io.syndesis.common.model.connection.ConnectorSettings#getConnectorTemplateId() connectorTemplateId}
         *    .description(String | null) // nullable {@link io.syndesis.common.model.connection.ConnectorSettings#getDescription() description}
         *    .icon(String | null) // nullable {@link io.syndesis.common.model.connection.ConnectorSettings#getIcon() icon}
         *    .specification(Optional&amp;lt;java.io.InputStream&amp;gt;) // optional {@link io.syndesis.common.model.connection.ConnectorSettings#getSpecification() specification}
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof ConnectorSettings.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new ConnectorSettings.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithName} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorSettings.Builder createFrom(WithName instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (ConnectorSettings.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithConfiguredProperties} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorSettings.Builder createFrom(
                WithConfiguredProperties instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (ConnectorSettings.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.connection.ConnectorSettings} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorSettings.Builder createFrom(
                ConnectorSettings instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (ConnectorSettings.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithConfigurationProperties} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorSettings.Builder createFrom(
                WithConfigurationProperties instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (ConnectorSettings.Builder) this;
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
            if (object instanceof ConnectorSettings) {
                ConnectorSettings instance = (ConnectorSettings) object;
                String iconValue = instance.getIcon();
                if (iconValue != null) {
                    icon(iconValue);
                }
                String descriptionValue = instance.getDescription();
                if (descriptionValue != null) {
                    description(descriptionValue);
                }
                specification(instance.getSpecification());
                String connectorTemplateIdValue =
                        instance.getConnectorTemplateId();
                if (connectorTemplateIdValue != null) {
                    connectorTemplateId(connectorTemplateIdValue);
                }
            }
            if (object instanceof WithConfigurationProperties) {
                WithConfigurationProperties instance =
                        (WithConfigurationProperties) object;
                putAllProperties(instance.getProperties());
            }
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConnectorSettings#getName() name} attribute.
         *
         * @param name The value for name (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("name")
        public final ConnectorSettings.Builder name(String name) {
            this.name = name;
            return (ConnectorSettings.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.connection.ConnectorSettings#getProperties() properties} map.
         *
         * @param key   The key in the properties map
         * @param value The associated value in the properties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorSettings.Builder putProperty(String key,
                                                           ConfigurationProperty value) {
            this.properties.put(
                    Objects.requireNonNull(key, "properties key"),
                    Objects.requireNonNull(value, "properties value"));
            return (ConnectorSettings.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.connection.ConnectorSettings#getProperties() properties} map. Nulls are not permitted
         *
         * @param entry The key and value entry
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorSettings.Builder putProperty(
                Map.Entry<String, ? extends ConfigurationProperty> entry) {
            String k = entry.getKey();
            ConfigurationProperty v = entry.getValue();
            this.properties.put(
                    Objects.requireNonNull(k, "properties key"),
                    Objects.requireNonNull(v, "properties value"));
            return (ConnectorSettings.Builder) this;
        }

        /**
         * Sets or replaces all mappings from the specified map as entries for the {@link io.syndesis.common.model.connection.ConnectorSettings#getProperties() properties} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the properties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("properties")
        public final ConnectorSettings.Builder properties(
                Map<String, ? extends ConfigurationProperty> entries) {
            this.properties.clear();
            return putAllProperties(entries);
        }

        /**
         * Put all mappings from the specified map as entries to {@link io.syndesis.common.model.connection.ConnectorSettings#getProperties() properties} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the properties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorSettings.Builder putAllProperties(
                Map<String, ? extends ConfigurationProperty> entries) {
            for (Map.Entry<String, ? extends ConfigurationProperty> e : entries.entrySet()) {
                String k = e.getKey();
                ConfigurationProperty v = e.getValue();
                this.properties.put(
                        Objects.requireNonNull(k, "properties key"),
                        Objects.requireNonNull(v, "properties value"));
            }
            return (ConnectorSettings.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.connection.ConnectorSettings#getConfiguredProperties() configuredProperties} map.
         *
         * @param key   The key in the configuredProperties map
         * @param value The associated value in the configuredProperties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorSettings.Builder putConfiguredProperty(String key,
                                                                     String value) {
            this.configuredProperties.put(
                    Objects.requireNonNull(key, "configuredProperties key"),
                    Objects.requireNonNull(value,
                            "configuredProperties value"));
            optBits |= OPT_BIT_CONFIGURED_PROPERTIES;
            return (ConnectorSettings.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.connection.ConnectorSettings#getConfiguredProperties() configuredProperties} map. Nulls are not permitted
         *
         * @param entry The key and value entry
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorSettings.Builder putConfiguredProperty(
                Map.Entry<String, ? extends String> entry) {
            String k = entry.getKey();
            String v = entry.getValue();
            this.configuredProperties.put(
                    Objects.requireNonNull(k, "configuredProperties key"),
                    Objects.requireNonNull(v, "configuredProperties value"));
            optBits |= OPT_BIT_CONFIGURED_PROPERTIES;
            return (ConnectorSettings.Builder) this;
        }

        /**
         * Sets or replaces all mappings from the specified map as entries for the {@link io.syndesis.common.model.connection.ConnectorSettings#getConfiguredProperties() configuredProperties} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the configuredProperties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("configuredProperties")
        public final ConnectorSettings.Builder configuredProperties(
                Map<String, ? extends String> entries) {
            this.configuredProperties.clear();
            optBits |= OPT_BIT_CONFIGURED_PROPERTIES;
            return putAllConfiguredProperties(entries);
        }

        /**
         * Put all mappings from the specified map as entries to {@link io.syndesis.common.model.connection.ConnectorSettings#getConfiguredProperties() configuredProperties} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the configuredProperties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorSettings.Builder putAllConfiguredProperties(
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
            return (ConnectorSettings.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConnectorSettings#getConnectorTemplateId() connectorTemplateId} attribute.
         *
         * @param connectorTemplateId The value for connectorTemplateId (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("connectorTemplateId")
        public final ConnectorSettings.Builder connectorTemplateId(
                String connectorTemplateId) {
            this.connectorTemplateId = connectorTemplateId;
            return (ConnectorSettings.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConnectorSettings#getDescription() description} attribute.
         *
         * @param description The value for description (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("description")
        public final ConnectorSettings.Builder description(String description) {
            this.description = description;
            return (ConnectorSettings.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConnectorSettings#getIcon() icon} attribute.
         *
         * @param icon The value for icon (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("icon")
        public final ConnectorSettings.Builder icon(String icon) {
            this.icon = icon;
            return (ConnectorSettings.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConnectorSettings#getSpecification() specification} attribute.
         * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link io.syndesis.common.model.connection.ConnectorSettings#getSpecification() specification}.</em>
         *
         * @param specification The value for specification
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("specification")
        @JsonIgnore
        public final ConnectorSettings.Builder specification(
                Optional<InputStream> specification) {
            this.specification =
                    Objects.requireNonNull(specification, "specification");
            return (ConnectorSettings.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.connection.ConnectorSettings ConnectorSettings}.
         *
         * @return An immutable instance of ConnectorSettings
         * @throws IllegalStateException if any required attributes are missing
         */
        public ConnectorSettings build() {
            return ImmutableConnectorSettings.validate(
                    new ImmutableConnectorSettings(this));
        }

        private boolean configuredPropertiesIsSet() {
            return (optBits & OPT_BIT_CONFIGURED_PROPERTIES) != 0;
        }
    }

    private final class InitShim {
        private byte configuredPropertiesBuildStage = STAGE_UNINITIALIZED;
        private Map<String, String> configuredProperties;
        private byte specificationBuildStage = STAGE_UNINITIALIZED;
        private Optional<InputStream> specification;

        Map<String, String> getConfiguredProperties() {
            if (configuredPropertiesBuildStage == STAGE_INITIALIZING) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (configuredPropertiesBuildStage == STAGE_UNINITIALIZED) {
                configuredPropertiesBuildStage = STAGE_INITIALIZING;
                this.configuredProperties = createUnmodifiableMap(true, false,
                        getConfiguredPropertiesInitialize());
                configuredPropertiesBuildStage = STAGE_INITIALIZED;
            }
            return this.configuredProperties;
        }

        void configuredProperties(Map<String, String> configuredProperties) {
            this.configuredProperties = configuredProperties;
            configuredPropertiesBuildStage = STAGE_INITIALIZED;
        }

        Optional<InputStream> getSpecification() {
            if (specificationBuildStage == STAGE_INITIALIZING) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (specificationBuildStage == STAGE_UNINITIALIZED) {
                specificationBuildStage = STAGE_INITIALIZING;
                this.specification =
                        Objects.requireNonNull(getSpecificationInitialize(),
                                "specification");
                specificationBuildStage = STAGE_INITIALIZED;
            }
            return this.specification;
        }

        void specification(Optional<InputStream> specification) {
            this.specification = specification;
            specificationBuildStage = STAGE_INITIALIZED;
        }

        private String formatInitCycleMessage() {
            List<String> attributes = new ArrayList<>();
            if (configuredPropertiesBuildStage == STAGE_INITIALIZING) {
                attributes.add("configuredProperties");
            }
            if (specificationBuildStage == STAGE_INITIALIZING) {
                attributes.add("specification");
            }
            return "Cannot build ConnectorSettings, attribute initializers form cycle "
                    + attributes;
        }
    }
}
