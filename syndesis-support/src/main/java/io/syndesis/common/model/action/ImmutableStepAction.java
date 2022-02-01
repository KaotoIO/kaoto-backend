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
import io.syndesis.common.model.WithMetadata;
import io.syndesis.common.model.WithName;
import io.syndesis.common.model.WithResourceId;
import io.syndesis.common.model.WithTags;

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
 * Immutable implementation of {@link io.syndesis.common.model.action.StepAction}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new StepAction.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableStepAction.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableStepAction implements StepAction {
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private static final byte STAGE_INITIALIZED = 1;
    private final String id;
    private final String name;
    private final SortedSet<String> tags;
    private final Map<String, String> metadata;
    private final String description;
    private final Action.Pattern pattern;
    private final String actionType;
    private final StepDescriptor descriptor;
    @SuppressWarnings("Immutable")
    private transient volatile InitShim initShim = new InitShim();

    @SuppressWarnings("unchecked") // safe covariant cast
    private ImmutableStepAction(
            Optional<String> id,
            String name,
            Iterable<String> tags,
            Map<String, ? extends String> metadata,
            String description,
            Optional<? extends Pattern> pattern,
            String actionType,
            StepDescriptor descriptor) {
        this.id = id.orElse(null);
        this.name = name;
        this.tags = createUnmodifiableSortedSet(false,
                createSafeList(tags, false, true));
        this.metadata = createUnmodifiableMap(true, false, metadata);
        this.description = description;
        this.pattern = pattern.orElse(null);
        this.actionType = Objects.requireNonNull(actionType, "actionType");
        this.descriptor = descriptor;
        this.initShim = null;
    }

    private ImmutableStepAction(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.tags = createUnmodifiableSortedSet(false,
                createSafeList(builder.tags, false, false));
        this.description = builder.description;
        this.pattern = builder.pattern;
        this.descriptor = builder.descriptor;
        if (builder.metadataIsSet()) {
            initShim.metadata(
                    createUnmodifiableMap(false, false, builder.metadata));
        }
        if (builder.actionType != null) {
            initShim.actionType(builder.actionType);
        }
        this.metadata = initShim.getMetadata();
        this.actionType = initShim.getActionType();
        this.initShim = null;
    }

    private ImmutableStepAction(
            ImmutableStepAction original,
            String id,
            String name,
            SortedSet<String> tags,
            Map<String, String> metadata,
            String description,
            Action.Pattern pattern,
            String actionType,
            StepDescriptor descriptor) {
        this.id = id;
        this.name = name;
        this.tags = tags;
        this.metadata = metadata;
        this.description = description;
        this.pattern = pattern;
        this.actionType = actionType;
        this.descriptor = descriptor;
        this.initShim = null;
    }

    /**
     * Construct a new immutable {@code StepAction} instance.
     *
     * @param id          The value for the {@code id} attribute
     * @param name        The value for the {@code name} attribute
     * @param tags        The value for the {@code tags} attribute
     * @param metadata    The value for the {@code metadata} attribute
     * @param description The value for the {@code description} attribute
     * @param pattern     The value for the {@code pattern} attribute
     * @param actionType  The value for the {@code actionType} attribute
     * @param descriptor  The value for the {@code descriptor} attribute
     * @return An immutable StepAction instance
     */
    public static StepAction of(Optional<String> id, String name,
                                SortedSet<String> tags,
                                Map<String, String> metadata,
                                String description, Optional<Pattern> pattern,
                                String actionType, StepDescriptor descriptor) {
        return of(id, name, (Iterable<String>) tags, metadata, description,
                pattern, actionType, descriptor);
    }

    /**
     * Construct a new immutable {@code StepAction} instance.
     *
     * @param id          The value for the {@code id} attribute
     * @param name        The value for the {@code name} attribute
     * @param tags        The value for the {@code tags} attribute
     * @param metadata    The value for the {@code metadata} attribute
     * @param description The value for the {@code description} attribute
     * @param pattern     The value for the {@code pattern} attribute
     * @param actionType  The value for the {@code actionType} attribute
     * @param descriptor  The value for the {@code descriptor} attribute
     * @return An immutable StepAction instance
     */
    public static StepAction of(Optional<String> id, String name,
                                Iterable<String> tags,
                                Map<String, ? extends String> metadata,
                                String description,
                                Optional<? extends Pattern> pattern,
                                String actionType, StepDescriptor descriptor) {
        return validate(
                new ImmutableStepAction(id, name, tags, metadata, description,
                        pattern, actionType, descriptor));
    }

    private static ImmutableStepAction validate(ImmutableStepAction instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.action.StepAction} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable StepAction instance
     */
    public static StepAction copyOf(StepAction instance) {
        if (instance instanceof ImmutableStepAction) {
            return (ImmutableStepAction) instance;
        }
        return new StepAction.Builder()
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

    private Map<String, String> getMetadataInitialize() {
        return StepAction.super.getMetadata();
    }

    private String getActionTypeInitialize() {
        return StepAction.super.getActionType();
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
     * @return The value of the {@code description} attribute
     */
    @JsonProperty("description")
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * @return The value of the {@code pattern} attribute
     */
    @JsonProperty("pattern")
    @Override
    public Optional<Pattern> getPattern() {
        return Optional.ofNullable(pattern);
    }

    /**
     * @return The value of the {@code actionType} attribute
     */
    @JsonProperty("actionType")
    @Override
    public String getActionType() {
        InitShim shim = this.initShim;
        return shim != null
                ? shim.getActionType()
                : this.actionType;
    }

    /**
     * @return The value of the {@code descriptor} attribute
     */
    @JsonProperty("descriptor")
    @Override
    public StepDescriptor getDescriptor() {
        return descriptor;
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.action.StepAction#getId() id} attribute.
     *
     * @param value The value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableStepAction withId(String value) {
        String newValue = Objects.requireNonNull(value, "id");
        if (Objects.equals(this.id, newValue)) {
            return this;
        }
        return validate(new ImmutableStepAction(
                this,
                newValue,
                this.name,
                this.tags,
                this.metadata,
                this.description,
                this.pattern,
                this.actionType,
                this.descriptor));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.action.StepAction#getId() id} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableStepAction withId(Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.id, value)) {
            return this;
        }
        return validate(new ImmutableStepAction(
                this,
                value,
                this.name,
                this.tags,
                this.metadata,
                this.description,
                this.pattern,
                this.actionType,
                this.descriptor));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.action.StepAction#getName() name} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for name (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableStepAction withName(String value) {
        if (Objects.equals(this.name, value)) {
            return this;
        }
        return validate(new ImmutableStepAction(
                this,
                this.id,
                value,
                this.tags,
                this.metadata,
                this.description,
                this.pattern,
                this.actionType,
                this.descriptor));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.action.StepAction#getTags() tags}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableStepAction withTags(String... elements) {
        SortedSet<String> newValue = createUnmodifiableSortedSet(false,
                createSafeList(Arrays.asList(elements), false, true));
        return validate(new ImmutableStepAction(
                this,
                this.id,
                this.name,
                newValue,
                this.metadata,
                this.description,
                this.pattern,
                this.actionType,
                this.descriptor));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.action.StepAction#getTags() tags}.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of tags elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableStepAction withTags(Iterable<String> elements) {
        if (this.tags == elements) {
            return this;
        }
        SortedSet<String> newValue = createUnmodifiableSortedSet(false,
                createSafeList(elements, false, true));
        return validate(new ImmutableStepAction(
                this,
                this.id,
                this.name,
                newValue,
                this.metadata,
                this.description,
                this.pattern,
                this.actionType,
                this.descriptor));
    }

    /**
     * Copy the current immutable object by replacing the {@link io.syndesis.common.model.action.StepAction#getMetadata() metadata} map with the specified map.
     * Nulls are not permitted as keys or values.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param entries The entries to be added to the metadata map
     * @return A modified copy of {@code this} object
     */
    public final ImmutableStepAction withMetadata(
            Map<String, ? extends String> entries) {
        if (this.metadata == entries) {
            return this;
        }
        Map<String, String> newValue =
                createUnmodifiableMap(true, false, entries);
        return validate(new ImmutableStepAction(
                this,
                this.id,
                this.name,
                this.tags,
                newValue,
                this.description,
                this.pattern,
                this.actionType,
                this.descriptor));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.action.StepAction#getDescription() description} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for description (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableStepAction withDescription(String value) {
        if (Objects.equals(this.description, value)) {
            return this;
        }
        return validate(new ImmutableStepAction(
                this,
                this.id,
                this.name,
                this.tags,
                this.metadata,
                value,
                this.pattern,
                this.actionType,
                this.descriptor));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.action.StepAction#getPattern() pattern} attribute.
     *
     * @param value The value for pattern
     * @return A modified copy of {@code this} object
     */
    public final ImmutableStepAction withPattern(Pattern value) {
        Action.Pattern newValue = Objects.requireNonNull(value, "pattern");
        if (this.pattern == newValue) {
            return this;
        }
        return validate(new ImmutableStepAction(
                this,
                this.id,
                this.name,
                this.tags,
                this.metadata,
                this.description,
                newValue,
                this.actionType,
                this.descriptor));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.action.StepAction#getPattern() pattern} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for pattern
     * @return A modified copy of {@code this} object
     */
    @SuppressWarnings("unchecked") // safe covariant cast
    public final ImmutableStepAction withPattern(
            Optional<? extends Pattern> optional) {
        Action.Pattern value = optional.orElse(null);
        if (Objects.equals(this.pattern, value)) {
            return this;
        }
        return validate(new ImmutableStepAction(
                this,
                this.id,
                this.name,
                this.tags,
                this.metadata,
                this.description,
                value,
                this.actionType,
                this.descriptor));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.action.StepAction#getActionType() actionType} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for actionType
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableStepAction withActionType(String value) {
        String newValue = Objects.requireNonNull(value, "actionType");
        if (this.actionType.equals(newValue)) {
            return this;
        }
        return validate(new ImmutableStepAction(
                this,
                this.id,
                this.name,
                this.tags,
                this.metadata,
                this.description,
                this.pattern,
                newValue,
                this.descriptor));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.action.StepAction#getDescriptor() descriptor} attribute.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for descriptor (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableStepAction withDescriptor(StepDescriptor value) {
        if (this.descriptor == value) {
            return this;
        }
        return validate(new ImmutableStepAction(
                this,
                this.id,
                this.name,
                this.tags,
                this.metadata,
                this.description,
                this.pattern,
                this.actionType,
                value));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableStepAction} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableStepAction
                && equalTo((ImmutableStepAction) another);
    }

    private boolean equalTo(ImmutableStepAction another) {
        return Objects.equals(id, another.id)
                && Objects.equals(name, another.name)
                && tags.equals(another.tags)
                && metadata.equals(another.metadata)
                && Objects.equals(description, another.description)
                && Objects.equals(pattern, another.pattern)
                && actionType.equals(another.actionType)
                && Objects.equals(descriptor, another.descriptor);
    }

    /**
     * Computes a hash code from attributes: {@code id}, {@code name}, {@code tags}, {@code metadata}, {@code description}, {@code pattern}, {@code actionType}, {@code descriptor}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(id);
        h += (h << 5) + Objects.hashCode(name);
        h += (h << 5) + tags.hashCode();
        h += (h << 5) + metadata.hashCode();
        h += (h << 5) + Objects.hashCode(description);
        h += (h << 5) + Objects.hashCode(pattern);
        h += (h << 5) + actionType.hashCode();
        h += (h << 5) + Objects.hashCode(descriptor);
        return h;
    }

    /**
     * Prints the immutable value {@code StepAction} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("StepAction{");
        if (id != null) {
            builder.append("id=").append(id);
        }
        if (name != null) {
            if (builder.length() > 11) {
                builder.append(", ");
            }
            builder.append("name=").append(name);
        }
        if (builder.length() > 11) {
            builder.append(", ");
        }
        builder.append("tags=").append(tags);
        builder.append(", ");
        builder.append("metadata=").append(metadata);
        if (description != null) {
            builder.append(", ");
            builder.append("description=").append(description);
        }
        if (pattern != null) {
            builder.append(", ");
            builder.append("pattern=").append(pattern);
        }
        builder.append(", ");
        builder.append("actionType=").append(actionType);
        if (descriptor != null) {
            builder.append(", ");
            builder.append("descriptor=").append(descriptor);
        }
        return builder.append("}").toString();
    }

    private Object readResolve() throws ObjectStreamException {
        return validate(this);
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.action.StepAction StepAction}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private static final long OPT_BIT_METADATA = 0x1L;
        private long optBits;

        private String id;
        private String name;
        private List<String> tags = new ArrayList<String>();
        private Map<String, String> metadata =
                new LinkedHashMap<String, String>();
        private String description;
        private Action.Pattern pattern;
        private String actionType;
        private StepDescriptor descriptor;

        /**
         * Creates a builder for {@link io.syndesis.common.model.action.StepAction StepAction} instances.
         * <pre>
         * new StepAction.Builder()
         *    .id(String) // optional {@link io.syndesis.common.model.action.StepAction#getId() id}
         *    .name(String | null) // nullable {@link io.syndesis.common.model.action.StepAction#getName() name}
         *    .addTag|addAllTags(String) // {@link io.syndesis.common.model.action.StepAction#getTags() tags} elements
         *    .putMetadata|putAllMetadata(String =&gt; String) // {@link io.syndesis.common.model.action.StepAction#getMetadata() metadata} mappings
         *    .description(String | null) // nullable {@link io.syndesis.common.model.action.StepAction#getDescription() description}
         *    .pattern(io.syndesis.common.io.syndesis.common.model.action.Action.Pattern) // optional {@link io.syndesis.common.model.action.StepAction#getPattern() pattern}
         *    .actionType(String) // optional {@link io.syndesis.common.model.action.StepAction#getActionType() actionType}
         *    .descriptor(io.syndesis.common.io.syndesis.common.model.action.StepDescriptor | null) // nullable {@link io.syndesis.common.model.action.StepAction#getDescriptor() descriptor}
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof StepAction.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new StepAction.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithName} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final StepAction.Builder createFrom(WithName instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (StepAction.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.action.Action} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final StepAction.Builder createFrom(Action instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (StepAction.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.action.StepAction} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final StepAction.Builder createFrom(StepAction instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (StepAction.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithMetadata} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final StepAction.Builder createFrom(WithMetadata instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (StepAction.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithResourceId} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final StepAction.Builder createFrom(WithResourceId instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (StepAction.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithTags} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final StepAction.Builder createFrom(WithTags instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (StepAction.Builder) this;
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
            if (object instanceof Action) {
                Action instance = (Action) object;
                Optional<Pattern> patternOptional = instance.getPattern();
                if (patternOptional.isPresent()) {
                    pattern(patternOptional);
                }
                if ((bits & 0x1L) == 0) {
                    actionType(instance.getActionType());
                    bits |= 0x1L;
                }
                String descriptionValue = instance.getDescription();
                if (descriptionValue != null) {
                    description(descriptionValue);
                }
            }
            if (object instanceof StepAction) {
                StepAction instance = (StepAction) object;
                if ((bits & 0x1L) == 0) {
                    actionType(instance.getActionType());
                    bits |= 0x1L;
                }
                StepDescriptor descriptorValue = instance.getDescriptor();
                if (descriptorValue != null) {
                    descriptor(descriptorValue);
                }
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
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.action.StepAction#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final StepAction.Builder id(String id) {
            this.id = Objects.requireNonNull(id, "id");
            return (StepAction.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.action.StepAction#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("id")
        public final StepAction.Builder id(Optional<String> id) {
            this.id = id.orElse(null);
            return (StepAction.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.action.StepAction#getName() name} attribute.
         *
         * @param name The value for name (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("name")
        public final StepAction.Builder name(String name) {
            this.name = name;
            return (StepAction.Builder) this;
        }

        /**
         * Adds one element to {@link io.syndesis.common.model.action.StepAction#getTags() tags} sortedSet.
         *
         * @param element A tags element
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final StepAction.Builder addTag(String element) {
            if (element != null) {
                this.tags.add(element);
            }
            return (StepAction.Builder) this;
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.action.StepAction#getTags() tags} sortedSet.
         *
         * @param elements An array of tags elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final StepAction.Builder addTags(String... elements) {
            for (String element : elements) {
                if (element != null) {
                    this.tags.add(element);
                }
            }
            return (StepAction.Builder) this;
        }


        /**
         * Sets or replaces all elements for {@link io.syndesis.common.model.action.StepAction#getTags() tags} sortedSet.
         *
         * @param elements An iterable of tags elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("tags")
        public final StepAction.Builder tags(Iterable<String> elements) {
            this.tags.clear();
            return addAllTags(elements);
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.action.StepAction#getTags() tags} sortedSet.
         *
         * @param elements An iterable of tags elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final StepAction.Builder addAllTags(Iterable<String> elements) {
            for (String element : elements) {
                if (element != null) {
                    this.tags.add(element);
                }
            }
            return (StepAction.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.action.StepAction#getMetadata() metadata} map.
         *
         * @param key   The key in the metadata map
         * @param value The associated value in the metadata map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final StepAction.Builder putMetadata(String key, String value) {
            this.metadata.put(
                    Objects.requireNonNull(key, "metadata key"),
                    Objects.requireNonNull(value, "metadata value"));
            optBits |= OPT_BIT_METADATA;
            return (StepAction.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.action.StepAction#getMetadata() metadata} map. Nulls are not permitted
         *
         * @param entry The key and value entry
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final StepAction.Builder putMetadata(
                Map.Entry<String, ? extends String> entry) {
            String k = entry.getKey();
            String v = entry.getValue();
            this.metadata.put(
                    Objects.requireNonNull(k, "metadata key"),
                    Objects.requireNonNull(v, "metadata value"));
            optBits |= OPT_BIT_METADATA;
            return (StepAction.Builder) this;
        }

        /**
         * Sets or replaces all mappings from the specified map as entries for the {@link io.syndesis.common.model.action.StepAction#getMetadata() metadata} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the metadata map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("metadata")
        public final StepAction.Builder metadata(
                Map<String, ? extends String> entries) {
            this.metadata.clear();
            optBits |= OPT_BIT_METADATA;
            return putAllMetadata(entries);
        }

        /**
         * Put all mappings from the specified map as entries to {@link io.syndesis.common.model.action.StepAction#getMetadata() metadata} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the metadata map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final StepAction.Builder putAllMetadata(
                Map<String, ? extends String> entries) {
            for (Map.Entry<String, ? extends String> e : entries.entrySet()) {
                String k = e.getKey();
                String v = e.getValue();
                this.metadata.put(
                        Objects.requireNonNull(k, "metadata key"),
                        Objects.requireNonNull(v, "metadata value"));
            }
            optBits |= OPT_BIT_METADATA;
            return (StepAction.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.action.StepAction#getDescription() description} attribute.
         *
         * @param description The value for description (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("description")
        public final StepAction.Builder description(String description) {
            this.description = description;
            return (StepAction.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.action.StepAction#getPattern() pattern} to pattern.
         *
         * @param pattern The value for pattern
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final StepAction.Builder pattern(Pattern pattern) {
            this.pattern = Objects.requireNonNull(pattern, "pattern");
            return (StepAction.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.action.StepAction#getPattern() pattern} to pattern.
         *
         * @param pattern The value for pattern
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("pattern")
        public final StepAction.Builder pattern(
                Optional<? extends Pattern> pattern) {
            this.pattern = pattern.orElse(null);
            return (StepAction.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.action.StepAction#getActionType() actionType} attribute.
         * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link io.syndesis.common.model.action.StepAction#getActionType() actionType}.</em>
         *
         * @param actionType The value for actionType
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("actionType")
        public final StepAction.Builder actionType(String actionType) {
            this.actionType = Objects.requireNonNull(actionType, "actionType");
            return (StepAction.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.action.StepAction#getDescriptor() descriptor} attribute.
         *
         * @param descriptor The value for descriptor (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("descriptor")
        public final StepAction.Builder descriptor(StepDescriptor descriptor) {
            this.descriptor = descriptor;
            return (StepAction.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.action.StepAction StepAction}.
         *
         * @return An immutable instance of StepAction
         * @throws IllegalStateException if any required attributes are missing
         */
        public StepAction build() {
            return ImmutableStepAction.validate(new ImmutableStepAction(this));
        }

        private boolean metadataIsSet() {
            return (optBits & OPT_BIT_METADATA) != 0;
        }
    }

    private final class InitShim {
        private byte metadataBuildStage = STAGE_UNINITIALIZED;
        private Map<String, String> metadata;
        private byte actionTypeBuildStage = STAGE_UNINITIALIZED;
        private String actionType;

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

        String getActionType() {
            if (actionTypeBuildStage == STAGE_INITIALIZING) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (actionTypeBuildStage == STAGE_UNINITIALIZED) {
                actionTypeBuildStage = STAGE_INITIALIZING;
                this.actionType =
                        Objects.requireNonNull(getActionTypeInitialize(),
                                "actionType");
                actionTypeBuildStage = STAGE_INITIALIZED;
            }
            return this.actionType;
        }

        void actionType(String actionType) {
            this.actionType = actionType;
            actionTypeBuildStage = STAGE_INITIALIZED;
        }

        private String formatInitCycleMessage() {
            List<String> attributes = new ArrayList<>();
            if (metadataBuildStage == STAGE_INITIALIZING) {
                attributes.add("metadata");
            }
            if (actionTypeBuildStage == STAGE_INITIALIZING) {
                attributes.add("actionType");
            }
            return "Cannot build StepAction, attribute initializers form cycle "
                    + attributes;
        }
    }
}
