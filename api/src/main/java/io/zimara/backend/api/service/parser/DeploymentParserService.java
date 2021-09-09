package io.zimara.backend.api.service.parser;

import io.zimara.backend.model.step.Step;
import io.zimara.backend.model.view.ViewDefinition;

import java.util.List;

/**
 * 🐱class ViewParserService
 * 🐱relationship dependsOn ViewDefinition
 *
 * Generic interface for all viewDefinition parsers.
 *
 */
public interface DeploymentParserService {

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
