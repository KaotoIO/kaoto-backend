package io.kaoto.backend.api.service.deployment.generator.camelroute;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.kaoto.backend.api.service.deployment.generator.kamelet.KameletRepresenter;
import io.kaoto.backend.model.deployment.kamelet.Flow;
import io.kaoto.backend.model.deployment.camelroute.IntegrationSpec;
import io.kaoto.backend.model.deployment.rest.Rest;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.Tag;

import java.util.LinkedHashMap;
import java.util.Map;

public class IntegrationRepresenter extends KameletRepresenter {


    public IntegrationRepresenter() {
        super();
        spec();
    }

    private void spec() {
        this.multiRepresenters.put(IntegrationSpec.class,
                new RepresentMap() {
                    @Override
                    public Node representData(final Object data) {
                        ObjectMapper mapper = new ObjectMapper();
                        Map<String, Object> properties = mapper.convertValue(data,
                                new TypeReference<Map<String, Object>>() {});
                        properties.put("flows", ((IntegrationSpec) data).get_flows());
                        properties.remove("_flows");
                        return representMapping(getTag(data.getClass(), Tag.MAP),
                                properties,
                                DumperOptions.FlowStyle.AUTO);
                    }
                });
        this.multiRepresenters.put(Flow.class,
                new RepresentMap() {
                    @Override
                    public Node representData(final Object data) {
                        Map<String, Object> properties = new LinkedHashMap<>();
                        Flow flow = (Flow) data;
                        if (flow.getFrom() instanceof Rest) {
                            properties.put("rest", flow.getFrom());
                        } else {
                            properties.put("from", flow.getFrom());
                        }
                        return representMapping(getTag(data.getClass(), Tag.MAP),
                                properties,
                                DumperOptions.FlowStyle.AUTO);
                    }
                });
    }

}
