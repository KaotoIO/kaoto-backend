package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;

import java.io.Serial;
import java.util.Map;

@JsonPropertyOrder({"uri", "parameters"})
@JsonDeserialize(
        using = JsonDeserializer.None.class
)
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
}
