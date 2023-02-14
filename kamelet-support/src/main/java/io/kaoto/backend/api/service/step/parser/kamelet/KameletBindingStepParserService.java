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
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
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

    private static final String CAMEL_CONNECTOR = "CAMEL-CONNECTOR";
    private static final String KAMELET = "KAMELET";
    private static final String KNATIVE = "KNATIVE";
    private static final List<String> KINDS = Arrays.asList(KAMELET, KNATIVE, CAMEL_CONNECTOR);

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
            throw new IllegalArgumentException("Wrong format provided. This is not parseable by us");
        }

        ParseResult<Step> res = new ParseResult<>();

        List<Step> steps = new ArrayList<>();
        Map<String, Object> md = new LinkedHashMap<>();
        try {
            ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory())
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            KameletBinding binding = yamlMapper.readValue(input, KameletBinding.class);
            processMetadata(md, binding.getMetadata());
            processSpec(steps, binding.getSpec());

        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Wrong format provided. This is not parseable by us");
        }

        res.setSteps(steps.stream().filter(Objects::nonNull).toList());
        res.setMetadata(md);
        res.setParameters(Collections.emptyList());
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
        Optional<Step> step = Optional.empty();

        try {

            if (bindingStep.getUri() != null) {
                log.trace("Found uri component. Probably a Camel Conector.");
                String uri = bindingStep.getUri();
                step = catalog.getReadOnlyCatalog()
                        .searchByName(uri.substring(0, uri.indexOf(":")))
                        .stream()
                        .filter(s -> KINDS.stream().anyMatch(k -> s.getKind().equalsIgnoreCase(k)))
                        .sorted(Comparator.comparing(
                                s -> KINDS.indexOf(((Step) s).getKind().toUpperCase(Locale.ROOT))).reversed())
                        .findFirst();

                if (step.isPresent()) {
                    setValuesOnParameters(step.get(), uri);
                }
            } else if (bindingStep.getRef() != null) {
                log.trace("Found ref component.");

                var name = bindingStep.getRef().getName();
                var kind = bindingStep.getRef().getKind();

                if (bindingStep.getRef().getApiVersion().contains("knative")) {
                    name = "knative";
                    kind = "";
                }

                var candidates = catalog.getReadOnlyCatalog()
                        .searchByName(name).stream();
                if (!kind.isBlank()) {
                    candidates =
                            candidates.filter(s -> s.getKind().equalsIgnoreCase(bindingStep.getRef().getKind()));
                }
                step = candidates
                        .sorted(Comparator.comparing(s -> KINDS.indexOf(s.getKind().toUpperCase(Locale.ROOT))))
                        .findFirst();

                //knative
                if (step.isPresent()
                        && step.get().getKind().equalsIgnoreCase(KNATIVE)) {
                    for (Parameter p : new ArrayList<>(step.get().getParameters())) {
                        if (p.getId().equalsIgnoreCase("kind")) {
                            p.setValue(bindingStep.getRef().getKind());
                        }
                        if (p.getId().equalsIgnoreCase("name")) {
                            p.setValue(bindingStep.getRef().getName());
                        }
                    }
                }
            }

            if (step.isPresent()) {
                log.trace("Found step " + step.get().getName() + " of kind " + step.get().getKind());
                setValuesOnParameters(step.get(), bindingStep.getProperties());
                setValuesOnParameters(step.get(), bindingStep.getParameters());
            }
        } catch (Exception e) {
            log.warn("Can't parse step -> " + e.getMessage());
        }
        return step.orElse(null);
    }

    private void setValuesOnParameters(final Step step, final Map<String, String> properties) {

        for (Map.Entry<String, String> c : properties.entrySet()) {
            var valid = false;
            for (Parameter p : step.getParameters()) {
                if (p.getId().equalsIgnoreCase(c.getKey())) {
                    p.setValue(p.convertToType(c.getValue()));
                    valid = true;
                    break;
                }
            }
            if (!valid) {
                log.warn("There is an unknown property: " + c);
            }
        }

    }

    private void setValuesOnParameters(final Step step,
                                       final String uri) {

        String path = uri.substring(uri.indexOf(":") + 1);
        if (path.indexOf("?") > -1) {
            path = path.substring(0, path.indexOf("?"));
        }

        String[] pathParts = path.split(":");
        int i = 0;
        Collections.sort(step.getParameters());
        for (Parameter p : step.getParameters()) {
            if (i >= pathParts.length) {
                break;
            }
            if (p.isPath()) {
                p.setValue(p.convertToType(pathParts[i++]));
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
                    p.setValue(p.convertToType(value));
                    break;
                }
            }
        }
    }

    private void processMetadata(final Map<String, Object> res, final ObjectMeta metadata) {
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
