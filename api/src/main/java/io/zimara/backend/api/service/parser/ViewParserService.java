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
public interface ViewParserService<T extends ViewDefinition> {

    /*
     * 🐱method parse: List[ViewDefinition]
     * 🐱param steps: List[Step]
     *
     * Based on the list of steps, offer a list of compatible ViewDefinitions.
     */
    List<T> parse(List<Step> steps);

    String getIdentifier();

    /*
     * 🐱method appliesTo: boolean
     * 🐱param steps: List[Step]
     * 🐱param viewDefinition: ViewDefinition
     *
     * Check if the viewDefinition applies to the steps
     */
    boolean appliesTo(List<Step> steps, ViewDefinition viewDefinition);

}
