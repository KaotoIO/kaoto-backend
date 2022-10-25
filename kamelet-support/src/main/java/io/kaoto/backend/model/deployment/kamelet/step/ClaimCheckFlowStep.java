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


@JsonPropertyOrder({"claim-check"})
@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClaimCheckFlowStep implements FlowStep {
    @Serial
    private static final long serialVersionUID = 76302498136497L;

    @JsonCreator
    public ClaimCheckFlowStep(final @JsonProperty(value = "claim-check", required = true) ClaimCheck claimCheck) {
        setClaimCheck(claimCheck);
    }

    public ClaimCheckFlowStep() {
        //Needed for serialization
    }

    public ClaimCheckFlowStep(Step step) {
        setClaimCheck(new ClaimCheck(step));
    }

    @JsonProperty("claim-check")
    private ClaimCheck claimCheck;

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("claim-check", this.getClaimCheck().getRepresenterProperties());
        return properties;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService) {
        return getClaimCheck().getStep(catalog, kameletStepParserService);
    }

    public ClaimCheck getClaimCheck() {
        return claimCheck;
    }

    public void setClaimCheck(final ClaimCheck claimCheck) {
        this.claimCheck = claimCheck;
    }
}
