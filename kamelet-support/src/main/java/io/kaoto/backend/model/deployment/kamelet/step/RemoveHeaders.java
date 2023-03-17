package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import java.util.Map;


public class RemoveHeaders extends EIPStep {

    public static final String PATTERN_LABEL = "pattern";

    public static final String EXCLUDE_PATTERN_LABEL = "exclude-pattern";
    public static final String EXCLUDE_PATTERN_LABEL2 = "excludePattern";

    public static final String DESCRIPTION_LABEL = "description";

    private String pattern;

    private String excludePattern;

    private Map<String, String> description;


    public RemoveHeaders() {
         //Needed for serialization
    }

    public RemoveHeaders(final @JsonProperty(PATTERN_LABEL) String pattern,
                         final @JsonProperty(EXCLUDE_PATTERN_LABEL)  String excludePattern,
                         final @JsonProperty(EXCLUDE_PATTERN_LABEL2) String excludePattern2,
                         final @JsonProperty(DESCRIPTION_LABEL) Map<String, String> description,
                         final @JsonProperty("id") String id) {
        setPattern(pattern);
        setExcludePattern(excludePattern != null ? excludePattern : excludePattern2);
        setDescription(description);
        setId(id);
    }

    public RemoveHeaders(Step step) {
        super(step);
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = super.getDefaultRepresenterProperties();
        if (this.getPattern() != null) {
            properties.put(PATTERN_LABEL, this.getPattern());
        }

        if (this.getExcludePattern() != null) {
            properties.put(EXCLUDE_PATTERN_LABEL, this.getExcludePattern());
        }

        if (this.description != null) {
            properties.put(DESCRIPTION_LABEL, this.description);
        }
        return properties;
    }

    @Override
    protected void assignProperty(final Parameter parameter) {
        switch (parameter.getId()) {
            case PATTERN_LABEL:
                parameter.setValue(this.getPattern());
                break;
            case EXCLUDE_PATTERN_LABEL:
            case EXCLUDE_PATTERN_LABEL2:
                parameter.setValue(this.getExcludePattern());
                break;
            case DESCRIPTION_LABEL:
                parameter.setValue(this.getDescription());
                break;
            default:
                break;
        }
    }


    @Override
    protected void assignAttribute(final Parameter parameter) {
        switch (parameter.getId()) {
            case PATTERN_LABEL:
                this.setPattern(String.valueOf(parameter.getValue()));
                break;
            case EXCLUDE_PATTERN_LABEL:
            case EXCLUDE_PATTERN_LABEL2:
                this.setExcludePattern(String.valueOf(parameter.getValue()));
                break;
            case DESCRIPTION_LABEL:
                this.setDescription((Map) parameter.getValue());
                break;
            default:
                break;
        }
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(final String pattern) {
        this.pattern = pattern;
    }

    public String getExcludePattern() {
        return excludePattern;
    }

    public void setExcludePattern(final String excludePattern) {
        this.excludePattern = excludePattern;
    }

    public Map<String, String> getDescription() {
        return description;
    }

    public void setDescription(final Map<String, String> description) {
        this.description = description;
    }
}
