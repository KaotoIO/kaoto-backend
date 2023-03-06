package io.kaoto.backend.model.step;

import io.kaoto.backend.model.parameter.Parameter;

import java.util.LinkedList;
import java.util.List;

/**
 * 🐱miniclass Branch (Step)
 * 🐱aka List[Branch]
 * 🐱aka Branch[]
 *
 */
public class Branch {

    public enum ConditionSyntax {
        SIMPLE ("simple"),
        JQ ("jq"),
        JSONPATH("jsonpath");

        private final String value;

        ConditionSyntax(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }

    private List<Step> steps = new LinkedList<>();
    private List<Parameter> parameters = new LinkedList<>();
    private String identifier;
    private String condition;
    private ConditionSyntax conditionSyntax = ConditionSyntax.SIMPLE;

    public Branch() {
        super();
    }

    public Branch(final String identifier) {
        super();
        this.identifier = identifier;
    }

    /*
     * 🐱property steps: List[Step]
     *
     * List of steps this branch has.
     *
     */
    public List<Step> getSteps() {
        return this.steps;
    }

    /*
     * 🐱property parameters: Parameter[]
     *
     * List of parameters of the branch.
     *
     */
    public List<Parameter> getParameters() {
        return this.parameters;
    }

    public void setSteps(final List<Step> steps) {
        this.steps = steps;
    }

    public void setParameters(final List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(final String identifier) {
        this.identifier = identifier;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(final String condition) {
        this.condition = condition;
    }

    public ConditionSyntax getConditionSyntax() { return conditionSyntax; }

    public void setConditionSyntax(final ConditionSyntax syntax) { this.conditionSyntax = syntax; }
}
