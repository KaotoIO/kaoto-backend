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
import io.syndesis.common.model.WithConfiguredProperties;
import io.syndesis.common.model.WithMetadata;
import io.syndesis.common.model.WithName;
import io.syndesis.common.model.WithResourceId;
import io.syndesis.common.model.WithTags;
import io.syndesis.common.model.WithUsage;
import io.syndesis.common.model.bulletin.ConnectionBulletinBoard;
import io.syndesis.common.model.environment.Organization;

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
 * Immutable implementation of {@link io.syndesis.common.model.connection.ConnectionOverview}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new ConnectionOverview.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableConnectionOverview.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableConnectionOverview
        implements ConnectionOverview {
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private static final byte STAGE_INITIALIZED = 1;
    private final String id;
    private final SortedSet<String> tags;
    private final String name;
    private final Map<String, String> configuredProperties;
    private final Map<String, String> metadata;
    private final int uses;
    private final Organization organization;
    private final String organizationId;
    private final Connector connector;
    private final String connectorId;
    private final Map<String, String> options;
    private final String icon;
    private final String description;
    private final String userId;
    private final Date lastUpdated;
    private final Date createdDate;
    private final boolean isDerived;
    private final ConnectionBulletinBoard board;
    @SuppressWarnings("Immutable")
    private transient volatile InitShim initShim = new InitShim();

    @SuppressWarnings("unchecked") // safe covariant cast
    private ImmutableConnectionOverview(
            Optional<String> id,
            Iterable<String> tags,
            String name,
            Map<String, ? extends String> configuredProperties,
            Map<String, ? extends String> metadata,
            int uses,
            Optional<? extends Organization> organization,
            Optional<String> organizationId,
            Optional<? extends Connector> connector,
            String connectorId,
            Map<String, ? extends String> options,
            String icon,
            Optional<String> description,
            Optional<String> userId,
            Optional<? extends Date> lastUpdated,
            Optional<? extends Date> createdDate,
            boolean isDerived,
            ConnectionBulletinBoard board) {
        this.id = id.orElse(null);
        this.tags = createUnmodifiableSortedSet(false,
                createSafeList(tags, false, true));
        this.name = name;
        this.configuredProperties =
                createUnmodifiableMap(true, false, configuredProperties);
        this.metadata = createUnmodifiableMap(true, false, metadata);
        this.uses = uses;
        this.organization = organization.orElse(null);
        this.organizationId = organizationId.orElse(null);
        this.connector = connector.orElse(null);
        this.connectorId = connectorId;
        this.options = createUnmodifiableMap(true, false, options);
        this.icon = icon;
        this.description = description.orElse(null);
        this.userId = userId.orElse(null);
        this.lastUpdated = lastUpdated.orElse(null);
        this.createdDate = createdDate.orElse(null);
        this.isDerived = isDerived;
        this.board = Objects.requireNonNull(board, "board");
        this.initShim = null;
    }

    private ImmutableConnectionOverview(Builder builder) {
        this.id = builder.id;
        this.tags = createUnmodifiableSortedSet(false,
                createSafeList(builder.tags, false, false));
        this.name = builder.name;
        this.uses = builder.uses;
        this.organization = builder.organization;
        this.organizationId = builder.organizationId;
        this.connector = builder.connector;
        this.connectorId = builder.connectorId;
        this.options = createUnmodifiableMap(false, false, builder.options);
        this.icon = builder.icon;
        this.description = builder.description;
        this.userId = builder.userId;
        this.lastUpdated = builder.lastUpdated;
        this.createdDate = builder.createdDate;
        this.isDerived = builder.isDerived;
        if (builder.configuredPropertiesIsSet()) {
            initShim.configuredProperties(createUnmodifiableMap(false, false,
                    builder.configuredProperties));
        }
        if (builder.metadataIsSet()) {
            initShim.metadata(
                    createUnmodifiableMap(false, false, builder.metadata));
        }
        if (builder.board != null) {
            initShim.board(builder.board);
        }
        this.configuredProperties = initShim.getConfiguredProperties();
        this.metadata = initShim.getMetadata();
        this.board = initShim.getBoard();
        this.initShim = null;
    }

    private ImmutableConnectionOverview(
            ImmutableConnectionOverview original,
            String id,
            SortedSet<String> tags,
            String name,
            Map<String, String> configuredProperties,
            Map<String, String> metadata,
            int uses,
            Organization organization,
            String organizationId,
            Connector connector,
            String connectorId,
            Map<String, String> options,
            String icon,
            String description,
            String userId,
            Date lastUpdated,
            Date createdDate,
            boolean isDerived,
            ConnectionBulletinBoard board) {
        this.id = id;
        this.tags = tags;
        this.name = name;
        this.configuredProperties = configuredProperties;
        this.metadata = metadata;
        this.uses = uses;
        this.organization = organization;
        this.organizationId = organizationId;
        this.connector = connector;
        this.connectorId = connectorId;
        this.options = options;
        this.icon = icon;
        this.description = description;
        this.userId = userId;
        this.lastUpdated = lastUpdated;
        this.createdDate = createdDate;
        this.isDerived = isDerived;
        this.board = board;
        this.initShim = null;
    }

    /**
     * Construct a new immutable {@code ConnectionOverview} instance.
     *
     * @param id                   The value for the {@code id} attribute
     * @param tags                 The value for the {@code tags} attribute
     * @param name                 The value for the {@code name} attribute
     * @param configuredProperties The value for the {@code configuredProperties} attribute
     * @param metadata             The value for the {@code metadata} attribute
     * @param uses                 The value for the {@code uses} attribute
     * @param organization         The value for the {@code organization} attribute
     * @param organizationId       The value for the {@code organizationId} attribute
     * @param connector            The value for the {@code connector} attribute
     * @param connectorId          The value for the {@code connectorId} attribute
     * @param options              The value for the {@code options} attribute
     * @param icon                 The value for the {@code icon} attribute
     * @param description          The value for the {@code description} attribute
     * @param userId               The value for the {@code userId} attribute
     * @param lastUpdated          The value for the {@code lastUpdated} attribute
     * @param createdDate          The value for the {@code createdDate} attribute
     * @param isDerived            The value for the {@code isDerived} attribute
     * @param board                The value for the {@code board} attribute
     * @return An immutable ConnectionOverview instance
     */
    public static ConnectionOverview of(Optional<String> id,
                                        SortedSet<String> tags, String name,
                                        Map<String, String> configuredProperties,
                                        Map<String, String> metadata, int uses,
                                        Optional<Organization> organization,
                                        Optional<String> organizationId,
                                        Optional<Connector> connector,
                                        String connectorId,
                                        Map<String, String> options,
                                        String icon,
                                        Optional<String> description,
                                        Optional<String> userId,
                                        Optional<Date> lastUpdated,
                                        Optional<Date> createdDate,
                                        boolean isDerived,
                                        ConnectionBulletinBoard board) {
        return of(id, (Iterable<String>) tags, name, configuredProperties,
                metadata, uses, organization, organizationId, connector,
                connectorId, options, icon, description, userId, lastUpdated,
                createdDate, isDerived, board);
    }

    /**
     * Construct a new immutable {@code ConnectionOverview} instance.
     *
     * @param id                   The value for the {@code id} attribute
     * @param tags                 The value for the {@code tags} attribute
     * @param name                 The value for the {@code name} attribute
     * @param configuredProperties The value for the {@code configuredProperties} attribute
     * @param metadata             The value for the {@code metadata} attribute
     * @param uses                 The value for the {@code uses} attribute
     * @param organization         The value for the {@code organization} attribute
     * @param organizationId       The value for the {@code organizationId} attribute
     * @param connector            The value for the {@code connector} attribute
     * @param connectorId          The value for the {@code connectorId} attribute
     * @param options              The value for the {@code options} attribute
     * @param icon                 The value for the {@code icon} attribute
     * @param description          The value for the {@code description} attribute
     * @param userId               The value for the {@code userId} attribute
     * @param lastUpdated          The value for the {@code lastUpdated} attribute
     * @param createdDate          The value for the {@code createdDate} attribute
     * @param isDerived            The value for the {@code isDerived} attribute
     * @param board                The value for the {@code board} attribute
     * @return An immutable ConnectionOverview instance
     */
    public static ConnectionOverview of(Optional<String> id,
                                        Iterable<String> tags, String name,
                                        Map<String, ? extends String> configuredProperties,
                                        Map<String, ? extends String> metadata,
                                        int uses,
                                        Optional<? extends Organization> organization,
                                        Optional<String> organizationId,
                                        Optional<? extends Connector> connector,
                                        String connectorId,
                                        Map<String, ? extends String> options,
                                        String icon,
                                        Optional<String> description,
                                        Optional<String> userId,
                                        Optional<? extends Date> lastUpdated,
                                        Optional<? extends Date> createdDate,
                                        boolean isDerived,
                                        ConnectionBulletinBoard board) {
        return validate(new ImmutableConnectionOverview(id, tags, name,
                configuredProperties, metadata, uses, organization,
                organizationId, connector, connectorId, options, icon,
                description, userId, lastUpdated, createdDate, isDerived,
                board));
    }

    private static ImmutableConnectionOverview validate(
            ImmutableConnectionOverview instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.connection.ConnectionOverview} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable ConnectionOverview instance
     */
    public static ConnectionOverview copyOf(ConnectionOverview instance) {
        if (instance instanceof ImmutableConnectionOverview) {
            return (ImmutableConnectionOverview) instance;
        }
        return new ConnectionOverview.Builder()
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
        return ConnectionOverview.super.getConfiguredProperties();
    }

    private Map<String, String> getMetadataInitialize() {
        return ConnectionOverview.super.getMetadata();
    }

    private ConnectionBulletinBoard getBoardInitialize() {
        return ConnectionOverview.super.getBoard();
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
     * @return The value of the {@code organization} attribute
     */
    @JsonProperty("organization")
    @Override
    public Optional<Organization> getOrganization() {
        return Optional.ofNullable(organization);
    }

    /**
     * @return The value of the {@code organizationId} attribute
     */
    @JsonProperty("organizationId")
    @Override
    public Optional<String> getOrganizationId() {
        return Optional.ofNullable(organizationId);
    }

    /**
     * @return The value of the {@code connector} attribute
     */
    @JsonProperty("connector")
    @Override
    public Optional<Connector> getConnector() {
        return Optional.ofNullable(connector);
    }

    /**
     * @return The value of the {@code connectorId} attribute
     */
    @JsonProperty("connectorId")
    @Override
    public String getConnectorId() {
        return connectorId;
    }

    /**
     * @return The value of the {@code options} attribute
     */
    @JsonProperty("options")
    @Override
    public Map<String, String> getOptions() {
        return options;
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
    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
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
     * @return The value of the {@code isDerived} attribute
     */
    @JsonProperty("isDerived")
    @Override
    public boolean isDerived() {
        return isDerived;
    }

    /**
     * @return The value of the {@code board} attribute
     */
    @JsonProperty("board")
    @Override
    public ConnectionBulletinBoard getBoard() {
        InitShim shim = this.initShim;
        return shim != null
                ? shim.getBoard()
                : this.board;
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.connection.ConnectionOverview#getId() id} attribute.
     *
     * @param value The value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnectionOverview withId(String value) {
        String newValue = Objects.requireNonNull(value, "id");
        if (Objects.equals(this.id, newValue)) {
            return this;
        }
        return validate(new ImmutableConnectionOverview(
                this,
                newValue,
                this.tags,
                this.name,
                this.configuredProperties,
                this.metadata,
                this.uses,
                this.organization,
                this.organizationId,
                this.connector,
                this.connectorId,
                this.options,
                this.icon,
                this.description,
                this.userId,
                this.lastUpdated,
                this.createdDate,
                this.isDerived,
                this.board));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.connection.ConnectionOverview#getId() id} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnectionOverview withId(Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.id, value)) {
            return this;
        }
        return validate(new ImmutableConnectionOverview(
                this,
                value,
                this.tags,
                this.name,
                this.configuredProperties,
                this.metadata,
                this.uses,
                this.organization,
                this.organizationId,
                this.connector,
                this.connectorId,
                this.options,
                this.icon,
                this.description,
                this.userId,
                this.lastUpdated,
                this.createdDate,
                this.isDerived,
                this.board));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.connection.ConnectionOverview#getTags() tags}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnectionOverview withTags(String... elements) {
        SortedSet<String> newValue = createUnmodifiableSortedSet(false,
                createSafeList(Arrays.asList(elements), false, true));
        return validate(new ImmutableConnectionOverview(
                this,
                this.id,
                newValue,
                this.name,
                this.configuredProperties,
                this.metadata,
                this.uses,
                this.organization,
                this.organizationId,
                this.connector,
                this.connectorId,
                this.options,
                this.icon,
                this.description,
                this.userId,
                this.lastUpdated,
                this.createdDate,
                this.isDerived,
                this.board));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.connection.ConnectionOverview#getTags() tags}.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of tags elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnectionOverview withTags(
            Iterable<String> elements) {
        if (this.tags == elements) {
            return this;
        }
        SortedSet<String> newValue = createUnmodifiableSortedSet(false,
                createSafeList(elements, false, true));
        return validate(new ImmutableConnectionOverview(
                this,
                this.id,
                newValue,
                this.name,
                this.configuredProperties,
                this.metadata,
                this.uses,
                this.organization,
                this.organizationId,
                this.connector,
                this.connectorId,
                this.options,
                this.icon,
                this.description,
                this.userId,
                this.lastUpdated,
                this.createdDate,
                this.isDerived,
                this.board));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConnectionOverview#getName() name} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for name (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConnectionOverview withName(String value) {
        if (Objects.equals(this.name, value)) {
            return this;
        }
        return validate(new ImmutableConnectionOverview(
                this,
                this.id,
                this.tags,
                value,
                this.configuredProperties,
                this.metadata,
                this.uses,
                this.organization,
                this.organizationId,
                this.connector,
                this.connectorId,
                this.options,
                this.icon,
                this.description,
                this.userId,
                this.lastUpdated,
                this.createdDate,
                this.isDerived,
                this.board));
    }

    /**
     * Copy the current immutable object by replacing the {@link io.syndesis.common.model.connection.ConnectionOverview#getConfiguredProperties() configuredProperties} map with the specified map.
     * Nulls are not permitted as keys or values.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param entries The entries to be added to the configuredProperties map
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnectionOverview withConfiguredProperties(
            Map<String, ? extends String> entries) {
        if (this.configuredProperties == entries) {
            return this;
        }
        Map<String, String> newValue =
                createUnmodifiableMap(true, false, entries);
        return validate(new ImmutableConnectionOverview(
                this,
                this.id,
                this.tags,
                this.name,
                newValue,
                this.metadata,
                this.uses,
                this.organization,
                this.organizationId,
                this.connector,
                this.connectorId,
                this.options,
                this.icon,
                this.description,
                this.userId,
                this.lastUpdated,
                this.createdDate,
                this.isDerived,
                this.board));
    }

    /**
     * Copy the current immutable object by replacing the {@link io.syndesis.common.model.connection.ConnectionOverview#getMetadata() metadata} map with the specified map.
     * Nulls are not permitted as keys or values.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param entries The entries to be added to the metadata map
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnectionOverview withMetadata(
            Map<String, ? extends String> entries) {
        if (this.metadata == entries) {
            return this;
        }
        Map<String, String> newValue =
                createUnmodifiableMap(true, false, entries);
        return validate(new ImmutableConnectionOverview(
                this,
                this.id,
                this.tags,
                this.name,
                this.configuredProperties,
                newValue,
                this.uses,
                this.organization,
                this.organizationId,
                this.connector,
                this.connectorId,
                this.options,
                this.icon,
                this.description,
                this.userId,
                this.lastUpdated,
                this.createdDate,
                this.isDerived,
                this.board));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConnectionOverview#getUses() uses} attribute.
     * A value equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for uses
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConnectionOverview withUses(int value) {
        if (this.uses == value) {
            return this;
        }
        return validate(new ImmutableConnectionOverview(
                this,
                this.id,
                this.tags,
                this.name,
                this.configuredProperties,
                this.metadata,
                value,
                this.organization,
                this.organizationId,
                this.connector,
                this.connectorId,
                this.options,
                this.icon,
                this.description,
                this.userId,
                this.lastUpdated,
                this.createdDate,
                this.isDerived,
                this.board));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.connection.ConnectionOverview#getOrganization() organization} attribute.
     *
     * @param value The value for organization
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnectionOverview withOrganization(
            Organization value) {
        Organization newValue = Objects.requireNonNull(value, "organization");
        if (this.organization == newValue) {
            return this;
        }
        return validate(new ImmutableConnectionOverview(
                this,
                this.id,
                this.tags,
                this.name,
                this.configuredProperties,
                this.metadata,
                this.uses,
                newValue,
                this.organizationId,
                this.connector,
                this.connectorId,
                this.options,
                this.icon,
                this.description,
                this.userId,
                this.lastUpdated,
                this.createdDate,
                this.isDerived,
                this.board));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.connection.ConnectionOverview#getOrganization() organization} attribute.
     * A shallow reference equality check is used on unboxed optional value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for organization
     * @return A modified copy of {@code this} object
     */
    @SuppressWarnings("unchecked") // safe covariant cast
    public final ImmutableConnectionOverview withOrganization(
            Optional<? extends Organization> optional) {
        Organization value = optional.orElse(null);
        if (this.organization == value) {
            return this;
        }
        return validate(new ImmutableConnectionOverview(
                this,
                this.id,
                this.tags,
                this.name,
                this.configuredProperties,
                this.metadata,
                this.uses,
                value,
                this.organizationId,
                this.connector,
                this.connectorId,
                this.options,
                this.icon,
                this.description,
                this.userId,
                this.lastUpdated,
                this.createdDate,
                this.isDerived,
                this.board));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.connection.ConnectionOverview#getOrganizationId() organizationId} attribute.
     *
     * @param value The value for organizationId
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnectionOverview withOrganizationId(String value) {
        String newValue = Objects.requireNonNull(value, "organizationId");
        if (Objects.equals(this.organizationId, newValue)) {
            return this;
        }
        return validate(new ImmutableConnectionOverview(
                this,
                this.id,
                this.tags,
                this.name,
                this.configuredProperties,
                this.metadata,
                this.uses,
                this.organization,
                newValue,
                this.connector,
                this.connectorId,
                this.options,
                this.icon,
                this.description,
                this.userId,
                this.lastUpdated,
                this.createdDate,
                this.isDerived,
                this.board));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.connection.ConnectionOverview#getOrganizationId() organizationId} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for organizationId
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnectionOverview withOrganizationId(
            Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.organizationId, value)) {
            return this;
        }
        return validate(new ImmutableConnectionOverview(
                this,
                this.id,
                this.tags,
                this.name,
                this.configuredProperties,
                this.metadata,
                this.uses,
                this.organization,
                value,
                this.connector,
                this.connectorId,
                this.options,
                this.icon,
                this.description,
                this.userId,
                this.lastUpdated,
                this.createdDate,
                this.isDerived,
                this.board));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.connection.ConnectionOverview#getConnector() connector} attribute.
     *
     * @param value The value for connector
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnectionOverview withConnector(Connector value) {
        Connector newValue = Objects.requireNonNull(value, "connector");
        if (this.connector == newValue) {
            return this;
        }
        return validate(new ImmutableConnectionOverview(
                this,
                this.id,
                this.tags,
                this.name,
                this.configuredProperties,
                this.metadata,
                this.uses,
                this.organization,
                this.organizationId,
                newValue,
                this.connectorId,
                this.options,
                this.icon,
                this.description,
                this.userId,
                this.lastUpdated,
                this.createdDate,
                this.isDerived,
                this.board));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.connection.ConnectionOverview#getConnector() connector} attribute.
     * A shallow reference equality check is used on unboxed optional value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for connector
     * @return A modified copy of {@code this} object
     */
    @SuppressWarnings("unchecked") // safe covariant cast
    public final ImmutableConnectionOverview withConnector(
            Optional<? extends Connector> optional) {
        Connector value = optional.orElse(null);
        if (this.connector == value) {
            return this;
        }
        return validate(new ImmutableConnectionOverview(
                this,
                this.id,
                this.tags,
                this.name,
                this.configuredProperties,
                this.metadata,
                this.uses,
                this.organization,
                this.organizationId,
                value,
                this.connectorId,
                this.options,
                this.icon,
                this.description,
                this.userId,
                this.lastUpdated,
                this.createdDate,
                this.isDerived,
                this.board));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConnectionOverview#getConnectorId() connectorId} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for connectorId (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConnectionOverview withConnectorId(String value) {
        if (Objects.equals(this.connectorId, value)) {
            return this;
        }
        return validate(new ImmutableConnectionOverview(
                this,
                this.id,
                this.tags,
                this.name,
                this.configuredProperties,
                this.metadata,
                this.uses,
                this.organization,
                this.organizationId,
                this.connector,
                value,
                this.options,
                this.icon,
                this.description,
                this.userId,
                this.lastUpdated,
                this.createdDate,
                this.isDerived,
                this.board));
    }

    /**
     * Copy the current immutable object by replacing the {@link io.syndesis.common.model.connection.ConnectionOverview#getOptions() options} map with the specified map.
     * Nulls are not permitted as keys or values.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param entries The entries to be added to the options map
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnectionOverview withOptions(
            Map<String, ? extends String> entries) {
        if (this.options == entries) {
            return this;
        }
        Map<String, String> newValue =
                createUnmodifiableMap(true, false, entries);
        return validate(new ImmutableConnectionOverview(
                this,
                this.id,
                this.tags,
                this.name,
                this.configuredProperties,
                this.metadata,
                this.uses,
                this.organization,
                this.organizationId,
                this.connector,
                this.connectorId,
                newValue,
                this.icon,
                this.description,
                this.userId,
                this.lastUpdated,
                this.createdDate,
                this.isDerived,
                this.board));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConnectionOverview#getIcon() icon} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for icon (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConnectionOverview withIcon(String value) {
        if (Objects.equals(this.icon, value)) {
            return this;
        }
        return validate(new ImmutableConnectionOverview(
                this,
                this.id,
                this.tags,
                this.name,
                this.configuredProperties,
                this.metadata,
                this.uses,
                this.organization,
                this.organizationId,
                this.connector,
                this.connectorId,
                this.options,
                value,
                this.description,
                this.userId,
                this.lastUpdated,
                this.createdDate,
                this.isDerived,
                this.board));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.connection.ConnectionOverview#getDescription() description} attribute.
     *
     * @param value The value for description
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnectionOverview withDescription(String value) {
        String newValue = Objects.requireNonNull(value, "description");
        if (Objects.equals(this.description, newValue)) {
            return this;
        }
        return validate(new ImmutableConnectionOverview(
                this,
                this.id,
                this.tags,
                this.name,
                this.configuredProperties,
                this.metadata,
                this.uses,
                this.organization,
                this.organizationId,
                this.connector,
                this.connectorId,
                this.options,
                this.icon,
                newValue,
                this.userId,
                this.lastUpdated,
                this.createdDate,
                this.isDerived,
                this.board));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.connection.ConnectionOverview#getDescription() description} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for description
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnectionOverview withDescription(
            Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.description, value)) {
            return this;
        }
        return validate(new ImmutableConnectionOverview(
                this,
                this.id,
                this.tags,
                this.name,
                this.configuredProperties,
                this.metadata,
                this.uses,
                this.organization,
                this.organizationId,
                this.connector,
                this.connectorId,
                this.options,
                this.icon,
                value,
                this.userId,
                this.lastUpdated,
                this.createdDate,
                this.isDerived,
                this.board));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.connection.ConnectionOverview#getUserId() userId} attribute.
     *
     * @param value The value for userId
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnectionOverview withUserId(String value) {
        String newValue = Objects.requireNonNull(value, "userId");
        if (Objects.equals(this.userId, newValue)) {
            return this;
        }
        return validate(new ImmutableConnectionOverview(
                this,
                this.id,
                this.tags,
                this.name,
                this.configuredProperties,
                this.metadata,
                this.uses,
                this.organization,
                this.organizationId,
                this.connector,
                this.connectorId,
                this.options,
                this.icon,
                this.description,
                newValue,
                this.lastUpdated,
                this.createdDate,
                this.isDerived,
                this.board));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.connection.ConnectionOverview#getUserId() userId} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for userId
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnectionOverview withUserId(
            Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.userId, value)) {
            return this;
        }
        return validate(new ImmutableConnectionOverview(
                this,
                this.id,
                this.tags,
                this.name,
                this.configuredProperties,
                this.metadata,
                this.uses,
                this.organization,
                this.organizationId,
                this.connector,
                this.connectorId,
                this.options,
                this.icon,
                this.description,
                value,
                this.lastUpdated,
                this.createdDate,
                this.isDerived,
                this.board));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.connection.ConnectionOverview#getLastUpdated() lastUpdated} attribute.
     *
     * @param value The value for lastUpdated
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnectionOverview withLastUpdated(Date value) {
        Date newValue = Objects.requireNonNull(value, "lastUpdated");
        if (this.lastUpdated == newValue) {
            return this;
        }
        return validate(new ImmutableConnectionOverview(
                this,
                this.id,
                this.tags,
                this.name,
                this.configuredProperties,
                this.metadata,
                this.uses,
                this.organization,
                this.organizationId,
                this.connector,
                this.connectorId,
                this.options,
                this.icon,
                this.description,
                this.userId,
                newValue,
                this.createdDate,
                this.isDerived,
                this.board));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.connection.ConnectionOverview#getLastUpdated() lastUpdated} attribute.
     * A shallow reference equality check is used on unboxed optional value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for lastUpdated
     * @return A modified copy of {@code this} object
     */
    @SuppressWarnings("unchecked") // safe covariant cast
    public final ImmutableConnectionOverview withLastUpdated(
            Optional<? extends Date> optional) {
        Date value = optional.orElse(null);
        if (this.lastUpdated == value) {
            return this;
        }
        return validate(new ImmutableConnectionOverview(
                this,
                this.id,
                this.tags,
                this.name,
                this.configuredProperties,
                this.metadata,
                this.uses,
                this.organization,
                this.organizationId,
                this.connector,
                this.connectorId,
                this.options,
                this.icon,
                this.description,
                this.userId,
                value,
                this.createdDate,
                this.isDerived,
                this.board));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.connection.ConnectionOverview#getCreatedDate() createdDate} attribute.
     *
     * @param value The value for createdDate
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnectionOverview withCreatedDate(Date value) {
        Date newValue = Objects.requireNonNull(value, "createdDate");
        if (this.createdDate == newValue) {
            return this;
        }
        return validate(new ImmutableConnectionOverview(
                this,
                this.id,
                this.tags,
                this.name,
                this.configuredProperties,
                this.metadata,
                this.uses,
                this.organization,
                this.organizationId,
                this.connector,
                this.connectorId,
                this.options,
                this.icon,
                this.description,
                this.userId,
                this.lastUpdated,
                newValue,
                this.isDerived,
                this.board));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.connection.ConnectionOverview#getCreatedDate() createdDate} attribute.
     * A shallow reference equality check is used on unboxed optional value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for createdDate
     * @return A modified copy of {@code this} object
     */
    @SuppressWarnings("unchecked") // safe covariant cast
    public final ImmutableConnectionOverview withCreatedDate(
            Optional<? extends Date> optional) {
        Date value = optional.orElse(null);
        if (this.createdDate == value) {
            return this;
        }
        return validate(new ImmutableConnectionOverview(
                this,
                this.id,
                this.tags,
                this.name,
                this.configuredProperties,
                this.metadata,
                this.uses,
                this.organization,
                this.organizationId,
                this.connector,
                this.connectorId,
                this.options,
                this.icon,
                this.description,
                this.userId,
                this.lastUpdated,
                value,
                this.isDerived,
                this.board));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConnectionOverview#isDerived() isDerived} attribute.
     * A value equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for isDerived
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConnectionOverview withIsDerived(boolean value) {
        if (this.isDerived == value) {
            return this;
        }
        return validate(new ImmutableConnectionOverview(
                this,
                this.id,
                this.tags,
                this.name,
                this.configuredProperties,
                this.metadata,
                this.uses,
                this.organization,
                this.organizationId,
                this.connector,
                this.connectorId,
                this.options,
                this.icon,
                this.description,
                this.userId,
                this.lastUpdated,
                this.createdDate,
                value,
                this.board));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConnectionOverview#getBoard() board} attribute.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for board
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConnectionOverview withBoard(
            ConnectionBulletinBoard value) {
        if (this.board == value) {
            return this;
        }
        ConnectionBulletinBoard newValue =
                Objects.requireNonNull(value, "board");
        return validate(new ImmutableConnectionOverview(
                this,
                this.id,
                this.tags,
                this.name,
                this.configuredProperties,
                this.metadata,
                this.uses,
                this.organization,
                this.organizationId,
                this.connector,
                this.connectorId,
                this.options,
                this.icon,
                this.description,
                this.userId,
                this.lastUpdated,
                this.createdDate,
                this.isDerived,
                newValue));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableConnectionOverview} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableConnectionOverview
                && equalTo((ImmutableConnectionOverview) another);
    }

    private boolean equalTo(ImmutableConnectionOverview another) {
        return Objects.equals(id, another.id)
                && tags.equals(another.tags)
                && Objects.equals(name, another.name)
                && configuredProperties.equals(another.configuredProperties)
                && metadata.equals(another.metadata)
                && Objects.equals(organization, another.organization)
                && Objects.equals(organizationId, another.organizationId)
                && Objects.equals(connector, another.connector)
                && Objects.equals(connectorId, another.connectorId)
                && options.equals(another.options)
                && Objects.equals(icon, another.icon)
                && Objects.equals(description, another.description)
                && Objects.equals(userId, another.userId)
                && Objects.equals(lastUpdated, another.lastUpdated)
                && Objects.equals(createdDate, another.createdDate)
                && isDerived == another.isDerived
                && board.equals(another.board);
    }

    /**
     * Computes a hash code from attributes: {@code id}, {@code tags}, {@code name}, {@code configuredProperties}, {@code metadata}, {@code organization}, {@code organizationId}, {@code connector}, {@code connectorId}, {@code options}, {@code icon}, {@code description}, {@code userId}, {@code lastUpdated}, {@code createdDate}, {@code isDerived}, {@code board}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(id);
        h += (h << 5) + tags.hashCode();
        h += (h << 5) + Objects.hashCode(name);
        h += (h << 5) + configuredProperties.hashCode();
        h += (h << 5) + metadata.hashCode();
        h += (h << 5) + Objects.hashCode(organization);
        h += (h << 5) + Objects.hashCode(organizationId);
        h += (h << 5) + Objects.hashCode(connector);
        h += (h << 5) + Objects.hashCode(connectorId);
        h += (h << 5) + options.hashCode();
        h += (h << 5) + Objects.hashCode(icon);
        h += (h << 5) + Objects.hashCode(description);
        h += (h << 5) + Objects.hashCode(userId);
        h += (h << 5) + Objects.hashCode(lastUpdated);
        h += (h << 5) + Objects.hashCode(createdDate);
        h += (h << 5) + Boolean.hashCode(isDerived);
        h += (h << 5) + board.hashCode();
        return h;
    }

    /**
     * Prints the immutable value {@code ConnectionOverview} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("ConnectionOverview{");
        if (id != null) {
            builder.append("id=").append(id);
        }
        if (builder.length() > 19) {
            builder.append(", ");
        }
        builder.append("tags=").append(tags);
        if (name != null) {
            builder.append(", ");
            builder.append("name=").append(name);
        }
        builder.append(", ");
        builder.append("configuredProperties=").append(configuredProperties);
        builder.append(", ");
        builder.append("metadata=").append(metadata);
        if (organization != null) {
            builder.append(", ");
            builder.append("organization=").append(organization);
        }
        if (organizationId != null) {
            builder.append(", ");
            builder.append("organizationId=").append(organizationId);
        }
        if (connector != null) {
            builder.append(", ");
            builder.append("connector=").append(connector);
        }
        if (connectorId != null) {
            builder.append(", ");
            builder.append("connectorId=").append(connectorId);
        }
        builder.append(", ");
        builder.append("options=").append(options);
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
        builder.append(", ");
        builder.append("isDerived=").append(isDerived);
        builder.append(", ");
        builder.append("board=").append(board);
        return builder.append("}").toString();
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.connection.ConnectionOverview ConnectionOverview}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private static final long OPT_BIT_CONFIGURED_PROPERTIES = 0x1L;
        private static final long OPT_BIT_METADATA = 0x2L;
        private long optBits;

        private String id;
        private List<String> tags = new ArrayList<String>();
        private String name;
        private Map<String, String> configuredProperties =
                new LinkedHashMap<String, String>();
        private Map<String, String> metadata =
                new LinkedHashMap<String, String>();
        private int uses;
        private Organization organization;
        private String organizationId;
        private Connector connector;
        private String connectorId;
        private Map<String, String> options =
                new LinkedHashMap<String, String>();
        private String icon;
        private String description;
        private String userId;
        private Date lastUpdated;
        private Date createdDate;
        private boolean isDerived;
        private ConnectionBulletinBoard board;

        /**
         * Creates a builder for {@link io.syndesis.common.model.connection.ConnectionOverview ConnectionOverview} instances.
         * <pre>
         * new ConnectionOverview.Builder()
         *    .id(String) // optional {@link io.syndesis.common.model.connection.ConnectionOverview#getId() id}
         *    .addTag|addAllTags(String) // {@link io.syndesis.common.model.connection.ConnectionOverview#getTags() tags} elements
         *    .name(String | null) // nullable {@link io.syndesis.common.model.connection.ConnectionOverview#getName() name}
         *    .putConfiguredProperty|putAllConfiguredProperties(String =&gt; String) // {@link io.syndesis.common.model.connection.ConnectionOverview#getConfiguredProperties() configuredProperties} mappings
         *    .putMetadata|putAllMetadata(String =&gt; String) // {@link io.syndesis.common.model.connection.ConnectionOverview#getMetadata() metadata} mappings
         *    .uses(int) // optional {@link io.syndesis.common.model.connection.ConnectionOverview#getUses() uses}
         *    .organization(io.syndesis.common.io.syndesis.common.model.environment.Organization) // optional {@link io.syndesis.common.model.connection.ConnectionOverview#getOrganization() organization}
         *    .organizationId(String) // optional {@link io.syndesis.common.model.connection.ConnectionOverview#getOrganizationId() organizationId}
         *    .connector(io.syndesis.common.io.syndesis.common.model.connection.Connector) // optional {@link io.syndesis.common.model.connection.ConnectionOverview#getConnector() connector}
         *    .connectorId(String | null) // nullable {@link io.syndesis.common.model.connection.ConnectionOverview#getConnectorId() connectorId}
         *    .putOption|putAllOptions(String =&gt; String) // {@link io.syndesis.common.model.connection.ConnectionOverview#getOptions() options} mappings
         *    .icon(String | null) // nullable {@link io.syndesis.common.model.connection.ConnectionOverview#getIcon() icon}
         *    .description(String) // optional {@link io.syndesis.common.model.connection.ConnectionOverview#getDescription() description}
         *    .userId(String) // optional {@link io.syndesis.common.model.connection.ConnectionOverview#getUserId() userId}
         *    .lastUpdated(Date) // optional {@link io.syndesis.common.model.connection.ConnectionOverview#getLastUpdated() lastUpdated}
         *    .createdDate(Date) // optional {@link io.syndesis.common.model.connection.ConnectionOverview#getCreatedDate() createdDate}
         *    .isDerived(boolean) // optional {@link io.syndesis.common.model.connection.ConnectionOverview#isDerived() isDerived}
         *    .board(io.syndesis.common.io.syndesis.common.model.bulletin.ConnectionBulletinBoard) // optional {@link io.syndesis.common.model.connection.ConnectionOverview#getBoard() board}
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof ConnectionOverview.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new ConnectionOverview.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithName} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectionOverview.Builder createFrom(WithName instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (ConnectionOverview.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.connection.ConnectionOverview} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectionOverview.Builder createFrom(
                ConnectionOverview instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (ConnectionOverview.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithUsage} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectionOverview.Builder createFrom(WithUsage instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (ConnectionOverview.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithConfiguredProperties} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectionOverview.Builder createFrom(
                WithConfiguredProperties instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (ConnectionOverview.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithMetadata} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectionOverview.Builder createFrom(
                WithMetadata instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (ConnectionOverview.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithTags} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectionOverview.Builder createFrom(WithTags instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (ConnectionOverview.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithResourceId} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectionOverview.Builder createFrom(
                WithResourceId instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (ConnectionOverview.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.connection.ConnectionBase} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectionOverview.Builder createFrom(
                ConnectionBase instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (ConnectionOverview.Builder) this;
        }

        private void from(Object object) {
            if (object instanceof WithName) {
                WithName instance = (WithName) object;
                String nameValue = instance.getName();
                if (nameValue != null) {
                    name(nameValue);
                }
            }
            if (object instanceof ConnectionOverview) {
                ConnectionOverview instance = (ConnectionOverview) object;
                board(instance.getBoard());
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
            if (object instanceof ConnectionBase) {
                ConnectionBase instance = (ConnectionBase) object;
                Optional<String> organizationIdOptional =
                        instance.getOrganizationId();
                if (organizationIdOptional.isPresent()) {
                    organizationId(organizationIdOptional);
                }
                Optional<Date> lastUpdatedOptional = instance.getLastUpdated();
                if (lastUpdatedOptional.isPresent()) {
                    lastUpdated(lastUpdatedOptional);
                }
                isDerived(instance.isDerived());
                Optional<Date> createdDateOptional = instance.getCreatedDate();
                if (createdDateOptional.isPresent()) {
                    createdDate(createdDateOptional);
                }
                Optional<Connector> connectorOptional = instance.getConnector();
                if (connectorOptional.isPresent()) {
                    connector(connectorOptional);
                }
                String connectorIdValue = instance.getConnectorId();
                if (connectorIdValue != null) {
                    connectorId(connectorIdValue);
                }
                Optional<Organization> organizationOptional =
                        instance.getOrganization();
                if (organizationOptional.isPresent()) {
                    organization(organizationOptional);
                }
                String iconValue = instance.getIcon();
                if (iconValue != null) {
                    icon(iconValue);
                }
                putAllOptions(instance.getOptions());
                Optional<String> descriptionOptional =
                        instance.getDescription();
                if (descriptionOptional.isPresent()) {
                    description(descriptionOptional);
                }
                Optional<String> userIdOptional = instance.getUserId();
                if (userIdOptional.isPresent()) {
                    userId(userIdOptional);
                }
            }
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.ConnectionOverview#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectionOverview.Builder id(String id) {
            this.id = Objects.requireNonNull(id, "id");
            return (ConnectionOverview.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.ConnectionOverview#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("id")
        public final ConnectionOverview.Builder id(Optional<String> id) {
            this.id = id.orElse(null);
            return (ConnectionOverview.Builder) this;
        }

        /**
         * Adds one element to {@link io.syndesis.common.model.connection.ConnectionOverview#getTags() tags} sortedSet.
         *
         * @param element A tags element
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectionOverview.Builder addTag(String element) {
            if (element != null) {
                this.tags.add(element);
            }
            return (ConnectionOverview.Builder) this;
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.connection.ConnectionOverview#getTags() tags} sortedSet.
         *
         * @param elements An array of tags elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectionOverview.Builder addTags(String... elements) {
            for (String element : elements) {
                if (element != null) {
                    this.tags.add(element);
                }
            }
            return (ConnectionOverview.Builder) this;
        }


        /**
         * Sets or replaces all elements for {@link io.syndesis.common.model.connection.ConnectionOverview#getTags() tags} sortedSet.
         *
         * @param elements An iterable of tags elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("tags")
        public final ConnectionOverview.Builder tags(
                Iterable<String> elements) {
            this.tags.clear();
            return addAllTags(elements);
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.connection.ConnectionOverview#getTags() tags} sortedSet.
         *
         * @param elements An iterable of tags elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectionOverview.Builder addAllTags(
                Iterable<String> elements) {
            for (String element : elements) {
                if (element != null) {
                    this.tags.add(element);
                }
            }
            return (ConnectionOverview.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConnectionOverview#getName() name} attribute.
         *
         * @param name The value for name (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("name")
        public final ConnectionOverview.Builder name(String name) {
            this.name = name;
            return (ConnectionOverview.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.connection.ConnectionOverview#getConfiguredProperties() configuredProperties} map.
         *
         * @param key   The key in the configuredProperties map
         * @param value The associated value in the configuredProperties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectionOverview.Builder putConfiguredProperty(
                String key, String value) {
            this.configuredProperties.put(
                    Objects.requireNonNull(key, "configuredProperties key"),
                    Objects.requireNonNull(value,
                            "configuredProperties value"));
            optBits |= OPT_BIT_CONFIGURED_PROPERTIES;
            return (ConnectionOverview.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.connection.ConnectionOverview#getConfiguredProperties() configuredProperties} map. Nulls are not permitted
         *
         * @param entry The key and value entry
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectionOverview.Builder putConfiguredProperty(
                Map.Entry<String, ? extends String> entry) {
            String k = entry.getKey();
            String v = entry.getValue();
            this.configuredProperties.put(
                    Objects.requireNonNull(k, "configuredProperties key"),
                    Objects.requireNonNull(v, "configuredProperties value"));
            optBits |= OPT_BIT_CONFIGURED_PROPERTIES;
            return (ConnectionOverview.Builder) this;
        }

        /**
         * Sets or replaces all mappings from the specified map as entries for the {@link io.syndesis.common.model.connection.ConnectionOverview#getConfiguredProperties() configuredProperties} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the configuredProperties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("configuredProperties")
        public final ConnectionOverview.Builder configuredProperties(
                Map<String, ? extends String> entries) {
            this.configuredProperties.clear();
            optBits |= OPT_BIT_CONFIGURED_PROPERTIES;
            return putAllConfiguredProperties(entries);
        }

        /**
         * Put all mappings from the specified map as entries to {@link io.syndesis.common.model.connection.ConnectionOverview#getConfiguredProperties() configuredProperties} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the configuredProperties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectionOverview.Builder putAllConfiguredProperties(
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
            return (ConnectionOverview.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.connection.ConnectionOverview#getMetadata() metadata} map.
         *
         * @param key   The key in the metadata map
         * @param value The associated value in the metadata map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectionOverview.Builder putMetadata(String key,
                                                            String value) {
            this.metadata.put(
                    Objects.requireNonNull(key, "metadata key"),
                    Objects.requireNonNull(value, "metadata value"));
            optBits |= OPT_BIT_METADATA;
            return (ConnectionOverview.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.connection.ConnectionOverview#getMetadata() metadata} map. Nulls are not permitted
         *
         * @param entry The key and value entry
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectionOverview.Builder putMetadata(
                Map.Entry<String, ? extends String> entry) {
            String k = entry.getKey();
            String v = entry.getValue();
            this.metadata.put(
                    Objects.requireNonNull(k, "metadata key"),
                    Objects.requireNonNull(v, "metadata value"));
            optBits |= OPT_BIT_METADATA;
            return (ConnectionOverview.Builder) this;
        }

        /**
         * Sets or replaces all mappings from the specified map as entries for the {@link io.syndesis.common.model.connection.ConnectionOverview#getMetadata() metadata} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the metadata map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("metadata")
        public final ConnectionOverview.Builder metadata(
                Map<String, ? extends String> entries) {
            this.metadata.clear();
            optBits |= OPT_BIT_METADATA;
            return putAllMetadata(entries);
        }

        /**
         * Put all mappings from the specified map as entries to {@link io.syndesis.common.model.connection.ConnectionOverview#getMetadata() metadata} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the metadata map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectionOverview.Builder putAllMetadata(
                Map<String, ? extends String> entries) {
            for (Map.Entry<String, ? extends String> e : entries.entrySet()) {
                String k = e.getKey();
                String v = e.getValue();
                this.metadata.put(
                        Objects.requireNonNull(k, "metadata key"),
                        Objects.requireNonNull(v, "metadata value"));
            }
            optBits |= OPT_BIT_METADATA;
            return (ConnectionOverview.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConnectionOverview#getUses() uses} attribute.
         *
         * @param uses The value for uses
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        public final ConnectionOverview.Builder uses(int uses) {
            this.uses = uses;
            return (ConnectionOverview.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.ConnectionOverview#getOrganization() organization} to organization.
         *
         * @param organization The value for organization
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectionOverview.Builder organization(
                Organization organization) {
            this.organization =
                    Objects.requireNonNull(organization, "organization");
            return (ConnectionOverview.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.ConnectionOverview#getOrganization() organization} to organization.
         *
         * @param organization The value for organization
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("organization")
        public final ConnectionOverview.Builder organization(
                Optional<? extends Organization> organization) {
            this.organization = organization.orElse(null);
            return (ConnectionOverview.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.ConnectionOverview#getOrganizationId() organizationId} to organizationId.
         *
         * @param organizationId The value for organizationId
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectionOverview.Builder organizationId(
                String organizationId) {
            this.organizationId =
                    Objects.requireNonNull(organizationId, "organizationId");
            return (ConnectionOverview.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.ConnectionOverview#getOrganizationId() organizationId} to organizationId.
         *
         * @param organizationId The value for organizationId
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("organizationId")
        public final ConnectionOverview.Builder organizationId(
                Optional<String> organizationId) {
            this.organizationId = organizationId.orElse(null);
            return (ConnectionOverview.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.ConnectionOverview#getConnector() connector} to connector.
         *
         * @param connector The value for connector
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectionOverview.Builder connector(Connector connector) {
            this.connector = Objects.requireNonNull(connector, "connector");
            return (ConnectionOverview.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.ConnectionOverview#getConnector() connector} to connector.
         *
         * @param connector The value for connector
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("connector")
        public final ConnectionOverview.Builder connector(
                Optional<? extends Connector> connector) {
            this.connector = connector.orElse(null);
            return (ConnectionOverview.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConnectionOverview#getConnectorId() connectorId} attribute.
         *
         * @param connectorId The value for connectorId (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("connectorId")
        public final ConnectionOverview.Builder connectorId(
                String connectorId) {
            this.connectorId = connectorId;
            return (ConnectionOverview.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.connection.ConnectionOverview#getOptions() options} map.
         *
         * @param key   The key in the options map
         * @param value The associated value in the options map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectionOverview.Builder putOption(String key,
                                                          String value) {
            this.options.put(
                    Objects.requireNonNull(key, "options key"),
                    Objects.requireNonNull(value, "options value"));
            return (ConnectionOverview.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.connection.ConnectionOverview#getOptions() options} map. Nulls are not permitted
         *
         * @param entry The key and value entry
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectionOverview.Builder putOption(
                Map.Entry<String, ? extends String> entry) {
            String k = entry.getKey();
            String v = entry.getValue();
            this.options.put(
                    Objects.requireNonNull(k, "options key"),
                    Objects.requireNonNull(v, "options value"));
            return (ConnectionOverview.Builder) this;
        }

        /**
         * Sets or replaces all mappings from the specified map as entries for the {@link io.syndesis.common.model.connection.ConnectionOverview#getOptions() options} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the options map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("options")
        public final ConnectionOverview.Builder options(
                Map<String, ? extends String> entries) {
            this.options.clear();
            return putAllOptions(entries);
        }

        /**
         * Put all mappings from the specified map as entries to {@link io.syndesis.common.model.connection.ConnectionOverview#getOptions() options} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the options map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectionOverview.Builder putAllOptions(
                Map<String, ? extends String> entries) {
            for (Map.Entry<String, ? extends String> e : entries.entrySet()) {
                String k = e.getKey();
                String v = e.getValue();
                this.options.put(
                        Objects.requireNonNull(k, "options key"),
                        Objects.requireNonNull(v, "options value"));
            }
            return (ConnectionOverview.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConnectionOverview#getIcon() icon} attribute.
         *
         * @param icon The value for icon (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("icon")
        public final ConnectionOverview.Builder icon(String icon) {
            this.icon = icon;
            return (ConnectionOverview.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.ConnectionOverview#getDescription() description} to description.
         *
         * @param description The value for description
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectionOverview.Builder description(
                String description) {
            this.description =
                    Objects.requireNonNull(description, "description");
            return (ConnectionOverview.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.ConnectionOverview#getDescription() description} to description.
         *
         * @param description The value for description
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("description")
        public final ConnectionOverview.Builder description(
                Optional<String> description) {
            this.description = description.orElse(null);
            return (ConnectionOverview.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.ConnectionOverview#getUserId() userId} to userId.
         *
         * @param userId The value for userId
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectionOverview.Builder userId(String userId) {
            this.userId = Objects.requireNonNull(userId, "userId");
            return (ConnectionOverview.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.ConnectionOverview#getUserId() userId} to userId.
         *
         * @param userId The value for userId
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("userId")
        public final ConnectionOverview.Builder userId(
                Optional<String> userId) {
            this.userId = userId.orElse(null);
            return (ConnectionOverview.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.ConnectionOverview#getLastUpdated() lastUpdated} to lastUpdated.
         *
         * @param lastUpdated The value for lastUpdated
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectionOverview.Builder lastUpdated(Date lastUpdated) {
            this.lastUpdated =
                    Objects.requireNonNull(lastUpdated, "lastUpdated");
            return (ConnectionOverview.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.ConnectionOverview#getLastUpdated() lastUpdated} to lastUpdated.
         *
         * @param lastUpdated The value for lastUpdated
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("lastUpdated")
        public final ConnectionOverview.Builder lastUpdated(
                Optional<? extends Date> lastUpdated) {
            this.lastUpdated = lastUpdated.orElse(null);
            return (ConnectionOverview.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.ConnectionOverview#getCreatedDate() createdDate} to createdDate.
         *
         * @param createdDate The value for createdDate
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectionOverview.Builder createdDate(Date createdDate) {
            this.createdDate =
                    Objects.requireNonNull(createdDate, "createdDate");
            return (ConnectionOverview.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.ConnectionOverview#getCreatedDate() createdDate} to createdDate.
         *
         * @param createdDate The value for createdDate
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("createdDate")
        public final ConnectionOverview.Builder createdDate(
                Optional<? extends Date> createdDate) {
            this.createdDate = createdDate.orElse(null);
            return (ConnectionOverview.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConnectionOverview#isDerived() isDerived} attribute.
         *
         * @param isDerived The value for isDerived
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("isDerived")
        public final ConnectionOverview.Builder isDerived(boolean isDerived) {
            this.isDerived = isDerived;
            return (ConnectionOverview.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConnectionOverview#getBoard() board} attribute.
         * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link io.syndesis.common.model.connection.ConnectionOverview#getBoard() board}.</em>
         *
         * @param board The value for board
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("board")
        public final ConnectionOverview.Builder board(
                ConnectionBulletinBoard board) {
            this.board = Objects.requireNonNull(board, "board");
            return (ConnectionOverview.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.connection.ConnectionOverview ConnectionOverview}.
         *
         * @return An immutable instance of ConnectionOverview
         * @throws IllegalStateException if any required attributes are missing
         */
        public ConnectionOverview build() {
            return ImmutableConnectionOverview.validate(
                    new ImmutableConnectionOverview(this));
        }

        private boolean configuredPropertiesIsSet() {
            return (optBits & OPT_BIT_CONFIGURED_PROPERTIES) != 0;
        }

        private boolean metadataIsSet() {
            return (optBits & OPT_BIT_METADATA) != 0;
        }
    }

    private final class InitShim {
        private byte configuredPropertiesBuildStage = STAGE_UNINITIALIZED;
        private Map<String, String> configuredProperties;
        private byte metadataBuildStage = STAGE_UNINITIALIZED;
        private Map<String, String> metadata;
        private byte boardBuildStage = STAGE_UNINITIALIZED;
        private ConnectionBulletinBoard board;

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

        ConnectionBulletinBoard getBoard() {
            if (boardBuildStage == STAGE_INITIALIZING) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (boardBuildStage == STAGE_UNINITIALIZED) {
                boardBuildStage = STAGE_INITIALIZING;
                this.board =
                        Objects.requireNonNull(getBoardInitialize(), "board");
                boardBuildStage = STAGE_INITIALIZED;
            }
            return this.board;
        }

        void board(ConnectionBulletinBoard board) {
            this.board = board;
            boardBuildStage = STAGE_INITIALIZED;
        }

        private String formatInitCycleMessage() {
            List<String> attributes = new ArrayList<>();
            if (configuredPropertiesBuildStage == STAGE_INITIALIZING) {
                attributes.add("configuredProperties");
            }
            if (metadataBuildStage == STAGE_INITIALIZING) {
                attributes.add("metadata");
            }
            if (boardBuildStage == STAGE_INITIALIZING) {
                attributes.add("board");
            }
            return "Cannot build ConnectionOverview, attribute initializers form cycle "
                    + attributes;
        }
    }
}
