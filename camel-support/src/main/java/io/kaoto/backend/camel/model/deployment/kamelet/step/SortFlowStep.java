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
public class SortFlowStep implements FlowStep {
    public static final String LABEL = "sort";

    private Sort sort;

    public SortFlowStep() {
        //Empty for serialization purposes
    }

    @JsonCreator
    public SortFlowStep(final @JsonProperty(LABEL) Sort sort) {
        super();
        setSort(sort);
    }

    public SortFlowStep(Step step) {
        setSort(new Sort(step));
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> aggregator = new HashMap<>();
        aggregator.put(LABEL, getSort().getRepresenterProperties());
        return aggregator;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final Boolean start, final Boolean end) {
        return getSort().getStep(catalog, LABEL, kameletStepParserService);
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(final Sort sort) {
        this.sort = sort;
    }
}
