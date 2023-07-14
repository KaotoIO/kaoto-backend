package io.kaoto.backend.camel.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.camel.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.camel.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.camel.model.deployment.kamelet.step.dataformat.DataFormat;
import io.kaoto.backend.camel.model.deployment.kamelet.step.marshal.MarshalDeserializer;
import io.kaoto.backend.model.step.Step;

import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

@JsonDeserialize(
        using = MarshalDeserializer.class
)
public class MarshalFlowStep implements FlowStep {
    @Serial
    private static final long serialVersionUID = 4817060513623828129L;

    private DataFormat dataFormat;

    public MarshalFlowStep() {
        //Needed for the serialization
    }
    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> step = new HashMap<>();
        Map<String, Object> properties = new HashMap<>();
        step.put("marshal", properties);
        if (dataFormat != null) {
            properties.put(dataFormat.getFormat(), dataFormat.getProperties());
        }
        return step;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final boolean start, final boolean end) {
        Step res = catalog.getReadOnlyCatalog().searchByName("marshal")
                .stream().filter(s -> s.getKind().equalsIgnoreCase("EIP")).findAny().orElse(null);
        if (res != null) {
            assignParameters(res);
        }
        return res;
    }

    protected void assignParameters(final Step res) {
        for (var param : res.getParameters()) {
            if ("dataformat".equalsIgnoreCase(param.getId())) {
                param.setValue(this.getDataFormat());
            }
        }
    }

    @JsonIgnore
    public DataFormat getDataFormat() {
        return dataFormat;
    }

    public void setDataFormat(final DataFormat dataFormat) {
        this.dataFormat = dataFormat;
    }
}
