package io.kaoto.backend.api.service.deployment.generator;

import io.kaoto.backend.model.step.Step;

import java.util.List;

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
     * 🐱method appliesTo: boolean
     * 🐱param steps: List[Step]
     *
     * Check if the parser applies to the steps
     */
    boolean appliesTo(List<Step> steps);

}
