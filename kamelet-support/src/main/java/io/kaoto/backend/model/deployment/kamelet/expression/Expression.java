package io.kaoto.backend.model.deployment.kamelet.expression;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.kaoto.backend.api.service.deployment.generator.kamelet.KameletRepresenter;
import io.kaoto.backend.model.deployment.kamelet.step.EIPStep;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import java.util.Map;

@JsonPropertyOrder({"name", "constant", "simple", "jq", "jsonpath"})
public class Expression extends EIPStep {
    public static final String CONSTANT_LABEL = KameletRepresenter.CONSTANT;
    public static final String SIMPLE_LABEL = KameletRepresenter.SIMPLE;
    public static final String JQ_LABEL = KameletRepresenter.JQ;
    public static final String JSON_PATH_LABEL = "jsonpath";
    public static final String NAME_LABEL = KameletRepresenter.NAME;

    public static final String RESULT_TYPE = "result-type";
    public static final String RESULT_TYPE2 = "resultType";

    public static final String EXPRESSION_LABEL = "expression";

    private Object constant;

    private Expression expression;

    private Object simple;

    private Object jsonpath;
    private String jq;

    private String name;

    private String resultType;

    public Expression() {
        //Needed for serialization
    }

    @JsonCreator
    public Expression(
            final @JsonProperty(EXPRESSION_LABEL) Expression expression,
            final @JsonProperty(CONSTANT_LABEL) Object constant,
            final @JsonProperty(SIMPLE_LABEL) Object simple,
            final @JsonProperty(JQ_LABEL) String jq,
            final @JsonProperty(NAME_LABEL) String name,
            final @JsonProperty(RESULT_TYPE) String resultType,
            final @JsonProperty(RESULT_TYPE2) String resultType2,
            final @JsonProperty(JSON_PATH_LABEL) Object jsonpath,
            final @JsonProperty("id") String id) {
        setExpression(expression);
        setConstant(constant);
        setSimple(simple);
        setJq(jq);
        setName(name);
        setResultType(resultType != null ? resultType : resultType2);
        setJsonpath(jsonpath);
        setId(id);
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
            setJsonpath(e.getJsonpath());
            setExpression(e.getExpression());
            setId(e.getId());
        } else if (obj instanceof Map map) {
            setPropertiesFromMap(map, this);
        }
    }

    protected void setPropertiesFromMap(final Map map, final Expression expression) {
        if (map.containsKey(CONSTANT_LABEL) && map.get(CONSTANT_LABEL) != null) {
            expression.setConstant(String.valueOf(map.get(CONSTANT_LABEL)));
        }
        if (map.containsKey(SIMPLE_LABEL) && map.get(SIMPLE_LABEL) != null) {
            expression.setSimple(String.valueOf(map.get(SIMPLE_LABEL)));
        }
        if (map.containsKey(JQ_LABEL) && map.get(JQ_LABEL) != null) {
            expression.setJq(String.valueOf(map.get(JQ_LABEL)));
        }
        if (map.containsKey(JSON_PATH_LABEL) && map.get(JSON_PATH_LABEL) != null) {
            expression.setJsonpath(map.get(JSON_PATH_LABEL));
        }
        if (map.containsKey(NAME_LABEL) && map.get(NAME_LABEL) != null) {
            expression.setName(String.valueOf(map.get(NAME_LABEL)));
        }
        if (map.containsKey(RESULT_TYPE) && map.get(RESULT_TYPE) != null) {
            expression.setResultType(String.valueOf(map.get(RESULT_TYPE)));
        } else if (map.containsKey(RESULT_TYPE2) && map.get(RESULT_TYPE2) != null) {
            expression.setResultType(String.valueOf(map.get(RESULT_TYPE2)));
        }
        if (map.containsKey(EXPRESSION_LABEL) && map.get(EXPRESSION_LABEL) != null) {
            expression.setExpression((Expression) map.get(EXPRESSION_LABEL));
        }
        if (map.containsKey("id") && map.get("id") != null) {
            expression.setId(String.valueOf(map.get("id")));
        }
    }

    @Override
    protected void assignAttribute(final Parameter parameter) {
        switch (parameter.getId()) {
            case CONSTANT_LABEL:
                this.setConstant(String.valueOf(parameter.getValue()));
                break;
            case SIMPLE_LABEL:
                this.setSimple(parameter.getValue());
                break;
            case JQ_LABEL:
                this.setJq(String.valueOf(parameter.getValue()));
                break;
            case JSON_PATH_LABEL:
                this.setJsonpath(parameter.getValue());
                break;
            case NAME_LABEL:
                this.setName(String.valueOf(parameter.getValue()));
                break;
            case EXPRESSION_LABEL:
                this.setExpression((Expression) parameter.getValue());
                break;
            case RESULT_TYPE:
            case RESULT_TYPE2:
                setResultType(String.valueOf(parameter.getValue()));
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
            case JSON_PATH_LABEL:
                parameter.setValue(this.getJsonpath());
                break;
            case NAME_LABEL:
                parameter.setValue(this.name);
                break;
            case EXPRESSION_LABEL:
                parameter.setValue(this.getExpression());
                break;
            case RESULT_TYPE:
            case RESULT_TYPE2:
                parameter.setValue(this.getResultType());
                break;
            default:
                break;
        }
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {

        Map<String, Object> properties = super.getDefaultRepresenterProperties();
        if (this.getConstant() != null) {
            if (this.getConstant() instanceof CSimple csimple) {
                properties.put(CONSTANT_LABEL, csimple.getRepresenterProperties());
            } else {
                properties.put(CONSTANT_LABEL, this.getConstant());
            }
        }

        if (this.getSimple() != null) {
            if (this.getSimple() instanceof CSimple csimple) {
                properties.put(SIMPLE_LABEL, csimple.getRepresenterProperties());
            } else {
                properties.put(SIMPLE_LABEL, this.getSimple());
            }
        }

        if (this.getJq() != null) {
            properties.put(JQ_LABEL, this.getJq());
        }

        if (this.getJsonpath() != null) {
            if (this.getJsonpath() instanceof JsonPath jsonPath) {
                properties.put(JSON_PATH_LABEL, jsonPath.getRepresenterProperties());
            } else {
                properties.put(JSON_PATH_LABEL, this.getJsonpath());
            }
        }

        if (this.getName() != null) {
            properties.put(NAME_LABEL, this.getName());
        }

        if (this.getResultType() != null) {
            properties.put(RESULT_TYPE, this.getResultType());
        }

        if (this.getExpression() != null) {
            properties.put(EXPRESSION_LABEL, this.getExpression());
        }
        return properties;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Object getConstant() {
        return constant;
    }

    public void setConstant(final Object constant) {
        if (constant instanceof Map map) {
            this.constant = new CSimple(map);
        } else {
            this.constant = constant;
        }
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public Object getSimple() {
        return simple;
    }

    public void setSimple(final Object simple) {
        if (simple instanceof Map map) {
            this.simple = new CSimple(map);
        } else {
            this.simple = simple;
        }
    }

    public Object getJsonpath() {
        return jsonpath;
    }

    public void setJsonpath(Object jsonpath) {
        if (jsonpath instanceof Map map) {
            this.jsonpath = new JsonPath(map);
        } else {
            this.jsonpath = jsonpath;
        }
    }

    public String getJq() {
        return jq;
    }

    public void setJq(final String jq) {
        this.jq = jq;
    }
}
