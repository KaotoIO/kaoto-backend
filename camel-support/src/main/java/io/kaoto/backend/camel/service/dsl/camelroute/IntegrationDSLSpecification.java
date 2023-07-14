package io.kaoto.backend.camel.service.dsl.camelroute;

import io.kaoto.backend.api.service.deployment.generator.DeploymentGeneratorService;
import io.kaoto.backend.api.service.dsl.DSLSpecification;
import io.kaoto.backend.api.service.step.parser.StepParserService;
import io.kaoto.backend.camel.KamelHelper;
import io.kaoto.backend.camel.service.deployment.generator.camelroute.CamelRouteDeploymentGeneratorService;
import io.kaoto.backend.camel.service.deployment.generator.camelroute.IntegrationDeploymentGeneratorService;
import io.kaoto.backend.camel.service.step.parser.camelroute.IntegrationStepParserService;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@ApplicationScoped
@RegisterForReflection
public class IntegrationDSLSpecification extends DSLSpecification {

    private static final String CAMEL_CONNECTOR = "CAMEL-CONNECTOR";
    private static final String EIP = "EIP";
    private static final String EIP_BRANCHES = "EIP-BRANCH";

    private static final List<String> KINDS = Arrays.asList(CAMEL_CONNECTOR, EIP, EIP_BRANCHES);
    private static final String VALIDATION_SCHEME = KamelHelper.loadResourceAsString(
            CamelRouteDeploymentGeneratorService.class,
            "integration.json").orElse("");

    private DeploymentGeneratorService deploymentGeneratorService;

    private StepParserService stepParserService;



    public String identifier() {
        return "Integration";
    }

    public String description() {
        return "An Integration defines a workflow of actions and steps.";
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
        return true;
    }

    @Override
    public Boolean doesSupportMultipleFlows() {
        return true;
    }

    @Override
    public Boolean doesSupportResourceDescription() { return true; }

    @Override
    public Map<String, String> getVocabulary() {
        return Map.of("stepsName", "Steps");
    }

    @Override
    public DeploymentGeneratorService getDeploymentGeneratorService() {
        return deploymentGeneratorService;
    }

    @Inject
    public void setDeploymentGeneratorService(IntegrationDeploymentGeneratorService deploymentGeneratorService) {
        this.deploymentGeneratorService = deploymentGeneratorService;
    }

    @Override
    public StepParserService getStepParserService() {
        return stepParserService;
    }

    @Inject
    public void setStepParserService(IntegrationStepParserService stepParserService) {
        this.stepParserService = stepParserService;
    }
}
