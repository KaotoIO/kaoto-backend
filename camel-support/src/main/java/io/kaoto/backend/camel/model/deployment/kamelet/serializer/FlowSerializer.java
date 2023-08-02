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

        if (flow.getFrom() instanceof Rest) {
            routeProperties.put("rest", flow.getFrom());
        } else if (flow.getBeans() != null) {
            routeProperties.put("beans", flow.getBeans());
        } else {
            if (!StringUtil.isNullOrEmpty(flow.getId())
                    || !StringUtil.isNullOrEmpty(flow.getRouteConfigurationId())
                    || !StringUtil.isNullOrEmpty(flow.getDescription())) {
                final var miniRoute = new LinkedHashMap<String, Object>();
                if (flow.getId() != null) {
                    miniRoute.put("id", flow.getId());
                }
                if (flow.getRouteConfigurationId() != null) {
                    miniRoute.put("route-configuration-id", flow.getRouteConfigurationId());
                }
                if (flow.getDescription() != null) {
                    miniRoute.put(KamelHelper.DESCRIPTION, flow.getDescription());
                }
                miniRoute.put("from", flow.getFrom());
                routeProperties.put("route", miniRoute);
            } else {
                routeProperties.put("from", flow.getFrom());
            }
        }

        return routeProperties;
    }
}
