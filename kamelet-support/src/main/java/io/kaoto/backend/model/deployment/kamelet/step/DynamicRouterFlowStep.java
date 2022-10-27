package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.model.step.Step;

import java.util.HashMap;
import java.util.Map;

@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DynamicRouterFlowStep implements FlowStep {
    public static final String LABEL = "dynamic-router";
    public static final String LABEL2 = "dynamicRouter";

    private DynamicRouter dynamicRouter;

    @JsonCreator
    public DynamicRouterFlowStep(final @JsonProperty(LABEL) DynamicRouter dynamicRouter,
                                 final @JsonProperty(LABEL2) DynamicRouter dynamicRouter2) {
        super();
        setDynamicRouter(dynamicRouter != null ? dynamicRouter : dynamicRouter2);
    }

    public DynamicRouterFlowStep(final Step step) {
        super();
        setDynamicRouter(new DynamicRouter(step));
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> circuitbreaker = new HashMap<>();
        circuitbreaker.put(LABEL, getDynamicRouter().getRepresenterProperties());
        return circuitbreaker;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService) {
        return getDynamicRouter().getStep(catalog, LABEL, kameletStepParserService);
    }

    public DynamicRouter getDynamicRouter() {
        return dynamicRouter;
    }

    public void setDynamicRouter(final DynamicRouter dynamicRouter) {
        this.dynamicRouter = dynamicRouter;
    }
}
