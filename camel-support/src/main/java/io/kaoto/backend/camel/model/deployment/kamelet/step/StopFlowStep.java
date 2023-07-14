package io.kaoto.backend.camel.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.camel.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.camel.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.step.Step;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class StopFlowStep implements FlowStep {

    @JsonCreator
    public StopFlowStep() {
        super();
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("stop", Collections.emptyMap());
        return properties;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final boolean start, final boolean end) {
        var stopEip= catalog.getReadOnlyCatalog()
                .searchByName("stop").stream()
                .filter(step -> step.getKind().equalsIgnoreCase("EIP"))
                .findAny().orElse(null);
        if (stopEip != null) {
            // @FIXME this is a workaround for https://github.com/KaotoIO/kaoto-ui/issues/1587
            // Once UI implements the END step handling, STOP EIP has to get back to be an END step
            stopEip.setType("MIDDLE");
        }
        return stopEip;
    }
}
