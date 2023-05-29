package io.kaoto.backend.api.service.dsl;

import io.kaoto.backend.api.service.deployment.generator.DeploymentGeneratorService;
import io.kaoto.backend.api.service.step.parser.StepParserService;
import io.kaoto.backend.model.step.Step;
import io.opentelemetry.extension.annotations.WithSpan;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * 🐱miniclass DSLSpecification
 * <p>
 * <p>
 * 🐱section
 * <p>
 * <p>
 * Generic interface to define DSL specific elements like the vocabulary or capabilities.
 */
public abstract class DSLSpecification {

    /*
     * 🐱method identifier: String
     *
     * Returns the identifier of the supported language.
     *
     * This must be unique on the whole Kaoto instance and relates the deployments and parsers services with the DSL
     * specification.
     */
    public abstract String identifier();

    /*
     * 🐱method description: String
     *
     * Returns the identifier of the supported language.
     *
     * This must be unique on the whole Kaoto instance and relates the deployments and parsers services with the DSL
     * specification.
     */
    public abstract String description();

    /*
     * 🐱 method description: String
     *
     * Returns validationSchema/validationSchema URL if exists.
     */
    public abstract String validationSchema();

    /*
     * 🐱method isDeployable: boolean
     *
     * Based on the environment, the capabilities, or any other important feature.
     * But, is this DSL deployable using the `Deploy` button in Kaoto?
     *
     */
    public abstract boolean isDeployable();

    /*
     * 🐱method getKinds: String[]
     *
     * Return the kinds of steps compatible with this DSL.
     */
    public abstract Collection<String> getKinds();

    /*
     * 🐱method appliesTo: boolean
     * 🐱param steps: List[Step]
     *
     * Check if this DSL can be applied to the list of steps.
     */
    @Deprecated
    public boolean appliesTo(final List<Step> steps) {
        return steps.stream().filter(Objects::nonNull)
                .allMatch(s -> getKinds().stream().anyMatch(Predicate.isEqual(s.getKind().toUpperCase())));
    }

    /*
     * 🐱method appliesTo: boolean
     * 🐱param flows: ParseResult
     *
     * Check if at least one of the flows belongs to this DSL.
     */
    @WithSpan
    public boolean appliesToFlows(List<StepParserService.ParseResult<Step>> flows) {
        return flows.stream().anyMatch(flow -> flow.getSteps().stream().filter(Objects::nonNull)
                .allMatch(s -> getKinds().stream().anyMatch(Predicate.isEqual(s.getKind().toUpperCase()))));
    }

    /*
     * 🐱method appliesTo: boolean
     * 🐱param yaml: String
     *
     * Check if the source code is from this DSL.
     */
    public boolean appliesTo(final String sourceCode) {
        return this.getStepParserService().appliesTo(sourceCode);
    }


    /*
     * 🐱method getDeploymentGeneratorService: DeploymentGeneratorService
     *
     * Returns the Deployment Generator Service associated to this DSL, if exists.
     */
    public abstract DeploymentGeneratorService getDeploymentGeneratorService();


    /*
     * 🐱method getStepParserService: StepParserService
     *
     * Returns the Step Parser Service associated to this DSL, if exists.
     */
    public abstract StepParserService getStepParserService();

    public abstract Boolean doesSupportMultipleFlows();

    public abstract Map<String, String> getVocabulary();
}
