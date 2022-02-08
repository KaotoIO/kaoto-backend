package io.kaoto.backend.api.service.deployment.generator.kamelet;


import io.kaoto.backend.api.service.deployment.generator.DeploymentGeneratorService;
import io.kaoto.backend.model.deployment.kamelet.KameletBinding;
import io.kaoto.backend.model.deployment.kamelet.KameletBindingSpec;
import io.kaoto.backend.model.deployment.kamelet.KameletBindingStep;
import io.kaoto.backend.model.deployment.kamelet.KameletBindingStepRef;
import io.kaoto.backend.model.step.Step;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import javax.enterprise.context.ApplicationScoped;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

@ApplicationScoped
public class KameletBindingDeploymentGeneratorService
        implements DeploymentGeneratorService {

    public static final String CAMEL_CONNECTOR = "CAMEL-CONNECTOR";
    public static final String KAMELET = "KAMELET";
    public static final String EIP = "EIP";
    public static final List<String> KINDS = Arrays.asList(
            new String[]{CAMEL_CONNECTOR, KAMELET, EIP});

    public String identifier() {
        return "KameletBinding";
    }

    @Override
    public List<String> getKinds() {
        return KINDS;
    }

    @Override
    public String parse(final String name, final List<Step> steps) {
        if (!appliesTo(steps)) {
            return "";
        }

        KameletBindingSpec spec = new KameletBindingSpec();
        if (steps.size() > 0) {
            spec.setSource(createKameletBindingStep(steps.get(0)));
        }

        for (int i = 1; i < steps.size() - 1; i++) {
            spec.getSteps().add(createKameletBindingStep(steps.get(i)));
        }

        if (spec.getSteps().isEmpty()) {
            spec.setSteps(null);
        }

        if (steps.size() > 1) {
            spec.setSink(createKameletBindingStep(steps.get(steps.size() - 1)));
        }
        KameletBinding binding = new KameletBinding(name, spec);

        Representer representer = new Representer() {
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
        };
        representer.getPropertyUtils().setAllowReadOnlyProperties(true);

        Yaml yaml = new Yaml(new Constructor(KameletBinding.class),
                    representer);
        return yaml.dumpAsMap(binding);
    }

    @Override
    public String parse(final List<Step> steps,
                        final Map<String, Object> metadata) {
        return parse(
                metadata.getOrDefault("name", "").toString(),
                steps);
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
        return steps.stream().allMatch(
                s -> getKinds().stream()
                        .anyMatch(
                                Predicate.isEqual(s.getKind().toUpperCase())));
    }
}
