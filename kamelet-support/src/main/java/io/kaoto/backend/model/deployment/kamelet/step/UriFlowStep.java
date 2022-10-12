package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.deployment.generator.kamelet.KameletRepresenter;
import io.kaoto.backend.api.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.model.step.Step;

import java.io.Serial;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@JsonPropertyOrder({"uri", "parameters"})
@JsonDeserialize(
        using = JsonDeserializer.None.class
)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UriFlowStep implements FlowStep {
    @Serial
    private static final long serialVersionUID = 3379417696583645440L;

    @JsonCreator
    public UriFlowStep(
            final @JsonProperty(value = "uri") String uri,
            final @JsonProperty(value = "parameters") Map<String, String> parameters) {
        super();
        setUri(uri);
        setParameters(parameters);
    }

    @JsonProperty("uri")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String uri;

    @JsonProperty("parameters")
    private Map<String, String> parameters;

    public UriFlowStep() {
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(final Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(final String uri) {
        this.uri = uri;
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(KameletRepresenter.URI, this.getUri());
        if (this.getParameters() != null
                && !this.getParameters().isEmpty()) {
            properties.put(KameletRepresenter.PARAMETERS, this.getParameters());
        }
        return properties;
    }


    @Override
    public Step getStep(final StepCatalog catalog,
                        final KameletStepParserService
                                kameletStepParserService) {
        String connectorName = this.getUri();

        if (this.getUri() != null
                && this.getUri().contains(":")
                && !this.getUri().startsWith("kamelet:")) {
            connectorName = this.getUri().substring(0, this.getUri().indexOf(':'));
        }

        Optional<Step> res = catalog.getReadOnlyCatalog()
                .searchByName(connectorName).stream()
                .findAny();


        if (res.isPresent() && this.getUri() != null) {
            kameletStepParserService.setValuesOnParameters(res.get(), this.getUri());
            kameletStepParserService.setValuesOnParameters(res.get(),
                    this.getParameters());
        }

        return res.get();
    }
}
