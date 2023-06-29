package io.kaoto.backend.api.service.step.parser.camelroute;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.kaoto.backend.model.deployment.kamelet.Flow;
import io.kaoto.backend.model.deployment.kamelet.step.From;
import io.kaoto.backend.model.deployment.rest.Rest;

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
        if (((ObjectNode)root).has("route")) {
            var route = ((ObjectNode)root).get("route");
            if (route.has("id")) {
                flow.setId(route.get("id").asText());
            }
            if (route.has("route-configuration-id")) {
                flow.setRouteConfigurationId(route.get("route-configuration-id").asText());
            }
            if (route.has("description")) {
                flow.setDescription(route.get("description").asText());
            }
            if (route.has("from")) {
                var from = ctxt.readTreeAsValue(route.get("from"), From.class);
                flow.setFrom(from);
            }
        }
        if (((ObjectNode)root).has("from")) {
            var from = ctxt.readTreeAsValue((JsonNode)root.get("from"), From.class);
            flow.setFrom(from);
        }
        if (((ObjectNode)root).has("rest")) {
            var rest = ctxt.readTreeAsValue((JsonNode)root.get("rest"), Rest.class);
            flow.setRest(rest);
        }
        return flow;
    }
}
