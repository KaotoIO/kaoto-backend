package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import java.util.HashMap;
import java.util.Map;


public class Sample extends EIPStep {

    public static final String SAMPLE_PERIOD_LABEL = "sample-period";
    public static final String SAMPLE_PERIOD_LABEL2 = "samplePeriod";

    public static final String MESSAGE_FREQUENCY_LABEL = "message-frequency";
    public static final String MESSAGE_FREQUENCY_LABEL2 = "messageFrequency";

    public static final String DESCRIPTION_LABEL = "description";

    private String samplePeriod;

    private Long messageFrequency;

    private Map<String, String> description;


    public Sample() {
         //Needed for serialization
    }

    public Sample(final @JsonProperty(SAMPLE_PERIOD_LABEL) String samplePeriod,
                  final @JsonProperty(SAMPLE_PERIOD_LABEL2) String samplePeriod2,
                  final @JsonProperty(MESSAGE_FREQUENCY_LABEL)  Long messageFrequency,
                  final @JsonProperty(MESSAGE_FREQUENCY_LABEL2) Long messageFrequency2,
                  final @JsonProperty(DESCRIPTION_LABEL) Map<String, String> description) {
        setDescription(description);
        setSamplePeriod(samplePeriod != null ? samplePeriod : samplePeriod2);
        setMessageFrequency(messageFrequency != null ? messageFrequency : messageFrequency2);
    }

    public Sample(Step step) {
        super(step);
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new HashMap<>();
        if (this.getSamplePeriod() != null) {
            properties.put(SAMPLE_PERIOD_LABEL, this.getSamplePeriod());
        }

        if (this.getMessageFrequency() != null) {
            properties.put(MESSAGE_FREQUENCY_LABEL, this.getMessageFrequency());
        }

        if (this.description != null) {
            properties.put(DESCRIPTION_LABEL, this.description);
        }

        return properties;
    }

    @Override
    protected void assignProperty(final Parameter parameter) {
        switch (parameter.getId()) {
            case SAMPLE_PERIOD_LABEL:
            case SAMPLE_PERIOD_LABEL2:
                parameter.setValue(this.getSamplePeriod());
                break;
            case MESSAGE_FREQUENCY_LABEL:
            case MESSAGE_FREQUENCY_LABEL2:
                parameter.setValue(this.getMessageFrequency());
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
            case SAMPLE_PERIOD_LABEL:
            case SAMPLE_PERIOD_LABEL2:
                this.setSamplePeriod(String.valueOf(parameter.getValue()));
                break;
            case MESSAGE_FREQUENCY_LABEL:
            case MESSAGE_FREQUENCY_LABEL2:
                this.setMessageFrequency(Long.valueOf(String.valueOf(parameter.getValue())));
                break;
            case DESCRIPTION_LABEL:
                this.setDescription((Map) parameter.getValue());
                break;
            default:
                break;
        }
    }

    public String getSamplePeriod() {
        return samplePeriod;
    }

    public void setSamplePeriod(final String samplePeriod) {
        this.samplePeriod = samplePeriod;
    }

    public Long getMessageFrequency() {
        return messageFrequency;
    }

    public void setMessageFrequency(final Long messageFrequency) {
        this.messageFrequency = messageFrequency;
    }

    public Map<String, String> getDescription() {
        return description;
    }

    public void setDescription(final Map<String, String> description) {
        this.description = description;
    }
}
