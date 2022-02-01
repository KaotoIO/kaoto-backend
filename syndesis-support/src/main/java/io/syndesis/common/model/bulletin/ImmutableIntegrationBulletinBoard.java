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
package io.syndesis.common.model.bulletin;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Var;
import io.syndesis.common.model.WithMetadata;
import io.syndesis.common.model.WithModificationTimestamps;
import io.syndesis.common.model.WithResourceId;

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
 * Immutable implementation of {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new IntegrationBulletinBoard.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableIntegrationBulletinBoard.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableIntegrationBulletinBoard
        implements IntegrationBulletinBoard {
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private static final byte STAGE_INITIALIZED = 1;
    private final String id;
    private final Map<String, String> metadata;
    private final List<LeveledMessage> messages;
    private final long createdAt;
    private final long updatedAt;
    private final String targetResourceId;
    private final Integer notices;
    private final Integer warnings;
    private final Integer errors;
    @SuppressWarnings("Immutable")
    private transient volatile InitShim initShim = new InitShim();

    private ImmutableIntegrationBulletinBoard(
            Optional<String> id,
            Map<String, ? extends String> metadata,
            Iterable<? extends LeveledMessage> messages,
            long createdAt,
            long updatedAt,
            String targetResourceId,
            Optional<Integer> notices,
            Optional<Integer> warnings,
            Optional<Integer> errors) {
        this.id = id.orElse(null);
        this.metadata = createUnmodifiableMap(true, false, metadata);
        this.messages = createUnmodifiableList(false,
                createSafeList(messages, true, false));
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.targetResourceId = targetResourceId;
        this.notices = notices.isPresent() ? notices.get() : null;
        this.warnings = warnings.isPresent() ? warnings.get() : null;
        this.errors = errors.isPresent() ? errors.get() : null;
        this.initShim = null;
    }

    private ImmutableIntegrationBulletinBoard(Builder builder) {
        this.id = builder.id;
        this.targetResourceId = builder.targetResourceId;
        this.notices = builder.notices;
        this.warnings = builder.warnings;
        this.errors = builder.errors;
        if (builder.metadataIsSet()) {
            initShim.metadata(
                    createUnmodifiableMap(false, false, builder.metadata));
        }
        if (builder.messagesIsSet()) {
            initShim.messages(createUnmodifiableList(true, builder.messages));
        }
        if (builder.createdAtIsSet()) {
            initShim.createdAt(builder.createdAt);
        }
        if (builder.updatedAtIsSet()) {
            initShim.updatedAt(builder.updatedAt);
        }
        this.metadata = initShim.getMetadata();
        this.messages = initShim.getMessages();
        this.createdAt = initShim.getCreatedAt();
        this.updatedAt = initShim.getUpdatedAt();
        this.initShim = null;
    }

    private ImmutableIntegrationBulletinBoard(
            ImmutableIntegrationBulletinBoard original,
            String id,
            Map<String, String> metadata,
            List<LeveledMessage> messages,
            long createdAt,
            long updatedAt,
            String targetResourceId,
            Integer notices,
            Integer warnings,
            Integer errors) {
        this.id = id;
        this.metadata = metadata;
        this.messages = messages;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.targetResourceId = targetResourceId;
        this.notices = notices;
        this.warnings = warnings;
        this.errors = errors;
        this.initShim = null;
    }

    /**
     * Construct a new immutable {@code IntegrationBulletinBoard} instance.
     *
     * @param id               The value for the {@code id} attribute
     * @param metadata         The value for the {@code metadata} attribute
     * @param messages         The value for the {@code messages} attribute
     * @param createdAt        The value for the {@code createdAt} attribute
     * @param updatedAt        The value for the {@code updatedAt} attribute
     * @param targetResourceId The value for the {@code targetResourceId} attribute
     * @param notices          The value for the {@code notices} attribute
     * @param warnings         The value for the {@code warnings} attribute
     * @param errors           The value for the {@code errors} attribute
     * @return An immutable IntegrationBulletinBoard instance
     */
    public static IntegrationBulletinBoard of(Optional<String> id,
                                              Map<String, String> metadata,
                                              List<LeveledMessage> messages,
                                              long createdAt, long updatedAt,
                                              String targetResourceId,
                                              Optional<Integer> notices,
                                              Optional<Integer> warnings,
                                              Optional<Integer> errors) {
        return of(id, metadata, (Iterable<? extends LeveledMessage>) messages,
                createdAt, updatedAt, targetResourceId, notices, warnings,
                errors);
    }

    /**
     * Construct a new immutable {@code IntegrationBulletinBoard} instance.
     *
     * @param id               The value for the {@code id} attribute
     * @param metadata         The value for the {@code metadata} attribute
     * @param messages         The value for the {@code messages} attribute
     * @param createdAt        The value for the {@code createdAt} attribute
     * @param updatedAt        The value for the {@code updatedAt} attribute
     * @param targetResourceId The value for the {@code targetResourceId} attribute
     * @param notices          The value for the {@code notices} attribute
     * @param warnings         The value for the {@code warnings} attribute
     * @param errors           The value for the {@code errors} attribute
     * @return An immutable IntegrationBulletinBoard instance
     */
    public static IntegrationBulletinBoard of(Optional<String> id,
                                              Map<String, ? extends String> metadata,
                                              Iterable<? extends LeveledMessage> messages,
                                              long createdAt, long updatedAt,
                                              String targetResourceId,
                                              Optional<Integer> notices,
                                              Optional<Integer> warnings,
                                              Optional<Integer> errors) {
        return validate(
                new ImmutableIntegrationBulletinBoard(id, metadata, messages,
                        createdAt, updatedAt, targetResourceId, notices,
                        warnings, errors));
    }

    private static ImmutableIntegrationBulletinBoard validate(
            ImmutableIntegrationBulletinBoard instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable IntegrationBulletinBoard instance
     */
    public static IntegrationBulletinBoard copyOf(
            IntegrationBulletinBoard instance) {
        if (instance instanceof ImmutableIntegrationBulletinBoard) {
            return (ImmutableIntegrationBulletinBoard) instance;
        }
        return new IntegrationBulletinBoard.Builder()
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

    private Map<String, String> getMetadataInitialize() {
        return IntegrationBulletinBoard.super.getMetadata();
    }

    private List<LeveledMessage> getMessagesInitialize() {
        return IntegrationBulletinBoard.super.getMessages();
    }

    private long getCreatedAtInitialize() {
        return IntegrationBulletinBoard.super.getCreatedAt();
    }

    private long getUpdatedAtInitialize() {
        return IntegrationBulletinBoard.super.getUpdatedAt();
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
     * @return The value of the {@code messages} attribute
     */
    @JsonProperty("messages")
    @Override
    public List<LeveledMessage> getMessages() {
        InitShim shim = this.initShim;
        return shim != null
                ? shim.getMessages()
                : this.messages;
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
     * @return The value of the {@code targetResourceId} attribute
     */
    @JsonProperty("targetResourceId")
    @Override
    public String getTargetResourceId() {
        return targetResourceId;
    }

    /**
     * @return The value of the {@code notices} attribute
     */
    @JsonProperty("notices")
    @Override
    public Optional<Integer> getNotices() {
        return Optional.of(notices);
    }

    /**
     * @return The value of the {@code warnings} attribute
     */
    @JsonProperty("warnings")
    @Override
    public Optional<Integer> getWarnings() {
        return Optional.of(warnings);
    }

    /**
     * @return The value of the {@code errors} attribute
     */
    @JsonProperty("errors")
    @Override
    public Optional<Integer> getErrors() {
        return Optional.of(this.errors);
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getId() id} attribute.
     *
     * @param value The value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegrationBulletinBoard withId(String value) {
        String newValue = Objects.requireNonNull(value, "id");
        if (Objects.equals(this.id, newValue)) {
            return this;
        }
        return validate(new ImmutableIntegrationBulletinBoard(
                this,
                newValue,
                this.metadata,
                this.messages,
                this.createdAt,
                this.updatedAt,
                this.targetResourceId,
                this.notices,
                this.warnings,
                this.errors));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getId() id} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegrationBulletinBoard withId(
            Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.id, value)) {
            return this;
        }
        return validate(new ImmutableIntegrationBulletinBoard(
                this,
                value,
                this.metadata,
                this.messages,
                this.createdAt,
                this.updatedAt,
                this.targetResourceId,
                this.notices,
                this.warnings,
                this.errors));
    }

    /**
     * Copy the current immutable object by replacing the {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getMetadata() metadata} map with the specified map.
     * Nulls are not permitted as keys or values.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param entries The entries to be added to the metadata map
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegrationBulletinBoard withMetadata(
            Map<String, ? extends String> entries) {
        if (this.metadata == entries) {
            return this;
        }
        Map<String, String> newValue =
                createUnmodifiableMap(true, false, entries);
        return validate(new ImmutableIntegrationBulletinBoard(
                this,
                this.id,
                newValue,
                this.messages,
                this.createdAt,
                this.updatedAt,
                this.targetResourceId,
                this.notices,
                this.warnings,
                this.errors));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getMessages() messages}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegrationBulletinBoard withMessages(
            LeveledMessage... elements) {
        List<LeveledMessage> newValue = createUnmodifiableList(false,
                createSafeList(Arrays.asList(elements), true, false));
        return validate(new ImmutableIntegrationBulletinBoard(
                this,
                this.id,
                this.metadata,
                newValue,
                this.createdAt,
                this.updatedAt,
                this.targetResourceId,
                this.notices,
                this.warnings,
                this.errors));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getMessages() messages}.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of messages elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegrationBulletinBoard withMessages(
            Iterable<? extends LeveledMessage> elements) {
        if (this.messages == elements) {
            return this;
        }
        List<LeveledMessage> newValue = createUnmodifiableList(false,
                createSafeList(elements, true, false));
        return validate(new ImmutableIntegrationBulletinBoard(
                this,
                this.id,
                this.metadata,
                newValue,
                this.createdAt,
                this.updatedAt,
                this.targetResourceId,
                this.notices,
                this.warnings,
                this.errors));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getCreatedAt() createdAt} attribute.
     * A value equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for createdAt
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableIntegrationBulletinBoard withCreatedAt(long value) {
        if (this.createdAt == value) {
            return this;
        }
        return validate(new ImmutableIntegrationBulletinBoard(
                this,
                this.id,
                this.metadata,
                this.messages,
                value,
                this.updatedAt,
                this.targetResourceId,
                this.notices,
                this.warnings,
                this.errors));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getUpdatedAt() updatedAt} attribute.
     * A value equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for updatedAt
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableIntegrationBulletinBoard withUpdatedAt(long value) {
        if (this.updatedAt == value) {
            return this;
        }
        return validate(new ImmutableIntegrationBulletinBoard(
                this,
                this.id,
                this.metadata,
                this.messages,
                this.createdAt,
                value,
                this.targetResourceId,
                this.notices,
                this.warnings,
                this.errors));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getTargetResourceId() targetResourceId} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for targetResourceId (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableIntegrationBulletinBoard withTargetResourceId(
            String value) {
        if (Objects.equals(this.targetResourceId, value)) {
            return this;
        }
        return validate(new ImmutableIntegrationBulletinBoard(
                this,
                this.id,
                this.metadata,
                this.messages,
                this.createdAt,
                this.updatedAt,
                value,
                this.notices,
                this.warnings,
                this.errors));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getNotices() notices} attribute.
     *
     * @param value The value for notices
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegrationBulletinBoard withNotices(int value) {
        Integer newValue = value;
        if (Objects.equals(this.notices, newValue)) {
            return this;
        }
        return validate(new ImmutableIntegrationBulletinBoard(
                this,
                this.id,
                this.metadata,
                this.messages,
                this.createdAt,
                this.updatedAt,
                this.targetResourceId,
                newValue,
                this.warnings,
                this.errors));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getNotices() notices} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for notices
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegrationBulletinBoard withNotices(
            Optional<Integer> optional) {
        Integer value = optional.isPresent() ? optional.get() : null;
        if (Objects.equals(this.notices, value)) {
            return this;
        }
        return validate(new ImmutableIntegrationBulletinBoard(
                this,
                this.id,
                this.metadata,
                this.messages,
                this.createdAt,
                this.updatedAt,
                this.targetResourceId,
                value,
                this.warnings,
                this.errors));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getWarnings() warnings} attribute.
     *
     * @param value The value for warnings
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegrationBulletinBoard withWarnings(int value) {
        Integer newValue = value;
        if (Objects.equals(this.warnings, newValue)) {
            return this;
        }
        return validate(new ImmutableIntegrationBulletinBoard(
                this,
                this.id,
                this.metadata,
                this.messages,
                this.createdAt,
                this.updatedAt,
                this.targetResourceId,
                this.notices,
                newValue,
                this.errors));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getWarnings() warnings} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for warnings
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegrationBulletinBoard withWarnings(
            Optional<Integer> optional) {
        Integer value = optional.isPresent() ? optional.get() : null;
        if (Objects.equals(this.warnings, value)) {
            return this;
        }
        return validate(new ImmutableIntegrationBulletinBoard(
                this,
                this.id,
                this.metadata,
                this.messages,
                this.createdAt,
                this.updatedAt,
                this.targetResourceId,
                this.notices,
                value,
                this.errors));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getErrors() errors} attribute.
     *
     * @param value The value for errors
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegrationBulletinBoard withErrors(int value) {
        Integer newValue = value;
        if (Objects.equals(this.errors, newValue)) {
            return this;
        }
        return validate(new ImmutableIntegrationBulletinBoard(
                this,
                this.id,
                this.metadata,
                this.messages,
                this.createdAt,
                this.updatedAt,
                this.targetResourceId,
                this.notices,
                this.warnings,
                newValue));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getErrors() errors} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for errors
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegrationBulletinBoard withErrors(
            Optional<Integer> optional) {
        Integer value = optional.isPresent() ? optional.get() : null;
        if (Objects.equals(this.errors, value)) {
            return this;
        }
        return validate(new ImmutableIntegrationBulletinBoard(
                this,
                this.id,
                this.metadata,
                this.messages,
                this.createdAt,
                this.updatedAt,
                this.targetResourceId,
                this.notices,
                this.warnings,
                value));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableIntegrationBulletinBoard} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableIntegrationBulletinBoard
                && equalTo((ImmutableIntegrationBulletinBoard) another);
    }

    private boolean equalTo(ImmutableIntegrationBulletinBoard another) {
        return Objects.equals(id, another.id)
                && metadata.equals(another.metadata)
                && messages.equals(another.messages)
                && createdAt == another.createdAt
                && updatedAt == another.updatedAt
                && Objects.equals(targetResourceId, another.targetResourceId)
                && Objects.equals(notices, another.notices)
                && Objects.equals(warnings, another.warnings)
                && Objects.equals(errors, another.errors);
    }

    /**
     * Computes a hash code from attributes: {@code id}, {@code metadata}, {@code messages}, {@code createdAt}, {@code updatedAt}, {@code targetResourceId}, {@code notices}, {@code warnings}, {@code errors}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(id);
        h += (h << 5) + metadata.hashCode();
        h += (h << 5) + messages.hashCode();
        h += (h << 5) + Long.hashCode(createdAt);
        h += (h << 5) + Long.hashCode(updatedAt);
        h += (h << 5) + Objects.hashCode(targetResourceId);
        h += (h << 5) + Objects.hashCode(notices);
        h += (h << 5) + Objects.hashCode(warnings);
        h += (h << 5) + Objects.hashCode(errors);
        return h;
    }

    /**
     * Prints the immutable value {@code IntegrationBulletinBoard} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("IntegrationBulletinBoard{");
        if (id != null) {
            builder.append("id=").append(id);
        }
        if (builder.length() > 25) {
            builder.append(", ");
        }
        builder.append("metadata=").append(metadata);
        builder.append(", ");
        builder.append("messages=").append(messages);
        builder.append(", ");
        builder.append("createdAt=").append(createdAt);
        builder.append(", ");
        builder.append("updatedAt=").append(updatedAt);
        if (targetResourceId != null) {
            builder.append(", ");
            builder.append("targetResourceId=").append(targetResourceId);
        }
        if (notices != null) {
            builder.append(", ");
            builder.append("notices=").append(notices);
        }
        if (warnings != null) {
            builder.append(", ");
            builder.append("warnings=").append(warnings);
        }
        if (errors != null) {
            builder.append(", ");
            builder.append("errors=").append(errors);
        }
        return builder.append("}").toString();
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard IntegrationBulletinBoard}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private static final long OPT_BIT_METADATA = 0x1L;
        private static final long OPT_BIT_MESSAGES = 0x2L;
        private static final long OPT_BIT_CREATED_AT = 0x4L;
        private static final long OPT_BIT_UPDATED_AT = 0x8L;
        private long optBits;

        private String id;
        private Map<String, String> metadata =
                new LinkedHashMap<String, String>();
        private List<LeveledMessage> messages = new ArrayList<LeveledMessage>();
        private long createdAt;
        private long updatedAt;
        private String targetResourceId;
        private Integer notices;
        private Integer warnings;
        private Integer errors;

        /**
         * Creates a builder for {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard IntegrationBulletinBoard} instances.
         * <pre>
         * new IntegrationBulletinBoard.Builder()
         *    .id(String) // optional {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getId() id}
         *    .putMetadata|putAllMetadata(String =&gt; String) // {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getMetadata() metadata} mappings
         *    .addMessage|addAllMessages(io.syndesis.common.io.syndesis.common.model.bulletin.LeveledMessage) // {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getMessages() messages} elements
         *    .createdAt(long) // optional {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getCreatedAt() createdAt}
         *    .updatedAt(long) // optional {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getUpdatedAt() updatedAt}
         *    .targetResourceId(String | null) // nullable {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getTargetResourceId() targetResourceId}
         *    .notices(int) // optional {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getNotices() notices}
         *    .warnings(int) // optional {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getWarnings() warnings}
         *    .errors(int) // optional {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getErrors() errors}
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof IntegrationBulletinBoard.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new IntegrationBulletinBoard.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.bulletin.IntegrationBulletinBoard} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationBulletinBoard.Builder createFrom(
                IntegrationBulletinBoard instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (IntegrationBulletinBoard.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.bulletin.WithLeveledMessages} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationBulletinBoard.Builder createFrom(
                WithLeveledMessages instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (IntegrationBulletinBoard.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithMetadata} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationBulletinBoard.Builder createFrom(
                WithMetadata instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (IntegrationBulletinBoard.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithResourceId} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationBulletinBoard.Builder createFrom(
                WithResourceId instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (IntegrationBulletinBoard.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithModificationTimestamps} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationBulletinBoard.Builder createFrom(
                WithModificationTimestamps instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (IntegrationBulletinBoard.Builder) this;
        }

        private void from(Object object) {
            if (object instanceof IntegrationBulletinBoard) {
                IntegrationBulletinBoard instance =
                        (IntegrationBulletinBoard) object;
                Optional<Integer> noticesOptional = instance.getNotices();
                if (noticesOptional.isPresent()) {
                    notices(noticesOptional);
                }
                String targetResourceIdValue = instance.getTargetResourceId();
                if (targetResourceIdValue != null) {
                    targetResourceId(targetResourceIdValue);
                }
                Optional<Integer> errorsOptional = instance.getErrors();
                if (errorsOptional.isPresent()) {
                    errors(errorsOptional);
                }
                Optional<Integer> warningsOptional = instance.getWarnings();
                if (warningsOptional.isPresent()) {
                    warnings(warningsOptional);
                }
            }
            if (object instanceof WithLeveledMessages) {
                WithLeveledMessages instance = (WithLeveledMessages) object;
                addAllMessages(instance.getMessages());
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
            if (object instanceof WithModificationTimestamps) {
                WithModificationTimestamps instance =
                        (WithModificationTimestamps) object;
                createdAt(instance.getCreatedAt());
                updatedAt(instance.getUpdatedAt());
            }
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationBulletinBoard.Builder id(String id) {
            this.id = Objects.requireNonNull(id, "id");
            return (IntegrationBulletinBoard.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("id")
        public final IntegrationBulletinBoard.Builder id(Optional<String> id) {
            this.id = id.orElse(null);
            return (IntegrationBulletinBoard.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getMetadata() metadata} map.
         *
         * @param key   The key in the metadata map
         * @param value The associated value in the metadata map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationBulletinBoard.Builder putMetadata(String key,
                                                                  String value) {
            this.metadata.put(
                    Objects.requireNonNull(key, "metadata key"),
                    Objects.requireNonNull(value, "metadata value"));
            optBits |= OPT_BIT_METADATA;
            return (IntegrationBulletinBoard.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getMetadata() metadata} map. Nulls are not permitted
         *
         * @param entry The key and value entry
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationBulletinBoard.Builder putMetadata(
                Map.Entry<String, ? extends String> entry) {
            String k = entry.getKey();
            String v = entry.getValue();
            this.metadata.put(
                    Objects.requireNonNull(k, "metadata key"),
                    Objects.requireNonNull(v, "metadata value"));
            optBits |= OPT_BIT_METADATA;
            return (IntegrationBulletinBoard.Builder) this;
        }

        /**
         * Sets or replaces all mappings from the specified map as entries for the {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getMetadata() metadata} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the metadata map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("metadata")
        public final IntegrationBulletinBoard.Builder metadata(
                Map<String, ? extends String> entries) {
            this.metadata.clear();
            optBits |= OPT_BIT_METADATA;
            return putAllMetadata(entries);
        }

        /**
         * Put all mappings from the specified map as entries to {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getMetadata() metadata} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the metadata map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationBulletinBoard.Builder putAllMetadata(
                Map<String, ? extends String> entries) {
            for (Map.Entry<String, ? extends String> e : entries.entrySet()) {
                String k = e.getKey();
                String v = e.getValue();
                this.metadata.put(
                        Objects.requireNonNull(k, "metadata key"),
                        Objects.requireNonNull(v, "metadata value"));
            }
            optBits |= OPT_BIT_METADATA;
            return (IntegrationBulletinBoard.Builder) this;
        }

        /**
         * Adds one element to {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getMessages() messages} list.
         *
         * @param element A messages element
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationBulletinBoard.Builder addMessage(
                LeveledMessage element) {
            this.messages.add(
                    Objects.requireNonNull(element, "messages element"));
            optBits |= OPT_BIT_MESSAGES;
            return (IntegrationBulletinBoard.Builder) this;
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getMessages() messages} list.
         *
         * @param elements An array of messages elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationBulletinBoard.Builder addMessages(
                LeveledMessage... elements) {
            for (LeveledMessage element : elements) {
                this.messages.add(
                        Objects.requireNonNull(element, "messages element"));
            }
            optBits |= OPT_BIT_MESSAGES;
            return (IntegrationBulletinBoard.Builder) this;
        }


        /**
         * Sets or replaces all elements for {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getMessages() messages} list.
         *
         * @param elements An iterable of messages elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("messages")
        public final IntegrationBulletinBoard.Builder messages(
                Iterable<? extends LeveledMessage> elements) {
            this.messages.clear();
            return addAllMessages(elements);
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getMessages() messages} list.
         *
         * @param elements An iterable of messages elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationBulletinBoard.Builder addAllMessages(
                Iterable<? extends LeveledMessage> elements) {
            for (LeveledMessage element : elements) {
                this.messages.add(
                        Objects.requireNonNull(element, "messages element"));
            }
            optBits |= OPT_BIT_MESSAGES;
            return (IntegrationBulletinBoard.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getCreatedAt() createdAt} attribute.
         * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getCreatedAt() createdAt}.</em>
         *
         * @param createdAt The value for createdAt
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("createdAt")
        public final IntegrationBulletinBoard.Builder createdAt(
                long createdAt) {
            this.createdAt = createdAt;
            optBits |= OPT_BIT_CREATED_AT;
            return (IntegrationBulletinBoard.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getUpdatedAt() updatedAt} attribute.
         * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getUpdatedAt() updatedAt}.</em>
         *
         * @param updatedAt The value for updatedAt
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("updatedAt")
        public final IntegrationBulletinBoard.Builder updatedAt(
                long updatedAt) {
            this.updatedAt = updatedAt;
            optBits |= OPT_BIT_UPDATED_AT;
            return (IntegrationBulletinBoard.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getTargetResourceId() targetResourceId} attribute.
         *
         * @param targetResourceId The value for targetResourceId (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("targetResourceId")
        public final IntegrationBulletinBoard.Builder targetResourceId(
                String targetResourceId) {
            this.targetResourceId = targetResourceId;
            return (IntegrationBulletinBoard.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getNotices() notices} to notices.
         *
         * @param notices The value for notices
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationBulletinBoard.Builder notices(int notices) {
            this.notices = notices;
            return (IntegrationBulletinBoard.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getNotices() notices} to notices.
         *
         * @param notices The value for notices
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("notices")
        public final IntegrationBulletinBoard.Builder notices(
                Optional<Integer> notices) {
            this.notices = notices.isPresent() ? notices.get() : null;
            return (IntegrationBulletinBoard.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getWarnings() warnings} to warnings.
         *
         * @param warnings The value for warnings
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationBulletinBoard.Builder warnings(int warnings) {
            this.warnings = warnings;
            return (IntegrationBulletinBoard.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getWarnings() warnings} to warnings.
         *
         * @param warnings The value for warnings
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("warnings")
        public final IntegrationBulletinBoard.Builder warnings(
                Optional<Integer> warnings) {
            this.warnings = warnings.isPresent() ? warnings.get() : null;
            return (IntegrationBulletinBoard.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getErrors() errors} to errors.
         *
         * @param errors The value for errors
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationBulletinBoard.Builder errors(int errors) {
            this.errors = errors;
            return (IntegrationBulletinBoard.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard#getErrors() errors} to errors.
         *
         * @param errors The value for errors
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("errors")
        public final IntegrationBulletinBoard.Builder errors(
                Optional<Integer> errors) {
            this.errors = errors.isPresent() ? errors.get() : null;
            return (IntegrationBulletinBoard.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.bulletin.IntegrationBulletinBoard IntegrationBulletinBoard}.
         *
         * @return An immutable instance of IntegrationBulletinBoard
         * @throws IllegalStateException if any required attributes are missing
         */
        public IntegrationBulletinBoard build() {
            return ImmutableIntegrationBulletinBoard.validate(
                    new ImmutableIntegrationBulletinBoard(this));
        }

        private boolean metadataIsSet() {
            return (optBits & OPT_BIT_METADATA) != 0;
        }

        private boolean messagesIsSet() {
            return (optBits & OPT_BIT_MESSAGES) != 0;
        }

        private boolean createdAtIsSet() {
            return (optBits & OPT_BIT_CREATED_AT) != 0;
        }

        private boolean updatedAtIsSet() {
            return (optBits & OPT_BIT_UPDATED_AT) != 0;
        }
    }

    private final class InitShim {
        private byte metadataBuildStage = STAGE_UNINITIALIZED;
        private Map<String, String> metadata;
        private byte messagesBuildStage = STAGE_UNINITIALIZED;
        private List<LeveledMessage> messages;
        private byte createdAtBuildStage = STAGE_UNINITIALIZED;
        private long createdAt;
        private byte updatedAtBuildStage = STAGE_UNINITIALIZED;
        private long updatedAt;

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

        List<LeveledMessage> getMessages() {
            if (messagesBuildStage == STAGE_INITIALIZING) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (messagesBuildStage == STAGE_UNINITIALIZED) {
                messagesBuildStage = STAGE_INITIALIZING;
                this.messages = createUnmodifiableList(false,
                        createSafeList(getMessagesInitialize(), true, false));
                messagesBuildStage = STAGE_INITIALIZED;
            }
            return this.messages;
        }

        void messages(List<LeveledMessage> messages) {
            this.messages = messages;
            messagesBuildStage = STAGE_INITIALIZED;
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

        private String formatInitCycleMessage() {
            List<String> attributes = new ArrayList<>();
            if (metadataBuildStage == STAGE_INITIALIZING) {
                attributes.add("metadata");
            }
            if (messagesBuildStage == STAGE_INITIALIZING) {
                attributes.add("messages");
            }
            if (createdAtBuildStage == STAGE_INITIALIZING) {
                attributes.add("createdAt");
            }
            if (updatedAtBuildStage == STAGE_INITIALIZING) {
                attributes.add("updatedAt");
            }
            return "Cannot build IntegrationBulletinBoard, attribute initializers form cycle "
                    + attributes;
        }
    }
}
