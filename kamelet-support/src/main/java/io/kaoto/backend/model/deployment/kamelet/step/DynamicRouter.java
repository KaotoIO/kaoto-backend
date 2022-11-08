package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kaoto.backend.model.deployment.kamelet.expression.Expression;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import java.util.LinkedHashMap;
import java.util.Map;


public class DynamicRouter extends EIPStep {
    public static final String EXPRESSION_LABEL = "expression";
    public static final String URI_DELIMITER_LABEL = "uri-delimiter";
    public static final String URI_DELIMITER_LABEL2 = "uriDelimiter";
    public static final String IGNORE_VALID_ENDPOINTS_LABEL = "ignore-valid-endpoints";
    public static final String IGNORE_VALID_ENDPOINTS_LABEL2 = "ignoreValidEndpoints";
    public static final String CACHE_SIZE_LABEL = "cache-size";
    public static final String CACHE_SIZE_LABEL2 = "cacheSize";
    public static final String DESCRIPTION_LABEL = "description";

    @JsonProperty(EXPRESSION_LABEL)
    private Expression expression;

    @JsonProperty(URI_DELIMITER_LABEL)
    private String uriDelimiter;

    @JsonProperty(IGNORE_VALID_ENDPOINTS_LABEL)
    private Boolean ignoreInvalidEndpoints;

    @JsonProperty(CACHE_SIZE_LABEL)
    private Integer cacheSize;


    @JsonProperty(DESCRIPTION_LABEL)
    private Map<String, String> description;


    public DynamicRouter() {
         //Needed for serialization
    }

    public DynamicRouter(Step step) {
        super(step);
    }

    @Override
    protected void assignAttribute(final Parameter parameter) {
        switch (parameter.getId()) {
            case EXPRESSION_LABEL:
                this.setExpression(new Expression(parameter.getValue()));
                break;
            case URI_DELIMITER_LABEL:
            case URI_DELIMITER_LABEL2:
                this.setUriDelimiter(parameter.getValue().toString());
                break;
            case IGNORE_VALID_ENDPOINTS_LABEL:
            case IGNORE_VALID_ENDPOINTS_LABEL2:
                this.setIgnoreInvalidEndpoints(Boolean.valueOf(parameter.getValue().toString()));
                break;
            case CACHE_SIZE_LABEL:
            case CACHE_SIZE_LABEL2:
                this.setCacheSize(Integer.valueOf(parameter.getValue().toString()));
                break;
            case DESCRIPTION_LABEL:
                this.setDescription((Map<String, String>) parameter.getValue());
                break;
            default:
                log.error("Unknown property: " + parameter.getId());
                break;
        }
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new LinkedHashMap<>();
        if (this.expression != null) {
            properties.put(EXPRESSION_LABEL, this.expression);
        }
        if (this.uriDelimiter != null) {
            properties.put(URI_DELIMITER_LABEL, this.uriDelimiter);
        }
        if (this.ignoreInvalidEndpoints != null) {
            properties.put(IGNORE_VALID_ENDPOINTS_LABEL, this.ignoreInvalidEndpoints);
        }
        if (this.cacheSize != null) {
            properties.put(CACHE_SIZE_LABEL, this.cacheSize);
        }
        if (this.description != null) {
            properties.put(DESCRIPTION_LABEL, this.description);
        }
        return properties;
    }

    @Override
    protected void assignProperty(final Parameter parameter) {
        switch (parameter.getId()) {
            case EXPRESSION_LABEL:
                parameter.setValue(this.expression);
                break;
            case URI_DELIMITER_LABEL:
            case URI_DELIMITER_LABEL2:
                parameter.setValue(this.uriDelimiter);
                break;
            case IGNORE_VALID_ENDPOINTS_LABEL2:
            case IGNORE_VALID_ENDPOINTS_LABEL:
                parameter.setValue(this.ignoreInvalidEndpoints);
                break;
            case CACHE_SIZE_LABEL:
            case CACHE_SIZE_LABEL2:
                parameter.setValue(this.cacheSize);
                break;
            case DESCRIPTION_LABEL:
                parameter.setValue(this.description);
                break;
            default:
                log.error("Unknown property: " + parameter.getId());
                break;
        }
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(final Expression expression) {
        this.expression = expression;
    }

    public String getUriDelimiter() {
        return uriDelimiter;
    }

    public void setUriDelimiter(final String uriDelimiter) {
        this.uriDelimiter = uriDelimiter;
    }

    public Boolean getIgnoreInvalidEndpoints() {
        return ignoreInvalidEndpoints;
    }

    public void setIgnoreInvalidEndpoints(final Boolean ignoreInvalidEndpoints) {
        this.ignoreInvalidEndpoints = ignoreInvalidEndpoints;
    }

    public Integer getCacheSize() {
        return cacheSize;
    }

    public void setCacheSize(final Integer cacheSize) {
        this.cacheSize = cacheSize;
    }

    public Map<String, String> getDescription() {
        return description;
    }

    public void setDescription(final Map<String, String> description) {
        this.description = description;
    }
}
