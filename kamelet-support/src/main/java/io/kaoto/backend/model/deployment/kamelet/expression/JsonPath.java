package io.kaoto.backend.model.deployment.kamelet.expression;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonPropertyOrder({JsonPath.EXPRESSION_LABEL, JsonPath.ID_LABEL, JsonPath.RESULT_TYPE_LABEL, JsonPath.TRIM_LABEL})
public class JsonPath {
    public static final String EXPRESSION_LABEL = "expression";
    public static final String ALLOW_EASY_PREDICATE_LABEL = "allow-easy-predicate";
    public static final String ALLOW_EASY_PREDICATE_LABEL2 = "allowEasyPredicate";
    public static final String ALLOW_SIMPLE_LABEL = "allow-simple";
    public static final String ALLOW_SIMPLE_LABEL2 = "allowSimple";
    public static final String HEADER_NAME_LABEL = "header-name";
    public static final String HEADER_NAME_LABEL2 = "headerName";
    public static final String ID_LABEL = "id";
    public static final String OPTION_LABEL = "property-name";
    public static final String PROPERTY_NAME_LABEL = "property-name";
    public static final String PROPERTY_NAME_LABEL2 = "propertyName";
    public static final String RESULT_TYPE_LABEL = "result-type";
    public static final String RESULT_TYPE_LABEL2 = "resultType";
    public static final String SUPRESS_EXCEPTIONS_LABEL = "suppress-exceptions";
    public static final String SUPRESS_EXCEPTIONS_LABEL2 = "suppressExceptions";
    public static final String TRIM_LABEL = "trim";
    public static final String UNPACK_ARRAY_LABEL = "unpack-array";
    public static final String UNPACK_ARRAY_LABEL2 = "unpackArray";
    public static final String WRITE_AS_STRING_LABEL = "write-as-string";
    public static final String WRITE_AS_STRING_LABEL2 = "writeAsString";

    private Boolean allowEasyPredicate;
    private Boolean allowSimple;
    private String expression;
    private String headerName;
    private String id;
    private String option;
    private String propertyName;
    private String resultType;
    private Boolean supressExceptions;
    private Boolean trim;
    private Boolean unpackArray;
    private Boolean writeAsString;

    public JsonPath() {

    }

    public JsonPath(Map map) {

        if (map.get(ALLOW_EASY_PREDICATE_LABEL) != null) {
            setAllowEasyPredicate(Boolean.valueOf(String.valueOf(map.get(ALLOW_EASY_PREDICATE_LABEL))));
        } else if (map.get(ALLOW_EASY_PREDICATE_LABEL2) != null) {
            setAllowEasyPredicate(Boolean.valueOf(String.valueOf(map.get(ALLOW_EASY_PREDICATE_LABEL2))));
        }

        if (map.get(ALLOW_SIMPLE_LABEL) != null) {
            setAllowSimple(Boolean.valueOf(String.valueOf(map.get(ALLOW_SIMPLE_LABEL))));
        } else if (map.get(ALLOW_SIMPLE_LABEL2) != null) {
            setAllowSimple(Boolean.valueOf(String.valueOf(map.get(ALLOW_SIMPLE_LABEL2))));
        }

        if (map.get(EXPRESSION_LABEL) != null) {
            setExpression(String.valueOf(map.get(EXPRESSION_LABEL)));
        }

        if (map.get(HEADER_NAME_LABEL) != null) {
            setHeaderName(String.valueOf(map.get(HEADER_NAME_LABEL)));
        } else if (map.get(HEADER_NAME_LABEL2) != null) {
            setHeaderName(String.valueOf(map.get(HEADER_NAME_LABEL2)));
        }

        if (map.get(ID_LABEL) != null) {
            setId(String.valueOf(map.get(ID_LABEL)));
        }

        if (map.get(OPTION_LABEL) != null) {
            setOption(String.valueOf(map.get(OPTION_LABEL)));
        }

        if (map.get(PROPERTY_NAME_LABEL) != null) {
            setPropertyName(String.valueOf(map.get(PROPERTY_NAME_LABEL)));
        } else if (map.get(PROPERTY_NAME_LABEL2) != null) {
            setPropertyName(String.valueOf(map.get(PROPERTY_NAME_LABEL2)));
        }

        if (map.get(RESULT_TYPE_LABEL) != null) {
            setResultType(String.valueOf(map.get(RESULT_TYPE_LABEL)));
        } else if (map.get(RESULT_TYPE_LABEL2) != null) {
            setResultType(String.valueOf(map.get(RESULT_TYPE_LABEL2)));
        }

        if (map.get(SUPRESS_EXCEPTIONS_LABEL) != null) {
            setSupressExceptions(Boolean.valueOf(String.valueOf(map.get(SUPRESS_EXCEPTIONS_LABEL))));
        } else if (map.get(SUPRESS_EXCEPTIONS_LABEL2) != null) {
            setSupressExceptions(Boolean.valueOf(String.valueOf(map.get(SUPRESS_EXCEPTIONS_LABEL2))));
        }

        if (map.get(TRIM_LABEL) != null) {
            setTrim(Boolean.valueOf(String.valueOf(map.get(TRIM_LABEL))));
        }

        if (map.get(UNPACK_ARRAY_LABEL) != null) {
            setUnpackArray(Boolean.valueOf(String.valueOf(map.get(UNPACK_ARRAY_LABEL))));
        } else if (map.get(UNPACK_ARRAY_LABEL2) != null) {
            setUnpackArray(Boolean.valueOf(String.valueOf(map.get(UNPACK_ARRAY_LABEL2))));
        }

        if (map.get(WRITE_AS_STRING_LABEL) != null) {
            setWriteAsString(Boolean.valueOf(String.valueOf(map.get(WRITE_AS_STRING_LABEL))));
        } else if (map.get(WRITE_AS_STRING_LABEL2) != null) {
            setWriteAsString(Boolean.valueOf(String.valueOf(map.get(WRITE_AS_STRING_LABEL2))));
        }
    }

    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new LinkedHashMap<>();
        if (this.getAllowEasyPredicate() != null) {
            properties.put(ALLOW_EASY_PREDICATE_LABEL, this.getAllowEasyPredicate());
        }
        if (this.getAllowSimple() != null) {
            properties.put(ALLOW_SIMPLE_LABEL, this.getAllowSimple());
        }
        if (this.getExpression() != null) {
            properties.put(EXPRESSION_LABEL, this.getExpression());
        }
        if (this.getHeaderName() != null) {
            properties.put(HEADER_NAME_LABEL, this.getHeaderName());
        }
        if (this.getId() != null) {
            properties.put(ID_LABEL, this.getId());
        }
        if (this.getOption() != null) {
            properties.put(OPTION_LABEL, this.getOption());
        }
        if (this.getPropertyName() != null) {
            properties.put(PROPERTY_NAME_LABEL, this.getPropertyName());
        }
        if (this.getResultType() != null) {
            properties.put(RESULT_TYPE_LABEL, this.getResultType());
        }
        if (this.getSupressExceptions() != null) {
            properties.put(SUPRESS_EXCEPTIONS_LABEL, this.getSupressExceptions());
        }
        if (this.getTrim() != null) {
            properties.put(TRIM_LABEL, this.getTrim());
        }
        if (this.getUnpackArray() != null) {
            properties.put(UNPACK_ARRAY_LABEL, this.getUnpackArray());
        }
        if (this.getWriteAsString() != null) {
            properties.put(WRITE_AS_STRING_LABEL, this.getWriteAsString());
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

    public Boolean getAllowEasyPredicate() {
        return allowEasyPredicate;
    }

    public void setAllowEasyPredicate(Boolean allowEasyPredicate) {
        this.allowEasyPredicate = allowEasyPredicate;
    }

    public Boolean getAllowSimple() {
        return allowSimple;
    }

    public void setAllowSimple(Boolean allowSimple) {
        this.allowSimple = allowSimple;
    }

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Boolean getSupressExceptions() {
        return supressExceptions;
    }

    public void setSupressExceptions(Boolean supressExceptions) {
        this.supressExceptions = supressExceptions;
    }

    public Boolean getUnpackArray() {
        return unpackArray;
    }

    public void setUnpackArray(Boolean unpackArray) {
        this.unpackArray = unpackArray;
    }

    public Boolean getWriteAsString() {
        return writeAsString;
    }

    public void setWriteAsString(Boolean writeAsString) {
        this.writeAsString = writeAsString;
    }
}
