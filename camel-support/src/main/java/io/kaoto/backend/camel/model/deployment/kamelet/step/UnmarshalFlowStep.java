package io.kaoto.backend.camel.model.deployment.kamelet.step;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.camel.model.deployment.kamelet.step.marshal.MarshalDeserializer;
import io.kaoto.backend.camel.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.step.Step;

import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

@JsonDeserialize(using = MarshalDeserializer.class)
public class UnmarshalFlowStep extends MarshalFlowStep {
    @Serial
    private static final long serialVersionUID = 5841231681362382129L;

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> step = new HashMap<>();
        Map<String, Object> properties = new HashMap<>();
        step.put("unmarshal", properties);
        if (getDataFormat() != null) {
            properties.put(getDataFormat().getFormat(), getDataFormat().getProperties());
        }
        return step;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final Boolean start, final Boolean end) {
        Step res = catalog.getReadOnlyCatalog()
                .searchByName("unmarshal").stream()
                .filter(step -> step.getKind().equalsIgnoreCase("EIP")
                        || step.getKind().equalsIgnoreCase("EIP-BRANCH"))
                .findAny().orElse(null);
        if (res != null) {
            assignParameters(res);
        }
        return res;
    }
}
