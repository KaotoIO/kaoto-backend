package io.kaoto.backend.model.step;

import io.kaoto.backend.model.parameter.Parameter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 🐱class Branch
 */
public class Branch extends HashMap<String, Object> {

    public static final String STEPS = "steps";
    public static final String PARAMETERS = "parameters";
    public static final String IDENTIFIER = "identifier";

    public Branch(final String identifier) {
        super();
        this.put(IDENTIFIER, identifier);
        this.put(STEPS, new LinkedList<Step>());
        this.put(PARAMETERS, new LinkedList<Parameter>());
    }

    public List<Step> getSteps() {
        this.computeIfAbsent(STEPS, k -> new LinkedList<Step>());

        return ((List<Step>) this.get(STEPS));
    }

    public List<Parameter> getParameters() {
        this.computeIfAbsent(PARAMETERS, k -> new LinkedList<Parameter>());

        return ((List<Parameter>) this.get(PARAMETERS));
    }
}
