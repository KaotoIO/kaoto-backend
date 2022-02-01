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

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Immutable implementation of {@link io.syndesis.common.model.bulletin.LeveledMessage}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new LeveledMessage.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableLeveledMessage.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableLeveledMessage implements LeveledMessage {
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private static final byte STAGE_INITIALIZED = 1;
    private final Map<String, String> metadata;
    private final Level level;
    private final Code code;
    private final String message;
    private final String detail;
    @SuppressWarnings("Immutable")
    private transient volatile InitShim initShim = new InitShim();

    private ImmutableLeveledMessage(
            Map<String, ? extends String> metadata,
            Level level,
            Code code,
            Optional<String> message,
            Optional<String> detail) {
        this.metadata = createUnmodifiableMap(true, false, metadata);
        this.level = Objects.requireNonNull(level, "level");
        this.code = Objects.requireNonNull(code, "code");
        this.message = message.orElse(null);
        this.detail = detail.orElse(null);
        this.initShim = null;
    }

    private ImmutableLeveledMessage(Builder builder) {
        this.message = builder.message;
        this.detail = builder.detail;
        if (builder.metadataIsSet()) {
            initShim.metadata(
                    createUnmodifiableMap(false, false, builder.metadata));
        }
        if (builder.level != null) {
            initShim.level(builder.level);
        }
        if (builder.code != null) {
            initShim.code(builder.code);
        }
        this.metadata = initShim.getMetadata();
        this.level = initShim.getLevel();
        this.code = initShim.getCode();
        this.initShim = null;
    }

    private ImmutableLeveledMessage(
            ImmutableLeveledMessage original,
            Map<String, String> metadata,
            Level level,
            Code code,
            String message,
            String detail) {
        this.metadata = metadata;
        this.level = level;
        this.code = code;
        this.message = message;
        this.detail = detail;
        this.initShim = null;
    }

    /**
     * Construct a new immutable {@code LeveledMessage} instance.
     *
     * @param metadata The value for the {@code metadata} attribute
     * @param level    The value for the {@code level} attribute
     * @param code     The value for the {@code code} attribute
     * @param message  The value for the {@code message} attribute
     * @param detail   The value for the {@code detail} attribute
     * @return An immutable LeveledMessage instance
     */
    public static LeveledMessage of(Map<String, ? extends String> metadata,
                                    Level level, Code code,
                                    Optional<String> message,
                                    Optional<String> detail) {
        return validate(
                new ImmutableLeveledMessage(metadata, level, code, message,
                        detail));
    }

    private static ImmutableLeveledMessage validate(
            ImmutableLeveledMessage instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.bulletin.LeveledMessage} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable LeveledMessage instance
     */
    public static LeveledMessage copyOf(LeveledMessage instance) {
        if (instance instanceof ImmutableLeveledMessage) {
            return (ImmutableLeveledMessage) instance;
        }
        return new LeveledMessage.Builder()
                .createFrom(instance)
                .build();
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
        return LeveledMessage.super.getMetadata();
    }

    private Level getLevelInitialize() {
        return LeveledMessage.super.getLevel();
    }

    private Code getCodeInitialize() {
        return LeveledMessage.super.getCode();
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
     * @return The value of the {@code level} attribute
     */
    @JsonProperty("level")
    @Override
    public Level getLevel() {
        InitShim shim = this.initShim;
        return shim != null
                ? shim.getLevel()
                : this.level;
    }

    /**
     * @return The value of the {@code code} attribute
     */
    @JsonProperty("code")
    @Override
    public Code getCode() {
        InitShim shim = this.initShim;
        return shim != null
                ? shim.getCode()
                : this.code;
    }

    /**
     * @return The value of the {@code message} attribute
     */
    @JsonProperty("message")
    @Override
    public Optional<String> getMessage() {
        return Optional.ofNullable(message);
    }

    /**
     * @return The value of the {@code detail} attribute
     */
    @JsonProperty("detail")
    @Override
    public Optional<String> getDetail() {
        return Optional.ofNullable(detail);
    }

    /**
     * Copy the current immutable object by replacing the {@link io.syndesis.common.model.bulletin.LeveledMessage#getMetadata() metadata} map with the specified map.
     * Nulls are not permitted as keys or values.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param entries The entries to be added to the metadata map
     * @return A modified copy of {@code this} object
     */
    public final ImmutableLeveledMessage withMetadata(
            Map<String, ? extends String> entries) {
        if (this.metadata == entries) {
            return this;
        }
        Map<String, String> newValue =
                createUnmodifiableMap(true, false, entries);
        return validate(new ImmutableLeveledMessage(this, newValue, this.level,
                this.code, this.message, this.detail));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.bulletin.LeveledMessage#getLevel() level} attribute.
     * A value equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for level
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableLeveledMessage withLevel(Level value) {
        if (this.level == value) {
            return this;
        }
        Level newValue = Objects.requireNonNull(value, "level");
        if (this.level.equals(newValue)) {
            return this;
        }
        return validate(
                new ImmutableLeveledMessage(this, this.metadata, newValue,
                        this.code, this.message, this.detail));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.bulletin.LeveledMessage#getCode() code} attribute.
     * A value equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for code
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableLeveledMessage withCode(Code value) {
        if (this.code == value) {
            return this;
        }
        Code newValue = Objects.requireNonNull(value, "code");
        if (this.code.equals(newValue)) {
            return this;
        }
        return validate(
                new ImmutableLeveledMessage(this, this.metadata, this.level,
                        newValue, this.message, this.detail));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.bulletin.LeveledMessage#getMessage() message} attribute.
     *
     * @param value The value for message
     * @return A modified copy of {@code this} object
     */
    public final ImmutableLeveledMessage withMessage(String value) {
        String newValue = Objects.requireNonNull(value, "message");
        if (Objects.equals(this.message, newValue)) {
            return this;
        }
        return validate(
                new ImmutableLeveledMessage(this, this.metadata, this.level,
                        this.code, newValue, this.detail));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.bulletin.LeveledMessage#getMessage() message} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for message
     * @return A modified copy of {@code this} object
     */
    public final ImmutableLeveledMessage withMessage(
            Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.message, value)) {
            return this;
        }
        return validate(
                new ImmutableLeveledMessage(this, this.metadata, this.level,
                        this.code, value, this.detail));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.bulletin.LeveledMessage#getDetail() detail} attribute.
     *
     * @param value The value for detail
     * @return A modified copy of {@code this} object
     */
    public final ImmutableLeveledMessage withDetail(String value) {
        String newValue = Objects.requireNonNull(value, "detail");
        if (Objects.equals(this.detail, newValue)) {
            return this;
        }
        return validate(
                new ImmutableLeveledMessage(this, this.metadata, this.level,
                        this.code, this.message, newValue));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.bulletin.LeveledMessage#getDetail() detail} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for detail
     * @return A modified copy of {@code this} object
     */
    public final ImmutableLeveledMessage withDetail(Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.detail, value)) {
            return this;
        }
        return validate(
                new ImmutableLeveledMessage(this, this.metadata, this.level,
                        this.code, this.message, value));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableLeveledMessage} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableLeveledMessage
                && equalTo((ImmutableLeveledMessage) another);
    }

    private boolean equalTo(ImmutableLeveledMessage another) {
        return metadata.equals(another.metadata)
                && level.equals(another.level)
                && code.equals(another.code)
                && Objects.equals(message, another.message)
                && Objects.equals(detail, another.detail);
    }

    /**
     * Computes a hash code from attributes: {@code metadata}, {@code level}, {@code code}, {@code message}, {@code detail}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + metadata.hashCode();
        h += (h << 5) + level.hashCode();
        h += (h << 5) + code.hashCode();
        h += (h << 5) + Objects.hashCode(message);
        h += (h << 5) + Objects.hashCode(detail);
        return h;
    }

    /**
     * Prints the immutable value {@code LeveledMessage} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("LeveledMessage{");
        builder.append("metadata=").append(metadata);
        builder.append(", ");
        builder.append("level=").append(level);
        builder.append(", ");
        builder.append("code=").append(code);
        if (message != null) {
            builder.append(", ");
            builder.append("message=").append(message);
        }
        if (detail != null) {
            builder.append(", ");
            builder.append("detail=").append(detail);
        }
        return builder.append("}").toString();
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.bulletin.LeveledMessage LeveledMessage}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private static final long OPT_BIT_METADATA = 0x1L;
        private long optBits;

        private Map<String, String> metadata =
                new LinkedHashMap<String, String>();
        private LeveledMessage.Level level;
        private LeveledMessage.Code code;
        private String message;
        private String detail;

        /**
         * Creates a builder for {@link io.syndesis.common.model.bulletin.LeveledMessage LeveledMessage} instances.
         * <pre>
         * new LeveledMessage.Builder()
         *    .putMetadata|putAllMetadata(String =&gt; String) // {@link io.syndesis.common.model.bulletin.LeveledMessage#getMetadata() metadata} mappings
         *    .level(io.syndesis.common.io.syndesis.common.model.bulletin.LeveledMessage.Level) // optional {@link io.syndesis.common.model.bulletin.LeveledMessage#getLevel() level}
         *    .code(io.syndesis.common.io.syndesis.common.model.bulletin.LeveledMessage.Code) // optional {@link io.syndesis.common.model.bulletin.LeveledMessage#getCode() code}
         *    .message(String) // optional {@link io.syndesis.common.model.bulletin.LeveledMessage#getMessage() message}
         *    .detail(String) // optional {@link io.syndesis.common.model.bulletin.LeveledMessage#getDetail() detail}
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof LeveledMessage.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new LeveledMessage.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.bulletin.LeveledMessage} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final LeveledMessage.Builder createFrom(
                LeveledMessage instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (LeveledMessage.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithMetadata} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final LeveledMessage.Builder createFrom(WithMetadata instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (LeveledMessage.Builder) this;
        }

        private void from(Object object) {
            if (object instanceof LeveledMessage) {
                LeveledMessage instance = (LeveledMessage) object;
                code(instance.getCode());
                Optional<String> detailOptional = instance.getDetail();
                if (detailOptional.isPresent()) {
                    detail(detailOptional);
                }
                Optional<String> messageOptional = instance.getMessage();
                if (messageOptional.isPresent()) {
                    message(messageOptional);
                }
                level(instance.getLevel());
            }
            if (object instanceof WithMetadata) {
                WithMetadata instance = (WithMetadata) object;
                putAllMetadata(instance.getMetadata());
            }
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.bulletin.LeveledMessage#getMetadata() metadata} map.
         *
         * @param key   The key in the metadata map
         * @param value The associated value in the metadata map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final LeveledMessage.Builder putMetadata(String key,
                                                        String value) {
            this.metadata.put(
                    Objects.requireNonNull(key, "metadata key"),
                    Objects.requireNonNull(value, "metadata value"));
            optBits |= OPT_BIT_METADATA;
            return (LeveledMessage.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.bulletin.LeveledMessage#getMetadata() metadata} map. Nulls are not permitted
         *
         * @param entry The key and value entry
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final LeveledMessage.Builder putMetadata(
                Map.Entry<String, ? extends String> entry) {
            String k = entry.getKey();
            String v = entry.getValue();
            this.metadata.put(
                    Objects.requireNonNull(k, "metadata key"),
                    Objects.requireNonNull(v, "metadata value"));
            optBits |= OPT_BIT_METADATA;
            return (LeveledMessage.Builder) this;
        }

        /**
         * Sets or replaces all mappings from the specified map as entries for the {@link io.syndesis.common.model.bulletin.LeveledMessage#getMetadata() metadata} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the metadata map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("metadata")
        public final LeveledMessage.Builder metadata(
                Map<String, ? extends String> entries) {
            this.metadata.clear();
            optBits |= OPT_BIT_METADATA;
            return putAllMetadata(entries);
        }

        /**
         * Put all mappings from the specified map as entries to {@link io.syndesis.common.model.bulletin.LeveledMessage#getMetadata() metadata} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the metadata map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final LeveledMessage.Builder putAllMetadata(
                Map<String, ? extends String> entries) {
            for (Map.Entry<String, ? extends String> e : entries.entrySet()) {
                String k = e.getKey();
                String v = e.getValue();
                this.metadata.put(
                        Objects.requireNonNull(k, "metadata key"),
                        Objects.requireNonNull(v, "metadata value"));
            }
            optBits |= OPT_BIT_METADATA;
            return (LeveledMessage.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.bulletin.LeveledMessage#getLevel() level} attribute.
         * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link io.syndesis.common.model.bulletin.LeveledMessage#getLevel() level}.</em>
         *
         * @param level The value for level
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("level")
        public final LeveledMessage.Builder level(Level level) {
            this.level = Objects.requireNonNull(level, "level");
            return (LeveledMessage.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.bulletin.LeveledMessage#getCode() code} attribute.
         * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link io.syndesis.common.model.bulletin.LeveledMessage#getCode() code}.</em>
         *
         * @param code The value for code
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("code")
        public final LeveledMessage.Builder code(Code code) {
            this.code = Objects.requireNonNull(code, "code");
            return (LeveledMessage.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.bulletin.LeveledMessage#getMessage() message} to message.
         *
         * @param message The value for message
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final LeveledMessage.Builder message(String message) {
            this.message = Objects.requireNonNull(message, "message");
            return (LeveledMessage.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.bulletin.LeveledMessage#getMessage() message} to message.
         *
         * @param message The value for message
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("message")
        public final LeveledMessage.Builder message(Optional<String> message) {
            this.message = message.orElse(null);
            return (LeveledMessage.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.bulletin.LeveledMessage#getDetail() detail} to detail.
         *
         * @param detail The value for detail
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final LeveledMessage.Builder detail(String detail) {
            this.detail = Objects.requireNonNull(detail, "detail");
            return (LeveledMessage.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.bulletin.LeveledMessage#getDetail() detail} to detail.
         *
         * @param detail The value for detail
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("detail")
        public final LeveledMessage.Builder detail(Optional<String> detail) {
            this.detail = detail.orElse(null);
            return (LeveledMessage.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.bulletin.LeveledMessage LeveledMessage}.
         *
         * @return An immutable instance of LeveledMessage
         * @throws IllegalStateException if any required attributes are missing
         */
        public LeveledMessage build() {
            return ImmutableLeveledMessage.validate(
                    new ImmutableLeveledMessage(this));
        }

        private boolean metadataIsSet() {
            return (optBits & OPT_BIT_METADATA) != 0;
        }
    }

    private final class InitShim {
        private byte metadataBuildStage = STAGE_UNINITIALIZED;
        private Map<String, String> metadata;
        private byte levelBuildStage = STAGE_UNINITIALIZED;
        private Level level;
        private byte codeBuildStage = STAGE_UNINITIALIZED;
        private Code code;

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

        Level getLevel() {
            if (levelBuildStage == STAGE_INITIALIZING) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (levelBuildStage == STAGE_UNINITIALIZED) {
                levelBuildStage = STAGE_INITIALIZING;
                this.level =
                        Objects.requireNonNull(getLevelInitialize(), "level");
                levelBuildStage = STAGE_INITIALIZED;
            }
            return this.level;
        }

        void level(Level level) {
            this.level = level;
            levelBuildStage = STAGE_INITIALIZED;
        }

        Code getCode() {
            if (codeBuildStage == STAGE_INITIALIZING) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (codeBuildStage == STAGE_UNINITIALIZED) {
                codeBuildStage = STAGE_INITIALIZING;
                this.code = Objects.requireNonNull(getCodeInitialize(), "code");
                codeBuildStage = STAGE_INITIALIZED;
            }
            return this.code;
        }

        void code(Code code) {
            this.code = code;
            codeBuildStage = STAGE_INITIALIZED;
        }

        private String formatInitCycleMessage() {
            List<String> attributes = new ArrayList<>();
            if (metadataBuildStage == STAGE_INITIALIZING) {
                attributes.add("metadata");
            }
            if (levelBuildStage == STAGE_INITIALIZING) {
                attributes.add("level");
            }
            if (codeBuildStage == STAGE_INITIALIZING) {
                attributes.add("code");
            }
            return "Cannot build LeveledMessage, attribute initializers form cycle "
                    + attributes;
        }
    }
}
