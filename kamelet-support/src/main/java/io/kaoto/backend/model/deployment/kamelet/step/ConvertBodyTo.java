package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import java.util.LinkedHashMap;
import java.util.Map;


public class ConvertBodyTo extends EIPStep {

    public static final String TYPE_LABEL = "type";
    public static final String MANDATORY_LABEL = "mandatory";
    public static final String CHARSET_LABEL = "charset";
    public static final String DESCRIPTION_LABEL = "description";

    @JsonProperty(TYPE_LABEL)
    private String type;

    @JsonProperty(MANDATORY_LABEL)
    private Boolean mandatory;

    @JsonProperty(CHARSET_LABEL)
    private String charset;

    @JsonProperty(DESCRIPTION_LABEL)
    private String description;


    public ConvertBodyTo() {
         //Needed for serialization
    }

    public ConvertBodyTo(Step step) {
        super(step);
    }

    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new LinkedHashMap<>();
        if (this.type != null) {
            properties.put(TYPE_LABEL, this.type);
        }
        if (this.mandatory != null) {
            properties.put(MANDATORY_LABEL, this.mandatory);
        }
        if (this.charset != null) {
            properties.put(CHARSET_LABEL, this.charset);
        }
        if (this.description != null) {
            properties.put(DESCRIPTION_LABEL, this.description);
        }
        return properties;
    }


    @Override
    void assignAttribute(final Parameter parameter) {
        switch (parameter.getId()) {
            case TYPE_LABEL:
                this.setType(parameter.getValue().toString());
                break;
            case MANDATORY_LABEL:
                this.setMandatory(Boolean.valueOf(parameter.getValue().toString()));
                break;
            case DESCRIPTION_LABEL:
                this.setDescription(parameter.getValue().toString());
                break;
            case CHARSET_LABEL:
                this.setCharset(parameter.getValue().toString());
                break;
            default:
                log.error("Unknown property: " + parameter.getId());
                break;
        }
    }

    @Override
    void assignProperty(final Parameter parameter) {
        switch (parameter.getId()) {
            case TYPE_LABEL:
                parameter.setValue(this.type);
                break;
            case MANDATORY_LABEL:
                parameter.setValue(this.mandatory);
                break;
            case CHARSET_LABEL:
                parameter.setValue(this.charset);
                break;
            case DESCRIPTION_LABEL:
                parameter.setValue(this.description);
                break;
            default:
                log.error("Unknown property: " + parameter.getId());
                break;
        }
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public Boolean getMandatory() {
        return mandatory;
    }

    public void setMandatory(final Boolean mandatory) {
        this.mandatory = mandatory;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(final String charset) {
        this.charset = charset;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}
