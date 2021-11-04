package io.kaoto.backend.api.service.viewdefinition.parser;

import io.kaoto.backend.model.step.Step;
import io.kaoto.backend.model.view.ViewDefinition;

import java.util.List;
/**
 * 🐱class ViewDefinitionParserService
 * 🐱relationship dependsOn ViewDefinition
 *
 * Generic interface for all viewDefinition parsers.
 *
 */
public interface ViewDefinitionParserService<T extends ViewDefinition> {

    /*
     * 🐱method parse: List[ViewDefinition]
     * 🐱param steps: List[Step]
     *
     * Based on the list of steps, offer a list of compatible ViewDefinitions.
     */
    List<T> parse(List<Step> steps);

    /*
     * 🐱method getViewsPerStep: List[ViewDefinition]
     * 🐱param steps: List[Step]
     * 🐱param view: ViewDefinition
     *
     * If the ViewDefinition is a stepView, it returns all
     * possible ViewDefinitions associated to steps.
     */
    List<ViewDefinition> getViewsPerStep(List<Step> steps,
                                         ViewDefinition view);

    /*
     * 🐱method appliesTo: boolean
     * 🐱param steps: List[Step]
     * 🐱param viewDefinition: ViewDefinition
     *
     * Check if the viewDefinition applies to the steps
     */
    boolean appliesTo(List<Step> steps, ViewDefinition viewDefinition);

}
