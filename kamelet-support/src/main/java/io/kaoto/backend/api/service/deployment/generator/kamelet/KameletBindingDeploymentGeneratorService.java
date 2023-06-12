package io.kaoto.backend.api.service.deployment.generator.kamelet;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.kaoto.backend.api.service.deployment.generator.DeploymentGeneratorService;
import io.kaoto.backend.api.service.step.parser.StepParserService;
import io.kaoto.backend.api.service.step.parser.kamelet.KameletBindingStepParserService;
import io.kaoto.backend.model.deployment.Deployment;
import io.kaoto.backend.model.deployment.kamelet.KameletBinding;
import io.kaoto.backend.model.deployment.kamelet.KameletBindingStep;
import io.kaoto.backend.model.deployment.kamelet.KameletBindingStepRef;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;
import io.opentelemetry.api.trace.Span;
import org.apache.camel.v1alpha1.KameletBindingSpec;
import org.jboss.logging.Logger;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@ApplicationScoped
public class KameletBindingDeploymentGeneratorService implements DeploymentGeneratorService {

    private static final String CAMEL_CONNECTOR = "CAMEL-CONNECTOR";
    private static final String KAMELET = "KAMELET";
    private static final String KNATIVE = "KNATIVE";
    private static final List<String> KINDS = Arrays.asList(KAMELET, KNATIVE);
    private static final boolean IGNORE_CAMEL_COMPONENTS = true;

    @Inject
    private KameletBindingStepParserService stepParserService;

    private Logger log = Logger.getLogger(KameletBindingDeploymentGeneratorService.class);

    @Override
    public String parse(final List<Step> steps,
                        final Map<String, Object> metadata,
                        final List<Parameter> parameters) {

        var spec = new KameletBindingSpec();
        spec.setSteps(new LinkedList<>());

        for (int i = 0; i < steps.size(); i++) {
            var step = steps.get(i);
            if (i == 0 && Step.START.equalsIgnoreCase(step.getType())) {
                spec.setSource(createKameletBindingStep(step).getSource());
            } else if (i == steps.size() - 1 && Step.END.equalsIgnoreCase(step.getType())) {
                spec.setSink(createKameletBindingStep(step).getSink());
            } else {
                final var intermediateStep = createKameletBindingStep(step).getSteps();
                if (intermediateStep != null) {
                    spec.getSteps().add(intermediateStep);
                }
            }
        }

        if (spec.getSteps() != null && spec.getSteps().isEmpty()) {
            spec.setSteps(null);
        }

        var name = "";
        if (metadata != null) {
            name = String.valueOf(metadata.getOrDefault("name", ""));
        }

        KameletBinding binding = new KameletBinding(name, spec);

        Yaml yaml = new Yaml(new Constructor(KameletBinding.class), new KameletRepresenter());
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

            Collections.sort(step.getParameters());
            for (var property : step.getParameters()) {
                if (property.isPath()) {
                    prefix.append(property.getPathSeparator());
                    prefix.append(property.getValue());
                }
            }
            kameletStep.setUri(prefix.toString());

            if (step.getParameters() != null) {
                for (var p : step.getParameters()) {
                    if (p.getValue() != null && !p.isPath()) {
                        kameletStep.getParameters().put(p.getId(), p.getValue());
                    }
                }
            }
        } else {
            var ref = new KameletBindingStepRef();
            ref.setName(step.getName());
            if (step.getKind().equalsIgnoreCase("Knative")) {
                ref.setKind("Broker");
                ref.setApiVersion("eventing.knative.dev/v1");

                if (step.getParameters() != null) {
                    for (var p : new ArrayList<>(step.getParameters())) {
                        if (p.getTitle().equalsIgnoreCase("name") && p.getValue() != null) {
                            ref.setName(p.getValue().toString());
                            step.getParameters().remove(p);
                        }
                        if (p.getTitle().equalsIgnoreCase("kind") && p.getValue() != null) {
                            ref.setKind(p.getValue().toString());
                            step.getParameters().remove(p);
                        }
                    }
                }
            }

            if (step.getParameters() != null) {
                for (var p : step.getParameters()) {
                    if (p.getValue() != null && !p.isPath()) {
                        kameletStep.getProperties().put(p.getId(), p.getValue().toString());
                    }
                }
            }
            kameletStep.setRef(ref);
        }

        return kameletStep;
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
    public String parse(List<StepParserService.ParseResult<Step>> flows) {
        StringBuilder sb = new StringBuilder();

        StepParserService.ParseResult<Step> last = flows.stream().reduce((a, b) -> b).orElseThrow();
        flows.stream().forEachOrdered(stepParseResult -> {
            sb.append(parse(stepParseResult.getSteps(), stepParseResult.getMetadata(),
                    stepParseResult.getParameters()));
            if (stepParseResult != last) {
                sb.append("---");
                sb.append(System.lineSeparator());
            }
        });
        return sb.toString();
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

    @Override
    public Stream<Step> filterCatalog(Step previousStep, Step followingStep, Stream<Step> steps) {
        return steps;
    }
}
