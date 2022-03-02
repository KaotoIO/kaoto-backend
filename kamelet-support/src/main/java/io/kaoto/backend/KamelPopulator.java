package io.kaoto.backend;

import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.kaoto.backend.model.deployment.kamelet.Expression;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.model.deployment.kamelet.Kamelet;
import io.kaoto.backend.model.deployment.kamelet.KameletSpec;
import io.kaoto.backend.model.deployment.kamelet.Template;
import io.kaoto.backend.model.deployment.kamelet.step.ChoiceFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.From;
import io.kaoto.backend.model.deployment.kamelet.step.SetBodyFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.SetHeaderFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.ToFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.UriFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.choice.Choice;
import io.kaoto.backend.model.deployment.kamelet.step.choice.SuperChoice;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Branch;
import io.kaoto.backend.model.step.Step;
import org.jboss.logging.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class KamelPopulator {

    public static final String CONDITION = "condition";
    public static final String SIMPLE = "simple";
    public static final String CONSTANT = "constant";
    public static final String NAME = "name";
    public static final String CAMEL_APACHE_ORG_KAMELET_ICON =
            "camel.apache.org/kamelet.icon";
    private final String group = "camel.apache.org";
    private Logger log = Logger.getLogger(KamelPopulator.class);

    public void populateKamelet(
            final Kamelet kamelet,
            final Map<String, Object> metadata,
            final List<Step> steps) {

        kamelet.setSpec(new KameletSpec());
        kamelet.getSpec().setTemplate(new Template());
        kamelet.getSpec().getTemplate().setFrom(getFlow(steps));

        kamelet.setMetadata(new ObjectMeta());
        populateLabels(kamelet, (Map<String, String>) metadata.getOrDefault(
                "labels",
                Collections.emptyMap()));
        populateAdditionalProperties(kamelet,
                (Map<String, String>) metadata.getOrDefault(
                "additionalProperties",
                Collections.emptyMap()));
        populateAnnotations(kamelet,
                (Map<String, String>) metadata.getOrDefault(
                        "annotations",
                        Collections.emptyMap()));

        //override in case this is outdated from the graphic side
        Type type = defineType(steps);
        kamelet.getMetadata().getLabels().put(group + "/kamelet.type",
                type.name());

        //consistent naming for kamelets
        String name = metadata.getOrDefault(NAME, "").toString();
        if (!name.endsWith(type.name())) {
            name = name + "-" + type.name();
        }
        kamelet.getMetadata().setName(name);

        //do we have an icon?
        if (kamelet.getMetadata().getAnnotations().getOrDefault(
                CAMEL_APACHE_ORG_KAMELET_ICON, "").isBlank()) {
            kamelet.getMetadata().getAnnotations().put(
                    CAMEL_APACHE_ORG_KAMELET_ICON,
                    metadata.getOrDefault("icon", "").toString());
        }
    }

    private void populateAnnotations(final Kamelet kamelet,
                                     final Map<String, String> annotations) {
        kamelet.getMetadata().setAnnotations(new HashMap<>());
        for (Map.Entry<String, String> entry : annotations.entrySet()) {
            kamelet.getMetadata().getAnnotations().put(entry.getKey(),
                    entry.getValue());
        }

    }

    private void populateAdditionalProperties(final Kamelet kamelet,
                            final Map<String, String> additionalProperties) {
        kamelet.getMetadata().setAdditionalProperties(new HashMap<>());
        for (var entry : additionalProperties.entrySet()) {
            kamelet.getMetadata().getAdditionalProperties()
                    .put(entry.getKey(),
                            entry.getValue());
        }

    }

    private void populateLabels(final Kamelet kamelet,
                                final Map<String, String> labels) {
        kamelet.getMetadata().setLabels(new HashMap<>());
        for (Map.Entry<String, String> entry : labels.entrySet()) {
            kamelet.getMetadata().getLabels().put(entry.getKey(),
                    entry.getValue());

        }
    }

    private Type defineType(final List<Step> steps) {
        // The code of a "source" Kamelet must send data to the kamelet:sink
        // special endpoint. The code of a "sink" Kamelet must consume data
        // from the special endpoint kamelet:source.
        // If it has both, it is an action.
        Type type = Type.action;
        if (steps.size() > 1) {
            boolean source = steps.get(0).getName()
                    .equalsIgnoreCase("kamelet:source");
            boolean sink =
                    steps.get(steps.size() - 1).getName().equalsIgnoreCase(
                            "kamelet:sink");

            if (source && !sink) {
                type = Type.sink;
            } else if (!source && sink) {
                type = Type.source;
            }
        }
        return type;
    }

    private From getFlow(final List<Step> steps) {
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
                    uri.append((p.getValue() != null ? p.getValue()
                            : p.getDefaultValue()));
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
                default:
                    break;
            }
        } else  if ("EIP-BRANCH".equalsIgnoreCase(step.getKind())) {
            switch (step.getName()) {
                case "choice":
                    flowStep = getChoiceStep(step);
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
            if (b.containsKey(CONDITION)) {
                choices.add(processChoice(b));
            } else if (choice.getOtherwise() == null) {
                choice.setOtherwise(processChoice(b).getSteps());
            } else {
                choices.add(processChoice(b));
            }
        }
        choice.setChoice(choices);

        return flowStep;
    }

    private Choice processChoice(final Branch b) {
        Choice choice = new Choice();

        choice.setSteps(new LinkedList<>());
        for (Step step : b.getSteps()) {
            if (step != null) {
                choice.getSteps().add(processStep(step, true));
            }
        }

        if (b.containsKey(CONDITION)) {
            choice.setSimple(b.get(CONDITION).toString());
        }

        return choice;
    }

    private FlowStep getSetBodyStep(final Step step) {
        Expression expression = new Expression(null, null);

        for (Parameter p : step.getParameters()) {
            if (p.getValue() == null) {
                continue;
            }
            if (SIMPLE.equalsIgnoreCase(p.getId())) {
                expression.setSimple(p.getValue().toString());
            } else if (CONSTANT.equalsIgnoreCase(p.getId())) {
                expression.setConstant(p.getValue().toString());
            }
        }

        return new SetBodyFlowStep(expression);
    }

    private FlowStep getSetHeaderStep(final Step step) {
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

        return new SetHeaderFlowStep(expression);
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
