package io.kaoto.backend.camel.model.deployment.kamelet.expression;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder({Groovy.EXPRESSION_LABEL, Groovy.ID_LABEL, Groovy.RESULT_TYPE_LABEL, Groovy.TRIM_LABEL})
@RegisterForReflection
public class Groovy {
    public static final String EXPRESSION_LABEL = "expression";
    public static final String ID_LABEL = "id";
    public static final String RESULT_TYPE_LABEL = "result-type";
    public static final String RESULT_TYPE_LABEL2 = "resultType";
    public static final String TRIM_LABEL = "trim";
    private String expression;
    private String id;
    private String resultType;
    private Boolean trim;

    public Groovy() {

    }

    @SuppressWarnings("CPD-START")
    public Groovy(Map<?,?> map) {
        if (map.get(EXPRESSION_LABEL) != null) {
            setExpression(String.valueOf(map.get(EXPRESSION_LABEL)));
        }
        if (map.get(ID_LABEL) != null) {
            setId(String.valueOf(map.get(ID_LABEL)));
        }
        if (map.get(RESULT_TYPE_LABEL) != null) {
            setResultType(String.valueOf(map.get(RESULT_TYPE_LABEL)));
        } else if (map.get(RESULT_TYPE_LABEL2) != null) {
            setResultType(String.valueOf(map.get(RESULT_TYPE_LABEL2)));
        }
        if (map.get(TRIM_LABEL) != null) {
            setTrim(Boolean.valueOf(String.valueOf(map.get(TRIM_LABEL))));
        }
    }

    public Map<String, Object> getRepresenterProperties() {

        Map<String, Object> properties = new HashMap<>();
        if (this.getExpression() != null) {
            properties.put(EXPRESSION_LABEL, this.getExpression());
        }

        if (this.getId() != null) {
            properties.put(ID_LABEL, this.getId());
        }

        if (this.getTrim() != null) {
            properties.put(TRIM_LABEL, this.getTrim());
        }

        if (this.getResultType() != null) {
            properties.put(RESULT_TYPE_LABEL, this.getResultType());
        }

        return properties;
    }
    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public Boolean getTrim() {
        return trim;
    }

    public void setTrim(Boolean trim) {
        this.trim = trim;
    }
}
