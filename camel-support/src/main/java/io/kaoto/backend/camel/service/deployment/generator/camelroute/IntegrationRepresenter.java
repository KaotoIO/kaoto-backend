package io.kaoto.backend.camel.service.deployment.generator.camelroute;

import com.fasterxml.jackson.core.type.TypeReference;
import io.kaoto.backend.camel.KamelHelper;
import io.kaoto.backend.camel.model.deployment.camelroute.IntegrationSpec;
import io.kaoto.backend.camel.model.deployment.kamelet.Bean;
import io.kaoto.backend.camel.model.deployment.kamelet.Flow;
import io.kaoto.backend.camel.model.deployment.rest.Rest;
import io.kaoto.backend.camel.service.deployment.generator.kamelet.KameletRepresenter;
import io.quarkus.runtime.util.StringUtil;
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
                        Map<String, Object> properties = KamelHelper.JSON_MAPPER.convertValue(data,
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
                        } else if (flow.getBeans() != null) {
                            properties.put("beans", flow.getBeans());
                        } else {
                            properties.put("from", flow.getFrom());
                        }

                        if (!StringUtil.isNullOrEmpty(flow.getId())
                                || !StringUtil.isNullOrEmpty(flow.getRouteConfigurationId())) {
                            var routeProperties = new LinkedHashMap<String, Object>();
                            //properties2 helps us maintain a good order
                            var properties2 = new LinkedHashMap<String, Object>();
                            if (flow.getId() != null) {
                                properties2.put("id", flow.getId());
                            }
                            if (flow.getRouteConfigurationId() != null) {
                                properties2.put("route-configuration-id", flow.getRouteConfigurationId());
                            }
                            if (flow.getDescription() != null) {
                                properties2.put("description", flow.getDescription());
                            }
                            properties2.putAll(properties);
                            routeProperties.put("route", properties2);
                            properties = routeProperties;
                        }
                        return representMapping(getTag(data.getClass(), Tag.MAP),
                                properties,
                                DumperOptions.FlowStyle.AUTO);
                    }
                });
        this.multiRepresenters.put(Bean.class, super.bean());
    }

}
