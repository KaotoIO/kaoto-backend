package io.kaoto.backend.api.service.deployment.generator.kamelet;

import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.client.CustomResource;
import io.kaoto.backend.model.deployment.kamelet.Expression;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.model.deployment.kamelet.KameletBindingSpec;
import io.kaoto.backend.model.deployment.kamelet.KameletBindingStep;
import io.kaoto.backend.model.deployment.kamelet.KameletBindingStepRef;
import io.kaoto.backend.model.deployment.kamelet.KameletDefinition;
import io.kaoto.backend.model.deployment.kamelet.KameletSpec;
import io.kaoto.backend.model.deployment.kamelet.Template;
import io.kaoto.backend.model.deployment.kamelet.step.ChoiceFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.Filter;
import io.kaoto.backend.model.deployment.kamelet.step.FilterFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.From;
import io.kaoto.backend.model.deployment.kamelet.step.MarshalFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.RemoveHeaderFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.RemovePropertyFlowStep;
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
import org.yaml.snakeyaml.introspector.BeanAccess;
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
    public static final String KIND = "kind";
    public static final String API_VERSION = "apiVersion";

    public KameletRepresenter() {
        this.getPropertyUtils()
                .setSkipMissingProperties(true);
        this.getPropertyUtils()
                .setAllowReadOnlyProperties(true);
        this.getPropertyUtils()
                .setBeanAccess(BeanAccess.FIELD);

        customResource();
        metadata();

        //proper order sink steps and source
        spec();

        //For each type of FlowStep or custom classes, create a representer
        addEIP();
    }

    private void customResource() {
        this.multiRepresenters.put(CustomResource.class,
                new RepresentMap() {
                    @Override
                    public Node representData(final Object data) {
                        Map<String, Object> properties = new LinkedHashMap<>();
                        CustomResource cr = (CustomResource) data;
                        properties.put(API_VERSION, cr.getApiVersion());
                        properties.put(KIND, cr.getKind());
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
                        properties.put(NAME, meta.getName());
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
                        properties.put(STEPS, spec.getSteps());
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
                        properties.put(URI, step.getUri());
                    }
                    if (step.getParameters() != null
                            && !step.getParameters().isEmpty()) {
                        properties.put(PARAMETERS, step.getParameters());
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
                        properties.put(API_VERSION, ref.getApiVersion());
                    }
                    if (ref.getName() != null) {
                        properties.put(NAME, ref.getName());
                    }
                    if (ref.getKind() != null) {
                        properties.put(KIND, ref.getKind());
                    }
                    return representMapping(getTag(data.getClass(), Tag.MAP),
                            properties,
                            DumperOptions.FlowStyle.AUTO);
                }
            });
    }

    private void addEIP() {
        //Can we dynamically add this without Quarkus removing the classes
        // and making a mess?
        var eips = new Class[] {
                ChoiceFlowStep.class,
                ToFlowStep.class,
                From.class,
                UriFlowStep.class,
                SetBodyFlowStep.class,
                SetHeaderFlowStep.class,
                SetPropertyFlowStep.class,
                Expression.class,
                TransformFlowStep.class,
                FilterFlowStep.class,
                RemoveHeaderFlowStep.class,
                RemovePropertyFlowStep.class,
                MarshalFlowStep.class
        };

        for (var eip : eips) {
            this.multiRepresenters.put(eip, new RepresentMap() {
                @Override
                public Node representData(final Object data) {
                    return representMapping(getTag(data.getClass(), Tag.MAP),
                            ((FlowStep) data).getRepresenterProperties(),
                            DumperOptions.FlowStyle.AUTO);
                }
            });
        }

        choice();
        filter();
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

    private void filter() {
        this.multiRepresenters.put(Filter.class, new RepresentMap() {
            @Override
            public Node representData(final Object data) {
                Map<String, Object> properties = new HashMap<>();
                Filter step = (Filter) data;
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
