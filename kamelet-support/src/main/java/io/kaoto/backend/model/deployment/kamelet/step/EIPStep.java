package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Branch;
import io.kaoto.backend.model.step.Step;
import org.jboss.logging.Logger;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class EIPStep implements Serializable {
    protected static final Logger log = Logger.getLogger(EIPStep.class);

    //All steps have ids
    @JsonProperty("id")
    private String id;

    protected EIPStep() {
        //Needed for serialization
    }

    protected EIPStep(Step step) {
        if (step.getParameters() != null) {
            for (var parameter : step.getParameters()) {
                if (parameter.getValue() != null && !parameter.getValue().equals(parameter.getDefaultValue())) {
                    try {
                        assignAttribute(parameter);
                    } catch (Exception e) {
                        log.error("Couldn't assign value to attribute " + parameter.getId(), e);
                    }
                }
            }
        }
        setId(step.getStepId());
    }

    public EIPStep(Map<String, Object> map) {
        if(map.containsKey("id")) {
            this.setId(String.valueOf(map.get("id")));
        }
    }

    public Step getStep(final StepCatalog catalog, final String name,
                        final KameletStepParserService kameletStepParserService) {

        Optional<Step> res = catalog.getReadOnlyCatalog()
                .searchByName(name).stream()
                .filter(step -> step.getKind().equalsIgnoreCase("EIP")
                        || step.getKind().equalsIgnoreCase("EIP-BRANCH"))
                .findAny();


        if (res.isPresent()) {
            var step = res.get();
            for (var parameter : step.getParameters()) {
                try {
                    assignProperty(parameter);
                } catch (Exception e) {
                    log.error("Couldn't assign value to parameter " + parameter.getId(), e);
                }
            }
            step.setStepId(this.getId());
            processBranches(res.get(), catalog, kameletStepParserService);
        }

        return res.orElse(null);
    }

    protected abstract void assignAttribute(final Parameter parameter);

    protected void processBranches(final Step step, final StepCatalog catalog,
                         final KameletStepParserService kameletStepParserService) {
        //Ready to override
    }

    protected Branch createBranch(final String id, final List<FlowStep> steps,
                           final KameletStepParserService kameletStepParserService) {
        Branch branch = new Branch(id);
        if (steps != null) {
            int i = 0;
            int size = steps.size();
            for (var s : steps) {
                branch.getSteps().add(kameletStepParserService.processStep(s, false, ++i == size));
            }
        }
        return branch;
    }

    protected abstract void assignProperty(final Parameter parameter);

    public abstract Map<String, Object> getRepresenterProperties();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    protected Map<String, Object> getDefaultRepresenterProperties() {
        Map<String, Object> map = new LinkedHashMap<>();
        if(this.getId() != null) {
            map.put("id", this.getId());
        }
        return map;
    }
}
