package io.kaoto.backend.camel.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.camel.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.camel.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.step.Step;

import java.util.HashMap;
import java.util.Map;


@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecipientListFlowStep implements FlowStep {

    public static final String LABEL = "recipient-list";

    private RecipientList recipientList;

    @JsonCreator
    public RecipientListFlowStep(final @JsonProperty(LABEL) RecipientList recipientList) {
        setRecipientList(recipientList);
    }

    public RecipientListFlowStep() {
        //Needed for serialization
    }

    public RecipientListFlowStep(Step step) {
        setRecipientList(new RecipientList(step));
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(LABEL, this.getRecipientList().getRepresenterProperties());
        return properties;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final Boolean start, final Boolean end) {
        return getRecipientList().getStep(catalog, LABEL, kameletStepParserService);
    }

    public RecipientList getRecipientList() {
        return recipientList;
    }

    public void setRecipientList(final RecipientList recipientList) {
        this.recipientList = recipientList;
    }
}
