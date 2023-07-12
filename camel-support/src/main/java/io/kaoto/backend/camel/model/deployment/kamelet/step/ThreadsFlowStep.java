package io.kaoto.backend.camel.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.camel.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.camel.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.model.step.Step;

import java.util.HashMap;
import java.util.Map;


@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ThreadsFlowStep implements FlowStep {
    public static final String LABEL = "threads";

    private Threads threads;

    public ThreadsFlowStep() {
        //Empty for serialization purposes
    }

    @JsonCreator
    public ThreadsFlowStep(final @JsonProperty(LABEL) Threads threads) {
        super();
        setThreads(threads);
    }

    public ThreadsFlowStep(Step step) {
        setThreads(new Threads(step));
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> aggregator = new HashMap<>();
        aggregator.put(LABEL, getThreads().getRepresenterProperties());
        return aggregator;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final Boolean start, final Boolean end) {
        return getThreads().getStep(catalog, LABEL, kameletStepParserService);
    }

    public Threads getThreads() {
        return threads;
    }

    public void setThreads(final Threads threads) {
        this.threads = threads;
    }
}
