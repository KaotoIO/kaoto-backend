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
package io.syndesis.common.model.openapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Var;
import io.syndesis.common.model.WithMetadata;
import io.syndesis.common.model.WithName;
import io.syndesis.common.model.WithResourceId;
import io.syndesis.common.model.WithVersion;

import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Immutable implementation of {@link io.syndesis.common.model.openapi.OpenApi}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new OpenApi.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableOpenApi.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableOpenApi implements OpenApi {
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private static final byte STAGE_INITIALIZED = 1;
    private final String id;
    private final String name;
    private final int version;
    private final Map<String, String> metadata;
    private final byte[] document;
    @SuppressWarnings("Immutable")
    private transient volatile InitShim initShim = new InitShim();

    private ImmutableOpenApi(
            Optional<String> id,
            String name,
            int version,
            Map<String, ? extends String> metadata,
            byte[] document) {
        this.id = id.orElse(null);
        this.name = name;
        this.version = version;
        this.metadata = createUnmodifiableMap(true, false, metadata);
        this.document = document;
        this.initShim = null;
    }

    private ImmutableOpenApi(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.document = builder.document;
        if (builder.versionIsSet()) {
            initShim.version(builder.version);
        }
        if (builder.metadataIsSet()) {
            initShim.metadata(
                    createUnmodifiableMap(false, false, builder.metadata));
        }
        this.version = initShim.getVersion();
        this.metadata = initShim.getMetadata();
        this.initShim = null;
    }

    private ImmutableOpenApi(
            ImmutableOpenApi original,
            String id,
            String name,
            int version,
            Map<String, String> metadata,
            byte[] document) {
        this.id = id;
        this.name = name;
        this.version = version;
        this.metadata = metadata;
        this.document = document;
        this.initShim = null;
    }

    /**
     * Construct a new immutable {@code OpenApi} instance.
     *
     * @param id       The value for the {@code id} attribute
     * @param name     The value for the {@code name} attribute
     * @param version  The value for the {@code version} attribute
     * @param metadata The value for the {@code metadata} attribute
     * @param document The value for the {@code document} attribute
     * @return An immutable OpenApi instance
     */
    public static OpenApi of(Optional<String> id, String name, int version,
                             Map<String, ? extends String> metadata,
                             byte[] document) {
        return validate(
                new ImmutableOpenApi(id, name, version, metadata, document));
    }

    private static ImmutableOpenApi validate(ImmutableOpenApi instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.openapi.OpenApi} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable OpenApi instance
     */
    public static OpenApi copyOf(OpenApi instance) {
        if (instance instanceof ImmutableOpenApi) {
            return (ImmutableOpenApi) instance;
        }
        return new OpenApi.Builder()
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

    private int getVersionInitialize() {
        return OpenApi.super.getVersion();
    }

    private Map<String, String> getMetadataInitialize() {
        return OpenApi.super.getMetadata();
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
     * @return A cloned {@code document} array
     */
    @JsonProperty("document")
    @Override
    public byte[] getDocument() {
        return document;
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.openapi.OpenApi#getId() id} attribute.
     *
     * @param value The value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableOpenApi withId(String value) {
        String newValue = Objects.requireNonNull(value, "id");
        if (Objects.equals(this.id, newValue)) {
            return this;
        }
        return validate(
                new ImmutableOpenApi(this, newValue, this.name, this.version,
                        this.metadata, this.document));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.openapi.OpenApi#getId() id} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableOpenApi withId(Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.id, value)) {
            return this;
        }
        return validate(
                new ImmutableOpenApi(this, value, this.name, this.version,
                        this.metadata, this.document));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.openapi.OpenApi#getName() name} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for name (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableOpenApi withName(String value) {
        if (Objects.equals(this.name, value)) {
            return this;
        }
        return validate(new ImmutableOpenApi(this, this.id, value, this.version,
                this.metadata, this.document));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.openapi.OpenApi#getVersion() version} attribute.
     * A value equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for version
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableOpenApi withVersion(int value) {
        if (this.version == value) {
            return this;
        }
        return validate(new ImmutableOpenApi(this, this.id, this.name, value,
                this.metadata, this.document));
    }

    /**
     * Copy the current immutable object by replacing the {@link io.syndesis.common.model.openapi.OpenApi#getMetadata() metadata} map with the specified map.
     * Nulls are not permitted as keys or values.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param entries The entries to be added to the metadata map
     * @return A modified copy of {@code this} object
     */
    public final ImmutableOpenApi withMetadata(
            Map<String, ? extends String> entries) {
        if (this.metadata == entries) {
            return this;
        }
        Map<String, String> newValue =
                createUnmodifiableMap(true, false, entries);
        return validate(
                new ImmutableOpenApi(this, this.id, this.name, this.version,
                        newValue, this.document));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.openapi.OpenApi#getDocument() document}.
     * The array is cloned before being saved as attribute values.
     *
     * @param elements The non-null elements for document
     * @return A modified copy of {@code this} object
     */
    public final ImmutableOpenApi withDocument(byte... elements) {
        byte[] newValue = elements == null ? null : elements.clone();
        return validate(
                new ImmutableOpenApi(this, this.id, this.name, this.version,
                        this.metadata, newValue));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableOpenApi} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableOpenApi
                && equalTo((ImmutableOpenApi) another);
    }

    private boolean equalTo(ImmutableOpenApi another) {
        return Objects.equals(id, another.id)
                && Objects.equals(name, another.name)
                && version == another.version
                && metadata.equals(another.metadata)
                && Arrays.equals(document, another.document);
    }

    /**
     * Computes a hash code from attributes: {@code id}, {@code name}, {@code version}, {@code metadata}, {@code document}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(id);
        h += (h << 5) + Objects.hashCode(name);
        h += (h << 5) + version;
        h += (h << 5) + metadata.hashCode();
        h += (h << 5) + Arrays.hashCode(document);
        return h;
    }

    /**
     * Prints the immutable value {@code OpenApi} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("OpenApi{");
        if (id != null) {
            builder.append("id=").append(id);
        }
        if (name != null) {
            if (builder.length() > 8) {
                builder.append(", ");
            }
            builder.append("name=").append(name);
        }
        if (builder.length() > 8) {
            builder.append(", ");
        }
        builder.append("version=").append(version);
        builder.append(", ");
        builder.append("metadata=").append(metadata);
        builder.append(", ");
        builder.append("document=").append(Arrays.toString(document));
        return builder.append("}").toString();
    }

    private Object readResolve() throws ObjectStreamException {
        return validate(this);
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.openapi.OpenApi OpenApi}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private static final long OPT_BIT_VERSION = 0x1L;
        private static final long OPT_BIT_METADATA = 0x2L;
        private long optBits;

        private String id;
        private String name;
        private int version;
        private Map<String, String> metadata =
                new LinkedHashMap<String, String>();
        private byte[] document;

        /**
         * Creates a builder for {@link io.syndesis.common.model.openapi.OpenApi OpenApi} instances.
         * <pre>
         * new OpenApi.Builder()
         *    .id(String) // optional {@link io.syndesis.common.model.openapi.OpenApi#getId() id}
         *    .name(String | null) // nullable {@link io.syndesis.common.model.openapi.OpenApi#getName() name}
         *    .version(int) // optional {@link io.syndesis.common.model.openapi.OpenApi#getVersion() version}
         *    .putMetadata|putAllMetadata(String =&gt; String) // {@link io.syndesis.common.model.openapi.OpenApi#getMetadata() metadata} mappings
         *    .document(byte[] | null) // nullable {@link io.syndesis.common.model.openapi.OpenApi#getDocument() document}
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof OpenApi.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new OpenApi.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithName} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final OpenApi.Builder createFrom(WithName instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (OpenApi.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.openapi.OpenApi} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final OpenApi.Builder createFrom(OpenApi instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (OpenApi.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithVersion} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final OpenApi.Builder createFrom(WithVersion instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (OpenApi.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithMetadata} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final OpenApi.Builder createFrom(WithMetadata instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (OpenApi.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithResourceId} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final OpenApi.Builder createFrom(WithResourceId instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (OpenApi.Builder) this;
        }

        private void from(Object object) {
            if (object instanceof WithName) {
                WithName instance = (WithName) object;
                String nameValue = instance.getName();
                if (nameValue != null) {
                    name(nameValue);
                }
            }
            if (object instanceof OpenApi) {
                OpenApi instance = (OpenApi) object;
                byte[] documentValue = instance.getDocument();
                if (documentValue != null) {
                    document(documentValue);
                }
            }
            if (object instanceof WithVersion) {
                WithVersion instance = (WithVersion) object;
                version(instance.getVersion());
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
         * Initializes the optional value {@link io.syndesis.common.model.openapi.OpenApi#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final OpenApi.Builder id(String id) {
            this.id = Objects.requireNonNull(id, "id");
            return (OpenApi.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.openapi.OpenApi#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("id")
        public final OpenApi.Builder id(Optional<String> id) {
            this.id = id.orElse(null);
            return (OpenApi.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.openapi.OpenApi#getName() name} attribute.
         *
         * @param name The value for name (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("name")
        public final OpenApi.Builder name(String name) {
            this.name = name;
            return (OpenApi.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.openapi.OpenApi#getVersion() version} attribute.
         * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link io.syndesis.common.model.openapi.OpenApi#getVersion() version}.</em>
         *
         * @param version The value for version
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("version")
        public final OpenApi.Builder version(int version) {
            this.version = version;
            optBits |= OPT_BIT_VERSION;
            return (OpenApi.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.openapi.OpenApi#getMetadata() metadata} map.
         *
         * @param key   The key in the metadata map
         * @param value The associated value in the metadata map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final OpenApi.Builder putMetadata(String key, String value) {
            this.metadata.put(
                    Objects.requireNonNull(key, "metadata key"),
                    Objects.requireNonNull(value, "metadata value"));
            optBits |= OPT_BIT_METADATA;
            return (OpenApi.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.openapi.OpenApi#getMetadata() metadata} map. Nulls are not permitted
         *
         * @param entry The key and value entry
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final OpenApi.Builder putMetadata(
                Map.Entry<String, ? extends String> entry) {
            String k = entry.getKey();
            String v = entry.getValue();
            this.metadata.put(
                    Objects.requireNonNull(k, "metadata key"),
                    Objects.requireNonNull(v, "metadata value"));
            optBits |= OPT_BIT_METADATA;
            return (OpenApi.Builder) this;
        }

        /**
         * Sets or replaces all mappings from the specified map as entries for the {@link io.syndesis.common.model.openapi.OpenApi#getMetadata() metadata} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the metadata map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("metadata")
        public final OpenApi.Builder metadata(
                Map<String, ? extends String> entries) {
            this.metadata.clear();
            optBits |= OPT_BIT_METADATA;
            return putAllMetadata(entries);
        }

        /**
         * Put all mappings from the specified map as entries to {@link io.syndesis.common.model.openapi.OpenApi#getMetadata() metadata} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the metadata map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final OpenApi.Builder putAllMetadata(
                Map<String, ? extends String> entries) {
            for (Map.Entry<String, ? extends String> e : entries.entrySet()) {
                String k = e.getKey();
                String v = e.getValue();
                this.metadata.put(
                        Objects.requireNonNull(k, "metadata key"),
                        Objects.requireNonNull(v, "metadata value"));
            }
            optBits |= OPT_BIT_METADATA;
            return (OpenApi.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.openapi.OpenApi#getDocument() document} attribute.
         *
         * @param document The elements for document
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("document")
        public final OpenApi.Builder document(byte... document) {
            this.document = document;
            return (OpenApi.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.openapi.OpenApi OpenApi}.
         *
         * @return An immutable instance of OpenApi
         * @throws IllegalStateException if any required attributes are missing
         */
        public OpenApi build() {
            return ImmutableOpenApi.validate(new ImmutableOpenApi(this));
        }

        private boolean versionIsSet() {
            return (optBits & OPT_BIT_VERSION) != 0;
        }

        private boolean metadataIsSet() {
            return (optBits & OPT_BIT_METADATA) != 0;
        }
    }

    private final class InitShim {
        private byte versionBuildStage = STAGE_UNINITIALIZED;
        private int version;
        private byte metadataBuildStage = STAGE_UNINITIALIZED;
        private Map<String, String> metadata;

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
            if (versionBuildStage == STAGE_INITIALIZING) {
                attributes.add("version");
            }
            if (metadataBuildStage == STAGE_INITIALIZING) {
                attributes.add("metadata");
            }
            return "Cannot build OpenApi, attribute initializers form cycle "
                    + attributes;
        }
    }
}
