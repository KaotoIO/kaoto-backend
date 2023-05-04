package io.kaoto.backend.api.service.deployment.generator.camelroute;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.deployment.generator.DeploymentGeneratorService;
import io.kaoto.backend.api.service.step.parser.StepParserService;
import io.kaoto.backend.metadata.parser.step.camelroute.CamelRestDSLParseCatalog;
import io.kaoto.backend.model.deployment.Deployment;
import io.kaoto.backend.model.deployment.camelroute.CamelRoute;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;
import io.quarkus.runtime.annotations.RegisterForReflection;
import org.jboss.logging.Logger;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

@ApplicationScoped
@RegisterForReflection
public class CamelRouteDeploymentGeneratorService implements DeploymentGeneratorService {

    private static final String CAMEL_CONNECTOR = "CAMEL-CONNECTOR";
    private static final String EIP = "EIP";
    private static final String EIP_BRANCHES = "EIP-BRANCH";
    public static final String CAMEL_REST_DSL = CamelRestDSLParseCatalog.CAMEL_REST_DSL;
    public static final String CAMEL_REST_VERB =  CamelRestDSLParseCatalog.CAMEL_REST_VERB;
    public static final String CAMEL_REST_ENDPOINT =  CamelRestDSLParseCatalog.CAMEL_REST_ENDPOINT;
    private static final List<String> KINDS = Arrays.asList(
            CAMEL_CONNECTOR, EIP, EIP_BRANCHES, CAMEL_REST_DSL, CAMEL_REST_VERB, CAMEL_REST_ENDPOINT);

    private Logger log = Logger.getLogger(CamelRouteDeploymentGeneratorService.class);

    private StepCatalog catalog;

    public CamelRouteDeploymentGeneratorService() {
        //Empty for injection
    }

    @Override
    public List<String> getKinds() {
        return KINDS;
    }

    public String identifier() {
        return "Camel Route";
    }

    public String description() {
        return "A camel route is a non deployable in cluster workflow of actions and steps.";
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
        return  "";
    }

    @Override
    public String parse(final List<Step> steps,
                        final Map<String, Object> metadata,
                        final List<Parameter> parameters) {
        Yaml yaml = new Yaml(new Constructor(CamelRoute.class), new CamelRouteRepresenter());
        return yaml.dumpAs(new CamelRoute(steps, metadata, catalog), Tag.SEQ, DumperOptions.FlowStyle.BLOCK);
    }

    @Override
    public String parse(List<StepParserService.ParseResult<Step>> flows) {
        StringBuilder sb = new StringBuilder();
        flows.stream().forEachOrdered(stepParseResult -> sb.append(parse(stepParseResult.getSteps(),
                stepParseResult.getMetadata(), stepParseResult.getParameters())));
        return sb.toString();
    }

    @Override
    public CustomResource parse(final String input) {
        //We are not handling deployments here
        return null;
    }

    @Override
    public boolean appliesTo(final List<Step> steps) {
        return steps.stream()
                .filter(Objects::nonNull)
                .allMatch(s -> getKinds().stream().anyMatch(Predicate.isEqual(s.getKind().toUpperCase())));
    }

    @Override
    public Status getStatus(final CustomResource cr) {
        //We are not handling deployments here
        return Status.Invalid;
    }

    @Override
    public List<Class<? extends CustomResource>> supportedCustomResources() {
        //We are not handling deployments here
        return Collections.emptyList();
    }

    @Override
    public boolean appliesToFlows(List<StepParserService.ParseResult<Step>> flows) {
        return flows.stream().anyMatch(flow -> flow.getSteps().stream().filter(Objects::nonNull)
                .allMatch(s -> getKinds().stream().anyMatch(Predicate.isEqual(s.getKind().toUpperCase()))));
    }

    @Override
    public boolean isDeployable() {
        return false;
    }

    @Override
    public Collection<? extends Deployment> getResources(final String namespace, final KubernetesClient kclient) {
        //We are not handling deployments here
        return Collections.emptyList();
    }

    @Override
    public Pod getPod(final String namespace, final String name, final KubernetesClient kubernetesClient) {
        //We are not handling deployments here
        return null;
    }


    @Override
    public Stream<Step> filterCatalog(Step previousStep, Step followingStep, Stream<Step> steps) {

        if (previousStep != null) {
            switch (previousStep.getKind().toUpperCase()) {
                case CAMEL_REST_DSL:
                    //After the "rest" element, comes a http verb
                    steps = steps.filter(s -> s.getKind().equalsIgnoreCase(CAMEL_REST_VERB));
                    break;
                case CAMEL_REST_VERB:
                    //After a verb comes the configuration that consumes
                    steps = steps.filter(s -> s.getKind().equalsIgnoreCase(CAMEL_REST_ENDPOINT));
                    break;
                default:
                    //We are at the end of the integration or not on REST, looking for a  normal camel flow
                    steps = steps.filter(s -> !s.getKind().equalsIgnoreCase(CAMEL_REST_VERB)
                            && !s.getKind().equalsIgnoreCase(CAMEL_REST_ENDPOINT)
                            && !s.getKind().equalsIgnoreCase(CAMEL_REST_DSL));
                    break;
            }
        }
        return steps;
    }

    @Inject
    public void setCatalog(StepCatalog catalog) {
        this.catalog = catalog;
    }
}
