package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SetExchangePatternFlowStep implements FlowStep {

    public static final String LABEL = "set-exchange-pattern";
    public static final String LABEL2 = "setExchangePattern";

    private String exchangePattern;

    @JsonCreator
    public SetExchangePatternFlowStep(final @JsonProperty(LABEL) String exchangePattern,
                                      final @JsonProperty(LABEL2) String exchangePattern2) {
        super();
        setExchangePattern(exchangePattern != null ? exchangePattern : exchangePattern2);
    }

    public SetExchangePatternFlowStep(Step step) {
        setExchangePattern(String.valueOf(step.getParameters().get(0).getValue()));
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(LABEL, getExchangePattern());
        return properties;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final Boolean start, final Boolean end) {

        Optional<Step> res = catalog.getReadOnlyCatalog()
                .searchByName(LABEL).stream()
                .filter(step -> step.getKind().equalsIgnoreCase("EIP"))
                .findAny();

        if (res.isPresent()) {
            for (Parameter p : res.get().getParameters()) {
                if (!p.getId().equalsIgnoreCase("step-id-kaoto")) {
                    p.setValue(getExchangePattern());
                }
            }
        }

        return res.orElse(null);
    }

    public String getExchangePattern() {
        return exchangePattern;
    }

    public void setExchangePattern(final String exchangePattern) {
        this.exchangePattern = exchangePattern;
    }
}
