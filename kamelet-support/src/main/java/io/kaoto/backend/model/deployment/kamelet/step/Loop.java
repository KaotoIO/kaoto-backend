package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kaoto.backend.KamelPopulator;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.deployment.kamelet.expression.Expression;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Branch;
import io.kaoto.backend.model.step.Step;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Loop extends Expression {
    public static final String EXPRESSION_LABEL = "expression";

    public static final String COPY_LABEL= "copy";

    public static final String DO_WHILE_LABEL = "do-while";
    public static final String DO_WHILE_LABEL2 = "doWhile";

    public static final String BREAK_ON_SHUTDOWN_LABEL = "break-on-shutdown";
    public static final String BREAK_ON_SHUTDOWN_LABEL2 = "breakOnShutdown";

    public static final String DISABLED_LABEL = "disabled";

    public static final String DESCRIPTION_LABEL = "description";

    public static final String STEPS_LABEL = "steps";

    @JsonProperty(EXPRESSION_LABEL)
    private Expression expression;

    @JsonProperty(COPY_LABEL)
    private Boolean copy;

    @JsonProperty(DO_WHILE_LABEL)
    private Boolean doWhile;

    @JsonProperty(BREAK_ON_SHUTDOWN_LABEL)
    private Boolean breakOnShutdown;

    @JsonProperty(DISABLED_LABEL)
    private Boolean disabled;

    @JsonProperty(DESCRIPTION_LABEL)
    private Map<String, String> description;

    @JsonProperty(STEPS_LABEL)
    private List<FlowStep> steps;


    public Loop() {
         //Needed for serialization
    }

    public Loop(Step step, final KamelPopulator kameletPopulator) {
        super(step);

        if (step.getBranches() != null && !step.getBranches().isEmpty()) {
            setSteps(kameletPopulator.processSteps(step.getBranches().get(0)));
        }
    }

    @Override
    protected void assignAttribute(final Parameter parameter) {
        super.assignAttribute(parameter);
        switch (parameter.getId()) {
            case EXPRESSION_LABEL:
                this.setExpression(new Expression(parameter.getValue()));
                break;
            case COPY_LABEL:
                this.setCopy(Boolean.valueOf(parameter.getValue().toString()));
                break;
            case DO_WHILE_LABEL:
            case DO_WHILE_LABEL2:
                this.setDoWhile(Boolean.valueOf(parameter.getValue().toString()));
                break;
            case BREAK_ON_SHUTDOWN_LABEL:
            case BREAK_ON_SHUTDOWN_LABEL2:
                this.setBreakOnShutdown(Boolean.valueOf(parameter.getValue().toString()));
                break;
            case DISABLED_LABEL:
                this.setDisabled(Boolean.valueOf(parameter.getValue().toString()));
                break;
            case DESCRIPTION_LABEL:
                this.setDescription((Map<String, String>) parameter.getValue());
                break;
            default:
                log.error("Unknown property: " + parameter.getId());
                break;
        }
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = super.getRepresenterProperties();
        if (this.expression != null) {
            properties.putAll(this.expression.getRepresenterProperties());
        }
        if (this.copy != null) {
            properties.put(COPY_LABEL, this.copy);
        }
        if (this.doWhile != null) {
            properties.put(DO_WHILE_LABEL, this.doWhile);
        }
        if (this.breakOnShutdown != null) {
            properties.put(BREAK_ON_SHUTDOWN_LABEL, this.breakOnShutdown);
        }
        if (this.disabled != null) {
            properties.put(DISABLED_LABEL, this.disabled);
        }
        if (this.description != null) {
            properties.put(DESCRIPTION_LABEL, this.description);
        }
        if (this.getSteps() != null) {
            properties.put(STEPS_LABEL, this.getSteps());
        }

        return properties;
    }

    @Override
    protected void assignProperty(final Parameter parameter) {
        super.assignProperty(parameter);
        switch (parameter.getId()) {
            case EXPRESSION_LABEL:
                parameter.setValue(this.expression);
                break;
            case COPY_LABEL:
                parameter.setValue(this.copy);
                break;
            case DO_WHILE_LABEL:
            case DO_WHILE_LABEL2:
                parameter.setValue(this.doWhile);
                break;
            case BREAK_ON_SHUTDOWN_LABEL:
            case BREAK_ON_SHUTDOWN_LABEL2:
                parameter.setValue(this.breakOnShutdown);
                break;
            case DISABLED_LABEL:
                parameter.setValue(this.disabled);
                break;
            case DESCRIPTION_LABEL:
                parameter.setValue(this.description);
                break;
            default:
                log.error("Unknown property in Loop: " + parameter.getId());
                break;
        }
    }

    @Override
    public void processBranches(final Step step, final StepCatalog catalog,
                                final KameletStepParserService kameletStepParserService) {
        step.setBranches(new LinkedList<>());

        Branch branch = new Branch(STEPS_LABEL);
        if (this.getSteps() != null) {
            int i = 0;
            for (var s : this.getSteps()) {
                branch.getSteps().add(kameletStepParserService.processStep(s, i == 0,
                        i++ == this.getSteps().size() - 1));
            }
        }
        step.getBranches().add(branch);
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(final Expression expression) {
        this.expression = expression;
    }

    public Boolean getCopy() {
        return copy;
    }

    public void setCopy(final Boolean copy) {
        this.copy = copy;
    }

    public Boolean getDoWhile() {
        return doWhile;
    }

    public void setDoWhile(final Boolean doWhile) {
        this.doWhile = doWhile;
    }

    public Boolean getBreakOnShutdown() {
        return breakOnShutdown;
    }

    public void setBreakOnShutdown(final Boolean breakOnShutdown) {
        this.breakOnShutdown = breakOnShutdown;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(final Boolean disabled) {
        this.disabled = disabled;
    }

    public Map<String, String> getDescription() {
        return description;
    }

    public void setDescription(final Map<String, String> description) {
        this.description = description;
    }

    public List<FlowStep> getSteps() {
        return steps;
    }

    public void setSteps(final List<FlowStep> steps) {
        this.steps = steps;
    }
}
