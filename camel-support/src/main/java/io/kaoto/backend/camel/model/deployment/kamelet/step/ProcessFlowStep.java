package io.kaoto.backend.camel.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.camel.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.camel.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.step.Step;

import java.util.HashMap;
import java.util.Map;


@JsonPropertyOrder({"process"})
@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProcessFlowStep implements FlowStep {

    public static final String LABEL = "process";


    private Process process;

    @JsonCreator
    public ProcessFlowStep(final @JsonProperty(LABEL) Process process) {
        super();
        setProcess(process);
    }

    public ProcessFlowStep() {
        //Needed for serialization
    }

    public ProcessFlowStep(Step step) {
        setProcess(new Process(step));
    }


    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(LABEL, getProcess().getRepresenterProperties());
        return properties;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final boolean start, final boolean end) {
        return getProcess().getStep(catalog, LABEL, kameletStepParserService);
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(final Process process) {
        this.process = process;
    }
}
