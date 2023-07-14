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
public class ThrowExceptionFlowStep implements FlowStep {

    public static final String LABEL = "throw-exception";
    public static final String LABEL2 = "throwException";

    private ThrowException throwException;

    @JsonCreator
    public ThrowExceptionFlowStep(final @JsonProperty(LABEL) ThrowException throwException,
                                  final @JsonProperty(LABEL2) ThrowException throwException2) {
        super();
        setThrowException(throwException != null ? throwException : throwException2);
    }

    public ThrowExceptionFlowStep(final Step step) {
        setThrowException(new ThrowException(step));
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(LABEL, this.getThrowException().getRepresenterProperties());
        return properties;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final boolean start, final boolean end) {
        return this.getThrowException().getStep(catalog, LABEL, kameletStepParserService);
    }

    public ThrowException getThrowException() {
        return throwException;
    }

    public void setThrowException(final ThrowException throwException) {
        this.throwException = throwException;
    }
}
