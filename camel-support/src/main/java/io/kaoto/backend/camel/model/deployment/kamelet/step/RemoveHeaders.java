package io.kaoto.backend.camel.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.kaoto.backend.camel.KamelHelper;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import java.util.Map;


public class RemoveHeaders extends EIPStep {

    public static final String PATTERN_LABEL = "pattern";

    public static final String EXCLUDE_PATTERN_LABEL = "exclude-pattern";
    public static final String EXCLUDE_PATTERN_LABEL2 = "excludePattern";

    @JsonProperty(PATTERN_LABEL)
    private String pattern;

    @JsonProperty(EXCLUDE_PATTERN_LABEL)
    @JsonAlias(EXCLUDE_PATTERN_LABEL2)
    private String excludePattern;

    @JsonProperty(KamelHelper.DESCRIPTION)
    private Map<String, String> description;


    public RemoveHeaders() {
         //Needed for serialization
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
            properties.put(KamelHelper.DESCRIPTION, this.description);
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
            case KamelHelper.DESCRIPTION:
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
            case KamelHelper.DESCRIPTION:
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
