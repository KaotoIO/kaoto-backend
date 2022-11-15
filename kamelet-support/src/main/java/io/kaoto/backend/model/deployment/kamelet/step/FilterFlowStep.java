package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.model.step.Branch;
import io.kaoto.backend.model.step.Step;

import java.io.Serial;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@JsonPropertyOrder({"filter"})
@JsonDeserialize(
        using = JsonDeserializer.None.class
)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilterFlowStep implements FlowStep {
    @Serial
    private static final long serialVersionUID = -3320625752519808958L;

    @JsonCreator
    public FilterFlowStep(
            final @JsonProperty(value = "filter") Filter filter) {
        super();
        setFilter(filter);
    }

    @JsonProperty("filter")
    private Filter filter;

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(
            final Filter filter) {
        this.filter = filter;
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("filter", this.getFilter());
        return properties;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final Boolean start, final Boolean end) {
        Step res = catalog.getReadOnlyCatalog().searchByID("filter");
        res.setBranches(new LinkedList<>());

        var flow = this.getFilter();
        Branch branch = new Branch(kameletStepParserService.getFilterIdentifier(flow));
        branch.setCondition(kameletStepParserService.getFilterCondition(flow));

        int i = 0;
        for (var s : flow.getSteps()) {
            branch.getSteps().add(kameletStepParserService.processStep(s, i == 0, i++ == flow.getSteps().size() - 1));
        }
        kameletStepParserService.setValueOnStepProperty(res, KameletStepParserService.SIMPLE, branch.getCondition());
        res.getBranches().add(branch);

        return res;
    }
}
