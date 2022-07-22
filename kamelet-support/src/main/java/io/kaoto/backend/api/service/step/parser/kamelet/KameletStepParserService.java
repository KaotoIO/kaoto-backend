package io.kaoto.backend.api.service.step.parser.kamelet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.step.parser.StepParserService;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.model.deployment.kamelet.Kamelet;
import io.kaoto.backend.model.deployment.kamelet.KameletDefinitionProperty;
import io.kaoto.backend.model.deployment.kamelet.KameletSpec;
import io.kaoto.backend.model.deployment.kamelet.step.ChoiceFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.SetBodyFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.SetHeaderFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.ToFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.UriFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.choice.Choice;
import io.kaoto.backend.model.parameter.ArrayParameter;
import io.kaoto.backend.model.parameter.BooleanParameter;
import io.kaoto.backend.model.parameter.IntegerParameter;
import io.kaoto.backend.model.parameter.NumberParameter;
import io.kaoto.backend.model.parameter.ObjectParameter;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.parameter.StringParameter;
import io.kaoto.backend.model.step.Branch;
import io.kaoto.backend.model.step.Step;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 🐱miniclass KameletStepParserService (StepParserService)
 */
@ApplicationScoped
public class KameletStepParserService
        implements StepParserService<Step> {

    public static final String CONDITION = "condition";
    public static final String SIMPLE = "simple";
    public static final String OTHERWISE = "otherwise";
    private final Logger log =
            Logger.getLogger(KameletStepParserService.class);

    private StepCatalog catalog;

    public String identifier() {
        return "Kamelet";
    }

    public String description() {
        return "A Kamelet is a snippet of a route. It defines meta building "
                + "blocks or steps that can be reused on integrations.";
    }

    @Inject
    public void setCatalog(final StepCatalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public ParseResult<Step> deepParse(final String input) {
        if (!appliesTo(input)) {
            throw new IllegalArgumentException(
                    "Wrong format provided. This is not parseable by us.");
        }

        ParseResult<Step> res = new ParseResult<>();

        List<Step> steps = new ArrayList<>();
        try {
            ObjectMapper yamlMapper =
                    new ObjectMapper(new YAMLFactory())
                            .configure(
                              DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                              false);
            Kamelet kamelet = yamlMapper.readValue(input,
                    Kamelet.class);

            processMetadata(res, kamelet.getMetadata());
            processSpec(steps, res, kamelet.getSpec());
            processParameters(res, kamelet.getSpec());

        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(
                    "Error trying to parse.", e);
        }

        res.setSteps(steps.stream()
                .filter(Objects::nonNull)
                .toList());
        return res;
    }

    private void processParameters(final ParseResult<Step> res,
                                   final KameletSpec spec) {
        res.setParameters(new ArrayList<>());
        if (spec.getDefinition() != null
                && spec.getDefinition().getProperties() != null) {
            Map<String, KameletDefinitionProperty> props =
                    spec.getDefinition().getProperties();

            for (var prop : props.entrySet()) {
                String key = prop.getKey();
                var def = prop.getValue();
                Parameter p = new ObjectParameter();
                switch (def.getType()) {
                    case "string":
                        p = new StringParameter(key, def.getTitle(),
                        def.getDescription(), def.getDefault(),
                                def.getFormat());
                        break;
                    case "number":
                        p = new NumberParameter(key, def.getTitle(),
                        def.getDescription(), Double.valueOf(def.getDefault()));
                        break;
                    case "integer":
                        p = new IntegerParameter(key, def.getTitle(),
                                def.getDescription(),
                                (def.getDefault() != null
                                        ? Integer.valueOf(def.getDefault())
                                        : null));
                        break;
                    case "boolean":
                        p = new BooleanParameter(key, def.getTitle(),
                                def.getDescription(),
                                (def.getDefault() != null
                                        ? Boolean.valueOf(def.getDefault())
                                        : null));
                        break;
                    case "array":
                        p = new ArrayParameter(key, def.getTitle(),
                                def.getDescription(),
                                (def.getDefault() != null
                                    ? def.getDefault().split(",") : null));
                        break;
                    default:
                        p = new ObjectParameter(key, def.getTitle(),
                                def.getDescription(), def.getDefault());
                }
                res.getParameters().add(p);
            }
        }
    }

    private void processSpec(final List<Step> steps,
                             final ParseResult<Step> res,
                             final KameletSpec spec) {
        if (spec.getTemplate() != null
                && spec.getTemplate().getFrom() != null) {
            steps.add(processStep(spec.getTemplate().getFrom()));

            if (spec.getTemplate().getFrom().getSteps() != null) {
                for (FlowStep flowStep : spec.getTemplate().getFrom()
                        .getSteps()) {
                    steps.add(processStep(flowStep));
                }
            }
        }

        res.getMetadata().put("definition", spec.getDefinition());
        res.getMetadata().put("beans", spec.getTemplate().getBeans());
        res.getMetadata().put("dependencies", spec.getDependencies());
    }

    //there must be a more elegant solution
    //but a visitor sounds like overengineering
    public Step processStep(final FlowStep step) {
        if (step instanceof ChoiceFlowStep choiceFlowStep) {
            return processDefinedStep(choiceFlowStep);
        } else if (step instanceof ToFlowStep toFlowStep) {
            return processDefinedStep(toFlowStep);
        } else if (step instanceof UriFlowStep uriFlowStep) {
            return processDefinedStep(uriFlowStep);
        } else if (step instanceof SetBodyFlowStep setBodyFlowStep) {
            return processDefinedStep(setBodyFlowStep);
        } else if (step instanceof SetHeaderFlowStep setHeaderFlowStep) {
            return processDefinedStep(setHeaderFlowStep);
        } else {
            log.warn("Unrecognized step -> " + step);
            return null;
        }
    }

    private Step processDefinedStep(final ChoiceFlowStep choice) {
        Step res = catalog.getReadOnlyCatalog().searchStepByID("choice");
        res.setBranches(new LinkedList<>());

        try {
            for (var flow : choice.getChoice().getChoice()) {
                Branch branch =
                        new Branch(getChoiceIdentifier(flow));
                branch.put(CONDITION, getChoiceCondition(flow));
                for (var s : flow.getSteps()) {
                    branch.getSteps().add(processStep(s));
                }
                setValueOnStepProperty(res, SIMPLE, branch.get(CONDITION));
                res.getBranches().add(branch);
            }

            if (choice.getChoice().getOtherwise() != null) {
                Branch branch = new Branch(OTHERWISE);
                for (var s : choice.getChoice().getOtherwise()) {
                    branch.getSteps().add(processStep(s));
                }
                res.getBranches().add(branch);
            }
        } catch (Exception e) {
            log.warn("Can't parse step -> " + e.getMessage());
        }

        return res;
    }

    private String getChoiceIdentifier(final Choice flow) {
        return flow.getSimple();
    }

    private String getChoiceCondition(final Choice flow) {
        return flow.getSimple();
    }

    private Step processDefinedStep(final ToFlowStep step) {
        return processStep(step.getTo());
    }

    private Step processDefinedStep(final UriFlowStep uriFlowStep) {
        String uri = uriFlowStep.getUri();
        String connectorName = uri;
        log.trace("Parsing " + uri);

        if (uri != null
                && uri.contains(":")
                && !uri.startsWith("kamelet:")) {
            connectorName = uri.substring(0, uri.indexOf(":"));
        }
        log.trace("Found connector " + connectorName);

        Step step = catalog.getReadOnlyCatalog()
                .searchStepByName(connectorName);

        if (step != null && uri != null) {
            log.trace("Found step " + step.getName());
            setValuesOnParameters(step, uri);
            setValuesOnParameters(step, uriFlowStep.getParameters());
        }

        return step;
    }

    private Step processDefinedStep(final SetBodyFlowStep step) {
        Step res = catalog.getReadOnlyCatalog().searchStepByName("set-body");

        for (var p : res.getParameters()) {
            if (p.getId().equalsIgnoreCase("simple")) {
                p.setValue(step.getSetBody().getSimple());
            } else if (p.getId().equalsIgnoreCase("constant")) {
                p.setValue(step.getSetBody().getConstant());
            } else if (p.getId().equalsIgnoreCase("name")) {
                p.setValue(step.getSetBody().getName());
            }
        }

        return res;
    }


    private Step processDefinedStep(final SetHeaderFlowStep step) {
        Step res = catalog.getReadOnlyCatalog().searchStepByName("set-header");


        for (Parameter p : res.getParameters()) {
            if (p.getId().equalsIgnoreCase("name")) {
                p.setValue(step.getSetHeaderPairFlowStep().getName());
            } else if (p.getId().equalsIgnoreCase(SIMPLE)) {
                p.setValue(step.getSetHeaderPairFlowStep().getSimple());
            } else if (p.getId().equalsIgnoreCase("constant")) {
                p.setValue(step.getSetHeaderPairFlowStep().getConstant());
            }
        }


        return res;
    }

    private void setValuesOnParameters(final Step step,
                                       final String uri) {

        String path = uri.substring(uri.indexOf(":") + 1);
        if (path.contains("?")) {
            path = path.substring(0, path.indexOf("?"));
        }

        for (Parameter p : step.getParameters()) {
            if (p.isPath()) {
                p.setValue(path);
            }
        }

        Pattern pattern = Pattern.compile(
                "[?&]([^=]+)=([^&\\n]+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(uri);

        while (matcher.find()) {
            setValueOnStepProperty(step, matcher.group(1), matcher.group(2));
        }
    }

    private void setValuesOnParameters(final Step step,
                                       final Map<String, String> properties) {

        if (properties != null) {
            for (Map.Entry<String, String> c : properties.entrySet()) {
                setValueOnStepProperty(step, c.getKey(), c.getValue());
            }
        }

    }

    private void setValueOnStepProperty(final Step step, final String key,
                                        final Object value) {
        for (Parameter p : step.getParameters()) {
            if (p.getId().equalsIgnoreCase(key)) {
                p.setValue(value);
                break;
            }
        }
    }

    private void processMetadata(
            final ParseResult<Step> result,
            final ObjectMeta metadata) {
        result.setMetadata(new LinkedHashMap<>());

        var labels = new LinkedHashMap<String, String>();
        result.getMetadata().put("labels", labels);
        if (metadata.getLabels() != null) {
            labels.putAll(metadata.getLabels());
        }

        var annotations = new LinkedHashMap<String, String>();
        result.getMetadata().put("annotations", annotations);
        if (metadata.getAnnotations() != null) {
            annotations.putAll(metadata.getAnnotations());
            annotations.put("icon",
                    annotations.get("camel.apache.org/kamelet.icon"));
        }

        var additionalProperties = new LinkedHashMap<String, Object>();
        result.getMetadata().put("additionalProperties", additionalProperties);
        if (metadata.getAdditionalProperties() != null) {
            additionalProperties.putAll((Map<String, Object>)
                    metadata.getAdditionalProperties()
                            .getOrDefault("additionalProperties",
                                Collections.emptyMap()));
        }

        result.getMetadata().put("name", metadata.getName());
    }

    @Override
    public boolean appliesTo(final String yaml) {
        String[] kinds = new String[]{
                "Kamelet", "Knative", "Camel-Connector", "EIP", "EIP-BRANCH"};

        Pattern pattern = Pattern.compile(
                "(\nkind:)(.+)\n", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(yaml);
        if (matcher.find()) {
            return Arrays.stream(kinds).anyMatch(
                    k -> k.equalsIgnoreCase(matcher.group(2).trim()));
        }

        return false;
    }

}
