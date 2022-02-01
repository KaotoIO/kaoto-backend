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
import io.syndesis.common.model.Ordered;
import io.syndesis.common.model.WithTags;

import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Immutable implementation of {@link io.syndesis.common.model.connection.ConfigurationProperty}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new ConfigurationProperty.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableConfigurationProperty.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableConfigurationProperty
        implements ConfigurationProperty {
    private final SortedSet<String> tags;
    private final Integer order;
    private final Boolean componentProperty;
    private final String connectorValue;
    private final String controlHint;
    private final String defaultValue;
    private final Boolean deprecated;
    private final String description;
    private final String displayName;
    private final List<PropertyValue> getEnum;
    private final List<String> dataList;
    private final String generator;
    private final String group;
    private final String javaType;
    private final String kind;
    private final String label;
    private final String labelHint;
    private final String placeholder;
    private final Boolean raw;
    private final List<PropertyRelation> relation;
    private final Boolean required;
    private final Boolean secret;
    private final String type;
    private final Boolean multiple;
    private final String extendedProperties;
    private final ConfigurationProperty.ArrayDefinition arrayDefinition;
    private final ConfigurationProperty.ArrayDefinitionOptions
            arrayDefinitionOptions;

    @SuppressWarnings("unchecked") // safe covariant cast
    private ImmutableConfigurationProperty(
            Iterable<String> tags,
            Optional<Integer> order,
            Boolean componentProperty,
            Optional<String> connectorValue,
            String controlHint,
            String defaultValue,
            Boolean deprecated,
            String description,
            String displayName,
            Iterable<? extends PropertyValue> getEnum,
            Iterable<String> dataList,
            String generator,
            String group,
            String javaType,
            String kind,
            String label,
            String labelHint,
            String placeholder,
            Boolean raw,
            Iterable<? extends PropertyRelation> relation,
            Boolean required,
            Boolean secret,
            String type,
            Boolean multiple,
            String extendedProperties,
            Optional<? extends ArrayDefinition> arrayDefinition,
            Optional<? extends ArrayDefinitionOptions> arrayDefinitionOptions) {
        this.tags = createUnmodifiableSortedSet(false,
                createSafeList(tags, false, true));
        this.order = order.isPresent() ? order.get() : null;
        this.componentProperty = componentProperty;
        this.connectorValue = connectorValue.orElse(null);
        this.controlHint = controlHint;
        this.defaultValue = defaultValue;
        this.deprecated = deprecated;
        this.description = description;
        this.displayName = displayName;
        this.getEnum = createUnmodifiableList(false,
                createSafeList(getEnum, true, false));
        this.dataList = createUnmodifiableList(false,
                createSafeList(dataList, true, false));
        this.generator = generator;
        this.group = group;
        this.javaType = javaType;
        this.kind = kind;
        this.label = label;
        this.labelHint = labelHint;
        this.placeholder = placeholder;
        this.raw = raw;
        this.relation = createUnmodifiableList(false,
                createSafeList(relation, true, false));
        this.required = required;
        this.secret = secret;
        this.type = type;
        this.multiple = multiple;
        this.extendedProperties = extendedProperties;
        this.arrayDefinition = arrayDefinition.orElse(null);
        this.arrayDefinitionOptions = arrayDefinitionOptions.orElse(null);
    }

    private ImmutableConfigurationProperty(
            ImmutableConfigurationProperty original,
            SortedSet<String> tags,
            Integer order,
            Boolean componentProperty,
            String connectorValue,
            String controlHint,
            String defaultValue,
            Boolean deprecated,
            String description,
            String displayName,
            List<PropertyValue> getEnum,
            List<String> dataList,
            String generator,
            String group,
            String javaType,
            String kind,
            String label,
            String labelHint,
            String placeholder,
            Boolean raw,
            List<PropertyRelation> relation,
            Boolean required,
            Boolean secret,
            String type,
            Boolean multiple,
            String extendedProperties,
            ConfigurationProperty.ArrayDefinition arrayDefinition,
            ConfigurationProperty.ArrayDefinitionOptions arrayDefinitionOptions) {
        this.tags = tags;
        this.order = order;
        this.componentProperty = componentProperty;
        this.connectorValue = connectorValue;
        this.controlHint = controlHint;
        this.defaultValue = defaultValue;
        this.deprecated = deprecated;
        this.description = description;
        this.displayName = displayName;
        this.getEnum = getEnum;
        this.dataList = dataList;
        this.generator = generator;
        this.group = group;
        this.javaType = javaType;
        this.kind = kind;
        this.label = label;
        this.labelHint = labelHint;
        this.placeholder = placeholder;
        this.raw = raw;
        this.relation = relation;
        this.required = required;
        this.secret = secret;
        this.type = type;
        this.multiple = multiple;
        this.extendedProperties = extendedProperties;
        this.arrayDefinition = arrayDefinition;
        this.arrayDefinitionOptions = arrayDefinitionOptions;
    }

    /**
     * Construct a new immutable {@code ConfigurationProperty} instance.
     *
     * @param tags                   The value for the {@code tags} attribute
     * @param order                  The value for the {@code order} attribute
     * @param componentProperty      The value for the {@code componentProperty} attribute
     * @param connectorValue         The value for the {@code connectorValue} attribute
     * @param controlHint            The value for the {@code controlHint} attribute
     * @param defaultValue           The value for the {@code defaultValue} attribute
     * @param deprecated             The value for the {@code deprecated} attribute
     * @param description            The value for the {@code description} attribute
     * @param displayName            The value for the {@code displayName} attribute
     * @param getEnum                The value for the {@code getEnum} attribute
     * @param dataList               The value for the {@code dataList} attribute
     * @param generator              The value for the {@code generator} attribute
     * @param group                  The value for the {@code group} attribute
     * @param javaType               The value for the {@code javaType} attribute
     * @param kind                   The value for the {@code kind} attribute
     * @param label                  The value for the {@code label} attribute
     * @param labelHint              The value for the {@code labelHint} attribute
     * @param placeholder            The value for the {@code placeholder} attribute
     * @param raw                    The value for the {@code raw} attribute
     * @param relation               The value for the {@code relation} attribute
     * @param required               The value for the {@code required} attribute
     * @param secret                 The value for the {@code secret} attribute
     * @param type                   The value for the {@code type} attribute
     * @param multiple               The value for the {@code multiple} attribute
     * @param extendedProperties     The value for the {@code extendedProperties} attribute
     * @param arrayDefinition        The value for the {@code arrayDefinition} attribute
     * @param arrayDefinitionOptions The value for the {@code arrayDefinitionOptions} attribute
     * @return An immutable ConfigurationProperty instance
     */
    public static ConfigurationProperty of(SortedSet<String> tags,
                                           Optional<Integer> order,
                                           Boolean componentProperty,
                                           Optional<String> connectorValue,
                                           String controlHint,
                                           String defaultValue,
                                           Boolean deprecated,
                                           String description,
                                           String displayName,
                                           List<PropertyValue> getEnum,
                                           List<String> dataList,
                                           String generator, String group,
                                           String javaType, String kind,
                                           String label, String labelHint,
                                           String placeholder, Boolean raw,
                                           List<PropertyRelation> relation,
                                           Boolean required, Boolean secret,
                                           String type, Boolean multiple,
                                           String extendedProperties,
                                           Optional<ArrayDefinition> arrayDefinition,
                                           Optional<ArrayDefinitionOptions> arrayDefinitionOptions) {
        return of((Iterable<String>) tags, order, componentProperty,
                connectorValue, controlHint, defaultValue, deprecated,
                description, displayName,
                (Iterable<? extends PropertyValue>) getEnum,
                (Iterable<String>) dataList, generator, group, javaType, kind,
                label, labelHint, placeholder, raw,
                (Iterable<? extends PropertyRelation>) relation, required,
                secret, type, multiple, extendedProperties, arrayDefinition,
                arrayDefinitionOptions);
    }

    /**
     * Construct a new immutable {@code ConfigurationProperty} instance.
     *
     * @param tags                   The value for the {@code tags} attribute
     * @param order                  The value for the {@code order} attribute
     * @param componentProperty      The value for the {@code componentProperty} attribute
     * @param connectorValue         The value for the {@code connectorValue} attribute
     * @param controlHint            The value for the {@code controlHint} attribute
     * @param defaultValue           The value for the {@code defaultValue} attribute
     * @param deprecated             The value for the {@code deprecated} attribute
     * @param description            The value for the {@code description} attribute
     * @param displayName            The value for the {@code displayName} attribute
     * @param getEnum                The value for the {@code getEnum} attribute
     * @param dataList               The value for the {@code dataList} attribute
     * @param generator              The value for the {@code generator} attribute
     * @param group                  The value for the {@code group} attribute
     * @param javaType               The value for the {@code javaType} attribute
     * @param kind                   The value for the {@code kind} attribute
     * @param label                  The value for the {@code label} attribute
     * @param labelHint              The value for the {@code labelHint} attribute
     * @param placeholder            The value for the {@code placeholder} attribute
     * @param raw                    The value for the {@code raw} attribute
     * @param relation               The value for the {@code relation} attribute
     * @param required               The value for the {@code required} attribute
     * @param secret                 The value for the {@code secret} attribute
     * @param type                   The value for the {@code type} attribute
     * @param multiple               The value for the {@code multiple} attribute
     * @param extendedProperties     The value for the {@code extendedProperties} attribute
     * @param arrayDefinition        The value for the {@code arrayDefinition} attribute
     * @param arrayDefinitionOptions The value for the {@code arrayDefinitionOptions} attribute
     * @return An immutable ConfigurationProperty instance
     */
    public static ConfigurationProperty of(Iterable<String> tags,
                                           Optional<Integer> order,
                                           Boolean componentProperty,
                                           Optional<String> connectorValue,
                                           String controlHint,
                                           String defaultValue,
                                           Boolean deprecated,
                                           String description,
                                           String displayName,
                                           Iterable<? extends PropertyValue> getEnum,
                                           Iterable<String> dataList,
                                           String generator, String group,
                                           String javaType, String kind,
                                           String label, String labelHint,
                                           String placeholder, Boolean raw,
                                           Iterable<? extends PropertyRelation> relation,
                                           Boolean required, Boolean secret,
                                           String type, Boolean multiple,
                                           String extendedProperties,
                                           Optional<? extends ArrayDefinition> arrayDefinition,
                                           Optional<? extends ArrayDefinitionOptions> arrayDefinitionOptions) {
        return validate(new ImmutableConfigurationProperty(tags, order,
                componentProperty, connectorValue, controlHint, defaultValue,
                deprecated, description, displayName, getEnum, dataList,
                generator, group, javaType, kind, label, labelHint, placeholder,
                raw, relation, required, secret, type, multiple,
                extendedProperties, arrayDefinition, arrayDefinitionOptions));
    }

    private static ImmutableConfigurationProperty validate(
            ImmutableConfigurationProperty instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.connection.ConfigurationProperty} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable ConfigurationProperty instance
     */
    public static ConfigurationProperty copyOf(ConfigurationProperty instance) {
        if (instance instanceof ImmutableConfigurationProperty) {
            return (ImmutableConfigurationProperty) instance;
        }
        return new ConfigurationProperty.Builder()
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

    /**
     * @return The value of the {@code tags} attribute
     */
    @JsonProperty("tags")
    @Override
    public SortedSet<String> getTags() {
        return tags;
    }

    /**
     * @return The value of the {@code order} attribute
     */
    @JsonProperty("order")
    @Override
    public Optional<Integer> getOrder() {
        return Optional.of(order);
    }

    /**
     * @return The value of the {@code componentProperty} attribute
     */
    @JsonProperty("componentProperty")
    @Override
    public Boolean getComponentProperty() {
        return componentProperty;
    }

    /**
     * @return The value of the {@code connectorValue} attribute
     */
    @JsonProperty("connectorValue")
    @Override
    public Optional<String> getConnectorValue() {
        return Optional.ofNullable(connectorValue);
    }

    /**
     * @return The value of the {@code controlHint} attribute
     */
    @JsonProperty("controlHint")
    @Override
    public String getControlHint() {
        return controlHint;
    }

    /**
     * @return The value of the {@code defaultValue} attribute
     */
    @JsonProperty("defaultValue")
    @Override
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * @return The value of the {@code deprecated} attribute
     */
    @JsonProperty("deprecated")
    @Override
    public Boolean getDeprecated() {
        return deprecated;
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
     * @return The value of the {@code displayName} attribute
     */
    @JsonProperty("displayName")
    @Override
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @return The value of the {@code getEnum} attribute
     */
    @JsonProperty("enum")
    @Override
    public List<PropertyValue> getEnum() {
        return getEnum;
    }

    /**
     * @return The value of the {@code dataList} attribute
     */
    @JsonProperty("dataList")
    @Override
    public List<String> getDataList() {
        return dataList;
    }

    /**
     * @return The value of the {@code generator} attribute
     */
    @JsonProperty("generator")
    @Override
    public String getGenerator() {
        return generator;
    }

    /**
     * @return The value of the {@code group} attribute
     */
    @JsonProperty("group")
    @Override
    public String getGroup() {
        return group;
    }

    /**
     * @return The value of the {@code javaType} attribute
     */
    @JsonProperty("javaType")
    @Override
    public String getJavaType() {
        return javaType;
    }

    /**
     * @return The value of the {@code kind} attribute
     */
    @JsonProperty("kind")
    @Override
    public String getKind() {
        return kind;
    }

    /**
     * @return The value of the {@code label} attribute
     */
    @JsonProperty("label")
    @Override
    public String getLabel() {
        return label;
    }

    /**
     * @return The value of the {@code labelHint} attribute
     */
    @JsonProperty("labelHint")
    @Override
    public String getLabelHint() {
        return labelHint;
    }

    /**
     * @return The value of the {@code placeholder} attribute
     */
    @JsonProperty("placeholder")
    @Override
    public String getPlaceholder() {
        return placeholder;
    }

    /**
     * @return The value of the {@code raw} attribute
     */
    @JsonProperty("raw")
    @Override
    public Boolean getRaw() {
        return raw;
    }

    /**
     * @return The value of the {@code relation} attribute
     */
    @JsonProperty("relation")
    @Override
    public List<PropertyRelation> getRelation() {
        return relation;
    }

    /**
     * @return The value of the {@code required} attribute
     */
    @JsonProperty("required")
    @Override
    public Boolean getRequired() {
        return required;
    }

    /**
     * @return The value of the {@code secret} attribute
     */
    @JsonProperty("secret")
    @Override
    public Boolean getSecret() {
        return secret;
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
     * @return The value of the {@code multiple} attribute
     */
    @JsonProperty("multiple")
    @Override
    public Boolean getMultiple() {
        return multiple;
    }

    /**
     * @return The value of the {@code extendedProperties} attribute
     */
    @JsonProperty("extendedProperties")
    @Override
    public String getExtendedProperties() {
        return extendedProperties;
    }

    /**
     * @return The value of the {@code arrayDefinition} attribute
     */
    @JsonProperty("arrayDefinition")
    @Override
    public Optional<ArrayDefinition> getArrayDefinition() {
        return Optional.ofNullable(arrayDefinition);
    }

    /**
     * @return The value of the {@code arrayDefinitionOptions} attribute
     */
    @JsonProperty("arrayDefinitionOptions")
    @Override
    public Optional<ArrayDefinitionOptions> getArrayDefinitionOptions() {
        return Optional.ofNullable(arrayDefinitionOptions);
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.connection.ConfigurationProperty#getTags() tags}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConfigurationProperty withTags(String... elements) {
        SortedSet<String> newValue = createUnmodifiableSortedSet(false,
                createSafeList(Arrays.asList(elements), false, true));
        return validate(new ImmutableConfigurationProperty(
                this,
                newValue,
                this.order,
                this.componentProperty,
                this.connectorValue,
                this.controlHint,
                this.defaultValue,
                this.deprecated,
                this.description,
                this.displayName,
                this.getEnum,
                this.dataList,
                this.generator,
                this.group,
                this.javaType,
                this.kind,
                this.label,
                this.labelHint,
                this.placeholder,
                this.raw,
                this.relation,
                this.required,
                this.secret,
                this.type,
                this.multiple,
                this.extendedProperties,
                this.arrayDefinition,
                this.arrayDefinitionOptions));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.connection.ConfigurationProperty#getTags() tags}.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of tags elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConfigurationProperty withTags(
            Iterable<String> elements) {
        if (this.tags == elements) {
            return this;
        }
        SortedSet<String> newValue = createUnmodifiableSortedSet(false,
                createSafeList(elements, false, true));
        return validate(new ImmutableConfigurationProperty(
                this,
                newValue,
                this.order,
                this.componentProperty,
                this.connectorValue,
                this.controlHint,
                this.defaultValue,
                this.deprecated,
                this.description,
                this.displayName,
                this.getEnum,
                this.dataList,
                this.generator,
                this.group,
                this.javaType,
                this.kind,
                this.label,
                this.labelHint,
                this.placeholder,
                this.raw,
                this.relation,
                this.required,
                this.secret,
                this.type,
                this.multiple,
                this.extendedProperties,
                this.arrayDefinition,
                this.arrayDefinitionOptions));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.connection.ConfigurationProperty#getOrder() order} attribute.
     *
     * @param value The value for order
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConfigurationProperty withOrder(int value) {
        Integer newValue = value;
        if (Objects.equals(this.order, newValue)) {
            return this;
        }
        return validate(new ImmutableConfigurationProperty(
                this,
                this.tags,
                newValue,
                this.componentProperty,
                this.connectorValue,
                this.controlHint,
                this.defaultValue,
                this.deprecated,
                this.description,
                this.displayName,
                this.getEnum,
                this.dataList,
                this.generator,
                this.group,
                this.javaType,
                this.kind,
                this.label,
                this.labelHint,
                this.placeholder,
                this.raw,
                this.relation,
                this.required,
                this.secret,
                this.type,
                this.multiple,
                this.extendedProperties,
                this.arrayDefinition,
                this.arrayDefinitionOptions));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.connection.ConfigurationProperty#getOrder() order} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for order
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConfigurationProperty withOrder(
            Optional<Integer> optional) {
        Integer value = optional.isPresent() ? optional.get() : null;
        if (Objects.equals(this.order, value)) {
            return this;
        }
        return validate(new ImmutableConfigurationProperty(
                this,
                this.tags,
                value,
                this.componentProperty,
                this.connectorValue,
                this.controlHint,
                this.defaultValue,
                this.deprecated,
                this.description,
                this.displayName,
                this.getEnum,
                this.dataList,
                this.generator,
                this.group,
                this.javaType,
                this.kind,
                this.label,
                this.labelHint,
                this.placeholder,
                this.raw,
                this.relation,
                this.required,
                this.secret,
                this.type,
                this.multiple,
                this.extendedProperties,
                this.arrayDefinition,
                this.arrayDefinitionOptions));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConfigurationProperty#getComponentProperty() componentProperty} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for componentProperty (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConfigurationProperty withComponentProperty(
            Boolean value) {
        if (Objects.equals(this.componentProperty, value)) {
            return this;
        }
        return validate(new ImmutableConfigurationProperty(
                this,
                this.tags,
                this.order,
                value,
                this.connectorValue,
                this.controlHint,
                this.defaultValue,
                this.deprecated,
                this.description,
                this.displayName,
                this.getEnum,
                this.dataList,
                this.generator,
                this.group,
                this.javaType,
                this.kind,
                this.label,
                this.labelHint,
                this.placeholder,
                this.raw,
                this.relation,
                this.required,
                this.secret,
                this.type,
                this.multiple,
                this.extendedProperties,
                this.arrayDefinition,
                this.arrayDefinitionOptions));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.connection.ConfigurationProperty#getConnectorValue() connectorValue} attribute.
     *
     * @param value The value for connectorValue
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConfigurationProperty withConnectorValue(
            String value) {
        String newValue = Objects.requireNonNull(value, "connectorValue");
        if (Objects.equals(this.connectorValue, newValue)) {
            return this;
        }
        return validate(new ImmutableConfigurationProperty(
                this,
                this.tags,
                this.order,
                this.componentProperty,
                newValue,
                this.controlHint,
                this.defaultValue,
                this.deprecated,
                this.description,
                this.displayName,
                this.getEnum,
                this.dataList,
                this.generator,
                this.group,
                this.javaType,
                this.kind,
                this.label,
                this.labelHint,
                this.placeholder,
                this.raw,
                this.relation,
                this.required,
                this.secret,
                this.type,
                this.multiple,
                this.extendedProperties,
                this.arrayDefinition,
                this.arrayDefinitionOptions));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.connection.ConfigurationProperty#getConnectorValue() connectorValue} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for connectorValue
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConfigurationProperty withConnectorValue(
            Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.connectorValue, value)) {
            return this;
        }
        return validate(new ImmutableConfigurationProperty(
                this,
                this.tags,
                this.order,
                this.componentProperty,
                value,
                this.controlHint,
                this.defaultValue,
                this.deprecated,
                this.description,
                this.displayName,
                this.getEnum,
                this.dataList,
                this.generator,
                this.group,
                this.javaType,
                this.kind,
                this.label,
                this.labelHint,
                this.placeholder,
                this.raw,
                this.relation,
                this.required,
                this.secret,
                this.type,
                this.multiple,
                this.extendedProperties,
                this.arrayDefinition,
                this.arrayDefinitionOptions));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConfigurationProperty#getControlHint() controlHint} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for controlHint (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConfigurationProperty withControlHint(String value) {
        if (Objects.equals(this.controlHint, value)) {
            return this;
        }
        return validate(new ImmutableConfigurationProperty(
                this,
                this.tags,
                this.order,
                this.componentProperty,
                this.connectorValue,
                value,
                this.defaultValue,
                this.deprecated,
                this.description,
                this.displayName,
                this.getEnum,
                this.dataList,
                this.generator,
                this.group,
                this.javaType,
                this.kind,
                this.label,
                this.labelHint,
                this.placeholder,
                this.raw,
                this.relation,
                this.required,
                this.secret,
                this.type,
                this.multiple,
                this.extendedProperties,
                this.arrayDefinition,
                this.arrayDefinitionOptions));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConfigurationProperty#getDefaultValue() defaultValue} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for defaultValue (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConfigurationProperty withDefaultValue(String value) {
        if (Objects.equals(this.defaultValue, value)) {
            return this;
        }
        return validate(new ImmutableConfigurationProperty(
                this,
                this.tags,
                this.order,
                this.componentProperty,
                this.connectorValue,
                this.controlHint,
                value,
                this.deprecated,
                this.description,
                this.displayName,
                this.getEnum,
                this.dataList,
                this.generator,
                this.group,
                this.javaType,
                this.kind,
                this.label,
                this.labelHint,
                this.placeholder,
                this.raw,
                this.relation,
                this.required,
                this.secret,
                this.type,
                this.multiple,
                this.extendedProperties,
                this.arrayDefinition,
                this.arrayDefinitionOptions));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConfigurationProperty#getDeprecated() deprecated} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for deprecated (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConfigurationProperty withDeprecated(Boolean value) {
        if (Objects.equals(this.deprecated, value)) {
            return this;
        }
        return validate(new ImmutableConfigurationProperty(
                this,
                this.tags,
                this.order,
                this.componentProperty,
                this.connectorValue,
                this.controlHint,
                this.defaultValue,
                value,
                this.description,
                this.displayName,
                this.getEnum,
                this.dataList,
                this.generator,
                this.group,
                this.javaType,
                this.kind,
                this.label,
                this.labelHint,
                this.placeholder,
                this.raw,
                this.relation,
                this.required,
                this.secret,
                this.type,
                this.multiple,
                this.extendedProperties,
                this.arrayDefinition,
                this.arrayDefinitionOptions));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConfigurationProperty#getDescription() description} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for description (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConfigurationProperty withDescription(String value) {
        if (Objects.equals(this.description, value)) {
            return this;
        }
        return validate(new ImmutableConfigurationProperty(
                this,
                this.tags,
                this.order,
                this.componentProperty,
                this.connectorValue,
                this.controlHint,
                this.defaultValue,
                this.deprecated,
                value,
                this.displayName,
                this.getEnum,
                this.dataList,
                this.generator,
                this.group,
                this.javaType,
                this.kind,
                this.label,
                this.labelHint,
                this.placeholder,
                this.raw,
                this.relation,
                this.required,
                this.secret,
                this.type,
                this.multiple,
                this.extendedProperties,
                this.arrayDefinition,
                this.arrayDefinitionOptions));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConfigurationProperty#getDisplayName() displayName} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for displayName (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConfigurationProperty withDisplayName(String value) {
        if (Objects.equals(this.displayName, value)) {
            return this;
        }
        return validate(new ImmutableConfigurationProperty(
                this,
                this.tags,
                this.order,
                this.componentProperty,
                this.connectorValue,
                this.controlHint,
                this.defaultValue,
                this.deprecated,
                this.description,
                value,
                this.getEnum,
                this.dataList,
                this.generator,
                this.group,
                this.javaType,
                this.kind,
                this.label,
                this.labelHint,
                this.placeholder,
                this.raw,
                this.relation,
                this.required,
                this.secret,
                this.type,
                this.multiple,
                this.extendedProperties,
                this.arrayDefinition,
                this.arrayDefinitionOptions));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.connection.ConfigurationProperty#getEnum() enum}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConfigurationProperty withEnum(
            PropertyValue... elements) {
        List<PropertyValue> newValue = createUnmodifiableList(false,
                createSafeList(Arrays.asList(elements), true, false));
        return validate(new ImmutableConfigurationProperty(
                this,
                this.tags,
                this.order,
                this.componentProperty,
                this.connectorValue,
                this.controlHint,
                this.defaultValue,
                this.deprecated,
                this.description,
                this.displayName,
                newValue,
                this.dataList,
                this.generator,
                this.group,
                this.javaType,
                this.kind,
                this.label,
                this.labelHint,
                this.placeholder,
                this.raw,
                this.relation,
                this.required,
                this.secret,
                this.type,
                this.multiple,
                this.extendedProperties,
                this.arrayDefinition,
                this.arrayDefinitionOptions));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.connection.ConfigurationProperty#getEnum() enum}.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of getEnum elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConfigurationProperty withEnum(
            Iterable<? extends PropertyValue> elements) {
        if (this.getEnum == elements) {
            return this;
        }
        List<PropertyValue> newValue = createUnmodifiableList(false,
                createSafeList(elements, true, false));
        return validate(new ImmutableConfigurationProperty(
                this,
                this.tags,
                this.order,
                this.componentProperty,
                this.connectorValue,
                this.controlHint,
                this.defaultValue,
                this.deprecated,
                this.description,
                this.displayName,
                newValue,
                this.dataList,
                this.generator,
                this.group,
                this.javaType,
                this.kind,
                this.label,
                this.labelHint,
                this.placeholder,
                this.raw,
                this.relation,
                this.required,
                this.secret,
                this.type,
                this.multiple,
                this.extendedProperties,
                this.arrayDefinition,
                this.arrayDefinitionOptions));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.connection.ConfigurationProperty#getDataList() dataList}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConfigurationProperty withDataList(
            String... elements) {
        List<String> newValue = createUnmodifiableList(false,
                createSafeList(Arrays.asList(elements), true, false));
        return validate(new ImmutableConfigurationProperty(
                this,
                this.tags,
                this.order,
                this.componentProperty,
                this.connectorValue,
                this.controlHint,
                this.defaultValue,
                this.deprecated,
                this.description,
                this.displayName,
                this.getEnum,
                newValue,
                this.generator,
                this.group,
                this.javaType,
                this.kind,
                this.label,
                this.labelHint,
                this.placeholder,
                this.raw,
                this.relation,
                this.required,
                this.secret,
                this.type,
                this.multiple,
                this.extendedProperties,
                this.arrayDefinition,
                this.arrayDefinitionOptions));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.connection.ConfigurationProperty#getDataList() dataList}.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of dataList elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConfigurationProperty withDataList(
            Iterable<String> elements) {
        if (this.dataList == elements) {
            return this;
        }
        List<String> newValue = createUnmodifiableList(false,
                createSafeList(elements, true, false));
        return validate(new ImmutableConfigurationProperty(
                this,
                this.tags,
                this.order,
                this.componentProperty,
                this.connectorValue,
                this.controlHint,
                this.defaultValue,
                this.deprecated,
                this.description,
                this.displayName,
                this.getEnum,
                newValue,
                this.generator,
                this.group,
                this.javaType,
                this.kind,
                this.label,
                this.labelHint,
                this.placeholder,
                this.raw,
                this.relation,
                this.required,
                this.secret,
                this.type,
                this.multiple,
                this.extendedProperties,
                this.arrayDefinition,
                this.arrayDefinitionOptions));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConfigurationProperty#getGenerator() generator} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for generator (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConfigurationProperty withGenerator(String value) {
        if (Objects.equals(this.generator, value)) {
            return this;
        }
        return validate(new ImmutableConfigurationProperty(
                this,
                this.tags,
                this.order,
                this.componentProperty,
                this.connectorValue,
                this.controlHint,
                this.defaultValue,
                this.deprecated,
                this.description,
                this.displayName,
                this.getEnum,
                this.dataList,
                value,
                this.group,
                this.javaType,
                this.kind,
                this.label,
                this.labelHint,
                this.placeholder,
                this.raw,
                this.relation,
                this.required,
                this.secret,
                this.type,
                this.multiple,
                this.extendedProperties,
                this.arrayDefinition,
                this.arrayDefinitionOptions));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConfigurationProperty#getGroup() group} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for group (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConfigurationProperty withGroup(String value) {
        if (Objects.equals(this.group, value)) {
            return this;
        }
        return validate(new ImmutableConfigurationProperty(
                this,
                this.tags,
                this.order,
                this.componentProperty,
                this.connectorValue,
                this.controlHint,
                this.defaultValue,
                this.deprecated,
                this.description,
                this.displayName,
                this.getEnum,
                this.dataList,
                this.generator,
                value,
                this.javaType,
                this.kind,
                this.label,
                this.labelHint,
                this.placeholder,
                this.raw,
                this.relation,
                this.required,
                this.secret,
                this.type,
                this.multiple,
                this.extendedProperties,
                this.arrayDefinition,
                this.arrayDefinitionOptions));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConfigurationProperty#getJavaType() javaType} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for javaType (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConfigurationProperty withJavaType(String value) {
        if (Objects.equals(this.javaType, value)) {
            return this;
        }
        return validate(new ImmutableConfigurationProperty(
                this,
                this.tags,
                this.order,
                this.componentProperty,
                this.connectorValue,
                this.controlHint,
                this.defaultValue,
                this.deprecated,
                this.description,
                this.displayName,
                this.getEnum,
                this.dataList,
                this.generator,
                this.group,
                value,
                this.kind,
                this.label,
                this.labelHint,
                this.placeholder,
                this.raw,
                this.relation,
                this.required,
                this.secret,
                this.type,
                this.multiple,
                this.extendedProperties,
                this.arrayDefinition,
                this.arrayDefinitionOptions));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConfigurationProperty#getKind() kind} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for kind (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConfigurationProperty withKind(String value) {
        if (Objects.equals(this.kind, value)) {
            return this;
        }
        return validate(new ImmutableConfigurationProperty(
                this,
                this.tags,
                this.order,
                this.componentProperty,
                this.connectorValue,
                this.controlHint,
                this.defaultValue,
                this.deprecated,
                this.description,
                this.displayName,
                this.getEnum,
                this.dataList,
                this.generator,
                this.group,
                this.javaType,
                value,
                this.label,
                this.labelHint,
                this.placeholder,
                this.raw,
                this.relation,
                this.required,
                this.secret,
                this.type,
                this.multiple,
                this.extendedProperties,
                this.arrayDefinition,
                this.arrayDefinitionOptions));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConfigurationProperty#getLabel() label} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for label (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConfigurationProperty withLabel(String value) {
        if (Objects.equals(this.label, value)) {
            return this;
        }
        return validate(new ImmutableConfigurationProperty(
                this,
                this.tags,
                this.order,
                this.componentProperty,
                this.connectorValue,
                this.controlHint,
                this.defaultValue,
                this.deprecated,
                this.description,
                this.displayName,
                this.getEnum,
                this.dataList,
                this.generator,
                this.group,
                this.javaType,
                this.kind,
                value,
                this.labelHint,
                this.placeholder,
                this.raw,
                this.relation,
                this.required,
                this.secret,
                this.type,
                this.multiple,
                this.extendedProperties,
                this.arrayDefinition,
                this.arrayDefinitionOptions));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConfigurationProperty#getLabelHint() labelHint} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for labelHint (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConfigurationProperty withLabelHint(String value) {
        if (Objects.equals(this.labelHint, value)) {
            return this;
        }
        return validate(new ImmutableConfigurationProperty(
                this,
                this.tags,
                this.order,
                this.componentProperty,
                this.connectorValue,
                this.controlHint,
                this.defaultValue,
                this.deprecated,
                this.description,
                this.displayName,
                this.getEnum,
                this.dataList,
                this.generator,
                this.group,
                this.javaType,
                this.kind,
                this.label,
                value,
                this.placeholder,
                this.raw,
                this.relation,
                this.required,
                this.secret,
                this.type,
                this.multiple,
                this.extendedProperties,
                this.arrayDefinition,
                this.arrayDefinitionOptions));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConfigurationProperty#getPlaceholder() placeholder} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for placeholder (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConfigurationProperty withPlaceholder(String value) {
        if (Objects.equals(this.placeholder, value)) {
            return this;
        }
        return validate(new ImmutableConfigurationProperty(
                this,
                this.tags,
                this.order,
                this.componentProperty,
                this.connectorValue,
                this.controlHint,
                this.defaultValue,
                this.deprecated,
                this.description,
                this.displayName,
                this.getEnum,
                this.dataList,
                this.generator,
                this.group,
                this.javaType,
                this.kind,
                this.label,
                this.labelHint,
                value,
                this.raw,
                this.relation,
                this.required,
                this.secret,
                this.type,
                this.multiple,
                this.extendedProperties,
                this.arrayDefinition,
                this.arrayDefinitionOptions));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConfigurationProperty#getRaw() raw} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for raw (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConfigurationProperty withRaw(Boolean value) {
        if (Objects.equals(this.raw, value)) {
            return this;
        }
        return validate(new ImmutableConfigurationProperty(
                this,
                this.tags,
                this.order,
                this.componentProperty,
                this.connectorValue,
                this.controlHint,
                this.defaultValue,
                this.deprecated,
                this.description,
                this.displayName,
                this.getEnum,
                this.dataList,
                this.generator,
                this.group,
                this.javaType,
                this.kind,
                this.label,
                this.labelHint,
                this.placeholder,
                value,
                this.relation,
                this.required,
                this.secret,
                this.type,
                this.multiple,
                this.extendedProperties,
                this.arrayDefinition,
                this.arrayDefinitionOptions));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.connection.ConfigurationProperty#getRelation() relation}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConfigurationProperty withRelation(
            PropertyRelation... elements) {
        List<PropertyRelation> newValue = createUnmodifiableList(false,
                createSafeList(Arrays.asList(elements), true, false));
        return validate(new ImmutableConfigurationProperty(
                this,
                this.tags,
                this.order,
                this.componentProperty,
                this.connectorValue,
                this.controlHint,
                this.defaultValue,
                this.deprecated,
                this.description,
                this.displayName,
                this.getEnum,
                this.dataList,
                this.generator,
                this.group,
                this.javaType,
                this.kind,
                this.label,
                this.labelHint,
                this.placeholder,
                this.raw,
                newValue,
                this.required,
                this.secret,
                this.type,
                this.multiple,
                this.extendedProperties,
                this.arrayDefinition,
                this.arrayDefinitionOptions));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.connection.ConfigurationProperty#getRelation() relation}.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of relation elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConfigurationProperty withRelation(
            Iterable<? extends PropertyRelation> elements) {
        if (this.relation == elements) {
            return this;
        }
        List<PropertyRelation> newValue = createUnmodifiableList(false,
                createSafeList(elements, true, false));
        return validate(new ImmutableConfigurationProperty(
                this,
                this.tags,
                this.order,
                this.componentProperty,
                this.connectorValue,
                this.controlHint,
                this.defaultValue,
                this.deprecated,
                this.description,
                this.displayName,
                this.getEnum,
                this.dataList,
                this.generator,
                this.group,
                this.javaType,
                this.kind,
                this.label,
                this.labelHint,
                this.placeholder,
                this.raw,
                newValue,
                this.required,
                this.secret,
                this.type,
                this.multiple,
                this.extendedProperties,
                this.arrayDefinition,
                this.arrayDefinitionOptions));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConfigurationProperty#getRequired() required} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for required (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConfigurationProperty withRequired(Boolean value) {
        if (Objects.equals(this.required, value)) {
            return this;
        }
        return validate(new ImmutableConfigurationProperty(
                this,
                this.tags,
                this.order,
                this.componentProperty,
                this.connectorValue,
                this.controlHint,
                this.defaultValue,
                this.deprecated,
                this.description,
                this.displayName,
                this.getEnum,
                this.dataList,
                this.generator,
                this.group,
                this.javaType,
                this.kind,
                this.label,
                this.labelHint,
                this.placeholder,
                this.raw,
                this.relation,
                value,
                this.secret,
                this.type,
                this.multiple,
                this.extendedProperties,
                this.arrayDefinition,
                this.arrayDefinitionOptions));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConfigurationProperty#getSecret() secret} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for secret (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConfigurationProperty withSecret(Boolean value) {
        if (Objects.equals(this.secret, value)) {
            return this;
        }
        return validate(new ImmutableConfigurationProperty(
                this,
                this.tags,
                this.order,
                this.componentProperty,
                this.connectorValue,
                this.controlHint,
                this.defaultValue,
                this.deprecated,
                this.description,
                this.displayName,
                this.getEnum,
                this.dataList,
                this.generator,
                this.group,
                this.javaType,
                this.kind,
                this.label,
                this.labelHint,
                this.placeholder,
                this.raw,
                this.relation,
                this.required,
                value,
                this.type,
                this.multiple,
                this.extendedProperties,
                this.arrayDefinition,
                this.arrayDefinitionOptions));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConfigurationProperty#getType() type} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for type (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConfigurationProperty withType(String value) {
        if (Objects.equals(this.type, value)) {
            return this;
        }
        return validate(new ImmutableConfigurationProperty(
                this,
                this.tags,
                this.order,
                this.componentProperty,
                this.connectorValue,
                this.controlHint,
                this.defaultValue,
                this.deprecated,
                this.description,
                this.displayName,
                this.getEnum,
                this.dataList,
                this.generator,
                this.group,
                this.javaType,
                this.kind,
                this.label,
                this.labelHint,
                this.placeholder,
                this.raw,
                this.relation,
                this.required,
                this.secret,
                value,
                this.multiple,
                this.extendedProperties,
                this.arrayDefinition,
                this.arrayDefinitionOptions));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConfigurationProperty#getMultiple() multiple} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for multiple (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConfigurationProperty withMultiple(Boolean value) {
        if (Objects.equals(this.multiple, value)) {
            return this;
        }
        return validate(new ImmutableConfigurationProperty(
                this,
                this.tags,
                this.order,
                this.componentProperty,
                this.connectorValue,
                this.controlHint,
                this.defaultValue,
                this.deprecated,
                this.description,
                this.displayName,
                this.getEnum,
                this.dataList,
                this.generator,
                this.group,
                this.javaType,
                this.kind,
                this.label,
                this.labelHint,
                this.placeholder,
                this.raw,
                this.relation,
                this.required,
                this.secret,
                this.type,
                value,
                this.extendedProperties,
                this.arrayDefinition,
                this.arrayDefinitionOptions));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.connection.ConfigurationProperty#getExtendedProperties() extendedProperties} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for extendedProperties (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableConfigurationProperty withExtendedProperties(
            String value) {
        if (Objects.equals(this.extendedProperties, value)) {
            return this;
        }
        return validate(new ImmutableConfigurationProperty(
                this,
                this.tags,
                this.order,
                this.componentProperty,
                this.connectorValue,
                this.controlHint,
                this.defaultValue,
                this.deprecated,
                this.description,
                this.displayName,
                this.getEnum,
                this.dataList,
                this.generator,
                this.group,
                this.javaType,
                this.kind,
                this.label,
                this.labelHint,
                this.placeholder,
                this.raw,
                this.relation,
                this.required,
                this.secret,
                this.type,
                this.multiple,
                value,
                this.arrayDefinition,
                this.arrayDefinitionOptions));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.connection.ConfigurationProperty#getArrayDefinition() arrayDefinition} attribute.
     *
     * @param value The value for arrayDefinition
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConfigurationProperty withArrayDefinition(
            ArrayDefinition value) {
        ConfigurationProperty.ArrayDefinition newValue =
                Objects.requireNonNull(value, "arrayDefinition");
        if (this.arrayDefinition == newValue) {
            return this;
        }
        return validate(new ImmutableConfigurationProperty(
                this,
                this.tags,
                this.order,
                this.componentProperty,
                this.connectorValue,
                this.controlHint,
                this.defaultValue,
                this.deprecated,
                this.description,
                this.displayName,
                this.getEnum,
                this.dataList,
                this.generator,
                this.group,
                this.javaType,
                this.kind,
                this.label,
                this.labelHint,
                this.placeholder,
                this.raw,
                this.relation,
                this.required,
                this.secret,
                this.type,
                this.multiple,
                this.extendedProperties,
                newValue,
                this.arrayDefinitionOptions));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.connection.ConfigurationProperty#getArrayDefinition() arrayDefinition} attribute.
     * A shallow reference equality check is used on unboxed optional value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for arrayDefinition
     * @return A modified copy of {@code this} object
     */
    @SuppressWarnings("unchecked") // safe covariant cast
    public final ImmutableConfigurationProperty withArrayDefinition(
            Optional<? extends ArrayDefinition> optional) {
        ConfigurationProperty.ArrayDefinition value = optional.orElse(null);
        if (this.arrayDefinition == value) {
            return this;
        }
        return validate(new ImmutableConfigurationProperty(
                this,
                this.tags,
                this.order,
                this.componentProperty,
                this.connectorValue,
                this.controlHint,
                this.defaultValue,
                this.deprecated,
                this.description,
                this.displayName,
                this.getEnum,
                this.dataList,
                this.generator,
                this.group,
                this.javaType,
                this.kind,
                this.label,
                this.labelHint,
                this.placeholder,
                this.raw,
                this.relation,
                this.required,
                this.secret,
                this.type,
                this.multiple,
                this.extendedProperties,
                value,
                this.arrayDefinitionOptions));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.connection.ConfigurationProperty#getArrayDefinitionOptions() arrayDefinitionOptions} attribute.
     *
     * @param value The value for arrayDefinitionOptions
     * @return A modified copy of {@code this} object
     */
    public final ImmutableConfigurationProperty withArrayDefinitionOptions(
            ArrayDefinitionOptions value) {
        ConfigurationProperty.ArrayDefinitionOptions newValue =
                Objects.requireNonNull(value, "arrayDefinitionOptions");
        if (this.arrayDefinitionOptions == newValue) {
            return this;
        }
        return validate(new ImmutableConfigurationProperty(
                this,
                this.tags,
                this.order,
                this.componentProperty,
                this.connectorValue,
                this.controlHint,
                this.defaultValue,
                this.deprecated,
                this.description,
                this.displayName,
                this.getEnum,
                this.dataList,
                this.generator,
                this.group,
                this.javaType,
                this.kind,
                this.label,
                this.labelHint,
                this.placeholder,
                this.raw,
                this.relation,
                this.required,
                this.secret,
                this.type,
                this.multiple,
                this.extendedProperties,
                this.arrayDefinition,
                newValue));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.connection.ConfigurationProperty#getArrayDefinitionOptions() arrayDefinitionOptions} attribute.
     * A shallow reference equality check is used on unboxed optional value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for arrayDefinitionOptions
     * @return A modified copy of {@code this} object
     */
    @SuppressWarnings("unchecked") // safe covariant cast
    public final ImmutableConfigurationProperty withArrayDefinitionOptions(
            Optional<? extends ArrayDefinitionOptions> optional) {
        ConfigurationProperty.ArrayDefinitionOptions value =
                optional.orElse(null);
        if (this.arrayDefinitionOptions == value) {
            return this;
        }
        return validate(new ImmutableConfigurationProperty(
                this,
                this.tags,
                this.order,
                this.componentProperty,
                this.connectorValue,
                this.controlHint,
                this.defaultValue,
                this.deprecated,
                this.description,
                this.displayName,
                this.getEnum,
                this.dataList,
                this.generator,
                this.group,
                this.javaType,
                this.kind,
                this.label,
                this.labelHint,
                this.placeholder,
                this.raw,
                this.relation,
                this.required,
                this.secret,
                this.type,
                this.multiple,
                this.extendedProperties,
                this.arrayDefinition,
                value));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableConfigurationProperty} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableConfigurationProperty
                && equalTo((ImmutableConfigurationProperty) another);
    }

    private boolean equalTo(ImmutableConfigurationProperty another) {
        return tags.equals(another.tags)
                && Objects.equals(order, another.order)
                && Objects.equals(componentProperty, another.componentProperty)
                && Objects.equals(connectorValue, another.connectorValue)
                && Objects.equals(controlHint, another.controlHint)
                && Objects.equals(defaultValue, another.defaultValue)
                && Objects.equals(deprecated, another.deprecated)
                && Objects.equals(description, another.description)
                && Objects.equals(displayName, another.displayName)
                && getEnum.equals(another.getEnum)
                && dataList.equals(another.dataList)
                && Objects.equals(generator, another.generator)
                && Objects.equals(group, another.group)
                && Objects.equals(javaType, another.javaType)
                && Objects.equals(kind, another.kind)
                && Objects.equals(label, another.label)
                && Objects.equals(labelHint, another.labelHint)
                && Objects.equals(placeholder, another.placeholder)
                && Objects.equals(raw, another.raw)
                && relation.equals(another.relation)
                && Objects.equals(required, another.required)
                && Objects.equals(secret, another.secret)
                && Objects.equals(type, another.type)
                && Objects.equals(multiple, another.multiple)
                && Objects.equals(extendedProperties,
                another.extendedProperties)
                && Objects.equals(arrayDefinition, another.arrayDefinition)
                && Objects.equals(arrayDefinitionOptions,
                another.arrayDefinitionOptions);
    }

    /**
     * Computes a hash code from attributes: {@code tags}, {@code order}, {@code componentProperty}, {@code connectorValue}, {@code controlHint}, {@code defaultValue}, {@code deprecated}, {@code description}, {@code displayName}, {@code getEnum}, {@code dataList}, {@code generator}, {@code group}, {@code javaType}, {@code kind}, {@code label}, {@code labelHint}, {@code placeholder}, {@code raw}, {@code relation}, {@code required}, {@code secret}, {@code type}, {@code multiple}, {@code extendedProperties}, {@code arrayDefinition}, {@code arrayDefinitionOptions}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + tags.hashCode();
        h += (h << 5) + Objects.hashCode(order);
        h += (h << 5) + Objects.hashCode(componentProperty);
        h += (h << 5) + Objects.hashCode(connectorValue);
        h += (h << 5) + Objects.hashCode(controlHint);
        h += (h << 5) + Objects.hashCode(defaultValue);
        h += (h << 5) + Objects.hashCode(deprecated);
        h += (h << 5) + Objects.hashCode(description);
        h += (h << 5) + Objects.hashCode(displayName);
        h += (h << 5) + getEnum.hashCode();
        h += (h << 5) + dataList.hashCode();
        h += (h << 5) + Objects.hashCode(generator);
        h += (h << 5) + Objects.hashCode(group);
        h += (h << 5) + Objects.hashCode(javaType);
        h += (h << 5) + Objects.hashCode(kind);
        h += (h << 5) + Objects.hashCode(label);
        h += (h << 5) + Objects.hashCode(labelHint);
        h += (h << 5) + Objects.hashCode(placeholder);
        h += (h << 5) + Objects.hashCode(raw);
        h += (h << 5) + relation.hashCode();
        h += (h << 5) + Objects.hashCode(required);
        h += (h << 5) + Objects.hashCode(secret);
        h += (h << 5) + Objects.hashCode(type);
        h += (h << 5) + Objects.hashCode(multiple);
        h += (h << 5) + Objects.hashCode(extendedProperties);
        h += (h << 5) + Objects.hashCode(arrayDefinition);
        h += (h << 5) + Objects.hashCode(arrayDefinitionOptions);
        return h;
    }

    /**
     * Prints the immutable value {@code ConfigurationProperty} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("ConfigurationProperty{");
        builder.append("tags=").append(tags);
        if (order != null) {
            builder.append(", ");
            builder.append("order=").append(order);
        }
        if (componentProperty != null) {
            builder.append(", ");
            builder.append("componentProperty=").append(componentProperty);
        }
        if (connectorValue != null) {
            builder.append(", ");
            builder.append("connectorValue=").append(connectorValue);
        }
        if (controlHint != null) {
            builder.append(", ");
            builder.append("controlHint=").append(controlHint);
        }
        if (defaultValue != null) {
            builder.append(", ");
            builder.append("defaultValue=").append(defaultValue);
        }
        if (deprecated != null) {
            builder.append(", ");
            builder.append("deprecated=").append(deprecated);
        }
        if (description != null) {
            builder.append(", ");
            builder.append("description=").append(description);
        }
        if (displayName != null) {
            builder.append(", ");
            builder.append("displayName=").append(displayName);
        }
        builder.append(", ");
        builder.append("enum=").append(getEnum);
        builder.append(", ");
        builder.append("dataList=").append(dataList);
        if (generator != null) {
            builder.append(", ");
            builder.append("generator=").append(generator);
        }
        if (group != null) {
            builder.append(", ");
            builder.append("group=").append(group);
        }
        if (javaType != null) {
            builder.append(", ");
            builder.append("javaType=").append(javaType);
        }
        if (kind != null) {
            builder.append(", ");
            builder.append("kind=").append(kind);
        }
        if (label != null) {
            builder.append(", ");
            builder.append("label=").append(label);
        }
        if (labelHint != null) {
            builder.append(", ");
            builder.append("labelHint=").append(labelHint);
        }
        if (placeholder != null) {
            builder.append(", ");
            builder.append("placeholder=").append(placeholder);
        }
        if (raw != null) {
            builder.append(", ");
            builder.append("raw=").append(raw);
        }
        builder.append(", ");
        builder.append("relation=").append(relation);
        if (required != null) {
            builder.append(", ");
            builder.append("required=").append(required);
        }
        if (secret != null) {
            builder.append(", ");
            builder.append("secret=").append(secret);
        }
        if (type != null) {
            builder.append(", ");
            builder.append("type=").append(type);
        }
        if (multiple != null) {
            builder.append(", ");
            builder.append("multiple=").append(multiple);
        }
        if (extendedProperties != null) {
            builder.append(", ");
            builder.append("extendedProperties=").append(extendedProperties);
        }
        if (arrayDefinition != null) {
            builder.append(", ");
            builder.append("arrayDefinition=").append(arrayDefinition);
        }
        if (arrayDefinitionOptions != null) {
            builder.append(", ");
            builder.append("arrayDefinitionOptions=")
                    .append(arrayDefinitionOptions);
        }
        return builder.append("}").toString();
    }

    private Object readResolve() throws ObjectStreamException {
        return validate(this);
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.connection.ConfigurationProperty ConfigurationProperty}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private List<String> tags = new ArrayList<String>();
        private Integer order;
        private Boolean componentProperty;
        private String connectorValue;
        private String controlHint;
        private String defaultValue;
        private Boolean deprecated;
        private String description;
        private String displayName;
        private List<PropertyValue> getEnum = new ArrayList<PropertyValue>();
        private List<String> dataList = new ArrayList<String>();
        private String generator;
        private String group;
        private String javaType;
        private String kind;
        private String label;
        private String labelHint;
        private String placeholder;
        private Boolean raw;
        private List<PropertyRelation> relation =
                new ArrayList<PropertyRelation>();
        private Boolean required;
        private Boolean secret;
        private String type;
        private Boolean multiple;
        private String extendedProperties;
        private ConfigurationProperty.ArrayDefinition arrayDefinition;
        private ConfigurationProperty.ArrayDefinitionOptions
                arrayDefinitionOptions;

        /**
         * Creates a builder for {@link io.syndesis.common.model.connection.ConfigurationProperty ConfigurationProperty} instances.
         * <pre>
         * new ConfigurationProperty.Builder()
         *    .addTag|addAllTags(String) // {@link io.syndesis.common.model.connection.ConfigurationProperty#getTags() tags} elements
         *    .order(int) // optional {@link io.syndesis.common.model.connection.ConfigurationProperty#getOrder() order}
         *    .componentProperty(Boolean | null) // nullable {@link io.syndesis.common.model.connection.ConfigurationProperty#getComponentProperty() componentProperty}
         *    .connectorValue(String) // optional {@link io.syndesis.common.model.connection.ConfigurationProperty#getConnectorValue() connectorValue}
         *    .controlHint(String | null) // nullable {@link io.syndesis.common.model.connection.ConfigurationProperty#getControlHint() controlHint}
         *    .defaultValue(String | null) // nullable {@link io.syndesis.common.model.connection.ConfigurationProperty#getDefaultValue() defaultValue}
         *    .deprecated(Boolean | null) // nullable {@link io.syndesis.common.model.connection.ConfigurationProperty#getDeprecated() deprecated}
         *    .description(String | null) // nullable {@link io.syndesis.common.model.connection.ConfigurationProperty#getDescription() description}
         *    .displayName(String | null) // nullable {@link io.syndesis.common.model.connection.ConfigurationProperty#getDisplayName() displayName}
         *    .addEnum|addAllEnum(io.syndesis.common.io.syndesis.common.model.connection.ConfigurationProperty.PropertyValue) // {@link io.syndesis.common.model.connection.ConfigurationProperty#getEnum() enum} elements
         *    .addDataList|addAllDataList(String) // {@link io.syndesis.common.model.connection.ConfigurationProperty#getDataList() dataList} elements
         *    .generator(String | null) // nullable {@link io.syndesis.common.model.connection.ConfigurationProperty#getGenerator() generator}
         *    .group(String | null) // nullable {@link io.syndesis.common.model.connection.ConfigurationProperty#getGroup() group}
         *    .javaType(String | null) // nullable {@link io.syndesis.common.model.connection.ConfigurationProperty#getJavaType() javaType}
         *    .kind(String | null) // nullable {@link io.syndesis.common.model.connection.ConfigurationProperty#getKind() kind}
         *    .label(String | null) // nullable {@link io.syndesis.common.model.connection.ConfigurationProperty#getLabel() label}
         *    .labelHint(String | null) // nullable {@link io.syndesis.common.model.connection.ConfigurationProperty#getLabelHint() labelHint}
         *    .placeholder(String | null) // nullable {@link io.syndesis.common.model.connection.ConfigurationProperty#getPlaceholder() placeholder}
         *    .raw(Boolean | null) // nullable {@link io.syndesis.common.model.connection.ConfigurationProperty#getRaw() raw}
         *    .addRelation|addAllRelation(io.syndesis.common.io.syndesis.common.model.connection.PropertyRelation) // {@link io.syndesis.common.model.connection.ConfigurationProperty#getRelation() relation} elements
         *    .required(Boolean | null) // nullable {@link io.syndesis.common.model.connection.ConfigurationProperty#getRequired() required}
         *    .secret(Boolean | null) // nullable {@link io.syndesis.common.model.connection.ConfigurationProperty#getSecret() secret}
         *    .type(String | null) // nullable {@link io.syndesis.common.model.connection.ConfigurationProperty#getType() type}
         *    .multiple(Boolean | null) // nullable {@link io.syndesis.common.model.connection.ConfigurationProperty#getMultiple() multiple}
         *    .extendedProperties(String | null) // nullable {@link io.syndesis.common.model.connection.ConfigurationProperty#getExtendedProperties() extendedProperties}
         *    .arrayDefinition(io.syndesis.common.io.syndesis.common.model.connection.ConfigurationProperty.ArrayDefinition) // optional {@link io.syndesis.common.model.connection.ConfigurationProperty#getArrayDefinition() arrayDefinition}
         *    .arrayDefinitionOptions(io.syndesis.common.io.syndesis.common.model.connection.ConfigurationProperty.ArrayDefinitionOptions) // optional {@link io.syndesis.common.model.connection.ConfigurationProperty#getArrayDefinitionOptions() arrayDefinitionOptions}
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof ConfigurationProperty.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new ConfigurationProperty.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.Ordered} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConfigurationProperty.Builder createFrom(
                Ordered instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (ConfigurationProperty.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.connection.ConfigurationProperty} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConfigurationProperty.Builder createFrom(
                ConfigurationProperty instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (ConfigurationProperty.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithTags} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConfigurationProperty.Builder createFrom(
                WithTags instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (ConfigurationProperty.Builder) this;
        }

        private void from(Object object) {
            if (object instanceof Ordered) {
                Ordered instance = (Ordered) object;
                Optional<Integer> orderOptional = instance.getOrder();
                if (orderOptional.isPresent()) {
                    order(orderOptional);
                }
            }
            if (object instanceof ConfigurationProperty) {
                ConfigurationProperty instance = (ConfigurationProperty) object;
                String defaultValueValue = instance.getDefaultValue();
                if (defaultValueValue != null) {
                    defaultValue(defaultValueValue);
                }
                String displayNameValue = instance.getDisplayName();
                if (displayNameValue != null) {
                    displayName(displayNameValue);
                }
                Boolean deprecatedValue = instance.getDeprecated();
                if (deprecatedValue != null) {
                    deprecated(deprecatedValue);
                }
                Optional<String> connectorValueOptional =
                        instance.getConnectorValue();
                if (connectorValueOptional.isPresent()) {
                    connectorValue(connectorValueOptional);
                }
                String descriptionValue = instance.getDescription();
                if (descriptionValue != null) {
                    description(descriptionValue);
                }
                addAllEnum(instance.getEnum());
                String generatorValue = instance.getGenerator();
                if (generatorValue != null) {
                    generator(generatorValue);
                }
                Boolean secretValue = instance.getSecret();
                if (secretValue != null) {
                    secret(secretValue);
                }
                String typeValue = instance.getType();
                if (typeValue != null) {
                    type(typeValue);
                }
                Boolean requiredValue = instance.getRequired();
                if (requiredValue != null) {
                    required(requiredValue);
                }
                Boolean componentPropertyValue =
                        instance.getComponentProperty();
                if (componentPropertyValue != null) {
                    componentProperty(componentPropertyValue);
                }
                addAllRelation(instance.getRelation());
                Optional<ArrayDefinition> arrayDefinitionOptional =
                        instance.getArrayDefinition();
                if (arrayDefinitionOptional.isPresent()) {
                    arrayDefinition(arrayDefinitionOptional);
                }
                String extendedPropertiesValue =
                        instance.getExtendedProperties();
                if (extendedPropertiesValue != null) {
                    extendedProperties(extendedPropertiesValue);
                }
                String labelHintValue = instance.getLabelHint();
                if (labelHintValue != null) {
                    labelHint(labelHintValue);
                }
                String placeholderValue = instance.getPlaceholder();
                if (placeholderValue != null) {
                    placeholder(placeholderValue);
                }
                String groupValue = instance.getGroup();
                if (groupValue != null) {
                    group(groupValue);
                }
                String kindValue = instance.getKind();
                if (kindValue != null) {
                    kind(kindValue);
                }
                Boolean multipleValue = instance.getMultiple();
                if (multipleValue != null) {
                    multiple(multipleValue);
                }
                Boolean rawValue = instance.getRaw();
                if (rawValue != null) {
                    raw(rawValue);
                }
                String labelValue = instance.getLabel();
                if (labelValue != null) {
                    label(labelValue);
                }
                String javaTypeValue = instance.getJavaType();
                if (javaTypeValue != null) {
                    javaType(javaTypeValue);
                }
                Optional<ArrayDefinitionOptions>
                        arrayDefinitionOptionsOptional =
                        instance.getArrayDefinitionOptions();
                if (arrayDefinitionOptionsOptional.isPresent()) {
                    arrayDefinitionOptions(arrayDefinitionOptionsOptional);
                }
                addAllDataList(instance.getDataList());
                String controlHintValue = instance.getControlHint();
                if (controlHintValue != null) {
                    controlHint(controlHintValue);
                }
            }
            if (object instanceof WithTags) {
                WithTags instance = (WithTags) object;
                addAllTags(instance.getTags());
            }
        }

        /**
         * Adds one element to {@link io.syndesis.common.model.connection.ConfigurationProperty#getTags() tags} sortedSet.
         *
         * @param element A tags element
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConfigurationProperty.Builder addTag(String element) {
            if (element != null) {
                this.tags.add(element);
            }
            return (ConfigurationProperty.Builder) this;
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.connection.ConfigurationProperty#getTags() tags} sortedSet.
         *
         * @param elements An array of tags elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConfigurationProperty.Builder addTags(String... elements) {
            for (String element : elements) {
                if (element != null) {
                    this.tags.add(element);
                }
            }
            return (ConfigurationProperty.Builder) this;
        }


        /**
         * Sets or replaces all elements for {@link io.syndesis.common.model.connection.ConfigurationProperty#getTags() tags} sortedSet.
         *
         * @param elements An iterable of tags elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("tags")
        public final ConfigurationProperty.Builder tags(
                Iterable<String> elements) {
            this.tags.clear();
            return addAllTags(elements);
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.connection.ConfigurationProperty#getTags() tags} sortedSet.
         *
         * @param elements An iterable of tags elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConfigurationProperty.Builder addAllTags(
                Iterable<String> elements) {
            for (String element : elements) {
                if (element != null) {
                    this.tags.add(element);
                }
            }
            return (ConfigurationProperty.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.ConfigurationProperty#getOrder() order} to order.
         *
         * @param order The value for order
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final ConfigurationProperty.Builder order(int order) {
            this.order = order;
            return (ConfigurationProperty.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.ConfigurationProperty#getOrder() order} to order.
         *
         * @param order The value for order
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("order")
        public final ConfigurationProperty.Builder order(Optional<Integer> order) {
            this.order = order.isPresent() ? order.get() : null;
            return (ConfigurationProperty.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConfigurationProperty#getComponentProperty() componentProperty} attribute.
         *
         * @param componentProperty The value for componentProperty (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("componentProperty")
        public final ConfigurationProperty.Builder componentProperty(
                Boolean componentProperty) {
            this.componentProperty = componentProperty;
            return (ConfigurationProperty.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.ConfigurationProperty#getConnectorValue() connectorValue} to connectorValue.
         *
         * @param connectorValue The value for connectorValue
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final ConfigurationProperty.Builder connectorValue(
                String connectorValue) {
            this.connectorValue =
                    Objects.requireNonNull(connectorValue, "connectorValue");
            return (ConfigurationProperty.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.ConfigurationProperty#getConnectorValue() connectorValue} to connectorValue.
         *
         * @param connectorValue The value for connectorValue
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("connectorValue")
        public final ConfigurationProperty.Builder connectorValue(
                Optional<String> connectorValue) {
            this.connectorValue = connectorValue.orElse(null);
            return (ConfigurationProperty.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConfigurationProperty#getControlHint() controlHint} attribute.
         *
         * @param controlHint The value for controlHint (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("controlHint")
        public final ConfigurationProperty.Builder controlHint(
                String controlHint) {
            this.controlHint = controlHint;
            return (ConfigurationProperty.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConfigurationProperty#getDefaultValue() defaultValue} attribute.
         *
         * @param defaultValue The value for defaultValue (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("defaultValue")
        public final ConfigurationProperty.Builder defaultValue(
                String defaultValue) {
            this.defaultValue = defaultValue;
            return (ConfigurationProperty.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConfigurationProperty#getDeprecated() deprecated} attribute.
         *
         * @param deprecated The value for deprecated (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("deprecated")
        public final ConfigurationProperty.Builder deprecated(
                Boolean deprecated) {
            this.deprecated = deprecated;
            return (ConfigurationProperty.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConfigurationProperty#getDescription() description} attribute.
         *
         * @param description The value for description (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("description")
        public final ConfigurationProperty.Builder description(
                String description) {
            this.description = description;
            return (ConfigurationProperty.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConfigurationProperty#getDisplayName() displayName} attribute.
         *
         * @param displayName The value for displayName (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("displayName")
        public final ConfigurationProperty.Builder displayName(
                String displayName) {
            this.displayName = displayName;
            return (ConfigurationProperty.Builder) this;
        }

        /**
         * Adds one element to {@link io.syndesis.common.model.connection.ConfigurationProperty#getEnum() enum} list.
         *
         * @param element A getEnum element
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConfigurationProperty.Builder addEnum(
                PropertyValue element) {
            this.getEnum.add(
                    Objects.requireNonNull(element, "getEnum element"));
            return (ConfigurationProperty.Builder) this;
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.connection.ConfigurationProperty#getEnum() enum} list.
         *
         * @param elements An array of getEnum elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConfigurationProperty.Builder addEnum(
                PropertyValue... elements) {
            for (PropertyValue element : elements) {
                this.getEnum.add(
                        Objects.requireNonNull(element, "getEnum element"));
            }
            return (ConfigurationProperty.Builder) this;
        }


        /**
         * Sets or replaces all elements for {@link io.syndesis.common.model.connection.ConfigurationProperty#getEnum() enum} list.
         *
         * @param elements An iterable of getEnum elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("enum")
        public final ConfigurationProperty.Builder getEnum(
                Iterable<? extends PropertyValue> elements) {
            this.getEnum.clear();
            return addAllEnum(elements);
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.connection.ConfigurationProperty#getEnum() enum} list.
         *
         * @param elements An iterable of getEnum elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConfigurationProperty.Builder addAllEnum(
                Iterable<? extends PropertyValue> elements) {
            for (PropertyValue element : elements) {
                this.getEnum.add(
                        Objects.requireNonNull(element, "getEnum element"));
            }
            return (ConfigurationProperty.Builder) this;
        }

        /**
         * Adds one element to {@link io.syndesis.common.model.connection.ConfigurationProperty#getDataList() dataList} list.
         *
         * @param element A dataList element
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConfigurationProperty.Builder addDataList(String element) {
            this.dataList.add(
                    Objects.requireNonNull(element, "dataList element"));
            return (ConfigurationProperty.Builder) this;
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.connection.ConfigurationProperty#getDataList() dataList} list.
         *
         * @param elements An array of dataList elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConfigurationProperty.Builder addDataList(
                String... elements) {
            for (String element : elements) {
                this.dataList.add(
                        Objects.requireNonNull(element, "dataList element"));
            }
            return (ConfigurationProperty.Builder) this;
        }


        /**
         * Sets or replaces all elements for {@link io.syndesis.common.model.connection.ConfigurationProperty#getDataList() dataList} list.
         *
         * @param elements An iterable of dataList elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("dataList")
        public final ConfigurationProperty.Builder dataList(
                Iterable<String> elements) {
            this.dataList.clear();
            return addAllDataList(elements);
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.connection.ConfigurationProperty#getDataList() dataList} list.
         *
         * @param elements An iterable of dataList elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConfigurationProperty.Builder addAllDataList(
                Iterable<String> elements) {
            for (String element : elements) {
                this.dataList.add(
                        Objects.requireNonNull(element, "dataList element"));
            }
            return (ConfigurationProperty.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConfigurationProperty#getGenerator() generator} attribute.
         *
         * @param generator The value for generator (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("generator")
        public final ConfigurationProperty.Builder generator(String generator) {
            this.generator = generator;
            return (ConfigurationProperty.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConfigurationProperty#getGroup() group} attribute.
         *
         * @param group The value for group (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("group")
        public final ConfigurationProperty.Builder group(String group) {
            this.group = group;
            return (ConfigurationProperty.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConfigurationProperty#getJavaType() javaType} attribute.
         *
         * @param javaType The value for javaType (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("javaType")
        public final ConfigurationProperty.Builder javaType(String javaType) {
            this.javaType = javaType;
            return (ConfigurationProperty.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConfigurationProperty#getKind() kind} attribute.
         *
         * @param kind The value for kind (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("kind")
        public final ConfigurationProperty.Builder kind(String kind) {
            this.kind = kind;
            return (ConfigurationProperty.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConfigurationProperty#getLabel() label} attribute.
         *
         * @param label The value for label (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("label")
        public final ConfigurationProperty.Builder label(String label) {
            this.label = label;
            return (ConfigurationProperty.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConfigurationProperty#getLabelHint() labelHint} attribute.
         *
         * @param labelHint The value for labelHint (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("labelHint")
        public final ConfigurationProperty.Builder labelHint(String labelHint) {
            this.labelHint = labelHint;
            return (ConfigurationProperty.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConfigurationProperty#getPlaceholder() placeholder} attribute.
         *
         * @param placeholder The value for placeholder (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("placeholder")
        public final ConfigurationProperty.Builder placeholder(
                String placeholder) {
            this.placeholder = placeholder;
            return (ConfigurationProperty.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConfigurationProperty#getRaw() raw} attribute.
         *
         * @param raw The value for raw (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("raw")
        public final ConfigurationProperty.Builder raw(Boolean raw) {
            this.raw = raw;
            return (ConfigurationProperty.Builder) this;
        }

        /**
         * Adds one element to {@link io.syndesis.common.model.connection.ConfigurationProperty#getRelation() relation} list.
         *
         * @param element A relation element
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConfigurationProperty.Builder addRelation(
                PropertyRelation element) {
            this.relation.add(
                    Objects.requireNonNull(element, "relation element"));
            return (ConfigurationProperty.Builder) this;
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.connection.ConfigurationProperty#getRelation() relation} list.
         *
         * @param elements An array of relation elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConfigurationProperty.Builder addRelation(
                PropertyRelation... elements) {
            for (PropertyRelation element : elements) {
                this.relation.add(
                        Objects.requireNonNull(element, "relation element"));
            }
            return (ConfigurationProperty.Builder) this;
        }


        /**
         * Sets or replaces all elements for {@link io.syndesis.common.model.connection.ConfigurationProperty#getRelation() relation} list.
         *
         * @param elements An iterable of relation elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("relation")
        public final ConfigurationProperty.Builder relation(
                Iterable<? extends PropertyRelation> elements) {
            this.relation.clear();
            return addAllRelation(elements);
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.connection.ConfigurationProperty#getRelation() relation} list.
         *
         * @param elements An iterable of relation elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final ConfigurationProperty.Builder addAllRelation(
                Iterable<? extends PropertyRelation> elements) {
            for (PropertyRelation element : elements) {
                this.relation.add(
                        Objects.requireNonNull(element, "relation element"));
            }
            return (ConfigurationProperty.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConfigurationProperty#getRequired() required} attribute.
         *
         * @param required The value for required (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("required")
        public final ConfigurationProperty.Builder required(Boolean required) {
            this.required = required;
            return (ConfigurationProperty.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConfigurationProperty#getSecret() secret} attribute.
         *
         * @param secret The value for secret (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("secret")
        public final ConfigurationProperty.Builder secret(Boolean secret) {
            this.secret = secret;
            return (ConfigurationProperty.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConfigurationProperty#getType() type} attribute.
         *
         * @param type The value for type (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("type")
        public final ConfigurationProperty.Builder type(String type) {
            this.type = type;
            return (ConfigurationProperty.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConfigurationProperty#getMultiple() multiple} attribute.
         *
         * @param multiple The value for multiple (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("multiple")
        public final ConfigurationProperty.Builder multiple(Boolean multiple) {
            this.multiple = multiple;
            return (ConfigurationProperty.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.connection.ConfigurationProperty#getExtendedProperties() extendedProperties} attribute.
         *
         * @param extendedProperties The value for extendedProperties (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("extendedProperties")
        public final ConfigurationProperty.Builder extendedProperties(
                String extendedProperties) {
            this.extendedProperties = extendedProperties;
            return (ConfigurationProperty.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.ConfigurationProperty#getArrayDefinition() arrayDefinition} to arrayDefinition.
         *
         * @param arrayDefinition The value for arrayDefinition
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final ConfigurationProperty.Builder arrayDefinition(
                ArrayDefinition arrayDefinition) {
            this.arrayDefinition =
                    Objects.requireNonNull(arrayDefinition, "arrayDefinition");
            return (ConfigurationProperty.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.ConfigurationProperty#getArrayDefinition() arrayDefinition} to arrayDefinition.
         *
         * @param arrayDefinition The value for arrayDefinition
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("arrayDefinition")
        public final ConfigurationProperty.Builder arrayDefinition(
                Optional<? extends ArrayDefinition> arrayDefinition) {
            this.arrayDefinition = arrayDefinition.orElse(null);
            return (ConfigurationProperty.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.ConfigurationProperty#getArrayDefinitionOptions() arrayDefinitionOptions} to arrayDefinitionOptions.
         *
         * @param arrayDefinitionOptions The value for arrayDefinitionOptions
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final ConfigurationProperty.Builder arrayDefinitionOptions(
                ArrayDefinitionOptions arrayDefinitionOptions) {
            this.arrayDefinitionOptions =
                    Objects.requireNonNull(arrayDefinitionOptions,
                            "arrayDefinitionOptions");
            return (ConfigurationProperty.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.connection.ConfigurationProperty#getArrayDefinitionOptions() arrayDefinitionOptions} to arrayDefinitionOptions.
         *
         * @param arrayDefinitionOptions The value for arrayDefinitionOptions
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("arrayDefinitionOptions")
        public final ConfigurationProperty.Builder arrayDefinitionOptions(
                Optional<? extends ArrayDefinitionOptions> arrayDefinitionOptions) {
            this.arrayDefinitionOptions = arrayDefinitionOptions.orElse(null);
            return (ConfigurationProperty.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.connection.ConfigurationProperty ConfigurationProperty}.
         *
         * @return An immutable instance of ConfigurationProperty
         * @throws IllegalStateException if any required attributes are missing
         */
        public ConfigurationProperty build() {
            return ImmutableConfigurationProperty.validate(
                    new ImmutableConfigurationProperty(
                            null,
                            createUnmodifiableSortedSet(false,
                                    createSafeList(tags, false, false)),
                            order,
                            componentProperty,
                            connectorValue,
                            controlHint,
                            defaultValue,
                            deprecated,
                            description,
                            displayName,
                            createUnmodifiableList(true, getEnum),
                            createUnmodifiableList(true, dataList),
                            generator,
                            group,
                            javaType,
                            kind,
                            label,
                            labelHint,
                            placeholder,
                            raw,
                            createUnmodifiableList(true, relation),
                            required,
                            secret,
                            type,
                            multiple,
                            extendedProperties,
                            arrayDefinition,
                            arrayDefinitionOptions));
        }
    }
}
