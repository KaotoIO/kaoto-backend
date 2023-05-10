package io.kaoto.backend.api.service.deployment.generator.kamelet;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.deployment.generator.DeploymentGeneratorService;
import io.kaoto.backend.api.service.step.parser.StepParserService;
import io.kaoto.backend.api.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.metadata.parser.step.camelroute.CamelRouteFileProcessor;
import io.kaoto.backend.model.deployment.Deployment;
import io.kaoto.backend.model.deployment.kamelet.Kamelet;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;
import io.opentelemetry.api.trace.Span;
import org.jboss.logging.Logger;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

@ApplicationScoped
public class KameletDeploymentGeneratorService implements DeploymentGeneratorService {

    private static final String CAMEL_CONNECTOR = "CAMEL-CONNECTOR";
    private static final String EIP = "EIP";
    private static final String EIP_BRANCHES = "EIP-BRANCH";
    private static final List<String> KINDS = Arrays.asList(CAMEL_CONNECTOR, EIP, EIP_BRANCHES);

    private KameletStepParserService stepParserService;

    private StepCatalog catalog;

    private Logger log = Logger.getLogger(KameletDeploymentGeneratorService.class);
    public KameletDeploymentGeneratorService() {
    }

    @Override
    public List<String> getKinds() {
        return KINDS;
    }

    public String identifier() {
        return "Kamelet";
    }

    public String description() {
        return "A Kamelet is a snippet of a route. It defines meta building "
                + "blocks or steps that can be reused on integrations.";
    }

    @Override
    public String validationSchema() {
        try {
            String schema = new String(CamelRouteFileProcessor.class
                    .getResourceAsStream("kamelet.json").readAllBytes());
            return schema;
        } catch (IOException e) {
            log.error("Can't load Kamelet DSL schema", e);
        }
        return "";
    }

    @Override
    public String parse(final List<Step> steps,
                        final Map<String, Object> metadata,
                        final List<Parameter> parameters) {
        return getYAML(new Kamelet(steps, metadata, parameters, catalog), new KameletRepresenter());
    }

    @Override
    public String parse(List<StepParserService.ParseResult<Step>> flows) {
        StringBuilder res = new StringBuilder("");

        StepParserService.ParseResult<Step> last = flows.stream().reduce((a, b) -> b).get();
        flows.stream().forEachOrdered(parseResult -> {
            res.append(parse(parseResult.getSteps(), parseResult.getMetadata(), parseResult.getParameters()));
            if (parseResult != last) {
                res.append("---");
                res.append(System.lineSeparator());
            }
        });
        return res.toString();
    }
    public String getYAML(final CustomResource kamelet,
                          final Representer representer) {
        Yaml yaml = new Yaml(new Constructor(Kamelet.class), representer);
        return yaml.dumpAsMap(kamelet);
    }

    @Override
    public boolean appliesTo(final List<Step> steps) {
        return steps.stream().filter(Objects::nonNull)
                .allMatch(
                        s -> getKinds().stream()
                        .anyMatch(Predicate.isEqual(s.getKind().toUpperCase())));
    }

    @Override
    public boolean appliesToFlows(List<StepParserService.ParseResult<Step>> flows) {
        return flows.stream().anyMatch(flow -> flow.getSteps().stream().filter(Objects::nonNull)
                .allMatch(s -> getKinds().stream().anyMatch(Predicate.isEqual(s.getKind().toUpperCase()))));
    }

    @Override
    public Status getStatus(final CustomResource cr) {
        Status s = Status.Invalid;
        if (cr instanceof Kamelet kamelet
                && kamelet.getStatus() != null) {
            switch (kamelet.getStatus().getPhase()) {
                case "Ready":
                    s = Status.Ready;
                    break;
                default:
                    log.warn("Detected unrecognized status " + kamelet.getStatus().getPhase());
            }
        }
        return s;
    }

    @Override
    public List<Class<? extends CustomResource>> supportedCustomResources() {
        return Arrays.asList(new Class[]{Kamelet.class});
    }

    @Override
    public boolean isDeployable() {
        return true;
    }

    @Override
    public CustomResource parse(final String input) {
        if (stepParserService.appliesTo(input)) {
            try {
                ObjectMapper yamlMapper =
                        new ObjectMapper(new YAMLFactory())
                                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                return yamlMapper.readValue(input, Kamelet.class);
            } catch (Exception e) {
                log.trace("Tried creating a kamelet and it didn't work.");
            }
        }

        return null;
    }

    @Override
    public Collection<? extends Deployment> getResources(final String namespace, final KubernetesClient kclient) {
        List<Deployment> res = new LinkedList<>();
        try {
            final var resources = kclient.resources(Kamelet.class).inNamespace(namespace).list();
            for (CustomResource customResource : resources.getItems()) {
                res.add(new Deployment(customResource, getStatus(customResource)));

                if (Span.current() != null) {
                    Span.current().setAttribute("Kamelet[" + res.size() + "]", res.get(res.size() - 1).toString());
                }
            }
        } catch (Exception e) {
            log.warn("Error extracting the list of integrations.", e);
        }

        return res;
    }

    @Override
    public Pod getPod(final String namespace, final String name, final KubernetesClient kubernetesClient) {
        //There is no pod associated to Kamelets
        return null;
    }

    @Override
    public Stream<Step> filterCatalog(Step previousStep, Step followingStep, Stream<Step> steps) {
        return steps;
    }

    @Inject
    public void setStepParserService(final KameletStepParserService stepParserService, final StepCatalog catalog) {
        this.stepParserService = stepParserService;
        this.catalog = catalog;
    }
}
