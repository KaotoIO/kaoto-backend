package io.kaoto.backend.api.service.deployment;

import io.kaoto.backend.api.service.deployment.generator.DeploymentGeneratorService;
import io.kaoto.backend.model.step.Step;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 🐱class DeploymentService
 * This endpoint will return a list of views based on the parameters.
 */
@ApplicationScoped
public class DeploymentService {

    @Inject
    private Instance<DeploymentGeneratorService> parsers;

    /*
     * 🐱method yaml: String
     * 🐱param steps: List[Step]
     * 🐱param name: String
     *
     * Based on the provided steps, return a valid yaml string to deploy
     */
    public Map<String, String> crd(final String name, final Step[] stepArray) {

        List<Step> steps = Arrays.asList(stepArray);
        Map<String, String> strings = new HashMap<>();

        for (DeploymentGeneratorService parser : getParsers()) {
            if (parser.appliesTo(steps)) {
                strings.put(parser.identifier(), parser.parse(name, steps));
            }
        }

        return strings;
    }

    public Instance<DeploymentGeneratorService> getParsers() {
        return parsers;
    }

    public void setParsers(final Instance<DeploymentGeneratorService> parsers) {
        this.parsers = parsers;
    }
}
