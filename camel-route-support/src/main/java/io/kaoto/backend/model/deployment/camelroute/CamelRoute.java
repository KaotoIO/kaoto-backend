package io.kaoto.backend.model.deployment.camelroute;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.kaoto.backend.KamelPopulator;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.model.deployment.kamelet.Flow;
import io.kaoto.backend.model.step.Step;

import java.util.LinkedList;
import java.util.List;

/**
 * 🐱class CamelRoute
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CamelRoute {
    private List<Flow> flows;

    public CamelRoute() {
        super();
    }

    public CamelRoute(final List<Step> steps, final StepCatalog catalog) {
        setFlows(new LinkedList<>());
        var flow = new Flow();
        flow.setFrom(new KamelPopulator(catalog).getFlow(steps));
        getFlows().add(flow);
    }

    public List<Flow> getFlows() {
        return flows;
    }

    public void setFlows(final List<Flow> flows) {
        this.flows = flows;
    }


}
