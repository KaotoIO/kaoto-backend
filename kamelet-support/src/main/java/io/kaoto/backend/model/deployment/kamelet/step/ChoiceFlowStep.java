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
import io.kaoto.backend.model.deployment.kamelet.step.choice.Choice;
import io.kaoto.backend.model.deployment.kamelet.step.choice.SuperChoice;
import io.kaoto.backend.model.step.Branch;
import io.kaoto.backend.model.step.Step;

import java.io.Serial;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@JsonPropertyOrder({"choice"})
@JsonDeserialize(
        using = JsonDeserializer.None.class
)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChoiceFlowStep implements FlowStep {
    @Serial
    private static final long serialVersionUID = -3320625752519808958L;

    @JsonCreator
    public ChoiceFlowStep(
           final @JsonProperty(value = "choice") SuperChoice choice) {
        super();
        setChoice(choice);
    }

    @JsonProperty("choice")
    private SuperChoice choice;

    public SuperChoice getChoice() {
        return choice;
    }

    public void setChoice(
            final SuperChoice choice) {
        this.choice = choice;
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("choice", this.getChoice());
        return properties;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final Boolean start, final Boolean end) {
        Step res = catalog.getReadOnlyCatalog().searchByID("choice");
        res.setBranches(new LinkedList<>());

        for (var flow : this.getChoice().getChoice()) {
            Branch branch = new Branch(getChoiceIdentifier(flow));
            branch.setCondition(getChoiceCondition(flow));
            int i = 0;
            for (var s : flow.getSteps()) {
                branch.getSteps().add(kameletStepParserService.processStep(s, i == 0,
                        i++ == flow.getSteps().size() - 1));
            }
            kameletStepParserService.setValueOnStepProperty(res, KameletStepParserService.SIMPLE,
                    branch.getCondition());
            res.getBranches().add(branch);
        }

        if (this.getChoice().getOtherwise() != null) {
            Branch branch = new Branch(KameletStepParserService.OTHERWISE);

            int i = 0;
            for (var s : this.getChoice().getOtherwise().getSteps()) {
                branch.getSteps().add(kameletStepParserService.processStep(s, i == 0,
                        i++ == this.getChoice().getOtherwise().getSteps().size() - 1));
            }
            res.getBranches().add(branch);
        }

        return res;
    }
    private String getChoiceIdentifier(final Choice flow) {
        return flow.getSimple();
    }

    private String getChoiceCondition(final Choice flow) {
        return flow.getSimple();
    }

}
