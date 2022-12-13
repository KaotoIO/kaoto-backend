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

@JsonPropertyOrder({"name", "constant", "simple", "jq"})
public class Expression extends EIPStep {
    public static final String CONSTANT_LABEL = KameletRepresenter.CONSTANT;
    public static final String SIMPLE_LABEL = KameletRepresenter.SIMPLE;
    public static final String JQ_LABEL = KameletRepresenter.JQ;
    public static final String NAME_LABEL = KameletRepresenter.NAME;

    public static final String EXPRESSION_LABEL = "expression";

    @JsonProperty(CONSTANT_LABEL)
    private String constant;

    @JsonProperty(SIMPLE_LABEL)
    private String simple;
    @JsonProperty(JQ_LABEL)
    private String jq;

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
            final @JsonProperty(JQ_LABEL) String jq,
            final @JsonProperty(NAME_LABEL) String name) {
        setConstant(constant);
        setSimple(simple);
        setJq(jq);
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
            setJq(e.getJq());
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
        if (map.containsKey(JQ_LABEL) && map.get(JQ_LABEL) != null) {
            setJq(map.get(JQ_LABEL).toString());
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
            case JQ_LABEL:
                this.setJq(parameter.getValue().toString());
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
            case JQ_LABEL:
                parameter.setValue(this.jq);
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

        if (this.getJq() != null) {
            properties.put(JQ_LABEL, this.getJq());
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

    public String getJq() {
        return jq;
    }

    public void setJq(final String jq) {
        this.jq = jq;
    }
}
