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
           final @JsonProperty(value = "uri", required = true) String uri) {
        super();
        setUri(uri);
    }

    @JsonProperty("uri")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String uri;

    @JsonProperty("parameters")
    private Map<String, String> parameters;

    public UriFlowStep() {
    }

    public UriFlowStep(final String uri, final Map<String, String> params) {
        setUri(uri);
        setParameters(params);
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
        String uri = this.getUri();
        String connectorName = uri;

        if (uri != null
                && uri.contains(":")
                && !uri.startsWith("kamelet:")) {
            connectorName = uri.substring(0, uri.indexOf(':'));
        }

        Optional<Step> res = catalog.getReadOnlyCatalog()
                .searchByName(connectorName).stream()
                .findAny();


        if (res.isPresent() && uri != null) {
            kameletStepParserService.setValuesOnParameters(res.get(), uri);
            kameletStepParserService.setValuesOnParameters(res.get(),
                    this.getParameters());
        }

        return res.get();
    }
}
