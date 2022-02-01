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
package io.syndesis.common.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Var;
import io.syndesis.common.model.Violation;
import io.syndesis.common.model.WithConfigurationProperties;
import io.syndesis.common.model.WithConfiguredProperties;
import io.syndesis.common.model.WithName;
import io.syndesis.common.model.action.ActionsSummary;
import io.syndesis.common.model.connection.ConfigurationProperty;

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
 * Immutable implementation of {@link io.syndesis.common.model.api.APISummary}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new APISummary.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableAPISummary.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableAPISummary implements APISummary {
    private final String name;
    private final Map<String, ConfigurationProperty> properties;
    private final Map<String, String> configuredProperties;
    private final List<ActionsSummary> actionsSummary;
    private final String description;
    private final List<Violation> errors;
    private final String icon;
    private final List<Violation> warnings;

    private ImmutableAPISummary(
            String name,
            Map<String, ? extends ConfigurationProperty> properties,
            Map<String, ? extends String> configuredProperties,
            Iterable<? extends ActionsSummary> actionsSummary,
            String description,
            Iterable<? extends Violation> errors,
            Optional<String> icon,
            Iterable<? extends Violation> warnings) {
        this.name = name;
        this.properties = createUnmodifiableMap(true, false, properties);
        this.configuredProperties =
                createUnmodifiableMap(true, false, configuredProperties);
        this.actionsSummary = createUnmodifiableList(false,
                createSafeList(actionsSummary, true, false));
        this.description = description;
        this.errors = createUnmodifiableList(false,
                createSafeList(errors, true, false));
        this.icon = icon.orElse(null);
        this.warnings = createUnmodifiableList(false,
                createSafeList(warnings, true, false));
    }

    private ImmutableAPISummary(Builder builder) {
        this.name = builder.name;
        this.properties =
                createUnmodifiableMap(false, false, builder.properties);
        this.actionsSummary =
                createUnmodifiableList(true, builder.actionsSummary);
        this.description = builder.description;
        this.errors = createUnmodifiableList(true, builder.errors);
        this.icon = builder.icon;
        this.warnings = createUnmodifiableList(true, builder.warnings);
        this.configuredProperties = builder.configuredPropertiesIsSet()
                ? createUnmodifiableMap(false, false,
                builder.configuredProperties)
                : createUnmodifiableMap(true, false,
                APISummary.super.getConfiguredProperties());
    }

    private ImmutableAPISummary(
            ImmutableAPISummary original,
            String name,
            Map<String, ConfigurationProperty> properties,
            Map<String, String> configuredProperties,
            List<ActionsSummary> actionsSummary,
            String description,
            List<Violation> errors,
            String icon,
            List<Violation> warnings) {
        this.name = name;
        this.properties = properties;
        this.configuredProperties = configuredProperties;
        this.actionsSummary = actionsSummary;
        this.description = description;
        this.errors = errors;
        this.icon = icon;
        this.warnings = warnings;
    }

    /**
     * Construct a new immutable {@code APISummary} instance.
     *
     * @param name                 The value for the {@code name} attribute
     * @param properties           The value for the {@code properties} attribute
     * @param configuredProperties The value for the {@code configuredProperties} attribute
     * @param actionsSummary       The value for the {@code actionsSummary} attribute
     * @param description          The value for the {@code description} attribute
     * @param errors               The value for the {@code errors} attribute
     * @param icon                 The value for the {@code icon} attribute
     * @param warnings             The value for the {@code warnings} attribute
     * @return An immutable APISummary instance
     */
    public static APISummary of(String name,
                                Map<String, ConfigurationProperty> properties,
                                Map<String, String> configuredProperties,
                                List<ActionsSummary> actionsSummary,
                                String description, List<Violation> errors,
                                Optional<String> icon,
                                List<Violation> warnings) {
        return of(name, properties, configuredProperties,
                (Iterable<? extends ActionsSummary>) actionsSummary,
                description, (Iterable<? extends Violation>) errors, icon,
                (Iterable<? extends Violation>) warnings);
    }

    /**
     * Construct a new immutable {@code APISummary} instance.
     *
     * @param name                 The value for the {@code name} attribute
     * @param properties           The value for the {@code properties} attribute
     * @param configuredProperties The value for the {@code configuredProperties} attribute
     * @param actionsSummary       The value for the {@code actionsSummary} attribute
     * @param description          The value for the {@code description} attribute
     * @param errors               The value for the {@code errors} attribute
     * @param icon                 The value for the {@code icon} attribute
     * @param warnings             The value for the {@code warnings} attribute
     * @return An immutable APISummary instance
     */
    public static APISummary of(String name,
                                Map<String, ? extends ConfigurationProperty> properties,
                                Map<String, ? extends String> configuredProperties,
                                Iterable<? extends ActionsSummary> actionsSummary,
                                String description,
                                Iterable<? extends Violation> errors,
                                Optional<String> icon,
                                Iterable<? extends Violation> warnings) {
        return validate(
                new ImmutableAPISummary(name, properties, configuredProperties,
                        actionsSummary, description, errors, icon, warnings));
    }

    private static ImmutableAPISummary validate(ImmutableAPISummary instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.api.APISummary} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable APISummary instance
     */
    public static APISummary copyOf(APISummary instance) {
        if (instance instanceof ImmutableAPISummary) {
            return (ImmutableAPISummary) instance;
        }
        return new APISummary.Builder()
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
        return configuredProperties;
    }

    /**
     * @return The value of the {@code actionsSummary} attribute
     */
    @JsonProperty("actionsSummary")
    @Override
    public List<ActionsSummary> getActionsSummary() {
        return actionsSummary;
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
     * @return The value of the {@code errors} attribute
     */
    @JsonProperty("errors")
    @Override
    public List<Violation> getErrors() {
        return errors;
    }

    /**
     * @return The value of the {@code icon} attribute
     */
    @JsonProperty("icon")
    @Override
    public Optional<String> getIcon() {
        return Optional.ofNullable(icon);
    }

    /**
     * @return The value of the {@code warnings} attribute
     */
    @JsonProperty("warnings")
    @Override
    public List<Violation> getWarnings() {
        return warnings;
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.api.APISummary#getName() name} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for name (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableAPISummary withName(String value) {
        if (Objects.equals(this.name, value)) {
            return this;
        }
        return validate(new ImmutableAPISummary(
                this,
                value,
                this.properties,
                this.configuredProperties,
                this.actionsSummary,
                this.description,
                this.errors,
                this.icon,
                this.warnings));
    }

    /**
     * Copy the current immutable object by replacing the {@link io.syndesis.common.model.api.APISummary#getProperties() properties} map with the specified map.
     * Nulls are not permitted as keys or values.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param entries The entries to be added to the properties map
     * @return A modified copy of {@code this} object
     */
    public final ImmutableAPISummary withProperties(
            Map<String, ? extends ConfigurationProperty> entries) {
        if (this.properties == entries) {
            return this;
        }
        Map<String, ConfigurationProperty> newValue =
                createUnmodifiableMap(true, false, entries);
        return validate(new ImmutableAPISummary(
                this,
                this.name,
                newValue,
                this.configuredProperties,
                this.actionsSummary,
                this.description,
                this.errors,
                this.icon,
                this.warnings));
    }

    /**
     * Copy the current immutable object by replacing the {@link io.syndesis.common.model.api.APISummary#getConfiguredProperties() configuredProperties} map with the specified map.
     * Nulls are not permitted as keys or values.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param entries The entries to be added to the configuredProperties map
     * @return A modified copy of {@code this} object
     */
    public final ImmutableAPISummary withConfiguredProperties(
            Map<String, ? extends String> entries) {
        if (this.configuredProperties == entries) {
            return this;
        }
        Map<String, String> newValue =
                createUnmodifiableMap(true, false, entries);
        return validate(new ImmutableAPISummary(
                this,
                this.name,
                this.properties,
                newValue,
                this.actionsSummary,
                this.description,
                this.errors,
                this.icon,
                this.warnings));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.api.APISummary#getActionsSummary() actionsSummary}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableAPISummary withActionsSummary(
            ActionsSummary... elements) {
        List<ActionsSummary> newValue = createUnmodifiableList(false,
                createSafeList(Arrays.asList(elements), true, false));
        return validate(new ImmutableAPISummary(
                this,
                this.name,
                this.properties,
                this.configuredProperties,
                newValue,
                this.description,
                this.errors,
                this.icon,
                this.warnings));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.api.APISummary#getActionsSummary() actionsSummary}.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of actionsSummary elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableAPISummary withActionsSummary(
            Iterable<? extends ActionsSummary> elements) {
        if (this.actionsSummary == elements) {
            return this;
        }
        List<ActionsSummary> newValue = createUnmodifiableList(false,
                createSafeList(elements, true, false));
        return validate(new ImmutableAPISummary(
                this,
                this.name,
                this.properties,
                this.configuredProperties,
                newValue,
                this.description,
                this.errors,
                this.icon,
                this.warnings));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.api.APISummary#getDescription() description} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for description (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableAPISummary withDescription(String value) {
        if (Objects.equals(this.description, value)) {
            return this;
        }
        return validate(new ImmutableAPISummary(
                this,
                this.name,
                this.properties,
                this.configuredProperties,
                this.actionsSummary,
                value,
                this.errors,
                this.icon,
                this.warnings));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.api.APISummary#getErrors() errors}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableAPISummary withErrors(Violation... elements) {
        List<Violation> newValue = createUnmodifiableList(false,
                createSafeList(Arrays.asList(elements), true, false));
        return validate(new ImmutableAPISummary(
                this,
                this.name,
                this.properties,
                this.configuredProperties,
                this.actionsSummary,
                this.description,
                newValue,
                this.icon,
                this.warnings));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.api.APISummary#getErrors() errors}.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of errors elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableAPISummary withErrors(
            Iterable<? extends Violation> elements) {
        if (this.errors == elements) {
            return this;
        }
        List<Violation> newValue = createUnmodifiableList(false,
                createSafeList(elements, true, false));
        return validate(new ImmutableAPISummary(
                this,
                this.name,
                this.properties,
                this.configuredProperties,
                this.actionsSummary,
                this.description,
                newValue,
                this.icon,
                this.warnings));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.api.APISummary#getIcon() icon} attribute.
     *
     * @param value The value for icon
     * @return A modified copy of {@code this} object
     */
    public final ImmutableAPISummary withIcon(String value) {
        String newValue = Objects.requireNonNull(value, "icon");
        if (Objects.equals(this.icon, newValue)) {
            return this;
        }
        return validate(new ImmutableAPISummary(
                this,
                this.name,
                this.properties,
                this.configuredProperties,
                this.actionsSummary,
                this.description,
                this.errors,
                newValue,
                this.warnings));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.api.APISummary#getIcon() icon} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for icon
     * @return A modified copy of {@code this} object
     */
    public final ImmutableAPISummary withIcon(Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.icon, value)) {
            return this;
        }
        return validate(new ImmutableAPISummary(
                this,
                this.name,
                this.properties,
                this.configuredProperties,
                this.actionsSummary,
                this.description,
                this.errors,
                value,
                this.warnings));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.api.APISummary#getWarnings() warnings}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableAPISummary withWarnings(Violation... elements) {
        List<Violation> newValue = createUnmodifiableList(false,
                createSafeList(Arrays.asList(elements), true, false));
        return validate(new ImmutableAPISummary(
                this,
                this.name,
                this.properties,
                this.configuredProperties,
                this.actionsSummary,
                this.description,
                this.errors,
                this.icon,
                newValue));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.api.APISummary#getWarnings() warnings}.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of warnings elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableAPISummary withWarnings(
            Iterable<? extends Violation> elements) {
        if (this.warnings == elements) {
            return this;
        }
        List<Violation> newValue = createUnmodifiableList(false,
                createSafeList(elements, true, false));
        return validate(new ImmutableAPISummary(
                this,
                this.name,
                this.properties,
                this.configuredProperties,
                this.actionsSummary,
                this.description,
                this.errors,
                this.icon,
                newValue));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableAPISummary} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableAPISummary
                && equalTo((ImmutableAPISummary) another);
    }

    private boolean equalTo(ImmutableAPISummary another) {
        return Objects.equals(name, another.name)
                && properties.equals(another.properties)
                && configuredProperties.equals(another.configuredProperties)
                && actionsSummary.equals(another.actionsSummary)
                && Objects.equals(description, another.description)
                && errors.equals(another.errors)
                && Objects.equals(icon, another.icon)
                && warnings.equals(another.warnings);
    }

    /**
     * Computes a hash code from attributes: {@code name}, {@code properties}, {@code configuredProperties}, {@code actionsSummary}, {@code description}, {@code errors}, {@code icon}, {@code warnings}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(name);
        h += (h << 5) + properties.hashCode();
        h += (h << 5) + configuredProperties.hashCode();
        h += (h << 5) + actionsSummary.hashCode();
        h += (h << 5) + Objects.hashCode(description);
        h += (h << 5) + errors.hashCode();
        h += (h << 5) + Objects.hashCode(icon);
        h += (h << 5) + warnings.hashCode();
        return h;
    }

    /**
     * Prints the immutable value {@code APISummary} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("APISummary{");
        if (name != null) {
            builder.append("name=").append(name);
        }
        if (builder.length() > 11) {
            builder.append(", ");
        }
        builder.append("properties=").append(properties);
        builder.append(", ");
        builder.append("configuredProperties=").append(configuredProperties);
        builder.append(", ");
        builder.append("actionsSummary=").append(actionsSummary);
        if (description != null) {
            builder.append(", ");
            builder.append("description=").append(description);
        }
        builder.append(", ");
        builder.append("errors=").append(errors);
        if (icon != null) {
            builder.append(", ");
            builder.append("icon=").append(icon);
        }
        builder.append(", ");
        builder.append("warnings=").append(warnings);
        return builder.append("}").toString();
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.api.APISummary APISummary}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private static final long OPT_BIT_CONFIGURED_PROPERTIES = 0x1L;
        private long optBits;

        private String name;
        private Map<String, ConfigurationProperty> properties =
                new LinkedHashMap<String, ConfigurationProperty>();
        private Map<String, String> configuredProperties =
                new LinkedHashMap<String, String>();
        private List<ActionsSummary> actionsSummary =
                new ArrayList<ActionsSummary>();
        private String description;
        private List<Violation> errors = new ArrayList<Violation>();
        private String icon;
        private List<Violation> warnings = new ArrayList<Violation>();

        /**
         * Creates a builder for {@link io.syndesis.common.model.api.APISummary APISummary} instances.
         * <pre>
         * new APISummary.Builder()
         *    .name(String | null) // nullable {@link io.syndesis.common.model.api.APISummary#getName() name}
         *    .putProperty|putAllProperties(String =&gt; io.syndesis.common.io.syndesis.common.model.connection.ConfigurationProperty) // {@link io.syndesis.common.model.api.APISummary#getProperties() properties} mappings
         *    .putConfiguredProperty|putAllConfiguredProperties(String =&gt; String) // {@link io.syndesis.common.model.api.APISummary#getConfiguredProperties() configuredProperties} mappings
         *    .addActionsSummary|addAllActionsSummary(io.syndesis.common.io.syndesis.common.model.action.ActionsSummary) // {@link io.syndesis.common.model.api.APISummary#getActionsSummary() actionsSummary} elements
         *    .description(String | null) // nullable {@link io.syndesis.common.model.api.APISummary#getDescription() description}
         *    .addError|addAllErrors(io.syndesis.common.io.syndesis.common.model.Violation) // {@link io.syndesis.common.model.api.APISummary#getErrors() errors} elements
         *    .icon(String) // optional {@link io.syndesis.common.model.api.APISummary#getIcon() icon}
         *    .addWarning|addAllWarnings(io.syndesis.common.io.syndesis.common.model.Violation) // {@link io.syndesis.common.model.api.APISummary#getWarnings() warnings} elements
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof APISummary.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new APISummary.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.api.APISummary} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final APISummary.Builder createFrom(APISummary instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (APISummary.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithName} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final APISummary.Builder createFrom(WithName instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (APISummary.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithConfiguredProperties} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final APISummary.Builder createFrom(
                WithConfiguredProperties instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (APISummary.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithConfigurationProperties} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final APISummary.Builder createFrom(
                WithConfigurationProperties instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (APISummary.Builder) this;
        }

        private void from(Object object) {
            if (object instanceof APISummary) {
                APISummary instance = (APISummary) object;
                Optional<String> iconOptional = instance.getIcon();
                if (iconOptional.isPresent()) {
                    icon(iconOptional);
                }
                String descriptionValue = instance.getDescription();
                if (descriptionValue != null) {
                    description(descriptionValue);
                }
                addAllActionsSummary(instance.getActionsSummary());
                addAllErrors(instance.getErrors());
                addAllWarnings(instance.getWarnings());
            }
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
            if (object instanceof WithConfigurationProperties) {
                WithConfigurationProperties instance =
                        (WithConfigurationProperties) object;
                putAllProperties(instance.getProperties());
            }
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.api.APISummary#getName() name} attribute.
         *
         * @param name The value for name (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("name")
        public final APISummary.Builder name(String name) {
            this.name = name;
            return (APISummary.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.api.APISummary#getProperties() properties} map.
         *
         * @param key   The key in the properties map
         * @param value The associated value in the properties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final APISummary.Builder putProperty(String key,
                                                    ConfigurationProperty value) {
            this.properties.put(
                    Objects.requireNonNull(key, "properties key"),
                    Objects.requireNonNull(value, "properties value"));
            return (APISummary.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.api.APISummary#getProperties() properties} map. Nulls are not permitted
         *
         * @param entry The key and value entry
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final APISummary.Builder putProperty(
                Map.Entry<String, ? extends ConfigurationProperty> entry) {
            String k = entry.getKey();
            ConfigurationProperty v = entry.getValue();
            this.properties.put(
                    Objects.requireNonNull(k, "properties key"),
                    Objects.requireNonNull(v, "properties value"));
            return (APISummary.Builder) this;
        }

        /**
         * Sets or replaces all mappings from the specified map as entries for the {@link io.syndesis.common.model.api.APISummary#getProperties() properties} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the properties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("properties")
        public final APISummary.Builder properties(
                Map<String, ? extends ConfigurationProperty> entries) {
            this.properties.clear();
            return putAllProperties(entries);
        }

        /**
         * Put all mappings from the specified map as entries to {@link io.syndesis.common.model.api.APISummary#getProperties() properties} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the properties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final APISummary.Builder putAllProperties(
                Map<String, ? extends ConfigurationProperty> entries) {
            for (Map.Entry<String, ? extends ConfigurationProperty> e : entries.entrySet()) {
                String k = e.getKey();
                ConfigurationProperty v = e.getValue();
                this.properties.put(
                        Objects.requireNonNull(k, "properties key"),
                        Objects.requireNonNull(v, "properties value"));
            }
            return (APISummary.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.api.APISummary#getConfiguredProperties() configuredProperties} map.
         *
         * @param key   The key in the configuredProperties map
         * @param value The associated value in the configuredProperties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final APISummary.Builder putConfiguredProperty(String key,
                                                              String value) {
            this.configuredProperties.put(
                    Objects.requireNonNull(key, "configuredProperties key"),
                    Objects.requireNonNull(value,
                            "configuredProperties value"));
            optBits |= OPT_BIT_CONFIGURED_PROPERTIES;
            return (APISummary.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.api.APISummary#getConfiguredProperties() configuredProperties} map. Nulls are not permitted
         *
         * @param entry The key and value entry
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final APISummary.Builder putConfiguredProperty(
                Map.Entry<String, ? extends String> entry) {
            String k = entry.getKey();
            String v = entry.getValue();
            this.configuredProperties.put(
                    Objects.requireNonNull(k, "configuredProperties key"),
                    Objects.requireNonNull(v, "configuredProperties value"));
            optBits |= OPT_BIT_CONFIGURED_PROPERTIES;
            return (APISummary.Builder) this;
        }

        /**
         * Sets or replaces all mappings from the specified map as entries for the {@link io.syndesis.common.model.api.APISummary#getConfiguredProperties() configuredProperties} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the configuredProperties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("configuredProperties")
        public final APISummary.Builder configuredProperties(
                Map<String, ? extends String> entries) {
            this.configuredProperties.clear();
            optBits |= OPT_BIT_CONFIGURED_PROPERTIES;
            return putAllConfiguredProperties(entries);
        }

        /**
         * Put all mappings from the specified map as entries to {@link io.syndesis.common.model.api.APISummary#getConfiguredProperties() configuredProperties} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the configuredProperties map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final APISummary.Builder putAllConfiguredProperties(
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
            return (APISummary.Builder) this;
        }

        /**
         * Adds one element to {@link io.syndesis.common.model.api.APISummary#getActionsSummary() actionsSummary} list.
         *
         * @param element A actionsSummary element
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final APISummary.Builder addActionsSummary(
                ActionsSummary element) {
            this.actionsSummary.add(
                    Objects.requireNonNull(element, "actionsSummary element"));
            return (APISummary.Builder) this;
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.api.APISummary#getActionsSummary() actionsSummary} list.
         *
         * @param elements An array of actionsSummary elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final APISummary.Builder addActionsSummary(
                ActionsSummary... elements) {
            for (ActionsSummary element : elements) {
                this.actionsSummary.add(Objects.requireNonNull(element,
                        "actionsSummary element"));
            }
            return (APISummary.Builder) this;
        }


        /**
         * Sets or replaces all elements for {@link io.syndesis.common.model.api.APISummary#getActionsSummary() actionsSummary} list.
         *
         * @param elements An iterable of actionsSummary elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("actionsSummary")
        public final APISummary.Builder actionsSummary(
                Iterable<? extends ActionsSummary> elements) {
            this.actionsSummary.clear();
            return addAllActionsSummary(elements);
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.api.APISummary#getActionsSummary() actionsSummary} list.
         *
         * @param elements An iterable of actionsSummary elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final APISummary.Builder addAllActionsSummary(
                Iterable<? extends ActionsSummary> elements) {
            for (ActionsSummary element : elements) {
                this.actionsSummary.add(Objects.requireNonNull(element,
                        "actionsSummary element"));
            }
            return (APISummary.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.api.APISummary#getDescription() description} attribute.
         *
         * @param description The value for description (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("description")
        public final APISummary.Builder description(String description) {
            this.description = description;
            return (APISummary.Builder) this;
        }

        /**
         * Adds one element to {@link io.syndesis.common.model.api.APISummary#getErrors() errors} list.
         *
         * @param element A errors element
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final APISummary.Builder addError(Violation element) {
            this.errors.add(Objects.requireNonNull(element, "errors element"));
            return (APISummary.Builder) this;
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.api.APISummary#getErrors() errors} list.
         *
         * @param elements An array of errors elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final APISummary.Builder addErrors(Violation... elements) {
            for (Violation element : elements) {
                this.errors.add(
                        Objects.requireNonNull(element, "errors element"));
            }
            return (APISummary.Builder) this;
        }


        /**
         * Sets or replaces all elements for {@link io.syndesis.common.model.api.APISummary#getErrors() errors} list.
         *
         * @param elements An iterable of errors elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("errors")
        public final APISummary.Builder errors(
                Iterable<? extends Violation> elements) {
            this.errors.clear();
            return addAllErrors(elements);
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.api.APISummary#getErrors() errors} list.
         *
         * @param elements An iterable of errors elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final APISummary.Builder addAllErrors(
                Iterable<? extends Violation> elements) {
            for (Violation element : elements) {
                this.errors.add(
                        Objects.requireNonNull(element, "errors element"));
            }
            return (APISummary.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.api.APISummary#getIcon() icon} to icon.
         *
         * @param icon The value for icon
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final APISummary.Builder icon(String icon) {
            this.icon = Objects.requireNonNull(icon, "icon");
            return (APISummary.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.api.APISummary#getIcon() icon} to icon.
         *
         * @param icon The value for icon
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("icon")
        public final APISummary.Builder icon(Optional<String> icon) {
            this.icon = icon.orElse(null);
            return (APISummary.Builder) this;
        }

        /**
         * Adds one element to {@link io.syndesis.common.model.api.APISummary#getWarnings() warnings} list.
         *
         * @param element A warnings element
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final APISummary.Builder addWarning(Violation element) {
            this.warnings.add(
                    Objects.requireNonNull(element, "warnings element"));
            return (APISummary.Builder) this;
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.api.APISummary#getWarnings() warnings} list.
         *
         * @param elements An array of warnings elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final APISummary.Builder addWarnings(Violation... elements) {
            for (Violation element : elements) {
                this.warnings.add(
                        Objects.requireNonNull(element, "warnings element"));
            }
            return (APISummary.Builder) this;
        }


        /**
         * Sets or replaces all elements for {@link io.syndesis.common.model.api.APISummary#getWarnings() warnings} list.
         *
         * @param elements An iterable of warnings elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("warnings")
        public final APISummary.Builder warnings(
                Iterable<? extends Violation> elements) {
            this.warnings.clear();
            return addAllWarnings(elements);
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.api.APISummary#getWarnings() warnings} list.
         *
         * @param elements An iterable of warnings elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final APISummary.Builder addAllWarnings(
                Iterable<? extends Violation> elements) {
            for (Violation element : elements) {
                this.warnings.add(
                        Objects.requireNonNull(element, "warnings element"));
            }
            return (APISummary.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.api.APISummary APISummary}.
         *
         * @return An immutable instance of APISummary
         * @throws IllegalStateException if any required attributes are missing
         */
        public APISummary build() {
            return ImmutableAPISummary.validate(new ImmutableAPISummary(this));
        }

        private boolean configuredPropertiesIsSet() {
            return (optBits & OPT_BIT_CONFIGURED_PROPERTIES) != 0;
        }
    }
}
