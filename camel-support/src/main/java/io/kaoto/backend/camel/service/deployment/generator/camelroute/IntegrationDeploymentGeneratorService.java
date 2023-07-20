package io.kaoto.backend.camel.service.deployment.generator.camelroute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.jboss.logging.Logger;

import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.deployment.generator.DeploymentGeneratorService;
import io.kaoto.backend.api.service.step.parser.StepParserService;
import io.kaoto.backend.camel.KamelHelper;
import io.kaoto.backend.camel.model.deployment.camelroute.Integration;
import io.kaoto.backend.camel.model.deployment.camelroute.IntegrationFlow;
import io.kaoto.backend.camel.service.deployment.generator.AbstractDeploymentGeneratorService;
import io.kaoto.backend.camel.service.deployment.generator.kamelet.KameletDeploymentGeneratorService;
import io.kaoto.backend.camel.service.step.parser.camelroute.IntegrationStepParserService;
import io.kaoto.backend.model.deployment.Deployment;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;
import io.opentelemetry.api.trace.Span;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
@RegisterForReflection
public class IntegrationDeploymentGeneratorService extends AbstractDeploymentGeneratorService {

    private static final String CAMEL_CONNECTOR = "CAMEL-CONNECTOR";
    private static final String EIP = "EIP";
    private static final String EIP_BRANCHES = "EIP-BRANCH";
    private static final List<String> KINDS = Arrays.asList(CAMEL_CONNECTOR, EIP, EIP_BRANCHES);

    private static final List<Class<? extends CustomResource>> SUPPORTED_RESOURCES = List.of(Integration.class);
    private static final Logger LOG = Logger.getLogger(IntegrationDeploymentGeneratorService.class);

    private IntegrationStepParserService stepParserService;

    private KameletDeploymentGeneratorService kdgs;

    private StepCatalog catalog;

    public IntegrationDeploymentGeneratorService() {
    }


    @Override
    public String parse(final List<Step> steps, final Map<String, Object> metadata, final List<Parameter> parameters) {
        List<IntegrationFlow> parsedList = new ArrayList<>();
        if (steps != null) {
            var parsed = new IntegrationFlow();
            parsed.setSteps(steps);
            parsedList.add(parsed);
        }
        return kdgs.getYAML(new Integration(
                        parsedList,
                        metadata != null ? new LinkedHashMap<>(metadata) : Map.of(),
                        catalog),
                new IntegrationRepresenter());
    }

    @Override
    public String parse(List<StepParserService.ParseResult<Step>> flows) {
        List<IntegrationFlow> parsedList = new ArrayList<>();
        Map<String, Object> metadata = null;
        for (var f : flows) {
            if (f.getSteps() != null) {
                var iflow = new IntegrationFlow();
                iflow.setSteps(f.getSteps());
                iflow.setMetadata(f.getMetadata());
                iflow.setParameters(f.getParameters());
                parsedList.add(iflow);
            } else if (f.getMetadata() != null) {
                metadata = f.getMetadata();
            }
        }
        return kdgs.getYAML(new Integration(
                        parsedList,
                        metadata != null ? new LinkedHashMap<>(metadata) : Map.of(),
                        catalog),
                new IntegrationRepresenter());
    }

    @Override
    public CustomResource parse(final String input) {
        if (stepParserService.appliesTo(input)) {
            try {
                return KamelHelper.YAML_MAPPER.readValue(input, Integration.class);
            } catch (Exception e) {
                LOG.trace("Tried creating an integration and it didn't work.");
            }
        }

        return null;
    }

    @Override
    public DeploymentGeneratorService.Status getStatus(final CustomResource cr) {
        DeploymentGeneratorService.Status s = DeploymentGeneratorService.Status.Invalid;
        if (cr instanceof Integration integration && integration.getStatus() != null) {
            switch (integration.getStatus().getPhase()) {
                case "Ready":
                case "Running":
                    s = DeploymentGeneratorService.Status.Running;
                    break;
                case "Building Kit":
                    s = DeploymentGeneratorService.Status.Building;
                    break;
                default:
                    LOG.warn("Unrecognized status: " + integration.getStatus().getPhase());
                    s = DeploymentGeneratorService.Status.Stopped;
            }
        }
        return s;
    }

    @Override
    public List<Class<? extends CustomResource>> supportedCustomResources() {
        return SUPPORTED_RESOURCES;
    }

    @Override
    public Collection<? extends Deployment> getResources(final String namespace, final KubernetesClient kclient) {
        List<Deployment> res = new ArrayList<>();
        try {
            String createdLabel = "camel.apache.org/created.by.kind";
            final var resources = kclient.resources(Integration.class).inNamespace(namespace).list();
            for (CustomResource customResource : resources.getItems()) {
                if (customResource.getMetadata() == null
                        || customResource.getMetadata().getLabels() == null
                        || !customResource.getMetadata().getLabels().containsKey(createdLabel)) {
                    res.add(new Deployment(customResource, getStatus(customResource)));

                    if (Span.current() != null) {
                        Span.current().setAttribute("CustomResource[" + res.size() + "]",
                                res.get(res.size() - 1).toString());
                    }
                }
            }
        } catch (Exception e) {
            LOG.warn("Error extracting the list of integrations.", e);
        }

        return res;
    }

    @Override
    public Stream<Step> filterCatalog(Step previousStep, Step followingStep, Stream<Step> steps) {
        return steps;
    }

    @Inject
    public void setStepParserService(final IntegrationStepParserService stepParserService,
                                     final KameletDeploymentGeneratorService kdgs, final StepCatalog catalog) {
        this.stepParserService = stepParserService;
        this.catalog = catalog;
        this.kdgs = kdgs;
    }
}
