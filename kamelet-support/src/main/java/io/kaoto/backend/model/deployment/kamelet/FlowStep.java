package io.kaoto.backend.model.deployment.kamelet;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.model.deployment.kamelet.step.FlowStepDeserializer;

import java.io.Serializable;
import java.util.Map;

@JsonDeserialize(
        using = FlowStepDeserializer.class
)
public interface FlowStep extends Serializable {

    Map<String, Object> getRepresenterProperties();
}
