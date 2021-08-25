package io.zimara.backend.api.service.deployment;

import io.zimara.backend.api.service.parser.StepParserService;
import io.zimara.backend.api.service.parser.ViewParserService;
import io.zimara.backend.api.service.parser.step.KameletBindingStepParserService;
import io.zimara.backend.model.deployment.kamelet.KameletBinding;
import io.zimara.backend.model.deployment.kamelet.KameletBindingSpec;
import io.zimara.backend.model.deployment.kamelet.KameletBindingStep;
import io.zimara.backend.model.deployment.kamelet.KameletBindingStepRef;
import io.zimara.backend.model.step.Step;
import io.zimara.backend.model.step.kamelet.KameletStep;
import io.zimara.backend.model.view.ViewDefinition;
import org.jboss.logging.Logger;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.QueryParam;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 🐱class DeploymentService
 *
 * This endpoint will return a list of views based on the parameters.
 *
 */
@ApplicationScoped
public class DeploymentService {

    private Logger log = Logger.getLogger(DeploymentService.class);


    /*
     * 🐱method yaml: String
     * 🐱param steps: List[Step]
     * 🐱param name: String
     *
     * Based on the provided steps, return a valid yaml string to deploy
     */
    public String yaml(String name, Step[] steps) {

        KameletBindingSpec spec = new KameletBindingSpec();

        spec.setSource(createKameletBindingStep((KameletStep) steps[0]));

        for(int i = 1; i < steps.length - 1; i++) {
            spec.getSteps().add(createKameletBindingStep((KameletStep) steps[i]));
        }

        spec.setSink(createKameletBindingStep((KameletStep) steps[steps.length - 1]));

        KameletBinding binding = new KameletBinding(name, spec);

        Representer representer = new Representer();
        representer.getPropertyUtils().setAllowReadOnlyProperties(true);

        Yaml yaml = new Yaml(new Constructor(KameletBinding.class), representer);

        StringWriter writer = new StringWriter();
        yaml.dump(binding, writer);
        return writer.toString();
    }

    private KameletBindingStep createKameletBindingStep(KameletStep step) {
        KameletBindingStep kameletStep = new KameletBindingStep();

        KameletBindingStepRef ref = new KameletBindingStepRef();
        ref.setName(step.getName());
        kameletStep.setRef(ref);

        for(var p : step.getParameters()){
            if(p.getValue() != null) {
                kameletStep.getProperties().put(p.getId(), p.getValue().toString());
            }
        }

        return kameletStep;
    }
}
