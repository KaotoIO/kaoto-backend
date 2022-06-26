package io.kaoto.backend.api.service.step.parser.kamelet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.step.parser.StepParserService;
import io.kaoto.backend.model.deployment.kamelet.KameletBinding;
import io.kaoto.backend.model.deployment.kamelet.KameletBindingSpec;
import io.kaoto.backend.model.deployment.kamelet.KameletBindingStep;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 🐱miniclass KameletBindingStepParserService (StepParserService)
 */
@ApplicationScoped
public class KameletBindingStepParserService
        implements StepParserService<Step> {

    private Logger log =
            Logger.getLogger(KameletBindingStepParserService.class);

    private StepCatalog catalog;

    public String identifier() {
        return "KameletBinding";
    }

    public String description() {
        return "Kamelet Bindings are used to create simple integrations that "
                + "link a start step to an end step with optional "
                + "intermediate action steps.";
    }

    @Inject
    public void setCatalog(final StepCatalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public ParseResult<Step> deepParse(final String input) {
        if (!appliesTo(input)) {
            throw new IllegalArgumentException(
                    "Wrong format provided. This is not parseable by us");
        }

        ParseResult<Step> res = new ParseResult<>();

        List<Step> steps = new ArrayList<>();
        Map<String, Object> md = new LinkedHashMap<>();
        try {
            ObjectMapper yamlMapper =
                    new ObjectMapper(new YAMLFactory())
                    .configure(
                            DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                            false);
            KameletBinding binding = yamlMapper.readValue(input,
                    KameletBinding.class);
            processMetadata(md, binding.getMetadata());
            processSpec(steps, binding.getSpec());

        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(
                    "Wrong format provided. This is not parseable by us");
        }

        res.setSteps(steps.stream()
                .filter(Objects::nonNull)
                .toList());
        res.setMetadata(md);
        return res;
    }

    private void processSpec(final List<Step> steps,
                             final KameletBindingSpec spec) {
        steps.add(processStep(spec.getSource()));

        if (spec.getSteps() != null) {
            for (KameletBindingStep intermediateStep : spec.getSteps()) {
                steps.add(processStep(intermediateStep));
            }
        }

        steps.add(processStep(spec.getSink()));
    }

    private Step processStep(final KameletBindingStep bindingStep) {
        Step step = null;

        try {

            if (bindingStep.getUri() != null) {
                log.trace("Found uri component.");
                String uri = bindingStep.getUri();
                step = catalog.getReadOnlyCatalog()
                        .searchStepByName(uri.substring(0, uri.indexOf(":")));
                if (step != null) {
                    log.trace("Found step " + step.getName());
                    setValuesOnParameters(step, uri);
                }
            } else if (bindingStep.getRef() != null) {
                log.trace("Found ref component.");
                if (bindingStep.getRef().getApiVersion().equalsIgnoreCase(
                        "eventing.knative.dev/v1")) {
                    step = catalog.getReadOnlyCatalog()
                            .searchStepByName("knative");
                    for (Parameter p : new ArrayList<>(step.getParameters())) {
                        if (p.getId().equalsIgnoreCase("kind")) {
                            p.setValue(bindingStep.getRef().getKind());
                        }
                        if (p.getId().equalsIgnoreCase("name")) {
                            p.setValue(bindingStep.getRef().getName());
                        }
                    }

                } else {
                    step = catalog.getReadOnlyCatalog()
                            .searchStepByName(bindingStep.getRef().getName());
                }

                if (step != null) {
                    log.trace("Found step " + step.getName());
                    setValuesOnParameters(step, bindingStep.getProperties());
                }
            }
        } catch (Exception e) {
            log.warn("Can't parse step -> " + e.getMessage());
        }

        return step;
    }

    private void setValuesOnParameters(final Step step,
                                       final Map<String, String> properties) {

        for (Map.Entry<String, String> c : properties.entrySet()) {
            for (Parameter p : step.getParameters()) {
                if (p.getId().equalsIgnoreCase(c.getKey())) {
                    p.setValue(c.getValue());
                    break;
                }
            }
        }

    }

    private void setValuesOnParameters(final Step step,
                                       final String uri) {

        String path = uri.substring(uri.indexOf(":") + 1);
        if (path.indexOf("?") > -1) {
            path = path.substring(0, path.indexOf("?"));
        }

        for (Parameter p : step.getParameters()) {
            if (p.isPath()) {
                p.setValue(path);
            }
        }

        Pattern pattern = Pattern.compile(
                "(?:\\?|\\&)([^=]+)\\=([^&\\n]+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(uri);

        while (matcher.find()) {
            String key = matcher.group(1);
            String value = matcher.group(2);

            for (Parameter p : step.getParameters()) {
                if (p.getId().equalsIgnoreCase(key)) {
                    p.setValue(value);
                    break;
                }
            }
        }
    }

    private void processMetadata(final Map<String, Object> res,
            final ObjectMeta metadata) {
        res.put("name", metadata.getName());
        res.put("additionalProperties", metadata.getAdditionalProperties());
        res.put("finalizers", metadata.getFinalizers());
        res.put("managedFields", metadata.getManagedFields());
        res.put("annotations", metadata.getAnnotations());
    }

    @Override
    public boolean appliesTo(final String yaml) {
        return yaml.contains("kind: KameletBinding");
    }

}
