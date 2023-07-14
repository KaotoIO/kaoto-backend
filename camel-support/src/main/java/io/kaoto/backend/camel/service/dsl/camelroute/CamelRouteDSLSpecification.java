package io.kaoto.backend.camel.service.dsl.camelroute;

import io.kaoto.backend.api.service.deployment.generator.DeploymentGeneratorService;
import io.kaoto.backend.api.service.dsl.DSLSpecification;
import io.kaoto.backend.camel.KamelHelper;
import io.kaoto.backend.camel.metadata.parser.step.camelroute.CamelRestDSLParseCatalog;
import io.kaoto.backend.camel.service.deployment.generator.camelroute.CamelRouteDeploymentGeneratorService;
import io.kaoto.backend.camel.service.step.parser.camelroute.CamelRouteStepParserService;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@ApplicationScoped
@RegisterForReflection
public class CamelRouteDSLSpecification extends DSLSpecification {

    public static final String CAMEL_REST_DSL = CamelRestDSLParseCatalog.CAMEL_REST_DSL;
    public static final String CAMEL_REST_VERB = CamelRestDSLParseCatalog.CAMEL_REST_VERB;
    public static final String CAMEL_REST_ENDPOINT = CamelRestDSLParseCatalog.CAMEL_REST_ENDPOINT;
    private static final String CAMEL_CONNECTOR = "CAMEL-CONNECTOR";
    private static final String EIP = "EIP";
    private static final String EIP_BRANCHES = "EIP-BRANCH";
    private static final List<String> KINDS = Arrays.asList(
            CAMEL_CONNECTOR, EIP, EIP_BRANCHES, CAMEL_REST_DSL, CAMEL_REST_VERB, CAMEL_REST_ENDPOINT);

    private static final String VALIDATION_SCHEME = KamelHelper.loadResourceAsString(
            CamelRouteDeploymentGeneratorService.class,
            "camel-yaml-dsl.json").orElse("");

    private DeploymentGeneratorService deploymentGeneratorService;
    private CamelRouteStepParserService stepParserService;

    public String identifier() {
        return "Camel Route";
    }

    public String description() {
        return "A camel route is a non deployable in cluster workflow of actions and steps.";
    }

    @Override
    public List<String> getKinds() {
        return KINDS;
    }

    @Override
    public String validationSchema() {
        return VALIDATION_SCHEME;
    }

    @Override
    public boolean isDeployable() {
        return false;
    }

    @Override
    public Boolean doesSupportMultipleFlows() {
        return true;
    }

    @Override
    public Map<String, String> getVocabulary() {
        return Map.of("stepsName", "Steps");
    }

    @Override
    public Boolean doesSupportResourceDescription() { return false; }

    @Override
    public DeploymentGeneratorService getDeploymentGeneratorService() {
        return deploymentGeneratorService;
    }

    @Inject
    public void setDeploymentGeneratorService(CamelRouteDeploymentGeneratorService deploymentGeneratorService) {
        this.deploymentGeneratorService = deploymentGeneratorService;
    }

    @Override
    public CamelRouteStepParserService getStepParserService() {
        return stepParserService;
    }

    @Inject
    public void setStepParserService(CamelRouteStepParserService stepParserService) {
        this.stepParserService = stepParserService;
    }
}
