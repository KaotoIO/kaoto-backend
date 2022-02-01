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
package io.syndesis.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Var;

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
 * Immutable implementation of {@link io.syndesis.common.model.DataShape}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new DataShape.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableDataShape.of()}.
 */
@SuppressWarnings({"ArrayEquals", "ArrayHashCode", "ArrayToString", "all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableDataShape implements DataShape {
    private final String name;
    private final String description;
    private final DataShapeKinds kind;
    private final String type;
    private final String collectionType;
    private final String collectionClassName;
    private final String specification;
    private final byte[] exemplar;
    private final Map<String, String> metadata;
    private final Map<String, String> parameters;
    private final List<DataShape> variants;

    @SuppressWarnings("unchecked") // safe covariant cast
    private ImmutableDataShape(
            String name,
            String description,
            DataShapeKinds kind,
            String type,
            Optional<String> collectionType,
            Optional<String> collectionClassName,
            String specification,
            Optional<? extends byte[]> exemplar,
            Map<String, ? extends String> metadata,
            Map<String, ? extends String> parameters,
            Iterable<? extends DataShape> variants) {
        this.name = name;
        this.description = description;
        this.kind = kind;
        this.type = type;
        this.collectionType = collectionType.orElse(null);
        this.collectionClassName = collectionClassName.orElse(null);
        this.specification = specification;
        this.exemplar = exemplar.orElse(null);
        this.metadata = createUnmodifiableMap(true, false, metadata);
        this.parameters = createUnmodifiableMap(true, false, parameters);
        this.variants = createUnmodifiableList(false,
                createSafeList(variants, true, false));
    }

    private ImmutableDataShape(Builder builder) {
        this.name = builder.name;
        this.description = builder.description;
        this.kind = builder.kind;
        this.type = builder.type;
        this.collectionType = builder.collectionType;
        this.collectionClassName = builder.collectionClassName;
        this.specification = builder.specification;
        this.exemplar = builder.exemplar;
        this.metadata = createUnmodifiableMap(false, false, builder.metadata);
        this.parameters =
                createUnmodifiableMap(false, false, builder.parameters);
        this.variants = builder.variantsIsSet()
                ? createUnmodifiableList(true, builder.variants)
                : createUnmodifiableList(false,
                createSafeList(DataShape.super.getVariants(), true, false));
    }

    private ImmutableDataShape(
            ImmutableDataShape original,
            String name,
            String description,
            DataShapeKinds kind,
            String type,
            String collectionType,
            String collectionClassName,
            String specification,
            byte[] exemplar,
            Map<String, String> metadata,
            Map<String, String> parameters,
            List<DataShape> variants) {
        this.name = name;
        this.description = description;
        this.kind = kind;
        this.type = type;
        this.collectionType = collectionType;
        this.collectionClassName = collectionClassName;
        this.specification = specification;
        this.exemplar = exemplar;
        this.metadata = metadata;
        this.parameters = parameters;
        this.variants = variants;
    }

    /**
     * Construct a new immutable {@code DataShape} instance.
     *
     * @param name                The value for the {@code name} attribute
     * @param description         The value for the {@code description} attribute
     * @param kind                The value for the {@code kind} attribute
     * @param type                The value for the {@code type} attribute
     * @param collectionType      The value for the {@code collectionType} attribute
     * @param collectionClassName The value for the {@code collectionClassName} attribute
     * @param specification       The value for the {@code specification} attribute
     * @param exemplar            The value for the {@code exemplar} attribute
     * @param metadata            The value for the {@code metadata} attribute
     * @param parameters          The value for the {@code parameters} attribute
     * @param variants            The value for the {@code variants} attribute
     * @return An immutable DataShape instance
     */
    public static DataShape of(String name, String description,
                               DataShapeKinds kind, String type,
                               Optional<String> collectionType,
                               Optional<String> collectionClassName,
                               String specification, Optional<byte[]> exemplar,
                               Map<String, String> metadata,
                               Map<String, String> parameters,
                               List<DataShape> variants) {
        return of(name, description, kind, type, collectionType,
                collectionClassName, specification, exemplar, metadata,
                parameters, (Iterable<? extends DataShape>) variants);
    }

    /**
     * Construct a new immutable {@code DataShape} instance.
     *
     * @param name                The value for the {@code name} attribute
     * @param description         The value for the {@code description} attribute
     * @param kind                The value for the {@code kind} attribute
     * @param type                The value for the {@code type} attribute
     * @param collectionType      The value for the {@code collectionType} attribute
     * @param collectionClassName The value for the {@code collectionClassName} attribute
     * @param specification       The value for the {@code specification} attribute
     * @param exemplar            The value for the {@code exemplar} attribute
     * @param metadata            The value for the {@code metadata} attribute
     * @param parameters          The value for the {@code parameters} attribute
     * @param variants            The value for the {@code variants} attribute
     * @return An immutable DataShape instance
     */
    public static DataShape of(String name, String description,
                               DataShapeKinds kind, String type,
                               Optional<String> collectionType,
                               Optional<String> collectionClassName,
                               String specification,
                               Optional<? extends byte[]> exemplar,
                               Map<String, ? extends String> metadata,
                               Map<String, ? extends String> parameters,
                               Iterable<? extends DataShape> variants) {
        return validate(new ImmutableDataShape(name, description, kind, type,
                collectionType, collectionClassName, specification, exemplar,
                metadata, parameters, variants));
    }

    private static ImmutableDataShape validate(ImmutableDataShape instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.DataShape} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable DataShape instance
     */
    public static DataShape copyOf(DataShape instance) {
        if (instance instanceof ImmutableDataShape) {
            return (ImmutableDataShape) instance;
        }
        return new DataShape.Builder()
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

    /**
     * @return The value of the {@code name} attribute
     */
    @JsonProperty("name")
    @Override
    public String getName() {
        return name;
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
     * @return The value of the {@code kind} attribute
     */
    @JsonProperty("kind")
    @Override
    public DataShapeKinds getKind() {
        return kind;
    }

    /**
     * @return The value of the {@code type} attribute
     */
    @JsonProperty("type")
    @Override
    public String getType() {
        return type;
    }

    /**
     * @return The value of the {@code collectionType} attribute
     */
    @JsonProperty("collectionType")
    @Override
    public Optional<String> getCollectionType() {
        return Optional.ofNullable(collectionType);
    }

    /**
     * @return The value of the {@code collectionClassName} attribute
     */
    @JsonProperty("collectionClassName")
    @Override
    public Optional<String> getCollectionClassName() {
        return Optional.ofNullable(collectionClassName);
    }

    /**
     * @return The value of the {@code specification} attribute
     */
    @JsonProperty("specification")
    @Override
    public String getSpecification() {
        return specification;
    }

    /**
     * @return The value of the {@code exemplar} attribute
     */
    @JsonProperty("exemplar")
    @Override
    public Optional<byte[]> getExemplar() {
        return Optional.ofNullable(exemplar);
    }

    /**
     * @return The value of the {@code metadata} attribute
     */
    @JsonProperty("metadata")
    @Override
    public Map<String, String> getMetadata() {
        return metadata;
    }

    /**
     * @return The value of the {@code parameters} attribute
     */
    @JsonProperty("parameters")
    @Override
    public Map<String, String> getParameters() {
        return parameters;
    }

    /**
     * @return The value of the {@code variants} attribute
     */
    @JsonProperty("variants")
    @Override
    public List<DataShape> getVariants() {
        return variants;
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.DataShape#getName() name} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for name (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableDataShape withName(String value) {
        if (Objects.equals(this.name, value)) {
            return this;
        }
        return validate(new ImmutableDataShape(
                this,
                value,
                this.description,
                this.kind,
                this.type,
                this.collectionType,
                this.collectionClassName,
                this.specification,
                this.exemplar,
                this.metadata,
                this.parameters,
                this.variants));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.DataShape#getDescription() description} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for description (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableDataShape withDescription(String value) {
        if (Objects.equals(this.description, value)) {
            return this;
        }
        return validate(new ImmutableDataShape(
                this,
                this.name,
                value,
                this.kind,
                this.type,
                this.collectionType,
                this.collectionClassName,
                this.specification,
                this.exemplar,
                this.metadata,
                this.parameters,
                this.variants));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.DataShape#getKind() kind} attribute.
     * A value equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for kind (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableDataShape withKind(DataShapeKinds value) {
        if (this.kind == value) {
            return this;
        }
        if (Objects.equals(this.kind, value)) {
            return this;
        }
        return validate(new ImmutableDataShape(
                this,
                this.name,
                this.description,
                value,
                this.type,
                this.collectionType,
                this.collectionClassName,
                this.specification,
                this.exemplar,
                this.metadata,
                this.parameters,
                this.variants));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.DataShape#getType() type} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for type (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableDataShape withType(String value) {
        if (Objects.equals(this.type, value)) {
            return this;
        }
        return validate(new ImmutableDataShape(
                this,
                this.name,
                this.description,
                this.kind,
                value,
                this.collectionType,
                this.collectionClassName,
                this.specification,
                this.exemplar,
                this.metadata,
                this.parameters,
                this.variants));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.DataShape#getCollectionType() collectionType} attribute.
     *
     * @param value The value for collectionType
     * @return A modified copy of {@code this} object
     */
    public final ImmutableDataShape withCollectionType(String value) {
        String newValue = Objects.requireNonNull(value, "collectionType");
        if (Objects.equals(this.collectionType, newValue)) {
            return this;
        }
        return validate(new ImmutableDataShape(
                this,
                this.name,
                this.description,
                this.kind,
                this.type,
                newValue,
                this.collectionClassName,
                this.specification,
                this.exemplar,
                this.metadata,
                this.parameters,
                this.variants));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.DataShape#getCollectionType() collectionType} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for collectionType
     * @return A modified copy of {@code this} object
     */
    public final ImmutableDataShape withCollectionType(
            Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.collectionType, value)) {
            return this;
        }
        return validate(new ImmutableDataShape(
                this,
                this.name,
                this.description,
                this.kind,
                this.type,
                value,
                this.collectionClassName,
                this.specification,
                this.exemplar,
                this.metadata,
                this.parameters,
                this.variants));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.DataShape#getCollectionClassName() collectionClassName} attribute.
     *
     * @param value The value for collectionClassName
     * @return A modified copy of {@code this} object
     */
    public final ImmutableDataShape withCollectionClassName(String value) {
        String newValue = Objects.requireNonNull(value, "collectionClassName");
        if (Objects.equals(this.collectionClassName, newValue)) {
            return this;
        }
        return validate(new ImmutableDataShape(
                this,
                this.name,
                this.description,
                this.kind,
                this.type,
                this.collectionType,
                newValue,
                this.specification,
                this.exemplar,
                this.metadata,
                this.parameters,
                this.variants));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.DataShape#getCollectionClassName() collectionClassName} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for collectionClassName
     * @return A modified copy of {@code this} object
     */
    public final ImmutableDataShape withCollectionClassName(
            Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.collectionClassName, value)) {
            return this;
        }
        return validate(new ImmutableDataShape(
                this,
                this.name,
                this.description,
                this.kind,
                this.type,
                this.collectionType,
                value,
                this.specification,
                this.exemplar,
                this.metadata,
                this.parameters,
                this.variants));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.DataShape#getSpecification() specification} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for specification (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableDataShape withSpecification(String value) {
        if (Objects.equals(this.specification, value)) {
            return this;
        }
        return validate(new ImmutableDataShape(
                this,
                this.name,
                this.description,
                this.kind,
                this.type,
                this.collectionType,
                this.collectionClassName,
                value,
                this.exemplar,
                this.metadata,
                this.parameters,
                this.variants));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.DataShape#getExemplar() exemplar} attribute.
     *
     * @param value The value for exemplar
     * @return A modified copy of {@code this} object
     */
    public final ImmutableDataShape withExemplar(byte[] value) {
        byte[] newValue = Objects.requireNonNull(value, "exemplar");
        if (this.exemplar == newValue) {
            return this;
        }
        return validate(new ImmutableDataShape(
                this,
                this.name,
                this.description,
                this.kind,
                this.type,
                this.collectionType,
                this.collectionClassName,
                this.specification,
                newValue,
                this.metadata,
                this.parameters,
                this.variants));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.DataShape#getExemplar() exemplar} attribute.
     * A shallow reference equality check is used on unboxed optional value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for exemplar
     * @return A modified copy of {@code this} object
     */
    @SuppressWarnings("unchecked") // safe covariant cast
    public final ImmutableDataShape withExemplar(
            Optional<? extends byte[]> optional) {
        byte[] value = optional.orElse(null);
        if (this.exemplar == value) {
            return this;
        }
        return validate(new ImmutableDataShape(
                this,
                this.name,
                this.description,
                this.kind,
                this.type,
                this.collectionType,
                this.collectionClassName,
                this.specification,
                value,
                this.metadata,
                this.parameters,
                this.variants));
    }

    /**
     * Copy the current immutable object by replacing the {@link io.syndesis.common.model.DataShape#getMetadata() metadata} map with the specified map.
     * Nulls are not permitted as keys or values.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param entries The entries to be added to the metadata map
     * @return A modified copy of {@code this} object
     */
    public final ImmutableDataShape withMetadata(
            Map<String, ? extends String> entries) {
        if (this.metadata == entries) {
            return this;
        }
        Map<String, String> newValue =
                createUnmodifiableMap(true, false, entries);
        return validate(new ImmutableDataShape(
                this,
                this.name,
                this.description,
                this.kind,
                this.type,
                this.collectionType,
                this.collectionClassName,
                this.specification,
                this.exemplar,
                newValue,
                this.parameters,
                this.variants));
    }

    /**
     * Copy the current immutable object by replacing the {@link io.syndesis.common.model.DataShape#getParameters() parameters} map with the specified map.
     * Nulls are not permitted as keys or values.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param entries The entries to be added to the parameters map
     * @return A modified copy of {@code this} object
     */
    public final ImmutableDataShape withParameters(
            Map<String, ? extends String> entries) {
        if (this.parameters == entries) {
            return this;
        }
        Map<String, String> newValue =
                createUnmodifiableMap(true, false, entries);
        return validate(new ImmutableDataShape(
                this,
                this.name,
                this.description,
                this.kind,
                this.type,
                this.collectionType,
                this.collectionClassName,
                this.specification,
                this.exemplar,
                this.metadata,
                newValue,
                this.variants));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.DataShape#getVariants() variants}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableDataShape withVariants(DataShape... elements) {
        List<DataShape> newValue = createUnmodifiableList(false,
                createSafeList(Arrays.asList(elements), true, false));
        return validate(new ImmutableDataShape(
                this,
                this.name,
                this.description,
                this.kind,
                this.type,
                this.collectionType,
                this.collectionClassName,
                this.specification,
                this.exemplar,
                this.metadata,
                this.parameters,
                newValue));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.DataShape#getVariants() variants}.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of variants elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableDataShape withVariants(
            Iterable<? extends DataShape> elements) {
        if (this.variants == elements) {
            return this;
        }
        List<DataShape> newValue = createUnmodifiableList(false,
                createSafeList(elements, true, false));
        return validate(new ImmutableDataShape(
                this,
                this.name,
                this.description,
                this.kind,
                this.type,
                this.collectionType,
                this.collectionClassName,
                this.specification,
                this.exemplar,
                this.metadata,
                this.parameters,
                newValue));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableDataShape} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableDataShape
                && equalTo((ImmutableDataShape) another);
    }

    private boolean equalTo(ImmutableDataShape another) {
        return Objects.equals(name, another.name)
                && Objects.equals(description, another.description)
                && Objects.equals(kind, another.kind)
                && Objects.equals(type, another.type)
                && Objects.equals(collectionType, another.collectionType)
                && Objects.equals(collectionClassName,
                another.collectionClassName)
                && Objects.equals(specification, another.specification)
                && Objects.equals(exemplar, another.exemplar)
                && metadata.equals(another.metadata)
                && parameters.equals(another.parameters)
                && variants.equals(another.variants);
    }

    /**
     * Computes a hash code from attributes: {@code name}, {@code description}, {@code kind}, {@code type}, {@code collectionType}, {@code collectionClassName}, {@code specification}, {@code exemplar}, {@code metadata}, {@code parameters}, {@code variants}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(name);
        h += (h << 5) + Objects.hashCode(description);
        h += (h << 5) + Objects.hashCode(kind);
        h += (h << 5) + Objects.hashCode(type);
        h += (h << 5) + Objects.hashCode(collectionType);
        h += (h << 5) + Objects.hashCode(collectionClassName);
        h += (h << 5) + Objects.hashCode(specification);
        h += (h << 5) + Objects.hashCode(exemplar);
        h += (h << 5) + metadata.hashCode();
        h += (h << 5) + parameters.hashCode();
        h += (h << 5) + variants.hashCode();
        return h;
    }

    /**
     * Prints the immutable value {@code DataShape} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("DataShape{");
        if (name != null) {
            builder.append("name=").append(name);
        }
        if (description != null) {
            if (builder.length() > 10) {
                builder.append(", ");
            }
            builder.append("description=").append(description);
        }
        if (kind != null) {
            if (builder.length() > 10) {
                builder.append(", ");
            }
            builder.append("kind=").append(kind);
        }
        if (type != null) {
            if (builder.length() > 10) {
                builder.append(", ");
            }
            builder.append("type=").append(type);
        }
        if (collectionType != null) {
            if (builder.length() > 10) {
                builder.append(", ");
            }
            builder.append("collectionType=").append(collectionType);
        }
        if (collectionClassName != null) {
            if (builder.length() > 10) {
                builder.append(", ");
            }
            builder.append("collectionClassName=").append(collectionClassName);
        }
        if (specification != null) {
            if (builder.length() > 10) {
                builder.append(", ");
            }
            builder.append("specification=").append(specification);
        }
        if (exemplar != null) {
            if (builder.length() > 10) {
                builder.append(", ");
            }
            builder.append("exemplar=").append(exemplar);
        }
        if (builder.length() > 10) {
            builder.append(", ");
        }
        builder.append("metadata=").append(metadata);
        builder.append(", ");
        builder.append("parameters=").append(parameters);
        builder.append(", ");
        builder.append("variants=").append(variants);
        return builder.append("}").toString();
    }

    private Object readResolve() throws ObjectStreamException {
        return validate(this);
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.DataShape DataShape}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private static final long OPT_BIT_VARIANTS = 0x1L;
        private long optBits;

        private String name;
        private String description;
        private DataShapeKinds kind;
        private String type;
        private String collectionType;
        private String collectionClassName;
        private String specification;
        private byte[] exemplar;
        private Map<String, String> metadata =
                new LinkedHashMap<String, String>();
        private Map<String, String> parameters =
                new LinkedHashMap<String, String>();
        private List<DataShape> variants = new ArrayList<DataShape>();

        /**
         * Creates a builder for {@link io.syndesis.common.model.DataShape DataShape} instances.
         * <pre>
         * new DataShape.Builder()
         *    .name(String | null) // nullable {@link io.syndesis.common.model.DataShape#getName() name}
         *    .description(String | null) // nullable {@link io.syndesis.common.model.DataShape#getDescription() description}
         *    .kind(io.syndesis.common.io.syndesis.common.model.DataShapeKinds | null) // nullable {@link io.syndesis.common.model.DataShape#getKind() kind}
         *    .type(String | null) // nullable {@link io.syndesis.common.model.DataShape#getType() type}
         *    .collectionType(String) // optional {@link io.syndesis.common.model.DataShape#getCollectionType() collectionType}
         *    .collectionClassName(String) // optional {@link io.syndesis.common.model.DataShape#getCollectionClassName() collectionClassName}
         *    .specification(String | null) // nullable {@link io.syndesis.common.model.DataShape#getSpecification() specification}
         *    .exemplar(byte[]) // optional {@link io.syndesis.common.model.DataShape#getExemplar() exemplar}
         *    .putMetadata|putAllMetadata(String =&gt; String) // {@link io.syndesis.common.model.DataShape#getMetadata() metadata} mappings
         *    .putParameter|putAllParameters(String =&gt; String) // {@link io.syndesis.common.model.DataShape#getParameters() parameters} mappings
         *    .addVariant|addAllVariants(io.syndesis.common.io.syndesis.common.model.DataShape) // {@link io.syndesis.common.model.DataShape#getVariants() variants} elements
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof DataShape.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new DataShape.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithName} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final DataShape.Builder createFrom(WithName instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (DataShape.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.DataShape} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final DataShape.Builder createFrom(DataShape instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (DataShape.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithMetadata} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final DataShape.Builder createFrom(WithMetadata instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (DataShape.Builder) this;
        }

        private void from(Object object) {
            @Var long bits = 0;
            if (object instanceof WithName) {
                WithName instance = (WithName) object;
                if ((bits & 0x2L) == 0) {
                    String nameValue = instance.getName();
                    if (nameValue != null) {
                        name(nameValue);
                    }
                    bits |= 0x2L;
                }
            }
            if (object instanceof DataShape) {
                DataShape instance = (DataShape) object;
                Optional<String> collectionTypeOptional =
                        instance.getCollectionType();
                if (collectionTypeOptional.isPresent()) {
                    collectionType(collectionTypeOptional);
                }
                if ((bits & 0x1L) == 0) {
                    putAllMetadata(instance.getMetadata());
                    bits |= 0x1L;
                }
                DataShapeKinds kindValue = instance.getKind();
                if (kindValue != null) {
                    kind(kindValue);
                }
                if ((bits & 0x2L) == 0) {
                    String nameValue = instance.getName();
                    if (nameValue != null) {
                        name(nameValue);
                    }
                    bits |= 0x2L;
                }
                Optional<String> collectionClassNameOptional =
                        instance.getCollectionClassName();
                if (collectionClassNameOptional.isPresent()) {
                    collectionClassName(collectionClassNameOptional);
                }
                Optional<byte[]> exemplarOptional = instance.getExemplar();
                if (exemplarOptional.isPresent()) {
                    exemplar(exemplarOptional);
                }
                String descriptionValue = instance.getDescription();
                if (descriptionValue != null) {
                    description(descriptionValue);
                }
                String specificationValue = instance.getSpecification();
                if (specificationValue != null) {
                    specification(specificationValue);
                }
                addAllVariants(instance.getVariants());
                String typeValue = instance.getType();
                if (typeValue != null) {
                    type(typeValue);
                }
                putAllParameters(instance.getParameters());
            }
            if (object instanceof WithMetadata) {
                WithMetadata instance = (WithMetadata) object;
                if ((bits & 0x1L) == 0) {
                    putAllMetadata(instance.getMetadata());
                    bits |= 0x1L;
                }
            }
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.DataShape#getName() name} attribute.
         *
         * @param name The value for name (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("name")
        public final DataShape.Builder name(String name) {
            this.name = name;
            return (DataShape.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.DataShape#getDescription() description} attribute.
         *
         * @param description The value for description (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("description")
        public final DataShape.Builder description(String description) {
            this.description = description;
            return (DataShape.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.DataShape#getKind() kind} attribute.
         *
         * @param kind The value for kind (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("kind")
        public final DataShape.Builder kind(DataShapeKinds kind) {
            this.kind = kind;
            return (DataShape.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.DataShape#getType() type} attribute.
         *
         * @param type The value for type (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("type")
        public final DataShape.Builder type(String type) {
            this.type = type;
            return (DataShape.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.DataShape#getCollectionType() collectionType} to collectionType.
         *
         * @param collectionType The value for collectionType
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final DataShape.Builder collectionType(String collectionType) {
            this.collectionType =
                    Objects.requireNonNull(collectionType, "collectionType");
            return (DataShape.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.DataShape#getCollectionType() collectionType} to collectionType.
         *
         * @param collectionType The value for collectionType
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("collectionType")
        public final DataShape.Builder collectionType(
                Optional<String> collectionType) {
            this.collectionType = collectionType.orElse(null);
            return (DataShape.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.DataShape#getCollectionClassName() collectionClassName} to collectionClassName.
         *
         * @param collectionClassName The value for collectionClassName
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final DataShape.Builder collectionClassName(
                String collectionClassName) {
            this.collectionClassName =
                    Objects.requireNonNull(collectionClassName,
                            "collectionClassName");
            return (DataShape.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.DataShape#getCollectionClassName() collectionClassName} to collectionClassName.
         *
         * @param collectionClassName The value for collectionClassName
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("collectionClassName")
        public final DataShape.Builder collectionClassName(
                Optional<String> collectionClassName) {
            this.collectionClassName = collectionClassName.orElse(null);
            return (DataShape.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.DataShape#getSpecification() specification} attribute.
         *
         * @param specification The value for specification (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("specification")
        public final DataShape.Builder specification(String specification) {
            this.specification = specification;
            return (DataShape.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.DataShape#getExemplar() exemplar} to exemplar.
         *
         * @param exemplar The value for exemplar
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final DataShape.Builder exemplar(byte[] exemplar) {
            this.exemplar = Objects.requireNonNull(exemplar, "exemplar");
            return (DataShape.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.DataShape#getExemplar() exemplar} to exemplar.
         *
         * @param exemplar The value for exemplar
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("exemplar")
        public final DataShape.Builder exemplar(
                Optional<? extends byte[]> exemplar) {
            this.exemplar = exemplar.orElse(null);
            return (DataShape.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.DataShape#getMetadata() metadata} map.
         *
         * @param key   The key in the metadata map
         * @param value The associated value in the metadata map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final DataShape.Builder putMetadata(String key, String value) {
            this.metadata.put(
                    Objects.requireNonNull(key, "metadata key"),
                    Objects.requireNonNull(value, "metadata value"));
            return (DataShape.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.DataShape#getMetadata() metadata} map. Nulls are not permitted
         *
         * @param entry The key and value entry
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final DataShape.Builder putMetadata(
                Map.Entry<String, ? extends String> entry) {
            String k = entry.getKey();
            String v = entry.getValue();
            this.metadata.put(
                    Objects.requireNonNull(k, "metadata key"),
                    Objects.requireNonNull(v, "metadata value"));
            return (DataShape.Builder) this;
        }

        /**
         * Sets or replaces all mappings from the specified map as entries for the {@link io.syndesis.common.model.DataShape#getMetadata() metadata} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the metadata map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("metadata")
        public final DataShape.Builder metadata(
                Map<String, ? extends String> entries) {
            this.metadata.clear();
            return putAllMetadata(entries);
        }

        /**
         * Put all mappings from the specified map as entries to {@link io.syndesis.common.model.DataShape#getMetadata() metadata} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the metadata map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final DataShape.Builder putAllMetadata(
                Map<String, ? extends String> entries) {
            for (Map.Entry<String, ? extends String> e : entries.entrySet()) {
                String k = e.getKey();
                String v = e.getValue();
                this.metadata.put(
                        Objects.requireNonNull(k, "metadata key"),
                        Objects.requireNonNull(v, "metadata value"));
            }
            return (DataShape.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.DataShape#getParameters() parameters} map.
         *
         * @param key   The key in the parameters map
         * @param value The associated value in the parameters map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final DataShape.Builder putParameter(String key, String value) {
            this.parameters.put(
                    Objects.requireNonNull(key, "parameters key"),
                    Objects.requireNonNull(value, "parameters value"));
            return (DataShape.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.DataShape#getParameters() parameters} map. Nulls are not permitted
         *
         * @param entry The key and value entry
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final DataShape.Builder putParameter(
                Map.Entry<String, ? extends String> entry) {
            String k = entry.getKey();
            String v = entry.getValue();
            this.parameters.put(
                    Objects.requireNonNull(k, "parameters key"),
                    Objects.requireNonNull(v, "parameters value"));
            return (DataShape.Builder) this;
        }

        /**
         * Sets or replaces all mappings from the specified map as entries for the {@link io.syndesis.common.model.DataShape#getParameters() parameters} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the parameters map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("parameters")
        public final DataShape.Builder parameters(
                Map<String, ? extends String> entries) {
            this.parameters.clear();
            return putAllParameters(entries);
        }

        /**
         * Put all mappings from the specified map as entries to {@link io.syndesis.common.model.DataShape#getParameters() parameters} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the parameters map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final DataShape.Builder putAllParameters(
                Map<String, ? extends String> entries) {
            for (Map.Entry<String, ? extends String> e : entries.entrySet()) {
                String k = e.getKey();
                String v = e.getValue();
                this.parameters.put(
                        Objects.requireNonNull(k, "parameters key"),
                        Objects.requireNonNull(v, "parameters value"));
            }
            return (DataShape.Builder) this;
        }

        /**
         * Adds one element to {@link io.syndesis.common.model.DataShape#getVariants() variants} list.
         *
         * @param element A variants element
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final DataShape.Builder addVariant(DataShape element) {
            this.variants.add(
                    Objects.requireNonNull(element, "variants element"));
            optBits |= OPT_BIT_VARIANTS;
            return (DataShape.Builder) this;
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.DataShape#getVariants() variants} list.
         *
         * @param elements An array of variants elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final DataShape.Builder addVariants(DataShape... elements) {
            for (DataShape element : elements) {
                this.variants.add(
                        Objects.requireNonNull(element, "variants element"));
            }
            optBits |= OPT_BIT_VARIANTS;
            return (DataShape.Builder) this;
        }


        /**
         * Sets or replaces all elements for {@link io.syndesis.common.model.DataShape#getVariants() variants} list.
         *
         * @param elements An iterable of variants elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("variants")
        public final DataShape.Builder variants(
                Iterable<? extends DataShape> elements) {
            this.variants.clear();
            return addAllVariants(elements);
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.DataShape#getVariants() variants} list.
         *
         * @param elements An iterable of variants elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final DataShape.Builder addAllVariants(
                Iterable<? extends DataShape> elements) {
            for (DataShape element : elements) {
                this.variants.add(
                        Objects.requireNonNull(element, "variants element"));
            }
            optBits |= OPT_BIT_VARIANTS;
            return (DataShape.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.DataShape DataShape}.
         *
         * @return An immutable instance of DataShape
         * @throws IllegalStateException if any required attributes are missing
         */
        public DataShape build() {
            return ImmutableDataShape.validate(new ImmutableDataShape(this));
        }

        private boolean variantsIsSet() {
            return (optBits & OPT_BIT_VARIANTS) != 0;
        }
    }
}
