package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.model.step.Step;
import org.jboss.logging.Logger;

import java.io.Serial;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;


@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LogStep implements FlowStep {
    @Serial
    private static final long serialVersionUID = 854654615436852L;
    private static final Logger log = Logger.getLogger(LogStep.class);

    private String message;

    @JsonProperty("logging-level")
    private String loggingLevel;

    @JsonProperty("log-name")
    private String logName;
    private String marker;
    private String logger;
    private String description;

    public LogStep() {
    }

    public LogStep(Step step) {
        for (var parameter : step.getParameters()) {
            if (parameter.getValue() != null) {
                try {
                    switch (parameter.getId()) {
                        case "message":
                            this.setMessage(parameter.getValue().toString());
                            break;
                        case "loggingLevel":
                        case "logging-level":
                            this.setLoggingLevel(parameter.getValue().toString());
                            break;
                        case "logName":
                        case "log-name":
                            this.setLogName(parameter.getValue().toString());
                            break;
                        case "marker":
                            this.setMarker(parameter.getValue().toString());
                            break;
                        case "logger":
                            this.setLogger(parameter.getValue().toString());
                            break;
                        case "description":
                            this.setDescription(parameter.getValue().toString());
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

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new LinkedHashMap<>();
        if (this.message != null) {
            properties.put("message", this.getMessage());
        }
        if (this.loggingLevel != null) {
            properties.put("logging-level", this.getLoggingLevel());
        }
        if (this.logName != null) {
            properties.put("log-name", this.getLogName());
        }
        if (this.marker != null) {
            properties.put("marker", this.getMarker());
        }
        if (this.logger != null) {
            properties.put("logger", this.getLogger());
        }
        if (this.description != null) {
            properties.put("description", this.getDescription());
        }
        return properties;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService) {

        Optional<Step> res = catalog.getReadOnlyCatalog()
                .searchByName("log").stream()
                .filter(step -> step.getKind().equalsIgnoreCase("EIP"))
                .findAny();


        if (res.isPresent()) {
            var step = res.get();
            for (var parameter : step.getParameters()) {
                try {
                    switch (parameter.getId()) {
                        case "message":
                            parameter.setValue(this.getMessage());
                            break;
                        case "loggingLevel":
                            parameter.setValue(this.getLoggingLevel());
                            break;
                        case "logName":
                            parameter.setValue(this.getLogName());
                            break;
                        case "marker":
                            parameter.setValue(this.getMarker());
                            break;
                        case "logger":
                            parameter.setValue(this.getLogger());
                            break;
                        case "description":
                            parameter.setValue(this.getDescription());
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

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String getLoggingLevel() {
        return loggingLevel;
    }

    public void setLoggingLevel(final String loggingLevel) {
        this.loggingLevel = loggingLevel;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(final String logName) {
        this.logName = logName;
    }

    public String getMarker() {
        return marker;
    }

    public void setMarker(final String marker) {
        this.marker = marker;
    }

    public String getLogger() {
        return logger;
    }

    public void setLogger(final String logger) {
        this.logger = logger;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}
