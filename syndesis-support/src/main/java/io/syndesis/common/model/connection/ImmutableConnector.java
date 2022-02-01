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
import io.syndesis.common.model.Dependency;
import io.syndesis.common.model.Kind;
import io.syndesis.common.model.WithConfigurationProperties;
import io.syndesis.common.model.WithConfiguredProperties;
import io.syndesis.common.model.WithDependencies;
import io.syndesis.common.model.WithKind;
import io.syndesis.common.model.WithMetadata;
import io.syndesis.common.model.WithName;
import io.syndesis.common.model.WithResourceId;
import io.syndesis.common.model.WithTags;
import io.syndesis.common.model.WithVersion;
import io.syndesis.common.model.action.ActionsSummary;
import io.syndesis.common.model.action.ConnectorAction;

import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Immutable implementation of {@link io.syndesis.common.model.connection.Connector}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new Connector.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableConnector.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableConnector implements Connector {
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private static final byte STAGE_INITIALIZED = 1;
    private final String id;
    private final int version;
    private final List<ConnectorAction> actions;
    private final SortedSet<String> tags;
    private final String name;
    private final Map<String, ConfigurationProperty> properties;
    private final Map<String, String> configuredProperties;
    private final List<Dependency> dependencies;
    private final Map<String, String> metadata;
    private final ConnectorGroup connectorGroup;
    private final String connectorGroupId;
    private final String description;
    private final String icon;
    private final Kind kind;
    private final String componentScheme;
    private final String connectorFactory;
    private final List<String> connectorCustomizers;
    private final String exceptionHandler;
    private final ActionsSummary actionsSummary;
    private final Integer uses;
    @SuppressWarnings("Immutable")
    private transient volatile InitShim initShim = new InitShim();

    @SuppressWarnings("unchecked") // safe covariant cast
    private ImmutableConnector(
            Optional<String> id,
            int version,
            Iterable<? extends ConnectorAction> actions,
            Iterable<String> tags,
            String name,
            Map<String, ? extends ConfigurationProperty> properties,
            Map<String, ? extends String> configuredProperties,
            Iterable<? extends Dependency> dependencies,
            Map<String, ? extends String> metadata,
            Optional<? extends ConnectorGroup> connectorGroup,
            Optional<String> connectorGroupId,
            String description,
            String icon,
            Kind kind,
            Optional<String> componentScheme,
            Optional<String> connectorFactory,
            Iterable<String> connectorCustomizers,
            Optional<String> exceptionHandler,
            Optional<? extends ActionsSummary> actionsSummary,
            Optional<Integer> uses) {
        this.id = id.orElse(null);
        this.version = version;
        this.actions = createUnmodifiableList(false,
                createSafeList(actions, true, false));
        this.tags = createUnmodifiableSortedSet(false,
                createSafeList(tags, false, true));
        this.name = name;
        this.properties = createUnmodifiableMap(true, false, properties);
        this.configuredProperties =
                createUnmodifiableMap(true, false, configuredProperties);
        this.dependencies = createUnmodifiableList(false,
                createSafeList(dependencies, true, false));
        this.metadata = createUnmodifiableMap(true, false, metadata);
        this.connectorGroup = connectorGroup.orElse(null);
        this.connectorGroupId = connectorGroupId.orElse(null);
        this.description = description;
        this.icon = icon;
        this.kind = Objects.requireNonNull(kind, "kind");
        this.componentScheme = componentScheme.orElse(null);
        this.connectorFactory = connectorFactory.orElse(null);
        this.connectorCustomizers = createUnmodifiableList(false,
                createSafeList(connectorCustomizers, true, false));
        this.exceptionHandler = exceptionHandler.orElse(null);
        this.actionsSummary = actionsSummary.orElse(null);
        this.uses = uses.isPresent() ? uses.get() : null;
        this.initShim = null;
    }

    private ImmutableConnector(Builder builder) {
        this.id = builder.id;
        this.actions = createUnmodifiableList(true, builder.actions);
        this.tags = createUnmodifiableSortedSet(false,
                createSafeList(builder.tags, false, false));
        this.name = builder.name;
        this.properties =
                createUnmodifiableMap(false, false, builder.properties);
        this.connectorGroup = builder.connectorGroup;
        this.connectorGroupId = builder.connectorGroupId;
        this.description = builder.description;
        this.icon = builder.icon;
        this.componentScheme = builder.componentScheme;
        this.connectorFactory = builder.connectorFactory;
        this.exceptionHandler = builder.exceptionHandler;
        this.actionsSummary = builder.actionsSummary;
        this.uses = builder.uses;
        if (builder.versionIsSet()) {
            initShim.version(builder.version);
        }
        if (builder.configuredPropertiesIsSet()) {
            initShim.configuredProperties(createUnmodifiableMap(false, false,
                    builder.configuredProperties));
        }
        if (builder.dependenciesIsSet()) {
            initShim.dependencies(
                    createUnmodifiableList(true, builder.dependencies));
        }
        if (builder.metadataIsSet()) {
            initShim.metadata(
                    createUnmodifiableMap(false, false, builder.metadata));
        }
        if (builder.kind != null) {
            initShim.kind(builder.kind);
        }
        if (builder.connectorCustomizersIsSet()) {
            initShim.connectorCustomizers(
                    createUnmodifiableList(true, builder.connectorCustomizers));
        }
        this.version = initShim.getVersion();
        this.configuredProperties = initShim.getConfiguredProperties();
        this.dependencies = initShim.getDependencies();
        this.metadata = initShim.getMetadata();
        this.kind = initShim.getKind();
        this.connectorCustomizers = initShim.getConnectorCustomizers();
        this.initShim = null;
    }

    private ImmutableConnector(
            ImmutableConnector original,
            String id,
            int version,
            List<ConnectorAction> actions,
            SortedSet<String> tags,
            String name,
            Map<String, ConfigurationProperty> properties,
            Map<String, String> configuredProperties,
            List<Dependency> dependencies,
            Map<String, String> metadata,
            ConnectorGroup connectorGroup,
            String connectorGroupId,
            String description,
            String icon,
            Kind kind,
            String componentScheme,
            String connectorFactory,
            List<String> connectorCustomizers,
            String exceptionHandler,
            ActionsSummary actionsSummary,
            Integer uses) {
        this.id = id;
        this.version = version;
        this.actions = actions;
        this.tags = tags;
        this.name = name;
        this.properties = properties;
        this.configuredProperties = configuredProperties;
        this.dependencies = dependencies;
        this.metadata = metadata;
        this.connectorGroup = connectorGroup;
        this.connectorGroupId = connectorGroupId;
        this.description = description;
        this.icon = icon;
        this.kind = kind;
        this.componentScheme = componentScheme;
        this.connectorFactory = connectorFactory;
        this.connectorCustomizers = connectorCustomizers;
        this.exceptionHandler = exceptionHandler;
        this.actionsSummary = actionsSummary;
        this.uses = uses;
        this.initShim = null;
    }

    /**
     * Construct a new immutable {@code Connector} instance.
     *
     * @param id                   The value for the {@code id} attribute
     * @param version              The value for the {@code version} attribute
     * @param actions              The value for the {@code actions} attribute
     * @param tags                 The value for the {@code tags} attribute
     * @param name                 The value for the {@code name} attribute
     * @param properties           The value for the {@code properties} attribute
     * @param configuredProperties The value for the {@code configuredProperties} attribute
     * @param dependencies         The value for the {@code dependencies} attribute
     * @param metadata             The value for the {@code metadata} attribute
     * @param connectorGroup       The value for the {@code connectorGroup} attribute
     * @param connectorGroupId     The value for the {@code connectorGroupId} attribute
     * @param description          The value for the {@code description} attribute
     * @param icon                 The value for the {@code icon} attribute
     * @param kind                 The value for the {@code kind} attribute
     * @param componentScheme      The value for the {@code componentScheme} attribute
     * @param connectorFactory     The value for the {@code connectorFactory} attribute
     * @param connectorCustomizers The value for the {@code connectorCustomizers} attribute
     * @param exceptionHandler     The value for the {@code exceptionHandler} attribute
     * @param actionsSummary       The value for the {@code actionsSummary} attribute
     * @param uses                 The value for the {@code uses} attribute
     * @return An immutable Connector instance
     */
    public static Connector of(Optional<String> id, int version,
                               List<ConnectorAction> actions,
                               SortedSet<String> tags, String name,
                               Map<String, ConfigurationProperty> properties,
                               Map<String, String> configuredProperties,
                               List<Dependency> dependencies,
                               Map<String, String> metadata,
                               Optional<ConnectorGroup> connectorGroup,
                               Optional<String> connectorGroupId,
                               String description, String icon, Kind kind,
                               Optional<String> componentScheme,
                               Optional<String> connectorFactory,
                               List<String> connectorCustomizers,
                               Optional<String> exceptionHandler,
                               Optional<ActionsSummary> actionsSummary,
                               Optional<Integer> uses) {
        return of(id, version, (Iterable<? extends ConnectorAction>) actions,
                (Iterable<String>) tags, name, properties, configuredProperties,
                (Iterable<? extends Dependency>) dependencies, metadata,
                connectorGroup, connectorGroupId, description, icon, kind,
                componentScheme, connectorFactory,
                (Iterable<String>) connectorCustomizers, exceptionHandler,
                actionsSummary, uses);
    }

    /**
     * Construct a new immutable {@code Connector} instance.
     *
     * @param id                   The value for the {@code id} attribute
     * @param version              The value for the {@code version} attribute
     * @param actions              The value for the {@code actions} attribute
     * @param tags                 The value for the {@code tags} attribute
     * @param name                 The value for the {@code name} attribute
     * @param properties           The value for the {@code properties} attribute
     * @param configuredProperties The value for the {@code configuredProperties} attribute
     * @param dependencies         The value for the {@code dependencies} attribute
     * @param metadata             The value for the {@code metadata} attribute
     * @param connectorGroup       The value for the {@code connectorGroup} attribute
     * @param connectorGroupId     The value for the {@code connectorGroupId} attribute
     * @param description          The value for the {@code description} attribute
     * @param icon                 The value for the {@code icon} attribute
     * @param kind                 The value for the {@code kind} attribute
     * @param componentScheme      The value for the {@code componentScheme} attribute
     * @param connectorFactory     The value for the {@code connectorFactory} attribute
     * @param connectorCustomizers The value for the {@code connectorCustomizers} attribute
     * @param exceptionHandler     The value for the {@code exceptionHandler} attribute
     * @param actionsSummary       The value for the {@code actionsSummary} attribute
     * @param uses                 The value for the {@code uses} attribute
     * @return An immutable Connector instance
     */
    public static Connector of(Optional<String> id, int version,
                               Iterable<? extends ConnectorAction> actions,
                               Iterable<String> tags, String name,
                               Map<String, ? extends ConfigurationProperty> properties,
                               Map<String, ? extends String> configuredProperties,
                               Iterable<? extends Dependency> dependencies,
                               Map<String, ? extends String> metadata,
                               Optional<? extends ConnectorGroup> connectorGroup,
                               Optional<String> connectorGroupId,
                               String description, String icon, Kind kind,
                               Optional<String> componentScheme,
                               Optional<String> connectorFactory,
                               Iterable<String> connectorCustomizers,
                               Optional<String> exceptionHandler,
                               Optional<? extends ActionsSummary> actionsSummary,
                               Optional<Integer> uses) {
        return validate(new ImmutableConnector(id, version, actions, tags, name,
                properties, configuredProperties, dependencies, metadata,
                connectorGroup, connectorGroupId, description, icon, kind,
                componentScheme, connectorFactory, connectorCustomizers,
                exceptionHandler, actionsSummary, uses));
    }

    private static ImmutableConnector validate(ImmutableConnector instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.connection.Connector} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable Connector instance
     */
    public static Connector copyOf(Connector instance) {
        if (instance instanceof ImmutableConnector) {
            return (ImmutableConnector) instance;
        }
        return new Connector.Builder()
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

    private static <T extends Comparable<T>> NavigableSet<T> createUnmodifiableSortedSet(
            boolean reverse, List<T> list) {
        TreeSet<T> set = reverse
                ? new TreeSet<>(Collections.reverseOrder())
                : new TreeSet<>();
        set.addAll(list);
        return Collections.unmodifiableNavigableSet(set);
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

    private int getVersionInitialize() {
        return Connector.super.getVersion();
    }

    private Map<String, String> getConfiguredPropertiesInitialize() {
        return Connector.super.getConfiguredProperties();
    }

    private List<Dependency> getDependenciesInitialize() {
        return Connector.super.getDependencies();
    }

    private Map<String, String> getMetadataInitialize() {
        return Connector.super.getMetadata();
    }

    private Kind getKindInitialize() {
        return Connector.super.getKind();
    }

    private List<String> getConnectorCustomizersInitialize() {
        return Connector.super.getConnectorCustomizers();
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
     * @return The value of the {@code version} attribute
     */
    @JsonProperty("version")
    @Override
    public int getVersion() {
        InitShim shim = this.initShim;
        return shim != null
                ? shim.getVersion()
                : this.version;
    }

    /**
     * @return The value of the {@code actions} attribute
     */
    @JsonProperty("actions")
    @Override
    public List<ConnectorAction> getActions() {
        return actions;
    }

    /**
     * @return The value of the {@code tags} attribute
     */
    @JsonProperty("tags")
    @Override
    public SortedSet<String> getTags() {
        return tags;
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
     * @return The value of the {@code dependencies} attribute
     */
    @JsonProperty("dependencies")
    @Override
    public List<Dependency> getDependencies() {
        InitShim shim = this.initShim;
        return shim != null
                ? shim.getDependencies()
                : this.dependencies;
    }

    /**
     * @return The value of the {@code metadata} attribute
     */
    @JsonProperty("metadata")
    @Override
    public Map<String, String> getMetadata() {
        InitShim shim = this.initShim;
        return shim != null
                ? shim.getMetadata()
                : this.metadata;
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
     * @return The value of the {@code connectorGroupId} attribute
     */
    @JsonProperty("connectorGroupId")
    @Override
    public Optional<String> getConnectorGroupId() {
        return Optional.ofNullable(connectorGroupId);
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
     * @return The value of the {@code componentScheme} attribute
     */
    @JsonProperty("componentScheme")
    @Override
    public Optional<String> getComponentScheme() {
        return Optional.ofNullable(componentScheme);
    }

    /**
     * @return The value of the {@code connectorFactory} attribute
     */
    @JsonProperty("connectorFactory")
    @Override
    public Optional<String> getConnectorFactory() {
        return Optional.ofNullable(connectorFactory);
    }

    /**
     * @return The value of the {@code connectorCustomizers} attribute
     */
    @JsonProperty("connectorCustomizers")
    @Override
    public List<String> getConnectorCustomizers() {
        InitShim shim = this.initShim;
        return shim != null
                ? shim.getConnectorCustomizers()
                : this.connectorCustomizers;
    }

    /**
     * @return The value of the {@code exceptionHandler} attribute
     */
    @JsonProperty("exceptionHandler")
    @Override
    public Optional<String> getExceptionHandler() {
        return Optional.ofNullable(exceptionHandler);
    }

    /**
     * @return The value of the {@code actionsSummary} attribute
     */
    @JsonProperty("actionsSummary")
    @Override
    public Optional<ActionsSummary> getActionsSummary() {
        return Optional.ofNullable(actionsSummary);
    }

    /**
     * @return The value of the {@code uses} attribute
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Override
    public Optional<Integer> getUses() {
        return Optional.ofNullable(this.uses);
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.connection.Connector#getId() id} attribute.
     *
     * @param value The value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnector withId(String value) {
        String newValue = Objects.requireNonNull(value, "id");
        if (Objects.equals(this.id, newValue)) {
            return this;
        }
        return validate(new ImmutableConnector(
                this,
                newValue,
                this.version,
                this.actions,
                this.tags,
                this.name,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.connectorGroup,
                this.connectorGroupId,
                this.description,
                this.icon,
                this.kind,
                this.componentScheme,
                this.connectorFactory,
                this.connectorCustomizers,
                this.exceptionHandler,
                this.actionsSummary,
                this.uses));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.connection.Connector#getId() id} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnector withId(Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.id, value)) {
            return this;
        }
        return validate(new ImmutableConnector(
                this,
                value,
                this.version,
                this.actions,
                this.tags,
                this.name,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.connectorGroup,
                this.connectorGroupId,
                this.description,
                this.icon,
                this.kind,
                this.componentScheme,
                this.connectorFactory,
                this.connectorCustomizers,
                this.exceptionHandler,
                this.actionsSummary,
                this.uses));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.Connector#getVersion() version} attribute.
     * A value equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for version
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConnector withVersion(int value) {
        if (this.version == value) {
            return this;
        }
        return validate(new ImmutableConnector(
                this,
                this.id,
                value,
                this.actions,
                this.tags,
                this.name,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.connectorGroup,
                this.connectorGroupId,
                this.description,
                this.icon,
                this.kind,
                this.componentScheme,
                this.connectorFactory,
                this.connectorCustomizers,
                this.exceptionHandler,
                this.actionsSummary,
                this.uses));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.connection.Connector#getActions() actions}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnector withActions(ConnectorAction... elements) {
        List<ConnectorAction> newValue = createUnmodifiableList(false,
                createSafeList(Arrays.asList(elements), true, false));
        return validate(new ImmutableConnector(
                this,
                this.id,
                this.version,
                newValue,
                this.tags,
                this.name,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.connectorGroup,
                this.connectorGroupId,
                this.description,
                this.icon,
                this.kind,
                this.componentScheme,
                this.connectorFactory,
                this.connectorCustomizers,
                this.exceptionHandler,
                this.actionsSummary,
                this.uses));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.connection.Connector#getActions() actions}.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of actions elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnector withActions(
            Iterable<? extends ConnectorAction> elements) {
        if (this.actions == elements) {
            return this;
        }
        List<ConnectorAction> newValue = createUnmodifiableList(false,
                createSafeList(elements, true, false));
        return validate(new ImmutableConnector(
                this,
                this.id,
                this.version,
                newValue,
                this.tags,
                this.name,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.connectorGroup,
                this.connectorGroupId,
                this.description,
                this.icon,
                this.kind,
                this.componentScheme,
                this.connectorFactory,
                this.connectorCustomizers,
                this.exceptionHandler,
                this.actionsSummary,
                this.uses));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.connection.Connector#getTags() tags}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnector withTags(String... elements) {
        SortedSet<String> newValue = createUnmodifiableSortedSet(false,
                createSafeList(Arrays.asList(elements), false, true));
        return validate(new ImmutableConnector(
                this,
                this.id,
                this.version,
                this.actions,
                newValue,
                this.name,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.connectorGroup,
                this.connectorGroupId,
                this.description,
                this.icon,
                this.kind,
                this.componentScheme,
                this.connectorFactory,
                this.connectorCustomizers,
                this.exceptionHandler,
                this.actionsSummary,
                this.uses));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.connection.Connector#getTags() tags}.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of tags elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnector withTags(Iterable<String> elements) {
        if (this.tags == elements) {
            return this;
        }
        SortedSet<String> newValue = createUnmodifiableSortedSet(false,
                createSafeList(elements, false, true));
        return validate(new ImmutableConnector(
                this,
                this.id,
                this.version,
                this.actions,
                newValue,
                this.name,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.connectorGroup,
                this.connectorGroupId,
                this.description,
                this.icon,
                this.kind,
                this.componentScheme,
                this.connectorFactory,
                this.connectorCustomizers,
                this.exceptionHandler,
                this.actionsSummary,
                this.uses));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.Connector#getName() name} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for name (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConnector withName(String value) {
        if (Objects.equals(this.name, value)) {
            return this;
        }
        return validate(new ImmutableConnector(
                this,
                this.id,
                this.version,
                this.actions,
                this.tags,
                value,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.connectorGroup,
                this.connectorGroupId,
                this.description,
                this.icon,
                this.kind,
                this.componentScheme,
                this.connectorFactory,
                this.connectorCustomizers,
                this.exceptionHandler,
                this.actionsSummary,
                this.uses));
    }

    /**
     * Copy the current immutable object by replacing the {@link io.syndesis.common.model.connection.Connector#getProperties() properties} map with the specified map.
     * Nulls are not permitted as keys or values.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param entries The entries to be added to the properties map
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnector withProperties(
            Map<String, ? extends ConfigurationProperty> entries) {
        if (this.properties == entries) {
            return this;
        }
        Map<String, ConfigurationProperty> newValue =
                createUnmodifiableMap(true, false, entries);
        return validate(new ImmutableConnector(
                this,
                this.id,
                this.version,
                this.actions,
                this.tags,
                this.name,
                newValue,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.connectorGroup,
                this.connectorGroupId,
                this.description,
                this.icon,
                this.kind,
                this.componentScheme,
                this.connectorFactory,
                this.connectorCustomizers,
                this.exceptionHandler,
                this.actionsSummary,
                this.uses));
    }

    /**
     * Copy the current immutable object by replacing the {@link io.syndesis.common.model.connection.Connector#getConfiguredProperties() configuredProperties} map with the specified map.
     * Nulls are not permitted as keys or values.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param entries The entries to be added to the configuredProperties map
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnector withConfiguredProperties(
            Map<String, ? extends String> entries) {
        if (this.configuredProperties == entries) {
            return this;
        }
        Map<String, String> newValue =
                createUnmodifiableMap(true, false, entries);
        return validate(new ImmutableConnector(
                this,
                this.id,
                this.version,
                this.actions,
                this.tags,
                this.name,
                this.properties,
                newValue,
                this.dependencies,
                this.metadata,
                this.connectorGroup,
                this.connectorGroupId,
                this.description,
                this.icon,
                this.kind,
                this.componentScheme,
                this.connectorFactory,
                this.connectorCustomizers,
                this.exceptionHandler,
                this.actionsSummary,
                this.uses));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.connection.Connector#getDependencies() dependencies}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnector withDependencies(Dependency... elements) {
        List<Dependency> newValue = createUnmodifiableList(false,
                createSafeList(Arrays.asList(elements), true, false));
        return validate(new ImmutableConnector(
                this,
                this.id,
                this.version,
                this.actions,
                this.tags,
                this.name,
                this.properties,
                this.configuredProperties,
                newValue,
                this.metadata,
                this.connectorGroup,
                this.connectorGroupId,
                this.description,
                this.icon,
                this.kind,
                this.componentScheme,
                this.connectorFactory,
                this.connectorCustomizers,
                this.exceptionHandler,
                this.actionsSummary,
                this.uses));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.connection.Connector#getDependencies() dependencies}.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of dependencies elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnector withDependencies(
            Iterable<? extends Dependency> elements) {
        if (this.dependencies == elements) {
            return this;
        }
        List<Dependency> newValue = createUnmodifiableList(false,
                createSafeList(elements, true, false));
        return validate(new ImmutableConnector(
                this,
                this.id,
                this.version,
                this.actions,
                this.tags,
                this.name,
                this.properties,
                this.configuredProperties,
                newValue,
                this.metadata,
                this.connectorGroup,
                this.connectorGroupId,
                this.description,
                this.icon,
                this.kind,
                this.componentScheme,
                this.connectorFactory,
                this.connectorCustomizers,
                this.exceptionHandler,
                this.actionsSummary,
                this.uses));
    }

    /**
     * Copy the current immutable object by replacing the {@link io.syndesis.common.model.connection.Connector#getMetadata() metadata} map with the specified map.
     * Nulls are not permitted as keys or values.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param entries The entries to be added to the metadata map
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnector withMetadata(
            Map<String, ? extends String> entries) {
        if (this.metadata == entries) {
            return this;
        }
        Map<String, String> newValue =
                createUnmodifiableMap(true, false, entries);
        return validate(new ImmutableConnector(
                this,
                this.id,
                this.version,
                this.actions,
                this.tags,
                this.name,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                newValue,
                this.connectorGroup,
                this.connectorGroupId,
                this.description,
                this.icon,
                this.kind,
                this.componentScheme,
                this.connectorFactory,
                this.connectorCustomizers,
                this.exceptionHandler,
                this.actionsSummary,
                this.uses));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.connection.Connector#getConnectorGroup() connectorGroup} attribute.
     *
     * @param value The value for connectorGroup
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnector withConnectorGroup(ConnectorGroup value) {
        ConnectorGroup newValue =
                Objects.requireNonNull(value, "connectorGroup");
        if (this.connectorGroup == newValue) {
            return this;
        }
        return validate(new ImmutableConnector(
                this,
                this.id,
                this.version,
                this.actions,
                this.tags,
                this.name,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                newValue,
                this.connectorGroupId,
                this.description,
                this.icon,
                this.kind,
                this.componentScheme,
                this.connectorFactory,
                this.connectorCustomizers,
                this.exceptionHandler,
                this.actionsSummary,
                this.uses));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.connection.Connector#getConnectorGroup() connectorGroup} attribute.
     * A shallow reference equality check is used on unboxed optional value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for connectorGroup
     * @return A modified copy of {@code this} object
     */
    @SuppressWarnings("unchecked") // safe covariant cast
    public final ImmutableConnector withConnectorGroup(
            Optional<? extends ConnectorGroup> optional) {
        ConnectorGroup value = optional.orElse(null);
        if (this.connectorGroup == value) {
            return this;
        }
        return validate(new ImmutableConnector(
                this,
                this.id,
                this.version,
                this.actions,
                this.tags,
                this.name,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                value,
                this.connectorGroupId,
                this.description,
                this.icon,
                this.kind,
                this.componentScheme,
                this.connectorFactory,
                this.connectorCustomizers,
                this.exceptionHandler,
                this.actionsSummary,
                this.uses));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.connection.Connector#getConnectorGroupId() connectorGroupId} attribute.
     *
     * @param value The value for connectorGroupId
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnector withConnectorGroupId(String value) {
        String newValue = Objects.requireNonNull(value, "connectorGroupId");
        if (Objects.equals(this.connectorGroupId, newValue)) {
            return this;
        }
        return validate(new ImmutableConnector(
                this,
                this.id,
                this.version,
                this.actions,
                this.tags,
                this.name,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.connectorGroup,
                newValue,
                this.description,
                this.icon,
                this.kind,
                this.componentScheme,
                this.connectorFactory,
                this.connectorCustomizers,
                this.exceptionHandler,
                this.actionsSummary,
                this.uses));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.connection.Connector#getConnectorGroupId() connectorGroupId} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for connectorGroupId
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnector withConnectorGroupId(
            Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.connectorGroupId, value)) {
            return this;
        }
        return validate(new ImmutableConnector(
                this,
                this.id,
                this.version,
                this.actions,
                this.tags,
                this.name,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.connectorGroup,
                value,
                this.description,
                this.icon,
                this.kind,
                this.componentScheme,
                this.connectorFactory,
                this.connectorCustomizers,
                this.exceptionHandler,
                this.actionsSummary,
                this.uses));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.Connector#getDescription() description} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for description (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConnector withDescription(String value) {
        if (Objects.equals(this.description, value)) {
            return this;
        }
        return validate(new ImmutableConnector(
                this,
                this.id,
                this.version,
                this.actions,
                this.tags,
                this.name,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.connectorGroup,
                this.connectorGroupId,
                value,
                this.icon,
                this.kind,
                this.componentScheme,
                this.connectorFactory,
                this.connectorCustomizers,
                this.exceptionHandler,
                this.actionsSummary,
                this.uses));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.Connector#getIcon() icon} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for icon (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConnector withIcon(String value) {
        if (Objects.equals(this.icon, value)) {
            return this;
        }
        return validate(new ImmutableConnector(
                this,
                this.id,
                this.version,
                this.actions,
                this.tags,
                this.name,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.connectorGroup,
                this.connectorGroupId,
                this.description,
                value,
                this.kind,
                this.componentScheme,
                this.connectorFactory,
                this.connectorCustomizers,
                this.exceptionHandler,
                this.actionsSummary,
                this.uses));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.Connector#getKind() kind} attribute.
     * A value equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for kind
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConnector withKind(Kind value) {
        if (this.kind == value) {
            return this;
        }
        Kind newValue = Objects.requireNonNull(value, "kind");
        if (this.kind.equals(newValue)) {
            return this;
        }
        return validate(new ImmutableConnector(
                this,
                this.id,
                this.version,
                this.actions,
                this.tags,
                this.name,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.connectorGroup,
                this.connectorGroupId,
                this.description,
                this.icon,
                newValue,
                this.componentScheme,
                this.connectorFactory,
                this.connectorCustomizers,
                this.exceptionHandler,
                this.actionsSummary,
                this.uses));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.connection.Connector#getComponentScheme() componentScheme} attribute.
     *
     * @param value The value for componentScheme
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnector withComponentScheme(String value) {
        String newValue = Objects.requireNonNull(value, "componentScheme");
        if (Objects.equals(this.componentScheme, newValue)) {
            return this;
        }
        return validate(new ImmutableConnector(
                this,
                this.id,
                this.version,
                this.actions,
                this.tags,
                this.name,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.connectorGroup,
                this.connectorGroupId,
                this.description,
                this.icon,
                this.kind,
                newValue,
                this.connectorFactory,
                this.connectorCustomizers,
                this.exceptionHandler,
                this.actionsSummary,
                this.uses));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.connection.Connector#getComponentScheme() componentScheme} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for componentScheme
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnector withComponentScheme(
            Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.componentScheme, value)) {
            return this;
        }
        return validate(new ImmutableConnector(
                this,
                this.id,
                this.version,
                this.actions,
                this.tags,
                this.name,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.connectorGroup,
                this.connectorGroupId,
                this.description,
                this.icon,
                this.kind,
                value,
                this.connectorFactory,
                this.connectorCustomizers,
                this.exceptionHandler,
                this.actionsSummary,
                this.uses));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.connection.Connector#getConnectorFactory() connectorFactory} attribute.
     *
     * @param value The value for connectorFactory
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnector withConnectorFactory(String value) {
        String newValue = Objects.requireNonNull(value, "connectorFactory");
        if (Objects.equals(this.connectorFactory, newValue)) {
            return this;
        }
        return validate(new ImmutableConnector(
                this,
                this.id,
                this.version,
                this.actions,
                this.tags,
                this.name,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.connectorGroup,
                this.connectorGroupId,
                this.description,
                this.icon,
                this.kind,
                this.componentScheme,
                newValue,
                this.connectorCustomizers,
                this.exceptionHandler,
                this.actionsSummary,
                this.uses));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.connection.Connector#getConnectorFactory() connectorFactory} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for connectorFactory
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnector withConnectorFactory(
            Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.connectorFactory, value)) {
            return this;
        }
        return validate(new ImmutableConnector(
                this,
                this.id,
                this.version,
                this.actions,
                this.tags,
                this.name,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.connectorGroup,
                this.connectorGroupId,
                this.description,
                this.icon,
                this.kind,
                this.componentScheme,
                value,
                this.connectorCustomizers,
                this.exceptionHandler,
                this.actionsSummary,
                this.uses));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.connection.Connector#getConnectorCustomizers() connectorCustomizers}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnector withConnectorCustomizers(
            String... elements) {
        List<String> newValue = createUnmodifiableList(false,
                createSafeList(Arrays.asList(elements), true, false));
        return validate(new ImmutableConnector(
                this,
                this.id,
                this.version,
                this.actions,
                this.tags,
                this.name,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.connectorGroup,
                this.connectorGroupId,
                this.description,
                this.icon,
                this.kind,
                this.componentScheme,
                this.connectorFactory,
                newValue,
                this.exceptionHandler,
                this.actionsSummary,
                this.uses));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.connection.Connector#getConnectorCustomizers() connectorCustomizers}.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of connectorCustomizers elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnector withConnectorCustomizers(
            Iterable<String> elements) {
        if (this.connectorCustomizers == elements) {
            return this;
        }
        List<String> newValue = createUnmodifiableList(false,
                createSafeList(elements, true, false));
        return validate(new ImmutableConnector(
                this,
                this.id,
                this.version,
                this.actions,
                this.tags,
                this.name,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.connectorGroup,
                this.connectorGroupId,
                this.description,
                this.icon,
                this.kind,
                this.componentScheme,
                this.connectorFactory,
                newValue,
                this.exceptionHandler,
                this.actionsSummary,
                this.uses));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.connection.Connector#getExceptionHandler() exceptionHandler} attribute.
     *
     * @param value The value for exceptionHandler
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnector withExceptionHandler(String value) {
        String newValue = Objects.requireNonNull(value, "exceptionHandler");
        if (Objects.equals(this.exceptionHandler, newValue)) {
            return this;
        }
        return validate(new ImmutableConnector(
                this,
                this.id,
                this.version,
                this.actions,
                this.tags,
                this.name,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.connectorGroup,
                this.connectorGroupId,
                this.description,
                this.icon,
                this.kind,
                this.componentScheme,
                this.connectorFactory,
                this.connectorCustomizers,
                newValue,
                this.actionsSummary,
                this.uses));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.connection.Connector#getExceptionHandler() exceptionHandler} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for exceptionHandler
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnector withExceptionHandler(
            Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.exceptionHandler, value)) {
            return this;
        }
        return validate(new ImmutableConnector(
                this,
                this.id,
                this.version,
                this.actions,
                this.tags,
                this.name,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.connectorGroup,
                this.connectorGroupId,
                this.description,
                this.icon,
                this.kind,
                this.componentScheme,
                this.connectorFactory,
                this.connectorCustomizers,
                value,
                this.actionsSummary,
                this.uses));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.connection.Connector#getActionsSummary() actionsSummary} attribute.
     *
     * @param value The value for actionsSummary
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnector withActionsSummary(ActionsSummary value) {
        ActionsSummary newValue =
                Objects.requireNonNull(value, "actionsSummary");
        if (this.actionsSummary == newValue) {
            return this;
        }
        return validate(new ImmutableConnector(
                this,
                this.id,
                this.version,
                this.actions,
                this.tags,
                this.name,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.connectorGroup,
                this.connectorGroupId,
                this.description,
                this.icon,
                this.kind,
                this.componentScheme,
                this.connectorFactory,
                this.connectorCustomizers,
                this.exceptionHandler,
                newValue,
                this.uses));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.connection.Connector#getActionsSummary() actionsSummary} attribute.
     * A shallow reference equality check is used on unboxed optional value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for actionsSummary
     * @return A modified copy of {@code this} object
     */
    @SuppressWarnings("unchecked") // safe covariant cast
    public final ImmutableConnector withActionsSummary(
            Optional<? extends ActionsSummary> optional) {
        ActionsSummary value = optional.orElse(null);
        if (this.actionsSummary == value) {
            return this;
        }
        return validate(new ImmutableConnector(
                this,
                this.id,
                this.version,
                this.actions,
                this.tags,
                this.name,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.connectorGroup,
                this.connectorGroupId,
                this.description,
                this.icon,
                this.kind,
                this.componentScheme,
                this.connectorFactory,
                this.connectorCustomizers,
                this.exceptionHandler,
                value,
                this.uses));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.connection.Connector#getUses() uses} attribute.
     *
     * @param value The value for uses
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnector withUses(int value) {
        Integer newValue = value;
        if (Objects.equals(this.uses, newValue)) {
            return this;
        }
        return validate(new ImmutableConnector(
                this,
                this.id,
                this.version,
                this.actions,
                this.tags,
                this.name,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.connectorGroup,
                this.connectorGroupId,
                this.description,
                this.icon,
                this.kind,
                this.componentScheme,
                this.connectorFactory,
                this.connectorCustomizers,
                this.exceptionHandler,
                this.actionsSummary,
                newValue));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.connection.Connector#getUses() uses} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for uses
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnector withUses(Optional<Integer> optional) {
        Integer value = optional.isPresent() ? optional.get() : null;
        if (Objects.equals(this.uses, value)) {
            return this;
        }
        return validate(new ImmutableConnector(
                this,
                this.id,
                this.version,
                this.actions,
                this.tags,
                this.name,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.connectorGroup,
                this.connectorGroupId,
                this.description,
                this.icon,
                this.kind,
                this.componentScheme,
                this.connectorFactory,
                this.connectorCustomizers,
                this.exceptionHandler,
                this.actionsSummary,
                value));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableConnector} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableConnector
                && equalTo((ImmutableConnector) another);
    }

    private boolean equalTo(ImmutableConnector another) {
        return Objects.equals(id, another.id)
                && version == another.version
                && actions.equals(another.actions)
                && tags.equals(another.tags)
                && Objects.equals(name, another.name)
                && properties.equals(another.properties)
                && configuredProperties.equals(another.configuredProperties)
                && dependencies.equals(another.dependencies)
                && metadata.equals(another.metadata)
                && Objects.equals(connectorGroup, another.connectorGroup)
                && Objects.equals(connectorGroupId, another.connectorGroupId)
                && Objects.equals(description, another.description)
                && Objects.equals(icon, another.icon)
                && kind.equals(another.kind)
                && Objects.equals(componentScheme, another.componentScheme)
                && Objects.equals(connectorFactory, another.connectorFactory)
                && connectorCustomizers.equals(another.connectorCustomizers)
                && Objects.equals(exceptionHandler, another.exceptionHandler);
    }

    /**
     * Computes a hash code from attributes: {@code id}, {@code version}, {@code actions}, {@code tags}, {@code name}, {@code properties}, {@code configuredProperties}, {@code dependencies}, {@code metadata}, {@code connectorGroup}, {@code connectorGroupId}, {@code description}, {@code icon}, {@code kind}, {@code componentScheme}, {@code connectorFactory}, {@code connectorCustomizers}, {@code exceptionHandler}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(id);
        h += (h << 5) + version;
        h += (h << 5) + actions.hashCode();
        h += (h << 5) + tags.hashCode();
        h += (h << 5) + Objects.hashCode(name);
        h += (h << 5) + properties.hashCode();
        h += (h << 5) + configuredProperties.hashCode();
        h += (h << 5) + dependencies.hashCode();
        h += (h << 5) + metadata.hashCode();
        h += (h << 5) + Objects.hashCode(connectorGroup);
        h += (h << 5) + Objects.hashCode(connectorGroupId);
        h += (h << 5) + Objects.hashCode(description);
        h += (h << 5) + Objects.hashCode(icon);
        h += (h << 5) + kind.hashCode();
        h += (h << 5) + Objects.hashCode(componentScheme);
        h += (h << 5) + Objects.hashCode(connectorFactory);
        h += (h << 5) + connectorCustomizers.hashCode();
        h += (h << 5) + Objects.hashCode(exceptionHandler);
        return h;
    }

    /**
     * Prints the immutable value {@code Connector} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Connector{");
        if (id != null) {
            builder.append("id=").append(id);
        }
        if (builder.length() > 10) {
            builder.append(", ");
        }
        builder.append("version=").append(version);
        builder.append(", ");
        builder.append("actions=").append(actions);
        builder.append(", ");
        builder.append("tags=").append(tags);
        if (name != null) {
            builder.append(", ");
            builder.append("name=").append(name);
        }
        builder.append(", ");
        builder.append("properties=").append(properties);
        builder.append(", ");
        builder.append("configuredProperties=").append(configuredProperties);
        builder.append(", ");
        builder.append("dependencies=").append(dependencies);
        builder.append(", ");
        builder.append("metadata=").append(metadata);
        if (connectorGroup != null) {
            builder.append(", ");
            builder.append("connectorGroup=").append(connectorGroup);
        }
        if (connectorGroupId != null) {
            builder.append(", ");
            builder.append("connectorGroupId=").append(connectorGroupId);
        }
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
        if (componentScheme != null) {
            builder.append(", ");
            builder.append("componentScheme=").append(componentScheme);
        }
        if (connectorFactory != null) {
            builder.append(", ");
            builder.append("connectorFactory=").append(connectorFactory);
        }
        builder.append(", ");
        builder.append("connectorCustomizers=").append(connectorCustomizers);
        if (exceptionHandler != null) {
            builder.append(", ");
            builder.append("exceptionHandler=").append(exceptionHandler);
        }
        return builder.append("}").toString();
    }

    private Object readResolve() throws ObjectStreamException {
        return validate(this);
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.connection.Connector Connector}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private static final long OPT_BIT_VERSION = 0x1L;
        private static final long OPT_BIT_CONFIGURED_PROPERTIES = 0x2L;
        private static final long OPT_BIT_DEPENDENCIES = 0x4L;
        private static final long OPT_BIT_METADATA = 0x8L;
        private static final long OPT_BIT_CONNECTOR_CUSTOMIZERS = 0x10L;
        private long optBits;

        private String id;
        private int version;
        private List<ConnectorAction> actions =
                new ArrayList<ConnectorAction>();
        private List<String> tags = new ArrayList<String>();
        private String name;
        private Map<String, ConfigurationProperty> properties =
                new LinkedHashMap<String, ConfigurationProperty>();
        private Map<String, String> configuredProperties =
                new LinkedHashMap<String, String>();
        private List<Dependency> dependencies = new ArrayList<Dependency>();
        private Map<String, String> metadata =
                new LinkedHashMap<String, String>();
        private ConnectorGroup connectorGroup;
        private String connectorGroupId;
        private String description;
        private String icon;
        private Kind kind;
        private String componentScheme;
        private String connectorFactory;
        private List<String> connectorCustomizers = new ArrayList<String>();
        private String exceptionHandler;
        private ActionsSummary actionsSummary;
        private Integer uses;

        /**
         * Creates a builder for {@link io.syndesis.common.model.connection.Connector Connector} instances.
         * <pre>
         * new Connector.Builder()
         *    .id(String) // optional {@link io.syndesis.common.model.connection.Connector#getId() id}
         *    .version(int) // optional {@link io.syndesis.common.model.connection.Connector#getVersion() version}
         *    .addAction|addAllActions(io.syndesis.common.io.syndesis.common.model.action.ConnectorAction) // {@link io.syndesis.common.model.connection.Connector#getActions() actions} elements
         *    .addTag|addAllTags(String) // {@link io.syndesis.common.model.connection.Connector#getTags() tags} elements
         *    .name(String | null) // nullable {@link io.syndesis.common.model.connection.Connector#getName() name}
         *    .putProperty|putAllProperties(String =&gt; io.syndesis.common.io.syndesis.common.model.connection.ConfigurationProperty) // {@link io.syndesis.common.model.connection.Connector#getProperties() properties} mappings
         *    .putConfiguredProperty|putAllConfiguredProperties(String =&gt; String) // {@link io.syndesis.common.model.connection.Connector#getConfiguredProperties() configuredProperties} mappings
         *    .addDependency|addAllDependencies(io.syndesis.common.io.syndesis.common.model.Dependency) // {@link io.syndesis.common.model.connection.Connector#getDependencies() dependencies} elements
         *    .putMetadata|putAllMetadata(String =&gt; String) // {@link io.syndesis.common.model.connection.Connector#getMetadata() metadata} mappings
         *    .connectorGroup(io.syndesis.common.io.syndesis.common.model.connection.ConnectorGroup) // optional {@link io.syndesis.common.model.connection.Connector#getConnectorGroup() connectorGroup}
         *    .connectorGroupId(String) // optional {@link io.syndesis.common.model.connection.Connector#getConnectorGroupId() connectorGroupId}
         *    .description(String | null) // nullable {@link io.syndesis.common.model.connection.Connector#getDescription() description}
         *    .icon(String | null) // nullable {@link io.syndesis.common.model.connection.Connector#getIcon() icon}
         *    .kind(io.syndesis.common.io.syndesis.common.model.Kind) // optional {@link io.syndesis.common.model.connection.Connector#getKind() kind}
         *    .componentScheme(String) // optional {@link io.syndesis.common.model.connection.Connector#getComponentScheme() componentScheme}
         *    .connectorFactory(String) // optional {@link io.syndesis.common.model.connection.Connector#getConnectorFactory() connectorFactory}
         *    .addConnectorCustomizer|addAllConnectorCustomizers(String) // {@link io.syndesis.common.model.connection.Connector#getConnectorCustomizers() connectorCustomizers} elements
         *    .exceptionHandler(String) // optional {@link io.syndesis.common.model.connection.Connector#getExceptionHandler() exceptionHandler}
         *    .actionsSummary(io.syndesis.common.io.syndesis.common.model.action.ActionsSummary) // optional {@link io.syndesis.common.model.connection.Connector#getActionsSummary() actionsSummary}
         *    .uses(int) // optional {@link io.syndesis.common.model.connection.Connector#getUses() uses}
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof Connector.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new Connector.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithName} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Connector.Builder createFrom(WithName instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Connector.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithConfiguredProperties} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Connector.Builder createFrom(
                WithConfiguredProperties instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Connector.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithKind} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Connector.Builder createFrom(WithKind instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Connector.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithVersion} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Connector.Builder createFrom(WithVersion instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Connector.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.connection.Connector} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Connector.Builder createFrom(Connector instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Connector.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithDependencies} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Connector.Builder createFrom(WithDependencies instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Connector.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithMetadata} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Connector.Builder createFrom(WithMetadata instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Connector.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithTags} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Connector.Builder createFrom(WithTags instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Connector.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithResourceId} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Connector.Builder createFrom(WithResourceId instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Connector.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithConfigurationProperties} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Connector.Builder createFrom(
                WithConfigurationProperties instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Connector.Builder) this;
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
            if (object instanceof WithVersion) {
                WithVersion instance = (WithVersion) object;
                version(instance.getVersion());
            }
            if (object instanceof Connector) {
                Connector instance = (Connector) object;
                Optional<ConnectorGroup> connectorGroupOptional =
                        instance.getConnectorGroup();
                if (connectorGroupOptional.isPresent()) {
                    connectorGroup(connectorGroupOptional);
                }
                Optional<String> connectorFactoryOptional =
                        instance.getConnectorFactory();
                if (connectorFactoryOptional.isPresent()) {
                    connectorFactory(connectorFactoryOptional);
                }
                if ((bits & 0x1L) == 0) {
                    kind(instance.getKind());
                    bits |= 0x1L;
                }
                Optional<String> connectorGroupIdOptional =
                        instance.getConnectorGroupId();
                if (connectorGroupIdOptional.isPresent()) {
                    connectorGroupId(connectorGroupIdOptional);
                }
                String iconValue = instance.getIcon();
                if (iconValue != null) {
                    icon(iconValue);
                }
                String descriptionValue = instance.getDescription();
                if (descriptionValue != null) {
                    description(descriptionValue);
                }
                Optional<String> componentSchemeOptional =
                        instance.getComponentScheme();
                if (componentSchemeOptional.isPresent()) {
                    componentScheme(componentSchemeOptional);
                }
                Optional<Integer> usesOptional = instance.getUses();
                if (usesOptional.isPresent()) {
                    uses(usesOptional);
                }
                addAllConnectorCustomizers(instance.getConnectorCustomizers());
                addAllActions(instance.getActions());
                Optional<String> exceptionHandlerOptional =
                        instance.getExceptionHandler();
                if (exceptionHandlerOptional.isPresent()) {
                    exceptionHandler(exceptionHandlerOptional);
                }
                Optional<ActionsSummary> actionsSummaryOptional =
                        instance.getActionsSummary();
                if (actionsSummaryOptional.isPresent()) {
                    actionsSummary(actionsSummaryOptional);
                }
            }
            if (object instanceof WithDependencies) {
                WithDependencies instance = (WithDependencies) object;
                addAllDependencies(instance.getDependencies());
            }
            if (object instanceof WithMetadata) {
                WithMetadata instance = (WithMetadata) object;
                putAllMetadata(instance.getMetadata());
            }
            if (object instanceof WithTags) {
                WithTags instance = (WithTags) object;
                addAllTags(instance.getTags());
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
         * Initializes the optional value {@link io.syndesis.common.model.connection.Connector#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final Connector.Builder id(String id) {
            this.id = Objects.requireNonNull(id, "id");
            return (Connector.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.Connector#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("id")
        public final Connector.Builder id(Optional<String> id) {
            this.id = id.orElse(null);
            return (Connector.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.Connector#getVersion() version} attribute.
         * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link io.syndesis.common.model.connection.Connector#getVersion() version}.</em>
         *
         * @param version The value for version
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("version")
        public final Connector.Builder version(int version) {
            this.version = version;
            optBits |= OPT_BIT_VERSION;
            return (Connector.Builder) this;
        }

        /**
         * Adds one element to {@link io.syndesis.common.model.connection.Connector#getActions() actions} list.
         *
         * @param element A actions element
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Connector.Builder addAction(ConnectorAction element) {
            this.actions.add(
                    Objects.requireNonNull(element, "actions element"));
            return (Connector.Builder) this;
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.connection.Connector#getActions() actions} list.
         *
         * @param elements An array of actions elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Connector.Builder addActions(ConnectorAction... elements) {
            for (ConnectorAction element : elements) {
                this.actions.add(
                        Objects.requireNonNull(element, "actions element"));
            }
            return (Connector.Builder) this;
        }


        /**
         * Sets or replaces all elements for {@link io.syndesis.common.model.connection.Connector#getActions() actions} list.
         *
         * @param elements An iterable of actions elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("actions")
        public final Connector.Builder actions(
                Iterable<? extends ConnectorAction> elements) {
            this.actions.clear();
            return addAllActions(elements);
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.connection.Connector#getActions() actions} list.
         *
         * @param elements An iterable of actions elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Connector.Builder addAllActions(
                Iterable<? extends ConnectorAction> elements) {
            for (ConnectorAction element : elements) {
                this.actions.add(
                        Objects.requireNonNull(element, "actions element"));
            }
            return (Connector.Builder) this;
        }

        /**
         * Adds one element to {@link io.syndesis.common.model.connection.Connector#getTags() tags} sortedSet.
         *
         * @param element A tags element
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Connector.Builder addTag(String element) {
            if (element != null) {
                this.tags.add(element);
            }
            return (Connector.Builder) this;
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.connection.Connector#getTags() tags} sortedSet.
         *
         * @param elements An array of tags elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Connector.Builder addTags(String... elements) {
            for (String element : elements) {
                if (element != null) {
                    this.tags.add(element);
                }
            }
            return (Connector.Builder) this;
        }


        /**
         * Sets or replaces all elements for {@link io.syndesis.common.model.connection.Connector#getTags() tags} sortedSet.
         *
         * @param elements An iterable of tags elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("tags")
        public final Connector.Builder tags(Iterable<String> elements) {
            this.tags.clear();
            return addAllTags(elements);
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.connection.Connector#getTags() tags} sortedSet.
         *
         * @param elements An iterable of tags elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Connector.Builder addAllTags(Iterable<String> elements) {
            for (String element : elements) {
                if (element != null) {
                    this.tags.add(element);
                }
            }
            return (Connector.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.Connector#getName() name} attribute.
         *
         * @param name The value for name (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("name")
        public final Connector.Builder name(String name) {
            this.name = name;
            return (Connector.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.connection.Connector#getProperties() properties} map.
         *
         * @param key   The key in the properties map
         * @param value The associated value in the properties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Connector.Builder putProperty(String key,
                                                   ConfigurationProperty value) {
            this.properties.put(
                    Objects.requireNonNull(key, "properties key"),
                    Objects.requireNonNull(value, "properties value"));
            return (Connector.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.connection.Connector#getProperties() properties} map. Nulls are not permitted
         *
         * @param entry The key and value entry
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Connector.Builder putProperty(
                Map.Entry<String, ? extends ConfigurationProperty> entry) {
            String k = entry.getKey();
            ConfigurationProperty v = entry.getValue();
            this.properties.put(
                    Objects.requireNonNull(k, "properties key"),
                    Objects.requireNonNull(v, "properties value"));
            return (Connector.Builder) this;
        }

        /**
         * Sets or replaces all mappings from the specified map as entries for the {@link io.syndesis.common.model.connection.Connector#getProperties() properties} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the properties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("properties")
        public final Connector.Builder properties(
                Map<String, ? extends ConfigurationProperty> entries) {
            this.properties.clear();
            return putAllProperties(entries);
        }

        /**
         * Put all mappings from the specified map as entries to {@link io.syndesis.common.model.connection.Connector#getProperties() properties} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the properties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Connector.Builder putAllProperties(
                Map<String, ? extends ConfigurationProperty> entries) {
            for (Map.Entry<String, ? extends ConfigurationProperty> e : entries.entrySet()) {
                String k = e.getKey();
                ConfigurationProperty v = e.getValue();
                this.properties.put(
                        Objects.requireNonNull(k, "properties key"),
                        Objects.requireNonNull(v, "properties value"));
            }
            return (Connector.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.connection.Connector#getConfiguredProperties() configuredProperties} map.
         *
         * @param key   The key in the configuredProperties map
         * @param value The associated value in the configuredProperties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Connector.Builder putConfiguredProperty(String key,
                                                             String value) {
            this.configuredProperties.put(
                    Objects.requireNonNull(key, "configuredProperties key"),
                    Objects.requireNonNull(value,
                            "configuredProperties value"));
            optBits |= OPT_BIT_CONFIGURED_PROPERTIES;
            return (Connector.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.connection.Connector#getConfiguredProperties() configuredProperties} map. Nulls are not permitted
         *
         * @param entry The key and value entry
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Connector.Builder putConfiguredProperty(
                Map.Entry<String, ? extends String> entry) {
            String k = entry.getKey();
            String v = entry.getValue();
            this.configuredProperties.put(
                    Objects.requireNonNull(k, "configuredProperties key"),
                    Objects.requireNonNull(v, "configuredProperties value"));
            optBits |= OPT_BIT_CONFIGURED_PROPERTIES;
            return (Connector.Builder) this;
        }

        /**
         * Sets or replaces all mappings from the specified map as entries for the {@link io.syndesis.common.model.connection.Connector#getConfiguredProperties() configuredProperties} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the configuredProperties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("configuredProperties")
        public final Connector.Builder configuredProperties(
                Map<String, ? extends String> entries) {
            this.configuredProperties.clear();
            optBits |= OPT_BIT_CONFIGURED_PROPERTIES;
            return putAllConfiguredProperties(entries);
        }

        /**
         * Put all mappings from the specified map as entries to {@link io.syndesis.common.model.connection.Connector#getConfiguredProperties() configuredProperties} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the configuredProperties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Connector.Builder putAllConfiguredProperties(
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
            return (Connector.Builder) this;
        }

        /**
         * Adds one element to {@link io.syndesis.common.model.connection.Connector#getDependencies() dependencies} list.
         *
         * @param element A dependencies element
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Connector.Builder addDependency(Dependency element) {
            this.dependencies.add(
                    Objects.requireNonNull(element, "dependencies element"));
            optBits |= OPT_BIT_DEPENDENCIES;
            return (Connector.Builder) this;
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.connection.Connector#getDependencies() dependencies} list.
         *
         * @param elements An array of dependencies elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Connector.Builder addDependencies(Dependency... elements) {
            for (Dependency element : elements) {
                this.dependencies.add(Objects.requireNonNull(element,
                        "dependencies element"));
            }
            optBits |= OPT_BIT_DEPENDENCIES;
            return (Connector.Builder) this;
        }


        /**
         * Sets or replaces all elements for {@link io.syndesis.common.model.connection.Connector#getDependencies() dependencies} list.
         *
         * @param elements An iterable of dependencies elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("dependencies")
        public final Connector.Builder dependencies(
                Iterable<? extends Dependency> elements) {
            this.dependencies.clear();
            return addAllDependencies(elements);
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.connection.Connector#getDependencies() dependencies} list.
         *
         * @param elements An iterable of dependencies elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Connector.Builder addAllDependencies(
                Iterable<? extends Dependency> elements) {
            for (Dependency element : elements) {
                this.dependencies.add(Objects.requireNonNull(element,
                        "dependencies element"));
            }
            optBits |= OPT_BIT_DEPENDENCIES;
            return (Connector.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.connection.Connector#getMetadata() metadata} map.
         *
         * @param key   The key in the metadata map
         * @param value The associated value in the metadata map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Connector.Builder putMetadata(String key, String value) {
            this.metadata.put(
                    Objects.requireNonNull(key, "metadata key"),
                    Objects.requireNonNull(value, "metadata value"));
            optBits |= OPT_BIT_METADATA;
            return (Connector.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.connection.Connector#getMetadata() metadata} map. Nulls are not permitted
         *
         * @param entry The key and value entry
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Connector.Builder putMetadata(
                Map.Entry<String, ? extends String> entry) {
            String k = entry.getKey();
            String v = entry.getValue();
            this.metadata.put(
                    Objects.requireNonNull(k, "metadata key"),
                    Objects.requireNonNull(v, "metadata value"));
            optBits |= OPT_BIT_METADATA;
            return (Connector.Builder) this;
        }

        /**
         * Sets or replaces all mappings from the specified map as entries for the {@link io.syndesis.common.model.connection.Connector#getMetadata() metadata} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the metadata map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("metadata")
        public final Connector.Builder metadata(
                Map<String, ? extends String> entries) {
            this.metadata.clear();
            optBits |= OPT_BIT_METADATA;
            return putAllMetadata(entries);
        }

        /**
         * Put all mappings from the specified map as entries to {@link io.syndesis.common.model.connection.Connector#getMetadata() metadata} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the metadata map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Connector.Builder putAllMetadata(
                Map<String, ? extends String> entries) {
            for (Map.Entry<String, ? extends String> e : entries.entrySet()) {
                String k = e.getKey();
                String v = e.getValue();
                this.metadata.put(
                        Objects.requireNonNull(k, "metadata key"),
                        Objects.requireNonNull(v, "metadata value"));
            }
            optBits |= OPT_BIT_METADATA;
            return (Connector.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.Connector#getConnectorGroup() connectorGroup} to connectorGroup.
         *
         * @param connectorGroup The value for connectorGroup
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final Connector.Builder connectorGroup(
                ConnectorGroup connectorGroup) {
            this.connectorGroup =
                    Objects.requireNonNull(connectorGroup, "connectorGroup");
            return (Connector.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.Connector#getConnectorGroup() connectorGroup} to connectorGroup.
         *
         * @param connectorGroup The value for connectorGroup
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("connectorGroup")
        public final Connector.Builder connectorGroup(
                Optional<? extends ConnectorGroup> connectorGroup) {
            this.connectorGroup = connectorGroup.orElse(null);
            return (Connector.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.Connector#getConnectorGroupId() connectorGroupId} to connectorGroupId.
         *
         * @param connectorGroupId The value for connectorGroupId
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final Connector.Builder connectorGroupId(
                String connectorGroupId) {
            this.connectorGroupId = Objects.requireNonNull(connectorGroupId,
                    "connectorGroupId");
            return (Connector.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.Connector#getConnectorGroupId() connectorGroupId} to connectorGroupId.
         *
         * @param connectorGroupId The value for connectorGroupId
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("connectorGroupId")
        public final Connector.Builder connectorGroupId(
                Optional<String> connectorGroupId) {
            this.connectorGroupId = connectorGroupId.orElse(null);
            return (Connector.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.Connector#getDescription() description} attribute.
         *
         * @param description The value for description (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("description")
        public final Connector.Builder description(String description) {
            this.description = description;
            return (Connector.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.Connector#getIcon() icon} attribute.
         *
         * @param icon The value for icon (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("icon")
        public final Connector.Builder icon(String icon) {
            this.icon = icon;
            return (Connector.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.Connector#getKind() kind} attribute.
         * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link io.syndesis.common.model.connection.Connector#getKind() kind}.</em>
         *
         * @param kind The value for kind
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("kind")
        public final Connector.Builder kind(Kind kind) {
            this.kind = Objects.requireNonNull(kind, "kind");
            return (Connector.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.Connector#getComponentScheme() componentScheme} to componentScheme.
         *
         * @param componentScheme The value for componentScheme
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final Connector.Builder componentScheme(String componentScheme) {
            this.componentScheme =
                    Objects.requireNonNull(componentScheme, "componentScheme");
            return (Connector.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.Connector#getComponentScheme() componentScheme} to componentScheme.
         *
         * @param componentScheme The value for componentScheme
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("componentScheme")
        public final Connector.Builder componentScheme(
                Optional<String> componentScheme) {
            this.componentScheme = componentScheme.orElse(null);
            return (Connector.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.Connector#getConnectorFactory() connectorFactory} to connectorFactory.
         *
         * @param connectorFactory The value for connectorFactory
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final Connector.Builder connectorFactory(
                String connectorFactory) {
            this.connectorFactory = Objects.requireNonNull(connectorFactory,
                    "connectorFactory");
            return (Connector.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.Connector#getConnectorFactory() connectorFactory} to connectorFactory.
         *
         * @param connectorFactory The value for connectorFactory
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("connectorFactory")
        public final Connector.Builder connectorFactory(
                Optional<String> connectorFactory) {
            this.connectorFactory = connectorFactory.orElse(null);
            return (Connector.Builder) this;
        }

        /**
         * Adds one element to {@link io.syndesis.common.model.connection.Connector#getConnectorCustomizers() connectorCustomizers} list.
         *
         * @param element A connectorCustomizers element
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Connector.Builder addConnectorCustomizer(String element) {
            this.connectorCustomizers.add(Objects.requireNonNull(element,
                    "connectorCustomizers element"));
            optBits |= OPT_BIT_CONNECTOR_CUSTOMIZERS;
            return (Connector.Builder) this;
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.connection.Connector#getConnectorCustomizers() connectorCustomizers} list.
         *
         * @param elements An array of connectorCustomizers elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Connector.Builder addConnectorCustomizers(
                String... elements) {
            for (String element : elements) {
                this.connectorCustomizers.add(Objects.requireNonNull(element,
                        "connectorCustomizers element"));
            }
            optBits |= OPT_BIT_CONNECTOR_CUSTOMIZERS;
            return (Connector.Builder) this;
        }


        /**
         * Sets or replaces all elements for {@link io.syndesis.common.model.connection.Connector#getConnectorCustomizers() connectorCustomizers} list.
         *
         * @param elements An iterable of connectorCustomizers elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("connectorCustomizers")
        public final Connector.Builder connectorCustomizers(
                Iterable<String> elements) {
            this.connectorCustomizers.clear();
            return addAllConnectorCustomizers(elements);
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.connection.Connector#getConnectorCustomizers() connectorCustomizers} list.
         *
         * @param elements An iterable of connectorCustomizers elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Connector.Builder addAllConnectorCustomizers(
                Iterable<String> elements) {
            for (String element : elements) {
                this.connectorCustomizers.add(Objects.requireNonNull(element,
                        "connectorCustomizers element"));
            }
            optBits |= OPT_BIT_CONNECTOR_CUSTOMIZERS;
            return (Connector.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.Connector#getExceptionHandler() exceptionHandler} to exceptionHandler.
         *
         * @param exceptionHandler The value for exceptionHandler
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final Connector.Builder exceptionHandler(
                String exceptionHandler) {
            this.exceptionHandler = Objects.requireNonNull(exceptionHandler,
                    "exceptionHandler");
            return (Connector.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.Connector#getExceptionHandler() exceptionHandler} to exceptionHandler.
         *
         * @param exceptionHandler The value for exceptionHandler
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("exceptionHandler")
        public final Connector.Builder exceptionHandler(
                Optional<String> exceptionHandler) {
            this.exceptionHandler = exceptionHandler.orElse(null);
            return (Connector.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.Connector#getActionsSummary() actionsSummary} to actionsSummary.
         *
         * @param actionsSummary The value for actionsSummary
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final Connector.Builder actionsSummary(
                ActionsSummary actionsSummary) {
            this.actionsSummary =
                    Objects.requireNonNull(actionsSummary, "actionsSummary");
            return (Connector.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.Connector#getActionsSummary() actionsSummary} to actionsSummary.
         *
         * @param actionsSummary The value for actionsSummary
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("actionsSummary")
        public final Connector.Builder actionsSummary(
                Optional<? extends ActionsSummary> actionsSummary) {
            this.actionsSummary = actionsSummary.orElse(null);
            return (Connector.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.Connector#getUses() uses} to uses.
         *
         * @param uses The value for uses
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final Connector.Builder uses(int uses) {
            this.uses = uses;
            return (Connector.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.Connector#getUses() uses} to uses.
         *
         * @param uses The value for uses
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        public final Connector.Builder uses(Optional<Integer> uses) {
            this.uses = uses.isPresent() ? uses.get() : null;
            return (Connector.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.connection.Connector Connector}.
         *
         * @return An immutable instance of Connector
         * @throws IllegalStateException if any required attributes are missing
         */
        public Connector build() {
            return ImmutableConnector.validate(new ImmutableConnector(this));
        }

        private boolean versionIsSet() {
            return (optBits & OPT_BIT_VERSION) != 0;
        }

        private boolean configuredPropertiesIsSet() {
            return (optBits & OPT_BIT_CONFIGURED_PROPERTIES) != 0;
        }

        private boolean dependenciesIsSet() {
            return (optBits & OPT_BIT_DEPENDENCIES) != 0;
        }

        private boolean metadataIsSet() {
            return (optBits & OPT_BIT_METADATA) != 0;
        }

        private boolean connectorCustomizersIsSet() {
            return (optBits & OPT_BIT_CONNECTOR_CUSTOMIZERS) != 0;
        }
    }
    private final class InitShim {
        private byte versionBuildStage = STAGE_UNINITIALIZED;
        private int version;
        private byte configuredPropertiesBuildStage = STAGE_UNINITIALIZED;
        private Map<String, String> configuredProperties;
        private byte dependenciesBuildStage = STAGE_UNINITIALIZED;
        private List<Dependency> dependencies;
        private byte metadataBuildStage = STAGE_UNINITIALIZED;
        private Map<String, String> metadata;
        private byte kindBuildStage = STAGE_UNINITIALIZED;
        private Kind kind;
        private byte connectorCustomizersBuildStage = STAGE_UNINITIALIZED;
        private List<String> connectorCustomizers;

        int getVersion() {
            if (versionBuildStage == STAGE_INITIALIZING) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (versionBuildStage == STAGE_UNINITIALIZED) {
                versionBuildStage = STAGE_INITIALIZING;
                this.version = getVersionInitialize();
                versionBuildStage = STAGE_INITIALIZED;
            }
            return this.version;
        }

        void version(int version) {
            this.version = version;
            versionBuildStage = STAGE_INITIALIZED;
        }

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

        List<Dependency> getDependencies() {
            if (dependenciesBuildStage == STAGE_INITIALIZING) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (dependenciesBuildStage == STAGE_UNINITIALIZED) {
                dependenciesBuildStage = STAGE_INITIALIZING;
                this.dependencies = createUnmodifiableList(false,
                        createSafeList(getDependenciesInitialize(), true,
                                false));
                dependenciesBuildStage = STAGE_INITIALIZED;
            }
            return this.dependencies;
        }

        void dependencies(List<Dependency> dependencies) {
            this.dependencies = dependencies;
            dependenciesBuildStage = STAGE_INITIALIZED;
        }

        Map<String, String> getMetadata() {
            if (metadataBuildStage == STAGE_INITIALIZING) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (metadataBuildStage == STAGE_UNINITIALIZED) {
                metadataBuildStage = STAGE_INITIALIZING;
                this.metadata = createUnmodifiableMap(true, false,
                        getMetadataInitialize());
                metadataBuildStage = STAGE_INITIALIZED;
            }
            return this.metadata;
        }

        void metadata(Map<String, String> metadata) {
            this.metadata = metadata;
            metadataBuildStage = STAGE_INITIALIZED;
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

        List<String> getConnectorCustomizers() {
            if (connectorCustomizersBuildStage == STAGE_INITIALIZING) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (connectorCustomizersBuildStage == STAGE_UNINITIALIZED) {
                connectorCustomizersBuildStage = STAGE_INITIALIZING;
                this.connectorCustomizers = createUnmodifiableList(false,
                        createSafeList(getConnectorCustomizersInitialize(),
                                true, false));
                connectorCustomizersBuildStage = STAGE_INITIALIZED;
            }
            return this.connectorCustomizers;
        }

        void connectorCustomizers(List<String> connectorCustomizers) {
            this.connectorCustomizers = connectorCustomizers;
            connectorCustomizersBuildStage = STAGE_INITIALIZED;
        }

        private String formatInitCycleMessage() {
            List<String> attributes = new ArrayList<>();
            if (versionBuildStage == STAGE_INITIALIZING) {
                attributes.add("version");
            }
            if (configuredPropertiesBuildStage == STAGE_INITIALIZING) {
                attributes.add("configuredProperties");
            }
            if (dependenciesBuildStage == STAGE_INITIALIZING) {
                attributes.add("dependencies");
            }
            if (metadataBuildStage == STAGE_INITIALIZING) {
                attributes.add("metadata");
            }
            if (kindBuildStage == STAGE_INITIALIZING) {
                attributes.add("kind");
            }
            if (connectorCustomizersBuildStage == STAGE_INITIALIZING) {
                attributes.add("connectorCustomizers");
            }
            return "Cannot build Connector, attribute initializers form cycle "
                    + attributes;
        }
    }
}
