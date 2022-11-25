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
public class RemovePropertiesFlowStep implements FlowStep {

    public static final String LABEL = "remove-properties";
    public static final String LABEL2 = "removeProperties";

    private RemoveHeaders removeProperties;

    @JsonCreator
    public RemovePropertiesFlowStep(final @JsonProperty(LABEL) RemoveHeaders removeProperties,
                                    final @JsonProperty(LABEL2) RemoveHeaders removeProperties2) {
        setRemoveProperties(removeProperties != null ? removeProperties : removeProperties2);
    }

    public RemovePropertiesFlowStep() {
        //Needed for serialization
    }

    public RemovePropertiesFlowStep(Step step) {
        setRemoveProperties(new RemoveHeaders(step));
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(LABEL, this.getRemoveProperties().getRepresenterProperties());
        return properties;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final Boolean start, final Boolean end) {
        return getRemoveProperties().getStep(catalog, LABEL, kameletStepParserService);
    }

    public RemoveHeaders getRemoveProperties() {
        return removeProperties;
    }

    public void setRemoveProperties(final RemoveHeaders removeProperties) {
        this.removeProperties = removeProperties;
    }
}
