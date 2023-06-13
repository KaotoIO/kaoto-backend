package io.kaoto.backend.api.service.deployment.generator.camelroute;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.deployment.generator.DeploymentGeneratorService;
import io.kaoto.backend.api.service.deployment.generator.kamelet.KameletDeploymentGeneratorService;
import io.kaoto.backend.api.service.step.parser.StepParserService;
import io.kaoto.backend.api.service.step.parser.camelroute.IntegrationStepParserService;
import io.kaoto.backend.model.deployment.Deployment;
import io.kaoto.backend.model.deployment.camelroute.Integration;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;
import io.opentelemetry.api.trace.Span;
import io.quarkus.runtime.annotations.RegisterForReflection;
import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@ApplicationScoped
@RegisterForReflection
public class IntegrationDeploymentGeneratorService implements DeploymentGeneratorService {

    private static final String CAMEL_CONNECTOR = "CAMEL-CONNECTOR";
    private static final String EIP = "EIP";
    private static final String EIP_BRANCHES = "EIP-BRANCH";
    private static final List<String> KINDS = Arrays.asList(CAMEL_CONNECTOR, EIP, EIP_BRANCHES);

    private Logger log = Logger.getLogger(IntegrationDeploymentGeneratorService.class);

    private IntegrationStepParserService stepParserService;

    private KameletDeploymentGeneratorService kdgs;

    private StepCatalog catalog;

    public IntegrationDeploymentGeneratorService() {
    }


    @Override
    public String parse(final List<Step> steps, final Map<String, Object> metadata, final List<Parameter> parameters) {
        return kdgs.getYAML(new Integration(
                        steps != null ? new LinkedList<>(steps) : List.of(),
                        metadata != null ? new LinkedHashMap<>(metadata) : Map.of(),
                        catalog),
                new IntegrationRepresenter());
    }

    @Override
    public String parse(List<StepParserService.ParseResult<Step>> flows) {
        StringBuilder sb = new StringBuilder();

        StepParserService.ParseResult<Step> last = flows.stream().reduce((a, b) -> b).orElseThrow();
        flows.stream().forEachOrdered(stepParseResult -> {
            sb.append(
                    parse(stepParseResult.getSteps(), stepParseResult.getMetadata(), stepParseResult.getParameters()));
            if (stepParseResult != last) {
                sb.append("---" + System.lineSeparator());
            }
        });
        return sb.toString();
    }

    @Override
    public CustomResource parse(final String input) {
        if (stepParserService.appliesTo(input)) {
            try {
                ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());
                return yamlMapper.readValue(input, Integration.class);
            } catch (Exception e) {
                log.trace("Tried creating an integration and it didn't work.");
            }
        }

        return null;
    }

    @Override
    public Status getStatus(final CustomResource cr) {
        Status s = Status.Invalid;
        if (cr instanceof Integration integration && integration.getStatus() != null) {
            switch (integration.getStatus().getPhase()) {
                case "Ready":
                case "Running":
                    s = Status.Running;
                    break;
                case "Building Kit":
                    s = Status.Building;
                    break;
                default:
                    log.warn("Unrecognized status: " + integration.getStatus().getPhase());
                    s = Status.Stopped;
            }
        }
        return s;
    }

    @Override
    public List<Class<? extends CustomResource>> supportedCustomResources() {
        return Arrays.asList(new Class[]{Integration.class});
    }

    @Override
    public Collection<? extends Deployment> getResources(final String namespace, final KubernetesClient kclient) {
        List<Deployment> res = new LinkedList<>();
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
            log.warn("Error extracting the list of integrations.", e);
        }

        return res;
    }

    @Override
    public Pod getPod(final String namespace, final String name, final KubernetesClient kubernetesClient) {

        for (var d : getResources(namespace, kubernetesClient)) {
            if (d.getName().equalsIgnoreCase(name)) {
                var pods = kubernetesClient.pods()
                        .inNamespace(namespace)
                        .withLabel("camel.apache.org/integration=" + d.getName())
                        .list().getItems();

                for (var pod : pods) {
                    if (pod.getStatus() != null
                            && pod.getStatus().getPhase() != null
                            && (pod.getStatus().getPhase().equalsIgnoreCase("Running")
                            || pod.getStatus().getPhase().equalsIgnoreCase("Succeeded"))) {
                        return pod;
                    }
                }

            }
        }

        return null;
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
