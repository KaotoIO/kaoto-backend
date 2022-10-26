package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;
import org.jboss.logging.Logger;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class EIPStep implements Serializable {
    static final Logger log = Logger.getLogger(EIPStep.class);

    EIPStep() {
        //Needed for serialization
    }

    EIPStep(Step step) {
        for (var parameter : step.getParameters()) {
            if (parameter.getValue() != null) {
                try {
                    assignAttribute(parameter);
                } catch (Exception e) {
                    log.error("Couldn't assign value to attribute " + parameter.getId(), e);
                }
            }
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
            processBranches(res.get(), catalog, kameletStepParserService);
        }

        return res.orElse(null);
    }

    abstract void assignAttribute(final Parameter parameter);

    void processBranches(final Step step, final StepCatalog catalog,
                         final KameletStepParserService kameletStepParserService) {
        //Ready to override
    }

    abstract void assignProperty(final Parameter parameter);

    abstract Map<String, Object> getRepresenterProperties();
}
