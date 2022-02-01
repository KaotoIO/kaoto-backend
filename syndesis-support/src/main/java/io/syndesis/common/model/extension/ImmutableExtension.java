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
package io.syndesis.common.model.extension;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Var;
import io.syndesis.common.model.Dependency;
import io.syndesis.common.model.WithConfigurationProperties;
import io.syndesis.common.model.WithConfiguredProperties;
import io.syndesis.common.model.WithDependencies;
import io.syndesis.common.model.WithMetadata;
import io.syndesis.common.model.WithName;
import io.syndesis.common.model.WithResourceId;
import io.syndesis.common.model.WithTags;
import io.syndesis.common.model.WithUsage;
import io.syndesis.common.model.action.Action;
import io.syndesis.common.model.connection.ConfigurationProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Immutable implementation of {@link io.syndesis.common.model.extension.Extension}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new Extension.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableExtension.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableExtension implements Extension {
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private static final byte STAGE_INITIALIZED = 1;
    private final String id;
    private final List<Action> actions;
    private final String name;
    private final SortedSet<String> tags;
    private final Map<String, ConfigurationProperty> properties;
    private final Map<String, String> configuredProperties;
    private final List<Dependency> dependencies;
    private final Map<String, String> metadata;
    private final int uses;
    private final String version;
    private final String extensionId;
    private final String schemaVersion;
    private final Extension.Status status;
    private final String icon;
    private final String description;
    private final String userId;
    private final Date lastUpdated;
    private final Date createdDate;
    private final Type extensionType;
    @SuppressWarnings("Immutable")
    private transient volatile InitShim initShim = new InitShim();

    @SuppressWarnings("unchecked") // safe covariant cast
    private ImmutableExtension(
            Optional<String> id,
            Iterable<? extends Action> actions,
            String name,
            Iterable<String> tags,
            Map<String, ? extends ConfigurationProperty> properties,
            Map<String, ? extends String> configuredProperties,
            Iterable<? extends Dependency> dependencies,
            Map<String, ? extends String> metadata,
            int uses,
            String version,
            String extensionId,
            String schemaVersion,
            Optional<? extends Status> status,
            String icon,
            String description,
            Optional<String> userId,
            Optional<? extends Date> lastUpdated,
            Optional<? extends Date> createdDate,
            Type extensionType) {
        this.id = id.orElse(null);
        this.actions = createUnmodifiableList(false,
                createSafeList(actions, true, false));
        this.name = name;
        this.tags = createUnmodifiableSortedSet(false,
                createSafeList(tags, false, true));
        this.properties = createUnmodifiableMap(true, false, properties);
        this.configuredProperties =
                createUnmodifiableMap(true, false, configuredProperties);
        this.dependencies = createUnmodifiableList(false,
                createSafeList(dependencies, true, false));
        this.metadata = createUnmodifiableMap(true, false, metadata);
        this.uses = uses;
        this.version = version;
        this.extensionId = extensionId;
        this.schemaVersion = schemaVersion;
        this.status = status.orElse(null);
        this.icon = icon;
        this.description = description;
        this.userId = userId.orElse(null);
        this.lastUpdated = lastUpdated.orElse(null);
        this.createdDate = createdDate.orElse(null);
        this.extensionType = extensionType;
        this.initShim = null;
    }

    private ImmutableExtension(Builder builder) {
        this.id = builder.id;
        this.actions = createUnmodifiableList(true, builder.actions);
        this.name = builder.name;
        this.tags = createUnmodifiableSortedSet(false,
                createSafeList(builder.tags, false, false));
        this.properties =
                createUnmodifiableMap(false, false, builder.properties);
        this.uses = builder.uses;
        this.version = builder.version;
        this.extensionId = builder.extensionId;
        this.schemaVersion = builder.schemaVersion;
        this.status = builder.status;
        this.icon = builder.icon;
        this.description = builder.description;
        this.userId = builder.userId;
        this.lastUpdated = builder.lastUpdated;
        this.createdDate = builder.createdDate;
        this.extensionType = builder.extensionType;
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
        this.configuredProperties = initShim.getConfiguredProperties();
        this.dependencies = initShim.getDependencies();
        this.metadata = initShim.getMetadata();
        this.initShim = null;
    }

    private ImmutableExtension(
            ImmutableExtension original,
            String id,
            List<Action> actions,
            String name,
            SortedSet<String> tags,
            Map<String, ConfigurationProperty> properties,
            Map<String, String> configuredProperties,
            List<Dependency> dependencies,
            Map<String, String> metadata,
            int uses,
            String version,
            String extensionId,
            String schemaVersion,
            Extension.Status status,
            String icon,
            String description,
            String userId,
            Date lastUpdated,
            Date createdDate,
            Type extensionType) {
        this.id = id;
        this.actions = actions;
        this.name = name;
        this.tags = tags;
        this.properties = properties;
        this.configuredProperties = configuredProperties;
        this.dependencies = dependencies;
        this.metadata = metadata;
        this.uses = uses;
        this.version = version;
        this.extensionId = extensionId;
        this.schemaVersion = schemaVersion;
        this.status = status;
        this.icon = icon;
        this.description = description;
        this.userId = userId;
        this.lastUpdated = lastUpdated;
        this.createdDate = createdDate;
        this.extensionType = extensionType;
        this.initShim = null;
    }

    /**
     * Construct a new immutable {@code Extension} instance.
     *
     * @param id                   The value for the {@code id} attribute
     * @param actions              The value for the {@code actions} attribute
     * @param name                 The value for the {@code name} attribute
     * @param tags                 The value for the {@code tags} attribute
     * @param properties           The value for the {@code properties} attribute
     * @param configuredProperties The value for the {@code configuredProperties} attribute
     * @param dependencies         The value for the {@code dependencies} attribute
     * @param metadata             The value for the {@code metadata} attribute
     * @param uses                 The value for the {@code uses} attribute
     * @param version              The value for the {@code version} attribute
     * @param extensionId          The value for the {@code extensionId} attribute
     * @param schemaVersion        The value for the {@code schemaVersion} attribute
     * @param status               The value for the {@code status} attribute
     * @param icon                 The value for the {@code icon} attribute
     * @param description          The value for the {@code description} attribute
     * @param userId               The value for the {@code userId} attribute
     * @param lastUpdated          The value for the {@code lastUpdated} attribute
     * @param createdDate          The value for the {@code createdDate} attribute
     * @param extensionType        The value for the {@code extensionType} attribute
     * @return An immutable Extension instance
     */
    public static Extension of(Optional<String> id, List<Action> actions,
                               String name, SortedSet<String> tags,
                               Map<String, ConfigurationProperty> properties,
                               Map<String, String> configuredProperties,
                               List<Dependency> dependencies,
                               Map<String, String> metadata, int uses,
                               String version, String extensionId,
                               String schemaVersion, Optional<Status> status,
                               String icon, String description,
                               Optional<String> userId,
                               Optional<Date> lastUpdated,
                               Optional<Date> createdDate, Type extensionType) {
        return of(id, (Iterable<? extends Action>) actions, name,
                (Iterable<String>) tags, properties, configuredProperties,
                (Iterable<? extends Dependency>) dependencies, metadata, uses,
                version, extensionId, schemaVersion, status, icon, description,
                userId, lastUpdated, createdDate, extensionType);
    }

    /**
     * Construct a new immutable {@code Extension} instance.
     *
     * @param id                   The value for the {@code id} attribute
     * @param actions              The value for the {@code actions} attribute
     * @param name                 The value for the {@code name} attribute
     * @param tags                 The value for the {@code tags} attribute
     * @param properties           The value for the {@code properties} attribute
     * @param configuredProperties The value for the {@code configuredProperties} attribute
     * @param dependencies         The value for the {@code dependencies} attribute
     * @param metadata             The value for the {@code metadata} attribute
     * @param uses                 The value for the {@code uses} attribute
     * @param version              The value for the {@code version} attribute
     * @param extensionId          The value for the {@code extensionId} attribute
     * @param schemaVersion        The value for the {@code schemaVersion} attribute
     * @param status               The value for the {@code status} attribute
     * @param icon                 The value for the {@code icon} attribute
     * @param description          The value for the {@code description} attribute
     * @param userId               The value for the {@code userId} attribute
     * @param lastUpdated          The value for the {@code lastUpdated} attribute
     * @param createdDate          The value for the {@code createdDate} attribute
     * @param extensionType        The value for the {@code extensionType} attribute
     * @return An immutable Extension instance
     */
    public static Extension of(Optional<String> id,
                               Iterable<? extends Action> actions, String name,
                               Iterable<String> tags,
                               Map<String, ? extends ConfigurationProperty> properties,
                               Map<String, ? extends String> configuredProperties,
                               Iterable<? extends Dependency> dependencies,
                               Map<String, ? extends String> metadata, int uses,
                               String version, String extensionId,
                               String schemaVersion,
                               Optional<? extends Status> status, String icon,
                               String description, Optional<String> userId,
                               Optional<? extends Date> lastUpdated,
                               Optional<? extends Date> createdDate,
                               Type extensionType) {
        return validate(
                new ImmutableExtension(id, actions, name, tags, properties,
                        configuredProperties, dependencies, metadata, uses,
                        version, extensionId, schemaVersion, status, icon,
                        description, userId, lastUpdated, createdDate,
                        extensionType));
    }

    private static ImmutableExtension validate(ImmutableExtension instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.extension.Extension} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable Extension instance
     */
    public static Extension copyOf(Extension instance) {
        if (instance instanceof ImmutableExtension) {
            return (ImmutableExtension) instance;
        }
        return new Extension.Builder()
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

    private Map<String, String> getConfiguredPropertiesInitialize() {
        return Extension.super.getConfiguredProperties();
    }

    private List<Dependency> getDependenciesInitialize() {
        return Extension.super.getDependencies();
    }

    private Map<String, String> getMetadataInitialize() {
        return Extension.super.getMetadata();
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
     * @return The value of the {@code actions} attribute
     */
    @JsonProperty("actions")
    @Override
    public List<Action> getActions() {
        return actions;
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
     * @return The value of the {@code tags} attribute
     */
    @JsonProperty("tags")
    @Override
    public SortedSet<String> getTags() {
        return tags;
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
     * @return The value of the {@code uses} attribute
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Override
    public int getUses() {
        return uses;
    }

    /**
     * @return The value of the {@code version} attribute
     */
    @JsonProperty("version")
    @Override
    public String getVersion() {
        return version;
    }

    /**
     * @return The value of the {@code extensionId} attribute
     */
    @JsonProperty("extensionId")
    @Override
    public String getExtensionId() {
        return extensionId;
    }

    /**
     * @return The value of the {@code schemaVersion} attribute
     */
    @JsonProperty("schemaVersion")
    @Override
    public String getSchemaVersion() {
        return schemaVersion;
    }

    /**
     * @return The value of the {@code status} attribute
     */
    @JsonProperty("status")
    @Override
    public Optional<Status> getStatus() {
        return Optional.ofNullable(status);
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
     * @return The value of the {@code description} attribute
     */
    @JsonProperty("description")
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * @return The value of the {@code userId} attribute
     */
    @JsonProperty("userId")
    @Override
    public Optional<String> getUserId() {
        return Optional.ofNullable(userId);
    }

    /**
     * @return The value of the {@code lastUpdated} attribute
     */
    @JsonProperty("lastUpdated")
    @Override
    public Optional<Date> getLastUpdated() {
        return Optional.ofNullable(lastUpdated);
    }

    /**
     * @return The value of the {@code createdDate} attribute
     */
    @JsonProperty("createdDate")
    @Override
    public Optional<Date> getCreatedDate() {
        return Optional.ofNullable(createdDate);
    }

    /**
     * @return The value of the {@code extensionType} attribute
     */
    @JsonProperty("extensionType")
    @Override
    public Type getExtensionType() {
        return extensionType;
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.extension.Extension#getId() id} attribute.
     *
     * @param value The value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableExtension withId(String value) {
        String newValue = Objects.requireNonNull(value, "id");
        if (Objects.equals(this.id, newValue)) {
            return this;
        }
        return validate(new ImmutableExtension(
                this,
                newValue,
                this.actions,
                this.name,
                this.tags,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.uses,
                this.version,
                this.extensionId,
                this.schemaVersion,
                this.status,
                this.icon,
                this.description,
                this.userId,
                this.lastUpdated,
                this.createdDate,
                this.extensionType));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.extension.Extension#getId() id} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableExtension withId(Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.id, value)) {
            return this;
        }
        return validate(new ImmutableExtension(
                this,
                value,
                this.actions,
                this.name,
                this.tags,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.uses,
                this.version,
                this.extensionId,
                this.schemaVersion,
                this.status,
                this.icon,
                this.description,
                this.userId,
                this.lastUpdated,
                this.createdDate,
                this.extensionType));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.extension.Extension#getActions() actions}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableExtension withActions(Action... elements) {
        List<Action> newValue = createUnmodifiableList(false,
                createSafeList(Arrays.asList(elements), true, false));
        return validate(new ImmutableExtension(
                this,
                this.id,
                newValue,
                this.name,
                this.tags,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.uses,
                this.version,
                this.extensionId,
                this.schemaVersion,
                this.status,
                this.icon,
                this.description,
                this.userId,
                this.lastUpdated,
                this.createdDate,
                this.extensionType));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.extension.Extension#getActions() actions}.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of actions elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableExtension withActions(
            Iterable<? extends Action> elements) {
        if (this.actions == elements) {
            return this;
        }
        List<Action> newValue = createUnmodifiableList(false,
                createSafeList(elements, true, false));
        return validate(new ImmutableExtension(
                this,
                this.id,
                newValue,
                this.name,
                this.tags,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.uses,
                this.version,
                this.extensionId,
                this.schemaVersion,
                this.status,
                this.icon,
                this.description,
                this.userId,
                this.lastUpdated,
                this.createdDate,
                this.extensionType));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.extension.Extension#getName() name} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for name (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableExtension withName(String value) {
        if (Objects.equals(this.name, value)) {
            return this;
        }
        return validate(new ImmutableExtension(
                this,
                this.id,
                this.actions,
                value,
                this.tags,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.uses,
                this.version,
                this.extensionId,
                this.schemaVersion,
                this.status,
                this.icon,
                this.description,
                this.userId,
                this.lastUpdated,
                this.createdDate,
                this.extensionType));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.extension.Extension#getTags() tags}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableExtension withTags(String... elements) {
        SortedSet<String> newValue = createUnmodifiableSortedSet(false,
                createSafeList(Arrays.asList(elements), false, true));
        return validate(new ImmutableExtension(
                this,
                this.id,
                this.actions,
                this.name,
                newValue,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.uses,
                this.version,
                this.extensionId,
                this.schemaVersion,
                this.status,
                this.icon,
                this.description,
                this.userId,
                this.lastUpdated,
                this.createdDate,
                this.extensionType));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.extension.Extension#getTags() tags}.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of tags elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableExtension withTags(Iterable<String> elements) {
        if (this.tags == elements) {
            return this;
        }
        SortedSet<String> newValue = createUnmodifiableSortedSet(false,
                createSafeList(elements, false, true));
        return validate(new ImmutableExtension(
                this,
                this.id,
                this.actions,
                this.name,
                newValue,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.uses,
                this.version,
                this.extensionId,
                this.schemaVersion,
                this.status,
                this.icon,
                this.description,
                this.userId,
                this.lastUpdated,
                this.createdDate,
                this.extensionType));
    }

    /**
     * Copy the current immutable object by replacing the {@link io.syndesis.common.model.extension.Extension#getProperties() properties} map with the specified map.
     * Nulls are not permitted as keys or values.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param entries The entries to be added to the properties map
     * @return A modified copy of {@code this} object
     */
    public final ImmutableExtension withProperties(
            Map<String, ? extends ConfigurationProperty> entries) {
        if (this.properties == entries) {
            return this;
        }
        Map<String, ConfigurationProperty> newValue =
                createUnmodifiableMap(true, false, entries);
        return validate(new ImmutableExtension(
                this,
                this.id,
                this.actions,
                this.name,
                this.tags,
                newValue,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.uses,
                this.version,
                this.extensionId,
                this.schemaVersion,
                this.status,
                this.icon,
                this.description,
                this.userId,
                this.lastUpdated,
                this.createdDate,
                this.extensionType));
    }

    /**
     * Copy the current immutable object by replacing the {@link io.syndesis.common.model.extension.Extension#getConfiguredProperties() configuredProperties} map with the specified map.
     * Nulls are not permitted as keys or values.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param entries The entries to be added to the configuredProperties map
     * @return A modified copy of {@code this} object
     */
    public final ImmutableExtension withConfiguredProperties(
            Map<String, ? extends String> entries) {
        if (this.configuredProperties == entries) {
            return this;
        }
        Map<String, String> newValue =
                createUnmodifiableMap(true, false, entries);
        return validate(new ImmutableExtension(
                this,
                this.id,
                this.actions,
                this.name,
                this.tags,
                this.properties,
                newValue,
                this.dependencies,
                this.metadata,
                this.uses,
                this.version,
                this.extensionId,
                this.schemaVersion,
                this.status,
                this.icon,
                this.description,
                this.userId,
                this.lastUpdated,
                this.createdDate,
                this.extensionType));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.extension.Extension#getDependencies() dependencies}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableExtension withDependencies(Dependency... elements) {
        List<Dependency> newValue = createUnmodifiableList(false,
                createSafeList(Arrays.asList(elements), true, false));
        return validate(new ImmutableExtension(
                this,
                this.id,
                this.actions,
                this.name,
                this.tags,
                this.properties,
                this.configuredProperties,
                newValue,
                this.metadata,
                this.uses,
                this.version,
                this.extensionId,
                this.schemaVersion,
                this.status,
                this.icon,
                this.description,
                this.userId,
                this.lastUpdated,
                this.createdDate,
                this.extensionType));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.extension.Extension#getDependencies() dependencies}.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of dependencies elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableExtension withDependencies(
            Iterable<? extends Dependency> elements) {
        if (this.dependencies == elements) {
            return this;
        }
        List<Dependency> newValue = createUnmodifiableList(false,
                createSafeList(elements, true, false));
        return validate(new ImmutableExtension(
                this,
                this.id,
                this.actions,
                this.name,
                this.tags,
                this.properties,
                this.configuredProperties,
                newValue,
                this.metadata,
                this.uses,
                this.version,
                this.extensionId,
                this.schemaVersion,
                this.status,
                this.icon,
                this.description,
                this.userId,
                this.lastUpdated,
                this.createdDate,
                this.extensionType));
    }

    /**
     * Copy the current immutable object by replacing the {@link io.syndesis.common.model.extension.Extension#getMetadata() metadata} map with the specified map.
     * Nulls are not permitted as keys or values.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param entries The entries to be added to the metadata map
     * @return A modified copy of {@code this} object
     */
    public final ImmutableExtension withMetadata(
            Map<String, ? extends String> entries) {
        if (this.metadata == entries) {
            return this;
        }
        Map<String, String> newValue =
                createUnmodifiableMap(true, false, entries);
        return validate(new ImmutableExtension(
                this,
                this.id,
                this.actions,
                this.name,
                this.tags,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                newValue,
                this.uses,
                this.version,
                this.extensionId,
                this.schemaVersion,
                this.status,
                this.icon,
                this.description,
                this.userId,
                this.lastUpdated,
                this.createdDate,
                this.extensionType));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.extension.Extension#getUses() uses} attribute.
     * A value equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for uses
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableExtension withUses(int value) {
        if (this.uses == value) {
            return this;
        }
        return validate(new ImmutableExtension(
                this,
                this.id,
                this.actions,
                this.name,
                this.tags,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                value,
                this.version,
                this.extensionId,
                this.schemaVersion,
                this.status,
                this.icon,
                this.description,
                this.userId,
                this.lastUpdated,
                this.createdDate,
                this.extensionType));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.extension.Extension#getVersion() version} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for version (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableExtension withVersion(String value) {
        if (Objects.equals(this.version, value)) {
            return this;
        }
        return validate(new ImmutableExtension(
                this,
                this.id,
                this.actions,
                this.name,
                this.tags,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.uses,
                value,
                this.extensionId,
                this.schemaVersion,
                this.status,
                this.icon,
                this.description,
                this.userId,
                this.lastUpdated,
                this.createdDate,
                this.extensionType));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.extension.Extension#getExtensionId() extensionId} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for extensionId (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableExtension withExtensionId(String value) {
        if (Objects.equals(this.extensionId, value)) {
            return this;
        }
        return validate(new ImmutableExtension(
                this,
                this.id,
                this.actions,
                this.name,
                this.tags,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.uses,
                this.version,
                value,
                this.schemaVersion,
                this.status,
                this.icon,
                this.description,
                this.userId,
                this.lastUpdated,
                this.createdDate,
                this.extensionType));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.extension.Extension#getSchemaVersion() schemaVersion} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for schemaVersion (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableExtension withSchemaVersion(String value) {
        if (Objects.equals(this.schemaVersion, value)) {
            return this;
        }
        return validate(new ImmutableExtension(
                this,
                this.id,
                this.actions,
                this.name,
                this.tags,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.uses,
                this.version,
                this.extensionId,
                value,
                this.status,
                this.icon,
                this.description,
                this.userId,
                this.lastUpdated,
                this.createdDate,
                this.extensionType));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.extension.Extension#getStatus() status} attribute.
     *
     * @param value The value for status
     * @return A modified copy of {@code this} object
     */
    public final ImmutableExtension withStatus(Status value) {
        Extension.Status newValue = Objects.requireNonNull(value, "status");
        if (this.status == newValue) {
            return this;
        }
        return validate(new ImmutableExtension(
                this,
                this.id,
                this.actions,
                this.name,
                this.tags,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.uses,
                this.version,
                this.extensionId,
                this.schemaVersion,
                newValue,
                this.icon,
                this.description,
                this.userId,
                this.lastUpdated,
                this.createdDate,
                this.extensionType));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.extension.Extension#getStatus() status} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for status
     * @return A modified copy of {@code this} object
     */
    @SuppressWarnings("unchecked") // safe covariant cast
    public final ImmutableExtension withStatus(
            Optional<? extends Status> optional) {
        Extension.Status value = optional.orElse(null);
        if (Objects.equals(this.status, value)) {
            return this;
        }
        return validate(new ImmutableExtension(
                this,
                this.id,
                this.actions,
                this.name,
                this.tags,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.uses,
                this.version,
                this.extensionId,
                this.schemaVersion,
                value,
                this.icon,
                this.description,
                this.userId,
                this.lastUpdated,
                this.createdDate,
                this.extensionType));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.extension.Extension#getIcon() icon} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for icon (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableExtension withIcon(String value) {
        if (Objects.equals(this.icon, value)) {
            return this;
        }
        return validate(new ImmutableExtension(
                this,
                this.id,
                this.actions,
                this.name,
                this.tags,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.uses,
                this.version,
                this.extensionId,
                this.schemaVersion,
                this.status,
                value,
                this.description,
                this.userId,
                this.lastUpdated,
                this.createdDate,
                this.extensionType));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.extension.Extension#getDescription() description} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for description (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableExtension withDescription(String value) {
        if (Objects.equals(this.description, value)) {
            return this;
        }
        return validate(new ImmutableExtension(
                this,
                this.id,
                this.actions,
                this.name,
                this.tags,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.uses,
                this.version,
                this.extensionId,
                this.schemaVersion,
                this.status,
                this.icon,
                value,
                this.userId,
                this.lastUpdated,
                this.createdDate,
                this.extensionType));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.extension.Extension#getUserId() userId} attribute.
     *
     * @param value The value for userId
     * @return A modified copy of {@code this} object
     */
    public final ImmutableExtension withUserId(String value) {
        String newValue = Objects.requireNonNull(value, "userId");
        if (Objects.equals(this.userId, newValue)) {
            return this;
        }
        return validate(new ImmutableExtension(
                this,
                this.id,
                this.actions,
                this.name,
                this.tags,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.uses,
                this.version,
                this.extensionId,
                this.schemaVersion,
                this.status,
                this.icon,
                this.description,
                newValue,
                this.lastUpdated,
                this.createdDate,
                this.extensionType));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.extension.Extension#getUserId() userId} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for userId
     * @return A modified copy of {@code this} object
     */
    public final ImmutableExtension withUserId(Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.userId, value)) {
            return this;
        }
        return validate(new ImmutableExtension(
                this,
                this.id,
                this.actions,
                this.name,
                this.tags,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.uses,
                this.version,
                this.extensionId,
                this.schemaVersion,
                this.status,
                this.icon,
                this.description,
                value,
                this.lastUpdated,
                this.createdDate,
                this.extensionType));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.extension.Extension#getLastUpdated() lastUpdated} attribute.
     *
     * @param value The value for lastUpdated
     * @return A modified copy of {@code this} object
     */
    public final ImmutableExtension withLastUpdated(Date value) {
        Date newValue = Objects.requireNonNull(value, "lastUpdated");
        if (this.lastUpdated == newValue) {
            return this;
        }
        return validate(new ImmutableExtension(
                this,
                this.id,
                this.actions,
                this.name,
                this.tags,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.uses,
                this.version,
                this.extensionId,
                this.schemaVersion,
                this.status,
                this.icon,
                this.description,
                this.userId,
                newValue,
                this.createdDate,
                this.extensionType));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.extension.Extension#getLastUpdated() lastUpdated} attribute.
     * A shallow reference equality check is used on unboxed optional value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for lastUpdated
     * @return A modified copy of {@code this} object
     */
    @SuppressWarnings("unchecked") // safe covariant cast
    public final ImmutableExtension withLastUpdated(
            Optional<? extends Date> optional) {
        Date value = optional.orElse(null);
        if (this.lastUpdated == value) {
            return this;
        }
        return validate(new ImmutableExtension(
                this,
                this.id,
                this.actions,
                this.name,
                this.tags,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.uses,
                this.version,
                this.extensionId,
                this.schemaVersion,
                this.status,
                this.icon,
                this.description,
                this.userId,
                value,
                this.createdDate,
                this.extensionType));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.extension.Extension#getCreatedDate() createdDate} attribute.
     *
     * @param value The value for createdDate
     * @return A modified copy of {@code this} object
     */
    public final ImmutableExtension withCreatedDate(Date value) {
        Date newValue = Objects.requireNonNull(value, "createdDate");
        if (this.createdDate == newValue) {
            return this;
        }
        return validate(new ImmutableExtension(
                this,
                this.id,
                this.actions,
                this.name,
                this.tags,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.uses,
                this.version,
                this.extensionId,
                this.schemaVersion,
                this.status,
                this.icon,
                this.description,
                this.userId,
                this.lastUpdated,
                newValue,
                this.extensionType));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.extension.Extension#getCreatedDate() createdDate} attribute.
     * A shallow reference equality check is used on unboxed optional value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for createdDate
     * @return A modified copy of {@code this} object
     */
    @SuppressWarnings("unchecked") // safe covariant cast
    public final ImmutableExtension withCreatedDate(
            Optional<? extends Date> optional) {
        Date value = optional.orElse(null);
        if (this.createdDate == value) {
            return this;
        }
        return validate(new ImmutableExtension(
                this,
                this.id,
                this.actions,
                this.name,
                this.tags,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.uses,
                this.version,
                this.extensionId,
                this.schemaVersion,
                this.status,
                this.icon,
                this.description,
                this.userId,
                this.lastUpdated,
                value,
                this.extensionType));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.extension.Extension#getExtensionType() extensionType} attribute.
     * A value equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for extensionType (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableExtension withExtensionType(Type value) {
        if (this.extensionType == value) {
            return this;
        }
        if (Objects.equals(this.extensionType, value)) {
            return this;
        }
        return validate(new ImmutableExtension(
                this,
                this.id,
                this.actions,
                this.name,
                this.tags,
                this.properties,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.uses,
                this.version,
                this.extensionId,
                this.schemaVersion,
                this.status,
                this.icon,
                this.description,
                this.userId,
                this.lastUpdated,
                this.createdDate,
                value));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableExtension} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableExtension
                && equalTo((ImmutableExtension) another);
    }

    private boolean equalTo(ImmutableExtension another) {
        return Objects.equals(id, another.id)
                && actions.equals(another.actions)
                && Objects.equals(name, another.name)
                && tags.equals(another.tags)
                && properties.equals(another.properties)
                && configuredProperties.equals(another.configuredProperties)
                && dependencies.equals(another.dependencies)
                && metadata.equals(another.metadata)
                && Objects.equals(version, another.version)
                && Objects.equals(extensionId, another.extensionId)
                && Objects.equals(schemaVersion, another.schemaVersion)
                && Objects.equals(status, another.status)
                && Objects.equals(icon, another.icon)
                && Objects.equals(description, another.description)
                && Objects.equals(userId, another.userId)
                && Objects.equals(lastUpdated, another.lastUpdated)
                && Objects.equals(createdDate, another.createdDate)
                && Objects.equals(extensionType, another.extensionType);
    }

    /**
     * Computes a hash code from attributes: {@code id}, {@code actions}, {@code name}, {@code tags}, {@code properties}, {@code configuredProperties}, {@code dependencies}, {@code metadata}, {@code version}, {@code extensionId}, {@code schemaVersion}, {@code status}, {@code icon}, {@code description}, {@code userId}, {@code lastUpdated}, {@code createdDate}, {@code extensionType}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(id);
        h += (h << 5) + actions.hashCode();
        h += (h << 5) + Objects.hashCode(name);
        h += (h << 5) + tags.hashCode();
        h += (h << 5) + properties.hashCode();
        h += (h << 5) + configuredProperties.hashCode();
        h += (h << 5) + dependencies.hashCode();
        h += (h << 5) + metadata.hashCode();
        h += (h << 5) + Objects.hashCode(version);
        h += (h << 5) + Objects.hashCode(extensionId);
        h += (h << 5) + Objects.hashCode(schemaVersion);
        h += (h << 5) + Objects.hashCode(status);
        h += (h << 5) + Objects.hashCode(icon);
        h += (h << 5) + Objects.hashCode(description);
        h += (h << 5) + Objects.hashCode(userId);
        h += (h << 5) + Objects.hashCode(lastUpdated);
        h += (h << 5) + Objects.hashCode(createdDate);
        h += (h << 5) + Objects.hashCode(extensionType);
        return h;
    }

    /**
     * Prints the immutable value {@code Extension} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Extension{");
        if (id != null) {
            builder.append("id=").append(id);
        }
        if (builder.length() > 10) {
            builder.append(", ");
        }
        builder.append("actions=").append(actions);
        if (name != null) {
            builder.append(", ");
            builder.append("name=").append(name);
        }
        builder.append(", ");
        builder.append("tags=").append(tags);
        builder.append(", ");
        builder.append("properties=").append(properties);
        builder.append(", ");
        builder.append("configuredProperties=").append(configuredProperties);
        builder.append(", ");
        builder.append("dependencies=").append(dependencies);
        builder.append(", ");
        builder.append("metadata=").append(metadata);
        if (version != null) {
            builder.append(", ");
            builder.append("version=").append(version);
        }
        if (extensionId != null) {
            builder.append(", ");
            builder.append("extensionId=").append(extensionId);
        }
        if (schemaVersion != null) {
            builder.append(", ");
            builder.append("schemaVersion=").append(schemaVersion);
        }
        if (status != null) {
            builder.append(", ");
            builder.append("status=").append(status);
        }
        if (icon != null) {
            builder.append(", ");
            builder.append("icon=").append(icon);
        }
        if (description != null) {
            builder.append(", ");
            builder.append("description=").append(description);
        }
        if (userId != null) {
            builder.append(", ");
            builder.append("userId=").append(userId);
        }
        if (lastUpdated != null) {
            builder.append(", ");
            builder.append("lastUpdated=").append(lastUpdated);
        }
        if (createdDate != null) {
            builder.append(", ");
            builder.append("createdDate=").append(createdDate);
        }
        if (extensionType != null) {
            builder.append(", ");
            builder.append("extensionType=").append(extensionType);
        }
        return builder.append("}").toString();
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.extension.Extension Extension}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    @JsonPropertyOrder(
            {"schemaVersion", "name", "description", "icon", "extensionId",
                    "version", "tags", "actions", "dependencies"})
    public static class Builder {
        private static final long OPT_BIT_CONFIGURED_PROPERTIES = 0x1L;
        private static final long OPT_BIT_DEPENDENCIES = 0x2L;
        private static final long OPT_BIT_METADATA = 0x4L;
        private long optBits;

        private String id;
        private List<Action> actions = new ArrayList<Action>();
        private String name;
        private List<String> tags = new ArrayList<String>();
        private Map<String, ConfigurationProperty> properties =
                new LinkedHashMap<String, ConfigurationProperty>();
        private Map<String, String> configuredProperties =
                new LinkedHashMap<String, String>();
        private List<Dependency> dependencies = new ArrayList<Dependency>();
        private Map<String, String> metadata =
                new LinkedHashMap<String, String>();
        private int uses;
        private String version;
        private String extensionId;
        private String schemaVersion;
        private Extension.Status status;
        private String icon;
        private String description;
        private String userId;
        private Date lastUpdated;
        private Date createdDate;
        private Extension.Type extensionType;

        /**
         * Creates a builder for {@link io.syndesis.common.model.extension.Extension Extension} instances.
         * <pre>
         * new Extension.Builder()
         *    .id(String) // optional {@link io.syndesis.common.model.extension.Extension#getId() id}
         *    .addAction|addAllActions(io.syndesis.common.io.syndesis.common.model.action.Action) // {@link io.syndesis.common.model.extension.Extension#getActions() actions} elements
         *    .name(String | null) // nullable {@link io.syndesis.common.model.extension.Extension#getName() name}
         *    .addTag|addAllTags(String) // {@link io.syndesis.common.model.extension.Extension#getTags() tags} elements
         *    .putProperty|putAllProperties(String =&gt; io.syndesis.common.io.syndesis.common.model.connection.ConfigurationProperty) // {@link io.syndesis.common.model.extension.Extension#getProperties() properties} mappings
         *    .putConfiguredProperty|putAllConfiguredProperties(String =&gt; String) // {@link io.syndesis.common.model.extension.Extension#getConfiguredProperties() configuredProperties} mappings
         *    .addDependency|addAllDependencies(io.syndesis.common.io.syndesis.common.model.Dependency) // {@link io.syndesis.common.model.extension.Extension#getDependencies() dependencies} elements
         *    .putMetadata|putAllMetadata(String =&gt; String) // {@link io.syndesis.common.model.extension.Extension#getMetadata() metadata} mappings
         *    .uses(int) // optional {@link io.syndesis.common.model.extension.Extension#getUses() uses}
         *    .version(String | null) // nullable {@link io.syndesis.common.model.extension.Extension#getVersion() version}
         *    .extensionId(String | null) // nullable {@link io.syndesis.common.model.extension.Extension#getExtensionId() extensionId}
         *    .schemaVersion(String | null) // nullable {@link io.syndesis.common.model.extension.Extension#getSchemaVersion() schemaVersion}
         *    .status(io.syndesis.common.io.syndesis.common.model.extension.Extension.Status) // optional {@link io.syndesis.common.model.extension.Extension#getStatus() status}
         *    .icon(String | null) // nullable {@link io.syndesis.common.model.extension.Extension#getIcon() icon}
         *    .description(String | null) // nullable {@link io.syndesis.common.model.extension.Extension#getDescription() description}
         *    .userId(String) // optional {@link io.syndesis.common.model.extension.Extension#getUserId() userId}
         *    .lastUpdated(Date) // optional {@link io.syndesis.common.model.extension.Extension#getLastUpdated() lastUpdated}
         *    .createdDate(Date) // optional {@link io.syndesis.common.model.extension.Extension#getCreatedDate() createdDate}
         *    .extensionType(io.syndesis.common.io.syndesis.common.model.extension.Extension.Type | null) // nullable {@link io.syndesis.common.model.extension.Extension#getExtensionType() extensionType}
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof Extension.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new Extension.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithName} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Extension.Builder createFrom(WithName instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Extension.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithUsage} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Extension.Builder createFrom(WithUsage instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Extension.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithConfiguredProperties} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Extension.Builder createFrom(
                WithConfiguredProperties instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Extension.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.extension.Extension} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Extension.Builder createFrom(Extension instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Extension.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithDependencies} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Extension.Builder createFrom(WithDependencies instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Extension.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithMetadata} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Extension.Builder createFrom(WithMetadata instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Extension.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithTags} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Extension.Builder createFrom(WithTags instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Extension.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithResourceId} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Extension.Builder createFrom(WithResourceId instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Extension.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithConfigurationProperties} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Extension.Builder createFrom(
                WithConfigurationProperties instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Extension.Builder) this;
        }

        private void from(Object object) {
            if (object instanceof WithName) {
                WithName instance = (WithName) object;
                String nameValue = instance.getName();
                if (nameValue != null) {
                    name(nameValue);
                }
            }
            if (object instanceof WithUsage) {
                WithUsage instance = (WithUsage) object;
                uses(instance.getUses());
            }
            if (object instanceof WithConfiguredProperties) {
                WithConfiguredProperties instance =
                        (WithConfiguredProperties) object;
                putAllConfiguredProperties(instance.getConfiguredProperties());
            }
            if (object instanceof Extension) {
                Extension instance = (Extension) object;
                Optional<Date> lastUpdatedOptional = instance.getLastUpdated();
                if (lastUpdatedOptional.isPresent()) {
                    lastUpdated(lastUpdatedOptional);
                }
                String schemaVersionValue = instance.getSchemaVersion();
                if (schemaVersionValue != null) {
                    schemaVersion(schemaVersionValue);
                }
                Optional<Date> createdDateOptional = instance.getCreatedDate();
                if (createdDateOptional.isPresent()) {
                    createdDate(createdDateOptional);
                }
                Type extensionTypeValue = instance.getExtensionType();
                if (extensionTypeValue != null) {
                    extensionType(extensionTypeValue);
                }
                String iconValue = instance.getIcon();
                if (iconValue != null) {
                    icon(iconValue);
                }
                String descriptionValue = instance.getDescription();
                if (descriptionValue != null) {
                    description(descriptionValue);
                }
                String versionValue = instance.getVersion();
                if (versionValue != null) {
                    version(versionValue);
                }
                Optional<String> userIdOptional = instance.getUserId();
                if (userIdOptional.isPresent()) {
                    userId(userIdOptional);
                }
                addAllActions(instance.getActions());
                String extensionIdValue = instance.getExtensionId();
                if (extensionIdValue != null) {
                    extensionId(extensionIdValue);
                }
                Optional<Status> statusOptional = instance.getStatus();
                if (statusOptional.isPresent()) {
                    status(statusOptional);
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
         * Initializes the optional value {@link io.syndesis.common.model.extension.Extension#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final Extension.Builder id(String id) {
            this.id = Objects.requireNonNull(id, "id");
            return (Extension.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.extension.Extension#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("id")
        public final Extension.Builder id(Optional<String> id) {
            this.id = id.orElse(null);
            return (Extension.Builder) this;
        }

        /**
         * Adds one element to {@link io.syndesis.common.model.extension.Extension#getActions() actions} list.
         *
         * @param element A actions element
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Extension.Builder addAction(Action element) {
            this.actions.add(
                    Objects.requireNonNull(element, "actions element"));
            return (Extension.Builder) this;
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.extension.Extension#getActions() actions} list.
         *
         * @param elements An array of actions elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Extension.Builder addActions(Action... elements) {
            for (Action element : elements) {
                this.actions.add(
                        Objects.requireNonNull(element, "actions element"));
            }
            return (Extension.Builder) this;
        }


        /**
         * Sets or replaces all elements for {@link io.syndesis.common.model.extension.Extension#getActions() actions} list.
         *
         * @param elements An iterable of actions elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("actions")
        public final Extension.Builder actions(
                Iterable<? extends Action> elements) {
            this.actions.clear();
            return addAllActions(elements);
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.extension.Extension#getActions() actions} list.
         *
         * @param elements An iterable of actions elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Extension.Builder addAllActions(
                Iterable<? extends Action> elements) {
            for (Action element : elements) {
                this.actions.add(
                        Objects.requireNonNull(element, "actions element"));
            }
            return (Extension.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.extension.Extension#getName() name} attribute.
         *
         * @param name The value for name (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("name")
        public final Extension.Builder name(String name) {
            this.name = name;
            return (Extension.Builder) this;
        }

        /**
         * Adds one element to {@link io.syndesis.common.model.extension.Extension#getTags() tags} sortedSet.
         *
         * @param element A tags element
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Extension.Builder addTag(String element) {
            if (element != null) {
                this.tags.add(element);
            }
            return (Extension.Builder) this;
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.extension.Extension#getTags() tags} sortedSet.
         *
         * @param elements An array of tags elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Extension.Builder addTags(String... elements) {
            for (String element : elements) {
                if (element != null) {
                    this.tags.add(element);
                }
            }
            return (Extension.Builder) this;
        }


        /**
         * Sets or replaces all elements for {@link io.syndesis.common.model.extension.Extension#getTags() tags} sortedSet.
         *
         * @param elements An iterable of tags elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("tags")
        public final Extension.Builder tags(Iterable<String> elements) {
            this.tags.clear();
            return addAllTags(elements);
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.extension.Extension#getTags() tags} sortedSet.
         *
         * @param elements An iterable of tags elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Extension.Builder addAllTags(Iterable<String> elements) {
            for (String element : elements) {
                if (element != null) {
                    this.tags.add(element);
                }
            }
            return (Extension.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.extension.Extension#getProperties() properties} map.
         *
         * @param key   The key in the properties map
         * @param value The associated value in the properties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Extension.Builder putProperty(String key,
                                                   ConfigurationProperty value) {
            this.properties.put(
                    Objects.requireNonNull(key, "properties key"),
                    Objects.requireNonNull(value, "properties value"));
            return (Extension.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.extension.Extension#getProperties() properties} map. Nulls are not permitted
         *
         * @param entry The key and value entry
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Extension.Builder putProperty(
                Map.Entry<String, ? extends ConfigurationProperty> entry) {
            String k = entry.getKey();
            ConfigurationProperty v = entry.getValue();
            this.properties.put(
                    Objects.requireNonNull(k, "properties key"),
                    Objects.requireNonNull(v, "properties value"));
            return (Extension.Builder) this;
        }

        /**
         * Sets or replaces all mappings from the specified map as entries for the {@link io.syndesis.common.model.extension.Extension#getProperties() properties} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the properties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("properties")
        public final Extension.Builder properties(
                Map<String, ? extends ConfigurationProperty> entries) {
            this.properties.clear();
            return putAllProperties(entries);
        }

        /**
         * Put all mappings from the specified map as entries to {@link io.syndesis.common.model.extension.Extension#getProperties() properties} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the properties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Extension.Builder putAllProperties(
                Map<String, ? extends ConfigurationProperty> entries) {
            for (Map.Entry<String, ? extends ConfigurationProperty> e : entries.entrySet()) {
                String k = e.getKey();
                ConfigurationProperty v = e.getValue();
                this.properties.put(
                        Objects.requireNonNull(k, "properties key"),
                        Objects.requireNonNull(v, "properties value"));
            }
            return (Extension.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.extension.Extension#getConfiguredProperties() configuredProperties} map.
         *
         * @param key   The key in the configuredProperties map
         * @param value The associated value in the configuredProperties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Extension.Builder putConfiguredProperty(String key,
                                                             String value) {
            this.configuredProperties.put(
                    Objects.requireNonNull(key, "configuredProperties key"),
                    Objects.requireNonNull(value,
                            "configuredProperties value"));
            optBits |= OPT_BIT_CONFIGURED_PROPERTIES;
            return (Extension.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.extension.Extension#getConfiguredProperties() configuredProperties} map. Nulls are not permitted
         *
         * @param entry The key and value entry
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Extension.Builder putConfiguredProperty(
                Map.Entry<String, ? extends String> entry) {
            String k = entry.getKey();
            String v = entry.getValue();
            this.configuredProperties.put(
                    Objects.requireNonNull(k, "configuredProperties key"),
                    Objects.requireNonNull(v, "configuredProperties value"));
            optBits |= OPT_BIT_CONFIGURED_PROPERTIES;
            return (Extension.Builder) this;
        }

        /**
         * Sets or replaces all mappings from the specified map as entries for the {@link io.syndesis.common.model.extension.Extension#getConfiguredProperties() configuredProperties} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the configuredProperties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("configuredProperties")
        public final Extension.Builder configuredProperties(
                Map<String, ? extends String> entries) {
            this.configuredProperties.clear();
            optBits |= OPT_BIT_CONFIGURED_PROPERTIES;
            return putAllConfiguredProperties(entries);
        }

        /**
         * Put all mappings from the specified map as entries to {@link io.syndesis.common.model.extension.Extension#getConfiguredProperties() configuredProperties} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the configuredProperties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Extension.Builder putAllConfiguredProperties(
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
            return (Extension.Builder) this;
        }

        /**
         * Adds one element to {@link io.syndesis.common.model.extension.Extension#getDependencies() dependencies} list.
         *
         * @param element A dependencies element
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Extension.Builder addDependency(Dependency element) {
            this.dependencies.add(
                    Objects.requireNonNull(element, "dependencies element"));
            optBits |= OPT_BIT_DEPENDENCIES;
            return (Extension.Builder) this;
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.extension.Extension#getDependencies() dependencies} list.
         *
         * @param elements An array of dependencies elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Extension.Builder addDependencies(Dependency... elements) {
            for (Dependency element : elements) {
                this.dependencies.add(Objects.requireNonNull(element,
                        "dependencies element"));
            }
            optBits |= OPT_BIT_DEPENDENCIES;
            return (Extension.Builder) this;
        }


        /**
         * Sets or replaces all elements for {@link io.syndesis.common.model.extension.Extension#getDependencies() dependencies} list.
         *
         * @param elements An iterable of dependencies elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("dependencies")
        public final Extension.Builder dependencies(
                Iterable<? extends Dependency> elements) {
            this.dependencies.clear();
            return addAllDependencies(elements);
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.extension.Extension#getDependencies() dependencies} list.
         *
         * @param elements An iterable of dependencies elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Extension.Builder addAllDependencies(
                Iterable<? extends Dependency> elements) {
            for (Dependency element : elements) {
                this.dependencies.add(Objects.requireNonNull(element,
                        "dependencies element"));
            }
            optBits |= OPT_BIT_DEPENDENCIES;
            return (Extension.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.extension.Extension#getMetadata() metadata} map.
         *
         * @param key   The key in the metadata map
         * @param value The associated value in the metadata map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Extension.Builder putMetadata(String key, String value) {
            this.metadata.put(
                    Objects.requireNonNull(key, "metadata key"),
                    Objects.requireNonNull(value, "metadata value"));
            optBits |= OPT_BIT_METADATA;
            return (Extension.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.extension.Extension#getMetadata() metadata} map. Nulls are not permitted
         *
         * @param entry The key and value entry
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Extension.Builder putMetadata(
                Map.Entry<String, ? extends String> entry) {
            String k = entry.getKey();
            String v = entry.getValue();
            this.metadata.put(
                    Objects.requireNonNull(k, "metadata key"),
                    Objects.requireNonNull(v, "metadata value"));
            optBits |= OPT_BIT_METADATA;
            return (Extension.Builder) this;
        }

        /**
         * Sets or replaces all mappings from the specified map as entries for the {@link io.syndesis.common.model.extension.Extension#getMetadata() metadata} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the metadata map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("metadata")
        public final Extension.Builder metadata(
                Map<String, ? extends String> entries) {
            this.metadata.clear();
            optBits |= OPT_BIT_METADATA;
            return putAllMetadata(entries);
        }

        /**
         * Put all mappings from the specified map as entries to {@link io.syndesis.common.model.extension.Extension#getMetadata() metadata} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the metadata map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Extension.Builder putAllMetadata(
                Map<String, ? extends String> entries) {
            for (Map.Entry<String, ? extends String> e : entries.entrySet()) {
                String k = e.getKey();
                String v = e.getValue();
                this.metadata.put(
                        Objects.requireNonNull(k, "metadata key"),
                        Objects.requireNonNull(v, "metadata value"));
            }
            optBits |= OPT_BIT_METADATA;
            return (Extension.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.extension.Extension#getUses() uses} attribute.
         *
         * @param uses The value for uses
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        public final Extension.Builder uses(int uses) {
            this.uses = uses;
            return (Extension.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.extension.Extension#getVersion() version} attribute.
         *
         * @param version The value for version (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("version")
        public final Extension.Builder version(String version) {
            this.version = version;
            return (Extension.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.extension.Extension#getExtensionId() extensionId} attribute.
         *
         * @param extensionId The value for extensionId (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("extensionId")
        public final Extension.Builder extensionId(String extensionId) {
            this.extensionId = extensionId;
            return (Extension.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.extension.Extension#getSchemaVersion() schemaVersion} attribute.
         *
         * @param schemaVersion The value for schemaVersion (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("schemaVersion")
        public final Extension.Builder schemaVersion(String schemaVersion) {
            this.schemaVersion = schemaVersion;
            return (Extension.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.extension.Extension#getStatus() status} to status.
         *
         * @param status The value for status
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final Extension.Builder status(Status status) {
            this.status = Objects.requireNonNull(status, "status");
            return (Extension.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.extension.Extension#getStatus() status} to status.
         *
         * @param status The value for status
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("status")
        public final Extension.Builder status(
                Optional<? extends Status> status) {
            this.status = status.orElse(null);
            return (Extension.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.extension.Extension#getIcon() icon} attribute.
         *
         * @param icon The value for icon (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("icon")
        public final Extension.Builder icon(String icon) {
            this.icon = icon;
            return (Extension.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.extension.Extension#getDescription() description} attribute.
         *
         * @param description The value for description (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("description")
        public final Extension.Builder description(String description) {
            this.description = description;
            return (Extension.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.extension.Extension#getUserId() userId} to userId.
         *
         * @param userId The value for userId
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final Extension.Builder userId(String userId) {
            this.userId = Objects.requireNonNull(userId, "userId");
            return (Extension.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.extension.Extension#getUserId() userId} to userId.
         *
         * @param userId The value for userId
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("userId")
        public final Extension.Builder userId(Optional<String> userId) {
            this.userId = userId.orElse(null);
            return (Extension.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.extension.Extension#getLastUpdated() lastUpdated} to lastUpdated.
         *
         * @param lastUpdated The value for lastUpdated
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final Extension.Builder lastUpdated(Date lastUpdated) {
            this.lastUpdated =
                    Objects.requireNonNull(lastUpdated, "lastUpdated");
            return (Extension.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.extension.Extension#getLastUpdated() lastUpdated} to lastUpdated.
         *
         * @param lastUpdated The value for lastUpdated
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("lastUpdated")
        public final Extension.Builder lastUpdated(
                Optional<? extends Date> lastUpdated) {
            this.lastUpdated = lastUpdated.orElse(null);
            return (Extension.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.extension.Extension#getCreatedDate() createdDate} to createdDate.
         *
         * @param createdDate The value for createdDate
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final Extension.Builder createdDate(Date createdDate) {
            this.createdDate =
                    Objects.requireNonNull(createdDate, "createdDate");
            return (Extension.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.extension.Extension#getCreatedDate() createdDate} to createdDate.
         *
         * @param createdDate The value for createdDate
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("createdDate")
        public final Extension.Builder createdDate(
                Optional<? extends Date> createdDate) {
            this.createdDate = createdDate.orElse(null);
            return (Extension.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.extension.Extension#getExtensionType() extensionType} attribute.
         *
         * @param extensionType The value for extensionType (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("extensionType")
        public final Extension.Builder extensionType(Type extensionType) {
            this.extensionType = extensionType;
            return (Extension.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.extension.Extension Extension}.
         *
         * @return An immutable instance of Extension
         * @throws IllegalStateException if any required attributes are missing
         */
        public Extension build() {
            return ImmutableExtension.validate(new ImmutableExtension(this));
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
    }

    private final class InitShim {
        private byte configuredPropertiesBuildStage = STAGE_UNINITIALIZED;
        private Map<String, String> configuredProperties;
        private byte dependenciesBuildStage = STAGE_UNINITIALIZED;
        private List<Dependency> dependencies;
        private byte metadataBuildStage = STAGE_UNINITIALIZED;
        private Map<String, String> metadata;

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

        private String formatInitCycleMessage() {
            List<String> attributes = new ArrayList<>();
            if (configuredPropertiesBuildStage == STAGE_INITIALIZING) {
                attributes.add("configuredProperties");
            }
            if (dependenciesBuildStage == STAGE_INITIALIZING) {
                attributes.add("dependencies");
            }
            if (metadataBuildStage == STAGE_INITIALIZING) {
                attributes.add("metadata");
            }
            return "Cannot build Extension, attribute initializers form cycle "
                    + attributes;
        }
    }
}
