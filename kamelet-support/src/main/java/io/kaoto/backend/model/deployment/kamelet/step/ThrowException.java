package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import java.util.Map;


public class ThrowException extends EIPStep {

    public static final String EXCEPTION_TYPE_LABEL = "exception-type";
    public static final String EXCEPTION_TYPE_LABEL2 = "exceptionType";
    public static final String MESSAGE_LABEL = "message";

    private String exceptionType;
    private String message;


    public ThrowException() {
        //Needed for serialization
    }

    public ThrowException(Step step) {
        super(step);
    }

    public ThrowException(final @JsonProperty(EXCEPTION_TYPE_LABEL) String exceptionType,
                          final @JsonProperty(EXCEPTION_TYPE_LABEL2) String exceptionType2,
                          final @JsonProperty(MESSAGE_LABEL) String message,
                          final @JsonProperty("id") String id) {
        super();
        setExceptionType(exceptionType != null ? exceptionType : exceptionType2);
        setMessage(message);
        setId(id);
    }

    @Override
    protected void assignAttribute(final Parameter parameter) {
        switch (parameter.getId()) {
            case EXCEPTION_TYPE_LABEL:
            case EXCEPTION_TYPE_LABEL2:
                this.setExceptionType(String.valueOf(parameter.getValue()));
                break;
            case MESSAGE_LABEL:
                this.setMessage(String.valueOf(parameter.getValue()));
                break;
            default:
                break;
        }
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        var properties = super.getDefaultRepresenterProperties();
        if (this.getMessage() != null) {
            properties.put(MESSAGE_LABEL, this.getMessage());
        }
        if (this.getExceptionType() != null) {
            properties.put(EXCEPTION_TYPE_LABEL, this.getExceptionType());
        }
        return properties;
    }

    @Override
    protected void assignProperty(final Parameter parameter) {
        switch (parameter.getId()) {
            case EXCEPTION_TYPE_LABEL:
            case EXCEPTION_TYPE_LABEL2:
                parameter.setValue(this.getExceptionType());
                break;
            case MESSAGE_LABEL:
                parameter.setValue(this.getMessage());
                break;
            default:
                break;
        }
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(final String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}
