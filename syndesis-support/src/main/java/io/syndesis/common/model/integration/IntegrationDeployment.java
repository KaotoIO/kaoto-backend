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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.syndesis.common.model.Kind;
import io.syndesis.common.model.WithId;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@JsonDeserialize(builder = IntegrationDeployment.Builder.class)
@SuppressWarnings("immutables")
public interface IntegrationDeployment
        extends IntegrationDeploymentBase, WithId<IntegrationDeployment> {

    String COMPOSITE_ID_SEPARATOR = ":";

    static String compositeId(final String integrationId, final int version) {
        return integrationId + COMPOSITE_ID_SEPARATOR + version;
    }

    default Builder builder() {
        return new Builder().createFrom(this);
    }

    default Optional<String> getIntegrationId() {
        return getSpec().getId();
    }

    @Override
    default Kind getKind() {
        return Kind.IntegrationDeployment;
    }

    Integration getSpec();

    default IntegrationDeployment unpublished() {
        final Map<String, String> stepsDone = new HashMap<>(getStepsDone());
        stepsDone.remove("deploy");
        return builder().currentState(IntegrationDeploymentState.Unpublished)
                .stepsDone(stepsDone).build();
    }

    default IntegrationDeployment unpublishing() {
        final Map<String, String> stepsDone = new HashMap<>(getStepsDone());
        stepsDone.remove("deploy");
        return builder().targetState(IntegrationDeploymentState.Unpublished)
                .stepsDone(stepsDone).build();
    }

    default IntegrationDeployment withCurrentState(
            final IntegrationDeploymentState state) {
        return builder().currentState(state).build();
    }

    default IntegrationDeployment withTargetState(
            final IntegrationDeploymentState state) {
        return builder().targetState(state).build();
    }

    class Builder extends ImmutableIntegrationDeployment.Builder {
        // allow access to ImmutableIntegrationDeployment.Builder
    }

}
