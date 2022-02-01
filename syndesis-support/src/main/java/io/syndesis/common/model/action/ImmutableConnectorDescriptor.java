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
import io.syndesis.common.model.DataShape;
import io.syndesis.common.model.WithConfiguredProperties;

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
 * Immutable implementation of {@link io.syndesis.common.model.action.ConnectorDescriptor}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new ConnectorDescriptor.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableConnectorDescriptor.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableConnectorDescriptor implements ConnectorDescriptor {
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private static final byte STAGE_INITIALIZED = 1;
    private final DataShape inputDataShape;
    private final DataShape outputDataShape;
    private final List<ActionDescriptorStep> propertyDefinitionSteps;
    private final Map<String, String> configuredProperties;
    private final String connectorId;
    private final String camelConnectorGAV;
    private final String camelConnectorPrefix;
    private final String componentScheme;
    private final String connectorFactory;
    private final List<String> connectorCustomizers;
    private final String exceptionHandler;
    private final List<StandardizedError> standardizedErrors;
    @SuppressWarnings("Immutable")
    private transient volatile InitShim initShim = new InitShim();

    @SuppressWarnings("unchecked") // safe covariant cast
    private ImmutableConnectorDescriptor(
            Optional<? extends DataShape> inputDataShape,
            Optional<? extends DataShape> outputDataShape,
            Iterable<? extends ActionDescriptorStep> propertyDefinitionSteps,
            Map<String, ? extends String> configuredProperties,
            String connectorId,
            String camelConnectorGAV,
            String camelConnectorPrefix,
            Optional<String> componentScheme,
            Optional<String> connectorFactory,
            Iterable<String> connectorCustomizers,
            Optional<String> exceptionHandler,
            Iterable<? extends StandardizedError> standardizedErrors) {
        this.inputDataShape = inputDataShape.orElse(null);
        this.outputDataShape = outputDataShape.orElse(null);
        this.propertyDefinitionSteps = createUnmodifiableList(false,
                createSafeList(propertyDefinitionSteps, true, false));
        this.configuredProperties =
                createUnmodifiableMap(true, false, configuredProperties);
        this.connectorId = connectorId;
        this.camelConnectorGAV = camelConnectorGAV;
        this.camelConnectorPrefix = camelConnectorPrefix;
        this.componentScheme = componentScheme.orElse(null);
        this.connectorFactory = connectorFactory.orElse(null);
        this.connectorCustomizers = createUnmodifiableList(false,
                createSafeList(connectorCustomizers, true, false));
        this.exceptionHandler = exceptionHandler.orElse(null);
        this.standardizedErrors = createUnmodifiableList(false,
                createSafeList(standardizedErrors, true, false));
        this.initShim = null;
    }

    private ImmutableConnectorDescriptor(Builder builder) {
        this.inputDataShape = builder.inputDataShape;
        this.outputDataShape = builder.outputDataShape;
        this.connectorId = builder.connectorId;
        this.camelConnectorGAV = builder.camelConnectorGAV;
        this.camelConnectorPrefix = builder.camelConnectorPrefix;
        this.componentScheme = builder.componentScheme;
        this.connectorFactory = builder.connectorFactory;
        this.exceptionHandler = builder.exceptionHandler;
        if (builder.propertyDefinitionStepsIsSet()) {
            initShim.propertyDefinitionSteps(createUnmodifiableList(true,
                    builder.propertyDefinitionSteps));
        }
        if (builder.configuredPropertiesIsSet()) {
            initShim.configuredProperties(createUnmodifiableMap(false, false,
                    builder.configuredProperties));
        }
        if (builder.connectorCustomizersIsSet()) {
            initShim.connectorCustomizers(
                    createUnmodifiableList(true, builder.connectorCustomizers));
        }
        if (builder.standardizedErrorsIsSet()) {
            initShim.standardizedErrors(
                    createUnmodifiableList(true, builder.standardizedErrors));
        }
        this.propertyDefinitionSteps = initShim.getPropertyDefinitionSteps();
        this.configuredProperties = initShim.getConfiguredProperties();
        this.connectorCustomizers = initShim.getConnectorCustomizers();
        this.standardizedErrors = initShim.getStandardizedErrors();
        this.initShim = null;
    }

    private ImmutableConnectorDescriptor(
            ImmutableConnectorDescriptor original,
            DataShape inputDataShape,
            DataShape outputDataShape,
            List<ActionDescriptorStep> propertyDefinitionSteps,
            Map<String, String> configuredProperties,
            String connectorId,
            String camelConnectorGAV,
            String camelConnectorPrefix,
            String componentScheme,
            String connectorFactory,
            List<String> connectorCustomizers,
            String exceptionHandler,
            List<StandardizedError> standardizedErrors) {
        this.inputDataShape = inputDataShape;
        this.outputDataShape = outputDataShape;
        this.propertyDefinitionSteps = propertyDefinitionSteps;
        this.configuredProperties = configuredProperties;
        this.connectorId = connectorId;
        this.camelConnectorGAV = camelConnectorGAV;
        this.camelConnectorPrefix = camelConnectorPrefix;
        this.componentScheme = componentScheme;
        this.connectorFactory = connectorFactory;
        this.connectorCustomizers = connectorCustomizers;
        this.exceptionHandler = exceptionHandler;
        this.standardizedErrors = standardizedErrors;
        this.initShim = null;
    }

    /**
     * Construct a new immutable {@code ConnectorDescriptor} instance.
     *
     * @param inputDataShape          The value for the {@code inputDataShape} attribute
     * @param outputDataShape         The value for the {@code outputDataShape} attribute
     * @param propertyDefinitionSteps The value for the {@code propertyDefinitionSteps} attribute
     * @param configuredProperties    The value for the {@code configuredProperties} attribute
     * @param connectorId             The value for the {@code connectorId} attribute
     * @param camelConnectorGAV       The value for the {@code camelConnectorGAV} attribute
     * @param camelConnectorPrefix    The value for the {@code camelConnectorPrefix} attribute
     * @param componentScheme         The value for the {@code componentScheme} attribute
     * @param connectorFactory        The value for the {@code connectorFactory} attribute
     * @param connectorCustomizers    The value for the {@code connectorCustomizers} attribute
     * @param exceptionHandler        The value for the {@code exceptionHandler} attribute
     * @param standardizedErrors      The value for the {@code standardizedErrors} attribute
     * @return An immutable ConnectorDescriptor instance
     */
    public static ConnectorDescriptor of(Optional<DataShape> inputDataShape,
                                         Optional<DataShape> outputDataShape,
                                         List<ActionDescriptorStep> propertyDefinitionSteps,
                                         Map<String, String> configuredProperties,
                                         String connectorId,
                                         String camelConnectorGAV,
                                         String camelConnectorPrefix,
                                         Optional<String> componentScheme,
                                         Optional<String> connectorFactory,
                                         List<String> connectorCustomizers,
                                         Optional<String> exceptionHandler,
                                         List<StandardizedError> standardizedErrors) {
        return of(inputDataShape, outputDataShape,
                (Iterable<? extends ActionDescriptorStep>) propertyDefinitionSteps,
                configuredProperties, connectorId, camelConnectorGAV,
                camelConnectorPrefix, componentScheme, connectorFactory,
                (Iterable<String>) connectorCustomizers, exceptionHandler,
                (Iterable<? extends StandardizedError>) standardizedErrors);
    }

    /**
     * Construct a new immutable {@code ConnectorDescriptor} instance.
     *
     * @param inputDataShape          The value for the {@code inputDataShape} attribute
     * @param outputDataShape         The value for the {@code outputDataShape} attribute
     * @param propertyDefinitionSteps The value for the {@code propertyDefinitionSteps} attribute
     * @param configuredProperties    The value for the {@code configuredProperties} attribute
     * @param connectorId             The value for the {@code connectorId} attribute
     * @param camelConnectorGAV       The value for the {@code camelConnectorGAV} attribute
     * @param camelConnectorPrefix    The value for the {@code camelConnectorPrefix} attribute
     * @param componentScheme         The value for the {@code componentScheme} attribute
     * @param connectorFactory        The value for the {@code connectorFactory} attribute
     * @param connectorCustomizers    The value for the {@code connectorCustomizers} attribute
     * @param exceptionHandler        The value for the {@code exceptionHandler} attribute
     * @param standardizedErrors      The value for the {@code standardizedErrors} attribute
     * @return An immutable ConnectorDescriptor instance
     */
    public static ConnectorDescriptor of(
            Optional<? extends DataShape> inputDataShape,
            Optional<? extends DataShape> outputDataShape,
            Iterable<? extends ActionDescriptorStep> propertyDefinitionSteps,
            Map<String, ? extends String> configuredProperties,
            String connectorId, String camelConnectorGAV,
            String camelConnectorPrefix, Optional<String> componentScheme,
            Optional<String> connectorFactory,
            Iterable<String> connectorCustomizers,
            Optional<String> exceptionHandler,
            Iterable<? extends StandardizedError> standardizedErrors) {
        return validate(new ImmutableConnectorDescriptor(inputDataShape,
                outputDataShape, propertyDefinitionSteps, configuredProperties,
                connectorId, camelConnectorGAV, camelConnectorPrefix,
                componentScheme, connectorFactory, connectorCustomizers,
                exceptionHandler, standardizedErrors));
    }

    private static ImmutableConnectorDescriptor validate(
            ImmutableConnectorDescriptor instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.action.ConnectorDescriptor} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable ConnectorDescriptor instance
     */
    public static ConnectorDescriptor copyOf(ConnectorDescriptor instance) {
        if (instance instanceof ImmutableConnectorDescriptor) {
            return (ImmutableConnectorDescriptor) instance;
        }
        return new ConnectorDescriptor.Builder()
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

    private List<ActionDescriptorStep> getPropertyDefinitionStepsInitialize() {
        return ConnectorDescriptor.super.getPropertyDefinitionSteps();
    }

    private Map<String, String> getConfiguredPropertiesInitialize() {
        return ConnectorDescriptor.super.getConfiguredProperties();
    }

    private List<String> getConnectorCustomizersInitialize() {
        return ConnectorDescriptor.super.getConnectorCustomizers();
    }

    private List<StandardizedError> getStandardizedErrorsInitialize() {
        return ConnectorDescriptor.super.getStandardizedErrors();
    }

    /**
     * @return The value of the {@code inputDataShape} attribute
     */
    @JsonProperty("inputDataShape")
    @Override
    public Optional<DataShape> getInputDataShape() {
        return Optional.ofNullable(inputDataShape);
    }

    /**
     * @return The value of the {@code outputDataShape} attribute
     */
    @JsonProperty("outputDataShape")
    @Override
    public Optional<DataShape> getOutputDataShape() {
        return Optional.ofNullable(outputDataShape);
    }

    /**
     * @return The value of the {@code propertyDefinitionSteps} attribute
     */
    @JsonProperty("propertyDefinitionSteps")
    @Override
    public List<ActionDescriptorStep> getPropertyDefinitionSteps() {
        InitShim shim = this.initShim;
        return shim != null
                ? shim.getPropertyDefinitionSteps()
                : this.propertyDefinitionSteps;
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
     * @return The value of the {@code connectorId} attribute
     */
    @JsonProperty("connectorId")
    @Override
    public String getConnectorId() {
        return connectorId;
    }

    /**
     * @return The value of the {@code camelConnectorGAV} attribute
     */
    @JsonProperty("camelConnectorGAV")
    @Override
    public String getCamelConnectorGAV() {
        return camelConnectorGAV;
    }

    /**
     * @return The value of the {@code camelConnectorPrefix} attribute
     */
    @JsonProperty("camelConnectorPrefix")
    @Override
    public String getCamelConnectorPrefix() {
        return camelConnectorPrefix;
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
     * @return The value of the {@code standardizedErrors} attribute
     */
    @JsonProperty("standardizedErrors")
    @Override
    public List<StandardizedError> getStandardizedErrors() {
        InitShim shim = this.initShim;
        return shim != null
                ? shim.getStandardizedErrors()
                : this.standardizedErrors;
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.action.ConnectorDescriptor#getInputDataShape() inputDataShape} attribute.
     *
     * @param value The value for inputDataShape
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnectorDescriptor withInputDataShape(
            DataShape value) {
        DataShape newValue = Objects.requireNonNull(value, "inputDataShape");
        if (this.inputDataShape == newValue) {
            return this;
        }
        return validate(new ImmutableConnectorDescriptor(
                this,
                newValue,
                this.outputDataShape,
                this.propertyDefinitionSteps,
                this.configuredProperties,
                this.connectorId,
                this.camelConnectorGAV,
                this.camelConnectorPrefix,
                this.componentScheme,
                this.connectorFactory,
                this.connectorCustomizers,
                this.exceptionHandler,
                this.standardizedErrors));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.action.ConnectorDescriptor#getInputDataShape() inputDataShape} attribute.
     * A shallow reference equality check is used on unboxed optional value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for inputDataShape
     * @return A modified copy of {@code this} object
     */
    @SuppressWarnings("unchecked") // safe covariant cast
    public final ImmutableConnectorDescriptor withInputDataShape(
            Optional<? extends DataShape> optional) {
        DataShape value = optional.orElse(null);
        if (this.inputDataShape == value) {
            return this;
        }
        return validate(new ImmutableConnectorDescriptor(
                this,
                value,
                this.outputDataShape,
                this.propertyDefinitionSteps,
                this.configuredProperties,
                this.connectorId,
                this.camelConnectorGAV,
                this.camelConnectorPrefix,
                this.componentScheme,
                this.connectorFactory,
                this.connectorCustomizers,
                this.exceptionHandler,
                this.standardizedErrors));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.action.ConnectorDescriptor#getOutputDataShape() outputDataShape} attribute.
     *
     * @param value The value for outputDataShape
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnectorDescriptor withOutputDataShape(
            DataShape value) {
        DataShape newValue = Objects.requireNonNull(value, "outputDataShape");
        if (this.outputDataShape == newValue) {
            return this;
        }
        return validate(new ImmutableConnectorDescriptor(
                this,
                this.inputDataShape,
                newValue,
                this.propertyDefinitionSteps,
                this.configuredProperties,
                this.connectorId,
                this.camelConnectorGAV,
                this.camelConnectorPrefix,
                this.componentScheme,
                this.connectorFactory,
                this.connectorCustomizers,
                this.exceptionHandler,
                this.standardizedErrors));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.action.ConnectorDescriptor#getOutputDataShape() outputDataShape} attribute.
     * A shallow reference equality check is used on unboxed optional value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for outputDataShape
     * @return A modified copy of {@code this} object
     */
    @SuppressWarnings("unchecked") // safe covariant cast
    public final ImmutableConnectorDescriptor withOutputDataShape(
            Optional<? extends DataShape> optional) {
        DataShape value = optional.orElse(null);
        if (this.outputDataShape == value) {
            return this;
        }
        return validate(new ImmutableConnectorDescriptor(
                this,
                this.inputDataShape,
                value,
                this.propertyDefinitionSteps,
                this.configuredProperties,
                this.connectorId,
                this.camelConnectorGAV,
                this.camelConnectorPrefix,
                this.componentScheme,
                this.connectorFactory,
                this.connectorCustomizers,
                this.exceptionHandler,
                this.standardizedErrors));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.action.ConnectorDescriptor#getPropertyDefinitionSteps() propertyDefinitionSteps}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnectorDescriptor withPropertyDefinitionSteps(
            ActionDescriptorStep... elements) {
        List<ActionDescriptorStep> newValue = createUnmodifiableList(false,
                createSafeList(Arrays.asList(elements), true, false));
        return validate(new ImmutableConnectorDescriptor(
                this,
                this.inputDataShape,
                this.outputDataShape,
                newValue,
                this.configuredProperties,
                this.connectorId,
                this.camelConnectorGAV,
                this.camelConnectorPrefix,
                this.componentScheme,
                this.connectorFactory,
                this.connectorCustomizers,
                this.exceptionHandler,
                this.standardizedErrors));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.action.ConnectorDescriptor#getPropertyDefinitionSteps() propertyDefinitionSteps}.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of propertyDefinitionSteps elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnectorDescriptor withPropertyDefinitionSteps(
            Iterable<? extends ActionDescriptorStep> elements) {
        if (this.propertyDefinitionSteps == elements) {
            return this;
        }
        List<ActionDescriptorStep> newValue = createUnmodifiableList(false,
                createSafeList(elements, true, false));
        return validate(new ImmutableConnectorDescriptor(
                this,
                this.inputDataShape,
                this.outputDataShape,
                newValue,
                this.configuredProperties,
                this.connectorId,
                this.camelConnectorGAV,
                this.camelConnectorPrefix,
                this.componentScheme,
                this.connectorFactory,
                this.connectorCustomizers,
                this.exceptionHandler,
                this.standardizedErrors));
    }

    /**
     * Copy the current immutable object by replacing the {@link io.syndesis.common.model.action.ConnectorDescriptor#getConfiguredProperties() configuredProperties} map with the specified map.
     * Nulls are not permitted as keys or values.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param entries The entries to be added to the configuredProperties map
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnectorDescriptor withConfiguredProperties(
            Map<String, ? extends String> entries) {
        if (this.configuredProperties == entries) {
            return this;
        }
        Map<String, String> newValue =
                createUnmodifiableMap(true, false, entries);
        return validate(new ImmutableConnectorDescriptor(
                this,
                this.inputDataShape,
                this.outputDataShape,
                this.propertyDefinitionSteps,
                newValue,
                this.connectorId,
                this.camelConnectorGAV,
                this.camelConnectorPrefix,
                this.componentScheme,
                this.connectorFactory,
                this.connectorCustomizers,
                this.exceptionHandler,
                this.standardizedErrors));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.action.ConnectorDescriptor#getConnectorId() connectorId} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for connectorId (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConnectorDescriptor withConnectorId(String value) {
        if (Objects.equals(this.connectorId, value)) {
            return this;
        }
        return validate(new ImmutableConnectorDescriptor(
                this,
                this.inputDataShape,
                this.outputDataShape,
                this.propertyDefinitionSteps,
                this.configuredProperties,
                value,
                this.camelConnectorGAV,
                this.camelConnectorPrefix,
                this.componentScheme,
                this.connectorFactory,
                this.connectorCustomizers,
                this.exceptionHandler,
                this.standardizedErrors));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.action.ConnectorDescriptor#getCamelConnectorGAV() camelConnectorGAV} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for camelConnectorGAV (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConnectorDescriptor withCamelConnectorGAV(
            String value) {
        if (Objects.equals(this.camelConnectorGAV, value)) {
            return this;
        }
        return validate(new ImmutableConnectorDescriptor(
                this,
                this.inputDataShape,
                this.outputDataShape,
                this.propertyDefinitionSteps,
                this.configuredProperties,
                this.connectorId,
                value,
                this.camelConnectorPrefix,
                this.componentScheme,
                this.connectorFactory,
                this.connectorCustomizers,
                this.exceptionHandler,
                this.standardizedErrors));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.action.ConnectorDescriptor#getCamelConnectorPrefix() camelConnectorPrefix} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for camelConnectorPrefix (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConnectorDescriptor withCamelConnectorPrefix(
            String value) {
        if (Objects.equals(this.camelConnectorPrefix, value)) {
            return this;
        }
        return validate(new ImmutableConnectorDescriptor(
                this,
                this.inputDataShape,
                this.outputDataShape,
                this.propertyDefinitionSteps,
                this.configuredProperties,
                this.connectorId,
                this.camelConnectorGAV,
                value,
                this.componentScheme,
                this.connectorFactory,
                this.connectorCustomizers,
                this.exceptionHandler,
                this.standardizedErrors));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.action.ConnectorDescriptor#getComponentScheme() componentScheme} attribute.
     *
     * @param value The value for componentScheme
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnectorDescriptor withComponentScheme(
            String value) {
        String newValue = Objects.requireNonNull(value, "componentScheme");
        if (Objects.equals(this.componentScheme, newValue)) {
            return this;
        }
        return validate(new ImmutableConnectorDescriptor(
                this,
                this.inputDataShape,
                this.outputDataShape,
                this.propertyDefinitionSteps,
                this.configuredProperties,
                this.connectorId,
                this.camelConnectorGAV,
                this.camelConnectorPrefix,
                newValue,
                this.connectorFactory,
                this.connectorCustomizers,
                this.exceptionHandler,
                this.standardizedErrors));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.action.ConnectorDescriptor#getComponentScheme() componentScheme} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for componentScheme
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnectorDescriptor withComponentScheme(
            Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.componentScheme, value)) {
            return this;
        }
        return validate(new ImmutableConnectorDescriptor(
                this,
                this.inputDataShape,
                this.outputDataShape,
                this.propertyDefinitionSteps,
                this.configuredProperties,
                this.connectorId,
                this.camelConnectorGAV,
                this.camelConnectorPrefix,
                value,
                this.connectorFactory,
                this.connectorCustomizers,
                this.exceptionHandler,
                this.standardizedErrors));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.action.ConnectorDescriptor#getConnectorFactory() connectorFactory} attribute.
     *
     * @param value The value for connectorFactory
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnectorDescriptor withConnectorFactory(
            String value) {
        String newValue = Objects.requireNonNull(value, "connectorFactory");
        if (Objects.equals(this.connectorFactory, newValue)) {
            return this;
        }
        return validate(new ImmutableConnectorDescriptor(
                this,
                this.inputDataShape,
                this.outputDataShape,
                this.propertyDefinitionSteps,
                this.configuredProperties,
                this.connectorId,
                this.camelConnectorGAV,
                this.camelConnectorPrefix,
                this.componentScheme,
                newValue,
                this.connectorCustomizers,
                this.exceptionHandler,
                this.standardizedErrors));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.action.ConnectorDescriptor#getConnectorFactory() connectorFactory} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for connectorFactory
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnectorDescriptor withConnectorFactory(
            Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.connectorFactory, value)) {
            return this;
        }
        return validate(new ImmutableConnectorDescriptor(
                this,
                this.inputDataShape,
                this.outputDataShape,
                this.propertyDefinitionSteps,
                this.configuredProperties,
                this.connectorId,
                this.camelConnectorGAV,
                this.camelConnectorPrefix,
                this.componentScheme,
                value,
                this.connectorCustomizers,
                this.exceptionHandler,
                this.standardizedErrors));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.action.ConnectorDescriptor#getConnectorCustomizers() connectorCustomizers}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnectorDescriptor withConnectorCustomizers(
            String... elements) {
        List<String> newValue = createUnmodifiableList(false,
                createSafeList(Arrays.asList(elements), true, false));
        return validate(new ImmutableConnectorDescriptor(
                this,
                this.inputDataShape,
                this.outputDataShape,
                this.propertyDefinitionSteps,
                this.configuredProperties,
                this.connectorId,
                this.camelConnectorGAV,
                this.camelConnectorPrefix,
                this.componentScheme,
                this.connectorFactory,
                newValue,
                this.exceptionHandler,
                this.standardizedErrors));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.action.ConnectorDescriptor#getConnectorCustomizers() connectorCustomizers}.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of connectorCustomizers elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnectorDescriptor withConnectorCustomizers(
            Iterable<String> elements) {
        if (this.connectorCustomizers == elements) {
            return this;
        }
        List<String> newValue = createUnmodifiableList(false,
                createSafeList(elements, true, false));
        return validate(new ImmutableConnectorDescriptor(
                this,
                this.inputDataShape,
                this.outputDataShape,
                this.propertyDefinitionSteps,
                this.configuredProperties,
                this.connectorId,
                this.camelConnectorGAV,
                this.camelConnectorPrefix,
                this.componentScheme,
                this.connectorFactory,
                newValue,
                this.exceptionHandler,
                this.standardizedErrors));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.action.ConnectorDescriptor#getExceptionHandler() exceptionHandler} attribute.
     *
     * @param value The value for exceptionHandler
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnectorDescriptor withExceptionHandler(
            String value) {
        String newValue = Objects.requireNonNull(value, "exceptionHandler");
        if (Objects.equals(this.exceptionHandler, newValue)) {
            return this;
        }
        return validate(new ImmutableConnectorDescriptor(
                this,
                this.inputDataShape,
                this.outputDataShape,
                this.propertyDefinitionSteps,
                this.configuredProperties,
                this.connectorId,
                this.camelConnectorGAV,
                this.camelConnectorPrefix,
                this.componentScheme,
                this.connectorFactory,
                this.connectorCustomizers,
                newValue,
                this.standardizedErrors));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.action.ConnectorDescriptor#getExceptionHandler() exceptionHandler} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for exceptionHandler
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnectorDescriptor withExceptionHandler(
            Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.exceptionHandler, value)) {
            return this;
        }
        return validate(new ImmutableConnectorDescriptor(
                this,
                this.inputDataShape,
                this.outputDataShape,
                this.propertyDefinitionSteps,
                this.configuredProperties,
                this.connectorId,
                this.camelConnectorGAV,
                this.camelConnectorPrefix,
                this.componentScheme,
                this.connectorFactory,
                this.connectorCustomizers,
                value,
                this.standardizedErrors));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.action.ConnectorDescriptor#getStandardizedErrors() standardizedErrors}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnectorDescriptor withStandardizedErrors(
            StandardizedError... elements) {
        List<StandardizedError> newValue = createUnmodifiableList(false,
                createSafeList(Arrays.asList(elements), true, false));
        return validate(new ImmutableConnectorDescriptor(
                this,
                this.inputDataShape,
                this.outputDataShape,
                this.propertyDefinitionSteps,
                this.configuredProperties,
                this.connectorId,
                this.camelConnectorGAV,
                this.camelConnectorPrefix,
                this.componentScheme,
                this.connectorFactory,
                this.connectorCustomizers,
                this.exceptionHandler,
                newValue));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.action.ConnectorDescriptor#getStandardizedErrors() standardizedErrors}.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of standardizedErrors elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConnectorDescriptor withStandardizedErrors(
            Iterable<? extends StandardizedError> elements) {
        if (this.standardizedErrors == elements) {
            return this;
        }
        List<StandardizedError> newValue = createUnmodifiableList(false,
                createSafeList(elements, true, false));
        return validate(new ImmutableConnectorDescriptor(
                this,
                this.inputDataShape,
                this.outputDataShape,
                this.propertyDefinitionSteps,
                this.configuredProperties,
                this.connectorId,
                this.camelConnectorGAV,
                this.camelConnectorPrefix,
                this.componentScheme,
                this.connectorFactory,
                this.connectorCustomizers,
                this.exceptionHandler,
                newValue));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableConnectorDescriptor} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableConnectorDescriptor
                && equalTo((ImmutableConnectorDescriptor) another);
    }

    private boolean equalTo(ImmutableConnectorDescriptor another) {
        return Objects.equals(inputDataShape, another.inputDataShape)
                && Objects.equals(outputDataShape, another.outputDataShape)
                && propertyDefinitionSteps.equals(
                another.propertyDefinitionSteps)
                && configuredProperties.equals(another.configuredProperties)
                && Objects.equals(connectorId, another.connectorId)
                && Objects.equals(camelConnectorGAV, another.camelConnectorGAV)
                && Objects.equals(camelConnectorPrefix,
                another.camelConnectorPrefix)
                && Objects.equals(componentScheme, another.componentScheme)
                && Objects.equals(connectorFactory, another.connectorFactory)
                && connectorCustomizers.equals(another.connectorCustomizers)
                && Objects.equals(exceptionHandler, another.exceptionHandler)
                && standardizedErrors.equals(another.standardizedErrors);
    }

    /**
     * Computes a hash code from attributes: {@code inputDataShape}, {@code outputDataShape}, {@code propertyDefinitionSteps}, {@code configuredProperties}, {@code connectorId}, {@code camelConnectorGAV}, {@code camelConnectorPrefix}, {@code componentScheme}, {@code connectorFactory}, {@code connectorCustomizers}, {@code exceptionHandler}, {@code standardizedErrors}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(inputDataShape);
        h += (h << 5) + Objects.hashCode(outputDataShape);
        h += (h << 5) + propertyDefinitionSteps.hashCode();
        h += (h << 5) + configuredProperties.hashCode();
        h += (h << 5) + Objects.hashCode(connectorId);
        h += (h << 5) + Objects.hashCode(camelConnectorGAV);
        h += (h << 5) + Objects.hashCode(camelConnectorPrefix);
        h += (h << 5) + Objects.hashCode(componentScheme);
        h += (h << 5) + Objects.hashCode(connectorFactory);
        h += (h << 5) + connectorCustomizers.hashCode();
        h += (h << 5) + Objects.hashCode(exceptionHandler);
        h += (h << 5) + standardizedErrors.hashCode();
        return h;
    }

    /**
     * Prints the immutable value {@code ConnectorDescriptor} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("ConnectorDescriptor{");
        if (inputDataShape != null) {
            builder.append("inputDataShape=").append(inputDataShape);
        }
        if (outputDataShape != null) {
            if (builder.length() > 20) {
                builder.append(", ");
            }
            builder.append("outputDataShape=").append(outputDataShape);
        }
        if (builder.length() > 20) {
            builder.append(", ");
        }
        builder.append("propertyDefinitionSteps=")
                .append(propertyDefinitionSteps);
        builder.append(", ");
        builder.append("configuredProperties=").append(configuredProperties);
        if (connectorId != null) {
            builder.append(", ");
            builder.append("connectorId=").append(connectorId);
        }
        if (camelConnectorGAV != null) {
            builder.append(", ");
            builder.append("camelConnectorGAV=").append(camelConnectorGAV);
        }
        if (camelConnectorPrefix != null) {
            builder.append(", ");
            builder.append("camelConnectorPrefix=")
                    .append(camelConnectorPrefix);
        }
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
        builder.append(", ");
        builder.append("standardizedErrors=").append(standardizedErrors);
        return builder.append("}").toString();
    }

    private Object readResolve() throws ObjectStreamException {
        return validate(this);
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.action.ConnectorDescriptor ConnectorDescriptor}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private static final long OPT_BIT_PROPERTY_DEFINITION_STEPS = 0x1L;
        private static final long OPT_BIT_CONFIGURED_PROPERTIES = 0x2L;
        private static final long OPT_BIT_CONNECTOR_CUSTOMIZERS = 0x4L;
        private static final long OPT_BIT_STANDARDIZED_ERRORS = 0x8L;
        private long optBits;

        private DataShape inputDataShape;
        private DataShape outputDataShape;
        private List<ActionDescriptorStep> propertyDefinitionSteps =
                new ArrayList<ActionDescriptorStep>();
        private Map<String, String> configuredProperties =
                new LinkedHashMap<String, String>();
        private String connectorId;
        private String camelConnectorGAV;
        private String camelConnectorPrefix;
        private String componentScheme;
        private String connectorFactory;
        private List<String> connectorCustomizers = new ArrayList<String>();
        private String exceptionHandler;
        private List<StandardizedError> standardizedErrors =
                new ArrayList<StandardizedError>();

        /**
         * Creates a builder for {@link io.syndesis.common.model.action.ConnectorDescriptor ConnectorDescriptor} instances.
         * <pre>
         * new ConnectorDescriptor.Builder()
         *    .inputDataShape(io.syndesis.common.io.syndesis.common.model.DataShape) // optional {@link io.syndesis.common.model.action.ConnectorDescriptor#getInputDataShape() inputDataShape}
         *    .outputDataShape(io.syndesis.common.io.syndesis.common.model.DataShape) // optional {@link io.syndesis.common.model.action.ConnectorDescriptor#getOutputDataShape() outputDataShape}
         *    .addPropertyDefinitionStep|addAllPropertyDefinitionSteps(io.syndesis.common.io.syndesis.common.model.action.ActionDescriptor.ActionDescriptorStep) // {@link io.syndesis.common.model.action.ConnectorDescriptor#getPropertyDefinitionSteps() propertyDefinitionSteps} elements
         *    .putConfiguredProperty|putAllConfiguredProperties(String =&gt; String) // {@link io.syndesis.common.model.action.ConnectorDescriptor#getConfiguredProperties() configuredProperties} mappings
         *    .connectorId(String | null) // nullable {@link io.syndesis.common.model.action.ConnectorDescriptor#getConnectorId() connectorId}
         *    .camelConnectorGAV(String | null) // nullable {@link io.syndesis.common.model.action.ConnectorDescriptor#getCamelConnectorGAV() camelConnectorGAV}
         *    .camelConnectorPrefix(String | null) // nullable {@link io.syndesis.common.model.action.ConnectorDescriptor#getCamelConnectorPrefix() camelConnectorPrefix}
         *    .componentScheme(String) // optional {@link io.syndesis.common.model.action.ConnectorDescriptor#getComponentScheme() componentScheme}
         *    .connectorFactory(String) // optional {@link io.syndesis.common.model.action.ConnectorDescriptor#getConnectorFactory() connectorFactory}
         *    .addConnectorCustomizer|addAllConnectorCustomizers(String) // {@link io.syndesis.common.model.action.ConnectorDescriptor#getConnectorCustomizers() connectorCustomizers} elements
         *    .exceptionHandler(String) // optional {@link io.syndesis.common.model.action.ConnectorDescriptor#getExceptionHandler() exceptionHandler}
         *    .addStandardizedError|addAllStandardizedErrors(io.syndesis.common.io.syndesis.common.model.action.ConnectorDescriptor.StandardizedError) // {@link io.syndesis.common.model.action.ConnectorDescriptor#getStandardizedErrors() standardizedErrors} elements
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof ConnectorDescriptor.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new ConnectorDescriptor.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.action.ConnectorDescriptor} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorDescriptor.Builder createFrom(
                ConnectorDescriptor instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (ConnectorDescriptor.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithConfiguredProperties} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorDescriptor.Builder createFrom(
                WithConfiguredProperties instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (ConnectorDescriptor.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.action.ActionDescriptor} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorDescriptor.Builder createFrom(
                ActionDescriptor instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (ConnectorDescriptor.Builder) this;
        }

        private void from(Object object) {
            if (object instanceof ConnectorDescriptor) {
                ConnectorDescriptor instance = (ConnectorDescriptor) object;
                Optional<String> connectorFactoryOptional =
                        instance.getConnectorFactory();
                if (connectorFactoryOptional.isPresent()) {
                    connectorFactory(connectorFactoryOptional);
                }
                String connectorIdValue = instance.getConnectorId();
                if (connectorIdValue != null) {
                    connectorId(connectorIdValue);
                }
                String camelConnectorPrefixValue =
                        instance.getCamelConnectorPrefix();
                if (camelConnectorPrefixValue != null) {
                    camelConnectorPrefix(camelConnectorPrefixValue);
                }
                Optional<String> componentSchemeOptional =
                        instance.getComponentScheme();
                if (componentSchemeOptional.isPresent()) {
                    componentScheme(componentSchemeOptional);
                }
                addAllStandardizedErrors(instance.getStandardizedErrors());
                addAllConnectorCustomizers(instance.getConnectorCustomizers());
                Optional<String> exceptionHandlerOptional =
                        instance.getExceptionHandler();
                if (exceptionHandlerOptional.isPresent()) {
                    exceptionHandler(exceptionHandlerOptional);
                }
                String camelConnectorGAVValue = instance.getCamelConnectorGAV();
                if (camelConnectorGAVValue != null) {
                    camelConnectorGAV(camelConnectorGAVValue);
                }
            }
            if (object instanceof WithConfiguredProperties) {
                WithConfiguredProperties instance =
                        (WithConfiguredProperties) object;
                putAllConfiguredProperties(instance.getConfiguredProperties());
            }
            if (object instanceof ActionDescriptor) {
                ActionDescriptor instance = (ActionDescriptor) object;
                Optional<DataShape> inputDataShapeOptional =
                        instance.getInputDataShape();
                if (inputDataShapeOptional.isPresent()) {
                    inputDataShape(inputDataShapeOptional);
                }
                Optional<DataShape> outputDataShapeOptional =
                        instance.getOutputDataShape();
                if (outputDataShapeOptional.isPresent()) {
                    outputDataShape(outputDataShapeOptional);
                }
                addAllPropertyDefinitionSteps(
                        instance.getPropertyDefinitionSteps());
            }
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.action.ConnectorDescriptor#getInputDataShape() inputDataShape} to inputDataShape.
         *
         * @param inputDataShape The value for inputDataShape
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorDescriptor.Builder inputDataShape(
                DataShape inputDataShape) {
            this.inputDataShape =
                    Objects.requireNonNull(inputDataShape, "inputDataShape");
            return (ConnectorDescriptor.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.action.ConnectorDescriptor#getInputDataShape() inputDataShape} to inputDataShape.
         *
         * @param inputDataShape The value for inputDataShape
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("inputDataShape")
        public final ConnectorDescriptor.Builder inputDataShape(
                Optional<? extends DataShape> inputDataShape) {
            this.inputDataShape = inputDataShape.orElse(null);
            return (ConnectorDescriptor.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.action.ConnectorDescriptor#getOutputDataShape() outputDataShape} to outputDataShape.
         *
         * @param outputDataShape The value for outputDataShape
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorDescriptor.Builder outputDataShape(
                DataShape outputDataShape) {
            this.outputDataShape =
                    Objects.requireNonNull(outputDataShape, "outputDataShape");
            return (ConnectorDescriptor.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.action.ConnectorDescriptor#getOutputDataShape() outputDataShape} to outputDataShape.
         *
         * @param outputDataShape The value for outputDataShape
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("outputDataShape")
        public final ConnectorDescriptor.Builder outputDataShape(
                Optional<? extends DataShape> outputDataShape) {
            this.outputDataShape = outputDataShape.orElse(null);
            return (ConnectorDescriptor.Builder) this;
        }

        /**
         * Adds one element to {@link io.syndesis.common.model.action.ConnectorDescriptor#getPropertyDefinitionSteps() propertyDefinitionSteps} list.
         *
         * @param element A propertyDefinitionSteps element
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorDescriptor.Builder addPropertyDefinitionStep(
                ActionDescriptorStep element) {
            this.propertyDefinitionSteps.add(Objects.requireNonNull(element,
                    "propertyDefinitionSteps element"));
            optBits |= OPT_BIT_PROPERTY_DEFINITION_STEPS;
            return (ConnectorDescriptor.Builder) this;
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.action.ConnectorDescriptor#getPropertyDefinitionSteps() propertyDefinitionSteps} list.
         *
         * @param elements An array of propertyDefinitionSteps elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorDescriptor.Builder addPropertyDefinitionSteps(
                ActionDescriptorStep... elements) {
            for (ActionDescriptorStep element : elements) {
                this.propertyDefinitionSteps.add(Objects.requireNonNull(element,
                        "propertyDefinitionSteps element"));
            }
            optBits |= OPT_BIT_PROPERTY_DEFINITION_STEPS;
            return (ConnectorDescriptor.Builder) this;
        }


        /**
         * Sets or replaces all elements for {@link io.syndesis.common.model.action.ConnectorDescriptor#getPropertyDefinitionSteps() propertyDefinitionSteps} list.
         *
         * @param elements An iterable of propertyDefinitionSteps elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("propertyDefinitionSteps")
        public final ConnectorDescriptor.Builder propertyDefinitionSteps(
                Iterable<? extends ActionDescriptorStep> elements) {
            this.propertyDefinitionSteps.clear();
            return addAllPropertyDefinitionSteps(elements);
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.action.ConnectorDescriptor#getPropertyDefinitionSteps() propertyDefinitionSteps} list.
         *
         * @param elements An iterable of propertyDefinitionSteps elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorDescriptor.Builder addAllPropertyDefinitionSteps(
                Iterable<? extends ActionDescriptorStep> elements) {
            for (ActionDescriptorStep element : elements) {
                this.propertyDefinitionSteps.add(Objects.requireNonNull(element,
                        "propertyDefinitionSteps element"));
            }
            optBits |= OPT_BIT_PROPERTY_DEFINITION_STEPS;
            return (ConnectorDescriptor.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.action.ConnectorDescriptor#getConfiguredProperties() configuredProperties} map.
         *
         * @param key   The key in the configuredProperties map
         * @param value The associated value in the configuredProperties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorDescriptor.Builder putConfiguredProperty(
                String key, String value) {
            this.configuredProperties.put(
                    Objects.requireNonNull(key, "configuredProperties key"),
                    Objects.requireNonNull(value,
                            "configuredProperties value"));
            optBits |= OPT_BIT_CONFIGURED_PROPERTIES;
            return (ConnectorDescriptor.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.action.ConnectorDescriptor#getConfiguredProperties() configuredProperties} map. Nulls are not permitted
         *
         * @param entry The key and value entry
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorDescriptor.Builder putConfiguredProperty(
                Map.Entry<String, ? extends String> entry) {
            String k = entry.getKey();
            String v = entry.getValue();
            this.configuredProperties.put(
                    Objects.requireNonNull(k, "configuredProperties key"),
                    Objects.requireNonNull(v, "configuredProperties value"));
            optBits |= OPT_BIT_CONFIGURED_PROPERTIES;
            return (ConnectorDescriptor.Builder) this;
        }

        /**
         * Sets or replaces all mappings from the specified map as entries for the {@link io.syndesis.common.model.action.ConnectorDescriptor#getConfiguredProperties() configuredProperties} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the configuredProperties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("configuredProperties")
        public final ConnectorDescriptor.Builder configuredProperties(
                Map<String, ? extends String> entries) {
            this.configuredProperties.clear();
            optBits |= OPT_BIT_CONFIGURED_PROPERTIES;
            return putAllConfiguredProperties(entries);
        }

        /**
         * Put all mappings from the specified map as entries to {@link io.syndesis.common.model.action.ConnectorDescriptor#getConfiguredProperties() configuredProperties} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the configuredProperties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorDescriptor.Builder putAllConfiguredProperties(
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
            return (ConnectorDescriptor.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.action.ConnectorDescriptor#getConnectorId() connectorId} attribute.
         *
         * @param connectorId The value for connectorId (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("connectorId")
        public final ConnectorDescriptor.Builder connectorId(
                String connectorId) {
            this.connectorId = connectorId;
            return (ConnectorDescriptor.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.action.ConnectorDescriptor#getCamelConnectorGAV() camelConnectorGAV} attribute.
         *
         * @param camelConnectorGAV The value for camelConnectorGAV (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("camelConnectorGAV")
        public final ConnectorDescriptor.Builder camelConnectorGAV(
                String camelConnectorGAV) {
            this.camelConnectorGAV = camelConnectorGAV;
            return (ConnectorDescriptor.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.action.ConnectorDescriptor#getCamelConnectorPrefix() camelConnectorPrefix} attribute.
         *
         * @param camelConnectorPrefix The value for camelConnectorPrefix (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("camelConnectorPrefix")
        public final ConnectorDescriptor.Builder camelConnectorPrefix(
                String camelConnectorPrefix) {
            this.camelConnectorPrefix = camelConnectorPrefix;
            return (ConnectorDescriptor.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.action.ConnectorDescriptor#getComponentScheme() componentScheme} to componentScheme.
         *
         * @param componentScheme The value for componentScheme
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorDescriptor.Builder componentScheme(
                String componentScheme) {
            this.componentScheme =
                    Objects.requireNonNull(componentScheme, "componentScheme");
            return (ConnectorDescriptor.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.action.ConnectorDescriptor#getComponentScheme() componentScheme} to componentScheme.
         *
         * @param componentScheme The value for componentScheme
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("componentScheme")
        public final ConnectorDescriptor.Builder componentScheme(
                Optional<String> componentScheme) {
            this.componentScheme = componentScheme.orElse(null);
            return (ConnectorDescriptor.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.action.ConnectorDescriptor#getConnectorFactory() connectorFactory} to connectorFactory.
         *
         * @param connectorFactory The value for connectorFactory
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorDescriptor.Builder connectorFactory(
                String connectorFactory) {
            this.connectorFactory = Objects.requireNonNull(connectorFactory,
                    "connectorFactory");
            return (ConnectorDescriptor.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.action.ConnectorDescriptor#getConnectorFactory() connectorFactory} to connectorFactory.
         *
         * @param connectorFactory The value for connectorFactory
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("connectorFactory")
        public final ConnectorDescriptor.Builder connectorFactory(
                Optional<String> connectorFactory) {
            this.connectorFactory = connectorFactory.orElse(null);
            return (ConnectorDescriptor.Builder) this;
        }

        /**
         * Adds one element to {@link io.syndesis.common.model.action.ConnectorDescriptor#getConnectorCustomizers() connectorCustomizers} list.
         *
         * @param element A connectorCustomizers element
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorDescriptor.Builder addConnectorCustomizer(
                String element) {
            this.connectorCustomizers.add(Objects.requireNonNull(element,
                    "connectorCustomizers element"));
            optBits |= OPT_BIT_CONNECTOR_CUSTOMIZERS;
            return (ConnectorDescriptor.Builder) this;
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.action.ConnectorDescriptor#getConnectorCustomizers() connectorCustomizers} list.
         *
         * @param elements An array of connectorCustomizers elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorDescriptor.Builder addConnectorCustomizers(
                String... elements) {
            for (String element : elements) {
                this.connectorCustomizers.add(Objects.requireNonNull(element,
                        "connectorCustomizers element"));
            }
            optBits |= OPT_BIT_CONNECTOR_CUSTOMIZERS;
            return (ConnectorDescriptor.Builder) this;
        }


        /**
         * Sets or replaces all elements for {@link io.syndesis.common.model.action.ConnectorDescriptor#getConnectorCustomizers() connectorCustomizers} list.
         *
         * @param elements An iterable of connectorCustomizers elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("connectorCustomizers")
        public final ConnectorDescriptor.Builder connectorCustomizers(
                Iterable<String> elements) {
            this.connectorCustomizers.clear();
            return addAllConnectorCustomizers(elements);
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.action.ConnectorDescriptor#getConnectorCustomizers() connectorCustomizers} list.
         *
         * @param elements An iterable of connectorCustomizers elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorDescriptor.Builder addAllConnectorCustomizers(
                Iterable<String> elements) {
            for (String element : elements) {
                this.connectorCustomizers.add(Objects.requireNonNull(element,
                        "connectorCustomizers element"));
            }
            optBits |= OPT_BIT_CONNECTOR_CUSTOMIZERS;
            return (ConnectorDescriptor.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.action.ConnectorDescriptor#getExceptionHandler() exceptionHandler} to exceptionHandler.
         *
         * @param exceptionHandler The value for exceptionHandler
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorDescriptor.Builder exceptionHandler(
                String exceptionHandler) {
            this.exceptionHandler = Objects.requireNonNull(exceptionHandler,
                    "exceptionHandler");
            return (ConnectorDescriptor.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.action.ConnectorDescriptor#getExceptionHandler() exceptionHandler} to exceptionHandler.
         *
         * @param exceptionHandler The value for exceptionHandler
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("exceptionHandler")
        public final ConnectorDescriptor.Builder exceptionHandler(
                Optional<String> exceptionHandler) {
            this.exceptionHandler = exceptionHandler.orElse(null);
            return (ConnectorDescriptor.Builder) this;
        }

        /**
         * Adds one element to {@link io.syndesis.common.model.action.ConnectorDescriptor#getStandardizedErrors() standardizedErrors} list.
         *
         * @param element A standardizedErrors element
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorDescriptor.Builder addStandardizedError(
                StandardizedError element) {
            this.standardizedErrors.add(Objects.requireNonNull(element,
                    "standardizedErrors element"));
            optBits |= OPT_BIT_STANDARDIZED_ERRORS;
            return (ConnectorDescriptor.Builder) this;
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.action.ConnectorDescriptor#getStandardizedErrors() standardizedErrors} list.
         *
         * @param elements An array of standardizedErrors elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorDescriptor.Builder addStandardizedErrors(
                StandardizedError... elements) {
            for (StandardizedError element : elements) {
                this.standardizedErrors.add(Objects.requireNonNull(element,
                        "standardizedErrors element"));
            }
            optBits |= OPT_BIT_STANDARDIZED_ERRORS;
            return (ConnectorDescriptor.Builder) this;
        }


        /**
         * Sets or replaces all elements for {@link io.syndesis.common.model.action.ConnectorDescriptor#getStandardizedErrors() standardizedErrors} list.
         *
         * @param elements An iterable of standardizedErrors elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("standardizedErrors")
        public final ConnectorDescriptor.Builder standardizedErrors(
                Iterable<? extends StandardizedError> elements) {
            this.standardizedErrors.clear();
            return addAllStandardizedErrors(elements);
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.action.ConnectorDescriptor#getStandardizedErrors() standardizedErrors} list.
         *
         * @param elements An iterable of standardizedErrors elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConnectorDescriptor.Builder addAllStandardizedErrors(
                Iterable<? extends StandardizedError> elements) {
            for (StandardizedError element : elements) {
                this.standardizedErrors.add(Objects.requireNonNull(element,
                        "standardizedErrors element"));
            }
            optBits |= OPT_BIT_STANDARDIZED_ERRORS;
            return (ConnectorDescriptor.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.action.ConnectorDescriptor ConnectorDescriptor}.
         *
         * @return An immutable instance of ConnectorDescriptor
         * @throws IllegalStateException if any required attributes are missing
         */
        public ConnectorDescriptor build() {
            return ImmutableConnectorDescriptor.validate(
                    new ImmutableConnectorDescriptor(this));
        }

        private boolean propertyDefinitionStepsIsSet() {
            return (optBits & OPT_BIT_PROPERTY_DEFINITION_STEPS) != 0;
        }

        private boolean configuredPropertiesIsSet() {
            return (optBits & OPT_BIT_CONFIGURED_PROPERTIES) != 0;
        }

        private boolean connectorCustomizersIsSet() {
            return (optBits & OPT_BIT_CONNECTOR_CUSTOMIZERS) != 0;
        }

        private boolean standardizedErrorsIsSet() {
            return (optBits & OPT_BIT_STANDARDIZED_ERRORS) != 0;
        }
    }

    private final class InitShim {
        private byte propertyDefinitionStepsBuildStage = STAGE_UNINITIALIZED;
        private List<ActionDescriptorStep> propertyDefinitionSteps;
        private byte configuredPropertiesBuildStage = STAGE_UNINITIALIZED;
        private Map<String, String> configuredProperties;
        private byte connectorCustomizersBuildStage = STAGE_UNINITIALIZED;
        private List<String> connectorCustomizers;
        private byte standardizedErrorsBuildStage = STAGE_UNINITIALIZED;
        private List<StandardizedError> standardizedErrors;

        List<ActionDescriptorStep> getPropertyDefinitionSteps() {
            if (propertyDefinitionStepsBuildStage == STAGE_INITIALIZING) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (propertyDefinitionStepsBuildStage == STAGE_UNINITIALIZED) {
                propertyDefinitionStepsBuildStage = STAGE_INITIALIZING;
                this.propertyDefinitionSteps = createUnmodifiableList(false,
                        createSafeList(getPropertyDefinitionStepsInitialize(),
                                true, false));
                propertyDefinitionStepsBuildStage = STAGE_INITIALIZED;
            }
            return this.propertyDefinitionSteps;
        }

        void propertyDefinitionSteps(
                List<ActionDescriptorStep> propertyDefinitionSteps) {
            this.propertyDefinitionSteps = propertyDefinitionSteps;
            propertyDefinitionStepsBuildStage = STAGE_INITIALIZED;
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

        List<StandardizedError> getStandardizedErrors() {
            if (standardizedErrorsBuildStage == STAGE_INITIALIZING) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (standardizedErrorsBuildStage == STAGE_UNINITIALIZED) {
                standardizedErrorsBuildStage = STAGE_INITIALIZING;
                this.standardizedErrors = createUnmodifiableList(false,
                        createSafeList(getStandardizedErrorsInitialize(), true,
                                false));
                standardizedErrorsBuildStage = STAGE_INITIALIZED;
            }
            return this.standardizedErrors;
        }

        void standardizedErrors(List<StandardizedError> standardizedErrors) {
            this.standardizedErrors = standardizedErrors;
            standardizedErrorsBuildStage = STAGE_INITIALIZED;
        }

        private String formatInitCycleMessage() {
            List<String> attributes = new ArrayList<>();
            if (propertyDefinitionStepsBuildStage == STAGE_INITIALIZING) {
                attributes.add("propertyDefinitionSteps");
            }
            if (configuredPropertiesBuildStage == STAGE_INITIALIZING) {
                attributes.add("configuredProperties");
            }
            if (connectorCustomizersBuildStage == STAGE_INITIALIZING) {
                attributes.add("connectorCustomizers");
            }
            if (standardizedErrorsBuildStage == STAGE_INITIALIZING) {
                attributes.add("standardizedErrors");
            }
            return "Cannot build ConnectorDescriptor, attribute initializers form cycle "
                    + attributes;
        }
    }
}
