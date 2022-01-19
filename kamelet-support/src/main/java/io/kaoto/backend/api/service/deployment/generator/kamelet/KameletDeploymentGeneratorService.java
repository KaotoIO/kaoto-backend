package io.kaoto.backend.api.service.deployment.generator.kamelet;

import io.kaoto.backend.api.service.deployment.generator.DeploymentGeneratorService;
import io.kaoto.backend.model.deployment.kamelet.Kamelet;
import io.kaoto.backend.model.deployment.kamelet.step.SetBodyFlowStep;
import io.kaoto.backend.model.step.Step;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import javax.enterprise.context.ApplicationScoped;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

@ApplicationScoped
public class KameletDeploymentGeneratorService
        implements DeploymentGeneratorService {

    private String defaultIcon;

    @Override
    public List<String> getKinds() {
        return Arrays.asList(new String[]{"Camel-Connector", "EIP"});
    }

    public String identifier() {
        return "Kamelet";
    }

    public KameletDeploymentGeneratorService() {
        try {
            defaultIcon = Files.readString(
                    Path.of(
                            KameletDeploymentGeneratorService.class.getResource(
                                            "defaultIcon.txt")
                                    .toURI()));
        } catch (Exception e) {
            defaultIcon = "";
            //No need to raise any exception, just the icon will be empty
        }
    }

    @Override
    public String parse(final String name, final List<Step> steps) {
        if (!appliesTo(steps)) {
            return "";
        }

        Kamelet kamelet = new Kamelet(name, steps, defaultIcon);

        TypeDescription personDesc = new TypeDescription(SetBodyFlowStep.class);
        personDesc.substituteProperty("set-body", SetBodyFlowStep.class,
                "getSetBody", "setSetBody");
        final var constructor = new Constructor(Kamelet.class);
        constructor.addTypeDescription(personDesc);
        final var representer = new KameletRepresenter();
        representer.addTypeDescription(personDesc);
        Yaml yaml = new Yaml(constructor, representer);
        return yaml.dumpAsMap(kamelet);
    }

    @Override
    public String parse(final List<Step> steps,
                        final Map<String, Object> metadata) {
        return parse(
                metadata.getOrDefault("name", "").toString(),
                steps);
    }

    @Override
    public boolean appliesTo(final List<Step> steps) {
        String[] validSteps = new String[]{"CAMEL-CONNECTOR", "EIP"};
        return steps.size() > 1
                && steps.stream().allMatch(
                s -> Arrays.stream(validSteps)
                        .anyMatch(
                                Predicate.isEqual(s.getKind().toUpperCase())));
    }
}
