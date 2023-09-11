package io.kaoto.backend.camel.model.deployment.camelroute;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class CamelRouteSerializer extends StdSerializer<CamelRoute> {

    public CamelRouteSerializer() {
        super(CamelRoute.class);
    }

    @Override
    public void serialize(CamelRoute route, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {

        List<Object> properties = new ArrayList<>();
        if (route.getFlows() != null) {
            properties.addAll(route.getFlows());
        }
        if (route.getBeans() != null) {
            Map<String, Object> beans = new LinkedHashMap<>();
            beans.put("beans", route.getBeans());
            properties.add(beans);
        }
        if (route.getRouteConfiguration() != null) {
            Map<String, Object> config = new LinkedHashMap<>();
            config.put("routeConfiguration", route.getRouteConfiguration());
            properties.add(config);
        }
        if (route.getRestConfiguration() != null) {
            Map<String, Object> config = new LinkedHashMap<>();
            config.put("restConfiguration", route.getRestConfiguration());
            properties.add(config);
        }
        jsonGenerator.writeObject(properties);
    }


}
