package io.zimara.backend.model.step;

import io.zimara.backend.model.Parameter;
import io.zimara.backend.model.Step;

import java.util.List;
/**
 * 🐱class KameletStep
 * 🐱inherits Step
 * Represents a Kamelet step inside an integration
 */
public class KameletStep implements Step {

    public KameletStep(String name, String icon, List<Parameter> parameters) {
        this.name = name;
        this.icon = icon;
        this.parameters = parameters;
    }

    private String name;
    private String icon;
    private List<Parameter> parameters;

    @Override
    public String getType() {
        return "CONNECTOR";
    }

    @Override
    public String getSubType() {
        return "KAMELET";
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }
}
