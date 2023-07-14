package io.kaoto.backend.api.service.deployment;

import io.kaoto.backend.api.resource.v1.model.Integration;
import io.kaoto.backend.api.service.dsl.DSLSpecification;
import io.kaoto.backend.api.service.step.parser.StepParserService;
import io.kaoto.backend.model.step.Step;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 🐱miniclass DeploymentService (IntegrationsResource)
 * 🐱relationship compositionOf DSLSpecification, 0..N
 *
 * 🐱section
 * Service to interact with the cluster. This is the utility class the
 * resource relies on to perform the operations.
 */
@ApplicationScoped
public class DeploymentService {

    private static final Logger LOG = Logger.getLogger(DeploymentService.class);

    @Inject
    private Instance<DSLSpecification> parsers;

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
        List<Map<String, String>> res = new ArrayList<>();
        Map<String, Object> metadata = new HashMap<>();
        if (name != null && !name.isBlank()) {
            metadata.put("name", name);
        }

        for (DSLSpecification parser : getParsers()) {
            try {
                if (parser.appliesTo(steps) && parser.getDeploymentGeneratorService() != null) {
                    Map<String, String> strings = new HashMap<>();
                    strings.put("dsl", parser.identifier());
                    strings.put("crd", parser.getDeploymentGeneratorService().parse(steps, metadata,
                            Collections.emptyList()));
                    res.add(strings);
                }
            } catch (Exception e) {
                LOG.warn("Parser " + parser.getClass() + "threw an unexpected error. ", e);
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

        for (DSLSpecification parser : getParsers()) {
            try {
                if (parser.getDeploymentGeneratorService() != null
                        && parser.identifier().equalsIgnoreCase(dsl)
                        && parser.appliesTo(i.getSteps())) {
                    return parser.getDeploymentGeneratorService()
                            .parse(i.getSteps(), i.getMetadata(), i.getParameters());
                }
            } catch (Exception e) {
                LOG.warn("Parser " + parser.getClass() + "threw an unexpected error. ", e);
            }
        }

        for (DSLSpecification parser : getParsers()) {
            try {
                if (parser.appliesTo(i.getSteps())
                        && parser.getDeploymentGeneratorService() != null) {
                    return parser.getDeploymentGeneratorService()
                            .parse(i.getSteps(), i.getMetadata(), i.getParameters());
                }
            } catch (Exception e) {
                LOG.warn("Parser " + parser.getClass() + "threw an unexpected error. ", e);
            }
        }

        return null;
    }


    /*
     * 🐱method crds: String
     * 🐱param integrationList: List<Integration>
     * 🐱param dsl: String
     *
     * Based on the provided steps, return a valid yaml string to deploy
     */
    @WithSpan
    public String crds(final List<Integration> integrationList, final Map<String, Object> metadata) {
        List<StepParserService.ParseResult<Step>> integrations = new ArrayList<>();
        String dsl = null;

        for (Integration integration : integrationList) {
            var parseResult = new StepParserService.ParseResult<Step>();
            parseResult.setMetadata(integration.getMetadata());
            parseResult.setSteps(integration.getSteps());
            parseResult.setParameters(integration.getParameters());
            integrations.add(parseResult);
            if (integration.getDsl() != null) {
                if (dsl != null && !integration.getDsl().equalsIgnoreCase(dsl)) {
                    LOG.error("We were sent a mix of DSL in the same list of flows!");
                }
                dsl = integration.getDsl();
            }
        }

        if (metadata != null && !metadata.isEmpty()) {
            var parseResult = new StepParserService.ParseResult<Step>();
            parseResult.setMetadata(metadata);
            integrations.add(parseResult);
        }

        if (dsl != null) {
            for (DSLSpecification parser : getParsers()) {
                try {
                    if (parser.identifier().equalsIgnoreCase(dsl)
                            && parser.getDeploymentGeneratorService() != null) {
                        return parser.getDeploymentGeneratorService().parse(integrations);
                    }
                } catch (Exception e) {
                    LOG.warn("Parser " + parser.getClass() + "threw an unexpected error. ", e);
                    break;
                }
            }
        }

        for (DSLSpecification parser : getParsers()) {
            try {
                if (parser.appliesToFlows(integrations)
                        && parser.getDeploymentGeneratorService() != null) {
                    return parser.getDeploymentGeneratorService().parse(integrations);
                }
            } catch (Exception e) {
                LOG.warn("Parser " + parser.getClass() + "threw an unexpected error. ", e);
            }
        }

        return null;
    }

    public Instance<DSLSpecification> getParsers() {
        return parsers;
    }

    public void setParsers(final Instance<DSLSpecification> parsers) {
        this.parsers = parsers;
    }
}
