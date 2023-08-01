package io.kaoto.backend.camel.model.deployment.kamelet.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.kaoto.backend.camel.KamelHelper;
import io.kaoto.backend.camel.model.deployment.kamelet.Flow;
import io.kaoto.backend.camel.model.deployment.rest.Rest;
import io.quarkus.runtime.util.StringUtil;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class FlowSerializer extends StdSerializer<Flow> {

    public FlowSerializer() {
        super(Flow.class);
    }

    @Override
    public void serialize(Flow flow, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeObject(getRouteProperties(flow, new LinkedHashMap<>()));
    }

    public static Map<String, Object> getRouteProperties(final Flow flow, final Map<String, Object> routeProperties) {

        if (!StringUtil.isNullOrEmpty(flow.getId())
                || !StringUtil.isNullOrEmpty(flow.getRouteConfigurationId())) {
            //properties2 helps us maintain a good order
            var properties2 = new LinkedHashMap<String, Object>();
            if (flow.getId() != null) {
                properties2.put("id", flow.getId());
            }
            if (flow.getRouteConfigurationId() != null) {
                properties2.put("route-configuration-id", flow.getRouteConfigurationId());
            }
            if (flow.getDescription() != null) {
                properties2.put(KamelHelper.DESCRIPTION, flow.getDescription());
            }
            addMainFlowElement(flow, properties2);
            routeProperties.put("route", properties2);

        } else {
            addMainFlowElement(flow, routeProperties);
        }

        return routeProperties;
    }

    private static void addMainFlowElement(Flow flow, Map<String, Object> routeProperties) {
        if (flow.getFrom() instanceof Rest) {
            routeProperties.put("rest", flow.getFrom());
        } else if (flow.getBeans() != null) {
            routeProperties.put("beans", flow.getBeans());
        } else {
            routeProperties.put("from", flow.getFrom());
        }
    }
}
