package io.kaoto.backend.camel.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.camel.KamelPopulator;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.camel.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.camel.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.step.Step;

import java.util.HashMap;
import java.util.Map;

@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class IdempotentConsumerFlowStep implements FlowStep {

    public static final String LABEL = "idempotent-consumer";
    public static final String LABEL2 = "idempotentConsumer";

    private IdempotentConsumer idempotentConsumer;

    @JsonCreator
    public IdempotentConsumerFlowStep(final @JsonProperty(value = LABEL) IdempotentConsumer idempotentConsumer,
                                      final @JsonProperty(value = LABEL2) IdempotentConsumer idempotentConsumer2) {
        super();
        setIdempotentConsumer(idempotentConsumer != null ? idempotentConsumer : idempotentConsumer2);
    }

    public IdempotentConsumerFlowStep(final Step step, final KamelPopulator kameletPopulator) {
        setIdempotentConsumer(new IdempotentConsumer(step, kameletPopulator));
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(LABEL, this.getIdempotentConsumer());
        return properties;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final Boolean start, final Boolean end) {
        return this.getIdempotentConsumer().getStep(catalog, LABEL, kameletStepParserService);
    }

    public IdempotentConsumer getIdempotentConsumer() {
        return idempotentConsumer;
    }

    public void setIdempotentConsumer(final IdempotentConsumer idempotentConsumer) {
        this.idempotentConsumer = idempotentConsumer;
    }
}
