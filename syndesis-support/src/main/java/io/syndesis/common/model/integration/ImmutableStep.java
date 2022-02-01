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
import io.syndesis.common.model.WithConfiguredProperties;
import io.syndesis.common.model.WithDependencies;
import io.syndesis.common.model.WithMetadata;
import io.syndesis.common.model.WithResourceId;
import io.syndesis.common.model.action.Action;
import io.syndesis.common.model.connection.Connection;
import io.syndesis.common.model.extension.Extension;

import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Immutable implementation of {@link io.syndesis.common.model.integration.Step}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new Step.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableStep.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableStep implements Step {
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private static final byte STAGE_INITIALIZED = 1;
    private final String id;
    private final Map<String, String> configuredProperties;
    private final List<Dependency> dependencies;
    private final Map<String, String> metadata;
    private final Action action;
    private final Connection connection;
    private final Extension extension;
    private final StepKind stepKind;
    private final String name;
    @SuppressWarnings("Immutable")
    private transient volatile InitShim initShim = new InitShim();

    @SuppressWarnings("unchecked") // safe covariant cast
    private ImmutableStep(
            Optional<String> id,
            Map<String, ? extends String> configuredProperties,
            Iterable<? extends Dependency> dependencies,
            Map<String, ? extends String> metadata,
            Optional<? extends Action> action,
            Optional<? extends Connection> connection,
            Optional<? extends Extension> extension,
            StepKind stepKind,
            String name) {
        this.id = id.orElse(null);
        this.configuredProperties =
                createUnmodifiableMap(true, false, configuredProperties);
        this.dependencies = createUnmodifiableList(false,
                createSafeList(dependencies, true, false));
        this.metadata = createUnmodifiableMap(true, false, metadata);
        this.action = action.orElse(null);
        this.connection = connection.orElse(null);
        this.extension = extension.orElse(null);
        this.stepKind = stepKind;
        this.name = name;
        this.initShim = null;
    }

    private ImmutableStep(Builder builder) {
        this.id = builder.id;
        this.action = builder.action;
        this.connection = builder.connection;
        this.extension = builder.extension;
        this.stepKind = builder.stepKind;
        this.name = builder.name;
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

    private ImmutableStep(
            ImmutableStep original,
            String id,
            Map<String, String> configuredProperties,
            List<Dependency> dependencies,
            Map<String, String> metadata,
            Action action,
            Connection connection,
            Extension extension,
            StepKind stepKind,
            String name) {
        this.id = id;
        this.configuredProperties = configuredProperties;
        this.dependencies = dependencies;
        this.metadata = metadata;
        this.action = action;
        this.connection = connection;
        this.extension = extension;
        this.stepKind = stepKind;
        this.name = name;
        this.initShim = null;
    }

    /**
     * Construct a new immutable {@code Step} instance.
     *
     * @param id                   The value for the {@code id} attribute
     * @param configuredProperties The value for the {@code configuredProperties} attribute
     * @param dependencies         The value for the {@code dependencies} attribute
     * @param metadata             The value for the {@code metadata} attribute
     * @param action               The value for the {@code action} attribute
     * @param connection           The value for the {@code connection} attribute
     * @param extension            The value for the {@code extension} attribute
     * @param stepKind             The value for the {@code stepKind} attribute
     * @param name                 The value for the {@code name} attribute
     * @return An immutable Step instance
     */
    public static Step of(Optional<String> id,
                          Map<String, String> configuredProperties,
                          List<Dependency> dependencies,
                          Map<String, String> metadata, Optional<Action> action,
                          Optional<Connection> connection,
                          Optional<Extension> extension, StepKind stepKind,
                          String name) {
        return of(id, configuredProperties,
                (Iterable<? extends Dependency>) dependencies, metadata, action,
                connection, extension, stepKind, name);
    }

    /**
     * Construct a new immutable {@code Step} instance.
     *
     * @param id                   The value for the {@code id} attribute
     * @param configuredProperties The value for the {@code configuredProperties} attribute
     * @param dependencies         The value for the {@code dependencies} attribute
     * @param metadata             The value for the {@code metadata} attribute
     * @param action               The value for the {@code action} attribute
     * @param connection           The value for the {@code connection} attribute
     * @param extension            The value for the {@code extension} attribute
     * @param stepKind             The value for the {@code stepKind} attribute
     * @param name                 The value for the {@code name} attribute
     * @return An immutable Step instance
     */
    public static Step of(Optional<String> id,
                          Map<String, ? extends String> configuredProperties,
                          Iterable<? extends Dependency> dependencies,
                          Map<String, ? extends String> metadata,
                          Optional<? extends Action> action,
                          Optional<? extends Connection> connection,
                          Optional<? extends Extension> extension,
                          StepKind stepKind, String name) {
        return validate(
                new ImmutableStep(id, configuredProperties, dependencies,
                        metadata, action, connection, extension, stepKind,
                        name));
    }

    private static ImmutableStep validate(ImmutableStep instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.integration.Step} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable Step instance
     */
    public static Step copyOf(Step instance) {
        if (instance instanceof ImmutableStep) {
            return (ImmutableStep) instance;
        }
        return new Step.Builder()
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
        return Step.super.getConfiguredProperties();
    }

    private List<Dependency> getDependenciesInitialize() {
        return Step.super.getDependencies();
    }

    private Map<String, String> getMetadataInitialize() {
        return Step.super.getMetadata();
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
     * @return The value of the {@code action} attribute
     */
    @JsonProperty("action")
    @Override
    public Optional<Action> getAction() {
        return Optional.ofNullable(action);
    }

    /**
     * @return The value of the {@code connection} attribute
     */
    @JsonProperty("connection")
    @Override
    public Optional<Connection> getConnection() {
        return Optional.ofNullable(connection);
    }

    /**
     * @return The value of the {@code extension} attribute
     */
    @JsonProperty("extension")
    @Override
    public Optional<Extension> getExtension() {
        return Optional.ofNullable(extension);
    }

    /**
     * @return The value of the {@code stepKind} attribute
     */
    @JsonProperty("stepKind")
    @Override
    public StepKind getStepKind() {
        return stepKind;
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
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.integration.Step#getId() id} attribute.
     *
     * @param value The value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableStep withId(String value) {
        String newValue = Objects.requireNonNull(value, "id");
        if (Objects.equals(this.id, newValue)) {
            return this;
        }
        return validate(new ImmutableStep(
                this,
                newValue,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.action,
                this.connection,
                this.extension,
                this.stepKind,
                this.name));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.integration.Step#getId() id} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableStep withId(Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.id, value)) {
            return this;
        }
        return validate(new ImmutableStep(
                this,
                value,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.action,
                this.connection,
                this.extension,
                this.stepKind,
                this.name));
    }

    /**
     * Copy the current immutable object by replacing the {@link io.syndesis.common.model.integration.Step#getConfiguredProperties() configuredProperties} map with the specified map.
     * Nulls are not permitted as keys or values.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param entries The entries to be added to the configuredProperties map
     * @return A modified copy of {@code this} object
     */
    public final ImmutableStep withConfiguredProperties(
            Map<String, ? extends String> entries) {
        if (this.configuredProperties == entries) {
            return this;
        }
        Map<String, String> newValue =
                createUnmodifiableMap(true, false, entries);
        return validate(new ImmutableStep(
                this,
                this.id,
                newValue,
                this.dependencies,
                this.metadata,
                this.action,
                this.connection,
                this.extension,
                this.stepKind,
                this.name));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.integration.Step#getDependencies() dependencies}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableStep withDependencies(Dependency... elements) {
        List<Dependency> newValue = createUnmodifiableList(false,
                createSafeList(Arrays.asList(elements), true, false));
        return validate(new ImmutableStep(
                this,
                this.id,
                this.configuredProperties,
                newValue,
                this.metadata,
                this.action,
                this.connection,
                this.extension,
                this.stepKind,
                this.name));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.integration.Step#getDependencies() dependencies}.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of dependencies elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableStep withDependencies(
            Iterable<? extends Dependency> elements) {
        if (this.dependencies == elements) {
            return this;
        }
        List<Dependency> newValue = createUnmodifiableList(false,
                createSafeList(elements, true, false));
        return validate(new ImmutableStep(
                this,
                this.id,
                this.configuredProperties,
                newValue,
                this.metadata,
                this.action,
                this.connection,
                this.extension,
                this.stepKind,
                this.name));
    }

    /**
     * Copy the current immutable object by replacing the {@link io.syndesis.common.model.integration.Step#getMetadata() metadata} map with the specified map.
     * Nulls are not permitted as keys or values.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param entries The entries to be added to the metadata map
     * @return A modified copy of {@code this} object
     */
    public final ImmutableStep withMetadata(
            Map<String, ? extends String> entries) {
        if (this.metadata == entries) {
            return this;
        }
        Map<String, String> newValue =
                createUnmodifiableMap(true, false, entries);
        return validate(new ImmutableStep(
                this,
                this.id,
                this.configuredProperties,
                this.dependencies,
                newValue,
                this.action,
                this.connection,
                this.extension,
                this.stepKind,
                this.name));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.integration.Step#getAction() action} attribute.
     *
     * @param value The value for action
     * @return A modified copy of {@code this} object
     */
    public final ImmutableStep withAction(Action value) {
        Action newValue = Objects.requireNonNull(value, "action");
        if (this.action == newValue) {
            return this;
        }
        return validate(new ImmutableStep(
                this,
                this.id,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                newValue,
                this.connection,
                this.extension,
                this.stepKind,
                this.name));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.integration.Step#getAction() action} attribute.
     * A shallow reference equality check is used on unboxed optional value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for action
     * @return A modified copy of {@code this} object
     */
    @SuppressWarnings("unchecked") // safe covariant cast
    public final ImmutableStep withAction(Optional<? extends Action> optional) {
        Action value = optional.orElse(null);
        if (this.action == value) {
            return this;
        }
        return validate(new ImmutableStep(
                this,
                this.id,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                value,
                this.connection,
                this.extension,
                this.stepKind,
                this.name));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.integration.Step#getConnection() connection} attribute.
     *
     * @param value The value for connection
     * @return A modified copy of {@code this} object
     */
    public final ImmutableStep withConnection(Connection value) {
        Connection newValue = Objects.requireNonNull(value, "connection");
        if (this.connection == newValue) {
            return this;
        }
        return validate(new ImmutableStep(
                this,
                this.id,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.action,
                newValue,
                this.extension,
                this.stepKind,
                this.name));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.integration.Step#getConnection() connection} attribute.
     * A shallow reference equality check is used on unboxed optional value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for connection
     * @return A modified copy of {@code this} object
     */
    @SuppressWarnings("unchecked") // safe covariant cast
    public final ImmutableStep withConnection(
            Optional<? extends Connection> optional) {
        Connection value = optional.orElse(null);
        if (this.connection == value) {
            return this;
        }
        return validate(new ImmutableStep(
                this,
                this.id,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.action,
                value,
                this.extension,
                this.stepKind,
                this.name));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.integration.Step#getExtension() extension} attribute.
     *
     * @param value The value for extension
     * @return A modified copy of {@code this} object
     */
    public final ImmutableStep withExtension(Extension value) {
        Extension newValue = Objects.requireNonNull(value, "extension");
        if (this.extension == newValue) {
            return this;
        }
        return validate(new ImmutableStep(
                this,
                this.id,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.action,
                this.connection,
                newValue,
                this.stepKind,
                this.name));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.integration.Step#getExtension() extension} attribute.
     * A shallow reference equality check is used on unboxed optional value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for extension
     * @return A modified copy of {@code this} object
     */
    @SuppressWarnings("unchecked") // safe covariant cast
    public final ImmutableStep withExtension(
            Optional<? extends Extension> optional) {
        Extension value = optional.orElse(null);
        if (this.extension == value) {
            return this;
        }
        return validate(new ImmutableStep(
                this,
                this.id,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.action,
                this.connection,
                value,
                this.stepKind,
                this.name));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.integration.Step#getStepKind() stepKind} attribute.
     * A value equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for stepKind (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableStep withStepKind(StepKind value) {
        if (this.stepKind == value) {
            return this;
        }
        if (Objects.equals(this.stepKind, value)) {
            return this;
        }
        return validate(new ImmutableStep(
                this,
                this.id,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.action,
                this.connection,
                this.extension,
                value,
                this.name));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.integration.Step#getName() name} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for name (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableStep withName(String value) {
        if (Objects.equals(this.name, value)) {
            return this;
        }
        return validate(new ImmutableStep(
                this,
                this.id,
                this.configuredProperties,
                this.dependencies,
                this.metadata,
                this.action,
                this.connection,
                this.extension,
                this.stepKind,
                value));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableStep} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableStep
                && equalTo((ImmutableStep) another);
    }

    private boolean equalTo(ImmutableStep another) {
        return Objects.equals(id, another.id)
                && configuredProperties.equals(another.configuredProperties)
                && dependencies.equals(another.dependencies)
                && metadata.equals(another.metadata)
                && Objects.equals(action, another.action)
                && Objects.equals(connection, another.connection)
                && Objects.equals(extension, another.extension)
                && Objects.equals(stepKind, another.stepKind)
                && Objects.equals(name, another.name);
    }

    /**
     * Computes a hash code from attributes: {@code id}, {@code configuredProperties}, {@code dependencies}, {@code metadata}, {@code action}, {@code connection}, {@code extension}, {@code stepKind}, {@code name}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(id);
        h += (h << 5) + configuredProperties.hashCode();
        h += (h << 5) + dependencies.hashCode();
        h += (h << 5) + metadata.hashCode();
        h += (h << 5) + Objects.hashCode(action);
        h += (h << 5) + Objects.hashCode(connection);
        h += (h << 5) + Objects.hashCode(extension);
        h += (h << 5) + Objects.hashCode(stepKind);
        h += (h << 5) + Objects.hashCode(name);
        return h;
    }

    /**
     * Prints the immutable value {@code Step} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Step{");
        if (id != null) {
            builder.append("id=").append(id);
        }
        if (builder.length() > 5) {
            builder.append(", ");
        }
        builder.append("configuredProperties=").append(configuredProperties);
        builder.append(", ");
        builder.append("dependencies=").append(dependencies);
        builder.append(", ");
        builder.append("metadata=").append(metadata);
        if (action != null) {
            builder.append(", ");
            builder.append("action=").append(action);
        }
        if (connection != null) {
            builder.append(", ");
            builder.append("connection=").append(connection);
        }
        if (extension != null) {
            builder.append(", ");
            builder.append("extension=").append(extension);
        }
        if (stepKind != null) {
            builder.append(", ");
            builder.append("stepKind=").append(stepKind);
        }
        if (name != null) {
            builder.append(", ");
            builder.append("name=").append(name);
        }
        return builder.append("}").toString();
    }

    private Object readResolve() throws ObjectStreamException {
        return validate(this);
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.integration.Step Step}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private static final long OPT_BIT_CONFIGURED_PROPERTIES = 0x1L;
        private static final long OPT_BIT_DEPENDENCIES = 0x2L;
        private static final long OPT_BIT_METADATA = 0x4L;
        private long optBits;

        private String id;
        private Map<String, String> configuredProperties =
                new LinkedHashMap<String, String>();
        private List<Dependency> dependencies = new ArrayList<Dependency>();
        private Map<String, String> metadata =
                new LinkedHashMap<String, String>();
        private Action action;
        private Connection connection;
        private Extension extension;
        private StepKind stepKind;
        private String name;

        /**
         * Creates a builder for {@link io.syndesis.common.model.integration.Step Step} instances.
         * <pre>
         * new Step.Builder()
         *    .id(String) // optional {@link io.syndesis.common.model.integration.Step#getId() id}
         *    .putConfiguredProperty|putAllConfiguredProperties(String =&gt; String) // {@link io.syndesis.common.model.integration.Step#getConfiguredProperties() configuredProperties} mappings
         *    .addDependency|addAllDependencies(io.syndesis.common.io.syndesis.common.model.Dependency) // {@link io.syndesis.common.model.integration.Step#getDependencies() dependencies} elements
         *    .putMetadata|putAllMetadata(String =&gt; String) // {@link io.syndesis.common.model.integration.Step#getMetadata() metadata} mappings
         *    .action(io.syndesis.common.io.syndesis.common.model.action.Action) // optional {@link io.syndesis.common.model.integration.Step#getAction() action}
         *    .connection(io.syndesis.common.io.syndesis.common.model.connection.Connection) // optional {@link io.syndesis.common.model.integration.Step#getConnection() connection}
         *    .extension(io.syndesis.common.io.syndesis.common.model.extension.Extension) // optional {@link io.syndesis.common.model.integration.Step#getExtension() extension}
         *    .stepKind(io.syndesis.common.io.syndesis.common.model.integration.StepKind | null) // nullable {@link io.syndesis.common.model.integration.Step#getStepKind() stepKind}
         *    .name(String | null) // nullable {@link io.syndesis.common.model.integration.Step#getName() name}
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof Step.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new Step.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithConfiguredProperties} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Step.Builder createFrom(
                WithConfiguredProperties instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Step.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.integration.Step} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Step.Builder createFrom(Step instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Step.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithDependencies} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Step.Builder createFrom(WithDependencies instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Step.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithMetadata} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Step.Builder createFrom(WithMetadata instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Step.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithResourceId} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Step.Builder createFrom(WithResourceId instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (Step.Builder) this;
        }

        private void from(Object object) {
            if (object instanceof WithConfiguredProperties) {
                WithConfiguredProperties instance =
                        (WithConfiguredProperties) object;
                putAllConfiguredProperties(instance.getConfiguredProperties());
            }
            if (object instanceof Step) {
                Step instance = (Step) object;
                String nameValue = instance.getName();
                if (nameValue != null) {
                    name(nameValue);
                }
                StepKind stepKindValue = instance.getStepKind();
                if (stepKindValue != null) {
                    stepKind(stepKindValue);
                }
                Optional<Action> actionOptional = instance.getAction();
                if (actionOptional.isPresent()) {
                    action(actionOptional);
                }
                Optional<Extension> extensionOptional = instance.getExtension();
                if (extensionOptional.isPresent()) {
                    extension(extensionOptional);
                }
                Optional<Connection> connectionOptional =
                        instance.getConnection();
                if (connectionOptional.isPresent()) {
                    connection(connectionOptional);
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
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.integration.Step#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final Step.Builder id(String id) {
            this.id = Objects.requireNonNull(id, "id");
            return (Step.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.integration.Step#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("id")
        public final Step.Builder id(Optional<String> id) {
            this.id = id.orElse(null);
            return (Step.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.integration.Step#getConfiguredProperties() configuredProperties} map.
         *
         * @param key   The key in the configuredProperties map
         * @param value The associated value in the configuredProperties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Step.Builder putConfiguredProperty(String key,
                                                        String value) {
            this.configuredProperties.put(
                    Objects.requireNonNull(key, "configuredProperties key"),
                    Objects.requireNonNull(value,
                            "configuredProperties value"));
            optBits |= OPT_BIT_CONFIGURED_PROPERTIES;
            return (Step.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.integration.Step#getConfiguredProperties() configuredProperties} map. Nulls are not permitted
         *
         * @param entry The key and value entry
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Step.Builder putConfiguredProperty(
                Map.Entry<String, ? extends String> entry) {
            String k = entry.getKey();
            String v = entry.getValue();
            this.configuredProperties.put(
                    Objects.requireNonNull(k, "configuredProperties key"),
                    Objects.requireNonNull(v, "configuredProperties value"));
            optBits |= OPT_BIT_CONFIGURED_PROPERTIES;
            return (Step.Builder) this;
        }

        /**
         * Sets or replaces all mappings from the specified map as entries for the {@link io.syndesis.common.model.integration.Step#getConfiguredProperties() configuredProperties} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the configuredProperties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("configuredProperties")
        public final Step.Builder configuredProperties(
                Map<String, ? extends String> entries) {
            this.configuredProperties.clear();
            optBits |= OPT_BIT_CONFIGURED_PROPERTIES;
            return putAllConfiguredProperties(entries);
        }

        /**
         * Put all mappings from the specified map as entries to {@link io.syndesis.common.model.integration.Step#getConfiguredProperties() configuredProperties} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the configuredProperties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Step.Builder putAllConfiguredProperties(
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
            return (Step.Builder) this;
        }

        /**
         * Adds one element to {@link io.syndesis.common.model.integration.Step#getDependencies() dependencies} list.
         *
         * @param element A dependencies element
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Step.Builder addDependency(Dependency element) {
            this.dependencies.add(
                    Objects.requireNonNull(element, "dependencies element"));
            optBits |= OPT_BIT_DEPENDENCIES;
            return (Step.Builder) this;
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.integration.Step#getDependencies() dependencies} list.
         *
         * @param elements An array of dependencies elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Step.Builder addDependencies(Dependency... elements) {
            for (Dependency element : elements) {
                this.dependencies.add(Objects.requireNonNull(element,
                        "dependencies element"));
            }
            optBits |= OPT_BIT_DEPENDENCIES;
            return (Step.Builder) this;
        }


        /**
         * Sets or replaces all elements for {@link io.syndesis.common.model.integration.Step#getDependencies() dependencies} list.
         *
         * @param elements An iterable of dependencies elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("dependencies")
        public final Step.Builder dependencies(
                Iterable<? extends Dependency> elements) {
            this.dependencies.clear();
            return addAllDependencies(elements);
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.integration.Step#getDependencies() dependencies} list.
         *
         * @param elements An iterable of dependencies elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Step.Builder addAllDependencies(
                Iterable<? extends Dependency> elements) {
            for (Dependency element : elements) {
                this.dependencies.add(Objects.requireNonNull(element,
                        "dependencies element"));
            }
            optBits |= OPT_BIT_DEPENDENCIES;
            return (Step.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.integration.Step#getMetadata() metadata} map.
         *
         * @param key   The key in the metadata map
         * @param value The associated value in the metadata map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Step.Builder putMetadata(String key, String value) {
            this.metadata.put(
                    Objects.requireNonNull(key, "metadata key"),
                    Objects.requireNonNull(value, "metadata value"));
            optBits |= OPT_BIT_METADATA;
            return (Step.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.integration.Step#getMetadata() metadata} map. Nulls are not permitted
         *
         * @param entry The key and value entry
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Step.Builder putMetadata(
                Map.Entry<String, ? extends String> entry) {
            String k = entry.getKey();
            String v = entry.getValue();
            this.metadata.put(
                    Objects.requireNonNull(k, "metadata key"),
                    Objects.requireNonNull(v, "metadata value"));
            optBits |= OPT_BIT_METADATA;
            return (Step.Builder) this;
        }

        /**
         * Sets or replaces all mappings from the specified map as entries for the {@link io.syndesis.common.model.integration.Step#getMetadata() metadata} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the metadata map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("metadata")
        public final Step.Builder metadata(
                Map<String, ? extends String> entries) {
            this.metadata.clear();
            optBits |= OPT_BIT_METADATA;
            return putAllMetadata(entries);
        }

        /**
         * Put all mappings from the specified map as entries to {@link io.syndesis.common.model.integration.Step#getMetadata() metadata} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the metadata map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final Step.Builder putAllMetadata(
                Map<String, ? extends String> entries) {
            for (Map.Entry<String, ? extends String> e : entries.entrySet()) {
                String k = e.getKey();
                String v = e.getValue();
                this.metadata.put(
                        Objects.requireNonNull(k, "metadata key"),
                        Objects.requireNonNull(v, "metadata value"));
            }
            optBits |= OPT_BIT_METADATA;
            return (Step.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.integration.Step#getAction() action} to action.
         *
         * @param action The value for action
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final Step.Builder action(Action action) {
            this.action = Objects.requireNonNull(action, "action");
            return (Step.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.integration.Step#getAction() action} to action.
         *
         * @param action The value for action
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("action")
        public final Step.Builder action(Optional<? extends Action> action) {
            this.action = action.orElse(null);
            return (Step.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.integration.Step#getConnection() connection} to connection.
         *
         * @param connection The value for connection
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final Step.Builder connection(Connection connection) {
            this.connection = Objects.requireNonNull(connection, "connection");
            return (Step.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.integration.Step#getConnection() connection} to connection.
         *
         * @param connection The value for connection
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("connection")
        public final Step.Builder connection(
                Optional<? extends Connection> connection) {
            this.connection = connection.orElse(null);
            return (Step.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.integration.Step#getExtension() extension} to extension.
         *
         * @param extension The value for extension
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final Step.Builder extension(Extension extension) {
            this.extension = Objects.requireNonNull(extension, "extension");
            return (Step.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.integration.Step#getExtension() extension} to extension.
         *
         * @param extension The value for extension
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("extension")
        public final Step.Builder extension(
                Optional<? extends Extension> extension) {
            this.extension = extension.orElse(null);
            return (Step.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.integration.Step#getStepKind() stepKind} attribute.
         *
         * @param stepKind The value for stepKind (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("stepKind")
        public final Step.Builder stepKind(StepKind stepKind) {
            this.stepKind = stepKind;
            return (Step.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.integration.Step#getName() name} attribute.
         *
         * @param name The value for name (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("name")
        public final Step.Builder name(String name) {
            this.name = name;
            return (Step.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.integration.Step Step}.
         *
         * @return An immutable instance of Step
         * @throws IllegalStateException if any required attributes are missing
         */
        public Step build() {
            return ImmutableStep.validate(new ImmutableStep(this));
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
            return "Cannot build Step, attribute initializers form cycle "
                    + attributes;
        }
    }
}
