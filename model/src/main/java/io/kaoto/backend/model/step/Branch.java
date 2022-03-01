package io.kaoto.backend.model.step;

import io.kaoto.backend.model.parameter.Parameter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 🐱class Branch
 */
public class Branch extends HashMap<String, Object> {

    public Branch(final String identifier) {
        super();
        this.put("identifier", identifier);
        this.put("steps", new LinkedList<Step>());
        this.put("parameters", new LinkedList<Parameter>());
    }

    public List<Step> getSteps() {
        if (!this.containsKey("steps")) {
            this.put("steps", new LinkedList<Step>());
        }

        return ((List<Step>) this.get("steps"));
    }

    public List<Parameter> getParameters() {
        if (!this.containsKey("parameters")) {
            this.put("parameters", new LinkedList<Parameter>());
        }

        return ((List<Parameter>) this.get("parameters"));
    }
}
