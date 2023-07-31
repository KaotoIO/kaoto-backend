package io.kaoto.backend.camel.service.deployment.generator.camelroute;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.deployment.generator.DeploymentGeneratorService;
import io.kaoto.backend.api.service.step.parser.StepParserService;
import io.kaoto.backend.camel.KamelHelper;
import io.kaoto.backend.camel.model.deployment.camelroute.CamelRoute;
import io.kaoto.backend.camel.service.dsl.camelroute.CamelRouteDSLSpecification;
import io.kaoto.backend.model.deployment.Deployment;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CamelRouteDeploymentGeneratorService implements DeploymentGeneratorService {

    private StepCatalog catalog;

    public CamelRouteDeploymentGeneratorService() {
        //Empty for injection
    }

    @Override
    public String parse(final List<Step> steps,
                        final Map<String, Object> metadata,
                        final List<Parameter> parameters) {
        final var camelRoute = new CamelRoute(
                steps != null ? new ArrayList<>(steps) : List.of(),
                metadata != null ? new LinkedHashMap<>(metadata) : Map.of(),
                catalog);
        try {
            return KamelHelper.YAML_MAPPER.writeValueAsString(camelRoute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
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
                case CamelRouteDSLSpecification.CAMEL_REST_DSL:
                    //After the "rest" element, comes a http verb
                    if (followingStep != null) {
                        steps = Stream.empty();
                    } else {
                        steps = steps.filter(s ->
                                s.getKind().equalsIgnoreCase(CamelRouteDSLSpecification.CAMEL_REST_VERB));
                    }
                    steps = steps.filter(s -> s.getKind().equalsIgnoreCase(CamelRouteDSLSpecification.CAMEL_REST_VERB));
                    break;
                case CamelRouteDSLSpecification.CAMEL_REST_VERB:
                    //After a verb comes the configuration that consumes
                    if (followingStep != null) {
                        steps = Stream.empty();
                    } else {
                        steps = steps.filter(s -> s.getKind().equalsIgnoreCase(
                                        CamelRouteDSLSpecification.CAMEL_REST_ENDPOINT));
                    }
                    break;
                case CamelRouteDSLSpecification.CAMEL_REST_ENDPOINT:
                    //We expect only one step, so if followingStep is not null, return empty
                    if (followingStep != null) {
                        steps = Stream.empty();
                    } else {
                        steps = steps.filter(s ->
                                        !s.getKind().equalsIgnoreCase(CamelRouteDSLSpecification.CAMEL_REST_VERB)
                                        && !s.getKind().equalsIgnoreCase(CamelRouteDSLSpecification.CAMEL_REST_ENDPOINT)
                                        && !s.getKind().equalsIgnoreCase(CamelRouteDSLSpecification.CAMEL_REST_DSL));
                        steps = steps.filter(s -> s.getType().equalsIgnoreCase("END"));
                    }
                    break;
                default:
                    //We are at the end of the integration, looking for a  normal camel flow
                    steps = steps.filter(s ->
                            !s.getKind().equalsIgnoreCase(CamelRouteDSLSpecification.CAMEL_REST_VERB)
                            && !s.getKind().equalsIgnoreCase(CamelRouteDSLSpecification.CAMEL_REST_ENDPOINT)
                            && !s.getKind().equalsIgnoreCase(CamelRouteDSLSpecification.CAMEL_REST_DSL));
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
