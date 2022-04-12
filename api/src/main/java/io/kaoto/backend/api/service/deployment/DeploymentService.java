package io.kaoto.backend.api.service.deployment;

import io.kaoto.backend.api.service.deployment.generator.DeploymentGeneratorService;
import io.kaoto.backend.model.step.Step;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 🐱class DeploymentService This endpoint will return a list of views based on
 * the parameters.
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
    public List<Map<String, String>> crd(final String name,
                                         final Step[] stepArray) {

        List<Step> steps = Arrays.asList(stepArray);
        List<Map<String, String>> res = new LinkedList<>();
        Map<String, Object> metadata = new HashMap<>();
        if (name != null && !name.isBlank()) {
            metadata.put("name", name);
        }

        for (DeploymentGeneratorService parser : getParsers()) {
            try {
                if (parser.appliesTo(steps)) {
                    Map<String, String> strings = new HashMap<>();
                    strings.put("dsl", parser.identifier());
                    strings.put("crd", parser.parse(steps, metadata));
                    res.add(strings);
                }
            } catch (Exception e) {
                log.warn("Parser " + parser.getClass() + "threw an unexpected"
                                + " error. ",
                        e);
            }
        }

        return res;
    }

    public Instance<DeploymentGeneratorService> getParsers() {
        return parsers;
    }

    public void setParsers(final Instance<DeploymentGeneratorService> parsers) {
        this.parsers = parsers;
    }
}
