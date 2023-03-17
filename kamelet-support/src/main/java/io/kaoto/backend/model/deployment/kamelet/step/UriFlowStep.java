package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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

@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UriFlowStep implements FlowStep {
    @Serial
    private static final long serialVersionUID = 3379417696583645440L;
    public static final String ID = "id";
    public static final String PARAMETERS = "parameters";
    public static final String URI = "uri";

    @JsonCreator
    public UriFlowStep(
            final @JsonProperty(value = URI) String uri,
            final @JsonProperty(value = PARAMETERS) Map<String, String> parameters,
            final @JsonProperty(value = ID) String id) {
        super();
        setUri(uri);
        setParameters(parameters);
        setId(id);
    }

    @JsonProperty(URI)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String uri;

    @JsonProperty(PARAMETERS)
    private Map<String, String> parameters;

    @JsonProperty(ID)
    private String id;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(KameletRepresenter.URI, this.getUri());
        if (this.getParameters() != null
                && !this.getParameters().isEmpty()) {
            properties.put(KameletRepresenter.PARAMETERS, this.getParameters());
        }
        if (this.getId() != null) {
            properties.put(ID, this.getId());
        }
        return properties;
    }


    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final Boolean start, final Boolean end) {
        String connectorName = this.getUri();

        if (this.getUri() != null
                && this.getUri().contains(":")
                && !this.getUri().startsWith("kamelet:")) {
            connectorName = this.getUri().substring(0, this.getUri().indexOf(':'));
        }

        var candidates = catalog.getReadOnlyCatalog().searchByName(connectorName).stream();

        //Make sure we do the smartest pick: don't put an end step at the beginning or a start at the end
        //unless there is no other option, sure, then whatever the user is doing
        if (start) {
            candidates = candidates.sorted((step, step2) -> {
                var type = Step.Type.valueOf(step.getType());
                var type2 = Step.Type.valueOf(step2.getType());
                return type.compareTo(type2);
            });
        } else {
            candidates = candidates.sorted((step, step2) -> {
                var type = Step.Type.valueOf(step.getType());
                var type2 = Step.Type.valueOf(step2.getType());
                if (type.equals(type2)) {
                    return 0;
                }

                //EIPs shouldn't go through this function
                if (step.getKind().startsWith("EIP")) {
                    return 10000;
                }

                if (type == Step.Type.MIDDLE) {
                    return -1;
                }
                if (type2 == Step.Type.MIDDLE) {
                    return 1;
                }

                if (type == Step.Type.START) {
                    return -1;
                }
                if (type2 == Step.Type.START) {
                    return 1;
                }
                return 0;
            });
        }

        Optional<Step> res = candidates.findFirst();

        if (res.isPresent() && this.getUri() != null) {
            kameletStepParserService.setValuesOnParameters(res.get(), this.getUri());
            kameletStepParserService.setValuesOnParameters(res.get(), this.getParameters());
            res.get().setStepId(id);
        }

        return res.orElse(null);
    }
}
