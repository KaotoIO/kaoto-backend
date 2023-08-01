package io.kaoto.backend.camel.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.kaoto.backend.camel.KamelHelper;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import java.util.Map;


public class ToDynamic extends EIPStep {

    public static final String URI_LABEL = "uri";

    public static final String PATTERN_LABEL = "pattern";

    public static final String CACHE_SIZE_LABEL = "cache-size";
    public static final String CACHE_SIZE_LABEL2 = "cacheSize";

    public static final String IGNORE_INVALID_ENDPOINTS_LABEL2 = "ignoreInvalidEndpoint";
    public static final String IGNORE_INVALID_ENDPOINTS_LABEL = "ignore-invalid-endpoint";

    public static final String ALLOW_OPTIMISED_COMPONENTS_LABEL2 = "allowOptimisedComponents";
    public static final String ALLOW_OPTIMISED_COMPONENTS_LABEL = "allow-optimised-components";

    public static final String AUTO_START_COMPONENTS_LABEL2 = "autoStartComponents";
    public static final String AUTO_START_COMPONENTS_LABEL = "auto-start-components";

    public static final String DESCRIPTION_LABEL = KamelHelper.DESCRIPTION;
    public static final String PARAMETERS_LABEL = KamelHelper.PARAMETERS;

    private String uri;
    private String pattern;
    private Integer cacheSize;
    private Boolean ignoreInvalidEndpoints;
    private Boolean allowOptimisedComponents;
    private Boolean autoStartComponents;
    private Map<String, String> description;
    private Map<String, Object> parameters;

    public ToDynamic() {
        //Needed for serialization
    }

    public ToDynamic(Step step) {
        super(step);
    }

    @JsonCreator
    public ToDynamic(final @JsonProperty(URI_LABEL) String uri,
                     final @JsonProperty(PATTERN_LABEL) String pattern,
                     final @JsonProperty(CACHE_SIZE_LABEL) Integer cacheSize,
                     final @JsonProperty(CACHE_SIZE_LABEL2) Integer cacheSize2,
                     final @JsonProperty(IGNORE_INVALID_ENDPOINTS_LABEL) Boolean ignoreInvalidEndpoints,
                     final @JsonProperty(IGNORE_INVALID_ENDPOINTS_LABEL2) Boolean ignoreInvalidEndpoints2,
                     final @JsonProperty(ALLOW_OPTIMISED_COMPONENTS_LABEL) Boolean allowOptimisedComponents,
                     final @JsonProperty(ALLOW_OPTIMISED_COMPONENTS_LABEL2) Boolean allowOptimisedComponents2,
                     final @JsonProperty(AUTO_START_COMPONENTS_LABEL) Boolean autoStartComponents,
                     final @JsonProperty(AUTO_START_COMPONENTS_LABEL2) Boolean autoStartComponents2,
                     final @JsonProperty(DESCRIPTION_LABEL) Map<String, String> description,
                     final @JsonProperty(PARAMETERS_LABEL) Map<String, Object> parameters,
                     final @JsonProperty("id") String id) {
        super();
        setUri(uri);
        setPattern(pattern);
        setCacheSize(cacheSize != null ? cacheSize : cacheSize2);
        setIgnoreInvalidEndpoints(ignoreInvalidEndpoints != null ? ignoreInvalidEndpoints : ignoreInvalidEndpoints2);
        setAllowOptimisedComponents(allowOptimisedComponents != null ? allowOptimisedComponents :
                allowOptimisedComponents2);
        setAutoStartComponents(autoStartComponents != null ? autoStartComponents : autoStartComponents2);
        setDescription(description);
        setParameters(parameters);
        setId(id);
    }

    @Override
    protected void assignAttribute(final Parameter parameter) {
        switch (parameter.getId()) {
            case URI_LABEL:
                this.setUri(String.valueOf(parameter.getValue()));
                break;
            case PATTERN_LABEL:
                this.setPattern(String.valueOf(parameter.getValue()));
                break;
            case CACHE_SIZE_LABEL:
            case CACHE_SIZE_LABEL2:
                this.setCacheSize(Integer.valueOf(String.valueOf(parameter.getValue())));
                break;
            case IGNORE_INVALID_ENDPOINTS_LABEL:
            case IGNORE_INVALID_ENDPOINTS_LABEL2:
                this.setIgnoreInvalidEndpoints(Boolean.valueOf(String.valueOf(parameter.getValue())));
                break;
            case ALLOW_OPTIMISED_COMPONENTS_LABEL:
            case ALLOW_OPTIMISED_COMPONENTS_LABEL2:
                this.setAllowOptimisedComponents(Boolean.valueOf(String.valueOf(parameter.getValue())));
                break;
            case AUTO_START_COMPONENTS_LABEL:
            case AUTO_START_COMPONENTS_LABEL2:
                this.setAutoStartComponents(Boolean.valueOf(String.valueOf(parameter.getValue())));
                break;
            case DESCRIPTION_LABEL:
                this.setDescription((Map<String, String>) parameter.getValue());
                break;
            case PARAMETERS_LABEL:
                this.setParameters((Map<String, Object>) parameter.getValue());
                break;
            default:
                break;
        }
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        var properties = super.getDefaultRepresenterProperties();
        if (this.getUri() != null) {
            properties.put(URI_LABEL, this.getUri());
        }
        if (this.getParameters() != null) {
            properties.put(PARAMETERS_LABEL, this.getParameters());
        }
        if (this.getPattern() != null) {
            properties.put(PATTERN_LABEL, this.getPattern());
        }
        if (this.getCacheSize() != null) {
            properties.put(CACHE_SIZE_LABEL, this.getCacheSize());
        }
        if (this.getIgnoreInvalidEndpoints() != null) {
            properties.put(IGNORE_INVALID_ENDPOINTS_LABEL, this.getIgnoreInvalidEndpoints());
        }
        if (this.getAllowOptimisedComponents() != null) {
            properties.put(ALLOW_OPTIMISED_COMPONENTS_LABEL, this.getAllowOptimisedComponents());
        }
        if (this.getAutoStartComponents() != null) {
            properties.put(AUTO_START_COMPONENTS_LABEL, this.getAutoStartComponents());
        }
        if (this.getDescription() != null) {
            properties.put(DESCRIPTION_LABEL, this.getDescription());
        }
        return properties;
    }

    @Override
    protected void assignProperty(final Parameter parameter) {
        switch (parameter.getId()) {
            case URI_LABEL:
                parameter.setValue(this.getUri());
                break;
            case PATTERN_LABEL:
                parameter.setValue(this.getPattern());
                break;
            case CACHE_SIZE_LABEL:
            case CACHE_SIZE_LABEL2:
                parameter.setValue(this.getCacheSize());
                break;
            case IGNORE_INVALID_ENDPOINTS_LABEL:
            case IGNORE_INVALID_ENDPOINTS_LABEL2:
                parameter.setValue(this.getIgnoreInvalidEndpoints());
                break;
            case ALLOW_OPTIMISED_COMPONENTS_LABEL:
            case ALLOW_OPTIMISED_COMPONENTS_LABEL2:
                parameter.setValue(this.getAllowOptimisedComponents());
                break;
            case AUTO_START_COMPONENTS_LABEL:
            case AUTO_START_COMPONENTS_LABEL2:
                parameter.setValue(this.getAutoStartComponents());
                break;
            case DESCRIPTION_LABEL:
                parameter.setValue(this.getDescription());
                break;
            case PARAMETERS_LABEL:
                parameter.setValue(this.getParameters());
                break;
            default:
                break;
        }
    }

    public String getUri() {
        return uri;
    }

    public void setUri(final String uri) {
        this.uri = uri;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(final String pattern) {
        this.pattern = pattern;
    }

    public Integer getCacheSize() {
        return cacheSize;
    }

    public void setCacheSize(final Integer cacheSize) {
        this.cacheSize = cacheSize;
    }

    public Boolean getIgnoreInvalidEndpoints() {
        return ignoreInvalidEndpoints;
    }

    public void setIgnoreInvalidEndpoints(final Boolean ignoreInvalidEndpoints) {
        this.ignoreInvalidEndpoints = ignoreInvalidEndpoints;
    }

    public Boolean getAllowOptimisedComponents() {
        return allowOptimisedComponents;
    }

    public void setAllowOptimisedComponents(final Boolean allowOptimisedComponents) {
        this.allowOptimisedComponents = allowOptimisedComponents;
    }

    public Boolean getAutoStartComponents() {
        return autoStartComponents;
    }

    public void setAutoStartComponents(final Boolean autoStartComponents) {
        this.autoStartComponents = autoStartComponents;
    }

    public Map<String, String> getDescription() {
        return description;
    }

    public void setDescription(final Map<String, String> description) {
        this.description = description;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(final Map<String, Object> parameters) {
        this.parameters = parameters;
    }
}
