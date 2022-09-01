package io.kaoto.backend.api.service.step.parser;

import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import java.util.List;
import java.util.Map;

/**
 * 🐱class StepParserService
 * 🐱relationship dependsOn Step
 *
 * Generic interface for all step parsers.
 *
 */
public interface StepParserService<T extends Step> {

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
     * 🐱method parse: ParseResult
     * 🐱param yaml: String
     *
     * Based on the YAML provided, offer a list of steps defined on it and
     * the metadata associated to the orchestration.
     */
    ParseResult<T> deepParse(String yaml);

    /*
     * 🐱method appliesTo: boolean
     * 🐱param yaml: String
     *
     * Check if this parser knows how to parse the provided string
     */
    boolean appliesTo(String yaml);

    /**
     * 🐱miniclass ParseResult (StepParserService)
     *
     */
    class ParseResult<T> {
        private List<T> steps;
        private List<Parameter> parameters;
        private Map<String, Object> metadata;

        public List<T> getSteps() {
            return steps;
        }

        public void setSteps(final List<T> steps) {
            this.steps = steps;
        }

        public Map<String, Object> getMetadata() {
            return metadata;
        }

        public void setMetadata(final Map<String, Object> metadata) {
            this.metadata = metadata;
        }

        public List<Parameter> getParameters() {
            return parameters;
        }

        public void setParameters(
                final List<Parameter> parameters) {
            this.parameters = parameters;
        }
    }

}
