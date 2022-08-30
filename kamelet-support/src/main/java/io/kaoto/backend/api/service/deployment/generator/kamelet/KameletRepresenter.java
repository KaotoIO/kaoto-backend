package io.kaoto.backend.api.service.deployment.generator.kamelet;

import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.client.CustomResource;
import io.kaoto.backend.model.deployment.kamelet.Expression;
import io.kaoto.backend.model.deployment.kamelet.KameletBindingSpec;
import io.kaoto.backend.model.deployment.kamelet.KameletBindingStep;
import io.kaoto.backend.model.deployment.kamelet.KameletBindingStepRef;
import io.kaoto.backend.model.deployment.kamelet.KameletDefinition;
import io.kaoto.backend.model.deployment.kamelet.KameletSpec;
import io.kaoto.backend.model.deployment.kamelet.Template;
import io.kaoto.backend.model.deployment.kamelet.step.ChoiceFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.From;
import io.kaoto.backend.model.deployment.kamelet.step.SetBodyFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.SetHeaderFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.SetPropertyFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.ToFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.TransformFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.UriFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.choice.Choice;
import io.kaoto.backend.model.deployment.kamelet.step.choice.Otherwise;
import io.kaoto.backend.model.deployment.kamelet.step.choice.SuperChoice;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class KameletRepresenter extends Representer {

    public static final String SIMPLE = "simple";
    public static final String STEPS = "steps";
    public static final String PARAMETERS = "parameters";
    public static final String URI = "uri";
    public static final String NAME = "name";

    public KameletRepresenter() {
        getPropertyUtils().setSkipMissingProperties(true);

        customResource();
        metadata();

        //proper order sink steps and source
        spec();

        //For each type of FlowStep or custom classes, create a representer
        from();
        to();

        uri();
        choice();
        setBody();
        setHeader();
        setProperty();
        expression();
        transform();

        addTypeDescriptions();
    }

    private void addTypeDescriptions() {
        TypeDescription setHeaderDesc =
                new TypeDescription(SetBodyFlowStep.class);
        setHeaderDesc.substituteProperty("set-header",
                SetHeaderFlowStep.class, "getSetHeader", "setSetHeader");

        TypeDescription setBodyDesc =
                new TypeDescription(SetBodyFlowStep.class);
        setBodyDesc.substituteProperty("set-body", SetBodyFlowStep.class,
                "getSetBody", "setSetBody");

        TypeDescription setPropertyDesc =
                new TypeDescription(SetPropertyFlowStep.class);
        setBodyDesc.substituteProperty("set-property",
                SetPropertyFlowStep.class,
                "getSetProperty", "setSetProperty");

        TypeDescription transformDesc =
                new TypeDescription(TransformFlowStep.class);
        transformDesc.substituteProperty("transform", TransformFlowStep.class,
                "getTransform", "setTransform");

        this.addTypeDescription(setBodyDesc);
        this.addTypeDescription(setHeaderDesc);
        this.addTypeDescription(setPropertyDesc);
        this.addTypeDescription(transformDesc);
    }

    private void customResource() {
        this.multiRepresenters.put(CustomResource.class,
                new RepresentMap() {
                    @Override
                    public Node representData(final Object data) {
                        Map<String, Object> properties = new LinkedHashMap<>();
                        CustomResource cr = (CustomResource) data;
                        properties.put("apiVersion", cr.getApiVersion());
                        properties.put("kind", cr.getKind());
                        properties.put("metadata", cr.getMetadata());
                        properties.put("spec", cr.getSpec());
                        return representMapping(getTag(data.getClass(),
                                        Tag.MAP),
                                properties,
                                DumperOptions.FlowStyle.AUTO);
                    }
                });
    }

    private void metadata() {
        this.multiRepresenters.put(ObjectMeta.class,
                new RepresentMap() {
                    @Override
                    public Node representData(final Object data) {
                        Map<String, Object> properties = new LinkedHashMap<>();
                        ObjectMeta meta = (ObjectMeta) data;
                        if (meta.getAnnotations() != null) {
                            properties.put("annotations",
                                    meta.getAnnotations());
                        }
                        if (meta.getLabels() != null) {
                            properties.put("labels",
                                    meta.getLabels());
                        }
                        properties.put("name", meta.getName());
                        return representMapping(getTag(data.getClass(),
                                        Tag.MAP),
                                properties,
                                DumperOptions.FlowStyle.BLOCK);
                    }
                });
    }

    private void spec() {
        //spec does not have the right order
        this.multiRepresenters.put(KameletBindingSpec.class,
            new RepresentMap() {
                @Override
                public Node representData(final Object data) {
                    Map<String, Object> properties = new LinkedHashMap<>();
                    KameletBindingSpec spec = (KameletBindingSpec) data;
                    if (spec.getSource() != null) {
                        properties.put("source", spec.getSource());
                    }
                    if (spec.getSteps() != null) {
                        properties.put("steps", spec.getSteps());
                    }
                    if (spec.getSink() != null) {
                        properties.put("sink", spec.getSink());
                    }
                    return representMapping(getTag(data.getClass(), Tag.MAP),
                            properties,
                            DumperOptions.FlowStyle.AUTO);
                }
            });

        this.multiRepresenters.put(KameletSpec.class,
                new RepresentMap() {
                    @Override
                    public Node representData(final Object data) {
                        Map<String, Object> properties = new LinkedHashMap<>();
                        KameletSpec spec = (KameletSpec) data;
                        properties.put("definition", spec.getDefinition());
                        properties.put("dependencies", spec.getDependencies());
                        properties.put("template", spec.getTemplate());
                        return representMapping(getTag(data.getClass(),
                                        Tag.MAP),
                                properties,
                                DumperOptions.FlowStyle.BLOCK);
                    }
                });

        this.multiRepresenters.put(KameletDefinition.class,
                new RepresentMap() {
                    @Override
                    public Node representData(final Object data) {
                        Map<String, Object> properties = new LinkedHashMap<>();
                        KameletDefinition def = (KameletDefinition) data;
                        properties.put("title", def.getTitle());
                        properties.put("description", def.getDescription());
                        if (def.getRequired() != null) {
                            properties.put("required", def.getRequired());
                        }
                        if (def.getProperties() != null) {
                            properties.put("properties", def.getProperties());
                        }
                        return representMapping(getTag(data.getClass(),
                                        Tag.MAP),
                                properties,
                                DumperOptions.FlowStyle.BLOCK);
                    }
                });
        this.multiRepresenters.put(Template.class,
                new RepresentMap() {
                    @Override
                    public Node representData(final Object data) {
                        Map<String, Object> properties = new LinkedHashMap<>();
                        Template template = (Template) data;
                        if (template.getBeans() != null) {
                            properties.put("beans", template.getBeans());
                        }
                        properties.put("from", template.getFrom());
                        return representMapping(getTag(data.getClass(),
                                        Tag.MAP),
                                properties,
                                DumperOptions.FlowStyle.BLOCK);
                    }
                });
        this.multiRepresenters.put(KameletBindingStep.class,
            new RepresentMap() {
                @Override
                public Node representData(final Object data) {
                    Map<String, Object> properties = new LinkedHashMap<>();
                    KameletBindingStep step = (KameletBindingStep) data;
                    if (step.getRef() != null) {
                        properties.put("ref", step.getRef());
                    }
                    if (step.getUri() != null) {
                        properties.put("uri", step.getUri());
                    }
                    if (step.getProperties() != null
                            && !step.getProperties().isEmpty()) {
                        properties.put("properties", step.getProperties());
                    }
                    return representMapping(getTag(data.getClass(), Tag.MAP),
                            properties,
                            DumperOptions.FlowStyle.AUTO);
                }
            });

        this.multiRepresenters.put(KameletBindingStepRef.class,
            new RepresentMap() {
                @Override
                public Node representData(final Object data) {
                    Map<String, Object> properties = new LinkedHashMap<>();
                    KameletBindingStepRef ref = (KameletBindingStepRef) data;
                    if (ref.getApiVersion() != null) {
                        properties.put("apiVersion", ref.getApiVersion());
                    }
                    if (ref.getName() != null) {
                        properties.put("name", ref.getName());
                    }
                    if (ref.getKind() != null) {
                        properties.put("kind", ref.getKind());
                    }
                    return representMapping(getTag(data.getClass(), Tag.MAP),
                            properties,
                            DumperOptions.FlowStyle.AUTO);
                }
            });
    }

    private void metaChoice() {
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
    }

    private void to() {
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
    }

    private void from() {
        this.multiRepresenters.put(From.class, new RepresentMap() {
            @Override
            public Node representData(final Object data) {
                Map<String, Object> properties = new HashMap<>();
                From step = (From) data;
                properties.put(URI, step.getUri());
                if (step.getParameters() != null
                        && !step.getParameters().isEmpty()) {
                    properties.put(PARAMETERS, step.getParameters());
                }
                properties.put(STEPS, step.getSteps());
                return representMapping(getTag(data.getClass(), Tag.MAP),
                        properties,
                        DumperOptions.FlowStyle.AUTO);
            }
        });
    }

    private void uri() {
        this.multiRepresenters.put(UriFlowStep.class, new RepresentMap() {
            @Override
            public Node representData(final Object data) {
                Map<String, Object> properties = new HashMap<>();
                UriFlowStep step = (UriFlowStep) data;
                properties.put(URI, step.getUri());
                if (step.getParameters() != null
                        && !step.getParameters().isEmpty()) {
                    properties.put(PARAMETERS, step.getParameters());
                }
                return representMapping(getTag(data.getClass(), Tag.MAP),
                        properties,
                        DumperOptions.FlowStyle.AUTO);
            }
        });
    }

    private void choice() {
        this.multiRepresenters.put(Choice.class, new RepresentMap() {
            @Override
            public Node representData(final Object data) {
                Map<String, Object> properties = new HashMap<>();
                Choice step = (Choice) data;
                properties.put(STEPS, step.getSteps());
                if (step.getSimple() != null
                        && !step.getSimple().isEmpty()) {
                    properties.put(SIMPLE, step.getSimple());
                }
                return representMapping(getTag(data.getClass(), Tag.MAP),
                        properties,
                        DumperOptions.FlowStyle.AUTO);
            }
        });

        this.multiRepresenters.put(Otherwise.class, new RepresentMap() {
            @Override
            public Node representData(final Object data) {
                Map<String, Object> properties = new HashMap<>();
                Otherwise step = (Otherwise) data;
                properties.put(STEPS, step.getSteps());
                return representMapping(getTag(data.getClass(), Tag.MAP),
                        properties,
                        DumperOptions.FlowStyle.AUTO);
            }
        });

        metaChoice();
        whenChoice();
    }

    private void whenChoice() {
        this.multiRepresenters.put(SuperChoice.class, new RepresentMap() {
            @Override
            public Node representData(final Object data) {
                Map<String, Object> properties = new LinkedHashMap<>();
                SuperChoice step = (SuperChoice) data;
                properties.put("when", step.getChoice());
                if (step.getOtherwise() != null) {
                    properties.put("otherwise", step.getOtherwise());
                }
                return representMapping(getTag(data.getClass(), Tag.MAP),
                        properties,
                        DumperOptions.FlowStyle.AUTO);
            }
        });
    }

    private void setBody() {
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
    }

    private void setHeader() {
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
    }

    private void setProperty() {
        this.multiRepresenters.put(SetPropertyFlowStep.class,
                new RepresentMap() {
            @Override
            public Node representData(final Object data) {
                Map<String, Object> properties = new HashMap<>();
                SetPropertyFlowStep step = (SetPropertyFlowStep) data;
                properties.put("set-property",
                        step.getSetPropertyPairFlowStep());
                return representMapping(getTag(data.getClass(), Tag.MAP),
                        properties,
                        DumperOptions.FlowStyle.AUTO);
            }
        });
    }

    private void expression() {
        this.multiRepresenters.put(Expression.class, new RepresentMap() {
            @Override
            public Node representData(final Object data) {
                Map<String, Object> properties = new HashMap<>();
                Expression step = (Expression) data;
                if (step.getConstant() != null) {
                    properties.put("constant", step.getConstant());
                }

                if (step.getSimple() != null) {
                    properties.put(SIMPLE, step.getSimple());
                }

                if (step.getName() != null) {
                    properties.put(NAME, step.getName());
                }
                return representMapping(getTag(data.getClass(), Tag.MAP),
                        properties,
                        DumperOptions.FlowStyle.AUTO);
            }
        });
    }

    private void transform() {
        this.multiRepresenters.put(TransformFlowStep.class, new RepresentMap() {
            @Override
            public Node representData(final Object data) {
                Map<String, Object> properties = new HashMap<>();
                TransformFlowStep step = (TransformFlowStep) data;
                properties.put("transform", step.getTransform());
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
