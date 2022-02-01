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
import io.syndesis.common.model.Dependency;
import io.syndesis.common.model.ResourceIdentifier;
import io.syndesis.common.model.WithConfigurationProperties;
import io.syndesis.common.model.WithConfiguredProperties;
import io.syndesis.common.model.WithDependencies;
import io.syndesis.common.model.WithEnvironment;
import io.syndesis.common.model.WithLabels;
import io.syndesis.common.model.WithModificationTimestamps;
import io.syndesis.common.model.WithName;
import io.syndesis.common.model.WithResourceId;
import io.syndesis.common.model.WithResources;
import io.syndesis.common.model.WithTags;
import io.syndesis.common.model.WithVersion;
import io.syndesis.common.model.connection.ConfigurationProperty;
import io.syndesis.common.model.connection.Connection;

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
 * Immutable implementation of {@link io.syndesis.common.model.integration.Integration}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new Integration.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableIntegration.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
public final class ImmutableIntegration implements Integration {
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private static final byte STAGE_INITIALIZED = 1;
    private final String id;
    private final Map<String, ConfigurationProperty> properties;
    private final Map<String, String> configuredProperties;
    private final int version;
    private final long createdAt;
    private final long updatedAt;
    private final SortedSet<String> tags;
    private final String name;
    private final List<Step> steps;
    private final List<ResourceIdentifier> resources;
    private final List<Dependency> dependencies;
    private final Map<String, String> labels;
    private final Map<String, String> environment;
    private final List<Connection> connections;
    private final Map<String, ContinuousDeliveryEnvironment>
            continuousDeliveryState;
    private final String description;
    private final String exposure;
    private final List<Flow> flows;
    @SuppressWarnings("Immutable")
    private transient volatile InitShim initShim = new InitShim();

    private ImmutableIntegration(
            Optional<String> id,
            Map<String, ? extends ConfigurationProperty> properties,
            Map<String, ? extends String> configuredProperties,
            int version,
            long createdAt,
            long updatedAt,
            Iterable<String> tags,
            String name,
            Iterable<? extends Step> steps,
            Iterable<? extends ResourceIdentifier> resources,
            Iterable<? extends Dependency> dependencies,
            Map<String, ? extends String> labels,
            Map<String, ? extends String> environment,
            Iterable<? extends Connection> connections,
            Map<String, ? extends ContinuousDeliveryEnvironment> continuousDeliveryState,
            Optional<String> description,
            Optional<String> exposure,
            Iterable<? extends Flow> flows) {
        this.id = id.orElse(null);
        this.properties = createUnmodifiableMap(true, false, properties);
        this.configuredProperties =
                createUnmodifiableMap(true, false, configuredProperties);
        this.version = version;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.tags = createUnmodifiableSortedSet(false,
                createSafeList(tags, false, true));
        this.name = name;
        this.steps = createUnmodifiableList(false,
                createSafeList(steps, true, false));
        this.resources = createUnmodifiableList(false,
                createSafeList(resources, true, false));
        this.dependencies = createUnmodifiableList(false,
                createSafeList(dependencies, true, false));
        this.labels = createUnmodifiableMap(true, false, labels);
        this.environment = createUnmodifiableMap(true, false, environment);
        this.connections = createUnmodifiableList(false,
                createSafeList(connections, true, false));
        this.continuousDeliveryState =
                createUnmodifiableMap(true, false, continuousDeliveryState);
        this.description = description.orElse(null);
        this.exposure = exposure.orElse(null);
        this.flows = createUnmodifiableList(false,
                createSafeList(flows, true, false));
        this.initShim = null;
    }

    private ImmutableIntegration(Builder builder) {
        this.id = builder.id;
        this.properties =
                createUnmodifiableMap(false, false, builder.properties);
        this.tags = createUnmodifiableSortedSet(false,
                createSafeList(builder.tags, false, false));
        this.name = builder.name;
        this.description = builder.description;
        this.exposure = builder.exposure;
        if (builder.configuredPropertiesIsSet()) {
            initShim.configuredProperties(createUnmodifiableMap(false, false,
                    builder.configuredProperties));
        }
        if (builder.versionIsSet()) {
            initShim.version(builder.version);
        }
        if (builder.createdAtIsSet()) {
            initShim.createdAt(builder.createdAt);
        }
        if (builder.updatedAtIsSet()) {
            initShim.updatedAt(builder.updatedAt);
        }
        if (builder.stepsIsSet()) {
            initShim.steps(createUnmodifiableList(true, builder.steps));
        }
        if (builder.resourcesIsSet()) {
            initShim.resources(createUnmodifiableList(true, builder.resources));
        }
        if (builder.dependenciesIsSet()) {
            initShim.dependencies(
                    createUnmodifiableList(true, builder.dependencies));
        }
        if (builder.labelsIsSet()) {
            initShim.labels(
                    createUnmodifiableMap(false, false, builder.labels));
        }
        if (builder.environmentIsSet()) {
            initShim.environment(
                    createUnmodifiableMap(false, false, builder.environment));
        }
        if (builder.connectionsIsSet()) {
            initShim.connections(
                    createUnmodifiableList(true, builder.connections));
        }
        if (builder.continuousDeliveryStateIsSet()) {
            initShim.continuousDeliveryState(createUnmodifiableMap(false, false,
                    builder.continuousDeliveryState));
        }
        if (builder.flowsIsSet()) {
            initShim.flows(createUnmodifiableList(true, builder.flows));
        }
        this.configuredProperties = initShim.getConfiguredProperties();
        this.version = initShim.getVersion();
        this.createdAt = initShim.getCreatedAt();
        this.updatedAt = initShim.getUpdatedAt();
        this.steps = initShim.getSteps();
        this.resources = initShim.getResources();
        this.dependencies = initShim.getDependencies();
        this.labels = initShim.getLabels();
        this.environment = initShim.getEnvironment();
        this.connections = initShim.getConnections();
        this.continuousDeliveryState = initShim.getContinuousDeliveryState();
        this.flows = initShim.getFlows();
        this.initShim = null;
    }

    private ImmutableIntegration(
            ImmutableIntegration original,
            String id,
            Map<String, ConfigurationProperty> properties,
            Map<String, String> configuredProperties,
            int version,
            long createdAt,
            long updatedAt,
            SortedSet<String> tags,
            String name,
            List<Step> steps,
            List<ResourceIdentifier> resources,
            List<Dependency> dependencies,
            Map<String, String> labels,
            Map<String, String> environment,
            List<Connection> connections,
            Map<String, ContinuousDeliveryEnvironment> continuousDeliveryState,
            String description,
            String exposure,
            List<Flow> flows) {
        this.id = id;
        this.properties = properties;
        this.configuredProperties = configuredProperties;
        this.version = version;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.tags = tags;
        this.name = name;
        this.steps = steps;
        this.resources = resources;
        this.dependencies = dependencies;
        this.labels = labels;
        this.environment = environment;
        this.connections = connections;
        this.continuousDeliveryState = continuousDeliveryState;
        this.description = description;
        this.exposure = exposure;
        this.flows = flows;
        this.initShim = null;
    }

    /**
     * Construct a new immutable {@code Integration} instance.
     *
     * @param id                      The value for the {@code id} attribute
     * @param properties              The value for the {@code properties} attribute
     * @param configuredProperties    The value for the {@code configuredProperties} attribute
     * @param version                 The value for the {@code version} attribute
     * @param createdAt               The value for the {@code createdAt} attribute
     * @param updatedAt               The value for the {@code updatedAt} attribute
     * @param tags                    The value for the {@code tags} attribute
     * @param name                    The value for the {@code name} attribute
     * @param steps                   The value for the {@code steps} attribute
     * @param resources               The value for the {@code resources} attribute
     * @param dependencies            The value for the {@code dependencies} attribute
     * @param labels                  The value for the {@code labels} attribute
     * @param environment             The value for the {@code environment} attribute
     * @param connections             The value for the {@code connections} attribute
     * @param continuousDeliveryState The value for the {@code continuousDeliveryState} attribute
     * @param description             The value for the {@code description} attribute
     * @param exposure                The value for the {@code exposure} attribute
     * @param flows                   The value for the {@code flows} attribute
     * @return An immutable Integration instance
     */
    public static Integration of(Optional<String> id,
                                 Map<String, ConfigurationProperty> properties,
                                 Map<String, String> configuredProperties,
                                 int version, long createdAt, long updatedAt,
                                 SortedSet<String> tags, String name,
                                 List<Step> steps,
                                 List<ResourceIdentifier> resources,
                                 List<Dependency> dependencies,
                                 Map<String, String> labels,
                                 Map<String, String> environment,
                                 List<Connection> connections,
                                 Map<String, ContinuousDeliveryEnvironment> continuousDeliveryState,
                                 Optional<String> description,
                                 Optional<String> exposure, List<Flow> flows) {
        return of(id, properties, configuredProperties, version, createdAt,
                updatedAt, (Iterable<String>) tags, name,
                (Iterable<? extends Step>) steps,
                (Iterable<? extends ResourceIdentifier>) resources,
                (Iterable<? extends Dependency>) dependencies, labels,
                environment, (Iterable<? extends Connection>) connections,
                continuousDeliveryState, description, exposure,
                (Iterable<? extends Flow>) flows);
    }

    /**
     * Construct a new immutable {@code Integration} instance.
     *
     * @param id                      The value for the {@code id} attribute
     * @param properties              The value for the {@code properties} attribute
     * @param configuredProperties    The value for the {@code configuredProperties} attribute
     * @param version                 The value for the {@code version} attribute
     * @param createdAt               The value for the {@code createdAt} attribute
     * @param updatedAt               The value for the {@code updatedAt} attribute
     * @param tags                    The value for the {@code tags} attribute
     * @param name                    The value for the {@code name} attribute
     * @param steps                   The value for the {@code steps} attribute
     * @param resources               The value for the {@code resources} attribute
     * @param dependencies            The value for the {@code dependencies} attribute
     * @param labels                  The value for the {@code labels} attribute
     * @param environment             The value for the {@code environment} attribute
     * @param connections             The value for the {@code connections} attribute
     * @param continuousDeliveryState The value for the {@code continuousDeliveryState} attribute
     * @param description             The value for the {@code description} attribute
     * @param exposure                The value for the {@code exposure} attribute
     * @param flows                   The value for the {@code flows} attribute
     * @return An immutable Integration instance
     */
    public static Integration of(Optional<String> id,
                                 Map<String, ? extends ConfigurationProperty> properties,
                                 Map<String, ? extends String> configuredProperties,
                                 int version, long createdAt, long updatedAt,
                                 Iterable<String> tags, String name,
                                 Iterable<? extends Step> steps,
                                 Iterable<? extends ResourceIdentifier> resources,
                                 Iterable<? extends Dependency> dependencies,
                                 Map<String, ? extends String> labels,
                                 Map<String, ? extends String> environment,
                                 Iterable<? extends Connection> connections,
                                 Map<String, ? extends ContinuousDeliveryEnvironment> continuousDeliveryState,
                                 Optional<String> description,
                                 Optional<String> exposure,
                                 Iterable<? extends Flow> flows) {
        return validate(
                new ImmutableIntegration(id, properties, configuredProperties,
                        version, createdAt, updatedAt, tags, name, steps,
                        resources, dependencies, labels, environment,
                        connections, continuousDeliveryState, description,
                        exposure, flows));
    }

    private static ImmutableIntegration validate(
            ImmutableIntegration instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.integration.Integration} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable Integration instance
     */
    public static Integration copyOf(Integration instance) {
        if (instance instanceof ImmutableIntegration) {
            return (ImmutableIntegration) instance;
        }
        return new Integration.Builder()
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
        return Integration.super.getConfiguredProperties();
    }

    private int getVersionInitialize() {
        return Integration.super.getVersion();
    }

    private long getCreatedAtInitialize() {
        return Integration.super.getCreatedAt();
    }

    private long getUpdatedAtInitialize() {
        return Integration.super.getUpdatedAt();
    }

    private List<Step> getStepsInitialize() {
        return Integration.super.getSteps();
    }

    private List<ResourceIdentifier> getResourcesInitialize() {
        return Integration.super.getResources();
    }

    private List<Dependency> getDependenciesInitialize() {
        return Integration.super.getDependencies();
    }

    private Map<String, String> getLabelsInitialize() {
        return Integration.super.getLabels();
    }

    private Map<String, String> getEnvironmentInitialize() {
        return Integration.super.getEnvironment();
    }

    private List<Connection> getConnectionsInitialize() {
        return Integration.super.getConnections();
    }

    private Map<String, ContinuousDeliveryEnvironment> getContinuousDeliveryStateInitialize() {
        return Integration.super.getContinuousDeliveryState();
    }

    private List<Flow> getFlowsInitialize() {
        return Integration.super.getFlows();
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
     * @return The value of the {@code createdAt} attribute
     */
    @JsonProperty("createdAt")
    @Override
    public long getCreatedAt() {
        InitShim shim = this.initShim;
        return shim != null
                ? shim.getCreatedAt()
                : this.createdAt;
    }

    /**
     * @return The value of the {@code updatedAt} attribute
     */
    @JsonProperty("updatedAt")
    @Override
    public long getUpdatedAt() {
        InitShim shim = this.initShim;
        return shim != null
                ? shim.getUpdatedAt()
                : this.updatedAt;
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
     * @return The value of the {@code resources} attribute
     */
    @JsonProperty("resources")
    @Override
    public List<ResourceIdentifier> getResources() {
        InitShim shim = this.initShim;
        return shim != null
                ? shim.getResources()
                : this.resources;
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
     * @return The value of the {@code labels} attribute
     */
    @JsonProperty("labels")
    @Override
    public Map<String, String> getLabels() {
        InitShim shim = this.initShim;
        return shim != null
                ? shim.getLabels()
                : this.labels;
    }

    /**
     * @return The value of the {@code environment} attribute
     */
    @JsonProperty("environment")
    @Override
    public Map<String, String> getEnvironment() {
        InitShim shim = this.initShim;
        return shim != null
                ? shim.getEnvironment()
                : this.environment;
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
     * @return The value of the {@code continuousDeliveryState} attribute
     */
    @JsonProperty("continuousDeliveryState")
    @Override
    public Map<String, ContinuousDeliveryEnvironment> getContinuousDeliveryState() {
        InitShim shim = this.initShim;
        return shim != null
                ? shim.getContinuousDeliveryState()
                : this.continuousDeliveryState;
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
     * @return The value of the {@code exposure} attribute
     */
    @JsonProperty("exposure")
    @Override
    public Optional<String> getExposure() {
        return Optional.ofNullable(exposure);
    }

    /**
     * @return The value of the {@code flows} attribute
     */
    @JsonProperty("flows")
    @Override
    public List<Flow> getFlows() {
        InitShim shim = this.initShim;
        return shim != null
                ? shim.getFlows()
                : this.flows;
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.integration.Integration#getId() id} attribute.
     *
     * @param value The value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegration withId(String value) {
        String newValue = Objects.requireNonNull(value, "id");
        if (Objects.equals(this.id, newValue)) {
            return this;
        }
        return validate(new ImmutableIntegration(
                this,
                newValue,
                this.properties,
                this.configuredProperties,
                this.version,
                this.createdAt,
                this.updatedAt,
                this.tags,
                this.name,
                this.steps,
                this.resources,
                this.dependencies,
                this.labels,
                this.environment,
                this.connections,
                this.continuousDeliveryState,
                this.description,
                this.exposure,
                this.flows));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.integration.Integration#getId() id} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegration withId(Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.id, value)) {
            return this;
        }
        return validate(new ImmutableIntegration(
                this,
                value,
                this.properties,
                this.configuredProperties,
                this.version,
                this.createdAt,
                this.updatedAt,
                this.tags,
                this.name,
                this.steps,
                this.resources,
                this.dependencies,
                this.labels,
                this.environment,
                this.connections,
                this.continuousDeliveryState,
                this.description,
                this.exposure,
                this.flows));
    }

    /**
     * Copy the current immutable object by replacing the {@link io.syndesis.common.model.integration.Integration#getProperties() properties} map with the specified map.
     * Nulls are not permitted as keys or values.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param entries The entries to be added to the properties map
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegration withProperties(
            Map<String, ? extends ConfigurationProperty> entries) {
        if (this.properties == entries) {
            return this;
        }
        Map<String, ConfigurationProperty> newValue =
                createUnmodifiableMap(true, false, entries);
        return validate(new ImmutableIntegration(
                this,
                this.id,
                newValue,
                this.configuredProperties,
                this.version,
                this.createdAt,
                this.updatedAt,
                this.tags,
                this.name,
                this.steps,
                this.resources,
                this.dependencies,
                this.labels,
                this.environment,
                this.connections,
                this.continuousDeliveryState,
                this.description,
                this.exposure,
                this.flows));
    }

    /**
     * Copy the current immutable object by replacing the {@link io.syndesis.common.model.integration.Integration#getConfiguredProperties() configuredProperties} map with the specified map.
     * Nulls are not permitted as keys or values.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param entries The entries to be added to the configuredProperties map
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegration withConfiguredProperties(
            Map<String, ? extends String> entries) {
        if (this.configuredProperties == entries) {
            return this;
        }
        Map<String, String> newValue =
                createUnmodifiableMap(true, false, entries);
        return validate(new ImmutableIntegration(
                this,
                this.id,
                this.properties,
                newValue,
                this.version,
                this.createdAt,
                this.updatedAt,
                this.tags,
                this.name,
                this.steps,
                this.resources,
                this.dependencies,
                this.labels,
                this.environment,
                this.connections,
                this.continuousDeliveryState,
                this.description,
                this.exposure,
                this.flows));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.integration.Integration#getVersion() version} attribute.
     * A value equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for version
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableIntegration withVersion(int value) {
        if (this.version == value) {
            return this;
        }
        return validate(new ImmutableIntegration(
                this,
                this.id,
                this.properties,
                this.configuredProperties,
                value,
                this.createdAt,
                this.updatedAt,
                this.tags,
                this.name,
                this.steps,
                this.resources,
                this.dependencies,
                this.labels,
                this.environment,
                this.connections,
                this.continuousDeliveryState,
                this.description,
                this.exposure,
                this.flows));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.integration.Integration#getCreatedAt() createdAt} attribute.
     * A value equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for createdAt
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableIntegration withCreatedAt(long value) {
        if (this.createdAt == value) {
            return this;
        }
        return validate(new ImmutableIntegration(
                this,
                this.id,
                this.properties,
                this.configuredProperties,
                this.version,
                value,
                this.updatedAt,
                this.tags,
                this.name,
                this.steps,
                this.resources,
                this.dependencies,
                this.labels,
                this.environment,
                this.connections,
                this.continuousDeliveryState,
                this.description,
                this.exposure,
                this.flows));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.integration.Integration#getUpdatedAt() updatedAt} attribute.
     * A value equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for updatedAt
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableIntegration withUpdatedAt(long value) {
        if (this.updatedAt == value) {
            return this;
        }
        return validate(new ImmutableIntegration(
                this,
                this.id,
                this.properties,
                this.configuredProperties,
                this.version,
                this.createdAt,
                value,
                this.tags,
                this.name,
                this.steps,
                this.resources,
                this.dependencies,
                this.labels,
                this.environment,
                this.connections,
                this.continuousDeliveryState,
                this.description,
                this.exposure,
                this.flows));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.integration.Integration#getTags() tags}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegration withTags(String... elements) {
        SortedSet<String> newValue = createUnmodifiableSortedSet(false,
                createSafeList(Arrays.asList(elements), false, true));
        return validate(new ImmutableIntegration(
                this,
                this.id,
                this.properties,
                this.configuredProperties,
                this.version,
                this.createdAt,
                this.updatedAt,
                newValue,
                this.name,
                this.steps,
                this.resources,
                this.dependencies,
                this.labels,
                this.environment,
                this.connections,
                this.continuousDeliveryState,
                this.description,
                this.exposure,
                this.flows));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.integration.Integration#getTags() tags}.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of tags elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegration withTags(Iterable<String> elements) {
        if (this.tags == elements) {
            return this;
        }
        SortedSet<String> newValue = createUnmodifiableSortedSet(false,
                createSafeList(elements, false, true));
        return validate(new ImmutableIntegration(
                this,
                this.id,
                this.properties,
                this.configuredProperties,
                this.version,
                this.createdAt,
                this.updatedAt,
                newValue,
                this.name,
                this.steps,
                this.resources,
                this.dependencies,
                this.labels,
                this.environment,
                this.connections,
                this.continuousDeliveryState,
                this.description,
                this.exposure,
                this.flows));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.integration.Integration#getName() name} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for name (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableIntegration withName(String value) {
        if (Objects.equals(this.name, value)) {
            return this;
        }
        return validate(new ImmutableIntegration(
                this,
                this.id,
                this.properties,
                this.configuredProperties,
                this.version,
                this.createdAt,
                this.updatedAt,
                this.tags,
                value,
                this.steps,
                this.resources,
                this.dependencies,
                this.labels,
                this.environment,
                this.connections,
                this.continuousDeliveryState,
                this.description,
                this.exposure,
                this.flows));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.integration.Integration#getSteps() steps}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegration withSteps(Step... elements) {
        List<Step> newValue = createUnmodifiableList(false,
                createSafeList(Arrays.asList(elements), true, false));
        return validate(new ImmutableIntegration(
                this,
                this.id,
                this.properties,
                this.configuredProperties,
                this.version,
                this.createdAt,
                this.updatedAt,
                this.tags,
                this.name,
                newValue,
                this.resources,
                this.dependencies,
                this.labels,
                this.environment,
                this.connections,
                this.continuousDeliveryState,
                this.description,
                this.exposure,
                this.flows));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.integration.Integration#getSteps() steps}.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of steps elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegration withSteps(
            Iterable<? extends Step> elements) {
        if (this.steps == elements) {
            return this;
        }
        List<Step> newValue = createUnmodifiableList(false,
                createSafeList(elements, true, false));
        return validate(new ImmutableIntegration(
                this,
                this.id,
                this.properties,
                this.configuredProperties,
                this.version,
                this.createdAt,
                this.updatedAt,
                this.tags,
                this.name,
                newValue,
                this.resources,
                this.dependencies,
                this.labels,
                this.environment,
                this.connections,
                this.continuousDeliveryState,
                this.description,
                this.exposure,
                this.flows));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.integration.Integration#getResources() resources}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegration withResources(
            ResourceIdentifier... elements) {
        List<ResourceIdentifier> newValue = createUnmodifiableList(false,
                createSafeList(Arrays.asList(elements), true, false));
        return validate(new ImmutableIntegration(
                this,
                this.id,
                this.properties,
                this.configuredProperties,
                this.version,
                this.createdAt,
                this.updatedAt,
                this.tags,
                this.name,
                this.steps,
                newValue,
                this.dependencies,
                this.labels,
                this.environment,
                this.connections,
                this.continuousDeliveryState,
                this.description,
                this.exposure,
                this.flows));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.integration.Integration#getResources() resources}.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of resources elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegration withResources(
            Iterable<? extends ResourceIdentifier> elements) {
        if (this.resources == elements) {
            return this;
        }
        List<ResourceIdentifier> newValue = createUnmodifiableList(false,
                createSafeList(elements, true, false));
        return validate(new ImmutableIntegration(
                this,
                this.id,
                this.properties,
                this.configuredProperties,
                this.version,
                this.createdAt,
                this.updatedAt,
                this.tags,
                this.name,
                this.steps,
                newValue,
                this.dependencies,
                this.labels,
                this.environment,
                this.connections,
                this.continuousDeliveryState,
                this.description,
                this.exposure,
                this.flows));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.integration.Integration#getDependencies() dependencies}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegration withDependencies(Dependency... elements) {
        List<Dependency> newValue = createUnmodifiableList(false,
                createSafeList(Arrays.asList(elements), true, false));
        return validate(new ImmutableIntegration(
                this,
                this.id,
                this.properties,
                this.configuredProperties,
                this.version,
                this.createdAt,
                this.updatedAt,
                this.tags,
                this.name,
                this.steps,
                this.resources,
                newValue,
                this.labels,
                this.environment,
                this.connections,
                this.continuousDeliveryState,
                this.description,
                this.exposure,
                this.flows));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.integration.Integration#getDependencies() dependencies}.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of dependencies elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegration withDependencies(
            Iterable<? extends Dependency> elements) {
        if (this.dependencies == elements) {
            return this;
        }
        List<Dependency> newValue = createUnmodifiableList(false,
                createSafeList(elements, true, false));
        return validate(new ImmutableIntegration(
                this,
                this.id,
                this.properties,
                this.configuredProperties,
                this.version,
                this.createdAt,
                this.updatedAt,
                this.tags,
                this.name,
                this.steps,
                this.resources,
                newValue,
                this.labels,
                this.environment,
                this.connections,
                this.continuousDeliveryState,
                this.description,
                this.exposure,
                this.flows));
    }

    /**
     * Copy the current immutable object by replacing the {@link io.syndesis.common.model.integration.Integration#getLabels() labels} map with the specified map.
     * Nulls are not permitted as keys or values.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param entries The entries to be added to the labels map
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegration withLabels(
            Map<String, ? extends String> entries) {
        if (this.labels == entries) {
            return this;
        }
        Map<String, String> newValue =
                createUnmodifiableMap(true, false, entries);
        return validate(new ImmutableIntegration(
                this,
                this.id,
                this.properties,
                this.configuredProperties,
                this.version,
                this.createdAt,
                this.updatedAt,
                this.tags,
                this.name,
                this.steps,
                this.resources,
                this.dependencies,
                newValue,
                this.environment,
                this.connections,
                this.continuousDeliveryState,
                this.description,
                this.exposure,
                this.flows));
    }

    /**
     * Copy the current immutable object by replacing the {@link io.syndesis.common.model.integration.Integration#getEnvironment() environment} map with the specified map.
     * Nulls are not permitted as keys or values.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param entries The entries to be added to the environment map
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegration withEnvironment(
            Map<String, ? extends String> entries) {
        if (this.environment == entries) {
            return this;
        }
        Map<String, String> newValue =
                createUnmodifiableMap(true, false, entries);
        return validate(new ImmutableIntegration(
                this,
                this.id,
                this.properties,
                this.configuredProperties,
                this.version,
                this.createdAt,
                this.updatedAt,
                this.tags,
                this.name,
                this.steps,
                this.resources,
                this.dependencies,
                this.labels,
                newValue,
                this.connections,
                this.continuousDeliveryState,
                this.description,
                this.exposure,
                this.flows));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.integration.Integration#getConnections() connections}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegration withConnections(Connection... elements) {
        List<Connection> newValue = createUnmodifiableList(false,
                createSafeList(Arrays.asList(elements), true, false));
        return validate(new ImmutableIntegration(
                this,
                this.id,
                this.properties,
                this.configuredProperties,
                this.version,
                this.createdAt,
                this.updatedAt,
                this.tags,
                this.name,
                this.steps,
                this.resources,
                this.dependencies,
                this.labels,
                this.environment,
                newValue,
                this.continuousDeliveryState,
                this.description,
                this.exposure,
                this.flows));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.integration.Integration#getConnections() connections}.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of connections elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegration withConnections(
            Iterable<? extends Connection> elements) {
        if (this.connections == elements) {
            return this;
        }
        List<Connection> newValue = createUnmodifiableList(false,
                createSafeList(elements, true, false));
        return validate(new ImmutableIntegration(
                this,
                this.id,
                this.properties,
                this.configuredProperties,
                this.version,
                this.createdAt,
                this.updatedAt,
                this.tags,
                this.name,
                this.steps,
                this.resources,
                this.dependencies,
                this.labels,
                this.environment,
                newValue,
                this.continuousDeliveryState,
                this.description,
                this.exposure,
                this.flows));
    }

    /**
     * Copy the current immutable object by replacing the {@link io.syndesis.common.model.integration.Integration#getContinuousDeliveryState() continuousDeliveryState} map with the specified map.
     * Nulls are not permitted as keys or values.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param entries The entries to be added to the continuousDeliveryState map
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegration withContinuousDeliveryState(
            Map<String, ? extends ContinuousDeliveryEnvironment> entries) {
        if (this.continuousDeliveryState == entries) {
            return this;
        }
        Map<String, ContinuousDeliveryEnvironment> newValue =
                createUnmodifiableMap(true, false, entries);
        return validate(new ImmutableIntegration(
                this,
                this.id,
                this.properties,
                this.configuredProperties,
                this.version,
                this.createdAt,
                this.updatedAt,
                this.tags,
                this.name,
                this.steps,
                this.resources,
                this.dependencies,
                this.labels,
                this.environment,
                this.connections,
                newValue,
                this.description,
                this.exposure,
                this.flows));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.integration.Integration#getDescription() description} attribute.
     *
     * @param value The value for description
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegration withDescription(String value) {
        String newValue = Objects.requireNonNull(value, "description");
        if (Objects.equals(this.description, newValue)) {
            return this;
        }
        return validate(new ImmutableIntegration(
                this,
                this.id,
                this.properties,
                this.configuredProperties,
                this.version,
                this.createdAt,
                this.updatedAt,
                this.tags,
                this.name,
                this.steps,
                this.resources,
                this.dependencies,
                this.labels,
                this.environment,
                this.connections,
                this.continuousDeliveryState,
                newValue,
                this.exposure,
                this.flows));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.integration.Integration#getDescription() description} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for description
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegration withDescription(
            Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.description, value)) {
            return this;
        }
        return validate(new ImmutableIntegration(
                this,
                this.id,
                this.properties,
                this.configuredProperties,
                this.version,
                this.createdAt,
                this.updatedAt,
                this.tags,
                this.name,
                this.steps,
                this.resources,
                this.dependencies,
                this.labels,
                this.environment,
                this.connections,
                this.continuousDeliveryState,
                value,
                this.exposure,
                this.flows));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.integration.Integration#getExposure() exposure} attribute.
     *
     * @param value The value for exposure
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegration withExposure(String value) {
        String newValue = Objects.requireNonNull(value, "exposure");
        if (Objects.equals(this.exposure, newValue)) {
            return this;
        }
        return validate(new ImmutableIntegration(
                this,
                this.id,
                this.properties,
                this.configuredProperties,
                this.version,
                this.createdAt,
                this.updatedAt,
                this.tags,
                this.name,
                this.steps,
                this.resources,
                this.dependencies,
                this.labels,
                this.environment,
                this.connections,
                this.continuousDeliveryState,
                this.description,
                newValue,
                this.flows));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.integration.Integration#getExposure() exposure} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for exposure
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegration withExposure(Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.exposure, value)) {
            return this;
        }
        return validate(new ImmutableIntegration(
                this,
                this.id,
                this.properties,
                this.configuredProperties,
                this.version,
                this.createdAt,
                this.updatedAt,
                this.tags,
                this.name,
                this.steps,
                this.resources,
                this.dependencies,
                this.labels,
                this.environment,
                this.connections,
                this.continuousDeliveryState,
                this.description,
                value,
                this.flows));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.integration.Integration#getFlows() flows}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegration withFlows(Flow... elements) {
        List<Flow> newValue = createUnmodifiableList(false,
                createSafeList(Arrays.asList(elements), true, false));
        return validate(new ImmutableIntegration(
                this,
                this.id,
                this.properties,
                this.configuredProperties,
                this.version,
                this.createdAt,
                this.updatedAt,
                this.tags,
                this.name,
                this.steps,
                this.resources,
                this.dependencies,
                this.labels,
                this.environment,
                this.connections,
                this.continuousDeliveryState,
                this.description,
                this.exposure,
                newValue));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.integration.Integration#getFlows() flows}.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of flows elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegration withFlows(
            Iterable<? extends Flow> elements) {
        if (this.flows == elements) {
            return this;
        }
        List<Flow> newValue = createUnmodifiableList(false,
                createSafeList(elements, true, false));
        return validate(new ImmutableIntegration(
                this,
                this.id,
                this.properties,
                this.configuredProperties,
                this.version,
                this.createdAt,
                this.updatedAt,
                this.tags,
                this.name,
                this.steps,
                this.resources,
                this.dependencies,
                this.labels,
                this.environment,
                this.connections,
                this.continuousDeliveryState,
                this.description,
                this.exposure,
                newValue));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableIntegration} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableIntegration
                && equalTo((ImmutableIntegration) another);
    }

    private boolean equalTo(ImmutableIntegration another) {
        return Objects.equals(id, another.id)
                && properties.equals(another.properties)
                && configuredProperties.equals(another.configuredProperties)
                && version == another.version
                && createdAt == another.createdAt
                && updatedAt == another.updatedAt
                && tags.equals(another.tags)
                && Objects.equals(name, another.name)
                && steps.equals(another.steps)
                && resources.equals(another.resources)
                && dependencies.equals(another.dependencies)
                && labels.equals(another.labels)
                && environment.equals(another.environment)
                && connections.equals(another.connections)
                && continuousDeliveryState.equals(
                another.continuousDeliveryState)
                && Objects.equals(description, another.description)
                && Objects.equals(exposure, another.exposure)
                && flows.equals(another.flows);
    }

    /**
     * Computes a hash code from attributes: {@code id}, {@code properties}, {@code configuredProperties}, {@code version}, {@code createdAt}, {@code updatedAt}, {@code tags}, {@code name}, {@code steps}, {@code resources}, {@code dependencies}, {@code labels}, {@code environment}, {@code connections}, {@code continuousDeliveryState}, {@code description}, {@code exposure}, {@code flows}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(id);
        h += (h << 5) + properties.hashCode();
        h += (h << 5) + configuredProperties.hashCode();
        h += (h << 5) + version;
        h += (h << 5) + Long.hashCode(createdAt);
        h += (h << 5) + Long.hashCode(updatedAt);
        h += (h << 5) + tags.hashCode();
        h += (h << 5) + Objects.hashCode(name);
        h += (h << 5) + steps.hashCode();
        h += (h << 5) + resources.hashCode();
        h += (h << 5) + dependencies.hashCode();
        h += (h << 5) + labels.hashCode();
        h += (h << 5) + environment.hashCode();
        h += (h << 5) + connections.hashCode();
        h += (h << 5) + continuousDeliveryState.hashCode();
        h += (h << 5) + Objects.hashCode(description);
        h += (h << 5) + Objects.hashCode(exposure);
        h += (h << 5) + flows.hashCode();
        return h;
    }

    /**
     * Prints the immutable value {@code Integration} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Integration{");
        if (id != null) {
            builder.append("id=").append(id);
        }
        if (builder.length() > 12) {
            builder.append(", ");
        }
        builder.append("properties=").append(properties);
        builder.append(", ");
        builder.append("configuredProperties=").append(configuredProperties);
        builder.append(", ");
        builder.append("version=").append(version);
        builder.append(", ");
        builder.append("createdAt=").append(createdAt);
        builder.append(", ");
        builder.append("updatedAt=").append(updatedAt);
        builder.append(", ");
        builder.append("tags=").append(tags);
        if (name != null) {
            builder.append(", ");
            builder.append("name=").append(name);
        }
        builder.append(", ");
        builder.append("steps=").append(steps);
        builder.append(", ");
        builder.append("resources=").append(resources);
        builder.append(", ");
        builder.append("dependencies=").append(dependencies);
        builder.append(", ");
        builder.append("labels=").append(labels);
        builder.append(", ");
        builder.append("environment=").append(environment);
        builder.append(", ");
        builder.append("connections=").append(connections);
        builder.append(", ");
        builder.append("continuousDeliveryState=")
                .append(continuousDeliveryState);
        if (description != null) {
            builder.append(", ");
            builder.append("description=").append(description);
        }
        if (exposure != null) {
            builder.append(", ");
            builder.append("exposure=").append(exposure);
        }
        builder.append(", ");
        builder.append("flows=").append(flows);
        return builder.append("}").toString();
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.integration.Integration Integration}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private static final long OPT_BIT_CONFIGURED_PROPERTIES = 0x1L;
        private static final long OPT_BIT_VERSION = 0x2L;
        private static final long OPT_BIT_CREATED_AT = 0x4L;
        private static final long OPT_BIT_UPDATED_AT = 0x8L;
        private static final long OPT_BIT_STEPS = 0x10L;
        private static final long OPT_BIT_RESOURCES = 0x20L;
        private static final long OPT_BIT_DEPENDENCIES = 0x40L;
        private static final long OPT_BIT_LABELS = 0x80L;
        private static final long OPT_BIT_ENVIRONMENT = 0x100L;
        private static final long OPT_BIT_CONNECTIONS = 0x200L;
        private static final long OPT_BIT_CONTINUOUS_DELIVERY_STATE = 0x400L;
        private static final long OPT_BIT_FLOWS = 0x800L;
        private long optBits;

        private String id;
        private Map<String, ConfigurationProperty> properties =
                new LinkedHashMap<String, ConfigurationProperty>();
        private Map<String, String> configuredProperties =
                new LinkedHashMap<String, String>();
        private int version;
        private long createdAt;
        private long updatedAt;
        private List<String> tags = new ArrayList<String>();
        private String name;
        private List<Step> steps = new ArrayList<Step>();
        private List<ResourceIdentifier> resources =
                new ArrayList<ResourceIdentifier>();
        private List<Dependency> dependencies = new ArrayList<Dependency>();
        private Map<String, String> labels =
                new LinkedHashMap<String, String>();
        private Map<String, String> environment =
                new LinkedHashMap<String, String>();
        private List<Connection> connections = new ArrayList<Connection>();
        private Map<String, ContinuousDeliveryEnvironment>
                continuousDeliveryState =
                new LinkedHashMap<String, ContinuousDeliveryEnvironment>();
        private String description;
        private String exposure;
        private List<Flow> flows = new ArrayList<Flow>();

        /**
         * Creates a builder for {@link io.syndesis.common.model.integration.Integration Integration} instances.
         * <pre>
         * new Integration.Builder()
         *    .id(String) // optional {@link io.syndesis.common.model.integration.Integration#getId() id}
         *    .putProperty|putAllProperties(String =&gt; io.syndesis.common.io.syndesis.common.model.connection.ConfigurationProperty) // {@link io.syndesis.common.model.integration.Integration#getProperties() properties} mappings
         *    .putConfiguredProperty|putAllConfiguredProperties(String =&gt; String) // {@link io.syndesis.common.model.integration.Integration#getConfiguredProperties() configuredProperties} mappings
         *    .version(int) // optional {@link io.syndesis.common.model.integration.Integration#getVersion() version}
         *    .createdAt(long) // optional {@link io.syndesis.common.model.integration.Integration#getCreatedAt() createdAt}
         *    .updatedAt(long) // optional {@link io.syndesis.common.model.integration.Integration#getUpdatedAt() updatedAt}
         *    .addTag|addAllTags(String) // {@link io.syndesis.common.model.integration.Integration#getTags() tags} elements
         *    .name(String | null) // nullable {@link io.syndesis.common.model.integration.Integration#getName() name}
         *    .addStep|addAllSteps(io.syndesis.common.io.syndesis.common.model.integration.Step) // {@link io.syndesis.common.model.integration.Integration#getSteps() steps} elements
         *    .addResource|addAllResources(io.syndesis.common.io.syndesis.common.model.ResourceIdentifier) // {@link io.syndesis.common.model.integration.Integration#getResources() resources} elements
         *    .addDependency|addAllDependencies(io.syndesis.common.io.syndesis.common.model.Dependency) // {@link io.syndesis.common.model.integration.Integration#getDependencies() dependencies} elements
         *    .putLabel|putAllLabels(String =&gt; String) // {@link io.syndesis.common.model.integration.Integration#getLabels() labels} mappings
         *    .putEnvironment|putAllEnvironment(String =&gt; String) // {@link io.syndesis.common.model.integration.Integration#getEnvironment() environment} mappings
         *    .addConnection|addAllConnections(io.syndesis.common.io.syndesis.common.model.connection.Connection) // {@link io.syndesis.common.model.integration.Integration#getConnections() connections} elements
         *    .putContinuousDeliveryState|putAllContinuousDeliveryState(String =&gt; io.syndesis.common.io.syndesis.common.model.integration.ContinuousDeliveryEnvironment) // {@link io.syndesis.common.model.integration.Integration#getContinuousDeliveryState() continuousDeliveryState} mappings
         *    .description(String) // optional {@link io.syndesis.common.model.integration.Integration#getDescription() description}
         *    .exposure(String) // optional {@link io.syndesis.common.model.integration.Integration#getExposure() exposure}
         *    .addFlow|addAllFlows(io.syndesis.common.io.syndesis.common.model.integration.Flow) // {@link io.syndesis.common.model.integration.Integration#getFlows() flows} elements
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof Integration.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new Integration.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.integration.WithSteps} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder createFrom(WithSteps instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Integration.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithLabels} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder createFrom(WithLabels instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Integration.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithDependencies} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder createFrom(WithDependencies instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Integration.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithTags} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder createFrom(WithTags instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Integration.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithResourceId} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder createFrom(WithResourceId instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Integration.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithName} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder createFrom(WithName instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Integration.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.integration.IntegrationBase} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder createFrom(IntegrationBase instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Integration.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithConfiguredProperties} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder createFrom(
                WithConfiguredProperties instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Integration.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithResources} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder createFrom(WithResources instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Integration.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithVersion} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder createFrom(WithVersion instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Integration.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithEnvironment} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder createFrom(WithEnvironment instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Integration.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithModificationTimestamps} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder createFrom(
                WithModificationTimestamps instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Integration.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithConfigurationProperties} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder createFrom(
                WithConfigurationProperties instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Integration.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.integration.Integration} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder createFrom(Integration instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Integration.Builder) this;
        }

        private void from(Object object) {
            if (object instanceof WithSteps) {
                WithSteps instance = (WithSteps) object;
                addAllSteps(instance.getSteps());
            }
            if (object instanceof WithLabels) {
                WithLabels instance = (WithLabels) object;
                putAllLabels(instance.getLabels());
            }
            if (object instanceof WithDependencies) {
                WithDependencies instance = (WithDependencies) object;
                addAllDependencies(instance.getDependencies());
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
            if (object instanceof WithName) {
                WithName instance = (WithName) object;
                String nameValue = instance.getName();
                if (nameValue != null) {
                    name(nameValue);
                }
            }
            if (object instanceof IntegrationBase) {
                IntegrationBase instance = (IntegrationBase) object;
                putAllContinuousDeliveryState(
                        instance.getContinuousDeliveryState());
                Optional<String> descriptionOptional =
                        instance.getDescription();
                if (descriptionOptional.isPresent()) {
                    description(descriptionOptional);
                }
                Optional<String> exposureOptional = instance.getExposure();
                if (exposureOptional.isPresent()) {
                    exposure(exposureOptional);
                }
                addAllFlows(instance.getFlows());
                addAllConnections(instance.getConnections());
            }
            if (object instanceof WithConfiguredProperties) {
                WithConfiguredProperties instance =
                        (WithConfiguredProperties) object;
                putAllConfiguredProperties(instance.getConfiguredProperties());
            }
            if (object instanceof WithResources) {
                WithResources instance = (WithResources) object;
                addAllResources(instance.getResources());
            }
            if (object instanceof WithVersion) {
                WithVersion instance = (WithVersion) object;
                version(instance.getVersion());
            }
            if (object instanceof WithEnvironment) {
                WithEnvironment instance = (WithEnvironment) object;
                putAllEnvironment(instance.getEnvironment());
            }
            if (object instanceof WithModificationTimestamps) {
                WithModificationTimestamps instance =
                        (WithModificationTimestamps) object;
                createdAt(instance.getCreatedAt());
                updatedAt(instance.getUpdatedAt());
            }
            if (object instanceof WithConfigurationProperties) {
                WithConfigurationProperties instance =
                        (WithConfigurationProperties) object;
                putAllProperties(instance.getProperties());
            }
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.integration.Integration#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder id(String id) {
            this.id = Objects.requireNonNull(id, "id");
            return (Integration.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.integration.Integration#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("id")
        public final Integration.Builder id(Optional<String> id) {
            this.id = id.orElse(null);
            return (Integration.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.integration.Integration#getProperties() properties} map.
         *
         * @param key   The key in the properties map
         * @param value The associated value in the properties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder putProperty(String key,
                                                     ConfigurationProperty value) {
            this.properties.put(
                    Objects.requireNonNull(key, "properties key"),
                    Objects.requireNonNull(value, "properties value"));
            return (Integration.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.integration.Integration#getProperties() properties} map. Nulls are not permitted
         *
         * @param entry The key and value entry
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder putProperty(
                Map.Entry<String, ? extends ConfigurationProperty> entry) {
            String k = entry.getKey();
            ConfigurationProperty v = entry.getValue();
            this.properties.put(
                    Objects.requireNonNull(k, "properties key"),
                    Objects.requireNonNull(v, "properties value"));
            return (Integration.Builder) this;
        }

        /**
         * Sets or replaces all mappings from the specified map as entries for the {@link io.syndesis.common.model.integration.Integration#getProperties() properties} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the properties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("properties")
        public final Integration.Builder properties(
                Map<String, ? extends ConfigurationProperty> entries) {
            this.properties.clear();
            return putAllProperties(entries);
        }

        /**
         * Put all mappings from the specified map as entries to {@link io.syndesis.common.model.integration.Integration#getProperties() properties} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the properties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder putAllProperties(
                Map<String, ? extends ConfigurationProperty> entries) {
            for (Map.Entry<String, ? extends ConfigurationProperty> e : entries.entrySet()) {
                String k = e.getKey();
                ConfigurationProperty v = e.getValue();
                this.properties.put(
                        Objects.requireNonNull(k, "properties key"),
                        Objects.requireNonNull(v, "properties value"));
            }
            return (Integration.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.integration.Integration#getConfiguredProperties() configuredProperties} map.
         *
         * @param key   The key in the configuredProperties map
         * @param value The associated value in the configuredProperties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder putConfiguredProperty(String key,
                                                               String value) {
            this.configuredProperties.put(
                    Objects.requireNonNull(key, "configuredProperties key"),
                    Objects.requireNonNull(value,
                            "configuredProperties value"));
            optBits |= OPT_BIT_CONFIGURED_PROPERTIES;
            return (Integration.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.integration.Integration#getConfiguredProperties() configuredProperties} map. Nulls are not permitted
         *
         * @param entry The key and value entry
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder putConfiguredProperty(
                Map.Entry<String, ? extends String> entry) {
            String k = entry.getKey();
            String v = entry.getValue();
            this.configuredProperties.put(
                    Objects.requireNonNull(k, "configuredProperties key"),
                    Objects.requireNonNull(v, "configuredProperties value"));
            optBits |= OPT_BIT_CONFIGURED_PROPERTIES;
            return (Integration.Builder) this;
        }

        /**
         * Sets or replaces all mappings from the specified map as entries for the {@link io.syndesis.common.model.integration.Integration#getConfiguredProperties() configuredProperties} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the configuredProperties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("configuredProperties")
        public final Integration.Builder configuredProperties(
                Map<String, ? extends String> entries) {
            this.configuredProperties.clear();
            optBits |= OPT_BIT_CONFIGURED_PROPERTIES;
            return putAllConfiguredProperties(entries);
        }

        /**
         * Put all mappings from the specified map as entries to {@link io.syndesis.common.model.integration.Integration#getConfiguredProperties() configuredProperties} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the configuredProperties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder putAllConfiguredProperties(
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
            return (Integration.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.integration.Integration#getVersion() version} attribute.
         * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link io.syndesis.common.model.integration.Integration#getVersion() version}.</em>
         *
         * @param version The value for version
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("version")
        public final Integration.Builder version(int version) {
            this.version = version;
            optBits |= OPT_BIT_VERSION;
            return (Integration.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.integration.Integration#getCreatedAt() createdAt} attribute.
         * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link io.syndesis.common.model.integration.Integration#getCreatedAt() createdAt}.</em>
         *
         * @param createdAt The value for createdAt
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("createdAt")
        public final Integration.Builder createdAt(long createdAt) {
            this.createdAt = createdAt;
            optBits |= OPT_BIT_CREATED_AT;
            return (Integration.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.integration.Integration#getUpdatedAt() updatedAt} attribute.
         * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link io.syndesis.common.model.integration.Integration#getUpdatedAt() updatedAt}.</em>
         *
         * @param updatedAt The value for updatedAt
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("updatedAt")
        public final Integration.Builder updatedAt(long updatedAt) {
            this.updatedAt = updatedAt;
            optBits |= OPT_BIT_UPDATED_AT;
            return (Integration.Builder) this;
        }

        /**
         * Adds one element to {@link io.syndesis.common.model.integration.Integration#getTags() tags} sortedSet.
         *
         * @param element A tags element
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder addTag(String element) {
            if (element != null) {
                this.tags.add(element);
            }
            return (Integration.Builder) this;
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.integration.Integration#getTags() tags} sortedSet.
         *
         * @param elements An array of tags elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder addTags(String... elements) {
            for (String element : elements) {
                if (element != null) {
                    this.tags.add(element);
                }
            }
            return (Integration.Builder) this;
        }


        /**
         * Sets or replaces all elements for {@link io.syndesis.common.model.integration.Integration#getTags() tags} sortedSet.
         *
         * @param elements An iterable of tags elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("tags")
        public final Integration.Builder tags(Iterable<String> elements) {
            this.tags.clear();
            return addAllTags(elements);
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.integration.Integration#getTags() tags} sortedSet.
         *
         * @param elements An iterable of tags elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder addAllTags(Iterable<String> elements) {
            for (String element : elements) {
                if (element != null) {
                    this.tags.add(element);
                }
            }
            return (Integration.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.integration.Integration#getName() name} attribute.
         *
         * @param name The value for name (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("name")
        public final Integration.Builder name(String name) {
            this.name = name;
            return (Integration.Builder) this;
        }

        /**
         * Adds one element to {@link io.syndesis.common.model.integration.Integration#getSteps() steps} list.
         *
         * @param element A steps element
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder addStep(Step element) {
            this.steps.add(Objects.requireNonNull(element, "steps element"));
            optBits |= OPT_BIT_STEPS;
            return (Integration.Builder) this;
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.integration.Integration#getSteps() steps} list.
         *
         * @param elements An array of steps elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder addSteps(Step... elements) {
            for (Step element : elements) {
                this.steps.add(
                        Objects.requireNonNull(element, "steps element"));
            }
            optBits |= OPT_BIT_STEPS;
            return (Integration.Builder) this;
        }


        /**
         * Sets or replaces all elements for {@link io.syndesis.common.model.integration.Integration#getSteps() steps} list.
         *
         * @param elements An iterable of steps elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("steps")
        public final Integration.Builder steps(
                Iterable<? extends Step> elements) {
            this.steps.clear();
            return addAllSteps(elements);
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.integration.Integration#getSteps() steps} list.
         *
         * @param elements An iterable of steps elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder addAllSteps(
                Iterable<? extends Step> elements) {
            for (Step element : elements) {
                this.steps.add(
                        Objects.requireNonNull(element, "steps element"));
            }
            optBits |= OPT_BIT_STEPS;
            return (Integration.Builder) this;
        }

        /**
         * Adds one element to {@link io.syndesis.common.model.integration.Integration#getResources() resources} list.
         *
         * @param element A resources element
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder addResource(
                ResourceIdentifier element) {
            this.resources.add(
                    Objects.requireNonNull(element, "resources element"));
            optBits |= OPT_BIT_RESOURCES;
            return (Integration.Builder) this;
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.integration.Integration#getResources() resources} list.
         *
         * @param elements An array of resources elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder addResources(
                ResourceIdentifier... elements) {
            for (ResourceIdentifier element : elements) {
                this.resources.add(
                        Objects.requireNonNull(element, "resources element"));
            }
            optBits |= OPT_BIT_RESOURCES;
            return (Integration.Builder) this;
        }


        /**
         * Sets or replaces all elements for {@link io.syndesis.common.model.integration.Integration#getResources() resources} list.
         *
         * @param elements An iterable of resources elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("resources")
        public final Integration.Builder resources(
                Iterable<? extends ResourceIdentifier> elements) {
            this.resources.clear();
            return addAllResources(elements);
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.integration.Integration#getResources() resources} list.
         *
         * @param elements An iterable of resources elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder addAllResources(
                Iterable<? extends ResourceIdentifier> elements) {
            for (ResourceIdentifier element : elements) {
                this.resources.add(
                        Objects.requireNonNull(element, "resources element"));
            }
            optBits |= OPT_BIT_RESOURCES;
            return (Integration.Builder) this;
        }

        /**
         * Adds one element to {@link io.syndesis.common.model.integration.Integration#getDependencies() dependencies} list.
         *
         * @param element A dependencies element
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder addDependency(Dependency element) {
            this.dependencies.add(
                    Objects.requireNonNull(element, "dependencies element"));
            optBits |= OPT_BIT_DEPENDENCIES;
            return (Integration.Builder) this;
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.integration.Integration#getDependencies() dependencies} list.
         *
         * @param elements An array of dependencies elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder addDependencies(
                Dependency... elements) {
            for (Dependency element : elements) {
                this.dependencies.add(Objects.requireNonNull(element,
                        "dependencies element"));
            }
            optBits |= OPT_BIT_DEPENDENCIES;
            return (Integration.Builder) this;
        }


        /**
         * Sets or replaces all elements for {@link io.syndesis.common.model.integration.Integration#getDependencies() dependencies} list.
         *
         * @param elements An iterable of dependencies elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("dependencies")
        public final Integration.Builder dependencies(
                Iterable<? extends Dependency> elements) {
            this.dependencies.clear();
            return addAllDependencies(elements);
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.integration.Integration#getDependencies() dependencies} list.
         *
         * @param elements An iterable of dependencies elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder addAllDependencies(
                Iterable<? extends Dependency> elements) {
            for (Dependency element : elements) {
                this.dependencies.add(Objects.requireNonNull(element,
                        "dependencies element"));
            }
            optBits |= OPT_BIT_DEPENDENCIES;
            return (Integration.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.integration.Integration#getLabels() labels} map.
         *
         * @param key   The key in the labels map
         * @param value The associated value in the labels map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder putLabel(String key, String value) {
            this.labels.put(
                    Objects.requireNonNull(key, "labels key"),
                    Objects.requireNonNull(value, "labels value"));
            optBits |= OPT_BIT_LABELS;
            return (Integration.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.integration.Integration#getLabels() labels} map. Nulls are not permitted
         *
         * @param entry The key and value entry
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder putLabel(
                Map.Entry<String, ? extends String> entry) {
            String k = entry.getKey();
            String v = entry.getValue();
            this.labels.put(
                    Objects.requireNonNull(k, "labels key"),
                    Objects.requireNonNull(v, "labels value"));
            optBits |= OPT_BIT_LABELS;
            return (Integration.Builder) this;
        }

        /**
         * Sets or replaces all mappings from the specified map as entries for the {@link io.syndesis.common.model.integration.Integration#getLabels() labels} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the labels map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("labels")
        public final Integration.Builder labels(
                Map<String, ? extends String> entries) {
            this.labels.clear();
            optBits |= OPT_BIT_LABELS;
            return putAllLabels(entries);
        }

        /**
         * Put all mappings from the specified map as entries to {@link io.syndesis.common.model.integration.Integration#getLabels() labels} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the labels map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder putAllLabels(
                Map<String, ? extends String> entries) {
            for (Map.Entry<String, ? extends String> e : entries.entrySet()) {
                String k = e.getKey();
                String v = e.getValue();
                this.labels.put(
                        Objects.requireNonNull(k, "labels key"),
                        Objects.requireNonNull(v, "labels value"));
            }
            optBits |= OPT_BIT_LABELS;
            return (Integration.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.integration.Integration#getEnvironment() environment} map.
         *
         * @param key   The key in the environment map
         * @param value The associated value in the environment map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder putEnvironment(String key,
                                                        String value) {
            this.environment.put(
                    Objects.requireNonNull(key, "environment key"),
                    Objects.requireNonNull(value, "environment value"));
            optBits |= OPT_BIT_ENVIRONMENT;
            return (Integration.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.integration.Integration#getEnvironment() environment} map. Nulls are not permitted
         *
         * @param entry The key and value entry
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder putEnvironment(
                Map.Entry<String, ? extends String> entry) {
            String k = entry.getKey();
            String v = entry.getValue();
            this.environment.put(
                    Objects.requireNonNull(k, "environment key"),
                    Objects.requireNonNull(v, "environment value"));
            optBits |= OPT_BIT_ENVIRONMENT;
            return (Integration.Builder) this;
        }

        /**
         * Sets or replaces all mappings from the specified map as entries for the {@link io.syndesis.common.model.integration.Integration#getEnvironment() environment} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the environment map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("environment")
        public final Integration.Builder environment(
                Map<String, ? extends String> entries) {
            this.environment.clear();
            optBits |= OPT_BIT_ENVIRONMENT;
            return putAllEnvironment(entries);
        }

        /**
         * Put all mappings from the specified map as entries to {@link io.syndesis.common.model.integration.Integration#getEnvironment() environment} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the environment map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder putAllEnvironment(
                Map<String, ? extends String> entries) {
            for (Map.Entry<String, ? extends String> e : entries.entrySet()) {
                String k = e.getKey();
                String v = e.getValue();
                this.environment.put(
                        Objects.requireNonNull(k, "environment key"),
                        Objects.requireNonNull(v, "environment value"));
            }
            optBits |= OPT_BIT_ENVIRONMENT;
            return (Integration.Builder) this;
        }

        /**
         * Adds one element to {@link io.syndesis.common.model.integration.Integration#getConnections() connections} list.
         *
         * @param element A connections element
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder addConnection(Connection element) {
            this.connections.add(
                    Objects.requireNonNull(element, "connections element"));
            optBits |= OPT_BIT_CONNECTIONS;
            return (Integration.Builder) this;
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.integration.Integration#getConnections() connections} list.
         *
         * @param elements An array of connections elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder addConnections(
                Connection... elements) {
            for (Connection element : elements) {
                this.connections.add(
                        Objects.requireNonNull(element, "connections element"));
            }
            optBits |= OPT_BIT_CONNECTIONS;
            return (Integration.Builder) this;
        }


        /**
         * Sets or replaces all elements for {@link io.syndesis.common.model.integration.Integration#getConnections() connections} list.
         *
         * @param elements An iterable of connections elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("connections")
        public final Integration.Builder connections(
                Iterable<? extends Connection> elements) {
            this.connections.clear();
            return addAllConnections(elements);
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.integration.Integration#getConnections() connections} list.
         *
         * @param elements An iterable of connections elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder addAllConnections(
                Iterable<? extends Connection> elements) {
            for (Connection element : elements) {
                this.connections.add(
                        Objects.requireNonNull(element, "connections element"));
            }
            optBits |= OPT_BIT_CONNECTIONS;
            return (Integration.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.integration.Integration#getContinuousDeliveryState() continuousDeliveryState} map.
         *
         * @param key   The key in the continuousDeliveryState map
         * @param value The associated value in the continuousDeliveryState map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder putContinuousDeliveryState(String key,
                                                                    ContinuousDeliveryEnvironment value) {
            this.continuousDeliveryState.put(
                    Objects.requireNonNull(key, "continuousDeliveryState key"),
                    Objects.requireNonNull(value,
                            "continuousDeliveryState value"));
            optBits |= OPT_BIT_CONTINUOUS_DELIVERY_STATE;
            return (Integration.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.integration.Integration#getContinuousDeliveryState() continuousDeliveryState} map. Nulls are not permitted
         *
         * @param entry The key and value entry
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder putContinuousDeliveryState(
                Map.Entry<String, ? extends ContinuousDeliveryEnvironment> entry) {
            String k = entry.getKey();
            ContinuousDeliveryEnvironment v = entry.getValue();
            this.continuousDeliveryState.put(
                    Objects.requireNonNull(k, "continuousDeliveryState key"),
                    Objects.requireNonNull(v, "continuousDeliveryState value"));
            optBits |= OPT_BIT_CONTINUOUS_DELIVERY_STATE;
            return (Integration.Builder) this;
        }

        /**
         * Sets or replaces all mappings from the specified map as entries for the {@link io.syndesis.common.model.integration.Integration#getContinuousDeliveryState() continuousDeliveryState} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the continuousDeliveryState map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("continuousDeliveryState")
        public final Integration.Builder continuousDeliveryState(
                Map<String, ? extends ContinuousDeliveryEnvironment> entries) {
            this.continuousDeliveryState.clear();
            optBits |= OPT_BIT_CONTINUOUS_DELIVERY_STATE;
            return putAllContinuousDeliveryState(entries);
        }

        /**
         * Put all mappings from the specified map as entries to {@link io.syndesis.common.model.integration.Integration#getContinuousDeliveryState() continuousDeliveryState} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the continuousDeliveryState map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder putAllContinuousDeliveryState(
                Map<String, ? extends ContinuousDeliveryEnvironment> entries) {
            for (Map.Entry<String, ? extends ContinuousDeliveryEnvironment> e : entries.entrySet()) {
                String k = e.getKey();
                ContinuousDeliveryEnvironment v = e.getValue();
                this.continuousDeliveryState.put(
                        Objects.requireNonNull(k,
                                "continuousDeliveryState key"),
                        Objects.requireNonNull(v,
                                "continuousDeliveryState value"));
            }
            optBits |= OPT_BIT_CONTINUOUS_DELIVERY_STATE;
            return (Integration.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.integration.Integration#getDescription() description} to description.
         *
         * @param description The value for description
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder description(String description) {
            this.description =
                    Objects.requireNonNull(description, "description");
            return (Integration.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.integration.Integration#getDescription() description} to description.
         *
         * @param description The value for description
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("description")
        public final Integration.Builder description(
                Optional<String> description) {
            this.description = description.orElse(null);
            return (Integration.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.integration.Integration#getExposure() exposure} to exposure.
         *
         * @param exposure The value for exposure
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder exposure(String exposure) {
            this.exposure = Objects.requireNonNull(exposure, "exposure");
            return (Integration.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.integration.Integration#getExposure() exposure} to exposure.
         *
         * @param exposure The value for exposure
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("exposure")
        public final Integration.Builder exposure(Optional<String> exposure) {
            this.exposure = exposure.orElse(null);
            return (Integration.Builder) this;
        }

        /**
         * Adds one element to {@link io.syndesis.common.model.integration.Integration#getFlows() flows} list.
         *
         * @param element A flows element
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder addFlow(Flow element) {
            this.flows.add(Objects.requireNonNull(element, "flows element"));
            optBits |= OPT_BIT_FLOWS;
            return (Integration.Builder) this;
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.integration.Integration#getFlows() flows} list.
         *
         * @param elements An array of flows elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder addFlows(Flow... elements) {
            for (Flow element : elements) {
                this.flows.add(
                        Objects.requireNonNull(element, "flows element"));
            }
            optBits |= OPT_BIT_FLOWS;
            return (Integration.Builder) this;
        }


        /**
         * Sets or replaces all elements for {@link io.syndesis.common.model.integration.Integration#getFlows() flows} list.
         *
         * @param elements An iterable of flows elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("flows")
        public final Integration.Builder flows(
                Iterable<? extends Flow> elements) {
            this.flows.clear();
            return addAllFlows(elements);
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.integration.Integration#getFlows() flows} list.
         *
         * @param elements An iterable of flows elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Integration.Builder addAllFlows(
                Iterable<? extends Flow> elements) {
            for (Flow element : elements) {
                this.flows.add(
                        Objects.requireNonNull(element, "flows element"));
            }
            optBits |= OPT_BIT_FLOWS;
            return (Integration.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.integration.Integration Integration}.
         *
         * @return An immutable instance of Integration
         * @throws IllegalStateException if any required attributes are missing
         */
        public Integration build() {
            return ImmutableIntegration.validate(
                    new ImmutableIntegration(this));
        }

        private boolean configuredPropertiesIsSet() {
            return (optBits & OPT_BIT_CONFIGURED_PROPERTIES) != 0;
        }

        private boolean versionIsSet() {
            return (optBits & OPT_BIT_VERSION) != 0;
        }

        private boolean createdAtIsSet() {
            return (optBits & OPT_BIT_CREATED_AT) != 0;
        }

        private boolean updatedAtIsSet() {
            return (optBits & OPT_BIT_UPDATED_AT) != 0;
        }

        private boolean stepsIsSet() {
            return (optBits & OPT_BIT_STEPS) != 0;
        }

        private boolean resourcesIsSet() {
            return (optBits & OPT_BIT_RESOURCES) != 0;
        }

        private boolean dependenciesIsSet() {
            return (optBits & OPT_BIT_DEPENDENCIES) != 0;
        }

        private boolean labelsIsSet() {
            return (optBits & OPT_BIT_LABELS) != 0;
        }

        private boolean environmentIsSet() {
            return (optBits & OPT_BIT_ENVIRONMENT) != 0;
        }

        private boolean connectionsIsSet() {
            return (optBits & OPT_BIT_CONNECTIONS) != 0;
        }

        private boolean continuousDeliveryStateIsSet() {
            return (optBits & OPT_BIT_CONTINUOUS_DELIVERY_STATE) != 0;
        }

        private boolean flowsIsSet() {
            return (optBits & OPT_BIT_FLOWS) != 0;
        }
    }

    private final class InitShim {
        private byte configuredPropertiesBuildStage = STAGE_UNINITIALIZED;
        private Map<String, String> configuredProperties;
        private byte versionBuildStage = STAGE_UNINITIALIZED;
        private int version;
        private byte createdAtBuildStage = STAGE_UNINITIALIZED;
        private long createdAt;
        private byte updatedAtBuildStage = STAGE_UNINITIALIZED;
        private long updatedAt;
        private byte stepsBuildStage = STAGE_UNINITIALIZED;
        private List<Step> steps;
        private byte resourcesBuildStage = STAGE_UNINITIALIZED;
        private List<ResourceIdentifier> resources;
        private byte dependenciesBuildStage = STAGE_UNINITIALIZED;
        private List<Dependency> dependencies;
        private byte labelsBuildStage = STAGE_UNINITIALIZED;
        private Map<String, String> labels;
        private byte environmentBuildStage = STAGE_UNINITIALIZED;
        private Map<String, String> environment;
        private byte connectionsBuildStage = STAGE_UNINITIALIZED;
        private List<Connection> connections;
        private byte continuousDeliveryStateBuildStage = STAGE_UNINITIALIZED;
        private Map<String, ContinuousDeliveryEnvironment>
                continuousDeliveryState;
        private byte flowsBuildStage = STAGE_UNINITIALIZED;
        private List<Flow> flows;

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

        long getCreatedAt() {
            if (createdAtBuildStage == STAGE_INITIALIZING) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (createdAtBuildStage == STAGE_UNINITIALIZED) {
                createdAtBuildStage = STAGE_INITIALIZING;
                this.createdAt = getCreatedAtInitialize();
                createdAtBuildStage = STAGE_INITIALIZED;
            }
            return this.createdAt;
        }

        void createdAt(long createdAt) {
            this.createdAt = createdAt;
            createdAtBuildStage = STAGE_INITIALIZED;
        }

        long getUpdatedAt() {
            if (updatedAtBuildStage == STAGE_INITIALIZING) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (updatedAtBuildStage == STAGE_UNINITIALIZED) {
                updatedAtBuildStage = STAGE_INITIALIZING;
                this.updatedAt = getUpdatedAtInitialize();
                updatedAtBuildStage = STAGE_INITIALIZED;
            }
            return this.updatedAt;
        }

        void updatedAt(long updatedAt) {
            this.updatedAt = updatedAt;
            updatedAtBuildStage = STAGE_INITIALIZED;
        }

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

        List<ResourceIdentifier> getResources() {
            if (resourcesBuildStage == STAGE_INITIALIZING) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (resourcesBuildStage == STAGE_UNINITIALIZED) {
                resourcesBuildStage = STAGE_INITIALIZING;
                this.resources = createUnmodifiableList(false,
                        createSafeList(getResourcesInitialize(), true, false));
                resourcesBuildStage = STAGE_INITIALIZED;
            }
            return this.resources;
        }

        void resources(List<ResourceIdentifier> resources) {
            this.resources = resources;
            resourcesBuildStage = STAGE_INITIALIZED;
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

        Map<String, String> getLabels() {
            if (labelsBuildStage == STAGE_INITIALIZING) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (labelsBuildStage == STAGE_UNINITIALIZED) {
                labelsBuildStage = STAGE_INITIALIZING;
                this.labels = createUnmodifiableMap(true, false,
                        getLabelsInitialize());
                labelsBuildStage = STAGE_INITIALIZED;
            }
            return this.labels;
        }

        void labels(Map<String, String> labels) {
            this.labels = labels;
            labelsBuildStage = STAGE_INITIALIZED;
        }

        Map<String, String> getEnvironment() {
            if (environmentBuildStage == STAGE_INITIALIZING) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (environmentBuildStage == STAGE_UNINITIALIZED) {
                environmentBuildStage = STAGE_INITIALIZING;
                this.environment = createUnmodifiableMap(true, false,
                        getEnvironmentInitialize());
                environmentBuildStage = STAGE_INITIALIZED;
            }
            return this.environment;
        }

        void environment(Map<String, String> environment) {
            this.environment = environment;
            environmentBuildStage = STAGE_INITIALIZED;
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

        Map<String, ContinuousDeliveryEnvironment> getContinuousDeliveryState() {
            if (continuousDeliveryStateBuildStage == STAGE_INITIALIZING) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (continuousDeliveryStateBuildStage == STAGE_UNINITIALIZED) {
                continuousDeliveryStateBuildStage = STAGE_INITIALIZING;
                this.continuousDeliveryState =
                        createUnmodifiableMap(true, false,
                                getContinuousDeliveryStateInitialize());
                continuousDeliveryStateBuildStage = STAGE_INITIALIZED;
            }
            return this.continuousDeliveryState;
        }

        void continuousDeliveryState(
                Map<String, ContinuousDeliveryEnvironment> continuousDeliveryState) {
            this.continuousDeliveryState = continuousDeliveryState;
            continuousDeliveryStateBuildStage = STAGE_INITIALIZED;
        }

        List<Flow> getFlows() {
            if (flowsBuildStage == STAGE_INITIALIZING) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (flowsBuildStage == STAGE_UNINITIALIZED) {
                flowsBuildStage = STAGE_INITIALIZING;
                this.flows = createUnmodifiableList(false,
                        createSafeList(getFlowsInitialize(), true, false));
                flowsBuildStage = STAGE_INITIALIZED;
            }
            return this.flows;
        }

        void flows(List<Flow> flows) {
            this.flows = flows;
            flowsBuildStage = STAGE_INITIALIZED;
        }

        private String formatInitCycleMessage() {
            List<String> attributes = new ArrayList<>();
            if (configuredPropertiesBuildStage == STAGE_INITIALIZING) {
                attributes.add("configuredProperties");
            }
            if (versionBuildStage == STAGE_INITIALIZING) {
                attributes.add("version");
            }
            if (createdAtBuildStage == STAGE_INITIALIZING) {
                attributes.add("createdAt");
            }
            if (updatedAtBuildStage == STAGE_INITIALIZING) {
                attributes.add("updatedAt");
            }
            if (stepsBuildStage == STAGE_INITIALIZING) {
                attributes.add("steps");
            }
            if (resourcesBuildStage == STAGE_INITIALIZING) {
                attributes.add("resources");
            }
            if (dependenciesBuildStage == STAGE_INITIALIZING) {
                attributes.add("dependencies");
            }
            if (labelsBuildStage == STAGE_INITIALIZING) {
                attributes.add("labels");
            }
            if (environmentBuildStage == STAGE_INITIALIZING) {
                attributes.add("environment");
            }
            if (connectionsBuildStage == STAGE_INITIALIZING) {
                attributes.add("connections");
            }
            if (continuousDeliveryStateBuildStage == STAGE_INITIALIZING) {
                attributes.add("continuousDeliveryState");
            }
            if (flowsBuildStage == STAGE_INITIALIZING) {
                attributes.add("flows");
            }
            return "Cannot build Integration, attribute initializers form cycle "
                    + attributes;
        }
    }
}
