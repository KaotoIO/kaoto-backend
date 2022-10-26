package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
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

    public EIPStep() {
        //Needed for serialization
    }

    public EIPStep(Step step) {
        for (var parameter : step.getParameters()) {
            if (parameter.getValue() != null) {
                try {
                    assignAttribute(parameter);
                } catch (Exception e) {
                    log.error("Couldn't assign value to parameter " + parameter.getId(), e);
                }
            }
        }
    }

    public Step getStep(final StepCatalog catalog, String name) {

        Optional<Step> res = catalog.getReadOnlyCatalog()
                .searchByName(name).stream()
                .filter(step -> step.getKind().equalsIgnoreCase("EIP"))
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

        }

        return res.orElse(null);
    }

    abstract void assignAttribute(final Parameter parameter);

    abstract void assignProperty(final Parameter parameter);

    abstract Map<String, Object> getRepresenterProperties();
}
