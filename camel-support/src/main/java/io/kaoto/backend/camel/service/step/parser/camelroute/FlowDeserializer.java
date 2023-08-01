package io.kaoto.backend.camel.service.step.parser.camelroute;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.CollectionType;
import io.kaoto.backend.camel.KamelHelper;
import io.kaoto.backend.camel.model.deployment.kamelet.Bean;
import io.kaoto.backend.camel.model.deployment.kamelet.Flow;
import io.kaoto.backend.camel.model.deployment.kamelet.step.From;
import io.kaoto.backend.camel.model.deployment.rest.Rest;

import java.io.IOException;
import java.util.List;

public class FlowDeserializer extends StdDeserializer<Flow> {
    protected FlowDeserializer() {
        this(null);
    }

    protected FlowDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Flow deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        Flow flow = new Flow();
        var root = p.getCodec().readTree(p);
        if (((ObjectNode) root).has("route")) {
            root = ((ObjectNode) root).get("route");
        }
        if (((ObjectNode) root).has("id")) {
            flow.setId(((ObjectNode) root).get("id").asText());
        }
        if (((ObjectNode) root).has("route-configuration-id")) {
            flow.setRouteConfigurationId(((ObjectNode) root).get("route-configuration-id").asText());
        }
        if (((ObjectNode) root).has(KamelHelper.DESCRIPTION)) {
            flow.setDescription(((ObjectNode) root).get(KamelHelper.DESCRIPTION).asText());
        }
        if (((ObjectNode) root).has("from")) {
            var from = ctxt.readTreeAsValue((JsonNode) root.get("from"), From.class);
            flow.setFrom(from);
        }
        if (((ObjectNode) root).has("rest")) {
            var rest = ctxt.readTreeAsValue((JsonNode) root.get("rest"), Rest.class);
            flow.setRest(rest);
        }
        if (((ObjectNode) root).has("beans")) {
            CollectionType beansType = ctxt.getTypeFactory().constructCollectionType(List.class, Bean.class);
            List<Bean> beans = ctxt.readTreeAsValue((JsonNode) root.get("beans"), beansType);
            flow.setBeans(beans);
        }
        return flow;
    }
}
