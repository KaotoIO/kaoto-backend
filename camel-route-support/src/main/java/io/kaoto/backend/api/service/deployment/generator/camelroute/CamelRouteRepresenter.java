package io.kaoto.backend.api.service.deployment.generator.camelroute;

import io.kaoto.backend.model.deployment.camelroute.CamelRoute;
import io.kaoto.backend.model.deployment.rest.HttpVerb;
import io.kaoto.backend.model.deployment.rest.Rest;
import io.kaoto.backend.model.deployment.rest.RestParameter;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.Tag;

public class CamelRouteRepresenter extends IntegrationRepresenter {


    public CamelRouteRepresenter() {
        super();
        this.multiRepresenters.put(CamelRoute.class,
                new RepresentMap() {
                    @Override
                    public Node representData(final Object data) {
                        return representSequence(getTag(data.getClass(), Tag.SEQ),
                                ((CamelRoute) data).getFlows(),
                                DumperOptions.FlowStyle.BLOCK);
                    }
                });
        this.multiRepresenters.put(Rest.class,
                new RepresentMap() {
                    @Override
                    public Node representData(final Object data) {
                        return representMapping(getTag(data.getClass(), Tag.MAP),
                                ((Rest) data).getRepresenterProperties(),
                                DumperOptions.FlowStyle.AUTO);
                    }
                });
        this.multiRepresenters.put(HttpVerb.class,
                new RepresentMap() {
                    @Override
                    public Node representData(final Object data) {
                        return representMapping(getTag(data.getClass(), Tag.MAP),
                                ((HttpVerb) data).getRepresenterProperties(),
                                DumperOptions.FlowStyle.AUTO);
                    }
                });
        this.multiRepresenters.put(RestParameter.class,
                new RepresentMap() {
                    @Override
                    public Node representData(final Object data) {
                        return representMapping(getTag(data.getClass(), Tag.MAP),
                                ((RestParameter) data).getRepresenterProperties(),
                                DumperOptions.FlowStyle.AUTO);
                    }
                });
    }
}
