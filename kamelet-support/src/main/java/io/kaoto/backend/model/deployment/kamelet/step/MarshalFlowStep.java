package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.dataformat.DataFormat;
import io.kaoto.backend.model.deployment.kamelet.step.marshal.MarshalDeserializer;
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
        properties.put(dataFormat.getFormat(), dataFormat.getProperties());
        return step;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final Boolean start, final Boolean end) {
        Step res = catalog.getReadOnlyCatalog().searchByID("marshal");
        assignParameters(res);
        return res;
    }

    protected void assignParameters(final Step res) {
        for (var param : res.getParameters()) {
            if ("dataformat".equalsIgnoreCase(param.getId())) {
                param.setValue(this.getDataFormat().getFormat());
            } else if ("properties".equalsIgnoreCase(param.getId())
                            && this.getDataFormat().getProperties() != null) {
                Object[] array = new Object[this.getDataFormat().getProperties().size()];
                int i = 0;
                for (var entry : this.getDataFormat().getProperties().entrySet()) {
                    array[i++] = new Object[]{entry.getKey(), entry.getValue()};
                }
                param.setValue(array);
            }
        }
    }

    @JsonIgnore
    public DataFormat getDataFormat() {
        return dataFormat;
    }

    public void setDataFormat(
            final DataFormat dataFormat) {
        this.dataFormat = dataFormat;
    }
}
