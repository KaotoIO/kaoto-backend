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
import io.syndesis.common.model.Kind;
import io.syndesis.common.model.WithConfigurationProperties;
import io.syndesis.common.model.WithConfiguredProperties;
import io.syndesis.common.model.WithKind;
import io.syndesis.common.model.WithName;
import io.syndesis.common.model.WithResourceId;

import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Immutable implementation of {@link io.syndesis.common.model.connection.ConnectorTemplate}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new ConnectorTemplate.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableConnectorTemplate.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableConnectorTemplate implements ConnectorTemplate {
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private static final byte STAGE_INITIALIZED = 1;
    private final String id;
    private final String name;
    private final Map<String, ConfigurationProperty> properties;
    private final Map<String, String> configuredProperties;
    private final String componentScheme;
    private final ConnectorGroup connectorGroup;
    private final Map<String, ConfigurationProperty> connectorProperties;
    private final String description;
    private final String icon;
    private final Kind kind;
    @SuppressWarnings("Immutable")
    private transient volatile InitShim initShim = new InitShim();

    @SuppressWarnings("unchecked") // safe covariant cast
    private ImmutableConnectorTemplate(
            Optional<String> id,
            String name,
            Map<String, ? extends ConfigurationProperty> properties,
            Map<String, ? extends String> configuredProperties,
            String componentScheme,
            Optional<? extends ConnectorGroup> connectorGroup,
            Map<String, ? extends ConfigurationProperty> connectorProperties,
            String description,
            String icon,
            Kind kind) {
        this.id = id.orElse(null);
        this.name = name;
        this.properties = createUnmodifiableMap(true, false, properties);
        this.configuredProperties =
                createUnmodifiableMap(true, false, configuredProperties);
        this.componentScheme = componentScheme;
        this.connectorGroup = connectorGroup.orElse(null);
        this.connectorProperties =
                createUnmodifiableMap(true, false, connectorProperties);
        this.description = description;
        this.icon = icon;
        this.kind = Objects.requireNonNull(kind, "kind");
        this.initShim = null;
    }

    private ImmutableConnectorTemplate(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.properties =
                createUnmodifiableMap(false, false, builder.properties);
        this.componentScheme = builder.componentScheme;
        this.connectorGroup = builder.connectorGroup;
        this.connectorProperties = createUnmodifiableMap(false, false,
                builder.connectorProperties);
        this.description = builder.description;
        this.icon = builder.icon;
        if (builder.configuredPropertiesIsSet()) {
            initShim.configuredProperties(createUnmodifiableMap(false, false,
                    builder.configuredProperties));
        }
        if (builder.kind != null) {
            initShim.kind(builder.kind);
        }
        this.configuredProperties = initShim.getConfiguredProperties();
        this.kind = initShim.getKind();
        this.initShim = null;
    }

    private ImmutableConnectorTemplate(
            ImmutableConnectorTemplate original,
            String id,
            String name,
            Map<String, ConfigurationProperty> properties,
            Map<String, String> configuredProperties,
            String componentScheme,
            ConnectorGroup connectorGroup,
            Map<String, ConfigurationProperty> connectorProperties,
            String description,
            String icon,
            Kind kind) {
        this.id = id;
        this.name = name;
        this.properties = properties;
        this.configuredProperties = configuredProperties;
        this.componentScheme = componentScheme;
        this.connectorGroup = connectorGroup;
        this.connectorProperties = connectorProperties;
        this.description = description;
        this.icon = icon;
        this.kind = kind;
        this.initShim = null;
    }

    /**
     * Construct a new immutable {@code ConnectorTemplate} instance.
     *
     * @param id                   The value for the {@code id} attribute
     * @param name                 The value for the {@code name} attribute
     * @param properties           The value for the {@code properties} attribute
     * @param configuredProperties The value for the {@code configuredProperties} attribute
     * @param componentScheme      The value for the {@code componentScheme} attribute
     * @param connectorGroup       The value for the {@code connectorGroup} attribute
     * @param connectorProperties  The value for the {@code connectorProperties} attribute
     * @param description          The value for the {@code description} attribute
     * @param icon                 The value for the {@code icon} attribute
     * @param kind                 The value for the {@code kind} attribute
     * @return An immutable ConnectorTemplate instance
     */
    public static ConnectorTemplate of(Optional<String> id, String name,
                                       Map<String, ? extends ConfigurationProperty> properties,
                                       Map<String, ? extends String> configuredProperties,
                                       String componentScheme,
                                       Optional<? extends ConnectorGroup> connectorGroup,
                                       Map<String, ? extends ConfigurationProperty> connectorProperties,
                                       String description, String icon,
                                       Kind kind) {
        return validate(new ImmutableConnectorTemplate(id, name, properties,
                configuredProperties, componentScheme, connectorGroup,
                connectorProperties, description, icon, kind));
    }

    private static ImmutableConnectorTemplate validate(
            ImmutableConnectorTemplate instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.connection.ConnectorTemplate} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable ConnectorTemplate instance
     */
    public static ConnectorTemplate copyOf(ConnectorTemplate instance) {
        if (instance instanceof ImmutableConnectorTemplate) {
            return (ImmutableConnectorTemplate) instance;
        }
        return new ConnectorTemplate.Builder()
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
        return ConnectorTemplate.super.getConfiguredProperties();
    }

    private Kind getKindInitialize() {
        return ConnectorTemplate.super.getKind();
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
     * @return The value of the {@code componentScheme} attribute
     */
    @JsonProperty("componentScheme")
    @Override
    public String getComponentScheme() {
        return componentScheme;
    }

    /**
     * @return The value of the {@code connectorGroup} attribute
     */
    @JsonProperty("connectorGroup")
    @Override
    public Optional<ConnectorGroup> getConnectorGroup() {
        return Optional.ofNullable(connectorGroup);
    }

    /**
     * @return The value of the {@code connectorProperties} attribute
     */
    @JsonProperty("connectorProperties")
    @Override
    public Map<String, ConfigurationProperty> getConnectorProperties() {
        return connectorProperties;
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
     * @return The value of the {@code kind} attribute
     */
    @JsonProperty("kind")
    @Override
    public Kind getKind() {
        InitShim shim = this.initShim;
        return shim != null
                ? shim.getKind()
                : this.kind;
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.connection.ConnectorTemplate#getId() id} attribute.
     *
     * @param value The value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnectorTemplate withId(String value) {
        String newValue = Objects.requireNonNull(value, "id");
        if (Objects.equals(this.id, newValue)) {
            return this;
        }
        return validate(new ImmutableConnectorTemplate(
                this,
                newValue,
                this.name,
                this.properties,
                this.configuredProperties,
                this.componentScheme,
                this.connectorGroup,
                this.connectorProperties,
                this.description,
                this.icon,
                this.kind));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.connection.ConnectorTemplate#getId() id} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnectorTemplate withId(Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.id, value)) {
            return this;
        }
        return validate(new ImmutableConnectorTemplate(
                this,
                value,
                this.name,
                this.properties,
                this.configuredProperties,
                this.componentScheme,
                this.connectorGroup,
                this.connectorProperties,
                this.description,
                this.icon,
                this.kind));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConnectorTemplate#getName() name} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for name (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConnectorTemplate withName(String value) {
        if (Objects.equals(this.name, value)) {
            return this;
        }
        return validate(new ImmutableConnectorTemplate(
                this,
                this.id,
                value,
                this.properties,
                this.configuredProperties,
                this.componentScheme,
                this.connectorGroup,
                this.connectorProperties,
                this.description,
                this.icon,
                this.kind));
    }

    /**
     * Copy the current immutable object by replacing the {@link io.syndesis.common.model.connection.ConnectorTemplate#getProperties() properties} map with the specified map.
     * Nulls are not permitted as keys or values.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param entries The entries to be added to the properties map
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnectorTemplate withProperties(
            Map<String, ? extends ConfigurationProperty> entries) {
        if (this.properties == entries) {
            return this;
        }
        Map<String, ConfigurationProperty> newValue =
                createUnmodifiableMap(true, false, entries);
        return validate(new ImmutableConnectorTemplate(
                this,
                this.id,
                this.name,
                newValue,
                this.configuredProperties,
                this.componentScheme,
                this.connectorGroup,
                this.connectorProperties,
                this.description,
                this.icon,
                this.kind));
    }

    /**
     * Copy the current immutable object by replacing the {@link io.syndesis.common.model.connection.ConnectorTemplate#getConfiguredProperties() configuredProperties} map with the specified map.
     * Nulls are not permitted as keys or values.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param entries The entries to be added to the configuredProperties map
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnectorTemplate withConfiguredProperties(
            Map<String, ? extends String> entries) {
        if (this.configuredProperties == entries) {
            return this;
        }
        Map<String, String> newValue =
                createUnmodifiableMap(true, false, entries);
        return validate(new ImmutableConnectorTemplate(
                this,
                this.id,
                this.name,
                this.properties,
                newValue,
                this.componentScheme,
                this.connectorGroup,
                this.connectorProperties,
                this.description,
                this.icon,
                this.kind));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConnectorTemplate#getComponentScheme() componentScheme} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for componentScheme (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConnectorTemplate withComponentScheme(String value) {
        if (Objects.equals(this.componentScheme, value)) {
            return this;
        }
        return validate(new ImmutableConnectorTemplate(
                this,
                this.id,
                this.name,
                this.properties,
                this.configuredProperties,
                value,
                this.connectorGroup,
                this.connectorProperties,
                this.description,
                this.icon,
                this.kind));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.connection.ConnectorTemplate#getConnectorGroup() connectorGroup} attribute.
     *
     * @param value The value for connectorGroup
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnectorTemplate withConnectorGroup(
            ConnectorGroup value) {
        ConnectorGroup newValue =
                Objects.requireNonNull(value, "connectorGroup");
        if (this.connectorGroup == newValue) {
            return this;
        }
        return validate(new ImmutableConnectorTemplate(
                this,
                this.id,
                this.name,
                this.properties,
                this.configuredProperties,
                this.componentScheme,
                newValue,
                this.connectorProperties,
                this.description,
                this.icon,
                this.kind));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.connection.ConnectorTemplate#getConnectorGroup() connectorGroup} attribute.
     * A shallow reference equality check is used on unboxed optional value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for connectorGroup
     * @return A modified copy of {@code this} object
     */
    @SuppressWarnings("unchecked") // safe covariant cast
    public final ImmutableConnectorTemplate withConnectorGroup(
            Optional<? extends ConnectorGroup> optional) {
        ConnectorGroup value = optional.orElse(null);
        if (this.connectorGroup == value) {
            return this;
        }
        return validate(new ImmutableConnectorTemplate(
                this,
                this.id,
                this.name,
                this.properties,
                this.configuredProperties,
                this.componentScheme,
                value,
                this.connectorProperties,
                this.description,
                this.icon,
                this.kind));
    }

    /**
     * Copy the current immutable object by replacing the {@link io.syndesis.common.model.connection.ConnectorTemplate#getConnectorProperties() connectorProperties} map with the specified map.
     * Nulls are not permitted as keys or values.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param entries The entries to be added to the connectorProperties map
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnectorTemplate withConnectorProperties(
            Map<String, ? extends ConfigurationProperty> entries) {
        if (this.connectorProperties == entries) {
            return this;
        }
        Map<String, ConfigurationProperty> newValue =
                createUnmodifiableMap(true, false, entries);
        return validate(new ImmutableConnectorTemplate(
                this,
                this.id,
                this.name,
                this.properties,
                this.configuredProperties,
                this.componentScheme,
                this.connectorGroup,
                newValue,
                this.description,
                this.icon,
                this.kind));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConnectorTemplate#getDescription() description} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for description (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConnectorTemplate withDescription(String value) {
        if (Objects.equals(this.description, value)) {
            return this;
        }
        return validate(new ImmutableConnectorTemplate(
                this,
                this.id,
                this.name,
                this.properties,
                this.configuredProperties,
                this.componentScheme,
                this.connectorGroup,
                this.connectorProperties,
                value,
                this.icon,
                this.kind));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConnectorTemplate#getIcon() icon} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for icon (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConnectorTemplate withIcon(String value) {
        if (Objects.equals(this.icon, value)) {
            return this;
        }
        return validate(new ImmutableConnectorTemplate(
                this,
                this.id,
                this.name,
                this.properties,
                this.configuredProperties,
                this.componentScheme,
                this.connectorGroup,
                this.connectorProperties,
                this.description,
                value,
                this.kind));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConnectorTemplate#getKind() kind} attribute.
     * A value equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for kind
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConnectorTemplate withKind(Kind value) {
        if (this.kind == value) {
            return this;
        }
        Kind newValue = Objects.requireNonNull(value, "kind");
        if (this.kind.equals(newValue)) {
            return this;
        }
        return validate(new ImmutableConnectorTemplate(
                this,
                this.id,
                this.name,
                this.properties,
                this.configuredProperties,
                this.componentScheme,
                this.connectorGroup,
                this.connectorProperties,
                this.description,
                this.icon,
                newValue));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableConnectorTemplate} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableConnectorTemplate
                && equalTo((ImmutableConnectorTemplate) another);
    }

    private boolean equalTo(ImmutableConnectorTemplate another) {
        return Objects.equals(id, another.id)
                && Objects.equals(name, another.name)
                && properties.equals(another.properties)
                && configuredProperties.equals(another.configuredProperties)
                && Objects.equals(componentScheme, another.componentScheme)
                && Objects.equals(connectorGroup, another.connectorGroup)
                && connectorProperties.equals(another.connectorProperties)
                && Objects.equals(description, another.description)
                && Objects.equals(icon, another.icon)
                && kind.equals(another.kind);
    }

    /**
     * Computes a hash code from attributes: {@code id}, {@code name}, {@code properties}, {@code configuredProperties}, {@code componentScheme}, {@code connectorGroup}, {@code connectorProperties}, {@code description}, {@code icon}, {@code kind}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(id);
        h += (h << 5) + Objects.hashCode(name);
        h += (h << 5) + properties.hashCode();
        h += (h << 5) + configuredProperties.hashCode();
        h += (h << 5) + Objects.hashCode(componentScheme);
        h += (h << 5) + Objects.hashCode(connectorGroup);
        h += (h << 5) + connectorProperties.hashCode();
        h += (h << 5) + Objects.hashCode(description);
        h += (h << 5) + Objects.hashCode(icon);
        h += (h << 5) + kind.hashCode();
        return h;
    }

    /**
     * Prints the immutable value {@code ConnectorTemplate} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("ConnectorTemplate{");
        if (id != null) {
            builder.append("id=").append(id);
        }
        if (name != null) {
            if (builder.length() > 18) {
                builder.append(", ");
            }
            builder.append("name=").append(name);
        }
        if (builder.length() > 18) {
            builder.append(", ");
        }
        builder.append("properties=").append(properties);
        builder.append(", ");
        builder.append("configuredProperties=").append(configuredProperties);
        if (componentScheme != null) {
            builder.append(", ");
            builder.append("componentScheme=").append(componentScheme);
        }
        if (connectorGroup != null) {
            builder.append(", ");
            builder.append("connectorGroup=").append(connectorGroup);
        }
        builder.append(", ");
        builder.append("connectorProperties=").append(connectorProperties);
        if (description != null) {
            builder.append(", ");
            builder.append("description=").append(description);
        }
        if (icon != null) {
            builder.append(", ");
            builder.append("icon=").append(icon);
        }
        builder.append(", ");
        builder.append("kind=").append(kind);
        return builder.append("}").toString();
    }

    private Object readResolve() throws ObjectStreamException {
        return validate(this);
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.connection.ConnectorTemplate ConnectorTemplate}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private static final long OPT_BIT_CONFIGURED_PROPERTIES = 0x1L;
        private long optBits;

        private String id;
        private String name;
        private Map<String, ConfigurationProperty> properties =
                new LinkedHashMap<String, ConfigurationProperty>();
        private Map<String, String> configuredProperties =
                new LinkedHashMap<String, String>();
        private String componentScheme;
        private ConnectorGroup connectorGroup;
        private Map<String, ConfigurationProperty> connectorProperties =
                new LinkedHashMap<String, ConfigurationProperty>();
        private String description;
        private String icon;
        private Kind kind;

        /**
         * Creates a builder for {@link io.syndesis.common.model.connection.ConnectorTemplate ConnectorTemplate} instances.
         * <pre>
         * new ConnectorTemplate.Builder()
         *    .id(String) // optional {@link io.syndesis.common.model.connection.ConnectorTemplate#getId() id}
         *    .name(String | null) // nullable {@link io.syndesis.common.model.connection.ConnectorTemplate#getName() name}
         *    .putProperty|putAllProperties(String =&gt; io.syndesis.common.io.syndesis.common.model.connection.ConfigurationProperty) // {@link io.syndesis.common.model.connection.ConnectorTemplate#getProperties() properties} mappings
         *    .putConfiguredProperty|putAllConfiguredProperties(String =&gt; String) // {@link io.syndesis.common.model.connection.ConnectorTemplate#getConfiguredProperties() configuredProperties} mappings
         *    .componentScheme(String | null) // nullable {@link io.syndesis.common.model.connection.ConnectorTemplate#getComponentScheme() componentScheme}
         *    .connectorGroup(io.syndesis.common.io.syndesis.common.model.connection.ConnectorGroup) // optional {@link io.syndesis.common.model.connection.ConnectorTemplate#getConnectorGroup() connectorGroup}
         *    .putConnectorProperty|putAllConnectorProperties(String =&gt; io.syndesis.common.io.syndesis.common.model.connection.ConfigurationProperty) // {@link io.syndesis.common.model.connection.ConnectorTemplate#getConnectorProperties() connectorProperties} mappings
         *    .description(String | null) // nullable {@link io.syndesis.common.model.connection.ConnectorTemplate#getDescription() description}
         *    .icon(String | null) // nullable {@link io.syndesis.common.model.connection.ConnectorTemplate#getIcon() icon}
         *    .kind(io.syndesis.common.io.syndesis.common.model.Kind) // optional {@link io.syndesis.common.model.connection.ConnectorTemplate#getKind() kind}
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof ConnectorTemplate.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new ConnectorTemplate.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithName} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorTemplate.Builder createFrom(WithName instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (ConnectorTemplate.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithConfiguredProperties} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorTemplate.Builder createFrom(
                WithConfiguredProperties instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (ConnectorTemplate.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithKind} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorTemplate.Builder createFrom(WithKind instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (ConnectorTemplate.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.connection.ConnectorTemplate} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorTemplate.Builder createFrom(
                ConnectorTemplate instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (ConnectorTemplate.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithResourceId} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorTemplate.Builder createFrom(
                WithResourceId instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (ConnectorTemplate.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithConfigurationProperties} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorTemplate.Builder createFrom(
                WithConfigurationProperties instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (ConnectorTemplate.Builder) this;
        }

        private void from(Object object) {
            @Var long bits = 0;
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
            if (object instanceof WithKind) {
                WithKind instance = (WithKind) object;
                if ((bits & 0x1L) == 0) {
                    kind(instance.getKind());
                    bits |= 0x1L;
                }
            }
            if (object instanceof ConnectorTemplate) {
                ConnectorTemplate instance = (ConnectorTemplate) object;
                String iconValue = instance.getIcon();
                if (iconValue != null) {
                    icon(iconValue);
                }
                String descriptionValue = instance.getDescription();
                if (descriptionValue != null) {
                    description(descriptionValue);
                }
                putAllConnectorProperties(instance.getConnectorProperties());
                String componentSchemeValue = instance.getComponentScheme();
                if (componentSchemeValue != null) {
                    componentScheme(componentSchemeValue);
                }
                Optional<ConnectorGroup> connectorGroupOptional =
                        instance.getConnectorGroup();
                if (connectorGroupOptional.isPresent()) {
                    connectorGroup(connectorGroupOptional);
                }
                if ((bits & 0x1L) == 0) {
                    kind(instance.getKind());
                    bits |= 0x1L;
                }
            }
            if (object instanceof WithResourceId) {
                WithResourceId instance = (WithResourceId) object;
                Optional<String> idOptional = instance.getId();
                if (idOptional.isPresent()) {
                    id(idOptional);
                }
            }
            if (object instanceof WithConfigurationProperties) {
                WithConfigurationProperties instance =
                        (WithConfigurationProperties) object;
                putAllProperties(instance.getProperties());
            }
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.ConnectorTemplate#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorTemplate.Builder id(String id) {
            this.id = Objects.requireNonNull(id, "id");
            return (ConnectorTemplate.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.ConnectorTemplate#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("id")
        public final ConnectorTemplate.Builder id(Optional<String> id) {
            this.id = id.orElse(null);
            return (ConnectorTemplate.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConnectorTemplate#getName() name} attribute.
         *
         * @param name The value for name (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("name")
        public final ConnectorTemplate.Builder name(String name) {
            this.name = name;
            return (ConnectorTemplate.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.connection.ConnectorTemplate#getProperties() properties} map.
         *
         * @param key   The key in the properties map
         * @param value The associated value in the properties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorTemplate.Builder putProperty(String key,
                                                           ConfigurationProperty value) {
            this.properties.put(
                    Objects.requireNonNull(key, "properties key"),
                    Objects.requireNonNull(value, "properties value"));
            return (ConnectorTemplate.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.connection.ConnectorTemplate#getProperties() properties} map. Nulls are not permitted
         *
         * @param entry The key and value entry
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorTemplate.Builder putProperty(
                Map.Entry<String, ? extends ConfigurationProperty> entry) {
            String k = entry.getKey();
            ConfigurationProperty v = entry.getValue();
            this.properties.put(
                    Objects.requireNonNull(k, "properties key"),
                    Objects.requireNonNull(v, "properties value"));
            return (ConnectorTemplate.Builder) this;
        }

        /**
         * Sets or replaces all mappings from the specified map as entries for the {@link io.syndesis.common.model.connection.ConnectorTemplate#getProperties() properties} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the properties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("properties")
        public final ConnectorTemplate.Builder properties(
                Map<String, ? extends ConfigurationProperty> entries) {
            this.properties.clear();
            return putAllProperties(entries);
        }

        /**
         * Put all mappings from the specified map as entries to {@link io.syndesis.common.model.connection.ConnectorTemplate#getProperties() properties} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the properties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorTemplate.Builder putAllProperties(
                Map<String, ? extends ConfigurationProperty> entries) {
            for (Map.Entry<String, ? extends ConfigurationProperty> e : entries.entrySet()) {
                String k = e.getKey();
                ConfigurationProperty v = e.getValue();
                this.properties.put(
                        Objects.requireNonNull(k, "properties key"),
                        Objects.requireNonNull(v, "properties value"));
            }
            return (ConnectorTemplate.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.connection.ConnectorTemplate#getConfiguredProperties() configuredProperties} map.
         *
         * @param key   The key in the configuredProperties map
         * @param value The associated value in the configuredProperties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorTemplate.Builder putConfiguredProperty(String key,
                                                                     String value) {
            this.configuredProperties.put(
                    Objects.requireNonNull(key, "configuredProperties key"),
                    Objects.requireNonNull(value,
                            "configuredProperties value"));
            optBits |= OPT_BIT_CONFIGURED_PROPERTIES;
            return (ConnectorTemplate.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.connection.ConnectorTemplate#getConfiguredProperties() configuredProperties} map. Nulls are not permitted
         *
         * @param entry The key and value entry
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorTemplate.Builder putConfiguredProperty(
                Map.Entry<String, ? extends String> entry) {
            String k = entry.getKey();
            String v = entry.getValue();
            this.configuredProperties.put(
                    Objects.requireNonNull(k, "configuredProperties key"),
                    Objects.requireNonNull(v, "configuredProperties value"));
            optBits |= OPT_BIT_CONFIGURED_PROPERTIES;
            return (ConnectorTemplate.Builder) this;
        }

        /**
         * Sets or replaces all mappings from the specified map as entries for the {@link io.syndesis.common.model.connection.ConnectorTemplate#getConfiguredProperties() configuredProperties} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the configuredProperties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("configuredProperties")
        public final ConnectorTemplate.Builder configuredProperties(
                Map<String, ? extends String> entries) {
            this.configuredProperties.clear();
            optBits |= OPT_BIT_CONFIGURED_PROPERTIES;
            return putAllConfiguredProperties(entries);
        }

        /**
         * Put all mappings from the specified map as entries to {@link io.syndesis.common.model.connection.ConnectorTemplate#getConfiguredProperties() configuredProperties} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the configuredProperties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorTemplate.Builder putAllConfiguredProperties(
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
            return (ConnectorTemplate.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConnectorTemplate#getComponentScheme() componentScheme} attribute.
         *
         * @param componentScheme The value for componentScheme (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("componentScheme")
        public final ConnectorTemplate.Builder componentScheme(
                String componentScheme) {
            this.componentScheme = componentScheme;
            return (ConnectorTemplate.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.ConnectorTemplate#getConnectorGroup() connectorGroup} to connectorGroup.
         *
         * @param connectorGroup The value for connectorGroup
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorTemplate.Builder connectorGroup(
                ConnectorGroup connectorGroup) {
            this.connectorGroup =
                    Objects.requireNonNull(connectorGroup, "connectorGroup");
            return (ConnectorTemplate.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.ConnectorTemplate#getConnectorGroup() connectorGroup} to connectorGroup.
         *
         * @param connectorGroup The value for connectorGroup
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("connectorGroup")
        public final ConnectorTemplate.Builder connectorGroup(
                Optional<? extends ConnectorGroup> connectorGroup) {
            this.connectorGroup = connectorGroup.orElse(null);
            return (ConnectorTemplate.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.connection.ConnectorTemplate#getConnectorProperties() connectorProperties} map.
         *
         * @param key   The key in the connectorProperties map
         * @param value The associated value in the connectorProperties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorTemplate.Builder putConnectorProperty(String key,
                                                                    ConfigurationProperty value) {
            this.connectorProperties.put(
                    Objects.requireNonNull(key, "connectorProperties key"),
                    Objects.requireNonNull(value, "connectorProperties value"));
            return (ConnectorTemplate.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.connection.ConnectorTemplate#getConnectorProperties() connectorProperties} map. Nulls are not permitted
         *
         * @param entry The key and value entry
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorTemplate.Builder putConnectorProperty(
                Map.Entry<String, ? extends ConfigurationProperty> entry) {
            String k = entry.getKey();
            ConfigurationProperty v = entry.getValue();
            this.connectorProperties.put(
                    Objects.requireNonNull(k, "connectorProperties key"),
                    Objects.requireNonNull(v, "connectorProperties value"));
            return (ConnectorTemplate.Builder) this;
        }

        /**
         * Sets or replaces all mappings from the specified map as entries for the {@link io.syndesis.common.model.connection.ConnectorTemplate#getConnectorProperties() connectorProperties} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the connectorProperties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("connectorProperties")
        public final ConnectorTemplate.Builder connectorProperties(
                Map<String, ? extends ConfigurationProperty> entries) {
            this.connectorProperties.clear();
            return putAllConnectorProperties(entries);
        }

        /**
         * Put all mappings from the specified map as entries to {@link io.syndesis.common.model.connection.ConnectorTemplate#getConnectorProperties() connectorProperties} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the connectorProperties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorTemplate.Builder putAllConnectorProperties(
                Map<String, ? extends ConfigurationProperty> entries) {
            for (Map.Entry<String, ? extends ConfigurationProperty> e : entries.entrySet()) {
                String k = e.getKey();
                ConfigurationProperty v = e.getValue();
                this.connectorProperties.put(
                        Objects.requireNonNull(k, "connectorProperties key"),
                        Objects.requireNonNull(v, "connectorProperties value"));
            }
            return (ConnectorTemplate.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConnectorTemplate#getDescription() description} attribute.
         *
         * @param description The value for description (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("description")
        public final ConnectorTemplate.Builder description(String description) {
            this.description = description;
            return (ConnectorTemplate.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConnectorTemplate#getIcon() icon} attribute.
         *
         * @param icon The value for icon (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("icon")
        public final ConnectorTemplate.Builder icon(String icon) {
            this.icon = icon;
            return (ConnectorTemplate.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConnectorTemplate#getKind() kind} attribute.
         * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link io.syndesis.common.model.connection.ConnectorTemplate#getKind() kind}.</em>
         *
         * @param kind The value for kind
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("kind")
        public final ConnectorTemplate.Builder kind(Kind kind) {
            this.kind = Objects.requireNonNull(kind, "kind");
            return (ConnectorTemplate.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.connection.ConnectorTemplate ConnectorTemplate}.
         *
         * @return An immutable instance of ConnectorTemplate
         * @throws IllegalStateException if any required attributes are missing
         */
        public ConnectorTemplate build() {
            return ImmutableConnectorTemplate.validate(
                    new ImmutableConnectorTemplate(this));
        }

        private boolean configuredPropertiesIsSet() {
            return (optBits & OPT_BIT_CONFIGURED_PROPERTIES) != 0;
        }
    }

    private final class InitShim {
        private byte configuredPropertiesBuildStage = STAGE_UNINITIALIZED;
        private Map<String, String> configuredProperties;
        private byte kindBuildStage = STAGE_UNINITIALIZED;
        private Kind kind;

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

        Kind getKind() {
            if (kindBuildStage == STAGE_INITIALIZING) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (kindBuildStage == STAGE_UNINITIALIZED) {
                kindBuildStage = STAGE_INITIALIZING;
                this.kind = Objects.requireNonNull(getKindInitialize(), "kind");
                kindBuildStage = STAGE_INITIALIZED;
            }
            return this.kind;
        }

        void kind(Kind kind) {
            this.kind = kind;
            kindBuildStage = STAGE_INITIALIZED;
        }

        private String formatInitCycleMessage() {
            List<String> attributes = new ArrayList<>();
            if (configuredPropertiesBuildStage == STAGE_INITIALIZING) {
                attributes.add("configuredProperties");
            }
            if (kindBuildStage == STAGE_INITIALIZING) {
                attributes.add("kind");
            }
            return "Cannot build ConnectorTemplate, attribute initializers form cycle "
                    + attributes;
        }
    }
}
