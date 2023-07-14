package io.kaoto.backend.camel.service.step.parser.kamelet;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.step.parser.StepParserService;
import io.kaoto.backend.camel.KamelHelper;
import io.kaoto.backend.camel.model.deployment.kamelet.KameletBinding;
import io.kaoto.backend.camel.model.deployment.kamelet.KameletBindingStep;
import io.kaoto.backend.camel.service.dsl.kamelet.KameletBindingDSLSpecification;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.v1alpha1.KameletBindingSpec;
import org.apache.camel.v1alpha1.kameletbindingspec.Steps;
import org.jboss.logging.Logger;

import java.util.ArrayList;
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
public class KameletBindingStepParserService implements StepParserService<Step> {

    private static final String[] ROOT_METADATA_NAMES = new String[] { "description" };
    private Logger log = Logger.getLogger(KameletBindingStepParserService.class);

    private StepCatalog catalog;

    @Inject
    public void setCatalog(final StepCatalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public ParseResult<Step> deepParse(final String input) {
        if (!appliesTo(input)) {
            throw new IllegalArgumentException("Wrong format provided. This is not parseable by us.");
        }

        ParseResult<Step> res = new ParseResult<>();

        List<Step> steps = new ArrayList<>();
        Map<String, Object> md = new LinkedHashMap<>();
        try {
            KameletBinding binding = KamelHelper.YAML_MAPPER.readValue(input, KameletBinding.class);
            processMetadata(md, binding.getMetadata());
            processSpec(steps, binding.getSpec());

            //Let's store the spec to make sure we don't lose anything else

            binding.getSpec().setSteps(null);
            binding.getSpec().setSource(null);
            binding.getSpec().setSink(null);
            binding.getSpec().setErrorHandler(null);
            binding.getSpec().setIntegration(null);
            md.put("spec", binding.getSpec());

            String description = binding.getMetadata().getAnnotations().get(KameletStepParserService.DESCRIPTION_ANNO);
            if (description != null) {
                md.put("description", description);
            }


        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Wrong format provided. This is not parseable by us.");
        }

        res.setSteps(steps.stream().filter(Objects::nonNull).toList());
        res.setMetadata(md);
        res.setParameters(Collections.emptyList());
        return res;
    }

    public boolean appliesTo(final String yaml) {
        return yaml.contains("kind: KameletBinding");
    }

    @Override
    public List<ParseResult<Step>> getParsedFlows(String input) {
        var res = new ArrayList<ParseResult<Step>>();
        ParseResult<Step> parsedMeta = new ParseResult<>();
        parsedMeta.setParameters(new ArrayList<>());
        parsedMeta.setMetadata(new LinkedHashMap<>());
        res.add(parsedMeta);

        var binding = deepParse(input);
        res.add(binding);
        // move root metadata to a dedicated ParseResult to align with other DSLs
        parsedMeta.getMetadata().put("name", binding.getMetadata().get("name"));
        for (String metadataName : ROOT_METADATA_NAMES) {
            if (binding.getMetadata().containsKey(metadataName)) {
                parsedMeta.getMetadata().put(metadataName, binding.getMetadata().remove(metadataName));
            }
        }
        return res;
    }

    private void processSpec(final List<Step> steps, final KameletBindingSpec spec) {
        var kbsSource = spec.getSource() != null
                ? new KameletBindingStep(spec.getSource())
                : new KameletBindingStep();
        steps.add(processStep(kbsSource, Step.Type.START));

        if (spec.getSteps() != null) {
            for (Steps intermediateStep : spec.getSteps()) {
                var kbsStep = intermediateStep != null
                        ? new KameletBindingStep(intermediateStep)
                        : new KameletBindingStep();
                steps.add(processStep(kbsStep, Step.Type.MIDDLE));
            }
        }

        var kbsSink = spec.getSink() != null
                ? new KameletBindingStep(spec.getSink())
                : new KameletBindingStep();
        steps.add(processStep(kbsSink, Step.Type.END));
    }

    private Step processStep(final KameletBindingStep bindingStep, final Step.Type type) {
        Optional<Step> step = Optional.empty();

        try {
            if (bindingStep.getUri() != null) {
                log.trace("Found uri component. Probably a Camel Conector.");
                String uri = bindingStep.getUri();
                step = catalog.getReadOnlyCatalog()
                        .searchByName(uri.substring(0, uri.indexOf(":")))
                        .stream()
                        .filter(s ->
                                KameletBindingDSLSpecification.KINDS.stream()
                                        .anyMatch(k -> s.getKind().equalsIgnoreCase(k)))
                        .sorted(Comparator.comparing(
                                s -> KameletBindingDSLSpecification.KINDS
                                        .indexOf(((Step) s).getKind().toUpperCase(Locale.ROOT))).reversed())
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

                var candidates = catalog.getReadOnlyCatalog().searchByName(name).stream();
                candidates = candidates.filter(s ->
                        KameletBindingDSLSpecification.KINDS.stream().anyMatch(k -> s.getKind().equalsIgnoreCase(k)));
                if (!kind.isBlank()) {
                    candidates =
                            candidates.filter(s -> s.getKind().equalsIgnoreCase(bindingStep.getRef().getKind()));
                }
                candidates = candidates.filter(s -> s.getType().equals(type.name()));
                step = candidates
                        .sorted(Comparator.comparing(s ->
                                KameletBindingDSLSpecification.KINDS.indexOf(s.getKind().toUpperCase(Locale.ROOT))))
                        .findFirst();

                //knative
                if (step.isPresent()
                        && step.get().getKind().equalsIgnoreCase(KameletBindingDSLSpecification.KNATIVE)) {
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

    private void setValuesOnParameters(final Step step, final Map<String, Object> properties) {

        for (Map.Entry<String, Object> c : properties.entrySet()) {
            var valid = false;
            for (Parameter<?> p : step.getParameters()) {
                if (p.getId().equalsIgnoreCase(c.getKey())) {
                    final var value = p.convertToType(c.getValue());
                    if (!value.equals(p.getDefaultValue())) {
                        p.setValue(value);
                    }
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

        var splitSeparators =
                step.getParameters().stream().filter(Objects::nonNull)
                        .map(p -> p.getPathSeparator()).distinct().reduce((s, s2) -> s + "|" + s2).orElse(":");
        String[] pathParts = path.split(splitSeparators);
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

}
