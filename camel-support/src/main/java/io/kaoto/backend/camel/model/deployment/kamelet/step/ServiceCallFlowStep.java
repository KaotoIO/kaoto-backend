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
public class ServiceCallFlowStep implements FlowStep {
    public static final String LABEL = "service-call";
    public static final String LABEL2 = "serviceCall";


    private ServiceCall serviceCall;

    public ServiceCallFlowStep() {
        //Empty for serialization purposes
    }

    @JsonCreator
    public ServiceCallFlowStep(final @JsonProperty(LABEL) ServiceCall serviceCall,
                               final @JsonProperty(LABEL2) ServiceCall serviceCall2) {
        super();
        setServiceCall(serviceCall != null ? serviceCall : serviceCall2);
    }

    public ServiceCallFlowStep(Step step) {
        setServiceCall(new ServiceCall(step));
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> aggregator = new HashMap<>();
        aggregator.put(LABEL, getServiceCall().getRepresenterProperties());
        return aggregator;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final Boolean start, final Boolean end) {
        return getServiceCall().getStep(catalog, LABEL, kameletStepParserService);
    }

    public ServiceCall getServiceCall() {
        return serviceCall;
    }

    public void setServiceCall(final ServiceCall serviceCall) {
        this.serviceCall = serviceCall;
    }
}
