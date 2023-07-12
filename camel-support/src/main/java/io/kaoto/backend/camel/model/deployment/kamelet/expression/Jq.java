package io.kaoto.backend.camel.model.deployment.kamelet.expression;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder({Jq.EXPRESSION_LABEL, Jq.ID_LABEL, Jq.RESULT_TYPE_LABEL, Jq.TRIM_LABEL})
@RegisterForReflection
public class Jq {
    public static final String EXPRESSION_LABEL = "expression";
    public static final String ID_LABEL = "id";
    public static final String RESULT_TYPE_LABEL = "result-type";
    public static final String RESULT_TYPE_LABEL2 = "resultType";
    public static final String HEADER_NAME_LABEL = "header-name";
    public static final String HEADER_NAME_LABEL2 = "headerName";
    public static final String PROPERTY_NAME_LABEL = "property-name";
    public static final String PROPERTY_NAME_LABEL2 = "propertyName";
    public static final String TRIM_LABEL = "trim";
    private String expression;
    private String id;
    private String resultType;
    private String headerName;
    private String propertyName;
    private Boolean trim;

    public Jq() {

    }

    @SuppressWarnings("CPD-START")
    public Jq(Map map) {
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
        if (map.get(HEADER_NAME_LABEL) != null) {
            setHeaderName(String.valueOf(map.get(HEADER_NAME_LABEL)));
        } else if (map.get(HEADER_NAME_LABEL2) != null) {
            setHeaderName(String.valueOf(map.get(HEADER_NAME_LABEL2)));
        }
        if (map.get(PROPERTY_NAME_LABEL) != null) {
            setPropertyName(String.valueOf(map.get(PROPERTY_NAME_LABEL)));
        } else if (map.get(PROPERTY_NAME_LABEL2) != null) {
            setPropertyName(String.valueOf(map.get(PROPERTY_NAME_LABEL2)));
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

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Boolean getTrim() {
        return trim;
    }

    public void setTrim(Boolean trim) {
        this.trim = trim;
    }
}
