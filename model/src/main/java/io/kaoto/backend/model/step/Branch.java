package io.kaoto.backend.model.step;

import io.kaoto.backend.model.parameter.Parameter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 🐱miniclass Branch (Step)
 * 🐱aka List[Branch]
 * 🐱aka Branch[]
 *
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

    /*
     * 🐱property steps: List[Step]
     *
     * List of steps this branch has.
     *
     */
    public List<Step> getSteps() {
        this.computeIfAbsent(STEPS, k -> new LinkedList<Step>());

        return (List<Step>) this.get(STEPS);
    }

    /*
     * 🐱property parameters: Parameter[]
     *
     * List of parameters of the branch.
     *
     */
    public List<Parameter> getParameters() {
        this.computeIfAbsent(PARAMETERS, k -> new LinkedList<Parameter>());

        return (List<Parameter>) this.get(PARAMETERS);
    }
}
