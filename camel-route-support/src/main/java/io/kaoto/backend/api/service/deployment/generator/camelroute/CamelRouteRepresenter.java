package io.kaoto.backend.api.service.deployment.generator.camelroute;

import io.kaoto.backend.model.deployment.camelroute.CamelRoute;
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
    }
}
