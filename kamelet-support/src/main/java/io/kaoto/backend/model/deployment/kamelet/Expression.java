package io.kaoto.backend.model.deployment.kamelet;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.kaoto.backend.api.service.deployment.generator.kamelet.KameletRepresenter;
import io.kaoto.backend.model.deployment.kamelet.step.EIPStep;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder({"constant", "simple"})
public class Expression extends EIPStep {
    public static final String CONSTANT_LABEL = KameletRepresenter.CONSTANT;
    public static final String SIMPLE_LABEL = KameletRepresenter.SIMPLE;
    public static final String NAME_LABEL = KameletRepresenter.NAME;

    @JsonProperty(CONSTANT_LABEL)
    private String constant;

    @JsonProperty(SIMPLE_LABEL)
    private String simple;

    @JsonProperty(KameletRepresenter.NAME)
    private String name;

    public Expression() {
        //Needed for serialization
    }

    @JsonCreator
    public Expression(
            final @JsonProperty(CONSTANT_LABEL) String constant,
            final @JsonProperty(SIMPLE_LABEL) String simple,
            final @JsonProperty(NAME_LABEL) String name) {
        setConstant(constant);
        setSimple(simple);
        setName(name);
    }

    public Expression(Step step) {
        super(step);
    }

    public Expression(Object obj) {
        if (obj instanceof Expression e) {
            setConstant(e.getConstant());
            setSimple(e.getSimple());
        } else if (obj instanceof Map map) {
            if (map.containsKey(CONSTANT_LABEL) && map.get(CONSTANT_LABEL) != null) {
                setConstant(map.get(CONSTANT_LABEL).toString());
            }
            if (map.containsKey(SIMPLE_LABEL) && map.get(SIMPLE_LABEL) != null) {
                setSimple(map.get(SIMPLE_LABEL).toString());
            }
            if (map.containsKey(NAME_LABEL) && map.get(NAME_LABEL) != null) {
                setName(map.get(NAME_LABEL).toString());
            }
        }
    }

    @Override
    protected void assignAttribute(final Parameter parameter) {
        switch (parameter.getId()) {
            case CONSTANT_LABEL:
                this.setConstant(parameter.getValue().toString());
                break;
            case SIMPLE_LABEL:
                this.setSimple(parameter.getValue().toString());
                break;
            case NAME_LABEL:
                this.setName(parameter.getValue().toString());
                break;
            default:
                log.trace("Unknown attribute in Expression: " + parameter.getId());
                break;
        }
    }

    @Override
    protected void assignProperty(final Parameter parameter) {
        switch (parameter.getId()) {
            case CONSTANT_LABEL:
                parameter.setValue(this.constant);
                break;
            case SIMPLE_LABEL:
                parameter.setValue(this.simple);
                break;
            case NAME_LABEL:
                parameter.setValue(this.name);
                break;
            default:
                log.trace("Unknown property in Expression: " + parameter.getId());
                break;
        }
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {

        Map<String, Object> properties = new HashMap<>();
        if (this.getConstant() != null) {
            properties.put(CONSTANT_LABEL, this.getConstant());
        }

        if (this.getSimple() != null) {
            properties.put(SIMPLE_LABEL, this.getSimple());
        }

        if (this.getName() != null) {
            properties.put(NAME_LABEL, this.getName());
        }
        return properties;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getConstant() {
        return constant;
    }

    public void setConstant(final String constant) {
        this.constant = constant;
    }

    public String getSimple() {
        return simple;
    }

    public void setSimple(final String simple) {
        this.simple = simple;
    }
}
