package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.step.Step;
import org.jboss.logging.Logger;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;


@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConvertBodyTo implements Serializable {

    private static final Logger log = Logger.getLogger(ConvertBodyTo.class);
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

        for (var parameter : step.getParameters()) {
            if (parameter.getValue() != null) {
                try {
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
                } catch (Exception e) {
                    log.error("Couldn't assign value to parameter " + parameter.getId(), e);
                }
            }
        }
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

    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService) {

        Optional<Step> res = catalog.getReadOnlyCatalog()
                .searchByName("convert-body-to").stream()
                .filter(step -> step.getKind().equalsIgnoreCase("EIP"))
                .findAny();


        if (res.isPresent()) {
            var step = res.get();
            for (var parameter : step.getParameters()) {
                try {
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
                } catch (Exception e) {
                    log.error("Couldn't assign value to parameter " + parameter.getId(), e);
                }
            }

        }

        return res.orElse(null);
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
