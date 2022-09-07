package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.dataformat.DataFormat;
import io.kaoto.backend.model.deployment.kamelet.step.marshal.MarshalDeserializer;
import io.kaoto.backend.model.step.Step;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.ScalarNode;

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

    }

    public MarshalFlowStep(final Node value) {
        if (value instanceof MappingNode mappingNode) {
            for (var df : mappingNode.getValue()) {
                if (df.getKeyNode() instanceof ScalarNode key) {
                    extractDataFormat(df, key);
                }
            }
        }
    }

    private void extractDataFormat(final NodeTuple df, final ScalarNode key) {
        this.setDataFormat(new DataFormat());
        this.getDataFormat().setFormat(key.getValue());

        if (df.getValueNode()
                instanceof MappingNode props) {
            this.getDataFormat().setProperties(new HashMap<>());
            for (var property : props.getValue()) {
                if (property.getKeyNode() instanceof ScalarNode k
                        && property.getValueNode()
                        instanceof ScalarNode v) {
                    this.getDataFormat().getProperties().put(
                            k.getValue(),
                            v.getValue());
                }
            }
        }
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
    public Step getStep(final StepCatalog catalog,
                        final KameletStepParserService
                                kameletStepParserService) {
        Step res = catalog.getReadOnlyCatalog().searchByID("marshal");

        for (var param : res.getParameters()) {
            if ("dataformat".equalsIgnoreCase(param.getId())) {
                param.setValue(this.getDataFormat().getFormat());
            } else if ("properties".equalsIgnoreCase(param.getId())
                            && this.getDataFormat().getProperties() != null) {
                param.setValue(this.getDataFormat()
                        .getProperties().entrySet().toArray());
            }
        }

        return res;
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
