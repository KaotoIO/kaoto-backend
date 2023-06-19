package io.kaoto.backend.api.service.deployment.generator;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.kaoto.backend.api.service.step.parser.StepParserService;
import io.kaoto.backend.model.deployment.Deployment;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;
import io.opentelemetry.instrumentation.annotations.WithSpan;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * 🐱miniclass DeploymentGeneratorService (StepResource)
 * 🐱relationship dependsOn Step
 * <p>
 * 🐱section
 * Generic interface to generate deployments
 */
public interface DeploymentGeneratorService {

    /*
     * 🐱method parse: String
     * 🐱param steps: List[Step]
     * 🐱param parameters: List[Parameter]
     * 🐱param metadata: Map
     *
     * Based on the list of steps, returns the yaml to deploy it.
     * If applies, the name will be the name used on the integration deployed.
     */
    @Deprecated
    String parse(List<Step> steps, Map<String, Object> metadata, List<Parameter> parameters);


    /*
     * 🐱method parse: String
     * 🐱param flows: ParseResult
     *
     * Based on the list of flows, returns the source code.
     * If applies, the name will be the name used on the integration deployed.
     *
     */
    @WithSpan
    String parse(List<StepParserService.ParseResult<Step>> flows);

    /*
     * 🐱method parse: CustomResource
     * 🐱param input: String
     *
     * Based on the source code, returns the resource to deploy.
     */
    @WithSpan
    CustomResource parse(String input);

    /*
     * 🐱method getStatus: CustomResource
     *
     * Returns the status of the custom resource provided. It must be one of
     * the supported types in #supportedCustomResources() .
     */
    @WithSpan
    Status getStatus(CustomResource cr);

    /*
     * 🐱method supportedCustomResources: List<Class>
     *
     * Returns the Java model classes that extend CustomResource and that are
     *  the types of custom resources supported in the cluster.
     */
    List<Class<? extends CustomResource>> supportedCustomResources();

    /*
     * 🐱method getResources: Deployment[]
     * 🐱param namespace: String
     * 🐱param kclient: KubernetesClient
     *
     * Return the step kinds compatible with this service.
     */
    @WithSpan
    Collection<? extends Deployment> getResources(String namespace, KubernetesClient kclient);

    /*
     * 🐱method getPod: Pod
     * 🐱param namespace: String
     * 🐱param name: String
     * 🐱param kclient: KubernetesClient
     *
     * Return the pod associated to the resource by name. If no pod associated, returns null.
     */
    @WithSpan
    Pod getPod(String namespace, String name, KubernetesClient kclient);

    /*
     * 🐱method filterCatalog: List[Step]
     * 🐱param previousStep: String
     * 🐱param followingStep: String
     *
     *  Filters a list of steps. previousStep and followingStep gives context to the catalog,
     *  potentially restricting the response.
     */
    @WithSpan
    Stream<Step> filterCatalog(Step previousStep, Step followingStep, Stream<Step> steps);


    enum Status {
        Stopped, Building, Ready, Running, Invalid
    }
}
