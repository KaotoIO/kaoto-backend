package io.kaoto.backend.api.service.deployment.generator;

import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.kaoto.backend.model.deployment.Deployment;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;
import io.opentelemetry.extension.annotations.WithSpan;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 🐱miniclass DeploymentGeneratorService (StepResource)
 * 🐱relationship dependsOn Step
 *
 * 🐱section
 * Generic interface to generate deployments
 *
 */
public interface DeploymentGeneratorService {

    /*
     * 🐱method identifier: String
     *
     * Returns the identifier of the supported language.
     */
    String identifier();

    /*
     * 🐱method description: String
     *
     * Returns the description of the supported language.
     */
    String description();

    /*
     * 🐱method parse: String
     * 🐱param steps: List[Step]
     * 🐱param parameters: List[Parameter]
     * 🐱param metadata: Map
     *
     * Based on the list of steps, returns the yaml to deploy it.
     * If applies, the name will be the name used on the integration deployed.
     */
    @WithSpan
    String parse(List<Step> steps, Map<String, Object> metadata,
                 List<Parameter> parameters);

    /*
     * 🐱method parse: CustomResource
     * 🐱param input: String
     *
     * Based on the source code, returns the resource to deploy.
     */
    @WithSpan
    CustomResource parse(String input);

    /*
     * 🐱method appliesTo: boolean
     * 🐱param steps: List[Step]
     *
     * Check if the parser applies to the steps
     */
    @WithSpan
    boolean appliesTo(List<Step> steps);

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
     * 🐱method getKinds: String[]
     *
     * Return the step kinds compatible with this service.
     */
    List<String> getKinds();

    /*
     * 🐱method getResources: Deployment[]
     * 🐱param namespace: String
     *
     * Return the step kinds compatible with this service.
     */
    @WithSpan
    Collection<? extends Deployment> getResources(String namespace, KubernetesClient kclient);

    enum Status {
        Stopped, Building, Ready, Running, Invalid
    }
}
