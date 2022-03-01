package io.kaoto.backend.api.service.deployment;

import io.kaoto.backend.api.service.deployment.generator.DeploymentGeneratorService;
import io.kaoto.backend.model.step.Step;
import org.jboss.logging.Logger;

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

    private Logger log = Logger.getLogger(DeploymentService.class);

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
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("name", name);

        for (DeploymentGeneratorService parser : getParsers()) {
            try {
                if (parser.appliesTo(steps)) {
                    strings.put(parser.identifier(),
                            parser.parse(steps, metadata));
                }
            } catch (Exception e) {
                log.warn("Parser " + parser.getClass() + "threw an unexpected"
                                + " error. ",
                        e);
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
