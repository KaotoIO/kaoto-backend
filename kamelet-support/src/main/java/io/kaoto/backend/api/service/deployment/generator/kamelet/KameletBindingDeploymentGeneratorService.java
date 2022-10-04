package io.kaoto.backend.api.service.deployment.generator.kamelet;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.kaoto.backend.api.service.deployment.generator.DeploymentGeneratorService;
import io.kaoto.backend.api.service.step.parser.kamelet.KameletBindingStepParserService;
import io.kaoto.backend.model.deployment.Deployment;
import io.kaoto.backend.model.deployment.kamelet.KameletBinding;
import io.kaoto.backend.model.deployment.kamelet.KameletBindingSpec;
import io.kaoto.backend.model.deployment.kamelet.KameletBindingStep;
import io.kaoto.backend.model.deployment.kamelet.KameletBindingStepRef;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;
import io.opentelemetry.api.trace.Span;
import org.jboss.logging.Logger;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

@ApplicationScoped
public class KameletBindingDeploymentGeneratorService
        implements DeploymentGeneratorService {

    private static final String CAMEL_CONNECTOR = "CAMEL-CONNECTOR";
    private static final String EIP = "EIP";
    private static final String KAMELET = "KAMELET";
    private static final String KNATIVE = "KNATIVE";
    private static final List<String> KINDS = Arrays.asList(KAMELET, KNATIVE, EIP);
    private static final boolean IGNORE_CAMEL_COMPONENTS = true;

    @Inject
    private KameletBindingStepParserService stepParserService;

    private Logger log = Logger.getLogger(KameletBindingDeploymentGeneratorService.class);

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
                        final Map<String, Object> metadata,
                        final List<Parameter> parameters) {

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

        if (CAMEL_CONNECTOR.equals(kind) && !IGNORE_CAMEL_COMPONENTS) {
            StringBuilder prefix = new StringBuilder(step.getName());
            for (var property : step.getParameters()) {
                if (property.isPath()) {
                    prefix.append(":");
                    prefix.append(property.getValue());
                    break;
                }
            }
            kameletStep.setUri(prefix.toString());

            if (step.getParameters() != null) {
                for (var p : step.getParameters()) {
                    if (p.getValue() != null && !p.isPath()) {
                        kameletStep.getParameters().put(
                                p.getId(),
                                p.getValue().toString());
                    }
                }
            }
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

            if (step.getParameters() != null) {
                for (var p : step.getParameters()) {
                    if (p.getValue() != null && !p.isPath()) {
                        kameletStep.getProperties().put(
                                p.getId(),
                                p.getValue().toString());
                    }
                }
            }
            kameletStep.setRef(ref);
        }

        return kameletStep;
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

    @Override
    public Status getStatus(final CustomResource cr) {
        Status s = Status.Invalid;
        if (cr instanceof KameletBinding binding
                && binding.getStatus() != null) {
            switch (binding.getStatus().getPhase()) {
                case "Ready":
                case "Running":
                    s = Status.Running;
                    break;
                case "Creating":
                case "Building Kit":
                    s = Status.Building;
                    break;
                default:
                    log.warn("Detected unrecognized status " + binding.getStatus().getPhase());
                    s = Status.Stopped;
            }
        }
        return s;
    }

    @Override
    public List<Class<? extends CustomResource>> supportedCustomResources() {
        return Arrays.asList(new Class[]{KameletBinding.class});
    }

    @Override
    public CustomResource parse(final String input) {
        if (stepParserService.appliesTo(input)) {
            try {
                ObjectMapper yamlMapper =
                        new ObjectMapper(new YAMLFactory())
                                .configure(
                                        DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                                        false);

                return yamlMapper.readValue(input, KameletBinding.class);
            } catch (Exception e) {
                log.trace("Tried creating a kamelet binding and it didn't work.");
            }
        }

        return null;
    }

    @Override
    public Collection<? extends Deployment> getResources(final String namespace, final KubernetesClient kclient) {
        List<Deployment> res = new LinkedList<>();
        try {
            final var resources = kclient.resources(KameletBinding.class).inNamespace(namespace).list();
            for (CustomResource customResource : resources.getItems()) {
                res.add(new Deployment(customResource, getStatus(customResource)));

                if (Span.current() != null) {
                    Span.current().setAttribute("KameletBinding[" + res.size() + "]",
                            res.get(res.size() - 1).toString());
                }
            }
        } catch (Exception e) {
            log.warn("Error extracting the list of integrations.", e);
        }

        return res;
    }

    @Override
    public Pod getPod(final String namespace, final String name, final KubernetesClient kubernetesClient) {

        for (var d : getResources(namespace, kubernetesClient)) {
            if (d.getName().equalsIgnoreCase(name)) {
                var pods = kubernetesClient.pods()
                        .inNamespace(namespace)
                        .withLabel("camel.apache.org/integration=" + d.getName())
                        .list().getItems();

                for (var pod : pods) {
                    if (pod.getStatus() != null
                            && pod.getStatus().getPhase() != null
                            && (pod.getStatus().getPhase().equalsIgnoreCase("Running")
                            || pod.getStatus().getPhase().equalsIgnoreCase("Succeeded"))) {
                        return pod;
                    }
                }

            }
        }

        return null;
    }
}
