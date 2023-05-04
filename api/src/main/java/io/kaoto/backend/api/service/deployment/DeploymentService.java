package io.kaoto.backend.api.service.deployment;

import io.kaoto.backend.api.resource.v1.model.Integration;
import io.kaoto.backend.api.service.deployment.generator.DeploymentGeneratorService;
import io.kaoto.backend.api.service.step.parser.StepParserService;
import io.kaoto.backend.model.step.Step;
import io.opentelemetry.extension.annotations.WithSpan;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 🐱miniclass DeploymentService (IntegrationsResource)
 * 🐱relationship compositionOf DeploymentGeneratorService, 0..1
 *
 *
 * 🐱section
 * Service to interact with the cluster. This is the utility class the
 * resource relies on to perform the operations.
 *
 */
@ApplicationScoped
public class DeploymentService {

    private Logger log = Logger.getLogger(DeploymentService.class);

    @Inject
    private Instance<DeploymentGeneratorService> parsers;

    /*
     * 🐱method integration: Map
     * 🐱param name: String
     * 🐱param stepArray: List[Step]
     *
     * Based on the provided steps, returns a valid CRDs to deploy
     */
    @WithSpan
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
                    strings.put("crd", parser.parse(steps,
                            metadata, Collections.emptyList()));
                    res.add(strings);
                }
            } catch (Exception e) {
                log.warn("Parser " + parser.getClass()
                                + "threw an unexpected error. ",
                        e);
            }
        }

        return res;
    }
    /*
     * 🐱method crd: String
     * 🐱param i: Integration
     * 🐱param dsl: String
     *
     * Based on the provided steps, return a valid yaml string to deploy
     */
    @WithSpan
    public String crd(final Integration i, final String dsl) {

        for (DeploymentGeneratorService parser : getParsers()) {
            try {
                if (parser.identifier().equalsIgnoreCase(dsl) && parser.appliesTo(i.getSteps())) {
                    return parser.parse(i.getSteps(), i.getMetadata(), i.getParameters());
                }
            } catch (Exception e) {
                log.warn("Parser " + parser.getClass() + "threw an unexpected"
                                + " error. ",
                        e);
            }
        }

        for (DeploymentGeneratorService parser : getParsers()) {
            try {
                if (parser.appliesTo(i.getSteps())) {
                    return parser.parse(i.getSteps(), i.getMetadata(), i.getParameters());
                }
            } catch (Exception e) {
                log.warn("Parser " + parser.getClass() + "threw an unexpected"
                                + " error. ",
                        e);
            }
        }

        return null;
    }


    /*
     * 🐱method crds: String
     * 🐱param i: List<Integration>
     * 🐱param dsl: String
     *
     * Based on the provided steps, return a valid yaml string to deploy
     */
    @WithSpan
    public String crds(final List<Integration> i, final Map<String, Object> metadata, final String dsl) {
        List<StepParserService.ParseResult<Step>> integrations = new LinkedList<>();

        for (Integration integration : i) {
            var parseResult = new StepParserService.ParseResult<Step>();
            parseResult.setMetadata(integration.getMetadata());
            parseResult.setSteps(integration.getSteps());
            parseResult.setParameters(integration.getParameters());
            integrations.add(parseResult);
        }

        if (metadata != null && !metadata.isEmpty()) {
            var parseResult = new StepParserService.ParseResult<Step>();
            parseResult.setMetadata(metadata);
            integrations.add(parseResult);
        }

        if (dsl != null) {
            for (DeploymentGeneratorService parser : getParsers()) {
                try {
                    if (parser.identifier().equalsIgnoreCase(dsl)) {
                        return parser.parse(integrations);
                    }
                } catch (Exception e) {
                    log.warn("Parser " + parser.getClass() + "threw an unexpected error. ", e);
                    break;
                }
            }
        }

        for (DeploymentGeneratorService parser : getParsers()) {
            try {
                if (parser.appliesToFlows(integrations)) {
                    return parser.parse(integrations);
                }
            } catch (Exception e) {
                log.warn("Parser " + parser.getClass() + "threw an unexpected error. ", e);
            }
        }

        return null;
    }

    public Instance<DeploymentGeneratorService> getParsers() {
        return parsers;
    }

    public void setParsers(final Instance<DeploymentGeneratorService> parsers) {
        this.parsers = parsers;
    }
}
