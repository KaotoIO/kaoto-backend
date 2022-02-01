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

import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Immutable implementation of {@link io.syndesis.common.model.action.StepDescriptor}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new StepDescriptor.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableStepDescriptor.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableStepDescriptor implements StepDescriptor {
    private final DataShape inputDataShape;
    private final DataShape outputDataShape;
    private final List<ActionDescriptorStep> propertyDefinitionSteps;
    private final StepAction.Kind kind;
    private final String entrypoint;
    private final String resource;

    @SuppressWarnings("unchecked") // safe covariant cast
    private ImmutableStepDescriptor(
            Optional<? extends DataShape> inputDataShape,
            Optional<? extends DataShape> outputDataShape,
            Iterable<? extends ActionDescriptorStep> propertyDefinitionSteps,
            StepAction.Kind kind,
            String entrypoint,
            String resource) {
        this.inputDataShape = inputDataShape.orElse(null);
        this.outputDataShape = outputDataShape.orElse(null);
        this.propertyDefinitionSteps = createUnmodifiableList(false,
                createSafeList(propertyDefinitionSteps, true, false));
        this.kind = kind;
        this.entrypoint = entrypoint;
        this.resource = resource;
    }

    private ImmutableStepDescriptor(Builder builder) {
        this.inputDataShape = builder.inputDataShape;
        this.outputDataShape = builder.outputDataShape;
        this.kind = builder.kind;
        this.entrypoint = builder.entrypoint;
        this.resource = builder.resource;
        this.propertyDefinitionSteps = builder.propertyDefinitionStepsIsSet()
                ? createUnmodifiableList(true, builder.propertyDefinitionSteps)
                : createUnmodifiableList(false, createSafeList(
                StepDescriptor.super.getPropertyDefinitionSteps(), true,
                false));
    }

    private ImmutableStepDescriptor(
            ImmutableStepDescriptor original,
            DataShape inputDataShape,
            DataShape outputDataShape,
            List<ActionDescriptorStep> propertyDefinitionSteps,
            StepAction.Kind kind,
            String entrypoint,
            String resource) {
        this.inputDataShape = inputDataShape;
        this.outputDataShape = outputDataShape;
        this.propertyDefinitionSteps = propertyDefinitionSteps;
        this.kind = kind;
        this.entrypoint = entrypoint;
        this.resource = resource;
    }

    /**
     * Construct a new immutable {@code StepDescriptor} instance.
     *
     * @param inputDataShape          The value for the {@code inputDataShape} attribute
     * @param outputDataShape         The value for the {@code outputDataShape} attribute
     * @param propertyDefinitionSteps The value for the {@code propertyDefinitionSteps} attribute
     * @param kind                    The value for the {@code kind} attribute
     * @param entrypoint              The value for the {@code entrypoint} attribute
     * @param resource                The value for the {@code resource} attribute
     * @return An immutable StepDescriptor instance
     */
    public static StepDescriptor of(Optional<DataShape> inputDataShape,
                                    Optional<DataShape> outputDataShape,
                                    List<ActionDescriptorStep> propertyDefinitionSteps,
                                    StepAction.Kind kind, String entrypoint,
                                    String resource) {
        return of(inputDataShape, outputDataShape,
                (Iterable<? extends ActionDescriptorStep>) propertyDefinitionSteps,
                kind, entrypoint, resource);
    }

    /**
     * Construct a new immutable {@code StepDescriptor} instance.
     *
     * @param inputDataShape          The value for the {@code inputDataShape} attribute
     * @param outputDataShape         The value for the {@code outputDataShape} attribute
     * @param propertyDefinitionSteps The value for the {@code propertyDefinitionSteps} attribute
     * @param kind                    The value for the {@code kind} attribute
     * @param entrypoint              The value for the {@code entrypoint} attribute
     * @param resource                The value for the {@code resource} attribute
     * @return An immutable StepDescriptor instance
     */
    public static StepDescriptor of(
            Optional<? extends DataShape> inputDataShape,
            Optional<? extends DataShape> outputDataShape,
            Iterable<? extends ActionDescriptorStep> propertyDefinitionSteps,
            StepAction.Kind kind, String entrypoint, String resource) {
        return validate(
                new ImmutableStepDescriptor(inputDataShape, outputDataShape,
                        propertyDefinitionSteps, kind, entrypoint, resource));
    }

    private static ImmutableStepDescriptor validate(
            ImmutableStepDescriptor instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.action.StepDescriptor} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable StepDescriptor instance
     */
    public static StepDescriptor copyOf(StepDescriptor instance) {
        if (instance instanceof ImmutableStepDescriptor) {
            return (ImmutableStepDescriptor) instance;
        }
        return new StepDescriptor.Builder()
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
        return propertyDefinitionSteps;
    }

    /**
     * @return The value of the {@code kind} attribute
     */
    @JsonProperty("kind")
    @Override
    public StepAction.Kind getKind() {
        return kind;
    }

    /**
     * @return The value of the {@code entrypoint} attribute
     */
    @JsonProperty("entrypoint")
    @Override
    public String getEntrypoint() {
        return entrypoint;
    }

    /**
     * @return The value of the {@code resource} attribute
     */
    @JsonProperty("resource")
    @Override
    public String getResource() {
        return resource;
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.action.StepDescriptor#getInputDataShape() inputDataShape} attribute.
     *
     * @param value The value for inputDataShape
     * @return A modified copy of {@code this} object
     */
    public final ImmutableStepDescriptor withInputDataShape(DataShape value) {
        DataShape newValue = Objects.requireNonNull(value, "inputDataShape");
        if (this.inputDataShape == newValue) {
            return this;
        }
        return validate(new ImmutableStepDescriptor(
                this,
                newValue,
                this.outputDataShape,
                this.propertyDefinitionSteps,
                this.kind,
                this.entrypoint,
                this.resource));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.action.StepDescriptor#getInputDataShape() inputDataShape} attribute.
     * A shallow reference equality check is used on unboxed optional value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for inputDataShape
     * @return A modified copy of {@code this} object
     */
    @SuppressWarnings("unchecked") // safe covariant cast
    public final ImmutableStepDescriptor withInputDataShape(
            Optional<? extends DataShape> optional) {
        DataShape value = optional.orElse(null);
        if (this.inputDataShape == value) {
            return this;
        }
        return validate(new ImmutableStepDescriptor(
                this,
                value,
                this.outputDataShape,
                this.propertyDefinitionSteps,
                this.kind,
                this.entrypoint,
                this.resource));
    }

    /**
     * Copy the current immutable object by setting a <i>present</i> value for the optional {@link io.syndesis.common.model.action.StepDescriptor#getOutputDataShape() outputDataShape} attribute.
     *
     * @param value The value for outputDataShape
     * @return A modified copy of {@code this} object
     */
    public final ImmutableStepDescriptor withOutputDataShape(DataShape value) {
        DataShape newValue = Objects.requireNonNull(value, "outputDataShape");
        if (this.outputDataShape == newValue) {
            return this;
        }
        return validate(new ImmutableStepDescriptor(
                this,
                this.inputDataShape,
                newValue,
                this.propertyDefinitionSteps,
                this.kind,
                this.entrypoint,
                this.resource));
    }

    /**
     * Copy the current immutable object by setting an optional value for the {@link io.syndesis.common.model.action.StepDescriptor#getOutputDataShape() outputDataShape} attribute.
     * A shallow reference equality check is used on unboxed optional value to prevent copying of the same value by returning {@code this}.
     *
     * @param optional A value for outputDataShape
     * @return A modified copy of {@code this} object
     */
    @SuppressWarnings("unchecked") // safe covariant cast
    public final ImmutableStepDescriptor withOutputDataShape(
            Optional<? extends DataShape> optional) {
        DataShape value = optional.orElse(null);
        if (this.outputDataShape == value) {
            return this;
        }
        return validate(new ImmutableStepDescriptor(
                this,
                this.inputDataShape,
                value,
                this.propertyDefinitionSteps,
                this.kind,
                this.entrypoint,
                this.resource));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.action.StepDescriptor#getPropertyDefinitionSteps() propertyDefinitionSteps}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableStepDescriptor withPropertyDefinitionSteps(
            ActionDescriptorStep... elements) {
        List<ActionDescriptorStep> newValue = createUnmodifiableList(false,
                createSafeList(Arrays.asList(elements), true, false));
        return validate(new ImmutableStepDescriptor(
                this,
                this.inputDataShape,
                this.outputDataShape,
                newValue,
                this.kind,
                this.entrypoint,
                this.resource));
    }

    /**
     * Copy the current immutable object with elements that replace the content of {@link io.syndesis.common.model.action.StepDescriptor#getPropertyDefinitionSteps() propertyDefinitionSteps}.
     * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of propertyDefinitionSteps elements to set
     * @return A modified copy of {@code this} object
     */
    public final ImmutableStepDescriptor withPropertyDefinitionSteps(
            Iterable<? extends ActionDescriptorStep> elements) {
        if (this.propertyDefinitionSteps == elements) {
            return this;
        }
        List<ActionDescriptorStep> newValue = createUnmodifiableList(false,
                createSafeList(elements, true, false));
        return validate(new ImmutableStepDescriptor(
                this,
                this.inputDataShape,
                this.outputDataShape,
                newValue,
                this.kind,
                this.entrypoint,
                this.resource));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.action.StepDescriptor#getKind() kind} attribute.
     * A value equality check is used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for kind (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableStepDescriptor withKind(StepAction.Kind value) {
        if (this.kind == value) {
            return this;
        }
        if (Objects.equals(this.kind, value)) {
            return this;
        }
        return validate(new ImmutableStepDescriptor(
                this,
                this.inputDataShape,
                this.outputDataShape,
                this.propertyDefinitionSteps,
                value,
                this.entrypoint,
                this.resource));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.action.StepDescriptor#getEntrypoint() entrypoint} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for entrypoint (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableStepDescriptor withEntrypoint(String value) {
        if (Objects.equals(this.entrypoint, value)) {
            return this;
        }
        return validate(new ImmutableStepDescriptor(
                this,
                this.inputDataShape,
                this.outputDataShape,
                this.propertyDefinitionSteps,
                this.kind,
                value,
                this.resource));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.action.StepDescriptor#getResource() resource} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for resource (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableStepDescriptor withResource(String value) {
        if (Objects.equals(this.resource, value)) {
            return this;
        }
        return validate(new ImmutableStepDescriptor(
                this,
                this.inputDataShape,
                this.outputDataShape,
                this.propertyDefinitionSteps,
                this.kind,
                this.entrypoint,
                value));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableStepDescriptor} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableStepDescriptor
                && equalTo((ImmutableStepDescriptor) another);
    }

    private boolean equalTo(ImmutableStepDescriptor another) {
        return Objects.equals(inputDataShape, another.inputDataShape)
                && Objects.equals(outputDataShape, another.outputDataShape)
                && propertyDefinitionSteps.equals(
                another.propertyDefinitionSteps)
                && Objects.equals(kind, another.kind)
                && Objects.equals(entrypoint, another.entrypoint)
                && Objects.equals(resource, another.resource);
    }

    /**
     * Computes a hash code from attributes: {@code inputDataShape}, {@code outputDataShape}, {@code propertyDefinitionSteps}, {@code kind}, {@code entrypoint}, {@code resource}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(inputDataShape);
        h += (h << 5) + Objects.hashCode(outputDataShape);
        h += (h << 5) + propertyDefinitionSteps.hashCode();
        h += (h << 5) + Objects.hashCode(kind);
        h += (h << 5) + Objects.hashCode(entrypoint);
        h += (h << 5) + Objects.hashCode(resource);
        return h;
    }

    /**
     * Prints the immutable value {@code StepDescriptor} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("StepDescriptor{");
        if (inputDataShape != null) {
            builder.append("inputDataShape=").append(inputDataShape);
        }
        if (outputDataShape != null) {
            if (builder.length() > 15) {
                builder.append(", ");
            }
            builder.append("outputDataShape=").append(outputDataShape);
        }
        if (builder.length() > 15) {
            builder.append(", ");
        }
        builder.append("propertyDefinitionSteps=")
                .append(propertyDefinitionSteps);
        if (kind != null) {
            builder.append(", ");
            builder.append("kind=").append(kind);
        }
        if (entrypoint != null) {
            builder.append(", ");
            builder.append("entrypoint=").append(entrypoint);
        }
        if (resource != null) {
            builder.append(", ");
            builder.append("resource=").append(resource);
        }
        return builder.append("}").toString();
    }

    private Object readResolve() throws ObjectStreamException {
        return validate(this);
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.action.StepDescriptor StepDescriptor}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private static final long OPT_BIT_PROPERTY_DEFINITION_STEPS = 0x1L;
        private long optBits;

        private DataShape inputDataShape;
        private DataShape outputDataShape;
        private List<ActionDescriptorStep> propertyDefinitionSteps =
                new ArrayList<ActionDescriptorStep>();
        private StepAction.Kind kind;
        private String entrypoint;
        private String resource;

        /**
         * Creates a builder for {@link io.syndesis.common.model.action.StepDescriptor StepDescriptor} instances.
         * <pre>
         * new StepDescriptor.Builder()
         *    .inputDataShape(io.syndesis.common.io.syndesis.common.model.DataShape) // optional {@link io.syndesis.common.model.action.StepDescriptor#getInputDataShape() inputDataShape}
         *    .outputDataShape(io.syndesis.common.io.syndesis.common.model.DataShape) // optional {@link io.syndesis.common.model.action.StepDescriptor#getOutputDataShape() outputDataShape}
         *    .addPropertyDefinitionStep|addAllPropertyDefinitionSteps(io.syndesis.common.io.syndesis.common.model.action.ActionDescriptor.ActionDescriptorStep) // {@link io.syndesis.common.model.action.StepDescriptor#getPropertyDefinitionSteps() propertyDefinitionSteps} elements
         *    .kind(io.syndesis.common.io.syndesis.common.model.action.StepAction.Kind | null) // nullable {@link io.syndesis.common.model.action.StepDescriptor#getKind() kind}
         *    .entrypoint(String | null) // nullable {@link io.syndesis.common.model.action.StepDescriptor#getEntrypoint() entrypoint}
         *    .resource(String | null) // nullable {@link io.syndesis.common.model.action.StepDescriptor#getResource() resource}
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof StepDescriptor.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new StepDescriptor.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.action.StepDescriptor} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final StepDescriptor.Builder createFrom(
                StepDescriptor instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (StepDescriptor.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.action.ActionDescriptor} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final StepDescriptor.Builder createFrom(
                ActionDescriptor instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (StepDescriptor.Builder) this;
        }

        private void from(Object object) {
            if (object instanceof StepDescriptor) {
                StepDescriptor instance = (StepDescriptor) object;
                String resourceValue = instance.getResource();
                if (resourceValue != null) {
                    resource(resourceValue);
                }
                StepAction.Kind kindValue = instance.getKind();
                if (kindValue != null) {
                    kind(kindValue);
                }
                String entrypointValue = instance.getEntrypoint();
                if (entrypointValue != null) {
                    entrypoint(entrypointValue);
                }
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
         * Initializes the optional value {@link io.syndesis.common.model.action.StepDescriptor#getInputDataShape() inputDataShape} to inputDataShape.
         *
         * @param inputDataShape The value for inputDataShape
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final StepDescriptor.Builder inputDataShape(
                DataShape inputDataShape) {
            this.inputDataShape =
                    Objects.requireNonNull(inputDataShape, "inputDataShape");
            return (StepDescriptor.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.action.StepDescriptor#getInputDataShape() inputDataShape} to inputDataShape.
         *
         * @param inputDataShape The value for inputDataShape
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("inputDataShape")
        public final StepDescriptor.Builder inputDataShape(
                Optional<? extends DataShape> inputDataShape) {
            this.inputDataShape = inputDataShape.orElse(null);
            return (StepDescriptor.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.action.StepDescriptor#getOutputDataShape() outputDataShape} to outputDataShape.
         *
         * @param outputDataShape The value for outputDataShape
         * @return {@code this} builder for chained invocation
         */
        @CanIgnoreReturnValue
        public final StepDescriptor.Builder outputDataShape(
                DataShape outputDataShape) {
            this.outputDataShape =
                    Objects.requireNonNull(outputDataShape, "outputDataShape");
            return (StepDescriptor.Builder) this;
        }

        /**
         * Initializes the optional value {@link io.syndesis.common.model.action.StepDescriptor#getOutputDataShape() outputDataShape} to outputDataShape.
         *
         * @param outputDataShape The value for outputDataShape
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("outputDataShape")
        public final StepDescriptor.Builder outputDataShape(
                Optional<? extends DataShape> outputDataShape) {
            this.outputDataShape = outputDataShape.orElse(null);
            return (StepDescriptor.Builder) this;
        }

        /**
         * Adds one element to {@link io.syndesis.common.model.action.StepDescriptor#getPropertyDefinitionSteps() propertyDefinitionSteps} list.
         *
         * @param element A propertyDefinitionSteps element
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final StepDescriptor.Builder addPropertyDefinitionStep(
                ActionDescriptorStep element) {
            this.propertyDefinitionSteps.add(Objects.requireNonNull(element,
                    "propertyDefinitionSteps element"));
            optBits |= OPT_BIT_PROPERTY_DEFINITION_STEPS;
            return (StepDescriptor.Builder) this;
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.action.StepDescriptor#getPropertyDefinitionSteps() propertyDefinitionSteps} list.
         *
         * @param elements An array of propertyDefinitionSteps elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final StepDescriptor.Builder addPropertyDefinitionSteps(
                ActionDescriptorStep... elements) {
            for (ActionDescriptorStep element : elements) {
                this.propertyDefinitionSteps.add(Objects.requireNonNull(element,
                        "propertyDefinitionSteps element"));
            }
            optBits |= OPT_BIT_PROPERTY_DEFINITION_STEPS;
            return (StepDescriptor.Builder) this;
        }


        /**
         * Sets or replaces all elements for {@link io.syndesis.common.model.action.StepDescriptor#getPropertyDefinitionSteps() propertyDefinitionSteps} list.
         *
         * @param elements An iterable of propertyDefinitionSteps elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("propertyDefinitionSteps")
        public final StepDescriptor.Builder propertyDefinitionSteps(
                Iterable<? extends ActionDescriptorStep> elements) {
            this.propertyDefinitionSteps.clear();
            return addAllPropertyDefinitionSteps(elements);
        }

        /**
         * Adds elements to {@link io.syndesis.common.model.action.StepDescriptor#getPropertyDefinitionSteps() propertyDefinitionSteps} list.
         *
         * @param elements An iterable of propertyDefinitionSteps elements
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final StepDescriptor.Builder addAllPropertyDefinitionSteps(
                Iterable<? extends ActionDescriptorStep> elements) {
            for (ActionDescriptorStep element : elements) {
                this.propertyDefinitionSteps.add(Objects.requireNonNull(element,
                        "propertyDefinitionSteps element"));
            }
            optBits |= OPT_BIT_PROPERTY_DEFINITION_STEPS;
            return (StepDescriptor.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.action.StepDescriptor#getKind() kind} attribute.
         *
         * @param kind The value for kind (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("kind")
        public final StepDescriptor.Builder kind(StepAction.Kind kind) {
            this.kind = kind;
            return (StepDescriptor.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.action.StepDescriptor#getEntrypoint() entrypoint} attribute.
         *
         * @param entrypoint The value for entrypoint (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("entrypoint")
        public final StepDescriptor.Builder entrypoint(String entrypoint) {
            this.entrypoint = entrypoint;
            return (StepDescriptor.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.action.StepDescriptor#getResource() resource} attribute.
         *
         * @param resource The value for resource (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("resource")
        public final StepDescriptor.Builder resource(String resource) {
            this.resource = resource;
            return (StepDescriptor.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.action.StepDescriptor StepDescriptor}.
         *
         * @return An immutable instance of StepDescriptor
         * @throws IllegalStateException if any required attributes are missing
         */
        public StepDescriptor build() {
            return ImmutableStepDescriptor.validate(
                    new ImmutableStepDescriptor(this));
        }

        private boolean propertyDefinitionStepsIsSet() {
            return (optBits & OPT_BIT_PROPERTY_DEFINITION_STEPS) != 0;
        }
    }
}
