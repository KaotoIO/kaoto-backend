package io.kaoto.backend.model.deployment.kamelet.expression;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.kaoto.backend.api.service.deployment.generator.kamelet.KameletRepresenter;
import io.kaoto.backend.model.deployment.kamelet.step.EIPStep;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder({"name", "constant", "simple"})
public class Expression extends EIPStep {
    public static final String CONSTANT_LABEL = KameletRepresenter.CONSTANT;
    public static final String SIMPLE_LABEL = KameletRepresenter.SIMPLE;
    public static final String NAME_LABEL = KameletRepresenter.NAME;

    public static final String EXPRESSION_LABEL = "expression";

    @JsonProperty(CONSTANT_LABEL)
    private String constant;

    @JsonProperty(SIMPLE_LABEL)
    private String simple;

    @JsonProperty(NAME_LABEL)
    private String name;

    public Expression() {
        //Needed for serialization
    }

    @JsonCreator
    public Expression(
            final @JsonProperty(EXPRESSION_LABEL) Expression expression,
            final @JsonProperty(CONSTANT_LABEL) String constant,
            final @JsonProperty(SIMPLE_LABEL) String simple,
            final @JsonProperty(NAME_LABEL) String name) {
        setConstant(constant);
        setSimple(simple);
        setName(name);
        if (expression != null) {
            setProperties(expression);
        }
    }

    public Expression(Step step) {
        super(step);
    }

    public Expression(Object obj) {
        setProperties(obj);
    }

    protected void setProperties(final Object obj) {
        if (obj instanceof Expression e) {
            setConstant(e.getConstant());
            setSimple(e.getSimple());
            setName(e.getName());
        } else if (obj instanceof Map map) {
            setPropertiesFromMap(map);
        }
    }

    protected void setPropertiesFromMap(final Map map) {
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
            case EXPRESSION_LABEL:
                setProperties(parameter.getValue());
                break;
            default:
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
            case EXPRESSION_LABEL:
                parameter.setValue(this);
                break;
            default:
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
