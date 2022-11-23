package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.model.step.Step;

import java.util.HashMap;
import java.util.Map;


@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RemoveHeadersFlowStep implements FlowStep {

    public static final String LABEL = "remove-headers";
    public static final String LABEL2 = "removeHeaders";

    private RemoveHeaders removeHeaders;

    @JsonCreator
    public RemoveHeadersFlowStep(final @JsonProperty(LABEL) RemoveHeaders removeHeaders,
                                 final @JsonProperty(LABEL2) RemoveHeaders removeHeaders2) {
        setRemoveHeaders(removeHeaders != null ? removeHeaders : removeHeaders2);
    }

    public RemoveHeadersFlowStep() {
        //Needed for serialization
    }

    public RemoveHeadersFlowStep(Step step) {
        setRemoveHeaders(new RemoveHeaders(step));
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(LABEL, this.getRemoveHeaders().getRepresenterProperties());
        return properties;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final Boolean start, final Boolean end) {
        return getRemoveHeaders().getStep(catalog, LABEL, kameletStepParserService);
    }

    public RemoveHeaders getRemoveHeaders() {
        return removeHeaders;
    }

    public void setRemoveHeaders(final RemoveHeaders removeHeaders) {
        this.removeHeaders = removeHeaders;
    }
}
