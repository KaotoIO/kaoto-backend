package io.kaoto.backend;

import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.kaoto.backend.model.deployment.kamelet.Bean;
import io.kaoto.backend.model.deployment.kamelet.Expression;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.model.deployment.kamelet.Kamelet;
import io.kaoto.backend.model.deployment.kamelet.KameletDefinition;
import io.kaoto.backend.model.deployment.kamelet.KameletDefinitionProperty;
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
import io.kaoto.backend.model.deployment.kamelet.step.UnmarshalFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.UriFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.choice.Choice;
import io.kaoto.backend.model.deployment.kamelet.step.choice.Otherwise;
import io.kaoto.backend.model.deployment.kamelet.step.choice.SuperChoice;
import io.kaoto.backend.model.deployment.kamelet.step.dataformat.DataFormat;
import io.kaoto.backend.model.parameter.ArrayParameter;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.parameter.StringParameter;
import io.kaoto.backend.model.step.Branch;
import io.kaoto.backend.model.step.Step;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class KamelPopulator {

    public static final String SIMPLE = "simple";
    public static final String CONSTANT = "constant";
    public static final String NAME = "name";
    public static final String CAMEL_APACHE_ORG_KAMELET_ICON = "camel.apache.org/kamelet.icon";
    private final String group = "camel.apache.org";

    public void populateKamelet(
            final Kamelet kamelet,
            final Map<String, Object> metadata,
            final List<Step> steps,
            final List<Parameter> parameters) {

        kamelet.setSpec(new KameletSpec());
        kamelet.getSpec().setTemplate(new Template());
        kamelet.getSpec().getTemplate().setFrom(getFlow(steps));
        if (metadata.containsKey("definition")) {
            if (metadata.get("definition") instanceof KameletDefinition def) {
                kamelet.getSpec().setDefinition(def);
            } else if (metadata.get("definition") instanceof HashMap map) {
                KameletDefinition def = new KameletDefinition();
                def.setTitle(map.getOrDefault("title", "").toString());
                def.setDescription(map.getOrDefault("description", "").toString());
                def.setRequired((List<String>) map.getOrDefault("required", null));
                def.setProperties((Map<String, KameletDefinitionProperty>) map.getOrDefault("required", null));
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
        if (kamelet.getMetadata().getAnnotations().getOrDefault(CAMEL_APACHE_ORG_KAMELET_ICON, "").isBlank()) {
            kamelet.getMetadata().getAnnotations().put(CAMEL_APACHE_ORG_KAMELET_ICON,
                    metadata.getOrDefault("icon", "").toString());
        }

        setSpecDependencies(kamelet.getSpec(), steps, metadata);
        setSpecDefinition(kamelet, parameters);
        kamelet.getSpec().getTemplate().setBeans((List<Bean>) metadata.getOrDefault("beans", null));
    }

    private void setSpecDefinition(final Kamelet kamelet,
                                   final List<Parameter> parameters) {
        if (kamelet.getSpec().getDefinition() == null) {
            kamelet.getSpec().setDefinition(new KameletDefinition());
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
            var dep = s.getId();
            if (dep != null && s.getKind().equalsIgnoreCase("Camel-Connector")) {
                if (dep.contains(":")) {
                    dep = dep.substring(0, dep.indexOf(':'));
                }
                if (!deps.contains("camel:" + dep)) {
                    deps.add("camel:" + dep);
                }
            }
        }
    }

    private void setParameters(final List<Parameter> parameters,
                           final KameletDefinition def) {
        for (Parameter p : parameters) {
            //this will override anything that comes from the metadata set
            //which means there are edited changes
            KameletDefinitionProperty property =
                    new KameletDefinitionProperty();
            if (p.getDefaultValue() != null) {
                property.setDefault(p.getDefaultValue().toString());
            }
            property.setDescription(p.getDescription());
            if ("string".equalsIgnoreCase(p.getType())) {
                property.setFormat(((StringParameter) p).getFormat());
            }
            property.setPath(false);
            property.setTitle(p.getTitle());
            property.setType(p.getType());
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
        final var from = new From();
        from.setSteps(new ArrayList<>());

        for (Step s : steps) {
            if (from.getUri() == null) {
                var uri = new StringBuilder(s.getName());
                HashMap<String, String> params = buildUri(s, uri);
                from.setUri(uri.toString());
                from.setParameters(params);
            } else {
                from.getSteps().add(processStep(s, true));
            }
        }

        return from;
    }

    private HashMap<String, String> buildUri(
            final Step s,
            final StringBuilder uri) {
        var params = new HashMap<String, String>();
        if (s.getParameters() != null) {
            for (Parameter p : s.getParameters()) {
                if (p.isPath()) {
                    uri.append(":");
                    uri.append(p.getValue() != null ? p.getValue() : p.getDefaultValue());
                } else if (p.getValue() != null) {
                    params.put(p.getId(), p.getValue().toString());
                }
            }
        }
        return params;
    }

    /** This implementation generates code "Using URI and parameters." as
     * defined in the "Defining Endpoints" section of
     * https://camel.apache.org/camel-k/1.6.x/languages/yaml.html
     **/
    private FlowStep processStep(final Step step, final boolean to) {
        FlowStep flowStep = null;

        if ("Camel-Connector".equalsIgnoreCase(step.getKind())) {
            flowStep = getCamelConnector(step, to);
        } else  if ("EIP".equalsIgnoreCase(step.getKind())) {
            switch (step.getName()) {
                case "set-body":
                    flowStep = getSetBodyStep(step);
                    break;
                case "set-header":
                    flowStep = getSetHeaderStep(step);
                    break;
                case "remove-header":
                    flowStep = getRemoveHeaderStep(step);
                    break;
                case "set-property":
                    flowStep = getSetPropertyStep(step);
                    break;
                case "remove-property":
                    flowStep = getRemovePropertyStep(step);
                    break;
                case "transform":
                    flowStep = getTransformStep(step);
                    break;
                case "marshal":
                    flowStep = getMarshalStep(step);
                    break;
                case "unmarshal":
                    flowStep = getUnmarshalStep(step);
                    break;
                default:
                    break;
            }
        } else  if ("EIP-BRANCH".equalsIgnoreCase(step.getKind())) {
            switch (step.getName()) {
                case "choice":
                    flowStep = getChoiceStep(step);
                    break;
                case "filter":
                    flowStep = getFilterStep(step);
                    break;
                default:
                    break;
            }
        }

        return flowStep;
    }

    private FlowStep getChoiceStep(final Step step) {

        final var choice = new SuperChoice();
        final var flowStep = new ChoiceFlowStep(choice);

        List<Choice> choices = new LinkedList<>();

        for (Branch b : step.getBranches()) {
            if (b.getCondition() != null) {
                choices.add(processChoice(b));
            } else {
                var otherwise = new Otherwise();
                otherwise.setSteps(processSteps(b));
                choice.setOtherwise(otherwise);
            }
        }
        choice.setChoice(choices);

        return flowStep;
    }

    private Choice processChoice(final Branch b) {
        Choice choice = new Choice();

        choice.setSteps(processSteps(b));
        choice.setSimple(b.getCondition());

        return choice;
    }

    private List<FlowStep> processSteps(final Branch b) {
        var list = new LinkedList<FlowStep>();
        for (Step step : b.getSteps()) {
            if (step != null) {
                list.add(processStep(step, true));
            }
        }
        return list;
    }

    private FlowStep getSetBodyStep(final Step step) {
        return new SetBodyFlowStep(getExpression(step));
    }
    private FlowStep getSetPropertyStep(final Step step) {
        return new SetPropertyFlowStep(getExpression(step));
    }
    private FlowStep getRemovePropertyStep(final Step step) {
        return new RemovePropertyFlowStep(getExpression(step));
    }

    private FlowStep getSetHeaderStep(final Step step) {
        return new SetHeaderFlowStep(getExpression(step));
    }

    private FlowStep getRemoveHeaderStep(final Step step) {
        return new RemoveHeaderFlowStep(getExpression(step));
    }

    private Expression getExpression(final Step step) {
        Expression expression = new Expression(null, null);
        for (Parameter p : step.getParameters()) {
            if (p.getValue() == null) {
                continue;
            }
           if (NAME.equalsIgnoreCase(p.getId())) {
               expression.setName(p.getValue().toString());
           } else if (SIMPLE.equalsIgnoreCase(p.getId())) {
               expression.setSimple(p.getValue().toString());
           } else if (CONSTANT.equalsIgnoreCase(p.getId())) {
               expression.setConstant(p.getValue().toString());
           }
        }
        return expression;
    }

    private FlowStep getTransformStep(final Step step) {
        return new TransformFlowStep(getExpression(step));
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
        marshal.setDataFormat(new DataFormat());
        marshal.getDataFormat().setProperties(new HashMap<>());

        for (Parameter p : step.getParameters()) {
            if ("dataformat".equalsIgnoreCase(p.getId())) {
                marshal.getDataFormat().setFormat(p.getValue().toString());
            } else if ("properties".equalsIgnoreCase(p.getId())) {
                for (var param : ((ArrayParameter) p).getValue()) {
                    if (param instanceof Object[] array) {
                        marshal.getDataFormat().getProperties().put(array[0].toString(), array[1].toString());
                    } else if (param instanceof List list) {
                        marshal.getDataFormat().getProperties().put(list.get(0).toString(), list.get(1).toString());
                    }
                }
            }
        }
    }

    private FlowStep getFilterStep(final Step step) {
        Branch b = step.getBranches().get(0);
        return new FilterFlowStep(processFilter(b));
    }

    private Filter processFilter(final Branch b) {
        Filter filter = new Filter();

        filter.setSteps(processSteps(b));
        filter.setSimple(b.getCondition());

        return filter;
    }

    private FlowStep getCamelConnector(final Step step, final boolean to) {
        var uri = new StringBuilder(step.getName());
        var params = buildUri(step, uri);
        FlowStep flowStep = new UriFlowStep(uri.toString(), params);
        if (to) {
            flowStep = new ToFlowStep(flowStep);
        }
        return flowStep;
    }

    enum Type { source, sink, action }
}
