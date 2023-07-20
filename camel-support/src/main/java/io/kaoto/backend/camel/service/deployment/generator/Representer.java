package io.kaoto.backend.camel.service.deployment.generator;

import java.util.Map;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Represent;

import com.fasterxml.jackson.core.type.TypeReference;

import io.kaoto.backend.camel.KamelHelper;

public class Representer extends org.yaml.snakeyaml.representer.Representer {


    public Representer(DumperOptions options) {
        super(options);
    }

    protected Represent bean() {
        return new RepresentMap() {
            @Override
            public Node representData(final Object data) {
                Map<String, Object> properties = KamelHelper.JSON_MAPPER.convertValue(data,
                        new TypeReference<Map<String, Object>>() {});
                return representMapping(getTag(data.getClass(), Tag.MAP),
                        properties,
                        DumperOptions.FlowStyle.AUTO);
            }
        };
    }
}
