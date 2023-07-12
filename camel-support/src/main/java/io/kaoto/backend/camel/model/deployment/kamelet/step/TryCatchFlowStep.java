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

import java.util.Map;

@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TryCatchFlowStep implements FlowStep {

    public static final String LABEL = "do-try";
    public static final String LABEL2 = "doTry";

    private TryCatch tryCatch;

    @JsonCreator
    public TryCatchFlowStep(final @JsonProperty(LABEL) TryCatch tryCatch,
                            final @JsonProperty(LABEL2) TryCatch tryCatch2) {
        super();
        setTryCatch(tryCatch != null ? tryCatch : tryCatch2);
    }

    public TryCatchFlowStep(final Step step, final KamelPopulator kameletPopulator) {
        super();
        setTryCatch(new TryCatch(step, kameletPopulator));
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        return Map.of(LABEL, getTryCatch().getRepresenterProperties());
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final Boolean start, final Boolean end) {
        return getTryCatch().getStep(catalog, LABEL, kameletStepParserService);
    }

    public TryCatch getTryCatch() {
        return tryCatch;
    }

    public void setTryCatch(final TryCatch tryCatch) {
        this.tryCatch = tryCatch;
    }
}
