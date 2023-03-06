package io.kaoto.backend.model.deployment.kamelet.step;

import io.kaoto.backend.model.deployment.kamelet.FlowStep;

import java.util.List;

public interface ConditionBlock {
    List<FlowStep> getSteps();

    String getSimple();

    String getJq();

    String getJsonpath();
}
