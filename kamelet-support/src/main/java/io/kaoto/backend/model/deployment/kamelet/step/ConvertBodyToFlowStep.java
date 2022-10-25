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
import io.kaoto.backend.model.step.Step;

import java.io.Serial;
import java.util.HashMap;
import java.util.Map;


@JsonPropertyOrder({"convert-body-to"})
@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConvertBodyToFlowStep implements FlowStep {
    @Serial
    private static final long serialVersionUID = 15438685786797L;

    @JsonCreator
    public ConvertBodyToFlowStep(
            final @JsonProperty(value = "convert-body-to") ConvertBodyTo convertBodyTo,
            final @JsonProperty(value = "convertBodyTo") ConvertBodyTo convertBodyTo2) {
        if (convertBodyTo != null) {
            setConvertBodyTo(convertBodyTo);
        } else if (convertBodyTo2 != null) {
            setConvertBodyTo(convertBodyTo2);
        }
    }

    public ConvertBodyToFlowStep() {
        //Needed for serialization
    }

    public ConvertBodyToFlowStep(Step step) {
        setConvertBodyTo(new ConvertBodyTo(step));
    }

    private ConvertBodyTo convertBodyTo;

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("convert-body-to", this.getConvertBodyTo().getRepresenterProperties());
        return properties;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService) {
        return getConvertBodyTo().getStep(catalog, kameletStepParserService);
    }

    public ConvertBodyTo getConvertBodyTo() {
        return convertBodyTo;
    }

    public void setConvertBodyTo(final ConvertBodyTo convertBodyTo) {
        this.convertBodyTo = convertBodyTo;
    }
}
