package io.kaoto.backend.camel.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kaoto.backend.camel.KamelPopulator;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.camel.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import java.util.List;
import java.util.Map;


public class WireTap extends EIPStep {
    public static final String COPY_LABEL = "copy";

    public static final String DYNAMIC_URI_LABEL = "dynamic-uri";
    public static final String DYNAMIC_URI_LABEL2 = "dynamicUri";

    public static final String ON_PREPARE_LABEL = "on-prepare";
    public static final String ON_PREPARE_LABEL2 = "OnPrepare";

    public static final String EXECUTOR_SERVICE_LABEL = "executor-service";
    public static final String EXECUTOR_SERVICE_LABEL2 = "executorService";

    public static final String PATTERN_LABEL = "pattern";

    public static final String CACHE_SIZE_LABEL = "cache-size";
    public static final String CACHE_SIZE_LABEL2 = "cacheSize";

    public static final String IGNORE_INVALID_ENDPOINT_LABEL = "ignore-invalid-endpoint";
    public static final String IGNORE_INVALID_ENDPOINT_LABEL2 = "ignoreInvalidEndpoint";

    public static final String ALLOW_OPTIMISED_COMPONENTS_LABEL = "allow-optimised-components";
    public static final String ALLOW_OPTIMISED_COMPONENTS_LABEL2 = "allowOptimisedComponents";

    public static final String AUTO_START_COMPONENTS_LABEL = "auto-start-components";
    public static final String AUTO_START_COMPONENTS_LABEL2 = "autoStartComponents";

    public static final String DESCRIPTION_LABEL = "description";

    public static final String PARAMETERS_LABEL = "parameters";
    public static final String URI_LABEL = "uri";

    private UriFlowStep uri;
    private Boolean copy;
    private Boolean dynamicUri;
    private Map<String, Object> onPrepare;
    private Map<String, Object> executorService;
    private String pattern;
    private Integer cacheSize;
    private Boolean ignoreInvalidEndpoint;
    private Boolean allowOptimisedComponents;
    private Boolean autoStartComponents;
    private Map<String, String> description;


    public WireTap() {
        //Needed for serialization
    }

    public WireTap(Step step, final KamelPopulator kamelPopulator) {
        super(step);

        if (step.getBranches() != null && !step.getBranches().isEmpty()) {
            var steps = kamelPopulator.processSteps(step.getBranches().get(0));
            if (!steps.isEmpty()) {
                var s = steps.get(0);
                if (s instanceof UriFlowStep uriFlowStep) {
                    setUri(uriFlowStep);
                } else if (s instanceof ToFlowStep toFlowStep) {
                    setUri((UriFlowStep) toFlowStep.getTo());
                } else {
                    setUri(new UriFlowStep());
                }
            }
        }
    }

    public WireTap(final @JsonProperty(COPY_LABEL) Boolean copy,
                   final @JsonProperty(DYNAMIC_URI_LABEL) Boolean dynamicUri,
                   final @JsonProperty(DYNAMIC_URI_LABEL2) Boolean dynamicUri2,
                   final @JsonProperty(PARAMETERS_LABEL) Map<String, Object> parameters,
                   final @JsonProperty(ON_PREPARE_LABEL) Map<String, Object> onPrepare,
                   final @JsonProperty(ON_PREPARE_LABEL2) Map<String, Object> onPrepare2,
                   final @JsonProperty(EXECUTOR_SERVICE_LABEL) Map<String, Object> executorService,
                   final @JsonProperty(EXECUTOR_SERVICE_LABEL2) Map<String, Object> executorService2,
                   final @JsonProperty(PATTERN_LABEL) String pattern,
                   final @JsonProperty(CACHE_SIZE_LABEL) Integer cacheSize,
                   final @JsonProperty(CACHE_SIZE_LABEL2) Integer cacheSize2,
                   final @JsonProperty(IGNORE_INVALID_ENDPOINT_LABEL) Boolean ignoreInvalidEndpoint,
                   final @JsonProperty(IGNORE_INVALID_ENDPOINT_LABEL2) Boolean ignoreInvalidEndpoint2,
                   final @JsonProperty(ALLOW_OPTIMISED_COMPONENTS_LABEL) Boolean allowOptimisedComponents,
                   final @JsonProperty(ALLOW_OPTIMISED_COMPONENTS_LABEL2) Boolean allowOptimisedComponents2,
                   final @JsonProperty(AUTO_START_COMPONENTS_LABEL) Boolean autoStartComponents,
                   final @JsonProperty(AUTO_START_COMPONENTS_LABEL2) Boolean autoStartComponents2,
                   final @JsonProperty(DESCRIPTION_LABEL) Map<String, String> description,
                   final @JsonProperty(URI_LABEL) String uri,
                   final @JsonProperty("id") String id) {
        super();
        setCopy(copy);
        setDynamicUri(dynamicUri != null ? dynamicUri : dynamicUri2);
        setOnPrepare(onPrepare != null ? onPrepare : onPrepare2);
        setExecutorService(executorService != null ? executorService : executorService2);
        setPattern(pattern);
        setCacheSize(cacheSize != null ? cacheSize : cacheSize2);
        setIgnoreInvalidEndpoint(ignoreInvalidEndpoint != null ? ignoreInvalidEndpoint : ignoreInvalidEndpoint2);
        setAllowOptimisedComponents(allowOptimisedComponents != null ? allowOptimisedComponents :
                allowOptimisedComponents2);
        setAutoStartComponents(autoStartComponents != null ? autoStartComponents : autoStartComponents2);
        setDescription(description);
        setUri(new UriFlowStep());
        getUri().setUri(uri);
        getUri().setParameters(parameters);
        setId(id);
    }

    @Override
    public void processBranches(final Step step, final StepCatalog catalog,
                                final KameletStepParserService kameletStepParserService) {
        step.setBranches(List.of(createBranch(URI_LABEL, List.of(this.getUri()), kameletStepParserService)));
    }

    @Override
    protected void assignAttribute(final Parameter parameter) {
        switch (parameter.getId()) {
            case COPY_LABEL:
                this.setCopy(Boolean.valueOf(String.valueOf(parameter.getValue())));
                break;
            case PARAMETERS_LABEL:
                this.getUri().setParameters((Map<String, Object>) parameter.getValue());
                break;
            case DYNAMIC_URI_LABEL:
            case DYNAMIC_URI_LABEL2:
                this.setDynamicUri(Boolean.valueOf(String.valueOf(parameter.getValue())));
                break;
            case ON_PREPARE_LABEL:
            case ON_PREPARE_LABEL2:
                this.setOnPrepare((Map<String, Object>) parameter.getValue());
                break;
            case EXECUTOR_SERVICE_LABEL:
            case EXECUTOR_SERVICE_LABEL2:
                this.setExecutorService((Map<String, Object>) parameter.getValue());
                break;
            case PATTERN_LABEL:
                this.setPattern(String.valueOf(parameter.getValue()));
                break;
            case CACHE_SIZE_LABEL:
            case CACHE_SIZE_LABEL2:
                this.setCacheSize(Integer.valueOf(String.valueOf(parameter.getValue())));
                break;
            case IGNORE_INVALID_ENDPOINT_LABEL:
            case IGNORE_INVALID_ENDPOINT_LABEL2:
                this.setIgnoreInvalidEndpoint(Boolean.valueOf(String.valueOf(parameter.getValue())));
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
            default:
                break;
        }
    }

    @Override
    protected void assignProperty(final Parameter parameter) {
        switch (parameter.getId()) {
            case COPY_LABEL:
                parameter.setValue(this.getCopy());
                break;
            case DYNAMIC_URI_LABEL:
            case DYNAMIC_URI_LABEL2:
                parameter.setValue(this.getDynamicUri());
                break;
            case ON_PREPARE_LABEL:
            case ON_PREPARE_LABEL2:
                parameter.setValue(this.getOnPrepare());
                break;
            case EXECUTOR_SERVICE_LABEL:
            case EXECUTOR_SERVICE_LABEL2:
                parameter.setValue(this.getExecutorService());
                break;
            case PATTERN_LABEL:
                parameter.setValue(this.getPattern());
                break;
            case CACHE_SIZE_LABEL:
            case CACHE_SIZE_LABEL2:
                parameter.setValue(this.getCacheSize());
                break;
            case IGNORE_INVALID_ENDPOINT_LABEL:
            case IGNORE_INVALID_ENDPOINT_LABEL2:
                parameter.setValue(this.getIgnoreInvalidEndpoint());
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
            default:
                break;
        }
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        var properties = this.getUri().getRepresenterProperties();
        if (this.getCopy() != null) {
            properties.put(COPY_LABEL, this.getCopy());
        }
        if (this.getDynamicUri() != null) {
            properties.put(DYNAMIC_URI_LABEL, this.getDynamicUri());
        }
        if (this.getOnPrepare() != null) {
            properties.put(ON_PREPARE_LABEL, this.getOnPrepare());
        }
        if (this.getExecutorService() != null) {
            properties.put(EXECUTOR_SERVICE_LABEL, this.getExecutorService());
        }
        if (this.getPattern() != null) {
            properties.put(PATTERN_LABEL, this.getPattern());
        }
        if (this.getCacheSize() != null) {
            properties.put(CACHE_SIZE_LABEL, this.getCacheSize());
        }
        if (this.getIgnoreInvalidEndpoint() != null) {
            properties.put(IGNORE_INVALID_ENDPOINT_LABEL, this.getIgnoreInvalidEndpoint());
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

    public Boolean getCopy() {
        return copy;
    }

    public void setCopy(final Boolean copy) {
        this.copy = copy;
    }

    public Boolean getDynamicUri() {
        return dynamicUri;
    }

    public void setDynamicUri(final Boolean dynamicUri) {
        this.dynamicUri = dynamicUri;
    }

    public Map<String, Object> getOnPrepare() {
        return onPrepare;
    }

    public void setOnPrepare(final Map<String, Object> onPrepare) {
        this.onPrepare = onPrepare;
    }

    public Map<String, Object> getExecutorService() {
        return executorService;
    }

    public void setExecutorService(final Map<String, Object> executorService) {
        this.executorService = executorService;
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

    public Boolean getIgnoreInvalidEndpoint() {
        return ignoreInvalidEndpoint;
    }

    public void setIgnoreInvalidEndpoint(final Boolean ignoreInvalidEndpoint) {
        this.ignoreInvalidEndpoint = ignoreInvalidEndpoint;
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

    public UriFlowStep getUri() {
        if (uri == null) {
            setUri(new UriFlowStep());
        }
        return uri;
    }

    public void setUri(final UriFlowStep uri) {
        this.uri = uri;
    }
}
