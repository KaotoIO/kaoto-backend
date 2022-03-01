package io.kaoto.backend.api.service.deployment.generator.kamelet;

import io.kaoto.backend.model.deployment.kamelet.Expression;
import io.kaoto.backend.model.deployment.kamelet.step.ChoiceFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.From;
import io.kaoto.backend.model.deployment.kamelet.step.SetBodyFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.SetHeaderFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.ToFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.UriFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.choice.Choice;
import io.kaoto.backend.model.deployment.kamelet.step.choice.SuperChoice;
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
            @Override
            public Node representData(final Object data) {
                Map<String, Object> properties = new HashMap<>();
                ToFlowStep uriFlowStep = (ToFlowStep) data;
                properties.put("to", uriFlowStep.getTo());
                return representMapping(getTag(data.getClass(), Tag.MAP),
                        properties,
                        DumperOptions.FlowStyle.AUTO);
            }
        });
        this.multiRepresenters.put(From.class, new RepresentMap() {
            @Override
            public Node representData(final Object data) {
                Map<String, Object> properties = new HashMap<>();
                From step = (From) data;
                properties.put("uri", step.getUri());
                if (step.getParameters() != null
                        && !step.getParameters().isEmpty()) {
                    properties.put("parameters", step.getParameters());
                }
                properties.put("steps", step.getSteps());
                return representMapping(getTag(data.getClass(), Tag.MAP),
                        properties,
                        DumperOptions.FlowStyle.AUTO);
            }
        });
        this.multiRepresenters.put(UriFlowStep.class, new RepresentMap() {
            @Override
            public Node representData(final Object data) {
                Map<String, Object> properties = new HashMap<>();
                UriFlowStep step = (UriFlowStep) data;
                properties.put("uri", step.getUri());
                if (step.getParameters() != null
                        && !step.getParameters().isEmpty()) {
                    properties.put("parameters", step.getParameters());
                }
                return representMapping(getTag(data.getClass(), Tag.MAP),
                        properties,
                        DumperOptions.FlowStyle.AUTO);
            }
        });
        this.multiRepresenters.put(ChoiceFlowStep.class, new RepresentMap() {
            @Override
            public Node representData(final Object data) {
                Map<String, Object> properties = new HashMap<>();
                ChoiceFlowStep uriFlowStep = (ChoiceFlowStep) data;
                properties.put("choice", uriFlowStep.getChoice());
                return representMapping(getTag(data.getClass(), Tag.MAP),
                        properties,
                        DumperOptions.FlowStyle.AUTO);
            }
        });
        this.multiRepresenters.put(Choice.class, new RepresentMap() {
            @Override
            public Node representData(final Object data) {
                Map<String, Object> properties = new HashMap<>();
                Choice step = (Choice) data;
                properties.put("steps", step.getSteps());
                if (step.getSimple() != null
                        && !step.getSimple().isEmpty()) {
                    properties.put("simple", step.getSimple());
//                } else if (step.getJsonPath() != null) {
//                    properties.put("jsonPath", step.getJsonPath());
                }
                return representMapping(getTag(data.getClass(), Tag.MAP),
                        properties,
                        DumperOptions.FlowStyle.AUTO);
            }
        });
        this.multiRepresenters.put(SuperChoice.class, new RepresentMap() {
            @Override
            public Node representData(final Object data) {
                Map<String, Object> properties = new HashMap<>();
                SuperChoice step = (SuperChoice) data;
                properties.put("when", step.getChoice());
                if (step.getOtherwise() != null
                       && !step.getOtherwise().isEmpty()) {
                    properties.put("otherwise", step.getOtherwise());
                }
                return representMapping(getTag(data.getClass(), Tag.MAP),
                        properties,
                        DumperOptions.FlowStyle.AUTO);
            }
        });
        this.multiRepresenters.put(SetBodyFlowStep.class, new RepresentMap() {
            @Override
            public Node representData(final Object data) {
                Map<String, Object> properties = new HashMap<>();
                SetBodyFlowStep step = (SetBodyFlowStep) data;
                properties.put("set-body", step.getSetBody());
                return representMapping(getTag(data.getClass(), Tag.MAP),
                        properties,
                        DumperOptions.FlowStyle.AUTO);
            }
        });
        this.multiRepresenters.put(SetHeaderFlowStep.class, new RepresentMap() {
            @Override
            public Node representData(final Object data) {
                Map<String, Object> properties = new HashMap<>();
                SetHeaderFlowStep step = (SetHeaderFlowStep) data;
                properties.put("set-header", step.getSetHeaderPairFlowStep());
                return representMapping(getTag(data.getClass(), Tag.MAP),
                        properties,
                        DumperOptions.FlowStyle.AUTO);
            }
        });
        this.multiRepresenters.put(Expression.class, new RepresentMap() {
            @Override
            public Node representData(final Object data) {
                Map<String, Object> properties = new HashMap<>();
                Expression step = (Expression) data;
                if (step.getConstant() != null) {
                    properties.put("constant", step.getConstant());
                }

                if (step.getSimple() != null) {
                    properties.put("simple", step.getSimple());
                }

                if (step.getName() != null) {
                    properties.put("name", step.getName());
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
