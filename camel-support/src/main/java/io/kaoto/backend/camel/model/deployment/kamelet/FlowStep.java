package io.kaoto.backend.camel.model.deployment.kamelet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.camel.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.camel.model.deployment.kamelet.step.FlowStepDeserializer;
import io.kaoto.backend.model.step.Step;

import java.io.Serializable;
import java.util.Map;

@JsonDeserialize(using = FlowStepDeserializer.class)
public interface FlowStep extends Serializable {

    @JsonIgnore
    Map<String, Object> getRepresenterProperties();

    Step getStep(StepCatalog catalog, KameletStepParserService kameletStepParserService,
                 boolean start, boolean end);
}
