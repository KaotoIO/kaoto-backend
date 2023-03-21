package io.kaoto.backend.model.deployment.kamelet.step;

import io.kaoto.backend.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.model.deployment.kamelet.expression.Expression;

import java.util.List;

public interface ConditionBlock {
    List<FlowStep> getSteps();

    String getSimple();

    String getJq();

    String getJsonpath();

    Expression getExpression();
}
