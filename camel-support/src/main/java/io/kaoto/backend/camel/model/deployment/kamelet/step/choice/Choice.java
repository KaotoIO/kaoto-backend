package io.kaoto.backend.camel.model.deployment.kamelet.step.choice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.kaoto.backend.camel.KamelHelper;
import io.kaoto.backend.camel.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.camel.model.deployment.kamelet.expression.Expression;
import io.kaoto.backend.camel.model.deployment.kamelet.step.ConditionBlock;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;


@JsonPropertyOrder({"simple", "jq", "jsonpath", "expression", KamelHelper.STEPS})
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Choice implements Serializable, ConditionBlock {
    @Serial
    private static final long serialVersionUID = -7206333633897407153L;

    @JsonProperty("simple")
    private String simple;
    @JsonProperty("jq")
    private String jq;
    @JsonProperty("jsonpath")
    private String jsonpath;

    @JsonProperty("expression")
    private Expression expression;

    @JsonProperty(KamelHelper.STEPS)
    private List<FlowStep> steps;

    public List<FlowStep> getSteps() {
        return steps;
    }

    public void setSteps(final List<FlowStep> steps) {
        this.steps = steps;
    }

    public String getSimple() {
        return simple;
    }

    public void setSimple(final String simple) {
        this.simple = simple;
    }

    public String getJq() {
        return jq;
    }

    public void setJq(final String jq) {
        this.jq = jq;
    }

    public String getJsonpath() {
        return jsonpath;
    }

    public void setJsonpath(String jsonpath) {
        this.jsonpath = jsonpath;
    }
    public Expression getExpression() { return expression; }

    public void setExpression(final Expression e) { this.expression = e; }
}
