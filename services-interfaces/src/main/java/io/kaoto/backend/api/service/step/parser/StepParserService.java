package io.kaoto.backend.api.service.step.parser;

import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;
import io.opentelemetry.instrumentation.annotations.WithSpan;

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
     * 🐱method parse: ParseResult
     * 🐱param yaml: String
     *
     * Based on the YAML provided, offer a list of steps defined on it and
     * the metadata associated to the orchestration.
     */
    @WithSpan
    ParseResult<T> deepParse(String yaml);

    /*
     * 🐱method getParsedFlows: List<ParseResult>
     * 🐱param yaml: String
     *
     * Based on the YAML provided, offer a list of flows defined on it and
     * the metadata associated to the orchestration.
     */
    @WithSpan
    List<ParseResult<T>> getParsedFlows(String yaml);

    /*
     * 🐱method appliesTo: boolean
     * 🐱param yaml: String
     *
     * Check if this parser knows how to parse the provided source code
     */
    boolean appliesTo(String sourceCode);

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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ParseResult<?> that)) return false;

            if (getSteps() != null ? !getSteps().equals(that.getSteps()) : that.getSteps() != null) return false;
            if (getParameters() != null ? !getParameters().equals(that.getParameters()) : that.getParameters() != null)
                return false;
            return getMetadata() != null ? getMetadata().equals(that.getMetadata()) : that.getMetadata() == null;
        }

        @Override
        public int hashCode() {
            int result = getSteps() != null ? getSteps().hashCode() : 0;
            result = 31 * result + (getParameters() != null ? getParameters().hashCode() : 0);
            result = 31 * result + (getMetadata() != null ? getMetadata().hashCode() : 0);
            return result;
        }
    }

}
