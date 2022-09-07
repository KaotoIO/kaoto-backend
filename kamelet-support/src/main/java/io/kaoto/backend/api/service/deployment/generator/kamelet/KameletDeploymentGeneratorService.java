package io.kaoto.backend.api.service.deployment.generator.kamelet;

import io.fabric8.kubernetes.client.CustomResource;
import io.kaoto.backend.api.service.deployment.generator.DeploymentGeneratorService;
import io.kaoto.backend.model.deployment.kamelet.Kamelet;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

import javax.enterprise.context.ApplicationScoped;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

@ApplicationScoped
public class KameletDeploymentGeneratorService
        implements DeploymentGeneratorService {

    private static final String CAMEL_CONNECTOR = "CAMEL-CONNECTOR";
    private static final String EIP = "EIP";
    private static final String EIP_BRANCHES = "EIP-BRANCH";
    private static final List<String> KINDS = Arrays.asList(
            CAMEL_CONNECTOR, EIP, EIP_BRANCHES);

    public KameletDeploymentGeneratorService() {
    }

    @Override
    public List<String> getKinds() {
        return KINDS;
    }

    public String identifier() {
        return "Kamelet";
    }

    public String description() {
        return "A Kamelet is a snippet of a route. It defines meta building "
                + "blocks or steps that can be reused on integrations.";
    }

    @Override
    public String parse(final List<Step> steps,
                        final Map<String, Object> metadata,
                        final List<Parameter> parameters) {
        return getYAML(new Kamelet(steps, metadata, parameters),
                new KameletRepresenter());
    }

    public String getYAML(final CustomResource kamelet,
                          final Representer representer) {
        Yaml yaml = new Yaml(
                new Constructor(Kamelet.class),
                representer);
        return yaml.dumpAsMap(kamelet);
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
        if (cr instanceof Kamelet kamelet
                && kamelet.getStatus() != null) {
            switch (kamelet.getStatus().getPhase()) {
                case "Ready":
                    s = Status.Running;
                    break;
                default:
                    s = Status.Stopped;
            }
        }
        return s;
    }

    @Override
    public List<Class<? extends CustomResource>> supportedCustomResources() {
        return Arrays.asList(new Class[]{Kamelet.class});
    }
}
