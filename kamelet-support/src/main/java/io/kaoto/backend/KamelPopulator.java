package io.kaoto.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.fabric8.kubernetes.api.model.AnyType;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.model.deployment.kamelet.Bean;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.model.deployment.kamelet.Kamelet;
import io.kaoto.backend.model.deployment.kamelet.KameletSpec;
import io.kaoto.backend.model.deployment.kamelet.KameletTemplate;
import io.kaoto.backend.model.deployment.kamelet.expression.Expression;
import io.kaoto.backend.model.deployment.kamelet.expression.Script;
import io.kaoto.backend.model.deployment.kamelet.expression.ScriptExpression;
import io.kaoto.backend.model.deployment.kamelet.step.AggregateFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.ChoiceFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.CircuitBreakerFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.ClaimCheckFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.ConvertBodyToFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.DelayFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.DynamicRouterFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.EnrichFlowStep;
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
import io.kaoto.backend.model.deployment.kamelet.step.dataformat.DataFormat;
import io.kaoto.backend.model.deployment.rest.Rest;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.parameter.StringParameter;
import io.kaoto.backend.model.step.Branch;
import io.kaoto.backend.model.step.Step;
import org.apache.camel.v1alpha1.kameletspec.Definition;
import org.apache.camel.v1alpha1.kameletspec.definition.Properties;
import org.jboss.logging.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class KamelPopulator {

    public static final String SIMPLE = "simple";
    public static final String JQ = "jq";
    public static final String CONSTANT = "constant";
    public static final String NAME = "name";
    public static final String EXPRESSION = "expression";
    public static final String GROOVY = "groovy";
    public static final String JAVASCRIPT = "javascript";
    public static final String CAMEL_APACHE_ORG_KAMELET_ICON = "camel.apache.org/kamelet.icon";
    protected static final Logger log = Logger.getLogger(KamelPopulator.class);
    private final String group = "camel.apache.org";

    private StepCatalog catalog;

    public KamelPopulator(final StepCatalog catalog) {
        this.catalog = catalog;
    }

    public static Expression getExpression(final Step step) {
        Expression expression = new Expression();
        for (Parameter p : step.getParameters()) {
            if (p.getValue() == null) {
                continue;
            }
            if (NAME.equalsIgnoreCase(p.getId())) {
                expression.setName(String.valueOf(p.getValue()));
            } else if (SIMPLE.equalsIgnoreCase(p.getId())) {
                expression.setSimple(p.getValue());
            } else if (JQ.equalsIgnoreCase(p.getId())) {
                expression.setJq(String.valueOf(p.getValue()));
            } else if (CONSTANT.equalsIgnoreCase(p.getId())) {
                expression.setConstant(String.valueOf(p.getValue()));
            } else if (EXPRESSION.equalsIgnoreCase(p.getId())) {
                Expression nestedExpression = new Expression(p.getValue());
                expression.setExpression(nestedExpression);
            }
        }
        expression.setId(step.getStepId());
        return expression;
    }

    public static Script getScript(final Step step) {
        Script script = new Script();
        for (Parameter p : step.getParameters()) {
            if (p.getValue() == null) {
                continue;
            }
            if (NAME.equalsIgnoreCase(p.getId())) {
                script.setName(String.valueOf(p.getValue()));
            } else if (GROOVY.equalsIgnoreCase(p.getId())) {
                script.setGroovy(String.valueOf(p.getValue()));
            } else if (JAVASCRIPT.equalsIgnoreCase(p.getId())) {
                script.setJavascript(String.valueOf(p.getValue()));
            } else if (EXPRESSION.equalsIgnoreCase(p.getId())) {
                ScriptExpression nestedExpression = new ScriptExpression(p.getValue());
                script.setExpression(nestedExpression);
            }
        }
        script.setId(step.getStepId());
        return script;
    }

    public static FlowStep deserializeToFlowStep(Object to) {
        FlowStep res = null;

        if (to instanceof FlowStep flowStep) {
            res = flowStep;
        } else if (to instanceof String sto) {
            UriFlowStep uri = new UriFlowStep();
            uri.setUri(sto);
            res = uri;
        } else if (to instanceof Map map) {
            UriFlowStep uri = new UriFlowStep();
            uri.setUri(map.getOrDefault("uri", "").toString());
            var parameters = (Map<String, String>) map.getOrDefault("parameters", Collections.emptyMap());
            if (parameters != null && !parameters.isEmpty()) {
                uri.setParameters(new LinkedHashMap<>(parameters));
            }
            res = uri;
        }

        return res;
    }

    public void populateKamelet(
            final Kamelet kamelet,
            final Map<String, Object> metadata,
            final List<Step> steps,
            final List<Parameter> parameters) {


        KameletSpec spec;
        var metaObject = new ObjectMeta();
        if (metadata != null && !metadata.isEmpty()) {
            metaObject.setAnnotations(
                    (Map<String, String>) metadata.getOrDefault("annotations", Collections.emptyMap()));
            metaObject.setAdditionalProperties(
                    (Map<String, Object>) metadata.getOrDefault("additionalProperties", Collections.emptyMap()));
            var original_spec = metadata.remove("spec");
            metaObject.setLabels((Map<String, String>) metadata.getOrDefault("labels", Collections.emptyMap()));
            metaObject.setName(String.valueOf(metadata.getOrDefault("name", "")));
            if (original_spec != null && original_spec instanceof KameletSpec ospec) {
                spec = ospec;
            } else if (original_spec != null && original_spec instanceof Map ospec) {
                ObjectMapper mapper = new ObjectMapper();
                spec = mapper.convertValue(ospec, KameletSpec.class);
            } else {
                spec = new KameletSpec();
            }
        } else {
            spec = new KameletSpec();
        }


        kamelet.setSpec(spec);
        final var template = new KameletTemplate();
        kamelet.getSpec().setTemplate(template);
        template.setFrom(getFlow(steps));
        if (metadata.containsKey("definition")) {
            if (metadata.get("definition") instanceof Definition def) {
                kamelet.getSpec().setDefinition(def);
            } else if (metadata.get("definition") instanceof Map map) {
                ObjectMapper objectMapper = new ObjectMapper();
                Definition def = objectMapper.convertValue(map, Definition.class);
                kamelet.getSpec().setDefinition(def);
            }
        }

        kamelet.setMetadata(new ObjectMeta());
        populateLabels(kamelet, (Map<String, String>) metadata.getOrDefault("labels", Collections.emptyMap()));
        populateAdditionalProperties(kamelet,
                (Map<String, String>) metadata.getOrDefault("additionalProperties", Collections.emptyMap()));
        populateAnnotations(kamelet,
                (Map<String, String>) metadata.getOrDefault("annotations", Collections.emptyMap()));

        //override in case this is outdated from the graphic side
        Type type = defineType(steps);
        kamelet.getMetadata().getLabels().put(group + "/kamelet.type", type.name());

        //consistent naming for kamelets
        String name = metadata.getOrDefault(NAME, "").toString();
        if (!name.endsWith(type.name())) {
            name = name + "-" + type.name();
        }
        kamelet.getMetadata().setName(name);

        //do we have an icon?
        if (metadata.containsKey("icon")) {
            kamelet.getMetadata().getAnnotations().put(CAMEL_APACHE_ORG_KAMELET_ICON,
                    metadata.remove("icon").toString());
        }

        //do we have a description?
        if (metadata.containsKey("description") && metadata.get("description") != null) {
            kamelet.getSpec().getDefinition().setDescription(metadata.remove("description").toString());
        }

        setSpecDependencies(kamelet.getSpec(), steps, metadata);
        setSpecDefinition(kamelet, parameters);
        template.setBeans((List<Bean>) metadata.getOrDefault("beans", null));
    }

    private void setSpecDefinition(final Kamelet kamelet,
                                   final List<Parameter> parameters) {
        if (kamelet.getSpec().getDefinition() == null) {
            kamelet.getSpec().setDefinition(new Definition());
            kamelet.getSpec().getDefinition().setTitle("");
        }
        var def = kamelet.getSpec().getDefinition();
        if (def.getProperties() == null) {
            def.setProperties(new LinkedHashMap<>());
        }
        setParameters(parameters, def);
    }

    private void setSpecDependencies(final KameletSpec spec,
                                     final List<Step> steps,
                                     final Map<String, Object> metadata) {

        spec.setDependencies((List<String>) metadata.getOrDefault("dependencies", new LinkedList<>()));

        if (spec.getDependencies() == null) {
            spec.setDependencies(new LinkedList<>());
        }

        var deps = spec.getDependencies();

        if (!deps.contains("camel:core")) {
            deps.add("camel:core");
        }

        for (Step s : steps) {
            var dep = s.getName();
            if (dep != null && s.getKind().equalsIgnoreCase("Camel-Connector")
                    && !dep.startsWith("kamelet")) {
                if (dep.contains(":")) {
                    dep = dep.substring(0, dep.indexOf(':'));
                }
                if (!deps.contains("camel:" + dep)) {
                    deps.add("camel:" + dep);
                }
            }
        }
    }

    private void setParameters(final List<Parameter> parameters, final Definition def) {
        for (Parameter p : parameters) {
            //this will override anything that comes from the metadata set
            //which means there are edited changes
            Properties property = new Properties();
            if (p.getDefaultValue() != null) {
                AnyType defaultValue = new AnyType();
                defaultValue.setValue(p.getDefaultValue());
                property.set_default(defaultValue);
            }
            property.setDescription(p.getDescription());
            if ("string".equalsIgnoreCase(p.getType())) {
                property.setFormat(((StringParameter) p).getFormat());
            }
            property.setTitle(p.getTitle());
            property.setType(p.getType());
            property.setNullable(p.getNullable());
            if (p.getEnum() != null) {
                var enumerable = new LinkedList<AnyType>();
                for (var enumy : p.getEnum()) {
                    var anyType = new AnyType();
                    anyType.setValue(enumy);
                    enumerable.add(anyType);
                }
                property.set_enum(enumerable);
            }
            if (p.getExamples() != null && p.getExamples().length > 0) {
                var example = new AnyType();
                example.setValue(p.getExamples()[0]);
                property.setExample(example);
            }
            def.getProperties().put(p.getId(), property);
        }
    }

    private void populateAnnotations(final Kamelet kamelet,
                                     final Map<String, String> annotations) {
        kamelet.getMetadata().setAnnotations(new LinkedHashMap<>());
        for (Map.Entry<String, String> entry : annotations.entrySet()) {
            kamelet.getMetadata().getAnnotations().put(entry.getKey(), entry.getValue());
        }

        kamelet.getMetadata().getAnnotations().remove("icon");

    }

    private void populateAdditionalProperties(final Kamelet kamelet,
                                              final Map<String, String> additionalProperties) {
        kamelet.getMetadata().setAdditionalProperties(new HashMap<>());
        for (var entry : additionalProperties.entrySet()) {
            kamelet.getMetadata().getAdditionalProperties().put(entry.getKey(), entry.getValue());
        }

    }

    private void populateLabels(final Kamelet kamelet,
                                final Map<String, String> labels) {
        kamelet.getMetadata().setLabels(new HashMap<>());
        for (Map.Entry<String, String> entry : labels.entrySet()) {
            kamelet.getMetadata().getLabels().put(entry.getKey(), entry.getValue());
        }
    }

    private Type defineType(final List<Step> steps) {
        // The code of a "source" Kamelet must send data to the kamelet:sink
        // special endpoint. The code of a "sink" Kamelet must consume data
        // from the special endpoint kamelet:source.
        // If it has both, it is an action.
        Type type = Type.action;
        if (steps.size() > 1) {
            boolean source = steps.get(0).getName().equalsIgnoreCase("kamelet:source");
            boolean sink = steps.get(steps.size() - 1).getName().equalsIgnoreCase("kamelet:sink");

            if (source && !sink) {
                type = Type.sink;
            } else if (!source && sink) {
                type = Type.source;
            }
        }
        return type;
    }

    public From getFlow(final List<Step> steps) {
        var from = new From();

        if (!steps.isEmpty()
                && "CAMEL-REST-DSL".equalsIgnoreCase(steps.get(0).getKind())) {
            from = new Rest(steps.get(0), catalog);
        } else {
            from.setSteps(new ArrayList<>());

            for (int i = 0; i < steps.size(); i++) {
                Step s = steps.get(i);
                if (i == 0 && "START".equals(s.getType())) {
                    var uri = new StringBuilder(s.getName());
                    HashMap<String, Object> params = buildUri(s, uri);
                    from.setUri(uri.toString());
                    from.setParameters(params);
                    from.setId(s.getStepId());
                } else {
                    final var flowStep = processStep(s, true);
                    from.getSteps().add(flowStep);
                }
            }
        }
        return from;
    }

    public HashMap<String, Object> buildUri(final Step s, final StringBuilder uri) {
        var params = new HashMap<String, Object>();
        if (s.getParameters() != null) {
            //We need to make sure parameters attributes coming from the frontend are complete and right
            //The only important thing we need to take from them is the value, the rest should be as default
            HashMap<String, Object> values = new HashMap<>();
            s.getParameters().stream().forEach(parameter -> values.put(parameter.getId(), parameter.getValue()));

            //these connectors ignore the camel component name when building the uri
            //or duplicate it, depending on where you are looking from
            var exceptions = Arrays.asList("http", "https");
            if (exceptions.contains(s.getName())) {
                s.getParameters().stream().forEach(parameter -> {
                    if (parameter.isPath()
                            && parameter.getPathOrder() == 0
                            && String.valueOf(parameter.getValue()).startsWith(s.getName())) {
                        values.put(parameter.getId(),
                                String.valueOf(parameter.getValue()).substring(s.getName().length() + 1));
                    }
                });
            }

            //Now we can work with the parameters list
            Step step = catalog.getReadOnlyCatalog().searchByID(s.getId());
            if (step != null) {
                Collections.sort(step.getParameters());
                for (Parameter p : step.getParameters()) {
                    var value = values.get(p.getId());
                    if (p.isPath()) {
                        if (p.getPathOrder() == 0) {
                            uri.append(":");
                        }
                        uri.append(p.getPathSeparator());
                        //Look for the right value
                        value = value != null ? value : p.getDefaultValue();
                        //If it is null, don't add it
                        if (value != null) {
                            uri.append(value);
                        }
                    } else if (value != null && !p.getId().equalsIgnoreCase("step-id-kaoto")
                            && !p.convertToType(value).equals(p.getDefaultValue())) {
                        params.put(p.getId(), p.convertToType(value));
                    }
                }
            }
        }
        return params;
    }

    /**
     * This implementation generates code "Using URI and parameters." as defined in the "Defining Endpoints" section of
     * https://camel.apache.org/camel-k/latest/languages/yaml.html
     **/
    private FlowStep processStep(final Step step, final boolean to) {
        FlowStep flowStep = null;

        if ("Camel-Connector".equalsIgnoreCase(step.getKind())) {
            flowStep = getCamelConnector(step, to);
        } else if ("EIP".equalsIgnoreCase(step.getKind())) {
            switch (step.getName()) {
                case "aggregate":
                    flowStep = new AggregateFlowStep(step);
                    break;
                case "claim-check":
                    flowStep = new ClaimCheckFlowStep(step);
                    break;
                case "convert-body-to":
                    flowStep = new ConvertBodyToFlowStep(step);
                    break;
                case "delay":
                    flowStep = new DelayFlowStep(step);
                    break;
                case "dynamic-router":
                    flowStep = new DynamicRouterFlowStep(step);
                    break;
                case "enrich":
                    flowStep = new EnrichFlowStep(step);
                    break;
                case "log":
                    flowStep = new LogFlowStep(step);
                    break;
                case "marshal":
                    flowStep = getMarshalStep(step);
                    break;
                case "remove-header":
                    flowStep = new RemoveHeaderFlowStep(getExpression(step));
                    break;
                case "remove-headers":
                    flowStep = new RemoveHeadersFlowStep(step);
                    break;
                case "remove-properties":
                    flowStep = new RemovePropertiesFlowStep(step);
                    break;
                case "remove-property":
                    flowStep = new RemovePropertyFlowStep(getExpression(step));
                    break;
                case "set-body":
                    flowStep = new SetBodyFlowStep(getExpression(step));
                    break;
                case "set-header":
                    flowStep = new SetHeaderFlowStep(getExpression(step));
                    break;
                case "set-property":
                    flowStep = new SetPropertyFlowStep(getExpression(step));
                    break;
                case "poll-enrich":
                    flowStep = new PollEnrichFlowStep(step);
                    break;
                case "process":
                    flowStep = new ProcessFlowStep(step);
                    break;
                case "recipient-list":
                    flowStep = new RecipientListFlowStep(step);
                    break;
                case "rollback":
                    flowStep = new RollbackFlowStep(step);
                    break;
                case "routing-slip":
                    flowStep = new RoutingSlipFlowStep(step);
                    break;
                case "sample":
                    flowStep = new SampleFlowStep(step);
                    break;
                case "script":
                    flowStep = new ScriptFlowStep(getScript(step));
                    break;
                case "service-call":
                    flowStep = new ServiceCallFlowStep(step);
                    break;
                case "sort":
                    flowStep = new SortFlowStep(step);
                    break;
                case "stop":
                    flowStep = new StopFlowStep();
                    break;
                case "set-exchange-pattern":
                    flowStep = new SetExchangePatternFlowStep(step);
                    break;
                case "threads":
                    flowStep = new ThreadsFlowStep(step);
                    break;
                case "throttle":
                    flowStep = new ThrottleFlowStep(step);
                    break;
                case "throw-exception":
                    flowStep = new ThrowExceptionFlowStep(step);
                    break;
                case "to-d":
                    flowStep = new ToDynamicFlowStep(step);
                    break;
                case "transacted":
                    flowStep = new TransactedFlowStep(step);
                    break;
                case "transform":
                    flowStep = new TransformFlowStep(getExpression(step));
                    break;
                case "unmarshal":
                    flowStep = getUnmarshalStep(step);
                    break;
                case "validate":
                    flowStep = new ValidateFlowStep(step);
                    break;
                default:
                    flowStep = getCamelConnector(step, to);
                    break;
            }
        } else if ("EIP-BRANCH".equalsIgnoreCase(step.getKind())) {
            switch (step.getName()) {
                case "circuit-breaker":
                    flowStep = new CircuitBreakerFlowStep(step, this);
                    break;
                case "choice":
                    flowStep = new ChoiceFlowStep(step, this);
                    break;
                case "do-try":
                    flowStep = new TryCatchFlowStep(step, this);
                    break;
                case "filter":
                    flowStep = new FilterFlowStep(step, this);
                    break;
                case "idempotent-consumer":
                    flowStep = new IdempotentConsumerFlowStep(step, this);
                    break;
                case "load-balance":
                    flowStep = new LoadBalanceFlowStep(step, this);
                    break;
                case "loop":
                    flowStep = new LoopFlowStep(step, this);
                    break;
                case "multicast":
                    flowStep = new MulticastFlowStep(step, this);
                    break;
                case "resequence":
                    flowStep = new ResequenceFlowStep(step, this);
                    break;
                case "saga":
                    flowStep = new SagaFlowStep(step, this);
                    break;
                case "split":
                    flowStep = new SplitFlowStep(step, this);
                    break;
                case "pipeline":
                    flowStep = new PipelineFlowStep(step, this);
                    break;
                case "wire-tap":
                    flowStep = new WireTapFlowStep(step, this);
                    break;
                default:
                    flowStep = getCamelConnector(step, to);
                    break;
            }
        } else {
            log.error("We don't recognize this kind of step: " + step.getKind());
        }

        return flowStep;
    }

    public List<FlowStep> processSteps(final Branch b) {
        final var list = new LinkedList<FlowStep>();
        if (b.getSteps() != null) {
            b.getSteps().stream()
                    //make sure we don't try to process null steps from the branch
                    .filter(s -> !Objects.isNull(s))
                    .map(s -> processStep(s, true))
                    //make sure we don't add null steps, the unrecognized ones
                    .filter(s -> !Objects.isNull(s))
                    .forEachOrdered(step -> list.add(step));
        }
        return list;
    }

    private FlowStep getMarshalStep(final Step step) {
        var marshal = new MarshalFlowStep();
        assignParameters(step, marshal);
        return marshal;
    }

    private FlowStep getUnmarshalStep(final Step step) {
        var marshal = new UnmarshalFlowStep();
        assignParameters(step, marshal);
        return marshal;
    }

    private void assignParameters(final Step step, final MarshalFlowStep marshal) {
        for (Parameter p : step.getParameters()) {
            if (null != p.getValue() && "dataformat".equalsIgnoreCase(p.getId())) {
                marshal.setDataFormat(new DataFormat(p.getValue()));
            }
        }
    }

    public FlowStep getCamelConnector(final Step step, final boolean to) {
        var uri = new StringBuilder(step.getName());
        var params = buildUri(step, uri);
        FlowStep flowStep = new UriFlowStep(uri.toString(), params, null);
        if (to) {
            flowStep = new ToFlowStep(flowStep);
        }
        return flowStep;
    }

    enum Type {source, sink, action}
}
