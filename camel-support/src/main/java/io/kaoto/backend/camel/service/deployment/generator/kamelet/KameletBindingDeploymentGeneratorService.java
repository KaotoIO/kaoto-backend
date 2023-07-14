package io.kaoto.backend.camel.service.deployment.generator.kamelet;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.kaoto.backend.camel.service.deployment.generator.AbstractDeploymentGeneratorService;
import io.kaoto.backend.api.service.deployment.generator.DeploymentGeneratorService;
import io.kaoto.backend.api.service.step.parser.StepParserService;
import io.kaoto.backend.camel.service.step.parser.kamelet.KameletBindingStepParserService;
import io.kaoto.backend.model.deployment.Deployment;
import io.kaoto.backend.camel.model.deployment.kamelet.KameletBinding;
import io.kaoto.backend.camel.model.deployment.kamelet.KameletBindingStep;
import io.kaoto.backend.camel.model.deployment.kamelet.KameletBindingStepRef;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;
import io.opentelemetry.api.trace.Span;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.v1alpha1.KameletBindingSpec;
import org.jboss.logging.Logger;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static io.kaoto.backend.camel.service.step.parser.kamelet.KameletStepParserService.DESCRIPTION_ANNO;

@ApplicationScoped
public class KameletBindingDeploymentGeneratorService extends AbstractDeploymentGeneratorService {

    private static final String CAMEL_CONNECTOR = "CAMEL-CONNECTOR";
    private static final String KAMELET = "KAMELET";
    private static final String KNATIVE = "KNATIVE";
    private static final List<String> KINDS = Arrays.asList(KAMELET, KNATIVE);
    private static final boolean IGNORE_CAMEL_COMPONENTS = true;
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Logger LOG = Logger.getLogger(KameletBindingDeploymentGeneratorService.class);

    @Inject
    private KameletBindingStepParserService stepParserService;

    @Override
    public String parse(final List<Step> stepList,
                        final Map<String, Object> md,
                        final List<Parameter> parameterList) {

        Map<String, Object> metadata = md != null ? new LinkedHashMap<>(md) : Map.of();
        List<Step> steps = stepList != null ? new LinkedList<>(stepList) : List.of();

        KameletBindingSpec spec;
        var metaObject = new ObjectMeta();
        if (metadata != null && !metadata.isEmpty()) {
            metaObject.setName(String.valueOf(metadata.getOrDefault("name", "")));
            metaObject.setAdditionalProperties(
                    (Map<String, Object>) metadata.getOrDefault("additionalProperties", Collections.emptyMap()));
            metaObject.setAnnotations(
                    (Map<String, String>) metadata.getOrDefault("annotations", new LinkedHashMap<>()));
            metaObject.setLabels((Map<String, String>) metadata.getOrDefault("labels", Collections.emptyMap()));
            if (metadata.containsKey("description")) {
                metaObject.getAnnotations().put(DESCRIPTION_ANNO, String.valueOf(metadata.remove("description")));
            }
            var originalSpec = metadata.remove("spec");
            if (originalSpec instanceof KameletBindingSpec ospec) {
                spec = ospec;
            } else if (originalSpec instanceof Map ospec) {
                spec = MAPPER.convertValue(ospec, KameletBindingSpec.class);
            } else {
                spec = new KameletBindingSpec();
            }
        } else {
            spec = new KameletBindingSpec();
        }

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

        KameletBinding binding = new KameletBinding(spec, metaObject);

        Yaml yaml = new Yaml(new Constructor(KameletBinding.class, new LoaderOptions()), new KameletRepresenter());
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
    public DeploymentGeneratorService.Status getStatus(final CustomResource cr) {
        DeploymentGeneratorService.Status s = DeploymentGeneratorService.Status.Invalid;
        if (cr instanceof KameletBinding binding
                && binding.getStatus() != null) {
            switch (binding.getStatus().getPhase()) {
                case "Ready":
                case "Running":
                    s = DeploymentGeneratorService.Status.Running;
                    break;
                case "Creating":
                case "Building Kit":
                    s = DeploymentGeneratorService.Status.Building;
                    break;
                default:
                    LOG.warn("Detected unrecognized status " + binding.getStatus().getPhase());
                    s = DeploymentGeneratorService.Status.Stopped;
            }
        }
        return s;
    }

    @Override
    public String parse(List<StepParserService.ParseResult<Step>> flows) {
        // migrate upper layer metadata into KameletBinding
        StepParserService.ParseResult<Step> metadata = null;
        StepParserService.ParseResult<Step> binding = null;
        for (StepParserService.ParseResult<Step> parseResult : flows) {
            if (parseResult.getSteps() != null) {
                binding = parseResult;
            } else {
                metadata = parseResult;
            }
        }
        if (metadata != null && metadata.getMetadata() != null) {
            if (binding.getMetadata() == null) {
                binding.setMetadata(new LinkedHashMap<>());
            }
            binding.getMetadata().putAll(metadata.getMetadata());
        }
        return parse(binding.getSteps(), binding.getMetadata(), binding.getParameters());
    }

    @Override
    public List<Class<? extends CustomResource>> supportedCustomResources() {
        return Arrays.asList(new Class[]{KameletBinding.class});
    }

    @Override
    public CustomResource parse(final String input) {
        if (stepParserService.appliesTo(input)) {
            try {
                ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());
                return yamlMapper.readValue(input, KameletBinding.class);
            } catch (Exception e) {
                LOG.trace("Tried creating a kamelet binding and it didn't work.");
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
            LOG.warn("Error extracting the list of integrations.", e);
        }

        return res;
    }

    @Override
    public Stream<Step> filterCatalog(Step previousStep, Step followingStep, Stream<Step> steps) {
        return steps;
    }
}
