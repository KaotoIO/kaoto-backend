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

import java.util.HashMap;
import java.util.Map;


@JsonPropertyOrder({"claim-check"})
@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClaimCheckFlowStep implements FlowStep {

    public static final String CLAIM_CHECK_LABEL = "claim-check";

    @JsonCreator
    public ClaimCheckFlowStep(final @JsonProperty(CLAIM_CHECK_LABEL) ClaimCheck claimCheck,
                              final @JsonProperty("claimCheck") ClaimCheck claimCheck2) {
        setClaimCheck(claimCheck != null ? claimCheck : claimCheck2);
    }

    public ClaimCheckFlowStep() {
        //Needed for serialization
    }

    public ClaimCheckFlowStep(Step step) {
        setClaimCheck(new ClaimCheck(step));
    }

    private ClaimCheck claimCheck;

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(CLAIM_CHECK_LABEL, this.getClaimCheck().getRepresenterProperties());
        return properties;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final Boolean start, final Boolean end) {
        return getClaimCheck().getStep(catalog, CLAIM_CHECK_LABEL, kameletStepParserService);
    }

    public ClaimCheck getClaimCheck() {
        return claimCheck;
    }

    public void setClaimCheck(final ClaimCheck claimCheck) {
        this.claimCheck = claimCheck;
    }
}
