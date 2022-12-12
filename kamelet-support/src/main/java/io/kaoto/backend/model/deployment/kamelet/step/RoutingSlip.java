package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kaoto.backend.model.deployment.kamelet.expression.Expression;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import java.util.Map;


public class RoutingSlip extends Expression {
    public static final String URI_DELIMITER_LABEL = "uri-delimiter";
    public static final String URI_DELIMITER_LABEL2 = "uriDelimiter";

    public static final String IGNORE_INVALID_ENDPOINTS_LABEL = "ignore-invalid-endpoints";
    public static final String IGNORE_INVALID_ENDPOINTS_LABEL2 = "ignoreInvalidEndpoints";

    public static final String CACHE_SIZE_LABEL = "cache-size";
    public static final String CACHE_SIZE_LABEL2 = "cacheSize";

    public static final String DESCRIPTION_LABEL = "description";

    private String uriDelimiter;

    private Boolean ignoreInvalidEndpoints;

    private Integer cacheSize;

    private Map<String, String> description;


    public RoutingSlip() {
        //Needed for serialization
    }

    public RoutingSlip(Step step) {
        super(step);
    }

    public RoutingSlip(final @JsonProperty(EXPRESSION_LABEL) Expression expression,
                       final @JsonProperty(URI_DELIMITER_LABEL) String uriDelimiter,
                       final @JsonProperty(URI_DELIMITER_LABEL2) String uriDelimiter2,
                       final @JsonProperty(IGNORE_INVALID_ENDPOINTS_LABEL) Boolean ignoreInvalidEndpoints,
                       final @JsonProperty(IGNORE_INVALID_ENDPOINTS_LABEL2) Boolean ignoreInvalidEndpoints2,
                       final @JsonProperty(CACHE_SIZE_LABEL) Integer cacheSize,
                       final @JsonProperty(CACHE_SIZE_LABEL2) Integer cacheSize2,
                       final @JsonProperty(DESCRIPTION_LABEL) Map<String, String> description,
                       final @JsonProperty(SIMPLE_LABEL) String simple,
                       final @JsonProperty(CONSTANT_LABEL) String constant) {
        super(expression, constant, simple, null);
        setUriDelimiter(uriDelimiter != null ? uriDelimiter : uriDelimiter2);
        setIgnoreInvalidEndpoints(ignoreInvalidEndpoints != null ? ignoreInvalidEndpoints : ignoreInvalidEndpoints2);
        setCacheSize(cacheSize != null ? cacheSize : cacheSize2);
        setDescription(description);
    }

    @Override
    protected void assignAttribute(final Parameter parameter) {
        super.assignAttribute(parameter);
        switch (parameter.getId()) {
            case URI_DELIMITER_LABEL:
            case URI_DELIMITER_LABEL2:
                this.setUriDelimiter(String.valueOf(parameter.getValue()));
                break;
            case IGNORE_INVALID_ENDPOINTS_LABEL:
            case IGNORE_INVALID_ENDPOINTS_LABEL2:
                this.setIgnoreInvalidEndpoints(Boolean.valueOf(String.valueOf(parameter.getValue())));
                break;
            case CACHE_SIZE_LABEL:
            case CACHE_SIZE_LABEL2:
                this.setCacheSize(Integer.valueOf(String.valueOf(parameter.getValue())));
                break;
            case DESCRIPTION_LABEL:
                this.setDescription((Map<String, String>) parameter.getValue());
                break;
            default:
                break;
        }
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = super.getRepresenterProperties();
        if (this.getUriDelimiter() != null) {
            properties.put(URI_DELIMITER_LABEL, this.getUriDelimiter());
        }
        if (this.getIgnoreInvalidEndpoints() != null) {
            properties.put(IGNORE_INVALID_ENDPOINTS_LABEL, this.getIgnoreInvalidEndpoints());
        }
        if (this.getCacheSize() != null) {
            properties.put(CACHE_SIZE_LABEL, this.getCacheSize());
        }
        if (this.getDescription() != null) {
            properties.put(DESCRIPTION_LABEL, this.getDescription());
        }

        return properties;
    }

    @Override
    protected void assignProperty(final Parameter parameter) {
        super.assignProperty(parameter);
        switch (parameter.getId()) {
            case URI_DELIMITER_LABEL:
            case URI_DELIMITER_LABEL2:
                parameter.setValue(getUriDelimiter());
                break;
            case IGNORE_INVALID_ENDPOINTS_LABEL:
            case IGNORE_INVALID_ENDPOINTS_LABEL2:
                parameter.setValue(getIgnoreInvalidEndpoints());
                break;
            case CACHE_SIZE_LABEL:
            case CACHE_SIZE_LABEL2:
                parameter.setValue(getCacheSize());
                break;
            case DESCRIPTION_LABEL:
                parameter.setValue(this.getDescription());
                break;
            default:
                break;
        }
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
