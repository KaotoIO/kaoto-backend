package io.kaoto.backend.api.service.deployment.generator.kamelet;

import io.kaoto.backend.api.service.deployment.generator.DeploymentGeneratorService;
import io.kaoto.backend.model.deployment.kamelet.Kamelet;
import io.kaoto.backend.model.step.Step;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import javax.enterprise.context.ApplicationScoped;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@ApplicationScoped
public class KameletDeploymentGeneratorService
        implements DeploymentGeneratorService {

    private String defaultIcon;

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

        Yaml yaml = new Yaml(new Constructor(Kamelet.class),
                new KameletRepresenter());
        return yaml.dumpAsMap(kamelet);
    }

    @Override
    public boolean appliesTo(final List<Step> steps) {
        return steps.size() > 1
                && steps.stream().allMatch(
                s -> "Camel-Connector".equalsIgnoreCase(s.getKind()));
    }
}
