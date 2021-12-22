package io.kaoto.backend.api.service.deployment;

import io.kaoto.backend.api.service.deployment.generator.DeploymentGeneratorService;
import io.kaoto.backend.model.step.Step;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

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
    public String yaml(final String name, final Step[] stepArray) {

        List<Step> steps = Arrays.asList(stepArray);

        for (DeploymentGeneratorService parser : getParsers()) {
            if (parser.appliesTo(steps)) {
                return parser.parse(name, steps);
            }
        }

        return "";
    }

    public Instance<DeploymentGeneratorService> getParsers() {
        return parsers;
    }

    public void setParsers(final Instance<DeploymentGeneratorService> parsers) {
        this.parsers = parsers;
    }
}
