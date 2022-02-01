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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Var;
import io.syndesis.common.model.Dependency;
import io.syndesis.common.model.Kind;
import io.syndesis.common.model.WithDependencies;
import io.syndesis.common.model.WithKind;
import io.syndesis.common.model.WithMetadata;
import io.syndesis.common.model.WithName;
import io.syndesis.common.model.WithResourceId;
import io.syndesis.common.model.WithTags;
import io.syndesis.common.model.connection.Connection;

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
 * Immutable implementation of {@link io.syndesis.common.model.integration.Flow}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new Flow.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableFlow.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableFlow implements Flow {
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private static final byte STAGE_INITIALIZED = 1;
    private final String name;
    private final Kind kind;
    private final SortedSet<String> tags;
    private final List<Step> steps;
    private final Map<String, String> metadata;
    private final List<Dependency> dependencies;
    private final FlowType type;
    private final List<Connection> connections;
    private final Scheduler scheduler;
    private final String description;
    @SuppressWarnings("Immutable")
    private transient volatile InitShim initShim = new InitShim();

    @SuppressWarnings("unchecked") // safe covariant cast
    private ImmutableFlow(
            String name,
            Optional<String> id,
            Kind kind,
            Iterable<String> tags,
            Iterable<? extends Step> steps,
            Map<String, ? extends String> metadata,
            Iterable<? extends Dependency> dependencies,
            FlowType type,
            Iterable<? extends Connection> connections,
            Optional<? extends Scheduler> scheduler,
            Optional<String> description) {
        this.name = name;
        this.kind = kind;
        this.tags = createUnmodifiableSortedSet(false,
                createSafeList(tags, false, true));
        this.steps = createUnmodifiableList(false,
                createSafeList(steps, true, false));
        this.metadata = createUnmodifiableMap(true, false, metadata);
        this.dependencies = createUnmodifiableList(false,
                createSafeList(dependencies, true, false));
        this.type = Objects.requireNonNull(type, "type");
        this.connections = createUnmodifiableList(false,
                createSafeList(connections, true, false));
        this.scheduler = scheduler.orElse(null);
        this.description = description.orElse(null);
        this.initShim = null;
    }

    private ImmutableFlow(Builder builder) {
        this.name = builder.name;
        this.kind = builder.kind;
        this.tags = createUnmodifiableSortedSet(false,
                createSafeList(builder.tags, false, false));
        this.scheduler = builder.scheduler;
        this.description = builder.description;
        if (builder.stepsIsSet()) {
            initShim.steps(createUnmodifiableList(true, builder.steps));
        }
        if (builder.metadataIsSet()) {
            initShim.metadata(
                    createUnmodifiableMap(false, false, builder.metadata));
        }
        if (builder.dependenciesIsSet()) {
            initShim.dependencies(
                    createUnmodifiableList(true, builder.dependencies));
        }
        if (builder.type != null) {
            initShim.type(builder.type);
        }
        if (builder.connectionsIsSet()) {
            initShim.connections(
                    createUnmodifiableList(true, builder.connections));
        }
        this.steps = initShim.getSteps();
        this.metadata = initShim.getMetadata();
        this.dependencies = initShim.getDependencies();
        this.type = initShim.getType();
        this.connections = initShim.getConnections();
        this.initShim = null;
    }

    private ImmutableFlow(
            ImmutableFlow original,
            String name,
            String id,
            Kind kind,
            SortedSet<String> tags,
            List<Step> steps,
            Map<String, String> metadata,
            List<Dependency> dependencies,
            FlowType type,
            List<Connection> connections,
            Scheduler scheduler,
            String description) {
        this.name = name;
        this.kind = kind;
        this.tags = tags;
        this.steps = steps;
        this.metadata = metadata;
        this.dependencies = dependencies;
        this.type = type;
        this.connections = connections;
        this.scheduler = scheduler;
        this.description = description;
        this.initShim = null;
    }

    /**
     * Construct a new immutable {@code Flow} instance.
     *
     * @param name         The value for the {@code name} attribute
     * @param id           The value for the {@code id} attribute
     * @param kind         The value for the {@code kind} attribute
     * @param tags         The value for the {@code tags} attribute
     * @param steps        The value for the {@code steps} attribute
     * @param metadata     The value for the {@code metadata} attribute
     * @param dependencies The value for the {@code dependencies} attribute
     * @param type         The value for the {@code type} attribute
     * @param connections  The value for the {@code connections} attribute
     * @param scheduler    The value for the {@code scheduler} attribute
     * @param description  The value for the {@code description} attribute
     * @return An immutable Flow instance
     */
    public static Flow of(String name, Optional<String> id, Kind kind,
                          SortedSet<String> tags, List<Step> steps,
                          Map<String, String> metadata,
                          List<Dependency> dependencies, FlowType type,
                          List<Connection> connections,
                          Optional<Scheduler> scheduler,
                          Optional<String> description) {
        return of(name, id, kind, (Iterable<String>) tags,
                (Iterable<? extends Step>) steps, metadata,
                (Iterable<? extends Dependency>) dependencies, type,
                (Iterable<? extends Connection>) connections, scheduler,
                description);
    }

    /**
     * Construct a new immutable {@code Flow} instance.
     *
     * @param name         The value for the {@code name} attribute
     * @param id           The value for the {@code id} attribute
     * @param kind         The value for the {@code kind} attribute
     * @param tags         The value for the {@code tags} attribute
     * @param steps        The value for the {@code steps} attribute
     * @param metadata     The value for the {@code metadata} attribute
     * @param dependencies The value for the {@code dependencies} attribute
     * @param type         The value for the {@code type} attribute
     * @param connections  The value for the {@code connections} attribute
     * @param scheduler    The value for the {@code scheduler} attribute
     * @param description  The value for the {@code description} attribute
     * @return An immutable Flow instance
     */
    public static Flow of(String name, Optional<String> id, Kind kind,
                          Iterable<String> tags, Iterable<? extends Step> steps,
                          Map<String, ? extends String> metadata,
                          Iterable<? extends Dependency> dependencies,
                          FlowType type,
                          Iterable<? extends Connection> connections,
                          Optional<? extends Scheduler> scheduler,
                          Optional<String> description) {
        return validate(new ImmutableFlow(name, id, kind, tags, steps, metadata,
                dependencies, type, connections, scheduler, description));
    }

    private static ImmutableFlow validate(ImmutableFlow instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.integration.Flow} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable Flow instance
     */
    public static Flow copyOf(Flow instance) {
        if (instance instanceof ImmutableFlow) {
            return (ImmutableFlow) instance;
        }
        return new Flow.Builder()
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

    private List<Step> getStepsInitialize() {
        return Flow.super.getSteps();
    }

    private Map<String, String> getMetadataInitialize() {
        return Flow.super.getMetadata();
    }

    private List<Dependency> getDependenciesInitialize() {
        return Flow.super.getDependencies();
    }

    private FlowType getTypeInitialize() {
        return Flow.super.getType();
    }

    private List<Connection> getConnectionsInitialize() {
        return Flow.super.getConnections();
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
     * @return The value of the {@code steps} attribute
     */
    @JsonProperty("steps")
    @Override
    public List<Step> getSteps() {
        InitShim shim = this.initShim;
        return shim != null
                ? shim.getSteps()
                : this.steps;
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
     * @return The value of the {@code type} attribute
     */
    @JsonProperty("type")
    @Override
    public FlowType getType() {
        InitShim shim = this.initShim;
        return shim != null
                ? shim.getType()
                : this.type;
    }

    /**
     * @return The value of the {@code connections} attribute
     */
    @JsonProperty("connections")
    @Override
    public List<Connection> getConnections() {
        InitShim shim = this.initShim;
        return shim != null
                ? shim.getConnections()
                : this.connections;
    }

    /**
     * @return The value of the {@code scheduler} attribute
     */
    @JsonProperty("scheduler")
    @Override
    public Scheduler getScheduler() {
        return scheduler;
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
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.integration.Flow#getName() name} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for name (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableFlow withName(String value) {
        if (Objects.equals(this.name, value)) {
            return this;
        }
        return validate(new ImmutableFlow(
                this,
                value,
                null,
                this.kind,
                this.tags,
                this.steps,
                this.metadata,
                this.dependencies,
                this.type,
                this.connections,
                this.scheduler,
                this.description));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.integration.Flow#getId() id} attribute.
     *
     * @param value The value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableFlow withId(String value) {
        String newValue = Objects.requireNonNull(value, "id");
        return validate(new ImmutableFlow(
                this,
                this.name,
                newValue,
                this.kind,
                this.tags,
                this.steps,
                this.metadata,
                this.dependencies,
                this.type,
                this.connections,
                this.scheduler,
                this.description));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.integration.Flow#getId() id} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableFlow withId(Optional<String> optional) {
        String value = optional.orElse(null);
        return validate(new ImmutableFlow(
                this,
                this.name,
                value,
                this.kind,
                this.tags,
                this.steps,
                this.metadata,
                this.dependencies,
                this.type,
                this.connections,
                this.scheduler,
                this.description));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.integration.Flow#getKind() kind} attribute.
     * A value equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for kind (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableFlow withKind(Kind value) {
        if (this.kind == value) {
            return this;
        }
        if (Objects.equals(this.kind, value)) {
            return this;
        }
        return validate(new ImmutableFlow(
                this,
                this.name,
                null,
                value,
                this.tags,
                this.steps,
                this.metadata,
                this.dependencies,
                this.type,
                this.connections,
                this.scheduler,
                this.description));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.integration.Flow#getTags() tags}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableFlow withTags(String... elements) {
        SortedSet<String> newValue = createUnmodifiableSortedSet(false,
                createSafeList(Arrays.asList(elements), false, true));
        return validate(new ImmutableFlow(
                this,
                this.name,
                null,
                this.kind,
                newValue,
                this.steps,
                this.metadata,
                this.dependencies,
                this.type,
                this.connections,
                this.scheduler,
                this.description));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.integration.Flow#getTags() tags}.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of tags elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableFlow withTags(Iterable<String> elements) {
        if (this.tags == elements) {
            return this;
        }
        SortedSet<String> newValue = createUnmodifiableSortedSet(false,
                createSafeList(elements, false, true));
        return validate(new ImmutableFlow(
                this,
                this.name,
                null,
                this.kind,
                newValue,
                this.steps,
                this.metadata,
                this.dependencies,
                this.type,
                this.connections,
                this.scheduler,
                this.description));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.integration.Flow#getSteps() steps}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableFlow withSteps(Step... elements) {
        List<Step> newValue = createUnmodifiableList(false,
                createSafeList(Arrays.asList(elements), true, false));
        return validate(new ImmutableFlow(
                this,
                this.name,
                null,
                this.kind,
                this.tags,
                newValue,
                this.metadata,
                this.dependencies,
                this.type,
                this.connections,
                this.scheduler,
                this.description));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.integration.Flow#getSteps() steps}.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of steps elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableFlow withSteps(Iterable<? extends Step> elements) {
        if (this.steps == elements) {
            return this;
        }
        List<Step> newValue = createUnmodifiableList(false,
                createSafeList(elements, true, false));
        return validate(new ImmutableFlow(
                this,
                this.name,
                null,
                this.kind,
                this.tags,
                newValue,
                this.metadata,
                this.dependencies,
                this.type,
                this.connections,
                this.scheduler,
                this.description));
    }

    /**
     * Copy the current immutable object by replacing the {@link io.syndesis.common.model.integration.Flow#getMetadata() metadata} map with the specified map.
     * Nulls are not permitted as keys or values.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param entries The entries to be added to the metadata map
     * @return A modified copy of {@code this} object
     */
    public final ImmutableFlow withMetadata(
            Map<String, ? extends String> entries) {
        if (this.metadata == entries) {
            return this;
        }
        Map<String, String> newValue =
                createUnmodifiableMap(true, false, entries);
        return validate(new ImmutableFlow(
                this,
                this.name,
                null,
                this.kind,
                this.tags,
                this.steps,
                newValue,
                this.dependencies,
                this.type,
                this.connections,
                this.scheduler,
                this.description));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.integration.Flow#getDependencies() dependencies}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableFlow withDependencies(Dependency... elements) {
        List<Dependency> newValue = createUnmodifiableList(false,
                createSafeList(Arrays.asList(elements), true, false));
        return validate(new ImmutableFlow(
                this,
                this.name,
                null,
                this.kind,
                this.tags,
                this.steps,
                this.metadata,
                newValue,
                this.type,
                this.connections,
                this.scheduler,
                this.description));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.integration.Flow#getDependencies() dependencies}.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of dependencies elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableFlow withDependencies(
            Iterable<? extends Dependency> elements) {
        if (this.dependencies == elements) {
            return this;
        }
        List<Dependency> newValue = createUnmodifiableList(false,
                createSafeList(elements, true, false));
        return validate(new ImmutableFlow(
                this,
                this.name,
                null,
                this.kind,
                this.tags,
                this.steps,
                this.metadata,
                newValue,
                this.type,
                this.connections,
                this.scheduler,
                this.description));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.integration.Flow#getType() type} attribute.
     * A value equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for type
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableFlow withType(FlowType value) {
        if (this.type == value) {
            return this;
        }
        FlowType newValue = Objects.requireNonNull(value, "type");
        if (this.type.equals(newValue)) {
            return this;
        }
        return validate(new ImmutableFlow(
                this,
                this.name,
                null,
                this.kind,
                this.tags,
                this.steps,
                this.metadata,
                this.dependencies,
                newValue,
                this.connections,
                this.scheduler,
                this.description));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.integration.Flow#getConnections() connections}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableFlow withConnections(Connection... elements) {
        List<Connection> newValue = createUnmodifiableList(false,
                createSafeList(Arrays.asList(elements), true, false));
        return validate(new ImmutableFlow(
                this,
                this.name,
                null,
                this.kind,
                this.tags,
                this.steps,
                this.metadata,
                this.dependencies,
                this.type,
                newValue,
                this.scheduler,
                this.description));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.integration.Flow#getConnections() connections}.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of connections elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableFlow withConnections(
            Iterable<? extends Connection> elements) {
        if (this.connections == elements) {
            return this;
        }
        List<Connection> newValue = createUnmodifiableList(false,
                createSafeList(elements, true, false));
        return validate(new ImmutableFlow(
                this,
                this.name,
                null,
                this.kind,
                this.tags,
                this.steps,
                this.metadata,
                this.dependencies,
                this.type,
                newValue,
                this.scheduler,
                this.description));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.integration.Flow#getScheduler() scheduler} attribute.
     *
     * @param value The value for scheduler
     * @return A modified copy of {@code this} object
     */
    public final ImmutableFlow withScheduler(Scheduler value) {
        Scheduler newValue = Objects.requireNonNull(value, "scheduler");
        if (this.scheduler == newValue) {
            return this;
        }
        return validate(new ImmutableFlow(
                this,
                this.name,
                null,
                this.kind,
                this.tags,
                this.steps,
                this.metadata,
                this.dependencies,
                this.type,
                this.connections,
                newValue,
                this.description));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.integration.Flow#getScheduler() scheduler} attribute.
     * A shallow reference equality check is used on unboxed optional value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for scheduler
     * @return A modified copy of {@code this} object
     */
    @SuppressWarnings("unchecked") // safe covariant cast
    public final ImmutableFlow withScheduler(
            Optional<? extends Scheduler> optional) {
        Scheduler value = optional.orElse(null);
        if (this.scheduler == value) {
            return this;
        }
        return validate(new ImmutableFlow(
                this,
                this.name,
                null,
                this.kind,
                this.tags,
                this.steps,
                this.metadata,
                this.dependencies,
                this.type,
                this.connections,
                value,
                this.description));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.integration.Flow#getDescription() description} attribute.
     *
     * @param value The value for description
     * @return A modified copy of {@code this} object
     */
    public final ImmutableFlow withDescription(String value) {
        String newValue = Objects.requireNonNull(value, "description");
        if (Objects.equals(this.description, newValue)) {
            return this;
        }
        return validate(new ImmutableFlow(
                this,
                this.name,
                null,
                this.kind,
                this.tags,
                this.steps,
                this.metadata,
                this.dependencies,
                this.type,
                this.connections,
                this.scheduler,
                newValue));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.integration.Flow#getDescription() description} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for description
     * @return A modified copy of {@code this} object
     */
    public final ImmutableFlow withDescription(Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.description, value)) {
            return this;
        }
        return validate(new ImmutableFlow(
                this,
                this.name,
                null,
                this.kind,
                this.tags,
                this.steps,
                this.metadata,
                this.dependencies,
                this.type,
                this.connections,
                this.scheduler,
                value));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableFlow} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableFlow
                && equalTo((ImmutableFlow) another);
    }

    private boolean equalTo(ImmutableFlow another) {
        return Objects.equals(name, another.name)
                && Objects.equals(kind, another.kind)
                && tags.equals(another.tags)
                && steps.equals(another.steps)
                && metadata.equals(another.metadata)
                && dependencies.equals(another.dependencies)
                && type.equals(another.type)
                && connections.equals(another.connections)
                && Objects.equals(scheduler, another.scheduler)
                && Objects.equals(description, another.description);
    }

    /**
     * Computes a hash code from attributes: {@code name}, {@code id}, {@code kind}, {@code tags}, {@code steps}, {@code metadata}, {@code dependencies}, {@code type}, {@code connections}, {@code scheduler}, {@code description}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(name);
        h += (h << 5) + Objects.hashCode(kind);
        h += (h << 5) + tags.hashCode();
        h += (h << 5) + steps.hashCode();
        h += (h << 5) + metadata.hashCode();
        h += (h << 5) + dependencies.hashCode();
        h += (h << 5) + type.hashCode();
        h += (h << 5) + connections.hashCode();
        h += (h << 5) + Objects.hashCode(scheduler);
        h += (h << 5) + Objects.hashCode(description);
        return h;
    }

    /**
     * Prints the immutable value {@code Flow} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Flow{");
        if (name != null) {
            builder.append("name=").append(name);
        }
        if (kind != null) {
            if (builder.length() > 5) {
                builder.append(", ");
            }
            builder.append("kind=").append(kind);
        }
        if (builder.length() > 5) {
            builder.append(", ");
        }
        builder.append("tags=").append(tags);
        builder.append(", ");
        builder.append("steps=").append(steps);
        builder.append(", ");
        builder.append("metadata=").append(metadata);
        builder.append(", ");
        builder.append("dependencies=").append(dependencies);
        builder.append(", ");
        builder.append("type=").append(type);
        builder.append(", ");
        builder.append("connections=").append(connections);
        if (scheduler != null) {
            builder.append(", ");
            builder.append("scheduler=").append(scheduler);
        }
        if (description != null) {
            builder.append(", ");
            builder.append("description=").append(description);
        }
        return builder.append("}").toString();
    }

    private Object readResolve() throws ObjectStreamException {
        return validate(this);
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.integration.Flow Flow}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private static final long OPT_BIT_STEPS = 0x1L;
        private static final long OPT_BIT_METADATA = 0x2L;
        private static final long OPT_BIT_DEPENDENCIES = 0x4L;
        private static final long OPT_BIT_CONNECTIONS = 0x8L;
        private long optBits;

        private String name;
        private String id;
        private Kind kind;
        private List<String> tags = new ArrayList<String>();
        private List<Step> steps = new ArrayList<Step>();
        private Map<String, String> metadata =
                new LinkedHashMap<String, String>();
        private List<Dependency> dependencies = new ArrayList<Dependency>();
        private Flow.FlowType type;
        private List<Connection> connections = new ArrayList<Connection>();
        private Scheduler scheduler;
        private String description;

        /**
         * Creates a builder for {@link io.syndesis.common.model.integration.Flow Flow} instances.
         * <pre>
         * new Flow.Builder()
         *    .name(String | null) // nullable {@link io.syndesis.common.model.integration.Flow#getName() name}
         *    .id(String) // optional {@link io.syndesis.common.model.integration.Flow#getId() id}
         *    .kind(io.syndesis.common.io.syndesis.common.model.Kind | null) // nullable {@link io.syndesis.common.model.integration.Flow#getKind() kind}
         *    .addTag|addAllTags(String) // {@link io.syndesis.common.model.integration.Flow#getTags() tags} elements
         *    .addStep|addAllSteps(io.syndesis.common.io.syndesis.common.model.integration.Step) // {@link io.syndesis.common.model.integration.Flow#getSteps() steps} elements
         *    .putMetadata|putAllMetadata(String =&gt; String) // {@link io.syndesis.common.model.integration.Flow#getMetadata() metadata} mappings
         *    .addDependency|addAllDependencies(io.syndesis.common.io.syndesis.common.model.Dependency) // {@link io.syndesis.common.model.integration.Flow#getDependencies() dependencies} elements
         *    .type(io.syndesis.common.io.syndesis.common.model.integration.Flow.FlowType) // optional {@link io.syndesis.common.model.integration.Flow#getType() type}
         *    .addConnection|addAllConnections(io.syndesis.common.io.syndesis.common.model.connection.Connection) // {@link io.syndesis.common.model.integration.Flow#getConnections() connections} elements
         *    .scheduler(io.syndesis.common.io.syndesis.common.model.integration.Scheduler) // optional {@link io.syndesis.common.model.integration.Flow#getScheduler() scheduler}
         *    .description(String) // optional {@link io.syndesis.common.model.integration.Flow#getDescription() description}
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof Flow.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new Flow.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithName} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Flow.Builder createFrom(WithName instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Flow.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.integration.WithSteps} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Flow.Builder createFrom(WithSteps instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Flow.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithKind} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Flow.Builder createFrom(WithKind instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Flow.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithDependencies} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Flow.Builder createFrom(WithDependencies instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Flow.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithMetadata} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Flow.Builder createFrom(WithMetadata instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Flow.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithResourceId} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Flow.Builder createFrom(WithResourceId instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Flow.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithTags} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Flow.Builder createFrom(WithTags instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Flow.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.integration.Flow} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Flow.Builder createFrom(Flow instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Flow.Builder) this;
        }

        private void from(Object object) {
            if (object instanceof WithName) {
                WithName instance = (WithName) object;
                String nameValue = instance.getName();
                if (nameValue != null) {
                    name(nameValue);
                }
            }
            if (object instanceof WithSteps) {
                WithSteps instance = (WithSteps) object;
                addAllSteps(instance.getSteps());
            }
            if (object instanceof WithKind) {
                WithKind instance = (WithKind) object;
                Kind kindValue = instance.getKind();
                if (kindValue != null) {
                    kind(kindValue);
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
            if (object instanceof WithResourceId) {
                WithResourceId instance = (WithResourceId) object;
                Optional<String> idOptional = instance.getId();
                if (idOptional.isPresent()) {
                    id(idOptional);
                }
            }
            if (object instanceof WithTags) {
                WithTags instance = (WithTags) object;
                addAllTags(instance.getTags());
            }
            if (object instanceof Flow) {
                Flow instance = (Flow) object;
                Scheduler schedulerOptional = instance.getScheduler();
                scheduler(schedulerOptional);

                String descriptionOptional =
                        instance.getDescription();
                description(descriptionOptional);

                type(instance.getType());
                addAllConnections(instance.getConnections());
            }
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.integration.Flow#getName() name} attribute.
         *
         * @param name The value for name (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("name")
        public final Flow.Builder name(String name) {
            this.name = name;
            return (Flow.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.integration.Flow#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final Flow.Builder id(String id) {
            this.id = Objects.requireNonNull(id, "id");
            return (Flow.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.integration.Flow#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("id")
        public final Flow.Builder id(Optional<String> id) {
            this.id = id.orElse(null);
            return (Flow.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.integration.Flow#getKind() kind} attribute.
         *
         * @param kind The value for kind (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("kind")
        @JsonIgnore
        public final Flow.Builder kind(Kind kind) {
            this.kind = kind;
            return (Flow.Builder) this;
        }

        /**
         * Adds one element to {@link io.syndesis.common.model.integration.Flow#getTags() tags} sortedSet.
         *
         * @param element A tags element
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Flow.Builder addTag(String element) {
            if (element != null) {
                this.tags.add(element);
            }
            return (Flow.Builder) this;
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.integration.Flow#getTags() tags} sortedSet.
         *
         * @param elements An array of tags elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Flow.Builder addTags(String... elements) {
            for (String element : elements) {
                if (element != null) {
                    this.tags.add(element);
                }
            }
            return (Flow.Builder) this;
        }


        /**
         * Sets or replaces all elements for {@link io.syndesis.common.model.integration.Flow#getTags() tags} sortedSet.
         *
         * @param elements An iterable of tags elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("tags")
        public final Flow.Builder tags(Iterable<String> elements) {
            this.tags.clear();
            return addAllTags(elements);
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.integration.Flow#getTags() tags} sortedSet.
         *
         * @param elements An iterable of tags elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Flow.Builder addAllTags(Iterable<String> elements) {
            for (String element : elements) {
                if (element != null) {
                    this.tags.add(element);
                }
            }
            return (Flow.Builder) this;
        }

        /**
         * Adds one element to {@link io.syndesis.common.model.integration.Flow#getSteps() steps} list.
         *
         * @param element A steps element
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Flow.Builder addStep(Step element) {
            this.steps.add(Objects.requireNonNull(element, "steps element"));
            optBits |= OPT_BIT_STEPS;
            return (Flow.Builder) this;
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.integration.Flow#getSteps() steps} list.
         *
         * @param elements An array of steps elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Flow.Builder addSteps(Step... elements) {
            for (Step element : elements) {
                this.steps.add(
                        Objects.requireNonNull(element, "steps element"));
            }
            optBits |= OPT_BIT_STEPS;
            return (Flow.Builder) this;
        }


        /**
         * Sets or replaces all elements for {@link io.syndesis.common.model.integration.Flow#getSteps() steps} list.
         *
         * @param elements An iterable of steps elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("steps")
        public final Flow.Builder steps(Iterable<? extends Step> elements) {
            this.steps.clear();
            return addAllSteps(elements);
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.integration.Flow#getSteps() steps} list.
         *
         * @param elements An iterable of steps elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Flow.Builder addAllSteps(
                Iterable<? extends Step> elements) {
            for (Step element : elements) {
                this.steps.add(
                        Objects.requireNonNull(element, "steps element"));
            }
            optBits |= OPT_BIT_STEPS;
            return (Flow.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.integration.Flow#getMetadata() metadata} map.
         *
         * @param key   The key in the metadata map
         * @param value The associated value in the metadata map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Flow.Builder putMetadata(String key, String value) {
            this.metadata.put(
                    Objects.requireNonNull(key, "metadata key"),
                    Objects.requireNonNull(value, "metadata value"));
            optBits |= OPT_BIT_METADATA;
            return (Flow.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.integration.Flow#getMetadata() metadata} map. Nulls are not permitted
         *
         * @param entry The key and value entry
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Flow.Builder putMetadata(
                Map.Entry<String, ? extends String> entry) {
            String k = entry.getKey();
            String v = entry.getValue();
            this.metadata.put(
                    Objects.requireNonNull(k, "metadata key"),
                    Objects.requireNonNull(v, "metadata value"));
            optBits |= OPT_BIT_METADATA;
            return (Flow.Builder) this;
        }

        /**
         * Sets or replaces all mappings from the specified map as entries for the {@link io.syndesis.common.model.integration.Flow#getMetadata() metadata} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the metadata map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("metadata")
        public final Flow.Builder metadata(
                Map<String, ? extends String> entries) {
            this.metadata.clear();
            optBits |= OPT_BIT_METADATA;
            return putAllMetadata(entries);
        }

        /**
         * Put all mappings from the specified map as entries to {@link io.syndesis.common.model.integration.Flow#getMetadata() metadata} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the metadata map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Flow.Builder putAllMetadata(
                Map<String, ? extends String> entries) {
            for (Map.Entry<String, ? extends String> e : entries.entrySet()) {
                String k = e.getKey();
                String v = e.getValue();
                this.metadata.put(
                        Objects.requireNonNull(k, "metadata key"),
                        Objects.requireNonNull(v, "metadata value"));
            }
            optBits |= OPT_BIT_METADATA;
            return (Flow.Builder) this;
        }

        /**
         * Adds one element to {@link io.syndesis.common.model.integration.Flow#getDependencies() dependencies} list.
         *
         * @param element A dependencies element
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Flow.Builder addDependency(Dependency element) {
            this.dependencies.add(
                    Objects.requireNonNull(element, "dependencies element"));
            optBits |= OPT_BIT_DEPENDENCIES;
            return (Flow.Builder) this;
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.integration.Flow#getDependencies() dependencies} list.
         *
         * @param elements An array of dependencies elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Flow.Builder addDependencies(Dependency... elements) {
            for (Dependency element : elements) {
                this.dependencies.add(Objects.requireNonNull(element,
                        "dependencies element"));
            }
            optBits |= OPT_BIT_DEPENDENCIES;
            return (Flow.Builder) this;
        }


        /**
         * Sets or replaces all elements for {@link io.syndesis.common.model.integration.Flow#getDependencies() dependencies} list.
         *
         * @param elements An iterable of dependencies elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("dependencies")
        public final Flow.Builder dependencies(
                Iterable<? extends Dependency> elements) {
            this.dependencies.clear();
            return addAllDependencies(elements);
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.integration.Flow#getDependencies() dependencies} list.
         *
         * @param elements An iterable of dependencies elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Flow.Builder addAllDependencies(
                Iterable<? extends Dependency> elements) {
            for (Dependency element : elements) {
                this.dependencies.add(Objects.requireNonNull(element,
                        "dependencies element"));
            }
            optBits |= OPT_BIT_DEPENDENCIES;
            return (Flow.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.integration.Flow#getType() type} attribute.
         * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link io.syndesis.common.model.integration.Flow#getType() type}.</em>
         *
         * @param type The value for type
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("type")
        public final Flow.Builder type(FlowType type) {
            this.type = Objects.requireNonNull(type, "type");
            return (Flow.Builder) this;
        }

        /**
         * Adds one element to {@link io.syndesis.common.model.integration.Flow#getConnections() connections} list.
         *
         * @param element A connections element
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Flow.Builder addConnection(Connection element) {
            this.connections.add(
                    Objects.requireNonNull(element, "connections element"));
            optBits |= OPT_BIT_CONNECTIONS;
            return (Flow.Builder) this;
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.integration.Flow#getConnections() connections} list.
         *
         * @param elements An array of connections elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Flow.Builder addConnections(Connection... elements) {
            for (Connection element : elements) {
                this.connections.add(
                        Objects.requireNonNull(element, "connections element"));
            }
            optBits |= OPT_BIT_CONNECTIONS;
            return (Flow.Builder) this;
        }


        /**
         * Sets or replaces all elements for {@link io.syndesis.common.model.integration.Flow#getConnections() connections} list.
         *
         * @param elements An iterable of connections elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("connections")
        public final Flow.Builder connections(
                Iterable<? extends Connection> elements) {
            this.connections.clear();
            return addAllConnections(elements);
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.integration.Flow#getConnections() connections} list.
         *
         * @param elements An iterable of connections elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Flow.Builder addAllConnections(
                Iterable<? extends Connection> elements) {
            for (Connection element : elements) {
                this.connections.add(
                        Objects.requireNonNull(element, "connections element"));
            }
            optBits |= OPT_BIT_CONNECTIONS;
            return (Flow.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.integration.Flow#getScheduler() scheduler} to scheduler.
         *
         * @param scheduler The value for scheduler
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final Flow.Builder scheduler(Scheduler scheduler) {
            this.scheduler = Objects.requireNonNull(scheduler, "scheduler");
            return (Flow.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.integration.Flow#getScheduler() scheduler} to scheduler.
         *
         * @param scheduler The value for scheduler
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("scheduler")
        public final Flow.Builder scheduler(
                Optional<? extends Scheduler> scheduler) {
            this.scheduler = scheduler.orElse(null);
            return (Flow.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.integration.Flow#getDescription() description} to description.
         *
         * @param description The value for description
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final Flow.Builder description(String description) {
            this.description =
                    Objects.requireNonNull(description, "description");
            return (Flow.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.integration.Flow#getDescription() description} to description.
         *
         * @param description The value for description
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("description")
        public final Flow.Builder description(Optional<String> description) {
            this.description = description.orElse(null);
            return (Flow.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.integration.Flow Flow}.
         *
         * @return An immutable instance of Flow
         * @throws IllegalStateException if any required attributes are missing
         */
        public Flow build() {
            return ImmutableFlow.validate(new ImmutableFlow(this));
        }

        private boolean stepsIsSet() {
            return (optBits & OPT_BIT_STEPS) != 0;
        }

        private boolean metadataIsSet() {
            return (optBits & OPT_BIT_METADATA) != 0;
        }

        private boolean dependenciesIsSet() {
            return (optBits & OPT_BIT_DEPENDENCIES) != 0;
        }

        private boolean connectionsIsSet() {
            return (optBits & OPT_BIT_CONNECTIONS) != 0;
        }
    }

    private final class InitShim {
        private byte stepsBuildStage = STAGE_UNINITIALIZED;
        private List<Step> steps;
        private byte metadataBuildStage = STAGE_UNINITIALIZED;
        private Map<String, String> metadata;
        private byte dependenciesBuildStage = STAGE_UNINITIALIZED;
        private List<Dependency> dependencies;
        private byte typeBuildStage = STAGE_UNINITIALIZED;
        private FlowType type;
        private byte connectionsBuildStage = STAGE_UNINITIALIZED;
        private List<Connection> connections;

        List<Step> getSteps() {
            if (stepsBuildStage == STAGE_INITIALIZING) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (stepsBuildStage == STAGE_UNINITIALIZED) {
                stepsBuildStage = STAGE_INITIALIZING;
                this.steps = createUnmodifiableList(false,
                        createSafeList(getStepsInitialize(), true, false));
                stepsBuildStage = STAGE_INITIALIZED;
            }
            return this.steps;
        }

        void steps(List<Step> steps) {
            this.steps = steps;
            stepsBuildStage = STAGE_INITIALIZED;
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

        FlowType getType() {
            if (typeBuildStage == STAGE_INITIALIZING) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (typeBuildStage == STAGE_UNINITIALIZED) {
                typeBuildStage = STAGE_INITIALIZING;
                this.type = Objects.requireNonNull(getTypeInitialize(), "type");
                typeBuildStage = STAGE_INITIALIZED;
            }
            return this.type;
        }

        void type(FlowType type) {
            this.type = type;
            typeBuildStage = STAGE_INITIALIZED;
        }

        List<Connection> getConnections() {
            if (connectionsBuildStage == STAGE_INITIALIZING) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (connectionsBuildStage == STAGE_UNINITIALIZED) {
                connectionsBuildStage = STAGE_INITIALIZING;
                this.connections = createUnmodifiableList(false,
                        createSafeList(getConnectionsInitialize(), true,
                                false));
                connectionsBuildStage = STAGE_INITIALIZED;
            }
            return this.connections;
        }

        void connections(List<Connection> connections) {
            this.connections = connections;
            connectionsBuildStage = STAGE_INITIALIZED;
        }

        private String formatInitCycleMessage() {
            List<String> attributes = new ArrayList<>();
            if (stepsBuildStage == STAGE_INITIALIZING) {
                attributes.add("steps");
            }
            if (metadataBuildStage == STAGE_INITIALIZING) {
                attributes.add("metadata");
            }
            if (dependenciesBuildStage == STAGE_INITIALIZING) {
                attributes.add("dependencies");
            }
            if (typeBuildStage == STAGE_INITIALIZING) {
                attributes.add("type");
            }
            if (connectionsBuildStage == STAGE_INITIALIZING) {
                attributes.add("connections");
            }
            return "Cannot build Flow, attribute initializers form cycle "
                    + attributes;
        }
    }
}
