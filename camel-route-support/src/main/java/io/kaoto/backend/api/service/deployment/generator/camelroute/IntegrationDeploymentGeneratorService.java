package io.kaoto.backend.api.service.deployment.generator.camelroute;

import io.fabric8.kubernetes.client.CustomResource;
import io.kaoto.backend.api.service.deployment.generator.DeploymentGeneratorService;
import io.kaoto.backend.api.service.deployment.generator.kamelet.KameletDeploymentGeneratorService;
import io.kaoto.backend.model.deployment.camelroute.Integration;
import io.kaoto.backend.model.deployment.kamelet.Kamelet;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

@ApplicationScoped
public class IntegrationDeploymentGeneratorService
        implements DeploymentGeneratorService {

    private static final String CAMEL_CONNECTOR = "CAMEL-CONNECTOR";
    private static final String EIP = "EIP";
    private static final String EIP_BRANCHES = "EIP-BRANCH";
    private static final List<String> KINDS = Arrays.asList(
            CAMEL_CONNECTOR, EIP, EIP_BRANCHES);

    @Inject
    private KameletDeploymentGeneratorService kdgs;

    public IntegrationDeploymentGeneratorService() {
    }

    @Override
    public List<String> getKinds() {
        return KINDS;
    }

    public String identifier() {
        return "Integration";
    }

    public String description() {
        return "An Integration defines a workflow of actions and steps.";
    }

    @Override
    public String parse(final List<Step> steps,
                        final Map<String, Object> metadata,
                        final List<Parameter> parameters) {
        var representer = new IntegrationRepresenter();
        return kdgs.getYAML(new Integration(steps, metadata), representer);
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
        return Arrays.asList(new Class[]{Integration.class});
    }
}
