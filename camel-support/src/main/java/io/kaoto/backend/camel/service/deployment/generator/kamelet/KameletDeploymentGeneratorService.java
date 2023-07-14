package io.kaoto.backend.camel.service.deployment.generator.kamelet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.deployment.generator.DeploymentGeneratorService;
import io.kaoto.backend.api.service.step.parser.StepParserService;
import io.kaoto.backend.camel.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.deployment.Deployment;
import io.kaoto.backend.camel.model.deployment.kamelet.Kamelet;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;
import io.opentelemetry.api.trace.Span;
import org.jboss.logging.Logger;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@ApplicationScoped
public class KameletDeploymentGeneratorService implements DeploymentGeneratorService {
    private static final Logger LOG = Logger.getLogger(KameletDeploymentGeneratorService.class);

    private KameletStepParserService stepParserService;

    private StepCatalog catalog;


    public KameletDeploymentGeneratorService() {
    }


    @Override
    public String parse(final List<Step> steps,
                        final Map<String, Object> metadata,
                        final List<Parameter> parameters) {
        return getYAML(new Kamelet(
                        steps != null ? new ArrayList<>(steps) : List.of(),
                        metadata != null ? new LinkedHashMap<>(metadata) : Map.of(),
                        parameters != null ? new ArrayList<>(parameters) : List.of(),
                        catalog),
                new KameletRepresenter());
    }

    @Override
    public String parse(List<StepParserService.ParseResult<Step>> flows) {
        // migrate upper layer metadata into kamelet local
        StepParserService.ParseResult<Step> metadata = null;
        StepParserService.ParseResult<Step> route = null;
        for (StepParserService.ParseResult<Step> parseResult : flows) {
            if (parseResult.getSteps() != null) {
                route = parseResult;
            } else {
                metadata = parseResult;
            }
        }
        if (metadata != null && metadata.getMetadata() != null) {
            if (route.getMetadata() == null) {
                route.setMetadata(new LinkedHashMap<>());
            }
            route.getMetadata().putAll(metadata.getMetadata());
        }
        return parse(route.getSteps(), route.getMetadata(), route.getParameters());
    }

    public String getYAML(final CustomResource kamelet,
                          final Representer representer) {
    Yaml yaml = new Yaml(
                new Constructor(Kamelet.class, new LoaderOptions()),
                representer);
        return yaml.dumpAsMap(kamelet);
    }

    @Override
    public Status getStatus(final CustomResource cr) {
        Status s = Status.Invalid;
        if (cr instanceof Kamelet kamelet
                && kamelet.getStatus() != null) {
            switch (kamelet.getStatus().getPhase()) {
                case "Ready":
                    s = Status.Ready;
                    break;
                default:
                    LOG.warn("Detected unrecognized status " + kamelet.getStatus().getPhase());
            }
        }
        return s;
    }

    @Override
    public List<Class<? extends CustomResource>> supportedCustomResources() {
        return Arrays.asList(new Class[]{Kamelet.class});
    }

    @Override
    public CustomResource parse(final String input) {
        if (stepParserService.appliesTo(input)) {
            try {
                ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());
                return yamlMapper.readValue(input, Kamelet.class);
            } catch (Exception e) {
                LOG.trace("Tried creating a kamelet and it didn't work.");
            }
        }
        return null;
    }

    @Override
    public Collection<? extends Deployment> getResources(final String namespace, final KubernetesClient kclient) {
        List<Deployment> res = new ArrayList<>();
        try {
            final var resources = kclient.resources(Kamelet.class).inNamespace(namespace).list();
            for (CustomResource customResource : resources.getItems()) {
                res.add(new Deployment(customResource, getStatus(customResource)));

                if (Span.current() != null) {
                    Span.current().setAttribute("Kamelet[" + res.size() + "]", res.get(res.size() - 1).toString());
                }
            }
        } catch (Exception e) {
            LOG.warn("Error extracting the list of integrations.", e);
        }

        return res;
    }

    @Override
    public Pod getPod(final String namespace, final String name, final KubernetesClient kubernetesClient) {
        //There is no pod associated to Kamelets
        return null;
    }

    @Override
    public Stream<Step> filterCatalog(Step previousStep, Step followingStep, Stream<Step> steps) {
        return steps;
    }

    @Inject
    public void setStepParserService(final KameletStepParserService stepParserService, final StepCatalog catalog) {
        this.stepParserService = stepParserService;
        this.catalog = catalog;
    }
}
