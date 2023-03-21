package io.kaoto.backend.api.resource.v1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 🐱miniclass Integration (IntegrationsResource)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Integration {


    private List<Step> steps = Collections.emptyList();
    private Map<String, Object> metadata = Collections.emptyMap();
    private List<Parameter> parameters = Collections.emptyList();
    private String dsl = null;

    /*
     * 🐱property steps: Step[]
     *
     *  Workflow of steps that define this resource.
     */
    public List<Step> getSteps() {
        return steps;
    }

    /*
     * 🐱property metadata: Map
     *
     *  Metadata associated to this resource.
     */
    public Map<String, Object> getMetadata() {
        return metadata;
    }

    /*
     * 🐱property parameters: Parameter[]
     *
     *  Input parameters associated to this resource.
     */
    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setSteps(final List<Step> steps) {
        this.steps = steps;
    }

    public void setMetadata(final Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public void setParameters(
            final List<Parameter> parameters) {
        this.parameters = parameters;
    }

    /*
     * 🐱property dsl: String
     *
     *  Language to use with this resource.
     */
    public String getDsl() {
        return dsl;
    }

    public void setDsl(final String dsl) {
        this.dsl = dsl;
    }
}
