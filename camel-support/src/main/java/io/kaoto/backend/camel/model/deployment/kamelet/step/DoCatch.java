package io.kaoto.backend.camel.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.kaoto.backend.camel.KamelPopulator;
import io.kaoto.backend.camel.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.camel.model.deployment.kamelet.expression.Expression;
import io.kaoto.backend.model.step.Branch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class DoCatch implements Serializable {

    private List<FlowStep> steps;
    private List<String> exceptions;
    private Expression onWhen;

    public DoCatch(Branch branch, KamelPopulator kameletPopulator) {
        setSteps(kameletPopulator.processSteps(branch));
        var exception = branch.getParameters().stream().filter(p -> p.getId().equalsIgnoreCase("exceptions")).findAny();
        if (exception.isPresent()) {
            setExceptions(new ArrayList<>());
            var list = Collections.emptyList();
            if (exception.get().getValue() instanceof List<?>) {
                list = (List<Object>) exception.get().getValue();
            } else if (exception.get().getValue() instanceof Object[]) {
                list = Arrays.asList((Object[]) exception.get().getValue());
            }
            list.forEach(v -> getExceptions().add(String.valueOf(v)));
        }

        branch.getParameters().stream()
                .filter(p -> p.getId().equalsIgnoreCase("on-when"))
                .findAny()
                .ifPresent(parameter -> setOnWhen(new Expression(parameter.getValue())));
    }

    @JsonCreator
    public DoCatch(
            final @JsonProperty("steps") List<FlowStep> steps,
            final @JsonProperty("exception") List<String> exceptions,
            final @JsonProperty("on-when") Expression onWhen,
            final @JsonProperty("onWhen") Expression onWhen2) {
        super();
        setSteps(steps);
        setExceptions(exceptions);
        setOnWhen(onWhen != null ? onWhen : onWhen2);
    }

    public List<String> getExceptions() {
        return exceptions;
    }

    public void setExceptions(final List<String> exceptions) {
        this.exceptions = exceptions;
    }

    public List<FlowStep> getSteps() {
        return steps;
    }

    public void setSteps(final List<FlowStep> steps) {
        this.steps = steps;
    }

    public Expression getOnWhen() {
        return onWhen;
    }

    public void setOnWhen(final Expression onWhen) {
        this.onWhen = onWhen;
    }
}
