package io.kaoto.backend.api.service.dsl.camelroute;

import io.kaoto.backend.api.service.deployment.generator.DeploymentGeneratorService;
import io.kaoto.backend.api.service.deployment.generator.camelroute.CamelRouteDeploymentGeneratorService;
import io.kaoto.backend.api.service.dsl.DSLSpecification;
import io.kaoto.backend.api.service.step.parser.camelroute.CamelRouteStepParserService;
import io.kaoto.backend.metadata.parser.step.camelroute.CamelRestDSLParseCatalog;
import io.quarkus.runtime.annotations.RegisterForReflection;
import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.io.IOException;
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

    private Logger log = Logger.getLogger(CamelRouteDSLSpecification.class);

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
        try {
            String schema = new String(CamelRouteDeploymentGeneratorService.class
                    .getResourceAsStream("camel-yaml-dsl.json").readAllBytes());
            return schema;
        } catch (IOException e) {
            log.error("Can't load Camel YAML DSL schema", e);
        }
        return "";
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
