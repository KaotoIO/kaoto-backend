package io.kaoto.backend.camel.service.step.parser.camelroute;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.type.CollectionType;

import io.kaoto.backend.camel.model.deployment.camelroute.CamelRoute;
import io.kaoto.backend.camel.model.deployment.kamelet.Bean;
import io.kaoto.backend.camel.model.deployment.kamelet.Flow;

public class CamelRouteDeserializer extends StdDeserializer<CamelRoute> {

    protected CamelRouteDeserializer() {
        this(null);
    }

    protected CamelRouteDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public CamelRoute deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        CamelRoute answer = new CamelRoute();
        TreeNode root = p.getCodec().readTree(p);
        if (!(root instanceof ArrayNode)) {
            throw new IOException(
                    "Camel Route is expected to have a topmost array, but detected " + root.getClass().getName());
        }
        Iterator<JsonNode> fields = ((ArrayNode) root).elements();
        while (fields.hasNext()) {
            var field = fields.next();
            if (field.has("route")) {
                field = field.get("route");
            }

            if (field.has("from") || field.has("rest")) {
                Flow flow = ctxt.readTreeAsValue(field, Flow.class);
                if (answer.getFlows() == null) {
                    answer.setFlows(new ArrayList<>(1));
                }
                answer.getFlows().add(flow);
            } else if (field.has("beans")) {
                CollectionType beansType = ctxt.getTypeFactory().constructCollectionType(List.class, Bean.class);
                List<Bean> beans = ctxt.readTreeAsValue(field.get("beans"), beansType);
                if (answer.getBeans() == null) {
                    answer.setBeans(new ArrayList<>(beans.size()));
                }
                answer.getBeans().addAll(beans);
            } else if (field.has("routeConfiguration") || field.has("route-configuration")) {
                JsonNode routeConfig = Objects.requireNonNullElse(
                        field.get("routeConfiguration"), field.get("route-configuration"));
                Object config = ctxt.readTreeAsValue(routeConfig, Object.class);
                if (answer.getRouteConfiguration() == null) {
                    answer.setRouteConfiguration(config);
                }
            } else if (field.has("restConfiguration") || field.has("rest-configuration")) {
                JsonNode restConfig = Objects.requireNonNullElse(field.get("restConfiguration"),
                        field.get("rest-configuration"));
                Object config = ctxt.readTreeAsValue(restConfig, Object.class);
                if (answer.getRestConfiguration() == null) {
                    answer.setRestConfiguration(config);
                }
            } else {
                throw new IOException("'" + field.fields().next().getKey()
                        + "' is not supported as a root element of Camel Route");
            }
        }
        return answer;
    }
}
