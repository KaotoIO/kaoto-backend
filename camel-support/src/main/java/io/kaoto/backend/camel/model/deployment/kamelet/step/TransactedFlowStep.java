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
public class TransactedFlowStep implements FlowStep {

    public static final String LABEL = "transacted";

    private Transacted transacted;

    @JsonCreator
    public TransactedFlowStep(final @JsonProperty(LABEL) Transacted transacted) {
        super();
        setTransacted(transacted);
    }

    public TransactedFlowStep(final Step step) {
        setTransacted(new Transacted(step));
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(LABEL, this.getTransacted().getRepresenterProperties());
        return properties;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final Boolean start, final Boolean end) {
        return this.getTransacted().getStep(catalog, LABEL, kameletStepParserService);
    }

    public Transacted getTransacted() {
        return transacted;
    }

    public void setTransacted(final Transacted transacted) {
        this.transacted = transacted;
    }
}
