package io.kaoto.backend.api.service.deployment.generator;

import io.kaoto.backend.model.step.Step;

import java.util.List;
import java.util.Map;

/**
 * 🐱class DeploymentGeneratorService
 * 🐱relationship dependsOn Step
 *
 * Generic interface to generate yaml deployments
 *
 */
public interface DeploymentGeneratorService {

    /*
     * 🐱method identifier: String
     *
     * Returns this generator identifier.
     */
    String identifier();

    /*
     * 🐱method parse: String
     * 🐱param name: String
     * 🐱param steps: List[Step]
     *
     * Based on the list of steps, returns the yaml to deploy it.
     * If applies, the name will be the name used on the integration deployed.
     */
    String parse(String name, List<Step> steps);

    /*
     * 🐱method parse: String
     * 🐱param steps: List[Step]
     * 🐱param metadata: Map
     *
     * Based on the list of steps, returns the yaml to deploy it.
     * If applies, the name will be the name used on the integration deployed.
     */
    String parse(List<Step> steps, Map<String, Object> metadata);

    /*
     * 🐱method appliesTo: boolean
     * 🐱param steps: List[Step]
     *
     * Check if the parser applies to the steps
     */
    boolean appliesTo(List<Step> steps);

    /*
     * 🐱method getKinds: String[]
     *
     * Return the step kinds compatible with this service.
     */
    List<String> getKinds();

}
