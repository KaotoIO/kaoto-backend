package io.kaoto.backend.model.deployment.kamelet.expression;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.kaoto.backend.api.service.deployment.generator.kamelet.KameletRepresenter;
import io.kaoto.backend.model.deployment.kamelet.step.EIPStep;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;
import io.quarkus.runtime.annotations.RegisterForReflection;

import java.util.Map;

@JsonPropertyOrder({"name", "groovy", "javascript", "expression"})
@RegisterForReflection
public class ScriptExpression extends EIPStep {
    public static final String GROOVY_LABEL = KameletRepresenter.GROOVY;
    public static final String JAVASCRIPT_LABEL = KameletRepresenter.JAVASCRIPT;
    public static final String EXPRESSION_LABEL = KameletRepresenter.EXPRESSION;
    public static final String NAME_LABEL = KameletRepresenter.NAME;

    public static final String RESULT_TYPE = "result-type";
    public static final String RESULT_TYPE2 = "resultType";

    private Object groovy;

    private ScriptExpression expression;

    private Object javascript;

    private String name;

    private String resultType;

    public ScriptExpression() {
        //Needed for serialization
    }

    @JsonCreator
    public ScriptExpression(
            final @JsonProperty(EXPRESSION_LABEL) ScriptExpression expression,
            final @JsonProperty(GROOVY_LABEL) Object groovy,
            final @JsonProperty(JAVASCRIPT_LABEL) Object javascript,
            final @JsonProperty(NAME_LABEL) String name,
            final @JsonProperty(RESULT_TYPE) String resultType,
            final @JsonProperty(RESULT_TYPE2) String resultType2,
            final @JsonProperty("id") String id) {
        setExpression(expression);
        setGroovy(groovy);
        setJavascript(javascript);
        setName(name);
        setResultType(resultType != null ? resultType : resultType2);
        setId(id);
    }

    public ScriptExpression(Step step) {
        super(step);
    }

    public ScriptExpression(Object obj) {
        setProperties(obj);
    }

    protected void setProperties(final Object obj) {
        if (obj instanceof ScriptExpression e) {
            setGroovy(e.getGroovy());
            setJavascript(e.getJavascript());
            setName(e.getName());
            setExpression(e.getExpression());
            setId(e.getId());
        } else if (obj instanceof Map map) {
            setPropertiesFromMap(map, this);
        }
    }

    @SuppressWarnings("CPD-START")
    protected void setPropertiesFromMap(final Map map, final ScriptExpression expression) {
        if (map.containsKey(GROOVY_LABEL) && map.get(GROOVY_LABEL) != null) {
            expression.setGroovy(parseLang(map.get(GROOVY_LABEL)));
        }
        if (map.containsKey(JAVASCRIPT_LABEL) && map.get(JAVASCRIPT_LABEL) != null) {
            expression.setJavascript(parseLang(map.get(JAVASCRIPT_LABEL)));
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
            Object nested = map.get(EXPRESSION_LABEL);
            ScriptExpression nestedExpression =
                    nested instanceof ScriptExpression ne ? ne : new ScriptExpression(nested);
            expression.setExpression(nestedExpression);
        }
        if (map.containsKey("id") && map.get("id") != null) {
            expression.setId(String.valueOf(map.get("id")));
        }
    }

    private Object parseLang(Object lang) {
        if (lang instanceof Map) {
            return lang;
        }
        return String.valueOf(lang);
    }

    @Override
    protected void assignAttribute(final Parameter parameter) {
        switch (parameter.getId()) {
            case GROOVY_LABEL:
                this.setGroovy(parameter.getValue());
                break;
            case JAVASCRIPT_LABEL:
                this.setJavascript(parameter.getValue());
                break;
            case NAME_LABEL:
                this.setName(String.valueOf(parameter.getValue()));
                break;
            case EXPRESSION_LABEL:
                ScriptExpression nestedExpression = new ScriptExpression(parameter.getValue());
                this.setExpression(nestedExpression);
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
            case GROOVY_LABEL:
                parameter.setValue(this.groovy);
                break;
            case JAVASCRIPT_LABEL:
                parameter.setValue(this.javascript);
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
        if (this.getGroovy() != null) {
            if (this.getGroovy() instanceof Groovy groovy) {
                properties.put(GROOVY_LABEL, groovy.getRepresenterProperties());
            } else {
                properties.put(GROOVY_LABEL, this.getGroovy());
            }
        }

        if (this.getJavascript() != null) {
            if (this.getJavascript() instanceof Javascript javascript) {
                properties.put(JAVASCRIPT_LABEL, javascript.getRepresenterProperties());
            } else {
                properties.put(JAVASCRIPT_LABEL, this.getJavascript());
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

    public Object getGroovy() {
        return groovy;
    }

    public void setGroovy(final Object groovy) {
        if (groovy instanceof Map map) {
            this.groovy = new Groovy(map);
        } else {
            this.groovy = groovy;
        }
    }

    public Object getJavascript() {
        return javascript;
    }

    public void setJavascript(final Object javascript) {
        if (javascript instanceof Map map) {
            this.javascript = new Javascript(map);
        } else {
            this.javascript = javascript;
        }
    }

    public ScriptExpression getExpression() {
        return expression;
    }

    public void setExpression(ScriptExpression expression) {
        this.expression = expression;
    }


}
