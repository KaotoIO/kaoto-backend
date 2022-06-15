package io.kaoto.backend.api.service.deployment.generator.kamelet;


import io.kaoto.backend.api.service.deployment.generator.DeploymentGeneratorService;
import io.kaoto.backend.model.deployment.kamelet.KameletBinding;
import io.kaoto.backend.model.deployment.kamelet.KameletBindingSpec;
import io.kaoto.backend.model.deployment.kamelet.KameletBindingStep;
import io.kaoto.backend.model.deployment.kamelet.KameletBindingStepRef;
import io.kaoto.backend.model.step.Step;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

@ApplicationScoped
public class KameletBindingDeploymentGeneratorService
        implements DeploymentGeneratorService {

    private static final String CAMEL_CONNECTOR = "CAMEL-CONNECTOR";
    private static final String KAMELET = "KAMELET";
    private static final String KNATIVE = "KNATIVE";
    private static final List<String> KINDS =
            Arrays.asList(CAMEL_CONNECTOR, KAMELET, KNATIVE);

    public String identifier() {
        return "KameletBinding";
    }

    public String description() {
        return "Kamelet Bindings are used to create simple integrations that "
                + "link a start step to an end step with optional "
                + "intermediate action steps.";
    }

    @Override
    public List<String> getKinds() {
        return KINDS;
    }

    @Override
    public String parse(final List<Step> steps,
                        final Map<String, Object> metadata) {

        if (!appliesTo(steps)) {
            return "";
        }

        KameletBindingSpec spec = new KameletBindingSpec();
        int i = 0;

        if (!steps.isEmpty()) {
            Step step = steps.get(i);
            if ("START".equalsIgnoreCase(step.getType())) {
                spec.setSource(createKameletBindingStep(steps.get(i++)));
            }
        }

        for (; i < steps.size() - 1; i++) {
            spec.getSteps().add(createKameletBindingStep(steps.get(i)));
        }

        if (spec.getSteps().isEmpty()) {
            spec.setSteps(null);
        }

        if (steps.size() > 1) {
            spec.setSink(createKameletBindingStep(steps.get(steps.size() - 1)));
        }
        KameletBinding binding = new KameletBinding(String.valueOf(
                metadata.getOrDefault("name", "")), spec);

        Yaml yaml = new Yaml(new Constructor(KameletBinding.class),
                new KameletRepresenter());
        return yaml.dumpAsMap(binding);
    }

    private KameletBindingStep createKameletBindingStep(final Step step) {
        KameletBindingStep kameletStep = new KameletBindingStep();

        String kind = step.getKind();
        if (kind != null) {
            kind = kind.toUpperCase().strip();
        } else {
            //assume a kamelet, we are binding them!
            kind = KAMELET;
        }

        if (CAMEL_CONNECTOR.equals(kind)) {
            StringBuilder prefix = new StringBuilder(step.getName());
            prefix.append(processParameters(step, prefix));
            kameletStep.setUri(prefix.toString());
            kameletStep.setProperties(null);
        } else {
            KameletBindingStepRef ref = new KameletBindingStepRef();
            ref.setName(step.getName());
            if (step.getKind().equalsIgnoreCase("Knative")) {
                ref.setKind("Broker");
                ref.setApiVersion("eventing.knative.dev/v1");

                if (step.getParameters() != null) {
                    for (var p : new ArrayList<>(step.getParameters())) {
                        if (p.getTitle().equalsIgnoreCase("name")
                                && p.getValue() != null) {
                            ref.setName(p.getValue().toString());
                            step.getParameters().remove(p);
                        }
                        if (p.getTitle().equalsIgnoreCase("kind")
                                && p.getValue() != null) {
                            ref.setKind(p.getValue().toString());
                            step.getParameters().remove(p);
                        }
                    }
                }
            }
            kameletStep.setRef(ref);

            if (step.getParameters() != null) {
                for (var p : step.getParameters()) {
                    if (p.getValue() != null) {
                        kameletStep.getProperties().put(
                                p.getId(),
                                p.getValue()
                                        .toString());
                    }
                }
            }
        }

        return kameletStep;
    }

    private String processParameters(final Step step,
                                     final StringBuilder prefix) {
        var sb = new StringBuilder();
        if (!step.getParameters().isEmpty()) {
            sb.append("?");
            for (var property : step.getParameters()) {
                if (property.isPath()) {
                    prefix.append(":");
                    prefix.append(property.getValue());
                } else if (property.getValue() != null) {
                    sb.append(property.getId());
                    sb.append("=");
                    sb.append(property.getValue());
                    sb.append("&");
                }
            }
        }
        return sb.toString();
    }

    @Override
    public boolean appliesTo(final List<Step> steps) {
        return steps.stream().filter(Objects::nonNull)
                .allMatch(
                        s -> getKinds().stream()
                                .anyMatch(
                                        Predicate.isEqual(
                                                s.getKind().toUpperCase())));
    }
}
