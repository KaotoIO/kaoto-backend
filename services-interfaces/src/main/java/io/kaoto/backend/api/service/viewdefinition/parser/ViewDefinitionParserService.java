package io.kaoto.backend.api.service.viewdefinition.parser;
import io.kaoto.backend.model.step.Step;
import io.kaoto.backend.model.view.ViewDefinition;
import io.opentelemetry.instrumentation.annotations.WithSpan;

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
    @WithSpan
    List<T> parse(List<Step> steps);

    /*
     * 🐱method getViewsPerStep: List[ViewDefinition]
     * 🐱param steps: List[Step]
     * 🐱param view: ViewDefinition
     *
     * If the ViewDefinition is a stepView, it returns all
     * possible ViewDefinitions associated to steps.
     */
    @WithSpan
    List<ViewDefinition> getViewsPerStep(List<Step> steps,
                                         ViewDefinition view);

    /*
     * 🐱method appliesTo: boolean
     * 🐱param steps: List[Step]
     * 🐱param viewDefinition: ViewDefinition
     *
     * Check if the viewDefinition applies to the steps or one of
     * the given steps by also looking into subsequent branches
     */
    @WithSpan
    boolean appliesTo(List<Step> steps, ViewDefinition viewDefinition);

    /*
     * 🐱method appliesToStep: boolean
     * 🐱param steps: Step
     * 🐱param viewDefinition: ViewDefinition
     *
     * Check if the viewDefinition applies to the exact given step
     * but not a step in subsequent branches
     */
    @WithSpan
    boolean appliesToStep(Step step, ViewDefinition viewDefinition);
}
