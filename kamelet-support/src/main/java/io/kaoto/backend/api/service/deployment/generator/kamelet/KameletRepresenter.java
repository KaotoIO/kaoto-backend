package io.kaoto.backend.api.service.deployment.generator.kamelet;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.fabric8.kubernetes.client.CustomResource;
import io.kaoto.backend.model.deployment.kamelet.expression.Expression;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.model.deployment.kamelet.KameletTemplate;
import io.kaoto.backend.model.deployment.kamelet.expression.Script;
import io.kaoto.backend.model.deployment.kamelet.expression.ScriptExpression;
import io.kaoto.backend.model.deployment.kamelet.step.AggregateFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.ChoiceFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.CircuitBreakerFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.ClaimCheckFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.ConditionBlock;
import io.kaoto.backend.model.deployment.kamelet.step.ConvertBodyToFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.DelayFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.DynamicRouterFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.EnrichFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.Filter;
import io.kaoto.backend.model.deployment.kamelet.step.FilterFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.From;
import io.kaoto.backend.model.deployment.kamelet.step.IdempotentConsumerFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.LoadBalanceFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.LogFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.LoopFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.MarshalFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.MulticastFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.PipelineFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.PollEnrichFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.ProcessFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.RecipientListFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.RemoveHeaderFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.RemoveHeadersFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.RemovePropertiesFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.RemovePropertyFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.ResequenceFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.RollbackFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.RoutingSlipFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.SagaFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.SampleFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.ScriptFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.ServiceCallFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.SetBodyFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.SetExchangePatternFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.SetHeaderFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.SetPropertyFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.SortFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.SplitFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.StopFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.ThreadsFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.ThrottleFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.ThrowExceptionFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.ToDynamicFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.ToFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.TransactedFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.TransformFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.TryCatchFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.UnmarshalFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.UriFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.ValidateFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.WireTapFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.choice.Choice;
import io.kaoto.backend.model.deployment.kamelet.step.choice.Otherwise;
import io.kaoto.backend.model.deployment.kamelet.step.choice.SuperChoice;
import org.apache.camel.v1alpha1.KameletBindingSpec;
import org.apache.camel.v1alpha1.KameletSpec;
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
    public static final String JQ = "jq";
    public static final String JSONPATH = "jsonpath";
    public static final String CONSTANT = "constant";
    public static final String GROOVY = "groovy";
    public static final String JAVASCRIPT = "javascript";
    public static final String EXPRESSION = "expression";
    public static final String STEPS = "steps";
    public static final String PARAMETERS = "parameters";
    public static final String URI = "uri";
    public static final String NAME = "name";

    public KameletRepresenter() {
        super(new DumperOptions());
        this.getPropertyUtils().setSkipMissingProperties(true);
        this.getPropertyUtils().setAllowReadOnlyProperties(true);
        this.getPropertyUtils().setBeanAccess(BeanAccess.FIELD);

        customResource();

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
                        ObjectMapper objectMapper = new ObjectMapper();
                        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                        Map<String, Object> properties = objectMapper.convertValue(data,
                                new TypeReference<Map<String, Object>>() {});
                        CustomResource cr = (CustomResource) data;

                        final var objectMeta = cr.getMetadata();
                        final var metadata = (Map<String, Object>) properties.get("metadata");

                        if (objectMeta.getAdditionalProperties() != null
                                && !objectMeta.getAdditionalProperties().isEmpty()) {
                            metadata.put("additionalProperties", new LinkedHashMap<String, Object>());
                            ((Map<String, Object>) metadata.get("additionalProperties"))
                                    .putAll(objectMeta.getAdditionalProperties());
                            for (var key : objectMeta.getAdditionalProperties().keySet()) {
                                metadata.remove(key);
                            }
                        } else {
                            metadata.remove("additionalProperties");
                        }

                        if (objectMeta.getAnnotations() != null && !objectMeta.getAnnotations().isEmpty()) {
                            metadata.put("annotations", new LinkedHashMap<String, Object>());
                            ((Map<String, Object>) metadata.get("annotations")).putAll(objectMeta.getAnnotations());
                            for (var key : objectMeta.getAnnotations().keySet()) {
                                metadata.remove(key);
                            }
                        } else {
                            metadata.remove("annotations");
                        }

                        if (objectMeta.getLabels() != null && !objectMeta.getLabels().isEmpty()) {
                            metadata.put("labels", new LinkedHashMap<String, String>());
                            ((Map<String, String>) metadata.get("labels")).putAll(objectMeta.getLabels());
                            for (var key : objectMeta.getLabels().keySet()) {
                                metadata.remove(key);
                            }
                        } else {
                            metadata.remove("labels");
                        }

                        properties.put("spec", cr.getSpec());

                        return representMapping(getTag(data.getClass(), Tag.MAP), properties,
                                DumperOptions.FlowStyle.AUTO);
                    }
                });
    }

    private void spec() {
        //spec does not have the right order
        final var objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        this.multiRepresenters.put(KameletBindingSpec.class,
            new RepresentMap() {
                @Override
                public Node representData(final Object data) {
                    Map<String, Object> properties = new LinkedHashMap<>();
                    KameletBindingSpec spec = (KameletBindingSpec) data;
                    //Ordering
                    properties.put("source", null);
                    if (spec.getSteps() != null) {
                        properties.put(STEPS, null);
                    }
                    properties.put("sink", null);
                    properties.putAll(
                            objectMapper.convertValue(data, new TypeReference<Map<String, Object>>() {}));
                    return representMapping(getTag(data.getClass(), Tag.MAP), properties, DumperOptions.FlowStyle.AUTO);
                }
            });

        this.multiRepresenters.put(KameletSpec.class,
                new RepresentMap() {
                    @Override
                    public Node representData(final Object data) {
                        Map<String, Object> properties = new LinkedHashMap<>();
                        KameletSpec spec = (KameletSpec) data;
                        properties.putAll(
                                new ObjectMapper().convertValue(data, new TypeReference<Map<String, Object>>() {}));
                        properties.put("template", spec.getTemplate());
                        return representMapping(
                                getTag(data.getClass(), Tag.MAP), properties, DumperOptions.FlowStyle.AUTO);
                    }
                });

        this.multiRepresenters.put(KameletTemplate.class,
                new RepresentMap() {
                    @Override
                    public Node representData(final Object data) {
                        Map<String, Object> properties = new LinkedHashMap<>();
                        KameletTemplate template = (KameletTemplate) data;
                        if (template.getBeans() != null) {
                            properties.put("beans", template.getBeans());
                        }
                        properties.put("from", template.getFrom());
                        return representMapping(getTag(data.getClass(), Tag.MAP), properties,
                                DumperOptions.FlowStyle.BLOCK);
                    }
                });
    }

    private void addEIP() {
        //Can we dynamically add this without Quarkus removing the classes
        // and making a mess?
        var eips = new Class[] {
                AggregateFlowStep.class,
                ClaimCheckFlowStep.class,
                CircuitBreakerFlowStep.class,
                ChoiceFlowStep.class,
                ConvertBodyToFlowStep.class,
                DelayFlowStep.class,
                DynamicRouterFlowStep.class,
                EnrichFlowStep.class,
                Expression.class,
                FilterFlowStep.class,
                From.class,
                IdempotentConsumerFlowStep.class,
                LoadBalanceFlowStep.class,
                LogFlowStep.class,
                LoopFlowStep.class,
                MarshalFlowStep.class,
                MulticastFlowStep.class,
                RemoveHeaderFlowStep.class,
                RemoveHeadersFlowStep.class,
                RemovePropertiesFlowStep.class,
                RemovePropertyFlowStep.class,
                ResequenceFlowStep.class,
                RollbackFlowStep.class,
                RoutingSlipFlowStep.class,
                SetBodyFlowStep.class,
                SetHeaderFlowStep.class,
                SetPropertyFlowStep.class,
                SplitFlowStep.class,
                PipelineFlowStep.class,
                PollEnrichFlowStep.class,
                ProcessFlowStep.class,
                RecipientListFlowStep.class,
                SagaFlowStep.class,
                SampleFlowStep.class,
                ScriptFlowStep.class,
                ServiceCallFlowStep.class,
                SortFlowStep.class,
                StopFlowStep.class,
                SetExchangePatternFlowStep.class,
                ThreadsFlowStep.class,
                ThrottleFlowStep.class,
                ThrowExceptionFlowStep.class,
                ToFlowStep.class,
                ToDynamicFlowStep.class,
                TransactedFlowStep.class,
                TransformFlowStep.class,
                TryCatchFlowStep.class,
                UnmarshalFlowStep.class,
                UriFlowStep.class,
                ValidateFlowStep.class,
                WireTapFlowStep.class
        };

        for (var eip : eips) {
            this.multiRepresenters.put(eip, new RepresentMap() {
                @Override
                public Node representData(final Object data) {
                    return representMapping(getTag(data.getClass(), Tag.MAP),
                            ((FlowStep) data).getRepresenterProperties(), DumperOptions.FlowStyle.AUTO);
                }
            });
        }

        this.multiRepresenters.put(Expression.class, new RepresentMap() {
            @Override
            public Node representData(final Object data) {
                return representMapping(getTag(data.getClass(), Tag.MAP),
                        ((Expression) data).getRepresenterProperties(), DumperOptions.FlowStyle.AUTO);
            }
        });

        this.multiRepresenters.put(Script.class, new RepresentMap() {
            @Override
            public Node representData(final Object data) {
                return representMapping(getTag(data.getClass(), Tag.MAP),
                        ((Script) data).getRepresenterProperties(), DumperOptions.FlowStyle.AUTO);
            }
        });

        this.multiRepresenters.put(ScriptExpression.class, new RepresentMap() {
            @Override
            public Node representData(final Object data) {
                return representMapping(getTag(data.getClass(), Tag.MAP),
                        ((ScriptExpression) data).getRepresenterProperties(), DumperOptions.FlowStyle.AUTO);
            }
        });

        choice();
        filter();
    }

    private void choice() {
        this.multiRepresenters.put(Choice.class, new RepresentMap() {
            @Override
            public Node representData(final Object data) {
                return representConditionBlock(data);
            }
        });

        this.multiRepresenters.put(Otherwise.class, new RepresentMap() {
            @Override
            public Node representData(final Object data) {
                Map<String, Object> properties = new HashMap<>();
                Otherwise step = (Otherwise) data;
                properties.put(STEPS, step.getSteps());
                return representMapping(getTag(data.getClass(), Tag.MAP), properties,
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
                return representMapping(getTag(data.getClass(), Tag.MAP), properties,
                        DumperOptions.FlowStyle.AUTO);
            }
        });
    }

    private Node representConditionBlock(final Object data) {
        Map<String, Object> properties = new HashMap<>();
        ConditionBlock step = (ConditionBlock) data;
        properties.put(STEPS, step.getSteps());
        if (step.getSimple() != null && !step.getSimple().isEmpty()) {
            properties.put(SIMPLE, step.getSimple());
        } else if (step.getJq() != null && !step.getJq().isEmpty()) {
            properties.put(JQ, step.getJq());
        } else if (step.getJsonpath() != null && !step.getJsonpath().isEmpty()) {
            properties.put(JSONPATH, step.getJsonpath());
        } else if (step.getExpression() != null) {
            properties.put(EXPRESSION, step.getExpression());
        }
        return representMapping(getTag(data.getClass(), Tag.MAP), properties,
                DumperOptions.FlowStyle.AUTO);
    }

    private void filter() {
        this.multiRepresenters.put(Filter.class, new RepresentMap() {
            @Override
            public Node representData(final Object data) {
                return representConditionBlock(data);
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

        return super.representJavaBeanProperty(javaBean, property, propertyValue, customTag);
    }
}
