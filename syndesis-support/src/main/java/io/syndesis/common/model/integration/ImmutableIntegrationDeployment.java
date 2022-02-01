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
import io.syndesis.common.model.WithModificationTimestamps;
import io.syndesis.common.model.WithResourceId;
import io.syndesis.common.model.WithVersion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Immutable implementation of {@link io.syndesis.common.model.integration.IntegrationDeployment}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new IntegrationDeployment.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableIntegrationDeployment.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableIntegrationDeployment
        implements IntegrationDeployment {
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private static final byte STAGE_INITIALIZED = 1;
    private final String id;
    private final int version;
    private final long createdAt;
    private final long updatedAt;
    private final String userId;
    private final IntegrationDeploymentState currentState;
    private final IntegrationDeploymentState targetState;
    private final Map<String, String> stepsDone;
    private final String statusMessage;
    private final IntegrationDeploymentError error;
    private final Optional<String> integrationId;
    private final Integration spec;
    @SuppressWarnings("Immutable")
    private transient volatile InitShim initShim = new InitShim();

    private ImmutableIntegrationDeployment(
            Optional<String> id,
            int version,
            long createdAt,
            long updatedAt,
            Optional<String> userId,
            IntegrationDeploymentState currentState,
            IntegrationDeploymentState targetState,
            Map<String, ? extends String> stepsDone,
            Optional<String> statusMessage,
            IntegrationDeploymentError error,
            Optional<String> integrationId,
            Integration spec) {
        this.id = id.orElse(null);
        this.version = version;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userId = userId.orElse(null);
        this.currentState =
                Objects.requireNonNull(currentState, "currentState");
        this.targetState = Objects.requireNonNull(targetState, "targetState");
        this.stepsDone = createUnmodifiableMap(true, false, stepsDone);
        this.statusMessage = statusMessage.orElse(null);
        this.error = error;
        this.integrationId =
                Objects.requireNonNull(integrationId, "integrationId");
        this.spec = spec;
        this.initShim = null;
    }

    private ImmutableIntegrationDeployment(Builder builder) {
        this.id = builder.id;
        this.userId = builder.userId;
        this.statusMessage = builder.statusMessage;
        this.error = builder.error;
        this.spec = builder.spec;
        if (builder.versionIsSet()) {
            initShim.version(builder.version);
        }
        if (builder.createdAtIsSet()) {
            initShim.createdAt(builder.createdAt);
        }
        if (builder.updatedAtIsSet()) {
            initShim.updatedAt(builder.updatedAt);
        }
        if (builder.currentState != null) {
            initShim.currentState(builder.currentState);
        }
        if (builder.targetState != null) {
            initShim.targetState(builder.targetState);
        }
        if (builder.stepsDoneIsSet()) {
            initShim.stepsDone(
                    createUnmodifiableMap(false, false, builder.stepsDone));
        }
        if (builder.integrationId != null) {
            initShim.integrationId(builder.integrationId);
        }
        this.version = initShim.getVersion();
        this.createdAt = initShim.getCreatedAt();
        this.updatedAt = initShim.getUpdatedAt();
        this.currentState = initShim.getCurrentState();
        this.targetState = initShim.getTargetState();
        this.stepsDone = initShim.getStepsDone();
        this.integrationId = initShim.getIntegrationId();
        this.initShim = null;
    }

    private ImmutableIntegrationDeployment(
            ImmutableIntegrationDeployment original,
            String id,
            int version,
            long createdAt,
            long updatedAt,
            String userId,
            IntegrationDeploymentState currentState,
            IntegrationDeploymentState targetState,
            Map<String, String> stepsDone,
            String statusMessage,
            IntegrationDeploymentError error,
            Optional<String> integrationId,
            Integration spec) {
        this.id = id;
        this.version = version;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userId = userId;
        this.currentState = currentState;
        this.targetState = targetState;
        this.stepsDone = stepsDone;
        this.statusMessage = statusMessage;
        this.error = error;
        this.integrationId = integrationId;
        this.spec = spec;
        this.initShim = null;
    }

    /**
     * Construct a new immutable {@code IntegrationDeployment} instance.
     *
     * @param id            The value for the {@code id} attribute
     * @param version       The value for the {@code version} attribute
     * @param createdAt     The value for the {@code createdAt} attribute
     * @param updatedAt     The value for the {@code updatedAt} attribute
     * @param userId        The value for the {@code userId} attribute
     * @param currentState  The value for the {@code currentState} attribute
     * @param targetState   The value for the {@code targetState} attribute
     * @param stepsDone     The value for the {@code stepsDone} attribute
     * @param statusMessage The value for the {@code statusMessage} attribute
     * @param error         The value for the {@code error} attribute
     * @param integrationId The value for the {@code integrationId} attribute
     * @param spec          The value for the {@code spec} attribute
     * @return An immutable IntegrationDeployment instance
     */
    public static IntegrationDeployment of(Optional<String> id, int version,
                                           long createdAt, long updatedAt,
                                           Optional<String> userId,
                                           IntegrationDeploymentState currentState,
                                           IntegrationDeploymentState targetState,
                                           Map<String, ? extends String> stepsDone,
                                           Optional<String> statusMessage,
                                           IntegrationDeploymentError error,
                                           Optional<String> integrationId,
                                           Integration spec) {
        return validate(
                new ImmutableIntegrationDeployment(id, version, createdAt,
                        updatedAt, userId, currentState, targetState, stepsDone,
                        statusMessage, error, integrationId, spec));
    }

    private static ImmutableIntegrationDeployment validate(
            ImmutableIntegrationDeployment instance) {

        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.integration.IntegrationDeployment} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable IntegrationDeployment instance
     */
    public static IntegrationDeployment copyOf(IntegrationDeployment instance) {
        if (instance instanceof ImmutableIntegrationDeployment) {
            return (ImmutableIntegrationDeployment) instance;
        }
        return new IntegrationDeployment.Builder()
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
        return IntegrationDeployment.super.getVersion();
    }

    private long getCreatedAtInitialize() {
        return IntegrationDeployment.super.getCreatedAt();
    }

    private long getUpdatedAtInitialize() {
        return IntegrationDeployment.super.getUpdatedAt();
    }

    private IntegrationDeploymentState getCurrentStateInitialize() {
        return IntegrationDeployment.super.getCurrentState();
    }

    private IntegrationDeploymentState getTargetStateInitialize() {
        return IntegrationDeployment.super.getTargetState();
    }

    private Map<String, String> getStepsDoneInitialize() {
        return IntegrationDeployment.super.getStepsDone();
    }

    private Optional<String> getIntegrationIdInitialize() {
        return IntegrationDeployment.super.getIntegrationId();
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
     * @return The value of the {@code userId} attribute
     */
    @JsonProperty("userId")
    @Override
    public Optional<String> getUserId() {
        return Optional.ofNullable(userId);
    }

    /**
     * @return The value of the {@code currentState} attribute
     */
    @JsonProperty("currentState")
    @Override
    public IntegrationDeploymentState getCurrentState() {
        InitShim shim = this.initShim;
        return shim != null
                ? shim.getCurrentState()
                : this.currentState;
    }

    /**
     * @return The value of the {@code targetState} attribute
     */
    @JsonProperty("targetState")
    @Override
    public IntegrationDeploymentState getTargetState() {
        InitShim shim = this.initShim;
        return shim != null
                ? shim.getTargetState()
                : this.targetState;
    }

    /**
     * @return The value of the {@code stepsDone} attribute
     */
    @JsonProperty("stepsDone")
    @Override
    public Map<String, String> getStepsDone() {
        InitShim shim = this.initShim;
        return shim != null
                ? shim.getStepsDone()
                : this.stepsDone;
    }

    /**
     * @return The value of the {@code statusMessage} attribute
     */
    @JsonProperty("statusMessage")
    @Override
    public Optional<String> getStatusMessage() {
        return Optional.ofNullable(statusMessage);
    }

    /**
     * @return The value of the {@code error} attribute
     */
    @JsonProperty("error")
    @Override
    public IntegrationDeploymentError getError() {
        return error;
    }

    /**
     * @return The value of the {@code integrationId} attribute
     */
    @JsonProperty("integrationId")
    @Override
    public Optional<String> getIntegrationId() {
        InitShim shim = this.initShim;
        return shim != null
                ? shim.getIntegrationId()
                : this.integrationId;
    }

    /**
     * @return The value of the {@code spec} attribute
     */
    @JsonProperty("spec")
    @Override
    public Integration getSpec() {
        return spec;
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.integration.IntegrationDeployment#getId() id} attribute.
     *
     * @param value The value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegrationDeployment withId(String value) {
        String newValue = Objects.requireNonNull(value, "id");
        if (Objects.equals(this.id, newValue)) {
            return this;
        }
        return validate(new ImmutableIntegrationDeployment(
                this,
                newValue,
                this.version,
                this.createdAt,
                this.updatedAt,
                this.userId,
                this.currentState,
                this.targetState,
                this.stepsDone,
                this.statusMessage,
                this.error,
                this.integrationId,
                this.spec));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.integration.IntegrationDeployment#getId() id} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegrationDeployment withId(
            Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.id, value)) {
            return this;
        }
        return validate(new ImmutableIntegrationDeployment(
                this,
                value,
                this.version,
                this.createdAt,
                this.updatedAt,
                this.userId,
                this.currentState,
                this.targetState,
                this.stepsDone,
                this.statusMessage,
                this.error,
                this.integrationId,
                this.spec));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.integration.IntegrationDeployment#getVersion() version} attribute.
     * A value equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for version
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableIntegrationDeployment withVersion(int value) {
        if (this.version == value) {
            return this;
        }
        return validate(new ImmutableIntegrationDeployment(
                this,
                this.id,
                value,
                this.createdAt,
                this.updatedAt,
                this.userId,
                this.currentState,
                this.targetState,
                this.stepsDone,
                this.statusMessage,
                this.error,
                this.integrationId,
                this.spec));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.integration.IntegrationDeployment#getCreatedAt() createdAt} attribute.
     * A value equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for createdAt
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableIntegrationDeployment withCreatedAt(long value) {
        if (this.createdAt == value) {
            return this;
        }
        return validate(new ImmutableIntegrationDeployment(
                this,
                this.id,
                this.version,
                value,
                this.updatedAt,
                this.userId,
                this.currentState,
                this.targetState,
                this.stepsDone,
                this.statusMessage,
                this.error,
                this.integrationId,
                this.spec));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.integration.IntegrationDeployment#getUpdatedAt() updatedAt} attribute.
     * A value equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for updatedAt
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableIntegrationDeployment withUpdatedAt(long value) {
        if (this.updatedAt == value) {
            return this;
        }
        return validate(new ImmutableIntegrationDeployment(
                this,
                this.id,
                this.version,
                this.createdAt,
                value,
                this.userId,
                this.currentState,
                this.targetState,
                this.stepsDone,
                this.statusMessage,
                this.error,
                this.integrationId,
                this.spec));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.integration.IntegrationDeployment#getUserId() userId} attribute.
     *
     * @param value The value for userId
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegrationDeployment withUserId(String value) {
        String newValue = Objects.requireNonNull(value, "userId");
        if (Objects.equals(this.userId, newValue)) {
            return this;
        }
        return validate(new ImmutableIntegrationDeployment(
                this,
                this.id,
                this.version,
                this.createdAt,
                this.updatedAt,
                newValue,
                this.currentState,
                this.targetState,
                this.stepsDone,
                this.statusMessage,
                this.error,
                this.integrationId,
                this.spec));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.integration.IntegrationDeployment#getUserId() userId} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for userId
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegrationDeployment withUserId(
            Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.userId, value)) {
            return this;
        }
        return validate(new ImmutableIntegrationDeployment(
                this,
                this.id,
                this.version,
                this.createdAt,
                this.updatedAt,
                value,
                this.currentState,
                this.targetState,
                this.stepsDone,
                this.statusMessage,
                this.error,
                this.integrationId,
                this.spec));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.integration.IntegrationDeployment#getCurrentState() currentState} attribute.
     * A value equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for currentState
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableIntegrationDeployment withCurrentState(
            IntegrationDeploymentState value) {
        if (this.currentState == value) {
            return this;
        }
        IntegrationDeploymentState newValue =
                Objects.requireNonNull(value, "currentState");
        if (this.currentState.equals(newValue)) {
            return this;
        }
        return validate(new ImmutableIntegrationDeployment(
                this,
                this.id,
                this.version,
                this.createdAt,
                this.updatedAt,
                this.userId,
                newValue,
                this.targetState,
                this.stepsDone,
                this.statusMessage,
                this.error,
                this.integrationId,
                this.spec));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.integration.IntegrationDeployment#getTargetState() targetState} attribute.
     * A value equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for targetState
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableIntegrationDeployment withTargetState(
            IntegrationDeploymentState value) {
        if (this.targetState == value) {
            return this;
        }
        IntegrationDeploymentState newValue =
                Objects.requireNonNull(value, "targetState");
        if (this.targetState.equals(newValue)) {
            return this;
        }
        return validate(new ImmutableIntegrationDeployment(
                this,
                this.id,
                this.version,
                this.createdAt,
                this.updatedAt,
                this.userId,
                this.currentState,
                newValue,
                this.stepsDone,
                this.statusMessage,
                this.error,
                this.integrationId,
                this.spec));
    }

    /**
     * Copy the current immutable object by replacing the {@link io.syndesis.common.model.integration.IntegrationDeployment#getStepsDone() stepsDone} map with the specified map.
     * Nulls are not permitted as keys or values.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param entries The entries to be added to the stepsDone map
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegrationDeployment withStepsDone(
            Map<String, ? extends String> entries) {
        if (this.stepsDone == entries) {
            return this;
        }
        Map<String, String> newValue =
                createUnmodifiableMap(true, false, entries);
        return validate(new ImmutableIntegrationDeployment(
                this,
                this.id,
                this.version,
                this.createdAt,
                this.updatedAt,
                this.userId,
                this.currentState,
                this.targetState,
                newValue,
                this.statusMessage,
                this.error,
                this.integrationId,
                this.spec));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.integration.IntegrationDeployment#getStatusMessage() statusMessage} attribute.
     *
     * @param value The value for statusMessage
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegrationDeployment withStatusMessage(
            String value) {
        String newValue = Objects.requireNonNull(value, "statusMessage");
        if (Objects.equals(this.statusMessage, newValue)) {
            return this;
        }
        return validate(new ImmutableIntegrationDeployment(
                this,
                this.id,
                this.version,
                this.createdAt,
                this.updatedAt,
                this.userId,
                this.currentState,
                this.targetState,
                this.stepsDone,
                newValue,
                this.error,
                this.integrationId,
                this.spec));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.integration.IntegrationDeployment#getStatusMessage() statusMessage} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for statusMessage
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegrationDeployment withStatusMessage(
            Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.statusMessage, value)) {
            return this;
        }
        return validate(new ImmutableIntegrationDeployment(
                this,
                this.id,
                this.version,
                this.createdAt,
                this.updatedAt,
                this.userId,
                this.currentState,
                this.targetState,
                this.stepsDone,
                value,
                this.error,
                this.integrationId,
                this.spec));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.integration.IntegrationDeployment#getError() error} attribute.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for error (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableIntegrationDeployment withError(
            IntegrationDeploymentError value) {
        if (this.error == value) {
            return this;
        }
        return validate(new ImmutableIntegrationDeployment(
                this,
                this.id,
                this.version,
                this.createdAt,
                this.updatedAt,
                this.userId,
                this.currentState,
                this.targetState,
                this.stepsDone,
                this.statusMessage,
                value,
                this.integrationId,
                this.spec));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.integration.IntegrationDeployment#getIntegrationId() integrationId} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for integrationId
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableIntegrationDeployment withIntegrationId(
            Optional<String> value) {
        Optional<String> newValue =
                Objects.requireNonNull(value, "integrationId");
        if (this.integrationId.equals(newValue)) {
            return this;
        }
        return validate(new ImmutableIntegrationDeployment(
                this,
                this.id,
                this.version,
                this.createdAt,
                this.updatedAt,
                this.userId,
                this.currentState,
                this.targetState,
                this.stepsDone,
                this.statusMessage,
                this.error,
                newValue,
                this.spec));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.integration.IntegrationDeployment#getSpec() spec} attribute.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for spec (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableIntegrationDeployment withSpec(Integration value) {
        if (this.spec == value) {
            return this;
        }
        return validate(new ImmutableIntegrationDeployment(
                this,
                this.id,
                this.version,
                this.createdAt,
                this.updatedAt,
                this.userId,
                this.currentState,
                this.targetState,
                this.stepsDone,
                this.statusMessage,
                this.error,
                this.integrationId,
                value));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableIntegrationDeployment} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableIntegrationDeployment
                && equalTo((ImmutableIntegrationDeployment) another);
    }

    private boolean equalTo(ImmutableIntegrationDeployment another) {
        return Objects.equals(id, another.id)
                && version == another.version
                && createdAt == another.createdAt
                && updatedAt == another.updatedAt
                && Objects.equals(userId, another.userId)
                && currentState.equals(another.currentState)
                && targetState.equals(another.targetState)
                && stepsDone.equals(another.stepsDone)
                && Objects.equals(statusMessage, another.statusMessage)
                && Objects.equals(error, another.error)
                && integrationId.equals(another.integrationId)
                && Objects.equals(spec, another.spec);
    }

    /**
     * Computes a hash code from attributes: {@code id}, {@code version}, {@code createdAt}, {@code updatedAt}, {@code userId}, {@code currentState}, {@code targetState}, {@code stepsDone}, {@code statusMessage}, {@code error}, {@code integrationId}, {@code spec}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(id);
        h += (h << 5) + version;
        h += (h << 5) + Long.hashCode(createdAt);
        h += (h << 5) + Long.hashCode(updatedAt);
        h += (h << 5) + Objects.hashCode(userId);
        h += (h << 5) + currentState.hashCode();
        h += (h << 5) + targetState.hashCode();
        h += (h << 5) + stepsDone.hashCode();
        h += (h << 5) + Objects.hashCode(statusMessage);
        h += (h << 5) + Objects.hashCode(error);
        h += (h << 5) + integrationId.hashCode();
        h += (h << 5) + Objects.hashCode(spec);
        return h;
    }

    /**
     * Prints the immutable value {@code IntegrationDeployment} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("IntegrationDeployment{");
        if (id != null) {
            builder.append("id=").append(id);
        }
        if (builder.length() > 22) {
            builder.append(", ");
        }
        builder.append("version=").append(version);
        builder.append(", ");
        builder.append("createdAt=").append(createdAt);
        builder.append(", ");
        builder.append("updatedAt=").append(updatedAt);
        if (userId != null) {
            builder.append(", ");
            builder.append("userId=").append(userId);
        }
        builder.append(", ");
        builder.append("currentState=").append(currentState);
        builder.append(", ");
        builder.append("targetState=").append(targetState);
        builder.append(", ");
        builder.append("stepsDone=").append(stepsDone);
        if (statusMessage != null) {
            builder.append(", ");
            builder.append("statusMessage=").append(statusMessage);
        }
        if (error != null) {
            builder.append(", ");
            builder.append("error=").append(error);
        }
        builder.append(", ");
        builder.append("integrationId=").append(integrationId);
        if (spec != null) {
            builder.append(", ");
            builder.append("spec=").append(spec);
        }
        return builder.append("}").toString();
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.integration.IntegrationDeployment IntegrationDeployment}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private static final long OPT_BIT_VERSION = 0x1L;
        private static final long OPT_BIT_CREATED_AT = 0x2L;
        private static final long OPT_BIT_UPDATED_AT = 0x4L;
        private static final long OPT_BIT_STEPS_DONE = 0x8L;
        private long optBits;

        private String id;
        private int version;
        private long createdAt;
        private long updatedAt;
        private String userId;
        private IntegrationDeploymentState currentState;
        private IntegrationDeploymentState targetState;
        private Map<String, String> stepsDone =
                new LinkedHashMap<String, String>();
        private String statusMessage;
        private IntegrationDeploymentError error;
        private Optional<String> integrationId;
        private Integration spec;

        /**
         * Creates a builder for {@link io.syndesis.common.model.integration.IntegrationDeployment IntegrationDeployment} instances.
         * <pre>
         * new IntegrationDeployment.Builder()
         *    .id(String) // optional {@link io.syndesis.common.model.integration.IntegrationDeployment#getId() id}
         *    .version(int) // optional {@link io.syndesis.common.model.integration.IntegrationDeployment#getVersion() version}
         *    .createdAt(long) // optional {@link io.syndesis.common.model.integration.IntegrationDeployment#getCreatedAt() createdAt}
         *    .updatedAt(long) // optional {@link io.syndesis.common.model.integration.IntegrationDeployment#getUpdatedAt() updatedAt}
         *    .userId(String) // optional {@link io.syndesis.common.model.integration.IntegrationDeployment#getUserId() userId}
         *    .currentState(io.syndesis.common.io.syndesis.common.model.integration.IntegrationDeploymentState) // optional {@link io.syndesis.common.model.integration.IntegrationDeployment#getCurrentState() currentState}
         *    .targetState(io.syndesis.common.io.syndesis.common.model.integration.IntegrationDeploymentState) // optional {@link io.syndesis.common.model.integration.IntegrationDeployment#getTargetState() targetState}
         *    .putStepsDone|putAllStepsDone(String =&gt; String) // {@link io.syndesis.common.model.integration.IntegrationDeployment#getStepsDone() stepsDone} mappings
         *    .statusMessage(String) // optional {@link io.syndesis.common.model.integration.IntegrationDeployment#getStatusMessage() statusMessage}
         *    .error(io.syndesis.common.io.syndesis.common.model.integration.IntegrationDeploymentError | null) // nullable {@link io.syndesis.common.model.integration.IntegrationDeployment#getError() error}
         *    .integrationId(Optional&amp;lt;String&amp;gt;) // optional {@link io.syndesis.common.model.integration.IntegrationDeployment#getIntegrationId() integrationId}
         *    .spec(io.syndesis.common.io.syndesis.common.model.integration.Integration | null) // nullable {@link io.syndesis.common.model.integration.IntegrationDeployment#getSpec() spec}
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof IntegrationDeployment.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new IntegrationDeployment.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithVersion} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationDeployment.Builder createFrom(
                WithVersion instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (IntegrationDeployment.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.integration.IntegrationDeploymentBase} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationDeployment.Builder createFrom(
                IntegrationDeploymentBase instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (IntegrationDeployment.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithResourceId} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationDeployment.Builder createFrom(
                WithResourceId instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (IntegrationDeployment.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithModificationTimestamps} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationDeployment.Builder createFrom(
                WithModificationTimestamps instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (IntegrationDeployment.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.integration.IntegrationDeployment} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationDeployment.Builder createFrom(
                IntegrationDeployment instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (IntegrationDeployment.Builder) this;
        }

        private void from(Object object) {
            if (object instanceof WithVersion) {
                WithVersion instance = (WithVersion) object;
                version(instance.getVersion());
            }
            if (object instanceof IntegrationDeploymentBase) {
                IntegrationDeploymentBase instance =
                        (IntegrationDeploymentBase) object;
                currentState(instance.getCurrentState());
                IntegrationDeploymentError errorValue = instance.getError();
                if (errorValue != null) {
                    error(errorValue);
                }
                Optional<String> userIdOptional = instance.getUserId();
                if (userIdOptional.isPresent()) {
                    userId(userIdOptional);
                }
                targetState(instance.getTargetState());
                putAllStepsDone(instance.getStepsDone());
                Optional<String> statusMessageOptional =
                        instance.getStatusMessage();
                if (statusMessageOptional.isPresent()) {
                    statusMessage(statusMessageOptional);
                }
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
            if (object instanceof IntegrationDeployment) {
                IntegrationDeployment instance = (IntegrationDeployment) object;
                integrationId(instance.getIntegrationId());
                Integration specValue = instance.getSpec();
                if (specValue != null) {
                    spec(specValue);
                }
            }
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.integration.IntegrationDeployment#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationDeployment.Builder id(String id) {
            this.id = Objects.requireNonNull(id, "id");
            return (IntegrationDeployment.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.integration.IntegrationDeployment#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("id")
        public final IntegrationDeployment.Builder id(Optional<String> id) {
            this.id = id.orElse(null);
            return (IntegrationDeployment.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.integration.IntegrationDeployment#getVersion() version} attribute.
         * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link io.syndesis.common.model.integration.IntegrationDeployment#getVersion() version}.</em>
         *
         * @param version The value for version
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("version")
        public final IntegrationDeployment.Builder version(int version) {
            this.version = version;
            optBits |= OPT_BIT_VERSION;
            return (IntegrationDeployment.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.integration.IntegrationDeployment#getCreatedAt() createdAt} attribute.
         * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link io.syndesis.common.model.integration.IntegrationDeployment#getCreatedAt() createdAt}.</em>
         *
         * @param createdAt The value for createdAt
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("createdAt")
        public final IntegrationDeployment.Builder createdAt(long createdAt) {
            this.createdAt = createdAt;
            optBits |= OPT_BIT_CREATED_AT;
            return (IntegrationDeployment.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.integration.IntegrationDeployment#getUpdatedAt() updatedAt} attribute.
         * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link io.syndesis.common.model.integration.IntegrationDeployment#getUpdatedAt() updatedAt}.</em>
         *
         * @param updatedAt The value for updatedAt
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("updatedAt")
        public final IntegrationDeployment.Builder updatedAt(long updatedAt) {
            this.updatedAt = updatedAt;
            optBits |= OPT_BIT_UPDATED_AT;
            return (IntegrationDeployment.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.integration.IntegrationDeployment#getUserId() userId} to userId.
         *
         * @param userId The value for userId
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationDeployment.Builder userId(String userId) {
            this.userId = Objects.requireNonNull(userId, "userId");
            return (IntegrationDeployment.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.integration.IntegrationDeployment#getUserId() userId} to userId.
         *
         * @param userId The value for userId
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("userId")
        public final IntegrationDeployment.Builder userId(
                Optional<String> userId) {
            this.userId = userId.orElse(null);
            return (IntegrationDeployment.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.integration.IntegrationDeployment#getCurrentState() currentState} attribute.
         * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link io.syndesis.common.model.integration.IntegrationDeployment#getCurrentState() currentState}.</em>
         *
         * @param currentState The value for currentState
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("currentState")
        public final IntegrationDeployment.Builder currentState(
                IntegrationDeploymentState currentState) {
            this.currentState =
                    Objects.requireNonNull(currentState, "currentState");
            return (IntegrationDeployment.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.integration.IntegrationDeployment#getTargetState() targetState} attribute.
         * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link io.syndesis.common.model.integration.IntegrationDeployment#getTargetState() targetState}.</em>
         *
         * @param targetState The value for targetState
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("targetState")
        public final IntegrationDeployment.Builder targetState(
                IntegrationDeploymentState targetState) {
            this.targetState =
                    Objects.requireNonNull(targetState, "targetState");
            return (IntegrationDeployment.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.integration.IntegrationDeployment#getStepsDone() stepsDone} map.
         *
         * @param key   The key in the stepsDone map
         * @param value The associated value in the stepsDone map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationDeployment.Builder putStepsDone(String key,
                                                                String value) {
            this.stepsDone.put(
                    Objects.requireNonNull(key, "stepsDone key"),
                    Objects.requireNonNull(value, "stepsDone value"));
            optBits |= OPT_BIT_STEPS_DONE;
            return (IntegrationDeployment.Builder) this;
        }

        /**
         * Put one entry to the {@link io.syndesis.common.model.integration.IntegrationDeployment#getStepsDone() stepsDone} map. Nulls are not permitted
         *
         * @param entry The key and value entry
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationDeployment.Builder putStepsDone(
                Map.Entry<String, ? extends String> entry) {
            String k = entry.getKey();
            String v = entry.getValue();
            this.stepsDone.put(
                    Objects.requireNonNull(k, "stepsDone key"),
                    Objects.requireNonNull(v, "stepsDone value"));
            optBits |= OPT_BIT_STEPS_DONE;
            return (IntegrationDeployment.Builder) this;
        }

        /**
         * Sets or replaces all mappings from the specified map as entries for the {@link io.syndesis.common.model.integration.IntegrationDeployment#getStepsDone() stepsDone} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the stepsDone map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("stepsDone")
        public final IntegrationDeployment.Builder stepsDone(
                Map<String, ? extends String> entries) {
            this.stepsDone.clear();
            optBits |= OPT_BIT_STEPS_DONE;
            return putAllStepsDone(entries);
        }

        /**
         * Put all mappings from the specified map as entries to {@link io.syndesis.common.model.integration.IntegrationDeployment#getStepsDone() stepsDone} map. Nulls are not permitted
         *
         * @param entries The entries that will be added to the stepsDone map
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationDeployment.Builder putAllStepsDone(
                Map<String, ? extends String> entries) {
            for (Map.Entry<String, ? extends String> e : entries.entrySet()) {
                String k = e.getKey();
                String v = e.getValue();
                this.stepsDone.put(
                        Objects.requireNonNull(k, "stepsDone key"),
                        Objects.requireNonNull(v, "stepsDone value"));
            }
            optBits |= OPT_BIT_STEPS_DONE;
            return (IntegrationDeployment.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.integration.IntegrationDeployment#getStatusMessage() statusMessage} to statusMessage.
         *
         * @param statusMessage The value for statusMessage
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationDeployment.Builder statusMessage(
                String statusMessage) {
            this.statusMessage =
                    Objects.requireNonNull(statusMessage, "statusMessage");
            return (IntegrationDeployment.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.integration.IntegrationDeployment#getStatusMessage() statusMessage} to statusMessage.
         *
         * @param statusMessage The value for statusMessage
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("statusMessage")
        public final IntegrationDeployment.Builder statusMessage(
                Optional<String> statusMessage) {
            this.statusMessage = statusMessage.orElse(null);
            return (IntegrationDeployment.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.integration.IntegrationDeployment#getError() error} attribute.
         *
         * @param error The value for error (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("error")
        public final IntegrationDeployment.Builder error(
                IntegrationDeploymentError error) {
            this.error = error;
            return (IntegrationDeployment.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.integration.IntegrationDeployment#getIntegrationId() integrationId} attribute.
         * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link io.syndesis.common.model.integration.IntegrationDeployment#getIntegrationId() integrationId}.</em>
         *
         * @param integrationId The value for integrationId
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("integrationId")
        public final IntegrationDeployment.Builder integrationId(
                Optional<String> integrationId) {
            this.integrationId =
                    Objects.requireNonNull(integrationId, "integrationId");
            return (IntegrationDeployment.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.integration.IntegrationDeployment#getSpec() spec} attribute.
         *
         * @param spec The value for spec (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("spec")
        public final IntegrationDeployment.Builder spec(Integration spec) {
            this.spec = spec;
            return (IntegrationDeployment.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.integration.IntegrationDeployment IntegrationDeployment}.
         *
         * @return An immutable instance of IntegrationDeployment
         * @throws IllegalStateException if any required attributes are missing
         */
        public IntegrationDeployment build() {
            return ImmutableIntegrationDeployment.validate(
                    new ImmutableIntegrationDeployment(this));
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

        private boolean stepsDoneIsSet() {
            return (optBits & OPT_BIT_STEPS_DONE) != 0;
        }
    }

    private final class InitShim {
        private byte versionBuildStage = STAGE_UNINITIALIZED;
        private int version;
        private byte createdAtBuildStage = STAGE_UNINITIALIZED;
        private long createdAt;
        private byte updatedAtBuildStage = STAGE_UNINITIALIZED;
        private long updatedAt;
        private byte currentStateBuildStage = STAGE_UNINITIALIZED;
        private IntegrationDeploymentState currentState;
        private byte targetStateBuildStage = STAGE_UNINITIALIZED;
        private IntegrationDeploymentState targetState;
        private byte stepsDoneBuildStage = STAGE_UNINITIALIZED;
        private Map<String, String> stepsDone;
        private byte integrationIdBuildStage = STAGE_UNINITIALIZED;
        private Optional<String> integrationId;

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

        IntegrationDeploymentState getCurrentState() {
            if (currentStateBuildStage == STAGE_INITIALIZING) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (currentStateBuildStage == STAGE_UNINITIALIZED) {
                currentStateBuildStage = STAGE_INITIALIZING;
                this.currentState =
                        Objects.requireNonNull(getCurrentStateInitialize(),
                                "currentState");
                currentStateBuildStage = STAGE_INITIALIZED;
            }
            return this.currentState;
        }

        void currentState(IntegrationDeploymentState currentState) {
            this.currentState = currentState;
            currentStateBuildStage = STAGE_INITIALIZED;
        }

        IntegrationDeploymentState getTargetState() {
            if (targetStateBuildStage == STAGE_INITIALIZING) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (targetStateBuildStage == STAGE_UNINITIALIZED) {
                targetStateBuildStage = STAGE_INITIALIZING;
                this.targetState =
                        Objects.requireNonNull(getTargetStateInitialize(),
                                "targetState");
                targetStateBuildStage = STAGE_INITIALIZED;
            }
            return this.targetState;
        }

        void targetState(IntegrationDeploymentState targetState) {
            this.targetState = targetState;
            targetStateBuildStage = STAGE_INITIALIZED;
        }

        Map<String, String> getStepsDone() {
            if (stepsDoneBuildStage == STAGE_INITIALIZING) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (stepsDoneBuildStage == STAGE_UNINITIALIZED) {
                stepsDoneBuildStage = STAGE_INITIALIZING;
                this.stepsDone = createUnmodifiableMap(true, false,
                        getStepsDoneInitialize());
                stepsDoneBuildStage = STAGE_INITIALIZED;
            }
            return this.stepsDone;
        }

        void stepsDone(Map<String, String> stepsDone) {
            this.stepsDone = stepsDone;
            stepsDoneBuildStage = STAGE_INITIALIZED;
        }

        Optional<String> getIntegrationId() {
            if (integrationIdBuildStage == STAGE_INITIALIZING) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (integrationIdBuildStage == STAGE_UNINITIALIZED) {
                integrationIdBuildStage = STAGE_INITIALIZING;
                this.integrationId =
                        Objects.requireNonNull(getIntegrationIdInitialize(),
                                "integrationId");
                integrationIdBuildStage = STAGE_INITIALIZED;
            }
            return this.integrationId;
        }

        void integrationId(Optional<String> integrationId) {
            this.integrationId = integrationId;
            integrationIdBuildStage = STAGE_INITIALIZED;
        }

        private String formatInitCycleMessage() {
            List<String> attributes = new ArrayList<>();
            if (versionBuildStage == STAGE_INITIALIZING) {
                attributes.add("version");
            }
            if (createdAtBuildStage == STAGE_INITIALIZING) {
                attributes.add("createdAt");
            }
            if (updatedAtBuildStage == STAGE_INITIALIZING) {
                attributes.add("updatedAt");
            }
            if (currentStateBuildStage == STAGE_INITIALIZING) {
                attributes.add("currentState");
            }
            if (targetStateBuildStage == STAGE_INITIALIZING) {
                attributes.add("targetState");
            }
            if (stepsDoneBuildStage == STAGE_INITIALIZING) {
                attributes.add("stepsDone");
            }
            if (integrationIdBuildStage == STAGE_INITIALIZING) {
                attributes.add("integrationId");
            }
            return "Cannot build IntegrationDeployment, attribute initializers form cycle "
                    + attributes;
        }
    }
}
