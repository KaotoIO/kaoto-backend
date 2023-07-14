package io.kaoto.backend.camel.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.camel.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.camel.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.camel.model.deployment.kamelet.expression.Script;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@JsonPropertyOrder({"script"})
@JsonDeserialize(
        using = JsonDeserializer.None.class
)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScriptFlowStep implements FlowStep {
    public static final String LABEL = "script";

    public ScriptFlowStep(final @JsonProperty(value = "script", required = true) Script script) {
        super();
        setScript(script);
    }

    @JsonProperty("script")
    private Script script;

    public Script getScript() {
        return script;
    }

    public void setScript(final Script script) {
        this.script = script;
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> values = new HashMap<>();
        values.put(LABEL, getScript());
        return values;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final boolean start, final boolean end) {
        Optional<Step> res = catalog.getReadOnlyCatalog()
                .searchByName("script").stream()
                .filter(step -> step.getKind().equalsIgnoreCase("EIP"))
                .findAny();

        if (res.isPresent()) {
            for (Parameter<?> p : res.get().getParameters()) {
                if (p.getId()
                        .equalsIgnoreCase(KameletStepParserService.NAME)) {
                    p.setValue(this.getScript().getName());
                } else if (p.getId()
                        .equalsIgnoreCase(KameletStepParserService.GROOVY)) {
                    p.setValue(this.getScript().getGroovy());
                } else if (p.getId()
                        .equalsIgnoreCase(KameletStepParserService.JAVASCRIPT)) {
                    p.setValue(this.getScript().getJavascript());
                } else if (p.getId()
                        .equalsIgnoreCase(KameletStepParserService.EXPRESSION)) {
                    p.setValue(this.getScript().getExpression());
                }
            }
            return res.get();
        }

        return null;
    }
}
