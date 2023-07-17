package io.kaoto.backend.camel.service.dsl.kamelet;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.jboss.logging.Logger;

import io.kaoto.backend.api.service.deployment.generator.DeploymentGeneratorService;
import io.kaoto.backend.api.service.dsl.DSLSpecification;
import io.kaoto.backend.api.service.step.parser.StepParserService;
import io.kaoto.backend.camel.service.deployment.generator.kamelet.KameletBindingDeploymentGeneratorService;
import io.kaoto.backend.camel.service.step.parser.kamelet.KameletBindingStepParserService;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
@RegisterForReflection
public class KameletBindingDSLSpecification extends DSLSpecification {

    private static final String KAMELET = "KAMELET";
    public static final String KNATIVE = "KNATIVE";
    public static final List<String> KINDS = Arrays.asList(KAMELET, KNATIVE);
    private static final Logger LOG = Logger.getLogger(KameletBindingDSLSpecification.class);

    private DeploymentGeneratorService deploymentGeneratorService;

    private StepParserService stepParserService;

    public String identifier() {
        return "KameletBinding";
    }

    public String description() {
        return "Kamelet Bindings are used to create simple integrations that link a start step to an end step "
                + "with optional intermediate action steps.";
    }


    @Override
    public String validationSchema() {
        try {
            String schema = new String(KameletBindingDSLSpecification.class
                    .getResourceAsStream("kameletbinding.json").readAllBytes());
            return schema;
        } catch (IOException e) {
            LOG.error("Can't load Kamelet Binding DSL schema", e);
        }
        return "";
    }

    @Override
    public List<String> getKinds() {
        return KINDS;
    }

    @Override
    public boolean isDeployable() {
        return true;
    }

    @Override
    public Boolean doesSupportMultipleFlows() {
        return false;
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
    public void setDeploymentGeneratorService(KameletBindingDeploymentGeneratorService deploymentGeneratorService) {
        this.deploymentGeneratorService = deploymentGeneratorService;
    }

    @Override
    public StepParserService getStepParserService() {
        return stepParserService;
    }

    @Inject
    public void setStepParserService(KameletBindingStepParserService stepParserService) {
        this.stepParserService = stepParserService;
    }
}
