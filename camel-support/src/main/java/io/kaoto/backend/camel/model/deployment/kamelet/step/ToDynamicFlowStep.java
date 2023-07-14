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
public class ToDynamicFlowStep implements FlowStep {

    public static final String LABEL = "to-d";
    public static final String LABEL2 = "toD";

    private ToDynamic toDynamic;

    public ToDynamicFlowStep(String uri) {
        super();
        this.setToDynamic(new ToDynamic());
        this.getToDynamic().setUri(uri);
    }

    @JsonCreator
    public ToDynamicFlowStep(final @JsonProperty(LABEL) ToDynamic toDynamic,
                             final @JsonProperty(LABEL2) ToDynamic toDynamic2) {
        super();
        setToDynamic(toDynamic != null ? toDynamic : toDynamic2);
    }

    public ToDynamicFlowStep(final Step step) {
        setToDynamic(new ToDynamic(step));
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(LABEL, this.getToDynamic().getRepresenterProperties());
        return properties;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final boolean start, final boolean end) {
        return this.getToDynamic().getStep(catalog, LABEL, kameletStepParserService);
    }

    public ToDynamic getToDynamic() {
        return toDynamic;
    }

    public void setToDynamic(final ToDynamic toDynamic) {
        this.toDynamic = toDynamic;
    }
}
