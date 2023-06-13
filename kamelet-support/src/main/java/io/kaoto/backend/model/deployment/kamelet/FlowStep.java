package io.kaoto.backend.model.deployment.kamelet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.deployment.kamelet.step.FlowStepDeserializer;
import io.kaoto.backend.model.step.Step;

import java.io.Serializable;
import java.util.Map;

@JsonDeserialize(using = FlowStepDeserializer.class)
public interface FlowStep extends Serializable {

    @JsonIgnore
    Map<String, Object> getRepresenterProperties();

    Step getStep(StepCatalog catalog, KameletStepParserService kameletStepParserService,
                 Boolean start, Boolean end);
}
