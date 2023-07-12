package io.kaoto.backend.camel.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.camel.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.camel.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.step.Step;

import java.io.Serial;
import java.util.HashMap;
import java.util.Map;


@JsonPropertyOrder({"aggregate"})
@JsonDeserialize(
        using = JsonDeserializer.None.class
)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AggregateFlowStep implements FlowStep {
    @Serial
    private static final long serialVersionUID = 7630089193555236497L;
    public static final String AGGREGATE_LABEL = "aggregate";


    @JsonProperty(AGGREGATE_LABEL)
    private Aggregate aggregate;

    public AggregateFlowStep() {
    }

    public AggregateFlowStep(Step step) {
        setAggregate(new Aggregate(step));
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> aggregator = new HashMap<>();
        aggregator.put(AGGREGATE_LABEL, aggregate.getRepresenterProperties());
        return aggregator;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final Boolean start, final Boolean end) {
        return aggregate.getStep(catalog, AGGREGATE_LABEL, kameletStepParserService);
    }

    public Aggregate getAggregate() {
        return aggregate;
    }

    public void setAggregate(final Aggregate aggregate) {
        this.aggregate = aggregate;
    }
}
