package io.kaoto.backend.camel.service.deployment.generator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Represent;

import java.util.Map;

public class Representer extends org.yaml.snakeyaml.representer.Representer {


    public Representer(DumperOptions options) {
        super(options);
    }

    protected Represent bean() {
        return new RepresentMap() {
            @Override
            public Node representData(final Object data) {
                ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> properties = mapper.convertValue(data,
                        new TypeReference<Map<String, Object>>() {});
                return representMapping(getTag(data.getClass(), Tag.MAP),
                        properties,
                        DumperOptions.FlowStyle.AUTO);
            }
        };
    }
}
