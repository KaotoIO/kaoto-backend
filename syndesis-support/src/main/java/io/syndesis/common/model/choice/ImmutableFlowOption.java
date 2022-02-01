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
package io.syndesis.common.model.choice;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Var;
import io.syndesis.common.model.expression.RuleBase;

import java.util.Objects;

/**
 * Immutable implementation of {@link io.syndesis.common.model.choice.FlowOption}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new FlowOption.Builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableFlowOption.of()}.
 */
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated(
        "org.immutables.processor.ProxyProcessor")
final class ImmutableFlowOption implements FlowOption {
    private final String condition;
    private final String flow;
    private final String path;
    private final String op;
    private final String value;

    private ImmutableFlowOption(
            String condition,
            String flow,
            String path,
            String op,
            String value) {
        this.condition = condition;
        this.flow = flow;
        this.path = path;
        this.op = op;
        this.value = value;
    }

    /**
     * Construct a new immutable {@code FlowOption} instance.
     *
     * @param condition The value for the {@code condition} attribute
     * @param flow      The value for the {@code flow} attribute
     * @param path      The value for the {@code path} attribute
     * @param op        The value for the {@code op} attribute
     * @param value     The value for the {@code value} attribute
     * @return An immutable FlowOption instance
     */
    public static FlowOption of(String condition, String flow, String path,
                                String op, String value) {
        return validate(
                new ImmutableFlowOption(condition, flow, path, op, value));
    }

    private static ImmutableFlowOption validate(ImmutableFlowOption instance) {
        return instance;
    }

    /**
     * Creates an immutable copy of a {@link io.syndesis.common.model.choice.FlowOption} value.
     * Uses accessors to get values to initialize the new immutable instance.
     * If an instance is already immutable, it is returned as is.
     *
     * @param instance The instance to copy
     * @return A copied immutable FlowOption instance
     */
    public static FlowOption copyOf(FlowOption instance) {
        if (instance instanceof ImmutableFlowOption) {
            return (ImmutableFlowOption) instance;
        }
        return new FlowOption.Builder()
                .createFrom(instance)
                .build();
    }

    /**
     * @return The value of the {@code condition} attribute
     */
    @JsonProperty("condition")
    @Override
    public String getCondition() {
        return condition;
    }

    /**
     * @return The value of the {@code flow} attribute
     */
    @JsonProperty("flow")
    @Override
    public String getFlow() {
        return flow;
    }

    /**
     * @return The value of the {@code path} attribute
     */
    @JsonProperty("path")
    @Override
    public String getPath() {
        return path;
    }

    /**
     * @return The value of the {@code op} attribute
     */
    @JsonProperty("op")
    @Override
    public String getOp() {
        return op;
    }

    /**
     * @return The value of the {@code value} attribute
     */
    @JsonProperty("value")
    @Override
    public String getValue() {
        return value;
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.choice.FlowOption#getCondition() condition} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for condition (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableFlowOption withCondition(String value) {
        if (Objects.equals(this.condition, value)) {
            return this;
        }
        return validate(
                new ImmutableFlowOption(value, this.flow, this.path, this.op,
                        this.value));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.choice.FlowOption#getFlow() flow} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for flow (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableFlowOption withFlow(String value) {
        if (Objects.equals(this.flow, value)) {
            return this;
        }
        return validate(
                new ImmutableFlowOption(this.condition, value, this.path,
                        this.op, this.value));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.choice.FlowOption#getPath() path} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for path (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableFlowOption withPath(String value) {
        if (Objects.equals(this.path, value)) {
            return this;
        }
        return validate(
                new ImmutableFlowOption(this.condition, this.flow, value,
                        this.op, this.value));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.choice.FlowOption#getOp() op} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for op (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableFlowOption withOp(String value) {
        if (Objects.equals(this.op, value)) {
            return this;
        }
        return validate(
                new ImmutableFlowOption(this.condition, this.flow, this.path,
                        value, this.value));
    }

    /**
     * Copy the current immutable object by setting a value for the {@link io.syndesis.common.model.choice.FlowOption#getValue() value} attribute.
     * An equals check used to prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for value (can be {@code null})
     * @return A modified copy of the {@code this} object
     */
    public final ImmutableFlowOption withValue(String value) {
        if (Objects.equals(this.value, value)) {
            return this;
        }
        return validate(
                new ImmutableFlowOption(this.condition, this.flow, this.path,
                        this.op, value));
    }

    /**
     * This instance is equal to all instances of {@code ImmutableFlowOption} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        return another instanceof ImmutableFlowOption
                && equalTo((ImmutableFlowOption) another);
    }

    private boolean equalTo(ImmutableFlowOption another) {
        return Objects.equals(condition, another.condition)
                && Objects.equals(flow, another.flow)
                && Objects.equals(path, another.path)
                && Objects.equals(op, another.op)
                && Objects.equals(value, another.value);
    }

    /**
     * Computes a hash code from attributes: {@code condition}, {@code flow}, {@code path}, {@code op}, {@code value}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        @Var int h = 5381;
        h += (h << 5) + Objects.hashCode(condition);
        h += (h << 5) + Objects.hashCode(flow);
        h += (h << 5) + Objects.hashCode(path);
        h += (h << 5) + Objects.hashCode(op);
        h += (h << 5) + Objects.hashCode(value);
        return h;
    }

    /**
     * Prints the immutable value {@code FlowOption} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        return "FlowOption{"
                + "condition=" + condition
                + ", flow=" + flow
                + ", path=" + path
                + ", op=" + op
                + ", value=" + value
                + "}";
    }

    /**
     * Builds instances of type {@link io.syndesis.common.model.choice.FlowOption FlowOption}.
     * Initialize attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
     * but instead used immediately to create instances.</em>
     */
    public static class Builder {
        private String condition;
        private String flow;
        private String path;
        private String op;
        private String value;

        /**
         * Creates a builder for {@link io.syndesis.common.model.choice.FlowOption FlowOption} instances.
         * <pre>
         * new FlowOption.Builder()
         *    .condition(String | null) // nullable {@link io.syndesis.common.model.choice.FlowOption#getCondition() condition}
         *    .flow(String | null) // nullable {@link io.syndesis.common.model.choice.FlowOption#getFlow() flow}
         *    .path(String | null) // nullable {@link io.syndesis.common.model.choice.FlowOption#getPath() path}
         *    .op(String | null) // nullable {@link io.syndesis.common.model.choice.FlowOption#getOp() op}
         *    .value(String | null) // nullable {@link io.syndesis.common.model.choice.FlowOption#getValue() value}
         *    .build();
         * </pre>
         */
        public Builder() {
            if (!(this instanceof FlowOption.Builder)) {
                throw new UnsupportedOperationException(
                        "Use: new FlowOption.Builder()");
            }
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.expression.RuleBase} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final FlowOption.Builder createFrom(RuleBase instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (FlowOption.Builder) this;
        }

        /**
         * Fill a builder with attribute values from the provided {@code io.syndesis.common.io.syndesis.common.model.choice.FlowOption} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        public final FlowOption.Builder createFrom(FlowOption instance) {
            Objects.requireNonNull(instance, "instance");
            from((Object) instance);
            return (FlowOption.Builder) this;
        }

        private void from(Object object) {
            @Var long bits = 0;
            if (object instanceof RuleBase) {
                RuleBase instance = (RuleBase) object;
                if ((bits & 0x1L) == 0) {
                    String pathValue = instance.getPath();
                    if (pathValue != null) {
                        path(pathValue);
                    }
                    bits |= 0x1L;
                }
                if ((bits & 0x2L) == 0) {
                    String opValue = instance.getOp();
                    if (opValue != null) {
                        op(opValue);
                    }
                    bits |= 0x2L;
                }
                if ((bits & 0x4L) == 0) {
                    String valueValue = instance.getValue();
                    if (valueValue != null) {
                        value(valueValue);
                    }
                    bits |= 0x4L;
                }
            }
            if (object instanceof FlowOption) {
                FlowOption instance = (FlowOption) object;
                if ((bits & 0x1L) == 0) {
                    String pathValue = instance.getPath();
                    if (pathValue != null) {
                        path(pathValue);
                    }
                    bits |= 0x1L;
                }
                if ((bits & 0x2L) == 0) {
                    String opValue = instance.getOp();
                    if (opValue != null) {
                        op(opValue);
                    }
                    bits |= 0x2L;
                }
                String conditionValue = instance.getCondition();
                if (conditionValue != null) {
                    condition(conditionValue);
                }
                if ((bits & 0x4L) == 0) {
                    String valueValue = instance.getValue();
                    if (valueValue != null) {
                        value(valueValue);
                    }
                    bits |= 0x4L;
                }
                String flowValue = instance.getFlow();
                if (flowValue != null) {
                    flow(flowValue);
                }
            }
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.choice.FlowOption#getCondition() condition} attribute.
         *
         * @param condition The value for condition (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("condition")
        public final FlowOption.Builder condition(String condition) {
            this.condition = condition;
            return (FlowOption.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.choice.FlowOption#getFlow() flow} attribute.
         *
         * @param flow The value for flow (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("flow")
        public final FlowOption.Builder flow(String flow) {
            this.flow = flow;
            return (FlowOption.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.choice.FlowOption#getPath() path} attribute.
         *
         * @param path The value for path (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("path")
        public final FlowOption.Builder path(String path) {
            this.path = path;
            return (FlowOption.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.choice.FlowOption#getOp() op} attribute.
         *
         * @param op The value for op (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("op")
        public final FlowOption.Builder op(String op) {
            this.op = op;
            return (FlowOption.Builder) this;
        }

        /**
         * Initializes the value for the {@link io.syndesis.common.model.choice.FlowOption#getValue() value} attribute.
         *
         * @param value The value for value (can be {@code null})
         * @return {@code this} builder for use in a chained invocation
         */
        @CanIgnoreReturnValue
        @JsonProperty("value")
        public final FlowOption.Builder value(String value) {
            this.value = value;
            return (FlowOption.Builder) this;
        }

        /**
         * Builds a new {@link io.syndesis.common.model.choice.FlowOption FlowOption}.
         *
         * @return An immutable instance of FlowOption
         * @throws IllegalStateException if any required attributes are missing
         */
        public FlowOption build() {
            return ImmutableFlowOption.validate(
                    new ImmutableFlowOption(condition, flow, path, op, value));
        }
    }
}
