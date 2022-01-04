package io.kaoto.backend.api.service.deployment.generator.kamelet;

import io.kaoto.backend.model.deployment.kamelet.Expression;
import io.kaoto.backend.model.deployment.kamelet.step.SetBodyFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.ToFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.UriFlowStep;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import java.util.HashMap;
import java.util.Map;

public class KameletRepresenter extends Representer {

    KameletRepresenter() {
        getPropertyUtils().setSkipMissingProperties(true);
        getPropertyUtils().setAllowReadOnlyProperties(true);

        //For each type of FlowStep or custom classes, create a representer
        this.multiRepresenters.put(ToFlowStep.class, new RepresentMap() {
            public Node representData(final Object data) {
                Map<String, Object> properties = new HashMap<>();
                ToFlowStep uriFlowStep = (ToFlowStep) data;
                properties.put("to", uriFlowStep.getTo());
                return representMapping(getTag(data.getClass(), Tag.MAP),
                        properties,
                        DumperOptions.FlowStyle.AUTO);
            }
        });
        this.multiRepresenters.put(UriFlowStep.class, new RepresentMap() {
            public Node representData(final Object data) {
                Map<String, Object> properties = new HashMap<>();
                UriFlowStep uriFlowStep = (UriFlowStep) data;
                properties.put("uri", uriFlowStep.getUri());
                properties.put("parameters", uriFlowStep.getParameters());
                return representMapping(getTag(data.getClass(), Tag.MAP),
                        properties,
                        DumperOptions.FlowStyle.AUTO);
            }
        });
        this.multiRepresenters.put(SetBodyFlowStep.class, new RepresentMap() {
            public Node representData(final Object data) {
                Map<String, Object> properties = new HashMap<>();
                SetBodyFlowStep step = (SetBodyFlowStep) data;
                properties.put("set-body", step.getSetBody());
                return representMapping(getTag(data.getClass(), Tag.MAP),
                        properties,
                        DumperOptions.FlowStyle.AUTO);
            }
        });
        this.multiRepresenters.put(Expression.class, new RepresentMap() {
            public Node representData(final Object data) {
                Map<String, Object> properties = new HashMap<>();
                Expression step = (Expression) data;
                if (step.getConstant() != null) {
                    properties.put("constant", step.getConstant());
                }

                if (step.getSimple() != null) {
                    properties.put("simple", step.getSimple());
                }
                return representMapping(getTag(data.getClass(), Tag.MAP),
                        properties,
                        DumperOptions.FlowStyle.AUTO);
            }
        });
    }


    @Override
    protected NodeTuple representJavaBeanProperty(
            final Object javaBean,
            final Property property,
            final Object propertyValue,
            final Tag customTag) {

        if (propertyValue == null) {
            return null;
        }

        if (property.getName().equalsIgnoreCase("CRDName")) {
            return null;
        }

        return super.representJavaBeanProperty(javaBean, property,
                propertyValue, customTag);
    }
}
