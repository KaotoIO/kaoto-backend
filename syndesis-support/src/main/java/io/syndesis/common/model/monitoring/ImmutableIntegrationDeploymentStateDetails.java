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
package io.syndesis.common.model.monitoring;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Var;
import io.syndesis.common.model.WithResourceId;

import java.io.ObjectStreamException;
import java.util.Objects;
import java.util.Optional;

/**
 * Immutable implementation of {@link io.syndesis.common.model.monitoring.IntegrationDeploymentStateDetails}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new IntegrationDeploymentStateDetails.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableIntegrationDeploymentStateDetails.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableIntegrationDeploymentStateDetails
        implements IntegrationDeploymentStateDetails {
    private final String id;
    private final String integrationId;
    private final int deploymentVersion;
    private final IntegrationDeploymentDetailedState detailedState;
    private final String namespace;
    private final String podName;
    private final LinkType linkType;

    @SuppressWarnings("unchecked") // safe covariant cast
    private ImmutableIntegrationDeploymentStateDetails(
            Optional<String> id,
            String integrationId,
            int deploymentVersion,
            IntegrationDeploymentDetailedState detailedState,
            Optional<String> namespace,
            Optional<String> podName,
            Optional<? extends LinkType> linkType) {
        this.id = id.orElse(null);
        this.integrationId = integrationId;
        this.deploymentVersion = deploymentVersion;
        this.detailedState = detailedState;
        this.namespace = namespace.orElse(null);
        this.podName = podName.orElse(null);
        this.linkType = linkType.orElse(null);
    }

    private ImmutableIntegrationDeploymentStateDetails(
            ImmutableIntegrationDeploymentStateDetails original,
            String id,
            String integrationId,
            int deploymentVersion,
            IntegrationDeploymentDetailedState detailedState,
            String namespace,
            String podName,
            LinkType linkType) {
        this.id = id;
        this.integrationId = integrationId;
        this.deploymentVersion = deploymentVersion;
        this.detailedState = detailedState;
        this.namespace = namespace;
        this.podName = podName;
        this.linkType = linkType;
    }

    /**
     * Construct a new immutable {@code IntegrationDeploymentStateDetails} instance.
     *
     * @param id                The value for the {@code id} attribute
     * @param integrationId     The value for the {@code integrationId} attribute
     * @param deploymentVersion The value for the {@code deploymentVersion} attribute
     * @param detailedState     The value for the {@code detailedState} attribute
     * @param namespace         The value for the {@code namespace} attribute
     * @param podName           The value for the {@code podName} attribute
     * @param linkType          The value for the {@code linkType} attribute
     * @return An immutable IntegrationDeploymentStateDetails instance
     */
    public static IntegrationDeploymentStateDetails of(Optional<String> id,
                                                       String integrationId,
                                                       int deploymentVersion,
                                                       IntegrationDeploymentDetailedState detailedState,
                                                       Optional<String> namespace,
                                                       Optional<String> podName,
                                                       Optional<? extends LinkType> linkType) {
        return validate(new ImmutableIntegrationDeploymentStateDetails(id,
                integrationId, deploymentVersion, detailedState, namespace,
                podName, linkType));
    }

    private static ImmutableIntegrationDeploymentStateDetails validate(
            ImmutableIntegrationDeploymentStateDetails instance) {

        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.monitoring.IntegrationDeploymentStateDetails} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable IntegrationDeploymentStateDetails instance
     */
    public static IntegrationDeploymentStateDetails copyOf(
            IntegrationDeploymentStateDetails instance) {
        if (instance instanceof ImmutableIntegrationDeploymentStateDetails) {
            return (ImmutableIntegrationDeploymentStateDetails) instance;
        }
        return new IntegrationDeploymentStateDetails.Builder()
                .createFrom(instance)
                .build();
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
     * @return The value of the {@code integrationId} attribute
     */
    @JsonProperty("integrationId")
    @Override
    public String getIntegrationId() {
        return integrationId;
    }

    /**
     * @return The value of the {@code deploymentVersion} attribute
     */
    @JsonProperty("deploymentVersion")
    @Override
    public int getDeploymentVersion() {
        return deploymentVersion;
    }

    /**
     * @return The value of the {@code detailedState} attribute
     */
    @JsonProperty("detailedState")
    @Override
    public IntegrationDeploymentDetailedState getDetailedState() {
        return detailedState;
    }

    /**
     * @return The value of the {@code namespace} attribute
     */
    @JsonProperty("namespace")
    @Override
    public Optional<String> getNamespace() {
        return Optional.ofNullable(namespace);
    }

    /**
     * @return The value of the {@code podName} attribute
     */
    @JsonProperty("podName")
    @Override
    public Optional<String> getPodName() {
        return Optional.ofNullable(podName);
    }

    /**
     * @return The value of the {@code linkType} attribute
     */
    @JsonProperty("linkType")
    @Override
    public Optional<LinkType> getLinkType() {
        return Optional.ofNullable(linkType);
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.monitoring.IntegrationDeploymentStateDetails#getId() id} attribute.
     *
     * @param value The value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegrationDeploymentStateDetails withId(
            String value) {
        String newValue = Objects.requireNonNull(value, "id");
        if (Objects.equals(this.id, newValue)) {
            return this;
        }
        return validate(new ImmutableIntegrationDeploymentStateDetails(
                this,
                newValue,
                this.integrationId,
                this.deploymentVersion,
                this.detailedState,
                this.namespace,
                this.podName,
                this.linkType));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.monitoring.IntegrationDeploymentStateDetails#getId() id} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for id
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegrationDeploymentStateDetails withId(
            Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.id, value)) {
            return this;
        }
        return validate(new ImmutableIntegrationDeploymentStateDetails(
                this,
                value,
                this.integrationId,
                this.deploymentVersion,
                this.detailedState,
                this.namespace,
                this.podName,
                this.linkType));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.monitoring.IntegrationDeploymentStateDetails#getIntegrationId() integrationId} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for integrationId (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableIntegrationDeploymentStateDetails withIntegrationId(
            String value) {
        if (Objects.equals(this.integrationId, value)) {
            return this;
        }
        return validate(new ImmutableIntegrationDeploymentStateDetails(
                this,
                this.id,
                value,
                this.deploymentVersion,
                this.detailedState,
                this.namespace,
                this.podName,
                this.linkType));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.monitoring.IntegrationDeploymentStateDetails#getDeploymentVersion() deploymentVersion} attribute.
     * A value equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for deploymentVersion
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableIntegrationDeploymentStateDetails withDeploymentVersion(
            int value) {
        if (this.deploymentVersion == value) {
            return this;
        }
        return validate(new ImmutableIntegrationDeploymentStateDetails(
                this,
                this.id,
                this.integrationId,
                value,
                this.detailedState,
                this.namespace,
                this.podName,
                this.linkType));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.monitoring.IntegrationDeploymentStateDetails#getDetailedState() detailedState} attribute.
     * A value equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for detailedState (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableIntegrationDeploymentStateDetails withDetailedState(
            IntegrationDeploymentDetailedState value) {
        if (this.detailedState == value) {
            return this;
        }
        if (Objects.equals(this.detailedState, value)) {
            return this;
        }
        return validate(new ImmutableIntegrationDeploymentStateDetails(
                this,
                this.id,
                this.integrationId,
                this.deploymentVersion,
                value,
                this.namespace,
                this.podName,
                this.linkType));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.monitoring.IntegrationDeploymentStateDetails#getNamespace() namespace} attribute.
     *
     * @param value The value for namespace
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegrationDeploymentStateDetails withNamespace(
            String value) {
        String newValue = Objects.requireNonNull(value, "namespace");
        if (Objects.equals(this.namespace, newValue)) {
            return this;
        }
        return validate(new ImmutableIntegrationDeploymentStateDetails(
                this,
                this.id,
                this.integrationId,
                this.deploymentVersion,
                this.detailedState,
                newValue,
                this.podName,
                this.linkType));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.monitoring.IntegrationDeploymentStateDetails#getNamespace() namespace} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for namespace
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegrationDeploymentStateDetails withNamespace(
            Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.namespace, value)) {
            return this;
        }
        return validate(new ImmutableIntegrationDeploymentStateDetails(
                this,
                this.id,
                this.integrationId,
                this.deploymentVersion,
                this.detailedState,
                value,
                this.podName,
                this.linkType));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.monitoring.IntegrationDeploymentStateDetails#getPodName() podName} attribute.
     *
     * @param value The value for podName
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegrationDeploymentStateDetails withPodName(
            String value) {
        String newValue = Objects.requireNonNull(value, "podName");
        if (Objects.equals(this.podName, newValue)) {
            return this;
        }
        return validate(new ImmutableIntegrationDeploymentStateDetails(
                this,
                this.id,
                this.integrationId,
                this.deploymentVersion,
                this.detailedState,
                this.namespace,
                newValue,
                this.linkType));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.monitoring.IntegrationDeploymentStateDetails#getPodName() podName} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for podName
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegrationDeploymentStateDetails withPodName(
            Optional<String> optional) {
        String value = optional.orElse(null);
        if (Objects.equals(this.podName, value)) {
            return this;
        }
        return validate(new ImmutableIntegrationDeploymentStateDetails(
                this,
                this.id,
                this.integrationId,
                this.deploymentVersion,
                this.detailedState,
                this.namespace,
                value,
                this.linkType));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.monitoring.IntegrationDeploymentStateDetails#getLinkType() linkType} attribute.
     *
     * @param value The value for linkType
     * @return A modified copy of {@code this} object
     */
    public final ImmutableIntegrationDeploymentStateDetails withLinkType(
            LinkType value) {
        LinkType newValue = Objects.requireNonNull(value, "linkType");
        if (this.linkType == newValue) {
            return this;
        }
        return validate(new ImmutableIntegrationDeploymentStateDetails(
                this,
                this.id,
                this.integrationId,
                this.deploymentVersion,
                this.detailedState,
                this.namespace,
                this.podName,
                newValue));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.monitoring.IntegrationDeploymentStateDetails#getLinkType() linkType} attribute.
     * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for linkType
     * @return A modified copy of {@code this} object
     */
    @SuppressWarnings("unchecked") // safe covariant cast
    public final ImmutableIntegrationDeploymentStateDetails withLinkType(
            Optional<? extends LinkType> optional) {
        LinkType value = optional.orElse(null);
        if (Objects.equals(this.linkType, value)) {
            return this;
        }
        return validate(new ImmutableIntegrationDeploymentStateDetails(
                this,
                this.id,
                this.integrationId,
                this.deploymentVersion,
                this.detailedState,
                this.namespace,
                this.podName,
                value));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableIntegrationDeploymentStateDetails} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableIntegrationDeploymentStateDetails
                && equalTo(
                (ImmutableIntegrationDeploymentStateDetails) another);
    }

    private boolean equalTo(
            ImmutableIntegrationDeploymentStateDetails another) {
        return Objects.equals(id, another.id)
                && Objects.equals(integrationId, another.integrationId)
                && deploymentVersion == another.deploymentVersion
                && Objects.equals(detailedState, another.detailedState)
                && Objects.equals(namespace, another.namespace)
                && Objects.equals(podName, another.podName)
                && Objects.equals(linkType, another.linkType);
    }

    /**
     * Computes a hash code from attributes: {@code id}, {@code integrationId}, {@code deploymentVersion}, {@code detailedState}, {@code namespace}, {@code podName}, {@code linkType}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(id);
        h += (h << 5) + Objects.hashCode(integrationId);
        h += (h << 5) + deploymentVersion;
        h += (h << 5) + Objects.hashCode(detailedState);
        h += (h << 5) + Objects.hashCode(namespace);
        h += (h << 5) + Objects.hashCode(podName);
        h += (h << 5) + Objects.hashCode(linkType);
        return h;
    }

    /**
     * Prints the immutable value {@code IntegrationDeploymentStateDetails} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        StringBuilder builder =
                new StringBuilder("IntegrationDeploymentStateDetails{");
        if (id != null) {
            builder.append("id=").append(id);
        }
        if (integrationId != null) {
            if (builder.length() > 34) {
                builder.append(", ");
            }
            builder.append("integrationId=").append(integrationId);
        }
        if (builder.length() > 34) {
            builder.append(", ");
        }
        builder.append("deploymentVersion=").append(deploymentVersion);
        if (detailedState != null) {
            builder.append(", ");
            builder.append("detailedState=").append(detailedState);
        }
        if (namespace != null) {
            builder.append(", ");
            builder.append("namespace=").append(namespace);
        }
        if (podName != null) {
            builder.append(", ");
            builder.append("podName=").append(podName);
        }
        if (linkType != null) {
            builder.append(", ");
            builder.append("linkType=").append(linkType);
        }
        return builder.append("}").toString();
    }

    private Object readResolve() throws ObjectStreamException {
        return validate(this);
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.monitoring.IntegrationDeploymentStateDetails IntegrationDeploymentStateDetails}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private String id;
        private String integrationId;
        private int deploymentVersion;
        private IntegrationDeploymentDetailedState detailedState;
        private String namespace;
        private String podName;
        private LinkType linkType;

        /**
         * Creates a builder for {@link io.syndesis.common.model.monitoring.IntegrationDeploymentStateDetails IntegrationDeploymentStateDetails} instances.
         * <pre>
         * new IntegrationDeploymentStateDetails.Builder()
         *    .id(String) // optional {@link io.syndesis.common.model.monitoring.IntegrationDeploymentStateDetails#getId() id}
         *    .integrationId(String | null) // nullable {@link io.syndesis.common.model.monitoring.IntegrationDeploymentStateDetails#getIntegrationId() integrationId}
         *    .deploymentVersion(int) // optional {@link io.syndesis.common.model.monitoring.IntegrationDeploymentStateDetails#getDeploymentVersion() deploymentVersion}
         *    .detailedState(io.syndesis.common.io.syndesis.common.model.monitoring.IntegrationDeploymentDetailedState | null) // nullable {@link io.syndesis.common.model.monitoring.IntegrationDeploymentStateDetails#getDetailedState() detailedState}
         *    .namespace(String) // optional {@link io.syndesis.common.model.monitoring.IntegrationDeploymentStateDetails#getNamespace() namespace}
         *    .podName(String) // optional {@link io.syndesis.common.model.monitoring.IntegrationDeploymentStateDetails#getPodName() podName}
         *    .linkType(io.syndesis.common.io.syndesis.common.model.monitoring.LinkType) // optional {@link io.syndesis.common.model.monitoring.IntegrationDeploymentStateDetails#getLinkType() linkType}
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof IntegrationDeploymentStateDetails.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new IntegrationDeploymentStateDetails.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.monitoring.IntegrationDeploymentStateDetails} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationDeploymentStateDetails.Builder createFrom(
                IntegrationDeploymentStateDetails instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (IntegrationDeploymentStateDetails.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.WithResourceId} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationDeploymentStateDetails.Builder createFrom(
                WithResourceId instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (IntegrationDeploymentStateDetails.Builder) this;
        }

        private void from(Object object) {
            if (object instanceof IntegrationDeploymentStateDetails) {
                IntegrationDeploymentStateDetails instance =
                        (IntegrationDeploymentStateDetails) object;
                Optional<String> namespaceOptional = instance.getNamespace();
                if (namespaceOptional.isPresent()) {
                    namespace(namespaceOptional);
                }
                String integrationIdValue = instance.getIntegrationId();
                if (integrationIdValue != null) {
                    integrationId(integrationIdValue);
                }
                Optional<String> podNameOptional = instance.getPodName();
                if (podNameOptional.isPresent()) {
                    podName(podNameOptional);
                }
                IntegrationDeploymentDetailedState detailedStateValue =
                        instance.getDetailedState();
                if (detailedStateValue != null) {
                    detailedState(detailedStateValue);
                }
                Optional<LinkType> linkTypeOptional = instance.getLinkType();
                if (linkTypeOptional.isPresent()) {
                    linkType(linkTypeOptional);
                }
                deploymentVersion(instance.getDeploymentVersion());
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
         * Initializes the optional value {@link io.syndesis.common.model.monitoring.IntegrationDeploymentStateDetails#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationDeploymentStateDetails.Builder id(String id) {
            this.id = Objects.requireNonNull(id, "id");
            return (IntegrationDeploymentStateDetails.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.monitoring.IntegrationDeploymentStateDetails#getId() id} to id.
         *
         * @param id The value for id
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("id")
        public final IntegrationDeploymentStateDetails.Builder id(
                Optional<String> id) {
            this.id = id.orElse(null);
            return (IntegrationDeploymentStateDetails.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.monitoring.IntegrationDeploymentStateDetails#getIntegrationId() integrationId} attribute.
         *
         * @param integrationId The value for integrationId (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("integrationId")
        public final IntegrationDeploymentStateDetails.Builder integrationId(
                String integrationId) {
            this.integrationId = integrationId;
            return (IntegrationDeploymentStateDetails.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.monitoring.IntegrationDeploymentStateDetails#getDeploymentVersion() deploymentVersion} attribute.
         *
         * @param deploymentVersion The value for deploymentVersion
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("deploymentVersion")
        public final IntegrationDeploymentStateDetails.Builder deploymentVersion(
                int deploymentVersion) {
            this.deploymentVersion = deploymentVersion;
            return (IntegrationDeploymentStateDetails.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.monitoring.IntegrationDeploymentStateDetails#getDetailedState() detailedState} attribute.
         *
         * @param detailedState The value for detailedState (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("detailedState")
        public final IntegrationDeploymentStateDetails.Builder detailedState(
                IntegrationDeploymentDetailedState detailedState) {
            this.detailedState = detailedState;
            return (IntegrationDeploymentStateDetails.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.monitoring.IntegrationDeploymentStateDetails#getNamespace() namespace} to namespace.
         *
         * @param namespace The value for namespace
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationDeploymentStateDetails.Builder namespace(
                String namespace) {
            this.namespace = Objects.requireNonNull(namespace, "namespace");
            return (IntegrationDeploymentStateDetails.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.monitoring.IntegrationDeploymentStateDetails#getNamespace() namespace} to namespace.
         *
         * @param namespace The value for namespace
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("namespace")
        public final IntegrationDeploymentStateDetails.Builder namespace(
                Optional<String> namespace) {
            this.namespace = namespace.orElse(null);
            return (IntegrationDeploymentStateDetails.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.monitoring.IntegrationDeploymentStateDetails#getPodName() podName} to podName.
         *
         * @param podName The value for podName
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationDeploymentStateDetails.Builder podName(
                String podName) {
            this.podName = Objects.requireNonNull(podName, "podName");
            return (IntegrationDeploymentStateDetails.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.monitoring.IntegrationDeploymentStateDetails#getPodName() podName} to podName.
         *
         * @param podName The value for podName
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("podName")
        public final IntegrationDeploymentStateDetails.Builder podName(
                Optional<String> podName) {
            this.podName = podName.orElse(null);
            return (IntegrationDeploymentStateDetails.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.monitoring.IntegrationDeploymentStateDetails#getLinkType() linkType} to linkType.
         *
         * @param linkType The value for linkType
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final IntegrationDeploymentStateDetails.Builder linkType(
                LinkType linkType) {
            this.linkType = Objects.requireNonNull(linkType, "linkType");
            return (IntegrationDeploymentStateDetails.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.monitoring.IntegrationDeploymentStateDetails#getLinkType() linkType} to linkType.
         *
         * @param linkType The value for linkType
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("linkType")
        public final IntegrationDeploymentStateDetails.Builder linkType(
                Optional<? extends LinkType> linkType) {
            this.linkType = linkType.orElse(null);
            return (IntegrationDeploymentStateDetails.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.monitoring.IntegrationDeploymentStateDetails IntegrationDeploymentStateDetails}.
         *
         * @return An immutable instance of IntegrationDeploymentStateDetails
         * @throws IllegalStateException if any required attributes are missing
         */
        public IntegrationDeploymentStateDetails build() {
            return ImmutableIntegrationDeploymentStateDetails.validate(
                    new ImmutableIntegrationDeploymentStateDetails(null, id,
                            integrationId, deploymentVersion, detailedState,
                            namespace, podName, linkType));
        }
    }
}
