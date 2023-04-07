package io.kaoto.backend.model.deployment.kamelet.expression;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder({
        Javascript.EXPRESSION_LABEL,
        Javascript.ID_LABEL,
        Javascript.RESULT_TYPE_LABEL,
        Javascript.TRIM_LABEL})
@RegisterForReflection
public class Javascript {
    public static final String EXPRESSION_LABEL = "expression";
    public static final String ID_LABEL = "id";
    public static final String RESULT_TYPE_LABEL = "result-type";
    public static final String RESULT_TYPE_LABEL2 = "resultType";
    public static final String TRIM_LABEL = "trim";
    private String expression;
    private String id;
    private String resultType;
    private Boolean trim;

    public Javascript() {

    }

    @SuppressWarnings("CPD-START")
    public Javascript(Map map) {
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
