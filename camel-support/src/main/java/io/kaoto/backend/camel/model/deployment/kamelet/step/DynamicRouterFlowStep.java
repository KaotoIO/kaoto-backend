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
public class DynamicRouterFlowStep implements FlowStep {
    public static final String LABEL = "dynamic-router";
    public static final String LABEL2 = "dynamicRouter";

    private RoutingSlip dynamicRouter;

    @JsonCreator
    public DynamicRouterFlowStep(final @JsonProperty(LABEL) RoutingSlip dynamicRouter,
                                 final @JsonProperty(LABEL2) RoutingSlip dynamicRouter2) {
        super();
        setDynamicRouter(dynamicRouter != null ? dynamicRouter : dynamicRouter2);
    }

    public DynamicRouterFlowStep(final Step step) {
        super();
        setDynamicRouter(new RoutingSlip(step));
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> circuitbreaker = new HashMap<>();
        circuitbreaker.put(LABEL, getDynamicRouter().getRepresenterProperties());
        return circuitbreaker;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final Boolean start, final Boolean end) {
        return getDynamicRouter().getStep(catalog, LABEL, kameletStepParserService);
    }

    public RoutingSlip getDynamicRouter() {
        return dynamicRouter;
    }

    public void setDynamicRouter(final RoutingSlip dynamicRouter) {
        this.dynamicRouter = dynamicRouter;
    }
}
