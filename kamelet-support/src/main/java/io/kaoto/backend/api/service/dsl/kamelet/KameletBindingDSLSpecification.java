package io.kaoto.backend.api.service.dsl.kamelet;

import io.kaoto.backend.api.service.deployment.generator.DeploymentGeneratorService;
import io.kaoto.backend.api.service.deployment.generator.kamelet.KameletBindingDeploymentGeneratorService;
import io.kaoto.backend.api.service.dsl.DSLSpecification;
import io.kaoto.backend.api.service.step.parser.StepParserService;
import io.kaoto.backend.api.service.step.parser.kamelet.KameletBindingStepParserService;
import io.kaoto.backend.metadata.parser.step.camelroute.CamelRouteFileProcessor;
import io.quarkus.runtime.annotations.RegisterForReflection;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
@RegisterForReflection
public class KameletBindingDSLSpecification extends DSLSpecification {

    private static final String CAMEL_CONNECTOR = "CAMEL-CONNECTOR";
    private static final String KAMELET = "KAMELET";
    public static final String KNATIVE = "KNATIVE";
    public static final List<String> KINDS = Arrays.asList(KAMELET, KNATIVE, CAMEL_CONNECTOR);
    private Logger log = Logger.getLogger(KameletBindingDSLSpecification.class);

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
            String schema = new String(CamelRouteFileProcessor.class
                    .getResourceAsStream("kameletbinding.json").readAllBytes());
            return schema;
        } catch (IOException e) {
            log.error("Can't load Kamelet Binding DSL schema", e);
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
